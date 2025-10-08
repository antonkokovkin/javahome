package org.example.repository;

import org.example.model.Address;

import java.util.List;

public interface AddressRepository {
    /**
     * Поиск всех адресов
     * @return список адресов
     */
    public List<Address> findAddressAll();

    /**
     * Удалить все записи адресов в домах которых нет маршрутизаторов
     */
    public void deleteNotRouter();
}