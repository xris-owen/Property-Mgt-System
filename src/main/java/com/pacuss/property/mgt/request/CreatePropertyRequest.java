package com.pacuss.property.mgt.request;

import com.pacuss.property.mgt.model.Review;
import com.pacuss.property.mgt.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreatePropertyRequest {
    private String name;
    private String description;
    private String address;
    private BigDecimal pricePerNight;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private boolean isAvailable;
    private boolean drinkAllowed;
    private boolean petAllowed;
    private int maxCheckoutTimeInNights;
    private BigDecimal extraCharges;
}
