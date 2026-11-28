package net.augustus.changelog;

public class ChangelogReadException extends RuntimeException {
   public ChangelogReadException(long l, String str) {
      super("Can't read changelog file: " + str);
   }
}
