package study.tdd.money;

import lombok.ToString;

@ToString
class Money implements Expression{
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }


    Money times(int multiplier){
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && currency.equals(money.currency);
    }

    public String currency(){
        return currency;
    }

    public Expression plus(Money added) {
        return new Money(amount + added.amount, currency);
    }
}
