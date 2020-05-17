package com.ffm.backend.data;

import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.output.FireHazardData;

import java.util.List;

public interface FireHazardDataProvider {

    List<FireHazardData> getFireHazardData(QueryArea queryArea);
}
