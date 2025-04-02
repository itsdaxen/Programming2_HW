package dev.m3s.programming2.homework1;

import java.util.Random;
import java.time.Year;
import java.text.DecimalFormat;


public class Student {

    // attributes
    private String firstName;
    private String lastName;
    private int id;
    private double bachelorCredits;
    private double masterCredits;
    private String titleOfMastersThesis;
    private String titleOfBachelorsThesis;
    private int startYear;
    private int graduationYear;
    private String birthDate;

    // constructors
    public Student() {
        this.firstName = ConstantValues.NO_NAME;
        this.lastName = ConstantValues.NO_NAME;
        this.id = getRandomId();
        this.bachelorCredits = 0.0;
        this.masterCredits = 0.0;
        this.titleOfMastersThesis = ConstantValues.NO_TITLE;
        this.titleOfBachelorsThesis = ConstantValues.NO_TITLE;
        this.startYear = Year.now().getValue();
        this.graduationYear = 0;
        this.birthDate = ConstantValues.NO_BIRTHDATE;
    }
    public Student(String lname, String fname){
        this();
        this.lastName = (lname != null) ? lname : ConstantValues.NO_NAME;
        this.firstName = (fname != null) ? fname : ConstantValues.NO_NAME;
}

    // methods
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

    public double getBachelorCredits() {
        return bachelorCredits;
    }

    public void setBachelorCredits(final double bachelorCredits) {
        if (bachelorCredits >= ConstantValues.MIN_CREDITS && bachelorCredits <= ConstantValues.MAX_CREDITS) {
            this.bachelorCredits = bachelorCredits;
        }
    }

    public double getMasterCredits() {
        return masterCredits;
    }

    public void setMasterCredits(final double masterCredits) {
        if (masterCredits >= ConstantValues.MIN_CREDITS && masterCredits <= ConstantValues.MAX_CREDITS) {
            this.masterCredits = masterCredits;
            }
    }

    public String getTitleOfMastersThesis() {
        return titleOfMastersThesis;
    }

    public void setTitleOfMastersThesis(String titleOfMastersThesis) {
        if (titleOfMastersThesis != null) {
            this.titleOfMastersThesis = titleOfMastersThesis;
        }
    }

    public String getTitleOfBachelorsThesis() {
        return titleOfBachelorsThesis;
    }

