import java.time.LocalDate;
import java.util.ArrayList;

public class BloodDonationEvent {
    private int eventID;
    private State eventState;
    private String eventAddress;
    private LocalDate eventDate;
    private ArrayList <BloodDonor> donorlist = new ArrayList<>();

    public BloodDonationEvent(int eventID,State evesState,LocalDate eventDate){
        this.eventID=eventID;
        this.eventState=evesState;
        this.eventDate=eventDate;
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
