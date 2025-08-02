package com.luqman.bank.be.repository;

import com.luqman.bank.be.entity.FundTrx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FundTrxRepository extends JpaRepository<FundTrx, Integer> {

    @Query(value = "SELECT ft FROM FundTrx ft " +
            "WHERE (COALESCE(ft.customerId) IS NULL OR ft.customerId LIKE %:customerId%) " +
            "AND (COALESCE(ft.accountNumber) IS NULL OR ft.accountNumber LIKE %:accountNumber%) " +
            "AND (COALESCE(ft.description) IS NULL OR ft.description LIKE %:description%) ")
    Page<FundTrx> getFundList(@Param("customerId") String customerId, @Param("accountNumber") String accountNumber,
                              @Param("description") String description, Pageable pageable);

}