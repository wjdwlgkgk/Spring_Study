package chap02;

public class ContructTestBean {

    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private String name5;


    public ContructTestBean(String name1,String name2,String name3,String name4,String name5){
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.name5 = name5;
    }

    public ContructTestBean(String name1){
        this.name1 = name1;
    }

    public void print(){
        System.out.println("name1 = " + name1);
        System.out.println("name2 = " + name2);
        System.out.println("name3 = " + name3);
        System.out.println("name4 = " + name4);
        System.out.println("name5 = " + name5);
    }
    /*
    bean�� ���� constructor-arg�� ���� ���� �� �޴� ���� �����Ѱ�?
    bean�� ��¶�ų� construct�� �����Ѵ�.
     */
}
