package es.uniovi.apuntesunioviapp.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.apuntesunioviapp.dtos.UserDto;
import es.uniovi.apuntesunioviapp.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(Pageable pageable) {
        log.info("findAll({}) - start", pageable);
        var page = userService.findAll(pageable);
        log.info("findAll({}) - end", pageable);
        return new ResponseEntity<>(page, page.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findById(@PathVariable("id") Long id) {
        log.info("findById({}) - start", id);
        var userDto = userService.findById(id);
        log.info("findById({}) - end", id);
        return userDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        log.info("create({}) - start", userDto);
        var result = userService.create(userDto);
        log.info("create({}) - end", userDto);
        return result;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto update(@RequestBody UserDto userDto) {
        log.info("update({}) - start", userDto);
        var result = userService.update(userDto);
        log.info("update({}) - end", userDto);
        return result;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
        @PathVariable Long id
    ) {
        log.info("delete({}) - start", id);
        userService.deleteById(id);
        log.info("delete({}) - start", id);
    }
}
