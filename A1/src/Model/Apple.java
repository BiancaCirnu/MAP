package Model;

public class Apple implements Tree{
    private final int ageInYears;
    public Apple(int age) {
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
        return "Apple - "+ ageInYears+" years";
    }
}
