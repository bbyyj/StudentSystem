<template>
    <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
        :collapse="isCollapse" text-color="#fff" active-text-color="#ffd04b"
        style="background-color: var(--left-menu-color)">
        <h3>{{ isCollapse ? "后台" : "通用后台管理系统" }}</h3>
        <el-menu-item @click="clickItem(item)" v-for="item in noChildren" :key="item.name" :index="item.name">
            <i :class="`el-icon-${item.icon}`"></i>
            <span slot="title">{{ item.label }}</span>
        </el-menu-item>
        <el-submenu v-for="item in hasChildren" :key="item.label" :index="item.label">
            <template slot="title">
                <i :class="`el-icon-${item.icon}`"></i>
                <span slot="title">{{ item.label }}</span>
            </template>
            <el-menu-item-group v-for="subItem in item.children" :key="subItem.name">
                <el-menu-item @click="clickItem(subItem)" :index="subItem.name">{{ subItem.label }}</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
    </el-menu>
</template>

<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}

.el-menu {
    height: 100vh;
    border-right: none;
    background-color: var(--left-menu-color) !important;

    h3 {
        text-align: center;
        line-height: 48px;
        color: #fff;
        font-size: 16px;
        font-weight: 400;
    }
}

.el-menu-item,
.el-submenu__title,
.el-menu-item-group {
    background-color: var(--left-menu-color) !important;
}

.el-menu-item i,
.el-submenu__title i {
    color: #fff;
}

.el-menu-item.is-active i {
    color: inherit;
}

.el-menu-item:focus,
.el-menu-item:hover,
.el-submenu__title:hover,
.el-menu-item-group:hover {
    background-color: var(--main-color) !important;
}

.el-menu-item-group {
    margin: 0 !important;
    padding: 0 !important;
}
</style>

<script>
import cookie from 'js-cookie'
export default {
    data() {
        return {};
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        clickItem(item) {
            if (this.$route.path !== item.path && !(this.$route.path === '/home' && (item.path === '/'))) {
                this.$router.push(item.path);
            }
            this.$store.commit('SelectMenu', item);
        }
    },
    computed: {
        noChildren() {
            return this.MenuData.filter(item => !item.children);
        },
        hasChildren() {
            return this.MenuData.filter(item => item.children);
        },
        isCollapse() {
            return this.$store.state.tab.isCollapse;
        },
        MenuData() {
            return JSON.parse(cookie.get('menu')) || this.$store.state.tab.menu;
        }
    }
}
</script>
