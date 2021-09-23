module.exports = {
    title: 'ChendemingNews系统 - 开发文档',  // 设置网站标题
    description: '政务服务应用开发文档',
    base: '/p/chendeming-plugin-news/docs/',
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
            {
                text: '部署手册',
                items: [
                    {text: 'runner部署', link: '/guide/deploy/runner.md'},
                    {text: '容器部署', link: '/guide/deploy/container.md'}
                ]
            },
            {text: '操作手册', link: '/opera/'},
            {text: '常见问题', link: '/faq/'},
        ],
        sidebar: {
            "/guide/": [
                {
                    title: '部署手册',   // 必要的
                    collapsable: false, // 可选的, 默认值是 true,
                    sidebarDepth: 1,    // 可选的, 默认值是 1
                    children: [
                        "runner部署", "容器部署"
                    ]
                }
            ],
            '/opera/': [
                {
                    title: '操作手册',   // 必要的
                    collapsable: true, // 可选的, 默认值是 true,
                    sidebarDepth: 2,    // 可选的, 默认值是 1
                    children: [
                        'opera'
                    ]
                }
            ]
        }
    }
}