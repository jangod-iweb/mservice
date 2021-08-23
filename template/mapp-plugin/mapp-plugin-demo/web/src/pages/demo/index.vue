<template>
  <div className="demo flex-column">
    <template v-if="!markdownBox">
      <h2 className="mb-l">欢迎使用</h2>
      <div className="title">{{ value }}</div>
      <span @click="markdownBox=true" class="doc mt-l">使用说明</span>
    </template>
    <div v-if="markdownBox" v-html class="markdown">
      <body marginwidth="0" marginheight="0">
      <div id="container"><h1 id="-" className="title">深圳市政务项目前端开发</h1>
        <h3 id="1-">1、安装依赖</h3>
        <p><strong>1.安装node最新环境</strong></p>
        <p><strong>2.安装cnpm <code>npm install -g cnpm</code></strong></p>
        <p><strong>3.切换到私有仓库<code>cnpm config set registry http://39.108.236.82:7001</code></strong></p>
        <p><strong>4.<code>cnpm install</code></strong></p>
        <h3 id="2-">2、项目配置</h3>
        <p><strong>1.基础服务配置文件<code>/config/application.json</code></strong></p>
        <pre><code>    参数说明:
              <span className="hljs-tag">appId</span>   (项目唯一编码)
              <span className="hljs-tag">active</span>  (当前启用模式<span className="hljs-attr_selector">[dev/pro]</span>)
              <span className="hljs-tag">version</span> (当前运行项目版本)<span className="hljs-attr_selector">[用于项目更新]</span>
              <span className="hljs-tag">context</span> (项目虚拟路径)
      </code></pre>
        <p><strong>2.启动模式配置<code>/config/application-&lt;模式&gt;.json文件</code></strong></p>
        <pre><code>    参数说明:
              application-dev.json    (开发测试环境配置)
              application-pro.json    (正式环境配置)

          注意: app_key 与 app_security 需申请
      </code></pre>
        <p><strong>3.使用配置文件<code>/src/api/app.config.js</code></strong></p>
        <pre><code>    使用说明:
              项目中需要使用配置文件信息,只需引入 /src/api/app.config.js 即可
              引入方式：<span className="hljs-keyword">import</span> config <span className="hljs-keyword">from</span> <span
              className="hljs-string">"@/api/app.config.js"</span>
      </code></pre>
        <h3 id="3-">3、启动项目</h3>
        <p><strong><code>npm run dev</code></strong></p>
        <h3 id="4-">4、访问项目</h3>
        <pre><code><span className="hljs-bullet">* </span>页面访问入口地址
      <span className="hljs-bullet">* </span>http://0.0.0.0:8082/demo
      </code></pre>
        <h3 id="5-">5、项目打包</h3>
        <p><strong>1.修改 <code>/config/application.json</code> 中的 version [版本号]</strong></p>
        <p><strong>2.执行<code>npm run build</code></strong></p>
        <p><strong>3.在根目录生成<code>dist</code>资源目录，与<code>/target/dist_[版本号]</code>的zip压缩包</strong></p>
        <p><strong>说明：<code>/target/dist_[版本号].zip</code>就是<code>dist</code>目录的打包文件</strong></p>
        <h3 id="-">扩展信息</h3>
        <pre><code><span className="hljs-number">1</span>、接口代理配置 vue.config.js
          参数说明：
              devServer &gt; proxy
              配置示例说明<span className="hljs-symbol">:</span>
              <span className="hljs-string">'/api'</span><span className="hljs-symbol">:</span> { <span
              className="hljs-regexp">//</span>代理接口前缀为/api的请求
                  <span class="hljs-symbol">target:</span> <span
              class="hljs-string">'http://localhost:5000'</span>, <span class="hljs-regexp">//</span>需要代理到的目标地址
                  <span class="hljs-symbol">ws:</span> <span class="hljs-keyword">true</span>, <span
              class="hljs-regexp">//</span>是否代理websockets
                  <span class="hljs-symbol">changeOrigin:</span> <span class="hljs-keyword">true</span>, <span
              class="hljs-regexp">//</span>是否跨域
                  <span class="hljs-symbol">pathRewrite:</span> {  <span class="hljs-regexp">//</span>重写路径 比如<span
              class="hljs-string">'/api/aaa/ccc'</span>重写为<span class="hljs-string">'/aaa/ccc'</span>
                    <span class="hljs-string">'^/api'</span><span class="hljs-symbol">:</span> <span
              class="hljs-string">''</span>
                  }
              }
      </code></pre>
        <pre><code><span class="hljs-number">2</span>、单独组件打包与发布
          <span class="hljs-number">1</span>、安装<span class="hljs-property">@jangod</span>/iweb-cli-service

            cnpm install <span class="hljs-property">@jangod</span>/iweb-cli-service -g

        <span class="hljs-number">2</span>、在package.json文件中设置
            <span class="hljs-attribute">script</span>:{
                <span class="hljs-attribute">export</span>: <span class="hljs-string">"iweb-cli-service build"</span>,
                <span class="hljs-attribute">publish</span>:<span class="hljs-string">"iweb-cli-service publish"</span>
            }
            注意: 执行script中命令时控制台提示：
            是否创建配置文件:(<span class="hljs-literal">yes</span>/<span class="hljs-literal">no</span>/fileName) &gt; y
            请输入(y/<span class="hljs-literal">yes</span>)回车，生成jangod.config.js配置文件

          ***组件打包***
            <span class="hljs-number">1.</span> 打开生成的jangod.config.js配置文件(build为打包配置)
            <span class="hljs-number">2.</span> 按照配置文件中的说明进行组件打包配置
            <span class="hljs-number">3.</span> 执行 <span class="hljs-built_in">npm</span> run <span
              class="hljs-reserved">export</span>

          ***组件上传***
            <span class="hljs-number">1.</span> 打开生成的jangod.config.js配置文件(publish为上传配置)
            <span class="hljs-number">2.</span> 按照配置文件中的说明进行组件上传配置(资源类型&lt;cdn&gt;)
            <span class="hljs-number">3.</span> 执行 <span class="hljs-built_in">npm</span> run publish
            温馨提示： resources上传资源有多个时，执行命令控制台会有操作提示，按提示完成上传操作
                      (多个资源上传时，支持自选上传资源)
      </code></pre>
        <h3 id="-">目录结构</h3>
        <pre><code>├── <span class="hljs-keyword">public</span>                      <span
            class="hljs-comment">// 静态资源</span>
      │    └──config.json             <span class="hljs-comment">// 项目环境配置</span>
      │    └──index.html              <span class="hljs-comment">// 静态模版</span>
      ├── src
      │   ├── api
      │   │    └──app.config.js       <span class="hljs-comment">// 私有配置文件（页面内需要使用配置文件信息引入[import AppConfig from "@/api/app.config.js"]）</span>
      │   ├── pages                   <span class="hljs-comment">// 页面文件目录</span>
      │   ├── router                  <span class="hljs-comment">// 路由配置</span>
      │   │    └──index.js
      │   ├── store                   <span class="hljs-comment">// 全局变量配置store(参考文档:https://vuex.vuejs.org/zh/)</span>
      │   │    └──actions.js          <span class="hljs-comment">// 提交状态，调用mutations方法对数据进行操作 </span>
      │   │    └──getters.js          <span class="hljs-comment">// store的计算属性，获取state中状态，但不做修改 </span>
      │   │    └──index.js            <span class="hljs-comment">// 入口文件，定义Vuex.Stroe </span>
      │   │    └──mutations.js        <span class="hljs-comment">// 定义state数据修改操作 </span>
      │   ├── App.vue                 <span class="hljs-comment">// 主组件(页面入口文件)</span>
      │   └── main.js                 <span class="hljs-comment">// 入口文件(初始化vue实例，加载所需插件)</span>
      ├── .gitignore                  <span class="hljs-comment">// git配置忽略推送文件</span>
      ├── babel.config.js             <span class="hljs-comment">// 主要用于将 ECMAScript 2015+ 版本的代码转换为向后兼容的 JavaScript 语法（参考文档：https://www.babeljs.cn/docs/）</span>
      ├── <span class="hljs-keyword">package</span>.json                <span class="hljs-comment">// 项目依赖文件</span>
      ├── README.md                   <span class="hljs-comment">// 项目说明文件</span>
      └── vue.config.js               <span class="hljs-comment">// vue配置文件（参考文档：https://cli.vuejs.org/zh/）</span>
      </code></pre>
      </div>
      </body>
    </div>
  </div>
