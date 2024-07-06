<template>
    <div class="header-container">
        <div class="l-content">
            <el-button @click="handleMenu" icon="el-icon-menu" size="mini"></el-button>
            <!-- 面包屑 -->
<!--            <el-breadcrumb separator="/">-->
<!--                <el-breadcrumb-item v-for="item in tags" :key="item.path" :to="{ path: item.path }">{{ item.label }}-->
<!--                </el-breadcrumb-item>-->
<!--            </el-breadcrumb>-->
        </div>
        <div class="r-content">
            <!--            <el-dropdown @command="handleClick">-->
            <!--                <span class="el-dropdown-link">-->
            <!--                    <img class="user" src="../assets/images/admin.jpeg" alt="">-->

            <!--                </span>-->
            <!--                <el-dropdown-menu slot="dropdown">-->
            <!--                    <el-dropdown-item>个人信息</el-dropdown-item>-->
            <!--                    <el-dropdown-item command="logout">退出</el-dropdown-item>-->
            <!--                </el-dropdown-menu>-->
            <!--            </el-dropdown>-->

            <!-- <el-select v-model="theme" size="mini" style="width: 100px;margin-right: 20px;" @change="toggleTheme">
                <el-option value="black" label="暗黑"></el-option>
                <el-option value="blue" label="鲸蓝"></el-option>
                <el-option value="green" label="橄榄绿"></el-option>
                <el-option value="red" label="浅红"></el-option>
            </el-select> -->
            <el-button type="primary" size="mini" icon="el-icon-orange" @click="dialogVisible = true"></el-button>
            <el-button type="danger" class="custom-button" @click="handleClick" size="mini">退出</el-button>
        </div>

        <el-dialog title="选择主题" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
            <div class="color-box">
                <div class="color-item" :class="{ active: theme == item.name, [item.name]: true }" v-for="item in colorList"
                    :key="item.id" @click="confirm(item.name)">
                    <i class="el-icon-check" v-if="theme == item.name"></i>
                </div>
                <!-- <div class="color-item blue"></div>
                <div class="color-item green"></div>
                <div class="color-item red"></div> -->
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">关 闭</el-button>
                <!-- <el-button type="primary" @click="confirm">确 定</el-button> -->
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import Cookie from 'js-cookie'
export default {
    data() {
        return {
            colorList: [{
                id: 1,
                name: 'black'
            }, {
                id: 2,
                name: 'green'
            }, {
                id: 3,
                name: 'blue'
            }, {
                id: 4,
                name: 'red'
            }, ],
            dialogVisible: false,
            theme: 'black'
        }
    },
    watch: {
        theme() {
            this.toggleTheme()
        }
    },
    methods: {
        handleClose(done) {
            done();
        },
        handleMenu() {
            // 相当于调用这个方法
            this.$store.commit('CollapseMenu')
        },
        handleClick() {
            // if (command === 'logout') {
            //     Cookie.remove('token')
            //     this.$router.push('/login')
            // }
            Cookie.remove('token')
            this.$router.push('/login')
        },
        confirm(name) {
            this.theme = name
            this.toggleTheme()
        },
        //切换主题
        toggleTheme() {
            localStorage.setItem('theme', this.theme)
            //获取根元素并设置属性
            document.documentElement.setAttribute('data-theme', this.theme)
        }
    },
    computed: {
        ...mapState({
            tags: state => state.tab.tabList
        })
    },
    created() {
        console.log('header created', localStorage.getItem('theme'))
        this.theme = localStorage.getItem('theme') ? localStorage.getItem('theme') : 'black'
    }
}
</script>

<style lang="less" scoped>
.color-box {
    display: flex;
    flex-wrap: wrap;
}

.color-item {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 10px;
    margin-bottom: 10px;
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: bold;
    color: #fff;
    font-size: 20px;

    &.active {}

    &:hover {
        filter: brightness(150%)
    }
}

.black {
    background-color: #7F9FCA;
}

.green {
    background-color: #358f09;
}

.blue {
    background-color: #0000ff;
}

.red {
    background-color: #fa3f3f;
}

.header-container {
    background-color: var(--main-color); //#333;
    height: 60px;

    // 让按钮和头像居中
    display: flex;
    justify-content: space-between;
    align-items: center;
    // 不要紧贴边框
    padding: 0 20px;

    .el-dropdown-link {
        cursor: pointer;
        color: #409EFF;

        .user {
            width: 40px;
            height: 40px;
            // 50%变圆形
            border-radius: 50%;
        }
    }
}

.l-content {
    display: flex;
    // 上下居中
    align-items: center;

    .el-breadcrumb {
        margin-left: 15px;

        // deep 强制生效
        /deep/.el-breadcrumb__item {
            .el-breadcrumb__inner {
                &.is-link {
                    color: #666;
                }
            }

            &:last-child {
                .el-breadcrumb__inner {
                    color: #fff;
                }
            }
        }
    }
}

.custom-button {
    /* 自定义背景颜色 */
    border-radius: 10px;
    /* 圆角 */
    color: white;
    /* 字体颜色 */
    padding: 10px 20px;
    /* 内边距 */
    border: none;
    /* 去掉边框 */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    /* 阴影效果 */
    transition: background-color 0.3s, box-shadow 0.3s;
    /* 过渡效果 */
}

.custom-button:hover {
    background-color: var(--logout-color) !important;
    /* 悬停时的背景颜色 */
    box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15);
    /* 悬停时的阴影效果 */
}

.custom-button:active {
    background-color: var(--logout-color) !important;
    /* 点击时的背景颜色 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    /* 点击时的阴影效果 */
}
</style>