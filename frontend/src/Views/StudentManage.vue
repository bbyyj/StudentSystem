<template>
  <div>
    <el-card>
        <el-row style="margin-bottom: 20px;">
            <el-button type="primary" round>导入学生信息</el-button>
        </el-row>
        
        <el-row>
          <el-col span="4">
            <el-select v-model="value" placeholder="请选择查询条件"></el-select>
          </el-col>
          <el-col span="8">
            <el-input v-model="searchKeyword" placeholder="请输入查询关键词" />
          </el-col>
          <el-col span="4">
            <el-button @click="searchStudents" icon="el-icon-search">查询</el-button>
          </el-col>
            
        </el-row>
        
        <el-table :data="filteredStudents" style="width: 100%">
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="gender" label="性别" />
        <el-table-column label="操作" width="180">
            <template #default="{ row }">
            <el-button type="text" size="small" @click="editStudent(row)">编辑</el-button>
            <el-button type="text" size="small" @click="deleteStudent(row)">删除</el-button>
            </template>
        </el-table-column>
        </el-table>

        <el-dialog :visible.sync="dialogVisible" title="学生信息管理" @close="resetForm">
        <el-form :model="studentForm" :rules="formRules" ref="studentFormRef">
            <el-form-item label="姓名" prop="name">
            <el-input v-model="studentForm.name"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
            <el-input v-model="studentForm.age"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
            <el-select v-model="studentForm.gender" placeholder="请选择">
                <el-option label="男" value="male"></el-option>
                <el-option label="女" value="female"></el-option>
            </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="handleDialogSubmit">确定</el-button>
        </div>
        </el-dialog>
    </el-card>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
      students: [],
      filteredStudents: [],
      dialogVisible: false,
      studentForm: {
        name: '',
        age: '',
        gender: ''
      },
      formRules: {
        // 表单校验规则
      },
      value: '',
      searchKeyword: ''
    };
  },
  methods: {
    async searchStudents() {
      // 添加加载状态
      this.loading = true;
      try {
        // 搜索逻辑
        this.filteredStudents = this.students.filter(student => student.name.includes(this.searchKeyword));
      } catch (error) {
        console.error('搜索学生信息失败:', error);
      } finally {
        this.loading = false;
      }
    },
    editStudent(row) {
      // 编辑逻辑
    },
    deleteStudent(row) {
      
      
      // 删除逻辑
    },
    handleDialogSubmit() {
      // 表单提交逻辑，包括数据校验和提交
      this.$refs.studentFormRef.validate((valid) => {
        if (valid) {
          // 提交逻辑
        } else {
          console.log('表单验证失败');
        }
      });
    },
    resetForm() {
      this.$refs.studentFormRef.resetFields();
      this.dialogVisible = false;
    }
  }
  
};
</script>