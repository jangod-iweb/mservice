import IWebSaas from '@jangod/iweb-saas';
/**
 * 模版使用
 * IWebSaas.ContainAside       包含头部与侧边栏模版
 * IWebSaas.ContainHeader      包含头部模版
 */
const Service = [
    {
        path: '/welcome',
        name: 'welcome',
        meta: {
            requireAuth: false
        },
        component: () => import('./welcome.vue')
    },
    {
        path: '/demo/',
        name: 'demo',
        redirect:"/demo/index",
        component: IWebSaas.NarrowSide,
        children: [
            {
                path: 'index',
                name: 'home',
                meta: {},
                component: () => import('./demo.vue')
            },
            {
                path: 'info',
                name: 'info',
                meta: {
                    activePath:"/demo/index"
                },
                component: () => import('./info.vue')
            }
        ]
    }
];
export default Service