    public void setTitleOfBachelorsThesis(String titleOfBachelorsThesis) {
        if (titleOfBachelorsThesis != null) {
            this.titleOfBachelorsThesis = titleOfBachelorsThesis;
        }
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(final int startYear) {
        int currentYear = java.time.Year.now().getValue();
        if (startYear > 2000 && startYear <= currentYear) {
            this.startYear = startYear;
        }
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public String setGraduationYear(final int year) {
        if (!canGraduate()) {
            return "Check the required studies";
        }
        if (year < startYear || year > Year.now().getValue()) {
            return "Check graduation year";
        }
        this.graduationYear = year;
        return "Ok";
    }

    public boolean hasGraduated() {
        return graduationYear > 0;
    }

    private boolean canGraduate() {
        return bachelorCredits >= ConstantValues.BACHELOR_CREDITS &&
                masterCredits >= ConstantValues.MASTER_CREDITS &&
                !titleOfBachelorsThesis.equals(ConstantValues.NO_TITLE) &&
                !titleOfMastersThesis.equals(ConstantValues.NO_TITLE);
    }
    public int getStudyYears() {
        if (graduationYear > 0) {
            return graduationYear - startYear;
        } else {
            return Year.now().getValue() - startYear;
        }
    }
    private int getRandomId() {
        return new Random().nextInt(100) + 1;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.0");
        return "Student id: " + id + "\n" +
                " FirstName: " + firstName + ", LastName: " + lastName + "\n" +
                " Date of birth: " + birthDate + "\n" +
                " Status: " + (hasGraduated() ? "The student has graduated in " + graduationYear : "The student has not graduated, yet") + "\n" +
                " StartYear: " + startYear + " (studies have lasted for " + getStudyYears() + " years)\n" +
                " BachelorCredits: " + df.format(bachelorCredits) + " ==> " +
                (bachelorCredits >= ConstantValues.BACHELOR_CREDITS
                        ? "All required bachelor's credits completed (" + df.format(bachelorCredits) + "/" + df.format(ConstantValues.BACHELOR_CREDITS) + ")"
                        : "Missing bachelor's credits " + df.format(ConstantValues.BACHELOR_CREDITS - bachelorCredits)
                        + " (" + df.format(bachelorCredits) + "/" + df.format(ConstantValues.BACHELOR_CREDITS) + ")") + "\n" +
                " TitleOfBachelorThesis: \"" + titleOfBachelorsThesis + "\"\n" +
                " MasterCredits: " + df.format(masterCredits) + " ==> " +
                (masterCredits >= ConstantValues.MASTER_CREDITS
                        ? "All required master's credits completed (" + df.format(masterCredits) + "/" + df.format(ConstantValues.MASTER_CREDITS) + ")"
                        : "Missing master's credits " + df.format(ConstantValues.MASTER_CREDITS - masterCredits)
                        + " (" + df.format(masterCredits) + "/" + df.format(ConstantValues.MASTER_CREDITS) + ")") + "\n" +
                " TitleOfMastersThesis: \"" + titleOfMastersThesis + "\"\n";
    }


    public String setPersonId(final String personId) {
        if (personId == null) {
            return ConstantValues.INVALID_BIRTHDAY;
        }
        if (!checkPersonIdNumber(personId)) {
            return ConstantValues.INVALID_BIRTHDAY;
        }
        String day = personId.substring(0, 2);
        String month = personId.substring(2, 4);
        String yearPrefix = (personId.charAt(6) == '+') ? "18" : (personId.charAt(6) == '-') ? "19" : "20";
        String year = yearPrefix + personId.substring(4, 6);
        String tempBirthDate = day + "." + month + "." + year;
        if (!checkBirthdate(tempBirthDate)) {
            return ConstantValues.INVALID_BIRTHDAY;
        }
        this.birthDate = tempBirthDate;
        if (!checkValidCharacter(personId)) {
            this.birthDate = ConstantValues.NO_BIRTHDATE;
            return ConstantValues.INCORRECT_CHECKMARK;
        }
        return "Ok";
    }

    private boolean checkPersonIdNumber(final String personID) {
        if (personID == null) {
            throw new IllegalArgumentException("Person ID cannot be null");
        }
        return personID.length() == 11 && (personID.charAt(6) == '+' || personID.charAt(6) == '-' || personID.charAt(6) == 'A');
    }

    private boolean checkLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    private boolean checkValidCharacter(final String personID) {
        String validCharacters = "0123456789ABCDEFHJKLMNPRSTUVWXY";
        int number = Integer.parseInt(personID.substring(0, 6) + personID.substring(7, 10));
        return personID.charAt(10) == validCharacters.charAt(number % 31);
    }
    private boolean checkBirthdate(final String date) {
        String[] parts = date.split("\\.");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if (year < 1800 || month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }
        int daysInMonth;
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12) {
            daysInMonth = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else {
            if (checkLeapYear(year)) {
                daysInMonth = 29;
            } else {
                daysInMonth = 28;
            }
        }
            return day <= daysInMonth;
    }

    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student("Mouse", "Mickey");
        Student student3 = new Student("Mouse", "Minnie");
        student1.setFirstName("Donald");
        student1.setLastName("Duck");
        student1.setBachelorCredits(120);
        student1.setMasterCredits(180);
        student1.setTitleOfMastersThesis("Masters thesis title");
        student1.setTitleOfBachelorsThesis("Bachelor thesis title");
        student1.setStartYear(2001);
        student1.setGraduationYear(2020);
        student2.setPersonId("221199-123A");
        student2.setTitleOfBachelorsThesis("A new exciting purpose of life");
        student2.setBachelorCredits(65);
        student2.setMasterCredits(22);
        student3.setPersonId("111111-3334");
        student3.setBachelorCredits(215);
        student3.setMasterCredits(120);
        student3.setTitleOfMastersThesis("Christmas - The most wonderful time of the year");
        student3.setTitleOfBachelorsThesis("Dreaming of a white Christmas");
        student3.setStartYear(2018);
        student3.setGraduationYear(2022);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
        System.out.println(student1.setPersonId("This is a string"));
        System.out.println(student1.setPersonId("320187-1234"));
        System.out.println(student1.setPersonId("11111111-3334"));
        System.out.println(student1.setPersonId("121298-830A"));
    }

}


