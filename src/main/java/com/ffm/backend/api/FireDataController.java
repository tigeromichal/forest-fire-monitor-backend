package com.ffm.backend.api;

import com.ffm.backend.config.api.ApiRestController;
import com.ffm.backend.data.model.input.QueryPoint;
import com.ffm.backend.data.model.output.FireData;
import com.ffm.backend.service.FireDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@ApiRestController
@RequiredArgsConstructor
public class FireDataController {

    private final FireDataService fireDataService;

    @PostMapping
    public FireData getFireData(@RequestBody @Valid List<QueryPoint> queryPoints) {
        return fireDataService.getFireData(queryPoints);
    }
}
