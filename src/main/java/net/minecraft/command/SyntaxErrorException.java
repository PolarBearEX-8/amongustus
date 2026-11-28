package net.minecraft.command;

public class SyntaxErrorException extends CommandException {
   public SyntaxErrorException() {
      this("commands.generic.snytax", new Object[0]);
   }

   public SyntaxErrorException(String str, Object[] array) {
      super(str, array);
   }
}
