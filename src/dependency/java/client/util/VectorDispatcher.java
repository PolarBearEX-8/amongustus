package client.util;

import client.setting.CombatNumberProperty;
import io.netty.buffer.ByteBuf;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public abstract class VectorDispatcher implements ICommandSender {
   public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
   public int count;
   public boolean enabled = true;
   public IChatComponent iChatComponent = null;
   public String labelText = "";
   public String labelText2 = "@";
   public final CommandStatsManager commandStatsManager = new CommandStatsManager();

   public void setIChatComponent(IChatComponent iChatComponent2) {
      this.iChatComponent = iChatComponent2;
   }

   @Override
   public IChatComponent getChatComponent() {
      return new ChatComponentText(this.getText());
   }

   @Override
   public String getText() {
      return this.labelText2;
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public abstract void updateState();

   public void setLabelText2(String labelText3) {
      this.labelText2 = labelText3;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public boolean isActive() {
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      return vanillaRunnable == null || !vanillaRunnable.onUpdate() || vanillaRunnable.worldServerArray[0].getDoFireTickManager().matchesText("commandBlockOutput");
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent2) {
      if (this.enabled && this.getWorldObj() != null && !this.getWorldObj().state) {
         this.iChatComponent = new ChatComponentText("[" + simpleDateFormat.format(new Date()) + "] ").appendChatComponent(iChatComponent2);
         this.updateState();
      }
   }

   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Command", this.labelText);
      tagCompound.put("SuccessCount", this.count);
      tagCompound.put("CustomName", this.labelText2);
      tagCompound.applyText("TrackOutput", this.enabled);
      if (this.iChatComponent != null && this.enabled) {
         tagCompound.put("LastOutput", ChatComponentSerializer.toJson(this.iChatComponent));
      }

      this.commandStatsManager.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }

   public abstract int getCountAsIndex();

   public int getCount() {
      return this.count;
   }

   public abstract void applyByteBuf(ByteBuf byteBuf);

   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      if (!player.invulnerableManager.enabled2) {
         return false;
      } else {
         if (player.getWorldObj().state) {
            player.applyVectorDispatcher(this);
         }

         return true;
      }
   }

   @Override
   public void applyCombatNumberProperty(CombatNumberProperty combatNumberProperty, int count2) {
      this.commandStatsManager.applyICommandSender(this, combatNumberProperty, count2);
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.labelText = tagCompound.resolveText("Command");
      this.count = tagCompound.computeCount("SuccessCount");
      if (tagCompound.matchesText("CustomName", 8)) {
         this.labelText2 = tagCompound.resolveText("CustomName");
      }

      if (tagCompound.matchesText("TrackOutput", 1)) {
         this.enabled = tagCompound.matchesText("TrackOutput");
      }

      if (tagCompound.matchesText("LastOutput", 8) && this.enabled) {
         this.iChatComponent = ChatComponentSerializer.parseChatComponent(tagCompound.resolveText("LastOutput"));
      }

      this.commandStatsManager.applyNBTTagCompound(tagCompound);
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   @Override
   public boolean matchesText(int count2, String text) {
      return count2 <= 2;
   }

   public void applyText(String text) {
      this.labelText = text;
      this.count = 0;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void applyWorldByWorld(World world) {
      if (world.state) {
         this.count = 0;
      }

      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      if (vanillaRunnable != null && vanillaRunnable.onUpdate() && vanillaRunnable.isActive7()) {
         ICommandManager iCommandManager = vanillaRunnable.getICommandManager();

         try {
            this.iChatComponent = null;
            this.count = iCommandManager.computeCount(this, this.labelText);
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Executing command block");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Command to be executed");
            crashReportCategory.applyText("Command", new VectorDispatcherTaskImpl(this));
            crashReportCategory.applyText("Name", new VectorDispatcherTask(this));
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      } else {
         this.count = 0;
      }
   }

   public CommandStatsManager getCommandStatsManager() {
      return this.commandStatsManager;
   }
}
