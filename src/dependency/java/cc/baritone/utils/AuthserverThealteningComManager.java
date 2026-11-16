package cc.baritone.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public final class AuthserverThealteningComManager {
   public static final TrustManager[] trustManagerArray = new TrustManager[]{new X509TrustManagerAdapter()};
   public static final HostnameVerifier hostnameVerifier = AuthserverThealteningComManager::matchesText;
   public final SSLSocketFactory sSLSocketFactory;
   public final SSLSocketFactory sSLSocketFactory2;
   public final HostnameVerifier hostnameVerifier2;

   private static boolean matchesText(String text, SSLSession sSLSession) {
      return text.equals("authserver.thealtening.com") || text.equals("sessionserver.thealtening.com");
   }

   public void updateState2() {
      this.applySSLSocketFactory(this.sSLSocketFactory2, this.hostnameVerifier2);
   }

   public void updateState() {
      this.applySSLSocketFactory(this.sSLSocketFactory, hostnameVerifier);
   }

   private void applySSLSocketFactory(SSLSocketFactory sSLSocketFactory3, HostnameVerifier hostnameVerifier3) {
      HttpsURLConnection.setDefaultSSLSocketFactory(sSLSocketFactory3);
      HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier3);
   }

   public AuthserverThealteningComManager() {
      SSLContext sSLContext = null;

      try {
         sSLContext = SSLContext.getInstance("SSL");
         sSLContext.init(null, trustManagerArray, new SecureRandom());
      } catch (KeyManagementException | NoSuchAlgorithmException keyManagementException) {
         keyManagementException.printStackTrace();
      }

      this.sSLSocketFactory = sSLContext.getSocketFactory();
      this.sSLSocketFactory2 = HttpsURLConnection.getDefaultSSLSocketFactory();
      this.hostnameVerifier2 = HttpsURLConnection.getDefaultHostnameVerifier();
   }
}
