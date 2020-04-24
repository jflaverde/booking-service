package com.way2learnonline.booking.application.internal.outboundservices;

import org.springframework.stereotype.Service;

import com.way2learnonline.shareddomain.model.TransitPath;

@Service
public class CargoRoutingServiceFallback implements CargoRoutingService {

	public TransitPath findOptimalRoute(String originUnLocode, String destinationUnLocode, String deadline) {
		 System.err.println("ExternalCargoRoutingService.fetchRouteForSpecification()======Not Available=========");
		return new TransitPath();
	}

}
