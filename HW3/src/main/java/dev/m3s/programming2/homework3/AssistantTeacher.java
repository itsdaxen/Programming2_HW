package dev.m3s.programming2.homework3;

import java.util.List;
import java.util.ArrayList;

public class AssistantTeacher extends Employee implements Teacher {

    private List<DesignatedCourse> courses = new ArrayList<>();

    public AssistantTeacher(String lname, String fname) {
        super(lname, fname);
    }

    public String getEmployeeIdString() {
        return "OY_ASSISTANT_";
    }

    @Override
    public String getCourses() {

        StringBuilder sb = new StringBuilder();

        for (DesignatedCourse designatedCourse : courses) {
            sb.append(designatedCourse.toString() + "\n");
        }

        return sb.toString();
    }

    public void setCourses(List<DesignatedCourse> courses) {
        if (courses != null) {
            this.courses = courses;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] courses = getCourses().split("\n");

        // Header
        sb.append("Teacher id: ").append(getIdString()).append("\n");
        sb.append("     First name: ").append(getFirstName())
                .append(", Last name: ").append(getLastName()).append("\n");

        if (getBirthDate() != null) {
            sb.append("     Birthdate: ").append(getBirthDate()).append("\n");
        }
        sb.append("     Salary: ").append(String.format("%.2f", calculatePayment())).append("\n");
        sb.append("     Assistant for courses:\n");
        for (String course : courses) {
            sb.append("     ").append(course.toString()).append("\n");
        }

        return sb.toString();
    }

}
