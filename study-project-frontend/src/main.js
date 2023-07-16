import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import 'element-plus/dist/index.css'
import axios from "axios";

const app = createApp(App)

axios.defaults.baseURL = 'http://localhost:8080'

const pinia = createPinia()
app.use(pinia)
app.use(router)

app.mount('#app')
