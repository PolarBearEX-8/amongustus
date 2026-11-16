package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;

public class EntityNameExtendedMinecraftEntry extends ExtendedMinecraftEntry {
   public final List list;
   public final SplineBridge splineBridge;

   public EntityNameExtendedMinecraftEntry(SplineBridge splineBridge2, Minecraft mc) {
      super(mc, splineBridge2.width, splineBridge2.height, 32, splineBridge2.height - 64, SplineBridge.resolveFontRendererBySplineBridge(splineBridge2).indexOffset * 4);
      this.splineBridge = splineBridge2;
      this.list = Lists.newArrayList();
      this.setEnabled2(false);

      for (Object duplicateStatIdManagerEntryObj : SkippingEntityWithIdManager.map6.values()) {
         DuplicateStatIdManagerEntry duplicateStatIdManagerEntry = (DuplicateStatIdManagerEntry)duplicateStatIdManagerEntryObj;
         if (SplineBridge.resolveStatFileWriter(splineBridge2).computeCount(duplicateStatIdManagerEntry.duplicateStatIdManager2) > 0
            || SplineBridge.resolveStatFileWriter(splineBridge2).computeCount(duplicateStatIdManagerEntry.duplicateStatIdManager) > 0) {
            this.list.add(duplicateStatIdManagerEntry);
         }
      }
   }

   @Override
   public void updateState2() {
      this.splineBridge.renderElement3();
   }

   @Override
   public int size() {
      return this.list.size();
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      DuplicateStatIdManagerEntry duplicateStatIdManagerEntry = (DuplicateStatIdManagerEntry)this.list.get(count);
      String text = StatCollector.getText("entity." + SkippingEntityWithIdManager.resolveText(duplicateStatIdManagerEntry.count2) + ".name", new Object[0]);
      int count7 = SplineBridge.resolveStatFileWriter(this.splineBridge).computeCount(duplicateStatIdManagerEntry.duplicateStatIdManager2);
      int count8 = SplineBridge.resolveStatFileWriter(this.splineBridge).computeCount(duplicateStatIdManagerEntry.duplicateStatIdManager);
      String text2 = StatCollector.getText("stat.entityKills", new Object[]{count7, text});
      String text3 = StatCollector.getText("stat.entityKilledBy", new Object[]{text, count8});
      if (count7 == 0) {
         text2 = StatCollector.getText("stat.entityKills.none", new Object[]{text});
      }

      if (count8 == 0) {
         text3 = StatCollector.getText("stat.entityKilledBy.none", new Object[]{text});
      }

      this.splineBridge.applyFontRenderer(SplineBridge.resolveFontRenderer14(this.splineBridge), text, count2 + 2 - 10, count3 + 1, 16777215);
      this.splineBridge
         .applyFontRenderer(
            SplineBridge.resolveFontRenderer17(this.splineBridge),
            text2,
            count2 + 2,
            count3 + 1 + SplineBridge.resolveFontRenderer5(this.splineBridge).indexOffset,
            count7 == 0 ? 6316128 : 9474192
         );
      this.splineBridge
         .applyFontRenderer(
            SplineBridge.resolveFontRenderer12(this.splineBridge),
            text3,
            count2 + 2,
            count3 + 1 + SplineBridge.resolveFontRenderer4(this.splineBridge).indexOffset * 2,
            count8 == 0 ? 6316128 : 9474192
         );
   }

   @Override
   public int getCount() {
      return this.size() * SplineBridge.resolveFontRenderer8(this.splineBridge).indexOffset * 4;
   }

   @Override
   public boolean checkIndex(int count) {
      return false;
   }

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
   }
}
