package org.pe.kata.supermarket.discount.service.impl;

import org.pe.kata.supermarket.Item;
import org.pe.kata.supermarket.ItemByWeight;
import org.pe.kata.supermarket.discount.service.DiscountCalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BuyOneKiloVegForHalfPriceDiscountCalculator implements DiscountCalculator {

    private List<ItemByWeight> items;

    public BuyOneKiloVegForHalfPriceDiscountCalculator(final List<Item> itemList) {
        this.items = getItemByWeight(itemList);
    }

    @Override
    public BigDecimal applyDiscount() {
        return BigDecimal.valueOf(items.stream().mapToDouble(item -> item.price().doubleValue() / 2).sum());
    }

    /**
     * Helper method to get the list of items by weight
     *
     * @param itemList
     * @return List of items by weight
     */
    private List<ItemByWeight> getItemByWeight(final List<Item> itemList) {

       return itemList.stream().filter(item -> item instanceof ItemByWeight).map(i -> {
            ItemByWeight itemByWeight = (ItemByWeight) i ;
            return itemByWeight;
        }).collect(Collectors.toList());

    }

}
