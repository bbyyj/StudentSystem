<template>
    <div style="font-size: small;">
<!--      <el-card class="ftype">-->
        
        <h1>基本信息</h1>

        <el-form :model="info1" ref="basicInfoForm" label-position="top" size="small">
          <div class="wrapper">
            <el-form-item 
              v-for="(item, index) in items1" 
              :key="index" 
              :label="item.label" 
              :prop="item.model" 
              class="blockitem">

              <el-input class="elinput" v-model="info1[item.model]" :disabled="true"></el-input>
            
            </el-form-item>
          </div>
        </el-form>

        <hr>

        <h1>修改联系方式</h1>

        <el-form :model="info2" ref="contactForm" label-position="top">
          <div class="wrapper">
            <el-form-item 
              v-for="(item, index) in items2" 
              :key="index" 
              :label="item.label" 
              :prop="item.model" 
              class="blockitem" size="small">

              <el-input class="elinput" v-model="info2[item.model]"></el-input>
              
            </el-form-item>
          </div>
        </el-form>

        <el-button type="primary" style="margin-bottom: 5px;" @click="submitContactForm" size="small">提交</el-button>


        <hr>

        <h1>修改密码</h1>
        <div class="wrapper">
          <el-form :model="passwordForm" :rules="rules" ref="form" size="small">

            <el-form-item label="新密码：" prop="newPassword" label-width="100px">
              <el-input class="elinput" type="password" v-model="passwordForm.newPassword" show-password>
              </el-input>
            </el-form-item>

            <el-form-item label="确认密码：" prop="confirmPassword" label-width="100px">
              <el-input class="elinput" type="password" v-model="passwordForm.confirmPassword" show-password>
              </el-input>
            </el-form-item>

          </el-form>
        </div>

        <el-button type="primary" @click="submitPasswordChange" :loading="isSubmitting" size="small">提交</el-button>

<!--      </el-card>-->
    </div>
  </template>
  
<script>
import axios from 'axios';
import Cookie from 'js-cookie'

  export default {
    data() {
      return {
        //基本信息
        items1: [
          { model: 'admissionYear', label: '入学年份'},
          { model: 'classId', label: '班级'},
          { model: 'name', label: '姓名' },
          { model: 'type', label: '学生类别' },
          { model: 'netId', label: 'netID' },
          { model: 'sex', label: '性别' },
          { model: 'nation', label: '民族' },
          { model: 'sid', label: '学号' },
          { model: 'pid', label: '身份证号' },
          { model: 'birth', label: '出生年月' },
          { model: 'undergraduate', label: '培养层次' },
          { model: 'politics', label: '政治面貌'},
          { model: 'nativePlace', label: '籍贯'},
          { model: 'dormitory', label: '宿舍' },
        ],
        //联系方式
        items2: [
          { model: 'phone', label: '个人联系电话' },
          { model: 'address', label: '详细家庭住址' },
          { model: 'urgentPhone', label: '紧急联系人电话' },
          { model: 'urgentName', label: '紧急联系人' },
          { model: 'email', label: '邮箱' },
          { model: 'wechat', label: '微信' }
        ],
        info1: {
          admissionYear: '',
          classId: '',
          name: '',
          type: '',
          sex: '',
          nation: '',
          sid: '',
          pid: '',
          birth: '',
          undergraduate: '',
          politics: '',
          native_place: '',
          dormitory: '',
        },
        info2: {
          phone: '',
          address: '',
          urgentPhone: '',
          urgentName: '',
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
            { validator: this.validateConfirmPassword }
          ]
        },
        isSubmitting: false
      };
    },
  created() {
    this.loadInfo();
  },
  methods: {
    loadInfo(){
      const apiUrl = 'http://127.0.0.1:8080/auth/getStudentInfo';
      const params = { netId: Cookie.get('netId') };

      axios.get(apiUrl, {params})
        .then(response => {
          if(response.data.code !== 200){
            this.$message.error('获取学生信息失败');
            return;
          }
          this.fillData(response.data);
        })
        .catch(error => {
          console.error("Error fetching data:", error);
        });
    },
    
    fillData(responseData) {
      const studentData = responseData.data.student;
      
      // 填充基本信息到info1
      this.items1.forEach(item => {
        if (item.model === 'undergraduate' && studentData.hasOwnProperty(item.model)) {
          this.info1[item.model] = studentData[item.model] ? '本科生' : '研究生';
        }
        else if(item.model === 'classId' && studentData.hasOwnProperty(item.model)){
          this.info1[item.model] = studentData[item.model] === 0 ? '研究生' : studentData[item.model];
        }
        else if (studentData.hasOwnProperty(item.model)) {
          this.info1[item.model] = studentData[item.model];
        }
      });

      // 填充联系方式到info2
      this.items2.forEach(item => {
        if (studentData.hasOwnProperty(item.model)) {
          this.info2[item.model] = studentData[item.model];
        }
      });
    },

    async submitContactForm() {
      try {
        // 验证至少有一项不为空
        const hasValue = Object.keys(this.info2).some(key => this.info2[key]);
        if (!hasValue) {
          this.$message.error('请至少填写一项信息');
          return; // 阻止表单提交
        }

        const formData = this.info2;
        formData.netId = Cookie.get('netId');
        const apiUrl = 'http://127.0.0.1:8080/student/modifyMyInfo';
        const response = await axios.put(apiUrl, formData);
        if (response.data.code === 200) {
          this.$message.success('提交成功！');
          this.loadInfo();
        } else {
          this.$message.error('提交失败，请稍后重试！');
        }
      } catch (error) {
        console.error('提交时发生错误:', error);
        this.$message.error('操作失败，请联系管理员！');
      }
    },

    validateConfirmPassword(_, value, callback) {
      if (value !== this.passwordForm.newPassword) {
        callback(new Error('两次输入密码不一致!')); 
      } else {
        callback(); 
      }
    },

    async submitPasswordChange() {
      this.isSubmitting = true;
      try {
        const isValid = await this.$refs.form.validate();
        if (!isValid) {
          this.$message.error('请检查输入信息是否符合要求');
          this.isSubmitting = false;
          return;
        }

        const apiUrl = 'http://127.0.0.1:8080/student/modifyPassword';
        const params = {
          netId: this.info1.netId,
          password: this.passwordForm.newPassword
        };

        const response = await axios.put(apiUrl, params);
        if (response.data.code === 200) {
          this.$message.success('修改成功！');
        
          // 清空表单数据
          this.$refs.form.resetFields();  
          // 取消已触发的验证提示
          this.$nextTick(() => {
            this.$refs.form.clearValidate();
          });

        } else {
          this.$message.error('修改失败，请稍后重试！');
        }

      } catch (e) {
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
  justify-content: flex-start;
}

.elinput{
  //max-width: 100%;
  display: flex;
  align-items: center;
}

.ftype{
    font-size: small;
}

.blockitem{
    height: 70px;
    width: calc(17% - 60px);
    margin-bottom: 10px;
    margin-right: 15px;
}

/* 移除最后一个块元素的右外边距，以避免整体右侧多出空白 */
.blockitem:last-child {
  margin-right: 0;
}

/* 辅助元素 让未满三个元素的行对齐 */
.wrapper::after {
  content: "";
  width: calc(33.333% - 30px);
  height: 0;
}

</style>