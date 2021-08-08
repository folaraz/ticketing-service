package com.parking.ticket.model.db;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "parkbooking")
public class ParkBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "park_id")
    private ParkSpace parkSpace;

    private String bookingReference;

    private LocalDateTime bookingStartTime;

    private LocalDateTime bookingEndTime;

    private double bookingCost;

    private boolean paid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkBooking")
    private List<BookingActivity> bookingActivities;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;
}
