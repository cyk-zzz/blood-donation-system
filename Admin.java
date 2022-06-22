public class Admin extends User{
    private int adminID;

    public Admin(String username, String password, int adminID){
        super(username, password);
        this.adminID = adminID;
    }

    public void printInfo(){
        System.out.printf("%-10d%-15s%-20s", adminID, username, password);
    }
}