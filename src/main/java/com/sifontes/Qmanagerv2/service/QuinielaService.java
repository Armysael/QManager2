package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.configuration.EntityConverter;
import com.sifontes.Qmanagerv2.utils.JsonMessage;
import com.sifontes.Qmanagerv2.dto.QuinielaDTO;
import com.sifontes.Qmanagerv2.model.Quiniela;
import com.sifontes.Qmanagerv2.repository.QuinielaRepository;
import com.sifontes.Qmanagerv2.utils.CustomMessages;
import com.sifontes.Qmanagerv2.utils.EnumAcciones;
import com.sifontes.Qmanagerv2.utils.EnumEntidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuinielaService implements CrudInterface<QuinielaDTO>{

    private final QuinielaRepository quinielaRepository;
    private final EntityConverter entityConverter;
    private final SequenceGeneratorService sequenceGenerator;

    @Autowired
    public QuinielaService(QuinielaRepository quinielaRepository, EntityConverter entityConverter, SequenceGeneratorService sequenceGenerator) {
        this.quinielaRepository = quinielaRepository;
        this.entityConverter = entityConverter;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public List<QuinielaDTO> findAllElements() {

        List<Quiniela> listaQuiniela = quinielaRepository.findAll();
        List<QuinielaDTO> listaQuinielaDto = new ArrayList<>();
        listaQuiniela.forEach( quiniela -> listaQuinielaDto.add(entityConverter.quinielaEntityToDto(quiniela)));

        return listaQuinielaDto;
    }

    @Override
    public JsonMessage addElement(QuinielaDTO elementDto) {

        CustomMessages customMessages = new CustomMessages();

        try{

      /*      for (InfoQuinielaPartidoDto infoPartido : elementDto.getInfoQuinielaPartidoDtos()) {

                if (!partidoRepository.existsById(infoPartido.getPartidoId())) {
                    throw new IllegalArgumentException("Id not found");
                }
            }*/

              Quiniela quiniela = entityConverter.quinielaDtoToEntity(elementDto);
              quiniela.setId(sequenceGenerator.generateSequence(Quiniela.SEQUENCE_NAME));
              quinielaRepository.insert(quiniela);

            return customMessages.getActionSuccessMessage(EnumEntidades.QUINIELA, EnumAcciones.SAVE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.QUINIELA, EnumAcciones.SAVE, e);
    }

    }

    @Override
    public QuinielaDTO findElementById(long id) {

        Optional<Quiniela> quiniela = quinielaRepository.findById(id);
        if(!quiniela.isPresent()) {
            throw new IllegalArgumentException("No existe Quiniela");
        }
        return entityConverter.quinielaEntityToDto(quiniela.get());
    }

    public List<QuinielaDTO> findByEvent(long id){

        List<Quiniela> quinielaList = quinielaRepository.findByEvent(id);
        List<QuinielaDTO> listaQuinielaDto = new ArrayList<>();

        quinielaList.forEach( quiniela -> listaQuinielaDto.add(entityConverter.quinielaEntityToDto(quiniela)));

        return listaQuinielaDto;
    }

    @Override
    public JsonMessage editElement(QuinielaDTO elementDto) {
        return null;
    }//TODO: probablemente no implementar interfaz para esta entidad

    @Override
    public JsonMessage deleteElement(long id) {
        CustomMessages customMessages = new CustomMessages();
        try {
            quinielaRepository.deleteById(id);
            return customMessages.getActionSuccessMessage(EnumEntidades.QUINIELA, EnumAcciones.DELETE);
        } catch (Exception e) {
            return customMessages.getActionErrorMessage(EnumEntidades.QUINIELA, EnumAcciones.DELETE, e);
        }
    }
}
