package dev.aostephano.emancipaapi.CramScrools.Repositories;

import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CramSchoolRepository extends JpaRepository<CramSchool, Long> {
    CramSchool findByName(String name);
    CramSchool findByUuid(UUID uuid);
}