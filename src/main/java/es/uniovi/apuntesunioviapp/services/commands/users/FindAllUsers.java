package es.uniovi.apuntesunioviapp.services.commands.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.uniovi.apuntesunioviapp.dtos.UserDto;
import es.uniovi.apuntesunioviapp.dtos.utils.Helper;
import es.uniovi.apuntesunioviapp.repositories.UserRepository;
import es.uniovi.apuntesunioviapp.services.commands.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class FindAllUsers implements Command<Page<UserDto>> {
    private final UserRepository userRepository;
    private final Pageable pageable;

    @Override
    public Page<UserDto> execute() {
        log.info("execute() - start");
        var page = userRepository.findAll(pageable);
        log.info("execute() - end");
        return Helper.convert(page, UserDto.class);
    }
}
