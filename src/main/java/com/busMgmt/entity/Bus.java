package com.busMgmt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "bus")
@Data
public class Bus {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long busId;

     private String busNo;
     private int capacity;

     @ManyToOne
     @JoinColumn(name = "route_id", nullable = true)
     private Route route;

     @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
     @JsonIgnore
     private List<Feedback> feedbacks;

     @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
     @JsonIgnore
     private List<User> users;

     // Getters and Setters


     public Long getBusId() {
          return busId;
     }

     public void setBusId(Long busId) {
          this.busId = busId;
     }

     public String getBusNo() {
          return busNo;
     }

     public void setBusNo(String busNo) {
          this.busNo = busNo;
     }

     public int getCapacity() {
          return capacity;
     }

     public void setCapacity(int capacity) {
          this.capacity = capacity;
     }

     public Route getRoute() {
          return route;
     }

     public void setRoute(Route route) {
          this.route = route;
     }

     public List<Feedback> getFeedbacks() {
          return feedbacks;
     }

     public void setFeedbacks(List<Feedback> feedbacks) {
          this.feedbacks = feedbacks;
     }

     public List<User> getUsers() {
          return users;
     }

     public void setUsers(List<User> users) {
          this.users = users;
     }
}
