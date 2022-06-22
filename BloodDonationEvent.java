import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BloodDonationEvent {
    private int eventID;
    private State eventState;
    private String eventAddress;
    private LocalDate eventDate;
    private ArrayList <BloodDonor> donorlist = new ArrayList<>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public BloodDonationEvent(int eventID,String eState,String eventAddress, String eDate){
        this.eventID=eventID;
        eventState=State.valueOf(eState);
        this.eventAddress = eventAddress;
        eventDate = LocalDate.parse(eDate,formatter);
        System.out.println("Created!");
    }
    public int getEventID() {
        return eventID;
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
    
}
