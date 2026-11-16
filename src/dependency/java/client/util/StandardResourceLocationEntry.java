package client.util;

import client.ClientGlobals;
import client.event.AuxiliaryListener;
import client.event.RenderListener;
import client.gui.ExtendedTexturesGuiIconsPngManagerAdapter;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import client.setting.AuxiliaryOptionType;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsChristmasType;
import client.setting.ShaderOptions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.opengl.GL11;

public class StandardResourceLocationEntry extends TexturesGuiIconsPngManager {
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/misc/vignette.png");
   public static final ResourceLocation resourceLocation8 = new ResourceLocation("textures/gui/widgets.png");
   public static final ResourceLocation resourceLocation7 = new ResourceLocation("textures/misc/pumpkinblur.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("augustus", "pumpkinblur.png");
   public final Random random = new Random();
   public final Minecraft mc;
   public final RenderItem renderItem;
   public final GuiNewChat guiNewChat;
   public final CustomResourceLocationEntry customResourceLocationEntry;
   public int count4;
   public String labelText3 = "";
   public int count5;
   public boolean enabled;
   public float factor3 = 1.0F;
   public int count9;
   public ItemStack itemStack;
   public final AuxiliaryMinecraftEntry auxiliaryMinecraftEntry;
   public final ExtendedTexturesGuiIconsPngManagerAdapter extendedTexturesGuiIconsPngManagerAdapter;
   public final GuiPlayerTabOverlay guiPlayerTabOverlay;
   public int count8;
   public String labelText2 = "";
   public String labelText = "";
   public int count6;
   public int count2;
   public int count7;
   public int count = 0;
   public int count3 = 0;
   public long timestamp2 = 0L;
   public long timestamp = 0L;

   public void updateCustomItemEntry(ScoreboardData scoreboardData, ScaledResolution scaledResolution, boolean flag) {
      if ((!ClientGlobals.moduleManager.scoreboard.settingRemove.isEnabled() || !((Module)ClientGlobals.moduleManager.scoreboard).isToggled())
         && !ClientGlobals.moduleManager.antiStrike.checkCondition()) {
         ThePlayerNameManager thePlayerNameManager = scoreboardData.getThePlayerNameManager();
         Collection collection = thePlayerNameManager.resolveCollection(scoreboardData);
         ArrayList list = Lists.newArrayList(Iterables.filter(collection, new ActivePredicateAdapter(this)));
         if (list.size() > 15) {
            list = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
         }

         int index = this.getFontRendererAsFontRenderer().getIndex(scoreboardData.getLabelText());

         for (Object element : list) {
            ScorePlayerTeam scorePlayerTeam = thePlayerNameManager.getScorePlayerTeam(((ComparatorEntry)element).getLabelText());
            String text = ScorePlayerTeam.getText(scorePlayerTeam, ((ComparatorEntry)element).getLabelText())
               + ": "
               + ClientSettingsBlackType.RED
               + ((ComparatorEntry)element).getIndex();
            index = Math.max(index, this.getFontRendererAsFontRenderer().getIndex(text));
         }

         int count10 = list.size() * this.getFontRendererAsFontRenderer().indexOffset;
         int count11;
         if (((Module)ClientGlobals.moduleManager.scoreboard).isToggled()) {
            if (ClientGlobals.moduleManager.scoreboard.settingStick.isEnabled()) {
               count11 = (int)((float)count10 + GuiIngame.factor4 + 2.0F);
            } else {
               count11 = (int)((double)(scaledResolution.getScaledHeight() - count10) * (ClientGlobals.moduleManager.scoreboard.settingPositionY.getValue() / 100.0)) + count10;
            }
         } else {
            count11 = scaledResolution.getScaledHeight() / 2 + count10 / 3;
         }

         int count12 = ((Module)ClientGlobals.moduleManager.scoreboard).isToggled()
            ? (int)((double)(scaledResolution.getScaledWidth() - index - 3) * (ClientGlobals.moduleManager.scoreboard.settingPositionX.getValue() / 100.0))
            : scaledResolution.getScaledWidth() - index - 3;
         int size = list.size();
         int count13 = ((Module)ClientGlobals.moduleManager.scoreboard).isToggled()
            ? scaledResolution.getScaledWidth()
               - (
                  scaledResolution.getScaledWidth()
                     - index
                     - 3
                     - (int)((double)(scaledResolution.getScaledWidth() - index - 3) * (ClientGlobals.moduleManager.scoreboard.settingPositionX.getValue() / 100.0))
               )
            : scaledResolution.getScaledWidth() - 3 + 2;
         MovementHandler.processBoolean(
            ((Module)ClientGlobals.moduleManager.scoreboard).isToggled() && ClientGlobals.moduleManager.scoreboard.settingScale2.canExecute(),
            ((Module)ClientGlobals.moduleManager.scoreboard).isToggled() && ClientGlobals.moduleManager.scoreboard.settingScale2.canExecute2()
         );
         String text2 = "";
         ClientGlobals.moduleManager.antiStrike.applyString(text2);
         ArrayList finalList = list;
         int finalIndex = index;
         MovementHandler.applyRunnableTask(
            false,
            renderPass -> this.applyArrayList(
               flag, count12, count11, size, count13, finalList, thePlayerNameManager, scoreboardData, finalIndex, renderPass
            )
         );
      }
   }

   public void applyIChatComponent(IChatComponent iChatComponent, boolean flag) {
      this.applyText(iChatComponent.getString(), flag);
   }

   public void renderElement3() {
      this.count6 = 10;
      this.count2 = 70;
      this.count7 = 20;
   }

   public void applyScaledResolutionByScaledResolution(ScaledResolution scaledResolution, int count10) {
      this.mc.uNKNOWNManager.applyTextByText("jumpBar");
      this.mc.getTextureManager().applyResourceLocation3(TexturesGuiIconsPngManager.resourceLocation2);
      int count11 = (int)(this.mc.thePlayer.getScaleFactor18() * (float)183);
      int count12 = scaledResolution.getScaledHeight() - 32 + 3;
      this.processAction(count10, count12, 0, 84, 182, 5);
      if (count11 > 0) {
         this.processAction(count10, count12, 0, 89, count11, 5);
      }

      this.mc.uNKNOWNManager.updateState();
   }

   public void renderElement2() {
      this.guiPlayerTabOverlay.renderElement();
   }

   public void applyEntityPlayer(int count10, int count11, int count12, float factor, EntityPlayer player) {
      ItemStack stack = player.inventoryPlayer.mainInventory[count10];
      this.applyItemStack(stack, count11, count12, factor);
   }

   private void applyScaledResolution5(ScaledResolution scaledResolution) {
      if (this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         EntityPlayer renderViewEntity = (EntityPlayer)this.mc.getRenderViewEntity();
         int count10 = MathHelper.computeCount(renderViewEntity.getFloat2());
         boolean flag = this.timestamp > (long)this.count4 && (this.timestamp - (long)this.count4) / 3L % 2L == 1L;
         if (count10 < this.count && renderViewEntity.hurtResistantTime > 0) {
            this.timestamp2 = Minecraft.getTimestamp();
            this.timestamp = (long)(this.count4 + 20);
         } else if (count10 > this.count && renderViewEntity.hurtResistantTime > 0) {
            this.timestamp2 = Minecraft.getTimestamp();
            this.timestamp = (long)(this.count4 + 10);
         }

         if (Minecraft.getTimestamp() - this.timestamp2 > 1000L) {
            this.count = count10;
            this.count3 = count10;
            this.timestamp2 = Minecraft.getTimestamp();
         }

         this.count = count10;
         int count11 = this.count3;
         this.random.setSeed((long)(this.count4 * 312871));
         FoodLevelManager foodLevelManager = renderViewEntity.getFoodLevelManager();
         int index = foodLevelManager.getIndex();
         foodLevelManager.getCount3();
         AuxiliaryListener auxiliaryListener = renderViewEntity.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5);
         int count12 = scaledResolution.getScaledWidth() / 2 - 91;
         int count13 = scaledResolution.getScaledWidth() / 2 + 91;
         int count14 = scaledResolution.getScaledHeight() - 39;
         float doubleItem = (float)auxiliaryListener.getDouble();
         float scaleFactor = renderViewEntity.getScaleFactor13();
         int count15 = MathHelper.computeCount((doubleItem + scaleFactor) / 2.0F / 10.0F);
         int count16 = Math.max(10 - (count15 - 2), 3);
         int count17 = count14 - (count15 - 1) * count16 - 10;
         float factor = scaleFactor;
         int count18 = renderViewEntity.getCount4();
         int count19 = -1;
         if (renderViewEntity.checkPotion(Potion.potion11)) {
            count19 = this.count4 % MathHelper.computeCount(doubleItem + 5.0F);
         }

         this.mc.uNKNOWNManager.applyTextByText("armor");

         for (int index2 = 0; index2 < 10; index2++) {
            if (count18 > 0) {
               int count20 = count12 + index2 * 8;
               if (index2 * 2 + 1 < count18) {
                  this.processAction(count20, count17, 34, 9, 9, 9);
               }

               if (index2 * 2 + 1 == count18) {
                  this.processAction(count20, count17, 25, 9, 9, 9);
               }

               if (index2 * 2 + 1 > count18) {
                  this.processAction(count20, count17, 16, 9, 9, 9);
               }
            }
         }

         this.mc.uNKNOWNManager.applyText("health");

         for (int index3 = MathHelper.computeCount((doubleItem + scaleFactor) / 2.0F) - 1; index3 >= 0; index3--) {
            int byteValue = 16;
            if (renderViewEntity.checkPotion(Potion.potion24)) {
               byteValue += 36;
            } else if (renderViewEntity.checkPotion(Potion.potion32)) {
               byteValue += 72;
            }

            byte byteValue2 = 0;
            if (flag) {
               byteValue2 = 1;
            }

            int count21 = MathHelper.computeCount((float)(index3 + 1) / 10.0F) - 1;
            int count22 = count12 + index3 % 10 * 8;
            int count23 = count14 - count21 * count16;
            if (count10 <= 4) {
               count23 += this.random.nextInt(2);
            }

            if (index3 == count19) {
               count23 -= 2;
            }

            byte byteValue3 = 0;
            if (renderViewEntity.worldObj.getRandomSeedManager().isEnabled2()) {
               byteValue3 = 5;
            }

            this.processAction(count22, count23, 16 + byteValue2 * 9, 9 * byteValue3, 9, 9);
            if ((ClientGlobals.moduleManager.legacyAnimations.canExecute5() || flag) && !ClientGlobals.moduleManager.legacyAnimations.checkCondition2()) {
               if (index3 * 2 + 1 < count11) {
                  this.processAction(count22, count23, byteValue + 54, 9 * byteValue3, 9, 9);
               }

               if (index3 * 2 + 1 == count11) {
                  this.processAction(count22, count23, byteValue + 63, 9 * byteValue3, 9, 9);
               }
            }

            if (factor <= 0.0F) {
               if (index3 * 2 + 1 < count10) {
                  this.processAction(count22, count23, byteValue + 36, 9 * byteValue3, 9, 9);
               }

               if (index3 * 2 + 1 == count10) {
                  this.processAction(count22, count23, byteValue + 45, 9 * byteValue3, 9, 9);
               }
            } else {
               if (factor == scaleFactor && scaleFactor % 2.0F == 1.0F) {
                  this.processAction(count22, count23, byteValue + 153, 9 * byteValue3, 9, 9);
               } else {
                  this.processAction(count22, count23, byteValue + 144, 9 * byteValue3, 9, 9);
               }

               factor -= 2.0F;
            }
         }

         Entity entity = renderViewEntity.ridingEntity;
         if (entity == null) {
            this.mc.uNKNOWNManager.applyText("food");

            for (int index4 = 0; index4 < 10; index4++) {
               int count24 = count14;
               int byteValue4 = 16;
               byte byteValue5 = 0;
               if (renderViewEntity.checkPotion(Potion.potion29)) {
                  byteValue4 += 36;
                  byteValue5 = 13;
               }

               if (renderViewEntity.getFoodLevelManager().getFactor() <= 0.0F && this.count4 % (index * 3 + 1) == 0) {
                  count24 = count14 + (this.random.nextInt(3) - 1);
               }

               int count25 = count13 - index4 * 8 - 9;
               this.processAction(count25, count24, 16 + byteValue5 * 9, 27, 9, 9);
               if (index4 * 2 + 1 < index) {
                  this.processAction(count25, count24, byteValue4 + 36, 27, 9, 9);
               }

               if (index4 * 2 + 1 == index) {
                  this.processAction(count25, count24, byteValue4 + 45, 27, 9, 9);
               }
            }
         } else if (entity instanceof EntityLivingBase) {
            this.mc.uNKNOWNManager.applyText("mountHealth");
            EntityLivingBase livingEntity = (EntityLivingBase)entity;
            int count26 = (int)Math.ceil((double)livingEntity.getFloat2());
            int count27 = (int)(livingEntity.getFloat() + 0.5F) / 2;
            if (count27 > 30) {
               count27 = 30;
            }

            int count28 = count14;

            for (int byteValue6 = 0; count27 > 0; byteValue6 += 20) {
               int count29 = Math.min(count27, 10);
               count27 -= count29;

               for (int index5 = 0; index5 < count29; index5++) {
                  int count30 = count13 - index5 * 8 - 9;
                  this.processAction(count30, count28, 52, 9, 9, 9);
                  if (index5 * 2 + 1 + byteValue6 < count26) {
                     this.processAction(count30, count28, 88, 9, 9, 9);
                  }

                  if (index5 * 2 + 1 + byteValue6 == count26) {
                     this.processAction(count30, count28, 97, 9, 9, 9);
                  }
               }

               count28 -= 10;
            }
         }

         this.mc.uNKNOWNManager.applyText("air");
         if (renderViewEntity.matchesMaterial(Material.coreItemEntry1)) {
            int count31 = this.mc.thePlayer.getCount();
            int count32 = MathHelper.truncateDoubleToInt((double)(count31 - 2) * 10.0 / 300.0);
            int count33 = MathHelper.truncateDoubleToInt((double)count31 * 10.0 / 300.0) - count32;

            for (int index6 = 0; index6 < count32 + count33; index6++) {
               if (index6 < count32) {
                  this.processAction(count13 - index6 * 8 - 9, count17, 16, 18, 9, 9);
               } else {
                  this.processAction(count13 - index6 * 8 - 9, count17, 25, 18, 9, 9);
               }
            }
         }

         this.mc.uNKNOWNManager.updateState();
      }
   }

