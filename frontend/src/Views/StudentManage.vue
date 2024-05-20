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
          width="auto"
          trigger="click">
          <el-radio-group v-model="selectedType" @change="handleActionChange" size="mini" style="margin-bottom: 5px;">
            <el-radio-button label="1" >批量生成学生</el-radio-button>
            <el-radio-button label="2" >仅更新资料</el-radio-button>
          </el-radio-group>
          
          <el-upload 
            action="https://mock.apifox.com/m2/4212159-3852880-default/161865323"  :multiple="false" :limit="1"  :file-list="fileList" :show-file-list="true" accept="xlsx" 
            :on-exceed="handleExceed" :on-error="handleError" :before-upload="validate" :on-success="handleSuccess">
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
      <!-- <el-table-column v-for="item in studentForm" 
        :key="item.model" :prop="item.model" :label="item.label" :width="item.width" 
        :filters="item.filters" :filter-method="item.filters ? filterMethod : null" /> -->
      
        <el-table-column v-for="item in studentForm" 
        :key="item.model" :prop="item.model" :label="item.label" :width="item.width" />

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
      <el-button @click="closeDialog">取 消</el-button>
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
        { model: 'admissionYear', label: '入学年份', width: '70px' },
        { model: 'classId', label: '班级', width: '70px' },
        { model: 'name', label: '姓名', width: '100px' },
        { model: 'type', label: '学生类别', width: '90px' },
        { model: 'sex', label: '性别', width: '60px' },
        { model: 'nation', label: '民族', width: '60px' },
        { model: 'sid', label: '学号', width: '100px' },
        { model: 'pid', label: '身份证号', width: '200px' },
        { model: 'birth', label: '出生年月', width: '100px' },
        { model: 'undergraduate', label: '培养层次', width: '100px' },
        { model: 'politics', label: '政治面貌', width: '90px' },
        { model: 'nativePlace', label: '籍贯', width: '150px' },
        { model: 'dormitory', label: '宿舍', width: '150px' },
        { model: 'phone', label: '个人联系电话', width: '150px' },
        { model: 'address', label: '详细家庭住址', width: '250px' },
        { model: 'urgentPhone', label: '紧急联系人电话', width: '150px' },
        { model: 'urgentName', label: '紧急联系人', width: '110px' },
        { model: 'email', label: '邮箱', width: '150px' },
        { model: 'wechat', label: '微信', width: '150px' }
      ],
      //修改或添加时的表单
      student: {
        "admissionYear": "",
        "classId": "",
        "name": "",
        "type": "",
        "sex": "",
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
      // 单个添加和修改信息的对话框
      dialogVisible: false,
      dialogtype: 'add',
      // 搜索相关属性
      searchvalue: '',
      searchKeyword: '',
      // 文件导入相关
      selectedType: '1', // 初始选中项
      fileList: [], // 上传文件列表
      uploadUrl: 'https://mock.apifox.com/m2/4212159-3852880-default/161865323', // 批批处理批量生成的默认接口
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
    loadStudents(pageSize, currentPage) {
      const apiUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/161881456';

      // 定义请求参数
      const params = {
        page: currentPage, // 添加当前页码参数
        pageSize: pageSize // 添加每页大小参数
      };

      axios.get(apiUrl, { params }) // 将参数对象作为配置传递给GET请求
        .then(response => {
          const data = response.data;
          const studentList = data.data.content;
          console.log(data)

          // 判断学生类型，并替换为本科生或研究生
          studentList.forEach(student => {
            if (student.undergraduate) {
              student.undergraduate = '本科生';
            } else {
              student.undergraduate = '研究生';
            }
          });

          this.totalStudents = data.data.totalElements; // 假设totalStudents应为实际返回的总条数，需后端支持
          this.Students = studentList; // 直接使用返回的分页数据
          
        })
        .catch(error => {
          console.error('There was a problem with your fetch operation:', error);
        });
    },

    // // 筛选
    // filterMethod(filterValue, row) {
    //   // 如果未选择筛选项，则返回 true，表示不进行筛选
    //   if (filterValue === undefined || filterValue === '') {
    //     return true;
    //   }
      
    //   // 遍历每一列的筛选值
    //   for (const key in row) {
    //     if (Object.hasOwnProperty.call(row, key)) {
    //       // 获取当前列的值
    //       const cellValue = row[key];
          
    //       // 如果当前列的值包含筛选值，则返回 true，表示当前行满足筛选条件
    //       if (cellValue !== null && cellValue !== undefined && cellValue.toString().includes(filterValue)) {
    //         return true;
    //       }
    //     }
    //   }
      
    //   // 如果当前行的所有列都不包含筛选值，则返回 false，表示当前行不满足筛选条件
    //   return false;
    // },
    
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
      // 检查是否有选择的查询条件
      if (!this.searchKeyword) {
          this.$message('请选择查询条件后再进行搜索！');
          return;
        }

      // 获取用户选择的查询条件\查询内容
      const selectedKeyword = this.searchKeyword;
      const searchContent = this.searchvalue;

      // 根据选择的查询条件，构建请求参数
      const params = {keyname: selectedKeyword, keyword: searchContent};

      // 发送请求，假设API接受一个名为'query'的参数来表示查询条件
      axios.get( 'https://mock.apifox.com/m2/4212159-3852880-default/161870137', { params })
        .then(response => {
          const data = response.data;
          const studentList = data.data.studentList;
          
          // 更新表格数据
          this.Students = studentList;
          // 更新总条数，如果API返回
          this.totalStudents = data.data.count;
        })
        .catch(error => {
          console.error('Error during search:', error);
        });
    },

    addStudent() {
      // 单个学生导入
      this.dialogVisible = true;
      this.dialogtype = 'add';
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
      this.student.type = row.type;
      this.student.sex = row.sex;
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

        // 判断是添加还是编辑
        if (this.dialogtype === 'add') {
          apiEndpoint = 'https://mock.apifox.com/m2/4212159-3852880-default/161870892'; // 添加学生的API地址
          method = 'post'; 
        } else if (this.dialogtype === 'edit') {
          apiEndpoint = 'https://mock.apifox.com/m2/4212159-3852880-default/161870892'; // 编辑学生的API地址，实际应包含学生ID
          method = 'put';
        }
        console.log('payload:', payload);

        // 发送请求
        const response = await axios({
          url: apiEndpoint,
          method: method,
          data: payload
        });

        console.log('response:', response);

        if (response.status === 200) {
          this.$message('操作成功');
          this.closeDialog();
          this.loadStudents(); // 刷新数据
        } else {
          this.$message('操作失败，请重试');
        }
      } catch (error) {
        console.error('错误:', error);
      }
    },

    closeDialog(){
      this.dialogVisible = false;
      this.student = {
        "admissionYear": "",
        "classId": "",
        "name": "",
        "type": "",
        "sex": "",
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
        ? 'https://mock.apifox.com/m2/4212159-3852880-default/161865323' // 批量生成接口
        : 'https://mock.apifox.com/m2/4212159-3852880-default/170643731'; // 仅更新接口
    },

    handleExceed(fileList) {
      console.log('文件超过限制');
      // 这里可以添加提示信息或逻辑，比如弹窗警告用户已超过上传限制
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
      //成功提示
      // 这里可以处理响应逻辑，如更新状态或列表等
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

