package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.dto.InfoQuinielaPartidoDto;
import com.sifontes.Qmanagerv2.dto.JsonMessage;
import com.sifontes.Qmanagerv2.dto.QuinielaDTO;
import com.sifontes.Qmanagerv2.model.Quiniela;
import com.sifontes.Qmanagerv2.repository.PartidoRepository;
import com.sifontes.Qmanagerv2.repository.QuinielaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuinielaService implements CrudInterface<QuinielaDTO>{

    @Autowired
    QuinielaRepository quinielaRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    EntityConverter entityConverter;

    @Autowired
    SequenceGeneratorService sequenceGenerator;



    @Override
    public List<QuinielaDTO> findAllElements() {

        List<Quiniela> listaQuiniela = quinielaRepository.findAll();
        List<QuinielaDTO> listaQuinielaDto = new ArrayList<>();

        listaQuiniela.stream().forEach( quiniela -> listaQuinielaDto.add(entityConverter.quinielaEntityToDto(quiniela)));

        return listaQuinielaDto;
    }

    @Override
    public JsonMessage addElement(QuinielaDTO elementDto) {

        try{

            for (InfoQuinielaPartidoDto infoPartido : elementDto.getInfoQuinielaPartidoDtos()) {

                if (!partidoRepository.existsById(infoPartido.getPartidoId())) {
                    throw new IllegalArgumentException("Id not found");
                }
            }
            //TODO:Validar partido duplicado

              Quiniela quiniela = entityConverter.quinielaDtoToEntity(elementDto);
              quiniela.setId(sequenceGenerator.generateSequence(quiniela.SEQUENCE_NAME));
              quinielaRepository.insert(quiniela);

        return new JsonMessage(true,"Quiniela guardada con éxito");
    }catch (Exception e){

        return new JsonMessage("Error guardando Quiniela:",e);
    }

    }

    @Override
    public QuinielaDTO findElementById(long id) {

        if(!quinielaRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe Quiniela");
        }

        Optional<Quiniela> quiniela = quinielaRepository.findById(id);

        return entityConverter.quinielaEntityToDto(quiniela.get());

    }

    public List<QuinielaDTO> findByEvent(long id){

        List<Quiniela> quinielaList = quinielaRepository.findByEvent(id);


        List<QuinielaDTO> listaQuinielaDto = new ArrayList<>();

        quinielaList.stream().forEach( quiniela -> listaQuinielaDto.add(entityConverter.quinielaEntityToDto(quiniela)));

        return listaQuinielaDto;
    }

    @Override
    public JsonMessage editElement(QuinielaDTO elementDto) {
        return null;
    }

    @Override
    public JsonMessage deleteElement(long id) {
        try {
            quinielaRepository.deleteById(id);
            return new JsonMessage(true, "Quiniela borrado con éxito");
        } catch (Exception e) {
            return new JsonMessage("Error borrando Quiniela:", e);
        }
    }
}