</template>

<script>
export default {
  name: "demo",
  components: {
    //组件
  },
  data() {
    return {
      value: "深圳市政务项目前端开发项目框架",
      markdownBox: false
    }
  },
  watch: {},
  computed: {},
  mounted() {

  },
  methods: {}
}
</script>

<style lang="less" scoped>
.demo {
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  font-weight: bold;
  font-size: 30px;
  justify-content: center;
  background: linear-gradient(to top, #95c4ff, #ffffff, #d4e6fd);

  h2 {
    color: orange;
  }

  .title {
    -webkit-animation: 3s naughty;
    animation: 3s naughty;
    animation-fill-mode: forwards;
    color: transparent;
    background: linear-gradient(to right, #2975d2, #a1c8e9);
    -webkit-background-clip: text;
  }

  .doc {
    color: #ccc;
    cursor: pointer;
    text-decoration: underline;
  }

  @keyframes naughty {
    0% {
      transform: scale(1);
    }
    100% {
      transform: scale(1.5);
    }
  }
}
.markdown {
  height: 700px;
  overflow-y: auto;
  overflow-x: hidden;
  background: white;
  border-radius: 10px;

  article,
  aside,
  details,
  figcaption,
  figure,
  footer,
  header,
  hgroup,
  main,
  nav,
  section,
  summary {
    display: block;
  }

  /**
  * 1. Correct `inline-block` display not defined in IE 8/9.
  * 2. Normalize vertical alignment of `progress` in Chrome, Firefox, and Opera.
  */

  audio,
  canvas,
  progress,
  video {
    display: inline-block; /* 1 */
    vertical-align: baseline; /* 2 */
  }


  audio:not([controls]) {
    display: none;
    height: 0;
  }


  [hidden],
  template {
    display: none;
  }

  a {
    background: transparent;
  }


  a:active,
  a:hover {
    outline: 0;
  }


  abbr[title] {
    border-bottom: 1px dotted;
  }


  b,
  strong {
    font-weight: bold;
  }

  dfn {
    font-style: italic;
  }

  h1 {
    font-size: 2em;
    margin: 0.67em 0;
  }


  mark {
    background: #ff0;
    color: #000;
  }

  small {
    font-size: 80%;
  }

  sub,
  sup {
    font-size: 75%;
    line-height: 0;
    position: relative;
    vertical-align: baseline;
  }

  sup {
    top: -0.5em;
  }

  sub {
    bottom: -0.25em;
  }


  img {
    border: 0;
  }


  svg:not(:root) {
    overflow: hidden;
  }


  figure {
    margin: 1em 40px;
  }


  hr {
    -moz-box-sizing: content-box;
    box-sizing: content-box;
    height: 0;
  }

  pre {
    overflow: auto;
  }


  code,
  kbd,
  pre,
  samp {
    font-family: monospace, monospace;
    font-size: 1em;
  }


  button,
  input,
  optgroup,
  select,
  textarea {
    color: inherit; /* 1 */
    font: inherit; /* 2 */
    margin: 0; /* 3 */
  }


  button {
    overflow: visible;
  }


  button,
  select {
    text-transform: none;
  }


  button,
  html input[type="button"], /* 1 */
  input[type="reset"],
  input[type="submit"] {
    -webkit-appearance: button; /* 2 */
    cursor: pointer; /* 3 */
  }


  button[disabled],
  html input[disabled] {
    cursor: default;
  }

  button::-moz-focus-inner,
  input::-moz-focus-inner {
    border: 0;
    padding: 0;
  }

  input {
    line-height: normal;
  }

  input[type="checkbox"],
  input[type="radio"] {
    box-sizing: border-box; /* 1 */
    padding: 0; /* 2 */
  }

  input[type="number"]::-webkit-inner-spin-button,
  input[type="number"]::-webkit-outer-spin-button {
    height: auto;
  }

  input[type="search"] {
    -webkit-appearance: textfield; /* 1 */
    -moz-box-sizing: content-box;
    -webkit-box-sizing: content-box; /* 2 */
    box-sizing: content-box;
  }

  input[type="search"]::-webkit-search-cancel-button,
  input[type="search"]::-webkit-search-decoration {
    -webkit-appearance: none;
  }

  fieldset {
    border: 1px solid #c0c0c0;
    margin: 0 2px;
    padding: 0.35em 0.625em 0.75em;
  }

  legend {
    border: 0; /* 1 */
    padding: 0; /* 2 */
  }

  textarea {
    overflow: auto;
  }

  optgroup {
    font-weight: bold;
  }

  table {
    border-collapse: collapse;
    border-spacing: 0;
  }

  td,
  th {
    padding: 0;
  }

  * {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
  }

  *:before,
  *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
  }

  html {
    font-size: 62.5%;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  }

  body {
    font-family: 'Helvetica Neue', Helvetica, Arial, 'Microsoft Yahei', sans-serif;
    font-size: 14px;
    line-height: 1.42857143;
    color: #333333;
    background-color: #ffffff;
  }

  input,
  button,
  select,
  textarea {
    font-family: inherit;
    font-size: inherit;
    line-height: inherit;
  }

  a {
    color: #428bca;
    text-decoration: none;
  }

  a:hover,
  a:focus {
    color: #2a6496;
    text-decoration: underline;
  }

  a:focus {
    outline: thin dotted;
    outline: 5px auto -webkit-focus-ring-color;
    outline-offset: -2px;
  }

  figure {
    margin: 0;
  }

  img {
    vertical-align: middle;
  }


  .hljs {
    display: block;
    overflow-x: auto;
    padding: 0.5em;
    background: #f0f0f0;
    -webkit-text-size-adjust: none;
  }

  .hljs,
  .hljs-subst,
  .hljs-tag .hljs-title,
  .nginx .hljs-title {
    color: black;
  }

  .hljs-string,
  .hljs-title,
  .hljs-constant,
  .hljs-parent,
  .hljs-tag .hljs-value,
  .hljs-rules .hljs-value,
  .hljs-preprocessor,
  .hljs-pragma,
  .haml .hljs-symbol,
  .ruby .hljs-symbol,
  .ruby .hljs-symbol .hljs-string,
  .hljs-template_tag,
  .django .hljs-variable,
  .smalltalk .hljs-class,
  .hljs-addition,
  .hljs-flow,
  .hljs-stream,
  .bash .hljs-variable,
  .apache .hljs-tag,
  .apache .hljs-cbracket,
  .tex .hljs-command,
  .tex .hljs-special,
  .erlang_repl .hljs-function_or_atom,
  .asciidoc .hljs-header,
  .markdown .hljs-header,
  .coffeescript .hljs-attribute {
    color: #800;
  }

  .smartquote,
  .hljs-comment,
  .hljs-annotation,
  .diff .hljs-header,
  .hljs-chunk,
  .asciidoc .hljs-blockquote,
  .markdown .hljs-blockquote {
    color: #888;
  }

  .hljs-number,
  .hljs-date,
  .hljs-regexp,
  .hljs-literal,
  .hljs-hexcolor,
  .smalltalk .hljs-symbol,
  .smalltalk .hljs-char,
  .go .hljs-constant,
  .hljs-change,
  .lasso .hljs-variable,
  .makefile .hljs-variable,
  .asciidoc .hljs-bullet,
  .markdown .hljs-bullet,
  .asciidoc .hljs-link_url,
  .markdown .hljs-link_url {
    color: #080;
  }

  .hljs-label,
  .hljs-javadoc,
  .ruby .hljs-string,
  .hljs-decorator,
  .hljs-filter .hljs-argument,
  .hljs-localvars,
  .hljs-array,
  .hljs-attr_selector,
  .hljs-important,
  .hljs-pseudo,
  .hljs-pi,
  .haml .hljs-bullet,
  .hljs-doctype,
  .hljs-deletion,
  .hljs-envvar,
  .hljs-shebang,
  .apache .hljs-sqbracket,
  .nginx .hljs-built_in,
  .tex .hljs-formula,
  .erlang_repl .hljs-reserved,
  .hljs-prompt,
  .asciidoc .hljs-link_label,
  .markdown .hljs-link_label,
  .vhdl .hljs-attribute,
  .clojure .hljs-attribute,
  .asciidoc .hljs-attribute,
  .lasso .hljs-attribute,
  .coffeescript .hljs-property,
  .hljs-phony {
    color: #88f;
  }

  .hljs-keyword,
  .hljs-id,
  .hljs-title,
  .hljs-built_in,
  .css .hljs-tag,
  .hljs-javadoctag,
  .hljs-phpdoc,
  .hljs-dartdoc,
  .hljs-yardoctag,
  .smalltalk .hljs-class,
  .hljs-winutils,
  .bash .hljs-variable,
  .apache .hljs-tag,
  .hljs-type,
  .hljs-typename,
  .tex .hljs-command,
  .asciidoc .hljs-strong,
  .markdown .hljs-strong,
  .hljs-request,
  .hljs-status {
    font-weight: bold;
  }

  .asciidoc .hljs-emphasis,
  .markdown .hljs-emphasis {
    font-style: italic;
  }

  .nginx .hljs-built_in {
    font-weight: normal;
  }

  .coffeescript .javascript,
  .javascript .xml,
  .lasso .markup,
  .tex .hljs-formula,
  .xml .javascript,
  .xml .vbscript,
  .xml .css,
  .xml .hljs-cdata {
    opacity: 0.5;
  }

  #container {
    padding: 15px;
    margin-left: 20px;
  }

  .title {
    text-align: center;
  }

  pre {
    border: 1px solid #ccc;
    border-radius: 4px;
    display: block;
  }

  pre code {
    white-space: pre-wrap;
  }

  .hljs,
  code {
    font-family: Monaco, Menlo, Consolas, 'Courier New', monospace;
  }

  pre {
    background-color: #eeeeee;
    padding: 8px 0px 8px 30px;
    word-wrap: break-word;
  }

  table tbody tr:nth-child(2n) {
    background: rgba(158, 188, 226, 0.12);
  }

  :not(pre) > code {
    padding: 2px 4px;
    font-size: 90%;
    color: #c7254e;
    background-color: #f9f2f4;
    white-space: nowrap;
    border-radius: 4px;
  }

  th, td {
    border: 1px solid #ccc;
    padding: 6px 12px;
  }

  blockquote {
    border-left-width: 10px;
    background-color: rgba(102, 128, 153, 0.05);
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
    padding: 1px 20px
  }

  blockquote.pull-right small:before, blockquote.pull-right .small:before {
    content: ''
  }

  blockquote.pull-right small:after, blockquote.pull-right .small:after {
    content: '\00A0 \2014'
  }

  blockquote:before, blockquote:after {
    content: ""
  }

  blockquote {
    margin: 0 0 1.1em
  }

  blockquote p {
    margin-bottom: 1.1em;
    font-size: 1em;
    line-height: 1.45
  }

  blockquote ul:last-child, blockquote ol:last-child {
    margin-bottom: 0
  }

  blockquote {
    margin: 0 0 21px;
    border-left: 10px solid #dddddd;
  }
}
</style>
