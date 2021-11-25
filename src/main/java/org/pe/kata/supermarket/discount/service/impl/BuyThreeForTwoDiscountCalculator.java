package org.pe.kata.supermarket.discount.service.impl;

import org.pe.kata.supermarket.Item;
import org.pe.kata.supermarket.discount.service.DiscountCalculator;

import java.math.BigDecimal;
import java.util.List;

public class BuyThreeForTwoDiscountCalculator implements DiscountCalculator {

    public BuyThreeForTwoDiscountCalculator(final List<Item> itemList) {}

    @Override
    public BigDecimal applyDiscount() {
        return null;
    }
}
