package client.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public abstract class AbstractGuiButtonExtendedMinecraftEntry extends ExtendedMinecraftEntry {
   public int count17;
   public List list;
   public Comparator comparator;
   public int count15;
   public int count16;
   public final SplineBridge splineBridge;

   @Override
   public void applyIndex(int count, int count2) {
      if (count2 >= this.count10 && count2 <= this.count7) {
         int count3 = this.computeCount(count, count2);
         int count4 = this.count9 / 2 - 92 - 16;
         if (count3 >= 0) {
            if (count < count4 + 40 || count > count4 + 40 + 20) {
               return;
            }

            DuplicateStatIdManagerAdapter duplicateStatIdManagerAdapter = this.get(count3);
            this.applyDuplicateStatIdManagerAdap(duplicateStatIdManagerAdapter, count, count2);
         } else {
            String text;
            if (count >= count4 + 115 - 18 && count <= count4 + 115) {
               text = this.resolveText(0);
            } else if (count >= count4 + 165 - 18 && count <= count4 + 165) {
               text = this.resolveText(1);
            } else {
               if (count < count4 + 215 - 18 || count > count4 + 215) {
                  return;
               }

               text = this.resolveText(2);
            }

            String text2 = ("" + StatCollector.getText(text, new Object[0])).trim();
            if (text2.length() > 0) {
               int count5 = count + 12;
               int count6 = count2 - 12;
               int index = SplineBridge.resolveFontRenderer15(this.splineBridge).getIndex(text2);
               SplineBridge.applySplineBridgeBySplineBridge(this.splineBridge, count5 - 3, count6 - 3, count5 + index + 3, count6 + 8 + 3, -1073741824, -1073741824);
               SplineBridge.resolveFontRenderer13(this.splineBridge).getIndex2(text2, (float)count5, (float)count6, -1);
            }
         }
      }
   }

   public void applyIndex2(int count) {
      if (count != this.count15) {
         this.count15 = count;
         this.count16 = -1;
      } else if (this.count16 == -1) {
         this.count16 = 1;
      } else {
         this.count15 = -1;
         this.count16 = 0;
      }

      Collections.sort(this.list, this.comparator);
   }

   public AbstractGuiButtonExtendedMinecraftEntry(SplineBridge splineBridge2, Minecraft mc) {
      super(mc, splineBridge2.width, splineBridge2.height, 32, splineBridge2.height - 64, 20);
      this.splineBridge = splineBridge2;
      this.count17 = -1;
      this.count15 = -1;
      this.setEnabled2(false);
      this.applyFlag(true, 20);
   }

   @Override
   public void applyIndexByIndex(int count, int count2) {
      this.count17 = -1;
      if (count >= 79 && count < 115) {
         this.count17 = 0;
      } else if (count >= 129 && count < 165) {
         this.count17 = 1;
      } else if (count >= 179 && count < 215) {
         this.count17 = 2;
      }

      if (this.count17 >= 0) {
         this.applyIndex2(this.count17);
         this.mc.getMcSoundHandler().applyISound2(PositionedSoundRecord.createButtonPress(new ResourceLocation("gui.button.press"), 1.0F));
      }
   }

   @Override
   public void applyTessellator(int count, int count2, Tessellator tessellator) {
      if (!MouseUtils.checkCondition(0)) {
         this.count17 = -1;
      }

      if (this.count17 == 0) {
         SplineBridge.applySplineBridge(this.splineBridge, count + 115 - 18, count2 + 1, 0, 0);
      } else {
         SplineBridge.applySplineBridge(this.splineBridge, count + 115 - 18, count2 + 1, 0, 18);
      }

      if (this.count17 == 1) {
         SplineBridge.applySplineBridge(this.splineBridge, count + 165 - 18, count2 + 1, 0, 0);
      } else {
         SplineBridge.applySplineBridge(this.splineBridge, count + 165 - 18, count2 + 1, 0, 18);
      }

      if (this.count17 == 2) {
         SplineBridge.applySplineBridge(this.splineBridge, count + 215 - 18, count2 + 1, 0, 0);
      } else {
         SplineBridge.applySplineBridge(this.splineBridge, count + 215 - 18, count2 + 1, 0, 18);
      }

      if (this.count15 != -1) {
         short shortValue = 79;
         byte byteValue = 18;
         if (this.count15 == 1) {
            shortValue = 129;
         } else if (this.count15 == 2) {
            shortValue = 179;
         }

         if (this.count16 == 1) {
            byteValue = 36;
         }

         SplineBridge.applySplineBridge(this.splineBridge, count + shortValue, count2 + 1, byteValue, 0);
      }
   }

   @Override
   public boolean checkIndex(int count) {
      return false;
   }

   @Override
   public void updateState2() {
      this.splineBridge.renderElement3();
   }

   public final DuplicateStatIdManagerAdapter get(int count) {
      return (DuplicateStatIdManagerAdapter)this.list.get(count);
   }

   public abstract String resolveText(int count);

   @Override
   public final int size() {
      return this.list.size();
   }

   public void applyDuplicateStatIdManagerAdap(DuplicateStatIdManagerAdapter duplicateStatIdManagerAdapter, int count, int count2) {
      if (duplicateStatIdManagerAdapter != null) {
         Item item = duplicateStatIdManagerAdapter.getItem();
         String text = new ItemStack(item).processAction7();
         String text2 = ("" + StatCollector.getText(text + ".name", new Object[0])).trim();
         if (text2.length() > 0) {
            int count3 = count + 12;
            int count4 = count2 - 12;
            int index = SplineBridge.resolveFontRenderer(this.splineBridge).getIndex(text2);
            SplineBridge.applySplineBridge(this.splineBridge, count3 - 3, count4 - 3, count3 + index + 3, count4 + 8 + 3, -1073741824, -1073741824);
            SplineBridge.resolveFontRenderer2(this.splineBridge).getIndex2(text2, (float)count3, (float)count4, -1);
         }
      }
   }

   public void applyDuplicateStatIdManager(DuplicateStatIdManager duplicateStatIdManager, int count, int count2, boolean flag) {
      if (duplicateStatIdManager != null) {
         String text = duplicateStatIdManager.resolveText(SplineBridge.resolveStatFileWriter(this.splineBridge).computeCount(duplicateStatIdManager));
         this.splineBridge
            .applyFontRenderer(
               SplineBridge.resolveFontRenderer16(this.splineBridge),
               text,
               count - SplineBridge.resolveFontRenderer9(this.splineBridge).getIndex(text),
               count2 + 5,
               flag ? 16777215 : 9474192
            );
      } else {
         String text2 = "-";
         this.splineBridge
            .applyFontRenderer(
               SplineBridge.resolveFontRenderer3(this.splineBridge),
               text2,
               count - SplineBridge.resolveFontRenderer10(this.splineBridge).getIndex(text2),
               count2 + 5,
               flag ? 16777215 : 9474192
            );
      }
   }

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
   }
}
