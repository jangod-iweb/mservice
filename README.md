# mservice

`mservice`是基于`jangod iweb`实现集成springcloud项目，用于开发插件式应用的集成框架。


## 开发必读

    http://developers.iszgov.cn/iweb/

## 开发环境

    jdk（ version >= 1.8 ）
    idea （用于编译运行后端程序）
    maven（ version >= 3.0.0 ）
    cnpm （用于编译运行前端程序）
    vuepress （用于构建运行在线文档）

## 初始化
``` shell
# demo为项目名称，请根据实际情况进行修改
vue init jangod-iweb/mservice demo

# 编译
cd {{name}}
mvn clean install -P [profileId]
profileId:
	1开发模式: 不打包前端应用
	2发布模式: 打包前端应用
```

## 调试部署

### 本地调试

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
        浏览器访问：http://localhost:{{webDocPort}}/


### 打包部署

``` shell

# 编译项目
mvn clean install -P 2发布模式

# 拷贝插件
cp {{name}}/{{name}}-plugin-{{pluginName}}/target/{{name}}-plugin-{{pluginName}}-1.0.SNAPSHOT.jar {{name}}/{{name}}-runner/target/{{name}}-runner/{{name}}-plugin/

# 容器启动
cd {{name}}/{{name}}-runner/target/{{name}}-runner/bin

# linux下运行
./startup.sh
Usage: ./startup.sh [ console | start | stop | restart | condrestart | status | install | installstart | remove | dump ]

Commands:
  console      控制台方式启动.
  start        后台进程启动.
  stop         停止进程.
  restart      重启.
  condrestart  如果存在进程情况下才重启.
  status       查询进程状态.
  install      安装程序到启动项中.
  installstart 安装程序到启动项中并后台启动.
  remove       从启动项目中卸载.
  dump         到处java dump文件.
  
# windows下运行
./console.bat  控制台方式启动
./install.bat  安装程序到windows服务中
./startup.bat  启动windows中程序
./remove.bat   从windows服务中卸载程序
```

