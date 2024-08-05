package com.pacuss.property.mgt.service;

import com.pacuss.property.mgt.model.Property;
import com.pacuss.property.mgt.model.User;
import com.pacuss.property.mgt.request.CreatePropertyRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PropertyService {
    public Property createProperty(CreatePropertyRequest req, User user);
    public Property updateProperty(Long propertyId, CreatePropertyRequest updatedProperty) throws Exception;
    public void deleteProperty(Long propertyId) throws Exception;
    public List<Property> getAllProperties();
    public Property findPropertyById(Long propertyId) throws Exception;
    public List<Property> getPropertyByUserId(Long userId)throws Exception;
}
