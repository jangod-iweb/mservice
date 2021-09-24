import '@babel/polyfill';
import Vue from 'vue'
import App from './App'
import { createStore } from './store';
import { createRouter } from './router';
import "./assets/css/index.css"
import 'element-ui/lib/theme-chalk/index.css';
//初始化路由store服务
const store = createStore();
const router = createRouter();
//基础服务
import IWebSDK from '@jangod/iweb-sdk';
Vue.use(IWebSDK);
import IWebSaas from '@jangod/iweb-saas';
Vue.use(IWebSaas);
import IWebUI from '@jangod/iweb-ui';
Vue.use(IWebUI);

//element组件
import ElementUI from 'element-ui';
Vue.use(ElementUI);

new Vue({
    store,
    router,
    render: h => h(App)
}).$mount("#app");
