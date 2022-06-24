import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
    private int currLoggedInDonorID; // set curr donor

    Scanner input = new Scanner(System.in);

    public ArrayList<BloodDonor> getDonorList(){
        return DonorList;
    }

    public ArrayList<BloodDonationEvent> getEventList(){
        return EventList;
    }

    //done
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

    }

    //done
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

    //done
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
    
    //done
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

    //done
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

    //done
    public void saveEvent() throws FileNotFoundException{
        PrintWriter EventFile = new PrintWriter(BloodDonorEventPath);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(int i=0; i<EventList.size();i++){
            EventFile.print(EventList.get(i).getEventID()+",");
            EventFile.print(EventList.get(i).getEventName()+",");
            EventFile.print(EventList.get(i).getEventState()+",");
            EventFile.print(EventList.get(i).getEventAddress()+",");
            EventFile.print(EventList.get(i).getEventDate().format(formatter)+",");

            if(i!=(EventList.size()-1)){
                EventFile.print("\n");
            }
        }

        EventFile.close();
    }

    //done
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    //done
    public void pressEnterToContinue(){
        System.out.print("Press enter to continue..");
        input.nextLine();
        input.nextLine();
        clearScreen();
    }

    //done
    public void displayBloodList(){
        for (Blood b:Blood.values()){
            System.out.printf("%d. %s\n",b.ordinal()+1,b.getName());
        }
    }
    //done
    public void displayState(){
        for (State s:State.values()){
            System.out.printf("%d. %s\n",s.ordinal()+1,s.name());
        }
    }

    //done
    public void displayHeader(){
        clearScreen();
        System.out.println("==========================");
        System.out.println("BLOOD DONATION SYSTEM\n");
        System.out.println("1. Liu Jia Hwee");
        System.out.println("2. Cheah Yau Khin");
        System.out.println("3. Tie Sing Hao");
        System.out.println("4. Lai Chee Yee");
        System.out.println("==========================");
    }
    
    //done
    public boolean ChoiceNotInRange(int min, int max, int input){
        if((input < min)||(input > max)){
            return true;
        }
        return false;
    }

    //done
    public int getChoice() throws InputMismatchException{
        System.out.print("Choice: ");
        int choice = input.nextInt();

        System.out.println();
        return choice;
    }

    //functions for admin
    //done
    public void addNewEvent(){
        //Scanner input = new Scanner(System.in);
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

        EventList.add(new BloodDonationEvent(eID, eName, eState, eAddress, eDate));
        clearScreen();
    }

    //done
    public void editEvent(){
        int innerChoice=0 ,choice=0;
        while(choice!=(EventList.size()+1)){
            try{
                printEventList();
                System.out.printf("%-4s%s\n",(EventList.size()+1),"Back to Admin Menu\n");

                choice = getChoice();
                if (ChoiceNotInRange(1, (EventList.size()+1), choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                //if not back to admin menu
                if(choice != (EventList.size()+1)){
                    EventList.get((choice-1)).printInfo();

                    //field to edit
                    System.out.println("<<< Edit Event >>>");
                    System.out.println("1. Name");
                    System.out.println("2. State");
                    System.out.println("3. Address");
                    System.out.println("4. Date");
                    System.out.println("5. Cancel");
                    innerChoice = getChoice();
                    if (innerChoice == 1) {
                        EventList.get((choice - 1)).editEventName();
                    } else if (innerChoice == 2) {
                        EventList.get((choice - 1)).editState();
                    } else if (innerChoice == 3) {
                        EventList.get((choice - 1)).editAddress();
                    } else if (innerChoice == 4) {
                        EventList.get((choice - 1)).editDate();
                    }
                    
                }
            }

            catch (InputMismatchException e){
                input.next();
                System.out.println("Invalid Input!!");
            }
            catch (InvalidChoice e) {
                System.out.println("Invalid Choice!!");
            }
        }
    }

    //done
    public void printEventList(){
        clearScreen();
        System.out.println("==========================");
        System.out.println("      Event List");
        System.out.println("==========================");
        //header
        System.out.printf("%-4s%-8s%-30s%-15s%-50s%s\n","No.","ID","Event Name","State","Address","Date");
        //content
        for (int i=0;i<EventList.size();i++){
            System.out.printf("%-4s",i+1);
            System.out.printf("%-8s",EventList.get(i).getEventID());
            System.out.printf("%-30s",EventList.get(i).getEventName());
            System.out.printf("%-15s",EventList.get(i).getEventState());
            System.out.printf("%-50s",EventList.get(i).getEventAddress());
            System.out.printf("%s",EventList.get(i).getEventDate());
            System.out.println();
        }
    }

    //done
    public void printDonorList(){
        clearScreen();
        System.out.println("==========================");
        System.out.println("      Donor List");
        System.out.println("==========================");
        //header
        System.out.printf("%-4s%-15s%-30s%-15s%-6s%-12s%s\n","No.","Username","Name","IC","YOB","Weight(kg)","Blood Type");
        //content
        for (int i=0;i<DonorList.size();i++){
            System.out.printf("%-4s",i+1);
            System.out.printf("%-15s",DonorList.get(i).getUsername());
            System.out.printf("%-30s",DonorList.get(i).getName());
            System.out.printf("%-15s",DonorList.get(i).getIC());
            System.out.printf("%-6s",DonorList.get(i).getYearBirth());
            System.out.printf("%-12s",DonorList.get(i).getWeight());
            System.out.printf("%s",DonorList.get(i).getBloodType());
            System.out.println();
        }
    }

    //can do approve pending application if got time
    public void adminMenu(){
        int choice = 0;
        do {
            try {
                System.out.println("==========================");
                System.out.println("      Admin Menu");
                System.out.println("==========================");
                System.out.println("1. Add New Event");
                System.out.println("2. Edit Event");
                System.out.println("3. Display Event List");
                System.out.println("4. Display Donor List");
                System.out.println("5. Logout");

                choice = getChoice();
                if (ChoiceNotInRange(1, 5, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                switch (choice) {
                    case (1):
                        addNewEvent();
                        break;
                    case (2):
                        editEvent();
                    case (3):
                        printEventList();
                        pressEnterToContinue();
                        break;
                    case (4):
                        printDonorList();
                        pressEnterToContinue();
                        break;
                    case (5):
                        break;
                }
            }

            catch (InputMismatchException e){
                input.next();
                System.out.println("Invalid Input");
            }

            catch (InvalidChoice e) {
                System.out.println("Invalid Choice");
            }
        } while(choice!=5);

    }

    
    // got problem, check tmr
    public boolean login(int role){
        clearScreen();
        System.out.println("==========================");
        System.out.println("         Login");
        System.out.println("==========================");
        System.out.print("Enter username: ");
        String username = input.next();
        System.out.print("Enter password: ");
        String password = input.next();

        System.out.println(username+" "+password);
        //admin
        if(role==1){
            System.out.println("Its admin");
            if(username =="admin" && password == "admin"){ 
                System.out.println("Valid");
                return true;
            }
        }
        //donor
        else if(role==2){
            for(int i=0; i<DonorList.size();i++){
                if(DonorList.get(i).getUsername()==username && DonorList.get(i).getPassword()==password){
                    System.out.println("Valid");
                    currLoggedInDonorID = DonorList.get(i).getID();
                    return true;
                }
            }
        }
        System.out.println("Invalid");
        return false; // by default, return false
    }

    //functions for donor
    //done
    public void register(){
        clearScreen();
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

        int id = DonorList.size();
        DonorList.add(new BloodDonor(id,username, password, firstName, lastName, IC, yearBirth, weight, bloodType));

    }
    
    //done
    public void viewRegisteredEvent(){
        int index = 1;
        //set curr donor id first, later use login function
        currLoggedInDonorID = 1;

        clearScreen();
        System.out.println("==========================");
        System.out.println("    Registered Event(s)");
        System.out.println("==========================");

        System.out.printf("%-4s%-40s%-15s%-30s%s\n","No.","Name", "State", "Address", "Date");
        //loop through whole event list

        for(int i=0; i<EventList.size();i++){
            for(int j=0; j<EventList.get(i).getDonorList().size();j++){
                if(EventList.get(i).getDonorList().get(j).getID()==currLoggedInDonorID){
                    System.out.printf("%-4s",index);
                    EventList.get(i).printInfoWithoutDonor();
                    index++;
                }
            }
        }
    }

    public void eventRegistration(){

    }

    //not yet 
    public void donorMenu() throws InputMismatchException{
        int choice = 0;

        do {
            try {
                System.out.println("==========================");
                System.out.println("      Donor Menu");
                System.out.println("==========================");
                System.out.println("1. Display Event List");
                System.out.println("2. Display Registered Event");
                System.out.println("3. Register For Event");
                System.out.println("4. Logout");

                choice = getChoice();
                if (ChoiceNotInRange(1, 4, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                switch (choice) {
                    case (1):
                        printEventList();
                        pressEnterToContinue();
                        break;
                    case (2):
                        viewRegisteredEvent();
                        pressEnterToContinue();
                        break;
                    case (3):
                        eventRegistration();
                        break;
                    case (4):
                        break;
                }
            }

            catch (InputMismatchException e){
                input.next();
                System.out.println("Invalid Input");
            }

            catch (InvalidChoice e) {
                System.out.println("Invalid Choice");
            }
        } while(choice!=4);

    }

    //not yet assign function
    public void menuBeforeLogin(int role) throws InputMismatchException{
        int choice;
        do {
            try {
                displayHeader();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Back to Main Menu");
                choice = getChoice();
                if (ChoiceNotInRange(1, 3, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                switch (choice) {
                    case (1):
                        register();
                        break;
                    case (2):
                        if(login(role)){
                            if(role==1){ adminMenu();}
                            else if(role==2){ donorMenu();}
                        }
                        break;
                    case (3):
                        break;
                        
                }
            }

            catch (InvalidChoice e) {
                System.out.println("Invalid Choice");
                continue;
            }
            
        }while(choice!=3)
    }

    public void mainMenu() {
        int choice = 0;
        while(choice!=3) {
            try {
                displayHeader();
                System.out.println("1. Admin");
                System.out.println("2. Blood Donor");
                System.out.println("3. Exit");

                choice = getChoice();
                if (ChoiceNotInRange(1, 3, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                switch (choice) {
                    case (1):
                        menuBeforeLogin(1);
                        break;
                    case (2):
                        menuBeforeLogin(2);
                        break;
                    case (3):
                        System.out.println("Thank You~ Have a nice day~");
                        
                }
            }

            catch (InputMismatchException e){
                input.next();
                clearScreen();
                System.out.println("Invalid Input");
            }

            catch (InvalidChoice e) {
                clearScreen();
                System.out.println("Invalid Choice");
            }
        } 

    }

    public static void main(String[] args) throws FileNotFoundException {
        BloocDonationSystem sys = new BloocDonationSystem();

        sys.loadEvent();
        sys.loadDonor();
        sys.loadAssoc();

        sys.login(1);

        sys.saveAssoc();
        sys.saveDonor();
        sys.saveEvent();
        System.exit(0);
    }
}
