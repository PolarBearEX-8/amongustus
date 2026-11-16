package client.util;

import net.minecraft.client.Minecraft;

public class SplineBridgeExtendedMinecraftEntry extends ExtendedMinecraftEntry {
   public final SplineBridge splineBridge;

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      DuplicateStatIdManager duplicateStatIdManager = (DuplicateStatIdManager)StatCraftItemManager.list2.get(count);
      this.splineBridge
         .applyFontRenderer(
            SplineBridge.resolveFontRenderer7(this.splineBridge), duplicateStatIdManager.getIChatComponent().getString(), count2 + 2, count3 + 1, count % 2 == 0 ? 16777215 : 9474192
         );
      String text = duplicateStatIdManager.resolveText(SplineBridge.resolveStatFileWriter(this.splineBridge).computeCount(duplicateStatIdManager));
      this.splineBridge
         .applyFontRenderer(
            SplineBridge.resolveFontRenderer11(this.splineBridge),
            text,
            count2 + 2 + 213 - SplineBridge.resolveFontRenderer6(this.splineBridge).getIndex(text),
            count3 + 1,
            count % 2 == 0 ? 16777215 : 9474192
         );
   }

   @Override
   public void updateState2() {
      this.splineBridge.renderElement3();
   }

   @Override
   public int getCount() {
      return this.size() * 10;
   }

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
   }

   @Override
   public boolean checkIndex(int count) {
      return false;
   }

   @Override
   public int size() {
      return StatCraftItemManager.list2.size();
   }

   public SplineBridgeExtendedMinecraftEntry(SplineBridge splineBridge2, Minecraft mc) {
      super(mc, splineBridge2.width, splineBridge2.height, 32, splineBridge2.height - 64, 10);
      this.splineBridge = splineBridge2;
      this.setEnabled2(false);
   }
}
