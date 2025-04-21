package dev.m3s.programming2.homework3;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private int id;
    private int startYear;
    private int graduationYear;
    private List<Degree> degrees = new ArrayList<>();

    public Student(String lname, String fname) {
        super(lname, fname);
        this.startYear = Year.now().getValue();
        this.id = getRandomId(ConstantValues.MIN_STUDENT_ID, ConstantValues.MAX_STUDENT_ID);
        for (int i = 0; i < 3; i++) {
            degrees.add(new Degree());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        if (id >= ConstantValues.MIN_STUDENT_ID && id <= ConstantValues.MAX_STUDENT_ID) {
            this.id = id;
        }
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(final int startYear) {
        int currentYear = Year.now().getValue();
        if (startYear > 2000 && startYear <= currentYear) {
            this.startYear = startYear;
        }
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public String setGraduationYear(final int year) {
        if (!canGraduate()) {
            return "Check amount of required credits";
        }
        if (year < startYear || year > Year.now().getValue()) {
            return "Check graduation year";
        }
        this.graduationYear = year;
        return "Ok";
    }

    public void setDegreeTitle(final int i, String dName) {
        if (i >= 0 && i < degrees.size() && dName != null) {
            degrees.get(i).setDegreeTitle(dName);
        }
    }

    public boolean addCourse(final int i, StudentCourse course) {
        if (i >= 0 && i < degrees.size() && course != null) {
            return degrees.get(i).addStudentCourse(course);
        }
        return false;
    }

    public int addCourses(final int i, List<StudentCourse> courses) {
        if (i >= 0 && i < degrees.size() && courses != null) {
            int added = 0;
            for (StudentCourse c : courses) {
                if (c != null && degrees.get(i).addStudentCourse(c)) {
                    added++;
                }
            }
            return added;
        }
        return 0;
    }

    public void printCourses() {
        for (Degree degree : degrees) {
            if (degree != null)
                degree.printCourses();
        }
    }

    public void printDegrees() {
        for (Degree degree : degrees) {
            if (degree != null)
                System.out.println(degree.toString());
        }
    }

    public void setTitleOfThesis(final int i, String title) {
        if (i >= 0 && i < degrees.size() && title != null) {
            degrees.get(i).setTitleOfThesis(title);
        }
    }

    public boolean hasGraduated() {
        return graduationYear > 0;
    }

    private boolean canGraduate() {
        Degree bachelor = degrees.get(0);
        Degree master = degrees.get(1);
        double bachelorCredits = bachelor.getCredits();
        double masterCredits = master.getCredits();
        boolean hasTitles = !bachelor.getTitleOfThesis().equals(ConstantValues.NO_TITLE)
                && !master.getTitleOfThesis().equals(ConstantValues.NO_TITLE);
        return bachelorCredits >= ConstantValues.BACHELOR_CREDITS
                && masterCredits >= ConstantValues.MASTER_CREDITS
                && hasTitles;
    }

    public int getStudyYears() {
        int currentYear = Year.now().getValue();
        return (hasGraduated() ? graduationYear : currentYear) - startYear;
    }


    public String getIdString() {
        return String.format("Student id: %d", id);
    }

    public double totalGPA() {
        double totalSum = 0.0;
        double totalCount = 0.0;

        for (Degree degree : degrees) {
            List<Double> gpaData = degree.getGPA(ConstantValues.ALL);
            double sum = gpaData.get(0);
            double count = gpaData.get(1);

            totalSum += sum;
            totalCount += count;
        }
        return totalCount > 0 ? totalSum / totalCount : 0.0;

    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.0");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat df2 = new DecimalFormat("0.00", symbols);
        StringBuilder sb = new StringBuilder();

        double bachelorCredits = degrees.get(0).getCredits();
        double masterCredits = degrees.get(1).getCredits();
        double totalCredits = bachelorCredits + masterCredits;
        double bachelorMandatoryCredits = degrees.get(0).getCreditsByType(ConstantValues.MANDATORY);
        double masterMandatoryCredits = degrees.get(1).getCreditsByType(ConstantValues.MANDATORY);
        double bachelorGpa = degrees.get(0).getGPA(ConstantValues.ALL).get(2);
        double masterGpa = degrees.get(1).getGPA(ConstantValues.ALL).get(2);
        double totalGpa = totalGPA();

        sb.append(getIdString());
        sb.append(String.format("%n     First name: %s, Last name: %s%n", getFirstName(), getLastName()));
        if (ConstantValues.NO_BIRTHDATE.equals(getBirthDate())) {
            sb.append(String.format("     Date of birth: \"%s\"%n", getBirthDate()));
        } else {
            sb.append(String.format("     Date of birth: %s%n", getBirthDate()));
        }
        if (hasGraduated()) {
            sb.append(String.format("     Status: The student has graduated in %d%n", getGraduationYear()));
        } else {
            sb.append("     Status: The student has not graduated, yet\n");
        }
        sb.append(String.format("     Start year: %d (studies %s for %d years)%n",
                startYear,
                hasGraduated() ? "lasted" : "have lasted",
                getStudyYears()));
        sb.append(String.format("     Total credits: %s", df.format(totalCredits)));
        sb.append(String.format(" (GPA = %s)%n", df2.format(totalGpa)));
        sb.append(String.format("     Bachelor credits: %s%n", df.format(bachelorCredits)));
        if (bachelorCredits >= ConstantValues.BACHELOR_CREDITS) {
            sb.append(String.format("          Total bachelor credits completed (%s/%s)%n",
                    df.format(bachelorCredits), df.format(ConstantValues.BACHELOR_CREDITS)));
        } else {
            sb.append(String.format("          Missing bachelor credits %s (%s/%s)%n",
                    df.format(ConstantValues.BACHELOR_CREDITS - bachelorCredits),
                    df.format(bachelorCredits),
                    df.format(ConstantValues.BACHELOR_CREDITS)));
        }
        sb.append(String.format("          %s (%s/%s)%n",
                bachelorMandatoryCredits >= ConstantValues.BACHELOR_MANDATORY
                        ? "All mandatory bachelor credits completed"
                        : String.format("Missing mandatory bachelor credits %s",
                                df.format(ConstantValues.BACHELOR_MANDATORY - bachelorMandatoryCredits)),
                df.format(bachelorMandatoryCredits), df.format(ConstantValues.BACHELOR_MANDATORY)));
        sb.append(String.format("          GPA of Bachelor studies: %s%n", df2.format(bachelorGpa)));
        sb.append(String.format("          Title of BSc Thesis: \"%s\"%n", degrees.get(0).getTitleOfThesis()));
        sb.append(String.format("     Master credits: %s%n", df.format(masterCredits)));
        if (masterCredits >= ConstantValues.MASTER_CREDITS) {
            sb.append(String.format("          Total master's credits completed (%s/%s)%n",
                    df.format(masterCredits), df.format(ConstantValues.MASTER_CREDITS)));
        } else {
            sb.append(String.format("          Missing master's credits %s (%s/%s)%n",
                    df.format(ConstantValues.MASTER_CREDITS - masterCredits),
                    df.format(masterCredits),
                    df.format(ConstantValues.MASTER_CREDITS)));
        }
        sb.append(String.format("          %s (%s/%s)%n",
                masterMandatoryCredits >= ConstantValues.MASTER_MANDATORY
                        ? "All mandatory master credits completed"
                        : String.format("Missing mandatory master credits %s",
                                df.format(ConstantValues.MASTER_MANDATORY - masterMandatoryCredits)),
                df.format(masterMandatoryCredits), df.format(ConstantValues.MASTER_MANDATORY)));
        sb.append(String.format("          GPA of Master studies: %s%n", df2.format(masterGpa)));
        sb.append(String.format("          Title of MSc Thesis: \"%s\"%n", degrees.get(1).getTitleOfThesis()));
        return sb.toString();
    }

    public static void main(String[] args) {
        ResponsibleTeacher responsibleTeacher1 = new ResponsibleTeacher("Mouse", "Mickey");
        responsibleTeacher1.setBirthDate("230498-045T");
        MonthlyPayment responsibleTeacherPayment1 = new MonthlyPayment();
        responsibleTeacherPayment1.setSalary(756.85);
        responsibleTeacher1.setPayment(responsibleTeacherPayment1);

        AssistantTeacher assistantTeacher1 = new AssistantTeacher("The Dog", "Goofy");
        assistantTeacher1.setBirthDate("141200A2315");
        HourBasedPayment assistantTeacherPayment1 = new HourBasedPayment();
        assistantTeacherPayment1.setEurosPerHour(3.5);
        assistantTeacherPayment1.setHours(11.0);
        assistantTeacher1.setPayment(assistantTeacherPayment1);

        Student student1 = new Student("Duck", "Donald");

        Course[] courses = new Course[11];
        courses[0] = new Course("Programming 1", 811104, 'P', 1, 1, 5.0, true);
        courses[1] = new Course("All kinds of basic studies", 112233, 'P', 1, 2, 45.0, true);
        courses[2] = new Course("More basic studies", 223344, 'a', 1, 1, 50.5, true);
        courses[3] = new Course("Even more basic studies", 556677, 'a', 0, 3, 50.0, true);
        courses[4] = new Course("Final basic studies", 123123, 'A', 1, 4, 50.5, true);
        courses[5] = new Course("Programming 2", 616161, 'A', 1, 3, 25.0, true);
        courses[6] = new Course("All kinds of master studies", 717171, 'P', 0, 2, 45.0, true);
        courses[7] = new Course("More master studies", 818181, 'A', 1, 1, 25.0, true);
        courses[8] = new Course("Even more master studies", 919191, 'S', 1, 3, 20.0, true);
        courses[9] = new Course("Extra master studies", 666666, 'S', 0, 5, 8.0, false);
        courses[10] = new Course("Final master studies", 888888, 'S', 1, 5, 18.0, false);

        List<DesignatedCourse> designatedCourses = new ArrayList<>();
        designatedCourses.add(new DesignatedCourse(courses[2], false, 2023));
        designatedCourses.add(new DesignatedCourse(courses[3], false, 2023));
        designatedCourses.add(new DesignatedCourse(courses[9], false, 2022));
        designatedCourses.add(new DesignatedCourse(courses[10], false, 2023));

        responsibleTeacher1.setCourses(designatedCourses);
        assistantTeacher1.setCourses(designatedCourses);

        System.out.println(responsibleTeacher1.toString());
        System.out.println(assistantTeacher1.toString());

        List<StudentCourse> studentCourses = new ArrayList<>();
        studentCourses.add(new StudentCourse(courses[0], 1, 2013));
        studentCourses.add(new StudentCourse(courses[1], 1, 2014));
        studentCourses.add(new StudentCourse(courses[2], 1, 2015));
        studentCourses.add(new StudentCourse(courses[3], 4, 2016));
        studentCourses.add(new StudentCourse(courses[4], 5, 2017));
        studentCourses.add(new StudentCourse(courses[5], 1, 2018));
        studentCourses.add(new StudentCourse(courses[6], 1, 2019));
        studentCourses.add(new StudentCourse(courses[7], 2, 2020));
        studentCourses.add(new StudentCourse(courses[8], 0, 2021));
        studentCourses.add(new StudentCourse(courses[9], 'A', 2021));
        studentCourses.add(new StudentCourse(courses[10], 'f', 2022));

        List<StudentCourse> bachelorCourses = studentCourses.subList(0, 5);
        List<StudentCourse> masterCourses = studentCourses.subList(5, 11);

        student1.setDegreeTitle(ConstantValues.BACHELOR_TYPE, "Bachelor of Science");
        student1.setDegreeTitle(ConstantValues.MASTER_TYPE, "Master of Science");
        student1.setTitleOfThesis(ConstantValues.BACHELOR_TYPE, "Bachelor thesis title");
        student1.setTitleOfThesis(ConstantValues.MASTER_TYPE, "Master thesis title");

        for (StudentCourse course : bachelorCourses) {
            student1.addCourse(ConstantValues.BACHELOR_TYPE, course);
        }
        for (StudentCourse course : masterCourses) {
            student1.addCourse(ConstantValues.MASTER_TYPE, course);
        }

        student1.setStartYear(2001);
        student1.setGraduationYear(2020);

        System.out.println(student1.toString());

        student1.setBirthDate("230498-045T");
        student1.setTitleOfThesis(ConstantValues.BACHELOR_TYPE, "Christmas - The most wonderful time of the year");
        student1.setTitleOfThesis(ConstantValues.MASTER_TYPE, "Dreaming of a white Christmas");

        for (StudentCourse sc : masterCourses) {
            if (sc.getCourse().getCourseCode().equals("919191S")) {
                sc.setGrade(3);
                break;
            }
        }

        student1.setGraduationYear(2020);

        System.out.println(student1.toString());
        student1.printDegrees();
        student1.printCourses();

        Student student2 = new Student("Duck", "Daisy");

        StudentCourse[] studentCourses2 = new StudentCourse[11];
        studentCourses2[0] = new StudentCourse(courses[0], 1, 2013);
        studentCourses2[1] = new StudentCourse(courses[1], 1, 2014);
        studentCourses2[2] = new StudentCourse(courses[2], 1, 2015);
        studentCourses2[3] = new StudentCourse(courses[3], 4, 2016);
        studentCourses2[4] = new StudentCourse(courses[4], 5, 2017);
        studentCourses2[5] = new StudentCourse(courses[5], 1, 2018);
        studentCourses2[6] = new StudentCourse(courses[6], 1, 2019);
        studentCourses2[7] = new StudentCourse(courses[7], 2, 2020);
        studentCourses2[8] = new StudentCourse(courses[8], 2, 2021);
        studentCourses2[9] = new StudentCourse(courses[9], (int) 'A', 2021);
        studentCourses2[10] = new StudentCourse(courses[10], (int) 'f', 2022);

        List<StudentCourse> bachelorCourses2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            bachelorCourses2.add(studentCourses2[i]);
        }

        List<StudentCourse> masterCourses2 = new ArrayList<>();
        for (int i = 8; i < 11; i++) {
            masterCourses2.add(studentCourses2[i]);
        }

        student2.setDegreeTitle(0, "Bachelor of Science");

        student2.setDegreeTitle(1, "Master of Science");

        student2.setTitleOfThesis(0, "ABC");

        student2.setTitleOfThesis(1, "DEF");

        for (StudentCourse course : bachelorCourses2) {
            student2.addCourse(0, course);
        }

        for (StudentCourse course : masterCourses2) {
            student2.addCourse(1, course);
        }

        student2.setStartYear(2011);

        student2.setGraduationYear(2020);

        System.out.println(student2.toString());

        student2.printDegrees();

        student2.setBirthDate("120944-8638");

        for (StudentCourse course : masterCourses2) {
            if (course.getCourse().getCourseCode().equals("919191")) {
                course.setGrade(3);
                break;
            }
        }

        student2.setGraduationYear(2022);

        System.out.println(student2.toString());

        student2.printDegrees();
    }

}
