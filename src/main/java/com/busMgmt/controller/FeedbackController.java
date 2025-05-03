package com.busMgmt.controller;

import com.busMgmt.dto.Response;
import com.busMgmt.service.interfac.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

        @Autowired
        private IFeedbackService feedbackService;

        @PostMapping("/add")
        @PreAuthorize("hasAuthority('DRIVER')")
        public ResponseEntity<Response> saveFeedback(
                @RequestParam(value = "date",required = false)LocalDate date,
                @RequestParam(value = "busId",required = false)Long busId,
                @RequestParam(value = "rating",required = false)int rating
                ){

            Response response = feedbackService.saveFeedback(date,busId,rating);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }


        @PreAuthorize("hasAuthority('ADMIN')")
        @GetMapping("/all-feedback-related-to-bus/{busId}")
        public ResponseEntity<Response> findAllFeedbacksRelatedToABus(@PathVariable Long busId){

            Response response = feedbackService.findAllFeedbackRelatedToABus(busId);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

        @PreAuthorize("hasAuthority('ADMIN')")
        @GetMapping("/all-feedback-on-a-day-route/{routeId}")
        public ResponseEntity<Response> findFeedbackOfARouteOnDay(
                           @PathVariable Long routeId,
                           @RequestParam(value = "date",required = false)LocalDate date){

            Response response = feedbackService.findFeedbacksOfARouteOnDay(date,routeId);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
}
