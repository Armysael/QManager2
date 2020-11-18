package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.utils.JsonMessage;
import com.sifontes.Qmanagerv2.dto.PoolDto;
import com.sifontes.Qmanagerv2.model.Pool;
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

    private final PoolRepository poolRepository;
    private final EntityConverter entityConverter;
    private final SequenceGeneratorService sequenceGenerator;

    @Autowired
    public PoolServiceImpl(PoolRepository poolRepository, EntityConverter entityConverter, SequenceGeneratorService sequenceGenerator) {
        this.poolRepository = poolRepository;
        this.entityConverter = entityConverter;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public List<PoolDto> findAllElements() {

        List<Pool> poolList = poolRepository.findAll();
        List<PoolDto> poolDtos = new ArrayList<>();

        poolList.forEach(pool -> poolDtos.add(entityConverter.poolEntitytoDto(pool)));

        return poolDtos;
    }

    @Override
    public JsonMessage addElement(PoolDto elementDto) {

        CustomMessages customMessages = new CustomMessages();
        try {
            //valido que exista el equipo
            /*for (EquipoDto equipoDto : elementDto.getListaEquipo()) {

                if (!equipoRepository.existsById(equipoDto.getId())) {
                    throw new IllegalArgumentException("Id not found");
                }
            }*/
            Pool pool = entityConverter.poolDtoToEntity(elementDto);
            pool.setId(sequenceGenerator.generateSequence(Pool.SEQUENCE_NAME));
            poolRepository.insert(pool);

            return customMessages.getActionSuccessMessage(EnumEntidades.POOL, EnumAcciones.SAVE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.POOL, EnumAcciones.SAVE, e);
        }
    }

    @Override
    public PoolDto findElementById(long id) {//TODO: ver como retornar un error en el response
        Optional<Pool> pool = poolRepository.findById(id);
        if (!pool.isPresent()) {
            throw new IllegalArgumentException("No existe pool");
        }else{
            return entityConverter.poolEntitytoDto(pool.get());
        }
    }

    @Override
    public JsonMessage editElement(PoolDto elementDto) {

        CustomMessages customMessages = new CustomMessages();

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
        CustomMessages customMessages = new CustomMessages();
        try {
            poolRepository.deleteById(id);
            return customMessages.getActionSuccessMessage(EnumEntidades.POOL, EnumAcciones.DELETE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.POOL, EnumAcciones.DELETE, e);
        }
    }
}
