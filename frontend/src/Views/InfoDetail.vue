<template>
  <div>
    <div class="competition-award">
      <div class="filter-search">
        <el-input placeholder="请输入内容" v-model="params.search" class="search-with-select" size="mini" clearable>
          <el-select v-model="params.select" slot="prepend" placeholder="请选择" ref="select" filterable>
            <el-option v-for="(column, index) in tableColumns" :label="column.label" :key="index" v-if="index > 0 && index < 5" :value=index></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="loadingData"></el-button>
        </el-input>

        <el-button @click="clearFilter" type="primary" size="mini" class="filter-del-btn">清除过滤器</el-button>
      </div>

      <el-table :data="tableData" style="width: 100%" size="mini" :row-class-name="RowState" ref="filterTable"  @filter-change="filterChange" @row-click="handleRowClick">
<!--      展开项-->
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="table-expand" size="mini">
              <el-form-item v-for="(column, index) in tableColumns" :label="column.label" :key="index" v-if="index > 0">
                <span>{{ props.row[column.prop]  }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>

<!--        表格里的其他项-->
        <el-table-column label="ID" prop="id"></el-table-column>
        <el-table-column label="提交时间" prop="submitTime" sortable></el-table-column>
        <el-table-column label="审核状态" prop="submitState" sortable :sort-method="sortByState" :filters="[{text: '已审核', value: '已审核'}, {text: '待审核', value: '待审核'}]"
                         :filter-multiple=false :filter-method="filterHandle"></el-table-column>
        <el-table-column v-for="(column, index) in tableColumns" :key="index" :prop="column.prop" :label="column.label" v-if="index > 0 && index < 5"></el-table-column>

        <el-table-column label="操作" fixed="right">
          <template slot-scope="scope">
            <el-popconfirm v-if="scope.row.submitState === '待审核'" title="该记录审核通过？" confirm-button-text='是的'
                           cancel-button-text='存在问题，不通过' @cancel="deleteItems(scope.row.id)" @confirm="putItems(scope.row.id)">
              <el-button type="info" slot="reference" size="mini" class="btn-examined" @click.native.stop>审核</el-button>
            </el-popconfirm>

            <el-popconfirm v-else-if="scope.row.submitState === '已审核'" title="确认删除？" @confirm="deleteItems()">
              <el-button type="danger" slot="reference" size="mini" class="btn-examined" @click.native.stop>删除</el-button>
            </el-popconfirm>
            <el-button @click.stop="showRichText(scope.row.textMessage)" type="primary" size="mini" class="btn-examined">查看附件</el-button>
          </template>
        </el-table-column>
      </el-table>

<!--      分页-->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="params.pageNum"
                     :page-sizes="[1, 2, 5, 10]" :page-size="params.pageSize" layout="total, sizes, prev, pager, next, jumper"
                     :total="total" class="pagination">
      </el-pagination>

<!--      附件-->
      <el-dialog title="查看附件：" :visible.sync="textVisible" width="80%">
        <div v-html="this.tableData.textMessage" class="w-e-text"></div>
      </el-dialog>

    </div>


  </div>
</template>
  
<script>
import InfoExamineData from "@/data/InfoExamineData";
import request from "@/utils/request";

export default {
  data() {
    return {
      params: {
        pageNum: 1,   // 前往第几页
        pageSize: 5,   // 每页显示的条数
        search: '',
        select: '',
      },
      total: 0,

      tableData: [],
      textVisible: false,
      tableColumns: [],
    }
  },

  created() {
    this.updateTableColumns();
    this.loadingData();
  },
  watch: {
    $route(to, from) {
      this.updateTableColumns();
      this.loadingData();
      this.clearFilter();

      this.$set(this.params, 'select', null)

    },
  },
  methods: {
    updateTableColumns() {  // 根据路由填入对应表项
      if (InfoExamineData.length > 0) {
        let firstData = null;

        if(this.$route.name === 'competition'){
          firstData = InfoExamineData[0];
        }else if(this.$route.name === 'paper'){
          firstData = InfoExamineData[1];
        }

        this.tableColumns = Object.keys(firstData).map(key => ({ prop: key, label: firstData[key] }));

      }
    },
    loadingData(){
      // console.log("search");
      request.post('/infoExamine/loadingData', {routeName: this.$route.name,params: this.params}).then(response => {
        if(response.data.code == '200'){
          this.tableData = response.data.data.tableData;
          this.total = response.data.data.total;
        }else{
          this.$message.error(response.data.message);
        }
      })
      this.tableData.forEach(val => {
        this.$set(val, "expanded", false);
      });
    },
    handleRowClick(row) {
      row.expanded = !row.expanded;
      this.$refs.filterTable.toggleRowExpansion(row, row.expanded);
    },
    showRichText(data){
      this.tableData.textMessage = data;
      if(data == null){
        this.tableData.textMessage = "暂无信息！";
      }
      this.textVisible = true;
    },
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.loadingData();
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.loadingData();
    },

    deleteItems(id){
      console.log("审核打回");
    },
    putItems(id){
      console.log("审核通过");
    },

    sortByState(a, b){
      if(a.submitState == '待审核'){
        return -1;
      } else{
        return 1;
      }
    },
    RowState({row, rowIndex}){
      if(row.submitState == '待审核'){
        return 'warning-row';
      }else if(row.submitState == '已审核'){
        return 'success-row';
      }
      return '';
    },
    filterHandle(value, row, column){
      return row.submitState === value;
    },
    filterChange() {
      this.total = this.$refs.filterTable.tableData.length;
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
  }
}
</script>
  
<style>
.competition-award{
}

.table-expand {
  font-size: 0;
}
.table-expand label {
  width: 120px;
  color: #99a9bf;

  font-size: 12px;
}
.table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;

}

.table-expand .el-form-item span {
  font-size: 12px;
  color: #0b151f;
}
.btn-examined{
  padding: 8px;
  margin: 5px;
}

.pagination{
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
.el-table .warning-row {
  background: oldlace;
}
.el-table .success-row {
  background: #f0f9eb;
}

.el-table-filter{
  background-color: #faf4f4;

}
.el-table-filter .el-table-filter__list .el-table-filter__list-item{
  font-size: 12px;
}

.filter-search{
  display: flex;
}
.search-with-select{
  width: 400px;
}

.filter-del-btn{
  margin-left: 10px;
}

.el-select .el-input {
  width: 130px;
}
.el-select-dropdown{
  background-color: #ffffff;
}
.el-select-dropdown__item{
  font-size: 12px;
}

.search-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>