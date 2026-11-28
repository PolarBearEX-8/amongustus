package com.thealtening.auth;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/** Keeps the platform's normal TLS certificate and hostname checks enabled. */
public final class SSLController {
   private final SSLSocketFactory originalFactory;
   private final HostnameVerifier originalHostVerifier;

   public SSLController() {
      this.originalFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
      this.originalHostVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
   }

   public void disableCertificateValidation() {
      // Intentionally disabled: never install a trust-all TLS configuration.
   }

   public void enableCertificateValidation() {
      HttpsURLConnection.setDefaultSSLSocketFactory(this.originalFactory);
      HttpsURLConnection.setDefaultHostnameVerifier(this.originalHostVerifier);
   }
}
