import Mock from 'mockjs'
import homeMock from '../api/mockServe/home'
import infoTest from "@/api/mockServe/infoTest";

// 定义mock拦截
Mock.mock('/api/home/getData',homeMock)



Mock.mock(/\/api\/infoExamine\/loadingData/,'post',infoTest.getLoadingData)