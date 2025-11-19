package q5;

import java.util.Scanner;

/*
  Attendance.java
  - 10-class inheritance chain for Attendance Management System
  - final class AttendanceSummaryAtt with generateSummary()
*/

class EntityAtt {
    private int id;
    public EntityAtt(int id) {
        if (id <= 0) throw new IllegalArgumentException("id > 0");
        this.id = id;
    }
    public int getId(){ return id; }
}

class InstitutionAtt extends EntityAtt {
    private String institutionName;
    private String code;
    private String address;
    public InstitutionAtt(int id, String institutionName, String code, String address) {
        super(id);
        if (code == null || code.length() < 3) throw new IllegalArgumentException("code >= 3");
        this.institutionName = institutionName; this.code = code; this.address = address;
    }
}

class DepartmentAtt extends InstitutionAtt {
    private String departmentName;
    private String departmentHead;
    public DepartmentAtt(int id, String institutionName, String code, String address, String departmentName, String departmentHead) {
        super(id, institutionName, code, address);
        if (departmentHead == null || departmentHead.isEmpty()) throw new IllegalArgumentException("department head required");
        this.departmentName = departmentName; this.departmentHead = departmentHead;
    }
}

class CourseAtt extends DepartmentAtt {
    private String courseName;
    private String courseCode;
    private int credits;
    public CourseAtt(int id, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits) {
        super(id, institutionName, code, address, departmentName, departmentHead);
        if (credits <= 0) throw new IllegalArgumentException("credits > 0");
        this.courseName = courseName; this.courseCode = courseCode; this.credits = credits;
    }
}

class InstructorAtt extends CourseAtt {
    private String instructorName;
    private String email;
    private String phone;
    public InstructorAtt(int id, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone) {
        super(id, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits);
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("invalid email");
        if (phone == null || !phone.matches("\\d{10}")) throw new IllegalArgumentException("phone must be 10 digits");
        this.instructorName = instructorName; this.email = email; this.phone = phone;
    }
}

class StudentAtt extends InstructorAtt {
    private String studentName;
    private String studentID;
    private int age;
    public StudentAtt(int id, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age) {
        super(id, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone);
        if (age <= 0) throw new IllegalArgumentException("age > 0");
        this.studentName = studentName; this.studentID = studentID; this.age = age;
    }
    public String getStudentName(){ return studentName; }
    public String getStudentID(){ return studentID; }
}

class ClassSessionAtt extends StudentAtt {
    private String sessionDate;
    private String topic;
    public ClassSessionAtt(int id, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age, String sessionDate, String topic) {
        super(id, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone, studentName, studentID, age);
        if (sessionDate == null || sessionDate.isEmpty()) throw new IllegalArgumentException("sessionDate required");
        this.sessionDate = sessionDate; this.topic = topic;
    }
}

class AttendanceRecordAtt extends ClassSessionAtt {
    private String status;
    public AttendanceRecordAtt(int id, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age, String sessionDate, String topic, String status) {
        super(id, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone, studentName, studentID, age, sessionDate, topic);
        if (!"Present".equals(status) && !"Absent".equals(status)) throw new IllegalArgumentException("status must be Present/Absent");
        this.status = status;
    }
    public String getStatus(){ return status; }
}

class LeaveRequestAtt extends AttendanceRecordAtt {
    private String requestDate;
    private String reason;
    private boolean approved;
    public LeaveRequestAtt(int id, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age, String sessionDate, String topic, String status, String requestDate, String reason, boolean approved) {
        super(id, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone, studentName, studentID, age, sessionDate, topic, status);
        if (reason == null || reason.isEmpty()) throw new IllegalArgumentException("reason required");
        this.requestDate = requestDate; this.reason = reason; this.approved = approved;
    }
    public boolean isApproved(){ return approved; }
}

final class AttendanceSummaryAtt extends LeaveRequestAtt {
    private String reportDate;
    private int totalPresent;
    private int totalAbsent;
    private int totalSessions;

    public AttendanceSummaryAtt(int id, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age, String sessionDate, String topic, String status, String requestDate, String reason, boolean approved, String reportDate, int totalPresent, int totalAbsent, int totalSessions) {
        super(id, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone, studentName, studentID, age, sessionDate, topic, status, requestDate, reason, approved);
        this.reportDate = reportDate;
        this.totalPresent = totalPresent;
        this.totalAbsent = totalAbsent;
        this.totalSessions = totalSessions;
    }

    public void generateSummary() {
        double percent = totalSessions == 0 ? 0.0 : ((double) totalPresent / totalSessions) * 100.0;
        System.out.println("\n=== ATTENDANCE SUMMARY ===");
        System.out.println("Report Date: " + reportDate);
        System.out.println("Total Present: " + totalPresent);
        System.out.println("Total Absent: " + totalAbsent);
        System.out.println("Total Sessions: " + totalSessions);
        System.out.printf("Attendance %%: %.2f%%\n", percent);
    }
}

public class Attendance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("=== Attendance Input ===");
            System.out.print("ID (>0): "); int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Institution Name: "); String inst = sc.nextLine().trim();
            System.out.print("Institution Code (>=3): "); String code = sc.nextLine().trim();
            System.out.print("Institution Address: "); String addr = sc.nextLine().trim();

            System.out.print("Department Name: "); String dept = sc.nextLine().trim();
            System.out.print("Department Head: "); String dhead = sc.nextLine().trim();

            System.out.print("Course Name: "); String cname = sc.nextLine().trim();
            System.out.print("Course Code: "); String ccode = sc.nextLine().trim();
            System.out.print("Credits (>0): "); int credits = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Instructor Name: "); String iname = sc.nextLine().trim();
            System.out.print("Instructor Email: "); String iemail = sc.nextLine().trim();
            System.out.print("Instructor Phone (10 digits): "); String iphone = sc.nextLine().trim();

            System.out.print("Student Name: "); String sname = sc.nextLine().trim();
            System.out.print("Student ID: "); String sid = sc.nextLine().trim();
            System.out.print("Student Age (>0): "); int sage = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Session Date: "); String sdate = sc.nextLine().trim();
            System.out.print("Topic: "); String topic = sc.nextLine().trim();
            System.out.print("Status (Present/Absent): "); String status = sc.nextLine().trim();

            System.out.print("Leave Request Date: "); String lrdate = sc.nextLine().trim();
            System.out.print("Leave Reason: "); String reason = sc.nextLine().trim();
            System.out.print("Leave Approved (true/false): "); boolean approved = Boolean.parseBoolean(sc.nextLine().trim());

            System.out.print("Report Date: "); String rdate = sc.nextLine().trim();
            System.out.print("Total Present: "); int totalPresent = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Total Absent: "); int totalAbsent = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Total Sessions: "); int totalSessions = Integer.parseInt(sc.nextLine().trim());

            AttendanceSummaryAtt summary = new AttendanceSummaryAtt(id, inst, code, addr, dept, dhead, cname, ccode, credits, iname, iemail, iphone, sname, sid, sage, sdate, topic, status, lrdate, reason, approved, rdate, totalPresent, totalAbsent, totalSessions);
            System.out.println("\n--- Entered Summary ---");
            System.out.println("Student: " + sname + " | Course: " + cname);
            summary.generateSummary();

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            sc.close();
        }
    }
}
