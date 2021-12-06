package es.uniovi.apuntesunioviapp.services.commands.users;

import es.uniovi.apuntesunioviapp.repositories.UserRepository;
import es.uniovi.apuntesunioviapp.services.commands.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DeleteUser implements Command<Void> {
    private final UserRepository userRepository;
    private final Long id;

    @Override
    public Void execute() {
        log.info("execute() - start");
        new FindUserById(userRepository, id).execute();
        log.info("execute() - end");
        userRepository.deleteById(id);
        return null;
    }
}
