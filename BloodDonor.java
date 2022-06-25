

public class BloodDonor extends User{
    int ID;
    final Name name;
    final Blood bloodType;
    final String IC;
    int yearBirth;
    double weight;


    public BloodDonor(int ID, String username, String password, String firstName, String lastName, String IC,int yearBirth,double weight,String bloodType) {
        super(username,password);
        this.ID = ID;
        name = new Name(firstName,lastName);
        this.IC = IC;
        this.yearBirth = yearBirth;
        this.weight = weight;
        this.bloodType = Blood.valueOf(bloodType);
        
    }

    public String getName() {
        return name.getFullName();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    public String getFirstName(){
        return name.getFirstName();
    }

    public String getLastName(){
        return name.getLastName();
    }


    public String getBloodType(){ return bloodType.name(); }
    public String getIC() {
        return IC;
    }
    public int getYearBirth() {
        return yearBirth;
    }
    public double getWeight() {
        return weight;
    }

    // print in one row (for table)
    public void printInfo(){
        System.out.printf("%-15s",getUsername());
        System.out.printf("%-30s",name.getFullName());
        System.out.printf("%-15s",IC);
        System.out.printf("%-6s",yearBirth);
        System.out.printf("%-12s",weight);
        System.out.printf("%s\n",bloodType.getName());
    }

    public int getID() {
        return ID;
    }
}
