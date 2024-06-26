import axios from "axios";

// 封装一个axios实例
const request = axios.create({
    // 通用请求的地址前缀
    baseURL: '/api',
    // 超时时间
    timeout: 100000
})

// 请求拦截器
request.interceptors.request.use(function (config) {
    // 在发送请求之前做什么
    return config;
}, function (error) {
    // 对请求错误做什么
    return Promise.reject(error);
})

// 添加响应拦截器
request.interceptors.response.use(function (response) {
    // 对响应数据做什么
    return response;
}, function (error) {
    // 对响应错误做什么
    return Promise.reject(error);
})

export default request