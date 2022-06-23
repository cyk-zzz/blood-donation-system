public class InvalidChoice extends Exception {
    
    private String msg;
    public InvalidChoice(){
        super();
    }
    
    public InvalidChoice(String msg){
        super(msg);
        this.msg = msg;
    }

    public String toString(){
        return msg;
    }
}
