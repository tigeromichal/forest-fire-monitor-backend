package com.ffm.backend.data.nasa.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ffm.backend.data.model.output.CurrentFire;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@JsonPropertyOrder({
    "latitude", "longitude", "bright_ti4", "scan", "track", "acq_date", "acq_time", "satellite", "confidence",
    "version", "bright_ti5", "frp", "daynight"
})
public class J1ViirsCsvRow extends AbstractNasaRow {

    private String bright_ti4;
    private String scan;
    private String track;
    private String acq_date;
    private String acq_time;
    private String satellite;
    private String confidence;
    private String version;
    private String bright_ti5;
    private String frp;
    private String daynight;

    @Override
    public CurrentFire mapToCurrentFire() {
        CurrentFire currentFire = new CurrentFire();
        currentFire.setLatitude(new BigDecimal(getLatitude()));
        currentFire.setLongitude(new BigDecimal(getLongitude()));
        currentFire.setConfidence(confidence);
        currentFire.setDate(LocalDate.parse(acq_date, DateTimeFormatter.ISO_DATE));
        return currentFire;
    }
}
