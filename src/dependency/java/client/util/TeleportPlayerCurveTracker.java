package client.util;

import client.gui.ExtendedTexturesGuiIconsPngManagerAdapter;
import client.gui.TexturesGuiIconsPngManager;
import client.network.NetworkPlayerInfo;
import client.network.NetworkPlayerInfoComparator;
import client.setting.WorldSettingsGameType;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TeleportPlayerCurveTracker implements CurveTracker, AuxiliaryCustom {
   public static final Ordering ordering = Ordering.from(new NetworkPlayerInfoComparator());
   public final List list = Lists.newArrayList();

   public TeleportPlayerCurveTracker(Collection collection) {
      for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(Iterable<?>)ordering.sortedCopy(collection)) {
         if (networkPlayerInfo.getWorldSettingsGameType() != WorldSettingsGameType.SPECTATOR) {
            this.list.add(new GameProfileCurveTracker(networkPlayerInfo.getGameProfile()));
         }
      }
   }

   @Override
   public IChatComponent getName() {
      return new ChatComponentText("Teleport to player");
   }

   @Override
   public void applyFloat(float factor, int count) {
      Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(ExtendedTexturesGuiIconsPngManagerAdapter.resourceLocation4);
      TexturesGuiIconsPngManager.applyIndex(0, 0, 0.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   @Override
   public List getList() {
      return this.list;
   }

   @Override
   public IChatComponent getIChatComponent() {
      return new ChatComponentText("Select a player to teleport to");
   }

   @Override
   public boolean isEmpty() {
      return !this.list.isEmpty();
   }

   @Override
   public void applySplineConverter(SplineConverter splineConverter) {
      splineConverter.applyAuxiliaryCustom(this);
   }

   public TeleportPlayerCurveTracker() {
      this(ordering.sortedCopy(Minecraft.getMinecraft().getNetHandlerPlayClient().getCollection()));
   }
}
