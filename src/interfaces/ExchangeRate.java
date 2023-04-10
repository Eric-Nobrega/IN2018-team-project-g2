package interfaces;

public class ExchangeRate {
    public int Amount;
    public String CurrencyName;

    public int amountUSD;

    public ExchangeRate(int Amount, String CurrencyName, int amountUSD) {
        this.Amount = Amount;
        this.CurrencyName = CurrencyName;
        this.amountUSD = amountUSD;
    }

    public int getAmount() {
        return Amount;
    }

    public String getCurrencyName() {
        return CurrencyName;
    }

    public int getAmountUSD() {
        return amountUSD;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setCurrencyName(String currencyName) {
        CurrencyName = currencyName;
    }

    public void setAmountUSD(int amountUSD) {
        this.amountUSD = amountUSD;
    }
}
