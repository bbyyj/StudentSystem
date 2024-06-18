<template>
    <div class="header-container">
        <div class="l-content">
            <el-button @click="handleMenu" icon="el-icon-menu" size="mini"></el-button>
            <!-- 面包屑 -->
            <el-breadcrumb separator="/">
                <el-breadcrumb-item v-for="item in tags" :key="item.path" :to="{ path: item.path }">{{ item.label }}
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="r-content">
            <el-button type="danger" class="custom-button" @click="handleClick" size="mini">退出</el-button>
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import Cookie from 'js-cookie'
export default {
    methods: {
        handleMenu() {
            // 相当于调用这个方法
            this.$store.commit('CollapseMenu')
        },
        handleClick() {
            Cookie.remove('token')
            Cookie.remove('netId')
            Cookie.remove('Role')
            Cookie.remove('sid')
            this.$store.commit('setMenu', [])
            this.$router.push('/login')
                
        }
    },
    computed: {
        ...mapState({
            tags: state => state.tab.tabList
        })
    }
}
</script>

<style lang="less" scoped>
.header-container {
    background-color: #333;
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
  background-color: #ff4d4f; /* 自定义背景颜色 */
  border-radius: 10px; /* 圆角 */
  color: white; /* 字体颜色 */
  padding: 10px 20px; /* 内边距 */
  border: none; /* 去掉边框 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  transition: background-color 0.3s, box-shadow 0.3s; /* 过渡效果 */
}

.custom-button:hover {
  background-color: #ff7875; /* 悬停时的背景颜色 */
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15); /* 悬停时的阴影效果 */
}

.custom-button:active {
  background-color: #d9363e; /* 点击时的背景颜色 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* 点击时的阴影效果 */
}

</style>