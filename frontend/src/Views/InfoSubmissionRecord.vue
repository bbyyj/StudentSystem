<template>
    <div>
    <el-card>
      <!-- 搜索部分 -->
      <el-row style="margin-bottom: 20px;">
        <el-col :span="4">
          <el-select v-model="searchKeyword" placeholder="请选择查询条件">
            <el-option v-for="item in recordForm" :key="item.model" :label="item.label" :value="item.model" />
          </el-select>    
        </el-col>
        <el-col :span="4">
          <el-input v-model="searchvalue" placeholder="请输入查询内容" />
        </el-col>
        <el-col :span="4">
          <el-button @click="searchStudents" icon="el-icon-search">查询</el-button>
        </el-col>
  
      </el-row>
  
      <!-- 表格部分 -->
      <el-table :data="Students" style="width: 100%" size="mini">
        <el-table-column v-for="item in recordForm" 
          :key="item.model" :prop="item.model" :label="item.label" :width="item.width"  />
  
        <el-table-column label="附件" width="80">
          <template #default="{ row }">
            <el-button type="text" size="small"  style="margin-right: 8px;">查看</el-button>
            <!-- <el-button type="text" size="small" @click="editStudent(row)" style="margin-right: 8px;">查看</el-button> -->
          </template>
        </el-table-column>
        <el-table-column label="详细信息" width="80">
          <template #default="{ row }">
            <el-button type="text" size="small" @click="editStudent(row)" style="margin-right: 8px;">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <!-- 分页 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalStudents">
      </el-pagination>
  
    </el-card>
      
    <el-dialog title="详细信息" :visible.sync="dialogVisible" >
      <el-form :model="student" label-width="80px" size="mini" class="wrapper">
        <el-form-item v-for="item in recordForm" :label="item.label" :key="item.model" class="blockitem">
          <el-input  v-model="student[item.model]" :placeholder="item.label" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
  
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        Students: [
        { "admissionYear": "1",
          "classId": "xxx",
          "name": "xxxxxx",
          "type": "xxxxxxx",
          "sex": "通过",
          "nation": "xxxxxxxxx",
          }
        ],
        recordForm: [
          { model: 'admissionYear', label: '编号', width: '70px' },
          { model: 'classId', label: '类别', width: '70px' },
          { model: 'name', label: '综测大类', width: '150px' },
          { model: 'type', label: '综测细则', width: '250px'},
          { model: 'sex', label: '状态', width: '100px' },
          { model: 'nation', label: '审核意见', width: '250px' },
        ],
        //修改或添加时的表单
        student: { 
          "admissionYear": "",
          "class_Id": "",
          "name": "",
          "type": "",
          "sex": "",
          "nation": "",
          "sid": "",
          "pid": "",
          "birthday": "",
          "politics": "",
          "dormitory": "",
          "phone": "",
          "address": "",
          "urgent_Phone": "",
          "urgent_Name": "null",
          "wechat": "",
          "email": "",
        },
        // 单个添加和修改信息的对话框
        dialogVisible: false,
        // 搜索相关属性
        searchvalue: '',
        searchKeyword: '',
        // 分页相关属性
        currentPage: 1,
        pageSize: 5,
        totalStudents: 0,
      };
    },
    created() {
      this.loadStudents(this.pageSize, this.currentPage);
    },
    methods: {
      // 获取学生数据并加载到表格中
      loadStudents(pageSize, currentPage) {
        const apiUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/161881456'; 
        const startIndex = (currentPage - 1) * pageSize;
        const endIndex = startIndex + pageSize;
  
        axios.get(apiUrl)
          .then(response => {
            const data = response.data;
            const studentList = data.data.studentList;
            // 判断学生类型，并替换为本科生或研究生
            studentList.forEach(student => {
              if (student.undergraduate) {
                student.undergraduate = '本科生';
              } else {
                student.undergraduate = '研究生';
              }
            });
            this.totalStudents = studentList.length;
            this.Students = studentList.slice(startIndex, endIndex); // 将从后端返回的学生数据赋值给表格数据
          })
          .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
          });
      },
  
      
      // 页面相关
      // 处理每页显示数量变化
      handleSizeChange(pageSize) {
        this.pageSize = pageSize;
        this.loadStudents(this.pageSize, this.currentPage);
      },
      // 处理当前页变化
      handleCurrentChange(currentPage) {
        this.currentPage = currentPage;
        this.loadStudents(this.pageSize, this.currentPage);
      },
  
  
      // 增删改查
      async searchStudents() {
        
      },
      
      editStudent(row) {
        // 编辑
        this.dialogVisible = true;
      },
      deleteStudent(row) {
        // 删除
      },
      saveStudent(){
        this.dialogVisible = false;
      },
  
    
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
    width: 45%; 
    height: auto;
    margin-bottom: 2%;
  }
  
  
  </style>