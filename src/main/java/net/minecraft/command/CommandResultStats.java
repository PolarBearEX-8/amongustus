package net.minecraft.command;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;

public class CommandResultStats {
   private static final int NUM_RESULT_TYPES = CommandResultStats.Type.values().length;
   private static final String[] STRING_RESULT_TYPES = new String[NUM_RESULT_TYPES];
   private String[] entitiesID = STRING_RESULT_TYPES;
   private String[] objectives = STRING_RESULT_TYPES;

   public void readStatsFromNBT(NBTTagCompound tag) {
      if (tag.hasKey("CommandStats", 10)) {
         NBTTagCompound compoundTag = tag.getCompoundTag("CommandStats");

         for (CommandResultStats.Type type : CommandResultStats.Type.values()) {
            String str = type.getTypeName() + "Name";
            String str2 = type.getTypeName() + "Objective";
            if (compoundTag.hasKey(str, 8) && compoundTag.hasKey(str2, 8)) {
               setScoreBoardStat(this, type, compoundTag.getString(str), compoundTag.getString(str2));
            }
         }
      }
   }

   public static void setScoreBoardStat(CommandResultStats commandResultStats, CommandResultStats.Type type, String str, String str2) {
      if (str != null && str.length() != 0 && str2 != null && str2.length() != 0) {
         if (commandResultStats.entitiesID == STRING_RESULT_TYPES || commandResultStats.objectives == STRING_RESULT_TYPES) {
            commandResultStats.entitiesID = new String[NUM_RESULT_TYPES];
            commandResultStats.objectives = new String[NUM_RESULT_TYPES];
         }

         commandResultStats.entitiesID[type.getTypeID()] = str;
         commandResultStats.objectives[type.getTypeID()] = str2;
      } else {
         removeScoreBoardStat(commandResultStats, type);
      }
   }

   public void addAllStats(CommandResultStats commandResultStats) {
      for (CommandResultStats.Type type : CommandResultStats.Type.values()) {
         setScoreBoardStat(this, type, commandResultStats.entitiesID[type.getTypeID()], commandResultStats.objectives[type.getTypeID()]);
      }
   }

   public void setCommandStatScore(ICommandSender commandSender, CommandResultStats.Type type, int n) {
      String str = this.entitiesID[type.getTypeID()];
      if (str != null) {
         CommandResultStats.DefaultCommandSender obj = new CommandResultStats.DefaultCommandSender(this, commandSender);

         String str2;
         try {
            str2 = CommandBase.getEntityName(obj, str);
         } catch (EntityNotFoundException e) {
            return;
         }

         String str3 = this.objectives[type.getTypeID()];
         if (str3 != null) {
            Scoreboard scoreboard = commandSender.getEntityWorld().getScoreboard();
            ScoreObjective objective = scoreboard.getObjective(str3);
            if (objective != null && scoreboard.entityHasObjective(str2, objective)) {
               scoreboard.getValueFromObjective(str2, objective).setScorePoints(n);
            }
         }
      }
   }

   private static void removeScoreBoardStat(CommandResultStats commandResultStats, CommandResultStats.Type type) {
      if (commandResultStats.entitiesID != STRING_RESULT_TYPES && commandResultStats.objectives != STRING_RESULT_TYPES) {
         commandResultStats.entitiesID[type.getTypeID()] = null;
         commandResultStats.objectives[type.getTypeID()] = null;
         boolean flag = true;

         for (CommandResultStats.Type type2 : CommandResultStats.Type.values()) {
            if (commandResultStats.entitiesID[type2.getTypeID()] != null && commandResultStats.objectives[type2.getTypeID()] != null) {
               flag = false;
               break;
            }
         }

         if (flag) {
            commandResultStats.entitiesID = STRING_RESULT_TYPES;
            commandResultStats.objectives = STRING_RESULT_TYPES;
         }
      }
   }

   public void writeStatsToNBT(NBTTagCompound tag) {
      NBTTagCompound tag2 = new NBTTagCompound();

      for (CommandResultStats.Type type : CommandResultStats.Type.values()) {
         String str = this.entitiesID[type.getTypeID()];
         String str2 = this.objectives[type.getTypeID()];
         if (str != null && str2 != null) {
            tag2.setString(type.getTypeName() + "Name", str);
            tag2.setString(type.getTypeName() + "Objective", str2);
         }
      }

      if (!tag2.hasNoTags()) {
         tag.setTag("CommandStats", tag2);
      }
   }


   public static class DefaultCommandSender implements ICommandSender {
      public final ICommandSender sender;
      public final CommandResultStats resultStats;

      @Override
      public Vec3 getPositionVector() {
         return this.sender.getPositionVector();
      }

      @Override
      public void addChatMessage(IChatComponent component) {
         this.sender.addChatMessage(component);
      }

      @Override
      public String getName() {
         return this.sender.getName();
      }

      @Override
      public World getEntityWorld() {
         return this.sender.getEntityWorld();
      }

      @Override
      public IChatComponent getDisplayName() {
         return this.sender.getDisplayName();
      }

      @Override
      public boolean sendCommandFeedback() {
         return this.sender.sendCommandFeedback();
      }

      @Override
      public Entity getCommandSenderEntity() {
         return this.sender.getCommandSenderEntity();
      }

      @Override
      public void setCommandStat(CommandResultStats.Type type, int n) {
         this.sender.setCommandStat(type, n);
      }

      @Override
      public BlockPos getPosition() {
         return this.sender.getPosition();
      }

      @Override
      public boolean canCommandSenderUseCommand(int n, String str) {
         return true;
      }

      public DefaultCommandSender(CommandResultStats commandResultStats, ICommandSender commandSender) {
         this.resultStats = commandResultStats;
         this.sender = commandSender;
      }
   }

   public enum Type {
      SUCCESS_COUNT(0, "SuccessCount"),
      AFFECTED_BLOCKS(1, "AffectedBlocks"),
      AFFECTED_ENTITIES(2, "AffectedEntities"),
      AFFECTED_ITEMS(3, "AffectedItems"),
      QUERY_RESULT(4, "QueryResult");

   public final int typeID;
      public final String typeName;
   private Type(int n, String str) {
         this.typeID = n;
         this.typeName = str;
      }

      public String getTypeName() {
         return this.typeName;
      }

      public static Type getTypeByName(String str) {
         for (Type type : values()) {
            if (type.getTypeName().equals(str)) {
               return type;
            }
         }

         return null;
      }

      public static String[] getTypeNames() {
         String[] strings = new String[values().length];
         int n = 0;

         for (Type type : values()) {
            strings[n++] = type.getTypeName();
         }

         return strings;
      }

      public int getTypeID() {
         return this.typeID;
      }
   }
}
