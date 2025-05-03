package com.busMgmt.controller;

import com.busMgmt.dto.Response;
import com.busMgmt.service.interfac.IBusService;
import com.busMgmt.service.interfac.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private IBusService busService;
    @Autowired
    private IRouteService iRouteService;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewBus(
            @RequestParam(value = "busNo", required = false) String busNo,
            @RequestParam(value = "capacity", required = false) Integer capacity
    ) {

        if (busNo == null || capacity == null) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(busNumber, capacity)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

        Response response = busService.addNewBus(busNo,capacity);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllBuses() {
        Response response = busService.getAllBuses();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/bus-by-id/{busId}")
    public ResponseEntity<Response> getBusById(@PathVariable Long busId) {
        Response response = busService.getBusById(busId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all-available-buses")
    public ResponseEntity<Response> getAvailableBuses() {
        Response response = busService.getAllAvailableBuses();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{busId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteBus(@PathVariable Long busId) {
        Response response = busService.deleteBus(busId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/buses-assigned-to-route/{routeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllBusesAssignedToARoute(@PathVariable Long routeId){

        Response response = busService.getAllBusesAssignedToARoute(routeId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @PutMapping("/set-bus-route-id-null/{busId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> removeBusFromRoute(@PathVariable Long busId){

        Response response = busService.setRouteIdAttributeToNull(busId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/assign-random-bus/{routeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> assignARandomBusToARoute(@PathVariable Long routeId) {
        Response response = busService.assignRandomBusToRoute(routeId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/assign-random-driver/{busId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> assignARandomDriverToABus(@PathVariable Long busId) {
        Response response = busService.assignBusIfNotAssigned(busId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
