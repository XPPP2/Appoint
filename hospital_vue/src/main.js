import Vue from 'vue'

import VueRouter from 'vue-router'
import router from './router'
import axios from 'axios'
import echarts from 'echarts'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import Utils from './assets/js/common.js'
import './assets/css/common.css'
Vue.prototype.utils=Utils;

// 安装路由
Vue.use(VueRouter);

Vue.use(ElementUI)

Vue.prototype.$axios = axios
Vue.prototype.$echarts = echarts
Vue.prototype.domain = "http://127.0.0.1:8080"; // 接口地址

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
