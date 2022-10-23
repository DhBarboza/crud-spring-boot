package com.api.CrudSpring.services;

import com.api.CrudSpring.repositories.MotorCycleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorCycleService {
    final MotorCycleRepository motorCycleRepository;

    public MotorCycleService(MotorCycleRepository motorCycleRepository) {
        this.motorCycleRepository = motorCycleRepository;
    }
}
