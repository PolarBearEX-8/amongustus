package client.event;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.gui.TexturesGuiIconsPngManager;
import client.util.GeometryDispatcher;
import net.minecraft.client.Minecraft;

public class MinecraftPreUpdateDispatcher implements PreUpdateDispatcher {
   public final Minecraft mc = Minecraft.getMinecraft();
   public final TexturesGuiIconsPngManager texturesGuiIconsPngManager;
   public final TexturesGuiIconsPngManager texturesGuiIconsPngManager2;
   public TexturesGuiIconsPngManager texturesGuiIconsPngManager3;

   private void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter, int count, int count2, int count3) {
      auxiliaryTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      boolean flag = this.matchesTexturesGuiIconsPngManager(this.texturesGuiIconsPngManager, count2, count3, count4);
      boolean flag2 = this.matchesTexturesGuiIconsPngManager(this.texturesGuiIconsPngManager2, count2, count3, count4);
      return flag || flag2;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      this.applyTexturesGuiIconsPngManager(this.texturesGuiIconsPngManager, count3, count6, count7, false);
      this.applyTexturesGuiIconsPngManager(this.texturesGuiIconsPngManager2, count3, count6, count7, false);
   }

   private void applyInternalTexturesGuiIconsPn(InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter, int count, int count2, int count3) {
      internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
      if (internalTexturesGuiIconsPngManagerAdapter.isEnabled2()) {
         this.texturesGuiIconsPngManager3 = internalTexturesGuiIconsPngManagerAdapter;
      }
   }

   private void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter, int count, int count2, int count3, boolean flag) {
      auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = count;
      if (!flag) {
         auxiliaryTexturesGuiIconsPngManagerAdapter.applyMinecraftByMinecraft(this.mc, count2, count3);
      }
   }

   private void applyTexturesGuiIconsPngManager(TexturesGuiIconsPngManager texturesGuiIconsPngManager4, int count, int count2, int count3) {
      if (texturesGuiIconsPngManager4 != null && texturesGuiIconsPngManager4 instanceof AuxiliaryTexturesGuiIconsPngManagerAdapter) {
         this.applyAuxiliaryTexturesGuiIconsP((AuxiliaryTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager4, count, count2, count3);
      }
   }

   public TexturesGuiIconsPngManager getTexturesGuiIconsPngManager() {
      return this.texturesGuiIconsPngManager;
   }

   private boolean matchesTexturesGuiIconsPngManager(TexturesGuiIconsPngManager texturesGuiIconsPngManager4, int count, int count2, int count3) {
      if (texturesGuiIconsPngManager4 == null) {
         return false;
      } else if (texturesGuiIconsPngManager4 instanceof AuxiliaryTexturesGuiIconsPngManagerAdapter) {
         return this.matchesAuxiliaryTexturesGuiIconsP((AuxiliaryTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager4, count, count2, count3);
      } else {
         if (texturesGuiIconsPngManager4 instanceof InternalTexturesGuiIconsPngManagerAdapter) {
            this.applyInternalTexturesGuiIconsPn((InternalTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager4, count, count2, count3);
         }

         return false;
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      this.applyTexturesGuiIconsPngManager(this.texturesGuiIconsPngManager, count2, count3, count4);
      this.applyTexturesGuiIconsPngManager(this.texturesGuiIconsPngManager2, count2, count3, count4);
   }

   public TexturesGuiIconsPngManager getTexturesGuiIconsPngManager2() {
      return this.texturesGuiIconsPngManager2;
   }

   public static TexturesGuiIconsPngManager resolveTexturesGuiIconsPngManager2(MinecraftPreUpdateDispatcher minecraftPreUpdateDispatcher) {
      return minecraftPreUpdateDispatcher.texturesGuiIconsPngManager2;
   }

   public static TexturesGuiIconsPngManager resolveTexturesGuiIconsPngManagerByMinecraftPreUpda(MinecraftPreUpdateDispatcher minecraftPreUpdateDispatcher) {
      return minecraftPreUpdateDispatcher.texturesGuiIconsPngManager3;
   }

   public MinecraftPreUpdateDispatcher(TexturesGuiIconsPngManager texturesGuiIconsPngManager4, TexturesGuiIconsPngManager texturesGuiIconsPngManager5) {
      this.texturesGuiIconsPngManager = texturesGuiIconsPngManager4;
      this.texturesGuiIconsPngManager2 = texturesGuiIconsPngManager5;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      this.applyTexturesGuiIconsPngManager(this.texturesGuiIconsPngManager, count3, 0, 0, true);
      this.applyTexturesGuiIconsPngManager(this.texturesGuiIconsPngManager2, count3, 0, 0, true);
   }

   public static TexturesGuiIconsPngManager resolveTexturesGuiIconsPngManager(MinecraftPreUpdateDispatcher minecraftPreUpdateDispatcher) {
      return minecraftPreUpdateDispatcher.texturesGuiIconsPngManager;
   }

   private void applyTexturesGuiIconsPngManager(TexturesGuiIconsPngManager texturesGuiIconsPngManager4, int count, int count2, int count3, boolean flag) {
      if (texturesGuiIconsPngManager4 != null) {
         if (texturesGuiIconsPngManager4 instanceof AuxiliaryTexturesGuiIconsPngManagerAdapter) {
            this.applyAuxiliaryTexturesGuiIconsP((AuxiliaryTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager4, count, count2, count3, flag);
         } else if (texturesGuiIconsPngManager4 instanceof InternalTexturesGuiIconsPngManagerAdapter) {
            this.applyInternalTexturesGuiIconsPn((InternalTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager4, count, flag);
         } else if (texturesGuiIconsPngManager4 instanceof GeometryDispatcher) {
            this.applyGeometryDispatcher((GeometryDispatcher)texturesGuiIconsPngManager4, count, count2, count3, flag);
         }
      }
   }

   private boolean matchesAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter, int count, int count2, int count3) {
      boolean flag = auxiliaryTexturesGuiIconsPngManagerAdapter.matchesMinecraft(this.mc, count, count2);
      if (flag) {
         this.texturesGuiIconsPngManager3 = auxiliaryTexturesGuiIconsPngManagerAdapter;
      }

      return flag;
   }

   private void applyInternalTexturesGuiIconsPn(InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter, int count, boolean flag) {
      internalTexturesGuiIconsPngManagerAdapter.count10 = count;
      if (!flag) {
         internalTexturesGuiIconsPngManagerAdapter.updateState();
      }
   }

   private void applyGeometryDispatcher(GeometryDispatcher geometryDispatcher, int count, int count2, int count3, boolean flag) {
      geometryDispatcher.count = count;
      if (!flag) {
         geometryDispatcher.applyMinecraft2(this.mc, count2, count3);
      }
   }
}
