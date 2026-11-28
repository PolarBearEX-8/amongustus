package net.minecraft.client.gui.spectator;

import net.minecraft.util.IChatComponent;

public interface ISpectatorMenuObject {
   IChatComponent getSpectatorName();

   boolean isEnabled();

   void renderIcon(float f, int n);

   void selectItem(SpectatorMenu spectatorMenu);
}
