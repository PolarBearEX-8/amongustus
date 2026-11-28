package net.minecraft.command;

import net.minecraft.block.material.Material;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CommandSpreadPlayers extends CommandBase {

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length >= 1 && strings.length <= 2 ? getTabCompletionCoordinateXZ(strings, 0, pos) : null;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 6) {
         throw new WrongUsageException("commands.spreadplayers.usage", new Object[0]);
      } else {
         int n = 0;
         BlockPos position = commandSender.getPosition();
         double d = parseDouble((double)position.getX(), strings[n++], true);
         double d2 = parseDouble((double)position.getZ(), strings[n++], true);
         double d3 = parseDouble(strings[n++], 0.0);
         double d4 = parseDouble(strings[n++], d3 + 1.0);
         boolean flag = parseBoolean(strings[n++]);
         ArrayList list = Lists.newArrayList();

         while (n < strings.length) {
            String str = strings[n++];
            if (PlayerSelector.hasArguments(str)) {
               List list2 = PlayerSelector.matchEntities(commandSender, str, Entity.class);
               if (list2.size() == 0) {
                  throw new EntityNotFoundException();
               }

               list.addAll(list2);
            } else {
               EntityPlayerMP playerByUsername = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(str);
               if (playerByUsername == null) {
                  throw new PlayerNotFoundException();
               }

               list.add(playerByUsername);
            }
         }

         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ENTITIES, list.size());
         if (list.isEmpty()) {
            throw new EntityNotFoundException();
         } else {
            commandSender.addChatMessage(
               new ChatComponentTranslation(
                  "commands.spreadplayers.spreading." + (flag ? "teams" : "players"), new Object[]{list.size(), d4, d, d2, d3}
               )
            );
            this.spread(commandSender, list, new CommandSpreadPlayers.Position(d, d2), d3, d4, ((Entity)list.get(0)).entityWorld, flag);
         }
      }
   }

   private void spread(ICommandSender commandSender, List list, CommandSpreadPlayers.Position position, double d, double d2, World world, boolean flag) throws CommandException {
      Random rand = new Random();
      double d3 = position.x - d2;
      double d4 = position.z - d2;
      double d5 = position.x + d2;
      double d6 = position.z + d2;
      CommandSpreadPlayers.Position[] positions = this.createInitialPositions(rand, flag ? this.getNumberOfTeams(list) : list.size(), d3, d4, d5, d6);
      int n = this.spreadPositions(position, d, world, rand, d3, d4, d5, d6, positions, flag);
      double d7 = this.setPlayerPositions(list, world, positions, flag);
      notifyOperators(commandSender, this, "commands.spreadplayers.success." + (flag ? "teams" : "players"), new Object[]{positions.length, position.x, position.z});
      if (positions.length > 1) {
         commandSender.addChatMessage(
            new ChatComponentTranslation("commands.spreadplayers.info." + (flag ? "teams" : "players"), new Object[]{String.format("%.2f", d7), n})
         );
      }
   }

   private int getNumberOfTeams(List list) {
      HashSet set = Sets.newHashSet();

      for (Entity entity : (Iterable<Entity>)(list)) {
         if (entity instanceof EntityPlayer) {
            set.add(((EntityPlayer)entity).getTeam());
         } else {
            set.add((Team)null);
         }
      }

      return set.size();
   }

   @Override
   public String getCommandName() {
      return "spreadplayers";
   }

   private double setPlayerPositions(List list, World world, CommandSpreadPlayers.Position[] positions, boolean flag) {
      double d = 0.0;
      int n = 0;
      HashMap map = Maps.newHashMap();

      for (int i = 0; i < list.size(); i++) {
         Entity entity = (Entity)list.get(i);
         CommandSpreadPlayers.Position position;
         if (flag) {
            Team team = entity instanceof EntityPlayer ? ((EntityPlayer)entity).getTeam() : null;
            if (!map.containsKey(team)) {
               map.put(team, positions[n++]);
            }

            position = (CommandSpreadPlayers.Position)map.get(team);
         } else {
            position = positions[n++];
         }

         entity.setPositionAndUpdate(
            (double)((float)MathHelper.floor_double(position.x) + 0.5F), (double)position.getSpawnY(world), (double)MathHelper.floor_double(position.z) + 0.5
         );
         double maxvalue = Double.MAX_VALUE;

         for (int j = 0; j < positions.length; j++) {
            if (position != positions[j]) {
               maxvalue = Math.min(position.getDistance(positions[j]), maxvalue);
            }
         }

         d += maxvalue;
      }

      return d / (double)list.size();
   }

   private CommandSpreadPlayers.Position[] createInitialPositions(Random rand, int n, double d, double d2, double d3, double d4) {
      CommandSpreadPlayers.Position[] positions = new CommandSpreadPlayers.Position[n];

      for (int i = 0; i < positions.length; i++) {
         CommandSpreadPlayers.Position position = new CommandSpreadPlayers.Position();
         position.randomize(rand, d, d2, d3, d4);
         positions[i] = position;
      }

      return positions;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.spreadplayers.usage";
   }

   private int spreadPositions(
      CommandSpreadPlayers.Position position,
      double d,
      World world,
      Random rand,
      double d2,
      double d3,
      double d4,
      double d5,
      CommandSpreadPlayers.Position[] positions,
      boolean flag
   ) throws CommandException {
      boolean flag2 = true;
      double maxvalue = Float.MAX_VALUE;

      int n;
      for (n = 0; n < 10000 && flag2; n++) {
         flag2 = false;
         maxvalue = Float.MAX_VALUE;

         for (int i = 0; i < positions.length; i++) {
            CommandSpreadPlayers.Position position2 = positions[i];
            int n2 = 0;
            CommandSpreadPlayers.Position position3 = new CommandSpreadPlayers.Position();

            for (int j = 0; j < positions.length; j++) {
               if (i != j) {
                  CommandSpreadPlayers.Position position4 = positions[j];
                  double distance = position2.getDistance(position4);
                  maxvalue = Math.min(distance, maxvalue);
                  if (distance < d) {
                     n2++;
                     position3.x = position3.x + (position4.x - position2.x);
                     position3.z = position3.z + (position4.z - position2.z);
                  }
               }
            }

            if (n2 > 0) {
               position3.x /= (double)n2;
               position3.z /= (double)n2;
               if (((double)position3.getLength()) > 0.0) {
                  position3.normalize();
                  position2.moveAway(position3);
               } else {
                  position2.randomize(rand, d2, d3, d4, d5);
               }

               flag2 = true;
            }

            if (position2.clamp(d2, d3, d4, d5)) {
               flag2 = true;
            }
         }

         if (!flag2) {
            for (CommandSpreadPlayers.Position position5 : positions) {
               if (!position5.isSafe(world)) {
                  position5.randomize(rand, d2, d3, d4, d5);
                  flag2 = true;
               }
            }
         }
      }

      if (n >= 10000) {
         throw new CommandException(
            "commands.spreadplayers.failure." + (flag ? "teams" : "players"), new Object[]{positions.length, position.x, position.z, String.format("%.2f", maxvalue)}
         );
      } else {
         return n;
      }
   }


   public static class Position {
      public double x;
      public double z;

      public Position() {
      }

      public float getLength() {
         return MathHelper.sqrt_double(this.x * this.x + this.z * this.z);
      }

      public int getSpawnY(World world) {
         BlockPos pos = new BlockPos(this.x, 256.0, this.z);

         while (pos.getY() > 0) {
            pos = pos.down();
            if (world.getBlockState(pos).getBlock().getMaterial() != Material.air) {
               return pos.getY() + 1;
            }
         }

         return 257;
      }

      public boolean isSafe(World world) {
         BlockPos pos = new BlockPos(this.x, 256.0, this.z);

         while (pos.getY() > 0) {
            pos = pos.down();
            Material material = world.getBlockState(pos).getBlock().getMaterial();
            if (material != Material.air) {
               return !material.isLiquid() && material != Material.fire;
            }
         }

         return false;
      }

      public double getDistance(Position position) {
         double d = this.x - position.x;
         double d2 = this.z - position.z;
         return Math.sqrt(d * d + d2 * d2);
      }

      public void normalize() {
         double length = (double)this.getLength();
         this.x /= length;
         this.z /= length;
      }

      public void moveAway(Position position) {
         this.x = this.x - position.x;
         this.z = this.z - position.z;
      }

      public boolean clamp(double d, double d2, double d3, double d4) {
         boolean flag = false;
         if (this.x < d) {
            this.x = d;
            flag = true;
         } else if (this.x > d3) {
            this.x = d3;
            flag = true;
         }

         if (this.z < d2) {
            this.z = d2;
            flag = true;
         } else if (this.z > d4) {
            this.z = d4;
            flag = true;
         }

         return flag;
      }

      public Position(double d, double d2) {
         this.x = d;
         this.z = d2;
      }

      public void randomize(Random rand, double d, double d2, double d3, double d4) {
         this.x = MathHelper.getRandomDoubleInRange(rand, d, d3);
         this.z = MathHelper.getRandomDoubleInRange(rand, d2, d4);
      }
   }
}
