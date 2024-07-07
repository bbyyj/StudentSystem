<template>
    <div>
<!--    <el-card>-->
      <!-- 表格部分 -->
      <el-table :data="Infos" style="width: 100%" size="small" class="common-table">
        <el-table-column v-for="item in recordForm" 
          :key="item.fieldName" 
          :prop="item.fieldName" 
          :label="item.label" 
          :width="item.width">
          <template #default="scope">
            {{ scope.row.basicInfo[item.fieldName] }}
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
        :total="totalInfos" class="pagination">
      </el-pagination>
  
<!--    </el-card>-->
      
    <!-- 查看图片 -->
    <el-dialog :visible.sync="photoDialogVisible" title="图片预览">
      <img v-if="imageUrl" :src="imageUrl" alt="图片预览" style="max-width: 70%; max-height: 70%;" />
    </el-dialog>

    <!-- 查看详细信息 -->
    <el-dialog title="详细信息" :visible.sync="dialogVisible" >
      <el-form :model="Info" label-width="170px" class="wrapper" size="small">
        <el-form-item 
          v-for="item in detailForm" 
          :label="item.label" 
          :key="item.fieldName" 
          class="blockitem">

          <el-input 
            :disabled="true" 
            v-model="Info[item.fieldName]" 
            :placeholder="item.label" />

        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="medium">取 消</el-button>
      </span>

    </el-dialog>
  
    </div>
 </template>
  
<script>
import axios from 'axios';
import InfoSubmissionsData from '@/data/InfoSubmissionsData.js';
import Cookie from 'js-cookie'

export default {
  data() {
    return {
      Infos: [
      {
        "basicInfo": {
          "sid": "20311000",
          "type": "比赛获奖",
          "rule_type": null,
          "rule_detail": null,
          "url": "https://student-system-1325533066.cos.ap-guangzhou.myqcloud.com/images/abe7afe8-9b42-48f4-a0cb-7ace479d625d.epub",
          "check_status": "未审核",
          "check_msg": "无"
        },
        "detailCA": {
          "rule_score": null,
          "result_type": "国家级",
          "award_name": "一等奖",
          "submit_time": null,
          "result_level": "一等奖",
          "captain": "John Doe",
          "type": "学科竞赛",
          "check_score": null,
          "competition_name": "数学建模大赛",
          "academic_year": 2023,
          "teammate": "Jane Smith, Jack Brown",
          "organization": "中国高等教育学会",
          "time": "2023-06-14T16:00:00.000+00:00",
          "rule_accept": null
        }
      },
      {
        "basicInfo": {
          "sid": "20311000",
          "type": "比赛获奖",
          "rule_type": null,
          "rule_detail": null,
          "url": "https://student-system-1325533066.cos.ap-guangzhou.myqcloud.com/images/4933bc93-fda5-40e6-88e3-72398b2e739c.epub",
          "check_status": "未审核",
          "check_msg": "无"
        },
        "detailCA": {
          "rule_score": null,
          "result_type": "Individual",
          "award_name": "First Prize",
          "submit_time": null,
          "result_level": "Regional",
          "captain": "you",
          "type": "Research Paper",
          "check_score": null,
          "competition_name": "Science Fair 2022",
          "academic_year": 2023,
          "teammate": "John Doe, Jane Smith",
          "organization": "Science Association",
          "time": "2022-08-14T16:00:00.000+00:00",
          "rule_accept": null
        }
      },
      ],
      // 基础信息
      recordForm: [
        { fieldName: 'sid', label: '编号', width: '100px' },
        { fieldName: 'type', label: '类别', width: '120px' },
        { fieldName: 'rule_type', label: '综测大类', width: '150px' },
        { fieldName: 'rule_detail', label: '综测细则', width: '260px'},
        { fieldName: 'check_status', label: '状态', width: '100px' },
        { fieldName: 'check_msg', label: '审核意见', width: '250px' },
      ],
      // 详细信息（根据类型不同而不同）
      detailForm: {},
      // 查看详细信息时显示的数据
      Info: {},
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
    this.detailForm = InfoSubmissionsData[0].items
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
      const apiUrl = 'http://127.0.0.1:8080/student/getMyCA';

      // 定义请求参数
      const params = {
        sid: Cookie.get('sid'),
        page: currentPage - 1, // 添加当前页码参数
        size: pageSize // 添加每页大小参数
      };

      axios.get(apiUrl, { params }) 
        .then(response => {
          if(response.data.code !== 200){
            this.$message.error('获取信息失败');
            return;
          }
          const data = response.data;
          const infoList = data.data.combinedCAList;

          this.totalInfos = data.data.totalNum; 
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
    
    showPhoto(row) {
      this.imageUrl = row.basicInfo.url;
      this.photoDialogVisible = true;
    },

    showInfoDetails(row) {
      this.getInfoType(row.basicInfo.type);

      // 由于基础信息和详细信息中存在相同的键值type，因此不显示基础信息中的type
      // 从recordForm中过滤掉fieldName为"type"的项
      const filteredRecordForm = this.recordForm.filter(item => item.fieldName !== 'type');

      // 从basicInfo中去除键名为"type"的项
      const filteredBasicInfo = { ...row.basicInfo };
      delete filteredBasicInfo.type;

      this.detailForm = [
        ...filteredRecordForm,
        ...InfoSubmissionsData[this.infoType].items
      ];

      this.Info = {
        ...filteredBasicInfo,
        ...row.detailCA
      };

      // 处理Info数据
      for (let key in this.Info) {
        if (this.Info.hasOwnProperty(key) && this.Info[key] === true) { 
          this.Info[key] = '是'; 
        } else if(this.Info.hasOwnProperty(key) && this.Info[key] === false){
          this.Info[key] = '否'; 
        }
      }

      this.dialogVisible = true;
    },

    getInfoType(type){
      if(type == '比赛获奖'){
        this.infoType = '0';
      }
      else if(type == '论文发表'){
        this.infoType = '1';
      }
      else if(type == '专利发明'){
        this.infoType = '2';
      }
      else if(type == '软件著作权发明'){
        this.infoType = '3';
      }
      else if(type == '专著出版'){
        this.infoType = '4';
      }
      else if(type == '赴外校交流'){
        this.infoType = '5';
      }
      else if(type == '志愿服务'){
        this.infoType = '6';
      }
      else if(type == '其他'){
        this.infoType = '7';
      }
      else{
        this.infoType = '8';
      }
    }

  
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
  width: 50%;
  height: auto;
  margin-bottom: 2%;
}
.pagination{
  display: flex;
  justify-content: center;
  margin-top: 8px;
}
.common-table{
  height: 500px;
  position: relative;
  overflow: auto;
}
</style>