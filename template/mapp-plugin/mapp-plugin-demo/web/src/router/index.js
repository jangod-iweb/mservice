import IwebSaas from '@jangod/iweb-saas'
export function createRouter(store, vue) {
    let router = new IwebSaas.Router(store, vue);
    /**
     * 自定义路由
     * 示例：
     * requireAuth 是否需要登录验证
     * router.addRouter([
         {path: '/home',meta: {requireAuth: false},component: () => import('./home.vue')},
         {path: '/index',component: () => import('./index.vue')}
        ]);
     */
    /**
     * 自定义路由处理
     * 返回值：true：代表已处理，false，代表未处理
     * router.addBeforeEach(function(to,from,next){
            // 自定义逻辑处理
            if(to.path==="/test"){
                return true;
            }
            return false;
        });
     */

    return router.getRouter();
}