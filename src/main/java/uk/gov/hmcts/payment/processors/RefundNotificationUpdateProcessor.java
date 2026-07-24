package uk.gov.hmcts.payment.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RefundNotificationUpdateProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(RefundNotificationUpdateProcessor.class.getName());

    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.debug("Value in Refund Notification Update Processor-----BaseURL--------{}", baseURL);
        PayApiClient.patch(serviceToken, baseURL, "/jobs/refund-notification-update");
    }
}
