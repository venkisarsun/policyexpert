package org.pe.kata.supermarket.discount.service;

import org.pe.kata.supermarket.Item;

import java.util.List;

/**
 * Interface method  to carry out discount calculation
 */
public interface DiscountCalculatorService {

    DiscountCalculator applyDiscountCalculation(DiscountType discountType, List<Item> itemList);
}
