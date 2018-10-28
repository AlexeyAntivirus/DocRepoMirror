export interface QualificationWorkFormData {
	title: string,
	beginYear: number,
	endYear: number,
	semesterNumber: number,
	discipline: DisciplineView | null,
	disciplineName: string,
	group: GroupView | null,
	groupName: string,
	student: StudentView | null,
	workType: string,
	studentFullName: string,
	faculty: string,
	specialty: string,
	branch: string,
	educationLevel: string,
	educationProgram: string,
	gradeECTS: string,
	gradeNational: string,
	grade: number,
	courseNumber: number,
	teachers: TeacherView[],
	teacherNames: number[]
}

export interface GroupView {
	id: number,
	groupName: string
}

export interface StudentView {
	id: number,
	fullName: string
}

export interface DisciplineView {
	id: number,
	shortName: string
}
export interface TeacherView {
	id: number,
	index: number
}

