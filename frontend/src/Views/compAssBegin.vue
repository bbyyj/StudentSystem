<template>
    <div class="compAssBegin">
        <div class="compAssBegin-header">
            <!-- 新增按钮 -->
            <el-button type="primary" @click="handlecreate">+ 新增综测</el-button>

            <!-- 对话框:点击新增或编辑才会弹出表单 -->
            <!-- :before-close="closeDialog" 点击关闭的x之前要做的事情 -->
            <el-dialog :title="modalType == 0 ? '新建' : '编辑'" :visible.sync="dialogVisible" width="50%"
                :before-close="closeDialog">
                <!-- 表单Form -->
                <!-- ref=form:为了通过this.$refs调用组件的方法 -->
                <el-form :inline="true" :model="form" :rules="rules" ref="form" label-width="80px">

                    <!-- 每一项表单域:el-form-item -->
                    <el-form-item label="综测名称" prop="name">
                        <el-input placeholder="请输入综测名称" v-model="form.compAssName"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间">
                        <el-form-item prop="start_time">
                            <el-date-picker v-model="form.compAssBeginTime" type="date" placeholder="选择日期"
                                format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd">
                            </el-date-picker>
                        </el-form-item>
                    </el-form-item>
                    <el-form-item label="结束时间">
                        <el-form-item prop="end_time">
                            <el-date-picker v-model="form.compAssEndTime" type="date" placeholder="选择日期"
                                format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd">
                            </el-date-picker>
                        </el-form-item>
                    </el-form-item>

                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="closeDialog">取 消</el-button>
                    <el-button type="primary" @click="submit">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 搜索框 -->
            <el-form :inline="true">
                <el-form-item>
                    <el-input v-model="searchForm.name" placeholder="请输入综测名称"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="search">查询</el-button>
                </el-form-item>
            </el-form>
        </div>

        <div class="common-table">
            <!-- 用户数据Table -->
            <el-table :data="tableData" stripe style="width: 100%" height="90%">
                <el-table-column prop="name" label="综测名称">
                </el-table-column>
                <el-table-column prop="start_time" label="综测开始时间">
                </el-table-column>
                <el-table-column prop="end_time" label="综测结束时间">
                </el-table-column>

                <!-- 自定义列 -->
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button type="primary" @click="gotoReview(scope.row)">查看</el-button>
                        <el-button @click="handleEdit(scope.row)">编辑</el-button>
                    </template>
                </el-table-column>

            </el-table>
            <!-- 分页 -->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="currentPage" :page-sizes="[1, 2, 5, 10]" :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="total" class="pagination">
            </el-pagination>

        </div>
    </div>
</template>

<script>

import axios from 'axios';


