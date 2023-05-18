package com.attech.repository;

import com.attech.model.entity.BlockArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockAreaRepository extends JpaRepository<BlockArea,Long> {

}
