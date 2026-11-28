package net.minecraft.client.gui;

import net.minecraft.client.settings.GameSettings;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;

public class GuiOptionsRowList extends GuiListExtended {
   private final List options = Lists.newArrayList();

   @Override
   public int getListWidth() {
      return 400;
   }

   @Override
   public GuiListExtended.IGuiListEntry getEntry(int n) {
      return this.getListEntry(n);
   }

   private GuiButton createOptionButton(Minecraft mc2, int n, int n2, GameSettings.Options options2) {
      if (options2 == null) {
         return null;
      } else {
         int n3 = options2.returnEnumOrdinal();
         return (GuiButton)(options2.getEnumFloat()
            ? new GuiOptionSlider(n3, n, n2, options2)
            : new GuiOptionButton(n3, n, n2, options2, mc2.gameSettings.getKeyBinding(options2)));
      }
   }

   public GuiOptionsRowList.Row getListEntry(int n) {
      return (GuiOptionsRowList.Row)this.options.get(n);
   }

   @Override
   public int getScrollBarX() {
      return super.getScrollBarX() + 32;
   }

   @Override
   public int getSize() {
      return this.options.size();
   }

   public GuiOptionsRowList(Minecraft mc2, int n, int n2, int n3, int n4, int n5, GameSettings.Options[] optionsArr) {
      super(mc2, n, n2, n3, n4, n5);
      this.centerListVertically = false;

      for (int i = 0; i < optionsArr.length; i += 2) {
         GameSettings.Options options2 = optionsArr[i];
         GameSettings.Options options3 = i < optionsArr.length - 1 ? optionsArr[i + 1] : null;
         GuiButton button = this.createOptionButton(mc2, n / 2 - 155, 0, options2);
         GuiButton button2 = this.createOptionButton(mc2, n / 2 - 155 + 160, 0, options3);
         this.options.add(new GuiOptionsRowList.Row(button, button2));
      }
   }


   public static class Row implements GuiListExtended.IGuiListEntry {
      private final Minecraft mc = Minecraft.getMinecraft();
      private final GuiButton buttonA;
      private final GuiButton buttonB;

      @Override
      public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
         if (this.buttonA.mousePressed(this.mc, n2, n3)) {
            if (this.buttonA instanceof GuiOptionButton) {
               this.mc.gameSettings.setOptionValue(((GuiOptionButton)this.buttonA).returnEnumOptions(), 1);
               this.buttonA.displayString = this.mc.gameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(this.buttonA.id));
            }

            return true;
         } else if (this.buttonB != null && this.buttonB.mousePressed(this.mc, n2, n3)) {
            if (this.buttonB instanceof GuiOptionButton) {
               this.mc.gameSettings.setOptionValue(((GuiOptionButton)this.buttonB).returnEnumOptions(), 1);
               this.buttonB.displayString = this.mc.gameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(this.buttonB.id));
            }

            return true;
         } else {
            return false;
         }
      }

      @Override
      public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
         if (this.buttonA != null) {
            this.buttonA.yPosition = n3;
            this.buttonA.drawButton(this.mc, n6, n7);
         }

         if (this.buttonB != null) {
            this.buttonB.yPosition = n3;
            this.buttonB.drawButton(this.mc, n6, n7);
         }
      }

      @Override
      public void setSelected(int n, int n2, int n3) {
      }

      @Override
      public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
         if (this.buttonA != null) {
            this.buttonA.mouseReleased(n2, n3);
         }

         if (this.buttonB != null) {
            this.buttonB.mouseReleased(n2, n3);
         }
      }

      public Row(GuiButton button, GuiButton button2) {
         this.buttonA = button;
         this.buttonB = button2;
      }
   }
}
