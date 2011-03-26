package de.webshop.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.api.RunModeType;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.webshop.gen.benutzerverwaltung.BenutzerList;
import de.webshop.test.util.AbstractTest;

@RunWith(Arquillian.class)
@Run(RunModeType.AS_CLIENT)
public class BenutzerverwaltungTest extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(BenutzerverwaltungTest.class);
	
	private static final String NACHNAME_EXISTS = "Sauer";
	
	@Test
	public void findBenutzerN() {
		LOGGER.debug("BEGINN findBenutzerN");
		
		BenutzerList list = bnvProxy.findBenutzerN(NACHNAME_EXISTS);
		assertThat(list.getBenutzer().size(), is(1));
		
		LOGGER.debug("ENDE findBenutzerN");
	}
}
