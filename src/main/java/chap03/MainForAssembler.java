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
            System.out.print("명령어를 입력하세요: ");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
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
            System.out.println("암호와 확인이 일치하지 않습니다.\n");
            return ;
        }
        try{
            regSvc.regist(req);
            System.out.println("등록했습니다.\n");
        }catch (AlreadyExistingMemberException e){
            System.out.println("이미 존재하는 이메일입니다.\n");
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
            System.out.println("암호를 변경했습니다.\n");
        }catch (MemberNotFoundException e){
            System.out.println("존재하지 않는 이메일입니다.\n");
        }catch (IdPasswordNotMatchingException e){
            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
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
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법:");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번, 변경비번");
        System.out.println();
    }
}
