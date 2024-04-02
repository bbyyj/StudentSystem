<template>
    <div class="student-application" style="height: calc(100vh - 160px) !important">
        <!-- 学生信息 -->
        <el-card class="box-card" shadow="never">

            <div slot="header" class="clearfix">
                <span>学生申请信息</span>
            </div>

            <el-form inline label-position="top" class="student-info-form" :model="application" :disabled="disabled"
                :rules="applicationRules" ref="applicationForm">

                <el-form-item label="学号" prop="studentID">
                    <el-input v-model="application.studentID"></el-input>
                </el-form-item>

                <el-form-item label="姓名" prop="studentName">
                    <el-input v-model="application.studentName"></el-input>
                </el-form-item>

                <el-form-item label="分值（自动计算）" prop="score">
                    <el-input v-model.number="application.score" disabled></el-input>
                </el-form-item>

            </el-form>
        </el-card>

        <!-- 添加条目按钮 -->
        <el-button type="primary" @click="drawerVisible = true">添加综测信息</el-button>

        <!-- 条目列表表格 -->
        <el-table :row-class-name="rowClassName" :data="application.list" style="width: 100%">
            <el-table-column prop="time" label="时间"></el-table-column>
            <el-table-column prop="category1" label="综测大类别"></el-table-column>
            <el-table-column prop="category2" label="加分条件"></el-table-column>
            <el-table-column prop="remark" label="详细说明"></el-table-column>

            <el-table-column prop="singleScore" label="分值">
                <!-- <template #default="scope"> -->
                <!-- {{ scope.row.item && scope.row.item.score }}</template> -->
            </el-table-column>

            <el-table-column prop="fileUrl" label="附件">
            </el-table-column>

            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
                </template>
            </el-table-column>

        </el-table>

        <!-- 提交申请按钮 -->
        <el-row type="flex" class="row-bg" justify="end" style="margin-top: 20px">
            <el-col :span="3">
                <el-button type="success" @click="submitApplication">{{
                disabled ? "撤销申请" : "提交申请"
            }}</el-button>
            </el-col>
        </el-row>


        <!-- 抽屉组件用于添加新条目 -->
        <el-drawer title="添加综测信息" :visible.sync="drawerVisible" direction="rtl" size="30%"  @close="handleDrawerClose">
            <p>请完整填写综测信息，便于管理员进行审查</p>
            <el-form :model="newItem" :disabled="disabled" :rules="rules" ref="newItemForm">
                <el-form-item label="时间" prop="time">
                    <el-date-picker v-model="newItem.time"></el-date-picker>
                </el-form-item>

                <el-form-item label="综测类别" prop="category1">
                    <el-select v-model="newItem.category1">
                        <el-option v-for="item1 in RootCategory" :key="item1.id" :value="item1.value"
                            :label="item1.label"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="加分条件" prop="category2">
                    <el-select v-model="newItem.category2">
                        <el-option v-for="item2 in ChildrenCategory" :key="item2.id" :value="item2.value"
                            :label="item2.label"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="详细说明" prop="remark">
                    <el-input v-model="newItem.remark"></el-input>
                </el-form-item>

                <el-form-item label="分值" prop="singleScore">
                    <el-input v-model="newItem.singleScore" disabled></el-input>
                </el-form-item>

                <el-form-item label="证明附件">
                    <custom-upload v-model="newItem.fileUrl"
                        :accepted-types="['application/pdf', 'image/jpeg', 'image/png']"></custom-upload>
                </el-form-item>

            </el-form>
            <div class="drawer-footer">
                <el-button @click="drawerVisible = false; isEditing = false;">取消</el-button>
                <el-button v-if="!disabled" type="primary" @click="addItem">
                    {{ isEditing ? '修改' : '添加' }}
                </el-button>
            </div>
        </el-drawer>
    </div>
</template>

