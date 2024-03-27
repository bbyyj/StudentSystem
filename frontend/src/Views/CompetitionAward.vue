<template>
  <div>
    <div class="competition-award">
      <div class="filter-search">
        <el-button @click="clearFilter" class="filter-del-btn">清除过滤器</el-button>
        <el-input size="mini" v-model="search" class="search-input" placeholder="输入关键字搜索"/>
      </div>

      <el-table :data="tableData" style="width: 100%" size="mini" :row-class-name="RowState" ref="filterTable">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="table-expand" size="mini">
              <el-form-item label="获奖人名称:">
                <span>{{ props.row.studentName  }}</span>
              </el-form-item>
              <el-form-item label="获奖人学号">
                <span>{{ props.row.studentID }}</span>
              </el-form-item>
              <el-form-item label="成果类别:">
                <span>{{ props.row.achievmentType  }}</span>
              </el-form-item>
              <el-form-item label="获得时间:">
                <span>{{ props.row.achivementTime }}</span>
              </el-form-item>
              <el-form-item label="比赛名称:">
                <span>{{ props.row.competitionName   }}</span>
              </el-form-item>
              <el-form-item label="成果名称">
                <span>{{ props.row.achievementName }}</span>
              </el-form-item>
              <el-form-item label="获奖名称:">
                <span>{{ props.row.awardName }}</span>
              </el-form-item>
              <el-form-item label="成果级别:">
                <span>{{ props.row.achievementGrade  }}</span>
              </el-form-item>
              <el-form-item label="成果等级">
                <span>{{ props.row.achievementRank }}</span>
              </el-form-item>
              <el-form-item label="评奖组织单位:">
                <span>{{ props.row.organizationUnit }}</span>
              </el-form-item>
              <el-form-item label="其他参与人员:">
                <span>{{ props.row.otherPaticipant }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>

        <el-table-column label="ID" prop="id"></el-table-column>
        <el-table-column label="提交时间" prop="submitTime" sortable></el-table-column>
        <el-table-column label="审核状态" prop="submitState" sortable :sort-method="sortByState" :filters="[{text: '已审核', value: '已审核'}, {text: '待审核', value: '待审核'}]"
                         :filter-multiple=false :filter-method="filterHandle"></el-table-column>
        <el-table-column label="获奖人名称" prop="studentName"></el-table-column>
        <el-table-column label="比赛名称" prop="competitionName"></el-table-column>
        <el-table-column label="成果级别" prop="achievementGrade"></el-table-column>
        <el-table-column label="成果等级" prop="achievementRank"></el-table-column>

        <el-table-column label="操作" fixed="right">
          <template slot-scope="scope">
            <el-popconfirm v-if="scope.row.submitState === '待审核'" title="该获奖记录审核通过？" confirm-button-text='是的'
                           cancel-button-text='存在问题，不通过' @cancel="deleteItems(scope.row.id)" @confirm="putItems(scope.row.id)">
              <el-button type="info" slot="reference" size="mini" class="btn-examined">审核</el-button>
            </el-popconfirm>

            <el-popconfirm v-else-if="scope.row.submitState === '已审核'" title="确认删除？" @confirm="deleteItems()">
              <el-button type="danger" slot="reference" size="mini" class="btn-examined">删除</el-button>
            </el-popconfirm>
            <el-button @click="showRichText(scope.row.textMessage)" type="primary" size="mini" class="btn-examined">查看附件</el-button>
          </template>
        </el-table-column>

      </el-table>

      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="params.pageNum"
                     :page-sizes="[1, 2, 5, 10]" :page-size="params.pageSize" layout="total, sizes, prev, pager, next, jumper"
                     :total="total" class="pagination">
      </el-pagination>

      <el-dialog title="查看附件：" :visible.sync="textVisible" width="80%">
        <div v-html="this.tableData.textMessage" class="w-e-text"></div>
      </el-dialog>

    </div>


  </div>
</template>
  
<script>
export default {
  data() {
    return {
      params: {
        pageNum: 1,   // 前往第几页
        pageSize: 5   // 每页显示的条数
      },
      total: 0,

      tableData: [{
        id: '12987125',
        studentName: '好滋好味鸡蛋仔',
        studentID: "11111",
        achievmentType: '成果类别',
        achivementTime: '获得时间，2019-09-09',
        competitionName: '比赛名称',
        achievementName: '成果名称，选填',
        awardName: '获奖名称',
        achievementGrade: '成果级别',
        achievementRank: '成果等级',
        organizationUnit:'评奖组织单位',
        otherPaticipant:'其他参与人员',

        submitTime: '2019-09-09',
        submitState: '已审核',
        textMessage: '附件',
      },{
        id: '12987125',
        studentName: '好滋好味鸡蛋仔',
        studentID: "11111",
        achievmentType: '成果类别',
        achivementTime: '获得时间，2019-09-09',
        competitionName: '比赛名称',
        achievementName: '成果名称，选填',
        awardName: '获奖名称',
        achievementGrade: '成果级别',
        achievementRank: '成果等级',
        organizationUnit:'评奖组织单位',
        otherPaticipant:'其他参与人员',

        submitTime: '2019-02-03',
        submitState: '待审核',
        textMessage: '',
      },{
        id: '12987125',
        studentName: '好滋好味鸡蛋仔',
        studentID: "11111",
        achievmentType: '成果类别',
        achivementTime: '获得时间，2019-09-09',
        competitionName: '比赛名称',
        achievementName: '成果名称，选填',
        awardName: '获奖名称',
        achievementGrade: '成果级别',
        achievementRank: '成果等级',
        organizationUnit:'评奖组织单位',
        otherPaticipant:'其他参与人员',

        submitTime: '2019-02-11',
        submitState: '待审核',
        textMessage: '',
      }],
      textVisible: false,
      search: '',
    }
  },

  methods: {
    showRichText(data){
      this.tableData.textMessage = data;
      if(data == null){
        this.tableData.textMessage = "暂无信息！";
      }
      this.textVisible = true;
    },
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.findBySearch();
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
      }
      else{
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
      console.log(value);
      return row.submitState === value;
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
  width: 90px;
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

.filter-del-btn{
  font-size: 13px;
  padding: 10px;
  background-color: #f0f9eb;
  color: #0b151f;

  margin-right: 20px;
}
.filter-search{
  display: flex;
}
.search-input{
  width: 300px;
}

</style>