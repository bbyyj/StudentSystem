<template>
    <el-card class="ftype">
        <h1>基本信息</h1>

        <div class="wrapper" style="display: flex; flex-wrap: wrap; justify-content: space-between;">
        <div v-for="(item, index) in items" :key="index" class="blockitem" style="width: 30%; margin-bottom: 15px;">
            <el-input class="elinput" :v-model="item.model" :disabled="true">
            <label slot="prepend">{{ item.label }}</label>
            </el-input>
        </div></div>
           
        <hr>
        <h1>联系方式</h1>

    </el-card>
</template>

<script>

export default {
    data() {
        return {
            items: [
                { model: 'info.name', label: '姓名：' },
                { model: 'info.type', label: '学生类别：' },
                { model: 'info.sex', label: '性别：' },
                { model: 'info.nation', label: '民族：' },
                { model: 'info.sid', label: '学号：' },
                { model: 'info.id', label: '身份证号：' },
                { model: 'info.birthday', label: '出生年月：' },
                { model: 'info.class', label: '班级：' },
                { model: 'info.categories', label: '培养层次：' },
            ],
            info: {
                name: '张三',
                type: '本科生',
                sex: '女',
                nation: '',
                sid: '',
                id: '',
                birthday: '',
                class: '',
                categories: '',
            }
        }
    },
    methods: {
        
    }

}
</script>

<style>
.wrapper {
  width: 100%;
}

.elinput{
    max-block-size: 10px;
}
.ftype{
    font-size: small;
}


.blockitem{
    height: 100px;
    width: 300px;

}

</style>