<script>
import customUpload from "@/components/upload";
export default {
    components: { customUpload },
    data() {
        return {
            disabled: false, // 添加综测按钮是否可用
            isEditing:false, // 编辑模式
            // 表头数据
            application: {
                studentName: "乔羿童", // 姓名
                studentID: "21311111", // 学号
                score: "",  // 综测分数——设置为自动计算
                list: [
                    {
                        id:1,
                        time: "2023-10-01", // TIME aaaa-bb-cc -format
                        category1: "社会工作类", // CATEGORY1
                        category2: "院团委副书记", // CATEGORY2
                        remark: "软件工程学院", // REMARK
                        singleScore: 1.5, // score
                        fileUrl: "", // 附件url
                    },
                    {
                        id:2,
                        time: "2023-10-02", // TIME aaaa-bb-cc -format
                        category1: "政治思想道德类", // CATEGORY1
                        category2: "全国三好学生", // CATEGORY2
                        remark: "无", // REMARK
                        singleScore: 1.5, // score
                        fileUrl: "", // 附件url
                    }
                ], // 申请的综测列表
            },
            // 新填写的综测信息
            newItem: {
                id: "",
                time: "", // TIME
                category1: "", // CATEGORY1
                category2: "", // CATEGORY2
                remark: "", // REMARK
                singleScore: "", // score
                fileUrl: "", // 附件url
            },
            // 综测大类别信息——后端返回
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
                }
            ],
            // 综测加分条件——后端返回
            ChildrenCategory: [
                {
                    id: 1,
                    label: "院团委副书记",
                    value: "院团委副书记"
                },
                {
                    id: 2,
                    label: "全国三好学生",
                    value: "全国三好学生"
                }],
            // 抽屉是否显示bool
            drawerVisible: false,

            // 新增综测信息的校验规则
            rules: {
                time: [{ required: true, message: "请选择时间", trigger: "blur" }],
                category1: [{ required: true, message: "请选择综测大类别", trigger: ['blur', 'change'] }],
                category2: [{ required: true, message: "请选择加分条件", trigger: ['blur', 'change'] }],
                remark: [{ required: true, message: "请输入详细说明", trigger: "blur" }],
            },

            // 整体表头信息的校验规则
            applicationRules: {
                studentID: [{ required: true, message: "请输入学号", trigger: "blur" }],
                studentName: [{ required: true, message: "请输入姓名", trigger: "blur" }],
            },
        };
    },
    computed: {
        disabledIdList() {
            return this.application.list.map((item) => item.item.id);
        },
    },
    methods: {
        // 获取综测大类别--后端接口
        getRootCategory() {
            this.RootCategory = [
                // todo
            ]
        },

        // 获取每个综测大类别下的加分条件--后端接口
        getChildrenCategory() {
            this.childrenCategory = [
                // todo
            ]
        },

        // 获取两个类别定位下的综测分数——后端接口
        getSingleScore() {
            // todo
        },
        rowClassName({ row }) {
            if (row.status === false) {
                return "row-error";
            }
            return "";
        },
        // 抽屉的添加按钮
        addItem() {
            if (this.disabled) return;
            this.$refs.newItemForm.validate((valid) => {
                if (valid) {
                    if (this.newItem.item === null) {
                        return false;
                    }
                    if (this.newItem.id) {
                        // 更新已存在的条目
                        this.$set(this.application.list, this.newItem.id, {
                            ...this.newItem,
                        });
                    } else {
                        // 添加新条目到 application.list
                        this.application.list.push({
                            ...this.newItem,
                            id: new Date().getTime(),
                        });
                    }
                    this.resetNewItem(); // 清空每一项
                    this.handleScore();  // 添加完该项之后 计算分数
                    this.drawerVisible = false; // 关闭抽屉
                    this.isEditing = false; // 关闭编辑模式
                } else {
                    console.log("表单校验未通过");
                    return false;
                }
            });
        },
        // 自动计算分值
        handleScore() {
            // 处理总分
            this.application.score = this.application.list.reduce((prev, item) => {
                return prev + item.singleScore;
            }, 0);
        },
        resetNewItem() {
            // 重置newItem
            this.newItem = { time: "", category1: "", category2: "", remark: "", singleScore: null };
        },
        // 点击提交申请的逻辑
        submitApplication() {
            if (this.disabled) {
                console.log("撤销申请");
                return;
            }
            this.$refs.applicationForm.validate((valid) => {
                if (valid) {
                    if (this.application.list.length === 0) return false;
                    // 进行提交申请的逻辑
                    this.application = { name: "", score: null, title: "", list: [] };
                } else {
                    console.log("表单校验未通过");
                    return false;
                }
            });
        },
        // 处理每个条目右侧的编辑按钮
        handleEdit(item) {
            this.newItem = { ...item };  // 数据绑定
            this.drawerVisible = true; // 显示抽屉
            this.isEditing = true; // 抽屉中是编辑按钮
        },
        // 处理按钮
        handleDrawerClose() {
            this.isEditing = false; // 重置编辑状态
        },
    },
    mounted() {
        // 初始渲染
        // this.getRootCategory();
        // this.getChildrenCategory();

        // 加载出数据之后就进行分数计算
        this.handleScore();
    },
};
</script>

<style>
.drawer-footer {
    text-align: right;
    margin-top: 20px;
}

.el-drawer {
    padding: 20px;
}

.row-error {
    background-color: red !important;
}
</style>