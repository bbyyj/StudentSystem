<template>
    <el-form ref="form" class="login_container" :model="login" status-icon :rules="rules" label-width="70px">
        <!-- 此处不想要radio的样式所以使用了el-tabs -->
        <el-tabs v-model="identity" style="margin-left: 90px; margin-right: 90px;">
            <el-tab-pane label="学生" name="student"></el-tab-pane>
            <el-tab-pane label="教师" name="teacher"></el-tab-pane>
        </el-tabs>
        <!-- prop对应rules里的键 -->
        <el-form-item label="netId" prop="netId">
            <el-input v-model="login.netId" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="login.password" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button @click="submit" type="primary" style="margin-left:30px;margin-top:10px">提交</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import axios from 'axios';
import Cookie from 'js-cookie'
import { logIn } from '../api/index'
import MenuData from '@/data/MenuData.js';
export default {
    data() {
        return {
            identity: 'student', // 默认选中学生选项
            // 登陆数据
            login: {
                netId: '',
                password: ''
            },
            menu: {},
            // 校验规则
            rules: {
                netId: [{ required: 'true', message: '请输入netID', trigger: 'blur' }],
                password: [{ required: 'true', message: '请输入密码', trigger: 'blur' }]
            }
        }
    },
    methods: {
        submit() {
            // 表单的校验
            this.$refs.form.validate(async (valid) => {
                if (valid) {
                    // 传入表单数据
                    try {
                        const loginResult = await logIn(this.login, this.identity);
                        if (loginResult.data.code === 200) {
                            // 记录cookie
                            Cookie.set('token', loginResult.data.data.token);

                            // 等待 getMenu 方法执行完毕
                            await this.getMenuAsync();

                            this.$store.commit('setMenu', this.menu);
                            this.$store.commit('addMenu', this.$router);
                            this.$router.push('/home');
                        } else {
                            this.$message.error('身份或账号密码错误！');
                        }
                    } catch (error) {
                        console.error('An error occurred during login or getMenu:', error);
                        this.$message.error('登录过程中出现问题，请重试！');
                    }
                }
            });
        },

        async getMenuAsync() {
            if (this.identity === 'student') {
                const apiUrl = 'http://127.0.0.1:8080/auth/getStudentInfo';
                const params = { netId: this.login.netId };
                try {
                    const response = await axios.get(apiUrl, { params });
                    const data = response.data;
                    const studentRole = data.data.student.studentRole;

                    this.menu = studentRole === 'MONITOR' ? MenuData[3] : MenuData[2];
                    Cookie.set('netId', this.login.netId);
                    Cookie.set('Role', studentRole);
                    Cookie.set('sid', data.data.student.sid);
                    Cookie.set('classId', data.data.student.classId)
                    Cookie.set('admissionYear', data.data.student.admissionYear)
                    Cookie.set('undergraduate', data.data.student.undergraduate)
                } catch (error) {
                    console.error('获取学生菜单时出现问题:', error);
                }
            } else if (this.identity === 'teacher') {
                const apiUrl = 'http://127.0.0.1:8080/auth/getAdminInfo';
                const params = { netId: this.login.netId };
                try {
                    const response = await axios.get(apiUrl, { params });
                    const data = response.data;
                    const adminRole = data.data.admin.adminRole;

                    this.menu = adminRole === 'TOP_ADMIN' ? MenuData[0] : MenuData[1];
                    Cookie.set('netId', this.login.netId);
                    Cookie.set('Role', adminRole);
                    Cookie.set('classId', data.data.admin.classId)
                    Cookie.set('admissionYear', data.data.admin.admissionYear)
                    Cookie.set('undergraduate', data.data.admin.undergraduate)

                } catch (error) {
                    console.error('获取学生菜单时出现问题:', error);
                }
            }
        }
    }
}
</script>

<style lang="less" scoped>
.login_container {
    width: 350px;
    border: 1px solid #eaeaea;

    // 居中
    margin: 180px auto;

    padding: 35px 35px 15px 35px;

    // 让padding在width里面
    box-sizing: border-box;

    border-radius: 15px;
    background-color: #fff;
    box-shadow: 0 0 25px #cac6c6;

    .login_title {
        color: #505458;
        // 左右居中
        text-align: center;
        margin-bottom: 40px;
    }

    .el-input {
        width: 198px;
    }
}
</style>