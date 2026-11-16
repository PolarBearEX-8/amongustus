package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.TexturesGuiIconsPngManager;
import net.minecraft.util.StatCollector;

public class WdlGuiPreUpdateDispatcherImpl implements PreUpdateDispatcher {
   public final AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
   public final CORERECOMMENDEDManager cORERECOMMENDEDManager;
   public final MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl;

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      TexturesGuiIconsPngManager.applyFontRenderer4(
         MessagesEnableAllGuiScreen.resolveFontRenderer(this.minecraftSplineCalculatorImpl.messagesEnableAllGuiScreen),
         this.cORERECOMMENDEDManager.getText(),
         this.minecraftSplineCalculatorImpl.messagesEnableAllGuiScreen.width / 2 - 40,
         count3 + count5 - MinecraftSplineCalculatorImpl.resolveMinecraft3(this.minecraftSplineCalculatorImpl).fontRendererObj.indexOffset - 1,
         16777215
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count = this.minecraftSplineCalculatorImpl.messagesEnableAllGuiScreen.width / 2 + 20;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = count3;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = StatCollector.getText(
         "wdl.gui.messages.group." + LoggerEntry.matchesCORERECOMMENDEDManager(this.cORERECOMMENDEDManager), new Object[0]
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = LoggerEntry.enabled;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .applyMinecraftByMinecraft(MinecraftSplineCalculatorImpl.resolveMinecraftByMinecraftSplineC(this.minecraftSplineCalculatorImpl), count6, count7);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .matchesMinecraft(MinecraftSplineCalculatorImpl.resolveMinecraft2(this.minecraftSplineCalculatorImpl), count2, count3)) {
         LoggerEntry.applyCORERECOMMENDEDManager(this.cORERECOMMENDEDManager);
         this.auxiliaryTexturesGuiIconsPngManagerAdapter
            .applySoundHandler(MinecraftSplineCalculatorImpl.resolveMinecraft(this.minecraftSplineCalculatorImpl).getMcSoundHandler());
         return true;
      } else {
         return false;
      }
   }

   public WdlGuiPreUpdateDispatcherImpl(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl2, CORERECOMMENDEDManager cORERECOMMENDEDManager2) {
      this.minecraftSplineCalculatorImpl = minecraftSplineCalculatorImpl2;
      this.cORERECOMMENDEDManager = cORERECOMMENDEDManager2;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, 0, 0, 80, 20, "");
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
   }
}
