<template>
  <div v-if="role === 'NOT_MONITOR'">
    <student-home></student-home>
  </div>
  <div v-else>
    <admin-home></admin-home>
  </div>
</template>

<script>
import Cookies from 'js-cookie';
import adminHome from '@/Views/adminHome.vue';
import studentHome from '@/Views/studentHome.vue';
export default {
  components: {
    adminHome,
    studentHome,
  },
  data() {
    return {
      role: Cookies.get('Role'),
    }
  },
}
</script>

<style scoped>

</style>