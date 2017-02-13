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
        Content c1 = new Content(UUID_GENERATOR.generate(),"Content C1");
        Content c2 = new Content(UUID_GENERATOR.generate(),"Content C2");
        Content c3 = new Content(UUID_GENERATOR.generate(),"Content C3");
        Content c4 = new Content(UUID_GENERATOR.generate(),"Content C4");
        Content c5 = new Content(UUID_GENERATOR.generate(),"Content C5");
        Content c6 = new Content(UUID_GENERATOR.generate(),"Content C6");
        Content c7 = new Content(UUID_GENERATOR.generate(),"Content C7");
        Content c8 = new Content(UUID_GENERATOR.generate(),"Content C8");

        // save a couple of rooms
        
        // Room A with content C1,C4,C7,C8
        Room roomA = new Room(UUID_GENERATOR.generate(),"Room A");
        Set roomAs = new HashSet<Content>(){{
            add(c1);
            add(c4);
            add(c7);
            add(c8);
        }};
        roomA.setContents(roomAs);

        // Room B with content C1,C3,C5,C6,C7
        Room roomB = new Room(UUID_GENERATOR.generate(),"Room B");
        Set roomBs = new HashSet<Content>(){{
            add(c1);
            add(c3);
            add(c5);
            add(c6);
            add(c7);
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
