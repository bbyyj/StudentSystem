<template>
    <div>
        <el-card>
            <el-form style="margin-top: 20px;">
                <el-form-item v-if="showRules" required label="综测大类" class="item">
                    <el-select v-model="formData.RuleType" @change="loadRuleDetails">
                        <el-option
                            v-for="item in rulestype"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item v-if="showRules" required label="综测细则" class="item">
                    <el-select v-model="formData.RuleDetail">
                    <el-option
                        v-for="item in rulesdetail"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item v-for="field in currentData.items" :rules="formRules[field.fieldName]" :key="field.fieldName" :label="field.label" class="item">
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
                <el-form-item label="证明附件" class="item" required>
                    <custom-upload v-model="formData.fileUrl"
                        :accepted-types="['application/pdf', 'image/jpeg', 'image/png']"></custom-upload>
                </el-form-item>
            </el-form>
            <div class="footer-button item">
                <el-button type="primary">提交</el-button>
            </div>
        </el-card>
    </div>
</template>

<script>
import customUpload from "@/components/upload";
import InfoSubmissionsData from '@/data/InfoSubmissionsData.js';

export default {
    components: { customUpload },
    data() {
        return {
            //存储当前填写的表单数据
            formData: {},
            //当前页面使用的字段数据（比赛、论文等表单的字段）
            currentData: {},
            //表单校验规则
            formRules: {},
            //综测大类、综测细则
            rulestype:[
                    { 
                        value: 'option1', 
                        label: '思想道德',
                        details:[
                            { value: 'optionA', label: 'xxx' },
                            { value: 'optionB', label: 'xxxx' }
                        ] 
                    },
                    { 
                        value: 'option2', 
                        label: '竞赛',
                        details:[
                            { value: 'optionA', label: '国家级' },
                            { value: 'optionB', label: '省部级' },
                        ] 
                    }
            ],
            //当前选择的综测大类对应的综测细则
            rulesdetail: {},
            //是否显示综测大类、综测细则选项
            showRules: true
        };
    },
    created() {
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
                if (this.$route.name === 'CompetitionSubmission'){
                    this.currentData = InfoSubmissionsData[0];
                    this.showRules = true;
                }
                else if (this.$route.name === 'PaperSubmission'){
                    this.currentData = InfoSubmissionsData[1];
                    this.showRules = true;
                }
                else if (this.$route.name === 'PatentSubmission'){
                    this.currentData = InfoSubmissionsData[2];
                    this.showRules = true;
                }
                else if (this.$route.name === 'SoftwareSubmission'){
                    this.currentData = InfoSubmissionsData[3];
                    this.showRules = true;
                }
                else if (this.$route.name === 'MonographSubmission'){
                    this.currentData = InfoSubmissionsData[4];
                    this.showRules = true;
                }
                else if (this.$route.name === 'ExchangeSubmission'){
                    this.currentData = InfoSubmissionsData[5];
                    this.showRules = false;
                }
                else if (this.$route.name === 'VolunteerismSubmission'){
                    this.currentData = InfoSubmissionsData[6];
                    this.showRules = true;
                }
                    
            }
            if (this.currentData && this.currentData.rules) {
                this.formRules = this.currentData.rules;
            }
            // 清空表单数据
            this.formData = {};
        },
        // 加载细则列表
        loadRuleDetails(selectedType) {
            const selectedCategory = this.rulestype.find(category => category.value === selectedType);
            if (selectedCategory) {
                this.rulesdetail = selectedCategory.details;
            } else {
                // 若未找到对应细则列表，可以设置为空数组或处理错误情况
                this.rulesdetail = [];
            }
        }
        
    }
}
</script>




<style scoped>
.footer-button {
    /* text-align: right; */
    margin-top: 20px;
}
.item {
    margin-left: 100px;
    width: 600px;
}

</style>

