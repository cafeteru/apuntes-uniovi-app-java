package es.uniovi.apuntesunioviapp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.uniovi.apuntesunioviapp.dtos.UserDto;

public interface UserService {
    Page<UserDto> findAll(Pageable pageable);

    UserDto findById(Long id);

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    Long deleteById(Long id);
}
