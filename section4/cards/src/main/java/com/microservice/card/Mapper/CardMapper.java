package com.microservice.card.Mapper;

import com.microservice.card.DTO.CardDto;
import com.microservice.card.entity.CardEntity;

public class CardMapper {
    public static CardDto mapToCardDTO(CardEntity card , CardDto cardDto){
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setAvailableAmount(card.getAvailableAmount());
        cardDto.setMobileNumber(card.getMobileNumber());
        cardDto.setTatalLimit(card.getTotalLimit());
        return cardDto;
    }

    public static CardEntity maptoCardEntity(CardDto cardDto,CardEntity card){
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        card.setMobileNumber(cardDto.getMobileNumber());
        card.setTotalLimit(cardDto.getTatalLimit());
        return card;

    }
}
