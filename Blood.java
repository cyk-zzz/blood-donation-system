public enum Blood{
    A1("A+"),
    A2("A-"),
    B1("B+"),
    B2("B-"),
    AB1("AB+"),
    AB2("AB-"),
    O1("O+"),
    O2("O-");

    private final String name;

    Blood(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}