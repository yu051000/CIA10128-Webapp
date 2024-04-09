package com.rental.model;

import java.util.*;

public interface RentalDAO_interface {
        public void insert(RentalVO rentalVO); //前端請求

        public void update(RentalVO rentalVO); //前端請求

        public RentalVO findByPrimaryKey(Integer rNo);

        public List<RentalVO> getAll();
}