<template>
    <div class="record-detail" style="height: calc(100vh - 160px) !important">
        <!-- 申请单详情 -->
        <el-card class="box-card" shadow="never">
            <div slot="header" class="clearfix">
                <span>综测详情</span>
            </div>
            <el-form
                    label-position="top"
                    class="student-info-form"
                    :model="record"
                    :disabled="true"
                    inline
            >
                <el-form-item label="学号">
                    <el-input v-model="record.studentID"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="record.name"></el-input>
                </el-form-item>
                <el-form-item label="综测分数">
                    <el-input v-model="record.score"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="record.remark"></el-input>
                </el-form-item>
            </el-form>
        </el-card>

        <!-- 综测信息列表 -->
        <el-card class="box-card" shadow="never">
            <div slot="header" class="clearfix">
                <span>以下是学生的综测申请项目</span>
            </div>
            <el-form
                    inline
                    class="search-form"
                    @submit.native.prevent="fetchListData"
            >
                <el-form-item label="类别">
                    <el-input v-model="searchTerms.a" placeholder="请输入类别"></el-input>
                </el-form-item>
                <el-form-item label="加分条件">
                    <el-input v-model="searchTerms.b" placeholder="请输入加分条件"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="searchTerms.c" placeholder="请输入备注"></el-input>
                </el-form-item>
                <el-form-item label="分值">
                    <el-input v-model="searchTerms.d" placeholder="请输入分值"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="fetchListData">搜索</el-button>
                </el-form-item>
            </el-form>

            <el-table :data="record.list" style="width: 100%">
                <el-table-column prop="time" label="时间"></el-table-column>
                <el-table-column prop="a" label="类别"></el-table-column>
                <el-table-column prop="b" label="加分条件"></el-table-column>
                <el-table-column prop="c" label="备注"></el-table-column>
                <el-table-column prop="d" label="分值"></el-table-column>
                <el-table-column prop="e" label="附件"></el-table-column>

                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button
                                size="mini"
                                type="success"
                                @click="approveItem(scope.row)"
                        >通过</el-button
                        >
                        <el-button
                                size="mini"
                                type="danger"
                                @click="showRejectDialog(scope.row)"
                        >不通过</el-button
                        >
                    </template>
                </el-table-column>
            </el-table>

            <el-pagination
                    layout="total, prev, pager, next"
                    :total="totalItems"
                    :current-page.sync="currentPage"
                    :page-size="pageSize"
                    @current-change="fetchListData"
            ></el-pagination>
        </el-card>

        <el-dialog title="拒绝理由" :visible.sync="showDialogReject" width="30%">
            <el-form>
                <el-form-item label="请输入拒绝的理由">
                    <el-input
                            type="textarea"
                            v-model="rejectReason"
                            rows="5"
                            placeholder="请输入理由"
                    ></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="showDialogReject = false">取消</el-button>
        <el-button type="primary" @click="submitRejectReason">确定</el-button>

      </span>
        </el-dialog>

        <!-- 审批操作按钮 -->
        <el-row type="flex" class="row-bg" justify="end">
            <el-col :span="3">
                <el-button type="success" @click="approveApplication">通过</el-button>
            </el-col>
            <el-col :span="3">
                <el-button type="danger" @click="rejectApplication">不通过</el-button>
            </el-col>
        </el-row>

    </div>
</template>

<script>
export default {
    data() {
        return {
            searchTerms: {
                a: "",
                b: "",
                c: "",
                d: "",
                e:"",
                time:""
            },
            record: {
                id: 1,
                studentID:"21311111",
                name: "乔羿童",
                score: "5.0",
                remark:"无",
                list: [
                    { a: "社会工作类", b: "院团委副书记", c: "软件工程学院院团委副书记", d: "3.0分" },
                    { a: "政治思想道德类", b: "全国三好学生", c: "无", d: "1.5分" },
                    { a: "政治思想道德类", b: "校级马研班、青马班获评优秀学员", c: "无", d: "0.8分" },
                    { a: "社会工作类", b: "班委委员", c: "软件工程学院2021级3班文体委员", d: "0.5分" },
                ],
            },
            totalItems: 4, // 模拟的总条目数
            pageSize: 10, // 默认每页显示的条目数
            currentPage: 1, // 当前页码
            showDialogReject: false, // 控制拒绝理由对话框的显示
            rejectReason: "", // 存储用户输入的拒绝理由
            currentRejectItem: null, // 当前打算拒绝的项目
        };
    },
    methods: {
        cancelReject() {
            this.showDialogReject = false; // 控制拒绝理由对话框的显示
            this.rejectReason = ""; // 存储用户输入的拒绝理由
            this.currentRejectItem = null; // 当前打算拒绝的项目
        },
        showRejectDialog(item) {
            this.currentRejectItem = item;
            this.rejectReason = ""; // 清空上次的输入
            this.showDialogReject = true;
        },
        fetchListData() {
            // 后端接口 根据 searchTerms 进行搜索
            console.log("搜索条件", this.searchTerms);
            // 假设这里返回了搜索结果和总数
            // this.record.list = ...
            // this.totalItems = ...
        },
        editItem(item) {
            // 修改列表项，可能需要弹窗或导航到编辑表单页面
            console.log("编辑项目", item);
        },
        approveItem(item) {
            // 通过审批 发送到后端 修改单项条目状态
            console.log("通过审批", item);
        },
        rejectItem(item) {
            // 不通过审批 发送到后端 修改单项条目状态
            console.log("不通过审批", item);
        },
        approveApplication() {
            // 审批通过整个申请单 修改整个表单状态
            console.log("整个申请单通过审批");
        },
        rejectApplication() {
            // 审批不通过整个申请单 修改整个表单状态
            console.log("整个申请单不通过审批");
        },
        // 设置不通过的理由
        submitRejectReason() {},
    },
    mounted() {
        // 页面加载完成后获取数据
        this.fetchListData();
    },
};
</script>