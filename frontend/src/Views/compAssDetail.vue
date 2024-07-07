<template>
    <div class="record-detail" style="height: calc(100vh - 160px) !important">
        <!-- 申请单详情 -->
        <el-card class="box-card" shadow="never" style="border: 0;">
            <!-- <div slot="header" class="clearfix">
                <span>综测详情</span>
            </div> -->
            <el-form label-position="top" class="student-info-form" :model="record" :disabled="true" inline>
                <el-form-item label="学号" size="small">
                    <el-input v-model="record.studentID"></el-input>
                </el-form-item>
                <el-form-item label="姓名" size="small">
                    <el-input v-model="record.name"></el-input>
                </el-form-item>
                <el-form-item label="综测总分" size="small">
                    <el-input v-model="record.score"></el-input>
                </el-form-item>
            </el-form>
        </el-card>

        <!-- 综测信息列表 -->
        <el-card class="box-card" shadow="never" style="border: 0;">
            <div slot="header" class="clearfix">
                <span style="font-weight: bold;font-size: 16px;">以下是学生的综测申请项目，请参照综测表进行不合规分值的修改:</span>
            </div>

            <el-table :data="record.tableData" style=" width: 100%" size="small">
                <el-table-column prop="rule_type" label="综测大类">
                    <template #default="scope">
                        {{ scope.row.rule_type === null ? "空" : scope.row.rule_type }}
                    </template>
                </el-table-column>

                <el-table-column prop="rule_detail" label="综测细则">
                    <template #default="scope">
                        {{ scope.row.rule_detail === null ? "空" : scope.row.rule_detail }}
                    </template>
                </el-table-column>

                <el-table-column prop="source" label="来源"></el-table-column>
                <el-table-column prop="name" label="名称"></el-table-column>

                <el-table-column prop="rule_score" label="分数"></el-table-column>
                <el-table-column prop="check_score" label="审核分数"></el-table-column>

                <el-table-column prop="rule_accept" label="加入综测分数">
                    <template #default="scope">
                        <el-radio-group v-model="scope.row.rule_accept">
                            <el-radio :label="null">待定</el-radio>
                            <el-radio :label="1">加入</el-radio>
                            <el-radio :label="0">不加入</el-radio>
                        </el-radio-group>
                    </template>
                </el-table-column>

                <el-table-column prop="others" label="其他">

                </el-table-column>


                <el-table-column label="操作">
                    <template #default="scope" >
                        <el-button size="small" type="success" @click="showModifyDialog(scope.row)">修改分值</el-button>
                    </template>
                </el-table-column>
            </el-table>


        </el-card>


        <!-- 审批操作按钮 -->
        <el-row type="flex" class="row-bg" justify="end">
            <el-col :span="3">
                <el-button type="success" @click="approveApplication" size="small">审核完毕</el-button>
            </el-col>
            <el-col :span="3">
                <el-button type="primary" @click="goBack" size="small">返回</el-button>
            </el-col>
        </el-row>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[1, 2, 5, 10]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total" class="pagination">
        </el-pagination>

        <!-- 修改分值对话框 -->
        <el-dialog title="修改审核分数" :visible.sync="modifyDialogVisible">
            <el-form :model="currentItem">
                <el-form-item label="审核分数">
                    <el-input v-model="currentItem.check_score" type="number"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="modifyDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="modifyScore">确定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            id: parseInt(this.$route.params.id), // 当前学年的综测
            sid: this.$route.params.sid, // 当前学生的综测

            record: {
                id: 1,
                studentID:"21311111",
                name: "乔羿童",
                score: "5.0",
                remark:"无",
                tableData: [],
            },
            

            // 固定写死的综测大类
            RootCategory: [
                {
                    id: 1,
                    label: "政治思想道德类",
                    value: "1"
                },
                {
                    id: 2,
                    label: "社会工作类",
                    value: "2"
                },
                {
                    id: 3,
                    label: "文体、实践类",
                    value: "3"
                },
                {
                    id: 4,
                    label: "学习、竞赛及科研成果",
                    value: "4"
                }
            ],

            // 分页相关属性
            currentPage: 1,
            pageSize: 5,
            total: 0,

            modifyDialogVisible: false, // 控制修改分值对话框的显示
            currentItem: {}, // 当前要修改的项目
        };
    },
   
    created(){
        this.getList();
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

            let id =this.id; // 综测学年id
            let sid = this.sid; // 学生的id

            axios.get(`http://127.0.0.1:8080/ruleReview/getStudentMatiarial?id=${id}&sid=${sid}&page=${nextPage}&size=${pageSize}`).then((response) => {
                console.log(response)
                if (response.data.code === 200) {
                    this.record.tableData = response.data.data.content; // 设置表格数据
                    this.total = response.data.data.totalElements; // 设置总数据量
                    this.currentPage = response.data.data.pageable.pageNumber + 1; // 更新当前页码
                }
            }).catch((error) => {
                console.error("获取数据失败:", error);
            });
        },


        // 显示修改分值对话框
        showModifyDialog(item) {
            this.currentItem = item;
            this.modifyDialogVisible = true;
        },

        // 修改分值
        modifyScore() {
            this.modifyDialogVisible = false;
        },

        // 审核完毕，将数据发送到后端
        approveApplication() {
            let payload = [
                {
                    "review_id": this.id,
                    "student_id": this.sid
                },
                ...this.record.tableData.map(item => {
                    let newItem = JSON.parse(JSON.stringify(item));
                    newItem.check_score = parseInt(newItem.check_score, 10);
                    return newItem;
                })
            ];
            console.log(payload)
            axios.post('http://127.0.0.1:8080/ruleReview/submitReview', payload)
                .then(response => {
                    console.log(response)
                    if (response.data.code === 200) {
                        this.$message.success('审核数据已成功提交');
                    } else {
                        this.$message.error('提交失败，请重试');
                    }
                })
                .catch(error => {
                    console.error('提交数据失败:', error);
                    this.$message.error('提交失败，请重试');
                });
        },

        // 返回上一个路由
        goBack() {
            this.$router.go(-1);
        },
    },
   
};
</script>