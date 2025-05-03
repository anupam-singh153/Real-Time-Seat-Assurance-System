package com.busMgmt.service.interfac;

import com.busMgmt.dto.Response;

import java.time.LocalDate;

public interface IFeedbackService {

    Response saveFeedback(LocalDate date,Long busId,int rating);

    Response findAllFeedbackRelatedToABus(Long busId);

    Response findFeedbacksOfARouteOnDay(LocalDate date,Long routeId);

}
