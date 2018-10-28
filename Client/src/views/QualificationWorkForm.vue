<template>
	<el-form :model="data" label-width="220px">
		<el-form-item size="small" label="Тип роботи: " prop="workType" required>
			<el-radio-group v-model="data.workType">
				<el-radio label="Дипломна"></el-radio>
				<el-radio label="Курсова"></el-radio>
			</el-radio-group>
		</el-form-item>
		<el-form-item size="small" label="Навчальний рік: " required>
			<el-col :span="11">
				<el-form-item size="small" prop="beginYear" required>
					<el-input-number size="small" v-model="data.beginYear" style="width: 100%;"/>
				</el-form-item>
			</el-col>
			<el-col class="line" :span="2">-</el-col>
			<el-col :span="11">
				<el-form-item size="small" prop="endYear" required>
					<el-input-number size="small" v-model="data.endYear" style="width: 100%;"/>
				</el-form-item>
			</el-col>
		</el-form-item>
		<el-form-item size="small" prop="semesterNumber" label="Семестр: " required>
			<el-input-number placeholder="Pick a time" size="small"
			                 v-model="data.semesterNumber"
			                 :min="1" :max="12"/>
		</el-form-item>
		<el-form-item size="small" prop="courseNumber" label="Курс: " required>
			<el-input-number placeholder="Pick a time" size="small"
			                 v-model="data.courseNumber"
			                 :min="1" :max="6"/>
		</el-form-item>
		<el-form-item size="small" prop="title" label="Тема: " required>
			<el-input size="small" v-model="data.title" style="width: 100%;"/>
		</el-form-item>
		<el-form-item size="small" prop="group" label="Група: " required>
			<el-select size="small" v-model="data.group"
			           remote filterable
			           :loading="isLoading"
			           loading-text="Зачекайте доки їде запит."
			           style="width: 100%;"
			           :remote-method="findGroupsByParam"
			           @change="handleGroupChange"
			           value-key="id">
				<el-option v-for="group in groupSuggestions" :key="group.key"
				           :value="group" :label="group.groupName"/>
			</el-select>
		</el-form-item>
		<el-form-item v-if="data.workType === 'Курсова'" size="small" prop="discipline" label="Дисципліна: "
		              required>
			<el-select size="small" v-model="data.discipline"
			           remote filterable
			           :loading="isLoading"
			           loading-text="Зачекайте доки їде запит."
			           style="width: 100%;"
			           :remote-method="findDisciplinesByParam"
			           @change="handleDisciplineChange"
			           value-key="id">
				<el-option v-for="discipline in disciplineSuggestions" :key="discipline.key"
				           :value="discipline" :label="discipline.shortName"/>
			</el-select>
		</el-form-item>
		<el-form-item size="small" prop="studentFullName" label="Студент: "
		              required>
			<el-select size="small" v-model="data.student"
			           remote filterable
			           :loading="isLoading"
			           loading-text="Зачекайте доки їде запит."
			           style="width: 100%;"
			           :remote-method="findStudentsByParam"
			           @change="handleStudentChange"
			           value-key="id">
				<el-option v-for="student in studentSuggestions"
				           :key="student.id"
				           :value="student" :label="student.fullName"/>
			</el-select>
		</el-form-item>
		<el-form-item size="small" label="Викладачі: " required prop="teacherNames">
			<el-transfer
					v-model="data.teacherNames"
					:data="teacherSuggestions"
					:props="{key: 'id', label: 'fullName'}"
					filterable
					@change="onTransfered">
			</el-transfer>
		</el-form-item>
		<el-form-item size="small" prop="faculty" label="Факультет: " required>
			<el-input size="small" v-model="data.faculty" style="width: 100%;"/>
		</el-form-item>
		<el-form-item size="small" prop="branch" label="Галузь: " required>
			<el-input size="small" v-model="data.branch" style="width: 100%;"/>
		</el-form-item>
		<el-form-item size="small" prop="specialty" label="Спеціальність: " required>
			<el-input size="small" v-model="data.specialty" style="width: 100%;"/>
		</el-form-item>
		<el-form-item size="small" prop="educationLevel" label="ОКР: " required>
			<el-input size="small" v-model="data.educationLevel" style="width: 100%;"/>
		</el-form-item>
		<el-form-item size="small" prop="educationProgram" label="ОП: " required>
			<el-input size="small" v-model="data.educationProgram" style="width: 100%;"/>
		</el-form-item>
		<el-form-item size="small" prop="grade" label="Оцінка: " required>
			<el-input-number size="small" v-model="data.grade"
			                 @change="onGradeChanged"
			                 style="width: 100%;" :min="0" :max="100"/>
		</el-form-item>
		<el-form-item size="small" prop="gradeECTS" label="Оцінка ECTS: " required>
			<el-select size="small" v-model="data.gradeECTS"
			           @change="onGradeECTSChanged"
			           style="width: 100%;" placeholder="Select">
				<el-option value="A" label="A"/>
				<el-option value="B" label="B"/>
				<el-option value="C" label="C"/>
				<el-option value="D" label="D"/>
				<el-option value="E" label="E"/>
				<el-option value="FX" label="FX"/>
				<el-option value="F" label="F"/>
			</el-select>
		</el-form-item>
		<el-form-item size="small" prop="gradeECTS" label="Оцінка гос.: " required>
			<el-select size="small" v-model="data.gradeNational" style="width: 100%;"
			           @change="onGradeNationalChanged"
			           placeholder="Select">
				<el-option value="відмінно" label="відмінно"/>
				<el-option value="добре" label="добре"/>
				<el-option value="задовільно" label="задовільно"/>
				<el-option value="не задовільно" label="не задовільно"/>
			</el-select>
		</el-form-item>
	</el-form>
