package net.minecraft.client.model;

import client.Client;
import client.ClientGlobals;
import client.event.PreMotionSubscriber;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.util.RayTraceMatcher;
import client.util.TertiaryHashMapEntry;
import client.util.TertiaryMapEntry;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryModelManager implements PreMotionSubscriber {
   public final NetworkPlayerInfo networkPlayerInfo;

   @Override
   public void applyType(Type type, ResourceLocation location, MinecraftProfileTexture minecraftProfileTexture) {
      switch (RayTraceMatcher.countArray[type.ordinal()]) {
         case 1:
            NetworkPlayerInfo.resolveResourceLocation2(this.networkPlayerInfo, location);
            if (((Module)ClientGlobals.moduleManager.protector).isToggled()) {
               NetworkPlayerInfo networkPlayerInfo2 = this.networkPlayerInfo;
               TertiaryHashMapEntry tertiaryHashMapEntry = Client.getClient().getTertiaryHashMapEntry();
               char character = 0;
               NetworkPlayerInfo.resolveResourceLocation2(networkPlayerInfo2, tertiaryHashMapEntry.resolveResourceLocation(character, 1041461654, 5021));
            }

            if (((Module)ClientGlobals.moduleManager.customSkin).isToggled()
               && NetworkPlayerInfo.processAction(this.networkPlayerInfo).equals(Minecraft.getMinecraft().session.getText1())) {
               NetworkPlayerInfo.resolveResourceLocation2(
                  this.networkPlayerInfo, TertiaryMapEntry.get(ClientGlobals.moduleManager.customSkin.settingCustomName.getSelected())
               );
            } else {
               NetworkPlayerInfo.resolveText(this.networkPlayerInfo, minecraftProfileTexture.getMetadata("model"));
               if (NetworkPlayerInfo.processAction2(this.networkPlayerInfo) == null) {
                  NetworkPlayerInfo.resolveText(this.networkPlayerInfo, "default");
               }
            }
            break;
         case 2:
            if (!((Module)ClientGlobals.moduleManager.protector).isToggled()) {
               NetworkPlayerInfo.resolveResourceLocation(this.networkPlayerInfo, location);
            }
      }
   }

   public AuxiliaryModelManager(NetworkPlayerInfo networkPlayerInfo2) {
      this.networkPlayerInfo = networkPlayerInfo2;
   }
}
