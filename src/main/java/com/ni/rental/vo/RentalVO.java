package com.ni.rental.vo;
import com.ni.rentalcategory.vo.RentalCategoryVO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity  //標示類別為"永續類別"
@Table(name = "Rental")  //此"永續類別"對應到的表格
public class RentalVO{

    @Id //標示為PK
    @Column(name="rNo")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rNo;

    @Column(name="rName", length=40)
    private String rName;

    @Column(name="rPrice",columnDefinition="BigDecimal")
    private BigDecimal rPrice;

    @Column(name="rSize")
    private Integer rSize;

    @Column(name="rColor", length=10)
    private String rColor;

    @Column(name="rInfo", length=1000)
    private String rInfo;

    @Column(name="rStat",columnDefinition = "TINYINT")
    private Byte rStat;

    @ManyToOne
    @JoinColumn(name = "rCatNo", referencedColumnName = "rCatNo") //對應rental的rCatNo
    private RentalCategoryVO rentalCategory;
    public RentalCategoryVO getRentalCategory() {
        return rentalCategory;
    }
    public void setRentalCategory(RentalCategoryVO rentalCategory) {
        this.rentalCategory = rentalCategory;
    }


    @OneToMany(mappedBy = "RentalOrderDetails", cascade = CascadeType.ALL)
    private Set<RentalCategoryVO> rentalOrderDetails;

    public Set<RentalCategoryVO> getRentalOrderDetails() {
        return rentalOrderDetails;
    }
    public void setRentalOrderDetails(Set<RentalCategoryVO> rentalOrderDetails) {
        this.rentalOrderDetails = rentalOrderDetails;
    }

    @OneToMany(mappedBy = "RentalMyFavorite",cascade = CascadeType.ALL)
    private Set<RentalCategoryVO> rentalMyFavorites;
    public Set<RentalCategoryVO> getRentalMyFavorites() {
        return rentalMyFavorites;
    }

    public void setRentalMyFavorites(Set<RentalCategoryVO> rentalMyFavorites) {
        this.rentalMyFavorites = rentalMyFavorites;
    }


    @OneToMany(mappedBy = "RentalPic", cascade = CascadeType.ALL)
    private Set<RentalCategoryVO> rentalPics;
    public Set<RentalCategoryVO> getRentalPics() {
        return rentalPics;
    }

    public void setRentalPics(Set<RentalCategoryVO> rentalPics) {
        this.rentalPics = rentalPics;
    }

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

//    @Override
//    public String toString() {
//        return "RentalCategory [rNo=" + rNo + "," +  "rName=" + rName + "," + "rPic=" + rPic + ","
//                +  "rSize=" + rSize + "," + "rColor=" + rColor  + ","+  "rInfo=" + rInfo + "," + "rStat=" + rStat + "]";
//    }
}