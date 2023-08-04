package dev.aostephano.emancipaapi.CramScrools.Services;

import dev.aostephano.emancipaapi.CramScrools.Models.CramSchool;
import dev.aostephano.emancipaapi.CramScrools.Repositories.CramSchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CramSchoolService {

    @Autowired
    private CramSchoolRepository cramSchoolRepository;

    public List<CramSchool> list() {
        return cramSchoolRepository.findAll();
    }
}