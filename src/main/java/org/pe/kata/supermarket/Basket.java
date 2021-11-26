package org.pe.kata.supermarket;

import org.pe.kata.supermarket.discount.service.DiscountCalculatorService;
import org.pe.kata.supermarket.discount.service.DiscountType;
import org.pe.kata.supermarket.discount.service.impl.DiscountCalculatorServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Basket {
    private final List<Item> items;
    private final DiscountCalculatorService discountCalculatorService;

    public Basket() {
        this.items = new ArrayList<>();
        this.discountCalculatorService = new DiscountCalculatorServiceImpl();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            return Stream.of(DiscountType.values())
                    .map(discountType -> {
                       BigDecimal discount= discountCalculatorService.applyDiscountCalculation(discountType, items).applyDiscount();
                       return discount;
                    }
                    ).reduce(BigDecimal::add).get();
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
