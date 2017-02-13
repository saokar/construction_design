package hello;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "room", path = "room")
public interface RoomRepository extends PagingAndSortingRepository<Room, UUID> {

	List<Room> findByName(@Param("name") String name);

	Room findById(UUID id);

}
