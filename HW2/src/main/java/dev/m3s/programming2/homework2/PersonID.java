package dev.m3s.programming2.homework2;

public class PersonID {

    // Attributes
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    // Methods
    public String getBirthDate() {
        return birthDate;
    }

    public String setPersonID(final String personID) {
        if (personID == null) {
            return ConstantValues.INVALID_BIRTHDAY;
        }
        if (!checkPersonIDNumber(personID)) {
            return ConstantValues.INVALID_BIRTHDAY;
        }
        String day = personID.substring(0, 2);
        String month = personID.substring(2, 4);
        String yearPrefix = (personID.charAt(6) == '+') ? "18" : (personID.charAt(6) == '-') ? "19" : "20";
        String year = yearPrefix + personID.substring(4, 6);
        String tempBirthDate = day + "." + month + "." + year;
        if (!checkBirthdate(tempBirthDate)) {
            return ConstantValues.INVALID_BIRTHDAY;
        }
        if (!checkValidCharacter(personID)) {
            this.birthDate = ConstantValues.NO_BIRTHDATE;
            return ConstantValues.INCORRECT_CHECKMARK;
        }
        this.birthDate = tempBirthDate;
        return "Ok";
    }
    private boolean checkPersonIDNumber(final String personID) {
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
        if (month == 2) {
            daysInMonth = checkLeapYear(year) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else {
            daysInMonth = 31;
        }
        return day <= daysInMonth;
    }
}
