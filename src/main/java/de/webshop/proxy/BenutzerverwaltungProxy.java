package de.webshop.proxy;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import de.webshop.gen.benutzerverwaltung.BenutzerList;

@Path("/benutzerverwaltung")
@Produces({APPLICATION_XML, TEXT_XML, APPLICATION_JSON })
@Consumes
public interface BenutzerverwaltungProxy {

//#### GET - BenutzerList
	@GET
	@Path("/benutzer")
	BenutzerList findBenutzerN(@QueryParam("nachname") @DefaultValue("") String nachname);

}