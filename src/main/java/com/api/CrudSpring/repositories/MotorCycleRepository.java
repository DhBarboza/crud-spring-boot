package com.api.CrudSpring.repositories;

import com.api.CrudSpring.models.MotorCycleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MotorCycleRepository extends JpaRepository<MotorCycleModel, UUID> {
    //Methods: VALIDATE placa do carro já existe?
    boolean existsByPlaca(String placa);
}
