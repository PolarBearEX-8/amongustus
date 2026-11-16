package client.util;

import client.Client;
import client.gui.AuxiliaryClientScreenAdapter;
import client.gui.InternalClientScreenAdapter;
import net.minecraft.client.gui.GuiScreen;

public class SharedStringEntry extends InternalClientScreenAdapter {
   @Override
   public boolean matchesAuxiliaryClientScreenAdapt(AuxiliaryClientScreenAdapter auxiliaryClientScreenAdapter) {
      return super.matchesAuxiliaryClientScreenAdapt(auxiliaryClientScreenAdapter);
   }

   public SharedStringEntry(GuiScreen screen, int count) {
      super(Client.getClient().getDraggableWindowHeader().getList(), screen, count);
   }
}
