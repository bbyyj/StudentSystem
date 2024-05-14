<template>
  <div>
  <el-card>
    <!-- 搜索部分 -->
    <el-row style="margin-bottom: 20px;">
      <el-col :span="4">
        <el-select v-model="searchKeyword" placeholder="请选择查询条件">
          <el-option v-for="item in studentForm" :key="item.model" :label="item.label" :value="item.model" />
        </el-select>    
      </el-col>
      <el-col :span="4">
        <el-input v-model="searchvalue" placeholder="请输入查询内容" />
      </el-col>
      <el-col :span="4">
        <el-button @click="searchStudents" icon="el-icon-search">查询</el-button>
      </el-col>

      <el-col :span="8" >
        <el-popover
          placement="bottom"
          width="aoto"
          trigger="click">
          <el-radio-group v-model="radio" size="mini" style="margin-bottom: 5px;">
            <el-radio-button label="1" >批量生成学生账号</el-radio-button>
            <el-radio-button label="2" >仅更新资料库</el-radio-button>
          </el-radio-group>
          
          <el-upload 
            action="https://jsonplaceholder.typicode.com/posts/"  :multiple="false" :limit="1" :on-exceed="handleExceed" 
            :on-error="handleError" :file-list="fileList" :show-file-list="true" accept="xlsx" :before-upload="validateExcelFile">
            <el-button size="small" type="plain">点击上传</el-button>
             
            <div slot="tip" class="el-upload__tip">只能上传单个.xlsx格式的Excel文件</div>
          </el-upload>
          <el-button slot="reference" type="primary" round @click="importStudents">批量导入</el-button>
        </el-popover>
        <el-button type="primary" round @ckick="addStudent">单个导入</el-button>
      </el-col>
    </el-row>

    <!-- 表格部分 -->
    <el-table :data="Students" style="width: 100%" size="mini">
      <el-table-column v-for="item in studentForm" 
        :key="item.model" :prop="item.model" :label="item.label" :width="item.width" 
        :filters="item.filters" :filter-method="item.filters ? filterMethod : null" />
      
        <!-- <el-table-column v-for="item in studentForm" 
        :key="item.model" :prop="item.model" :label="item.label" :width="item.width"  /> -->

      <el-table-column fixed="right" label="操作" width="180">
        <template #default="{ row }">
          <el-button type="text" size="small" @click="editStudent(row)" style="margin-right: 8px;">编辑</el-button>
          <el-popconfirm title="确定删除该学生？" @confirm="deleteStudent(row)">
            <el-button slot="reference" type="text" size="small">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页，有bug -->
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
    
  <el-dialog title="编辑学生信息" :visible.sync="dialogVisible" >
    <el-form :model="student" label-width="80px" size="mini" class="wrapper">
      <el-form-item v-for="item in studentForm" :label="item.label" :key="item.model" class="blockitem">
        <el-input  v-model="student[item.model]" :placeholder="item.label" />
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="saveStudent">保存</el-button>
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
      { "admissionYear": "2023",
        "classId": 1,
        "name": "罗翔",
        "type": "境内生",
        "sex": "男",
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
      studentForm: [
        { model: 'admissionYear', label: '入学年份', width: '70px', filters: null },
        { model: 'classId', label: '班级', width: '70px', filters: null },
        { model: 'name', label: '姓名', width: '100px', filters: null },
        { model: 'type', label: '学生类别', width: '90px', 
          filters: [
            { text: '境内生', value: '境内生' },
            { text: '国际生', value: '国际生' },
            { text: '港澳台生', value: '港澳台生' }
          ] 
        },
        { model: 'sex', label: '性别', width: '60px', 
          filters: [{ text: '男', value: '男' }, { text: '女', value: '女' }] 
        },
        { model: 'nation', label: '民族', width: '60px', filters: null },
        { model: 'sid', label: '学号', width: '100px', filters: null },
        { model: 'pid', label: '身份证号', width: '200px', filters: null },
        { model: 'birth', label: '出生年月', width: '100px', filters: null },
        { model: 'undergraduate', label: '培养层次', width: '100px', 
          filters: [{ text: '本科生', value: '本科生' }, { text: '研究生', value: '研究生' }] 
        },
        { model: 'politics', label: '政治面貌', width: '90px', 
          filters: [{ text: '共青团员', value: '共青团员' }, { text: '群众', value: '群众' }, { text: '无', value: '无' }] 
        },
        { model: 'nativePlace', label: '籍贯', width: '150px', filters: null },
        { model: 'dormitory', label: '宿舍', width: '150px', filters: null },
        { model: 'phone', label: '个人联系电话', width: '150px', filters: null },
        { model: 'address', label: '详细家庭住址', width: '250px', filters: null },
        { model: 'urgentPhone', label: '紧急联系人电话', width: '150px', filters: null },
        { model: 'urgentName', label: '紧急联系人', width: '110px', filters: null },
        { model: 'email', label: '邮箱', width: '150px', filters: null },
        { model: 'wechat', label: '微信', width: '150px', filters: null }
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
      // 是否批量导入
      radio: '1',
      fileList: [],
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

    // 筛选
    filterMethod(filterValue, row) {
      // 如果未选择筛选项，则返回 true，表示不进行筛选
      if (filterValue === undefined || filterValue === '') {
        return true;
      }
      
      // 遍历每一列的筛选值
      for (const key in row) {
        if (Object.hasOwnProperty.call(row, key)) {
          // 获取当前列的值
          const cellValue = row[key];
          
          // 如果当前列的值包含筛选值，则返回 true，表示当前行满足筛选条件
          if (cellValue !== null && cellValue !== undefined && cellValue.toString().includes(filterValue)) {
            return true;
          }
        }
      }
      
      // 如果当前行的所有列都不包含筛选值，则返回 false，表示当前行不满足筛选条件
      return false;
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
    addStudent() {
      // 单个学生导入
      this.dialogVisible = true;
    },
    importStudents() {
      // 批量导入
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

    // 文件处理
    handleExceed() {
        this.$message.warning(`只能上传单个文件！`);
    },
    handleError() {
        this.$message.error(`上传失败，请重试！`);
    },
    validateExcelFile(file) {
      const isValid = file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'; // .xlsx MIME type
      if (!isValid) {
        this.$message.error('只能上传.xlsx格式的Excel文件！');
        return false; // 返回false阻止文件上传
      }
      return true;
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
  width: 45%; 
  height: auto;
  margin-bottom: 2%;
}


</style>