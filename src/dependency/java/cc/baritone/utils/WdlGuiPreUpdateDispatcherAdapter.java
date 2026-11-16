package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import net.minecraft.util.StatCollector;

public class WdlGuiPreUpdateDispatcherAdapter implements PreUpdateDispatcher {
   public final AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
   public final AltListener altListener;
   public final CORERECOMMENDEDManager cORERECOMMENDEDManager;
   public final MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl;

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count = this.minecraftSplineCalculatorImpl.messagesEnableAllGuiScreen.width / 2 - 100;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = count3;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = StatCollector.getText(
         "wdl.gui.messages.message." + LoggerEntry.matchesAltListener(this.altListener), new Object[]{this.altListener.getText()}
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = LoggerEntry.enabled && LoggerEntry.matchesCORERECOMMENDEDManager(this.cORERECOMMENDEDManager);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .applyMinecraftByMinecraft(MinecraftSplineCalculatorImpl.resolveMinecraft4(this.minecraftSplineCalculatorImpl), count6, count7);
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter.isEnabled()) {
         MessagesEnableAllGuiScreen.resolveText(this.minecraftSplineCalculatorImpl.messagesEnableAllGuiScreen, this.altListener.getTextAsText());
      }
   }

   public WdlGuiPreUpdateDispatcherAdapter(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl2, AltListener altListener2, CORERECOMMENDEDManager cORERECOMMENDEDManager2) {
      this.minecraftSplineCalculatorImpl = minecraftSplineCalculatorImpl2;
      this.altListener = altListener2;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, 0, 0, altListener2.toString());
      this.cORERECOMMENDEDManager = cORERECOMMENDEDManager2;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .matchesMinecraft(MinecraftSplineCalculatorImpl.resolveMinecraft5(this.minecraftSplineCalculatorImpl), count2, count3)) {
         LoggerEntry.applyAltListener(this.altListener);
         this.auxiliaryTexturesGuiIconsPngManagerAdapter
            .applySoundHandler(MinecraftSplineCalculatorImpl.resolveMinecraft6(this.minecraftSplineCalculatorImpl).getMcSoundHandler());
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }
}
