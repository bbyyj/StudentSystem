<template>
    <div>
<!--        <el-card>-->
            <!-- 搜索部分 -->
            <el-row style="margin-bottom: 20px;">
                <el-col :span="4">
                    <el-select v-model="isUndergraduate" placeholder="请选择学生身份"  size="small">
                        <el-option value="全部">全部</el-option>
                        <el-option v-for="item in studentType" :key="item.key" :label="item.label"
                            :value="item.value" />
                    </el-select>
                </el-col>

                <el-col :span="4">
                    <el-select v-model="admission_year" placeholder="请选择入学年份"  size="small" class="search-with-select">
                        <el-option value="全部">全部</el-option>
                        <el-option v-for="item in AllAdmissionYear" :key="item.key" :label="item.label"
                            :value="item.value" />
                    </el-select>
                </el-col>

                <el-col :span="2.5">
                    <el-button @click="searchStudents" icon="el-icon-search" size="small" class="search-with-select">查询</el-button>
                </el-col>

                <el-col :span="4">
                    <el-popover placement="bottom" width="auto" trigger="click">
                        <el-upload :action="uploadUrl" :multiple="false" :limit="1" :file-list="fileList"
                            :show-file-list="true" accept="xlsx" :on-exceed="handleExceed" :on-error="handleError"
                            :before-upload="validate" :on-success="handleSuccess" :http-request="uploadFile"
                            method="PUT">
                            <el-button size="small" type="plain">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传单个.xlsx格式的Excel文件</div>
                        </el-upload>

                        <el-button slot="reference" type="primary" @click="importStudents" size="small" class="search-with-select">导入学生绩点</el-button>
                    </el-popover>
                </el-col>
            </el-row>

            <!-- 表格部分 -->
            <el-table :data="Students" style="width: 100%" size="small" class="common-table">
                <el-table-column v-for="item in studentForm" :key="item.model" :prop="item.model" :label="item.label"
                    :width="item.width" />
            </el-table>

            <!-- 分页 -->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="currentPage" :page-sizes="[5, 10, 20]" :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="totalStudents" class="pagination">
            </el-pagination>

