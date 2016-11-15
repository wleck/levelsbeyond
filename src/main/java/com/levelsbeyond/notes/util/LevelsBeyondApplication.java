/**
 * 
 */
package com.levelsbeyond.notes.util;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.levelsbeyond.notes.NoteKeeper;


/**
 * @author bill
 *
 */
@SpringBootApplication
public class LevelsBeyondApplication {
	@Autowired
	private Bus bus;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(LevelsBeyondApplication.class, args);
	}
	
	@Bean
	public Server rsServer() {
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(bus);
		endpoint.setServiceBean(new NoteKeeper());
		endpoint.setProvider(new JacksonJsonProvider());
//		endpoint.setAddress("/");
		return endpoint.create();
	}

}
