<template>
  <div >
    <el-container style="height: 100vh">
      <el-aside style="border-right: solid 1px #cccccc" width="250px">
        <div style="padding: 10px; text-align: center">
          <el-image src="https://element-plus.gitee.io/images/element-plus-logo.svg"
                       style="width: 180px" />
        </div>

        <el-menu
            style="border: none"
            default-active="2"
        >
          <el-sub-menu index="1">
            <template #title>
              <el-icon><location /></el-icon>
              <span>Navigator One</span>
            </template>
            <el-menu-item-group title="Group One">
              <el-menu-item index="1-1">item one</el-menu-item>
              <el-menu-item index="1-2">item two</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group Two">
              <el-menu-item index="1-3">item three</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="1-4">
              <template #title>item four</template>
              <el-menu-item index="1-4-1">item one</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-menu-item index="2">
            <el-icon><icon-menu /></el-icon>
            <span>Navigator Two</span>
          </el-menu-item>
          <el-menu-item index="3" disabled>
            <el-icon><document /></el-icon>
            <span>Navigator Three</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><setting /></el-icon>
            <span>Navigator Four</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="border-bottom: solid 1px #cccccc;">
          <div style="display: flex">
            <div style="flex: 1">
              <el-input
                  style="margin-top: 12px; width: 400px"
                  placeholder="搜索论坛内容.."
                  :prefix-icon="Search"
              />
            </div>
            <div style="display: flex" >
              <div style="text-align: right; margin: 10px 10px 0 0" >
                <div style="font-weight: bold">{{store.auth.user.username}}</div>
                <div style="font-size: 13px">{{store.auth.user.email}}</div>
              </div>
              <div style="padding: 7px 0">
                <el-dropdown trigger="click">
                  <el-avatar class="avatar" :size="45"
                             src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                  />
                  <template #dropdown>
                    <el-dropdown-menu>
                      <el-dropdown-item :icon="Plus">Action 1</el-dropdown-item>
                      <el-dropdown-item :icon="CirclePlusFilled">
                        Action 2
                      </el-dropdown-item>
                      <el-dropdown-item :icon="CirclePlus">Action 3</el-dropdown-item>
                      <el-dropdown-item :icon="Check">Action 4</el-dropdown-item>
                      <el-dropdown-item :icon="CircleCheck">Action 5</el-dropdown-item>
                    </el-dropdown-menu>
                  </template>
                </el-dropdown>
              </div>

            </div>
          </div>

        </el-header>
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
import {Document, Location, Setting, Menu as IconMenu, Search} from "@element-plus/icons-vue";

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
.avatar:hover {
  cursor: pointer;
}
</style>
