package it.swedbank.academy.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class LoanUtil {

    public static BigDecimal calculateVehicleDepreciation(BigDecimal initialValue, Date manufacturedYear, int maximumYears) {
        return initialValue.multiply((new BigDecimal(DateUtil.differenceInDays(new Date(), manufacturedYear) / 365))).divide(new BigDecimal(maximumYears), 2, RoundingMode.HALF_UP);
    }
}
