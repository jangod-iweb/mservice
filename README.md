# mservice
`mservice`是基于`jangod iweb`实现集成springcloud项目，用于开发插件式应用的集成框架。
## 初始化
``` shell
# demo为项目名称，请根据实际情况进行修改
vue init jangod-iweb/mservice demo

# 编译
cd demo
mvn clean install -P [profileId]
profileId:
	1开发模式: 不打包前端应用
	2发布模式: 打包前端应用
```

## 运行
``` shell
cd demo-runner/target/demo-runner/bin

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

## 打包前端
``` shell
cnpm install
cnpm run build
```