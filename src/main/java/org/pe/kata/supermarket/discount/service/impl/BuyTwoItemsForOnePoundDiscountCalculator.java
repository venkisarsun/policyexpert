package org.pe.kata.supermarket.discount.service.impl;

import org.pe.kata.supermarket.Item;
import org.pe.kata.supermarket.discount.service.DiscountCalculator;

import java.math.BigDecimal;
import java.util.List;

/**
 * Discount Two items for one pound discount type.
 */
public class BuyTwoItemsForOnePoundDiscountCalculator implements DiscountCalculator {

    public BuyTwoItemsForOnePoundDiscountCalculator(final List<Item> itemList) {}

    @Override
    public BigDecimal applyDiscount() {
        return null;
    }
}
