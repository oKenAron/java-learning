package day260426_00_basics;

class Ticket {
    // 为了统计全局挂号数, 设置该全局静态变量
    public static int totalTicket = 0;
    public static int currentTicket = 1;
    public static int specialTicket = 0;
    public static int usedTicket = 0;

    // 为了实例构造时可以填入东西，需要准备实例变量
    public String patientName;
    public int patientTicketNumber;
    public int patientAge;


    // 这里用构造函数来方便构建该类的实例
    public Ticket(String name, int age) {
        totalTicket++;
        this.patientName = name;
        this.patientTicketNumber = totalTicket;
        this.patientAge = age;
    }

    // 看看挂号票
    public void showTicket(){
        if (currentTicket == this.patientTicketNumber) {
            System.out.println("患者名为【" + this.patientName + "】, 号码是【A" + this.patientTicketNumber + "】, 请前往1号诊室.");
            usedTicket++;
            currentTicket++;
        } else if (currentTicket > this.patientTicketNumber && this.patientAge >= 60) {
            specialTicket++;
            totalTicket++;
            usedTicket++;
            System.out.println("患者名为【" + this.patientName + "】, 原号码是【A" + this.patientTicketNumber + "】,因过号为您安排临时号码【S" + specialTicket + "】.请前往1号诊室.");
        } else if (currentTicket > this.patientTicketNumber) {
            System.out.println("过号请重排");
        } else {
            System.out.println("滚回去排队");
        }
    }

    public static void avoidTicket(){
        System.out.println("号码为【" + currentTicket + "】的患者，您已错过排号。");
        currentTicket++;
    }

    public static void showSystemStatus(){
        System.out.println("系统广播,目前患者排号已到达【A"+ currentTicket + "】, 请您耐心等待");
        System.out.println("今日总挂号单数【"+ totalTicket + "】, 已问诊病人【"+ usedTicket +"】人");
        System.out.println("双点医院蒸蒸日上.");
    }

}

public class HospitalQueueSystem {
    // 你别管为啥名字叫银行排号系统结果排的是医院的号, 问就是我记岔了.
    public static void main(String[] args){
        Ticket userTrump = new Ticket("Trump" , 79);
        Ticket.avoidTicket();
        userTrump.showTicket();
        Ticket.showSystemStatus();
    }
}
