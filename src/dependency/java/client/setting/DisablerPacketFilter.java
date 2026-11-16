package client.setting;

import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C16PacketClientStatus;

public enum DisablerPacketFilter implements NamedOption {
    S00PacketKeepAlive("C00KeepAlive", C00PacketKeepAlive.class),
    S32PacketConfirmTransaction("C0FConfirmTransaction", C0FPacketConfirmTransaction.class),
    C0BPacketEntityAction("C0BEntityAction", C0BPacketEntityAction.class),
    C13PacketPlayerAbilities("C13PlayerAbilities", C13PacketPlayerAbilities.class),
    C16PacketClientStatus("C16ClientStatus", C16PacketClientStatus.class);

   public String labelText;
   public boolean enabled;
   public Class memberRef;
   public static final DisablerPacketFilter[] ENUM_VALUES = new DisablerPacketFilter[]{
      S00PacketKeepAlive, S32PacketConfirmTransaction, C0BPacketEntityAction, C13PacketPlayerAbilities, C16PacketClientStatus
   };

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static boolean handlePacket(DisablerPacketFilter packet) {
      return packet.enabled;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   public static Class handlePacket2(DisablerPacketFilter packet) {
      return packet.memberRef;
   }

   private DisablerPacketFilter(String text, Class clazz) {
      this.labelText = text;
      this.memberRef = clazz;
   }


}
