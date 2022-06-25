import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BloocDonationSystem {
    final String adminUsername = "admin";
    final String adminPassword = "admin";
    final int BLOOD_DONOR_MAX = 500;
    final int ADMIN_MAX = 10;
    final String BloodDonorPath = "Text\\Donor.txt";
    final String BloodDonorEventPath = "Text\\Event.txt";
    final String EventAssocPath = "Text\\EventAssoc.txt";
    final String PendingPath = "Text\\PendingApplication.txt";
    private ArrayList<BloodDonor> DonorList = new ArrayList<>();
    private ArrayList<BloodDonationEvent> EventList = new ArrayList<>();
    private ArrayList<Integer> ArrayReturned = new ArrayList<>(); 
    private int currLoggedInDonorID; // set curr donor

    Scanner input = new Scanner(System.in);

    public ArrayList<BloodDonor> getDonorList(){
        return DonorList;
    }

    public ArrayList<BloodDonationEvent> getEventList(){
        return EventList;
    }

    // to get pending applications
    public void loadPending() throws FileNotFoundException{
        File PendingFile = new File(PendingPath);
        Scanner read = new Scanner(PendingFile);
        int findEvent = 0;
        while(read.hasNextLine()){
            String x = read.nextLine();
            String[] y = x.split(",");

            if (y.length==1){
                findEvent++;
                continue;
            }

            for (int donorIndex=1;donorIndex<y.length;donorIndex++){
                EventList.get(findEvent).addPendingDonor(DonorList.get(Integer.parseInt(y[donorIndex])));
            }
            findEvent++;
        }
        read.close();
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
                findEvent++;
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
    
    public void savePending() throws FileNotFoundException{
        PrintWriter PendingFile = new PrintWriter(PendingPath);
        String perLine = new String();
        for(int i=0; i<EventList.size(); i++){
            perLine = EventList.get(i).getEventID();
            for (int j=0;j<EventList.get(i).getPendingDonorList().size();j++){
                perLine += ",";
                perLine += EventList.get(i).getPendingDonorList().get(j).getID();
            }

            if(i!=(EventList.size()-1)){
                perLine += "\n";
            }
            PendingFile.print(perLine);
        }
        PendingFile.close();
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

    //choose from whole list
    public void editEvent(){
        int innerChoice=0 ,choice=0,exitIndex;
        while(choice!=(EventList.size()+1)){
            try{
                exitIndex = printEventList(0); // display whole event list

                choice = getChoice();
                if (ChoiceNotInRange(1, exitIndex, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                //if not back to admin menu
                if(choice >0 && choice<exitIndex){
                    EventList.get(ArrayReturned.get(choice-1)).printInfo();

                    //field to edit
                    System.out.println("<<< Edit Event >>>");
                    System.out.println("1. Name");
                    System.out.println("2. State");
                    System.out.println("3. Address");
                    System.out.println("4. Date");
                    System.out.println("5. Cancel");
                    innerChoice = getChoice();
                    if (innerChoice == 1) {
                        EventList.get(ArrayReturned.get(choice-1)).editEventName();
                    } else if (innerChoice == 2) {
                        EventList.get(ArrayReturned.get(choice-1)).editState();
                    } else if (innerChoice == 3) {
                        EventList.get(ArrayReturned.get(choice-1)).editAddress();
                    } else if (innerChoice == 4) {
                        EventList.get(ArrayReturned.get(choice-1)).editDate();
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

    //variable day: 0=printEntireEventList 1=printPassEventList, 2=printUpcomingEventList
    // return exit index (index for back to admin menu)
    public int printEventList(int needChoose){
        //clearScreen();
        int day = needChoose; //by default, print upcoming event for approve donor application
        if(day == -1){
            System.out.println("==========================");
            System.out.println("   Display Event List");
            System.out.println("==========================");
            System.out.println("[0]=printEntireEventList; [1]=printPassEventList; [2]=printUpcomingEventList");
            day = getChoice();
            if(day!=0 && day!=1 && day!=2){
                return -1;// invalid choice, return to admin menu
            }
        }
        
        clearScreen();
        System.out.println("==========================");
        if(day == 0){
            System.out.println("   Event List");
        }
        else if(day == 1){
            System.out.println("   Past Event List");
        }
        else if(day == 2){
            System.out.println("   Upcoming Event List");
        }
        System.out.println("==========================");

        ArrayReturned.clear(); // clear the content of ArrayReturn before adding index
        System.out.printf("%-4s%-8s%-40s%-15s%-30s%s\n","No.","ID","Event Name","State","Address","Date");
        int count =1;
        LocalDate today = LocalDate.now();
        for (int i=0;i<EventList.size();i++){
            if(today.isAfter(EventList.get(i).getEventDate()) && day==2){
                continue;
            } 
            else if(today.isBefore(EventList.get(i).getEventDate()) && day==1){
                continue;
            }
            System.out.printf("%-4s",count);
            EventList.get(i).printInfoWithoutDonor();
            count++;
            ArrayReturned.add(i);
        }
        System.out.printf("%-4s%s\n",(count),"Back to admin menu\n");

        return count; // index for back to admin menu
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
            DonorList.get(i).printInfo();
        }
    }

    public void approvePending(){
        int innerChoice=0 ,choice=0, exitIndex=1;
        while(choice!=exitIndex){
            try{
                exitIndex = printEventList(2); // display pending list

                choice = getChoice();
                if (ChoiceNotInRange(1, exitIndex, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                //if not back to admin menu
                if(choice >0 && choice < exitIndex){
                    EventList.get(ArrayReturned.get(choice-1)).printInfo();
                    EventList.get(ArrayReturned.get(choice-1)).printPendingDonor();

                    innerChoice = getChoice();
                    if(innerChoice>-1 && innerChoice<EventList.get(ArrayReturned.get(choice-1)).getPendingDonorList().size()){
                        //add to approvelist
                        EventList.get(ArrayReturned.get(choice-1)).getDonorList().add(DonorList.get(EventList.get(ArrayReturned.get(choice-1)).getPendingDonorList().get(innerChoice-1).getID()));
                        //romove from pendingList
                        EventList.get(ArrayReturned.get(choice-1)).getPendingDonorList().remove(innerChoice-1);
                        
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
                System.out.println("5. Approve Pending Application");
                System.out.println("6. Logout");

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
                        int event=-1, exitIndex=-1;
                        do{
                            exitIndex = printEventList(-1); //choose whole/pass/upcoming event list
                            event = getChoice(); // choose event or exit
                            if(event<0 || event >= exitIndex){continue;}
                            clearScreen();
                            EventList.get(ArrayReturned.get(event-1)).printInfo();
                            System.out.println("\nRegistered Donors: ");
                            EventList.get(ArrayReturned.get(event-1)).printApprovedDonor();
                            EventList.get(ArrayReturned.get(event-1)).printPendingDonor();
                            pressEnterToContinue();
                        }while(event != exitIndex);
                        clearScreen();
                        break;
                    case (4):
                        printDonorList();
                        pressEnterToContinue();
                        break;
                    case (5):
                        approvePending();
                        clearScreen();
                        break;
                    case (6):
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
        } while(choice!=6);

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

        //admin
        if(role==1){
            if(username.equals("admin") && password.equals("admin")){ 
                clearScreen();
                return true;
            }
        }
        //donor
        else if(role==2){
            for(int i=0; i<DonorList.size();i++){
                if(DonorList.get(i).getUsername().equals(username) && DonorList.get(i).getPassword().equals(password)){
                    currLoggedInDonorID = DonorList.get(i).getID();
                    return true;
                }
            }
        }
        System.out.println("Invalid Username / Password");
        pressEnterToContinue();
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

        clearScreen();
        System.out.println("==========================");
        System.out.println("    Registered Event(s)");
        System.out.println("==========================");

        System.out.println("<<< Approved >>>");
        System.out.printf("%-4s%-40s%-15s%-30s%s\n","No.","Name", "State", "Address", "Date");
        for(int i=0; i<EventList.size();i++){
            for(int j=0; j<EventList.get(i).getDonorList().size();j++){
                if(EventList.get(i).getDonorList().get(j).getID()==currLoggedInDonorID){
                    System.out.printf("%-4s",index);
                    EventList.get(i).printInfoWithoutDonor();
                    index++;
                }
            }
        }

        System.out.println("\n<<< Pending >>>");
        System.out.printf("%-4s%-40s%-15s%-30s%s\n","No.","Name", "State", "Address", "Date");
        for(int i=0; i<EventList.size();i++){
            for(int j=0; j<EventList.get(i).getPendingDonorList().size();j++){
                if(EventList.get(i).getPendingDonorList().get(j).getID()==currLoggedInDonorID){
                    System.out.printf("%-4s",index);
                    EventList.get(i).printInfoWithoutDonor();
                    index++;
                }
            }
        }
    }

    //only reachable after a donor logged in
    public void eventRegistration(){
        clearScreen();
        int choice = -1, exitIndex = 1;

        while(choice!=exitIndex){
            try{
                System.out.println("==========================");
                System.out.println("    Event Registration");
                System.out.println("==========================");

                exitIndex = printEventList(2); //display upcoming events

                choice = getChoice();
                if (ChoiceNotInRange(1, exitIndex, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }

                //if not back to donor menu
                if(choice != exitIndex){
                    EventList.get(ArrayReturned.get(choice-1)).printInfo();
                    //confirmation
                    System.out.println("Are you sure? [Y/N]");
                    char confirm = Character.toUpperCase(input.next().charAt(0));
                    if(confirm == 'Y'){
                        EventList.get(ArrayReturned.get(choice-1)).getDonorList().add(DonorList.get(currLoggedInDonorID));
                        System.out.println("Successfully registered!");
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
    public void donorMenu() throws InputMismatchException{
        int choice = 0;

        do {
            try {
                System.out.println("==========================");
                System.out.println("      Donor Menu");
                System.out.println("==========================");
                System.out.println("1. Display Upcoming Event List");
                System.out.println("2. Display Registered Event");
                System.out.println("3. Register For Event");
                System.out.println("4. Logout");

                choice = getChoice();
                if (ChoiceNotInRange(1, 4, choice)) {
                    throw new InvalidChoice("Invalid Choice");
                }
                switch (choice) {
                    case (1):
                        printEventList(2); // display upcoming events
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

    //not yet assign function, only for donor
    public void menuBeforeLogin() throws InputMismatchException{
        int choice=0;
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
                        if(login(2)){
                            donorMenu();
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
            
        }while(choice!=3);
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
                        //admin directly go to login page
                        if(login(1)){
                            adminMenu();
                        } 
                        break;
                    case (2):
                        menuBeforeLogin(); //choose login or register
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
        sys.loadPending();

        sys.mainMenu();
        
        sys.saveAssoc();
        sys.saveDonor();
        sys.saveEvent();
        sys.savePending();
        System.exit(0);
    }
}
