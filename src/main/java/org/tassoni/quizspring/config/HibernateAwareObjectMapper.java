package org.tassoni.quizspring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Note: This is referenced in configuration. It's advantage over the standard Mapper configured by Spring is that
 * this one won't attempt to load domain properties that are lazy loaeded and not present.
 *
 */
public class HibernateAwareObjectMapper extends ObjectMapper{
	
	private static final long serialVersionUID = -2922228569128859604L;

	public HibernateAwareObjectMapper() {
        registerModule(new Hibernate4Module());
    }

}
