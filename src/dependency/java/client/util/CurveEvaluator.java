package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.CurveEvaluatorResourceLocationAuxili;
import client.gui.GuiDoneResourceLocationAuxili;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurveEvaluator extends GuiContainer {
   public static final Logger logger2 = LogManager.getLogger();
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/container/beacon.png");
   public IInventory iInventory;
   public GuiDoneResourceLocationAuxili guiDoneResourceLocationAuxili;
   public boolean enabled10;

   public CurveEvaluator(InventoryPlayer inventoryPlayer, IInventory iInventory2) {
      super(new BezierEvaluator(inventoryPlayer, iInventory2));
      this.iInventory = iInventory2;
      this.count9 = 230;
      this.count10 = 219;
   }

   public static void applyCurveEvaluator(CurveEvaluator curveEvaluator, String text, int count, int count2) {
      curveEvaluator.applyText(text, count, count2);
   }

   @Override
   public void updateState4() {
      super.updateState4();
      int count = this.iInventory.processAction(0);
      int count2 = this.iInventory.processAction(1);
      int count3 = this.iInventory.processAction(2);
      if (this.enabled10 && count >= 0) {
         this.enabled10 = false;

         for (int index = 0; index <= 2; index++) {
            int length = TileEntityBeacon.potionArray[index].length;
            int count4 = length * 22 + (length - 1) * 2;

            for (int index2 = 0; index2 < length; index2++) {
               int indexOffset = TileEntityBeacon.potionArray[index][index2].indexOffset3;
               CurveEvaluatorResourceLocationAuxili curveEvaluatorResourceLocationAuxili = new CurveEvaluatorResourceLocationAuxili(
                  this, index << 8 | indexOffset, this.indexOffset1 + 76 + index2 * 24 - count4 / 2, this.indexOffset2 + 22 + index * 25, indexOffset, index
               );
               this.list.add(curveEvaluatorResourceLocationAuxili);
               if (index >= count) {
                  curveEvaluatorResourceLocationAuxili.enabled3 = false;
               } else if (indexOffset == count2) {
                  curveEvaluatorResourceLocationAuxili.setEnabled5(true);
               }
            }
         }

         byte byteValue = 3;
         int count5 = TileEntityBeacon.potionArray[3].length + 1;
         int count6 = count5 * 22 + (count5 - 1) * 2;

         for (int index3 = 0; index3 < count5 - 1; index3++) {
            int indexOffset2 = TileEntityBeacon.potionArray[byteValue][index3].indexOffset3;
            CurveEvaluatorResourceLocationAuxili curveEvaluatorResourceLocationAuxili2 = new CurveEvaluatorResourceLocationAuxili(
               this, byteValue << 8 | indexOffset2, this.indexOffset1 + 167 + index3 * 24 - count6 / 2, this.indexOffset2 + 47, indexOffset2, byteValue
            );
            this.list.add(curveEvaluatorResourceLocationAuxili2);
            if (byteValue >= count) {
               curveEvaluatorResourceLocationAuxili2.enabled3 = false;
            } else if (indexOffset2 == count3) {
               curveEvaluatorResourceLocationAuxili2.setEnabled5(true);
            }
         }

         if (count2 > 0) {
            CurveEvaluatorResourceLocationAuxili curveEvaluatorResourceLocationAuxili3 = new CurveEvaluatorResourceLocationAuxili(
               this, byteValue << 8 | count2, this.indexOffset1 + 167 + (count5 - 1) * 24 - count6 / 2, this.indexOffset2 + 47, count2, byteValue
            );
            this.list.add(curveEvaluatorResourceLocationAuxili3);
            if (byteValue >= count) {
               curveEvaluatorResourceLocationAuxili3.enabled3 = false;
            } else if (count2 == count3) {
               curveEvaluatorResourceLocationAuxili3.setEnabled5(true);
            }
         }
      }

      this.guiDoneResourceLocationAuxili.enabled3 = this.iInventory.processAction3(0) != null && count2 > 0;
   }

   @Override
   public void applyIndex(int count, int count2) {
      RenderHelper.disableStandardItemLighting();
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("tile.beacon.primary", new Object[0]), 62, 10, 14737632);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("tile.beacon.secondary", new Object[0]), 169, 10, 14737632);

      for (AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter : (Iterable<AuxiliaryTexturesGuiIconsPngManagerAdapter>) (Iterable) this.list) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.isEnabled()) {
            auxiliaryTexturesGuiIconsPngManagerAdapter.applyIndexByIndex(count - this.indexOffset1, count2 - this.indexOffset2);
            break;
         }
      }

      RenderHelper.enableGUIStandardItemLighting();
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.list.add(this.guiDoneResourceLocationAuxili = new GuiDoneResourceLocationAuxili(this, -1, this.indexOffset1 + 164, this.indexOffset2 + 107));
      this.list.add(new RuntimeComponentAdapter(this, -2, this.indexOffset1 + 190, this.indexOffset2 + 107));
      this.enabled10 = true;
      this.guiDoneResourceLocationAuxili.enabled3 = false;
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      net.minecraft.client.Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count10);
      this.renderItem.factor = 100.0F;
      this.renderItem.renderItemIntoGUI(new ItemStack(Items.emerald), count3 + 42, count4 + 109);
      this.renderItem.renderItemIntoGUI(new ItemStack(Items.diamond), count3 + 42 + 22, count4 + 109);
      this.renderItem.renderItemIntoGUI(new ItemStack(Items.goldIngot), count3 + 42 + 44, count4 + 109);
      this.renderItem.renderItemIntoGUI(new ItemStack(Items.ironIngot), count3 + 42 + 66, count4 + 109);
      this.renderItem.factor = 0.0F;
   }

   public static void applyCurveEvaluator2(CurveEvaluator curveEvaluator, String text, int count, int count2) {
      curveEvaluator.applyText(text, count, count2);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == -2) {
         GuiScreen screen = null;
         net.minecraft.client.Minecraft.getMinecraft().displayGuiScreen(screen);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == -1) {
         String text = "MC|Beacon";
         PacketBuffer packet = new PacketBuffer(Unpooled.buffer());
         packet.writeInt(this.iInventory.processAction(1));
         packet.writeInt(this.iInventory.processAction(2));
         net.minecraft.client.Minecraft.getMinecraft().getNetHandlerPlayClient().handlePacket(new C17PacketCustomPayload(text, packet));
         GuiScreen screen2 = null;
         net.minecraft.client.Minecraft.getMinecraft().displayGuiScreen(screen2);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter instanceof CurveEvaluatorResourceLocationAuxili) {
         if (((CurveEvaluatorResourceLocationAuxili)auxiliaryTexturesGuiIconsPngManagerAdapter).isEnabled5()) {
            return;
         }

         int count = auxiliaryTexturesGuiIconsPngManagerAdapter.count2;
         int count2 = count & 0xFF;
         if (count >> 8 < 3) {
            this.iInventory.processAction(1, count2);
         } else {
            this.iInventory.processAction(2, count2);
         }

         this.list.clear();
         this.updateState2();
         this.updateState4();
      }
   }

   public static ResourceLocation getResourceLocation5() {
      return resourceLocation5;
   }

   public static void applyCurveEvaluatorByCurveEvaluator(CurveEvaluator curveEvaluator, String text, int count, int count2) {
      curveEvaluator.applyText(text, count, count2);
   }
}
