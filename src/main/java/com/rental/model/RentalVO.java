package com.rental.model;
import java.math.BigDecimal;


//@Entity //必要! 對應到資料庫的永續類別
//@Table(name = "Rental")
public class RentalVO implements java.io.Serializable{
    private Integer rNo;
    private String rName;
    private BigDecimal rPrice;
    private Integer rSize;
    private String rColor;
    private String rInfo;
    private Byte rStat;
    private Integer rCatNo;

    public Integer getrNo() {
        return rNo;
    }

    public void setrNo(Integer rNo) {
        this.rNo = rNo;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public BigDecimal getrPrice() {
        return rPrice;
    }

    public void setrPrice(BigDecimal rPrice) {
        this.rPrice = rPrice;
    }

    public Integer getrSize() {
        return rSize;
    }

    public void setrSize(Integer rSize) {
        this.rSize = rSize;
    }

    public String getrColor() {
        return rColor;
    }

    public void setrColor(String rColor) {
        this.rColor = rColor;
    }

    public String getrInfo() {
        return rInfo;
    }

    public void setrInfo(String rInfo) {
        this.rInfo = rInfo;
    }

    public Byte getrStat() {
        return rStat;
    }

    public void setrStat(Byte rStat) {
        this.rStat = rStat;
    }

    public Integer getrCatNo() {
        return rCatNo;
    }

    public void setrCatNo(Integer rCatNo) {
        this.rCatNo = rCatNo;
    }
}