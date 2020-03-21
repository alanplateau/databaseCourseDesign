package modal;

public class SimpleStudent {
    private String sNo;
    private String sName;
    private float sGrade;
    public SimpleStudent(){
        sNo="";
        sName="";
        sGrade=-1;
    }

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


    public float getsGrade() {
        return sGrade;
    }

    public void setsGrade(float sGrade) {
        this.sGrade = sGrade;
    }
}
