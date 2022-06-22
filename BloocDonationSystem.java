import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class BloocDonationSystem {
    final String adminUsername = "admin";
    final String adminPassword = "admin";
    final int BLOOD_DONOR_MAX = 500;
    final int ADMIN_MAX = 10;
    final String BloodDonorPath = "Text\\BloodDonor.txt";
    final String BloodDonorEventPath = "Text\\BloodDonorEvent.txt";
    private ArrayList<BloodDonor> DonorList = new ArrayList<>();
    private ArrayList<BloodDonationEvent> EventList = new ArrayList<>();

    public ArrayList<BloodDonor> getDonorList(){
        return DonorList;
    }

    public ArrayList<BloodDonationEvent> getEventList(){
        return EventList;
    }

    public void loadDonor() throws FileNotFoundException{
        File DonorFile = new File(BloodDonorPath);
        Scanner read = new Scanner(DonorFile);
        read.useDelimiter(",");
        while(read.hasNext()){
            String username = read.next();
            username = username.replace("\n","");
            String password = read.next();
            String firstName = read.next();
            String lastName = read.next();
            String IC = read.next();
            int yearBirth = read.nextInt();
            double weight = read.nextDouble();
            String bloodType = read.next();

            DonorList.add(new BloodDonor(username, password, firstName, lastName, IC, yearBirth, weight, bloodType));
        }
        read.close();

        for(int i=0; i<DonorList.size(); i++){
            DonorList.get(i).printInfo();
        }
    }
    public void loadEvent() throws FileNotFoundException{
        File EventFile = new File(BloodDonorEventPath);
        Scanner read = new Scanner(EventFile);
        read.useDelimiter(",");
        while(read.hasNext()){
            String id = read.next();
            id = id.replace("\n","");
            String name = read.next();
            String state = read.next();
            String address = read.next();
            String date = read.next();
            
            EventList.add(new BloodDonationEvent(id, name, state, address, date));
        }
        
        read.close();

        for(int i=0; i<EventList.size(); i++){
            EventList.get(i).printInfo();
        }
    }
    public void saveDonor() throws FileNotFoundException{
        PrintWriter DonorFile = new PrintWriter(BloodDonorPath);
        for(int i=0; i<DonorList.size(); i++){
            DonorFile.print(DonorList.get(i).getUsername()+",");
            DonorFile.print(DonorList.get(i).getPassword()+",");
            DonorFile.print(DonorList.get(i).getFirstName()+",");
            DonorFile.print(DonorList.get(i).getLastName()+",");
            DonorFile.print(DonorList.get(i).getIC()+",");
            DonorFile.print(DonorList.get(i).getYearBirth()+",");
            DonorFile.print(DonorList.get(i).getWeight()+",");
            DonorFile.print(DonorList.get(i).getBloodType()+",");

            if(i!=(DonorList.size()-1)){
                DonorFile.print("\n");
            }
        }
        DonorFile.close();
    }
    public void saveEvent() throws FileNotFoundException{
        PrintWriter EventFile = new PrintWriter(BloodDonorEventPath);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(int i=0; i<EventList.size();i++){
            EventFile.print(EventList.get(i).getEventID()+",");
            EventFile.print(EventList.get(i).getEventName()+",");
            EventFile.print(EventList.get(i).getEventState().name()+",");
            EventFile.print(EventList.get(i).getEventAddress()+",");
            EventFile.print(EventList.get(i).getEventDate().format(formatter)+",");

            if(i!=(EventList.size()-1)){
                EventFile.print("\n");
            }
        }

        EventFile.close();
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
        //sys.loadEvent();
        //sys.getEventList().add(new BloodDonationEvent("e1004", "My Blood", "Selangor", "Block Z", "11/02/2009"));
        //sys.getEventList().add(new BloodDonationEvent("e1005", "My Blood v2", "Sarawak", "Block Y", "12/03/2009"));
        //sys.saveEvent();
        sys.loadDonor();
        sys.getDonorList().add(new BloodDonor("aliAndMuthu","1234", "hey", "bye", "1234567", 2001, 90, "B1"));
        sys.saveDonor();

    }
}
