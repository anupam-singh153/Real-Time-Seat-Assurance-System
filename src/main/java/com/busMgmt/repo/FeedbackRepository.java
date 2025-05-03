package com.busMgmt.repo;

import com.busMgmt.dto.Response;
import com.busMgmt.entity.Feedback;
import com.busMgmt.entity.FeedbackId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, FeedbackId> {


    @Transactional
    @Modifying
    @Query("INSERT INTO Feedback (busId, date, rating) VALUES (:busId, :date, :rating)")
    void saveFeedback(LocalDate date,Long busId,int rating);

    @Transactional
    @Query("SELECT f FROM Feedback f WHERE f.bus.busId = :busId")
    List<Feedback> findAllFeedbacksRelatedToABus(Long busId);

    @Transactional
    @Query("SELECT f FROM Feedback f JOIN f.bus b WHERE b.route.routeId = :routeId AND f.date = :date")
    List<Feedback> findFeedbacksOfARouteOnDay(@Param("routeId") Long routeId, @Param("date") LocalDate date);

}
