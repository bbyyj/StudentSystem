import Vue from "vue";
import VueRouter from "vue-router";
import Main from '../Views/Main'
// import Home from '../Views/Home.vue'
// import Mall from '../Views/Mall.vue'
// import User from '../Views/User.vue'
// import InfoDetail from '../Views/InfoDetail.vue'
// import PageTwo from '../Views/PageTwo.vue'
import Login from '../Views/Login.vue'
import Cookie from 'js-cookie'

Vue.use(VueRouter)

const routes = [
    // 主路由
    {
        path: '/',
        name:'Main',
        component: Main,
        redirect: '/home', // 重定向
        children: [
            // 子路由
            // { path: '/home', name: 'home', component: Home }, // 首页
            // { path: '/user', name: 'user', component: User }, // 用户管理
            // { path: '/mall', name: 'mall', component: Mall }, // 商品管理
            // { path: '/competition', name: 'competition', component: InfoDetail }, // 页面1
            // { path: '/page2', name: 'page2', component: PageTwo }, // 页面2
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        // 管理员跳到的每个学生综测详情页面
        path: '/compAssDetail/:studentId',
        name: 'compAssDetail',
        component: () => import('@/Views/compAssDetail.vue'),
    },
    {
        // 管理员跳到的每个不同年份的综测审核页面
        path: '/compAssReview/:compAssId/:compAssName',
        name: 'compAssReview',
        component: () => import('@/Views/compAssReview.vue'),
    }
]

const router = new VueRouter({
    routes
})

// 路由守卫:全局前置导航守卫
router.beforeEach((to, from, next) => {
    // 获取token
    const token = Cookie.get('token')

    if (!token && to.name !== 'login') {
        next({ name: 'login' })
    } else if (token && to.name === 'login') {
        next({ name: 'home' })
    } else {
        next()
    }
})


export default router