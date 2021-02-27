package com.github.marcelcoding.luna.cacti.reposetory;


import com.github.marcelcoding.luna.cacti.model.Cactus;
import com.github.marcelcoding.luna.cacti.model.CactusHistory;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CactusHistoryRepository extends JpaRepository<CactusHistory, UUID> {

  List<CactusHistory> findAllByCactusOrderByTimestamp(Cactus cactus);
}
