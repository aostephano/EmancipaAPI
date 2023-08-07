package dev.aostephano.emancipaapi.Apply.Repositories;

import dev.aostephano.emancipaapi.Apply.Models.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
}
