<template>
  <div class="inner-padding">
      <iu-flex valign="center" class="header">
        <el-button type="text" @click="goBack" icon="el-icon-back">返回</el-button>
        <div v-if="ruleForm.title" class="pl-1 ml-1 line-l">\{{ruleForm.title}}</div>
      </iu-flex>
      <el-form class="customize-form" :model="ruleForm" :rules="rules" label-position="left" ref="ruleForm" label-width="100px">
        <el-form-item label="文章标题" prop="title">
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="信息来源" prop="source">
              <el-input v-model="ruleForm.source"></el-input>
            </el-form-item>
            <el-form-item label="发布组织" prop="deptId">
              <el-cascader
                v-model="ruleForm.deptId"
                :options="deptList"
                :props="{ checkStrictly: true,value:'id' ,emitPath:false}"
                clearable>
              </el-cascader>
            </el-form-item>
            <el-form-item label="信息类型" prop="typeName">
              <el-input placeholder="请输选择信息类型" readonly="readonly" v-model="ruleForm.typeName">
                <el-button @click="typeBox=true;" icon="el-icon-position" slot="append">选择信息类型</el-button>
              </el-input>
            </el-form-item>
            <el-row>
              <el-col :span="12">
                <el-form-item label="是否置顶" prop="topFlag">
                  <el-switch
                    v-model="ruleForm.topFlag"
                    active-value="1"
                    inactive-value="0"
                    active-text="置顶"
                    inactive-text="不置顶">
                  </el-switch>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="状态" prop="status">
                  <el-switch
                    v-model="ruleForm.status"
                    active-value="1"
                    inactive-value="0"
                    active-text="上线"
                    inactive-text="不上线">
                  </el-switch>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="12">
            <el-form-item label="标题图片" ref="logo" prop="logo">
              <iu-flex nowrap>
                <el-upload
                  ref="upload"
                  class="upload-logo"
                  :headers="uploadHeaders"
                  :on-success="logoSuccess"
                  :on-error="logoError"
                  :before-upload="logoBefore"
                  accept="image/jpg,image/jpeg,image/png"
                  :on-exceed="()=>{$message.warning('最多上传1个文件，请先删除再上传!')}"
                  :file-list="fileList"
                  list-type="picture-card"
                  :limit="1"
                  :action="uploadUrl">
                  <i class="el-icon-plus"></i>
                  <div slot="file" slot-scope="{file}">
                  <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
                  <span class="el-upload-list__item-actions">
                    <span class="el-upload-list__item-preview" @click="previewImg=true">
                      <i class="el-icon-zoom-in"></i>
                    </span>
                    <span class="el-upload-list__item-delete" @click="$refs.upload.clearFiles()">
                      <i class="el-icon-delete"></i>
                    </span>
                  </span>
                </div>
                  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10MB</div>
                </el-upload>
              </iu-flex>
            </el-form-item>
          </el-col>
        </el-row>
        <quill-editor v-model="ruleForm.content"
              class="quill-editor"
              ref="myQuillEditor"
              :options="editorOption"
              @blur="onEditorBlur($event)"
              @focus="onEditorFocus($event)"
              @ready="onEditorReady($event)">
        </quill-editor>
      </el-form>
      <iu-flex align="center" valign="center">
        <el-button type="primary" icon="el-icon-tickets" @click="submitForm">保存</el-button>
        <el-button icon="el-icon-refresh" @click="resetForm">重置</el-button>
      </iu-flex>
      <el-dialog
        title="信息类型"
        :visible.sync="typeBox"
        width="50%">
        <el-button icon="el-icon-plus" size="small" class="mb-l" type="primary" @click="append('#')">新增根节点</el-button>
        <el-tree
          :data="typeList"
          show-checkbox
          node-key="id"
          ref="typeTree"
          :check-strictly="true"
          :props="{label:'name',children:'pubNewsTypes'}"
          :default-checked-keys="[this.ruleForm.type]"
          :default-expanded-keys="[this.ruleForm.type]"
          @check-change="handleNodeClick">
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <el-input v-focus class="edit-input" ref="treeEditInput" v-model="data.name" @blur="edit(data,()=>{$set(data,'edit',false);})" v-if="data.edit" size="mini">\{{ node.name }}</el-input>
            <span v-else>\{{ node.label }}</span>
            <span>
              <el-button
                type="text"
                size="mini"
                icon="el-icon-edit"
                @click="$set(data,'edit',true)">
              </el-button>
              <el-button
                type="text"
                size="mini"
                icon="el-icon-plus"
                @click="() => append(node,data)">
              </el-button>
              <el-button
                type="text"
                icon="el-icon-delete"
                size="mini"
                @click="() => remove(node, data)">
              </el-button>
            </span>
          </span>
        </el-tree>
        <span slot="footer" class="dialog-footer">
          <el-button @click="typeBox = false">取 消</el-button>
          <el-button type="primary" @click="selectType">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog :visible.sync="previewImg">
        <img width="100%" :src="fileList[0]&&fileList[0].url" alt="">
      </el-dialog>
  </div>
