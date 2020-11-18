package com.sifontes.Qmanagerv2.controller;

import com.sifontes.Qmanagerv2.utils.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PoolDto;
import com.sifontes.Qmanagerv2.service.PoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pool")
public class PoolController {


    private final PoolServiceImpl poolService;

    @Autowired
    public PoolController(PoolServiceImpl poolService) {
        this.poolService = poolService;
    }

    @GetMapping("/mostrarPool")
    public List<PoolDto> getPool() {
        return poolService.findAllElements();
    }

    @GetMapping("/mostrarPool/{id}")
    public PoolDto getPool(@PathVariable long id) {
        return poolService.findElementById(id);
    }

    @CrossOrigin
    @PostMapping("/agregarPool")
    public JsonMessage addPool(@RequestBody PoolDto poolDto) {
        return poolService.addElement(poolDto);
    }

    @CrossOrigin
    @PutMapping("/mostrarPool")
    public JsonMessage modifyPool(@RequestBody PoolDto poolDto) {
        return poolService.editElement(poolDto);
    }

    @DeleteMapping("/mostrarPool/{id}")
    public JsonMessage removePool(@PathVariable long id) {
        return poolService.deleteElement(id);
    }
}
