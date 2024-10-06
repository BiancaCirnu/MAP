package Model;

public class Cherry implements Tree{
    private final int ageInYears;
    public Cherry(int age) {
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
        return "Cherry - "+ ageInYears+" years";
    }
}
