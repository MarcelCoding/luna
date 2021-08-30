package de.m4rc3l.luna.cacti.repository;

import de.m4rc3l.luna.cacti.model.SpecieModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<SpecieModel, UUID> {

}
