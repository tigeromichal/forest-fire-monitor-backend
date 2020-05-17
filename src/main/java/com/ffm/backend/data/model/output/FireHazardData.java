package com.ffm.backend.data.model.output;

import com.ffm.backend.data.model.input.QueryPoint;
import lombok.*;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class FireHazardData {

    private final QueryPoint point;
    private final Float hazard;
}
