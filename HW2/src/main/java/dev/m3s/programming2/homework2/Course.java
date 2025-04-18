package dev.m3s.programming2.homework2;

public class Course {
    private String name;
    private String courseCode;
    private Character courseBase;
    private int courseType;
    private int period;
    private double credits;
    private boolean numericGrade;

    public Course() {
    }

    public Course(String name, final int code, Character courseBase, final int type,
                  final int period, final double credits, boolean numericGrade) {
        setName(name);
        setCourseCode(code, courseBase);
        setCourseType(type);
        setPeriod(period);
        setCredits(credits);
        setNumericGrade(numericGrade);
    }

    public Course(Course course) {
        this.name = course.name;
        this.courseCode = course.courseCode;
        this.courseBase = course.courseBase;
        this.courseType = course.courseType;
        this.period = course.period;
        this.credits = course.credits;
        this.numericGrade = course.numericGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public String getCourseTypeString() {
        return (courseType == 1) ? "Mandatory" : "Optional";
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(final int type) {
        if (type == 0 || type == 1) {
            this.courseType = type;
        }
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(final int courseCode, Character courseBase) {
        if (courseCode > 0 && courseCode < 1000000 && courseBase != null) {
            char upperCaseBase = Character.toUpperCase(courseBase);
            if (upperCaseBase == 'A' || upperCaseBase == 'P' || upperCaseBase == 'S') {
                this.courseBase = upperCaseBase;
                this.courseCode = courseCode + String.valueOf(upperCaseBase);
            }
        }
    }

    public Character getCourseBase() {
        return courseBase;
    }


    public int getPeriod() {
        return period;
    }

    public void setPeriod(final int period) {
        if (period >= ConstantValues.MIN_PERIOD && period <= ConstantValues.MAX_PERIOD) {
            this.period = period;
        }
    }

    public double getCredits() {
        return credits;
    }

    private void setCredits(final double credits) {
        if (credits >= ConstantValues.MIN_CREDITS && credits <= ConstantValues.MAX_COURSE_CREDITS) {
            this.credits = credits;
        }
    }

    public boolean isNumericGrade() {
        return numericGrade;
    }

    public void setNumericGrade(boolean numericGrade) {
        this.numericGrade = numericGrade;
    }

    public String toString() {
        return String.format("[%s ( %5.2f cr), \"%s\". %s, period: %d.]",
                courseCode, credits, name, getCourseTypeString(), period);
    }
}