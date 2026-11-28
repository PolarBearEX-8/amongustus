package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiMerchant extends GuiContainer {
   private static final Logger LOGGER2 = LogManager.getLogger();
   private static final ResourceLocation MERCHANT_GUI_TEXTURE = new ResourceLocation("textures/gui/container/villager.png");
   private IMerchant merchant;
   private GuiMerchant.MerchantButton nextButton;
   private GuiMerchant.MerchantButton previousButton;
   private int selectedMerchantRecipe;
   private IChatComponent chatComponent;

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(MERCHANT_GUI_TEXTURE);
      this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
      MerchantRecipeList recipes = this.merchant.getRecipes(this.mc.thePlayer);
      if (recipes != null && !recipes.isEmpty()) {
         int selectedMerchantRecipe2 = this.selectedMerchantRecipe;
         if (selectedMerchantRecipe2 < 0 || selectedMerchantRecipe2 >= recipes.size()) {
            return;
         }

         if (((MerchantRecipe)recipes.get(selectedMerchantRecipe2)).isRecipeDisabled()) {
            this.mc.getTextureManager().bindTexture(MERCHANT_GUI_TEXTURE);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 21, 212, 0, 28, 21);
            this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
         }
      }
   }

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      String unformattedText = this.chatComponent.getUnformattedText();
      this.fontRendererObj.drawString(unformattedText, this.xSize / 2 - this.fontRendererObj.getStringWidth(unformattedText) / 2, 6, 4210752);
      this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
   }

   @Override
   public void initGui() {
      super.initGui();
      int n = (this.width - this.xSize) / 2;
      int n2 = (this.height - this.ySize) / 2;
      this.buttonList.add(this.nextButton = new GuiMerchant.MerchantButton(1, n + 120 + 27, n2 + 24 - 1, true));
      this.buttonList.add(this.previousButton = new GuiMerchant.MerchantButton(2, n + 36 - 19, n2 + 24 - 1, false));
      this.nextButton.enabled = false;
      this.previousButton.enabled = false;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      MerchantRecipeList recipes = this.merchant.getRecipes(this.mc.thePlayer);
      if (recipes != null && !recipes.isEmpty()) {
         int n = (this.width - this.xSize) / 2;
         int n2 = (this.height - this.ySize) / 2;
         MerchantRecipe merchantRecipe = (MerchantRecipe)recipes.get(this.selectedMerchantRecipe);
         ItemStack itemToBuy = merchantRecipe.getItemToBuy();
         ItemStack secondItemToBuy = merchantRecipe.getSecondItemToBuy();
         ItemStack itemToSell = merchantRecipe.getItemToSell();
         GlStateManager.pushMatrix();
         RenderHelper.enableGUIStandardItemLighting();
         GlStateManager.disableLighting();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableColorMaterial();
         GlStateManager.enableLighting();
         this.itemRender.zLevel = 100.0F;
         this.itemRender.renderItemAndEffectIntoGUI(itemToBuy, n + 36, n2 + 24);
         this.itemRender.renderItemOverlays(this.fontRendererObj, itemToBuy, n + 36, n2 + 24);
         if (secondItemToBuy != null) {
            this.itemRender.renderItemAndEffectIntoGUI(secondItemToBuy, n + 62, n2 + 24);
            this.itemRender.renderItemOverlays(this.fontRendererObj, secondItemToBuy, n + 62, n2 + 24);
         }

         this.itemRender.renderItemAndEffectIntoGUI(itemToSell, n + 120, n2 + 24);
         this.itemRender.renderItemOverlays(this.fontRendererObj, itemToSell, n + 120, n2 + 24);
         this.itemRender.zLevel = 0.0F;
         GlStateManager.disableLighting();
         if (this.isPointInRegion(36, 24, 16, 16, mouseX, mouseY) && itemToBuy != null) {
            this.renderToolTip(itemToBuy, mouseX, mouseY);
         } else if (secondItemToBuy != null && this.isPointInRegion(62, 24, 16, 16, mouseX, mouseY) && secondItemToBuy != null) {
            this.renderToolTip(secondItemToBuy, mouseX, mouseY);
         } else if (itemToSell != null && this.isPointInRegion(120, 24, 16, 16, mouseX, mouseY) && itemToSell != null) {
            this.renderToolTip(itemToSell, mouseX, mouseY);
         } else if (merchantRecipe.isRecipeDisabled() && (this.isPointInRegion(83, 21, 28, 21, mouseX, mouseY) || this.isPointInRegion(83, 51, 28, 21, mouseX, mouseY))) {
            this.drawCreativeTabHoveringText(I18n.format("merchant.deprecated", new Object[0]), mouseX, mouseY);
         }

         GlStateManager.popMatrix();
         GlStateManager.enableLighting();
         GlStateManager.enableDepth();
         RenderHelper.enableStandardItemLighting();
      }
   }

   public static ResourceLocation accessMERCHANT_GUI_TEXTURE() {
      return MERCHANT_GUI_TEXTURE;
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      MerchantRecipeList recipes = this.merchant.getRecipes(this.mc.thePlayer);
      if (recipes != null) {
         this.nextButton.enabled = this.selectedMerchantRecipe < recipes.size() - 1;
         this.previousButton.enabled = this.selectedMerchantRecipe > 0;
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      boolean flag = false;
      if (button == this.nextButton) {
         this.selectedMerchantRecipe++;
         MerchantRecipeList recipes = this.merchant.getRecipes(this.mc.thePlayer);
         if (recipes != null && this.selectedMerchantRecipe >= recipes.size()) {
            this.selectedMerchantRecipe = recipes.size() - 1;
         }

         flag = true;
      } else if (button == this.previousButton) {
         this.selectedMerchantRecipe--;
         if (this.selectedMerchantRecipe < 0) {
            this.selectedMerchantRecipe = 0;
         }

         flag = true;
      }

      if (flag) {
         ((ContainerMerchant)this.inventorySlots).setCurrentRecipeIndex(this.selectedMerchantRecipe);
         PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
         buf.writeInt(this.selectedMerchantRecipe);
         this.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload("MC|TrSel", buf));
      }
   }

   public GuiMerchant(InventoryPlayer inventoryPlayer, IMerchant merchant2, World world) {
      super(new ContainerMerchant(inventoryPlayer, merchant2, world));
      this.merchant = merchant2;
      this.chatComponent = merchant2.getDisplayName();
   }

   public IMerchant getMerchant() {
      return this.merchant;
   }


   public static class MerchantButton extends GuiButton {
      private final boolean forward;

      @Override
      public void drawButton(Minecraft mc, int n, int n2) {
         if (this.visible) {
            mc.getTextureManager().bindTexture(GuiMerchant.accessMERCHANT_GUI_TEXTURE());
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = n >= this.xPosition && n2 >= this.yPosition && n < this.xPosition + this.width && n2 < this.yPosition + this.height;
            int n3 = 0;
            int n4 = 176;
            if (!this.enabled) {
               n4 += this.width * 2;
            } else if (flag) {
               n4 += this.width;
            }

            if (!this.forward) {
               n3 += this.height;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, n4, n3, this.width, this.height);
         }
      }

      public MerchantButton(int n, int n2, int n3, boolean flag) {
         super(n, n2, n3, 12, 19, "");
         this.forward = flag;
      }
   }
}
