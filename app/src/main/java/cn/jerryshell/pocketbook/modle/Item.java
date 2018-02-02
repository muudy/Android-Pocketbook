package cn.jerryshell.pocketbook.modle;

import java.io.Serializable;
import java.util.UUID;

public class Item implements Serializable {
    private UUID mId;
    private String mTitle;
    private String mMoney;
    private String mDate;

    public Item(String title, String money, String date) {
        mId = UUID.randomUUID();
        mTitle = title;
        mMoney = money;
        mDate = date;
    }

    public Item(UUID id, String title, String money, String date) {
        mId = id;
        mTitle = title;
        mMoney = money;
        mDate = date;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    public String getMoney() {
        return mMoney;
    }

    public void setMoney(String money) {
        mMoney = money;
    }


    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }


    @Override
    public String toString() {
        return "Item{" +
                "mTitle='" + mTitle + '\'' +
                ", mMoney='" + mMoney + '\'' +
                ", mDate='" + mDate + '\'' +
                '}';
    }
}
