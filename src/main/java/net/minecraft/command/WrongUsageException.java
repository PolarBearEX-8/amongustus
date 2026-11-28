package net.minecraft.command;

public class WrongUsageException extends SyntaxErrorException {
   public WrongUsageException(String str, Object[] array) {
      super(str, array);
   }
}
