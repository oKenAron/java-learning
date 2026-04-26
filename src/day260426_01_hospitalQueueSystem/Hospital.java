package day260426_01_hospitalQueueSystem;

class Ticket {
    private final int ticketNumber;
    private final String patientName;
    private final int patientAge;

    public Ticket(int number, String name, int age) {
        this.ticketNumber = number;
        this.patientName = name;
        this.patientAge = age;
    }

    public void getTicketInfo() {
        System.out.printf("您好患者，您的信息如下:\n姓名: %s, 年龄: %d, 号码: %d", this.patientName, this.patientAge, this.ticketNumber);
    }
}

class HospitalMachine {
    public static int totalTicket = 0;
    public static int currentTicket = 0;

    public static Ticket generateTicket(String name, int age) {
        totalTicket++;
        return new Ticket(totalTicket, name, age);
    }

    public static void callNextPatient() {
        currentTicket++;
        System.out.println("请" + currentTicket + "号患者就诊");
    }
}


public class Hospital {
    public static void main(String[] args) {
        Ticket ticketForUserA = HospitalMachine.generateTicket("Trump", 114514);
        Ticket ticketForUserB = HospitalMachine.generateTicket("Musk", 1919810);

        ticketForUserA.getTicketInfo();
        ticketForUserB.getTicketInfo();

        HospitalMachine.callNextPatient();
    }
}
