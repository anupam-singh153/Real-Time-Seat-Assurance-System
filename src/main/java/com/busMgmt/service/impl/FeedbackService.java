package com.busMgmt.service.impl;

import com.busMgmt.dto.Response;
import com.busMgmt.entity.Feedback;
import com.busMgmt.repo.FeedbackRepository;
import com.busMgmt.service.interfac.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Transactional
    @Override
    public Response saveFeedback(LocalDate date, Long busId, int rating) {
        Response response = new Response();

        try {

            feedbackRepository.saveFeedback(date,busId,rating);

            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error during saving a Feedback " + e.getMessage());
        }
        return response;
    }

    @Transactional
    @Override
    public Response findAllFeedbackRelatedToABus(Long busId) {
        Response response = new Response();

        try {

            List<Feedback> feedbackList = feedbackRepository.findAllFeedbacksRelatedToABus(busId);

            System.out.println(feedbackList);
            response.setFeedbackList(feedbackList);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error during finding all Feedbacks of a bus " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response findFeedbacksOfARouteOnDay(LocalDate date, Long routeId) {
        Response response = new Response();

        try {

            List<Feedback> feedbackList = feedbackRepository.findFeedbacksOfARouteOnDay(routeId,date);

            response.setFeedbackList(feedbackList);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error during finding all Feedbacks of a route " + e.getMessage());
        }
        return response;
    }
}
