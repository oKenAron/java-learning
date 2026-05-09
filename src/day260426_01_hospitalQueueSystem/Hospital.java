package day260426_01_hospitalQueueSystem;

// To-Do list
// ========================================================================
// try 块调整为只放可能会炸的代码
//     疑问: genericTicket 也放了一大堆进 try 是不是也要改
//     回答: main 的职责是"路由调度", 原则上要遵守原子性事务, 尽量小
//          而 generateTicket 的职责是完整制造, 尽可能保证任何一步都不出错是原则
// 位置审查, 调整方法顺序
// checkTicket 方法避免直接调用print
// ========================================================================

// 修正前 Commit
// ========================================================================
// refactor: 缩小 main 方法中 try 块的作用域
// ========================================================================
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

class Ticket {
    private final int ticketNumber;
    private final String patientName;
    private final int patientAge;

    public Ticket(int number, String name, int age) {
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
    // 去除 totalTicket 逻辑, 遵守单一事实来源.
    // private static int totalTicket = 0;
    private static int currentTicket = 0;
    private static final Scanner scan = new Scanner(System.in);
    private static final ArrayList<Ticket> ticketArchive = new ArrayList<>();
    public static void generateTicket() {
        try{
            System.out.print("请输入姓名: ");
            String name = scan.nextLine().trim();
            if (name.isEmpty()){
                throw new IllegalArgumentException("我说请输入文本，尼尔多隆吗! ");
            }
            System.out.print("请输入年龄: ");
            int age = scan.nextInt();
            scan.nextLine();
            if (age < 0 || age > 255){
                throw new IllegalArgumentException("您这年龄一定很有故事! ");
            }
            System.out.println("录入成功，请取号.");
            // 去除 totalTicket 逻辑, 遵守单一事实来源.
            // totalTicket++;
            int currentAssignedNumber = ticketArchive.size() + 1;
            System.out.println("您的号码是: "+currentAssignedNumber);
            ticketArchive.add(new Ticket(currentAssignedNumber, name, age));
        }
        catch(java.util.InputMismatchException e){
            System.out.println("系统警告：您的年龄光是写下来就是本书! ");
            scan.nextLine();
        }
        catch(IllegalArgumentException e){
            System.out.println("系统警告：" + e.getMessage());
        }
    }

    public static void callNextPatient() {
        currentTicket++;
        System.out.println("请" + currentTicket + "号患者就诊");
    }

    public static Ticket getTicket(int number){
        if (number <= 0 || number > ticketArchive.size()){
            return null;
        }
        return ticketArchive.get(number - 1);
    }

    public static void checkTicket(Ticket ticket){
        if (ticket == null){
            System.out.println("未检测到有效票据，无法处理。");
            return;
        }

        if(ticket.getTicketNumber() < currentTicket){
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
        Scanner mainScan = new Scanner(System.in);

        while(true) {
            System.out.println("\n=== 欢迎来到红脖子医院 ===");
            System.out.println("1. 老登挂号");
            System.out.println("2. 医生叫号");
            System.out.println("3. 老登查号");
            // 并非 HospitalMachine 所属功能, 临时安置
            System.out.println("4. 老登看号");
            System.out.println("0. 关机下班");
            System.out.print("请输入操作指令：");

            String choice = mainScan.nextLine().trim();

            // 健壮的系统不应该将这些功能囊括在一起,
            // 换句话说,现在的系统,绝对不能让患者可以触摸到.
            switch (choice){
                case "1":
                    HospitalMachine.generateTicket();
                    break;
                case "2":
                    HospitalMachine.callNextPatient();
                    break;
                case "3":
                    System.out.println("请输入你的号码");
                    int queriedNumber;
                    try {
                        queriedNumber = mainScan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("MAGA人均数学博士");
                        mainScan.nextLine();
                        break;
                    }
                    mainScan.nextLine();
                    HospitalMachine.checkTicket(HospitalMachine.getTicket(queriedNumber));
                    break;
                case "4":
                    // 并非 HospitalMachine 所属功能, 临时安置
                    System.out.print("MAGA! 让我康康我的票, 我是几号来着:");
                    int myNumber;
                    try {
                        myNumber = mainScan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("MAGA人均数学博士");
                        mainScan.nextLine();
                        break;
                    }
                    mainScan.nextLine();
                    Ticket myTicket = HospitalMachine.getTicket(myNumber);
                    if (myTicket != null){
                        System.out.printf("\nMAGA! 我想起来了, 我不是爱泼斯坦, 我是\n"
                                +myTicket.getTicketInfo()+"\n");
                    } else {
                        System.out.println("MAGA! 眼睛花了看不清");
                    }
                    break;

                case "0":
                    System.out.println("下班收工! MAGA!!!");
                    return;
                default:
                    System.out.println("你的输入违反了MAGA!");
                    break;
            }
        }
    }
}
