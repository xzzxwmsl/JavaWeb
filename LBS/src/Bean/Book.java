package Bean;

import java.util.Date;

public class Book {
    private String Id;
    private String Name;
    private String Number;
    private String Picture;
    private String Note;
    private String Author;
    private int IfBorrowed;
    private String Type;
    private String Publish;
    private String PublishDate;
    private int TotalNum;
    private int BorrowedNum;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getIfBorrowed() {
        return IfBorrowed;
    }

    public void setIfBorrowed(int ifBorrowed) {
        IfBorrowed = ifBorrowed;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPublish() {
        return Publish;
    }

    public void setPublish(String publish) {
        Publish = publish;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public int getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(int totalNum) {
        TotalNum = totalNum;
    }

    public int getBorrowedNum() {
        return BorrowedNum;
    }

    public void setBorrowedNum(int borrowedNum) {
        BorrowedNum = borrowedNum;
    }
}
