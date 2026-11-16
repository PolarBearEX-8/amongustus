package client.util;

import client.event.PreRenderNotifier;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.NativeGuiScreenAdapter;
import client.gui.TexturesGuiIconsPngManager;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class AuxiliaryManager {
   public GuiScreen guiScreen;
   public PreRenderNotifier preRenderNotifier;
   public int count2 = 0;
   public int count = 0;
   public long timestamp = 0L;

   private void applyIndex(int count3, int count4, int count5, int count6, int count7) {
      TexturesGuiIconsPngManager.processAction(count3, count4 - 1, count5, count4, count7);
      TexturesGuiIconsPngManager.processAction(count3, count6, count5, count6 + 1, count7);
      TexturesGuiIconsPngManager.processAction(count3 - 1, count4, count3, count6, count7);
      TexturesGuiIconsPngManager.processAction(count5, count4, count5 + 1, count6, count7);
   }

   public void applyList(int count3, int count4, List list) {
      if (Math.abs(count3 - this.count2) > 5 || Math.abs(count4 - this.count) > 5) {
         this.count2 = count3;
         this.count = count4;
         this.timestamp = System.currentTimeMillis();
      } else if (System.currentTimeMillis() >= this.timestamp + 700L) {
         AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter = NativeGuiScreenAdapter.resolveAuxiliaryTexturesGuiIconsPng(count3, count4, list);
         if (auxiliaryTexturesGuiIconsPngManagerAdapter != null) {
            Rectangle rectangle = this.preRenderNotifier.resolveRectangle(this.guiScreen, count3, count4);
            String[] textArray = this.preRenderNotifier.resolveTextArray(auxiliaryTexturesGuiIconsPngManagerAdapter, rectangle.width);
            if (textArray != null) {
               if (textArray.length > 8) {
                  textArray = Arrays.copyOf(textArray, 8);
                  textArray[textArray.length - 1] = textArray[textArray.length - 1] + " ...";
               }

               if (this.preRenderNotifier.isActive()) {
                  this.applyIndex(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, -528449408);
               }

               TexturesGuiIconsPngManager.processAction(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, -536870912);

               for (int index = 0; index < textArray.length; index++) {
                  String text = textArray[index];
                  int count5 = 14540253;
                  if (text.endsWith("!")) {
                     count5 = 16719904;
                  }

                  float factor = (float)(rectangle.x + 5);
                  Minecraft.getMinecraft().fontRendererObj.getIndex2(text, factor, (float)(rectangle.y + 5 + index * 11), count5);
               }
            }
         }
      }
   }

   public AuxiliaryManager(GuiScreen screen, PreRenderNotifier preRenderNotifier2) {
      this.guiScreen = screen;
      this.preRenderNotifier = preRenderNotifier2;
   }
}
