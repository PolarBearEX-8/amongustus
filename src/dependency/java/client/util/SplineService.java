package client.util;

import client.event.ComplexListener;
import client.setting.ClientSettingsBlackType;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.DiamondDust;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.util.glu.Project;

public class SplineService extends GuiContainer {
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/container/enchanting_table.png");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/entity/enchanting_table_book.png");
   public static final DiamondDust diamondDust = new DiamondDust();
   public final InventoryPlayer inventoryPlayer;
   public Random random = new Random();
   public BezierTransformer bezierTransformer;
   public int count12;
   public float factor4;
   public float factor5;
   public float factor6;
   public float factor7;
   public float factor8;
   public float factor9;
   public ItemStack itemStack4;
   public final ComplexListener complexListener;

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      int count4 = (this.width - this.count9) / 2;
      int count5 = (this.height - this.count10) / 2;

      for (int index = 0; index < 3; index++) {
         int count6 = count - (count4 + 60);
         int count7 = count2 - (count5 + 14 + 19 * index);
         if (count6 >= 0 && count7 >= 0 && count6 < 108 && count7 < 19 && this.bezierTransformer.processAction(net.minecraft.client.Minecraft.getMinecraft().thePlayer, index)) {
            net.minecraft.client.Minecraft.getMinecraft().playerController.processAction(this.bezierTransformer.indexOffset2, index);
         }
      }
   }

   public void updateState() {
      ItemStack stack = this.container.getSlot(0).getStack();
      if (!ItemStack.matchesItemStack(stack, this.itemStack4)) {
         this.itemStack4 = stack;

         do {
            this.factor6 = this.factor6 + (float)(this.random.nextInt(4) - this.random.nextInt(4));
         } while (!(this.factor4 > this.factor6 + 1.0F) && !(this.factor4 < this.factor6 - 1.0F));
      }

      this.count12++;
      this.factor5 = this.factor4;
      this.factor9 = this.factor8;
      boolean flag = false;

      for (int index = 0; index < 3; index++) {
         if (this.bezierTransformer.countArray[index] != 0) {
            flag = true;
         }
      }

      if (flag) {
         this.factor8 += 0.2F;
      } else {
         this.factor8 -= 0.2F;
      }

      this.factor8 = MathHelper.clamp_float(this.factor8, 0.0F, 1.0F);
      float factor = (this.factor6 - this.factor4) * 0.4F;
      float factor2 = 0.2F;
      float factor3 = MathHelper.clamp_float(factor, -factor2, factor2);
      this.factor7 = this.factor7 + (factor3 - this.factor7) * 0.9F;
      this.factor4 = this.factor4 + this.factor7;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      super.applyIndex(count, count2, factor);
      boolean enabled = net.minecraft.client.Minecraft.getMinecraft().thePlayer.invulnerableManager.enabled2;
      int count3 = this.bezierTransformer.getCount();

      for (int index = 0; index < 3; index++) {
         int count4 = this.bezierTransformer.countArray[index];
         int count5 = this.bezierTransformer.countArray2[index];
         int count6 = index + 1;
         if (this.checkIndex(60, 14 + 19 * index, 108, 17, count, count2) && count4 > 0 && count5 >= 0) {
            ArrayList list = Lists.newArrayList();
            if (count5 >= 0 && EnchantmentManager.resolveEnchantmentManager(count5 & 0xFF) != null) {
               String text = EnchantmentManager.resolveEnchantmentManager(count5 & 0xFF).resolveText((count5 & 0xFF00) >> 8);
               list.add(
                  ClientSettingsBlackType.WHITE.toString()
                     + ClientSettingsBlackType.ITALIC
                     + StatCollector.getText("container.enchant.clue", new Object[]{text})
               );
            }

            if (!enabled) {
               if (count5 >= 0) {
                  list.add("");
               }

               if (net.minecraft.client.Minecraft.getMinecraft().thePlayer.count25 < count4) {
                  list.add(ClientSettingsBlackType.RED + "Level Requirement: " + this.bezierTransformer.countArray[index]);
               } else {
                  String text2;
                  if (count6 == 1) {
                     text2 = StatCollector.getText("container.enchant.lapis.one", new Object[0]);
                  } else {
                     text2 = StatCollector.getText("container.enchant.lapis.many", new Object[]{count6});
                  }

                  if (count3 >= count6) {
                     list.add(ClientSettingsBlackType.GRAY + "" + text2);
                  } else {
                     list.add(ClientSettingsBlackType.RED + "" + text2);
                  }

                  String text3;
                  if (count6 == 1) {
                     text3 = StatCollector.getText("container.enchant.level.one", new Object[0]);
                  } else {
                     text3 = StatCollector.getText("container.enchant.level.many", new Object[]{count6});
                  }

                  list.add(ClientSettingsBlackType.GRAY + "" + text3);
               }
            }

            this.applyList(list, count, count2);
            break;
         }
      }
   }

   @Override
   public void applyFloat(float factor, int count, int count2) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      net.minecraft.client.Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(resourceLocation5);
      int count3 = (this.width - this.count9) / 2;
      int count4 = (this.height - this.count10) / 2;
      this.processAction(count3, count4, 0, 0, this.count9, this.count10);
      GlStateManager.pushMatrix();
      GlStateManager.matrixMode(5889);
      GlStateManager.pushMatrix();
      GlStateManager.loadIdentity();
      ScaledResolution scaledResolution = new ScaledResolution(net.minecraft.client.Minecraft.getMinecraft());
      GlStateManager.viewport(
         (scaledResolution.getScaledWidth() - 320) / 2 * scaledResolution.getScaleFactor(),
         (scaledResolution.getScaledHeight() - 240) / 2 * scaledResolution.getScaleFactor(),
         320 * scaledResolution.getScaleFactor(),
         240 * scaledResolution.getScaleFactor()
      );
      GlStateManager.translate(-0.34F, 0.23F, 0.0F);
      Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
      float factor2 = 1.0F;
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      RenderHelper.enableStandardItemLighting();
      GlStateManager.translate(0.0F, 3.3F, -16.0F);
      GlStateManager.translate3f3(factor2, factor2, factor2);
      float factor3 = 5.0F;
      GlStateManager.translate3f3(factor3, factor3, factor3);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      net.minecraft.client.Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(resourceLocation6);
      GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
      float factor10 = this.factor9 + (this.factor8 - this.factor9) * factor;
      GlStateManager.translate((1.0F - factor10) * 0.2F, (1.0F - factor10) * 0.1F, (1.0F - factor10) * 0.25F);
      GlStateManager.rotate(-(1.0F - factor10) * 90.0F - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
      float factor11 = this.factor5 + (this.factor4 - this.factor5) * factor + 0.25F;
      float factor12 = this.factor5 + (this.factor4 - this.factor5) * factor + 0.75F;
      float factor13 = (factor11 - (float)MathHelper.floor_double((double)factor11)) * 1.6F - 0.3F;
      float factor14 = (factor12 - (float)MathHelper.floor_double((double)factor12)) * 1.6F - 0.3F;
      if (factor13 < 0.0F) {
         factor13 = 0.0F;
      }

      if (factor14 < 0.0F) {
         factor14 = 0.0F;
      }

      if (factor13 > 1.0F) {
         factor13 = 1.0F;
      }

      if (factor14 > 1.0F) {
         factor14 = 1.0F;
      }

      GlStateManager.disableLighting();
      diamondDust.applyEntity((Entity)null, 0.0F, factor13, factor14, factor10, 0.0F, 0.0625F, true);
      GlStateManager.applyState8();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.matrixMode(5889);
      GlStateManager.viewport(0, 0, net.minecraft.client.Minecraft.getMinecraft().displayWidth, net.minecraft.client.Minecraft.getMinecraft().displayHeight);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      RandomController.getRandomController().setSeed((long)this.bezierTransformer.count);
      int count5 = this.bezierTransformer.getCount();

      for (int index = 0; index < 3; index++) {
         int count6 = count3 + 60;
         int count7 = count6 + 20;
         String text = RandomController.getRandomController().getText();
         this.factor = 0.0F;
         net.minecraft.client.Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(resourceLocation5);
         int count8 = this.bezierTransformer.countArray[index];
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         if (count8 == 0) {
            this.processAction(count6, count4 + 14 + 19 * index, 0, 185, 108, 19);
         } else {
            String text2 = "" + count8;
            FontRenderer fontRenderer = net.minecraft.client.Minecraft.getMinecraft().fontRenderer;
            int count9 = 6839882;
            int count10;
            if ((count5 < index + 1 || net.minecraft.client.Minecraft.getMinecraft().thePlayer.count25 < count8) && !net.minecraft.client.Minecraft.getMinecraft().thePlayer.invulnerableManager.enabled2) {
               this.processAction(count6, count4 + 14 + 19 * index, 0, 185, 108, 19);
               this.processAction(count6 + 1, count4 + 15 + 19 * index, 16 * index, 239, 16, 16);
               fontRenderer.applyText(text, count7, count4 + 16 + 19 * index, 86, (count9 & 16711422) >> 1);
               count10 = 4226832;
            } else {
               int count11 = count - (count3 + 60);
               int count13 = count2 - (count4 + 14 + 19 * index);
               if (count11 >= 0 && count13 >= 0 && count11 < 108 && count13 < 19) {
                  this.processAction(count6, count4 + 14 + 19 * index, 0, 204, 108, 19);
                  count9 = 16777088;
               } else {
                  this.processAction(count6, count4 + 14 + 19 * index, 0, 166, 108, 19);
               }

               this.processAction(count6 + 1, count4 + 15 + 19 * index, 16 * index, 223, 16, 16);
               fontRenderer.applyText(text, count7, count4 + 16 + 19 * index, 86, count9);
               count10 = 8453920;
            }

            FontRenderer fontRendererObj = net.minecraft.client.Minecraft.getMinecraft().fontRendererObj;
            fontRendererObj.getIndex2(text2, (float)(count7 + 86 - fontRendererObj.getIndex(text2)), (float)(count4 + 16 + 19 * index + 7), count10);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2) {
      this.fontRenderer.getIndex(this.complexListener.getChatComponent().getString(), 12, 5, 4210752);
      this.fontRenderer.getIndex(this.inventoryPlayer.getChatComponent().getString(), 8, this.count10 - 96 + 2, 4210752);
   }

   public SplineService(InventoryPlayer inventoryPlayer2, World world, ComplexListener complexListener2) {
      super(new BezierTransformer(inventoryPlayer2, world));
      this.inventoryPlayer = inventoryPlayer2;
      this.bezierTransformer = (BezierTransformer)this.container;
      this.complexListener = complexListener2;
   }

   @Override
   public void updateState4() {
      super.updateState4();
      this.updateState();
   }
}
