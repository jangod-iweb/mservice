# 常见问题F&Q
### 1.什么是预发布环境，如果使用预发布环境？
`预发布环境`是指后端服务器连接在预发布环境上调试，非正式环境。

- 本模块使时，将 xxx/constant/env.js 代码修改如下：
```js
module.exports = 'stage';
```
- 主模块合包时，使用：
```bash
weshop a --env stage
```
主要表现为所有请求的 `Request Headers` 都会带请上 `dgd-pre-release:1`

### 2. 为什么接口有返回，但走到了catch？
合作商接口返回须按 [接口规范](http://) 返回
```js
{
  "errcode": 0,
  "errmsg": "",
  "data": {
    //业务数据
  }
}
```
