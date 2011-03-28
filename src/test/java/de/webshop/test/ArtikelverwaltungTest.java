package de.webshop.test;




import java.util.List;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;


import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.api.RunModeType;
import org.jboss.arquillian.junit.Arquillian;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
import de.swe1.gen.bestellverwaltung.Bestellposition;
import de.swe1.gen.bestellverwaltung.Bestellung;
import de.swe1.gen.bestellverwaltung.Bestellung.Bestellpositionen;
import de.swe1.gen.kundenverwaltung.AbstractKunde;
import de.swe1.proxy.BestellverwaltungProxy;
import de.swe1.proxy.KundenverwaltungProxy;

*/

import de.webshop.gen.artikelverwaltung.Artikel;
import de.webshop.gen.artikelverwaltung.ArtikelList;


import de.webshop.test.util.AbstractTest;


@RunWith(Arquillian.class)
@Run(RunModeType.AS_CLIENT)
public class ArtikelverwaltungTest extends AbstractTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(ArtikelverwaltungTest.class);
	
	private static final Long ARTIKEL_ID_VORHANDEN_1 = Long.valueOf(2);

	private static final String ARTIKEL_BEZ_VORHANDEN = "hose";

	
	@Test
	public void findArtikelById() {
		LOGGER.debug("BEGINN findArtikelById");
		
		final Artikel artikel = avProxy.findArtikel(ARTIKEL_ID_VORHANDEN_1);
		assertThat(artikel.getId(), is(ARTIKEL_ID_VORHANDEN_1));
		
		LOGGER.debug("ENDE findArtikelById");
	}
	
	@Test
	public void findArtikelByBezeichnung(){
		LOGGER.debug("BEGINN findArtikelByBezeichnung");
		
		final ArtikelList artikelList = avProxy.findArtikelB(ARTIKEL_BEZ_VORHANDEN);
		
		final List<Artikel> artikelListIntern = artikelList.getArtikel();
		for (Artikel artikel : artikelListIntern){
			assertThat(artikel.getBezeichnung(), is(ARTIKEL_BEZ_VORHANDEN));
		}
		
		LOGGER.debug("ENDE findArtikelByBezeichnung");
	}

}
