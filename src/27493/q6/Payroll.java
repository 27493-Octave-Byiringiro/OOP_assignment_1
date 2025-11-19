package q6;

import java.util.Scanner;



class EntityPay {
    private int id;
    public EntityPay(int id) {
        if (id <= 0) throw new IllegalArgumentException("id > 0");
        this.id = id;
    }
    public int getId(){ return id; }
}

class OrganizationPay extends EntityPay {
    private String orgName;
    private String orgCode;
    private String rssbNumber;
    private String contactEmail;
    public OrganizationPay(int id, String orgName, String orgCode, String rssbNumber, String contactEmail) {
        super(id);
        if (rssbNumber == null || !rssbNumber.matches("\\d{8}")) throw new IllegalArgumentException("rssbNumber must be 8 digits");
        if (contactEmail == null || !contactEmail.contains("@")) throw new IllegalArgumentException("invalid email");
        this.orgName = orgName; this.orgCode = orgCode; this.rssbNumber = rssbNumber; this.contactEmail = contactEmail;
    }
}

class DepartmentPay extends OrganizationPay {
    private String deptName;
    private String deptCode;
    private String managerName;
    public DepartmentPay(int id, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName) {
        super(id, orgName, orgCode, rssbNumber, contactEmail);
        if (deptCode == null || deptCode.length() < 3) throw new IllegalArgumentException("deptCode >= 3");
        this.deptName = deptName; this.deptCode = deptCode; this.managerName = managerName;
    }
}

class EmployeePay extends DepartmentPay {
    private int employeeID;
    private String fullName;
    private String position;
    private double baseSalary;
    private boolean rssbRegistered;
    public EmployeePay(int id, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered) {
        super(id, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName);
        if (employeeID < 1000) throw new IllegalArgumentException("employeeID must be >= 1000");
        if (baseSalary <= 0) throw new IllegalArgumentException("baseSalary > 0");
        this.employeeID = employeeID; this.fullName = fullName; this.position = position; this.baseSalary = baseSalary; this.rssbRegistered = rssbRegistered;
    }
    public double getBaseSalary(){ return baseSalary; }
    public String getFullName(){ return fullName; }
}

class PayrollPeriodPay extends EmployeePay {
    private int month;
    private int year;
    private String startDate;
    private String endDate;
    public PayrollPeriodPay(int id, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, String startDate, String endDate) {
        super(id, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered);
        if (month < 1 || month > 12) throw new IllegalArgumentException("month 1-12");
        if (year < 2000) throw new IllegalArgumentException("year >= 2000");
        this.month = month; this.year = year; this.startDate = startDate; this.endDate = endDate;
    }
}

class SalaryStructurePay extends PayrollPeriodPay {
    private double basicPay;
    private double transportAllowance;
    private double housingAllowance;
    public SalaryStructurePay(int id, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, String startDate, String endDate, double basicPay, double transportAllowance, double housingAllowance) {
        super(id, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate);
        if (basicPay < 0 || transportAllowance < 0 || housingAllowance < 0) throw new IllegalArgumentException("allowances must be >= 0");
        this.basicPay = basicPay; this.transportAllowance = transportAllowance; this.housingAllowance = housingAllowance;
    }
    public double getBasicPay(){ return basicPay; }
    public double getTotalAllowances(){ return transportAllowance + housingAllowance; }
}

class DeductionPay extends SalaryStructurePay {
    private double rssbContribution;
    private double payeTax;
    private double loanDeduction;
    public DeductionPay(int id, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, String startDate, String endDate, double basicPay, double transportAllowance, double housingAllowance, double loanDeduction) {
        super(id, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate, basicPay, transportAllowance, housingAllowance);
        if (loanDeduction < 0) throw new IllegalArgumentException("loanDeduction must be >= 0");
        this.rssbContribution = basicPay * 0.05;
        this.payeTax = basicPay * 0.15;
        this.loanDeduction = loanDeduction;
    }
    public double getTotalDeductions(){ return rssbContribution + payeTax + loanDeduction; }
    public double getRssbContribution(){ return rssbContribution; }
}

class AllowancePay extends DeductionPay {
    private double overtimeHours;
    private double overtimeRate;
    private double bonus;
    public AllowancePay(int id, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, String startDate, String endDate, double basicPay, double transportAllowance, double housingAllowance, double loanDeduction, double overtimeHours, double overtimeRate, double bonus) {
        super(id, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate, basicPay, transportAllowance, housingAllowance, loanDeduction);
        if (overtimeHours < 0 || overtimeRate < 0 || bonus < 0) throw new IllegalArgumentException("overtime/bonus must be >= 0");
        this.overtimeHours = overtimeHours; this.overtimeRate = overtimeRate; this.bonus = bonus;
    }
    public double getOvertimeAmount(){ return overtimeHours * overtimeRate; }
    public double getBonus(){ return bonus; }
}

