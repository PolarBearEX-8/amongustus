package client.util;

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
import net.minecraft.network.play.client.CAnimateHandPacket;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;

public interface INetHandlerPlayServer extends INetHandler {
   void applyC11PacketEnchantItem(C11PacketEnchantItem packet);

   void handleC08PacketPlayerBlockPlacement(C08PacketPlayerBlockPlacement packet);

   void applyC14PacketTabComplete(C14PacketTabComplete packet);

   void applyC0EPacketClickWindow(C0EPacketClickWindow packet);

   void applyCAnimateHandPacket(CAnimateHandPacket packet);

   void applyCPacketPlayerTryUseItemOnB(CPacketPlayerTryUseItemOnBlock packet);

   void applyC15PacketClientSettings(C15PacketClientSettings packet);

   void handleC07PacketPlayerDigging(C07PacketPlayerDigging packet);

   void handleC03PacketPlayer(C03PacketPlayer packet);

   void applyC0BPacketEntityAction(C0BPacketEntityAction packet);

   void applyC00PacketKeepAlive(C00PacketKeepAlive packet);

   void applyC01PacketChatMessage(C01PacketChatMessage packet);

   void handleC02PacketUseEntity(C02PacketUseEntity packet);

   void applyC0FPacketConfirmTransactio(C0FPacketConfirmTransaction packet);

   void applyC12PacketUpdateSign(C12PacketUpdateSign packet);

   void applyC17PacketCustomPayload(C17PacketCustomPayload packet);

   void applyC16PacketClientStatus(C16PacketClientStatus packet);

   void applyC0DPacketCloseWindow(C0DPacketCloseWindow packet);

   void applyC19PacketResourcePackStatu(C19PacketResourcePackStatus packet);

   void applyC13PacketPlayerAbilities(C13PacketPlayerAbilities packet);

   void applyC0APacketAnimation(C0APacketAnimation packet);

   void applyC10PacketCreativeInventory(C10PacketCreativeInventoryAction packet);

   void applyC18PacketSpectate(C18PacketSpectate packet);

   void applyC09PacketHeldItemChange(C09PacketHeldItemChange packet);

   void applyC0CPacketInput(C0CPacketInput packet);
}
