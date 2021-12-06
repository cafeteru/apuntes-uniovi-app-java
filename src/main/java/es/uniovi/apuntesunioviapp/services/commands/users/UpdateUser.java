package es.uniovi.apuntesunioviapp.services.commands.users;

import es.uniovi.apuntesunioviapp.dtos.UserDto;
import es.uniovi.apuntesunioviapp.dtos.utils.Helper;
import es.uniovi.apuntesunioviapp.model.User;
import es.uniovi.apuntesunioviapp.repositories.UserRepository;
import es.uniovi.apuntesunioviapp.services.commands.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class UpdateUser implements Command<UserDto> {
    private final UserRepository userRepository;
    private final UserDto userDto;

    @Override
    public UserDto execute() {
        log.debug("execute() - start");
        new FindUserById(userRepository, userDto.getId()).execute();
        var user = Helper.convert(userDto, User.class);
        var result = userRepository.save(user);
        log.debug("execute() - end");
        return Helper.convert(result, UserDto.class);
    }
}
