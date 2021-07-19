package sg.gov.tech.url.shorten.repository;

public interface BaseRepositoryDao <T, U, V>{
    public U insert(T t);
    public U delete(T t);
    public V fetch(T t);
}
