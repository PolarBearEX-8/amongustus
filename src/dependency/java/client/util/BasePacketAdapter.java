package client.util;

import client.gui.ClientScreenAdapter;
import client.setting.CombatKeybindProperty;
import java.io.IOException;

public class BasePacketAdapter implements Packet {
   public int count2;
   public String labelText;
   public int count;
   public CombatKeybindProperty combatKeybindProperty;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.getInt3();
      this.labelText = packet.resolveTextByIndex(255);
      this.count = packet.readUnsignedShort();
      this.combatKeybindProperty = CombatKeybindProperty.resolveCombatKeybindProperty(packet.getInt3());
   }

   public CombatKeybindProperty getCombatKeybindProperty() {
      return this.combatKeybindProperty;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applyAuxiliaryINetHandlerExtens((AuxiliaryINetHandlerExtension)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count2);
      packet.getPacketBuffer(this.labelText);
      packet.writeShort(this.count);
      packet.updateInt(this.combatKeybindProperty.getCount3());
   }

   public int getCount2() {
      return this.count2;
   }

   public void applyAuxiliaryINetHandlerExtens(AuxiliaryINetHandlerExtension auxiliaryINetHandlerExtension) {
      auxiliaryINetHandlerExtension.applyBasePacketAdapter(this);
   }

   public BasePacketAdapter() {
   }

   public BasePacketAdapter(int count3, String text, int count4, CombatKeybindProperty combatKeybindProperty2) {
      this.count2 = count3;
      this.labelText = text;
      this.count = count4;
      this.combatKeybindProperty = combatKeybindProperty2;
      this.labelText = ClientScreenAdapter.resolveText(this.labelText);
   }
}
