package com.ffm.backend.data;

import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.output.FireHazardData;

public interface FireHazardDataProvider {

    FireHazardData getFireHazardData(QueryArea queryArea);
}