</template>

<script lang="ts">
	import {Component, Watch} from "vue-property-decorator"
	import Vue from "vue"
	import {DisciplineView, GroupView, QualificationWorkFormData, StudentView, TeacherView} from "@/entities/entities"


	@Component({
		name: "QualificationWorkForm"
	})
	export default class QualificationWorkForm extends Vue {
		private readonly data: QualificationWorkFormData = {
			beginYear: 2017,
			endYear: 2018,
			semesterNumber: 1,
			discipline: null,
			group: null,
			groupName: "",
			disciplineName: "",
			student: null,
			title: "",
			workType: "Дипломна",
			studentFullName: "",
			faculty: "",
			specialty: "",
			branch: "",
			educationLevel: "",
			educationProgram: "",
			gradeECTS: "A",
			gradeNational: "відмінно",
			grade: 100,
			courseNumber: 1,
			teachers: [],
			teacherNames: []
		}

		private groupSuggestions: GroupView[] = []
		private disciplineSuggestions: DisciplineView[] = []
		private teacherSuggestions: TeacherView[] = []
		private studentSuggestions: StudentView[] = []

		private isLoading: boolean = false

		private mounted() {
			this.isLoading = true
			this.axios.get("/teacher/view/all").then((value) => {
				this.isLoading = false
				this.teacherSuggestions = value.data.teacherViews
			}).catch((reason) => {
				console.log(reason)
			})
		}

		private findGroupsByParam(groupNamePart: string) {
			this.isLoading = true
			this.axios.post("/group/get-by-course-number-and-academic-year", {
				beginYear: this.data.beginYear,
				endYear: this.data.endYear,
				courseNumber: this.data.courseNumber,
				semester: this.data.semesterNumber,
				groupNamePart
			}).then((value) => {
				this.isLoading = false
				this.groupSuggestions = value.data.groups
			}).catch((reason) => {
				console.log(reason)
			})
		}

		private findDisciplinesByParam(disciplineNamePart: string) {
			this.isLoading = true
			this.axios.post("/discipline/view/find", {
				parameterKey: "nazva",
				parameterValue: disciplineNamePart
			}).then((value) => {
				this.isLoading = false
				this.disciplineSuggestions = value.data.disciplines
			}).catch((reason) => {
				console.log(reason)
			})
		}

		private findStudentsByParam(studentFullNamePart: string) {
			this.isLoading = true
			this.axios.post("/student/view/find", {
				parameterKey: "pib",
				parameterValue: studentFullNamePart
			}).then((value) => {
				this.isLoading = false
				this.studentSuggestions = value.data.students
			}).catch((reason) => {
				console.log(reason)
			})
		}

		private handleDisciplineChange(value: DisciplineView) {
			this.data.disciplineName = value.shortName
			this.disciplineSuggestions = []
		}

		private handleGroupChange(groupView: GroupView) {
			this.groupSuggestions = []
			this.axios.post("/group/get-by-id", {
				id: groupView.id
			}).then((value) => {
				this.data.faculty = value.data.group.faculty
				this.data.branch = value.data.group.branch
				this.data.educationLevel = value.data.group.educationLevel
				this.data.specialty = value.data.group.specialty
				this.data.educationProgram = value.data.group.educationProgram
			}).catch((reason) => {
				console.log(reason)
			})
			this.data.groupName = groupView.groupName
		}

		private handleStudentChange(value: StudentView) {
			this.data.studentFullName = value.fullName
			this.studentSuggestions = []
		}

		@Watch("data.beginYear", {deep: true})
		private onBeginYearChanged(val: number) {
			this.data.endYear = val + 1
		}


		@Watch("data.endYear", {deep: true})
		private onEndYearChanged(val: number) {
			this.data.beginYear = val - 1
		}

		@Watch("data.courseNumber", {deep: true})
		private onCourseNumberChanged(val: number, oldVal: number) {
			if (val > oldVal) {
				this.data.semesterNumber = val + oldVal
			} else {
				this.data.semesterNumber = (val - 1) + (oldVal - 1)
			}

			this.data.group = null
		}

		@Watch("data.semesterNumber", {deep: true})
		private onSemesterNumberChanged(val: number) {
			this.data.courseNumber = Math.round(val / 2)

			this.data.group = null
			this.data.faculty = ""
			this.data.branch = ""
			this.data.educationLevel = ""
			this.data.specialty = ""
			this.data.educationProgram = ""
		}

		private onGradeChanged(val: number) {
			if (val >= 88) {
				this.data.gradeECTS = "A"
				this.data.gradeNational = "відмінно"
			} else if (val >= 81) {
				this.data.gradeECTS = "B"
				this.data.gradeNational = "добре"
			} else if (val >= 74) {
				this.data.gradeECTS = "С"
				this.data.gradeNational = "добре"
			} else if (val >= 68) {
				this.data.gradeECTS = "D"
				this.data.gradeNational = "задовільно"
			} else if (val >= 60) {
				this.data.gradeECTS = "E"
				this.data.gradeNational = "задовільно"
			} else if (val >= 40) {
				this.data.gradeECTS = "FX"
				this.data.gradeNational = "не задовільно"
			} else {
				this.data.gradeECTS = "F"
				this.data.gradeNational = "не задовільно"
			}
		}

		private onGradeECTSChanged(val: string) {
			console.log("onGradeECTSChanged")
			if (val === "A") {
				this.data.grade = 88
				this.data.gradeNational = "відмінно"
			} else if (val === "B") {
				this.data.grade = 80
				this.data.gradeNational = "добре"
			} else if (val === "С") {
				this.data.grade = 74
				this.data.gradeNational = "добре"
			} else if (val === "D") {
				this.data.grade = 68
				this.data.gradeNational = "задовільно"
			} else if (val === "E") {
				this.data.grade = 60
				this.data.gradeNational = "задовільно"
			} else if (val === "FX") {
				this.data.grade = 40
				this.data.gradeNational = "не задовільно"
			} else {
				this.data.grade = 39
				this.data.gradeNational = "не задовільно"
			}
		}

		private onGradeNationalChanged(val: string) {
			console.log("onGradeNationalChanged")
			if (val === "відмінно") {
				this.data.grade = 88
				this.data.gradeECTS = "A"
			} else if (val === "добре") {
				this.data.grade = 74
				this.data.gradeECTS = "C"
			} else if (val === "задовільно") {
				this.data.grade = 60
				this.data.gradeECTS = "E"
			} else {
				this.data.grade = 40
				this.data.gradeECTS = "FX"
			}
		}

		private onTransfered(id: number) {
			console.log(id)
		}

	}
</script>

<style scoped lang="scss">
	.line {
		text-align: center;
	}
</style>