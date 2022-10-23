package com.api.CrudSpring.controllers;

import com.api.CrudSpring.dtos.MotorCycleDtos;
import com.api.CrudSpring.models.MotorCycleModel;
import com.api.CrudSpring.services.MotorCycleService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
// Permitir que seja acessado de qualquer fonte:
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/motorcycle")
public class MotorCycleController {
    final MotorCycleService motorCycleService;

    public MotorCycleController(MotorCycleService motorCycleService) {
        this.motorCycleService = motorCycleService;
    }

    //[Method: POST | CREATED]
    @PostMapping
    public ResponseEntity<Object> saveMotorCycle(@RequestBody @Valid MotorCycleDtos motorCycleDtos){
        //Verifica se possuí alguma moto com essa placa já cadastrada:
        if (motorCycleService.existsByPlaca(motorCycleDtos.getPlaca())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Essa moto já está cadastrada");
        }
        var motorCycleModel = new MotorCycleModel();
        BeanUtils.copyProperties(motorCycleDtos, motorCycleModel);
        motorCycleModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(motorCycleService.save(motorCycleModel));
    }

    //[Method: GET | READ]
    @GetMapping
    public ResponseEntity<List<MotorCycleModel>> getAllMotorCycles(){
        return ResponseEntity.status(HttpStatus.OK).body(motorCycleService.findAll());
    }


}
