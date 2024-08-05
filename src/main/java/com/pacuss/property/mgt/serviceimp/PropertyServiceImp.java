package com.pacuss.property.mgt.serviceimp;

import com.pacuss.property.mgt.model.Property;
import com.pacuss.property.mgt.model.User;
import com.pacuss.property.mgt.repository.PropertyRepository;
import com.pacuss.property.mgt.request.CreatePropertyRequest;
import com.pacuss.property.mgt.service.PropertyService;
import com.pacuss.property.mgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PropertyServiceImp implements PropertyService {

    @Autowired private PropertyRepository propertyRepository;
    @Autowired private UserService userService;


    @Override
    public Property createProperty(CreatePropertyRequest req, User user) {

        Property property = new Property();
        property.setName(req.getName());
        property.setDescription(req.getDescription());
        property.setAddress(req.getAddress());
        property.setDrinkAllowed(req.isDrinkAllowed());
        property.setAvailable(req.isAvailable());
        property.setExtraCharges(req.getExtraCharges());
        property.setMaxCheckoutTimeInNights(req.getMaxCheckoutTimeInNights());
        property.setNumberOfBathrooms(req.getNumberOfBathrooms());
        property.setNumberOfBedrooms(req.getNumberOfBedrooms());
        property.setPetAllowed(req.isPetAllowed());
        property.setOwner(user);

        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Long propertyId, CreatePropertyRequest updatedProperty) throws Exception {
        Property property = findPropertyById(propertyId);

        if(!property.getAddress().equals(updatedProperty.getAddress())){
            property.setAddress(updatedProperty.getAddress());
        }
        if(property.getNumberOfBathrooms() != updatedProperty.getNumberOfBathrooms()){
            property.setNumberOfBathrooms(updatedProperty.getNumberOfBathrooms());
        }
        if(!property.getExtraCharges().equals(updatedProperty.getExtraCharges())){
            property.setExtraCharges(updatedProperty.getExtraCharges());
        }
        if(property.getDescription().equals(updatedProperty.getDescription())){
            property.setDescription(updatedProperty.getDescription());
        }
        // Other fields can be checked as well

        return propertyRepository.save(property);
    }


    @Override
    public void deleteProperty(Long propertyId) throws Exception {
        Property property = findPropertyById(propertyId);
        propertyRepository.delete(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property findPropertyById(Long propertyId) throws Exception {
        Optional<Property> response = propertyRepository.findById(propertyId);

        if (response.isEmpty()){
            throw new Exception("No property found for property with id " + propertyId);
        }
        return response.get();
    }

    @Override
    public List<Property> getPropertyByUserId(Long userId) throws Exception {
        List<Property> propertyList = propertyRepository.findByOwnerId(userId);
        if (propertyList == null){
            throw new Exception("No property found for user with id " + userId);
        }
        return propertyList;
    }
}
