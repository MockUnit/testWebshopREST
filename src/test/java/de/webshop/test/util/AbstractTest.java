package de.webshop.test.util;

import static de.webshop.util.Constants.BASE_URL;
import static org.junit.Assert.fail;

import org.apache.commons.httpclient.HttpClient;
import org.jboss.arquillian.api.Deployment;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.BeforeClass;

import de.webshop.proxy.ArtikelverwaltungProxy;
import de.webshop.proxy.BestellverwaltungProxy;
import de.webshop.proxy.BenutzerverwaltungProxy;

import de.webshop.util.RegisterResteasy;

public abstract class AbstractTest {
	//protected static BestellverwaltungProxy bvProxy;
	protected static BenutzerverwaltungProxy bnvProxy;
	protected static ArtikelverwaltungProxy avProxy;
	

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
		//bvProxy = ProxyFactory.create(BestellverwaltungProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
	
		client = new HttpClient();
		bnvProxy = ProxyFactory.create(BenutzerverwaltungProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
		
		client = new HttpClient();
		avProxy = ProxyFactory.create(ArtikelverwaltungProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
		
		
		try {
			DbReload.reload();
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Fehler in DbReload");
		}
	}
}