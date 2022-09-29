package com.cwnu.BlackIceBack.Persistence;

import com.cwnu.BlackIceBack.Entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, String> {
}
