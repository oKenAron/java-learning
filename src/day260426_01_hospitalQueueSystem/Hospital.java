package day260426_01_hospitalQueueSystem;

// 2026.05.08
// 执行了以下改进:
// 为 HospitalMachine.getTicket(number) 补充异常防御, 但担心不够健壮.
// 为遵守单一事实来源原则, 将 totalTicket 逻辑由 ticketArchive.size() 代替.
// 替换轮询逻辑中的 if 为 switch case.

// 需要思考: 类中方法需要更可读的排序

import java.util.Scanner;
import java.util.ArrayList;

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
    // 去除 totalTicket 逻辑, 遵守单一事实来源.
    // private static int totalTicket = 0;
    private static int currentTicket = 0;
    private static final Scanner scan = new Scanner(System.in);
    private static final ArrayList<Ticket> ticketArchive = new ArrayList<>();
    // 尝试将 Ticket 类的对象完全封装在 HospitalMachine 类内, 所以修改为 void.
    public static void generateTicket() {
        try{
            System.out.print("请输入姓名: ");
            // 因为姓名很可能包含空格, nextLine 读取整行更鲁棒，另外 trim() 可以帮助砍掉首尾的无意义空格。
            String name = scan.nextLine().trim();
            // 在 java 里, null和空字符不是一个东西，这里判断要用 .isEmpty()
            if (name.isEmpty()){
                // 自订逻辑炸弹
                throw new IllegalArgumentException("我说请输入文本，尼尔多隆吗! ");
            }
            System.out.print("请输入年龄: ");
            int age = scan.nextInt();
            // nextInt()很刁钻，它不会处理到留在管道里的回车，所以要手动处理。
            scan.nextLine();
            if (age < 0 || age > 255){
                // 自订逻辑炸弹
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
            // 这里逻辑和上面我定的错误貌似是不相关的，我大概是这么理解的。
            // 这里我是照着 Gemini 给的指示写的，所以这部分的语法对我来说是弱肌肉记忆。
            // 包括java一长串，还有后面接的e，感觉这里e是类似于一般方法使用时括号里定义的变量，
            // 只不过这个变量e是高度封装的，且在这里至少是作为占位符，下一个catch才用到了e。
            // 感觉是除了前面的两个if判断的排错外，就只剩下预期int输入怪东西一个脏数据的可能。
            // 所以才这么写。
            System.out.println("系统警告：您的年龄光是写下来就是本书! ");
            // 输入脏东西，nextInt()会报错罢工，这意味着脏东西没被抽走而是卡在管道里，
            // 一般清空管道就放一个没有头的nextLIne()就行。
            scan.nextLine();
            // 因为发号失败了，所以返回null;
            // 这里返回了null, 恰好对应上checkTicket对null的判断。
        }
        catch(IllegalArgumentException e){
            // e.getMessage() 会提取出throw里写的那句报错语，
            // 在我的理解里因为这个 error 因为被try来catch了，所以不像一般报错那么输出，
            System.out.println("系统警告：" + e.getMessage());
        }
    }

    public static void callNextPatient() {
        currentTicket++;
        System.out.println("请" + currentTicket + "号患者就诊");
    }

    public static Ticket getTicket(int number){
        return ticketArchive.get(number - 1);
    }

    public static void checkTicket(Ticket ticket){
        if (ticket == null){
            System.out.println("未检测到有效票据，无法处理。");
            return;
        }

        if(ticket.getTicketNumber() < currentTicket){
            // 确认过号之后要不要从内存里清除对应的ticket，值得思考。
            // 记得 Gemini 指摘过这个想法比较天真，但忘了具体是什么了。
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

    public static int getTicketArchiveSize(){
        return ticketArchive.size();
    }
}


public class Hospital {
    public static void main(String[] args) {
        Scanner mainScan = new Scanner(System.in);

        // 基于要求, 在这里写入while true死循环, 虽然符合目前工程上的需求,
        // 但还是感兴趣while true真的是工程上的最优解吗, 印象里死循环是坏代码.
        while(true) {
            System.out.println("\n=== 欢迎来到红脖子医院 ===");
            System.out.println("1. 老登挂号");
            System.out.println("2. 医生叫号");
            System.out.println("3. 老登查号");
            // 并非 HospitalMachine 所属方法, 临时安置
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
                    try {
                        int queriedNumber = mainScan.nextInt();
                        mainScan.nextLine();
                        HospitalMachine.checkTicket(HospitalMachine.getTicket(queriedNumber));
                    } catch (java.util.InputMismatchException e){
                        System.out.println("系统警告：这号存在于MAGA的理想国里");
                        mainScan.nextLine();
                    }
                    break;
                case "4":
                    // 并非 HospitalMachine 所属方法, 临时安置
                    System.out.print("MAGA! 让我康康我的票, 我是几号来着:");
                    try {
                        int myNumber = mainScan.nextInt();
                        mainScan.nextLine();
                        if (!(myNumber > HospitalMachine.getTicketArchiveSize())){
                            System.out.printf("\nMAGA! 我想起来了, 我不是爱泼斯坦, 我是\n"
                                    +HospitalMachine.getTicket(myNumber).getTicketInfo()+"\n");
                        } else {
                            // 感觉这里代码健壮性有问题, 但我不确定.
                            throw new java.util.InputMismatchException();
                        }
                    } catch (java.util.InputMismatchException e){
                        System.out.println("MAGA! 眼睛花了看不清");
                        mainScan.nextLine();
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
