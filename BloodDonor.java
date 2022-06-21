public class BloodDonor extends User{
    final Name name;
    final Blood bloodType;
    final String IC;
    int yearBirth;
    double weight;

    //Username
    //Password
    //Firstname
    //Lastname
    //IC
    //YearBirth
    //Weight
    //BloodType

    public BloodDonor(String username, String password, String firstName, String lastName, String IC,int yearBirth,double weight,String bloodType) {
        super(username,password);
        name = new Name(firstName,lastName);
        this.IC = IC;
        this.yearBirth = yearBirth;
        this.weight = weight;
        this.bloodType = Blood.valueOf(bloodType);
    }

    public String getName() {
        return name.getFirstName() + name.getLastName();
    }
    public String getBloodType(){ return bloodType.getName(); }
    public String getIC() {
        return IC;
    }
    public int getYearBirth() {
        return yearBirth;
    }
    public double getWeight() {
        return weight;
    }

    public void printInfo(){
        System.out.println("Full Name: " + name.getFullName());
        System.out.println("IC: " + IC);
        System.out.println("Year Of Birth: " + yearBirth);
        System.out.println("Weight: " + weight + " KG");
        System.out.println("Blood Type: " + bloodType.getName());
    }
}
