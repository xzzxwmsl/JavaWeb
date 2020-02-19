package Bean;

public class User {
    private String UserId;
    private String Sex;
    private String Name;
    private String Sdept;
    private String Grade;
    private int LoanBook;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSdept() {
        return Sdept;
    }

    public void setSdept(String sdept) {
        Sdept = sdept;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public int getLoanBook() {
        return LoanBook;
    }

    public void setLoanBook(int loanBook) {
        LoanBook = loanBook;
    }
}
