package net.minecraft.network.play;

import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.augustus.viaversion.packet.C0APacketSwingHand;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;

public interface INetHandlerPlayServer extends INetHandler {
   void processEnchantItem(C11PacketEnchantItem packet);

   void processCloseWindow(C0DPacketCloseWindow packet);

   void processPlayerDigging(C07PacketPlayerDigging packet);

   void processPlayerAbilities(C13PacketPlayerAbilities packet);

   void handleAnimation(C0APacketAnimation packet);

   void processCreativeInventoryAction(C10PacketCreativeInventoryAction packet);

   void processInput(C0CPacketInput packet);

   void handleSpectate(C18PacketSpectate packet);

   void processConfirmTransaction(C0FPacketConfirmTransaction packet);

   void processVanilla250Packet(C17PacketCustomPayload packet);

   void processPlayer(C03PacketPlayer packet);

   void processUseEntity(C02PacketUseEntity packet);

   void processClientStatus(C16PacketClientStatus packet);

   void processChatMessage(C01PacketChatMessage packet);

   void processTabComplete(C14PacketTabComplete packet);

   void processKeepAlive(C00PacketKeepAlive packet);

   void processClientSettings(C15PacketClientSettings packet);

   void processUpdateSign(C12PacketUpdateSign packet);

   void processEntityAction(C0BPacketEntityAction packet);

   void processSwingHand(C0APacketSwingHand packet);

   void processPlayerBlockPlacement(C08PacketPlayerBlockPlacement packet);

   void processHeldItemChange(C09PacketHeldItemChange packet);

   void handleResourcePackStatus(C19PacketResourcePackStatus packet);

   void processTryUseItemOnBlock(CPacketPlayerTryUseItemOnBlock block);

   void processClickWindow(C0EPacketClickWindow packet);
}
