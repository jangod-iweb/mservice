<template>
    <router-view/>
</template>

<script>
export default {
  name: 'App',
  data(){
    return{

    }
  },
  watch: {},
  computed: {},
  mounted() {
  },
  methods: {

  }
}
</script>

<style>
@media screen and(-ms-high-contrast:active),(-ms-high-contrast:none){
  /*兼容IE11隐藏滚动条*/
  *{
    -ms-scroll-chaining: chained;
    -ms-overflow-style: none;
    -ms-content-zooming: zoom;
    -ms-scroll-rails: none;
    -ms-content-zoom-limit-min: 100%;
    -ms-content-zoom-limit-max: 500%;
    -ms-scroll-snap-type: proximity;
    -ms-scroll-snap-points-x: snapList(100%, 200%, 300%, 400%, 500%);
    -ms-overflow-style: none;
  }
}
</style>
