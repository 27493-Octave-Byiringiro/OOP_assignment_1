package q3;

import java.util.Scanner;

/*
  TaxAdministration.java
  - 10-class inheritance chain for Tax Administration System
  - final class TaxRecordTax with computeTax()
*/

class EntityTax {
    private int id;
    public EntityTax(int id) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        this.id = id;
    }
    public int getId(){ return id; }
}

class TaxAuthorityTax extends EntityTax {
    private String authorityName;
    private String region;
    private String email;
    public TaxAuthorityTax(int id, String authorityName, String region, String email) {
        super(id);
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("invalid email");
        this.authorityName = authorityName; this.region = region; this.email = email;
    }
}

class TaxCategoryTax extends TaxAuthorityTax {
    private String categoryName;
    private double rate;
    private String code;
    public TaxCategoryTax(int id, String authorityName, String region, String email, String categoryName, double rate, String code) {
        super(id, authorityName, region, email);
        if (rate <= 0) throw new IllegalArgumentException("rate must be > 0");
        if (code == null || code.length() < 3) throw new IllegalArgumentException("code must be >= 3 chars");
        this.categoryName = categoryName; this.rate = rate; this.code = code;
    }
    public double getRate(){ return rate; }
}

class TaxpayerTax extends TaxCategoryTax {
    private String tin;
    private String taxpayerName;
    private String address;
    public TaxpayerTax(int id, String authorityName, String region, String email, String categoryName, double rate, String code, String tin, String taxpayerName, String address) {
        super(id, authorityName, region, email, categoryName, rate, code);
        if (tin == null || !tin.matches("\\d{9}")) throw new IllegalArgumentException("TIN must be 9 digits");
        this.tin = tin; this.taxpayerName = taxpayerName; this.address = address;
    }
    public String getTin(){ return tin; }
}

class EmployerTax extends TaxpayerTax {
    private String employerName;
    private String employerTIN;
    private String contact;
    public EmployerTax(int id, String authorityName, String region, String email, String categoryName, double rate, String code,
                       String tin, String taxpayerName, String address,
                       String employerName, String employerTIN, String contact) {
        super(id, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address);
        if (employerTIN == null || !employerTIN.matches("\\d{9}")) throw new IllegalArgumentException("invalid employer TIN");
        if (contact == null || !contact.matches("\\d{10}")) throw new IllegalArgumentException("contact phone must be 10 digits");
        this.employerName = employerName; this.employerTIN = employerTIN; this.contact = contact;
    }
}

class EmployeeTax extends EmployerTax {
    private String employeeName;
    private double salary;
    private String employeeTIN;
    public EmployeeTax(int id, String authorityName, String region, String email, String categoryName, double rate, String code,
                       String tin, String taxpayerName, String address,
                       String employerName, String employerTIN, String contact,
                       String employeeName, double salary, String employeeTIN) {
        super(id, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact);
        if (salary <= 0) throw new IllegalArgumentException("salary must be > 0");
        if (employeeTIN == null || !employeeTIN.matches("\\d{9}")) throw new IllegalArgumentException("invalid employee TIN");
        this.employeeName = employeeName; this.salary = salary; this.employeeTIN = employeeTIN;
    }
    public double getSalary(){ return salary; }
}

class TaxDeclarationTax extends EmployeeTax {
    private String declarationMonth;
    private double totalIncome;
    public TaxDeclarationTax(int id, String authorityName, String region, String email, String categoryName, double rate, String code,
                             String tin, String taxpayerName, String address,
                             String employerName, String employerTIN, String contact,
                             String employeeName, double salary, String employeeTIN,
                             String declarationMonth, double totalIncome) {
        super(id, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact, employeeName, salary, employeeTIN);
        if (totalIncome < 0) throw new IllegalArgumentException("income must be >= 0");
        this.declarationMonth = declarationMonth; this.totalIncome = totalIncome;
    }
    public double getTotalIncome(){ return totalIncome; }
}

class TaxAssessmentTax extends TaxDeclarationTax {
    private String assessmentDate;
    private double assessedTax;
    public TaxAssessmentTax(int id, String authorityName, String region, String email, String categoryName, double rate, String code,
                            String tin, String taxpayerName, String address,
                            String employerName, String employerTIN, String contact,
                            String employeeName, double salary, String employeeTIN,
                            String declarationMonth, double totalIncome,
                            String assessmentDate, double assessedTax) {
        super(id, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact, employeeName, salary, employeeTIN, declarationMonth, totalIncome);
        if (assessedTax < 0) throw new IllegalArgumentException("assessedTax >= 0");
        this.assessmentDate = assessmentDate; this.assessedTax = assessedTax;
    }
    public double getAssessedTax(){ return assessedTax; }
}

