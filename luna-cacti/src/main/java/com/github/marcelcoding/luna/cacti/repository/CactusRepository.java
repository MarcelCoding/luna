package com.github.marcelcoding.luna.cacti.repository;

import com.github.marcelcoding.luna.cacti.model.Cactus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CactusRepository extends JpaRepository<Cactus, UUID> {

  List<Cactus> findAllByOrderByNumber();

  Optional<Cactus> findByNumber(String number);
}
