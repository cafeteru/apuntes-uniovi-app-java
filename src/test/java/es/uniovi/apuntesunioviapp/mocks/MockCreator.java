package es.uniovi.apuntesunioviapp.mocks;

/**
 * Service to create mock data for testing
 *
 * @param <T> Mock class type
 */
public interface MockCreator<T> {
    /**
     *  Create a mock data
     *
     * @return a mock object to use in tests
     */
    T create();
}
