import Vue from "vue"
import Router from "vue-router"
import QualificationWorkTable from "@/components/QualificationWorkTable.vue"
import InsertQualificationWork from "@/components/InsertQualificationWork.vue"
import StudentsForm from "@/components/StudentsForm.vue"

Vue.use(Router)

export default new Router({
	routes: [
		{
			name: "QualificationWorkTable",
			path: "/works",
			component: QualificationWorkTable,
		},
		{
			name: "InsertQualificationWork",
			path: "/works/insert",
			component: InsertQualificationWork
		},
		{
			name: "InsertStudent",
			path: "/students",
			component: StudentsForm
		}
	],
})
