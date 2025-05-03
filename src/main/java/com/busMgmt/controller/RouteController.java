package com.busMgmt.controller;

import com.busMgmt.dto.Response;
import com.busMgmt.service.interfac.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")

public class RouteController {

    @Autowired
    private IRouteService routeService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewRoute(@RequestParam(value = "start",required = false) String start ,
                                                 @RequestParam(value = "end",required = false) String end,
                                                 @RequestParam(value = "distance",required = false) int distance) {


        Response response = routeService.addNewRoute(start,end,distance);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllRoutes() {
        Response response = routeService.getAllRoutes();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/update/{routeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateRoute(@PathVariable Long routeId,
                                                @RequestParam(value = "start", required = false)String start,
                                                @RequestParam(value = "end",required = false)String end,
                                                @RequestParam(value = "distance" ,required = false) int dist) {
        Response response = routeService.updateRoute(routeId,start,end,dist);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get/{routeId}")
    public ResponseEntity<Response> getByRouteId(@PathVariable Long routeId) {
        Response response = routeService.getRouteById(routeId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{routeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteRoute(@PathVariable Long routeId) {
        Response response = routeService.deleteRoute(routeId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-route-no-buses")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getRoutesWithNoBuses(){
        Response response = routeService.getAllRoutesWithNoBuses();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
