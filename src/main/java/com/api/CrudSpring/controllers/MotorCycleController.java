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
import java.util.Optional;
import java.util.UUID;

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

    //[Method: GET by id | READ]
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMotorCyclesById(@PathVariable(value = "id") UUID id) {
        Optional<MotorCycleModel> motorCycleModelOptional = motorCycleService.findById(id);
        if (!motorCycleModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(motorCycleModelOptional.get());
    }

    //[Method: DELETE | DELETE]
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMotorCyclesById(@PathVariable(value = "id") UUID id) {
        Optional<MotorCycleModel> motorCycleModelOptional = motorCycleService.findById(id);
        if (!motorCycleModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado");
        }
        motorCycleService.delete(motorCycleModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Registro deletado com sucesso");
    }

    //[Method: PUT | UPDATE]
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMotorCycle(@PathVariable(value = "id") UUID id, @RequestBody @Valid MotorCycleDtos motorCycleDtos) {
        Optional<MotorCycleModel> motorCycleModelOptional = motorCycleService.findById(id);
        if (!motorCycleModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado");
        }
        var motorCycleModel = new MotorCycleModel();
        BeanUtils.copyProperties(motorCycleDtos, motorCycleModel);
        motorCycleModel.setId(motorCycleModelOptional.get().getId());
        motorCycleModel.setDataRegistro(motorCycleModelOptional.get().getDataRegistro());
        return ResponseEntity.status(HttpStatus.OK).body(motorCycleService.save(motorCycleModel));
    }

}
