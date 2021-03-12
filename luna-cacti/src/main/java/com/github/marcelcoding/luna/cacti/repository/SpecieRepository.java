package com.github.marcelcoding.luna.cacti.repository;

import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<SpecieModel, UUID> {

  List<SpecieModel> findAllByOrderByName();
}
