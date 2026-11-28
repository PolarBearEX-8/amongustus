package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class CommandDifficulty extends CommandBase {

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length <= 0) {
         throw new WrongUsageException("commands.difficulty.usage", new Object[0]);
      } else {
         EnumDifficulty difficultyFromCommand = this.getDifficultyFromCommand(strings[0]);
         MinecraftServer.getServer().setDifficultyForAllWorlds(difficultyFromCommand);
         notifyOperators(commandSender, this, "commands.difficulty.success", new Object[]{new ChatComponentTranslation(difficultyFromCommand.getDifficultyResourceKey(), new Object[0])});
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, new String[]{"peaceful", "easy", "normal", "hard"}) : null;
   }

   public EnumDifficulty getDifficultyFromCommand(String str) throws CommandException, NumberInvalidException {
      return str.equalsIgnoreCase("peaceful") || str.equalsIgnoreCase("p")
         ? EnumDifficulty.PEACEFUL
         : (
            str.equalsIgnoreCase("easy") || str.equalsIgnoreCase("e")
               ? EnumDifficulty.EASY
               : (
                  !str.equalsIgnoreCase("normal") && !str.equalsIgnoreCase("n")
                     ? (
                        !str.equalsIgnoreCase("hard") && !str.equalsIgnoreCase("h")
                           ? EnumDifficulty.getDifficultyEnum(parseInt(str, 0, 3))
                           : EnumDifficulty.HARD
                     )
                     : EnumDifficulty.NORMAL
               )
         );
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "difficulty";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.difficulty.usage";
   }
}