   public void renderElement() {
      if (this.count5 > 0) {
         this.count5--;
      }

      if (this.count8 > 0) {
         this.count8--;
         if (this.count8 <= 0) {
            this.labelText2 = "";
            this.labelText = "";
         }
      }

      this.count4++;
      this.customResourceLocationEntry.updateState();
      if (this.mc.thePlayer != null) {
         ItemStack slot = this.mc.thePlayer.inventoryPlayer.getCurrentItem();
         if (slot == null) {
            this.count9 = 0;
         } else if (this.itemStack != null
            && slot.getItem() == this.itemStack.getItem()
            && ItemStack.matchesItemStack2(slot, this.itemStack)
            && (slot.isActive3() || slot.getIndexOffset() == this.itemStack.getIndexOffset())) {
            if (this.count9 > 0) {
               this.count9--;
            }
         } else {
            this.count9 = 40;
         }

         this.itemStack = slot;
      }
   }

   public void applyScaledResolution3(ScaledResolution scaledResolution) {
      this.mc.uNKNOWNManager.applyTextByText("demo");
      String text;
      if (this.mc.theWorld.getTimestamp2() >= 120500L) {
         text = StatCollector.getText("demo.demoExpired", new Object[0]);
      } else {
         text = StatCollector.getText("demo.remainingTime", new Object[]{ClientBrandHelper.resolveText((int)(120500L - this.mc.theWorld.getTimestamp2()))});
      }

      int index = this.getFontRendererAsFontRenderer().getIndex(text);
      this.getFontRendererAsFontRenderer().getIndex2(text, (float)(scaledResolution.getScaledWidth() - index - 10), 5.0F, 16777215);
      this.mc.uNKNOWNManager.updateState();
   }