<!--        </el-card>-->



    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            // 综测发布id，后续可能从路由处进行获取
            compID: parseInt(this.$route.params.id), // 当前学年的综测,

            //正常使用的数据
            Students: [],
            studentForm: [
                { model: 'undergraduate', label: '本科生/研究生', width: '120px' },
                { model: 'student_id', label: '学号', width: '120px' },
                { model: 'student_name', label: '姓名', width: '120px' },
                { model: 'admission_year', label: '入学年份', width: '120px' },
                { model: 'classId', label: '班级', width: '120px' },
                { model: 'state', label: '状态', width: '120px' },
                { model: 'score', label: '综测分数', width: '120px' },
                { model: 'exam_score', label: '绩点', width: '120px' },
                { model: 'final_score', label: '最终分数', width: '200px' },
            ],

            // 搜索相关属性
            isUndergraduate: '',
            admission_year: '',
            studentType: [
                { label: '本科生', key: 1, value: 1 },
                { label: '研究生', key: 0, value: 0 },
            ],
            AllAdmissionYear: [
                { label: '2020年', key: 2020, value: 2020 },
                { label: '2021年', key: 2021, value: 2021 },
                { label: '2022年', key: 2022, value: 2022 },
                { label: '2023年', key: 2023, value: 2023 },
            ],

            // 文件导入相关
            selectedType: '1', // 初始选中项
            fileList: [], // 上传文件列表
            uploadUrl: 'http://127.0.0.1:8080/ruleReview/upLoadExamScore', // 批批处理批量生成的默认接口

            // 分页相关属性
            currentPage: 1,
            pageSize: 5,
            totalStudents: 0,
        };
    },
    created() {
        this.loadStudents(this.pageSize, this.currentPage);
    },
    methods: {
        loadStudents(pageSize, currentPage) {
            const apiUrl = 'http://127.0.0.1:8080/ruleReview/getAllStudentReviewList';

            // 定义请求参数
            const params = {
                id: this.compID, // 综测发布的id,需要通过路由跳转或其他方式进行获取
                page: currentPage - 1, // 添加当前页码参数
                size: pageSize // 添加每页大小参数
            };

            axios.get(apiUrl, { params }) // 将参数对象作为配置传递给GET请求
                .then(response => {
                    const data = response.data;
                    const studentList = data.data.content;

                    // 判断学生类型，并替换为本科生或研究生
                    studentList.forEach(student => {
                        if (student.undergraduate) {
                            student.undergraduate = '本科生';
                        } else {
                            student.undergraduate = '研究生';
                        }
                    });

                    this.totalStudents = data.data.totalElements;
                    this.Students = studentList;

                })
                .catch(error => {
                    console.error('There was a problem with your fetch operation:', error);
                });
        },

        // 页面相关
        // 处理每页显示数量变化
        handleSizeChange(pageSize) {
            this.pageSize = pageSize;
            this.loadStudents(this.pageSize, this.currentPage);
        },
        // 处理当前页变化
        handleCurrentChange(currentPage) {
            // 用户看到的页码减1，因为后端返回页面从0开始
            this.currentPage = currentPage;
            this.loadStudents(this.pageSize, this.currentPage);
        },

        // 增删改查
        async searchStudents() {
            // 获取用户选择的查询条件\查询内容
            const selectedisUndergraduate = this.isUndergraduate;
            const selectedadmission_year = this.admission_year;

            if (selectedisUndergraduate === '' || selectedadmission_year === '') {
                this.$message('请选择完整两个查询条件后再进行搜索！');
                this.loadStudents(this.pageSize, this.currentPage);
                return;
            }
            else if (selectedisUndergraduate === '全部' || selectedadmission_year === '全部') {
                this.loadStudents(this.pageSize, this.currentPage);
                return;
            }

            const params = {
                id: this.compID, // 综测发布的id 后续需要通过路由获取
                isUndergraduate: this.isUndergraduate,
                admission_year: this.admission_year,
                page: this.currentPage - 1,
                size: this.pageSize
            };

            console.log(params)
            axios.get('http://127.0.0.1:8080/ruleReview/getStudentReviewListByTeacher', { params })
                .then(response => {
                    const data = response.data;
                    const studentList = data.data.content;

                    studentList.forEach(student => {
                        if (student.undergraduate) {
                            student.undergraduate = '本科生';
                        } else {
                            student.undergraduate = '研究生';
                        }
                    });
                    // 更新表格数据
                    this.Students = studentList;
                    // 更新总条数，如果API返回
                    this.totalStudents = data.data.numberOfElements;
                    console.log(this.Students);
                })
                .catch(error => {
                    console.error('Error during search:', error);
                });
        },


        importStudents() {
            // 批量导入
        },

        handleExceed(fileList) {
            console.log('文件超过限制');
            // 这里可以添加提示信息或逻辑，比如弹窗警告用户已超过上传限制
        },
        handleError(err, file) {
            console.error(err);
            this.$message('上传失败，请重试');
            // 显示错误提示给用户
        },
        validate(file) {
            const isExcel = file.name.endsWith('.xlsx');
            if (!isExcel) {
                this.$message('只支持.xlsx文件');
                return false; // 阻阻止不符合条件的文件上传
            }
            return true; // 允许继续上传
        },
        handleSuccess(response, file) {
            console.log(response);
            this.$message('上传成功,综测分数和绩点已经自动累加');
            this.loadStudents(this.pageSize, this.currentPage);
        },
        // 自定义的excel文件上传
        uploadFile({ file }) {
            const formData = new FormData();
            formData.append('file', file);
            formData.append('id', this.compID);

            fetch(this.uploadUrl, {
                method: 'PUT',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    if (data.code === 200) {
                        this.handleSuccess(data, file);
                    } else {
                        this.handleError(new Error('上传失败'), file);
                    }
                })
                .catch(err => {
                    this.handleError(err, file);
                });
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
.search-with-select{
  //width: 140px;
  margin-left: 8px;
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
