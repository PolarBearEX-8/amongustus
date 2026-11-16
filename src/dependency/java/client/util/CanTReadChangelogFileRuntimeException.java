package client.util;

public class CanTReadChangelogFileRuntimeException extends RuntimeException {
   public CanTReadChangelogFileRuntimeException(String text) {
      super("Can't read changelog file: " + text);
   }
}
