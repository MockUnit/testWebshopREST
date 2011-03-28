package de.webshop.proxy;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import org.jboss.resteasy.client.core.BaseClientResponse;

import de.webshop.gen.bestellungsverwaltung.Bestellstatus;
import de.webshop.gen.bestellungsverwaltung.Bestellung;
import de.webshop.gen.bestellungsverwaltung.BestellungList;

@Path("/bestellverwaltung")
@Produces({APPLICATION_XML, TEXT_XML, APPLICATION_JSON})
@Consumes
public interface BestellverwaltungProxy {
	@GET
	@Path("/bestellung/{id:[0-9]+}")
	Bestellung findBestellung(@PathParam("id") Long id);
	
	@GET
	@Path("/bestellungen/{status:[A-Z]+}")
	BestellungList findBestellungenStatus(@PathParam("status") Bestellstatus status);
	
	@POST
	@Consumes({ APPLICATION_XML, TEXT_XML })
	@Path("/bestellung")
	@Produces
	BaseClientResponse<String> createBestellung(Bestellung bestellung);
		
	@PUT
	@Consumes({ APPLICATION_XML, TEXT_XML })
	@Path("/bestellung")
	BaseClientResponse<String> updateBestellung(Bestellung bestellung);
	
	@DELETE
	@Path("/bestellung/{id:[0-9]+}")
	BaseClientResponse<String> deleteBestellung(@PathParam("id") Long id);
}
