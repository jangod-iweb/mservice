# 快速入门

> 假设`{{name}}`为项目名称,`{{pluginName}}`为`{{name}}`项目中的一个业务模块

## 开发环境

    jdk（ version >= 1.8 ）
    idea （用于编译运行后端程序）
    maven（ version >= 3.0.0 ）
    cnpm （用于编译运行前端程序）
    vuepress （用于构建运行在线文档）


## 本地调试

    1、后端启动
    
        运行（或调试）{{name}}/{{name}}-runner中{{#firstToUpper name}}{{/firstToUpper}}RunnerApplication。
        浏览器访问：http://127.0.0.1:{{backendPort}}/doc.html

    2、前端启动

        cd {{name}}/{{name}}-plugin-{{pluginName}}/web
        cnpm run dev
        浏览器访问：http://localhost:{{webPort}}/

    3、在线文档
        
        cd {{name}}/{{name}}-plugin-{{pluginName}}/web-docs
        cnpm run dev
        浏览器访问：http://localhost:{{webDocPort}}/p/{{name}}-plugin-{{pluginName}}/docs/

## 后端结构
```
├── {{name}}-api                        // 对外接口模块(主要包括api接口类，bean及第三方依赖包)
│   ├── {{name}}-api-{{pluginName}}     // 业务模块地外接口类
│   ├── {{name}}-thirds-activity        // 第三方定义依赖包定义类，主要用于容器发布
│   └──pom.xml              	        // 基础依赖包(主版本依赖定义到此pom中)
├── {{name}}-plugin					    // 插件模块                         
│   ├── {{name}}-plugin-{{pluginName}}	// 业务模块
│   │    └──src       			        // 业务后端代码目录
│   │    └──web       			        // 业务前端代码目录,参考前端结构
│   │    └──pom.xml       		        // 依赖文件，非自身需要的请指定<scope>provided</scope>
│   ├── disabled.txt                    // 禁用的插件
│   ├── enabled.txt                     // 启用的插件
│   └── pom.xml                         // 插件依赖
├── {{name}}-runner              // 系统启动程序
└── pom.xml               		        // 模块依赖
```

### 插件模块结构
> main下目录定义
```
├── java                      	 
│   ├── config              	        // 配置类目录
│   ├── controller      		        // 控制层
│   ├── mapper      		 	        // 持久层
│   ├── properties      		        // 配置类定义
│   ├── service      		            // 服务层
│   ├── util      		     	        // 内部工具类
│   └── {{#firstToUpper name}}{{/firstToUpper}}{{#firstToUpper pluginName}}{{/firstToUpper}}Plugin.java     // 插件定义类
├── resource           
│   ├── db			 			        // 数据库初始化类
│   ├── mybatis/mappers/{{pluginName}}  // mybatis xml文件
│   ├── {{name}}-plugin-{{pluginName}}.yml		        // 插件配置文件
│   └── plugin.properties               // 插件描述文件
```

### 系统启动程序({{name}}-runner)
> main下目录定义
```
├── build    					 	    // 程序启动脚本
├── java                      	 
│   └── {{#firstToUpper name}}{{/firstToUpper}}RunnerApplication      	// 程序启动类
├── resource           
│   ├── application.xml		     	    // 本地环境配置文件
│   ├── bootstrap.yml		     	    // 主配置文件
│   ├── license		 			 	    // 插件配置文件
│   └── logback.xml        		 	    // 日志文件
```

## 前端结构
```
├── public                              // 静态资源
│    └──index.html                      // 静态模版
├── src                         
│   ├── api
│   │    └──app.config.js               // 私有配置文件（页面内需要使用配置文件信息引入[import AppConfig from "@/api/app.config.js"]）
│   ├── pages                           // 页面文件目录
│   ├── router                          // 路由配置
│   │    └──index.js            
│   ├── store                           // 全局变量配置store(参考文档:https://vuex.vuejs.org/zh/)
│   │    └──actions.js                  // 提交状态，调用mutations方法对数据进行操作 
│   │    └──getters.js                  // store的计算属性，获取state中状态，但不做修改 
│   │    └──index.js                    // 入口文件，定义Vuex.Stroe 
│   │    └──mutations.js                // 定义state数据修改操作   
│   ├── utils                           // 项目工具
│   │    └──aes.js                      // 数据加密
│   │    └──generate-config.js          // 生成配置文件
│   ├── App.vue                         // 主组件(页面入口文件)
│   └── main.js                         // 入口文件(初始化vue实例，加载所需插件)
├── babel.config.js                     // 主要用于将 ECMAScript 2015+ 版本的代码转换为向后兼容的 JavaScript 语法（参考文档：https://www.babeljs.cn/docs/）
├── package.json                        // 项目依赖文件
├── README.md                           // 项目说明文件
└── vue.config.js                       // vue配置文件（参考文档：https://cli.vuejs.org/zh/）
```
