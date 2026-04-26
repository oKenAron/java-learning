package day260426_00_basics;

public class Puppy {
    private String puppyName;
    public Puppy(String name) {
        this.puppyName = name;
        System.out.println("小狗的名字是：" + this.puppyName);
    }
    public void puppyBark(){
        System.out.println("指挥小狗叫.\n"+this.puppyName+":Bark!!!");
    }
    public void renamePuppy(String name){
        System.out.print("重命名小狗 "+this.puppyName);
        this.puppyName = name;
        System.out.println(" 为："+this.puppyName);
    }
    public static void main(String[] args) {
        Puppy barkingPuppy = new Puppy("Tommy");
        barkingPuppy.puppyBark();
        barkingPuppy.renamePuppy("Trump");
        barkingPuppy.puppyBark();
    }
}
