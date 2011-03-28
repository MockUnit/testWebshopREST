package de.webshop.test;

import static de.webshop.util.Constants.BENUTZERVERWALTUNG_URL;
import static de.webshop.util.Constants.LAGER_URL;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;

import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.api.RunModeType;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.webshop.gen.bestellungsverwaltung.Bestellposition;
import de.webshop.gen.bestellungsverwaltung.Bestellung;
import de.webshop.test.util.AbstractTest;


@RunWith(Arquillian.class)
@Run(RunModeType.AS_CLIENT)
public class BestellungsverwaltungTest extends AbstractTest {

	private static final Long BENUTZER_ID_VORHANDEN = Long.valueOf(1);
	private static final Long LAGER_ID_VORHANDEN1 = Long.valueOf(1);
	private static final Long BESTELLUNG_ID_VORHANDEN = Long.valueOf(3);
	
	
	@Test
	public void findBestellung()
	{
		final Long bestellungId = BESTELLUNG_ID_VORHANDEN;
		final Bestellung bestellung = bvProxy.findBestellung(bestellungId);
		assertThat(bestellung.getId(), is(bestellungId.longValue()));
	}
	
	@Ignore
	@Test
	public void createBestellung(){
		
		
		final Long benutzerId = BENUTZER_ID_VORHANDEN;
		final Long idLager1 = LAGER_ID_VORHANDEN1;
		
		Bestellung bestellung = new Bestellung();
		bestellung.setBenutzer(BENUTZERVERWALTUNG_URL + "/" + benutzerId);
		
		Bestellposition bestellposition = new Bestellposition();
		
		bestellposition.setLagerArtikel(LAGER_URL + "/" + idLager1);
		bestellposition.setMenge(1);
		
		
		final BaseClientResponse<String> response = bvProxy.createBestellung(bestellung);

		// Status ueberpruefen
		final Status status = response.getResponseStatus();
		assertThat(status, is(CREATED));
		
		
	}
}
