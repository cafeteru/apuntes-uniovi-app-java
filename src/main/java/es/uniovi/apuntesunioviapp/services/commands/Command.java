package es.uniovi.apuntesunioviapp.services.commands;

public interface Command<T> {
    T execute();
}
