package dev.m3s.programming2.homework3;

public class DesignatedCourse {

    private Course course;
    private boolean responsible;
    private int year;

    public DesignatedCourse() {
    }

    public DesignatedCourse(Course course, boolean Responsible, int Year) {
        this.course = course;
        this.responsible = Responsible;
        this.year = Year;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course != null) {
            this.course = course;
        }
    }

    public boolean isResponsible() {
        return responsible;
    }

    public void setResponsible(boolean Responsible) {
        this.responsible = Responsible;
    }

    public int getYear() {
        return year;
    }

    // TODO: Implement the following
    // Time import?
    // compare to other methods years The method sets the given year if the value is
    // 2000<=year<=(current year+1)

    public void setYear(int Year) {
        if (Year >= 2000 && Year <= java.time.Year.now().getValue() + 1) {
            this.year = Year;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[course=[");

        sb.append(String.format("%s (%6.2f cr)",
                course.getCourseCode(), course.getCredits()));

        sb.append(", \"").append(course.getName()).append("\". ");
        sb.append(course.getCourseTypeString());

        sb.append(", period: ").append(course.getPeriod()).append(".], ");

        sb.append("year=").append(year).append("]");

        return sb.toString();
    }

}
