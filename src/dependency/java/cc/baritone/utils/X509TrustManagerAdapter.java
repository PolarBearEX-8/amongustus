package cc.baritone.utils;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public final class X509TrustManagerAdapter implements X509TrustManager {
   @Override
   public void checkClientTrusted(X509Certificate[] x509CertificateArray, String text) {
   }

   @Override
   public void checkServerTrusted(X509Certificate[] x509CertificateArray, String text) {
   }

   @Override
   public X509Certificate[] getAcceptedIssuers() {
      return null;
   }
}
