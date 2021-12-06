package es.uniovi.apuntesunioviapp.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.uniovi.apuntesunioviapp.dtos.UserDto;
import es.uniovi.apuntesunioviapp.repositories.UserRepository;
import es.uniovi.apuntesunioviapp.services.UserService;
import es.uniovi.apuntesunioviapp.services.commands.users.CreateUser;
import es.uniovi.apuntesunioviapp.services.commands.users.DeleteUser;
import es.uniovi.apuntesunioviapp.services.commands.users.FindAllUsers;
import es.uniovi.apuntesunioviapp.services.commands.users.FindUserById;
import es.uniovi.apuntesunioviapp.services.commands.users.UpdateUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        log.info("findAll({}) - start", pageable);
        var page = new FindAllUsers(userRepository, pageable).execute();
        log.info("findAll({}) - end", pageable);
        return page;
    }

    @Override
    public UserDto findById(Long id) {
        log.info("findById({}) - start", id);
        var userDto = new FindUserById(userRepository, id).execute();
        log.info("findById({}) - end", id);
        return userDto;
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.info("create({}) - start", userDto);
        var result = new CreateUser(userRepository, userDto).execute();
        log.info("create({}) - end", userDto);
        return result;
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.info("findAll({}) - start", userDto);
        var result = new UpdateUser(userRepository, userDto).execute();
        log.info("findAll({}) - end", userDto);
        return result;
    }

    @Override
    public Long deleteById(Long id) {
        log.info("deleteById({}) - start", id);
        new DeleteUser(userRepository, id).execute();
        log.info("deleteById({}) - end", id);
        return null;
    }
}
