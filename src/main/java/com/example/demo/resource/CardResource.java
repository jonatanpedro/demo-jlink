package com.example.demo.resource;

import com.example.demo.domain.Card;
import com.example.demo.service.CardService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardResource {

    private CardService cardService;

    @Autowired
    public CardResource(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ImmutableList<Card> findAll(){
        return cardService.findAll();
    }

    @PostMapping
    public Card create(@RequestBody Card card){
        return cardService.create(card);
    }

    @PutMapping("/{id}")
    public Card edit(@RequestBody Card card, @PathVariable Long id){
        card.setId(id);
        return cardService.edit(id, card);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        cardService.delete(Card.builder().id(id).build());
    }
}
