package net.minecraft.command;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandExecuteAt extends CommandBase {

   @Override
   public String getCommandName() {
      return "execute";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.execute.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 5) {
         throw new WrongUsageException("commands.execute.usage", new Object[0]);
      } else {
         Entity entity = getEntity(commandSender, strings[0], Entity.class);
         double d = parseDouble(entity.posX, strings[1], false);
         double d2 = parseDouble(entity.posY, strings[2], false);
         double d3 = parseDouble(entity.posZ, strings[3], false);
         BlockPos pos = new BlockPos(d, d2, d3);
         byte b = 4;
         if ("detect".equals(strings[4]) && strings.length > 10) {
            World entityWorld = entity.getEntityWorld();
            double d4 = parseDouble(d, strings[5], false);
            double d5 = parseDouble(d2, strings[6], false);
            double d6 = parseDouble(d3, strings[7], false);
            Block blockByText = getBlockByText(commandSender, strings[8]);
            int n = parseInt(strings[9], -1, 15);
            IBlockState blockState = entityWorld.getBlockState(new BlockPos(d4, d5, d6));
            if (blockState.getBlock() != blockByText || n >= 0 && blockState.getBlock().getMetaFromState(blockState) != n) {
               throw new CommandException("commands.execute.failed", new Object[]{"detect", entity.getName()});
            }

            b = 10;
         }

         String str = buildString(strings, b);
         CommandExecuteAt.DefaultCommandSender obj = new CommandExecuteAt.DefaultCommandSender(this, entity, commandSender, pos, d, d2, d3);
         ICommandManager commandManager = MinecraftServer.getServer().getCommandManager();

         try {
            if (commandManager.executeCommand(obj, str) < 1) {
               throw new CommandException("commands.execute.allInvocationsFailed", new Object[]{str});
            }
         } catch (Throwable e) {
            throw new CommandException("commands.execute.failed", new Object[]{str, entity.getName()});
         }
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames())
         : (
            strings.length > 1 && strings.length <= 4
               ? getTabCompletionCoordinate(strings, 1, pos)
               : (
                  strings.length > 5 && strings.length <= 8 && "detect".equals(strings[4])
                     ? getTabCompletionCoordinate(strings, 5, pos)
                     : (strings.length == 9 && "detect".equals(strings[4]) ? getListOfStringsMatchingLastWord(strings, Block.blockRegistry.getKeys()) : null)
               )
         );
   }


   public static class DefaultCommandSender implements ICommandSender {
      public final Entity commandSenderEntity;
      public final ICommandSender sender;
      public final BlockPos position;
      public final double posX;
      public final double posY;
      public final double posZ;
      public final CommandExecuteAt commandExecuteAt;

      @Override
      public World getEntityWorld() {
         return this.commandSenderEntity.entityWorld;
      }

      @Override
      public BlockPos getPosition() {
         return this.position;
      }

      @Override
      public String getName() {
         return this.commandSenderEntity.getName();
      }

      @Override
      public boolean canCommandSenderUseCommand(int n, String str) {
         return this.sender.canCommandSenderUseCommand(n, str);
      }

      @Override
      public void setCommandStat(CommandResultStats.Type type, int n) {
         this.commandSenderEntity.setCommandStat(type, n);
      }

      @Override
      public boolean sendCommandFeedback() {
         MinecraftServer server = MinecraftServer.getServer();
         return server == null || server.worldServers[0].getGameRules().getBoolean("commandBlockOutput");
      }

      @Override
      public IChatComponent getDisplayName() {
         return this.commandSenderEntity.getDisplayName();
      }

      @Override
      public Vec3 getPositionVector() {
         return new Vec3(this.posX, this.posY, this.posZ);
      }

      @Override
      public void addChatMessage(IChatComponent component) {
         this.sender.addChatMessage(component);
      }

      @Override
      public Entity getCommandSenderEntity() {
         return this.commandSenderEntity;
      }

      public DefaultCommandSender(CommandExecuteAt commandExecuteAt2, Entity entity, ICommandSender commandSender, BlockPos pos, double d, double d2, double d3) {
         this.commandExecuteAt = commandExecuteAt2;
         this.commandSenderEntity = entity;
         this.sender = commandSender;
         this.position = pos;
         this.posX = d;
         this.posY = d2;
         this.posZ = d3;
      }
   }
}
