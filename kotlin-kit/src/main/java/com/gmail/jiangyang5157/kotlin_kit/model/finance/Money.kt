package com.gmail.jiangyang5157.kotlin_kit.model.finance

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * Created by Yang Jiang on January 22, 2019
 */
data class Money(val amount: BigDecimal, val currency: Currency) {

    constructor(amount: Long, currency: Currency) :
            this(BigDecimal.valueOf(amount, currency.defaultFractionDigits), currency)

    constructor(amount: Long, currencyCode: String) :
            this(amount, Currency.getInstance(currencyCode))

    constructor(amount: Double, currency: Currency) :
            this(BigDecimal.valueOf(amount).setScale(currency.defaultFractionDigits, RoundingMode.UP), currency)

    constructor(amount: Double, currencyCode: String) :
            this(amount, Currency.getInstance(currencyCode))

    /**
     * Returns the sign for the money (negative or positive).
     * -1 if negative, 0 if 0.00 (zero), 1 if positive.
     *
     * @return The sign of the money.
     */
    fun signum() = amount.signum()

    /**
     * Returns the 'scale' for this money.
     * The scale is the number of digits that are moved to the fractional part.
     *
     * Assuming that all digits are represented by a single integer value.
     * For example:
     * If: 123456789012345678 has scaling 2, it would be : 1234567890123456.78
     *
     * @return The scale value.
     */
    fun scale() = amount.scale()

    /**
     * Returns the precision for this money.
     * The precision is the total number of digits that the value can represent.
     *
     * This includes the integer part.
     * For example:
     * 18 would be able to represent for:
     * 123456789012345678
     * 1234567890.12345678
     * 0.123456789012345678
     *
     * @return The precision.
     */
    fun precision() = amount.precision()

    /**
     * Returns the ISO-4217 currency code of the currency attached to this money.
     *
     * @return The ISO-4217 currency code.
     */
    fun getCurrencyCode() = currency.currencyCode

    fun hasSameCurrency(other: Money) = this.getCurrencyCode() == other.getCurrencyCode()

