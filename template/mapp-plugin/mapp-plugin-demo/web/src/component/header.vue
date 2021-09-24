<template>
  <iu-flex align="between" class="header-box">
    <iu-flex valign="center">
      <iu-flex class="item" valign=center :key="index" v-for="(item,index) in data">
        <div class="lable">\{{item.name}}</div>
        <el-select v-if="item.type=='select'" clearable size="small" v-model="item.value" :placeholder="'请选择'+item.name">
          <el-option
            v-for="temp in item.list"
            :key="temp.value"
            :label="temp.name"
            :value="temp.value">
          </el-option>
        </el-select>
        <el-cascader v-else-if="item.type=='cascader'" clearable size="small" v-model="item.value" :options="item.list" :props="item.props" :show-all-levels="false"></el-cascader>
        <el-input size="small" v-else class="input" :placeholder="'请输入'+item.name" v-model="item.value"></el-input>
      </iu-flex>
      <el-button size="small" class="ml-1" type="primary" icon="el-icon-search" @click="search">查询</el-button>
      <el-button size="small" icon="el-icon-refresh" @click="refresh">重置</el-button>
    </iu-flex>
    <div>
      <slot/>
    </div>
  </iu-flex>
</template>

<script>
import config from "@/api/app.config.js";
export default {
  name: "demo",
  components: {
    //组件
  },
  data() {
    return {
      data:[]
    }
  },
  props:{
    list:{
      type:Array,
      default:()=>{
        return []
      }
    }
  },
  watch: {
    list:{
      handler(){
        this.data = JSON.parse(JSON.stringify(this.list))
      },
      deep: true
    }
  },
  computed: {
  },
  mounted() {
    this.data = JSON.parse(JSON.stringify(this.list))
  },
  methods: {
    search(){
      let val = {};
      for(let i=0,len=this.data.length;i<len;i++){
        val[this.data[i].code] = this.data[i].value;
      }
      this.$emit("search",val,"search");
    },
    refresh(){
      let val = {};
      for(let i=0,len=this.data.length;i<len;i++){
        this.data[i].value = "";
        val[this.data[i].code] = "";
      }
      this.$emit("search",val,"refresh");
    }
  }
};
</script>

<style lang="less" scoped>
.header-box{
  .item{
    .lable{
        color: #606266;
        font-size: 15px;
        margin: 0px 10px;
    }
    &:first-child{
      .lable{
        margin-left: 0px;
      }
    }
  }
  .input{
    width: 200px;
  }
}
</style>
