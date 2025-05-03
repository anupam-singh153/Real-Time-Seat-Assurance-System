package com.busMgmt.service.interfac;

import com.busMgmt.dto.Response;

public interface IBusService {

    Response addNewBus(String busNo,int capacity);

    Response getAllBuses();

    Response deleteBus(Long busId);

    Response getBusById(Long busId);

    Response getAllAvailableBuses();

    Response getAllBusesAssignedToARoute(Long routeId);

    Response setRouteIdAttributeToNull(Long busId);

    Response assignRandomBusToRoute(Long routeId);

    public Response assignBusIfNotAssigned(Long busId);

}
