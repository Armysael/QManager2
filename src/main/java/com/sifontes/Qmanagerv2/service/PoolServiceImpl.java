package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PoolDto;
import com.sifontes.Qmanagerv2.model.Pool;
import com.sifontes.Qmanagerv2.repository.EquipoRepository;
import com.sifontes.Qmanagerv2.repository.PoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PoolServiceImpl implements CrudInterface<PoolDto> {

    @Autowired
    PoolRepository poolRepository;

    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    EntityConverter entityConverter;

    @Override
    public List<PoolDto> findAllElements() {

        List<Pool> poolList = poolRepository.findAll();
        List<PoolDto> poolDtos =new ArrayList<>();

        poolList.stream().forEach(pool -> poolDtos.add( entityConverter.poolEntitytoDto(pool)));

        return poolDtos;
    }

    @Override
    public JsonMessage addElement(PoolDto elementDto) {

        List<EquipoDto> listaEquiposDto = new ArrayList<>();

        //TODO:Validar que no juegue el mismo equipo 2 veces


        //valido que exista el equipo
        for (EquipoDto equipoDto : elementDto.getListaEquipo()) {

            if(!equipoRepository.existsById(equipoDto.getId())){
                throw new IllegalArgumentException("Id not found");
            }
            //listaEquiposDto.add(equipoDto);
        }

        try {
            Pool pool = entityConverter.poolDtoToEntity(elementDto);

            poolRepository.insert(pool);

            return new JsonMessage(true,"Pool guardado con éxito");
        }catch (Exception e){

            return new JsonMessage("Error guardando Pool:",e);
        }

    }

    @Override
    public PoolDto findElementById(String id) {

        if(!poolRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe pool");
        }

        Optional<Pool> pool = poolRepository.findById(id);

        return entityConverter.poolEntitytoDto(pool.get());

    }

    @Override
    public JsonMessage editElement(PoolDto elementDto) {

        try {
            Pool pool = entityConverter.poolDtoToEntity(elementDto);
            poolRepository.save(pool);
            return new JsonMessage(true,"Equipo editado con éxito");
        }catch (Exception e){
            return new JsonMessage("Error editando  equipo:",e);
        }

    }

    @Override
    public JsonMessage deleteElement(String id) {
        try {
            poolRepository.deleteById(id);
        return new JsonMessage(true,"Equipo editado con éxito");
    }catch (Exception e){
        return new JsonMessage("Error editando  equipo:",e);
    }

    }
}
