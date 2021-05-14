package com.example.testapp.repository;

import com.example.testapp.entities.CryptoCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CryptoCurrencyRepository extends CrudRepository<CryptoCurrency, String> {

}
