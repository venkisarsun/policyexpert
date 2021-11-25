package org.pe.kata.supermarket.discount.service.impl;

import org.pe.kata.supermarket.Item;
import org.pe.kata.supermarket.discount.service.DiscountCalculator;
import org.pe.kata.supermarket.discount.service.DiscountCalculatorService;
import org.pe.kata.supermarket.discount.service.DiscountType;

import java.util.List;

/**
 * Implementation class to apply the discount calculation based on the discount type
 */
public class DiscountCalculatorServiceImpl implements DiscountCalculatorService {

    @Override
    public DiscountCalculator applyDiscountCalculation(final DiscountType discountType, final List<Item> itemList){

        if(discountType == null)
            throw new IllegalArgumentException("Discount type is required");

        switch(discountType) {
            case BUYONEGETONEFREE:
                return new BuyOneGetOneFreeDiscountCalculator(itemList);
            case BUYTWOITEMSFOR1POUND:
                return new BuyTwoItemsForOnePoundDiscountCalculator(itemList);
            case BUYTHREEFORRWO:
                return new BuyThreeForTwoDiscountCalculator(itemList);
            case BUYONEKILOOFVEGFORHALFPRICE:
                return new BuyOneKiloVegForHalfPriceDiscountCalculator(itemList);
            default:
                throw new IllegalArgumentException("Unsupported discount scheme " + discountType);
        }
    }

}
