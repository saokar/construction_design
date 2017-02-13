package hello;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Content {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
	
	@Id
    private UUID id;

	private String name;


	public Content() {

    }

    public Content(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
