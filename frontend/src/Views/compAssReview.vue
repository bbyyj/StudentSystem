<template>
    <div>
        <el-form inline class="search-form" @submit.native.prevent="getList">
            <el-form-item label="审核状态">
                <el-select v-model="searchTerms.state" placeholder="选择审核状态">
                    <el-option label="已审核" value="已审核"></el-option>
                    <el-option label="未审核" value="未审核"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="学号">
                <el-input v-model="searchTerms.sid" placeholder="请输入学号"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
                <el-input v-model="searchTerms.sname" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="search">搜索</el-button>
            </el-form-item>
        </el-form>

        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="student_id" label="学号"></el-table-column>
            <el-table-column prop="student_name" label="姓名"></el-table-column>
            <el-table-column prop="score" label="分数"></el-table-column>

            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" size="mini" @click="goReview(scope.row)"
                        v-if="scope.row.state === '未审核'">审核</el-button>
                    <el-button type="danger" size="mini" @click="goReview(scope.row)" v-else>重新审核</el-button>
                </template>
            </el-table-column>

        </el-table>
        <!--分页相关-->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[1, 2, 5, 10]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total" class="pagination">
        </el-pagination>

    </div>
</template>

<script>
import axios from 'axios';


export default {
    data() {
        return {
            id: parseInt(this.$route.params.id), // 当前学年的综测
            searchTerms: {
                state: "", // 状态
                sid: "", // 学生学号
                sname: "", // 学生姓名
            },
            // 综测审核的信息
            tableData: [
                // { id: 1, a: "21311111", b: "乔羿童", c: "3", d: "5.0", status: "unverified" },
                // { id: 2, a: "21312312", b: "张三", c: "4", d: "5.0", status: "verified" },
                // { id: 3, a: "21342323", b: "李四", c: "1", d: "5.0", status: "unverified" },
                // { id: 4, a: "21342323", b: "王五", c: "1", d: "5.0", status: "verified" },
            ],

            // 分页相关属性
            currentPage: 1,
            pageSize: 5,
            total: 0,

            currentItem: {},
            isEditMode: false,
        };
    },
    created() {
        this.getList();
    },

    methods: {
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
            // 综测的学年 通过路由进行获取
            let id = this.id;

            // 班长携带的信息
            let isUndergraduate = 1;
            let admission_year = 2020;
            let classId = 1;

            // 将页码增加1，并确保page和size是字符串
            let nextPage = parseInt(page) - 1; // 确保页码是计算后再转为字符串
            let pageSize = parseInt(size); // 确保pageSize是字符串
            axios.get(`http://127.0.0.1:8080/ruleReview/getStudentReviewList?id=${id}&isUndergraduate=${isUndergraduate}&admission_year=${admission_year}&classId=${classId}&page=${nextPage}&size=${pageSize}`).then((response) => {
                console.log(response)
                if (response.data.code === 200) {
                    this.tableData = response.data.data.content; // 设置表格数据
                    this.total = response.data.data.totalElements; // 设置总数据量
                    this.currentPage = response.data.data.pageable.pageNumber + 1; // 更新当前页码
                }
            }).catch((error) => {
                console.error("获取数据失败:", error);
            });
        },
        // 搜索
        search() {
            this.getListByCondition()
        },
        // 获取列表数据
        getListByCondition(page = this.currentPage, size = this.pageSize) {
            // 综测的学年 通过路由进行获取
            let id = this.id;

            // 班长携带的信息
            let isUndergraduate = 1;
            let admission_year = 2020;
            let classId = 1;

            // 检索的条件 通过组件进行获取
            let state = this.searchTerms.state;
            let sid = this.searchTerms.sid;
            let sname = this.searchTerms.sname;

            // 将页码增加1，并确保page和size是字符串
            let nextPage = parseInt(page) - 1; // 确保页码是计算后再转为字符串
            let pageSize = parseInt(size); // 确保pageSize是字符串
            axios.get(`http://127.0.0.1:8080/ruleReview/getStudentReviewListByCondition?id=${id}&isUndergraduate=${isUndergraduate}&admission_year=${admission_year}&classId=${classId}&state=${state}&sid=${sid}&sname=${sname}&page=${nextPage}&size=${pageSize}`).then((response) => {
                if (response.data.code === 200) {
                    this.tableData = response.data.data.content; // 设置表格数据
                    this.total = response.data.data.totalElements; // 设置总数据量
                    this.currentPage = response.data.data.pageable.pageNumber + 1; // 更新当前页码
                }
            }).catch((error) => {
                console.error("获取数据失败:", error);
            });
        },

        // 跳转到当前学生的综测审核页面
        goReview(row) {
            this.$router.push({ path: `/compAssDetail/${this.id}/${row.sid}` });
        },
    },
};
</script>