package com.switchfully.eurder.items;

import com.switchfully.eurder.exceptions.InvalidAmountException;
import com.switchfully.eurder.exceptions.InvalidItemNameException;
import com.switchfully.eurder.exceptions.InvalidPriceException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Nested
    @DisplayName("Constructor Tests")

    class Constructor{
        @Test
        void givenNameNull_thenThrowsInvalidItemNameException(){
            Assertions.assertThatThrownBy(()-> new Item(null, 1.0, 1, "description"))
                    .isInstanceOf(InvalidItemNameException.class)
                    .hasMessage("Provided Item name cannot be null or empty");
        }
        @Test
        void givenNameEmpty_thenThrowsInvalidItemNameException(){
            Assertions.assertThatThrownBy(()-> new Item("", 1.0, 1, "description"))
                    .isInstanceOf(InvalidItemNameException.class)
                    .hasMessage("Provided Item name cannot be null or empty");
        }
        @Test
        void givenPriceNull_thenThrowsInvalidPriceException(){
            Assertions.assertThatThrownBy(()-> new Item("itemName", null, 1, "description"))
                    .isInstanceOf(InvalidPriceException.class)
                    .hasMessage("Price cannot be negative");
        }
        @Test
        void givenPriceNegative_thenThrowsInvalidPriceException(){
            Assertions.assertThatThrownBy(()-> new Item("itemName", -1.5, 1, "description"))
                    .isInstanceOf(InvalidPriceException.class)
                    .hasMessage("Price cannot be negative");
        }
        @Test
        void givenAmountNull_thenThrowsInvalidAmountException(){
            Assertions.assertThatThrownBy(()-> new Item("itemName", 1.0, null, "description"))
                    .isInstanceOf(InvalidAmountException.class)
                    .hasMessage("Amount of items can not be lower than zero");
        }
        @Test
        void givenAmountNegative_thenThrowsInvalidAmountException(){
            Assertions.assertThatThrownBy(()-> new Item("itemName", 1.0, -5, "description"))
                    .isInstanceOf(InvalidAmountException.class)
                    .hasMessage("Amount of items can not be lower than zero");
        }
    }
}
