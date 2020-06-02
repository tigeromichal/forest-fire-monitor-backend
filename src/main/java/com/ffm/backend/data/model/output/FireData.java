package com.ffm.backend.data.model.output;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@EqualsAndHashCode
public class FireData {

    private final List<CurrentFire> currentFires;
    private final List<FireHazardData> hazard;

    public FireData(List<CurrentFire> currentFires, List<FireHazardData> hazard) {
        this.currentFires = currentFires != null ? currentFires : Collections.emptyList();
        this.hazard = hazard != null ? hazard : Collections.emptyList();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return currentFires.isEmpty() && hazard.isEmpty();
    }
}
