package org.tassoni.quizspring.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isNew() {
		return this.id == null;
	}	
	
	//I don't know, implement equals/hashcode or not? See this article: http://www.onjava.com/pub/a/onjava/2006/09/13/dont-let-hibernate-steal-your-identity.html
	// "The Java Collections framework needs equals() and hashCode() to be based on immutable fields for the lifetime of the Collection.
	//The article suggests creating an id in memory. But what if you're in a clustered environment.  Could you possibly have
	//one id used for two entities?  The article uses IdGenerator.getId().  I guess that's org.hibernate.mapping.IdGenerator, but it doesn't have a getId()!
/*	@Override
	public boolean equals(Object other) {
		if(other == this)
			return true;
		if(other == null)
			return false;
		if(BaseEntity.class.isAssignableFrom(other.getClass())) {
			return false;
		}
		BaseEntity brother = (BaseEntity) other;
		if(this.isNew() || brother.isNew())
			return false;
		return brother.getClass().equals(this.getClass()) && brother.getId().equals(this.id);
	}
	
	@Override
	public int hashcode() {
		
	}*/

}
