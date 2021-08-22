package com.parking.ticket.controller;


import com.parking.ticket.model.constants.VehicleType;
import com.parking.ticket.model.db.ParkSpace;
import com.parking.ticket.service.parkspace.ParkSpacesRetriever;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ApiOperation(value = "/api/v1/park-space", tags = "Parking Spaces Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/park-space")
public class ParkingSpaceController {

    private static final Logger log = LoggerFactory.getLogger(ParkingSpaceController.class);

    private final ParkSpacesRetriever parkSpacesRetriever;

    public ParkingSpaceController(ParkSpacesRetriever parkSpacesRetriever) {
        this.parkSpacesRetriever = parkSpacesRetriever;
    }

    @GetMapping("/get-spaces/{isAvailable}")
    public ResponseEntity<?> getParkingSpaces(@PathVariable boolean isAvailable) {

        List<ParkSpace> parkSpaces = parkSpacesRetriever.getParkingSpacesByAvailability(isAvailable);

        if (CollectionUtils.isNotEmpty(parkSpaces)) {
            log.info("Retrieved {} parking space", parkSpaces.size());
            return new ResponseEntity<>(parkSpaces, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);

    }

    @GetMapping("/get-spaces/{vehicleType}/{isAvailable}")
    public ResponseEntity<?> getParkingSpacesForVehicleType(@PathVariable("vehicleType") VehicleType vehicleType, @PathVariable boolean isAvailable) {

        List<ParkSpace> parkSpaces = parkSpacesRetriever.getParkingSpacesByAvailabilityAndVehicleType(isAvailable, vehicleType);

        if (CollectionUtils.isNotEmpty(parkSpaces)) {
            log.info("Retrieved {} parking space", parkSpaces.size());
            return new ResponseEntity<>(parkSpaces, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);

    }

    @GetMapping("/get-spaces")
    public ResponseEntity<?> getAllParkingSpaces() {

        List<ParkSpace> parkSpaces = parkSpacesRetriever.getAllParkingSpaces();

        if (CollectionUtils.isNotEmpty(parkSpaces)) {
            log.info("Retrieved {} parking space", parkSpaces.size());
            return new ResponseEntity<>(parkSpaces, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);

    }
}
