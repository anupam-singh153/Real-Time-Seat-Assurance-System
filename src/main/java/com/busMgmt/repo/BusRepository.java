package com.busMgmt.repo;


import com.busMgmt.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    Optional<Bus> findById(Long busId);

    @Query("SELECT b FROM Bus b WHERE b.route.routeId = :routeId")
    List<Bus> findAllBusesAssignedToARoute(Long routeId);

    @Query("SELECT b FROM Bus b WHERE b.route IS NULL")
    List<Bus> findAllBusesNotAssignedYet();

    @Transactional
    @Modifying
    @Query("UPDATE Bus b SET b.route = NULL WHERE b.busId = :busId")
    void setRouteAttributeToNull(Long busId);

    @Query(value = "SELECT * FROM bus WHERE route_id IS NULL ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Bus> findRandomUnassignedBus();
}
