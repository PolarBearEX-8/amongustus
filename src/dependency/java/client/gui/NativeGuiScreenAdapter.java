package client.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public class NativeGuiScreenAdapter extends GuiScreen {
   public static AuxiliaryTexturesGuiIconsPngManagerAdapter resolveAuxiliaryTexturesGuiIconsPng(int count, int count2, List list) {
      for (int index = 0; index < list.size(); index++) {
         AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter = (AuxiliaryTexturesGuiIconsPngManagerAdapter)list.get(index);
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled2) {
            int count3 = CoreGuiScreenAdapter.computeCount(auxiliaryTexturesGuiIconsPngManagerAdapter);
            int count4 = CoreGuiScreenAdapter.computeCountByAuxiliaryTexture(auxiliaryTexturesGuiIconsPngManagerAdapter);
            if (count >= auxiliaryTexturesGuiIconsPngManagerAdapter.count && count2 >= auxiliaryTexturesGuiIconsPngManagerAdapter.count3 && count < auxiliaryTexturesGuiIconsPngManagerAdapter.count + count3 && count2 < auxiliaryTexturesGuiIconsPngManagerAdapter.count3 + count4) {
               return auxiliaryTexturesGuiIconsPngManagerAdapter;
            }
         }
      }

      return null;
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      if (count3 == 1) {
         AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter = resolveAuxiliaryTexturesGuiIconsPng(count, count2, this.list);
         if (auxiliaryTexturesGuiIconsPngManagerAdapter != null && auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
            auxiliaryTexturesGuiIconsPngManagerAdapter.applySoundHandler(this.mc.getMcSoundHandler());
            this.applyAuxiliaryTexturesGuiIconsPByAuxiliaryTexture(auxiliaryTexturesGuiIconsPngManagerAdapter);
         }
      }
   }

   public void applyAuxiliaryTexturesGuiIconsPByAuxiliaryTexture(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
   }
}
