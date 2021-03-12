package com.github.marcelcoding.luna.cacti.repository;

import com.github.marcelcoding.luna.cacti.model.CareGroupModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareGroupRepository extends JpaRepository<CareGroupModel, String> {

  List<CareGroupModel> findAllByOrderById();
}
