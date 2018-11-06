<template>
	<el-menu default-active="/works" router style="height: 100vh">
		<el-card shadow="never">DocRepo</el-card>
		<el-menu-item index="/works">Дипломні та курсові</el-menu-item>
		<el-menu-item index="/students">Студенти</el-menu-item>
		<el-menu-item index="/prep">Викладачі</el-menu-item>
		<el-menu-item index="/groups">Групи</el-menu-item>
		<el-menu-item index="/disciplines">Предмети</el-menu-item>
	</el-menu>
</template>
<script lang="ts">
	import Vue from "vue"
	import {Component, Prop, Watch} from "vue-property-decorator"

	@Component({
		name: "AppMenu"
	})
	export default class AppMenu extends Vue {

	}
</script>


