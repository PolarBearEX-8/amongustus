package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C16PacketClientStatus;

public enum DisablerPingSpoofPacketOption implements SelectableOption {
   S00PacketKeepAlive("C00KeepAlive", C00PacketKeepAlive.class),
   S32PacketConfirmTransaction("C0FConfirmTransaction", C0FPacketConfirmTransaction.class),
   C0BPacketEntityAction("C0BEntityAction", C0BPacketEntityAction.class),
   C13PacketPlayerAbilities("C13PlayerAbilities", C13PacketPlayerAbilities.class),
   C16PacketClientStatus("C16ClientStatus", C16PacketClientStatus.class);

private String name;
   private boolean selected;
   private Class packetClass;
private DisablerPingSpoofPacketOption(String str, Class clazz) {
      this.name = str;
      this.packetClass = clazz;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public static Class getPacketClass(DisablerPingSpoofPacketOption option) {
      return option.packetClass;
   }

   public static boolean isOptionSelected(DisablerPingSpoofPacketOption option) {
      return option.selected;
   }
}
