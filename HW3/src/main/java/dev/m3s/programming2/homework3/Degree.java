package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class Degree {

    private static final int MAX_COURSES = 50;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private List<StudentCourse> myCourses = new ArrayList<>();

    public List<StudentCourse> getCourses() {
        return myCourses;
    }

    public void addStudentCourses(List<StudentCourse> courses) {
        if (courses == null)
            return;
        for (StudentCourse course : courses) {
            addStudentCourse(course);
        }
    }

    public boolean addStudentCourse(StudentCourse course) {
        if (course != null && myCourses.size() < 50) {
            myCourses.add(course);
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
        if (base == null)
            return 0.0;
        char upperCaseBase = Character.toUpperCase(base);
        double total = 0.0;
        for (StudentCourse studentCourse : myCourses) {
            Course course = studentCourse.getCourse();
            if (isCourseCompleted(studentCourse) &&
                    course.getCourseBase() == upperCaseBase) {
                total += course.getCredits();
            }
        }
        return total;
    }

    public double getCreditsByType(final int courseType) {
        double total = 0.0;
        for (StudentCourse studentCourse : myCourses) {
            Course course = studentCourse.getCourse();
            if (isCourseCompleted(studentCourse) &&
                    course.getCourseType() == courseType) {
                total += course.getCredits();
            }
        }
        return total;
    }

    public double getCredits() {
        double total = 0.0;
        for (StudentCourse studentCourse : myCourses) {
            Course course = studentCourse.getCourse();
            if (isCourseCompleted(studentCourse)) {
                total += course.getCredits();
            }
        }
        return total;
    }

    private boolean isCourseCompleted(StudentCourse c) {
        return c != null && c.isPassed();
    }

    public void printCourses() {
        for (StudentCourse studentCourse : myCourses) {
            if (studentCourse != null) {
                System.out.printf("%d. %s\n", myCourses.indexOf(studentCourse) + 1, studentCourse.toString());
            }
        }
    }

    public List<Double> getGPA(int type) {
        List<Double> gpa = new ArrayList<>();
        double sum = 0.0;
        int count = 0;

        for (StudentCourse studentCourse : myCourses) {
            Course course = studentCourse.getCourse();
            // if (!isCourseCompleted(studentCourse)) {
            // continue;
            // }
            if (course.getCourseType() == type || type == ConstantValues.ALL) {
                if (course.isNumericGrade()) {
                    count++;
                    sum += studentCourse.getGradeNum();
                }

            }

        }

        gpa.add(sum);
        gpa.add((double) count);
        gpa.add(Math.round((count > 0 ? sum / count : 0.0) * 100.0) / 100.0);

        return gpa;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Degree [Title: \"%s\" (courses: %d) \n", degreeTitle, myCourses.size()));
        sb.append(String.format("     Thesis title: \"%s\" \n", titleOfThesis));
        for (StudentCourse studentCourse : myCourses) {
            if (studentCourse != null) {
                sb.append(String.format("     %d. %s \n", myCourses.indexOf(studentCourse) + 1,
                        studentCourse.toString()));
            }
        }
        sb.append("]\n");
        return sb.toString();
    }

}
