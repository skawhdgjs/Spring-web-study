package study.tdd.money;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MoneyTest {

    @Test
    public void testMultiplication(){
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);

        assertThat(10).isEqualTo(product.amount);
    }

    @Test
    public void testMultiplication2(){
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        assertThat(10).isEqualTo(product.amount);

        product = five.times(3);
        assertThat(15).isEqualTo(product.amount);
    }
}
