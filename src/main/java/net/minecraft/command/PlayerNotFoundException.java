package net.minecraft.command;

public class PlayerNotFoundException extends CommandException {
   public PlayerNotFoundException() {
      this("commands.generic.player.notFound", new Object[0]);
   }

   public PlayerNotFoundException(String str, Object[] array) {
      super(str, array);
   }
}
