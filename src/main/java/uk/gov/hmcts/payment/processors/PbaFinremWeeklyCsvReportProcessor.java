package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PbaFinremWeeklyCsvReportProcessor implements JobProcessor {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Logger LOG = LoggerFactory.getLogger(PbaFinremWeeklyCsvReportProcessor.class.getName());
    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {
        String date = LocalDateTime.now().minusDays(7).format(formatter);
        LOG.info("Value in PbaFinremWeeklyCsvReportProcessor-----"+"BaseURL--------"+baseURL+"Date-----"+date);
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=Finrem&start_date="+date);
    }
}
