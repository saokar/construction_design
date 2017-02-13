package hello;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private static final TimeBasedGenerator UUID_GENERATOR = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

	@Autowired
    private RoomRepository roomRepository;

	
	public static UUIDGenerator uuidGenerator() {
        return () -> UUID_GENERATOR.generate();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
    //@Transactional
    public void run(String... strings) throws Exception {
        // save a couple of categories
        Room roomA = new Room(UUID_GENERATOR.generate(),"Room A");
        Set roomAs = new HashSet<Content>(){{
            add(new Content(UUID_GENERATOR.generate(),"Content A1"));
            add(new Content(UUID_GENERATOR.generate(),"Content A2"));
            add(new Content(UUID_GENERATOR.generate(),"Content A3"));
        }};
        roomA.setContents(roomAs);

        Room roomB = new Room(UUID_GENERATOR.generate(),"Room B");
        Set roomBs = new HashSet<Content>(){{
            add(new Content(UUID_GENERATOR.generate(),"Content B1"));
            add(new Content(UUID_GENERATOR.generate(),"Content B2"));
            add(new Content(UUID_GENERATOR.generate(),"Content B3"));
        }};
        roomB.setContents(roomBs);
        

        roomRepository.save(new HashSet<Room>() {{
            add(roomA);
            add(roomB);
        }});

        // fetch all categories
        for (Room room : roomRepository.findAll()) {
            //logger.info(room.toString());
        }
    }
}
