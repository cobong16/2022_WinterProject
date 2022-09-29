package com.cwnu.BlackIceBack.Persistence;

import com.cwnu.BlackIceBack.Entity.AreaGridEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaGridRepository extends JpaRepository<AreaGridEntity, Integer> {
}
