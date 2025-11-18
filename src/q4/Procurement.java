package q4;

import java.util.Scanner;

/*
  Procurement.java
  - 10-class inheritance chain for Procurement Management System
  - final class ProcurementReportProc with calculateTotal()
*/

class EntityProc {
    private int id;
    public EntityProc(int id) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        this.id = id;
    }
    public int getId(){ return id; }
}

class OrganizationProc extends EntityProc {
    private String orgName;
    private String address;
    private String contactEmail;
    public OrganizationProc(int id, String orgName, String address, String contactEmail){
        super(id);
        if (contactEmail == null || !contactEmail.contains("@")) throw new IllegalArgumentException("invalid email");
        this.orgName = orgName; this.address = address; this.contactEmail = contactEmail;
    }
}

class DepartmentProc extends OrganizationProc {
    private String deptName;
    private String deptCode;
    public DepartmentProc(int id, String orgName, String address, String contactEmail, String deptName, String deptCode){
        super(id, orgName, address, contactEmail);
        if (deptCode == null || deptCode.length() < 3) throw new IllegalArgumentException("deptCode >= 3");
        this.deptName = deptName; this.deptCode = deptCode;
    }
}

class SupplierProc extends DepartmentProc {
    private String supplierName;
    private String supplierTIN;
    private String contact;
    public SupplierProc(int id, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact){
        super(id, orgName, address, contactEmail, deptName, deptCode);
        if (supplierTIN == null || !supplierTIN.matches("\\d{9}")) throw new IllegalArgumentException("TIN must be 9 digits");
        if (contact == null || !contact.matches("\\d{10}")) throw new IllegalArgumentException("phone must be 10 digits");
        this.supplierName = supplierName; this.supplierTIN = supplierTIN; this.contact = contact;
    }
}

class ProductProc extends SupplierProc {
    private String productName;
    private double unitPrice;
    private int quantity;
    public ProductProc(int id, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity){
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact);
        if (unitPrice <= 0) throw new IllegalArgumentException("unitPrice > 0");
        if (quantity < 0) throw new IllegalArgumentException("quantity >= 0");
        this.productName = productName; this.unitPrice = unitPrice; this.quantity = quantity;
    }
    public double getTotalPrice(){ return unitPrice * quantity; }
}

class PurchaseOrderProc extends ProductProc {
    private String poNumber;
    private String orderDate;
    private double totalAmount;
    public PurchaseOrderProc(int id, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, String orderDate){
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity);
        double total = getTotalPrice();
        if (total <= 0) throw new IllegalArgumentException("total must be > 0");
        this.poNumber = poNumber; this.orderDate = orderDate; this.totalAmount = total;
    }
    public double getTotalAmount(){ return totalAmount; }
}

class DeliveryProc extends PurchaseOrderProc {
    private String deliveryDate;
    private String deliveredBy;
    public DeliveryProc(int id, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, String orderDate, String deliveryDate, String deliveredBy){
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate);
        if (deliveryDate == null) throw new IllegalArgumentException("deliveryDate required");
        this.deliveryDate = deliveryDate; this.deliveredBy = deliveredBy;
    }
}

class InspectionProc extends DeliveryProc {
    private String inspectorName;
    private String status;
    private String remarks;
    public InspectionProc(int id, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, String orderDate, String deliveryDate, String deliveredBy, String inspectorName, String status, String remarks){
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate, deliveryDate, deliveredBy);
        if (!"Passed".equals(status) && !"Failed".equals(status)) throw new IllegalArgumentException("status must be Passed/Failed");
        this.inspectorName = inspectorName; this.status = status; this.remarks = remarks;
    }
}

class InvoiceProc extends InspectionProc {
    private String invoiceNo;
    private double invoiceAmount;
    public InvoiceProc(int id, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, String orderDate, String deliveryDate, String deliveredBy, String inspectorName, String status, String remarks, String invoiceNo, double invoiceAmount){
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate, deliveryDate, deliveredBy, inspectorName, status, remarks);
        if (invoiceAmount <= 0) throw new IllegalArgumentException("invoice amount > 0");
        this.invoiceNo = invoiceNo; this.invoiceAmount = invoiceAmount;
    }
    public double getInvoiceAmount(){ return invoiceAmount; }
}

final class ProcurementReportProc extends InvoiceProc {
    private String reportDate;
    private String summary;
    public ProcurementReportProc(int id, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, String orderDate, String deliveryDate, String deliveredBy, String inspectorName, String status, String remarks, String invoiceNo, double invoiceAmount, String reportDate, String summary){
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate, deliveryDate, deliveredBy, inspectorName, status, remarks, invoiceNo, invoiceAmount);
        if (reportDate == null) throw new IllegalArgumentException("reportDate required");
        this.reportDate = reportDate; this.summary = summary;
    }

    public void calculateTotal() {

         double total = getInvoiceAmount();
        System.out.println("\n=== PROCUREMENT REPORT ===");
        System.out.println("Report Date: " + reportDate);
        System.out.println("Invoice Amount: " + total);
        System.out.println("Summary: " + summary);
    }
}

public class Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("=== Procurement Management Input ===");
            System.out.print("ID (>0): "); int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Organization Name: "); String org = sc.nextLine().trim();
            System.out.print("Organization Address: "); String addr = sc.nextLine().trim();
            System.out.print("Organization Email: "); String oemail = sc.nextLine().trim();

            System.out.print("Department Name: "); String dept = sc.nextLine().trim();
            System.out.print("Department Code (>=3): "); String dcode = sc.nextLine().trim();

            System.out.print("Supplier Name: "); String sname = sc.nextLine().trim();
            System.out.print("Supplier TIN (9 digits): "); String stin = sc.nextLine().trim();
            System.out.print("Supplier Phone (10 digits): "); String sphone = sc.nextLine().trim();

            System.out.print("Product Name: "); String pname = sc.nextLine().trim();
            System.out.print("Unit Price (>0): "); double unit = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Quantity (>=0): "); int qty = Integer.parseInt(sc.nextLine().trim());

            System.out.print("PO Number: "); String po = sc.nextLine().trim();
            System.out.print("Order Date: "); String odate = sc.nextLine().trim();
            System.out.print("Delivery Date: "); String ddate = sc.nextLine().trim();
            System.out.print("Delivered By: "); String dby = sc.nextLine().trim();

            System.out.print("Inspector Name: "); String iname = sc.nextLine().trim();
            System.out.print("Inspection Status (Passed/Failed): "); String status = sc.nextLine().trim();
            System.out.print("Inspection Remarks: "); String remarks = sc.nextLine().trim();

            System.out.print("Invoice No: "); String invNo = sc.nextLine().trim();
            System.out.print("Invoice Amount (>0): "); double invAmt = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Report Date: "); String rdate = sc.nextLine().trim();
            System.out.print("Summary: "); String summary = sc.nextLine().trim();

            ProcurementReportProc report = new ProcurementReportProc(id, org, addr, oemail, dept, dcode, sname, stin, sphone, pname, unit, qty, po, odate, ddate, dby, iname, status, remarks, invNo, invAmt, rdate, summary);

            System.out.println("\n--- Summary ---");
            System.out.println("Product: " + pname + " | Supplier: " + sname + " | Quantity: " + qty);
            report.calculateTotal();

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            sc.close();
        }
    }
}
