package com.busMgmt.service.impl;

import com.busMgmt.dto.BusDTO;
import com.busMgmt.dto.Response;
import com.busMgmt.dto.UserDTO;
import com.busMgmt.entity.Bus;
import com.busMgmt.entity.Route;
import com.busMgmt.entity.User;
import com.busMgmt.exception.OurException;
import com.busMgmt.repo.BusRepository;
import com.busMgmt.repo.RouteRepository;
import com.busMgmt.repo.UserRepository;
import com.busMgmt.service.interfac.IBusService;
import com.busMgmt.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BusService implements IBusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response addNewBus(String busNo, int capacity) {
        Response response = new Response();

        try {
            Bus bus = new Bus();

            bus.setBusNo(busNo);
            bus.setCapacity(capacity);

            Bus savedBus = busRepository.save(bus);

            response.setStatusCode(200);

            BusDTO busDTO = Utils.mapBusEntityToBusDTO(savedBus);

            response.setMessage("successful");
            response.setBusDTO(busDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a Bus " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllBuses() {
        Response response = new Response();

        try {
            List<Bus> busList = busRepository.findAll(Sort.by(Sort.Direction.DESC, "busId"));
            List<BusDTO> busDTOList = Utils.mapBusListEntityToBusListDTO(busList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBusList(busDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error While Fetching Buses from database " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteBus(Long busId) {
        Response response = new Response();

        try {
            busRepository.findById(busId).orElseThrow(() -> new OurException("Bus Not Found"));
            busRepository.deleteById(busId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error while deleting a Bus " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getBusById(Long busId) {
        Response response = new Response();

        try {
            Bus bus = busRepository.findById(busId).orElseThrow(() -> new OurException("Bus Not Found"));
            BusDTO busDTO = Utils.mapBusEntityToBusDTO(bus);
            response.setStatusCode(200);

            response.setMessage("successful");
            response.setBusDTO(busDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error while Fetching a Bus " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllAvailableBuses() {
        Response response = new Response();

        try {
            List<Bus> busList = busRepository.findAllBusesNotAssignedYet();
            List<BusDTO> busDTOList = Utils.mapBusListEntityToBusListDTO(busList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBusList(busDTOList);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error While Fetching all available buses " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllBusesAssignedToARoute(Long routeId) {
        Response response = new Response();

        try {
            List<Bus> busList = busRepository.findAllBusesAssignedToARoute(routeId);
            List<BusDTO> busDTOList = Utils.mapBusListEntityToBusListDTO(busList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBusList(busDTOList);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error While Fetching all buses concerned to a route" + e.getMessage());
        }
        return response;
    }

    @Transactional
    @Override
    public Response setRouteIdAttributeToNull(Long busId) {
        Response response = new Response();

        try {
            busRepository.setRouteAttributeToNull(busId);

            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error While setting buses routeId = null" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response assignRandomBusToRoute(Long routeId) {
        Response response = new Response();

        try {
            Route route = routeRepository.findById(routeId)
                    .orElseThrow(() -> new OurException("Route not found with ID: " + routeId));

            Optional<Bus> optionalBus = busRepository.findRandomUnassignedBus();

            if (optionalBus.isEmpty()) {
                response.setStatusCode(404);
                response.setMessage("No unassigned bus is available for assignment.");
                return response;
            }

            Bus bus = optionalBus.get();
            bus.setRoute(route);
            busRepository.save(bus);

            response.setStatusCode(200);
            response.setMessage("Random bus assigned successfully to route with ID: " + routeId);
            response.setBus(bus); // Assuming Response includes a bus property
            response.setBus(bus);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error during random bus assignment: " + e.getMessage());
        }

        return response;
    }

    @Transactional
    @Override
    public Response assignBusIfNotAssigned(Long busId) {

        Response response = new Response();

        try {
            if (userRepository.alreadyBusAssigned(busId)){

                response.setMessage("Already Bus Assigned !!");
                response.setStatusCode(200);
                return response;
            }

            List<User> availableDriver = userRepository.findAvailableDriver("DRIVER");
            Bus bus = busRepository.findById(busId).get();

            if(availableDriver == null){
                response.setMessage("There is No extra Driver for Bus !!");
                response.setStatusCode(303);
                return response;
            }
            User user = availableDriver.get(0);

            user.setBus(bus);
            User savedUser = userRepository.save(user);

            response.setStatusCode(200);
            response.setBus(bus);
            response.setUser(savedUser);
        } catch (OurException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Occurred During User Bus Distribution " + e.getMessage());

        }
        return response;
    }
}