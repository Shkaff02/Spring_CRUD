package com.example.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    @Query(
            value = "SELECT price, number, date FROM supply WHERE supplierID=?",
            nativeQuery = true
    )
    public Object[] getSuppliesBySupplier(Integer id);

}