   public ExtendedTexturesGuiIconsPngManagerAdapter getExtendedTexturesGuiIconsPngManagerAdapter() {
      return this.extendedTexturesGuiIconsPngManagerAdapter;
   }

   private void applyScaledResolutionByScaledResolution(float factor, ScaledResolution scaledResolution) {
      float factor2 = factor;
      if (factor < 1.0F) {
         factor2 = factor * factor;
         factor2 *= factor2;
         factor2 = factor2 * 0.8F + 0.2F;
      }

      GlStateManager.applyState10();
      GlStateManager.enableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, factor2);
      this.mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
      ServiceStringEntry serviceStringEntry = this.mc.getGoldenCrystal().getCommonMapEntry().resolveServiceStringEntry(Blocks.blockPortal.getDefaultState());
      float factor4 = serviceStringEntry.getFactor5();
      float factor5 = serviceStringEntry.getFactor();
      float factor6 = serviceStringEntry.getFactor2();
      float factor7 = serviceStringEntry.getFactor6();
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(0.0, (double)scaledResolution.getScaledHeight(), -90.0).getWorldRenderer6((double)factor4, (double)factor7).updateState();
      worldRenderer.getWorldRenderer((double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight(), -90.0).getWorldRenderer6((double)factor6, (double)factor7).updateState();
      worldRenderer.getWorldRenderer((double)scaledResolution.getScaledWidth(), 0.0, -90.0).getWorldRenderer6((double)factor6, (double)factor5).updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, -90.0).getWorldRenderer6((double)factor4, (double)factor5).updateState();
      tessellator.updateState();
      GlStateManager.depthMask(true);
      GlStateManager.disableDepth();
      GlStateManager.applyState14();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void applyScaledResolutionByScaledResolution(ScaledResolution scaledResolution) {
      GlStateManager.enableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.applyState10();
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation7);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(0.0, (double)scaledResolution.getScaledHeight(), -90.0).getWorldRenderer6(0.0, 1.0).updateState();
      worldRenderer.getWorldRenderer((double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight(), -90.0).getWorldRenderer6(1.0, 1.0).updateState();
      worldRenderer.getWorldRenderer((double)scaledResolution.getScaledWidth(), 0.0, -90.0).getWorldRenderer6(1.0, 0.0).updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, -90.0).getWorldRenderer6(0.0, 0.0).updateState();
      tessellator.updateState();
      GlStateManager.depthMask(true);
      GlStateManager.disableDepth();
      GlStateManager.applyState14();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void applyText(String text, boolean flag) {
      this.labelText3 = text;
      this.count5 = 60;
      this.enabled = flag;
   }

