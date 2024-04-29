<template>
  <div class="compAssTable">
    <div class="compAssTable-header">
      <!-- 新增按钮 -->
      <el-button type="primary" @click="handlecreate">+ 新增综测加分条件</el-button>



      <!-- 对话框:点击新增或编辑才会弹出表单 -->
      <!-- :before-close="closeDialog" 点击关闭的x之前要做的事情 -->
      <el-dialog :title="modalType == 0 ? '新增综测分数条件' : '编辑综测分数条件'" :visible.sync="dialogVisible" width="50%"
        :before-close="closeDialog">
        <!-- 表单Form -->
        <el-form :inline="true" :model="form" :rules="rules" ref="form" label-width="80px">

          <!-- 每一项表单域:el-form-item -->
          <el-form-item label="综测类别" prop="RuleType">
            <el-select v-model="form.RuleType">
              <el-option v-for="item1 in RootCategory" :key="item1.id" :value="item1.value"
                :label="item1.label"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="加分条件" prop="RuleType">
            <el-input v-model="form.RuleDetail"></el-input>
          </el-form-item>
          <el-form-item label="加分分值" prop="RuleScore">
            <el-input v-model="form.RuleScore"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="submit">确 定</el-button>
        </div>
      </el-dialog>

      <!-- 搜索框 -->
      <el-form :inline="true">
        <el-form-item>
          <el-select v-model="selectedRuleType" placeholder="请选择综测类别" width="100px">
            <el-option v-for="item1 in RootCategory" :key="item1.id" :value="item1.value"
              :label="item1.label"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-input v-model="searchForm.name" placeholder="请输入加分条件"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
        </el-form-item>

      </el-form>
    </div>



    <div class="common-table">
      <!-- 数据表格 -->
      <el-table :data="tableData" stripe style="width: 100%" height="90%">
        <el-table-column prop="RuleType" label="综测类别">
        </el-table-column>
        <el-table-column prop="RuleDetail" label="加分条件">
        </el-table-column>
        <el-table-column prop="RuleScore" label="分值">
        </el-table-column>
        <!-- 操作列 -->
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>

      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="1" :page-sizes="[1, 2, 5, 10]" :page-size="1"
        layout="total, sizes, prev, pager, next, jumper" :total="total" class="pagination">
      </el-pagination>

    </div>
  </div>
</template>

<script>


export default {
  data() {
    return {
      // 当前选择的综测大类
      selectedRuleType: '',
      // 表单绑定的数据
      form: {
        RuleType: '',
        RuleDetail: '',
        RuleScore: '',
      },
      // 表单验证规则
      rules: {
        RuleType: [{ required: true, message: '请选择综测类别', trigger: 'blur' }],
        RuleDetail: [{ required: true, message: '请输入加分条件', trigger: 'blur' }],
        RuleScore: [{ required: true, message: '请输入加分分值', trigger: 'blur' }],
      },
      RootCategory: [
        {
          id: 1,
          label: "社会工作类",
          value: "社会工作类"
        },
        {
          id: 2,
          label: "政治思想道德类",
          value: "政治思想道德类"
        },
        {
          id: 3,
          label: "文体、实践类",
          value: "文体、实践类"
        },
        {
          id: 4,
          label: "学习、竞赛及科研成果",
          value: "学习、竞赛及科研成果"
        }
      ],
      // 表单是否打开
      dialogVisible: false,
      // 列表数据
      tableData: [{
        ID: 1,
        RuleType: '政治思想道德类',
        RuleDetail: '全国三好学生',
        RuleScore: '1.5',
      },
      {
        ID: 2,
        RuleType: '社会工作类',
        RuleDetail: '校学生会主席',
        RuleScore: '3',
      },
      ],
      // 打开表单:新建0,编辑1
      modalType: 0,
      // 分页的对象
      pageData: {
        page: 1,
        limit: 20
      },
      // 分页页数
      total: 0,
      // 搜索框表单
      searchForm: {
        compAssName: ''
      }
    }
  },
  methods: {
    // 获取列表数据
    getList() {
      // 由接口文档知传入一个对象:要返回的是当前页面数据和搜索到的数据的交集
      // getUser({ params: { ...this.pageData, ...this.searchForm } }).then((data) => {
      //     // this.tableData = data.data.list
      //     // this.total = data.data.count || 0
      // })
    },
    // 表单提交
    submit() {
      // 要用箭头函数,若用function会报错,不知道为什么
      this.$refs.form.validate((valid) => {
        // 符合校验
        if (valid) {
          // 提交数据
          if (this.modalType === 0) {
            // 新增
            addCompAssBegin(this.form).then(() => {
              console.log("新增综测")
              // 重新进行请求新的内容
              this.getList()
            })
          } else {
            // 编辑
            editCompAssBegin(this.form).then(() => {
              // 重新进行请求新的内容
              this.getList()
            })
          }
          // 清空,关闭
          this.closeDialog()
        }
      })
    },
    // 关闭对话框
    closeDialog() {
      // 先重置
      this.$refs.form.resetFields()
      // 后关闭
      this.dialogVisible = false
    },
    // 编辑按钮
    handleEdit(index) {
      this.modalType = 1
      this.openForm()
      // 深拷贝
      this.form = JSON.parse(JSON.stringify(index))
    },
    // 删除按钮
    handleDelete(index) {
      this.$confirm('确定删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 删除操作:根据后端接口,参数是对象,id是唯一标识符
        deleteCompAssBegin({ id: index.id }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getList()
        });
      }).catch(() => {
        // 点击取消:不删除了
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 新建按钮
    handlecreate() {
      this.modalType = 0
      this.openForm()
    },
    // 打开表单
    openForm() {
      this.dialogVisible = true
    },
    // 改变页码
    currentChange(val) {
      this.pageData.page = val
      this.getList()
    },
    // 搜索
    search() {
      this.getList()
    },
    // 跳转到综测审核页面
    gotoReview(row) {
      this.$router.push({ path: `/compAssReview/${row.compAssID}/${row.compAssName}` });
    }

  },
  mounted() {
    // 挂载时获取当前页面的综测信息
    this.getList()
    getAllCompAssBegin();
  }
}
</script>

<style lang="less" scoped>
.compAssTable {
  height: 100%;

  .compAssTable-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .common-table {
    height: 90%;
    position: relative;

    .pager {
      position: absolute;
      right: 20px;
      bottom: 0;
    }
  }
}
.compAssTable .el-form {
  display: flex;
  flex-wrap: nowrap;
  /* 确保所有表单项目在一行显示 */
  align-items: center;
  /* 纵向居中对齐 */
  justify-content: space-between;
  /* 分散对齐，可以根据需要调整为space-around等 */
}
</style>