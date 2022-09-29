package com.cwnu.BlackIceBack.Persistence;

import com.cwnu.BlackIceBack.Entity.HighwayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighwayRepository extends JpaRepository<HighwayEntity, String> {
}
