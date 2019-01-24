package com.url.api.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.url.api.model.URL;
import com.url.api.service.UrlService;

/**
 * <p> This class should be able to control the flow of the external request to this api and forward it to the 
 * service class and then give a proper response to the client </p>
 * 
 * @author Fernanda
 *
 */
@RestController
@RequestMapping("/urls")
public class UrlResource {

	
	
	@Autowired
	UrlService service;
	
	@GetMapping("/{hello}")
	public String hello() {
		return "hellow world";
	}
	
	/**
	 * <p> Method which lists all the URLs present in the database </p>
	 * @return a list of objects of type {@link URL}
	 */
	@GetMapping
	public List<URL> listar(){
		return service.listar();
	}
	
	/**
	 * <p> Method which creates a new URL object URL with the data informed by the user. </p> 
	 * @param url object filled with json data 
	 * @param response http object with request data. 
	 * @return object created and persisted with the informed data by the user
	 */
	@PostMapping("/{jsonData}")
	//@RequestMapping(value="/jsonData", method=RequestMethod.POST, params="url")
	public ResponseEntity<URL> save(@RequestBody URL url, HttpServletResponse response) {
		
		
		URL  urlRetorno = service.saveUrl(url.getShortForm(),url.longForm,url.getCreatedAt());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(urlRetorno.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(urlRetorno);
		
	}
	
	
	
	/**
	 * <p> Method which creates a URL object with the short url crafted through the combination algorithm on the {@link UrlService} </p>
	 * @param longUrl long url informed through the body of request.
	 * @param response http object with request data. 
	 * @return object created and persisted with the newly created short url. 
	 */
	@PostMapping("/with/{longUrl}")
	public ResponseEntity<URL> generateNewShortURL( @RequestBody String  longUrl ,HttpServletResponse response) {
		URL  urlRetorno = service.generateNewURL(longUrl);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(urlRetorno.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(urlRetorno);
		 
	}
}


