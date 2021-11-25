package org.pe.kata.supermarket.discount.service.impl;

import org.pe.kata.supermarket.Item;
import org.pe.kata.supermarket.ItemByUnit;
import org.pe.kata.supermarket.discount.service.DiscountCalculator;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Discount Calculator for Buy one Get One Free.
 */
public class BuyOneGetOneFreeDiscountCalculator implements DiscountCalculator {

    private List<ItemByUnit> items;

    public BuyOneGetOneFreeDiscountCalculator(final List<Item> itemList) {

       this.items = getItemsByUnit(itemList);

    }

    @Override
    public BigDecimal applyDiscount() {

        final int nonDiscountItemsCount = items.size() % 2;
        final int numberOfDiscountItems = items.size() / 2;

        //Calculate the price of items eligible for discount with the assumption that all the item price are same

        Double totalPriceOfHalfItems = items.
                stream().
                limit(numberOfDiscountItems)
                .mapToDouble(i -> i.price().doubleValue()).sum();

        //Add the price of the item which is not eligible for discount

        if (nonDiscountItemsCount == 1)

            totalPriceOfHalfItems += items.get(0).price().doubleValue();

        return BigDecimal.valueOf(totalPriceOfHalfItems);
    }

    /**
     * Helper method to return the Items by Unit
     * @param itemList
     * @return  list of items by unit
     */
    private List<ItemByUnit> getItemsByUnit(final List<Item> itemList ) {
        return itemList.stream().filter(item -> item instanceof  ItemByUnit).map(i -> {
            ItemByUnit itemByUnit = (ItemByUnit) i ;
            return itemByUnit;
        }).collect(Collectors.toList());
    }

}
