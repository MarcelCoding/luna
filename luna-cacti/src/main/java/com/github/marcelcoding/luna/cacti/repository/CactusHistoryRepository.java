package com.github.marcelcoding.luna.cacti.repository;

import com.github.marcelcoding.luna.cacti.model.CactusHistoryEntryModel;
import com.github.marcelcoding.luna.cacti.model.CactusHistoryEntryModel.IdModel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CactusHistoryRepository extends JpaRepository<CactusHistoryEntryModel, IdModel> {

  @Query("select he from CactusHistoryEntryModel he where he.id.cactus.id = ?1 order by he.id.date")
  List<CactusHistoryEntryModel> findAllOrderByDate(UUID cactusId);
}
