package com.example.demo.service;

import com.example.demo.domain.Card;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CardService {

    private List<Card> cache = new ArrayList<>();

    public Card create(Card card){
        cache.add(card);
        return card;
    }

    public ImmutableList<Card> createAll(List cards){
        cache.addAll(cards);
        return cache.stream().collect(ImmutableList.toImmutableList());
    }

    public void delete(Card card){
        cache.remove(card);
    }

    public Card edit(Long id, Card card){

        Card cardOld = cache.stream()
                .filter(card1 -> card1.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(cardOld != null){
            card.setId(id);
            Collections.replaceAll(cache, cardOld, card);
            return card;
        }

        return null;
    }

    public Card findOne(Card card){
        return cache.stream()
                .filter(card1 -> card1.getId().equals(card.getId()))
                .findFirst()
                .orElse(null);
    }

    public ImmutableList<Card> findAll(){
        return cache.stream().collect(ImmutableList.toImmutableList());
    }

    public ImmutableList<Card> findByTitle(String title){
        return cache.stream()
                .filter(card1 -> card1.getTitle().equals(title))
                .collect(ImmutableList.toImmutableList());
    }
}
