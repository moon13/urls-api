package com.url.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("urlData")
public class URL {

	@Id
    public String id;

    public String shortForm;
    public String longForm;
    
    public Date createdAt;

    /**
	 * @return the shortForm
	 */
	public String getShortForm() {
		return shortForm;
	}

	/**
	 * @param shortForm the shortForm to set
	 */
	public void setShortForm(String shortForm) {
		this.shortForm = shortForm;
	}

	/**
	 * @return the longForm
	 */
	public String getLongForm() {
		return longForm;
	}

	/**
	 * @param longForm the longForm to set
	 */
	public void setLongForm(String longForm) {
		this.longForm = longForm;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public URL() {}

    public URL(String shortForm, String longForm) {
        this.shortForm = shortForm;
        this.longForm = longForm;
    }

    @Override
    public String toString() {
        return String.format(
                "URL[id=%s, shortForm='%s', longForm='%s', createdAt='%s']",
                id, shortForm, longForm, createdAt);
    }

    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

 
 
}