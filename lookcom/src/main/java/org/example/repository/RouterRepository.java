package org.example.repository;

import org.example.model.RouterPort;

import java.util.List;

public interface RouterRepository {
    /**
     * Поиск свободного порта в маршрутизаторе по указанному адресу - регион, город, улица, дом.
     * @return первые count портов из списка свободных.
     * Если будет меньше чем count, вернёт сколько найдёт. Может вернуть пустой список, если нет таких вообще
     * Иногда нужно интернет отдельно, IPTV отдельно, третий порт может если и телефонию надо отдельно.
     * Может использоваться если надо перенести подключения с одного маршрутизатора на другой.
     */
    List<RouterPort> getFreePort( String region , String city , String street , String house , int count );
}
