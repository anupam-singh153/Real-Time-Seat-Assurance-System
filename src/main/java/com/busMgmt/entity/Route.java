package com.busMgmt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "route")
public class Route {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long routeId;

     private String start;
     private String end;
     private int dist;

     @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
     @JsonIgnore
     private List<Bus> buses;

     // Getters and Setters

     public Long getRouteId() {
          return routeId;
     }

     public void setRouteId(Long routeId) {
          this.routeId = routeId;
     }

     public String getStart() {
          return start;
     }

     public void setStart(String start) {
          this.start = start;
     }

     public String getEnd() {
          return end;
     }

     public void setEnd(String end) {
          this.end = end;
     }

     public int getDist() {
          return dist;
     }

     public void setDist(int dist) {
          this.dist = dist;
     }

     public List<Bus> getBuses() {
          return buses;
     }

     public void setBuses(List<Bus> buses) {
          this.buses = buses;
     }
}
