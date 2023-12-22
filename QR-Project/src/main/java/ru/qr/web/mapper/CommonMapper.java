package ru.qr.web.mapper;

public interface CommonMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}
