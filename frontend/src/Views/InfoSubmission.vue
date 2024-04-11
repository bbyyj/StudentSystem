<template>
    <div>
        <el-card>
            <el-form style="margin-top: 20px;">
                <el-form-item v-for="field in competition" :rules="rules[field.fieldName]" :key="field.fieldName" :label="field.label" class="item">
                    <template v-if="field.controlType === 'input'">
                        <el-input :id="field.fieldName" :name="field.fieldName" v-model="formData[field.fieldName]" :placeholder="field.message"></el-input>
                    </template>
                    <template v-else-if="field.controlType === 'datepicker'">
                        <el-date-picker :id="field.fieldName" :name="field.fieldName" v-model="formData[field.fieldName]"></el-date-picker>
                    </template>
                    <template v-else-if="field.controlType === 'select'">
                        <el-select :id="field.fieldName" :name="field.fieldName" v-model="formData[field.fieldName]">
                            <el-option v-for="item in selectOptions[field.fieldName]" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </template>
                    <template v-else>
                        <span style="color: red;">Unsupported form control type: {{ field.controlType }}</span>
                    </template>
                </el-form-item>
                <el-form-item label="证明附件" class="item" required="true">
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
export default {
    components: { customUpload },
    data(){
        return{
            competition: [
                { fieldName: "type", controlType: "select", label: "成果类别" },
                { fieldName: "time", controlType: "datepicker", label: "获得时间" },
                { fieldName: "competition_name", controlType: "input", label: "比赛名称", message:'请严格按照比赛通知、奖状信息填写，含比赛年份、届别、地区等信息' },
                { fieldName: "award_name", controlType: "input", label: "成果名称" },
                { fieldName: "result_name", controlType: "input", label: "获奖名称", message: '请严格按照奖状信息填写，注明地区、组别等信息' },
                { fieldName: "result_type", controlType: "select", label: "成果级别" },
                { fieldName: "result_level", controlType: "input", label: "成果等级", message: '学科竞赛、艺术比赛获奖等级指特等奖、一等奖、二等奖、三等奖、冠军、亚军、季军、金奖、银奖、铜奖；体育比赛获奖等级指第一名至第八名，冠军、金牌等同于第一名，亚军、银牌等同于第二名，季军、铜牌等同于第三名' },
                { fieldName: "organization", controlType: "input", label: "评奖组织单位", message: '请严格按照比赛通知、奖状信息填写' },
                { fieldName: "teammate", controlType: "input", label: "其他参与人员", message: '姓名+学院，没有就填“无”' }
            ],
            selectOptions: {
                type: [
                    { value: 'option1', label: '学科竞赛' },
                    { value: 'option2', label: '艺术比赛' },
                    { value: 'option3', label: '体育比赛' },
                    { value: 'option4', label: '其他' }
                ],
                result_type: [
                    { value: 'optionA', label: '国际级' },
                    { value: 'optionB', label: '国家级' },
                    { value: 'optionC', label: '省部级' },
                    { value: 'optionD', label: '校级' },
                ],
            },
            rules: {
                type: [
                    { required: true, message: '请选择成果类别', trigger: 'change' }
                ],
                time: [
                    { required: true, message: '请选择获得时间', trigger: 'change' }
                ],
                competition_name: [
                    { required: true, message: '请填写比赛名称', trigger: 'change' }
                ],
                award_name: [
                    { required: false, message: '选填', trigger: 'change' }
                ],
                result_name: [
                    { required: true, message: '请填写获奖名称', trigger: 'change' }
                ],
                result_type: [
                    { required: true, message: '请选择成果类别', trigger: 'change' }
                ],
                result_level: [
                    { required: true, message: '请填写成果等级', trigger: 'change' }
                ],
                organization: [
                    { required: true, message: '请填写评奖组织单位', trigger: 'change' }
                ],
                teammate: [
                    { required: true, message: '请填写其他参与人员', trigger: 'change' }
                ]
            },

            formData: {},
        }
    },
    methods: {
       
    },
}
</script>

<style scoped>
.footer-button {
    /* text-align: right; */
    margin-top: 20px;
}
.item {
    margin-left: 100px;
    width: 700px;
}
</style>