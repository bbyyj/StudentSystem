<template>
    <div>
      <el-card class="ftype">
        <h1>基本信息</h1>
        <div class="wrapper" >
          <div v-for="(item, index) in items1" :key="index" class="blockitem" >
            <el-input class="elinput" v-model=info[item.model] :disabled="true">
              <label slot="prepend">{{ item.label }}</label>
            </el-input>
          </div>
        </div>
        <hr>
        <h1>联系方式</h1>
        <div class="wrapper">
          <div v-for="(item, index) in items2" :key="index" class="blockitem">
            <el-input class="elinput" v-model=info[item.model]>
              <label slot="prepend">{{ item.label }}</label>
            </el-input>
          </div>
        </div>
      </el-card>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        //基本信息
        items1: [
          { model: 'name', label: '姓名：' },
          { model: 'type', label: '学生类别：' },
          { model: 'sex', label: '性别：' },
          { model: 'nation', label: '民族：' },
          { model: 'sid', label: '学号：' },
          { model: 'id', label: '身份证号：' },
          { model: 'birthday', label: '出生年月：' },
          { model: 'class', label: '班级：' },
          { model: 'categories', label: '培养层次：' }
        ],
        //联系方式
        items2: [
          { model: 'pnumber', label: '个人联系电话：' },
          { model: 'address', label: '详细家庭住址：' },
          { model: 'ecnumber', label: '紧急联系人电话：' },
          { model: 'econtact', label: '紧急联系人：' },
          { model: 'email', label: '邮箱：' },
          { model: 'wechat', label: '微信：' }
        ],
        info: {
          name: '张三',
          type: '本科生',
          sex: '女',
          nation: '',
          sid: '',
          id: '',
          birthday: '',
          class: '',
          categories: '',
          //省略部分
          //联系方式
          pnumber: '',
          address: '',
          ecnumber: '',
          econtact: '',
          email: '',
          wechat: ''
        }
      };
    }
  };
  </script>
  

<style>
.wrapper {
  width: 100%;
  display: flex; 
  flex-wrap: wrap; 
  justify-content: space-between;
}

.elinput{
    max-block-size: 10px;
}
.ftype{
    font-size: small;
}


.blockitem{
    height: 100px;
    width: 30%; 
    margin-bottom: 15px;
}

</style>