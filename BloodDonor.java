public class BloodDonor extends User implements Display {
    final Name name;
    final Blood bloodType;
    String IC;
    int age;
    double weight;

    public BloodDonor(Name name,String IC,int age,double weight,Blood bloodType) {
        this.name = name;
        this.IC = IC;
        this.age = age;
        this.weight = weight;
        this.bloodType = bloodType;
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
