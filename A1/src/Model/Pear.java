package Model;

public class Pear implements Tree{
    private final int ageInYears;
    public Pear(int age) {
        this.ageInYears = age;
    }
    @Override
    public int getAgeInYears() {
        return ageInYears;
    }
    @Override
    public boolean isOlderThanThreeYears(){
        return ageInYears >= 3;
    }
    @Override
    public String toString() {
        return "Pear - "+ ageInYears+" years";
    }
}
