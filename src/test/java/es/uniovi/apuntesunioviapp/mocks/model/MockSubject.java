package es.uniovi.apuntesunioviapp.mocks.model;

import es.uniovi.apuntesunioviapp.mocks.MockCreator;
import es.uniovi.apuntesunioviapp.model.Subject;
import es.uniovi.apuntesunioviapp.model.types.SubjectType;

/**
 * Create a mock subject to test
 */
public class MockSubject implements MockCreator<Subject> {

    @Override
    public Subject create() {
        return Subject.builder()
            .active(true)
            .subjectType(SubjectType.BASIC)
            .name("Name")
            .build();
    }
}
