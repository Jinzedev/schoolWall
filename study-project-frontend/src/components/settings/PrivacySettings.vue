<script setup>
import {reactive} from "vue";
import {Select} from "@element-plus/icons-vue";

const privacyForm = reactive({
  email: false,
  phone: false,
  qq: false,
  wx: false,
  blog: false,
  sex: false
})

</script>

<template>
<div>
  <el-form
      label-position="top"
      label-width="100px"
      :model="privacyForm"
      style="max-width: 460px"
  >
    <el-form-item>
      <el-checkbox v-model="privacyForm.email" label="是否公开你的邮箱" size="large" />
    </el-form-item>
    <el-form-item>
      <el-checkbox v-model="privacyForm.sex" label="是否公开你的性别" size="large" />
    </el-form-item>
    <el-form-item>
      <el-checkbox v-model="privacyForm.phone" label="是否公开你的手机号" size="large" />
    </el-form-item>
    <el-form-item>
      <el-checkbox v-model="privacyForm.qq" label="是否公开你的QQ" size="large" />
    </el-form-item>
    <el-form-item>
      <el-checkbox v-model="privacyForm.wx" label="是否公开你的微信" size="large" />
    </el-form-item>
    <el-form-item>
      <el-checkbox v-model="privacyForm.blog" label="是否公开你的博客" size="large" />
    </el-form-item>
  </el-form>
  <el-button type="success" :icon="Select" >保存隐私信息设置</el-button>
</div>
</template>

<style scoped>
::v-deep(el-form-item){
  margin-bottom: 5px;
}
</style>