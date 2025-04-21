package dev.m3s.programming2.homework3;

import java.util.Random;

public abstract class Person {

    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    public Person(String lName, String fName) {
        setLastName(lName != null ? lName : ConstantValues.NO_NAME);
        setFirstName(fName != null ? fName : ConstantValues.NO_NAME);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
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

    public String getBirthDate() {
        return birthDate;
    }

    public String setBirthDate(String personId) {
        PersonID objPersonID = new PersonID();
        if (personId == null)
            return "No change";
        if ("Ok".equals(objPersonID.setPersonID(personId))) {
            this.birthDate = objPersonID.getBirthDate();
            return this.birthDate;
        }
        return "No change";
    }

    protected int getRandomId(final int min, final int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    abstract public String getIdString();

}
