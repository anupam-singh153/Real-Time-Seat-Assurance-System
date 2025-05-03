package com.busMgmt.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class FeedbackId implements Serializable {

    private Long busId;
    private LocalDate date;

    public FeedbackId() {}

    public FeedbackId(Long busId, LocalDate date) {
        this.busId = busId;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackId that = (FeedbackId) o;
        return Objects.equals(busId, that.busId) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busId, date);
    }
}
