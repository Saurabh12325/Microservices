package com.microservice.card.Service;

import com.microservice.card.Constants.CardConstants;
import com.microservice.card.DTO.CardDto;
import com.microservice.card.Exception.CardAlreadyExistException;
import com.microservice.card.Exception.ResourceNotFoundException;
import com.microservice.card.Mapper.CardMapper;
import com.microservice.card.Repository.CardRepository;
import com.microservice.card.entity.CardEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber) {
     Optional<CardEntity> card = cardRepository.findByMobileNumber(mobileNumber);
     if(card.isPresent()){
         throw new CardAlreadyExistException("Card already exists with given mobileNumber " + mobileNumber);
     }
        cardRepository.save(createNewCard(mobileNumber));
    }



    private CardEntity createNewCard(String MobileNumber) {
        CardEntity card = new CardEntity();
        card.setMobileNumber(MobileNumber);
        long randomCardNumber = 100000000000L + new Random().nextLong(900000000);
        card.setCardNumber(Long.toString(randomCardNumber));
        card.setCardType(CardConstants.CREDIT_CARD);
        card.setAmountUsed(0);
        card.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        card.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return card;
    }
    @Override
    public CardDto fetchCard(String mobileNumber) {
        CardEntity cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.mapToCardDTO(cards, new CardDto());
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        boolean isUpdate;
        CardEntity card = cardRepository.findByMobileNumber(cardDto.getMobileNumber()).orElseThrow(
                   () -> new ResourceNotFoundException("Card", "mobileNumber", cardDto.getMobileNumber())
           );
        CardMapper.maptoCardEntity(cardDto,card);
        cardRepository.save(card);
        isUpdate = true;
        return isUpdate;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        boolean isdelete;
        CardEntity card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.delete(card);
        isdelete = true;
        return isdelete;

    }

}
