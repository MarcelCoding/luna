package com.github.marcelcoding.luna.cacti.reposetory;

import com.github.marcelcoding.luna.cacti.model.Specie;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, UUID> {

  List<Specie> findAllByOrderByName();
}
