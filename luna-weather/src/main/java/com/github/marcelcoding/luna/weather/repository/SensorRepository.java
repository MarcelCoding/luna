package com.github.marcelcoding.luna.weather.repository;

import com.github.marcelcoding.luna.weather.model.SensorModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorModel, UUID> {

}
