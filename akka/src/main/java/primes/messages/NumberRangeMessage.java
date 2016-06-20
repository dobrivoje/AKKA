package primes.messages;

import java.io.Serializable;

public class NumberRangeMessage implements Serializable {

    private long lowerNo;
    private long higherNo;

    public NumberRangeMessage(long lowerNo, long higherNo) {
        this.lowerNo = lowerNo;
        this.higherNo = higherNo;
    }

    public long getLowerNo() {
        return lowerNo;
    }

    public void setLowerNo(long lowerNo) {
        this.lowerNo = lowerNo;
    }

    public long getHigherNo() {
        return higherNo;
    }

    public void setHigherNo(long higherNo) {
        this.higherNo = higherNo;
    }

    public long getInterval() {
        return higherNo - lowerNo;
    }

}
