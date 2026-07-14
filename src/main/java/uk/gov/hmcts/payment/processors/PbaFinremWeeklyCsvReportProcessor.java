package uk.gov.hmcts.payment.processors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PbaFinremWeeklyCsvReportProcessor implements JobProcessor {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Logger LOG = LoggerFactory.getLogger(PbaFinremWeeklyCsvReportProcessor.class.getName());

    @Override
    public void process(String serviceToken, String baseURL) {
        String date = LocalDateTime.now().minusDays(7).format(formatter);
        LOG.info("Value in PbaFinremWeeklyCsvReportProcessor-----"+"BaseURL--------"+baseURL+"Date-----"+date);
        PayApiClient.post(
                serviceToken,
                baseURL + "/jobs/email-pay-reports?payment_method=PBA&service_name=Finrem&start_date=" + date
        );
    }
}
