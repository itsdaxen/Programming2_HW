package dev.m3s.programming2.homework2;

import java.text.DecimalFormat;
import java.time.Year;
import java.util.Random;

public class Student {

    private String firstName;
    private String lastName;
    private int id;
    private int startYear;
    private int graduationYear;
    private static final int degreeCount = 3;
    private Degree[] degrees = new Degree[degreeCount];
    private String birthDate;
    // Create personID for our student
    private PersonID personID = new PersonID();

    public Student() {
        this.firstName = ConstantValues.NO_NAME;
        this.lastName = ConstantValues.NO_NAME;
        this.id = getRandomId();
        this.startYear = Year.now().getValue();
        this.graduationYear = 0;
        this.birthDate = ConstantValues.NO_BIRTHDATE;
        for (int i = 0; i < degrees.length; i++) {
            degrees[i] = new Degree();
        }
    }

    public Student(String lname, String fname) {
        this();
        this.lastName = lname != null ? lname : ConstantValues.NO_NAME;
        this.firstName = fname != null ? fname : ConstantValues.NO_NAME;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        if (id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID) {
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
        if (i >= 0 && i < degrees.length && dName != null) {
            degrees[i].setDegreeTitle(dName);
        }
    }

    public boolean addCourse(final int i, StudentCourse course) {
        if (i >= 0 && i < degrees.length && course != null) {
            return degrees[i].addStudentCourse(course);
        }
        return false;
    }

    public int addCourses(final int i, StudentCourse[] courses) {
        if (i >= 0 && i < degrees.length && courses != null) {
            int added = 0;
            for (StudentCourse c : courses) {
                if (c != null && degrees[i].addStudentCourse(c)) {
                    added++;
                }
            }
            return added;
        }
        return 0;
    }


    public void printCourses() {
        for (Degree degree : degrees) {
            if (degree != null) degree.printCourses();
        }
    }

    public void printDegrees() {
        for (Degree degree : degrees) {
            if (degree != null) System.out.println(degree.toString());
        }
    }

    public void setTitleOfThesis(final int i, String title) {
        if (i >= 0 && i < degrees.length && title != null) {
            degrees[i].setTitleOfThesis(title);
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String setBirthDate(String personId) {
        if (personId == null) return "No change";
        if ("Ok".equals(personID.setPersonID(personId))) {
            this.birthDate = personID.getBirthDate();
            return this.birthDate;
        }
        return "No change";
    }

    public boolean hasGraduated() {
        return graduationYear > 0;
    }

    private boolean canGraduate() {
        Degree bachelor = degrees[0];
        Degree master = degrees[1];
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

    private int getRandomId() {
        return new Random().nextInt(ConstantValues.MAX_ID - ConstantValues.MIN_ID + 1) + ConstantValues.MIN_ID;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.0");
        double bachelorCredits = degrees[0].getCredits();
        double masterCredits = degrees[1].getCredits();
        double totalCredits = bachelorCredits + masterCredits;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Student id: %d%n", id));
        sb.append(String.format("     First name: %s, Last name: %s%n", firstName, lastName));
        if (ConstantValues.NO_BIRTHDATE.equals(birthDate)) {
            sb.append(String.format("     Date of birth: \"%s\"%n", birthDate));
        } else {
            sb.append(String.format("     Date of birth: %s%n", birthDate));
        }
        if (hasGraduated()) {
            sb.append(String.format("     Status: The student has graduated in %d%n", graduationYear));
        } else {
            sb.append("     Status: The student has not graduated, yet\n");
        }
        sb.append(String.format("     Start year: %d (studies %s for %d years)%n",
                startYear,
                hasGraduated() ? "lasted" : "have lasted",
                getStudyYears()));
        sb.append(String.format("     Total credits: %s%n", df.format(totalCredits)));
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
        sb.append(String.format("          Title of BSc Thesis: \"%s\"%n", degrees[0].getTitleOfThesis()));
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
        sb.append(String.format("          Title of MSc Thesis: \"%s\"%n", degrees[1].getTitleOfThesis()));
        return sb.toString();
    }

    public static void main(String[] args) {
        Student student = new Student();
        Course c1 = new Course("Programming 1", 811104, 'P', 1, 1, 5.0, true);
        Course c2 = new Course("All kinds of basic studies", 112233, 'P', 1, 2, 45.0, true);
        Course c3 = new Course("More basic studies", 223344, 'a', 1, 1, 50.5, true);
        Course c4 = new Course("Even more basic studies", 556677, 'a', 0, 3, 50.0, true);
        Course c5 = new Course("Final basic studies", 123123, 'A', 1, 4, 50.5, true);
        Course c6 = new Course("Programming 2", 616161, 'A', 1, 3, 25.0, true);
        Course c7 = new Course("All kinds of master studies", 717171, 'P', 0, 2, 45.0, true);
        Course c8 = new Course("More master studies", 818181, 'A', 1, 1, 25.0, true);
        Course c9 = new Course("Even more master studies", 919191, 'S', 1, 3, 20.0, true);
        Course c10 = new Course("Extra master studies", 666666, 'S', 0, 5, 8.0, false);
        Course c11 = new Course("Final master studies", 888888, 'S', 1, 5, 18.0, false);
        StudentCourse sc1 = new StudentCourse(c1, 1, 2013);
        StudentCourse sc2 = new StudentCourse(c2, 1, 2014);
        StudentCourse sc3 = new StudentCourse(c3, 1, 2015);
        StudentCourse sc4 = new StudentCourse(c4, 4, 2016);
        StudentCourse sc5 = new StudentCourse(c5, 5, 2017);
        StudentCourse sc6 = new StudentCourse(c6, 1, 2018);
        StudentCourse sc7 = new StudentCourse(c7, 1, 2019);
        StudentCourse sc8 = new StudentCourse(c8, 2, 2020);
        StudentCourse sc9 = new StudentCourse(c9, 0, 2021);
        StudentCourse sc10 = new StudentCourse(c10, 'A', 2021);
        StudentCourse sc11 = new StudentCourse(c11, 'f', 2022);
        StudentCourse[] bachelorCourses = {sc1, sc2, sc3, sc4, sc5};
        StudentCourse[] masterCourses = {sc6, sc7, sc8, sc9, sc10, sc11};
        student.setDegreeTitle(0, "Bachelor of Science");
        student.setDegreeTitle(1, "Master of Science");
        student.setTitleOfThesis(0, "Bachelor thesis title");
        student.setTitleOfThesis(1, "Masters thesis title");
        student.addCourses(0, bachelorCourses); // Bachelor
        student.addCourses(1, masterCourses);   // Master
        student.setStartYear(2001);
        student.setGraduationYear(2020);
        student.setFirstName("Donald");
        student.setLastName("Duck");
        System.out.println(student.toString());
        student.setBirthDate("230498-045T");
        student.setTitleOfThesis(0, "Christmas - The most wonderful time of the year");
        student.setTitleOfThesis(1, "Dreaming of a white Christmas");
        student.printDegrees();
        sc9.setGrade(3);
        System.out.println(student.toString());
        student.printDegrees();
        student.printCourses();
        sc11.setGrade('X');
        System.out.println(sc11.toString());
        sc11.setGrade('a');
        System.out.println(sc11.toString());
        sc1.setGrade(6);
        System.out.println(sc1.toString());
        sc1.setGrade(5);
        System.out.println(sc1.toString());
    }

}
