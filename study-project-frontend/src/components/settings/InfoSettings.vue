<script setup>
import {reactive} from "vue";
import {Select} from "@element-plus/icons-vue";

const infoForm = reactive({
  username: '',
  sex: ' ',
  wx: '',
  qq: '',
  phone: '',
  blog: '',
  desc: '',
})
</script>

<template>
 <div>
   <el-form

       label-position="top"
       label-width="100px"
       :model="infoForm"
       style="max-width: 460px"
   >
     <el-form-item label="用户名">
       <el-input v-model="infoForm.username" />
     </el-form-item>
     <el-form-item label="性别">
       <el-radio-group v-model="infoForm.sex">
         <el-radio label="male">男</el-radio>
         <el-radio label="female">女</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="QQ">
       <el-input v-model="infoForm.qq" />
     </el-form-item>
     <el-form-item label="微信">
       <el-input v-model="infoForm.wx" />
     </el-form-item>
     <el-form-item label="手机号">
       <el-input v-model="infoForm.phone" />
     </el-form-item>
     <el-form-item label="博客地址">
       <el-input v-model="infoForm.username" />
     </el-form-item>
     <el-form-item label="个人介绍">
       <el-input v-model="infoForm.desc" type="textarea" :rows="6" />
     </el-form-item>
   </el-form>
   <el-button type="success" :icon="Select" >保存个人信息设置</el-button>
 </div>
</template>

<style scoped>

</style>