<template>
    <div>
        <el-card>
            <el-form style="margin-top: 20px;" :model="ruleData" :rules="rulesValid" ref="ruleData">
                <el-form-item v-if="showRules" prop="rule_type" required label="综测大类" class="item">
                    <el-select v-model="ruleData.rule_type" @change="loadRuleDetails">
                        <el-option 
                            v-for="item in rulestype" 
                            :key="item" 
                            :label="item" 
                            :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item v-if="showRules" prop="rule_detail" required label="综测细则" class="item">
                    <el-select v-model="ruleData.rule_detail">
                        <el-option 
                            v-for="item in rulesdetail" 
                            :key="item.detail" 
                            :label="item.detail" 
                            :value="item.detail">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>

            <el-form style="margin-top: 20px;" :model="formData" :rules="formRules" ref="formData">
                <el-form-item v-for="field in currentData.items" 
                            :prop="field.fieldName"  
                            :key="field.fieldName" 
                            :label="field.label" 
                            class="item">
                    <template v-if="field.controlType === 'input'">
                        <el-input :id="field.fieldName" 
                                :name="field.fieldName" 
                                v-model="formData[field.fieldName]" 
                                :placeholder="field.message"></el-input>
                    </template>
                    <template v-else-if="field.controlType === 'datepicker'">
                        <el-date-picker :id="field.fieldName" 
                                        :name="field.fieldName" 
                                        v-model="formData[field.fieldName]"></el-date-picker>
                    </template>
                    <template v-else-if="field.controlType === 'select'">
                        <el-select :id="field.fieldName" 
                                :name="field.fieldName" 
                                v-model="formData[field.fieldName]">
                            <el-option v-for="item in currentData.selectOptions[field.fieldName]" 
                                    :key="item.value" 
                                    :label="item.label" 
                                    :value="item.value"></el-option>
                        </el-select>
                    </template>
                    <template v-else>
                        <span style="color: red;">Unsupported form control type: {{ field.controlType }}</span>
                    </template>
                </el-form-item>

                <el-form-item label="证明附件" class="item">
                    <el-upload
                    class="upload-demo"
                    action="#"
                    :on-change="handleFileChange"
                    :auto-upload="false"
                    :file-list="fileList"
                    :before-upload="beforeUpload">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过3MB</div>
                    </el-upload>
                </el-form-item>

                <el-form-item class="item">
                    <el-button type="primary" @click="submitInfo">提交</el-button>
                </el-form-item>

            </el-form>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios';
import customUpload from "@/components/upload";
import InfoSubmissionsData from '@/data/InfoSubmissionsData.js';

