package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;
import org.lwjgl.util.glu.Project;

public class GuiEnchantment extends GuiContainer {
   private static final ResourceLocation ENCHANTMENT_TABLE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/enchanting_table.png");
   private static final ResourceLocation ENCHANTMENT_TABLE_BOOK_TEXTURE = new ResourceLocation("textures/entity/enchanting_table_book.png");
   private static final ModelBook MODEL_BOOK = new ModelBook();
   private final InventoryPlayer playerInventory;
   private Random random = new Random();
   private ContainerEnchantment container;
   public int ticks;
   public float flip;
   public float oFlip;
   public float flipT;
   public float flipA;
   public float open;
   public float oOpen;
   public ItemStack last;
   public final IWorldNameable nameable;

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.tickBook();
   }

   public void tickBook() {
      ItemStack stack = this.inventorySlots.getSlot(0).getStack();
      if (!ItemStack.areItemStacksEqual(stack, this.last)) {
         this.last = stack;

         do {
            this.flipT = this.flipT + (float)(this.random.nextInt(4) - this.random.nextInt(4));
         } while (!(this.flip > this.flipT + 1.0F) && !(this.flip < this.flipT - 1.0F));
      }

      this.ticks++;
      this.oFlip = this.flip;
      this.oOpen = this.open;
      boolean flag = false;

      for (int i = 0; i < 3; i++) {
         if (this.container.enchantLevels[i] != 0) {
            flag = true;
         }
      }

      if (flag) {
         this.open += 0.2F;
      } else {
         this.open -= 0.2F;
      }

      this.open = MathHelper.clamp_float(this.open, 0.0F, 1.0F);
      float f = (this.flipT - this.flip) * 0.4F;
      float f2 = 0.2F;
      f = MathHelper.clamp_float(f, -f2, f2);
      this.flipA = this.flipA + (f - this.flipA) * 0.9F;
      this.flip = this.flip + this.flipA;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      boolean isCreativeMode = this.mc.thePlayer.capabilities.isCreativeMode;
      int lapisAmount = this.container.getLapisAmount();

      for (int i = 0; i < 3; i++) {
         int n = this.container.enchantLevels[i];
         int n2 = this.container.enchantClue[i];
         int n3 = i + 1;
         if (this.isPointInRegion(60, 14 + 19 * i, 108, 17, mouseX, mouseY) && n > 0 && n2 >= 0) {
            ArrayList list = Lists.newArrayList();
            if (n2 >= 0 && Enchantment.getEnchantmentById(n2 & 0xFF) != null) {
               String translatedName = Enchantment.getEnchantmentById(n2 & 0xFF).getTranslatedName((n2 & 0xFF00) >> 8);
               list.add(EnumChatFormatting.WHITE.toString() + EnumChatFormatting.ITALIC + I18n.format("container.enchant.clue", new Object[]{translatedName}));
            }

            if (!isCreativeMode) {
               if (n2 >= 0) {
                  list.add("");
               }

               if (this.mc.thePlayer.experienceLevel < n) {
                  list.add(EnumChatFormatting.RED + "Level Requirement: " + this.container.enchantLevels[i]);
               } else {
                  String str = "";
                  if (n3 == 1) {
                     str = I18n.format("container.enchant.lapis.one", new Object[0]);
                  } else {
                     str = I18n.format("container.enchant.lapis.many", new Object[]{n3});
                  }

                  if (lapisAmount >= n3) {
                     list.add(EnumChatFormatting.GRAY + "" + str);
                  } else {
                     list.add(EnumChatFormatting.RED + "" + str);
                  }

                  if (n3 == 1) {
                     str = I18n.format("container.enchant.level.one", new Object[0]);
                  } else {
                     str = I18n.format("container.enchant.level.many", new Object[]{n3});
                  }

                  list.add(EnumChatFormatting.GRAY + "" + str);
               }
            }

            this.drawHoveringText(list, mouseX, mouseY);
            break;
         }
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      int n = (this.width - this.xSize) / 2;
      int n2 = (this.height - this.ySize) / 2;

      for (int i = 0; i < 3; i++) {
         int n3 = mouseX - (n + 60);
         int n4 = mouseY - (n2 + 14 + 19 * i);
         if (n3 >= 0 && n4 >= 0 && n3 < 108 && n4 < 19 && this.container.enchantItem(this.mc.thePlayer, i)) {
            this.mc.playerController.sendEnchantPacket(this.container.windowId, i);
         }
      }
   }

   @Override
   public void drawGuiContainerForegroundLayer(int n, int n2) {
      this.fontRendererObj.drawString(this.nameable.getDisplayName().getUnformattedText(), 12, 5, 4210752);
      this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float f, int n, int n2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(ENCHANTMENT_TABLE_GUI_TEXTURE);
      int n3 = (this.width - this.xSize) / 2;
      int n4 = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(n3, n4, 0, 0, this.xSize, this.ySize);
      GlStateManager.pushMatrix();
      GlStateManager.matrixMode(5889);
      GlStateManager.pushMatrix();
      GlStateManager.loadIdentity();
      ScaledResolution sr = new ScaledResolution(this.mc);
      GlStateManager.viewport(
         (sr.getScaledWidth() - 320) / 2 * sr.getScaleFactor(),
         (sr.getScaledHeight() - 240) / 2 * sr.getScaleFactor(),
         320 * sr.getScaleFactor(),
         240 * sr.getScaleFactor()
      );
      GlStateManager.translate(-0.34F, 0.23F, 0.0F);
      Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
      float f2 = 1.0F;
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      RenderHelper.enableStandardItemLighting();
      GlStateManager.translate(0.0F, 3.3F, -16.0F);
      GlStateManager.scale(f2, f2, f2);
      float f3 = 5.0F;
      GlStateManager.scale(f3, f3, f3);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(ENCHANTMENT_TABLE_BOOK_TEXTURE);
      GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
      float f4 = this.oOpen + (this.open - this.oOpen) * f;
      GlStateManager.translate((1.0F - f4) * 0.2F, (1.0F - f4) * 0.1F, (1.0F - f4) * 0.25F);
      GlStateManager.rotate(-(1.0F - f4) * 90.0F - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
      float f5 = this.oFlip + (this.flip - this.oFlip) * f + 0.25F;
      float f6 = this.oFlip + (this.flip - this.oFlip) * f + 0.75F;
      f5 = (f5 - (float)MathHelper.truncateDoubleToInt((double)f5)) * 1.6F - 0.3F;
      f6 = (f6 - (float)MathHelper.truncateDoubleToInt((double)f6)) * 1.6F - 0.3F;
      if (f5 < 0.0F) {
         f5 = 0.0F;
      }

      if (f6 < 0.0F) {
         f6 = 0.0F;
      }

      if (f5 > 1.0F) {
         f5 = 1.0F;
      }

      if (f6 > 1.0F) {
         f6 = 1.0F;
      }

      GlStateManager.enableRescaleNormal();
      MODEL_BOOK.render((Entity)null, 0.0F, f5, f6, f4, 0.0F, 0.0625F, true);
      GlStateManager.disableRescaleNormal();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.matrixMode(5889);
      GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      EnchantmentNameParts.getInstance().reseedRandomGenerator((long)this.container.xpSeed);
      int lapisAmount = this.container.getLapisAmount();

      for (int i = 0; i < 3; i++) {
         int n5 = n3 + 60;
         int n6 = n5 + 20;
         byte b = 86;
         String str = EnchantmentNameParts.getInstance().generateNewRandomName();
         this.zLevel = 0.0F;
         this.mc.getTextureManager().bindTexture(ENCHANTMENT_TABLE_GUI_TEXTURE);
         int n7 = this.container.enchantLevels[i];
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (n7 == 0) {
            this.drawTexturedModalRect(n5, n4 + 14 + 19 * i, 0, 185, 108, 19);
         } else {
            String str2 = "" + n7;
            FontRenderer fontRenderer = this.mc.standardGalacticFontRenderer;
            int n8 = 6839882;
            if ((lapisAmount < i + 1 || this.mc.thePlayer.experienceLevel < n7) && !this.mc.thePlayer.capabilities.isCreativeMode) {
               this.drawTexturedModalRect(n5, n4 + 14 + 19 * i, 0, 185, 108, 19);
               this.drawTexturedModalRect(n5 + 1, n4 + 15 + 19 * i, 16 * i, 239, 16, 16);
               fontRenderer.drawSplitString(str, n6, n4 + 16 + 19 * i, b, (n8 & 16711422) >> 1);
               n8 = 4226832;
            } else {
               int n9 = n - (n3 + 60);
               int n10 = n2 - (n4 + 14 + 19 * i);
               if (n9 >= 0 && n10 >= 0 && n9 < 108 && n10 < 19) {
                  this.drawTexturedModalRect(n5, n4 + 14 + 19 * i, 0, 204, 108, 19);
                  n8 = 16777088;
               } else {
                  this.drawTexturedModalRect(n5, n4 + 14 + 19 * i, 0, 166, 108, 19);
               }

               this.drawTexturedModalRect(n5 + 1, n4 + 15 + 19 * i, 16 * i, 223, 16, 16);
               fontRenderer.drawSplitString(str, n6, n4 + 16 + 19 * i, b, n8);
               n8 = 8453920;
            }

            fontRenderer = this.mc.fontRendererObj;
            fontRenderer.drawStringWithShadow(str2, (float)(n6 + 86 - fontRenderer.getStringWidth(str2)), (float)(n4 + 16 + 19 * i + 7), n8);
         }
      }
   }

   public GuiEnchantment(InventoryPlayer inventoryPlayer, World world, IWorldNameable worldNameable) {
      super(new ContainerEnchantment(inventoryPlayer, world));
      this.playerInventory = inventoryPlayer;
      this.container = (ContainerEnchantment)this.inventorySlots;
      this.nameable = worldNameable;
   }
}
