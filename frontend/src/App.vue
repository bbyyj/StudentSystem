<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
}
</script>

<style lang="less">
html,
body,
h3,
p {
  margin: 0;
  padding: 0
}

.el-tag--dark {
  background-color: var(--main-color) !important;
  border-color: var(--main-color) !important;
}

.el-tag--plain {
  color: var(--main-color) !important;
  border-color: var(--main-color) !important;
}
</style>