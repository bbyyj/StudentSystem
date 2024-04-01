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
    <el-button type="primary" @click="handleCreateRoot">新增综测大类别</el-button>

    <el-tree :data="filteredTreeData" node-key="id" :default-expand-all="true" :props="defaultProps"
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
export default {
  data() {
    return {
      searchTerms: "",
      // 后端返回数据格式
      treeData: [
        {
          title: "社会工作类",
          id: 1,
          level: 1,
          children: [{ title: "院团委副书记", score: 3.0, id: 11, level: 2 },
          { title: "班委委员", score: 0.5, id: 12, level: 2 },
          ],
        },
        {
          title: "政治思想道德类",
          id: 2,
          level: 1,
          children: [{ title: "全国三好学生", score: 1.5, id: 21, level: 2 },
          { title: "班委委员", score: 0.5, id: 22, level: 2 },] 
        }

      ], 
      
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
      const parent = node.parent;
      const children = parent.data.children || parent.data;
      const index = children.findIndex((d) => d.id === data.id);
      children.splice(index, 1);
    },

    // 保存修改
    saveItem() {
      // 根据接口实现
      if (this.currentNode) {
        // 添加或编辑子节点
        if (this.currentItem.id) {
          // 编辑节点
          Object.assign(this.currentNode, this.currentItem);
        } else {
          // 新增子节点
          this.currentItem.id = Date.now(); // 假设ID由后端生成
          if (!this.currentNode.children) {
            this.$set(this.currentNode, "children", []);
          }
          this.currentNode.children.push(this.currentItem);
        }
      } else {
        // 添加根节点
        this.currentItem.id = Date.now(); // 假设ID由后端生成
        this.treeData.push(this.currentItem);
      }
      this.showDialog = false;
    },

    renderContent(h, { node, data }) {
      return (
        <span>
          <span>
            {`${node.label}` }
            {data.score ? ` ${data.score}分` : ""}
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
    // 初始渲染
    this.handleSearch();
  },
};
</script>