package com.ffm.backend.data.nasa.model;

import com.ffm.backend.data.MappableToCurrentFire;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractNasaRow implements MappableToCurrentFire {

    private String latitude;
    private String longitude;
}
