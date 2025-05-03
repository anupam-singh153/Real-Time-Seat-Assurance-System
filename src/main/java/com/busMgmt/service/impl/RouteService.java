package com.busMgmt.service.impl;

import com.busMgmt.dto.Response;
import com.busMgmt.dto.RouteDTO;
import com.busMgmt.entity.Route;
import com.busMgmt.exception.OurException;
import com.busMgmt.repo.RouteRepository;
import com.busMgmt.service.interfac.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import com.busMgmt.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService implements IRouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Response addNewRoute(String start, String end, int distance) {
        Response response = new Response();

        try {
            Route route = new Route();

            route.setStart(start);
            route.setEnd(end);
            route.setDist(distance);

            Route savedRoute = routeRepository.save(route);

            response.setStatusCode(200);

            RouteDTO routeDTO = Utils.mapRouteEntityToRouteDTO(savedRoute);

            response.setMessage("successful");
            response.setRoute(routeDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a Bus " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllRoutes() {
        Response response = new Response();

        try {
            List<Route> routeList = routeRepository.findAll(Sort.by(Sort.Direction.DESC, "routeId"));
            List<RouteDTO> routeDTOList = Utils.mapRouteListEntityToRouteListDTO(routeList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRouteList(routeDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error While Fetching Routes from database " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteRoute(Long routeId) {
        Response response = new Response();

        try {
            routeRepository.findById(routeId).orElseThrow(() -> new OurException("Bus Not Found"));
            routeRepository.deleteById(routeId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error while deleting a Route " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateRoute(Long routeId,String start, String end, int distance) {
        Response response = new Response();

        try {

            Route route = routeRepository.findById(routeId).orElseThrow(() -> new OurException("Room Not Found"));
            if (start != null) route.setStart(start);
            if (end != null) route.setEnd(end);
            if (distance > 0) route.setDist(distance);

            Route updatedRoute = routeRepository.save(route);
            RouteDTO routeDTO = Utils.mapRouteEntityToRouteDTO(updatedRoute);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoute(routeDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error during Updating a Route " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getRouteById(Long busId) {
        Response response = new Response();

        try {
            Route route = routeRepository.findById(busId).orElseThrow(() -> new OurException("Room Not Found"));
            RouteDTO routeDTO = Utils.mapRouteEntityToRouteDTO(route);
            response.setStatusCode(200);

            response.setMessage("successful");
            response.setRoute(routeDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error while Fetching a Route " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllRoutesWithNoBuses() {
        Response response = new Response();

        try {
            List<Route> routeList = routeRepository.findAllRoutesNoBusesAssigned();
            List<RouteDTO> routeDTOList = Utils.mapRouteListEntityToRouteListDTO(routeList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRouteList(routeDTOList);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error While Fetching all routes who have no Buses " + e.getMessage());
        }
        return response;
    }
}
