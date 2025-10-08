package org.example.repository;

import org.example.model.Abonent;

import java.util.List;

public interface AgreementRepository {
    /**
     * Обновление баланса
     * @param id ID абонента
     * @param balance баланс
     */
    public void updateBalance( int id , int balance );

    /**
     * Поиск всех абонентов
     * @return список всех абонентов
     */
    public List<Abonent> findAbonentAll();
}