package client.util;

import client.network.CustomDataPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public final class FinishedSmoothCalculator implements SmoothCalculator {
   public final NetworkManager networkManager;
   public final boolean[] enabledArray;

   @Override
   public void applyCustomDataPacket(CustomDataPacket packet) {
      this.enabledArray[0] = true;
      this.networkManager.applyIChatComponent(new ChatComponentText("Finished"));
   }

   @Override
   public void applyAdvancedPacketAdapter(AdvancedPacketAdapter packet) {
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
   }

   public FinishedSmoothCalculator(boolean[] flagArray, NetworkManager networkManager2) {
      this.enabledArray = flagArray;
      this.networkManager = networkManager2;
   }
}
