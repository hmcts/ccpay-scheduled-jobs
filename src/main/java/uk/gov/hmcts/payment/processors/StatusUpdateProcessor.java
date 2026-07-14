package uk.gov.hmcts.payment.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatusUpdateProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(StatusUpdateProcessor.class.getName());

    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.debug("Value in StatusUpdateProcessor-----BaseURL--------{}", baseURL);
        PayApiClient.patch(serviceToken, baseURL, "/jobs/card-payments-status-update");
    }
}
