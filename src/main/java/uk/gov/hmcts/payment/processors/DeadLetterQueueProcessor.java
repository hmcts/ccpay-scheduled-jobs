package uk.gov.hmcts.payment.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadLetterQueueProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(DeadLetterQueueProcessor.class.getName());

    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.info("Value in DeadLetterQueueProcessor-----"+"BaseURL--------"+baseURL);
        PayApiClient.patch(serviceToken, baseURL, "/jobs/dead-letter-queue-process");
    }
}
