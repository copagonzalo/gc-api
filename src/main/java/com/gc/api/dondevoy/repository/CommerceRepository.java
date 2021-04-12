package com.gc.api.dondevoy.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gc.api.dondevoy.model.Commerce;

public interface CommerceRepository extends PagingAndSortingRepository<Commerce, Long>, JpaSpecificationExecutor<Commerce> {

}
