package org.jesuitas.TFG.modelo.alumno;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlumnoDTOConverter {

    @Autowired
    private final ModelMapper modelMapper;

    public AlumnoDTOConverter(ModelMapper modelMapper) {
        super();
        this.modelMapper = modelMapper;
    }

    public AlumnoDTO convertToDto(Alumno alumno) {
        AlumnoDTO alumnodto= modelMapper.map(alumno, AlumnoDTO.class);
        return alumnodto;
    }

    public AlumnoDTO transformarAAlumnoDTO(Alumno alumno){
        return new AlumnoDTO(alumno.getIdentificador(),
                alumno.getNombre(),
                alumno.getFechaNacimiento(),
                alumno.getTelefono(),
                alumno.getDireccion());
    }

}
