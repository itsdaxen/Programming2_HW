package dev.m3s.programming2.homework3;

import java.time.Year;

public abstract class Employee extends Person implements Payment {

    private String empId; // Generated randomly, having the format “xxxx_nnnn”
    // - where xxxx is the prefix from the class of the actual object
    // - where nnnn is the identification number.
    // Possible values 2001-3000.
    // For example, “OY_TEACHER_2340” (for a teacher)

    private int startYear; // Studies must have started after 2000 and the year cannot be in the future,
    // i.e., 2000 < startYear <= current year.
    // By default, current year

    private Payment payment; // The attribute may be either of type HourBasedPayment or
    // MontlyPayment.

    public Employee(String lname, String fname) {
        super(lname, fname);
        // TODO: Handle string part
        // Generated randomly, having the format “xxxx_nnnn”
        // - where xxxx is the prefix from the class of the actual object
        // - where nnnn is the identification number.
        // Possible values 2001-3000.
        // For example, “OY_TEACHER_2340” (for a teacher)
        empId = String.valueOf(getRandomId(ConstantValues.MIN_EMP_ID, ConstantValues.MAX_EMP_ID));
        startYear = Year.now().getValue();
    }

    public String getIdString() {
        return String.format("%s%s", getEmployeeIdString(), empId);
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(final int startYear) {
        if (startYear > 2000 && startYear <= Year.now().getValue()) {
            this.startYear = startYear;
        }
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        if (payment != null) {
            this.payment = payment;
        }
    }

    public double calculatePayment() {
        // TODO: Implement
        if (payment == null) {
            return 0.0;
        }
        return payment.calculatePayment();
    }

    abstract protected String getEmployeeIdString();

}
