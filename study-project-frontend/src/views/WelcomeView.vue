<template>
    <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
        <div style="flex: 1">
            <el-image style="width: 100%;height: 100%" fit="cover"
                      src="https://pic2.zhimg.com/v2-bff3ad66aa4b756a9241646d35274061_r.jpg"/>
        </div>
        <div class="welcome-title">
            <div style="font-size: 30px;font-weight: bold">欢迎来到我们的ikun平台</div>
            <div style="margin-top: 10px">只看坤坤炒粉放鸡精</div>
            <div style="margin-top: 5px">ikun的时代已经来临，只看坤坤炒粉放鸡精</div>
        </div>
        <div style="width: 400px;background-color: white;z-index: 1">
            <router-view v-slot="{ Component }">
                <transition name="el-fade-in-linear" mode="out-in">
                    <component :is="Component" style="height: 100%"/>
                </transition>
            </router-view>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.welcome-title {
    position: absolute;
    bottom: 30px;
    left: 30px;
    color: white;
    text-shadow: 0 0 10px black;
}
</style>
