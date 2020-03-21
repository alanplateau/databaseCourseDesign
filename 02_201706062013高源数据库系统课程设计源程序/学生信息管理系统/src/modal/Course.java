package modal;

public class Course {
    private String cNo;
    private String cName;
    private float creditHours;
    private String checkMode;
    private float credit;


    public String getcNo() {
        return cNo;
    }

    public void setcNo(String cNo) {
        this.cNo = cNo;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public float getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(float creditHours) {
        this.creditHours = creditHours;
    }

    public String getCheckMode() {
        return checkMode;
    }

    public void setCheckMode(String checkMode) {
        this.checkMode = checkMode;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }
}
