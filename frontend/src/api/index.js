import http from '../utils/request'

// 请求首页数据,直接把这个对象导出
export const getData = () => {
    // 返回一个promise
    return http.get('/home/getData')
}

// 下面四个:用户管理-后端-网络请求接口
export const getUser = (params) => {
    return http.get('/user/get/', params)
}

export const createUser = (data) => {
    return http.post('/user/create', data)
}

export const deleteUser = (data) => {
    return http.post('/user/del', data)
}

export const updateUser = (data) => {
    return http.post('/user/update', data)
}

// 登录权限
export const getMenu = (data) => {
    return http.post('/permission/getMenu',data)
}
