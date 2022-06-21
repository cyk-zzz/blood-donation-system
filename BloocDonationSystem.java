import java.util.ArrayList;
import java.util.Scanner;

public class BloocDonationSystem {
    final String adminUsername = "admin";
    final String adminPassword = "admin";
    final int BLOOD_DONOR_MAX = 500;
    final int ADMIN_MAX = 10;
    ArrayList<BloodDonor> DonorList = new ArrayList<>();
    ArrayList<BloodDonationEvent> EventList = new ArrayList<>();

    public void displayBloodList(){
        for (Blood b:Blood.values()){
            System.out.printf("%d. %s\n",b.ordinal()+1,b.getName());
        }
    }
    public void displayRole(){
        System.out.println("1. Admin");
        System.out.println("2. Blood Donor");
        System.out.println("3. Exit");
    }
    public boolean ChoiceNotInRange(int min, int max, int input){
        if((input < min)||(input > max)){
            return true;
        }
        return false;
    }
    public int getChoice(){
        Scanner input = new Scanner(System.in);
        System.out.print("Choice: ");
        int choice = input.nextInt();
        return choice;
    }

    public void adminMenu(){

    }

    public void donorMenu(){
        
    }

    public static void main(String[] args) {
        BloocDonationSystem sys = new BloocDonationSystem();
        Scanner input = new Scanner(System.in);
        int role = 0;
        do{
            try{
                sys.displayRole();
                role = sys.getChoice();
                if(sys.ChoiceNotInRange(1, 3, end)){
                    throw new InvalidChoice();
                }
                switch(role){
                    case(1): sys.adminMenu();break;
                    case(2): sys.donorMenu();break;
                }
            }
            
            catch (InvalidChoice e){
                e.printStackTrace();
            }
        }while (role!=3);
    }
}
