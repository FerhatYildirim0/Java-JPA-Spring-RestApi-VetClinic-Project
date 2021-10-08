package com.works.repositories;

import com.works.entities.Customer;
import com.works.projections.CustomerSearchProjection;
import com.works.projections.PetCustomerProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select cid,cname,csurname,ctel,cmail, ccitizenship from Customer order by cid DESC", nativeQuery = true)
    List<PetCustomerProjection> findByAllIgnoreCaseOrderByCidDesc();

    @Query(value = "select cid,cname,csurname,ctel,cmail  from Customer  where Customer.cid = ?1",nativeQuery = true)
    PetCustomerProjection findByCidEquals(Integer cid);

    // Search
    @Query(value = "SELECT * from customer WHERE cname LIKE %:item% or csurname like %:item%",nativeQuery = true)
    List<CustomerSearchProjection> findByCnameContains(@Param("item") String item);

    @Query("select c from Customer c where c.cid = ?1")
    Optional<CustomerSearchProjection> findByCidIs(Integer cid);








    // Pageable Search
    List<Customer> findByOrderByCidAsc(Pageable pageable);

    // Customer count
    @Query(value = "SELECT COUNT(customer.cid) FROM customer",nativeQuery = true)
    long countByCidAllIgnoreCase();

    // date ile veri alma
    @Query("select c from Customer c where c.cdate = ?1 order by c.cid DESC")
    List<Customer> findByCdateEqualsOrderByCidDesc(Date cdate);


    @Query(value = "SELECT COUNT(cdate) FROM customer WHERE cdate LIKE %:keyword%",nativeQuery = true)
    long countByCdateLike(@Param("keyword") String cdate);

}
