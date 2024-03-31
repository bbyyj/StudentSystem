const MenuData= [
    {
      path: '/',
      name: 'home',
      label: '首页',
      icon: 's-home',
      url: 'Home/Home'
    },
    {
      path: '/mall',
      name: 'mall',
      label: '商品',
      icon: 'video-play',
      url: 'MallManage/MallManage'
    },
    {
      path: '/user',
      name: 'user',
      label: '用户管理',
      icon: 'user',
      url: 'UserManage/UserManage'
    },
    {
      label: '资料审查',
      icon: 'location',
      children: [
        {
          path: '/competition',
          name: 'competition',
          label: '比赛获奖',
          icon: 'setting',
          url: 'Info/CompetitionAward'
        },
        {
          path: '/page2',
          name: 'page2',
          label: '页面2',
          icon: 'setting',
          url: 'Other/PageTwo'
        }
      ]
    }
]

export default MenuData