<template>
  <div>
  <el-card>
    <!-- 搜索部分 -->
    <el-row style="margin-bottom: 20px;">
      <el-col :span="4">
        <el-select v-model="searchKeyword" placeholder="请选择查询条件">
          <el-option value="全部">全部</el-option>
          <el-option v-for="item in studentSearchForm.items" :key="item.fieldName" :label="item.label" :value="item.fieldName" />
        </el-select>    
      </el-col>
      <el-col :span="4">
        <el-input v-model="searchvalue" placeholder="请输入查询内容" />
      </el-col>
      <el-col :span="4">
        <el-button @click="searchStudents(1, pageSize)" icon="el-icon-search">查询</el-button>
      </el-col>

      <el-col :span="8" >
        <el-popover
          placement="bottom"
          width="auto"
          trigger="click">
          <el-radio-group v-model="selectedType" @change="handleActionChange" size="mini" style="margin-bottom: 5px;">
            <el-radio-button label="1" >批量生成学生</el-radio-button>
            <el-radio-button label="2" >仅更新资料</el-radio-button>
          </el-radio-group>
          
          <el-upload 
            :action="uploadUrl"  
            :multiple="false" :limit="1"  
            :file-list="fileList" 
            :show-file-list="true" accept="xlsx" 
            :on-exceed="handleExceed" 
            :on-error="handleError" 
            :before-upload="validate" 
            :on-success="handleSuccess">
            <el-button size="small" type="plain">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传单个.xlsx格式的Excel文件</div>
          </el-upload>
          <el-button slot="reference" type="primary" round @click="importStudents">批量导入</el-button>
        </el-popover>
        <el-button type="primary" round  @click="addStudent">单个导入</el-button>
      </el-col>
    </el-row>

    <!-- 表格部分 -->
    <el-table :data="Students" style="width: 100%" size="mini">
        <el-table-column v-for="item in studentForm.items" 
        :key="item.fieldName" :prop="item.fieldName" :label="item.label" :width="item.width" />

      <el-table-column fixed="right" label="操作" width="180">
        <template #default="{ row }">
          <el-button type="text" size="small" @click="editStudent(row)" style="margin-right: 8px;">编辑</el-button>
          <el-popconfirm title="确定删除该学生？" @confirm="deleteStudent(row)">
            <el-button slot="reference" type="text" size="small">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 10, 20]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalStudents">
    </el-pagination>
    
  </el-card>
    
  <el-dialog title="编辑学生信息" :visible.sync="dialogVisible" >
    <el-form :model="student" label-width="80px"  class="wrapper" >
      <el-form-item v-for="field in studentForm.items" 
                            :prop="field.fieldName"  
                            :key="field.fieldName" 
                            :label="field.label"
                            class="blockitem">
        <template v-if="field.controlType === 'input'">
            <el-input :id="field.fieldName" 
                    :name="field.fieldName" 
                    v-model="student[field.fieldName]" 
                    :placeholder="field.message"></el-input>
        </template>
        <template v-else-if="field.controlType === 'datepicker'">
            <el-date-picker :id="field.fieldName" 
                            :name="field.fieldName" 
                            v-model="student[field.fieldName]"
                            type="month"
                            value-format="yyyy-MM"></el-date-picker>
        </template>
        <template v-else-if="field.controlType === 'select'">
            <el-select :id="field.fieldName" 
                    :name="field.fieldName" 
                    v-model="student[field.fieldName]">
                <el-option v-for="item in studentForm.selectOptions[field.fieldName]" 
                        :key="item.value" 
                        :label="item.label" 
                        :value="item.value"></el-option>
            </el-select>
        </template>
        <template v-else>
            <span style="color: red;">Unsupported form control type: {{ field.controlType }}</span>
        </template>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="saveStudent">保存</el-button>
    </span>
  </el-dialog>

  <el-dialog title="添加学生信息" :visible.sync="addDialogVisible" >
    <el-form :model="student" label-width="80px" class="wrapper">
      <!-- <el-form-item v-for="item in studentAddForm" :label="item.label" :key="item.model" class="blockitem">
        <el-input  v-model="student[item.model]" :placeholder="item.label" />
      </el-form-item> -->
      <el-form-item v-for="field in studentAddForm.items" 
                            :prop="field.fieldName"  
                            :key="field.fieldName" 
                            :label="field.label"
                            class="blockitem">
        <template v-if="field.controlType === 'input'">
            <el-input :id="field.fieldName" 
                    :name="field.fieldName" 
                    v-model="student[field.fieldName]" 
                    :placeholder="field.message"></el-input>
        </template>
        <template v-else-if="field.controlType === 'datepicker'">
            <el-date-picker :id="field.fieldName" 
                            :name="field.fieldName" 
                            v-model="student[field.fieldName]"
                            type="month"
                            value-format="yyyy-MM"></el-date-picker>
        </template>
        <template v-else-if="field.controlType === 'select'">
            <el-select :id="field.fieldName" 
                    :name="field.fieldName" 
                    v-model="student[field.fieldName]">
                <el-option v-for="item in studentAddForm.selectOptions[field.fieldName]" 
                        :key="item.value" 
                        :label="item.label" 
                        :value="item.value"></el-option>
            </el-select>
        </template>
        <template v-else>
            <span style="color: red;">Unsupported form control type: {{ field.controlType }}</span>
        </template>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="saveAddStudent">保存</el-button>
    </span>
  </el-dialog>

  </div>
