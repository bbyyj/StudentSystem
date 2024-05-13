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
        <!--需要检索的综测类别-->
        <el-form-item>
          <el-select v-model="selectedRuleType" placeholder="请选择综测类别" width="100px">
            <el-option v-for="item1 in RootCategory" :key="item1.id" :value="item1.value"
              :label="item1.label"></el-option>
          </el-select>
        </el-form-item>

        <!--需要的加分条件-->
        <el-form-item>
          <el-input v-model="searchForm.detail" placeholder="请输入加分条件"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
        </el-form-item>

      </el-form>
    </div>



    <div class="common-table">
      <!-- 数据表格 -->
      <el-table :data="tableData" stripe style="width: 100%" height="90%">
        <el-table-column prop="type" label="综测类别">
        </el-table-column>
        <el-table-column prop="detail" label="加分条件">
        </el-table-column>
        <el-table-column prop="score" label="分值">
        </el-table-column>
        <!-- 操作列 -->
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>

      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
        :page-sizes="[1, 2, 5, 10]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
        :total="total" class="pagination">
      </el-pagination>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

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
      tableData: [],
      // 打开表单:新建0,编辑1
      modalType: 0,

      // 分页相关属性
      currentPage: 1, // 当前所在的页数
      pageSize: 5,  // 每个页请求的数量（1条/页、2条/页、5条/页、10条/页）
      total: 0, // 全部的条数
      
      // 搜索框表单
      searchForm: {
        detail: ''
      }
    }
  },
  methods: {
    // 分页相关的函数
    // 变化多少条每页
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.getList(this.currentPage, pageSize);
    },
    // 处理当前的页码
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.getList(currentPage);
    },

    // 获取列表数据
    getList(page = this.currentPage, size = this.pageSize) {
      // 将页码增加1，并确保page和size是字符串
      let nextPage = parseInt(page) - 1; // 确保页码是计算后再转为字符串
      let pageSize = parseInt(size); // 确保pageSize是字符串
      axios.get(`http://127.0.0.1:8080/rule/detailManage/getAllRuleDetail?page=${nextPage}&size=${pageSize}`).then((response) => {
        if (response.data.code === 200) {
          this.tableData = response.data.data.content; // 设置表格数据
          this.total = response.data.data.totalElements; // 设置总数据量
          this.currentPage = response.data.data.pageable.pageNumber + 1; // 更新当前页码
        }
      }).catch((error) => {
        console.error("获取数据失败:", error);
      });
    },
    // 根据条件检索获取
    getListByCondition(page = this.currentPage, size = this.pageSize){
      // 将页码增加1，并确保page和size是字符串
      let nextPage = parseInt(page) - 1; // 确保页码是计算后再转为字符串
      let pageSize = parseInt(size); // 确保pageSize是字符串
      let type = this.selectedRuleType;
      let detail = this.searchForm.detail;
      axios.get(`http://127.0.0.1:8080/rule/detailManage/getRuleDetailByCondition?type=${type}&detail=${detail}&page=${nextPage}&size=${pageSize}`).then((response) => {
        if (response.data.code === 200) {
          this.tableData = response.data.data.content; // 设置表格数据
          this.total = response.data.data.totalElements; // 设置总数据量
          this.currentPage = response.data.data.pageable.pageNumber + 1; // 更新当前页码
        }
      }).catch((error) => {
        console.error("获取数据失败:", error);
      });
    },


    // 表单提交
    submit() {
      // 要用箭头函数,若用function会报错,不知道为什么
      this.$refs.form.validate((valid) => {
        // 符合校验
        if (valid) {
          // 需要新增的数据
          const Data = {
            tid: "1",          // 根据需要从组件的data或props中获取
            detail: "123123",  // 同上，示例直接使用硬编码值
            score: "0.5"       // 同上
          };

          // 提交数据
          if (this.modalType === 0) {
            // 新增加分条件
            axios.post(`http://127.0.0.1:8080/rule/detailManage/addRuleDetail`, Data).then(() => {
              this.$message({
                type: 'success',
                message: '新增成功!'
              })
              // 重新进行请求新的内容
              this.getList()
            })
          } else {
            // 编辑加分条件
            axios.put(`http://127.0.0.1:8080/rule/detailManage/updateRuleDetailById`, Data).then(() => {
              this.$message({
                type: 'success',
                message: '修改成功!'
              })
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
    handleDelete(item) {
      this.$confirm('确定删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 删除操作:根据后端接口,参数是对象,id是唯一标识符
        axios.delete(`http://127.0.0.1:8080/rule/detailManage/deleteRuleDetailById?rid=${item.id}`).then((response) => {
          if (response.data.code === 200) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            // 重新进行数据请求
            this.getList()
          }
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

   
    // 搜索
    search() {
      this.getListByCondition()
    },
    // 跳转到综测审核页面
    gotoReview(row) {
      this.$router.push({ path: `/compAssReview/${row.compAssID}/${row.compAssName}` });
    }

  },
  mounted() {
    // 挂载时获取当前页面的综测信息
    this.getList()

    // getAllCompAssBegin();
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