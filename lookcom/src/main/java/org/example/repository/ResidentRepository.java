package org.example.repository;

import org.example.model.Resident;

import java.util.List;

public interface ResidentRepository {
    /**
     * Поиск всех пользователей
     * @return список пользователей
     */
    List<Resident> findAll();

    /**
     * Поиск в базе по имени
     * @param name имя
     * @return список с заданным именем
     */
    List<Resident> findByName( String name );

    /**
     * Сохранение пользователя в базе данных
     * @param resident измененный пользователь
     */
    void save(Resident resident);
}
