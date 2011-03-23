package de.webshop.test;


import static de.webshop.util.Constants.ARTIKEL_URL;
import static de.webshop.util.Constants.BASE_URL;
import static de.webshop.util.Constants.KUNDEN_URL;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.HttpClient;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.api.RunModeType;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.BeforeClass;
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

import de.webshop.proxy.BenutzerverwaltungProxy;
import de.webshop.proxy.BestellverwaltungProxy;
import de.webshop.test.util.ArchiveUtil;
import de.webshop.test.util.DbReload;
import de.webshop.util.RegisterResteasy;

@RunWith(Arquillian.class)
@Run(RunModeType.AS_CLIENT)
public class ArtikelverwaltungTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArtikelverwaltungTest.class);
	
	private static final Long BESTELLUNG_ID_VORHANDEN = Long.valueOf(11);
	private static final Long KUNDE_ID_VORHANDEN = Long.valueOf(1);
	private static final Long ARTIKEL_ID_VORHANDEN_1 = Long.valueOf(500);
	private static final Long ARTIKEL_ID_VORHANDEN_2 = Long.valueOf(501);

	private static BestellverwaltungProxy bvProxy;
	private static BenutzerverwaltungProxy kvProxy;

	/**
	 */
	@Deployment
	public static EnterpriseArchive createTestArchive() {
		return ArchiveUtil.getTestArchive();
	}
	
	/**
	 */
	@BeforeClass
	public static void setup() {
		RegisterResteasy.register();

		HttpClient client = new HttpClient();
		bvProxy = ProxyFactory.create(BestellverwaltungProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));

		client = new HttpClient();
		kvProxy = ProxyFactory.create(BenutzerverwaltungProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
		
		try {
			DbReload.reload();
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Fehler in DbReload");
		}
	}
	

}
