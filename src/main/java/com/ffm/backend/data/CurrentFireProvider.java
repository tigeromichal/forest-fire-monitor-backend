package com.ffm.backend.data;

import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.output.CurrentFire;

import java.util.List;

public interface CurrentFireProvider {

    List<CurrentFire> getCurrentFires(QueryArea queryArea);
}
