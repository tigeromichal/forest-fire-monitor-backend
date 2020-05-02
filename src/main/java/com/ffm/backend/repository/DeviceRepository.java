package com.ffm.backend.repository;

import com.ffm.backend.config.transaction.MandatoryTransactions;
import com.ffm.backend.entity.Device;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@MandatoryTransactions
public interface DeviceRepository extends CustomJpaRepository<Device, Long> {

    Optional<Device> findByFcmToken(String fcmToken);
}
