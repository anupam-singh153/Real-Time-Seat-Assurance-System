package com.busMgmt.repo;

import com.busMgmt.entity.Bus;
import com.busMgmt.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {


    @Query("SELECT r FROM Route r WHERE r.routeId NOT IN (SELECT b.route.routeId FROM Bus b WHERE b.route IS NOT NULL)")
    List<Route> findAllRoutesNoBusesAssigned();

    Optional<Route> findById(Long routeId);
}
