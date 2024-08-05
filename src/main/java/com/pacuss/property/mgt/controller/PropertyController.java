package com.pacuss.property.mgt.controller;

import com.pacuss.property.mgt.model.Property;
import com.pacuss.property.mgt.model.User;
import com.pacuss.property.mgt.request.CreatePropertyRequest;
import com.pacuss.property.mgt.service.PropertyService;
import com.pacuss.property.mgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired PropertyService propertyService;
    @Autowired UserService userService;

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody CreatePropertyRequest req,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Property property = propertyService.createProperty(req, user);

        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@RequestBody CreatePropertyRequest req,
                                                   @PathVariable Long id) throws Exception {
        Property property = propertyService.updateProperty(id, req);
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) throws Exception {
        propertyService.deleteProperty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> findPropertyById(@PathVariable Long id) throws Exception {
        Property property = propertyService.findPropertyById(id);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() throws Exception {
        List<Property> properties = propertyService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/api/users/{userId}/properties")
    public ResponseEntity<List<Property>> getPropertyByUserId(@PathVariable Long userId) throws Exception {
        List<Property> propertyList = propertyService.getPropertyByUserId(userId);

        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

}
