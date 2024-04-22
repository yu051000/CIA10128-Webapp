package com.ni.rentalmyfavorite.vo;

import javax.persistence.*;
import java.sql.Timestamp;

    @Entity  //標示類別為"永續類別"
    @Table(name = "rentalmyfavorite")  //此"永續類別"對應到的表格
    public class RentalMyFavoriteVO  implements java.io.Serializable {

        // 直接宣告複合識別類別的屬性 (rNo與memNo是複合主鍵)
        @EmbeddedId   //加上@EmbeddedId 標註，必須override此類別的hashcode()、equals()
        private CompositeDetail compositeKey;

        @Column(name = "rFavTime", columnDefinition = "DATETIME")
        private Timestamp rFavTime;

        public CompositeDetail getCompositeKey() {
            return compositeKey;
        }

        public void setCompositeKey(CompositeDetail compositeKey) {
            this.compositeKey = compositeKey;
        }

        public Timestamp getrFavTime() {
            return rFavTime;
        }

        public void setrFavTime(Timestamp rFavTime) {
            this.rFavTime = rFavTime;
        }

        // 需要宣告一個有包含複合主鍵屬性的類別，並一定要實作 java.io.Serializable 介面
        @Embeddable
        public static class CompositeDetail implements java.io.Serializable {
            private static final long serialVersionUID = 1L;

            @Column(name = "rNo")
            private Integer rNo;

            @Column(name = "memNo")
            private Integer memNo;

            // 一定要有無參數建構子
            public CompositeDetail() {
                super();
            }

            public CompositeDetail(Integer rNo, Integer memNo) {
                super();
                this.rNo = rNo;
                this.memNo = memNo;
            }

            public Integer getRNo() {
                return rNo;
            }

            public void setRNo(Integer rNo) {
                this.rNo = rNo;
            }

            public Integer getMemNo() {
                return memNo;
            }

            public void setMemNo(Integer memNo) {
                this.memNo = memNo;
            }

            // 一定要 override 此類別的 hashCode() 與 equals() 方法！
            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((rNo == null) ? 0 : rNo.hashCode());
                result = prime * result + ((memNo == null) ? 0 : memNo.hashCode());
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;

                if (obj != null && getClass() == obj.getClass()) {
                    CompositeDetail compositeKey = (CompositeDetail) obj;
                    if (memNo.equals(compositeKey.memNo) && rNo.equals(compositeKey.rNo)) {
                        return true;
                    }
                }
                return false;
            }
        }
//        @Override
//        public String toString() {
//            return "RentalCategory [rNo=" + rNo + ", memNo=" + memNo + "," + " rFavTime=" + rFavTime + "]";
//        }
    }