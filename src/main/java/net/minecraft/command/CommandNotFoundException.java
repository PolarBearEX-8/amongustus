package net.minecraft.command;

public class CommandNotFoundException extends CommandException {
   public CommandNotFoundException() {
      this("commands.generic.notFound", new Object[0]);
   }

   public CommandNotFoundException(String str, Object[] array) {
      super(str, array);
   }
}
