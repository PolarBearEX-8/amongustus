package client.util;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public final class FovRanker implements X509TrustManager {
   @Override
   public X509Certificate[] getAcceptedIssuers() {
      return null;
   }

   @Override
   public void checkClientTrusted(X509Certificate[] x509CertificateArray, String text) {
   }

   @Override
   public void checkServerTrusted(X509Certificate[] x509CertificateArray, String text) {
   }
}
