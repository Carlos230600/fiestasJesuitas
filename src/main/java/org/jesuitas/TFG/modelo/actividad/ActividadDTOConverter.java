package org.jesuitas.TFG.modelo.actividad;

import org.jesuitas.TFG.modelo.alumno.Alumno;
import org.jesuitas.TFG.modelo.alumno.AlumnoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActividadDTOConverter {

    @Autowired
    private final ModelMapper modelMapper;

    public ActividadDTOConverter(ModelMapper modelMapper) {
        super();
        this.modelMapper = modelMapper;
    }

    public ActividadDTO convertActividadToDto(Actividad actividad) {
        ActividadDTO actividadDTO= modelMapper.map(actividad, ActividadDTO.class);
        return actividadDTO;
    }

    public ActividadDTO transformarAActividadDTO(Actividad actividad){
        return new ActividadDTO(
                actividad.getTituloActividad(),
                actividad.getFechaInicio(),
                actividad.getFechaFin(),
                actividad.getDescripcion(),
                actividad.getImagen(),
                actividad.getIdEtapaEducativa()
        );
    }

}
