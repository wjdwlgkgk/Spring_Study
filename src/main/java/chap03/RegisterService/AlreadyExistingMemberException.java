package chap03.RegisterService;

public class AlreadyExistingMemberException extends RuntimeException{
    public AlreadyExistingMemberException(String message){
        super(message);
    }
}
