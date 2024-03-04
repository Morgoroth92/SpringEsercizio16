package Controllers;

import Entities.Flight;
import Repositories.FlightRepository;
import Services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/flights")
public class FlightController {

    @Autowired
    FlightService flightService;
    @Autowired
    FlightRepository flightRepository;

    @GetMapping("/retrieving")
    public List<Flight> getListOfFlight() {
        return flightService.findAllFlight();
    }
    @GetMapping("/from/airport/asc")
    public List<Flight> allOrderByAirportASC(@RequestParam(required = false) Integer page, @RequestParam Integer size) {
        PageRequest pages = PageRequest.of(page, size, Sort.by("fromAirport").ascending());
        return flightRepository.findAllOrderByAirportASC(pages);
    }
    @GetMapping("/retrieving/ontime")
    public List<Flight> findFlightOnTime() {
        return flightService.searchOnTime();
    }
    @GetMapping("/single/{findStatus}")
    public List<Flight> findByStatus(@PathVariable String findStatus) {
        String status = findStatus.toUpperCase();
        return flightService.searchByStatus(status);
    }
    @DeleteMapping("/deleteAllFlights")
    public boolean deleteAll() {
        return flightService.deleteAllData();
    }
    @PostMapping("/provisioning")
    public List<Flight> fillListOffFlight(@RequestParam(required = false, defaultValue = "100") int n) {
        return flightService.populateListWithFlight(n);
    }
}
