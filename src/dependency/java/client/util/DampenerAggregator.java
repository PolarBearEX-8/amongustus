package client.util;

import client.event.PreUpdateFilter;
import java.io.InputStream;

public class DampenerAggregator implements PreUpdateFilter {
   @Override
   public String getName() {
      return "(internal)";
   }

   @Override
   public InputStream getResourceAsStream(String text) {
      return DampenerAggregator.class.getResourceAsStream(text);
   }

   @Override
   public void close() {
   }

   @Override
   public boolean matchesText(String text) {
      return false;
   }
}
