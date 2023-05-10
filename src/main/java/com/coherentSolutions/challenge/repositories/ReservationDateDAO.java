package com.coherentSolutions.challenge.repositories;

import com.coherentSolutions.challenge.models.ReservationDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDateDAO extends JpaRepository<ReservationDate, Integer> {
    @Query(value = "select rd.id, rd.reservation_date from reservation_dates rd join reservations s where rd.fk_reservation_id=s.id and s.id = :id", nativeQuery = true)
    public List<ReservationDate> findAllDatesByReservationId(@Param("id") Integer id);

    @Modifying
    @Query(value = "delete from reservation_dates rd where rd.fk_reservation_id = :id", nativeQuery = true)
    public void deleteAllByReservationId(@Param("id") Integer id);
}
