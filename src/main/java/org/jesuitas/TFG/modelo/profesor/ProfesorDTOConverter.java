package org.jesuitas.TFG.modelo.profesor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfesorDTOConverter {

    @Autowired
    private final ModelMapper modelMapper;

    public ProfesorDTOConverter(ModelMapper modelMapper) {
        super();
        this.modelMapper = modelMapper;
    }

    public ProfesorDTO convertProfesorToDTO(Profesor profesor) {
        ProfesorDTO profesorDTO= modelMapper.map(profesor, ProfesorDTO.class);
        return profesorDTO;
    }


    public ProfesorDTO transformarAProfesorDTO(Profesor profesor){
        return new ProfesorDTO(profesor.getNombre(),profesor.getTelefono());
    }

}
