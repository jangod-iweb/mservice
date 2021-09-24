# mweb前端开发框架
> mweb是一套基于`jangod-iweb`的前端开发框架，用于快速构建大平台，小应用的设计理念。

### 1、安装依赖
**1.安装node最新环境**

**2.安装cnpm `npm install -g cnpm`**

**3.切换到私有仓库`cnpm config set registry http://39.108.236.82:7001`**

**4.`cnpm install`**

### 2、项目配置
**1.基础服务配置文件` /config/application.json`**
```
    参数说明: 
        appId   (项目唯一编码)
        active  (当前启用模式[dev/pro])
        version (当前运行项目版本)[用于项目更新]
        context (项目虚拟路径)
```
**2.启动模式配置` /config/application-<模式>.json文件`**
```
    参数说明:
        application-dev.json    (开发测试环境配置)
        application-pro.json    (正式环境配置)
        
    注意: app_key 与 app_security 需申请
```
**3.使用配置文件` /src/api/app.config.js`**
```
    使用说明:
        项目中需要使用配置文件信息,只需引入 /src/api/app.config.js 即可
        引入方式：import config from "@/api/app.config.js"
```
### 3、启动项目
**`npm run dev`**
### 4、访问项目
```
* 页面访问入口地址
* http://0.0.0.0:{{webPort}}/demo
```

### 5、项目打包
**1.修改 `/config/application.json` 中的 version [版本号]**

**2.执行` npm run build`**

**3.在根目录生成`dist`资源目录，与`/target/dist_[版本号]`的zip压缩包**

**说明：`/target/dist_[版本号].zip`就是`dist`目录的打包文件**
### 扩展信息
```
1、接口代理配置 vue.config.js
    参数说明：
        devServer > proxy
        配置示例说明:
        '/api': { //代理接口前缀为/api的请求
            target: 'http://localhost:5000', //需要代理到的目标地址
            ws: true, //是否代理websockets
            changeOrigin: true, //是否跨域
            pathRewrite: {  //重写路径 比如'/api/aaa/ccc'重写为'/aaa/ccc'
              '^/api': ''
            }
        }
```
```
2、单独组件打包与发布
    1、安装@jangod/iweb-cli-service  
    
       cnpm install @jangod/iweb-cli-service -g
       
   2、在package.json文件中设置
       script:{
          export: "iweb-cli-service build",
          publish:"iweb-cli-service publish"
       }
       注意: 执行script中命令时控制台提示：
       是否创建配置文件:(yes/no/fileName) > y
       请输入(y/yes)回车，生成jangod.config.js配置文件
       
    ***组件打包***
       1. 打开生成的jangod.config.js配置文件(build为打包配置)
       2. 按照配置文件中的说明进行组件打包配置
       3. 执行 npm run export
              
    ***组件上传***
       1. 打开生成的jangod.config.js配置文件(publish为上传配置)
       2. 按照配置文件中的说明进行组件上传配置(资源类型<cdn>)
       3. 执行 npm run publish
       温馨提示： resources上传资源有多个时，执行命令控制台会有操作提示，按提示完成上传操作
                (多个资源上传时，支持自选上传资源)
```
### 目录结构
```
├── public                      // 静态资源
│    └──config.json             // 项目环境配置
│    └──index.html              // 静态模版
├── src                         
│   ├── api
│   │    └──app.config.js       // 私有配置文件（页面内需要使用配置文件信息引入[import AppConfig from "@/api/app.config.js"]）
│   ├── pages                   // 页面文件目录
│   ├── router                  // 路由配置
│   │    └──index.js            
│   ├── store                   // 全局变量配置store(参考文档:https://vuex.vuejs.org/zh/)
│   │    └──actions.js          // 提交状态，调用mutations方法对数据进行操作 
│   │    └──getters.js          // store的计算属性，获取state中状态，但不做修改 
│   │    └──index.js            // 入口文件，定义Vuex.Stroe 
│   │    └──mutations.js        // 定义state数据修改操作 
│   ├── App.vue                 // 主组件(页面入口文件)
│   └── main.js                 // 入口文件(初始化vue实例，加载所需插件)
├── .gitignore                  // git配置忽略推送文件
├── babel.config.js             // 主要用于将 ECMAScript 2015+ 版本的代码转换为向后兼容的 JavaScript 语法（参考文档：https://www.babeljs.cn/docs/）
├── package.json                // 项目依赖文件
├── README.md                   // 项目说明文件
└── vue.config.js               // vue配置文件（参考文档：https://cli.vuejs.org/zh/）
```