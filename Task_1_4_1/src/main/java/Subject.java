public class Subject {

    private String subjectName;
    private Grade grade;

    Subject(String name, Grade grade) {
        this.subjectName = name;
        this.grade = grade;
    }

    public Grade getGrades() {
        return this.grade;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
