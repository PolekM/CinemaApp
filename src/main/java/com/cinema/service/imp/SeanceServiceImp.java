package com.cinema.service.imp;

import com.cinema.repository.SeanceRepository;
import com.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeanceServiceImp implements SeanceService {

    private final SeanceRepository seanceRepository;

    @Autowired
    public SeanceServiceImp(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }
}
