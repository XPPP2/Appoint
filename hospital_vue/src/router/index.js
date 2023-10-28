import Vue from 'vue'
import Router from 'vue-router'

import Index from '../views/Index'
import Login from '../views/Login'
import Register from '../views/Register'


Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Index',
            component: Index
        },
        {
            path: '/register',
            name: 'Register',
            component: Register
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        }
    ]
})