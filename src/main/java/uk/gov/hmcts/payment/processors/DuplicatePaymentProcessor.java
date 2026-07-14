package uk.gov.hmcts.payment.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DuplicatePaymentProcessor implements JobProcessor {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Logger LOG = LoggerFactory.getLogger(DuplicatePaymentProcessor.class.getName());
    LocalDate previousDay = LocalDate.now().minusDays(1);
    String startDate = previousDay.atStartOfDay().format(formatter);
    String endDate = previousDay.atTime(LocalTime.MAX).format(formatter);

    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.info("Value in DuplicatePaymentProcessor-----"+"BaseURL--------"+baseURL+"?start_date="+startDate+"&end_date="+endDate);
        String postURL = baseURL + "/jobs/duplicate-payment-process?start_date="+startDate+"&end_date="+endDate;

        PayApiClient.post(serviceToken, postURL);
    }
}
