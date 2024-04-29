<template>
    <div>
        <el-form inline class="search-form" @submit.native.prevent="fetchData">
            <el-form-item label="审核状态">
                <el-select v-model="searchTerms.status" placeholder="选择审核状态">
                    <el-option label="已审核" value="verified"></el-option>
                    <el-option label="未审核" value="unverified"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="学号">
                <el-input v-model="searchTerms.a" placeholder="请输入学号"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
                <el-input v-model="searchTerms.b" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="班级">
                <el-input v-model="searchTerms.c" placeholder="请输入班级"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="fetchData">搜索</el-button>
            </el-form-item>
        </el-form>

        <el-table :data="comprehensiveData" style="width: 100%">
            <el-table-column prop="a" label="学号"></el-table-column>
            <el-table-column prop="b" label="姓名"></el-table-column>
            <el-table-column prop="c" label="班级"></el-table-column>
            <el-table-column prop="d" label="分数"></el-table-column>

            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" size="mini" @click="goReview(scope.row)"
                        v-if="scope.row.status === 'unverified'">审核</el-button>
                    <el-button type="danger" size="mini" @click="goReview(scope.row)" v-else>重新审核</el-button>
                </template>
            </el-table-column>

        </el-table>

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
                status: ''  // 默认为空，即未选择状态
            },
            // mock 综测信息
            comprehensiveData: [
                { id: 1, a: "21311111", b: "乔羿童", c: "3", d: "5.0", status:"unverified" },
                { id: 2, a: "21312312", b: "张三", c: "4", d: "5.0", status: "verified" },
                { id: 3, a: "21342323", b: "李四", c: "1", d: "5.0", status: "unverified" },
                { id: 4, a: "21342323", b: "王五", c: "1", d: "5.0", status: "verified" },
            ],
            // 分页相关属性
            currentPage: 1,
            pageSize: 5,
            total: 0,
            currentItem: {},
            isEditMode: false,
        };
    },
    methods: {
        handleSizeChange(pageSize) {
            // this.pageSize = pageSize;
            // this.loadStudents(this.pageSize, this.currentPage);
        },
        // 处理当前页变化
        handleCurrentChange(currentPage) {
            // this.currentPage = currentPage;
            // this.loadStudents(this.pageSize, this.currentPage);
        },
        // 根据学生信息进行搜索
        fetchData() {
            // 后端接口 根据 searchTerms 进行搜索
            console.log("搜索条件", this.searchTerms);
            // 假设这里返回了搜索结果和总数
            // this.comprehensiveData = ...
            // this.totalItems = ...
        },

        // 跳转到当前学生的综测审核页面
        goReview(row) {
            this.$router.push({ path: `/compAssDetail/${row.a}`});
        },
    },
};
</script>