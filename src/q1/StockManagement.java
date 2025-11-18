package q1;

import java.util.Scanner;

/*
  StockManagement.java
  - 10-class inheritance chain for Stock Management System
  - final class StockReportStock with generateReport()
*/

class EntityStock {
    private int id;
    private String createdDate;
    private String updatedDate;

    public EntityStock(int id, String createdDate, String updatedDate) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        if (createdDate == null || updatedDate == null) throw new IllegalArgumentException("dates must not be null");
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public int getId(){ return id; }
    public String getCreatedDate(){ return createdDate; }
    public String getUpdatedDate(){ return updatedDate; }
}

class WarehouseStock extends EntityStock {
    private String warehouseName;
    private String location;
    private String contactNumber;

    public WarehouseStock(int id, String createdDate, String updatedDate, String warehouseName, String location, String contactNumber) {
        super(id, createdDate, updatedDate);
        if (contactNumber == null || !contactNumber.matches("\\d{10}"))
            throw new IllegalArgumentException("phone must be 10 digits");
        this.warehouseName = warehouseName;
        this.location = location;
        this.contactNumber = contactNumber;
    }

    public String getWarehouseName(){ return warehouseName; }
    public String getLocation(){ return location; }
    public String getContactNumber(){ return contactNumber; }
}

class CategoryStock extends WarehouseStock {
    private String categoryName;
    private String categoryCode;

    public CategoryStock(int id, String cd, String ud, String wn, String loc, String phone, String categoryName, String categoryCode) {
        super(id, cd, ud, wn, loc, phone);
        if (categoryCode == null || categoryCode.length() < 3 || !categoryCode.matches("[A-Za-z0-9]+"))
            throw new IllegalArgumentException("Category code must be alphanumeric and at least 3 chars");
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }
    public String getCategoryName(){ return categoryName; }
    public String getCategoryCode(){ return categoryCode; }
}

class SupplierStock extends CategoryStock {
    private String supplierName;
    private String supplierEmail;
    private String supplierPhone;

    public SupplierStock(int id, String cd, String ud, String wn, String loc, String phone,
                         String categoryName, String categoryCode,
                         String supplierName, String supplierEmail, String supplierPhone) {
        super(id, cd, ud, wn, loc, phone, categoryName, categoryCode);
        if (supplierEmail == null || !supplierEmail.contains("@")) throw new IllegalArgumentException("Invalid email");
        if (supplierPhone == null || !supplierPhone.matches("\\d{10}")) throw new IllegalArgumentException("phone must be 10 digits");
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierName(){ return supplierName; }
    public String getSupplierEmail(){ return supplierEmail; }
    public String getSupplierPhone(){ return supplierPhone; }
}

class ProductStock extends SupplierStock {
    private String productName;
    private double unitPrice;
    private int stockLimit;

    public ProductStock(int id, String cd, String ud, String wn, String loc, String phone,
                        String categoryName, String categoryCode,
                        String supplierName, String supplierEmail, String supplierPhone,
                        String productName, double unitPrice, int stockLimit) {
        super(id, cd, ud, wn, loc, phone, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone);
        if (unitPrice <= 0) throw new IllegalArgumentException("unitPrice must be > 0");
        if (stockLimit < 0) throw new IllegalArgumentException("stockLimit must be >= 0");
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stockLimit = stockLimit;
    }

    public String getProductName(){ return productName; }
    public double getUnitPrice(){ return unitPrice; }
    public int getStockLimit(){ return stockLimit; }
}

class StockItem extends ProductStock {
    private int quantityAvailable;
    private int reorderLevel;

    public StockItem(int id, String cd, String ud, String wn, String loc, String phone,
                     String categoryName, String categoryCode,
                     String supplierName, String supplierEmail, String supplierPhone,
                     String productName, double unitPrice, int stockLimit,
                     int quantityAvailable, int reorderLevel) {
        super(id, cd, ud, wn, loc, phone, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit);
        if (quantityAvailable < 0 || reorderLevel < 0) throw new IllegalArgumentException("quantities must be >= 0");
        this.quantityAvailable = quantityAvailable;
        this.reorderLevel = reorderLevel;
    }

    public int getQuantityAvailable(){ return quantityAvailable; }
    public int getReorderLevel(){ return reorderLevel; }
    public void increaseQuantity(int q){ if (q < 0) throw new IllegalArgumentException("q >= 0"); quantityAvailable += q; }
    public void decreaseQuantity(int q){ if (q < 0 || q > quantityAvailable) throw new IllegalArgumentException("invalid q"); quantityAvailable -= q; }
}

class PurchaseStock extends StockItem {
    private String purchaseDate;
    private int purchasedQuantity;
    private String purchaseSupplier;

    public PurchaseStock(int id, String cd, String ud, String wn, String loc, String phone,
                         String categoryName, String categoryCode,
                         String supplierName, String supplierEmail, String supplierPhone,
                         String productName, double unitPrice, int stockLimit,
                         int quantityAvailable, int reorderLevel,
                         String purchaseDate, int purchasedQuantity, String purchaseSupplier) {
        super(id, cd, ud, wn, loc, phone, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit, quantityAvailable, reorderLevel);
        if (purchaseDate == null || purchasedQuantity <= 0) throw new IllegalArgumentException("purchase date and quantity required");
        this.purchaseDate = purchaseDate;
        this.purchasedQuantity = purchasedQuantity;
        this.purchaseSupplier = purchaseSupplier;
        increaseQuantity(purchasedQuantity);
    }

    public String getPurchaseDate(){ return purchaseDate; }
    public int getPurchasedQuantity(){ return purchasedQuantity; }
    public String getPurchaseSupplier(){ return purchaseSupplier; }
}

class SaleStock extends PurchaseStock {
    private String saleDate;
    private int soldQuantity;
    private String customerName;

