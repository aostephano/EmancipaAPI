package dev.aostephano.emancipaapi.Apply.Services;

import dev.aostephano.emancipaapi.Apply.Models.Apply;
import dev.aostephano.emancipaapi.Apply.Repositories.ApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyService{

    @Autowired
    private ApplyRepository applyRepository;

    public List<Apply> list() {
        return applyRepository.findAll();
    }
}