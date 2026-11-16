package client.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public final class PasswordAuthenticatiAuthenticator extends Authenticator {
   public final String labelText;
   public final String labelText2;

   @Override
   public PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(this.labelText, this.labelText2.toCharArray());
   }

   public PasswordAuthenticatiAuthenticator(String text, String text2) {
      this.labelText = text;
      this.labelText2 = text2;
   }
}
