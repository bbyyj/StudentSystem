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
export const logIn = (data,identity) => {
  if(identity==='student')
    return axios.post('http://127.0.0.1:8080/auth/signinStudent',data)
  else if(identity==='teacher')
    return axios.post('http://127.0.0.1:8080/auth/signinAdmin',data)
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


// 获取当前全部综测发布的内容
export const getAllCompAssBegin = () => {
  console.log("获取当前全部综测发布的内容")
  return axios.get("https://mock.apifox.com/m2/4212159-0-default/162417372");
};


// 新增综测
// post 传入{综测名称，综测开始时间，综测结束时间} 返回状态码等
// publish_time，begin_time，finish_time
export const addCompAssBegin = (pName, begin_time, finish_time) => {
  console.log("新增综测")
  const requestBody = {
    pName: pName,
  };
  return axios
    .post("https://mock.apifox.com/m2/4212159-0-default/162417375", requestBody)
    .then((response) => response.data)
    .catch((error) => {
      console.error("Error making POST request:", error);
      throw error;
    });
};


// 删除综测
// delete 传入{综测pid}  返回状态码等
export const deleteCompAssBegin = (pid) => {
  console.log("删除综测")
  return axios
    .delete(`https://mock.apifox.com/m2/4212159-0-default/162417377?rid=${pid}`)
    .then((response) => response.data)
    .catch((error) => {
      console.error("Error making DELETE request:", error);
      throw error;
    });
};


// 修改综测 
//put 传入{综测pid，综测名称，综测开始时间，综测结束时间} 返回状态码等
export const editCompAssBegin = (rid, newRuleName, newScore) => {
  console.log("修改综测")
  const requestBody = {
    name: newRuleName,
    score: newScore,
  };
  return axios
    .put(
      `https://mock.apifox.com/m2/4212159-0-default/162417376?rid=${pid}`,
      requestBody
    )
    .then((response) => response.data)
    .catch((error) => {
      console.error("Error making PUT request:", error);
      throw error;
    });
};