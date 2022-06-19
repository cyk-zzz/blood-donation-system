public class BloodDonationEvent {
    private int eventID;
    private State eventState;
    private String eventAddress;
    private LocalDate eventDate;
    private Vector <User> donorlist = new Vector <User>();

    public BloodDonationEvent(int eventID,State evesState,String eventDate,Data date){
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
