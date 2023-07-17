<script setup>
import {ref} from "vue";
import {useStore} from "@/stores";
import InfoSettings from "@/components/settings/InfoSettings.vue";


const store = useStore()
const activeName = ref('info')

</script>

<template>
  <div style="display: flex;padding: 20px 30px">
    <div style="flex: 1">
      <el-tabs v-model="activeName">
        <el-tab-pane label="个人信息设置" name="info">
          <info-settings></info-settings>
        </el-tab-pane>
        <el-tab-pane label="账号安全设置" name="security">

        </el-tab-pane>
        <el-tab-pane label="隐私设置" name="privacy">

        </el-tab-pane>
      </el-tabs>
    </div>
    <div style="width: 300px">
      <div class="user-card" style="margin: 15px 0 0 30px">
        <div style="text-align: right;padding: 5px 15px 0 15px; border-bottom: solid 1px #cccccc">
          <div style="width: 70px;display: inline-block">
            <el-avatar :size="70" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
            <div style="text-align: center;font-weight: bold; margin-bottom: 5px">{{store.auth.user.username}}</div>
          </div>
        </div>
        <div style="padding: 10px;font-size: 13px">
          还打算登记卡手动滑稽啊扩大合计阿卡手打啊世纪大厦健康大厦登记卡
          撒谎登记卡手动滑稽卡死打卡机阿萨高大上科技大厦打撒黑科技
          撒活动卡萨丁卡仕达酱啊受打击卡萨
          大萨达哈萨克大豪科技啊打卡机阿萨达
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.user-card {
  height: 300px;
  width: 260px;
  border-radius: 5px;
  border: solid 1px #cccccc;
}


</style>
