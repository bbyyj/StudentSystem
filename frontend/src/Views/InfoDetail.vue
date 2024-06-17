<template>
  <div>
    <div class="competition-award">
      <div class="filter-search">
        <el-input placeholder="请输入内容" v-model="params.search" class="search-with-select" size="mini" clearable>
          <el-select v-model="selectedText" slot="prepend" placeholder="请选择" ref="select" filterable @change="handleSelectChange">
            <el-option v-for="(column, index) in displayedOptions" :label="column.label" :key="index" :value="index"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="loadingData"></el-button>
        </el-input>

        <el-button @click="clearAll" type="primary" size="mini" class="filter-del-btn">清空</el-button>
        <el-button type="success" size="mini" class="filter-del-btn" @click="handleSelectAllChange">一键全选</el-button>
        <el-button type="success" size="mini" class="filter-del-btn" @click="expData">批量导出</el-button>
      </div>

      <el-table :data="tableData" style="width: 100%" size="mini" :row-class-name="RowState" ref="filterTable"  @filter-change="filterChange" @row-click="handleRowClick"
                @selection-change="handleSelectionChange" :row-key="getRowKeys">
<!--      展开项-->
        <el-table-column type="expand" width="1">
          <template slot-scope="props">
            <el-form label-position="left" inline class="table-expand" size="mini">
              <el-form-item v-for="(column, index) in tableColumns" :label="column.label" :key="index" v-if="index > 0">
                <span>{{ props.row[column.prop]  }}</span>
              </el-form-item>
              <el-form-item v-if="props.row.check_status === '已审核' && index === 0" v-for="(column, index) in tableColumns" :label="column.label" :key="index">
                <span>{{ props.row[column.prop] }}</span>
              </el-form-item>

            </el-form>
          </template>
        </el-table-column>

        <el-table-column type="selection" width="55" :reserve-selection="true"></el-table-column>

<!--        表格里的其他项-->
        <el-table-column label="ID" prop="id"></el-table-column>
        <el-table-column label="提交时间" prop="submit_time" sortable></el-table-column>
        <el-table-column label="审核状态" prop="check_status" sortable :sort-method="sortByState" :filters="[{text: '已审核', value:'已审核'}, {text: '未审核', value: '未审核'}]"
                         :filter-multiple=false :filter-method="filterHandle">
<!--          <template slot-scope="scope">-->
<!--            {{ formatStatus(scope.row.check_status) }}-->
<!--          </template>-->
        </el-table-column>
        <el-table-column v-for="(column, index) in tableColumns" :key="index" :prop="column.prop" :label="column.label" v-if="index > 0 && index < 5"></el-table-column>

        <el-table-column label="操作" fixed="right">
          <template slot-scope="scope">
            <el-popconfirm v-if="scope.row.check_status === '未审核'" title="该记录审核通过？" confirm-button-text='是的'
                           cancel-button-text='存在问题，不通过' @cancel="showDialogReject(scope.row.id)" @confirm="submitCheck(scope.row.id,'已审核')">
              <el-button type="info" slot="reference" size="mini" class="btn-examined" @click.native.stop>审核</el-button>
            </el-popconfirm>

            <el-popconfirm v-else-if="scope.row.check_status === '已审核'" title="确认删除？" @confirm="deleteItems(scope.row.id)">
              <el-button type="danger" slot="reference" size="mini" class="btn-examined" @click.native.stop>删除</el-button>
            </el-popconfirm>
            <el-button @click.stop="showRichText(scope.row.url)" type="primary" size="mini" class="btn-examined">查看附件</el-button>
          </template>
        </el-table-column>
      </el-table>

<!--      分页-->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
                     :page-sizes="[1, 2, 5, 10]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
                     :total="total" class="pagination">
      </el-pagination>

<!--      附件-->
      <el-dialog title="查看附件：" :visible.sync="textVisible" width="80%">
        <img :src="currentImgUrl" alt="预览图片" />
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
          <el-button type="primary" @click="submitCheck(rejectID,'已审核')">确定</el-button>
        </span>
      </el-dialog>

    </div>


  </div>
</template>
  
<script>
import InfoExamineData from "@/data/InfoExamineData";
import request from "@/utils/request";
import axios from "axios";
import * as XLSX from "xlsx";

