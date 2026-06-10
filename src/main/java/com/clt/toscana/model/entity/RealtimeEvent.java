package com.clt.toscana.model.entity;

import com.clt.toscana.model.enums.EventStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity for storing realtime event history.
 */
@Entity
@Table(name = "realtime_events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealtimeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String channel;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private LocalDateTime timestamp;

    private String clientId;

    @Enumerated(EnumType.STRING)
    private EventStatus status;
}
