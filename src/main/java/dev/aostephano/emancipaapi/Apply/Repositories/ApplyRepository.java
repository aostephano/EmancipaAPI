package dev.aostephano.emancipaapi.Apply.Repositories;

import dev.aostephano.emancipaapi.Apply.Models.Apply.Apply;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

  Apply findByUuid(UUID uuid);
}
