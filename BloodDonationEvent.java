import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BloodDonationEvent {
    private String eventID;
    private String eventName;
    private State eventState;
    private String eventAddress;
    private LocalDate eventDate;
    private ArrayList <BloodDonor> donorList;//showing association

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public BloodDonationEvent(String eventID,String eventName, String eState,String eventAddress, String eDate){
        this.eventID=eventID;
        this.eventName = eventName;
        eventState=State.valueOf(eState);
        this.eventAddress = eventAddress;
        eventDate = LocalDate.parse(eDate,formatter);
        donorList = new ArrayList<>();
    }
    public String getEventID() {
        return eventID;
    }
    public String getEventName(){
        return eventName;
    }
    public State getEventState(){
        return eventState;
    }
    public String getEventAddress(){
        return eventAddress;
    }
    public LocalDate getEventDate() {
        return eventDate;
    }

    public void printInfo(){
        System.out.println("Event Name: "+eventName);
        System.out.println("State: "+eventState.name());
        System.out.println("Address: "+eventAddress);
        System.out.println("Date: "+eventDate.toString());
        System.out.println("Donor List: ");
        for (int i=0;i<donorList.size();i++){
            System.out.printf("%d. %s %d\n",i+1,donorList.get(i).getName(),donorList.get(i).getID());
        }
    }

    public void addDonor(BloodDonor bd){
        donorList.add(bd);
    }

    public ArrayList <BloodDonor> getDonorList(){
        return donorList;
    }
    
    public void sortDonorIndex(){
        for (int i=0;i<donorList.size();i++){
            
        }
    }
}
