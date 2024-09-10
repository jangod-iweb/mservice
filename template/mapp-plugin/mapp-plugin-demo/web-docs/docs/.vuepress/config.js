module.exports = {
    title: '{{#firstToUpper name}}{{/firstToUpper}}系统 - 开发文档',  // 设置网站标题
    description: '政务服务应用开发文档',
    base: '/p/{{name}}-plugin-{{pluginName}}/docs/',
    port: 9101,
    dest: "./dist/iweb/",
    head: [
        ['link', {rel: 'icon', href: '/assets/images/iweb.ico'}],
        ['script', {src: 'https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.slim.min.js'}],
        ['script', {src: 'https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.2/jquery.fancybox.min.js'}],
        ['link', {
            rel: 'stylesheet',
            type: 'text/css',
            href: 'https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.2/jquery.fancybox.min.css'
        }]
    ],
    themeConfig: {
        logo: "/assets/images/iweb-logo-text.png",
        nav: [
            {text: '资源中心', link: '/resource/'},
            {text: '指引文档', link: '/guide/'},
            {text: '常见问题', link: '/faq/'},
        ],
        sidebar: {
            '/resource/': [
                {
                    title: '资源中心',   // 必要的
                    collapsable: true, // 可选的, 默认值是 true,
                    sidebarDepth: 2,    // 可选的, 默认值是 1
                    children: [
                        // {path:"/resource/demo1/",title:"资源1"},
                    ]
                }
            ],
            "/guide/": [
                {
                    title: '插件说明',   // 必要的
                    collapsable: false, // 可选的, 默认值是 true,
                    sidebarDepth: 1,    // 可选的, 默认值是 1
                    children: [
                        {path:"/guide/quickstart/",title:"快速入门"},
                        {path:"/guide/config/",title:"插件配置"},
                    ]
                }
            ],
            '/faq/': [
                {
                    title: '常见问题',   // 必要的
                    collapsable: true, // 可选的, 默认值是 true,
                    sidebarDepth: 2,    // 可选的, 默认值是 1
                    children: [
                        // {path:"/faq/demo1/",title:"问题1"},
                    ]
                }
            ]
        }
    }
}