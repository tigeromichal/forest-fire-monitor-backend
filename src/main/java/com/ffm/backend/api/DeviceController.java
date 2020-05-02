package com.ffm.backend.api;

import com.ffm.backend.config.api.ApiRestController;
import com.ffm.backend.entity.Device;
import com.ffm.backend.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@ApiRestController
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/devices")
    public Device saveOrUpdateDevice(@RequestBody @Valid Device device) {
        return deviceService.saveOrUpdate(device);
    }
}
