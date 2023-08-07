package dev.aostephano.emancipaapi.CramScrools.Repositories;

import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchool;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CramSchoolRepository extends JpaRepository<CramSchool, Long> {

  CramSchool findByName(String name);

  CramSchool findByUuid(UUID uuid);
}