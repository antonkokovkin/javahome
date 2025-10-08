package org.example.App;

import org.example.model.*;
import org.example.repository.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.List;

public class App {
    public static void main( String[] args ) {

        // Параметры подключения из файла , читаем файл
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\db.properties");
            properties.load( fileInputStream );
        } catch (IOException e) {
            e.printStackTrace();
        }

        // подгружаем
        DataSource dataSource = new DriverManagerDataSource(
                properties.getProperty( "db.url"      ) ,
                properties.getProperty( "db.user"     ) ,
                properties.getProperty( "db.password" )
        );

        // Основные таблицы и операции над ними
        TariffRepository    tariffRepository     = new TariffRepositoryJdbcTemplateImpl(    dataSource );
        RouterRepository    routerRepository     = new RouterRepositoryJdbcTemplateImpl(    dataSource );
        ResidentRepository  residentRepository   = new ResidentRepositoryJdbcTemplateImpl(  dataSource );
        AddressRepository   addressRepository    = new AddressRepositoryJdbcTemplateImpl(   dataSource );
        AgreementRepository agreementRepository  = new AgreementRepositoryJdbcTemplateImpl( dataSource );

        // --- Демонстрация CRUID операций ---

        List<TariffEx> listTariffEx = tariffRepository.tariffCount();

        System.out.println( "ТОП-3 самых популярных тарифов" );
        listTariffEx.stream().limit( 3 ).forEach( te -> System.out.println( te.getName() + " : " + te.getCount() ) );
        System.out.println( "" );

        // --- Свободный порт (возможность подключения) по адресу ---

        String addrRegion = "Воронежская область";
        String addrCity   = "Воронеж";
        String addrStreet = "улица Новая";
        String addrHouse  = "3";
        List<RouterPort> freeRouterPort = routerRepository.getFreePort( addrRegion , addrCity , addrStreet , addrHouse , 1 );
        RouterPort routerPort;

        System.out.println( "Свободный порт (возможность подключения) по адресу : " + addrRegion + ", " + addrCity + ", " + addrStreet + ", д. " + addrHouse );

        if ( freeRouterPort.size() == 0 ) {
            System.out.println( "Нет свободных портов\n" );
        } else {
            routerPort = freeRouterPort.get( 0 );
            System.out.println( "Идентификатор маршрутизатора : " + routerPort.getRouter() + " , номер порта : " + routerPort.getPort() + "\n" );
        }

        // --- Добавляем тариф а регион ---

        String tariffName   = "Супер+";
        String tariffRegion = "Брянская область";
        int    tariffCost   = 301;
        System.out.println( "" );

        List<TariffCost> listTariffBefore = tariffRepository.findTariffByRegion( tariffRegion );
        tariffRepository.addTariffRegion( tariffName , tariffRegion , tariffCost );
        List<TariffCost> listTariffAfter  = tariffRepository.findTariffAllRegion(); // для демонстрации фильтрации через Stream API

        System.out.println( "Ищем доступные тарифы в регионе \"" + tariffRegion + "\"" );

        if ( listTariffBefore.size() == 0 ) {
            System.out.println( "Для региона \"" + tariffRegion + "\" не предусмотрены тарифы\n" );
        } else {
            listTariffBefore.stream().forEach( t -> System.out.println( t.getName() + " : " + t.getCost() ) );
            System.out.println( "" );
        }

        System.out.println( "Добавляем тариф \"" + tariffName + "\" в регион \"" + tariffRegion + "\" с абонентской платой в этом регионе \"" + tariffCost + "\" р." );
        System.out.println( "Ищем ещё раз доступные тарифы в регионе \"" + tariffRegion + "\"" );

        if ( listTariffAfter.size() == 0 ) {
            System.out.println( "Для региона \"" + tariffRegion + "\" не нашлись тарифы\n" );
        } else {
            listTariffAfter.stream().filter( t -> t.getRegion().equals( tariffRegion ) ).forEach( t -> System.out.println( t.getName() + " : " + t.getCost() ) );
            System.out.println( "" );
        }

        // --- Удалить все записи адресов в домах которых нет маршрутизаторов ---

        List<Address> listAddress = addressRepository.findAddressAll();

        System.out.println( "Количество зарегистрированных адресов было : " + listAddress.size() );
        System.out.println( "Удалить все записи адресов в домах которых нет маршрутизаторов" );
        addressRepository.deleteNotRouter();
        listAddress = addressRepository.findAddressAll();
        System.out.println( "Количество зарегистрированных адресов стало : " + listAddress.size() + "\n" );

        // --- Списание абонентской платы (у всех) ---

        List<Abonent> listAbonent = agreementRepository.findAbonentAll();
        int id , balance , cost;

        System.out.println( "Абоненты (не все, первые 10 из списка)" );
        listAbonent.stream().limit( 10 ).forEach( ab -> System.out.println( ab.getLast_name() + " " + ab.getFirst_name() + " , на счету : " + ab.getBalance() + " , абонтплата : " + ab.getCost() ) );

        for ( Abonent abonent : listAbonent ) {
            id      = abonent.getId();
            balance = abonent.getBalance();
            cost    = abonent.getCost();
            agreementRepository.updateBalance( id , balance - cost );
        }

        listAbonent = agreementRepository.findAbonentAll();
        System.out.println( "\nПроизвели списание абонентской платы" );
        listAbonent.stream().limit( 10 ).forEach( ab -> System.out.println( ab.getLast_name() + " " + ab.getFirst_name() + " , на счету : " + ab.getBalance() + " , абонтплата : " + ab.getCost() ) );
        System.out.println( "" );
    }
}
