package uk.gov.hmcts.payment.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnprocessedPaymentUpdateProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(UnprocessedPaymentUpdateProcessor.class.getName());

    @Override
    public void process(String serviceToken, String baseURL) {
        LOG.debug("Value in UnprocessedPaymentUpdateProcessor-----:{}",baseURL);
        PayApiClient.patch(serviceToken, baseURL, "/jobs/unprocessed-payment-update");
    }
}
