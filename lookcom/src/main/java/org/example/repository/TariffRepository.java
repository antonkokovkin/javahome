package org.example.repository;

import org.example.model.TariffCost;
import org.example.model.TariffEx;

import java.util.List;

public interface TariffRepository {
    /**
     * Подсчитывает кол-во использование тарифа
     * @return список тарифов с дополнительным полем (кол-во абонентов с таким тарифом)
     */
    List<TariffEx> tariffCount();

    /**
     * Добавляет доступность тарифа в регионе
     * @param tariffName наименование тарифа
     * @param regionName наименование региона
     * @param cost       абонентская плата в этом регионе
     */
    public void addTariffRegion( String tariffName , String regionName , int cost );

    /**
     * Поиск тарифов в указанном регионе
     * @param regionName наименование региона
     * @return список тарифов с дополнительными полями (регион и цена в этом регионе)
     */
    public List<TariffCost> findTariffByRegion( String regionName );

    /**
     * Поиск тарифов во всех регионах
     * @return список тарифов с дополнительными полями (регион и цена в этом регионе)
     */
    public List<TariffCost> findTariffAllRegion();
}
