package q2;

import java.util.Scanner;



class EntityFlight {
    private int id; private String createdDate; private String updatedDate;
    public EntityFlight(int id, String createdDate, String updatedDate) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        if (createdDate == null || updatedDate == null) throw new IllegalArgumentException("dates required");
        this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
    }
    public int getId(){ return id; }
    public String getCreatedDate(){ return createdDate; }
    public String getUpdatedDate(){ return updatedDate; }
}

class AirportFlight extends EntityFlight {
    private String airportName;
    private String code;
    private String location;
    public AirportFlight(int id, String cd, String ud, String airportName, String code, String location) {
        super(id, cd, ud);
        if (code == null || !code.matches("[A-Z]{3}")) throw new IllegalArgumentException("airport code must be 3 uppercase letters");
        this.airportName = airportName; this.code = code; this.location = location;
    }
    public String getAirportName(){ return airportName; }
    public String getCode(){ return code; }
}

class AirlineFlight extends AirportFlight {
    private String airlineName;
    private String airlineCode;
    private String contactEmail;
    public AirlineFlight(int id, String cd, String ud, String airportName, String code, String location,
                         String airlineName, String airlineCode, String contactEmail) {
        super(id, cd, ud, airportName, code, location);
        if (airlineCode == null || airlineCode.length() < 2 || airlineCode.length() > 4) throw new IllegalArgumentException("airline code 2-4 letters");
        if (contactEmail == null || !contactEmail.contains("@")) throw new IllegalArgumentException("invalid email");
        this.airlineName = airlineName; this.airlineCode = airlineCode; this.contactEmail = contactEmail;
    }
    public String getAirlineName(){ return airlineName; }
}

class FlightFlight extends AirlineFlight {
    private String flightNumber;
    private String departure;
    private String destination;
    private double baseFare;
    public FlightFlight(int id, String cd, String ud, String airportName, String code, String location,
                        String airlineName, String airlineCode, String contactEmail,
                        String flightNumber, String departure, String destination, double baseFare) {
        super(id, cd, ud, airportName, code, location, airlineName, airlineCode, contactEmail);
        if (flightNumber == null || flightNumber.isEmpty()) throw new IllegalArgumentException("flight number required");
        if (baseFare <= 0) throw new IllegalArgumentException("baseFare > 0");
        this.flightNumber = flightNumber; this.departure = departure; this.destination = destination; this.baseFare = baseFare;
    }
    public double getBaseFare(){ return baseFare; }
    public String getFlightNumber(){ return flightNumber; }
}

class PilotFlight extends FlightFlight {
    private String pilotName;
    private String licenseNumber;
    private int experienceYears;
    public PilotFlight(int id, String cd, String ud, String airportName, String code, String location,
                       String airlineName, String airlineCode, String contactEmail,
                       String flightNumber, String departure, String destination, double baseFare,
                       String pilotName, String licenseNumber, int experienceYears) {
        super(id, cd, ud, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare);
        if (licenseNumber == null || licenseNumber.isEmpty()) throw new IllegalArgumentException("license required");
        if (experienceYears < 2) throw new IllegalArgumentException("experience >= 2 years");
        this.pilotName = pilotName; this.licenseNumber = licenseNumber; this.experienceYears = experienceYears;
    }
}

class CabinCrewFlight extends PilotFlight {
    private String crewName;
    private String role;
    private String shift;
    public CabinCrewFlight(int id, String cd, String ud, String airportName, String code, String location,
                           String airlineName, String airlineCode, String contactEmail,
                           String flightNumber, String departure, String destination, double baseFare,
                           String pilotName, String licenseNumber, int experienceYears,
                           String crewName, String role, String shift) {
        super(id, cd, ud, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears);
        if (!"Day".equals(shift) && !"Night".equals(shift)) throw new IllegalArgumentException("shift must be Day or Night");
        this.crewName = crewName; this.role = role; this.shift = shift;
    }
}

class PassengerFlight extends CabinCrewFlight {
    private String passengerName;
    private int age;
    private String gender;
    private String contact;
    public PassengerFlight(int id, String cd, String ud, String airportName, String code, String location,
                           String airlineName, String airlineCode, String contactEmail,
                           String flightNumber, String departure, String destination, double baseFare,
                           String pilotName, String licenseNumber, int experienceYears,
                           String crewName, String role, String shift,
                           String passengerName, int age, String gender, String contact) {
        super(id, cd, ud, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears, crewName, role, shift);
        if (age <= 0) throw new IllegalArgumentException("age > 0");
        this.passengerName = passengerName; this.age = age; this.gender = gender; this.contact = contact;
    }
    public String getPassengerName(){ return passengerName; }
}

class BookingFlight extends PassengerFlight {
    private String bookingDate;
    private String seatNumber;
    private String travelClass;
    public BookingFlight(int id, String cd, String ud, String airportName, String code, String location,
                         String airlineName, String airlineCode, String contactEmail,
                         String flightNumber, String departure, String destination, double baseFare,
                         String pilotName, String licenseNumber, int experienceYears,
                         String crewName, String role, String shift,
                         String passengerName, int age, String gender, String contact,
                         String bookingDate, String seatNumber, String travelClass) {
        super(id, cd, ud, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears, crewName, role, shift, passengerName, age, gender, contact);
        if (!"Economy".equals(travelClass) && !"Business".equals(travelClass) && !"First".equals(travelClass)) throw new IllegalArgumentException("travelClass must be Economy/Business/First");
        this.bookingDate = bookingDate; this.seatNumber = seatNumber; this.travelClass = travelClass;
    }
    public String getTravelClass(){ return travelClass; }
}

