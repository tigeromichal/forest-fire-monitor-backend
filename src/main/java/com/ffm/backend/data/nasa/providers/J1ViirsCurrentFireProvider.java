package com.ffm.backend.data.nasa.providers;

import com.ffm.backend.data.nasa.model.J1ViirsCsvRow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class J1ViirsCurrentFireProvider extends AbstractNasaCurrentFireProvider<J1ViirsCsvRow> {

    private static final String URL =
        "https://firms.modaps.eosdis.nasa.gov/data/active_fire/noaa-20-viirs-c2/csv/J1_VIIRS_C2_Europe_24h.csv";

    public J1ViirsCurrentFireProvider() {
        super(J1ViirsCsvRow.class, buildUrl(URL));
    }
}
