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
                            path: '/studentmanage',
                            name: 'studentmanage',
                            label: '学生信息管理',
                            icon: 'user',
                            url: 'StudentManage.vue'
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
                                }, {
                                    path: '/info/paper',
                                    name: 'paper',
                                    label: '论文发表',
                                    icon: 'setting',
                                    url: 'InfoDetail.vue'
                                },{
                                    path: '/info/patent',
                                    name: 'patent',
                                    label: '专利发明',
                                    icon: 'setting',
                                    url: 'InfoDetail.vue'
                                },{
                                    path: '/info/software',
                                    name: 'software',
                                    label: '软件著作权发明',
                                    icon: 'setting',
                                    url: 'InfoDetail.vue'
                                },{
                                    path: '/info/monograph',
                                    name: 'monograph',
                                    label: '专著出版',
                                    icon: 'setting',
                                    url: 'InfoDetail.vue'
                                },{
                                    path: '/info/exchange',
                                    name: 'exchange',
                                    label: '赴校外交流',
                                    icon: 'setting',
                                    url: 'InfoDetail.vue'
                                },{
                                    path: '/info/volunteer',
                                    name: 'volunteer',
                                    label: '志愿服务',
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
                                    label: '综测表',
                                    icon: 'setting',
                                    url: 'compAssManage.vue'
                                },
                                {
                                    path: '/compAssessment/compAssBegin',
                                    name: 'compAssBegin',
                                    label: '综测评审',
                                    icon: 'setting',
                                    url: 'compAssBegin.vue'
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
                            path: '/student/info',
                            name: 'stufentInfo',
                            label: '个人信息',
                            icon: 'user',
                            url: 'StudentInfo.vue'
                        },
                        {
                            label: '资料提交',
                            icon: 'location',
                            children: [
                                {
                                    path: '/InfoSubmission/Competitions',
                                    name: 'CompetitionSubmission',
                                    label: '比赛获奖',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                                {
                                    path: '/InfoSubmission/Papers',
                                    name: 'PaperSubmission',
                                    label: '论文发表',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                                {
                                    path: '/InfoSubmission/Patents',
                                    name: 'PatentSubmission',
                                    label: '专利发明',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                                {
                                    path: '/InfoSubmission/Softwares',
                                    name: 'SoftwareSubmission',
                                    label: '软件著作权发明',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                                {
                                    path: '/InfoSubmission/Monographs',
                                    name: 'MonographSubmission',
                                    label: '专著出版',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                                {
                                    path: '/InfoSubmission/Exchange',
                                    name: 'ExchangeSubmission',
                                    label: '校外交流',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                                {
                                    path: '/InfoSubmission/Volunteerism',
                                    name: 'VolunteerismSubmission',
                                    label: '志愿服务',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                                {
                                    path: '/InfoSubmission/Community',
                                    name: 'CommunitySubmission',
                                    label: '社会工作',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                                {
                                    path: '/InfoSubmission/Ideological',
                                    name: 'IdeologicalSubmission',
                                    label: '思想道德',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                                {
                                    path: '/InfoSubmission/Other',
                                    name: 'OtherSubmission',
                                    label: '其他',
                                    icon: 'setting',
                                    url: 'InfoSubmission.vue'
                                },
                            ]
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
                        },
                        {
                            path: '/InfoSubmissionRecord/record',
                            name: 'InfoSubmissionRecord',
                            label: '资料提交记录',
                            icon: 'user',
                            url: 'InfoSubmissionRecord.vue'
                        },
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