package hello;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Room {

	/*
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	*/
	
	@Id
    private UUID id;
	

	private String name;

	@ManyToMany(cascade=CascadeType.ALL)
	private Set<Content> contents;

	
	
	public Room(){

    }

    public Room(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    public Set<Content> getContents() {
        return contents;
    }

    public void setContents(Set<Content> contents) {
        this.contents = contents;
    }
    
//    
//    @Override
//    public String toString() {
//        String result = String.format(
//                "Room[id=%d, name='%s']%n",
//                id, name);
//        if (contents != null) {
//            for(Content content : contents) {
//                result += String.format(
//                        "Content[id=%d, name='%s']%n",
//                        content.getId(), content.getName());
//            }
//        }
//
//        return result;
//    }
    
    

}
