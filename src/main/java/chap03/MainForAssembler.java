package chap03;

import chap03.Data.IdPasswordNotMatchingException;
import chap03.Data.Member;
import chap03.PasswordChangeService.ChangePasswordService;
import chap03.PasswordChangeService.MemberNotFoundException;
import chap03.RegisterService.AlreadyExistingMemberException;
import chap03.RegisterService.MemberRegisterService;
import chap03.RegisterService.RegisterRequest;
import chap03.assembler.Assembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {

    private static Assembler assembler =new Assembler();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("��ɾ �Է��ϼ���: ");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("�����մϴ�.");
                break;
            }
            if (command.startsWith("new")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change")) {
                processChangeCommand(command.split(" "));
                continue;
            } else if (command.startsWith("view")){
                processViewCommand(command.split(" "));
                continue;
            }
            printHelp();
        }
    }

    private static void processNewCommand(String[] arg){
        if(arg.length != 5){
            printHelp();
            return ;
        }
        MemberRegisterService regSvc = assembler.getMemberRegisterService();
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if(!req.isPasswordEqualToConfirmPassword()){
            System.out.println("��ȣ�� Ȯ���� ��ġ���� �ʽ��ϴ�.\n");
            return ;
        }
        try{
            regSvc.regist(req);
            System.out.println("����߽��ϴ�.\n");
        }catch (AlreadyExistingMemberException e){
            System.out.println("�̹� �����ϴ� �̸����Դϴ�.\n");
        }
    }

    private static void processChangeCommand(String[] arg){
        if(arg.length != 4){
            printHelp();
            return ;
        }
        ChangePasswordService PwdSvc = assembler.getChangePasswordService();
        try{
            PwdSvc.changePassword(arg[1],arg[2],arg[3]);
            System.out.println("��ȣ�� �����߽��ϴ�.\n");
        }catch (MemberNotFoundException e){
            System.out.println("�������� �ʴ� �̸����Դϴ�.\n");
        }catch (IdPasswordNotMatchingException e){
            System.out.println("�̸��ϰ� ��ȣ�� ��ġ���� �ʽ��ϴ�.\n");
        }
    }

    private static void processViewCommand(String[] arg){
        Member member = assembler.getMemberDao().selectByEmail(arg[1]);
        System.out.println("email : " + member.getEmail());
        System.out.println("name : " + member.getName());
        System.out.println("password : " + member.getPassword());
        System.out.println("registerDate : " + member.getRegisterDate());
    }

    private static void printHelp(){
        System.out.println();
        System.out.println("�߸��� ����Դϴ�. �Ʒ� ��ɾ� ������ Ȯ���ϼ���.");
        System.out.println("��ɾ� ����:");
        System.out.println("new �̸��� �̸� ��ȣ ��ȣȮ��");
        System.out.println("change �̸��� ������, ������");
        System.out.println();
    }
}
