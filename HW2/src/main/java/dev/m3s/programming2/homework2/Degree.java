package dev.m3s.programming2.homework2;

public class Degree {

    private static final int MAX_COURSES = 50;
    private int count = 0;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private StudentCourse[] myCourses = new StudentCourse[MAX_COURSES];

    public StudentCourse[] getCourses() {
        return myCourses;
    }

    public void addStudentCourses(StudentCourse[] courses) {
        if (courses == null) return;
        for (StudentCourse course : courses) {
            addStudentCourse(course);
        }
    }

    public boolean addStudentCourse(StudentCourse course) {
        if (course != null && count < MAX_COURSES) {
            myCourses[count++] = course;
            return true;
        }
        return false;
    }

    public String getDegreeTitle() {

        return degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        if (degreeTitle != null) {
            this.degreeTitle = degreeTitle;
        }
    }

    public String getTitleOfThesis() {
        return titleOfThesis;
    }

    public void setTitleOfThesis(String titleOfThesis) {
        if (titleOfThesis != null) {
            this.titleOfThesis = titleOfThesis;
        }
    }

    public double getCreditsByBase(Character base) {
        if (base == null) return 0.0;
        char upperCaseBase = Character.toUpperCase(base);
        double total = 0.0;
        for (int i = 0; i < count; i++) {
            Course course = myCourses[i].getCourse();
            if (isCourseCompleted(myCourses[i]) &&
                    course.getCourseBase() == upperCaseBase) {
                total += course.getCredits();
            }
        }
        return total;
    }

    public double getCreditsByType(final int courseType) {
        double total = 0.0;
        for (int i = 0; i < count; i++) {
            Course course = myCourses[i].getCourse();
            if (isCourseCompleted(myCourses[i]) &&
                    course.getCourseType() == courseType) {
                total += course.getCredits();
            }
        }
        return total;
    }

    public double getCredits() {
        double total = 0.0;
        for (int i = 0; i < count; i++) {
            Course course = myCourses[i].getCourse();
            if (isCourseCompleted(myCourses[i])) {
                total += course.getCredits();
            }
        }
        return total;
    }

    private boolean isCourseCompleted(StudentCourse c) {
        return c != null && c.isPassed();
    }

    public void printCourses() {
        for (int i = 0; i < count; i++) {
            if (myCourses[i] != null) {
                System.out.printf("%d. %s\n", i + 1, myCourses[i].toString());
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Degree [Title: \"%s\" (courses: %d) \n", degreeTitle, count));
        sb.append(String.format("     Thesis title: \"%s\" \n", titleOfThesis));
        for (int i = 0; i < count; i++) {
            if (myCourses[i] != null) {
                sb.append(String.format("     %d. %s \n", i + 1, myCourses[i].toString()));
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
