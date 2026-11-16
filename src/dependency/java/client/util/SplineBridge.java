package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.render.WorldRenderer;
import client.setting.AutoSoupPerformRespawnType;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.StatCollector;

public class SplineBridge extends GuiScreen implements StringArrayCallback {
   public String labelText = "Select world";
   public SplineBridgeExtendedMinecraftEntry splineBridgeExtendedMinecraftEntry;
   public StatCraftedGuiButtonExtenImpl statCraftedGuiButtonExtenImpl;
   public StatCraftedGuiButtonExten statCraftedGuiButtonExten;
   public EntityNameExtendedMinecraftEntry entityNameExtendedMinecraftEntry;
   public StatFileWriter statFileWriter;
   public ExtendedMinecraftEntry extendedMinecraftEntry;
   public boolean enabled6 = true;

   public static FontRenderer resolveFontRenderer14(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRenderer13(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static StatFileWriter resolveStatFileWriter(SplineBridge splineBridge) {
      return splineBridge.statFileWriter;
   }

   private void applyItem(int count, int count2, Item item) {
      this.applyIndex(count + 1, count2 + 1);
      GlStateManager.disableLighting();
      RenderHelper.enableGUIStandardItemLighting();
      this.renderItem.renderItemAndEffectIntoGUI(new ItemStack(item, 1, 0), count + 2, count2 + 2);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.applyState8();
   }

   public static void applySplineBridgeBySplineBridge(SplineBridge splineBridge, int count, int count2, int count3, int count4, int count5, int count6) {
      splineBridge.applyIndex(count, count2, count3, count4, count5, count6);
   }

   public static FontRenderer resolveFontRenderer4(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRenderer12(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static void applySplineBridge(SplineBridge splineBridge, int count, int count2, Item item) {
      splineBridge.applyItem(count, count2, item);
   }

   public void updateState5() {
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, this.width / 2 + 4, this.height - 28, 150, 20, StatCollector.getText("gui.done", new Object[0]))
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 160, this.height - 52, 80, 20, StatCollector.getText("stat.generalButton", new Object[0])
            )
         );
      AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
      this.list
         .add(
            auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               2, this.width / 2 - 80, this.height - 52, 80, 20, StatCollector.getText("stat.blocksButton", new Object[0])
            )
         );
      AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
      this.list
         .add(
            auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               3, this.width / 2, this.height - 52, 80, 20, StatCollector.getText("stat.itemsButton", new Object[0])
            )
         );
      AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
      this.list
         .add(
            auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               4, this.width / 2 + 80, this.height - 52, 80, 20, StatCollector.getText("stat.mobsButton", new Object[0])
            )
         );
      if (this.statCraftedGuiButtonExten.size() == 0) {
         auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = false;
      }

      if (this.statCraftedGuiButtonExtenImpl.size() == 0) {
         auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = false;
      }

      if (this.entityNameExtendedMinecraftEntry.size() == 0) {
         auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = false;
      }
   }

   public void updateState6() {
      this.splineBridgeExtendedMinecraftEntry = new SplineBridgeExtendedMinecraftEntry(this, this.mc);
      this.splineBridgeExtendedMinecraftEntry.applyIndex2(1, 1);
      this.statCraftedGuiButtonExtenImpl = new StatCraftedGuiButtonExtenImpl(this, this.mc);
      this.statCraftedGuiButtonExtenImpl.applyIndex2(1, 1);
      this.statCraftedGuiButtonExten = new StatCraftedGuiButtonExten(this, this.mc);
      this.statCraftedGuiButtonExten.applyIndex2(1, 1);
      this.entityNameExtendedMinecraftEntry = new EntityNameExtendedMinecraftEntry(this, this.mc);
      this.entityNameExtendedMinecraftEntry.applyIndex2(1, 1);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.extendedMinecraftEntry = this.splineBridgeExtendedMinecraftEntry;
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
            this.extendedMinecraftEntry = this.statCraftedGuiButtonExtenImpl;
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            this.extendedMinecraftEntry = this.statCraftedGuiButtonExten;
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4) {
            this.extendedMinecraftEntry = this.entityNameExtendedMinecraftEntry;
         } else {
            this.extendedMinecraftEntry.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
         }
      }
   }

   public static FontRenderer resolveFontRenderer17(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      if (this.enabled6) {
         this.renderElement3();
         applyFontRenderer4(this.fontRenderer, StatCollector.getText("multiplayer.downloadingStats", new Object[0]), this.width / 2, this.height / 2, 16777215);
         applyFontRenderer4(
            this.fontRenderer,
            stringArray[(int)(Minecraft.getTimestamp() / 150L % (long)stringArray.length)],
            this.width / 2,
            this.height / 2 + this.fontRenderer.indexOffset * 2,
            16777215
         );
      } else {
         this.extendedMinecraftEntry.applyIndex(count, count2, factor);
         applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 20, 16777215);
         super.applyIndex(count, count2, factor);
      }
   }

   private void applyIndex(int count, int count2) {
      this.applyIndex2(count, count2, 0, 0);
   }

   public static FontRenderer resolveFontRenderer6(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      if (this.extendedMinecraftEntry != null) {
         this.extendedMinecraftEntry.updateState();
      }
   }

   public static FontRenderer resolveFontRenderer8(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("gui.stats", new Object[0]);
      this.enabled6 = true;
      this.mc.getNetHandlerPlayClient().handlePacket(new C16PacketClientStatus(AutoSoupPerformRespawnType.REQUEST_STATS));
   }

   @Override
   public void updateState() {
      if (this.enabled6) {
         this.updateState6();
         this.updateState5();
         this.extendedMinecraftEntry = this.splineBridgeExtendedMinecraftEntry;
         this.enabled6 = false;
      }
   }

   public SplineBridge(GuiScreen screen, StatFileWriter statFileWriter2) {
      super(screen);
      this.statFileWriter = statFileWriter2;
   }

   public static FontRenderer resolveFontRenderer5(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRenderer3(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRendererBySplineBridge(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static void applySplineBridge(SplineBridge splineBridge, int count, int count2, int count3, int count4, int count5, int count6) {
      splineBridge.applyIndex(count, count2, count3, count4, count5, count6);
   }

   public static FontRenderer resolveFontRenderer7(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRenderer9(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRenderer10(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRenderer2(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRenderer11(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   private void applyIndex2(int count, int count2, int count3, int count4) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation3);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + 18), (double)this.factor)
         .getWorldRenderer6((double)((float)(count3 + 0) * 0.0078125F), (double)((float)(count4 + 18) * 0.0078125F))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 18), (double)(count2 + 18), (double)this.factor)
         .getWorldRenderer6((double)((float)(count3 + 18) * 0.0078125F), (double)((float)(count4 + 18) * 0.0078125F))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 18), (double)(count2 + 0), (double)this.factor)
         .getWorldRenderer6((double)((float)(count3 + 18) * 0.0078125F), (double)((float)(count4 + 0) * 0.0078125F))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + 0), (double)this.factor)
         .getWorldRenderer6((double)((float)(count3 + 0) * 0.0078125F), (double)((float)(count4 + 0) * 0.0078125F))
         .updateState();
      tessellator.updateState();
   }

   public static FontRenderer resolveFontRenderer15(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRenderer(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   public static FontRenderer resolveFontRenderer16(SplineBridge splineBridge) {
      return splineBridge.fontRenderer;
   }

   @Override
   public boolean isActive() {
      return !this.enabled6;
   }

   public static void applySplineBridge(SplineBridge splineBridge, int count, int count2, int count3, int count4) {
      splineBridge.applyIndex2(count, count2, count3, count4);
   }
}
