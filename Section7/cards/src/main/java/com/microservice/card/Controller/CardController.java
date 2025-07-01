package com.microservice.card.Controller;

import com.microservice.card.Constants.CardConstants;
import com.microservice.card.DTO.CardContactInfoDto;
import com.microservice.card.DTO.CardDto;
import com.microservice.card.DTO.ResponseDto;
import com.microservice.card.Service.CardService;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/card")
public class CardController {

  private final CardService cardService;
    private final CardContactInfoDto cardContactInfoDto;
    public CardController(CardService cardService, CardContactInfoDto cardContactInfoDto) {
        this.cardService = cardService;
        this.cardContactInfoDto = cardContactInfoDto;
    }

    @Value("${build.version}")
    private String buildVersion;





    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber){
         cardService.createCard(mobileNumber);
            return ResponseEntity
                 .status(HttpStatus.CREATED)
                    .body(new ResponseDto(CardConstants.STATUS_201,CardConstants.MESSAGE_201));
    }
   @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCard(@RequestParam String mobileNumber){
        CardDto cardDto = cardService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@RequestBody CardDto cardDto){
        boolean isUpdated = cardService.updateCard(cardDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstants.STATUS_200,CardConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(CardConstants.STATUS_417,CardConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber){
        boolean isDeleted = cardService.deleteCard(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstants.STATUS_200,CardConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(CardConstants.STATUS_417,CardConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/version")
    public ResponseEntity<String> getBuiildVersion(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }


    @GetMapping("/cardInfo")
    public ResponseEntity<CardContactInfoDto> getContactInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(cardContactInfoDto);
    }
}