class PaymentTax extends TaxAssessmentTax {
    private String paymentDate;
    private double paymentAmount;
    public PaymentTax(int id, String authorityName, String region, String email, String categoryName, double rate, String code,
                      String tin, String taxpayerName, String address,
                      String employerName, String employerTIN, String contact,
                      String employeeName, double salary, String employeeTIN,
                      String declarationMonth, double totalIncome,
                      String assessmentDate, double assessedTax,
                      String paymentDate, double paymentAmount) {
        super(id, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact, employeeName, salary, employeeTIN, declarationMonth, totalIncome, assessmentDate, assessedTax);
        if (paymentAmount <= 0) throw new IllegalArgumentException("paymentAmount must be > 0");
        this.paymentDate = paymentDate; this.paymentAmount = paymentAmount;
    }
    public double getPaymentAmount(){ return paymentAmount; }
}

final class TaxRecordTax extends PaymentTax {
    private String receiptNo;
    private double totalTax;
    private double rate;

    public TaxRecordTax(int id, String authorityName, String region, String email, String categoryName, double rate, String code,
                        String tin, String taxpayerName, String address,
                        String employerName, String employerTIN, String contact,
                        String employeeName, double salary, String employeeTIN,
                        String declarationMonth, double totalIncome,
                        String assessmentDate, double assessedTax,
                        String paymentDate, double paymentAmount,
                        String receiptNo) {
        super(id, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact, employeeName, salary, employeeTIN, declarationMonth, totalIncome, assessmentDate, assessedTax, paymentDate, paymentAmount);
        this.receiptNo = receiptNo;
        this.totalTax = assessedTax;
        this.rate = rate;
    }

    public void computeTax(){

         double salary = getSalary();
        double payable = (salary * rate) - getPaymentAmount();
        if (payable < 0) payable = 0;
        System.out.println("\n=== TAX ASSESSMENT ===");
        System.out.println("Receipt No: " + receiptNo);
        System.out.printf("Salary: %.2f\nRate: %.4f\nPayment/Credit: %.2f\nPayable Tax: %.2f\n", salary, rate, getPaymentAmount(), payable);
    }
}

public class TaxAdministration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("=== Tax Administration Input ===");
            System.out.print("ID (>0): "); int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Authority Name: "); String an = sc.nextLine().trim();
            System.out.print("Region: "); String region = sc.nextLine().trim();
            System.out.print("Authority Email: "); String aemail = sc.nextLine().trim();

            System.out.print("Category Name: "); String catName = sc.nextLine().trim();
            System.out.print("Rate (e.g., 0.15): "); double rate = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Category Code (>=3): "); String code = sc.nextLine().trim();

            System.out.print("Taxpayer TIN (9 digits): "); String tin = sc.nextLine().trim();
            System.out.print("Taxpayer Name: "); String tname = sc.nextLine().trim();
            System.out.print("Taxpayer Address: "); String addr = sc.nextLine().trim();

            System.out.print("Employer Name: "); String empName = sc.nextLine().trim();
            System.out.print("Employer TIN (9 digits): "); String empTin = sc.nextLine().trim();
            System.out.print("Employer Phone (10 digits): "); String empPhone = sc.nextLine().trim();

            System.out.print("Employee Name: "); String ename = sc.nextLine().trim();
            System.out.print("Employee Salary (>0): "); double salary = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Employee TIN (9 digits): "); String empTin2 = sc.nextLine().trim();

            System.out.print("Declaration Month: "); String dmonth = sc.nextLine().trim();
            System.out.print("Total Income (>=0): "); double income = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Assessment Date: "); String adate = sc.nextLine().trim();
            System.out.print("Assessed Tax (>=0): "); double atax = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Payment Date: "); String pdate = sc.nextLine().trim();
            System.out.print("Payment Amount (>0): "); double pamount = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Receipt No: "); String rno = sc.nextLine().trim();

            TaxRecordTax record = new TaxRecordTax(id, an, region, aemail, catName, rate, code, tin, tname, addr, empName, empTin, empPhone, ename, salary, empTin2, dmonth, income, adate, atax, pdate, pamount, rno);
            System.out.println("\n--- Entered Summary ---");
            System.out.println("Taxpayer: " + tname + " | Employer: " + empName);
            record.computeTax();

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            sc.close();
        }
    }
}
