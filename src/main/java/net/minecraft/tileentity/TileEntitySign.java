package net.minecraft.tileentity;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import com.google.gson.JsonParseException;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class TileEntitySign extends TileEntity {
   public final IChatComponent[] signText = new IChatComponent[]{
      new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText("")
   };
   public int lineBeingEdited = -1;
   private boolean isEditable = true;
   private EntityPlayer player;
   private final CommandResultStats stats = new CommandResultStats();

   @Override
   public boolean onlyOpsCanSetNbt() {
      return true;
   }

   public void setEditable(boolean flag) {
      this.isEditable = flag;
      if (!flag) {
         this.player = null;
      }
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);

      for (int i = 0; i < 4; i++) {
         String str = IChatComponent.Serializer.componentToJson(this.signText[i]);
         tag.setString("Text" + (i + 1), str);
      }

      this.stats.writeStatsToNBT(tag);
   }

   public CommandResultStats getStats() {
      return this.stats;
   }

   public boolean executeCommand(EntityPlayer player2) {
      TileEntitySign.DefaultCommandSender2 obj = new TileEntitySign.DefaultCommandSender2(this, player2);

      for (int i = 0; i < this.signText.length; i++) {
         ChatStyle chatStyle = this.signText[i] == null ? null : this.signText[i].getChatStyle();
         if (chatStyle != null && chatStyle.getChatClickEvent() != null) {
            ClickEvent chatClickEvent = chatStyle.getChatClickEvent();
            if (chatClickEvent.getAction() == ClickEvent.Action.RUN_COMMAND) {
               MinecraftServer.getServer().getCommandManager().executeCommand(obj, chatClickEvent.getValue());
            }
         }
      }

      return true;
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      this.isEditable = false;
      super.readFromNBT(tag);
      TileEntitySign.DefaultCommandSender obj = new TileEntitySign.DefaultCommandSender(this);

      for (int i = 0; i < 4; i++) {
         String string = tag.getString("Text" + (i + 1));

         try {
            IChatComponent component = IChatComponent.Serializer.jsonToComponent(string);

            try {
               this.signText[i] = ChatComponentProcessor.processComponent(obj, component, (Entity)null);
            } catch (CommandException e) {
               this.signText[i] = component;
            }
         } catch (JsonParseException e2) {
            this.signText[i] = new ChatComponentText(string);
         }
      }

      this.stats.readStatsFromNBT(tag);
   }

   public void setPlayer(EntityPlayer player2) {
      this.player = player2;
   }

   public static CommandResultStats accessStats(TileEntitySign tileEntitySign) {
      return tileEntitySign.stats;
   }

   public boolean getIsEditable() {
      return this.isEditable;
   }

   @Override
   public Packet getDescriptionPacket() {
      IChatComponent[] components = new IChatComponent[4];
      System.arraycopy(this.signText, 0, components, 0, 4);
      return new S33PacketUpdateSign(this.worldObj, this.pos, components);
   }

   public EntityPlayer getPlayer() {
      return this.player;
   }


   public static class DefaultCommandSender implements ICommandSender {
      public final TileEntitySign tileEntity;

      @Override
      public Entity getCommandSenderEntity() {
         return null;
      }

      @Override
      public BlockPos getPosition() {
         return this.tileEntity.pos;
      }

      @Override
      public World getEntityWorld() {
         return this.tileEntity.worldObj;
      }

      @Override
      public boolean sendCommandFeedback() {
         return false;
      }

      public DefaultCommandSender(TileEntitySign tileEntitySign) {
         this.tileEntity = tileEntitySign;
      }

      @Override
      public boolean canCommandSenderUseCommand(int n, String str) {
         return true;
      }

      @Override
      public void addChatMessage(IChatComponent component) {
      }

      @Override
      public void setCommandStat(CommandResultStats.Type type, int n) {
      }

      @Override
      public Vec3 getPositionVector() {
         return new Vec3((double)this.tileEntity.pos.getX() + 0.5, (double)this.tileEntity.pos.getY() + 0.5, (double)this.tileEntity.pos.getZ() + 0.5);
      }

      @Override
      public IChatComponent getDisplayName() {
         return new ChatComponentText(this.getName());
      }

      @Override
      public String getName() {
         return "Sign";
      }
   }

   public static class DefaultCommandSender2 implements ICommandSender {
      public final EntityPlayer commandSenderEntity;
      public final TileEntitySign tileEntity;

      public DefaultCommandSender2(TileEntitySign tileEntitySign, EntityPlayer player) {
         this.tileEntity = tileEntitySign;
         this.commandSenderEntity = player;
      }

      @Override
      public String getName() {
         return this.commandSenderEntity.getName();
      }

      @Override
      public void setCommandStat(CommandResultStats.Type type, int n) {
         TileEntitySign.accessStats(this.tileEntity).setCommandStatScore(this, type, n);
      }

      @Override
      public Entity getCommandSenderEntity() {
         return this.commandSenderEntity;
      }

      @Override
      public void addChatMessage(IChatComponent component) {
      }

      @Override
      public Vec3 getPositionVector() {
         return new Vec3((double)this.tileEntity.pos.getX() + 0.5, (double)this.tileEntity.pos.getY() + 0.5, (double)this.tileEntity.pos.getZ() + 0.5);
      }

      @Override
      public IChatComponent getDisplayName() {
         return this.commandSenderEntity.getDisplayName();
      }

      @Override
      public BlockPos getPosition() {
         return this.tileEntity.pos;
      }

      @Override
      public World getEntityWorld() {
         return this.commandSenderEntity.getEntityWorld();
      }

      @Override
      public boolean canCommandSenderUseCommand(int n, String str) {
         return n <= 2;
      }

      @Override
      public boolean sendCommandFeedback() {
         return false;
      }
   }
}
