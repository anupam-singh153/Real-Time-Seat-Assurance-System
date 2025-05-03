package com.busMgmt.service.interfac;
import com.busMgmt.dto.Response;

public interface IRouteService {

    Response addNewRoute(String start,String end,int distance);

    Response getAllRoutes();

    Response deleteRoute(Long routeId);

    Response updateRoute(Long routeId,String start,String end,int distance);

    Response getRouteById(Long busId);

    Response getAllRoutesWithNoBuses();
}
