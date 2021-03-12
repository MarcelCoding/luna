package com.github.marcelcoding.luna.cacti.repository;


import com.github.marcelcoding.luna.cacti.model.CactusModel;
import com.github.marcelcoding.luna.cacti.model.CactusHistoryModel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CactusHistoryRepository extends JpaRepository<CactusHistoryModel, UUID> {

  List<CactusHistoryModel> findAllByCactusOrderByTimestamp(CactusModel cactus);
}
