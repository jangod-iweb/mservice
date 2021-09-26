<template>
  <div class="inner-padding">
      <base-header @search="search" :list="headerList">
        <el-button size="small" @click="add" icon="el-icon-plus" type="primary">新增记录</el-button>
      </base-header>
      <el-table :data="tableData" ref="table" :height="tableHeight">
        <el-table-column
            prop="title"
            label="标题">
        </el-table-column>
        <el-table-column
            width="200"
            prop="typeName"
            label="类型">
        </el-table-column>
        <el-table-column
            width="270"
            prop="source"
            label="来源">
        </el-table-column>
        <el-table-column
            width="100"
            align="center"
            label="状态">
            <template slot-scope="scope">
                <el-tag :type="scope.row.topFlag==1?'success':'danger'">\{{scope.row.topFlag==1?"上线":"下线"}}</el-tag>
            </template>
        </el-table-column>
        <el-table-column
            width="100"
            align="center"
            label="是否置顶">
            <template slot-scope="scope">
                <el-tag :type="scope.row.topFlag==1?'success':'danger'">\{{scope.row.topFlag==1?"是":"否"}}</el-tag>
            </template>
        </el-table-column>
        <el-table-column
            align="right"
            fixed="right"
            width="180"
            label="操作">
            <template slot-scope="scope">
                <el-button size="small" icon="el-icon-edit" type="text" @click="edit(scope.row)">编辑</el-button>
                <el-button class="text-danger" size="small" icon="el-icon-delete" type="text" @click="remove(scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-pagination
      class="pagination"
      @current-change="onPagination"
      :page-size="this.pagination.limit"
      layout="total, prev, pager, next, jumper"
      :total="this.pagination.total">
    </el-pagination>
  </div>
</template>

<script>
import config from "@/api/app.config.js";
import baseHeader from "../../component/header.vue"
import Cgi from "./api/cgi.js"
export default {
  name: "demo",
  components: {
    //组件
    baseHeader
  },
  data() {
    return {
        headerList:[
            {name:"标题",code:"title",value:""},
            {name:"类型",code:"type",value:"",type:"cascader",list:[],props:{label:'name',value:'id',children:'pubNewsTypes'}},
            {name:"状态",code:"status",value:"",type:"select",list:[
                {name:"上线",value:"1"},
                {name:"下线",value:"0"}
            ]}
        ],
        parameter:{},
        tableHeight: 200,
        tableData: [],
        pagination:{
            page: 1,
            limit: 10,
            total: 0
        }
    }
  },
  props:{

  },
  watch: {
  },
  computed: {
  },
  mounted() {
    this.$nextTick(function () {
        this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop - 80;
        // 监听窗口大小变化
        window.onresize = ()=> {
            this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop - 80
        }
    })
    this.getTypeList();
    this.getList();
  },
  methods: {
    //搜索、重置按钮
    search(val,type){
        this.parameter = val;
        this.getList();
    },
    //获取新闻类型
    getTypeList(){
        this.$http_get(Cgi.listNewsType,{}).then((res)=>{
            this.headerList[1].list = res.rows;
        }).catch((e)=>{
            console.log("请求失败:",e)
        })
    },
    //获取表格数据
    getList(){
        let parameter = Object.assign({},{
            page: this.pagination.page,
            rows: this.pagination.limit,
        },this.parameter)
        this.$http_get(Cgi.paginateNews,parameter).then((res)=>{
            this.tableData = res.rows;
            this.pagination.total = res.records;
        }).catch((e)=>{
            console.log("请求失败:",e)
        })
    },
    //分页
    onPagination(val){
        this.pagination.page = val;
        this.getList();
    },
    //新增
    add(){
        this.$router.push({
            path:"info"
        });
    },
    //编辑
    edit(row){
        this.$router.push({
            path:"info",
            query:{
                id: row.id
            }
        });
    },
    //删除
    remove(row){
        this.$confirm('正在删除文章'+row.title+', 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$http_post(Cgi.delNews,{
                id: row.id
            }).then((res)=>{
                this.$message.success("删除成功");
                this.getList();
            }).catch((e)=>{
                console.log("请求失败:",e)
            })
        }).catch(() => {});
    }
  },
  destroyed() {
      window.onresize = null;
  }
};
</script>

<style lang="less" scoped>
.pagination{
    padding-top: 10px;
    text-align: right;
}
</style>
