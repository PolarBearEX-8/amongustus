package client.util;

import client.network.CustomDataPacket;
import client.setting.ClientSettingsBlackType;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.ArrayUtils;

public class SmoothProvider implements SmoothCalculator {
   public boolean enabled;
   public boolean enabled2;
   public long timestamp;
   public final NetworkManager networkManager;
   public final ServerData serverData;
   public final CancelledManager cancelledManager;

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      if (!this.enabled) {
         CancelledManager.getLogger().error("Can't ping " + this.serverData.text + ": " + iChatComponent.getString());
         this.serverData.labelText = ClientSettingsBlackType.DARK_RED + "Can't connect to server.";
         this.serverData.labelText5 = "";
      }
   }

   public SmoothProvider(CancelledManager cancelledManager2, NetworkManager networkManager2, ServerData serverData2) {
      this.cancelledManager = cancelledManager2;
      this.networkManager = networkManager2;
      this.serverData = serverData2;
      this.enabled = false;
      this.enabled2 = false;
      this.timestamp = 0L;
   }

   @Override
   public void applyCustomDataPacket(CustomDataPacket packet) {
      if (this.enabled2) {
         this.networkManager.applyIChatComponent(new ChatComponentText("Received unrequested status"));
      } else {
         this.enabled2 = true;
         IChatComponentEntry iChatComponentEntry = packet.getIChatComponentEntry();
         if (iChatComponentEntry.getIChatComponent() != null) {
            this.serverData.labelText = iChatComponentEntry.getIChatComponent().getString2();
         } else {
            this.serverData.labelText = "";
         }

         if (iChatComponentEntry.getDampenerDeserializer() != null) {
            this.serverData.labelText4 = iChatComponentEntry.getDampenerDeserializer().getLabelText();
            this.serverData.count = iChatComponentEntry.getDampenerDeserializer().getCount();
         } else {
            this.serverData.labelText4 = "Old";
            this.serverData.count = 0;
         }

         if (iChatComponentEntry.getGameProfileArrayHolder() == null) {
            this.serverData.labelText5 = ClientSettingsBlackType.DARK_GRAY + "???";
         } else {
            this.serverData.labelText5 = ClientSettingsBlackType.GRAY
               + ""
               + iChatComponentEntry.getGameProfileArrayHolder().getCount2()
               + ClientSettingsBlackType.DARK_GRAY
               + "/"
               + ClientSettingsBlackType.GRAY
               + iChatComponentEntry.getGameProfileArrayHolder().getCount();
            if (ArrayUtils.isNotEmpty(iChatComponentEntry.getGameProfileArrayHolder().getGameProfileArray())) {
               StringBuilder builder = new StringBuilder();

               for (GameProfile gameProfile : iChatComponentEntry.getGameProfileArrayHolder().getGameProfileArray()) {
                  if (builder.length() > 0) {
                     builder.append("\n");
                  }

                  builder.append(gameProfile.getName());
               }

               if (iChatComponentEntry.getGameProfileArrayHolder().getGameProfileArray().length < iChatComponentEntry.getGameProfileArrayHolder().getCount2()) {
                  if (builder.length() > 0) {
                     builder.append("\n");
                  }

                  builder.append("... and ")
                     .append(iChatComponentEntry.getGameProfileArrayHolder().getCount2() - iChatComponentEntry.getGameProfileArrayHolder().getGameProfileArray().length)
                     .append(" more ...");
               }

               this.serverData.labelText6 = builder.toString();
            }
         }

         if (iChatComponentEntry.getLabelText() != null) {
            String labelText = iChatComponentEntry.getLabelText();
            if (labelText.startsWith("data:image/png;base64,")) {
               this.serverData.setLabelText2(labelText.substring("data:image/png;base64,".length()));
            } else {
               CancelledManager.getLogger().error("Invalid server icon (unknown format)");
            }
         } else {
            this.serverData.setLabelText2(null);
         }

         this.timestamp = Minecraft.getTimestamp();
         this.networkManager.handlePacket6(new ExtendedPacketHandler(this.timestamp));
         this.enabled = true;
      }
   }

   @Override
   public void applyAdvancedPacketAdapter(AdvancedPacketAdapter packet) {
      long timestamp2 = this.timestamp;
      long timestamp3 = Minecraft.getTimestamp();
      this.serverData.timestamp = timestamp3 - timestamp2;
      this.networkManager.applyIChatComponent(new ChatComponentText("Finished"));
   }
}
