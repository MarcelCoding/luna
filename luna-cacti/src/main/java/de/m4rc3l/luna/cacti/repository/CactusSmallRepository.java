package de.m4rc3l.luna.cacti.repository;

import de.m4rc3l.luna.cacti.model.CactusSmallModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CactusSmallRepository extends JpaRepository<CactusSmallModel, UUID> {

}
