package de.m4rc3l.luna.cacti.repository;

import de.m4rc3l.luna.cacti.model.CactusHistoryEntryModel;
import de.m4rc3l.luna.cacti.model.CactusHistoryEntryModel.IdModel;
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
