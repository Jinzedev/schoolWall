<template>
  <div >
    <el-container style="height: 100vh">
      <el-aside style="border-right: solid 1px #cccccc" width="250px">Aside</el-aside>
      <el-container>
        <el-header style="border-bottom: solid 1px #cccccc;">Header</el-header>
        <el-main style="" >Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";

const store = useStore()

const logout = () => {
    get('/api/auth/logout', (message) => {
        ElMessage.success(message)
        store.auth.user = null
        router.push('/')
    })
}
</script>

<style scoped>

</style>
