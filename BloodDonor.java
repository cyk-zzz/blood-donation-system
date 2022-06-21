public class BloodDonor extends User{
    final Name name;
    final Blood bloodType;
    final String IC;
    int age;
    double weight;

    public BloodDonor(String username, String password, String firstName, String lastName, String IC,int age,double weight,String bloodType) {
        super(username,password);
        name = new Name(firstName,lastName);
        this.IC = IC;
        this.age = age;
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
    public int getAge() {
        return age;
    }
    public double getWeight() {
        return weight;
    }

    public void printInfo(){
        
    }
}
