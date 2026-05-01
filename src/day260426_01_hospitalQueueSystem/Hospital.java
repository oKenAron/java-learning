package day260426_01_hospitalQueueSystem;

// 2026.05.01 update: 添加Scanner方法，让main可以根据输入执行内容。
// 清理部分comment

import java.util.Scanner;

class Ticket {
    private final int ticketNumber;
    private final String patientName;
    private final int patientAge;

    public Ticket(int number, String name, int age) {
        // 2026.05.01 去除防御逻辑，防御应该被放到脏数据产生的位点
        this.ticketNumber = number;
        this.patientName = name;
        this.patientAge = age;
    }

    public int getTicketNumber(){
        return this.ticketNumber;
    }
    public String getTicketInfo() {
        return String.format("姓名: %s, 年龄: %d, 号码: %d",
                this.patientName, this.patientAge, this.ticketNumber);
    }
}

class HospitalMachine {
    private static int totalTicket = 0;
    private static int currentTicket = 0;
    private static final Scanner scan = new Scanner(System.in);

    public static Ticket generateTicket() {
        System.out.print("请输入姓名: ");
        String name = scan.next();
        if (name == null){
            // 下行为 Gemini 辅助生成，我目前对 throw 和 IllegalArgumentException 几乎不了解。
            // 现阶段的逻辑是遇错直接炸弹炸掉整个主程序
            // "throw 会像 return 一样立刻终止方法，但它同时会向外丢出一个炸弹"
            throw new IllegalArgumentException("请输入文本");
        }
        System.out.print("请输入年龄: ");
        int age = scan.nextInt();
        if (age < 0 || age > 255){
            throw new IllegalArgumentException("您这年龄一定很有故事");
        }
        System.out.println("录入成功，请取号.");
        totalTicket++;
        return new Ticket(totalTicket, name, age);
    }

    public static void callNextPatient() {
        currentTicket++;
        System.out.println("请" + currentTicket + "号患者就诊");
    }

    public static void checkTicket(Ticket ticket){
        if (ticket == null){
            System.out.println("未检测到有效票据，无法处理。");
            return;
        }

        if(ticket.getTicketNumber() < currentTicket){
            // 确认过号之后要不要从内存里清除对应的ticket，值得思考。
            // 记得gemini指摘过这个想法比较天真，但忘了具体是什么了。
            // Gemini: GC会处理掉废弃票据。
            System.out.println(ticket.getTicketNumber()+"号患者：过号请重排.");
        }
        else if(ticket.getTicketNumber() == currentTicket){
            System.out.println(ticket.getTicketNumber()+"号患者：您久等了，请前往n号诊室.");
            // 对号统一走一个验证渠道，可能未必符合大部分医院真实的排号系统
        }
        else {
            System.out.println((ticket.getTicketNumber()+"号患者：滚回去排队."));
        }
    }
}


public class Hospital {
    public static void main(String[] args) {
        Ticket ticketForUserA = HospitalMachine.generateTicket();
        Ticket ticketForUserB = HospitalMachine.generateTicket();
        Ticket ticketForUserC = HospitalMachine.generateTicket();

        System.out.println("您好患者，您的信息如下:\n"+ ticketForUserA.getTicketInfo());
        System.out.println("您好患者，您的信息如下:\n"+ ticketForUserB.getTicketInfo());
        System.out.println("您好患者，您的信息如下:\n"+ ticketForUserC.getTicketInfo());

        HospitalMachine.callNextPatient();
        HospitalMachine.checkTicket(ticketForUserA);
        HospitalMachine.checkTicket(ticketForUserB);
        HospitalMachine.callNextPatient();
        HospitalMachine.checkTicket(ticketForUserB);
        HospitalMachine.callNextPatient();
        HospitalMachine.callNextPatient();
        HospitalMachine.checkTicket(ticketForUserC);
        HospitalMachine.checkTicket(null);
    }
}
