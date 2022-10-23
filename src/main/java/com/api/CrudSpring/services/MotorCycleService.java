package com.api.CrudSpring.services;

import com.api.CrudSpring.models.MotorCycleModel;
import com.api.CrudSpring.repositories.MotorCycleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MotorCycleService {
    final MotorCycleRepository motorCycleRepository;

    public MotorCycleService(MotorCycleRepository motorCycleRepository) {
        this.motorCycleRepository = motorCycleRepository;
    }

    @Transactional
    public MotorCycleModel save(MotorCycleModel motorCycleModel) {
        return  motorCycleRepository.save(motorCycleModel);
    }
}
