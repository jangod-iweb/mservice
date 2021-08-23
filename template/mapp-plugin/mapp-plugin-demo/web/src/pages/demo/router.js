import IWebSaas from '@jangod/iweb-saas';
/**
 * 模版使用
 * IWebSaas.ContainAside       包含头部与侧边栏模版
 * IWebSaas.ContainHeader      包含头部模版
 */
const Service = [
    {
        path: '/demo',
        name: 'demo',
        meta: {
            requireAuth: false
        },
        component: () => import('./index.vue')
    }
];
export default Service