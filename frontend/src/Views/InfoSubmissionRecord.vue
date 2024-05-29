<template>
    <div>
    <el-card>
      <!-- 搜索部分 -->
      <!-- <el-row style="margin-bottom: 20px;">
        <el-col :span="4">
          <el-select v-model="searchKeyword" placeholder="请选择查询条件">
            <el-option v-for="item in recordForm" :key="item.model" :label="item.label" :value="item.model" />
          </el-select>    
        </el-col>
        <el-col :span="4">
          <el-input v-model="searchvalue" placeholder="请输入查询内容" />
        </el-col>
        <el-col :span="4">
          <el-button @click="searchInfos" icon="el-icon-search">查询</el-button>
        </el-col>
      </el-row> -->
  
      <!-- 表格部分 -->
      <el-table :data="Infos" style="width: 100%" size="mini">
        <el-table-column v-for="item in recordForm" :key="item.model" :prop="item.model" :label="item.label" :width="item.width">
          <template #default="scope">
            {{ scope.row.basicInfo[item.model] }}
          </template>
        </el-table-column>

        <el-table-column label="附件" width="80">
          <template #default="{ row }">
            <el-button type="text" size="small" @click="showPhoto(row)" style="margin-right: 8px;">查看</el-button>
            </template>
        </el-table-column>
        <el-table-column label="详细信息" width="80">
          <template #default="{ row }">
            <el-button type="text" size="small" @click="showInfoDetails(row)" style="margin-right: 8px;">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <!-- 分页 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalInfos">
      </el-pagination>
  
    </el-card>
      
    <!-- 查看图片 -->
    <el-dialog :visible.sync="photoDialogVisible" title="图片预览">
      <img v-if="imageUrl" :src="imageUrl" alt="图片预览" />
    </el-dialog>
    <!-- 查看详细信息 -->
    <el-dialog title="详细信息" :visible.sync="dialogVisible" >
      <el-form :model="Info" label-width="80px" size="mini" class="wrapper">
        <el-form-item v-for="item in recordForm" :label="item.label" :key="item.model" class="blockitem">
          <el-input :disabled="true" v-model="Info[item.model]" :placeholder="item.label" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
  
    </div>
 </template>
  
<script>
import axios from 'axios';
import InfoSubmissionsData from '@/data/InfoSubmissionsData.js';

export default {
  data() {
    return {
      Infos: [
        { "basicInfo": 
          {
            "sid": "20311000",
            "type": "比赛获奖",
            "rule_type": null,
            "rule_detail": null,
            "url": "https://student-system-1325533066.cos.ap-guangzhou.myqcloud.com/images/abe7afe8-9b42-48f4-a0cb-7ace479d625d.epub",
            "check_status": "未审核",
            "check_msg": "无"
          },
          "detailCA": 
          {
            "分数": null,
            "提交时间": null,
            "成果等级": "一等奖",
            "所属学年": 2023,
            "比赛名称": "数学建模大赛",
            "获得时间": "2023-06-14T16:00:00.000+00:00",
            "评奖组织单位": "中国高等教育学会",
            "其他参与人员": "Jane Smith, Jack Brown",
            "是否接受": null,
            "负责人": "John Doe",
            "成果级别": "国家级",
            "审核分数": null,
            "成果类别": "学科竞赛",
            "获奖名称": "一等奖"
          }
        }
      ],
      recordForm: [
        { model: 'sid', label: '编号', width: '100px' },
        { model: 'type', label: '类别', width: '120px' },
        { model: 'rule_type', label: '综测大类', width: '150px' },
        { model: 'rule_detail', label: '综测细则', width: '260px'},
        { model: 'check_status', label: '状态', width: '100px' },
        { model: 'check_msg', label: '审核意见', width: '250px' },
      ],
      //修改或添加时的表单
      Info: { 
        
      },
      // 图片预览相关属性
      photoDialogVisible: false,
      imageUrl: '',
      // 详细信息显示相关属性
      dialogVisible: false,
      infoType: '0',
      // 搜索相关属性
      searchvalue: '',
      searchKeyword: '',
      // 分页相关属性
      currentPage: 1,
      pageSize: 5,
      totalInfos: 0,
    };
  },
  created() {
    this.loadInfo(this.pageSize, this.currentPage);
  },
  computed: {
    getBasicInfoProp(model) {
      
      return (info) => {
          return info.basicInfo[model];
      };
    }
  },
  methods: {
    // 获取学生数据并加载到表格中
    loadInfo(pageSize, currentPage) {
      const apiUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/173896609';

      // 定义请求参数
      const params = {
        sid: '20311000',
        page: currentPage, // 添加当前页码参数
        size: pageSize // 添加每页大小参数
      };

      axios.get(apiUrl, { params }) 
        .then(response => {
          const data = response.data;
          const infoList = data.data;

          this.totalInfos = infoList.length; 
          this.Infos = infoList; 
          
        })
        .catch(error => {
          console.error('There was a problem with your fetch operation:', error);
        });
    },

    
    // 页面相关
    // 处理每页显示数量变化
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.loadInfo(this.pageSize, this.currentPage);
    },
    // 处理当前页变化
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.loadInfo(this.pageSize, this.currentPage);
    },

    async searchInfos() {
      
    },
    
    showPhoto(row) {
      this.imageUrl = row.basicInfo.url;
      this.photoDialogVisible = true;
    },

    showInfoDetails(row) {
      if(row.basicInfo.type == '比赛获奖'){
        this.infoType = '0';
      }
      else if(row.basicInfo.type == '论文发表'){
        this.infoType = '1';
      }
      else if(row.basicInfo.type == '专利发明'){
        this.infoType = '2';
      }
      else if(row.basicInfo.type == '软件著作权发明'){
        this.infoType = '3';
      }
      else if(row.basicInfo.type == '专著出版'){
        this.infoType = '4';
      }
      else if(row.basicInfo.type == '赴外校交流'){
        this.infoType = '5';
      }
      else if(row.basicInfo.type == '志愿服务'){
        this.infoType = '6';
      }
      else if(row.basicInfo.type == '其他'){
        this.infoType = '7';
      }
      else{
        this.infoType = '8';
      }
      this.info = row.detailCA;
      this.dialogVisible = true;
    },

  
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