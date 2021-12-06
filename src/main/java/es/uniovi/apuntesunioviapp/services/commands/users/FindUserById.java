package es.uniovi.apuntesunioviapp.services.commands.users;

import java.util.Optional;

import es.uniovi.apuntesunioviapp.dtos.UserDto;
import es.uniovi.apuntesunioviapp.dtos.utils.Helper;
import es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages;
import es.uniovi.apuntesunioviapp.model.User;
import es.uniovi.apuntesunioviapp.repositories.UserRepository;
import es.uniovi.apuntesunioviapp.services.commands.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class FindUserById implements Command<UserDto> {
    private final UserRepository userRepository;
    private final Long id;

    @Override
    public UserDto execute() {
        log.info("execute() - start");
        Optional<User> usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            log.error(UserMessages.NOT_EXISTS);
            throw new IllegalArgumentException(UserMessages.NOT_EXISTS);
        }
        log.info("execute() - end");
        return Helper.convert(usuario.get(), UserDto.class);
    }
}
