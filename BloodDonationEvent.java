import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;



public class BloodDonationEvent implements Display{
    private String eventID;
    private String eventName;
    private State eventState;
    private String eventAddress;
    private LocalDate eventDate;
    private ArrayList <BloodDonor> donorList;
    private ArrayList <BloodDonor> pendingDonorList;//showing aggregation

    Scanner input = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public BloodDonationEvent(String eventID,String eventName, String eState,String eventAddress, String eDate){
        this.eventID=eventID;
        this.eventName = eventName;
        eventState=State.valueOf(eState);
        this.eventAddress = eventAddress;
        eventDate = LocalDate.parse(eDate,formatter);
        donorList = new ArrayList<>();
        pendingDonorList = new ArrayList<>();
    }
    public String getEventID() {
        return eventID;
    }
    public String getEventName(){
        return eventName;
    }
    public String getEventState(){
        return eventState.name();
    }
    public String getEventAddress(){
        return eventAddress;
    }
    public LocalDate getEventDate() {
        return eventDate;
    }

    //functions for edit event
    public void editEventName(){
        System.out.print("Enter new event name: ");
        eventName = input.nextLine();
    }
    public void editState(){
        System.out.print("Enter new state: ");
        eventState = State.valueOf(input.next()); 
    }
    public void editAddress(){
        System.out.print("Enter new address: ");
        eventAddress = input.nextLine();
    }
    public void editDate(){
        System.out.print("Enter new date(dd/mm/yyyy): ");
        eventDate = LocalDate.parse(input.next(),formatter);
    }


    //print event info with list of donors in multiple rows
    public void printInfo(){
        System.out.println("Event ID: "+eventID);
        System.out.println("Event Name: "+eventName);
        System.out.println("State: "+eventState.name());
        System.out.println("Address: "+eventAddress);
        System.out.println("Date: "+eventDate.toString());
    }

    //print info in a row
    public void printInfoWithoutDonor(){
        System.out.printf("%-8s%-40s%-15s%-30s%s\n",eventID,eventName,eventState, eventAddress, eventDate);
    }

    public void printApprovedDonor(){
        System.out.println("<<< Approved >>>");
        for (int i=0;i<donorList.size();i++){
            System.out.printf("%d. %s %d\n",i+1,donorList.get(i).getName(),donorList.get(i).getID());
        }  
        System.out.println();  
    }

    public void printPendingDonor(){
        System.out.println("<<< Pending >>>");
        for (int i=0;i<pendingDonorList.size();i++){
            System.out.printf("%d. %s %d\n",i+1,pendingDonorList.get(i).getName(),pendingDonorList.get(i).getID());
        }
        System.out.println();
    }

    public void addDonor(BloodDonor bd){
        donorList.add(bd);
    }

    public void addPendingDonor(BloodDonor bd){
        pendingDonorList.add(bd);
    }

    public ArrayList <BloodDonor> getDonorList(){
        return donorList;
    }

    public ArrayList <BloodDonor> getPendingDonorList(){
        return pendingDonorList;
    }
    
}