export default {
  data() {
    return {
      params: {
        select: '', // 选择的搜索项
        search: '', // 输入的搜索内容
        classId: '', // 班级id
        year: '', // 年份id
        isUndergraduate: true, // 是否本科
      },
      total: 0,

      pageNum: 1,   // 前往第几页
      pageSize: 5,   // 每页显示的条数

      allData: [],
      tableData: [],
      textVisible: false,
      rejectVisible: false,
      rejectID: 0,
      rejectReason:'',
      tableColumns: [],
      multipleSelection: [],
      currentImgUrl: '', // 当前预览的图片URL

      selectedText:''
    }
  },

  created() {
    this.updateTableColumns();
    this.loadingData();
  },
  computed: {
    displayedOptions() {  // 表头筛选项
      let options = [];
      let isFirst = true;
      let count = 0;
      Object.values(this.tableColumns).forEach((data) => {
        if (isFirst) {
          isFirst = false;
          return;
        }

        let items = Object.keys(data).map(k => ({
          label: data[k]
        })).filter(item => /[\u4e00-\u9fa5]/.test(item.label));

        if (this.$route.name === 'competition' || this.$route.name === 'paper') {
          if(count > 3){
            return;
          }
        } else if (this.$route.name === 'patent' || this.$route.name === 'software' || this.$route.name === 'monograph' || this.$route.name === 'exchange') {
          if(count > 1){
            return;
          }
        }
        else {
          if(count > 0){
            return;
          }
        }
        count++;
        options = options.concat(items);
      });
      return options;
    }
  },
  watch: {
    $route(to, from) {
      this.updateTableColumns();
      this.clearAll();
      // this.loadingData();
      this.$set(this.params, 'select', '')

    },
  },
  methods: {
    loadingData(){
      // request.post(`/examine/loadingdata/${this.$route.name}`, this.params).then(response => {
      axios.post(`http://127.0.0.1:8080/examine/loadingdata/${this.$route.name}`, this.params).then(response => {
        console.log(this.params);
        if(response.data.code === 200){
          this.allData = response.data.data.tableData;
          this.tableData = (response.data.data.tableData).slice((this.pageNum - 1) * this.pageSize, this.pageNum * this.pageSize);  // 分页
          this.total = response.data.data.count;
        }else{
          this.$message.error(response.data.message);
        }
      })
      this.tableData.forEach(val => { // 开启展开行用
        this.$set(val, "expanded", false);
      });
    },
    deleteItems(id){  // 删除
      // request.put(`/examine/${this.$route.name}/del`, {id: id}).then(res => {
      axios.put(`http://127.0.0.1:8080/examine/${this.$route.name}/del`, {id: id}).then(res => {
        if (res.data.code === 200) {
          this.loadingData();
          this.$message.success(res.data.message);
        } else {
          this.$message.error(res.data.message);
        }
      })
    },
    submitCheck(id,status){ // 审核
      // request.post(`/examine/${this.$route.name}/check`,{id: id,status: status,msg: this.rejectReason}).then(res => {
      axios.post(`http://127.0.0.1:8080/examine/${this.$route.name}/check`,{id: id,status: status,msg: this.rejectReason}).then(res => {
        if (res.data.code === 200) {
          this.rejectVisible = false;
          this.rejectReason = "";
          this.$message.success(res.data.message);

          this.loadingData();
        } else {
          this.$message.error(res.data.code);
        }
      })
    },
    showRichText(url){ // 展示附件
      this.currentImgUrl = url;
      console.log(url);
      if(url == null){
        this.tableData.currentImgUrl = "暂无信息！";
      }
      this.textVisible = true;
    },
    expData() { // 批量导出数据
      if(this.multipleSelection.length === 0){
        this.$message.warning("请勾选待导出项！");
        return;
      }

      // this.$message.success(this.multipleSelection.map(item => item.id).join(","));
      const worksheet = XLSX.utils.json_to_sheet(this.multipleSelection); // 将数据转换为工作表

      const workbook = XLSX.utils.book_new(); // 创建一个新的工作簿
      XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1'); // 将工作表添加到工作簿
      XLSX.writeFile(workbook, `${this.$route.name}`+'_DataExport.xlsx'); // 导出工作簿为Excel文件
    },

    updateTableColumns() {  // 根据路由填入对应表项
      if (InfoExamineData.length > 0) {
        let firstData = null;
        if(this.$route.name === 'competition'){
          firstData = InfoExamineData[0];
        }else if(this.$route.name === 'paper'){
          firstData = InfoExamineData[1];
        }else if(this.$route.name === 'patent'){
          firstData = InfoExamineData[2];
        }else if(this.$route.name === 'software'){
          firstData = InfoExamineData[3];
        }else if(this.$route.name === 'monograph'){
          firstData = InfoExamineData[4];
        }else if(this.$route.name === 'exchange'){
          firstData = InfoExamineData[5];
        }else if(this.$route.name === 'volunteer'){
          firstData = InfoExamineData[6];
        }

        this.tableColumns = Object.keys(firstData).map(key => ({ prop: key, label: firstData[key] }));

      }
    },
    clearAll() {  // 清空
      this.$refs.filterTable.clearFilter();
      this.params = {
        select: '', // 选择的搜索项
        search: '', // 输入的搜索内容
        classId: '', // 班级id
        year: '', // 年份id
        isUndergraduate: true, // 是否本科
      }
      this.rejectReason = "";
      this.$refs.filterTable.clearSelection();

      this.loadingData();
    },
    showDialogReject(id){ // 展示审核后拒绝理由界面
      this.rejectReason = "";
      this.rejectVisible = true;
      this.rejectID = id;
    },
    handleRowClick(row) { // 处理展开行
      row.expanded = !row.expanded;
      this.$refs.filterTable.toggleRowExpansion(row, row.expanded);
    },
    handleSizeChange(pageSize) {  // 处理分页
      this.pageSize = pageSize;
      this.pageNum = 1;
      this.loadingData();
    },
    handleCurrentChange(pageNum) {  // 处理分页
      this.pageNum = pageNum;
      this.loadingData();
    },
    // formatStatus(status) {
    //   return status === '1' ? '已审核' : '未审核';
    // },
    sortByState(a, b){  // 表中审核状态排序
      if(a.check_status === '未审核'){
        return -1;
      } else{
        return 1;
      }
    },
    RowState({row, rowIndex}){  // 表中审核状态颜色
      if(row.check_status === '未审核'){
        return 'warning-row';
      }else if(row.check_status === '已审核'){
        return 'success-row';
      }
      return '';
    },
    filterHandle(value, row, column){ // 表中审核状态筛选
      return row.check_status === value;
    },
    filterChange() {  //根据选择修改总条数
      this.total = this.$refs.filterTable.tableData.length;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    getRowKeys(row){
      return row.id;
    },
    handleSelectChange(value) { // 获取查询对象的文本
      this.params.select = this.tableColumns[value+1].label;
    },
    handleSelectAllChange(){  // 一键全选
      this.$refs.filterTable.clearSelection();
      this.allData.forEach(row => {
        this.$refs.filterTable.toggleRowSelection(row, true);
      });
    }
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
  width: 140px;
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
.el-table__expand-icon {
  visibility:hidden !important;
}

</style>