package client.util;

import client.event.PreUpdateDispatcher;
import net.minecraft.util.StatCollector;

public class ListenerAdapter15 implements PreUpdateDispatcher {
   public final String labelText;
   public final int count;
   public final AuxiliaryClientManagerProvider auxiliaryClientManagerProvider;

   @Override
   public void applyIndex(int count2, int count3, int count4) {
   }

   @Override
   public boolean checkIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
      return false;
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7, int count8, boolean flag) {
      AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider)
         .fontRendererObj
         .getIndex(
            this.labelText,
            AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider).currentScreen.width / 2 - this.count / 2,
            count4 + count6 - AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider).fontRendererObj.indexOffset - 1,
            16777215
         );
   }

   public ListenerAdapter15(AuxiliaryClientManagerProvider auxiliaryClientManagerProvider2, String text) {
      this.auxiliaryClientManagerProvider = auxiliaryClientManagerProvider2;
      this.labelText = StatCollector.getText(text, new Object[0]);
      this.count = AuxiliaryClientManagerProvider.resolveMinecraft(auxiliaryClientManagerProvider2).fontRendererObj.getIndex(this.labelText);
   }
}
