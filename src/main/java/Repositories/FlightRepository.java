package Repositories;

import Entities.Flight;
import Entities.FlightStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("select f from Flight f where f.status = ONTIME")
    List<Flight> findByOnTime(FlightStatus ONTIME);


    @Query(value = "select * from flight  where f.status = ?1", nativeQuery = true)
    List<Flight> flightByStatus(String status);


    @Query("select f from Flight f order by fromAirport ASC")
    List<Flight> findAllOrderByAirportASC(PageRequest pageRequest);
}