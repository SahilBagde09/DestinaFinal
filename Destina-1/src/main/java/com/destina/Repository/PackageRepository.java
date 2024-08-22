package com.destina.Repository;






import com.destina.Dto.PackageDto;
import com.destina.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findByLocationContaining(String location);
    List<Package> findByAgentId(Long agentId);
}
