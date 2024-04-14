import request from '../utils/request'
import axios from "axios";

// 请求首页数据,直接把这个对象导出
export const getData = () => {
    // 返回一个promise
    return request.get('/home/getData')
}

// 下面四个:用户管理-后端-网络请求接口
export const getUser = (params) => {
    return request.get('/user/get/', params)
}

export const createUser = (data) => {
    return request.post('/user/create', data)
}

export const deleteUser = (data) => {
    return request.post('/user/del', data)
}

export const updateUser = (data) => {
    return request.post('/user/update', data)
}

// 登录权限
export const getMenu = (data) => {
    return request.post('/permission/getMenu',data)
}

// 请求全部综测细则
export  const getAllRuleDetail = () => {
    return axios.get("https://mock.apifox.com/m2/4212159-0-default/162417372");
}

// 新增综测大类别
// post 传入{综测大类别名称} 返回状态码
export const addNewRuleType = (categoryName) => {
  const requestBody = {
    typename: categoryName,
  };
  return axios
    .post(
      "https://mock.apifox.com/m2/4212159-0-default/162116977?apifoxApiId=162116977",
      requestBody
    )
    .then((response) => response.data)
    .catch((error) => {
      console.error("Error making POST request:", error);
      throw error;
    });
};


// 修改综测大类别
// put 传入{tid 新的综测大类别名称} 返回状态码
export const editRuleType = (tid, newTypeName) => {
  const requestBody = {
    typename: newTypeName,
  };
  return axios
    .put(
      `https://mock.apifox.com/m2/4212159-0-default/162417373?tid=${tid}`,
      requestBody
    )
    .then((response) => response.data)
    .catch((error) => {
      console.error("Error making PUT request:", error);
      throw error;
    });
};


// 删除综测大类别
// delete 传入{tid}  返回状态码
export const deleteRuleType = (tid) => {
  return axios
    .delete(`https://mock.apifox.com/m2/4212159-0-default/162124197?tid=${tid}`)
    .then((response) => response.data)
    .catch((error) => {
      console.error("Error making DELETE request:", error);
      throw error;
    });
};


// 新增加分条件
// post 传入{加分条件名称+分值}  返回状态码
export const addNewRule = (ruleName, score) => {
  const requestBody = {
    name: ruleName,
    score: score,
  };
  return axios
    .post("https://mock.apifox.com/m2/4212159-0-default/162417375", requestBody)
    .then((response) => response.data)
    .catch((error) => {
      console.error("Error making POST request:", error);
      throw error;
    });
};

// 修改加分条件
// put 传入{rid 新的加分条件名称} 新的分数 返回状态码
export const editRule = (rid, newRuleName, newScore) => {
  const requestBody = {
    name: newRuleName,
    score: newScore,
  };
  return axios
    .put(
      `https://mock.apifox.com/m2/4212159-0-default/162417376?rid=${rid}`,
      requestBody
    )
    .then((response) => response.data)
    .catch((error) => {
      console.error("Error making PUT request:", error);
      throw error;
    });
};


// 删除加分条件
// delete 传入{rid}  返回状态码
export const deleteRule = (rid) => {
  return axios
    .delete(`https://mock.apifox.com/m2/4212159-0-default/162417377?rid=${rid}`)
    .then((response) => response.data)
    .catch((error) => {
      console.error("Error making DELETE request:", error);
      throw error;
    });
};