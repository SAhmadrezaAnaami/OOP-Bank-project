public abstract class AccountType {

    private int interest ; 
    private boolean locked ;
    private int lockedTime ;
    private int createdDate ;
    
    

    public AccountType(int interest, boolean locked, int lockedTime , int createdDate) {
        this.interest = interest;
        this.locked = locked;
        this.lockedTime = lockedTime;
        this.createdDate = createdDate;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(int lockedTime) {
        this.lockedTime = lockedTime;
    }

    public int getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(int createdDate) {
        this.createdDate = createdDate;
    }    
}
