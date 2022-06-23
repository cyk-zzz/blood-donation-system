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
    final String BloodDonorPath = "Text\\Donor.txt";
    final String BloodDonorEventPath = "Text\\Event.txt";
    final String EventAssocPath = "Text\\EventAssoc.txt";
    private ArrayList<BloodDonor> DonorList = new ArrayList<>();
    private ArrayList<BloodDonationEvent> EventList = new ArrayList<>();

    public ArrayList<BloodDonor> getDonorList(){
        return DonorList;
    }

    public ArrayList<BloodDonationEvent> getEventList(){
        return EventList;
    }

    public void loadAssoc() throws FileNotFoundException{
        File AssocFile = new File(EventAssocPath);
        Scanner read = new Scanner(AssocFile);
        int findEvent = 0;
        while(read.hasNextLine()){
            String x = read.nextLine();
            String[] y = x.split(",");

            if (y.length==1){
                continue;
            }

            for (int donorIndex=1;donorIndex<y.length;donorIndex++){
                EventList.get(findEvent).addDonor(DonorList.get(Integer.parseInt(y[donorIndex])));
            }
            findEvent++;
        }
        read.close();

        for (BloodDonationEvent x : EventList){
            x.printInfo();
            System.out.println();
        }
    }

    public void loadDonor() throws FileNotFoundException{
        File DonorFile = new File(BloodDonorPath);
        Scanner read = new Scanner(DonorFile);
        read.useDelimiter(",");
        int id = 0;
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

            DonorList.add(new BloodDonor(id, username, password, firstName, lastName, IC, yearBirth, weight, bloodType));
            id++;
        }
        read.close();

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
    }
    
    public void saveAssoc() throws FileNotFoundException{
        PrintWriter AssocFile = new PrintWriter(EventAssocPath);
        String perLine = new String();
        for(int i=0; i<EventList.size(); i++){
            perLine = EventList.get(i).getEventID();
            for (int j=0;j<EventList.get(i).getDonorList().size();j++){
                perLine += ",";
                perLine += EventList.get(i).getDonorList().get(j).getID();
            }
            if(i!=(EventList.size()-1)){
                perLine += "\n";
            }
            AssocFile.print(perLine);
        }
        AssocFile.close();
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

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
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
        System.out.println("==========================");
        System.out.println("BLOOD DONATION SYSTEM\n");
        System.out.println("1. Liu Jia Hwee");
        System.out.println("2. Cheah Yau Khin");
        System.out.println("3. Tie Sing Hao");
        System.out.println("4. Lai Chee Yee");
        System.out.println("==========================");
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
        //int choice = 0;
        while(true) {
            try {
                displayHeader();
                System.out.println("1. Admin");
                System.out.println("2. Blood Donor");
                System.out.println("3. Exit");
                int choice = getChoice();
                if (ChoiceNotInRange(1, 3, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                switch (choice) {
                    case (1):
                        System.out.println("1");
                        //adminMenu();
                        break;
                    case (2):
                        System.out.println("2");
                        menuBeforeLogin(); // to choose either register or login
                        break;
                    case (3):
                        System.out.println("Thank You~ Have a nice day~");
                        System.exit(0);
                }
            }

            catch (InvalidChoice e) {
                System.out.println("Invalid Choice");
                continue;
            }
            
        }
        
    }

    public void menuBeforeLogin(){
        //int choice = 0;
        while(true) {
            try {
                displayHeader();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                int choice = getChoice();
                if (ChoiceNotInRange(1, 3, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                switch (choice) {
                    case (1):
                        System.out.println("1");
                        //adminMenu();
                        break;
                    case (2):
                        System.out.println("2");
                        //donorMenu();
                        break;
                    case (3):
                        System.out.println("Thank You~ Have a nice day~");
                        System.exit(0);
                }
            }

            catch (InvalidChoice e) {
                System.out.println("Invalid Choice");
                continue;
            }
            
        }
    }

    //functions for admin
    public void addNewEvent(){
        Scanner input = new Scanner(System.in);
        String eID, eName, eState, eAddress, eDate;

        clearScreen();
        System.out.println("==========================");
        System.out.println("      Add New Event");
        System.out.println("==========================");
        System.out.print("Enter Event ID: ");
        eID = input.next();
        input.nextLine();
        System.out.print("Enter Event Name: ");
        eName = input.nextLine();
        System.out.print("Enter State: ");
        eState = input.next();
        input.nextLine();
        System.out.print("Enter Address: ");
        eAddress = input.nextLine();
        System.out.print("Enter Date (dd/mm/yyyy): ");
        eDate = input.next();

        getEventList().add(new BloodDonationEvent(eID, eName, eState, eAddress, eDate));

        input.close();
    
      
    }

    public void adminMenu(){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do {
            try {
                System.out.println("==========================");
                System.out.println("      Admin Menu");
                System.out.println("==========================");
                System.out.println("1. Add New Event");
                System.out.println("2. View Event List");
                System.out.println("3. Add Donor");//aprrove? need pending application list?
                System.out.println("4. Exit");

                choice = getChoice();
                if (ChoiceNotInRange(1, 3, choice)) {
                    throw new InvalidChoice();
                }
                switch (choice) {
                    case (1):
                        //addNewEvent();
                        break;
                    case (2):
                        
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

    //functions for donor
    public void register(){
        clearScreen();

        Scanner input = new Scanner(System.in);
        
        System.out.println("==========================");
        System.out.println("      Registration");
        System.out.println("==========================");
        System.out.print("Enter username: ");
        String username = input.next();
        System.out.print("Enter password: ");
        String password = input.next();
        System.out.print("Enter First Name: ");
        String firstName = input.next();
        System.out.print("Enter Last Name: ");
        String lastName = input.next();
        System.out.print("Enter IC: ");
        String IC = input.next();
        System.out.print("Enter Year Of Birth: ");
        int yearBirth = input.nextInt();
        System.out.print("Enter Weight (in kg): ");
        double weight = input.nextDouble();
        System.out.print("Enter Blood Type: ");
        String bloodType = input.next();

        DonorList.add(new BloodDonor(username, password, firstName, lastName, IC, yearBirth, weight, bloodType));

        input.close();
    }
    
    public void donorMenu(){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do {
            try {
                System.out.println("==========================");
                System.out.println("      Donor Menu");
                System.out.println("==========================");
                System.out.println("1. Add New Event");
                System.out.println("2. View Event List");
                System.out.println("3. Add Donor");//aprrove? need pending application list?
                System.out.println("4. Exit");

                choice = getChoice();
                if (ChoiceNotInRange(1, 3, choice)) {
                    throw new InvalidChoice();
                }
                switch (choice) {
                    case (1):
                        //addNewEvent();
                        break;
                    case (2):
                        
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

    public static void main(String[] args) throws FileNotFoundException {
        BloocDonationSystem sys = new BloocDonationSystem();
        
        //sys.getEventList().add(new BloodDonationEvent("e1004", "My Blood", "Selangor", "Block Z", "11/02/2009"));
        //sys.getEventList().add(new BloodDonationEvent("e1005", "My Blood v2", "Sarawak", "Block Y", "12/03/2009"));

        
        //sys.getDonorList().add(new BloodDonor("aliAndMuthu","1234", "hey", "bye", "1234567", 2001, 90, "B1"));
        

        //sys.mainMenu(); //all menus got problem!

        sys.loadEvent();
        //sys.addNewEvent();
        //sys.saveEvent();
        sys.loadDonor();
        sys.loadAssoc();
        
        sys.saveAssoc();
        //sys.loadDonor();
        //sys.register();
        //sys.saveDonor();
        System.exit(0);
    }
}
