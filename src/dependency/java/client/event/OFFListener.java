package client.event;

import java.io.InputStream;

public class OFFListener implements PreUpdateFilter {
   @Override
   public boolean matchesText(String text) {
      return false;
   }

   @Override
   public void close() {
   }

   @Override
   public String getName() {
      return "OFF";
   }

   @Override
   public InputStream getResourceAsStream(String text) {
      return null;
   }
}