   public FontRenderer getFontRendererAsFontRenderer() {
      return this.mc.fontRendererObj;
   }

   public int getIndex() {
      return this.count4;
   }

   public void applyScaledResolution9(ScaledResolution scaledResolution) {
      this.mc.uNKNOWNManager.applyTextByText("selectedItemName");
      if (this.count9 > 0 && this.itemStack != null) {
         String string = this.itemStack.getString();
         if (this.itemStack.isActive()) {
            string = ClientSettingsBlackType.ITALIC + string;
         }

         int count10 = (scaledResolution.getScaledWidth() - this.getFontRendererAsFontRenderer().getIndex((String)string)) / 2;
         int count11 = scaledResolution.getScaledHeight() - 59;
         if (!this.mc.playerController.processAction4()) {
            count11 += 14;
         }

         int count12 = (int)((float)this.count9 * 256.0F / 10.0F);
         if (count12 > 255) {
            count12 = 255;
         }

         if (count12 > 0) {
            GlStateManager.pushMatrix();
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            this.getFontRendererAsFontRenderer().getIndex2((String)string, (float)count10, (float)count11, 16777215 + (count12 << 24));
            GlStateManager.enableBlend();
            GlStateManager.popMatrix();
         }
      }

      this.mc.uNKNOWNManager.updateState();
   }

