package day260426_01_hospitalQueueSystem;

// 2026.04.28 update: 将ticketNumber修改回private修饰，并因此建立getTicketNumber方法来读取变量。
// 添加防御性拦截，避免方法中被注入空值，避免空值调用特定类的方法。但在将该思路应用到Ticket方法时产生报错。
// 因此该commit保存到这个报错为止。

// 2026.04.27 update: 增加了查票系统，在目前的逻辑中，基于查票来分析个体的队列状态并安排其进入诊室。
// 实现过程中，修改ticketNumber的变量修饰符，以便checkTicket读取，
// 对于未来的优化逻辑，一是要更符合现实挂号系统，二是开发过程要考虑到未来运行时这应该是一个实时产号、排队的系统。
// 而不是在main里预设几个根本不会排队的老登。

class Ticket {
    private final int ticketNumber;
    private final String patientName;
    private final int patientAge;

    public Ticket(int number, String name, int age) {
        // 尝试拦截number是null的情况下的异常，但这里报错了
        if (number == null || ){

        }
        this.ticketNumber = number;
        this.patientName = name;
        this.patientAge = age;
    }

    public int getTicketNumber(){
        return this.ticketNumber;
    }
    public String getTicketInfo() {
        // 单行代码转多行怎么弄，我屏幕小这句溢出了
        // 解决方法: 直接在逗号后回车，多个字符串间在加号后回车。
        return String.format("姓名: %s, 年龄: %d, 号码: %d\n",
                this.patientName, this.patientAge, this.ticketNumber);
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

    public static void checkTicket(Ticket ticket){
        // 猜测在最初的if前应该增加一个检验输入Ticket类变量是否存在，
        // 当然这个工作可能不是这里负责的，因为实际上的叫号器是运行好了之后等人来用，
        // 如果有人输入了不存在的ticket，jvm直接报错就好玩了.
        // Gemini Comment: 需要主动执行防御性拦截。
        if (ticket == null){
            System.out.println("未检测到有效票据，无法处理。");
        }
        else if(ticket.getTicketNumber() < currentTicket){
            // 确认过号之后要不要从内存里清除对应的ticket，值得思考。
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
        Ticket ticketForUserA = HospitalMachine.generateTicket("Trump", 114514);
        Ticket ticketForUserB = HospitalMachine.generateTicket("Musk", 1919810);
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
