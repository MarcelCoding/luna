package com.github.marcelcoding.luna.cacti.reposetory;

import com.github.marcelcoding.luna.cacti.model.CareGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareGroupRepository extends JpaRepository<CareGroup, String> {

  List<CareGroup> findAllByOrderById();
}
