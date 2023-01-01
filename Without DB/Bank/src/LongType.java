public class LongType extends AccountType{

    private double additionalValue ;

    public LongType(int interest , int lockedTime) {
        super(interest, true, lockedTime);
        this.additionalValue = 0 ;
    }

    public double getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(double additionalValue) {
        this.additionalValue = additionalValue;
    }    
}
