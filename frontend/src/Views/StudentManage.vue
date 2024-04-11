<template>
  <div>
  <el-card>
    <!-- 搜索部分 -->
    <el-row style="margin-bottom: 20px;">
      <el-col span="4">
        <el-select v-model="searchKeyword" placeholder="请选择查询条件">
          <template v-for="item in studentForm">
            <el-option :key="item.model" :label="item.label" :value="item.model" />
          </template>
        </el-select>    
      </el-col>
      <el-col span="4">
        <el-input v-model="searchvalue" placeholder="请输入查询内容" />
      </el-col>
      <el-col span="4">
        <el-button @click="searchStudents" icon="el-icon-search">查询</el-button>
      </el-col>

      <el-col span="8" >
        <el-popover
          placement="bottom"
          width="aoto"
          trigger="click">
          <el-radio-group v-model="radio" size="mini" style="margin-bottom: 5px;">
            <el-radio-button label="1" >批量生成学生账号</el-radio-button>
            <el-radio-button label="2" >仅更新资料库</el-radio-button>
          </el-radio-group>
          
          <el-upload 
            action="https://jsonplaceholder.typicode.com/posts/"  multiple="false" :limit="1" :on-exceed="handleExceed" 
            :on-error="handleError" :file-list="fileList" show-file-list="true" accept="xlsx" :before-upload="validateExcelFile">
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
      <template v-for="item in studentForm">
        <el-table-column :key="item.model" :prop="item.model" :label="item.label" />
      </template>

      <el-table-column fixed="right" label="操作" width="180">
        <template #default="{ row }">
          <el-button type="text" size="small" @click="editStudent(row)" style="margin-right: 8px;">编辑</el-button>
          <el-popconfirm title="确定删除该学生？" @confirm="deleteStudent(row)">
            <el-button slot="reference" type="text" size="small">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
    
  <el-dialog title="编辑学生信息" :visible.sync="dialogVisible" size="small">
    <el-form :model="student" label-width="80px" size="mini" class="wrapper">
      <template v-for="item in studentForm">
        <el-form-item :label="item.label" :key="item.model" class="blockitem">
          <el-input v-model="student[item.model]" :placeholder="item.label" />
        </el-form-item>
      </template>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="saveStudent">保存</el-button>
    </span>
  </el-dialog>

  </div>
</template>

<script>
export default {
  data() {
    return {
      Students: [
      { "admissionYear": "2023",
        "class_id": 1,
        "name": "罗翔",
        "type": "境内生",
        "sex": "男",
        "nation": "汉",
        "sid": "23xxxxxx",
        "pid": "身份证号",
        "birthday": "出生年份",
        "politics": "政治面貌",
        "dormitory": "宿舍",
        "phone": "电话",
        "address": "地址",
        "urgent_Phone": "",
        "urgent_Name": "null",
        "wechat": "null",
        "email": "null",
        },
        { "admissionYear": "2023",
        "class_id": 1,
        "name": "张三",
        "type": "境内生",
        "sex": "男",
        "nation": "汉",
        "sid": "23xxxxxx",
        "pid": "身份证号",
        "birthday": "出生年份",
        "politics": "政治面貌",
        "dormitory": "宿舍",
        "phone": "电话",
        "address": "地址",
        "urgent_Phone": "",
        "urgent_Name": "null",
        "wechat": "null",
        "email": "null",
        }
      ],
      studentForm: [
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
        { model: 'phone', label: '个人联系电话' },
        { model: 'address', label: '详细家庭住址' },
        { model: 'urgent_Phone', label: '紧急联系人电话' },
        { model: 'urgent_Name', label: '紧急联系人' },
        { model: 'email', label: '邮箱' },
        { model: 'wechat', label: '微信' }
      ],
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
      dialogVisible: false,
      searchvalue: '',
      searchKeyword: '',
      radio: '1'
    };
  },
  methods: {
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