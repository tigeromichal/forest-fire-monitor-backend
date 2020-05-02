package com.ffm.backend.service;

import com.ffm.backend.config.transaction.RequiresNewTransactions;
import com.ffm.backend.entity.Device;
import com.ffm.backend.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiresNewTransactions
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public Device saveOrUpdate(Device device) {
        return deviceRepository.findByFcmToken(device.getFcmToken())
            .map(existingDevice -> {
                existingDevice.setLatitude(device.getLatitude());
                existingDevice.setLongitude(device.getLongitude());
                return existingDevice;
            })
            .orElseGet(() ->
                deviceRepository.save(device));
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }
}
