import Mock from 'mockjs'
export default {
    getMenu: config => {
        const { username, password } = JSON.parse(config.body)
        // 先判断用户是否存在
        // 判断账号和密码是否对应
        if (username === 'admin' && password === 'admin') {
            return {
                code: 20000,
                data: {
                    menu: [
                        {
                            path: '/home',
                            name: 'home',
                            label: '首页',
                            icon: 's-home',
                            url: 'Home.vue'
                        },
                        {
                            path: '/mall',
                            name: 'mall',
                            label: '商品管理',
                            icon: 'video-play',
                            url: 'Mall.vue'
                        },
                        {
                            path: '/user',
                            name: 'user',
                            label: '用户管理',
                            icon: 'user',
                            url: 'User.vue'
                        },
                        {
                            label: '资料审查',
                            icon: 'location',
                            children: [
                                {
                                    path: '/info/competition',
                                    name: 'competition',
                                    label: '比赛获奖',
                                    icon: 'setting',
                                    url: 'InfoDetail.vue'
                                },
                                {
                                    path: '/info/paper',
                                    name: 'paper',
                                    label: '论文发表',
                                    icon: 'setting',
                                    url: 'InfoDetail.vue'
                                }
                            ]
                        },
                        {
                            label: '综测',
                            icon: 'location',
                            children: [
                                {
                                    path: '/compAssessment/compAssManage',
                                    name: 'compAssManage',
                                    label: '综测管理',
                                    icon: 'setting',
                                    url: 'compAssManage.vue'
                                },
                                {
                                    path: '/compAssessment/compAssBegin',
                                    name: 'compAssBegin',
                                    label: '综测发布',
                                    icon: 'setting',
                                    url: 'compAssBegin.vue'
                                },
                                {
                                    path: '/compAssessment/compAssReview',
                                    name: 'compAssReview',
                                    label: '综测审核',
                                    icon: 'setting',
                                    url: 'compAssReview.vue'
                                },
                            ]
                        }
                    ],
                    token: Mock.Random.guid(),
                    message: '获取成功'
                }
            }
        } else if (username === 'student' && password === 'student') {
            return {
                code: 20000,
                data: {
                    menu: [
                        {
                            path: '/home',
                            name: 'home',
                            label: '首页',
                            icon: 's-home',
                            url: 'Home.vue'
                        },
                        {
                            path: '/video',
                            name: 'video',
                            label: '商品管理',
                            icon: 'video-play',
                            url: 'Mall.vue'
                        },
                        {
                            label: '综测',
                            icon: 'location',
                            children: [
                                {
                                    path: '/compAssessment/compAssCommit',
                                    name: 'compAssManage',
                                    label: '综测提交',
                                    icon: 'setting',
                                    url: 'compAssCommit.vue'
                                },
                            ]
                        }
                    ],
                    token: Mock.Random.guid(),
                    message: '获取成功'
                }
            }
        } else {
            return {
                code: -999,
                data: {
                    message: '密码错误'
                }
            }
        }

    }
}