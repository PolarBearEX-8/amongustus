package net.minecraft.command;

public class CommandException extends Exception {
   private final Object[] errorObjects;

   public Object[] getErrorObjects() {
      return this.errorObjects;
   }

   public CommandException(String str, Object[] array) {
      super(str);
      this.errorObjects = array;
   }
}
