public class Person {

    private String nom;
    private int age;
    private String CIN;

    public Person(String nom, int age, String CIN) {
        this.nom = nom;
        this.age = age;
        this.CIN = CIN;
    }


    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public String getCIN() {
        return CIN;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
}
