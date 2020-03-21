package modal;

public class Teacher {
    private String tNo;
    private String tName;
    private String tSex;
    private int tAge;
    private String tTitle;//职称
    private String tTelephone;

    public String gettNo() {
        return tNo;
    }

    public void settNo(String tNo) {
        this.tNo = tNo;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex;
    }

    public int gettAge() {
        return tAge;
    }

    public void settAge(int tAge) {
        this.tAge = tAge;
    }

    public String gettTitle() {
        return tTitle;
    }

    public void settTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    public String gettTelephone() {
        return tTelephone;
    }

    public void settTelephone(String tTelephone) {
        this.tTelephone = tTelephone;
    }
}
