package de.m4rc3l.luna.weather.repository;

import de.m4rc3l.luna.weather.model.SensorModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorModel, UUID> {

}
