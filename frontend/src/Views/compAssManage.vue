<template>
  <div>
    <el-card class="box-card" shadow="never">
      <el-form @submit.native.prevent="handleSearch" inline>
        <el-form-item>
          <el-input placeholder="请输入标题进行搜索" v-model="searchTerms" clearable @clear="handleSearch"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">搜索</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-button type="primary" @click="handleCreateRoot">新增综测大类</el-button>

    <el-tree :data="filteredTreeData" node-key="id" :default-expand-all="false" :props="defaultProps"
      :render-content="renderContent"></el-tree>

    <!-- dialog -->
    <el-dialog :title="dialogTitle" :visible.sync="showDialog">
      <el-form :model="currentItem">
        <el-form-item label="加分条件">
          <el-input v-model="currentItem.title"></el-input>
        </el-form-item>
        <el-form-item v-if="this.handleType !== ''" label="分值">
          <el-input v-model.number="currentItem.score"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="saveItem">保存</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { getAllRuleDetail } from "@/api";
import axios from "axios";

export default {
  data() {
    return {
      searchTerms: "",
      // 后端返回数据格式
      treeData: [],

      // 接口数据
      defaultProps: {
        children: "children",
        label: "title",
      },

      showDialog: false, // 是否显示dialog
      dialogTitle: "", // dialog复用
      currentItem: {},  // 当前内容
      currentNode: null, // 当前选中节点
      handleType: "", // 是否为叶子
      filteredTreeData: [], // 前端过滤
    };
  },
  methods: {
    handleSearch() {
      // 关键字搜索逻辑，更新 treeData
      if (this.searchTerms.trim()) {
        // 如果有输入关键字
        // 利用前端递归搜索 查找数据
        const searchResults = []; // 用来存储搜索结果
        const search = (nodes) => {
          nodes.forEach((node) => {
            if (node.title.includes(this.searchTerms)) {
              searchResults.push(node);
            }
            if (node.children && node.children.length) {
              search(node.children); // 递归搜索子节点
            }
          });
        };
        search(this.treeData);
        // 更新 treeData 为搜索结果
        this.filteredTreeData = searchResults;
      } else {
        // 如果搜索框为空，则还原最开始的 treeData
        this.filteredTreeData = this.treeData;
      }
    },
    // 创建新的综测大类
    handleCreateRoot() {
      this.handleType = "";
      this.dialogTitle = "新增综测大类别";
      this.currentItem = { title: "", level: 1 }; // 清空当前项目信息
      this.currentNode = null; // 明确设置当前节点为null
      this.showDialog = true; // 显示对话框
    },
    // 创建新的加分条件
    handleAdd(node) {
      this.handleType = "add";
      this.dialogTitle = "新增加分条件";
      this.showDialog = true;
      this.currentItem = { title: "", score: null, level: 2 };
      this.currentNode = node;
    },

    // 编辑根节点（不显示分值） 
    handleEditRoot(node) {
      this.handleType = "";
      this.dialogTitle = "编辑综测大类别";
      this.showDialog = true;
      this.currentItem = { ...node };
      this.currentNode = node;
    },

    // 编辑当前加分条件 （显示分值）
    handleEditLeaf(node) {
      this.handleType = "edit";
      this.dialogTitle = "编辑加分条件";
      this.showDialog = true;
      this.currentItem = { ...node };
      this.currentNode = node;
    },

    // 删除当前节点
    handleDelete(node, data) {
      this.$confirm('确定要删除吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 假设后端删除节点的接口为 `/api/delete-node`
        axios.post('https://mock.apifox.com/m2/4212159-3852880-default/162124197', { id: data.id }).then(response => {
          // 处理后端返回的成功响应
          const parent = node.parent;
          const children = parent.data.children || parent.data;
          const index = children.findIndex(d => d.id === data.id);
          children.splice(index, 1);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(error => {
          // 处理可能的错误响应
          this.$message({
            type: 'error',
            message: '删除失败: ' + error.message
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    // 保存修改
    saveItem() {
      this.$confirm('保存前请再次确认所填写的信息，然后点击确定', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        // 假设后端保存节点的接口为 `/api/save-node`
        axios.post('/api/save-node', this.currentItem).then(response => {
          // 处理后端返回的成功响应
          if (this.currentNode) {
            if (this.currentItem.id) {
              Object.assign(this.currentNode, this.currentItem);
            } else {
              this.currentItem.id = response.data.id; // 假设后端返回新生成的ID
              if (!this.currentNode.children) {
                this.$set(this.currentNode, 'children', []);
              }
              this.currentNode.children.push(this.currentItem);
            }
          } else {
            this.currentItem.id = response.data.id;
            this.treeData.push(this.currentItem);
          }
          this.showDialog = false;
          this.$message({
            type: 'success',
            message: '保存成功!'
          });
        }).catch(error => {
          // 处理可能的错误响应
          this.$message({
            type: 'error',
            message: '保存失败: ' + error.message
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消保存'
        });
      });
    },

    renderContent(h, { node, data }) {
      return (
        <span class="custom-tree-node">
          <span >
            {`${node.label}`}
            {data.score ? <span className="score">{` ${data.score}分`}</span> : ""}
          </span>

          {data.level === 1 ? (
            <el-button
              size="mini"
              type="text"
              on-click={() => this.handleEditRoot(data)}
            >
              编辑
            </el-button>
          ) : (
            <el-button
              size="mini"
              type="text"
              on-click={() => this.handleEditLeaf(data)}
            >
              编辑
            </el-button>
          )}


          {data.level === 1 ? (
            <el-button
              size="mini"
              type="text"
              on-click={() => this.handleAdd(data)}
            >
              添加
            </el-button>
          ) : null}

          <el-button
            size="mini"
            type="text"
            on-click={() => this.handleDelete(node, data)}
          >
            删除
          </el-button>

        </span>
      );
    },
    cancel() {
      this.currentNode = null; // 取消选中当前节点
      this.showDialog = false; // 对话框消失 
      this.currentItem = {}; // 当前内容置空
    },
  },
  mounted() {

    // 请求全部综测细则
    getAllRuleDetail()
      .then(response => {
        if (response.data.code === 200) {
          console.log("成功请求到数据")
          console.log(response.data["data"].treeData)
          this.treeData = response.data["data"].treeData
          this.handleSearch() 
        }
        else {
          console.log("网络错误")
        }
      })
      .catch(error => {
        console.error('请求错误:', error);
      });
  },
};
</script>

<style>
.custom-tree-node {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  margin-right: 8px;
}
</style>