package Bean;

import java.sql.Date;

public class LoanBook {
    private String Id;
    private String UserId;
    private String Name;
    private String UserName;
    private Date LoanDate;
    private Date ReturnDate;
    private int Flag;
    private int LoanDays;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getLoanDate() {
        return LoanDate;
    }

    public void setLoanDate(Date loanDate) {
        LoanDate = loanDate;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(Date returnDate) {
        ReturnDate = returnDate;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int flag) {
        Flag = flag;
    }

    public int getLoanDays() {
        return LoanDays;
    }

    public void setLoanDays(int loanDays) {
        LoanDays = loanDays;
    }
}
