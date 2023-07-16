import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";



const defaultError = () => ElMessage.error('发生了一些错误，请联系管理员')
const defaultFailure = (message) => ElMessage.warning(message)

function post(url, data, success, type = 'x-www-form-urlencoded', failure = defaultFailure, error = defaultError) {
    axios.post(url, data, {
        headers: {
            'Content-Type': 'application/'+type
        },
        withCredentials: true
    }).then(({data}) => {
        if(data.status === 401) {
            localStorage.removeItem('user')
            const store = useStore()
            store.auth.user = undefined
            router.push('/')
        } else if(data.success)
            success(data.message, data.status)
        else
            failure(data.message, data.status)
    }).catch(error)
}

function get(url, success, failure = defaultFailure, error = defaultError) {
    axios.get(url, {
        withCredentials: true
    }).then(({data}) => {
        if(data.status === 401) {
            localStorage.removeItem('user')
            const store = useStore()
            store.auth.user = undefined
            router.push('/')
        } else if(data.success)
            success(data.message, data.status)
        else
            failure(data.message, data.status)
    }).catch(error)
}


export { get, post }
