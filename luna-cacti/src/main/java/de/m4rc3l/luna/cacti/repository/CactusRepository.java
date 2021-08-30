package de.m4rc3l.luna.cacti.repository;

import de.m4rc3l.luna.cacti.model.CactusModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CactusRepository extends JpaRepository<CactusModel, UUID> {

//  Optional<CactusModel> findByNumber(String number);
}
