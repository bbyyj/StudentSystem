<template>
    <div class="record-detail" style="height: calc(100vh - 160px) !important">
        <!-- 申请单详情 -->
        <el-card class="box-card" shadow="never">
            <!-- <div slot="header" class="clearfix">
                <span>综测详情</span>
            </div> -->
            <el-form label-position="top" class="student-info-form" :model="record" :disabled="true" inline>
                <el-form-item label="学号">
                    <el-input v-model="record.studentID"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="record.name"></el-input>
                </el-form-item>
                <el-form-item label="综测总分">
                    <el-input v-model="record.score"></el-input>
                </el-form-item>
            </el-form>
        </el-card>

        <!-- 综测信息列表 -->
        <el-card class="box-card" shadow="never">
            <div slot="header" class="clearfix">
                <span>以下是学生的综测申请项目，请参照综测表进行不合规分值的修改</span>
            </div>

            <el-form inline class="search-form" @submit.native.prevent="fetchListData">
                <el-form-item label="类别">
                    <el-select v-model="selectedRuleType" placeholder="请选择综测类别" width="100px">
                        <el-option v-for="item1 in RootCategory" :key="item1.id" :value="item1.value"
                            :label="item1.label"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="加分条件">
                    <el-input v-model="searchTerms.b" placeholder="请输入加分条件"></el-input>
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
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button size="mini" type="success" @click="modifyItem(scope.row)">修改分值</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页-->

        </el-card>


        <!-- 审批操作按钮 -->
        <el-row type="flex" class="row-bg" justify="end">
            <el-col :span="3">
                <el-button type="success" @click="approveApplication">审核完毕</el-button>
            </el-col>
            <el-col :span="3">
                <el-button type="primary" @click="rejectApplication">返回</el-button>
            </el-col>
        </el-row>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[1, 2, 5, 10]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total" class="pagination">
        </el-pagination>

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
                    { a: "社会工作类", b: "院团委副书记", c: "软件工程学院院团委副书记", d: "3.0分",fileUrl:"https://attachment-1325509405.cos.ap-guangzhou.myqcloud.com/%E6%8F%90%E4%BA%A4%E8%AF%81%E6%98%8E.png"  },
                    { a: "政治思想道德类", b: "全国三好学生", c: "无", d: "1.5分",fileUrl: "https://attachment-1325509405.cos.ap-guangzhou.myqcloud.com/%E9%99%84%E4%BB%B61.%E4%B8%AD%E5%B1%B1%E5%A4%A7%E5%AD%A62023%E5%B9%B4%E5%BA%A6%E6%B0%91%E4%B8%BB%E8%AF%84%E8%AE%AE%E5%85%9A%E5%91%98%E7%99%BB%E8%AE%B0%E8%A1%A8.pdf" },
                    { a: "政治思想道德类", b: "校级马研班、青马班获评优秀学员", c: "无", d: "0.8分" },
                    { a: "社会工作类", b: "班委委员", c: "软件工程学院2021级3班文体委员", d: "0.5分" },
                ],
            },
            selectedRuleType:"",
            RootCategory: [
                {
                    id: 1,
                    label: "社会工作类",
                    value: "社会工作类"
                },
                {
                    id: 2,
                    label: "政治思想道德类",
                    value: "政治思想道德类"
                },
                {
                    id: 3,
                    label: "文体、实践类",
                    value: "文体、实践类"
                },
                {
                    id: 4,
                    label: "学习、竞赛及科研成果",
                    value: "学习、竞赛及科研成果"
                }
            ],

            // 分页相关属性
            currentPage: 1,
            pageSize: 5,
            total: 0,

            showDialogReject: false, // 控制拒绝理由对话框的显示
            rejectReason: "", // 存储用户输入的拒绝理由
            currentRejectItem: null, // 当前打算拒绝的项目
        };
    },
    methods: {
        // 修改新的分值
        modifyItem(item) {
            // 弹出对话框或通过其他UI方式接受新分值
            let newScore = prompt("请输入新的分值：", item.d); // 使用prompt作为简单示例
            if (newScore !== null && newScore.trim() !== '' && !isNaN(newScore)) {
                item.d = parseFloat(newScore).toFixed(1) + "分"; // 更新项目分数
                this.updateTotalScore(); // 更新总分
            }
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
        // 处理每页显示数量变化
        handleSizeChange(pageSize) {
            // this.pageSize = pageSize;
            // this.loadStudents(this.pageSize, this.currentPage);
        },
        // 处理当前页变化
        handleCurrentChange(currentPage) {
            // this.currentPage = currentPage;
            // this.loadStudents(this.pageSize, this.currentPage);
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