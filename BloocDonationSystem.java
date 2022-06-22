import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BloocDonationSystem {
    final String adminUsername = "admin";
    final String adminPassword = "admin";
    final int BLOOD_DONOR_MAX = 500;
    final int ADMIN_MAX = 10;
    final String BloodDonorPath = "Text\\BloodDonor.txt";
    final String BloodDonorEventPath = "Text\\BloodDonorEvent.txt";
    private ArrayList<BloodDonor> DonorList = new ArrayList<>();
    private ArrayList<BloodDonationEvent> EventList = new ArrayList<>();

    public void loadDonor() throws FileNotFoundException{
        File DonorFile = new File(BloodDonorPath);
        Scanner Read = new Scanner(DonorFile);
        Read.useDelimiter(",");
        while(Read.hasNext()){
            String username = Read.next();
            String password = Read.next();
            String firstName = Read.next();
            String lastName = Read.next();
            String IC = Read.next();
            int yearBirth = Read.nextInt();
            double weight = Read.nextDouble();
            String bloodType = Read.next();

            DonorList.add(new BloodDonor(username, password, firstName, lastName, IC, yearBirth, weight, bloodType));
        }
    }
    public void loadEvent(){
        File EventFile = new File(BloodDonorEventPath);
        Scanner read = new Scanner(EventFile);
        read.useDelimiter(",");
        while(read.hasNext()){
            int id = read.nextInt();
            String state = read.next();
            String address = read.next();
            String date = read.next();

            EventList.add(new BloodDonationEvent(id, state, address, date));
        }
        read.close();

        System.out.println("Eventlist:");
        for(int i=0; i<EventList.size();i++){
            System.out.println(EventList.get(i)+"\n");
        }
    }
    public void saveDonor(){

    }
    public void saveEvent(){

    }

    public void displayBloodList(){
        for (Blood b:Blood.values()){
            System.out.printf("%d. %s\n",b.ordinal()+1,b.getName());
        }
    }
    public void displayState(){
        for (State s:State.values()){
            System.out.printf("%d. %s\n",s.ordinal()+1,s.name());
        }
    }
    public void displayHeader(){
        System.out.println("BLOOD DONATION SYSTEM");
        System.out.println("Liu Jia Hwee");
        System.out.println("Cheah Yau Khin");
        System.out.println("Tie Sing Hao");
        System.out.println("Lai Chee Yee");
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
        input.close();
        return choice;
    }

    public void mainMenu() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do {
            try {
                displayHeader();
                System.out.println("1. Admin");
                System.out.println("2. Blood Donor");
                System.out.println("3. Exit");
                choice = getChoice();
                if (ChoiceNotInRange(1, 3, choice)) {
                    throw new InvalidChoice();
                }
                switch (choice) {
                    case (1):
                        adminMenu();
                        break;
                    case (2):
                        donorMenu();
                        break;
                    default:
                        System.out.println("Thank You");
                }
            }

            catch (InvalidChoice e) {
                e.printStackTrace();
            }
        } while (choice != 3);
        input.close();
    }

    public void adminMenu(){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do {
            try {
                choice = getChoice();
                if (ChoiceNotInRange(1, 3, choice)) {
                    throw new InvalidChoice();
                }
                switch (choice) {
                    case (1):
                        adminMenu();
                        break;
                    case (2):
                        donorMenu();
                        break;
                    default:
                        System.out.println("Thank You");
                }
            }

            catch (InvalidChoice e) {
                e.printStackTrace();
            }
        } while (choice != 3);
        input.close();
    }

    public void donorMenu(){
        
    }

    public static void main(String[] args) throws FileNotFoundException {
        BloocDonationSystem sys = new BloocDonationSystem();
        sys.loadDonor();
    }
}
