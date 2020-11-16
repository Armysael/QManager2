package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PoolDto;
import com.sifontes.Qmanagerv2.model.Pool;
import com.sifontes.Qmanagerv2.repository.EquipoRepository;
import com.sifontes.Qmanagerv2.repository.PoolRepository;
import com.sifontes.Qmanagerv2.utils.CustomMessages;
import com.sifontes.Qmanagerv2.utils.EnumAcciones;
import com.sifontes.Qmanagerv2.utils.EnumEntidades;
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

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    @Autowired
    CustomMessages customMessages;

    @Override
    public List<PoolDto> findAllElements() {

        List<Pool> poolList = poolRepository.findAll();
        List<PoolDto> poolDtos = new ArrayList<>();

        poolList.stream().forEach(pool -> poolDtos.add(entityConverter.poolEntitytoDto(pool)));

        return poolDtos;
    }

    @Override
    public JsonMessage addElement(PoolDto elementDto) {

        try {
            //valido que exista el equipo
            for (EquipoDto equipoDto : elementDto.getListaEquipo()) {

                if (!equipoRepository.existsById(equipoDto.getId())) {
                    throw new IllegalArgumentException("Id not found");
                }
            }
            Pool pool = entityConverter.poolDtoToEntity(elementDto);
            pool.setId(sequenceGenerator.generateSequence(pool.SEQUENCE_NAME));
            poolRepository.insert(pool);

            return customMessages.getActionSuccessMessage(EnumEntidades.POOL, EnumAcciones.SAVE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.POOL, EnumAcciones.SAVE, e);
        }
    }

    @Override
    public PoolDto findElementById(long id) {//TODO: ver como retornar un error en el response

        if (!poolRepository.existsById(id)) {

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
            return customMessages.getActionSuccessMessage(EnumEntidades.POOL, EnumAcciones.EDIT);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.POOL, EnumAcciones.EDIT, e);
        }

    }

    @Override
    public JsonMessage deleteElement(long id) {
        try {
            poolRepository.deleteById(id);
            return customMessages.getActionSuccessMessage(EnumEntidades.POOL, EnumAcciones.DELETE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.POOL, EnumAcciones.DELETE, e);
        }
    }
}