export default {
    data() {
        return {
            // 表单绑定的数据
            form: {
                compAssId:'',
                compAssName: '',
                compAssBeginTime: '',
                compAssEndTime: '',
            },
            // 表单验证规则
            rules: {
                compAssName: [{ required: true, message: '请输入综测名称', trigger: 'blur' }],
                compAssBeginTime: [{ required: true, message: '请选择综测开始时间', trigger: 'blur' }],
                compAssEndTime: [{ required: true, message: '请输入综测结束时间', trigger: 'blur' }],
            },
            // 表单是否打开
            dialogVisible: false,
            // 列表数据
            tableData: [
                {
                    compAssID: 1,
                    compAssName: '2024年综测',
                    compAssBeginTime: '2024-10-01',
                    compAssEndTime: '2024-10-20',
                },
                {
                    compAssID: 2,
                    compAssName: '2023年综测',
                    compAssBeginTime: '2023-10-01',
                    compAssEndTime: '2023-10-20',
                },
            ],
            // 打开表单:新建0,编辑1
            modalType: 0,
            // 分页相关属性
            currentPage: 1,
            pageSize: 5,
            total: 0,

            // 搜索框表单 用来根据特定条件进行检索
            searchForm: {
                compAssName: ''
            }
        }
    },
    methods: {
        handleSizeChange(pageSize) {
            this.pageSize = pageSize;
            this.getList(this.currentPage, pageSize);
        },
        // 处理当前的页码
        handleCurrentChange(currentPage) {
            this.currentPage = currentPage;
            this.getList(currentPage);
        },
        // 获取列表数据 获取当前全部的综测发布内容
        getList(page = this.currentPage, size = this.pageSize) {
            // 将页码增加1，并确保page和size是字符串
            let nextPage = parseInt(page) - 1; // 确保页码是计算后再转为字符串
            let pageSize = parseInt(size); // 确保pageSize是字符串
            axios.get(`http://127.0.0.1:8080/ruleReview/getAllReview?page=${nextPage}&size=${pageSize}`).then((response) => {
                if (response.data.code === 200) {
                    this.tableData = response.data.data.content; // 设置表格数据
                    this.total = response.data.data.totalElements; // 设置总数据量
                    this.currentPage = response.data.data.pageable.pageNumber + 1; // 更新当前页码
                }
            }).catch((error) => {
                console.error("获取数据失败:", error);
            });
        },
        // 根据条件检索获取
        getListByCondition(page = this.currentPage, size = this.pageSize) {
            // 将页码增加1，并确保page和size是字符串
            let nextPage = parseInt(page) - 1; // 确保页码是计算后再转为字符串
            let pageSize = parseInt(size); // 确保pageSize是字符串
            let name = this.searchForm.name
            axios.get(`http://127.0.0.1:8080/ruleReview/getAllReviewByCondition?name=${name}&page=${nextPage}&size=${pageSize}`).then((response) => {
                if (response.data.code === 200) {
                    this.tableData = response.data.data.content; // 返回的表格数据
                    this.total = response.data.data.totalElements; // 数据的总条数
                    this.currentPage = response.data.data.pageable.pageNumber + 1; // 数据的总页数
                }
            }).catch((error) => {
                console.error("获取数据失败:", error);
            });
        },
        // 表单提交
        submit() {
            // 要用箭头函数,若用function会报错
            this.$refs.form.validate((valid) => {
                // 符合校验
                if (valid) {
                    const addReviewData = {
                        name: this.form.compAssName,          // 根据需要从组件的data或props中获取
                        start_time: this.form.compAssBeginTime,  // 同上，示例直接使用硬编码值
                        end_time: this.form.compAssEndTime       // 同上
                    };
                    console.log(addReviewData)
                    const updateReviewData = {
                        id: this.form.compAssId,
                        name: this.form.compAssName,          // 根据需要从组件的data或props中获取
                        start_time: this.form.compAssBeginTime,  // 同上，示例直接使用硬编码值
                        end_time: this.form.compAssEndTime       // 同上
                    };
                    // 提交数据
                    if (this.modalType === 0) {
                        // 新增综测
                        axios.post(`http://127.0.0.1:8080/ruleReview/addReview`, addReviewData).then((response) => {
                            if (response.data.code == 200) {
                                this.$message({
                                    type: 'success',
                                    message: '新增成功!'
                                })
                                // 重新进行请求新的内容
                                this.getList()
                            }
                        }).catch((error) => {
                            this.$message({
                                type: 'error',
                                message: '新增失败!'
                            })
                        })
                    } else {
                        // 编辑综测
                        axios.put(`http://127.0.0.1:8080/ruleReview/updateReview`, updateReviewData).then((response) => {
                            if(response.data.code ==200){
                                this.$message({
                                    type: 'success',
                                    message: '修改成功!'
                                })
                                // 重新进行请求新的内容
                                this.getList()
                            }
                        }).catch((error) => {
                            this.$message({
                                type: 'error',
                                message: '修改失败!'
                            })
                        })
                    }
                    // 清空,关闭
                    this.closeDialog()
                }
            })
        },
        // 关闭对话框
        closeDialog() {
            // 先重置
            this.$refs.form.resetFields()
            // 后关闭
            this.dialogVisible = false
        },
        // 编辑按钮
        handleEdit(item) {
            this.modalType = 1
            this.form.compAssId = item.id
            this.form.compAssName = item.name
            this.form.compAssBeginTime = item.start_time
            this.form.compAssEndTime = item.end_time
            this.openForm()
        },

        // 新建按钮
        handlecreate() {
            this.modalType = 0;
            this.form.compAssName = ''
            this.form.compAssBeginTime =''
            this.form.compAssEndTime = ''
            this.openForm();
        },
        // 打开表单
        openForm() {
            this.dialogVisible = true
        },

        // 搜索
        search() {
            this.getListByCondition();
        },
        // 跳转到综测审核页面
        gotoReview(row) {
            this.$router.push({ path: `/compAssReview/${row.compAssID}/${row.compAssName}` });
        },


    },
    mounted() {
        // 挂载时获取当前页面的综测信息
        this.getList()
    }
}
</script>

<style lang="less" scoped>
.compAssBegin {
    height: 100%;

    .compAssBegin-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .common-table {
        height: 90%;
        position: relative;

        .pager {
            position: absolute;
            right: 20px;
            bottom: 0;
        }
    }
}
</style>