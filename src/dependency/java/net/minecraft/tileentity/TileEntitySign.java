package net.minecraft.tileentity;

import client.setting.ClientSettingsOpenUrlType;
import client.util.AngleMonitor;
import client.util.AngleWatcher;
import client.util.ClickEventActionManager;
import client.util.CommandStatsManager;
import client.util.DampenerValidator;
import client.util.InternalExceptionAdapter;
import client.util.Packet;
import client.util.StyleHasParentManager;
import client.util.VanillaRunnable;
import com.google.gson.JsonParseException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TileEntitySign extends TileEntity {
   public final IChatComponent[] iChatComponentArray = new IChatComponent[]{
      new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText("")
   };
   public int count2 = -1;
   public boolean enabled2 = true;
   public EntityPlayer entityPlayer;
   public final CommandStatsManager commandStatsManager = new CommandStatsManager();

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public static CommandStatsManager resolveCommandStatsManager(TileEntitySign tileEntitySign) {
      return tileEntitySign.commandStatsManager;
   }

   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      DampenerValidator dampenerValidator = new DampenerValidator(this, player);

      for (int index = 0; index < this.iChatComponentArray.length; index++) {
         StyleHasParentManager styleHasParentManager = this.iChatComponentArray[index] == null ? null : this.iChatComponentArray[index].getStyleHasParentManager();
         if (styleHasParentManager != null && styleHasParentManager.getClickEventActionManager() != null) {
            ClickEventActionManager clickEventActionManager = styleHasParentManager.getClickEventActionManager();
            if (clickEventActionManager.getClientSettingsOpenUrlType() == ClientSettingsOpenUrlType.RUN_COMMAND) {
               VanillaRunnable.getVanillaRunnable().getICommandManager().computeCount(dampenerValidator, clickEventActionManager.getLabelText());
            }
         }
      }

      return true;
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   public CommandStatsManager getCommandStatsManager() {
      return this.commandStatsManager;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      this.enabled2 = false;
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      AngleWatcher angleWatcher = new AngleWatcher(this);

      for (int index = 0; index < 4; index++) {
         String text = tagCompound.resolveText("Text" + (index + 1));

         try {
            IChatComponent iChatComponent = ChatComponentSerializer.parseChatComponent(text);

            try {
               this.iChatComponentArray[index] = AngleMonitor.resolveIChatComponentByICommandSender(angleWatcher, iChatComponent, (Entity)null);
            } catch (InternalExceptionAdapter exception) {
               this.iChatComponentArray[index] = iChatComponent;
            }
         } catch (JsonParseException jsonParseException) {
            this.iChatComponentArray[index] = new ChatComponentText(text);
         }
      }

      this.commandStatsManager.applyNBTTagCompound(tagCompound);
   }

   public void setEntityPlayer(EntityPlayer entityPlayer2) {
      this.entityPlayer = entityPlayer2;
   }

   public void setEnabled(boolean enabled) {
      this.enabled2 = enabled;
      if (!enabled) {
         this.entityPlayer = null;
      }
   }

   public EntityPlayer getEntityPlayerAsEntityPlayer() {
      return this.entityPlayer;
   }

   @Override
   public Packet getPacket() {
      IChatComponent[] iChatComponentArray2 = new IChatComponent[4];
      System.arraycopy(this.iChatComponentArray, 0, iChatComponentArray2, 0, 4);
      return new S33PacketUpdateSign(this.world, this.blockPos, iChatComponentArray2);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);

      for (int index = 0; index < 4; index++) {
         String text = ChatComponentSerializer.toJson(this.iChatComponentArray[index]);
         tagCompound.put("Text" + (index + 1), text);
      }

      this.commandStatsManager.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }
}
