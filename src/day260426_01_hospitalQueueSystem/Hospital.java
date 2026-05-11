package day260426_01_hospitalQueueSystem;

// To-Do list
// ========================================================================
// 功能性方法内避免直接调用 print, scanner : generateTicket.
// ========================================================================

// 修正前 Commit
// ========================================================================
// refactor: 将 generateTicket 改为返回 int 以解耦 UI 输出
//
// - 补全含参方法内部异常防御机制
// ========================================================================
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

class Ticket {
    private final int ticketNumber;
    private final String patientName;
    private final int patientAge;

    public Ticket(int number, String name, int age) {
        if (number <= 0 || name == null || name.isEmpty() || age < 0 || age > 255){
            throw new IllegalArgumentException("error:数据输入异常");
        }
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
    private static final ArrayList<Ticket> ticketArchive = new ArrayList<>();
    public static int generateTicket(String name, int age) {
        // 底部方法层不应信任一切传入的数据, 即便将异常防御写在了外部
        // 内部依然有必要保留异常防御
        if (name == null || name.isEmpty() || age < 0 || age > 255){
            throw new IllegalArgumentException("error:数据输入异常");
        }

        ticketArchive.add(new Ticket(ticketArchive.size() + 1, name, age));
        return ticketArchive.size();
    }

    public static String callNextPatient() {
        currentTicket++;
        return ("请" + currentTicket + "号患者就诊");
    }

    public static String checkTicket(Ticket ticket){
        if (ticket == null){
            return "未检测到有效票据，无法处理。";
        }

        if(ticket.getTicketNumber() < currentTicket){
            return (ticket.getTicketNumber()+"号患者：过号请重排.");
        }
        else if(ticket.getTicketNumber() == currentTicket){
            return (ticket.getTicketNumber()+"号患者：您久等了，请前往n号诊室.");
            // 对号统一走一个验证渠道，可能未必符合大部分医院真实的排号系统
        }
        else {
            return (ticket.getTicketNumber()+"号患者：滚回去排队.");
        }
    }

    public static Ticket getTicket(int number){
        if (number <= 0 || number > ticketArchive.size()){
            return null;
        }
        return ticketArchive.get(number - 1);
    }
}


public class Hospital {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.println("\n=== 欢迎来到红脖子医院 ===");
            System.out.println("1. 老登挂号");
            System.out.println("2. 医生叫号");
            System.out.println("3. 老登查号");
            // 并非 HospitalMachine 所属功能, 临时安置
            System.out.println("4. 老登看号");
            System.out.println("0. 关机下班");
            System.out.print("请输入操作指令：");

            String choice = scan.nextLine().trim();

            // 健壮的系统不应该将这些功能囊括在一起,
            // 换句话说,现在的系统,绝对不能让患者可以触摸到.
            switch (choice){
                case "1":
                    String name;
                    int age;
                    while(true){
                        System.out.print("请输入姓名: ");
                        name = scan.nextLine().trim();
                        if (name.isEmpty()){
                            System.out.println("输入不合法: 我说请输入文本,尼尔多隆吗");
                            continue;
                        }
                        break;
                    }
                    while(true){
                        System.out.print("请输入年龄: ");
                        try {
                            age = scan.nextInt();
                        } catch(InputMismatchException e){
                            System.out.println("输入不合法: MAGA写年龄人均是首诗.");
                            // 不要忘记洗管道
                            scan.nextLine();
                            continue;
                        }
                        if (age < 0 || age > 255){
                            System.out.println("输入不合法: MAGA的存在是薛定谔叠加态");
                            // 不要忘记洗管道
                            scan.nextLine();
                            continue;
                        }
                        scan.nextLine();
                        break;
                    }
                    try {
                        int ticketNumber = HospitalMachine.generateTicket(name,age);
                        System.out.println("录入成功, 您的号码是: " + ticketNumber);
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case "2":
                    System.out.println(HospitalMachine.callNextPatient());
                    break;
                case "3":
                    System.out.println("请输入你的号码");
                    int queriedNumber;
                    try {
                        queriedNumber = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("MAGA人均数学博士");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
                    System.out.println(HospitalMachine.checkTicket(HospitalMachine.getTicket(queriedNumber)));
                    break;
                case "4":
                    // 并非 HospitalMachine 所属功能, 临时安置
                    System.out.print("MAGA! 让我康康我的票, 我是几号来着:");
                    int myNumber;
                    try {
                        myNumber = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("MAGA人均数学博士");
                        scan.nextLine();
                        continue;
                    }
                    scan.nextLine();
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
