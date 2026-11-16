package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class SplineRegistry extends GuiScreen {
   public TileEntitySign tileEntitySign;
   public int count2;
   public int count3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 && auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         this.tileEntitySign.processAction5();
         GuiScreen screen = null;
         this.mc.displayGuiScreen(screen);
      }
   }

   public SplineRegistry(TileEntitySign tileEntitySign2) {
      GuiScreen.set.add(this);
      this.tileEntitySign = tileEntitySign2;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count == 200) {
         this.count3 = this.count3 - 1 & 3;
      }

      if (count == 208 || count == 28 || count == 156) {
         this.count3 = this.count3 + 1 & 3;
      }

      String string = this.tileEntitySign.iChatComponentArray[this.count3].getString();
      if (count == 14 && string.length() > 0) {
         string = string.substring(0, string.length() - 1);
      }

      if (CharArrayHolder.checkChar(character) && this.fontRenderer.getIndex(string + character) <= 90) {
         string = string + character;
      }

      this.tileEntitySign.iChatComponentArray[this.count3] = new ChatComponentText(string);
      if (count == 1) {
         this.applyAuxiliaryTexturesGuiIconsP(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      }
   }

   @Override
   public void updateState2() {
      this.list.clear();
      Keyboard.enableRepeatEvents(true);
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 100, this.height / 4 + 120, StatCollector.getText("gui.done", new Object[0])
            )
         );
      this.tileEntitySign.setEnabled(false);
   }

   @Override
   public void updateState4() {
      this.count2++;
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
      NetHandlerPlayClient netHandlerPlayClient = this.mc.getNetHandlerPlayClient();
      if (netHandlerPlayClient != null) {
         netHandlerPlayClient.handlePacket(new C12PacketUpdateSign(this.tileEntitySign.getBlockPos(), this.tileEntitySign.iChatComponentArray));
      }

      this.tileEntitySign.setEnabled(true);
   }

   @Override
   public void applyIndex(int count, int count4, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("sign.edit", new Object[0]), this.width / 2, 40, 16777215);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)(this.width / 2), 0.0F, 50.0F);
      float factor2 = 93.75F;
      GlStateManager.translate3f3(-factor2, -factor2, -factor2);
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      if (this.tileEntitySign.getBlock() == Blocks.block37) {
         GlStateManager.rotate((float)(this.tileEntitySign.getCount() * 360) / 16.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, -1.0625F, 0.0F);
      } else {
         int count5 = this.tileEntitySign.getCount();
         float factor3 = 0.0F;
         if (count5 == 2) {
            factor3 = 180.0F;
         }

         if (count5 == 4) {
            factor3 = 90.0F;
         }

         if (count5 == 5) {
            factor3 = -90.0F;
         }

         GlStateManager.rotate(factor3, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, -1.0625F, 0.0F);
      }

      if (this.count2 / 6 % 2 == 0) {
         this.tileEntitySign.count2 = this.count3;
      }

      StandardMapEntry.standardMapEntry.applyTileEntity(this.tileEntitySign, -0.5, -0.75, -0.5, 0.0F);
      this.tileEntitySign.count2 = -1;
      GlStateManager.popMatrix();
      super.applyIndex(count, count4, factor);
   }
}
