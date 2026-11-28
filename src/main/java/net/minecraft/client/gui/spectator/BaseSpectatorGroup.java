package net.minecraft.client.gui.spectator;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayer;
import net.minecraft.client.gui.spectator.categories.TeleportToTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class BaseSpectatorGroup implements ISpectatorMenuView {
   private final List items = Lists.newArrayList();

   public BaseSpectatorGroup() {
      this.items.add(new TeleportToPlayer());
      this.items.add(new TeleportToTeam());
   }

   @Override
   public IChatComponent getPrompt() {
      return new ChatComponentText("Press a key to select a command, and again to use it.");
   }

   @Override
   public List getItems() {
      return this.items;
   }
}
