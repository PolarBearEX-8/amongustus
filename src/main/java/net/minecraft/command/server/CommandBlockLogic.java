package net.minecraft.command.server;

import java.util.concurrent.Callable;
import io.netty.buffer.ByteBuf;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;

public abstract class CommandBlockLogic implements ICommandSender {
   private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("HH:mm:ss");
   private int successCount;
   private boolean trackOutput = true;
   private IChatComponent lastOutput = null;
   private String commandStored = "";
   private String customName = "@";
   private final CommandResultStats resultStats = new CommandResultStats();

   public CommandResultStats getCommandResultStats() {
      return this.resultStats;
   }

   @Override
   public boolean sendCommandFeedback() {
      MinecraftServer server = MinecraftServer.getServer();
      return server == null || !server.isAnvilFileSet() || server.worldServers[0].getGameRules().getBoolean("commandBlockOutput");
   }

   public abstract void updateCommand();

   @Override
   public void addChatMessage(IChatComponent component) {
      if (this.trackOutput && this.getEntityWorld() != null && !this.getEntityWorld().isRemote) {
         this.lastOutput = new ChatComponentText("[" + timestampFormat.format(new Date()) + "] ").appendSibling(component);
         this.updateCommand();
      }
   }

   public abstract void fillInInfo(ByteBuf buf);

   @Override
   public void setCommandStat(CommandResultStats.Type type, int n) {
      this.resultStats.setCommandStatScore(this, type, n);
   }

   public String getCommand() {
      return this.commandStored;
   }

   @Override
   public String getName() {
      return this.customName;
   }

   public void writeDataToNBT(NBTTagCompound tag) {
      tag.setString("Command", this.commandStored);
      tag.setInteger("SuccessCount", this.successCount);
      tag.setString("CustomName", this.customName);
      tag.setBoolean("TrackOutput", this.trackOutput);
      if (this.lastOutput != null && this.trackOutput) {
         tag.setString("LastOutput", IChatComponent.Serializer.componentToJson(this.lastOutput));
      }

      this.resultStats.writeStatsToNBT(tag);
   }

   public boolean shouldTrackOutput() {
      return this.trackOutput;
   }

   @Override
   public IChatComponent getDisplayName() {
      return new ChatComponentText(this.getName());
   }

   public int getSuccessCount() {
      return this.successCount;
   }

   public void trigger(World world) {
      if (world.isRemote) {
         this.successCount = 0;
      }

      MinecraftServer server = MinecraftServer.getServer();
      if (server != null && server.isAnvilFileSet() && server.isCommandBlockEnabled()) {
         ICommandManager commandManager = server.getCommandManager();

         try {
            this.lastOutput = null;
            this.successCount = commandManager.executeCommand(this, this.commandStored);
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Executing command block");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("Command to be executed");
            crashReportCategory.addCrashSectionCallable("Command", new CommandBlockLogic.ValueCallable(this));
            crashReportCategory.addCrashSectionCallable("Name", new CommandBlockLogic.ValueCallable2(this));
            throw new ReportedException(crashReport);
         }
      } else {
         this.successCount = 0;
      }
   }

   public boolean tryOpenEditCommandBlock(EntityPlayer player) {
      if (!player.capabilities.isCreativeMode) {
         return false;
      } else {
         if (player.getEntityWorld().isRemote) {
            player.openEditCommandBlock(this);
         }

         return true;
      }
   }

   public IChatComponent getLastOutput() {
      return this.lastOutput;
   }

   public void setLastOutput(IChatComponent component) {
      this.lastOutput = component;
   }

   public void setTrackOutput(boolean flag) {
      this.trackOutput = flag;
   }

   @Override
   public boolean canCommandSenderUseCommand(int n, String str) {
      return n <= 2;
   }

   public abstract int getCommandBlockType();

   public void readDataFromNBT(NBTTagCompound tag) {
      this.commandStored = tag.getString("Command");
      this.successCount = tag.getInteger("SuccessCount");
      if (tag.hasKey("CustomName", 8)) {
         this.customName = tag.getString("CustomName");
      }

      if (tag.hasKey("TrackOutput", 1)) {
         this.trackOutput = tag.getBoolean("TrackOutput");
      }

      if (tag.hasKey("LastOutput", 8) && this.trackOutput) {
         this.lastOutput = IChatComponent.Serializer.jsonToComponent(tag.getString("LastOutput"));
      }

      this.resultStats.readStatsFromNBT(tag);
   }

   public void setCommand(String str) {
      this.commandStored = str;
      this.successCount = 0;
   }

   public void setName(String str) {
      this.customName = str;
   }


   public static class ValueCallable implements Callable {
      public final CommandBlockLogic commandBlockLogic;

      public String callAsString() throws Exception {
         return this.commandBlockLogic.getCommand();
      }

      public ValueCallable(CommandBlockLogic commandBlockLogic2) {
         this.commandBlockLogic = commandBlockLogic2;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class ValueCallable2 implements Callable {
      public final CommandBlockLogic commandBlockLogic;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable2(CommandBlockLogic commandBlockLogic2) {
         this.commandBlockLogic = commandBlockLogic2;
      }

      public String callAsString() throws Exception {
         return this.commandBlockLogic.getName();
      }
   }
}
