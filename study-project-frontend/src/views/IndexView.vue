<template>
  <div >
    <el-container style="height: 100vh">
      <el-aside style="border-right: solid 1px #cccccc; transition: 0.5s" :width="isCollapse ? '60px':'250px'"  >
        <div style="padding: 10px; text-align: center; margin-left: 3px">
          <el-image src="https://element-plus.gitee.io/images/element-plus-logo.svg"
                       style="width: 180px" />
        </div>
        <el-menu
            style="border: none"
            :default-active="router.currentRoute.value.path"
            :collapse="isCollapse"
            router
        >
          <el-menu-item index="/index">
            <el-icon><icon-menu /></el-icon>
            <span>帖子列表</span>
          </el-menu-item>
          <el-menu-item index="2">
            <el-icon><document /></el-icon>
            <span>校园表白墙</span>
          </el-menu-item>
          <el-menu-item index="3">
            <el-icon><setting /></el-icon>
            <span>帖子管理</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><setting /></el-icon>
            <span>我的收藏</span>
          </el-menu-item>
          <el-menu-item index="/index/setting">
            <el-icon><setting /></el-icon>
            <span>个人设置</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="border-bottom: solid 1px #cccccc; padding: 7px 0 0 0 ">
          <div style="display: flex;">
            <div >
              <el-button :icon="isCollapse ? Expand : Fold" text @click="isCollapse = !isCollapse"
                         style="font-size: 25px;margin-top: 10px"/>
            </div>
            <div style="flex: 1; text-align: center">
              <el-input
                  style="margin-top: 10px; width: 400px"
                  placeholder="搜索论坛内容.."
                  :prefix-icon="Search"
              />
            </div>
            <div style="display: flex" >
              <div style="text-align: right; margin: 10px 10px 0 0" >
                <div style="font-weight: bold">{{store.auth.user?.username}}</div>
                <div style="font-size: 13px">{{store.auth.user?.email}}</div>
              </div>
              <div >
                <el-dropdown trigger="click">
                  <el-avatar class="avatar" :size="45"
                             src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                  />
                  <template #dropdown>
                    <el-dropdown-menu>
                      <el-dropdown-item :icon="Setting">个人设置</el-dropdown-item>
                      <el-dropdown-item @click="logout" style="color: #b2263f" :icon="SwitchButton">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                  </template>
                </el-dropdown>
              </div>

            </div>
          </div>

        </el-header>
        <el-main style="padding: 0" >
          <el-scrollbar height="100%">
            <router-view/>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";
import {
  Document,
  Setting,
  Menu as IconMenu,
  Search,
  SwitchButton,
  Fold,
  Expand
} from "@element-plus/icons-vue";
import {ref} from "vue";

const store = useStore()
const isCollapse = ref(false)

const logout = () => {
    get('/api/auth/logout', (message) => {
        ElMessage.success(message)
        store.auth.user = null
        localStorage.removeItem('user')
        router.push('/')
    })
}
</script>

<style scoped>
.avatar:hover {
  cursor: pointer;
}
</style>
