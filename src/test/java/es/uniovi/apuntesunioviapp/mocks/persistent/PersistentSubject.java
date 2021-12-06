package es.uniovi.apuntesunioviapp.mocks.persistent;

import es.uniovi.apuntesunioviapp.mocks.MockCreator;
import es.uniovi.apuntesunioviapp.mocks.model.MockSubject;
import es.uniovi.apuntesunioviapp.model.Subject;
import es.uniovi.apuntesunioviapp.repositories.SubjectRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersistentSubject implements MockCreator<Subject> {
    private final SubjectRepository subjectRepository;

    @Override
    public Subject create() {
        var subject = new MockSubject().create();
        return subjectRepository.save(subject);
    }
}
