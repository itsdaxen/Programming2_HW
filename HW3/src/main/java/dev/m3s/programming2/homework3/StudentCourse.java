package dev.m3s.programming2.homework3;

import java.time.Year;

public class StudentCourse {

    private Course course;
    private int gradeNum = 0;
    private int yearCompleted = 0;

    public StudentCourse() {
    }

    public StudentCourse(Course course, final int gradeNum, final int yearCompleted) {
        setCourse(course);
        setGrade(gradeNum);
        setYear(yearCompleted);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    protected void setGrade(int gradeNum) {
        if (course == null)
            return;

        if (checkGradeValidity(gradeNum)) {
            this.gradeNum = gradeNum;

            if (yearCompleted == 0) {
                setYear(Year.now().getValue());
            }
        }
    }

    private boolean checkGradeValidity(final int gradeNum) {
        if (course == null)
            return false;

        if (course.isNumericGrade()) {
            return gradeNum >= ConstantValues.MIN_GRADE && gradeNum <= ConstantValues.MAX_GRADE;
        } else {
            char gradeLetter = Character.toUpperCase((char) gradeNum);
            return gradeLetter == ConstantValues.GRADE_FAILED || gradeLetter == ConstantValues.GRADE_ACCEPTED;
        }
    }

    public boolean isPassed() {
        if (course == null)
            return false;

        if (course.isNumericGrade()) {
            return gradeNum > 0;
        } else {
            char gradeLetter = Character.toUpperCase((char) gradeNum);
            return gradeLetter == ConstantValues.GRADE_ACCEPTED;
        }
    }

    public int getYear() {
        return yearCompleted;
    }

    public void setYear(final int year) {
        int currentYear = Year.now().getValue();
        if (year > 2000 && year <= currentYear) {
            this.yearCompleted = year;
        }
    }

    public String toString() {
        if (course == null)
            return "";
        String gradeString;
        if (gradeNum == 0 && course.isNumericGrade()) {
            gradeString = "\"Not graded\"";
        } else if (course.isNumericGrade()) {
            gradeString = String.valueOf(gradeNum);
        } else {
            gradeString = String.valueOf((char) gradeNum).toUpperCase();
        }
        return String.format("%s Year: %d, Grade: %s.]",
                course.toString(), yearCompleted, gradeString);
    }
}