   private void applyScaledResolution3(float factor, ScaledResolution scaledResolution) {
      if (!BuildManager.isActive3()) {
         GlStateManager.disableDepth();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      } else {
         float factor2 = 1.0F - factor;
         factor2 = MathHelper.clamp_float(factor2, 0.0F, 1.0F);
         WorldInfo internalListEntry = this.mc.theWorld.getInternalListEntry();
         float factor4 = (float)internalListEntry.computeValueByEntity(this.mc.thePlayer);
         double offset = Math.min(internalListEntry.getValue8() * (double)internalListEntry.getCount3() * 1000.0, Math.abs(internalListEntry.getValue7() - internalListEntry.getValue3()));
         double offset2 = Math.max((double)internalListEntry.getCount2(), offset);
         float factor5;
         if ((double)factor4 < offset2) {
            factor5 = 1.0F - (float)((double)factor4 / offset2);
         } else {
            factor5 = 0.0F;
         }

         this.factor3 = (float)((double)this.factor3 + (double)(factor2 - this.factor3) * 0.01);
         GlStateManager.enableDepth();
         GlStateManager.depthMask(false);
         GlStateManager.tryBlendFuncSeparate(0, 769, 1, 0);
         if (factor5 > 0.0F) {
            GlStateManager.color4f2(0.0F, factor5, factor5, 1.0F);
         } else {
            GlStateManager.color4f2(this.factor3, this.factor3, this.factor3, 1.0F);
         }

         this.mc.getTextureManager().applyResourceLocation3(resourceLocation6);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
         worldRenderer.getWorldRenderer(0.0, (double)scaledResolution.getScaledHeight(), -90.0).getWorldRenderer6(0.0, 1.0).updateState();
         worldRenderer.getWorldRenderer((double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight(), -90.0).getWorldRenderer6(1.0, 1.0).updateState();
         worldRenderer.getWorldRenderer((double)scaledResolution.getScaledWidth(), 0.0, -90.0).getWorldRenderer6(1.0, 0.0).updateState();
         worldRenderer.getWorldRenderer(0.0, 0.0, -90.0).getWorldRenderer6(0.0, 0.0).updateState();
         tessellator.updateState();
         GlStateManager.depthMask(true);
         GlStateManager.disableDepth();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      }
   }

   public GuiNewChat getInternalLoggerEntry() {
      return this.guiNewChat;
   }

   public GuiPlayerTabOverlay getOrderingEntry() {
      return this.guiPlayerTabOverlay;
   }

   private void applyArrayList(
      boolean flag, int count10, int count11, int count12, int count13, ArrayList list, ThePlayerNameManager thePlayerNameManager, ScoreboardData scoreboardData, int count14, RenderPass renderPass
   ) {
      int count15 = 0;
      if (!flag || renderPass != RenderPass.NONE) {
         if (((Module)ClientGlobals.moduleManager.scoreboard).isToggled() && ClientGlobals.moduleManager.scoreboard.settingRounded.isEnabled()) {
            float factor = (float)(count10 - 2);
            float factor2 = (float)(count11 - count12 * this.getFontRendererAsFontRenderer().indexOffset);
            float factor4 = (float)count13;
            float factor5 = (float)count11;
            float value = (float)ClientGlobals.moduleManager.scoreboard.settingRoundFactor.getValue();
            ShaderOptions settingScale = ClientGlobals.moduleManager.scoreboard.settingScale2;
            int index = RandomUtil.getIndex(renderPass, 1342177280);
            RenderUtils.processFloat14(factor, factor2, factor4, factor5, value, 1, false, false, true, true, settingScale.getIndex(renderPass, index));
         } else {
            int count16 = count10 - 2;
            int count17 = count11 - count12 * this.getFontRendererAsFontRenderer().indexOffset;
            ShaderOptions settingScale2 = ClientGlobals.moduleManager.scoreboard.settingScale2;
            int index2 = RandomUtil.getIndex(renderPass, 1342177280);
            processAction(count16, count11, count13, count17, settingScale2.getIndex(renderPass, index2));
         }
      }

      for (Object element : list) {
         count15++;
         int count18 = count11 - count15 * this.getFontRendererAsFontRenderer().indexOffset;
         if (renderPass == RenderPass.NONE) {
            ComparatorEntry comparatorEntry = (ComparatorEntry)element;
            String text = ScorePlayerTeam.getText(thePlayerNameManager.getScorePlayerTeam(comparatorEntry.getLabelText()), comparatorEntry.getLabelText());
            String text2 = ClientSettingsBlackType.RED + "" + comparatorEntry.getIndex();
            if (text.contains(".")) {
               ClientGlobals.moduleManager.antiStrike.applyString(text);
               if (((Module)ClientGlobals.moduleManager.antiStrike).isToggled() && ClientGlobals.moduleManager.antiStrike.settingReplaceServerName.isEnabled()) {
                  String[] textArray = text.split("\\.");
                  text = TabListBotFilter.resolveTextByText(textArray[0], '§') + "Augustus.top";
               }
            }

            this.getFontRendererAsFontRenderer().getIndex(text, count10, count18, 553648127);
            if (!((Module)ClientGlobals.moduleManager.scoreboard).isToggled()) {
               this.getFontRendererAsFontRenderer().getIndex(text2, count13 - this.getFontRendererAsFontRenderer().getIndex(text2), count18, 553648127);
            }
         }

         if (count15 == list.size()) {
            if (!flag || renderPass != RenderPass.NONE) {
               if (((Module)ClientGlobals.moduleManager.scoreboard).isToggled() && ClientGlobals.moduleManager.scoreboard.settingRounded.isEnabled()) {
                  float factor6 = (float)(count10 - 2);
                  float factor7 = (float)(count18 - this.getFontRendererAsFontRenderer().indexOffset - 1);
                  float factor8 = (float)count13;
                  float factor9 = (float)(count18 - 1);
                  float value2 = (float)ClientGlobals.moduleManager.scoreboard.settingRoundFactor.getValue();
                  ShaderOptions settingScale3 = ClientGlobals.moduleManager.scoreboard.settingScale2;
                  int index3 = RandomUtil.getIndex(renderPass, 1610612736);
                  RenderUtils.processFloat14(factor6, factor7, factor8, factor9, value2, 1, true, true, false, false, settingScale3.getIndex(renderPass, index3));
                  int count19 = count10 - 2;
                  int count20 = count18 - 1;
                  ShaderOptions settingScale4 = ClientGlobals.moduleManager.scoreboard.settingScale2;
                  int index4 = RandomUtil.getIndex(renderPass, 1342177280);
                  processAction(count19, count20, count13, count18, settingScale4.getIndex(renderPass, index4));
               } else {
                  int count21 = count10 - 2;
                  int count22 = count18 - this.getFontRendererAsFontRenderer().indexOffset - 1;
                  int count23 = count18 - 1;
                  ShaderOptions settingScale5 = ClientGlobals.moduleManager.scoreboard.settingScale2;
                  int index5 = RandomUtil.getIndex(renderPass, 1610612736);
                  processAction(count21, count22, count13, count23, settingScale5.getIndex(renderPass, index5));
                  count21 = count10 - 2;
                  count22 = count18 - 1;
                  settingScale5 = ClientGlobals.moduleManager.scoreboard.settingScale2;
                  int index6 = RandomUtil.getIndex(renderPass, 1342177280);
                  processAction(count21, count22, count13, count18, settingScale5.getIndex(renderPass, index6));
               }
            }

            String labelText4 = scoreboardData.getLabelText();
            if (labelText4.contains(".")) {
               ClientGlobals.moduleManager.antiStrike.applyString6(labelText4);
               if (((Module)ClientGlobals.moduleManager.antiStrike).isToggled() && ClientGlobals.moduleManager.antiStrike.settingReplaceServerName.isEnabled()) {
                  String[] textArray2 = labelText4.split("\\.");
                  labelText4 = TabListBotFilter.resolveTextByText(textArray2[0], '§') + "Augustus.top";
               }
            } else {
               String text3 = "";
               ClientGlobals.moduleManager.antiStrike.applyString6(text3);
            }

            FontRenderer fontRendererAsFontRenderer = this.getFontRendererAsFontRenderer();
            int count24 = count10 + count14 / 2 - this.getFontRendererAsFontRenderer().getIndex(labelText4) / 2;
            int count25 = count18 - this.getFontRendererAsFontRenderer().indexOffset;
            ShaderOptions settingScale6 = ClientGlobals.moduleManager.scoreboard.settingScale2;
            int index7 = RandomUtil.getIndex(renderPass, 553648127);
            fontRendererAsFontRenderer.getIndex(labelText4, count24, count25, settingScale6.getIndex(renderPass, index7));
         }
      }
   }

   public boolean canExecute() {
      if (this.mc.gameSettings.state14 && !this.mc.thePlayer.onDisable() && !this.mc.gameSettings.state22) {
         return false;
      } else if (this.mc.playerController.isActive()) {
         if (this.mc.pointedEntity != null) {
            return true;
         } else {
            if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
               BlockPos blockPos = this.mc.objectMouseOver.getBlockPos();
               if (this.mc.theWorld.resolveTileEntity(blockPos) instanceof IInventory) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   private void applyScaledResolution11(ScaledResolution scaledResolution) {
      GlStateManager.enableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.applyState10();
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(0.0, (double)scaledResolution.getScaledHeight(), -90.0).getWorldRenderer6(0.0, 1.0).updateState();
      worldRenderer.getWorldRenderer((double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight(), -90.0).getWorldRenderer6(1.0, 1.0).updateState();
      worldRenderer.getWorldRenderer((double)scaledResolution.getScaledWidth(), 0.0, -90.0).getWorldRenderer6(1.0, 0.0).updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, -90.0).getWorldRenderer6(0.0, 0.0).updateState();
      tessellator.updateState();
      GlStateManager.depthMask(true);
      GlStateManager.disableDepth();
      GlStateManager.applyState14();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void updateState() {
      if (DefaultFloatEntry.labelText != null && DefaultFloatEntry.count > 0) {
         DefaultFloatEntry.count--;
         int scaledWidth = (((Module)ClientGlobals.moduleManager.hotbar).isToggled()
               ? new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (int)ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()
               )
               : new ScaledResolution(this.mc))
            .getScaledWidth();
         int count10 = scaledWidth / 2 - 182 / 2;
         int count11 = (int)(DefaultFloatEntry.factor * (float)183);
         this.processAction(count10, 12, 0, 74, 182, 5);
         this.processAction(count10, 12, 0, 74, 182, 5);
         if (count11 > 0) {
            this.processAction(count10, 12, 0, 79, count11, 5);
         }

         String labelText4 = DefaultFloatEntry.labelText;
         this.getFontRendererAsFontRenderer().getIndex2(labelText4, (float)(scaledWidth / 2 - this.getFontRendererAsFontRenderer().getIndex(labelText4) / 2), (float)2, 16777215);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation2);
      }
   }

   public void applyScaledResolution7(ScaledResolution scaledResolution) {
      int count10 = scaledResolution.getScaledWidth() / 2 - 91;
      this.mc.uNKNOWNManager.applyTextByText("expBar");
      this.mc.getTextureManager().applyResourceLocation3(TexturesGuiIconsPngManager.resourceLocation2);
      if (this.mc.thePlayer.getCount7() > 0) {
         int count11 = (int)(this.mc.thePlayer.factor39 * (float)183);
         int count12 = scaledResolution.getScaledHeight() - 32 + 3;
         this.processAction(count10, count12, 0, 64, 182, 5);
         if (count11 > 0) {
            this.processAction(count10, count12, 0, 69, count11, 5);
         }
      }

      this.mc.uNKNOWNManager.updateState();
      if (this.mc.thePlayer.count25 > 0) {
         this.mc.uNKNOWNManager.applyTextByText("expLevel");
         int count13 = 8453920;
         if (BuildManager.isActive33()) {
            count13 = LoadingManager.computeCount2(count13);
         }

         String text = "" + this.mc.thePlayer.count25;
         int count14 = (scaledResolution.getScaledWidth() - this.getFontRendererAsFontRenderer().getIndex(text)) / 2;
         int count15 = scaledResolution.getScaledHeight() - 31 - 4;
         this.getFontRendererAsFontRenderer().getIndex(text, count14 + 1, count15, 0);
         this.getFontRendererAsFontRenderer().getIndex(text, count14 - 1, count15, 0);
         this.getFontRendererAsFontRenderer().getIndex(text, count14, count15 + 1, 0);
         this.getFontRendererAsFontRenderer().getIndex(text, count14, count15 - 1, 0);
         this.getFontRendererAsFontRenderer().getIndex(text, count14, count15, count13);
         this.mc.uNKNOWNManager.updateState();
      }
   }

   public void applyText(String text) {
      this.applyText(StatCollector.getText("record.nowPlaying", new Object[]{text}), true);
   }

   public void applyItemStack(ItemStack stack, int count10, int count11, float factor) {
      if (stack != null) {
         float factor2 = (float)stack.itemDamage - factor;
         if (factor2 > 0.0F) {
            GlStateManager.pushMatrix();
            float factor4 = 1.0F + factor2 / 5.0F;
            GlStateManager.translate((float)(count10 + 8), (float)(count11 + 12), 0.0F);
            GlStateManager.translate3f3(1.0F / factor4, (factor4 + 1.0F) / 2.0F, 1.0F);
            GlStateManager.translate((float)(-(count10 + 8)), (float)(-(count11 + 12)), 0.0F);
         }

         this.renderItem.renderItemIntoGUI(stack, count10, count11);
         if (factor2 > 0.0F) {
            GlStateManager.popMatrix();
         }

         this.renderItem.renderItemOverlays(this.mc.fontRendererObj, stack, count10, count11);
      }
   }

   public void applyScaledResolution(ScaledResolution scaledResolution, float factor) {
      if (this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation8);
         EntityPlayer renderViewEntity = (EntityPlayer)this.mc.getRenderViewEntity();
         int count10 = scaledResolution.getScaledWidth() / 2;
         float factor2 = this.factor;
         this.factor = -90.0F;
         this.processAction(count10 - 91, scaledResolution.getScaledHeight() - 22, 0, 0, 182, 22);
         this.processAction(count10 - 91 - 1 + SlotManager.indexOffset * 20, scaledResolution.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
         this.factor = factor2;
         GlStateManager.disableLighting();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         RenderHelper.enableGUIStandardItemLighting();

         for (int index = 0; index < 9; index++) {
            int count11 = scaledResolution.getScaledWidth() / 2 - 90 + index * 20 + 2;
            int count12 = scaledResolution.getScaledHeight() - 16 - 3;
            this.applyEntityPlayer(index, count11, count12, factor, renderViewEntity);
         }

         RenderHelper.disableStandardItemLighting();
         GlStateManager.applyState8();
         GlStateManager.enableBlend();
      }
   }

   public void applyText(String text, String text2, int count10, int count11, int count12) {
      if (text == null && text2 == null && count10 < 0 && count11 < 0 && count12 < 0) {
         this.labelText2 = "";
         this.labelText = "";
         this.count8 = 0;
      } else if (text != null) {
         this.count2 = 70;
         this.labelText2 = text;
         this.count8 = this.count6 + this.count2 + this.count7;
      } else if (text2 != null) {
         this.labelText = text2;
      } else {
         if (count10 >= 0) {
            this.count6 = count10;
         }

         if (count11 >= 0) {
            this.count2 = count11;
         }

         if (count12 >= 0) {
            this.count7 = count12;
         }

         if (this.count8 > 0) {
            this.count8 = this.count6 + this.count2 + this.count7;
         }
      }
   }

   public void applyFloat(float factor) {
      ScaledResolution scaledResolution = new ScaledResolution(this.mc);
      int scaledWidth = scaledResolution.getScaledWidth();
      int scaledHeight = scaledResolution.getScaledHeight();
      this.mc.entityRenderer.applyScaledResolutionByScaledResolution(scaledResolution);
      AuxiliaryOptionType auxiliaryOptionType = AuxiliaryOptionType.RENDER2D_PRE;
      RenderListener.applyScaledResolution(factor, scaledResolution, auxiliaryOptionType);
      GlStateManager.disableBlend();
      if (BuildManager.isActive3()) {
         this.applyScaledResolution3(this.mc.thePlayer.computeFactor(factor), scaledResolution);
      } else {
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      }

      ItemStack stack = this.mc.thePlayer.inventoryPlayer.armorItemInSlot(3);
      if (HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.HALLOWEEN && this.mc.gameSettings.indexOffset64 == 0) {
         this.applyScaledResolution11(scaledResolution);
      } else if (this.mc.gameSettings.indexOffset64 == 0 && stack != null && stack.getItem() == Item.processAction2(Blocks.block146)) {
         this.applyScaledResolutionByScaledResolution(scaledResolution);
      }

      if (!this.mc.thePlayer.checkPotion(Potion.potion15)) {
         float factor2 = this.mc.thePlayer.scaleFactor16 + (this.mc.thePlayer.scaleFactor7 - this.mc.thePlayer.scaleFactor16) * factor;
         if (factor2 > 0.0F) {
            this.applyScaledResolutionByScaledResolution(factor2, scaledResolution);
         }
      }

      if (this.mc.playerController.isActive()) {
         this.extendedTexturesGuiIconsPngManagerAdapter.applyScaledResolution(scaledResolution, factor);
      } else if (!((Module)ClientGlobals.moduleManager.hotbar).isToggled() || !ClientGlobals.moduleManager.hotbar.settingCustomHotbar.isEnabled()) {
         GL11.glPushMatrix();
         if (((Module)ClientGlobals.moduleManager.hotbar).isToggled()) {
            GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
            GL11.glScaled(
               ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), 1.0
            );
         }

         this.applyScaledResolution(
            ((Module)ClientGlobals.moduleManager.hotbar).isToggled()
               ? new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (int)ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()
               )
               : scaledResolution,
            factor
         );
         GL11.glPopMatrix();
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation2);
      GlStateManager.disableBlend();
      if (this.canExecute()
         && (
            this.mc.gameSettings.indexOffset64 < 1
               || ((Module)ClientGlobals.moduleManager.crosshair).isToggled() && ClientGlobals.moduleManager.crosshair.settingThirdPerson.isEnabled()
         )) {
         GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
         GlStateManager.applyState14();
         if (!((Module)ClientGlobals.moduleManager.crosshair).isToggled() || ClientGlobals.moduleManager.crosshair.settingDefaultCrossHair.isEnabled()) {
            GL11.glPushMatrix();
            int count10 = scaledWidth;
            int count11 = scaledHeight;
            if (((Module)ClientGlobals.moduleManager.crosshair).isToggled()) {
               GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
               GL11.glScaled(ClientGlobals.moduleManager.crosshair.settingScale.getValue(), ClientGlobals.moduleManager.crosshair.settingScale.getValue(), 1.0);
               ScaledResolution scaledResolution2 = new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientGlobals.moduleManager.crosshair.settingScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientGlobals.moduleManager.crosshair.settingScale.getValue()),
                  (int)ClientGlobals.moduleManager.crosshair.settingScale.getValue()
               );
               count10 = scaledResolution2.getScaledWidth();
               count11 = scaledResolution2.getScaledHeight();
            }

            this.processAction(count10 / 2 - 7, count11 / 2 - 7, 0, 0, 16, 16);
            GL11.glPopMatrix();
         }

         GlStateManager.translate3f2(1.0F, 1.0F, 1.0F);
      }

