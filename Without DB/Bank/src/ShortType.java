public class ShortType extends AccountType{

    private double additionalValue;

    public ShortType(int interest) {
        super(interest, false, 0);
        this.additionalValue = 0 ; 
    }

    public double getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(double additionalValue) {
        this.additionalValue = additionalValue;
    }
    
}
