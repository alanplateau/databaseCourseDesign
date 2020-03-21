package modal;

public class Student {
    private String sNo;//学号
    private String sName;
    private String sSex;
    private int sAge;
    private String sHometown;//生源地
    private float sCredit;//已修学分

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public int getsAge() {
        return sAge;
    }

    public void setsAge(int sAge) {
        this.sAge = sAge;
    }

    public String getsHometown() {
        return sHometown;
    }

    public void setsHometown(String sHometown) {
        this.sHometown = sHometown;
    }

    public float getsCredit() {
        return sCredit;
    }

    public void setsCredit(float sCredit) {
        this.sCredit = sCredit;
    }
}
