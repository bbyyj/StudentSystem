<template>
    <div>
        <el-card>
            <el-form style="margin-top: 20px;">
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
            formData: {},
            currentData: {},
            formRules: {},
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
                if (this.$route.name === 'CompetitionSubmission')
                    this.currentData = InfoSubmissionsData[0];
                else if (this.$route.name === 'PaperSubmission')
                    this.currentData = InfoSubmissionsData[1];
                else if (this.$route.name === 'PatentSubmission')
                    this.currentData = InfoSubmissionsData[2];
                else if (this.$route.name === 'SoftwareSubmission')
                    this.currentData = InfoSubmissionsData[3];
                else if (this.$route.name === 'MonographSubmission')
                    this.currentData = InfoSubmissionsData[4];
                else if (this.$route.name === 'ExchangeSubmission')
                    this.currentData = InfoSubmissionsData[5];
                else if (this.$route.name === 'VolunteerismSubmission')
                    this.currentData = InfoSubmissionsData[6];
            }
            if (this.currentData && this.currentData.rules) {
                this.formRules = this.currentData.rules;
            }
        },
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
    width: 700px;
}
</style>

