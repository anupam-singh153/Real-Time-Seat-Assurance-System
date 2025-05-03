package com.busMgmt.utils;

import com.busMgmt.dto.BusDTO;
import com.busMgmt.dto.FeedbackDTO;
import com.busMgmt.dto.RouteDTO;
import com.busMgmt.dto.UserDTO;
import com.busMgmt.entity.Bus;
import com.busMgmt.entity.Feedback;
import com.busMgmt.entity.Route;
import com.busMgmt.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    // Map User Entity to UserDTO
    public static UserDTO mapUserEntityToUserDTO(User user) {
        if (user == null) return null; // Null check for safety

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail()); // Fixed incorrect mapping
        userDTO.setRole(user.getRole()); // Fixed incorrect mapping
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        // Null-safe check for bus assignment
        userDTO.setBusId(user.getBus() != null ? user.getBus().getBusId() : null);

        return userDTO;
    }

    // Map Bus Entity to BusDTO
    public static BusDTO mapBusEntityToBusDTO(Bus bus) {
        if (bus == null) return null; // Null check for safety

        BusDTO busDTO = new BusDTO();
        busDTO.setBusId(bus.getBusId());
        busDTO.setBusNo(bus.getBusNo());
        busDTO.setCapacity(bus.getCapacity());
        // Null-safe check for route assignment
        busDTO.setRouteId(bus.getRoute() != null ? bus.getRoute().getRouteId() : null);

        return busDTO;
    }

    // Map Route Entity to RouteDTO
    public static RouteDTO mapRouteEntityToRouteDTO(Route route) {
        if (route == null) return null; // Null check for safety

        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setRouteId(route.getRouteId());
        routeDTO.setStart(route.getStart());
        routeDTO.setEnd(route.getEnd());
        routeDTO.setDistance(route.getDist());

        return routeDTO;
    }

    // Map Feedback Entity to FeedbackDTO
    public static FeedbackDTO mapFeedbackEntityToFeedbackDTO(Feedback feedback) {
        if (feedback == null) return null; // Null check for safety

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setBusId(feedback.getBusId());
        feedbackDTO.setDate(feedback.getDate());
        feedbackDTO.setRating(feedback.getRating());

        return feedbackDTO;
    }

    // Map List of User Entities to List of UserDTOs
    public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList) {
        if (userList == null || userList.isEmpty()) return List.of(); // Handle null or empty lists
        return userList.stream()
                .map(Utils::mapUserEntityToUserDTO)
                .collect(Collectors.toList());
    }

    // Map List of Bus Entities to List of BusDTOs
    public static List<BusDTO> mapBusListEntityToBusListDTO(List<Bus> busList) {
        if (busList == null || busList.isEmpty()) return List.of(); // Handle null or empty lists
        return busList.stream()
                .map(Utils::mapBusEntityToBusDTO)
                .collect(Collectors.toList());
    }

    // Map List of Route Entities to List of RouteDTOs
    public static List<RouteDTO> mapRouteListEntityToRouteListDTO(List<Route> routeList) {
        if (routeList == null || routeList.isEmpty()) return List.of(); // Handle null or empty lists
        return routeList.stream()
                .map(Utils::mapRouteEntityToRouteDTO)
                .collect(Collectors.toList());
    }
}
