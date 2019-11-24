package study.tdd.money;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MoneyTest {

    @Test
    public void testMultiplication(){
        Money five = Money.dollar(5);

        assertThat(Money.dollar(10)).isEqualTo(five.times(2));
        assertThat(Money.dollar(15)).isEqualTo(five.times(3));
    }

    @Test
    public void testMultiplication2(){
        Money five = Money.dollar(5);
        assertThat(Money.dollar(10)).isEqualTo(five.times(2));

        assertThat(Money.dollar(15)).isEqualTo(five.times(3));
    }

    @Test
    public void testEquality(){
        assertThat(Money.dollar(5)).isEqualTo(Money.dollar(5));
        assertThat(Money.dollar(5)).isNotEqualTo(Money.dollar(6));
        assertThat(Money.franc(5)).isEqualTo(Money.franc(5));
        assertThat(Money.franc(5)).isNotEqualTo(Money.franc(6));
        assertThat(Money.dollar(5)).isNotEqualTo(Money.franc(5));
    }

    @Test
    public void testFrancMultiplication(){
        Money five = Money.franc(5);
        assertThat(Money.franc(10)).isEqualTo(five.times(2));

        assertThat(Money.franc(15)).isEqualTo(five.times(3));
    }

    @Test
    public void testCurrency(){
        assertThat("USD").isEqualTo(Money.dollar(1).currency());
        assertThat("CHF").isEqualTo(Money.franc(1).currency());
    }

    @Test
    public void testSimpleAddition(){
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertThat(Money.dollar(10)).isEqualTo(sum);
    }
}
