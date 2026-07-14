package uk.gov.hmcts.payment.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardCsvReportProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(CardCsvReportProcessor.class.getName());

    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.info("Value in CardCsvReportProcessor-----"+"BaseURL--------"+baseURL);
        PayApiClient.post(serviceToken, baseURL + "/jobs/email-pay-reports?payment_method=CARD");
    }
}
