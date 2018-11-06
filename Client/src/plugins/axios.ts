
import Vue from "vue"
import {PluginObject} from "vue"
import axios, {AxiosRequestConfig, AxiosInstance} from "axios"
import { globalConfig } from "@/plugins/config"

const requestConfig: AxiosRequestConfig = {
	baseURL: globalConfig.serverUrl
}

const axiosInstance = axios.create(requestConfig)

axiosInstance.interceptors.request.use(
	(config) => {
		// Do something before request is sent
		return config
	},
	(error) => {
		// Do something with request error
		return Promise.reject(error)
	}
)

// Add a response interceptor
axiosInstance.interceptors.response.use(
	(response) => {
		// Do something with response data
		return response
	},
	(error) => {
		// Do something with response error
		return Promise.reject(error)
	}
)

declare var window: any

declare module "vue/types/vue" {
	interface Vue {
		$axios: AxiosInstance,
		axios: AxiosInstance
	}
}


const Plugin: PluginObject<AxiosRequestConfig> = {
	install: (VueInstance: any, options) => {
		VueInstance.axios = axiosInstance
		window.axios = axiosInstance
		Object.defineProperties(VueInstance.prototype, {
			axios: {
				get() {
					return axiosInstance
				}
			},
			$axios: {
				get() {
					return axiosInstance
				}
			},
		})
	}
}

Vue.use(Plugin)

export default Plugin
