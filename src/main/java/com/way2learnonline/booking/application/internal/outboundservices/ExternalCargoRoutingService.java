package com.way2learnonline.booking.application.internal.outboundservices;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.way2learnonline.booking.domain.model.entities.Location;
import com.way2learnonline.booking.domain.model.valueobjects.CargoItinerary;
import com.way2learnonline.booking.domain.model.valueobjects.Leg;
import com.way2learnonline.booking.domain.model.valueobjects.RouteSpecification;
import com.way2learnonline.booking.domain.model.valueobjects.Voyage;
import com.way2learnonline.shareddomain.model.TransitEdge;
import com.way2learnonline.shareddomain.model.TransitPath;

/**
 * Anti Corruption Service Class
 */
@Service
public class ExternalCargoRoutingService implements CargoRoutingService {
	
	//@Autowired
	//private RestTemplate restTemplate;
	@Autowired
	private CargoRoutingService cargoRoutingService;


    /**
     * The Booking Bounded Context makes an external call to the Routing Service of the Routing Bounded Context to
     * fetch the Optimal Itinerary for a Cargo based on the Route Specification
     * @param routeSpecification
     * @return
     */
    public CargoItinerary fetchRouteForSpecification(RouteSpecification routeSpecification){
    	
    	 //final String REST_URI
         //= "http://localhost:8082/cargorouting/optimalRoute?origin={origin}&destination={destination}&deadline={deadline}";
 
    	 TransitPath transitPath = cargoRoutingService.findOptimalRoute(routeSpecification.getOrigin().getUnLocCode(),
    		        routeSpecification.getDestination().getUnLocCode(),
    		        routeSpecification.getArrivalDeadline().toString());
    	 System.out.println("ExternalCargoRoutingService.fetchRouteForSpecification()======hitting cargorouting service=========");
    	 return toCargoItenary(transitPath);
    }
    
    
    /**
     * Anti-corruption layer conversion method from the routing service's domain model (TransitPath and TransitEdges)
     * to the domain model recognized by the Booking Bounded Context (CargoItenary and Legs)
     * @param edge
     * @return
     */
    private CargoItinerary toCargoItenary(TransitPath transitPath) {
    	
    	List<Leg> legs = new ArrayList<Leg>(transitPath.getTransitEdges().size());
        for (TransitEdge edge : transitPath.getTransitEdges()) {
            legs.add(toLeg(edge));
        }

        return new CargoItinerary(legs);
    	
    }

  
    private Leg toLeg(TransitEdge edge) {
        return new Leg(
                new Voyage(edge.getVoyageNumber()),
                new Location(edge.getFromUnLocode()),
                new Location(edge.getToUnLocode()),
                edge.getFromDate(),
                edge.getToDate());
        }


	@Override
	public TransitPath findOptimalRoute(String originUnLocode, String destinationUnLocode, String deadline) {
		// TODO Auto-generated method stub
		return null;
	}
}
