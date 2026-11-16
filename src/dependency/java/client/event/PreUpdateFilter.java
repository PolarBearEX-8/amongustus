package client.event;

import java.io.InputStream;

public interface PreUpdateFilter {
   void close();

   boolean matchesText(String text);

   String getName();

   InputStream getResourceAsStream(String text);
}
