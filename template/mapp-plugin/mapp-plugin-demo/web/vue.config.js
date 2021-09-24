const path = require("path");
const RuntimePublicPathPlugin = require('webpack-runtime-public-path-plugin');
const fileManagerPlugin = require('filemanager-webpack-plugin');
const GenerateConfig = require('@jangod/iweb-sdk/generate-config.js');
const Config = GenerateConfig();
function resolve(dir) {
    return path.join(__dirname,dir)
}
module.exports = {
    publicPath: Config.context,
    outputDir: "dist",
    assetsDir: "static",
    runtimeCompiler: false,
    productionSourceMap: false,
    transpileDependencies: ["*"],
    devServer:{
        host: "0.0.0.0",
        port: {{webPort}},
        open: true,
        proxy:{
            '/local': {
                target: 'http://127.0.0.1:{{backendPort}}',
                changeOrigin: true,
                pathRewrite: {
                    '^/local': ''
                }
            }
        }
    },
    chainWebpack: config =>{
        config.resolve.alias
            .set('@_src',resolve('src'));
        config.plugin('runtime')
            .use(RuntimePublicPathPlugin,[{
                runtimePublicPath: '__iweb_context_path'
            }]);
        config.plugin('html')
            .tap(options => {
                options[0].inject = false;
                return options
            });
        if (process.env.NODE_ENV === 'production') {
            config.plugin('compress')
                .use(fileManagerPlugin, [{
                    events:{
                        onEnd: {
                            delete: [`./dist_*\.zip`,'./public/version.json'],
                            archive: [{source: './dist', destination: `./target/dist_${Config.version}.zip`}]
                        }
                    }
                }])
        }
    }
}
