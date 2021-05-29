package org.jesuitas.TFG.modelo.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {

    //Opcional: usar e Importar ModelMapper
    @Autowired
    private ModelMapper modelMapper;

    public UserDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertUserEntityToGetUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO convertirUsuarioaDTO(User user){
        return new UserDTO(user.getUsername(), user.getRoles());
    }
}
