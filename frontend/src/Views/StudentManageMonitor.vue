<template>
    <div>
<!--    <el-card>-->
      <!-- 搜索部分 -->
<!--      <el-row style="margin-bottom: 20px;">-->
<!--        <el-col :span="4">-->
<!--          <el-select v-model="searchKeyword" placeholder="请选择查询条件">-->
<!--            <el-option value="全部">全部</el-option>-->
<!--            <el-option v-for="item in studentSearchForm.items" :key="item.fieldName" :label="item.label" :value="item.fieldName" />-->
<!--          </el-select>    -->
<!--        </el-col>-->
<!--        <el-col :span="4">-->
<!--          <el-input v-model="searchvalue" placeholder="请输入查询内容" />-->
<!--        </el-col>-->
<!--        <el-col :span="4">-->
<!--          <el-button @click="searchStudents(1, pageSize)" icon="el-icon-search">查询</el-button>-->
<!--        </el-col>-->
<!--      </el-row>-->

      <el-input placeholder="请输入查询内容" v-model="searchvalue" class="search-with-select" size="small" clearable style="margin-bottom: 20px;">
        <el-select v-model="searchKeyword" slot="prepend" placeholder="请选择查询条件" class="search-with-select2">
          <el-option value="全部">全部</el-option>
          <el-option v-for="item in studentSearchForm.items" :key="item.fieldName" :label="item.label" :value="item.fieldName" />
        </el-select>
        <el-button slot="append" icon="el-icon-search" @click="searchStudents(1, pageSize)"></el-button>
      </el-input>

      <!-- 表格部分 -->
      <el-table :data="Students" style="width: 100%"  size="small" class="common-table">
          <el-table-column v-for="item in studentForm.items" 
          :key="item.fieldName" :prop="item.fieldName" :label="item.label" :width="item.width" />
  
      </el-table>
  
      <!-- 分页 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalStudents" size ="small" class="pagination">
      </el-pagination>
      
<!--    </el-card>-->
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import Cookies from 'js-cookie';
  import StudentInfoData from '@/data/StudentInfoData.js';
  
  export default {
    data() {
      return {
        // 存储获取到的学生列表信息
        Students: [
        { "admissionYear": "2023",
          "classId": 1,
          "name": "罗翔",
          "type": "境内生",
          "nation": "汉",
          "sid": "23xxxxxx",
          "birth": "出生年份",
          "politics": "政治面貌",
          "nativePlace": "籍贯",
          "dormitory": "宿舍",
          "phone": "电话",
          "wechat": "null",
          "email": "null",
          }
        ],
        // 表格列
        studentForm: [],
        // 搜索相关属性
        studentSearchForm: [],
        searchvalue: '',
        searchKeyword: '',
        isseraching: false,
        // 分页相关属性
        currentPage: 1,
        pageSize: 10,
        totalStudents: 0,
      };
    },
    created() {
      this.loadStudents(this.pageSize, this.currentPage);
      this.studentSearchForm = StudentInfoData[4];
      this.studentForm = StudentInfoData[5];
    },
    methods: {
      loadStudents(pageSize, currentPage) {
        let  apiUrl = 'http://127.0.0.1:8080/headTeacher/getClassStudent';
        let  params = {
            isUndergraduate: Cookies.get('undergraduate'),
            admissionYear: Cookies.get('admissionYear'),
            classId: Cookies.get('classId'),
            page: currentPage - 1, // 添加当前页码参数
            size: pageSize // 添加每页大小参数
            }
          
        axios.get(apiUrl, { params }) // 将参数对象作为配置传递给GET请求
          .then(response => {
            const data = response.data;
            const studentList = data.data.content;
  
            // 处理数据
            this.inListDataProcess(studentList);
  
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
          this.loadStudents(this.pageSize, this.currentPage);
        }
      },
      // 处理当前页变化
      handleCurrentChange(currentPage) {
        // 用户看到的页码减1，因为后端返回页面从0开始
        this.currentPage = currentPage;
        if(this.isseraching){
          this.searchStudents(this.currentPage, this.pageSize);
        }
        else{
          this.loadStudents(this.pageSize, this.currentPage);
        }
      },
  
      //查询
      async searchStudents(currentPage, pageSize) {
        // 检查是否有选择的查询条件
        if (!this.searchKeyword) {
            this.$message('请选择查询条件后再进行搜索！');
            return;
          }
        if(!this.searchvalue && this.searchKeyword !== '全部'){
          this.$message('请输入查询内容后再进行搜索！');
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
  
        const params = {  isUndergraduate: Cookies.get('undergraduate'),
                          admissionYear: Cookies.get('admissionYear'),
                          classId: Cookies.get('classId'),
                          keyname: selectedKeyword, 
                          keyword: searchContent, 
                          page: currentPage - 1, 
                          size: pageSize
                        };
  
        axios.get( 'http://127.0.0.1:8080/headTeacher/searchClassStuInfo', { params })
          .then(response => {
            const data = response.data;
            const studentList = data.data.content;
  
            // 处理数据
            this.inListDataProcess(studentList);
            
            // 更新表格数据
            this.Students = studentList;
            // 更新总条数，如果API返回
            this.totalStudents = data.data.totalElements;
            this.isseraching = true;
  
          })
          .catch(error => {
            console.error('Error during search:', error);
          });
      },

      inListDataProcess(studentList) {
        studentList.forEach(student => {
          // 判断学生类型，并替换为本科生或研究生
          if (student.undergraduate) {
            student.undergraduate = '本科生';
          } else {
            student.undergraduate = '研究生';
          }
          if(student.studentRole === 'NOT_MONITOR'){
            student.studentRole = '学生';
          } else {
            student.studentRole = '班长';
          }
          if(student.classId === 0){
            student.classId = '研究生';
          }
        });
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

  .pagination{
    display: flex;
    justify-content: center;
    margin-top: 20px;
  }
  .search-with-select{
    width: 400px;
  }
  .search-with-select2{
    width: 160px;
  }
  .common-table{
    height: 500px;
    position: relative;
    overflow: auto;
  }
  </style>
  
  