package day260426_01_hospitalQueueSystem;

// 2026.05.01 update: 修正防御逻辑的位置，基于可以触发breakpoint的报错来打断程序。
// 目前的防御逻辑依然只是一个暂时的设计，最终健壮的系统是应当可以在物理世界中并行处理各个不同的任务
// （这里要表达的是并非程序就一定并行了，毕竟小脚本跑很快，哪怕非并行大概率也不会出问题）
// 清理部分comment， 现阶段思考：准备加入scanner

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
    public static int totalTicket = 0;
    public static int currentTicket = 0;

    public static Ticket generateTicket(String name, int age) {
        if (name == null || age < 0 || age > 255){
            // 下行为 Gemini 辅助生成，我目前对 throw 和 IllegalArgumentException 几乎不了解。
            // 现阶段的逻辑是遇错直接炸弹炸掉整个主程序
            // "throw 会像 return 一样立刻终止方法，但它同时会向外丢出一个炸弹"
            throw new IllegalArgumentException("发号失败：患者姓名为空或年龄不合法！");
        }
        // 我猜，更健壮的逻辑是把print放到Ticket后面，保证Ticket真生成了再输出，
        // 然而这会带来问题就是Ticket得被赋值到变量里再return。
        // Gemini: 其实这很安全。
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
        Ticket ticketForUserA = HospitalMachine.generateTicket("Trump", 0);
        Ticket ticketForUserB = HospitalMachine.generateTicket("Musk", 114514);
        Ticket ticketForUserC = HospitalMachine.generateTicket("Vance",1);

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