      GlStateManager.applyState14();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      this.mc.uNKNOWNManager.applyTextByText("bossHealth");
      GL11.glPushMatrix();
      if (((Module)ClientGlobals.moduleManager.hotbar).isToggled()) {
         GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
         GL11.glScaled(ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), 1.0);
         GL11.glTranslated(0.0, (double)ClientGlobals.moduleManager.hotbar.getInt2(), 0.0);
      }

      this.updateState();
      this.mc.uNKNOWNManager.updateState();
      if (this.mc.playerController.processAction4()) {
         this.applyScaledResolution5(
            ((Module)ClientGlobals.moduleManager.hotbar).isToggled()
               ? new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (int)ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()
               )
               : scaledResolution
         );
      }

      GL11.glPopMatrix();
      GlStateManager.enableBlend();
      if (this.mc.thePlayer.getIndexOffset8() > 0) {
         this.mc.uNKNOWNManager.applyTextByText("sleep");
         GlStateManager.enableDepth();
         GlStateManager.applyState10();
         int indexOffset = this.mc.thePlayer.getIndexOffset8();
         float factor4 = (float)indexOffset / 100.0F;
         if (factor4 > 1.0F) {
            factor4 = 1.0F - (float)(indexOffset - 100) / 10.0F;
         }

         int count12 = (int)(220.0F * factor4) << 24 | 1052704;
         processAction(0, 0, scaledWidth, scaledHeight, count12);
         GlStateManager.applyState14();
         GlStateManager.disableDepth();
         this.mc.uNKNOWNManager.updateState();
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      int count13 = scaledWidth / 2 - 91;
      if (this.mc.thePlayer.isActive30()) {
         this.applyScaledResolutionByScaledResolution(scaledResolution, count13);
      } else if (this.mc.playerController.processAction()) {
         GL11.glPushMatrix();
         if (((Module)ClientGlobals.moduleManager.hotbar).isToggled()) {
            GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
            GL11.glScaled(
               ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), 1.0
            );
            GL11.glTranslated(0.0, (double)ClientGlobals.moduleManager.hotbar.getInt2(), 0.0);
         }

         this.applyScaledResolution7(
            ((Module)ClientGlobals.moduleManager.hotbar).isToggled()
               ? new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (int)ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()
               )
               : scaledResolution
         );
         GL11.glPopMatrix();
      }

      if (this.mc.gameSettings.state82 && !this.mc.playerController.isActive() && !ClientGlobals.moduleManager.antiStrike.canExecute11()) {
         GL11.glPushMatrix();
         if (((Module)ClientGlobals.moduleManager.hotbar).isToggled()) {
            GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
            GL11.glScaled(
               ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), 1.0
            );
            GL11.glTranslated(0.0, (double)ClientGlobals.moduleManager.hotbar.getInt2(), 0.0);
         }

         this.applyScaledResolution9(
            ((Module)ClientGlobals.moduleManager.hotbar).isToggled()
               ? new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (int)ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()
               )
               : scaledResolution
         );
         GL11.glPopMatrix();
      } else if (this.mc.thePlayer.isActive23()) {
         this.extendedTexturesGuiIconsPngManagerAdapter.applyScaledResolutionByScaledResolution(scaledResolution);
      }

      if (this.mc.isEnabled14()) {
         this.applyScaledResolution3(scaledResolution);
      }

      if (this.count5 > 0) {
         this.mc.uNKNOWNManager.applyTextByText("overlayMessage");
         float factor5 = (float)this.count5 - factor;
         int count14 = (int)(factor5 * 255.0F / 20.0F);
         if (count14 > 255) {
            count14 = 255;
         }

         if (count14 > 8) {
            GlStateManager.pushMatrix();
            ScaledResolution scaledResolution3 = scaledResolution;
            if (((Module)ClientGlobals.moduleManager.hotbar).isToggled()) {
               GlStateManager.translate3f3(1.0F / (float)scaledResolution.getScaleFactor(), 1.0F / (float)scaledResolution.getScaleFactor(), 1.0F);
               GlStateManager.translate3d(
                  ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue(), 1.0
               );
               GL11.glTranslated(0.0, (double)ClientGlobals.moduleManager.hotbar.getInt2(), 0.0);
               scaledResolution3 = new ScaledResolution(
                  (double)this.mc.displayWidth * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (double)this.mc.displayHeight * (1.0 / ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()),
                  (int)ClientGlobals.moduleManager.hotbar.settingHotbarScale.getValue()
               );
            }

            GlStateManager.translate((float)(scaledResolution3.getScaledWidth() / 2), (float)(scaledResolution3.getScaledHeight() - 68), 0.0F);
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            int count15 = 16777215;
            if (this.enabled) {
               count15 = MathHelper.computeCount(factor5 / 50.0F, 0.7F, 0.6F) & 16777215;
            }

            this.getFontRendererAsFontRenderer()
               .getIndex(this.labelText3, -this.getFontRendererAsFontRenderer().getIndex(this.labelText3) / 2, -4, count15 + (count14 << 24 & 0xFF000000));
            GlStateManager.enableBlend();
            GlStateManager.popMatrix();
         }

         this.mc.uNKNOWNManager.updateState();
      }

      if (this.count8 > 0) {
         this.mc.uNKNOWNManager.applyTextByText("titleAndSubtitle");
         float factor6 = (float)this.count8 - factor;
         int count16 = 255;
         if (this.count8 > this.count7 + this.count2) {
            count16 = (int)(((float)(this.count6 + this.count2 + this.count7) - factor6) * 255.0F / (float)this.count6);
         }

         if (this.count8 <= this.count7) {
            count16 = (int)(factor6 * 255.0F / (float)this.count7);
         }

         int intItem = MathHelper.getInt12(count16, 0, 255);
         if (intItem > 8) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(scaledWidth / 2), (float)(scaledHeight / 2), 0.0F);
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.pushMatrix();
            GlStateManager.translate3f3(4.0F, 4.0F, 4.0F);
            int count17 = intItem << 24 & 0xFF000000;
            this.getFontRendererAsFontRenderer()
               .computeCount(this.labelText2, (float)(-this.getFontRendererAsFontRenderer().getIndex(this.labelText2) / 2), -10.0F, 16777215 | count17, true);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.translate3f3(2.0F, 2.0F, 2.0F);
            this.getFontRendererAsFontRenderer()
               .computeCount(this.labelText, (float)(-this.getFontRendererAsFontRenderer().getIndex(this.labelText) / 2), 5.0F, 16777215 | count17, true);
            GlStateManager.popMatrix();
            GlStateManager.enableBlend();
            GlStateManager.popMatrix();
         }

         this.mc.uNKNOWNManager.updateState();
      }

      ThePlayerNameManager thePlayerNameManager = this.mc.theWorld.getThePlayerNameManager();
      ScoreboardData scoreboardData = null;
      ScorePlayerTeam scorePlayerTeam = thePlayerNameManager.getScorePlayerTeam(this.mc.thePlayer.getText());
      if (scorePlayerTeam != null) {
         int index = scorePlayerTeam.getClientSettingsBlackType().getIndex();
         if (index >= 0) {
            scoreboardData = thePlayerNameManager.getCustomItemEntry(3 + index);
         }
      }

      ScoreboardData scoreboardData2 = scoreboardData != null ? scoreboardData : thePlayerNameManager.getCustomItemEntry(1);
      if (scoreboardData2 != null && !((Module)ClientGlobals.moduleManager.scoreboard).isToggled()) {
         this.updateCustomItemEntry(scoreboardData2, scaledResolution, false);
      }

      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.applyState10();
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, (float)(scaledHeight - 48), 0.0F);
      this.mc.uNKNOWNManager.applyTextByText("chat");
      if (!((Module)ClientGlobals.moduleManager.chat).isToggled()) {
         this.guiNewChat.processAction(this.count4, true, Integer.MIN_VALUE);
      }

      this.mc.uNKNOWNManager.updateState();
      GlStateManager.popMatrix();
      ScoreboardData customItemEntry = thePlayerNameManager.getCustomItemEntry(0);
      if (this.mc.gameSettings.keyBindPlayerList.isKeyDown()
         && (!this.mc.isGamePaused() || this.mc.thePlayer.netHandlerPlayClient.getCollection().size() > 1 || customItemEntry != null)) {
         this.guiPlayerTabOverlay.setEnabled(true);
         if (!((Module)ClientGlobals.moduleManager.tabList).isToggled() && !ClientGlobals.moduleManager.antiStrike.canExecute7()) {
            this.guiPlayerTabOverlay.applyThePlayerNameManager(scaledWidth, thePlayerNameManager, customItemEntry);
         }
      } else {
         this.guiPlayerTabOverlay.setEnabled(false);
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.applyState3();
      MovementHandler.applyScaledResolution(scaledResolution, false);
      AuxiliaryOptionType auxiliaryOptionType2 = AuxiliaryOptionType.RENDER2D_POST;
      RenderListener.applyScaledResolution(factor, scaledResolution, auxiliaryOptionType2);
      GlStateManager.applyState14();
      if (this.mc.gameSettings.state14) {
         this.auxiliaryMinecraftEntry.applyScaledResolution3(scaledResolution);
      }
   }

   public StandardResourceLocationEntry(Minecraft mc2) {
      this.mc = mc2;
      this.renderItem = mc2.getRenderItem();
      this.auxiliaryMinecraftEntry = new AuxiliaryMinecraftEntry(mc2);
      this.extendedTexturesGuiIconsPngManagerAdapter = new ExtendedTexturesGuiIconsPngManagerAdapter(mc2);
      this.guiNewChat = new GuiNewChat(mc2);
      this.customResourceLocationEntry = new CustomResourceLocationEntry(mc2);
      this.guiPlayerTabOverlay = new GuiPlayerTabOverlay(mc2, this);
      this.renderElement3();
   }
}
