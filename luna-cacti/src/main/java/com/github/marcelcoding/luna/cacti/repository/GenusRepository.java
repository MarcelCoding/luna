package com.github.marcelcoding.luna.cacti.repository;

import com.github.marcelcoding.luna.cacti.model.GenusModel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenusRepository extends JpaRepository<GenusModel, UUID> {

  List<GenusModel> findAllByOrderByName();
}
