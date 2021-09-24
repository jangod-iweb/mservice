'use strict';
const prefix = "/{{name}}-plugin-{{pluginName}}";
export default {
    paginateNews: prefix+"/news/paginateNews",//查询新闻咨列表
    delNews: prefix+"/news/delNews",//删除新闻资讯
    getNewsInfo: prefix+"/news/getNewsInfo",//查询新闻资讯详情
    listNewsType: prefix+"/news/listNewsType",//查询新闻类型列表
    saveNewsType: prefix+"/news/saveNewsType",//保存新闻类型
    delNewsType: prefix+"/news/delNewsType",//删除新闻类型
    saveNews: prefix+"/news/saveNews",//保存新闻资讯
    uploadLogo: prefix+"/file/upload",//上传标题图片
    previewLogo: prefix+"/file/download",//标题图片预览地址
    docGetToken: prefix+"/file/getToken"//获取预览token
}