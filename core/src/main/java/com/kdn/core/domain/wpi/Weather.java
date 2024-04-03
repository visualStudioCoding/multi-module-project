package com.kdn.core.domain.wpi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="te_weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dbTimeStamp;
    private String kindCode;
    private String windDirection;
    private String windSpeed;
    private String temperature;
    private String rainyPercent;
    private String precipitationForm;

	@CreationTimestamp
    private LocalDateTime wdate;

    private String sky;
    private String pty;
    private String wind;
    private String humidity;
    private String wheightTop;
    private String wheightMean;
    private String wheightBottom;
    private String startDate;
    private String endDate;


}
