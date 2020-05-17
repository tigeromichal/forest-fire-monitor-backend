package com.ffm.backend.data.model.output;

import com.ffm.backend.data.model.input.QueryPoint;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class FireHazardData {

    private final Map<QueryPoint, Float> hazard;
}