    public SaleStock(int id, String cd, String ud, String wn, String loc, String phone,
                     String categoryName, String categoryCode,
                     String supplierName, String supplierEmail, String supplierPhone,
                     String productName, double unitPrice, int stockLimit,
                     int quantityAvailable, int reorderLevel,
                     String purchaseDate, int purchasedQuantity, String purchaseSupplier,
                     String saleDate, int soldQuantity, String customerName) {
        super(id, cd, ud, wn, loc, phone, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit, quantityAvailable, reorderLevel, purchaseDate, purchasedQuantity, purchaseSupplier);
        if (saleDate == null || soldQuantity <= 0) throw new IllegalArgumentException("sale date and sold quantity required");
        this.saleDate = saleDate;
        this.soldQuantity = soldQuantity;
        this.customerName = customerName;
        decreaseQuantity(soldQuantity);
    }

    public String getSaleDate(){ return saleDate; }
    public int getSoldQuantity(){ return soldQuantity; }
    public String getCustomerName(){ return customerName; }
}

class InventoryStock extends SaleStock {
    private int totalItems;
    private double stockValue;

    public InventoryStock(int id, String cd, String ud, String wn, String loc, String phone,
                          String categoryName, String categoryCode,
                          String supplierName, String supplierEmail, String supplierPhone,
                          String productName, double unitPrice, int stockLimit,
                          int quantityAvailable, int reorderLevel,
                          String purchaseDate, int purchasedQuantity, String purchaseSupplier,
                          String saleDate, int soldQuantity, String customerName) {
        super(id, cd, ud, wn, loc, phone, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit, quantityAvailable, reorderLevel, purchaseDate, purchasedQuantity, purchaseSupplier, saleDate, soldQuantity, customerName);
        recompute();
    }

    public void recompute(){
        this.totalItems = getQuantityAvailable();
        this.stockValue = totalItems * getUnitPrice();
    }

    public int getTotalItems(){ return totalItems; }
    public double getStockValue(){ return stockValue; }
}

final class StockReportStock extends InventoryStock {
    private String reportDate;
    private String remarks;

    public StockReportStock(int id, String cd, String ud, String wn, String loc, String phone,
                            String categoryName, String categoryCode,
                            String supplierName, String supplierEmail, String supplierPhone,
                            String productName, double unitPrice, int stockLimit,
                            int quantityAvailable, int reorderLevel,
                            String purchaseDate, int purchasedQuantity, String purchaseSupplier,
                            String saleDate, int soldQuantity, String customerName,
                            String reportDate, String remarks) {
        super(id, cd, ud, wn, loc, phone, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit, quantityAvailable, reorderLevel, purchaseDate, purchasedQuantity, purchaseSupplier, saleDate, soldQuantity, customerName);
        if (reportDate == null) throw new IllegalArgumentException("reportDate required");
        this.reportDate = reportDate;
        this.remarks = remarks;
        recompute();
    }

    public void generateReport(){
        System.out.println("\n=== STOCK REPORT ===");
        System.out.println("Report Date: " + reportDate);
        System.out.println("Product: " + getProductName());
        System.out.println("Unit Price: " + getUnitPrice());
        System.out.println("Total Items: " + getTotalItems());
        System.out.println("Stock Value: " + getStockValue());
        System.out.println("Remarks: " + remarks);
    }
}

public class StockManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("=== Stock Management System Input ===");
            System.out.print("ID (>0): "); int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Created Date: "); String cd = sc.nextLine().trim();
            System.out.print("Updated Date: "); String ud = sc.nextLine().trim();

            System.out.print("Warehouse Name: "); String wn = sc.nextLine().trim();
            System.out.print("Warehouse Location: "); String loc = sc.nextLine().trim();
            System.out.print("Warehouse Contact (10 digits): "); String wphone = sc.nextLine().trim();

            System.out.print("Category Name: "); String cname = sc.nextLine().trim();
            System.out.print("Category Code (>=3 alphanumeric): "); String ccode = sc.nextLine().trim();

            System.out.print("Supplier Name: "); String sname = sc.nextLine().trim();
            System.out.print("Supplier Email: "); String semail = sc.nextLine().trim();
            System.out.print("Supplier Phone (10 digits): "); String sphone = sc.nextLine().trim();

            System.out.print("Product Name: "); String pname = sc.nextLine().trim();
            System.out.print("Unit Price (>0): "); double up = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Stock Limit (>=0): "); int sl = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Quantity Available (>=0): "); int qa = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Reorder Level (>=0): "); int rl = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Purchase Date: "); String pdate = sc.nextLine().trim();
            System.out.print("Purchased Quantity (>0): "); int pq = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Purchase Supplier Name: "); String psn = sc.nextLine().trim();

            System.out.print("Sale Date: "); String sdate = sc.nextLine().trim();
            System.out.print("Sold Quantity (>0): "); int sq = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Customer Name: "); String cnameCustomer = sc.nextLine().trim();

            System.out.print("Report Date: "); String rdate = sc.nextLine().trim();
            System.out.print("Remarks: "); String remarks = sc.nextLine().trim();

            StockReportStock report = new StockReportStock(id, cd, ud, wn, loc, wphone, cname, ccode, sname, semail, sphone,
                    pname, up, sl, qa, rl, pdate, pq, psn, sdate, sq, cnameCustomer, rdate, remarks);

            System.out.println("\n--- Data Entered (Summary) ---");
            System.out.println("Product: " + pname + " | Supplier: " + sname + " | Warehouse: " + wn);
            report.generateReport();

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            sc.close();
        }
    }
}
