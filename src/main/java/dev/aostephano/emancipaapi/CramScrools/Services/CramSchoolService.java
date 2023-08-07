package dev.aostephano.emancipaapi.CramScrools.Services;

import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchool;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolMapper;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolRequest;
import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool.CramSchoolResponse;
import dev.aostephano.emancipaapi.CramScrools.Repositories.CramSchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CramSchoolService {
    private final CramSchoolRepository cramSchoolRepository;

    public CramSchoolService(CramSchoolRepository cramSchoolRepository) {
        this.cramSchoolRepository = cramSchoolRepository;
    }

    public List<CramSchool> getAllCramSchools() {
        return cramSchoolRepository.findAll();
    }

    public void saveCramSchool(CramSchool newCramSchool) {
        cramSchoolRepository.save(newCramSchool);
    }

    public static void updateCramSchoolByUuid(CramSchoolRequest data) {

    }

}