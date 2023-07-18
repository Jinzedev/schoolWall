<script setup>
import {reactive} from "vue";
import {Lock, Message, Select} from "@element-plus/icons-vue";

const securityForm = reactive({
  email: '',
  old_password: '',
  new_password: '',
  check_new_password:''
})

</script>

<template>
  <div>
    <div>
      <h1><el-icon><Message /></el-icon>邮箱设置</h1>
      <el-form
          label-position="top"
          label-width="100px"
          :model="securityForm"
          style="max-width: 460px"
      >
        <el-form-item label="邮箱">
          <el-input v-model="securityForm.email" />
        </el-form-item>
      </el-form>
      <el-button type="success" :icon="Select" >保存新的邮箱</el-button>
    </div>
    <el-divider/>
    <div>
      <h1><el-icon><Lock /></el-icon>修改密码</h1>
      <el-form
          label-position="top"
          label-width="100px"
          :model="securityForm"
          style="max-width: 460px"
      >
        <el-form-item label="旧密码">
          <el-input v-model="securityForm.old_password" />
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="securityForm.new_password" />
        </el-form-item>
        <el-form-item label="确认新密码">
          <el-input v-model="securityForm.check_new_password" />
        </el-form-item>
      </el-form>
      <el-button type="success" :icon="Select" >修改密码</el-button>
    </div>
  </div>
</template>

<style scoped>

</style>