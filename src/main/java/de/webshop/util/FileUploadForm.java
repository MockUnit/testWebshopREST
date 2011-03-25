package de.webshop.util;

import static javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessorType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

@XmlAccessorType(FIELD)
public class FileUploadForm {
	@FormParam("content")
	@PartType(APPLICATION_OCTET_STREAM)
	private byte[] content;

	public FileUploadForm(byte[] content) {
		super();
		this.content = content;
	}
	
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
