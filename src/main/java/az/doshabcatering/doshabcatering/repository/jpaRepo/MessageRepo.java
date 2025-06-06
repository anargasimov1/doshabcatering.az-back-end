package az.doshabcatering.doshabcatering.repository.jpaRepo;

import az.doshabcatering.doshabcatering.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepo extends JpaRepository<Messages, UUID> {
    List<Messages> id(UUID id);
}
