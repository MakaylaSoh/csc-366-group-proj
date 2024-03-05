package csc366.jpademo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
    //GET
    @Query("select s from Store s where s.id = :storeId")
    Store findByStoreId(@Param("storeId") Long storeId);
    List<Store> findByCountry(String country);
    List<Store> findByCountryAndState(String country, String state);
    List<Store> findByCountryAndStateAndCity(String country, String state, String city);
    List<Store> findByCountryAndStateAndCityAndStreet(String country, String state, String city, String street);
    List<Store> findByCountryAndStateAndCityAndStreetAndZipcode(String country, String state, String city, String street, int zipcode);
    List<Store> findByZipcode(int zipcode);

    //UPDATE - no updates, a store can close and re-open

    //DELETE
    @Modifying
    @Query("delete from Store s where s.id = :storeId")
    void deleteStoreByStoreId(@Param("storeId") Long storeId);    
}