export default {
    components: { customUpload },
    data() {
        return {
            //存储当前填写的表单数据
            formData: {},
            ruleData: {},
            //当前页面使用的字段数据（比赛、论文等表单的字段）
            currentData: {},
            //当前页面使用的接口需要的字段
            formDataKey: {},
            //表单校验规则
            formRules: {},
            //综测大类、综测细则
            rulesContent:[
                {
                    "detail": "全国三好学生",
                    "score": 1.5,
                    "type": "政治思想道德类",
                    "rid": 1
                },
                {
                    "score": 1.5,
                    "type": "政治思想道德类",
                    "detail": "国家级优秀学生干部",
                    "rid": 2
                },
                {
                    "score": 1.5,
                    "type": "政治思想道德类",
                    "detail": "国家级优秀团干部",
                    "rid": 3
                }
            ],
            rulesValid: {
                rule_type: [
                    { required: true, message: '请选择综测大类', trigger: 'change' }
                ],
                rule_detail: [
                    { required: true, message: '请选择综测细则', trigger: 'change' }
                ],
            },
            rulestype:{},
            //当前选择的综测大类对应的综测细则
            rulesdetail: {},
            //是否显示综测大类、综测细则选项
            showRules: true,
            // 文件列表
            fileList: [],
            uploadUrl: '',
        };
    },
    created() {
        this.getRuleTypes();
    },
    mounted() {
        this.setup();
    },
    watch: {
        $route(to, from) {
            this.setup();
        },
    },
    methods: {
        setup() {
            if (InfoSubmissionsData.length > 0) {
                switch (this.$route.name) {
                    case 'CompetitionSubmission':
                        this.currentData = InfoSubmissionsData[0];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/Competition';
                        this.showRules = true;
                        this.formDataKey = 'competitionJson';
                        break;
                    case 'PaperSubmission':
                        this.currentData = InfoSubmissionsData[1];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/Paper';
                        this.showRules = true;
                        this.formDataKey = 'paperJson';
                        break;
                    case 'PatentSubmission':
                        this.currentData = InfoSubmissionsData[2];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/Patent';
                        this.showRules = true;
                        this.formDataKey = 'patentJson';
                        break;
                    case 'SoftwareSubmission':
                        this.currentData = InfoSubmissionsData[3];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/Software';
                        this.showRules = true;
                        this.formDataKey = 'softwareJson';
                        break;
                    case 'MonographSubmission':
                        this.currentData = InfoSubmissionsData[4];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/Monograph';
                        this.showRules = true;
                        this.formDataKey = 'monographJson';
                        break;
                    case 'ExchangeSubmission':
                        this.currentData = InfoSubmissionsData[5];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/ExchangeActivity';
                        this.showRules = false;
                        this.formDataKey = 'exchangeActivityJson';
                        break;
                    case 'VolunteerismSubmission':
                        this.currentData = InfoSubmissionsData[6];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/Volunteer';
                        this.showRules = true;
                        this.formDataKey = 'volunteerJson';
                        break;
                    case 'OtherSubmission':
                        this.currentData = InfoSubmissionsData[7];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/Others';
                        this.showRules = true;
                        this.formDataKey = 'othersJson';
                        break;
                    case 'CommunitySubmission':
                        this.currentData = InfoSubmissionsData[8];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/SocialWork';
                        this.showRules = true;
                        this.formDataKey = 'socialWorkJson';
                        break;
                    case 'IdeologicalSubmission':
                        this.currentData = InfoSubmissionsData[8];
                        this.uploadUrl = 'http://127.0.0.1:8080/upload/IdeologyMorality';
                        this.showRules = true;
                        this.formDataKey = 'ideologyMoralityJson';
                        break;
                    default:
                        break;
                }
            }

            if (this.currentData && this.currentData.rules) {
                this.formRules = this.currentData.rules;
            }

            // 清空表单数据和已触发的验证提示
            this.$refs.ruleData.resetFields();
            this.$refs.formData.resetFields();
            this.ruleData = {};
            this.formData = {};

            this.$nextTick(() => {
                this.$refs.ruleData.clearValidate();
                this.$refs.formData.clearValidate();
            });
        },
        // 加载综测类型列表
        getRuleTypes() {
            //const apiUrl = 'http://127.0.0.1:8080/rule/detailManage/getAllRuleDetail';
            // 由于没有相关表格，暂时使用mock数据
            const apiUrl = 'https://apifoxmock.com/m2/4212159-3852880-default/162417372';

            axios.get(apiUrl) 
                .then(response => {
                const data = response.data;
                const ruleList = data.data.content;

                this.rulesContent = ruleList;

                // 提取出综测类型
                const uniqueTypesSet = new Set(this.rulesContent.map(item => item.type));
                this.rulestype = Array.from(uniqueTypesSet);
                
                })
                .catch(error => {
                console.error('There was a problem with your fetch operation:', error);
                });
            
        },

        // 加载细则列表
        loadRuleDetails(selectedType) {
            this.rulesdetail = this.rulesContent.filter(item => item.type === selectedType);
            console.log(this.rulesdetail)
        },

        handleFileChange(file, fileList) {
            this.fileList = fileList;
        },

        beforeUpload(file) {
            const isLt3M = file.size / 1024 / 1024 < 3;
            if (!isLt3M) {
                this.$message.error('上传文件大小不能超过 3MB!');
            }
            return isLt3M;
        },


        async submitInfo() {
            if (this.showRules) {
                this.$refs.ruleData.validate(async (ruleValid) => {
                    if (!ruleValid) {
                        this.$message.error('请检查综测大类和综测细则是否符合要求');
                        return;
                    }
                    this.validateFormData();
                });
            } else {
                this.ruleData.rule_type = '';
                this.ruleData.rule_detail = '';
                this.validateFormData();
            }
        },
        async validateFormData() {
            this.$refs.formData.validate(async (valid) => {
                if (valid) {
                    if(this.fileList.length === 0){
                        this.$message.error('请上传文件');
                        return;
                    }
                    const formDataObject = {
                        [this.formDataKey]: JSON.stringify({
                            rule_type: this.ruleData.rule_type,
                            rule_detail: this.ruleData.rule_detail,
                            sid: '20311000',
                            // 添加其他表单字段
                            ...this.formData
                        })
                    };

                    // 创建 FormData 实例
                    const formData = new FormData();
                    // 添加动态键名的数据
                    formData.append(this.formDataKey, formDataObject[this.formDataKey]);
                    
                    // 添加文件
                    if (this.fileList.length > 0) {
                        formData.append('file', this.fileList[0].raw);
                    }

                    console.log(formData);

                    try {
                        const response = await axios.post(this.uploadUrl, formData, {
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        });
                        
                        if (response.status === 200) {
                            this.$message.success('提交成功！');
                            // 清空表单数据和文件列表
                            this.$refs.formData.resetFields();
                            this.fileList = [];
                        } else {
                            this.$message.error('提交失败，请稍后重试！');
                        }
                    } catch (error) {
                        this.$message.error('提交失败，请稍后重试！');
                    }
                } else {
                    this.$message.error('请检查输入信息是否符合要求');
                }
            });
        }



        
    }
}
</script>




<style scoped>
.item {
    margin-left: 100px;
    width: 600px;
}

</style>

