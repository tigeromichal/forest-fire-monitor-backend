package com.ffm.backend.service;

import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.input.QueryPoint;
import com.ffm.backend.data.model.output.FireData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FireDataService {

    private final FireDataAggregateService fireDataAggregateService;

    public FireData getFireData(List<QueryPoint> queryPoints) {
        QueryArea queryArea = new QueryArea(queryPoints);
        return fireDataAggregateService.getAggregatedData(queryArea);
    }
}
