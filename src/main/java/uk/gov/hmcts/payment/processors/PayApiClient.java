package uk.gov.hmcts.payment.processors;

import java.io.IOException;
import java.net.URI;
import java.security.GeneralSecurityException;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.ssl.ClientTlsStrategyBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.ssl.SSLContexts;

final class PayApiClient {

    private static final String SERVICE_AUTHORIZATION = "ServiceAuthorization";

    private PayApiClient() {
    }

    static void post(String serviceToken, String url) {
        execute(serviceToken, new HttpPost(toUri(url)));
    }

    static void patch(String serviceToken, String baseUrl, String path) {
        execute(serviceToken, new HttpPatch(toUri(baseUrl + path)));
    }

    private static void execute(String serviceToken, HttpUriRequestBase request) {
        request.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        request.addHeader(SERVICE_AUTHORIZATION, serviceToken);
        request.setEntity(new StringEntity("", ContentType.APPLICATION_JSON));

        try (CloseableHttpClient client = createClient()) {
            client.execute(request, response -> {
                EntityUtils.consumeQuietly(response.getEntity());
                return null;
            });
        } catch (GeneralSecurityException | IOException e) {
            throw new IllegalStateException(
                    "Failed to call Payment API " + request.getMethod() + " " + request.getRequestUri(),
                    e
            );
        }
    }

    private static CloseableHttpClient createClient() throws GeneralSecurityException {
        var sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
                .build();
        var tlsSocketStrategy = ClientTlsStrategyBuilder.create()
                .setSslContext(sslContext)
                .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .buildClassic();
        var connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setTlsSocketStrategy(tlsSocketStrategy)
                .build();

        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
    }

    private static URI toUri(String url) {
        return URI.create(url.replace(" ", "%20"));
    }
}