class PayrollPay extends AllowancePay {
    private double grossSalary;
    private double totalDeductions;
    private double netSalary;

    public PayrollPay(int id, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, String startDate, String endDate, double basicPay, double transportAllowance, double housingAllowance, double loanDeduction, double overtimeHours, double overtimeRate, double bonus) {
        super(id, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate, basicPay, transportAllowance, housingAllowance, loanDeduction, overtimeHours, overtimeRate, bonus);
        recompute();
    }

    private void recompute() {
        this.grossSalary = getBasicPay() + getTotalAllowances() + getOvertimeAmount() + getBonus();
        this.totalDeductions = getTotalDeductions();
        this.netSalary = grossSalary - totalDeductions;
    }

    public double getGrossSalary(){ return grossSalary; }
    public double getTotalDeductions(){ return totalDeductions; }
    public double getNetSalary(){ return netSalary; }
}

final class PayslipPay extends PayrollPay {
    private int payslipNumber;
    private String issueDate;
    public PayslipPay(int id, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, String startDate, String endDate, double basicPay, double transportAllowance, double housingAllowance, double loanDeduction, double overtimeHours, double overtimeRate, double bonus, int payslipNumber, String issueDate) {
        super(id, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate, basicPay, transportAllowance, housingAllowance, loanDeduction, overtimeHours, overtimeRate, bonus);
        this.payslipNumber = payslipNumber; this.issueDate = issueDate;
    }

    public void generatePayslip() {
        System.out.println("\n=== PAYSLIP ===");
        System.out.println("Payslip No: " + payslipNumber);
        System.out.println("Issue Date: " + issueDate);
        System.out.printf("Employee: %s\n", getFullName());
        System.out.printf("Gross Salary: %.2f\n", getGrossSalary());
        System.out.printf("Total Deductions: %.2f\n", getTotalDeductions());
        System.out.printf("Net Salary: %.2f\n", getNetSalary());
        System.out.printf("RSSB Contribution (5%% of basic): %.2f\n", getRssbContribution());
    }
}

public class Payroll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("=== Payroll (RSSB) Input ===");
            System.out.print("ID (>0): "); int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Organization Name: "); String org = sc.nextLine().trim();
            System.out.print("Organization Code: "); String orgCode = sc.nextLine().trim();
            System.out.print("RSSB Number (8 digits): "); String rssb = sc.nextLine().trim();
            System.out.print("Contact Email: "); String oemail = sc.nextLine().trim();

            System.out.print("Department Name: "); String dept = sc.nextLine().trim();
            System.out.print("Department Code: "); String dcode = sc.nextLine().trim();
            System.out.print("Manager Name: "); String manager = sc.nextLine().trim();

            System.out.print("Employee ID (>=1000): "); int eid = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Full Name: "); String fname = sc.nextLine().trim();
            System.out.print("Position: "); String pos = sc.nextLine().trim();
            System.out.print("Base Salary (>0): "); double baseSalary = Double.parseDouble(sc.nextLine().trim());
            System.out.print("RSSB Registered (true/false): "); boolean reg = Boolean.parseBoolean(sc.nextLine().trim());

            System.out.print("Month (1-12): "); int month = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Year (>=2000): "); int year = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Start Date: "); String start = sc.nextLine().trim();
            System.out.print("End Date: "); String end = sc.nextLine().trim();

            System.out.print("Basic Pay (>=0): "); double basicPay = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Transport Allowance (>=0): "); double transport = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Housing Allowance (>=0): "); double housing = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Loan Deduction (>=0): "); double loan = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Overtime Hours (>=0): "); double ohours = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Overtime Rate (>=0): "); double orate = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Bonus (>=0): "); double bonus = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Payslip Number: "); int pno = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Issue Date: "); String issue = sc.nextLine().trim();

            PayslipPay payslip = new PayslipPay(id, org, orgCode, rssb, oemail, dept, dcode, manager, eid, fname, pos, baseSalary, reg, month, year, start, end, basicPay, transport, housing, loan, ohours, orate, bonus, pno, issue);

            System.out.println("\n--- Payroll Summary ---");
            payslip.generatePayslip();

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            sc.close();
        }
    }
}