</template>

<script>
import config from "@/api/app.config.js";
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'
import Cgi from "./api/cgi.js"
import IwebSdk from "@jangod/iweb-sdk"
export default {
  name: "demo",
  components: {
    //组件
    quillEditor
  },
  directives:{
    focus:{
      inserted:(el)=>{
        el.children[0].focus()
      }
    }
  },
  data() {
    return {
        ruleForm: {
          title: '',
          region: '',
          source: '',
          deptId: '',
          type: "",
          typeName: "",
          status: "0",
          topFlag: '0',
          logo: '',
          content:""
        },
        deptList:[
          {
            value: 'zhinan',
            id: "0",
            label: '测试组织',
            children: [{
              value: 'shejiyuanze',
              id:"01",
              label: '组织1'
            }, {
              value: 'daohang',
              label: '组织2',
              id:"02",
            }]
          },
          {
            value: 'zujian',
            id:"1",
            label: '部门组织',
            children: [
              {
                value: 'ceshi1',
                id:"11",
                label: '测试部门1',
              },
              {
                value: 'ceshi2',
                id:"12",
                label: '测试部门2',
              }
            ]
          }
        ],
        rules: {
          title: [
            { required: true, message: '请输入文章标题', trigger: 'blur' }
          ],
          source: [
            { required: true, message: '请输入信息来源', trigger: 'change' }
          ],
          deptId: [
            { required: true, message: '请选择发布组织', trigger: 'change' }
          ],
          typeName: [
            { required: true, message: '请选择信息类型', trigger: 'change' }
          ],
          status: [
            { required: true, message: '请选择状态', trigger: 'change' }
          ],
          topFlag: [
            { required: true, message: '请选择是否置顶', trigger: 'change' }
          ],
          logo: [
            { required: true, message: '请上传标题图片', trigger: 'change' }
          ]
        },
        editorOption:{
          placeholder:"请在此处输入新闻内容...",
          modules:{
            toolbar:[
              ['bold', 'italic', 'underline', 'strike'],
              ['blockquote', 'code-block'],
              [{'header': 1}, {'header': 2}],
              [{'list': 'ordered'}, {'list': 'bullet'}],
              [{'script': 'sub'}, {'script': 'super'}],
              [{'indent': '-1'}, {'indent': '+1'}],
              // [{'direction': 'rtl'}],
              [{'size': ['small', false, 'large', 'huge']}],
              [{'header': [1, 2, 3, 4, 5, 6, false]}],
              [{'color': []}, {'background': []}],
              [{'font': []}],
              [{'align': []}],
              ['clean'],
              [ 
               'link',
               'image',
              //  'video'
              ]
            ]
          }
        },
        typeBox:false,
        typeList:[],
        uploadUrl: config.base_url+Cgi.uploadLogo,
        uploadHeaders:{},
        fileList:[],
        previewImg: false
    }
  },
  props:{

  },
  watch: {
  },
  computed: {
  },
  mounted() {
    this.init();
    this.getTypeList();
  },
  methods: {
    //初始化
    init(){
      let Http = new IwebSdk.Http();
      this.uploadHeaders = Http.doCloundRequest();
      this.uploadHeaders["x-aep-token"] = this.$store.getters.token;
      this.getInfo();
    },
    //获取新闻详情
    getInfo(){
      this.$http_get(Cgi.getNewsInfo,{id:this.$route.query.id}).then((res)=>{
          this.ruleForm = Object.assign({},this.ruleForm,res.row);
          this.getDocToken();
      }).catch((e)=>{
          console.log("请求失败:",e)
      })
    },
    //获取图片预览token
    getDocToken(){
      if(!this.ruleForm.logo){return}
      this.$http_get(Cgi.docGetToken,{docId:this.ruleForm.logo}).then((res)=>{
          this.fileList.push({
            name: "",
            url: config.base_url+Cgi.previewLogo+"?token="+res.row
          })
      }).catch((e)=>{
          console.log("请求失败:",e)
      })
    },
    //获取新闻类型
    getTypeList(){
      this.$http_get(Cgi.listNewsType,{}).then((res)=>{
          this.typeList = res.rows;
      }).catch((e)=>{
          console.log("请求失败:",e)
      })
    },
    //提交
    submitForm() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.$http_post(Cgi.saveNews,{
            "content": this.ruleForm.content,
            "deptId": this.ruleForm.deptId,
            "id": this.$route.query.id,
            "logo": this.ruleForm.logo,
            "source": this.ruleForm.source,
            "status": this.ruleForm.status,
            "title": this.ruleForm.title,
            "topFlag": this.ruleForm.topFlag,
            "type": this.ruleForm.type
          },true).then((res)=>{
              if(res.state==1){
                this.$message.success("保存成功");
                if(!this.$route.query.id){
                  this.$router.go(-1);
                }
              }else{
                this.$message.error("保存失败:"+res.message);
              }
          }).catch((e)=>{
              console.log("请求失败:",e)
          })
        } else {
          return false;
        }
      });
    },
    //重置
    resetForm(formName) {
      this.$refs.ruleForm.resetFields();
      this.ruleForm.content = "";
      this.$refs.upload.clearFiles();
    },
    //上传
    logoSuccess(response, file, fileList){
      if(response.state==1){
        this.ruleForm.logo = response.row.docid;
        this.$refs.logo.$emit('el.form.change');
      }else{
        this.$message.error("上传失败，请重试!");
        this.$refs.upload.clearFiles();
      }
    },
    logoError(err, file, fileList){
      this.$message.error("上传失败，请重试!")
    },
    logoBefore(file){
      const isJPG = file.type === 'image/jpeg'||file.type==="image/jpg"||file.type==="image/png";
      const isLt2M = file.size / 1024 / 1024 < 10;
      if (!isJPG) {
        this.$message.error('上传图片只能是 jpg/png 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 10MB!');
      }
      return isJPG && isLt2M;
    },
    //富文件编辑器
    onEditorBlur(){

    },
    onEditorFocus(){

    },
    onEditorReady(){

    },
    //信息类型树组件
    edit(data,callback){
      this.$http_post(Cgi.saveNewsType,data,true).then((res)=>{
        if(res.state==1){
          callback()
        }else{
          this.$message.error("修改失败，请重试!")
        }
      }).catch((e)=>{
          console.log("请求失败:",e)
      })
    },
    append(node,data) {
      let newChild = { id: "", name: node=="#"?'新增根节点':'新增节点', pubNewsTypes: [] };
      this.$http_post(Cgi.saveNewsType,{
        name: newChild.name,
        parentId: node=="#"?"#":data.id,
        status: "1"
      },true).then((res)=>{
        if(res.state==1){
          newChild.id = res.row;
          if(node=="#"){
            this.typeList.push(newChild);
          }else{
            if (!data.pubNewsTypes) {
              this.$set(data, 'pubNewsTypes', []);
            }
            data.pubNewsTypes.push(newChild);
          }
        }else{
          this.$message.error("新增失败:"+res.message);
        }
      }).catch((e)=>{
          console.log("请求失败:",e)
      })
    },
    remove(node, data) {
      this.$http_post(Cgi.delNewsType,{
        id: data.id,
      }).then((res)=>{
        if(res.state==1){
          const parent = node.parent;
          const pubNewsTypes = parent.data.pubNewsTypes || parent.data;
          const index = pubNewsTypes.findIndex(d => d.id === data.id);
          pubNewsTypes.splice(index, 1);
        }else{
          this.$message.error("删除失败:"+res.message);
        }
      }).catch((e)=>{
          console.log("请求失败:",e)
      })
    },
    handleNodeClick(data,checked,node){
      if(checked===true){
        this.$refs.typeTree.setCheckedKeys([data.id]);
      }
    },
    selectType(){
      let result = this.$refs.typeTree.getCheckedNodes();
      if(result.length<1){
        this.$message.warning("请选择信息类型");
        return
      }
      this.ruleForm.type = result[0].id;
      this.ruleForm.typeName = this.$refs.typeTree.getNode(result[0].id).label;
      this.typeBox = false;
    },
    //返回
    goBack(){
      this.$router.go(-1);
    }
  }
};
</script>

<style lang="less" scoped>
/deep/.customize-form{
  .el-row{
    .el-col{
      &:nth-child(2){
        .el-form-item{
          .el-form-item__label{
            padding-left: 15px;
          } 
        }
      }
    }
  }
}
.header{
  margin-top: -10px;
}
/deep/.quill-editor{
  height: 350px;
  .ql-container{
    height: 85%;
  }
}
.line{
  text-align: center;
}
.el-date-editor,.el-cascader{
  width: 100%;
}
.line{
  &-l,&-r{
    position: relative;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
  }
  &-l{
    
    &::before{
      content: "";
      position: absolute;
      left: 0px;
      height: 15px;
      width: 1px;
      background: black;
      display: inline-block;
    }
  }
  &-r{
    &::after{
      content: "";
      position: absolute;
      right: 0px;
      height: 15px;
      width: 1px;
      background: black;
      display: inline-block;
    }
  }
}
/deep/.custom-tree-node{
  display: flex;
  align-items: center;
  span:nth-child(2){
    margin-left: 15px;
  }
  .edit-input{
    input{
      border: none;
      background: transparent;
      border-bottom: 1px solid #ccc;
      height: 25px;
      border-radius: 0px!important;
    }
  }
}
</style>
