package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.model.Equipo;
import com.sifontes.Qmanagerv2.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements CrudInterface<EquipoDto> {

    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    EntityConverter entityConverter;

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    @Override
    public List<EquipoDto> findAllElements() {

        List<EquipoDto> listaEquipo = new ArrayList<>();
        List<Equipo> equipo = equipoRepository.findAll();

        equipo.stream().forEach(equipo1 -> listaEquipo.add(entityConverter.equipoEntityToDto(equipo1)));
        return listaEquipo;
    }

    @Override
    public JsonMessage addElement(EquipoDto equipodto) {

        try {
            if (findelementByName(equipodto.getNombre())) {
                throw new IllegalArgumentException("equipo con nombre :" + equipodto.getNombre() + " ya existe");
            }

            Equipo equipo = entityConverter.equipoDtoToEntity(equipodto);
            equipo.setId(sequenceGenerator.generateSequence(equipo.SEQUENCE_NAME));
            equipoRepository.insert(equipo);

            return new JsonMessage(true, "Equipo guardado con éxito");
        } catch (Exception e) {
            return new JsonMessage("Error guardando  equipo:", e);
        }
    }

    private boolean findelementByName(String name) {

        Equipo equipo = equipoRepository.findByName(name);

        if (equipo != null) {
            return true;
        }
        return false;
    }

    @Override
    public EquipoDto findElementById(long id) {

        if (!equipoRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe partido");
        }

        Optional<Equipo> equipo = equipoRepository.findById(id);

        return entityConverter.equipoEntityToDto(equipo.get());
    }

    @Override
    public JsonMessage editElement(EquipoDto elementDto) {

        try {
            Equipo equipo = entityConverter.equipoDtoToEntity(elementDto);

            equipoRepository.save(equipo);
            return new JsonMessage(true, "Equipo editado con éxito");
        } catch (Exception e) {
            return new JsonMessage("Error editando  equipo:", e);
        }
    }

    @Override
    public JsonMessage deleteElement(long id) {
        try {
            equipoRepository.deleteById(id);
            return new JsonMessage(true, "Equipo borrado con éxito");
        } catch (Exception e) {
            return new JsonMessage("Error borrando equipo:", e);
        }
    }
}