class PaymentFlight extends BookingFlight {
    private String paymentDate;
    private String paymentMethod;
    private double amountPaid;
    public PaymentFlight(int id, String cd, String ud, String airportName, String code, String location,
                         String airlineName, String airlineCode, String contactEmail,
                         String flightNumber, String departure, String destination, double baseFare,
                         String pilotName, String licenseNumber, int experienceYears,
                         String crewName, String role, String shift,
                         String passengerName, int age, String gender, String contact,
                         String bookingDate, String seatNumber, String travelClass,
                         String paymentDate, String paymentMethod, double amountPaid) {
        super(id, cd, ud, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears, crewName, role, shift, passengerName, age, gender, contact, bookingDate, seatNumber, travelClass);
        if (amountPaid <= 0) throw new IllegalArgumentException("amountPaid > 0");
        this.paymentDate = paymentDate; this.paymentMethod = paymentMethod; this.amountPaid = amountPaid;
    }
    public double getAmountPaid(){ return amountPaid; }
}

final class TicketFlight extends PaymentFlight {
    private String ticketNumber;
    private String issueDate;

    public TicketFlight(int id, String cd, String ud, String airportName, String code, String location,
                        String airlineName, String airlineCode, String contactEmail,
                        String flightNumber, String departure, String destination, double baseFare,
                        String pilotName, String licenseNumber, int experienceYears,
                        String crewName, String role, String shift,
                        String passengerName, int age, String gender, String contact,
                        String bookingDate, String seatNumber, String travelClass,
                        String paymentDate, String paymentMethod, double amountPaid,
                        String ticketNumber, String issueDate) {
        super(id, cd, ud, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears, crewName, role, shift, passengerName, age, gender, contact, bookingDate, seatNumber, travelClass, paymentDate, paymentMethod, amountPaid);
        if (ticketNumber == null || issueDate == null) throw new IllegalArgumentException("ticket fields required");
        this.ticketNumber = ticketNumber; this.issueDate = issueDate;
    }

    public void calculateFare() {
        double base = getBaseFare();
         double taxes = base * 0.12;
        double serviceFee = base * 0.03;

        String tclass = getTravelClass();
        double discount = 0;
        if ("Business".equals(tclass)) discount = base * 0.05;
        if ("First".equals(tclass)) discount = base * 0.10;

        double total = base + taxes + serviceFee - discount;
        System.out.println("\n--- FARE CALCULATION ---");
        System.out.printf("Base Fare: %.2f\nTaxes (12%%): %.2f\nService Fee (3%%): %.2f\nDiscount: %.2f\n", base, taxes, serviceFee, discount);
        System.out.printf("Total Fare: %.2f\n", total);
    }
}

public class FlightBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("=== Flight Booking System Input ===");
            System.out.print("ID (>0): "); int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Created Date: "); String cd = sc.nextLine().trim();
            System.out.print("Updated Date: "); String ud = sc.nextLine().trim();

            System.out.print("Airport Name: "); String an = sc.nextLine().trim();
            System.out.print("Airport Code (3 uppercase): "); String acode = sc.nextLine().trim();
            System.out.print("Airport Location: "); String aloc = sc.nextLine().trim();

            System.out.print("Airline Name: "); String airline = sc.nextLine().trim();
            System.out.print("Airline Code (2-4 letters): "); String airlineCode = sc.nextLine().trim();
            System.out.print("Airline Email: "); String airlineEmail = sc.nextLine().trim();

            System.out.print("Flight Number: "); String fnum = sc.nextLine().trim();
            System.out.print("Departure: "); String dep = sc.nextLine().trim();
            System.out.print("Destination: "); String dest = sc.nextLine().trim();
            System.out.print("Base Fare (>0): "); double baseFare = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Pilot Name: "); String pname = sc.nextLine().trim();
            System.out.print("Pilot License: "); String plicense = sc.nextLine().trim();
            System.out.print("Pilot Experience Years (>=2): "); int pyears = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Crew Name: "); String crew = sc.nextLine().trim();
            System.out.print("Crew Role: "); String role = sc.nextLine().trim();
            System.out.print("Crew Shift (Day/Night): "); String shift = sc.nextLine().trim();

            System.out.print("Passenger Name: "); String passName = sc.nextLine().trim();
            System.out.print("Passenger Age: "); int pAge = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Passenger Gender: "); String gender = sc.nextLine().trim();
            System.out.print("Passenger Contact: "); String contact = sc.nextLine().trim();

            System.out.print("Booking Date: "); String bdate = sc.nextLine().trim();
            System.out.print("Seat Number: "); String seat = sc.nextLine().trim();
            System.out.print("Travel Class (Economy/Business/First): "); String tclass = sc.nextLine().trim();

            System.out.print("Payment Date: "); String payDate = sc.nextLine().trim();
            System.out.print("Payment Method: "); String payMethod = sc.nextLine().trim();
            System.out.print("Amount Paid (>0): "); double amt = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Ticket Number: "); String ticketNo = sc.nextLine().trim();
            System.out.print("Issue Date: "); String issue = sc.nextLine().trim();

            TicketFlight ticket = new TicketFlight(id, cd, ud, an, acode, aloc, airline, airlineCode, airlineEmail, fnum, dep, dest, baseFare,
                    pname, plicense, pyears, crew, role, shift, passName, pAge, gender, contact,
                    bdate, seat, tclass, payDate, payMethod, amt, ticketNo, issue);

            System.out.println("\n--- Ticket Summary ---");
            System.out.println("Passenger: " + passName);
            System.out.println("Flight: " + fnum + " from " + dep + " to " + dest);
            ticket.calculateFare();

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            sc.close();
        }
    }
}
