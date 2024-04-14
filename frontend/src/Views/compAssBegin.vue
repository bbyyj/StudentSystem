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
                    <el-form-item label="综测名称" prop="compAssName">
                        <el-input placeholder="请输入综测名称" v-model="form.compAssName"></el-input>
                    </el-form-item>

                    <el-form-item label="开始时间">
                        <el-form-item prop="compAssBeginTime">
                            <el-date-picker type="date" placeholder="请选择综测开始时间" v-model="form.compAssBeginTime" value-format="yyyy-MM-DD">
                            </el-date-picker>
                        </el-form-item>
                    </el-form-item>

                    <el-form-item label="结束时间">
                        <el-form-item prop="compAssEndTime">
                            <el-date-picker type="date" placeholder="请选择综测结束时间" v-model="form.compAssEndTime" value-format="yyyy-MM-DD">
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
                    <el-input v-model="searchForm.name" placeholder="请输入名称"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="search">查询</el-button>
                </el-form-item>
            </el-form>
        </div>

        <div class="common-table">
            <!-- 用户数据Table -->
            <el-table :data="tableData" stripe style="width: 100%" height="90%">
                <el-table-column prop="compAssName" label="综测名称">
                </el-table-column>
                <el-table-column prop="compAssBeginTime" label="综测开始时间">
                </el-table-column>
                <el-table-column prop="compAssEndTime" label="综测结束时间">
                </el-table-column>


                <!-- 自定义列 -->
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button type="primary" @click="gotoReview(scope.row)">查看</el-button>
                        <el-button @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>

            </el-table>
            <!-- 分页 -->
            <!-- <div class="pager">
                <el-pagination layout="prev, pager, next" :total="total" @current-change="currentChange">
                </el-pagination>
            </div> -->

        </div>
    </div>
</template>

<script>
import { createUser, deleteUser, updateUser } from '../api/index'
import { getAllCompAssBegin, addCompAssBegin , deleteCompAssBegin , editCompAssBegin } from '../api/index'


export default {
    data() {
        return {
            // 表单绑定的数据
            form: {
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
            tableData: [{
                compAssID:1,
                compAssName: '2024年综测',
                compAssBeginTime: '2024-10-01',
                compAssEndTime: '2024-10-20',
            },
                {
                    compAssID:2,
                    compAssName: '2023年综测',
                    compAssBeginTime: '2023-10-01',
                    compAssEndTime: '2023-10-20',
                },
            ],
            // 打开表单:新建0,编辑1
            modalType: 0,
            // 分页的对象
            pageData: {
                page: 1,
                limit: 20
            },
            // 分页页数
            total: 0,
            // 搜索框表单
            searchForm: {
                compAssName: ''
            }
        }
    },
    methods: {
        // 获取列表数据
        getList() {
            // 由接口文档知传入一个对象:要返回的是当前页面数据和搜索到的数据的交集
            // getUser({ params: { ...this.pageData, ...this.searchForm } }).then((data) => {
            //     // this.tableData = data.data.list
            //     // this.total = data.data.count || 0
            // })
        },
        // 表单提交
        submit() {
            // 要用箭头函数,若用function会报错,不知道为什么
            this.$refs.form.validate((valid) => {
                // 符合校验
                if (valid) {
                    // 提交数据
                    if (this.modalType === 0) {
                        // 新增
                        addCompAssBegin(this.form).then(() => {
                            console.log("新增综测")
                            // 重新进行请求新的内容
                            this.getList()
                        })
                    } else {
                        // 编辑
                        editCompAssBegin(this.form).then(() => {
                            // 重新进行请求新的内容
                            this.getList()
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
        handleEdit(index) {
            this.modalType = 1
            this.openForm()
            // 深拷贝
            this.form = JSON.parse(JSON.stringify(index))
        },
        // 删除按钮
        handleDelete(index) {
            this.$confirm('此操作将永久删除当下全部综测记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // 删除操作:根据后端接口,参数是对象,id是唯一标识符
                deleteCompAssBegin({ id: index.id }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    })
                    this.getList()
                });
            }).catch(() => {
                // 点击取消:不删除了
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        // 新建按钮
        handlecreate() {
            this.modalType = 0
            this.openForm()
        },
        // 打开表单
        openForm() {
            this.dialogVisible = true
        },
        // 改变页码
        currentChange(val) {
            this.pageData.page = val
            this.getList()
        },
        // 搜索
        search() {
            this.getList()
        },
        // 跳转到综测审核页面
        gotoReview(row){
            this.$router.push({ path: `/compAssReview/${row.compAssID}/${row.compAssName}`});
        }

    },
    mounted() {
        // 挂载时获取当前页面的综测信息
        this.getList()
        getAllCompAssBegin();
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
            right:20px;
            bottom: 0;
        }
    }
}
</style>