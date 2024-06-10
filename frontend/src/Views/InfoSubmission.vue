<template>
    <div>
        <el-card>
            <el-form style="margin-top: 20px;" :model="ruleData" :rules="rulesValid" ref="ruleData">
                <el-form-item v-if="showRules" required label="综测大类" class="item">
                    <el-select v-model="formData.RuleType" @change="loadRuleDetails">
                        <el-option
                            v-for="item in rulestype"
                            :key="item"
                            :label="item"
                            :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item v-if="showRules" required label="综测细则" class="item">
                    <el-select v-model="formData.RuleDetail">
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
                <el-form-item v-for="field in currentData.items" :prop="field.fieldName"  :key="field.fieldName" :label="field.label" class="item">
                    <template v-if="field.controlType === 'input'">
                        <el-input :id="field.fieldName" :name="field.fieldName" v-model="formData[field.fieldName]" :placeholder="field.message"></el-input>
                    </template>
                    <template v-else-if="field.controlType === 'datepicker'">
                        <el-date-picker :id="field.fieldName" :name="field.fieldName" v-model="formData[field.fieldName]"></el-date-picker>
                    </template>
                    <template v-else-if="field.controlType === 'select'">
                        <el-select :id="field.fieldName" :name="field.fieldName" v-model="formData[field.fieldName]">
                            <el-option v-for="item in currentData.selectOptions[field.fieldName]" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </template>
                    <template v-else>
                        <span style="color: red;">Unsupported form control type: {{ field.controlType }}</span>
                    </template>
                </el-form-item>

                <el-form-item label="证明附件" class="item" prop="file">
                    <custom-upload v-model="formData.fileUrl"
                        :accepted-types="['application/pdf', 'image/jpeg', 'image/png']"></custom-upload>
                </el-form-item>

                <el-form-item class="item">
                    <el-button type="primary" @click="submitInfo('formData')">提交</el-button>
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
                rulestype: [
                    { required: true, message: '请选择综测大类', trigger: 'change' }
                ],
                rulesdetail: [
                    { required: true, message: '请选择综测细则', trigger: 'change' }
                ],
            },
            rulestype:{},
            //当前选择的综测大类对应的综测细则
            rulesdetail: {},
            //是否显示综测大类、综测细则选项
            showRules: true,
            // 上传对应的接口
            uploadUrl: '',
        };
    },
    created() {
        this.setup();
        this.getRuleTypes();
    },
    watch: {
        $route(to, from) {
            this.setup();
        },
    },
    methods: {
        setup() {
            if (InfoSubmissionsData.length > 0) {
                if (this.$route.name === 'CompetitionSubmission'){
                    this.currentData = InfoSubmissionsData[0];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/161930184';
                    this.showRules = true;
                }
                else if (this.$route.name === 'PaperSubmission'){
                    this.currentData = InfoSubmissionsData[1];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/161930238';
                    this.showRules = true;
                }
                else if (this.$route.name === 'PatentSubmission'){
                    this.currentData = InfoSubmissionsData[2];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/161930484';
                    this.showRules = true;
                }
                else if (this.$route.name === 'SoftwareSubmission'){
                    this.currentData = InfoSubmissionsData[3];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/161927607';
                    this.showRules = true;
                }
                else if (this.$route.name === 'MonographSubmission'){
                    this.currentData = InfoSubmissionsData[4];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/161927618';
                    this.showRules = true;
                }
                else if (this.$route.name === 'ExchangeSubmission'){
                    this.currentData = InfoSubmissionsData[5];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/161927550';
                    this.showRules = false;
                }
                else if (this.$route.name === 'VolunteerismSubmission'){
                    this.currentData = InfoSubmissionsData[6];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/161930626';
                    this.showRules = true;
                }
                else if (this.$route.name === 'OtherSubmission'){
                    this.currentData = InfoSubmissionsData[7];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/170634389';
                    this.showRules = true;
                }
                else if (this.$route.name === 'CommunitySubmission'){
                    this.currentData = InfoSubmissionsData[8];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/171122131';
                    this.showRules = true;
                }
                else if (this.$route.name === 'IdeologicalSubmission'){
                    this.currentData = InfoSubmissionsData[8];
                    this.uploadUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/171122994';
                    this.showRules = true;
                }
                    
            }
            if (this.currentData && this.currentData.rules) {
                this.formRules = this.currentData.rules;
            }
            // 清空表单数据
            this.formData = {};
        },
        // 加载综测类型列表
        getRuleTypes() {
            const apiUrl = 'https://mock.apifox.com/m2/4212159-3852880-default/162417372';

            axios.get(apiUrl) 
                .then(response => {
                const data = response.data;
                const ruleList = data.data.content;

                this.rulesContent = ruleList;

                // 提取出综测类型
                const uniqueTypesSet = new Set(this.rulesContent.map(item => item.type));
                this.rulestype = Array.from(uniqueTypesSet);
                console.log(this.rulestype);
                
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

        submitInfo(formName) {
            console.log(this.formData);
            this.$refs[formName].validate((valid) => {
            if (valid) {
                alert('submit!');
            } else {
                console.log('error submit!!');
                return false;
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