    fun amountAsLong() = amount.unscaledValue().longValueExact()


//    private val cents = intArrayOf(1, 10, 100, 1000)
//
//    //private MathContext DEFAULT_CONTEXT = new MathContext( 2, HALF_UP );
//
//    private val DEFAULT_CONTEXT = MathContext(10, RoundingMode.HALF_DOWN)
//
//    public static Money dollars(double amount) {
//        Money result = null;
//        try {
//            result = new Money(amount, "USD");
//        } catch (UnknownCurrencyCodeException ex) {
//            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
//
//    public static Money dollars(long amount) {
//        Money result = null;
//        try {
//            result = new Money(amount, "USD");
//        } catch (UnknownCurrencyCodeException ex) {
//            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
//
//    public static Money pounds(double amount) {
//        Money result = null;
//        try {
//            result = new Money(amount, "GBP");
//        } catch (UnknownCurrencyCodeException ex) {
//            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
//
//    public static Money pounds(long amount) {
//        Money result = null;
//        try {
//            result = new Money(amount, "GBP");
//        } catch (UnknownCurrencyCodeException ex) {
//            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
//
//    public static Money pounds(BigDecimal amount) {
//        Money result = null;
//        try {
//            result = new Money(amount, "GBP");
//        } catch (UnknownCurrencyCodeException ex) {
//            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = (int) ( amount.hashCode() ^ (amount.hashCode() >>> 32) );
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return (other instanceof Money && equals((Money) other));
//    }
//
//    public boolean equals(Money other) {
//        return ( currency.equals(other.currency) && (amount.equals(other.amount)) );
//    }
//
//    public Money add(Money other) throws Exception{
//        assertSameCurrencyAs( other );
//        return newMoney(amount.add(other.amount, DEFAULT_CONTEXT));
//    }
//
//    private int compareTo(Money money) throws Exception {
//        assertSameCurrencyAs( money );
//        return amount.compareTo( money.amount );
//    }
//
//    public Money multiply(BigDecimal amount) {
//        return new Money( this.amount().multiply(amount, DEFAULT_CONTEXT), currency);
//    }
//
//    public Money multiply( BigDecimal amount, RoundingMode roundingMode ) {
//        MathContext ct = new MathContext( currency.getDefaultFractionDigits(), roundingMode );
//        return new Money( amount().multiply(amount, ct), currency);
//    }
//
//    private Money newMoney(BigDecimal amount) {
//        return new Money( amount, this.currency );
//    }
//
//    public Money multiply(double amount) {
//        return multiply( new BigDecimal( amount ) );
//    }
//
//    public Money subtract(Money other) throws Exception {
//        assertSameCurrencyAs(other);
//        return newMoney( amount.subtract(other.amount, DEFAULT_CONTEXT) );
//    }
//
//    public int compareTo(Object other) throws Exception {
//        return compareTo((Money) other);
//    }
//
//    public boolean greaterThan(Money other)throws Exception {
//        return (compareTo(other) > 0);
//    }
//
////  public Money[] allocate(int n){
////    Money lowResult = newMoney( amount.unscaledValue().longValue()/n );
////    Money highResult = newMoney(lowResult.amount + 1);
////    Money[] results = new Money[n];
////    int remainder = (int) amount % n;
////
////    for(int i = 0; i < remainder; i++)results[i] = highResult;
////    for(int i = 0; i < n; i++) results[i] = lowResult;
////
////    return results;
////  }
////
////  public Money[]allocate(long[] ratios){
////    long total = 0;
////    for (int i = 0; i < ratios.length; i++) {
////      total += ratios[i];
////    }
////    long remainder = amount;
////    Money[] results = new Money[ratios.length];
////    for (int i = 0; i < results.length; i++) {
////      results[i] = newMoney(amount * ratios[i]/total);
////      remainder -= results[i].amount;
////    }
////    for (int i = 0; i < remainder; i++) {
////      results[i].amount++;
////    }
////    return results;
////
////  }
//
//    public Money divideByNumber( double divisor){
//        BigDecimal div = BigDecimal.valueOf( divisor );
//        BigDecimal ans = this.amount.divide(div, DEFAULT_CONTEXT);
//        return new Money(ans, this.currency);
//    }
//
//    public int getQuotient( Money divisor ){
//        BigDecimal ans = this.amount.divide(divisor.amount, RoundingMode.DOWN);
//        return ans.intValue();
//    }
//
//    /**
//     * divides toe moneys and return the quotient and Remainder this method has been customised,
//     * for my money transfer needs...sorry
//     * @param divisor
//     * @return
//     */
//    public int[] getQuotientandRemainder(Money divisor){
//        int[] ans = new int[2];
//        BigDecimal[] bdArr = this.amount.divideAndRemainder(divisor.amount, DEFAULT_CONTEXT);
//        BigDecimal quo = bdArr[0];
//        BigDecimal rem = bdArr[1];
//        ans[0] = quo.intValue();
//        if( rem.compareTo(BigDecimal.ZERO) == 0 ){
//            ans[1] =0;
//        }else{
//            ans[1] = 1;
//        }
//        return ans;
//    }
//
//    public String toFormattedString() {
//        NumberFormat nf = NumberFormat.getCurrencyInstance();
//        nf.setCurrency( currency );
//        nf.setGroupingUsed( true );
//        nf.setMaximumFractionDigits( currency.getDefaultFractionDigits() );
//        return nf.format( this.amount.doubleValue() );
//    }


//}
//
//    operator fun unaryMinus(): Money = Money(-x, -y)
//    operator fun plus(other: Money): Money = Money(x + other.x, y + other.y)
//    operator fun plus(int: Int): Money = Money(x + int, y + int)
//    operator fun plus(double: Double): Money = Money(x + double, y + double)
//    operator fun minus(other: Money): Money = Money(x - other.x, y - other.y)
//    operator fun minus(int: Int): Money = Money(x - int, y - int)
//    operator fun minus(double: Double): Money = Money(x - double, y - double)
//    operator fun times(other: Money): Money = Money(x * other.x, y * other.y)
//    operator fun times(int: Int): Money = Money(x * int, y * int)
//    operator fun times(double: Double): Money = Money(x * double, y * double)
//    operator fun div(other: Money): Money = Money(x / other.x, y / other.y)
//    operator fun div(int: Int): Money = Money(x / int, y / int)
//    operator fun div(double: Double): Money = Money(x / double, y / double)
}