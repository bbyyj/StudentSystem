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
        <el-button type="primary" round>批量导入</el-button>
        <el-button type="primary" round>单个导入</el-button>
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
      searchKeyword: ''
    };
  },
  methods: {
    async searchStudents() {
      
    },
    editStudent(row) {
      // 编辑逻辑
      this.dialogVisible = true;
    },
    deleteStudent(row) {
      // 删除逻辑
    },
    saveStudent(){
      this.dialogVisible = false;
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