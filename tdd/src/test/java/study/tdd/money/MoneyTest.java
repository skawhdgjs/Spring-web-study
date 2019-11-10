package study.tdd.money;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MoneyTest {

    @Test
    public void testMultiplication(){
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);

        assertThat(new Dollar(10)).isEqualTo(five.times(2));
    }

    @Test
    public void testMultiplication2(){
        Dollar five = new Dollar(5);
        assertThat(new Dollar(10)).isEqualTo(five.times(2));

        assertThat(new Dollar(15)).isEqualTo(five.times(3));
    }

    @Test
    public void testEquality(){
        assertThat(new Dollar(5)).isEqualTo(new Dollar(5));
        assertThat(new Dollar(5)).isNotEqualTo(new Dollar(6));
    }

    @Test
    public void testFrancMultiplication(){
        Franc five = new Franc(5);
        assertThat(new Dollar(10)).isEqualTo(five.times(2));

        assertThat(new Dollar(15)).isEqualTo(five.times(3));
    }
}
