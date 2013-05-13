package com.thoughtworks.twu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name = "reserved_item")
public class ReserveItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long itemId;

    @NotNull
    private Long userId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar reservationDate;

    public Calendar getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Calendar reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

}