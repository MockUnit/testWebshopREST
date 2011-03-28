package de.webshop.proxy;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.resteasy.client.core.BaseClientResponse;

import de.webshop.artikelverwaltung.domain.Artikel;
import de.webshop.gen.artikelverwaltung.ArtikelList;

@Path("/artikelverwaltung")
@Produces({APPLICATION_XML, TEXT_XML, APPLICATION_JSON})
@Consumes
public interface ArtikelverwaltungProxy {

	@GET
	@Path("/artikel")
	ArtikelList findArtikelB(@QueryParam("bezeichnung") @DefaultValue("") String nachname);
	
	@GET
	@Path("/artikel/{id:[0-9]+}")
	Artikel findArtikel(@PathParam("id") Long id);
	
	@Path("/artikel")
	@POST
	@Consumes({APPLICATION_XML, TEXT_XML})
	@Produces
	public BaseClientResponse<String> createArtikel(Artikel artikel);
}
