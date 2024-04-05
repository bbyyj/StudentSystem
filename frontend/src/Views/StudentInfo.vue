<template>
    <div>
      <el-card class="ftype">
        
        <h1>基本信息</h1>

        <el-form :model="info" ref="basicInfoForm" label-position="top">
          <div class="wrapper">
            <el-form-item v-for="(item, index) in items1" :key="index" :label="item.label" :prop="item.model" class="blockitem">
              <el-input class="elinput" v-model="info[item.model]" :disabled="true"></el-input>
            </el-form-item>
          </div>
        </el-form>

        <hr>

        <h1>联系方式</h1>

        <el-form :model="info" ref="contactForm" label-position="top">
          <div class="wrapper">
            <el-form-item v-for="(item, index) in items2" :key="index" :label="item.label" :prop="item.model" class="blockitem">
              <el-input class="elinput" v-model="info[item.model]"></el-input>
            </el-form-item>
          </div>
        </el-form>

        <el-button type="primary" round style="margin-bottom: 20px;" @click="submitContactForm">提交</el-button>
        <hr>

        <h1>修改密码</h1>
        <div class="wrapper">
          <el-form :model="passwordForm" :rules="rules" ref="form">
            <el-form-item label="新密码" prop="newPassword">
              <el-input class="elinput" type="password" v-model="passwordForm.newPassword" show-password>
              </el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmPassword">
              <el-input class="elinput" type="password" v-model="passwordForm.confirmPassword" show-password>
              </el-input>
            </el-form-item>
          </el-form>
        </div>
        <div v-if="error" class="error-message">{{ error }}</div>
        <el-button type="primary" round @click="submitPasswordChange" :loading="isSubmitting" style="margin-top: 20px;">提交</el-button>
      </el-card>
    </div>
  </template>
  
<script>
  export default {
    data() {
      return {
        //基本信息
        items1: [
          { model: 'admissionYear', label: '入学年份'},
          { model: 'class_id', label: '班级'},
          { model: 'name', label: '姓名' },
          { model: 'type', label: '学生类别' },
          { model: 'sex', label: '性别' },
          { model: 'nation', label: '民族' },
          { model: 'sid', label: '学号' },
          { model: 'pid', label: '身份证号' },
          { model: 'birthday', label: '出生年月' },
          { model: 'is_undergraduate', label: '培养层次' },
          { model: 'politics', label: '政治面貌'},
          { model: 'native_place', label: '籍贯'},
          { model: 'dormitory', label: '宿舍' },
        ],
        //联系方式
        items2: [
          { model: 'phone', label: '个人联系电话' },
          { model: 'address', label: '详细家庭住址' },
          { model: 'urgent_Phone', label: '紧急联系人电话' },
          { model: 'urgent_Name', label: '紧急联系人' },
          { model: 'email', label: '邮箱' },
          { model: 'wechat', label: '微信' }
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
          pnumber: '123456',
          address: '',
          ecnumber: '',
          econtact: '',
          email: '',
          wechat: ''
        },
        passwordForm: {
          newPassword: '',
          confirmPassword: ''
        },
        rules: {
          newPassword: [
            { required: true, message: '新密码不能为空' },
            { min: 8, message: '新密码长度不能少于8个字符' },
            { pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+/, message: '新密码必须包含大写字母、小写字母和数字' }
          ],
          confirmPassword: [
            { required: true, message: '确认密码不能为空' },
            { validator: this.validateConfirmPassword, trigger: 'blur' }
          ]
        },
        error: '',
        isSubmitting: false
      };
    },
  methods: {
    submitContactForm() {
      this.$refs.contactForm.validate(async valid => {
        if (!valid) return;
        // 后端API
        // 提交成功提示操作成功
      });
    },
    validateConfirmPassword(_, value, callback) {
      if (value !== this.passwordForm.newPassword) {
        this.error = '新密码和确认密码不一致';
        callback(true);
      } else {
        this.error = '';
        callback(false);
      }
    },
    async submitPasswordChange() {
      this.isSubmitting = true;
      this.error = null;

      try {
        await this.$refs.form.validate();

        // 调用后端API
        // 更新后清空表单,提示用户操作成功

      } catch (e) {
        console.error(e);
        this.error = '密码修改失败，请稍后重试';
      } finally {
        this.isSubmitting = false;
      }
    }
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