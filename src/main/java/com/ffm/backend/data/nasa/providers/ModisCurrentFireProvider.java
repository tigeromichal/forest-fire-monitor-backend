package com.ffm.backend.data.nasa.providers;

import com.ffm.backend.data.nasa.model.ModisCsvRow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModisCurrentFireProvider extends AbstractNasaCurrentFireProvider<ModisCsvRow> {

    private static final String URL =
        "https://firms.modaps.eosdis.nasa.gov/data/active_fire/c6/csv/MODIS_C6_Europe_24h.csv";

    public ModisCurrentFireProvider() {
        super(ModisCsvRow.class, buildUrl(URL));
    }
}
