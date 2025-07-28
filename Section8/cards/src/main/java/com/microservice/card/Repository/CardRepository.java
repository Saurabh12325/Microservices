package com.microservice.card.Repository;

import com.microservice.card.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {


     Optional<CardEntity> findByMobileNumber(String mobileNumber) ;

}
