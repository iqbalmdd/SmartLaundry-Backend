package com.smartlaundry.repository;

import com.smartlaundry.dto.Response.ServiceResponse;
import com.smartlaundry.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ServiceTypeRepository extends JpaRepository<ServiceType,String>, JpaSpecificationExecutor<ServiceType> {
    // Mendapatkan semua service berdasarkan accountId, dengan nama yang unik (distinct)
    @Query("SELECT DISTINCT st.serviceName, st.image FROM ServiceType st WHERE st.accountId = :accountId")
    List<ServiceResponse> findDistinctServiceByAccountId(@Param("accountId") String accountId);

    // Mendapatkan semua type terkait dengan serviceName
    @Query("SELECT st.type FROM ServiceType st WHERE st.serviceName = :serviceName")
    List<String> findAllTypeByServiceName(@Param("serviceName") String serviceName);

}
