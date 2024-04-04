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

        <el-button @click="clearAll" type="primary" size="mini" class="filter-del-btn">清空</el-button>
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
                           cancel-button-text='存在问题，不通过' @cancel="showDialogReject(scope.row.id)" @confirm="putItems(scope.row.id)">
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

<!--      审核不通过界面-->
      <el-dialog title="不通过理由" :visible.sync="rejectVisible" width="30%">
        <el-form>
          <el-form-item label="请输入拒绝的理由">
            <el-input type="textarea" v-model="rejectReason" rows="5" placeholder="请输入理由"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="rejectVisible = false">取消</el-button>
          <el-button type="primary" @click="submitRejectReason">确定</el-button>
        </span>
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
        search: '', // 输入的搜索内容
        select: '', // 选择的搜索项
      },
      total: 0,

      tableData: [],
      textVisible: false,
      rejectVisible: false,
      rejectReason:'',
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

      this.tableData.forEach(val => { // 开启展开行用
        this.$set(val, "expanded", false);
      });
    },
    deleteItems(id){
      console.log("确认删除记录");
      // request.put("/examine/del"+id,{routeName: this.$route.name}).then(res => {
      //   if (res.code === '200') {
      //     this.$message.success("删除成功");
      //     this.loadingData();
      //   } else {
      //     this.$message.error(res.errorMessage);
      //   }
      // })
    },
    putItems(id){
      console.log("审核通过");
      // request.post("/examine/pass"+id,{routeName: this.$route.name}).then(res => {
      //   if (res.code === '200') {
      //     this.$message.success("操作成功");
      //     this.loadingData();
      //   } else {
      //     this.$message.error(res.errorMessage);
      //   }
      // })

    },
    submitRejectReason(id){ // 提交拒绝理由
      console.log("审核不通过-提交拒绝理由");
      // request.post("/examine/"+id,{routeName: this.$route.name, reason: this.rejectReason}).then(res => {
      //   if (res.code === '200') {
      //     this.$message.success("操作成功");
      //     this.loadingData();
      //     this.rejectVisible = false;
      //     this.rejectReason = "";
      //   } else {
      //     this.$message.error(res.errorMessage);
      //   }
      // })
      this.rejectVisible = false;
    },
    showRichText(data){ // 展示附件
      this.tableData.textMessage = data;
      if(data == null){
        this.tableData.textMessage = "暂无信息！";
      }
      this.textVisible = true;
    },


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
    clearAll() {  // 清空
      this.$refs.filterTable.clearFilter();
      this.params = {
        pageNum: 1,   // 前往第几页
        pageSize: 5,   // 每页显示的条数
        search: '', // 输入的搜索内容
        select: '', // 选择的搜索项
      }
      this.rejectReason = "";

      this.loadingData();
    },
    showDialogReject(){ // 展示审核后拒绝理由界面
      this.rejectReason = "";
      this.rejectVisible = true;
    },
    handleRowClick(row) { // 处理展开行
      row.expanded = !row.expanded;
      this.$refs.filterTable.toggleRowExpansion(row, row.expanded);
    },
    handleSizeChange(pageSize) {  // 处理分页
      this.params.pageSize = pageSize;
      this.loadingData();
    },
    handleCurrentChange(pageNum) {  // 处理分页
      this.params.pageNum = pageNum;
      this.loadingData();
    },
    sortByState(a, b){  // 表中审核状态排序
      if(a.submitState == '待审核'){
        return -1;
      } else{
        return 1;
      }
    },
    RowState({row, rowIndex}){  // 表中审核状态颜色
      if(row.submitState == '待审核'){
        return 'warning-row';
      }else if(row.submitState == '已审核'){
        return 'success-row';
      }
      return '';
    },
    filterHandle(value, row, column){ // 表中审核状态筛选
      return row.submitState === value;
    },
    filterChange() {  //根据选择修改总条数
      this.total = this.$refs.filterTable.tableData.length;
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