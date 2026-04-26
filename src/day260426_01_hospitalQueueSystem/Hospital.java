package day260426_01_hospitalQueueSystem;

class HosTicket {
    private final int ticketNumber;
    private final String patientName;
    private final int patientAge;

    public HosTicket(int number, String name, int age){
        this.ticketNumber = number;
        this.patientName = name;
        this.patientAge = age;
    }

    public int getTicketInfo(int number){
        return this.ticketNumber;
    }
}

class HospitalMachine {
    public static int totalTicket = 0;
    public static int currentTicket = 0;

    public static HosTicket generateTicket(String name, int age){
        totalTicket++;
        HosTicket patientTicket = new HosTicket(totalTicket, name, age);
        return patientTicket;
    }

    public static void callNextPatient(){
        currentTicket++;
        System.out.println("请"+currentTicket+"号患者就诊");
    }
}



public class Hospital {
    public static void main(String[] args) {

        System.out.println("tbd");
    }
}