</template>

<script>
import axios from 'axios';
import Cookies from 'js-cookie';
import StudentInfoData from '@/data/StudentInfoData.js';

export default {
  data() {
    return {
      //正常使用的数据
      Students: [
      { "admissionYear": "2023",
        "classId": 1,
        "name": "罗翔",
        "studentRole": "NOT_MONITOR",
        "type": "境内生",
        "netId": "",
        "nation": "汉",
        "sid": "23xxxxxx",
        "pid": "身份证号",
        "birth": "出生年份",
        "politics": "政治面貌",
        "dormitory": "宿舍",
        "phone": "电话",
        "address": "地址",
        "urgentPhone": "",
        "urgentName": "null",
        "wechat": "null",
        "email": "null",
        }
      ],
      studentForm:[],
      //添加时用到的，去掉了学生职位
      studentAddForm: [],
      //修改或添加时的表单
      student: {
        "admissionYear": "",
        "classId": "",
        "name": "",
        "studentRole": "",
        "type": "",
        "netId": "",
        "nation": "",
        "sid": "",
        "pid": "",
        "birth": "",
        "undergraduate": "",
        "politics": "",
        "nativePlace": "",
        "dormitory": "",
        "phone": "",
        "address": "",
        "urgentPhone": "",
        "urgentName": "",
        "wechat": "",
        "email": ""
      },
      // 搜索用 班主任和管理员不一样
      studentSearchForm: [],
      // 单个添加和修改信息的对话框
      dialogVisible: false,
      addDialogVisible: false,
      // 搜索相关属性
      searchvalue: '',
      searchKeyword: '',
      isseraching: false,
      // 文件导入相关
      selectedType: '1', // 初始选中项
      fileList: [], // 上传文件列表
      uploadUrl: 'http://127.0.0.1:8080/auth/signupStudentExcel', // 批批处理批量生成的默认接口
      // 分页相关属性
      currentPage: 1,
      pageSize: 5,
      totalStudents: 0,
    };
  },
  created() {
    this.loadStudents(this.pageSize, this.currentPage);
    this.studentForm = StudentInfoData[0];
    this.studentAddForm = StudentInfoData[1];
    const identity = Cookies.get('Role');
    if(identity === 'TOP_ADMIN'){
      this.studentSearchForm = StudentInfoData[2];
    }
    else{
      this.studentSearchForm = StudentInfoData[3];
    }
  },
  methods: {
    loadStudents(pageSize, currentPage) {
      const indentity = Cookies.get('Role');
      let apiUrl;
      let params;
      if(indentity === 'TOP_ADMIN'){
        apiUrl = 'http://127.0.0.1:8080/topAdmin/getAllStudent';
        params = {
                    page: currentPage - 1, // 添加当前页码参数
                    size: pageSize // 添加每页大小参数
                  }
      }
      else{
        apiUrl = 'http://127.0.0.1:8080/headTeacher/getClassStudent';
        params = {
                    isUndergraduate: Cookies.get('undergraduate'),
                    admissionYear: Cookies.get('admissionYear'),
                    classId: Cookies.get('classId'),
                    page: currentPage - 1, // 添加当前页码参数
                    size: pageSize // 添加每页大小参数
                  }
      }
      
        
      axios.get(apiUrl, { params }) // 将参数对象作为配置传递给GET请求
        .then(response => {
          if(response.data.code !== 200){
            this.$message.error('获取学生信息失败');
            return;
          }
          const data = response.data;
          const studentList = data.data.content;

          // 判断学生类型，并替换为本科生或研究生
          studentList.forEach(student => {
            if (student.undergraduate) {
              student.undergraduate = '本科生';
            } else {
              student.undergraduate = '研究生';
            }
          });

          this.totalStudents = data.data.totalElements; 
          this.Students = studentList;
          
        })
        .catch(error => {
          console.error('There was a problem with your fetch operation:', error);
        });
    },

    // 页面相关
    // 处理每页显示数量变化
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      if(this.isseraching){
        this.searchStudents(this.currentPage, this.pageSize);
      }
      else{
        his.loadStudents(this.pageSize, this.currentPage);
      }
      t
    },
    // 处理当前页变化
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      if(this.isseraching){
        this.searchStudents(this.currentPage, this.pageSize);
      }
      else{
        this.loadStudents(this.pageSize, this.currentPage);
      }
    },

    // 增删改查
    async searchStudents(currentPage, pageSize) {
      // 检查是否有选择的查询条件
      if (!this.searchKeyword) {
          this.$message('请选择查询条件后再进行搜索！');
          return;
        }

      // 获取用户选择的查询条件\查询内容
      let selectedKeyword = this.searchKeyword;
      let searchContent = this.searchvalue;

      if(selectedKeyword === '全部'){
        this.isseraching = false;
        this.loadStudents(pageSize, currentPage);
        return;
      }
      console.log(selectedKeyword);
      if(selectedKeyword === "undergraduate" && searchContent === '本科生'){
        selectedKeyword = 'isUndergraduate';
        searchContent = true;
      }
      if(selectedKeyword === "undergraduate" && searchContent === '研究生'){
        selectedKeyword = 'isUndergraduate';
        searchContent = false;
      }

      const indentity = Cookies.get('Role');
      let apiUrl;
      let params;
      if(indentity === 'TOP_ADMIN'){
        apiUrl = 'http://127.0.0.1:8080/headTeacher/searchStuInfo';
        params = {  keyname: selectedKeyword, 
                    keyword: searchContent, 
                    page: currentPage - 1, 
                    size: pageSize
              };
      }
      else{
        apiUrl = 'http://127.0.0.1:8080/headTeacher/searchClassStuInfo';
        params = {
                    isUndergraduate: Cookies.get('undergraduate'),
                    admissionYear: Cookies.get('admissionYear'),
                    classId: Cookies.get('classId'),
                    keyname: selectedKeyword, 
                    keyword: searchContent, 
                    page: currentPage - 1, // 添加当前页码参数
                    size: pageSize // 添加每页大小参数
                  }
      }

      

      axios.get( apiUrl, { params })
        .then(response => {
          if(response.data.code !== 200){
            this.$message.error('搜索失败，请检查输入是否正确！');
            return;
          }
          const data = response.data;
          const studentList = data.data.content;

          studentList.forEach(student => {
            if (student.undergraduate) {
              student.undergraduate = '本科生';
            } else {
              student.undergraduate = '研究生';
            }
          });
          
          // 更新表格数据
          this.Students = studentList;
          // 更新总条数，如果API返回
          this.totalStudents = data.data.totalElements;
          this.isseraching = true;

          console.log(this.Students);
        })
        .catch(error => {
          console.error('Error during search:', error);
        });
    },

    addStudent() {
      // 单个学生导入
      this.addDialogVisible = true;
    },
    importStudents() {
      // 批量导入
    },
    editStudent(row) {
      // 编辑
      this.dialogVisible = true;
      this.dialogtype = 'edit';
      // 复制选中行数据到student对象
      this.student.admissionYear = row.admissionYear;
      this.student.classId = row.classId;
      this.student.name = row.name;
      this.student.studentRole = row.studentRole;
      this.student.type = row.type;
      this.student.netId = row.netId;
      this.student.nation = row.nation;
      this.student.sid = row.sid;
      this.student.pid = row.pid;
      this.student.birth = row.birth;
      this.student.undergraduate = row.undergraduate;
      this.student.politics = row.politics;
      this.student.nativePlace = row.nativePlace;
      this.student.dormitory = row.dormitory;
      this.student.phone = row.phone;
      this.student.address = row.address;
      this.student.urgentPhone = row.urgentPhone;
      this.student.urgentName = row.urgentName; 
      this.student.wechat = row.wechat;
      this.student.email = row.email;
    },
    deleteStudent(row) {
      // 删除
      const params = {  netId: row.netId};

      axios.delete( 'http://127.0.0.1:8080/headTeacher/deleteStuInfo', { params })
        .then(response => {
          if(response.data.code !== 200){
            this.$message.error('删除失败！');
            return;
          }
          const code = response.data.code; 
          if(code === 200){
            this.$message.success('删除成功！');
            this.loadStudents(this.pageSize, this.currentPage); // 刷新数据
          }
          else
          {
            this.$message.error('删除失败，请稍后再试！');
          }
        })
        .catch(error => {
          console.error('Error during search:', error);
        });
    },
    async saveStudent() {
      try {
        let apiEndpoint;
        let method;
        let payload = JSON.parse(JSON.stringify(this.student)); // 创建payload的深拷贝，防止修改原始数据

        // 根据undergraduate的值调整payload
        if (payload.undergraduate === '本科生') {
          payload.undergraduate = true;
        } else {
          payload.undergraduate = false; // 研究生
        }

        apiEndpoint = 'http://127.0.0.1:8080/headTeacher/modifyStuInfo'; 
        method = 'put';
      
        // 发送请求
        const response = await axios({
          url: apiEndpoint,
          method: method,
          data: payload
        });
        if (response.data.code === 200) {
          this.$message('操作成功');
          this.closeDialog();
          this.loadStudents(this.pageSize, this.currentPage); // 刷新数据
        } else {
          this.$message('操作失败，请重试');
        }
      } catch (error) {
        console.error('错误:', error);
      }
    },

    async saveAddStudent() {
      try {
        let apiEndpoint;
        let method;
        let payload = JSON.parse(JSON.stringify(this.student)); // 创建payload的深拷贝，防止修改原始数据

        delete payload.studentRole;
        // 根据undergraduate的值调整payload
        if (payload.undergraduate === '本科生') {
          payload.undergraduate = true;
        } else {
          payload.undergraduate = false; // 研究生
        }

        apiEndpoint = 'http://127.0.0.1:8080/auth/signupStudent'; // 添加学生的API地址 
        method = 'post'; 

        console.log('payload:', payload);
        
        // 发送请求
        const response = await axios({
          url: apiEndpoint,
          method: method,
          data: payload
        });

        if (response.data.code === 200) {
          this.$message('操作成功');
          this.closeDialog();
          this.loadStudents(this.pageSize, this.currentPage); // 刷新数据
        } else {
          this.$message('操作失败，请检查输入！');
        }
      } catch (error) {
        console.error('错误:', error);
      }
    },

    closeDialog(){
      this.dialogVisible = false;
      this.addDialogVisible = false;
      this.student = {
        "admissionYear": "",
        "classId": "",
        "name": "",
        "studentRole": "",
        "type": "",
        "netId": "",
        "nation": "",
        "sid": "",
        "pid": "",
        "birth": "",
        "undergraduate": "",
        "politics": "",
        "nativePlace": "",
        "dormitory": "",
        "phone": "",
        "address": "",
        "urgentPhone": "",
        "urgentName": "",
        "wechat": "",
        "email": "",};
    },

    // 文件处理
    handleActionChange(value) {
      this.selectedType = value; // 更新选中状态
      this.uploadUrl = value === '1'
        ? 'http://127.0.0.1:8080/auth/signupStudentExcel' // 批量生成接口
        : 'http://127.0.0.1:8080/headTeacher/modifyStuInfoExcel'; // 仅更新接口
    },

    handleExceed(fileList) {
      console.log('文件超过限制');
    },
    handleError(err, file) {
      console.error(err);
      this.$message('上传失败，请重试');
      // 显示错误提示给用户
    },
    validate(file) {
      const isExcel = file.name.endsWith('.xlsx');
      if (!isExcel) {
        this.$message('只支持.xlsx文件');
        return false; // 阻阻止不符合条件的文件上传
      }
      return true; // 允许继续上传
    },
    handleSuccess(response, file) {
      console.log(response);
      this.$message('上传成功');
      this.loadStudents(this.pageSize, this.currentPage);
    }
  }
  
};
</script>

<style scoped>
.wrapper {
  width: 100%;
  height: 100%;
  display: flex; 
  flex-wrap: wrap; 
  justify-content: space-between;
}
.blockitem {
  width: 300px; 
  height: auto;
  margin-bottom: 2%;
}


</style>

