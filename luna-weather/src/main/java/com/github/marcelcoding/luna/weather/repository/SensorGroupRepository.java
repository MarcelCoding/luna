package com.github.marcelcoding.luna.weather.repository;

import com.github.marcelcoding.luna.weather.model.SensorGroupModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorGroupRepository extends JpaRepository<SensorGroupModel, UUID> {

}
