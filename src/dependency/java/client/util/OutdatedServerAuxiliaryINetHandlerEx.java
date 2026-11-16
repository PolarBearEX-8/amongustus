package client.util;

import client.setting.CombatKeybindProperty;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class OutdatedServerAuxiliaryINetHandlerEx implements AuxiliaryINetHandlerExtension {
   public final VanillaRunnable vanillaRunnable;
   public final NetworkManager networkManager;

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
   }

   @Override
   public void applyBasePacketAdapter(BasePacketAdapter packet) {
      switch (OutdatedServerAuxiliaryICombatKeybindPropertSw.countArray[packet.getCombatKeybindProperty().ordinal()]) {
         case 1:
            this.networkManager.applyCombatKeybindProperty(CombatKeybindProperty.LOGIN);
            if (packet.getCount2() > 47) {
               ChatComponentText chatComponentText = new ChatComponentText("Outdated server! I'm still on 1.8.9");
               this.networkManager.handlePacket6(new SpecialPacketAdapter(chatComponentText));
               this.networkManager.applyIChatComponent(chatComponentText);
            } else if (packet.getCount2() < 47) {
               ChatComponentText chatComponentText2 = new ChatComponentText("Outdated client! Please use 1.8.9");
               this.networkManager.handlePacket6(new SpecialPacketAdapter(chatComponentText2));
               this.networkManager.applyIChatComponent(chatComponentText2);
            } else {
               this.networkManager.applyINetHandler(new ListenerAdapter3(this.vanillaRunnable, this.networkManager));
            }
            break;
         case 2:
            this.networkManager.applyCombatKeybindProperty(CombatKeybindProperty.STATUS);
            this.networkManager.applyINetHandler(new StatusRequestRotationProfiler(this.vanillaRunnable, this.networkManager));
            break;
         default:
            throw new UnsupportedOperationException("Invalid intention " + packet.getCombatKeybindProperty());
      }
   }

   public OutdatedServerAuxiliaryINetHandlerEx(VanillaRunnable vanillaRunnable2, NetworkManager networkManager2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.networkManager = networkManager2;
   }
}
