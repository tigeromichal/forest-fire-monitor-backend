package com.ffm.backend.data.nasa.providers;

import com.ffm.backend.data.nasa.model.SuomiViirsCsvRow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SuomiViirsCurrentFireProvider extends AbstractNasaCurrentFireProvider<SuomiViirsCsvRow> {

    private static final String URL =
        "https://firms.modaps.eosdis.nasa.gov/data/active_fire/suomi-npp-viirs-c2/csv/SUOMI_VIIRS_C2_Europe_24h.csv";

    public SuomiViirsCurrentFireProvider() {
        super(SuomiViirsCsvRow.class, buildUrl(URL));
    }
}
