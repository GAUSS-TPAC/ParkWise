package com.kairos.backend_SmartParking.entities;

import java.time.Instant;
import java.time.LocalDateTime;

public class Reservation {
    private String id;
    private String user_id;
    private String parking_id;
    private Instant come_at;
    private LocalDateTime go_at;
}