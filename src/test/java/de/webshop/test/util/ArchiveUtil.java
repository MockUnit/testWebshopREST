package de.webshop.test.util;

import java.io.File;

import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.Filter;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class ArchiveUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveUtil.class);
	private static final String EAR_PROJEKT_NAME = "webshop";
	private static final String SUFFIX_EJB_PROJEKT = "EJB";  // z.B. swe2EJB
	private static final String SUFFIX_REST_PROJEKT = "REST";  // z.B. swe2REST
	private static final String SUFFIX_WEB_PROJEKT = "Web";  // z.B. swe2Web
	private static final String PFAD_BLOBS = "/resources/db/";

	// EAR-Archiv muss test.ear heissen, damit JNDI-Namen richtig aufgeloest werden
	private static final String TEST_EAR = "test.ear";

	private static final String EAR_DIR = "../" + EAR_PROJEKT_NAME + "/EarContent";
	private static final String EJB_CLASSES_DIR = "../" + EAR_PROJEKT_NAME + SUFFIX_EJB_PROJEKT + "/build/classes";
	
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private static final EnterpriseArchive EAR = createTestArchive();
	
	
	/**
	 */
	private static EnterpriseArchive createTestArchive() {
		final EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, TEST_EAR);
		
		// In das Archiv ein "exploded" Archiv importieren, d.h. ein Directory
		ear.as(ExplodedImporter.class).importDirectory(EAR_DIR);
		// META-INF\application.xml im EAR setzen, um test.war fuer Arquillian zu deklarieren
		ear.setApplicationXML("application.xml");

		// Java-Archiv fuer das EJB-Modul (evtl. mit Unterverzeichnissen .svn)
		final JavaArchive tmpJar = ShrinkWrap.create(JavaArchive.class);
		tmpJar.as(ExplodedImporter.class).importDirectory(EJB_CLASSES_DIR);

		// Unterverzeichnisse .svn durch den regulaeren Ausdruck .*/\.svn.* herausfiltern
		//    beliebige Zeichen beliebig oft .*
		//    dann /.svn, wobei . als \. maskiert werden muss
		//    dann nochmals beliebige Zeichen beliebig oft .*
		final JavaArchive ejbJar = ShrinkWrap.create(JavaArchive.class,
				                                     EAR_PROJEKT_NAME + SUFFIX_EJB_PROJEKT + ".jar");
		final Filter<ArchivePath> filter = Filters.exclude(".*/\\.svn.*");
		ejbJar.merge(tmpJar, filter);

		ear.addModule(ejbJar);  // addAsModule ab 1.0.0-alpha-12
		
		// Web-Modul fuer REST
		final WebArchive restWar = ShrinkWrap.create(WebArchive.class,
				                                     EAR_PROJEKT_NAME + SUFFIX_REST_PROJEKT + ".war");
		restWar.setWebXML("web.xml");
		ear.addModule(restWar);  // addAsModule ab 1.0.0-alpha-12
		
		// Web-Modul fuer File Upload und Download
		final WebArchive webWar = ShrinkWrap.create(WebArchive.class,
				                                    EAR_PROJEKT_NAME+ SUFFIX_WEB_PROJEKT + ".war");
		webWar.add(EmptyAsset.INSTANCE, ArchivePaths.create(PFAD_BLOBS + "__DUMMY__"));
		ear.addModule(webWar);  // addAsModule ab 1.0.0-alpha-12
		
		LOGGER.info(NEWLINE
		            + ear.toString(true)
					+ NEWLINE + NEWLINE
					+ ejbJar.toString(true)
					+ NEWLINE + NEWLINE
					+ restWar.toString(true)
					+ NEWLINE + NEWLINE
					+ webWar.toString(true)
					+ NEWLINE);
		
		final File earFile = new File("target/" + TEST_EAR);
		ear.as(ZipExporter.class).exportZip(earFile, true);  // exportTo ab 1.0.0-alpha-12
		LOGGER.info("target/" + TEST_EAR + " wurde angelegt");
		
		return ear;
	}
	
	/**
	 */
	public static EnterpriseArchive getTestArchive() {
		return EAR;
	}
	
	private ArchiveUtil() {}
}

