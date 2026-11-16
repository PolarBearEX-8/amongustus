package client.util;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

public class InvalidProxyConfigLineManager {
   public final String labelText3;
   public final int count;
   public final String labelText;
   public final String labelText2;
   public final VectorBridge vectorBridge;

   public boolean isEmpty() {
      return this.labelText3 != null && !this.labelText3.isEmpty();
   }

   private static boolean isEmpty(String text) {
      return !text.isEmpty();
   }

   public static Optional resolveOptional(String text, byte byteValue) {
      File file = new File(text);
      if (!file.exists()) {
         return Optional.empty();
      } else {
         try {
            return Files.lines(file.toPath())
               .map(String::trim)
               .filter(InvalidProxyConfigLineManager::isEmpty)
               .filter(InvalidProxyConfigLineManager::startsWith)
               .findFirst()
               .map(InvalidProxyConfigLineManager::resolveInvalidProxyConfigLineManage);
         } catch (Exception exception) {
            exception.printStackTrace();
            return Optional.empty();
         }
      }
   }

   public boolean isEmptyAsFlag() {
      return this.labelText2 != null && !this.labelText2.isEmpty();
   }

   public static InvalidProxyConfigLineManager resolveInvalidProxyConfigLineManage(String text) {
      String[] textArray = text.split(":");
      if (textArray.length < 3) {
         throw new IllegalArgumentException("Invalid proxy config line: " + text);
      } else {
         String text2 = textArray[0].toLowerCase();
         String text3 = textArray[1];
         int count2 = Integer.parseInt(textArray[2]);
         if ("socks5".equalsIgnoreCase(text2)) {
            if (textArray.length == 3) {
               return new InvalidProxyConfigLineManager(VectorBridge.SOCKS5, text3, count2, null, null);
            } else if (textArray.length == 5) {
               return new InvalidProxyConfigLineManager(VectorBridge.SOCKS5, text3, count2, textArray[3], textArray[4]);
            } else {
               throw new IllegalArgumentException("Invalid SOCKS5 config: " + text);
            }
         } else if ("socks4".equalsIgnoreCase(text2)) {
            if (textArray.length == 3) {
               return new InvalidProxyConfigLineManager(VectorBridge.SOCKS4, text3, count2, null, null);
            } else if (textArray.length == 4) {
               return new InvalidProxyConfigLineManager(VectorBridge.SOCKS4, text3, count2, textArray[3], null);
            } else {
               throw new IllegalArgumentException("Invalid SOCKS4 config: " + text);
            }
         } else {
            throw new IllegalArgumentException("Unknown proxy type: " + text2);
         }
      }
   }

   private static boolean startsWith(String text) {
      return !text.startsWith("#");
   }

   private InvalidProxyConfigLineManager(VectorBridge vectorBridge2, String text, int count2, String text2, String text3) {
      this.vectorBridge = vectorBridge2;
      this.labelText = text;
      this.count = count2;
      this.labelText3 = text2;
      this.labelText2 = text3;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.vectorBridge.name()).append(":").append(this.labelText).append(":").append(this.count);
      if (this.isEmpty()) {
         builder.append(":").append(this.labelText3);
      }

      if (this.isEmptyAsFlag()) {
         builder.append(":").append(this.labelText2);
      }

      return builder.toString();
   }
}
