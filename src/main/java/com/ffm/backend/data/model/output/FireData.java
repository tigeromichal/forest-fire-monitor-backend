package com.ffm.backend.data.model.output;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class FireData {

    private final List<CurrentFire> currentFires;
    private final FireHazardData hazard;
}
