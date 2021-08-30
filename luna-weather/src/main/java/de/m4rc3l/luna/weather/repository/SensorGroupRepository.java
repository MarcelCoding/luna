package de.m4rc3l.luna.weather.repository;

import de.m4rc3l.luna.weather.model.SensorGroupModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorGroupRepository extends JpaRepository<SensorGroupModel, UUID> {

}
