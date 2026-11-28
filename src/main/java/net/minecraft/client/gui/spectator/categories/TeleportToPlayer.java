package net.minecraft.client.gui.spectator.categories;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;
import net.minecraft.world.WorldSettings;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import net.minecraft.client.gui.spectator.PlayerMenuObject;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TeleportToPlayer implements ISpectatorMenuObject, ISpectatorMenuView {
   private static final Ordering PROFILE_ORDER = Ordering.from(new TeleportToPlayer.EntryComparator());
   private final List items = Lists.newArrayList();

   @Override
   public IChatComponent getSpectatorName() {
      return new ChatComponentText("Teleport to player");
   }

   @Override
   public void selectItem(SpectatorMenu spectatorMenu) {
      spectatorMenu.selectCategory(this);
   }

   @Override
   public boolean isEnabled() {
      return !this.items.isEmpty();
   }

   @Override
   public void renderIcon(float f, int n) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.SPECTATOR_WIDGETS_TEXTURE);
      Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   @Override
   public IChatComponent getPrompt() {
      return new ChatComponentText("Select a player to teleport to");
   }

   @Override
   public List getItems() {
      return this.items;
   }

   public TeleportToPlayer() {
      this(PROFILE_ORDER.sortedCopy(Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap()));
   }

   public TeleportToPlayer(Collection items2) {
      for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(PROFILE_ORDER.sortedCopy(items2))) {
         if (networkPlayerInfo.getGameType() != WorldSettings.GameType.SPECTATOR) {
            this.items.add(new PlayerMenuObject(networkPlayerInfo.getGameProfile()));
         }
      }
   }


   public static final class EntryComparator implements Comparator {
      @Override
      public int compare(Object a, Object b) {
         return this.compare((NetworkPlayerInfo)a, (NetworkPlayerInfo)b);
      }

      public int compare(NetworkPlayerInfo a, NetworkPlayerInfo b) {
         return ComparisonChain.start().compare(a.getGameProfile().getId(), b.getGameProfile().getId()).result();
      }
   }
}
