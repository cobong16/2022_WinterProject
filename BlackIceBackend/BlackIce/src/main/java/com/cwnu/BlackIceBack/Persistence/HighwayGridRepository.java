package com.cwnu.BlackIceBack.Persistence;

import com.cwnu.BlackIceBack.Entity.HighwayGridEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighwayGridRepository extends JpaRepository<HighwayGridEntity, Integer> {
}
