package net.minecraft.client.renderer;

import client.Client;
import client.ClientGlobals;
import client.event.EventStepHeight;
import client.event.EventStepHeightCallback;
import client.module.Module;
import client.render.RenderItemBuffer;
import client.render.RenderItemContainer;
import client.render.RenderItemHandler;
import client.render.RenderItemHolder;
import client.render.RenderItemWrapper;
import client.render.RenderPass;
import client.render.WorldRenderer;
import client.setting.BlockSolidType;
import client.setting.BlurItemsOnGroundType;
import client.setting.ClientSettingsBlackType;
import client.setting.CombatAlphaProperty;
import client.setting.CombatFactorProperty;
import client.setting.CombatTimeoutProperty;
import client.setting.CombatToggleProperty;
import client.setting.CombatWeightProperty;
import client.setting.CommonOptionType;
import client.setting.CoreOptionType;
import client.setting.CustomGlintSwordsType;
import client.setting.CustomOptionType;
import client.setting.DefaultOptionType;
import client.setting.ExternalOptionType;
import client.setting.IndirectOptionType;
import client.setting.InternalOptionType;
import client.setting.ItemPhysicsNoneType;
import client.setting.LocalOptionType;
import client.setting.MovementColorProperty;
import client.setting.MovementOffsetProperty;
import client.setting.MovementProperty;
import client.setting.RenderOptionProperty;
import client.setting.ShadowItemsOnGroundType;
import client.setting.SpecialOptionType;
import client.setting.SystemOptionType;
import client.util.AngleEvaluator;
import client.util.BuildManager;
import client.util.CurveRanker;
import client.util.Custom;
import client.util.DampenerCoordinator;
import client.util.DefaultVertexFormats;
import client.util.DistancePool;
import client.util.InvalidItemIDManager;
import client.util.ListenerAdapter27;
import client.util.LoadingManager;
import client.util.LocalIntArrayEntry;
import client.util.MovementHandler;
import client.util.PreEndHandManager;
import client.util.ResourceLocationAdapter;
import client.util.RotationBridge;
import client.util.SecondaryMapEntry;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SpecialLoggerEntry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.AuxiliaryItemEntry;
import net.minecraft.item.IronDust;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Items;
import net.minecraft.item.SpecialItemEntry;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3i;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

public class RenderItem implements IResourceManagerReloadListener {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   public boolean enabled2 = true;
   public float factor;
   public final SecondaryMapEntry secondaryMapEntry;
   public final TextureManager textureManager;
   public ResourceLocationAdapter resourceLocationAdapter = null;
   public boolean enabled3 = false;
   public IronDust ironDust = null;
   public boolean enabled4 = false;
   public boolean enabled = false;

   private void applyItem(Item item, String text) {
      this.applyItem(item, 0, text);
   }

   private void applyItemStack(ItemStack stack) {
      IBakedModel iBakedModel = this.secondaryMapEntry.resolveIBakedModel(stack);
      if (stack.getItem() != null) {
         if (!iBakedModel.canExecute()) {
            GlStateManager.translate3f3(2.0F, 2.0F, 2.0F);
         }

         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   private void applyIBakedModel(IBakedModel iBakedModel, int count, ItemStack stack) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      boolean active = Minecraft.getMinecraft().getListenerAdapter27().isActive();
      active = BuildManager.isActive46() && active;
      if (active) {
         worldRenderer.applyBlockSolidType(BlockSolidType.SOLID);
      }

      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry11);

      for (EnumFacing enumFacing : EnumFacing.VALUES) {
         this.applyWorldRenderer(worldRenderer, iBakedModel.resolveList(enumFacing), count, stack);
      }

      this.applyWorldRenderer(worldRenderer, iBakedModel.getList(), count, stack);
      tessellator.updateState();
      if (active) {
         worldRenderer.applyBlockSolidType((BlockSolidType)null);
         GlStateManager.bindTexture();
      }
   }

   public void applyItemStack(ItemStack stack, EntityLivingBase livingEntity, ItemPhysicsNoneType itemPhysicsNoneType) {
      if (stack != null && livingEntity != null) {
         IBakedModel iBakedModel = this.secondaryMapEntry.resolveIBakedModel(stack);
         if (livingEntity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)livingEntity;
            Item item = stack.getItem();
            ResourceLocationAdapter resourceLocationAdapter2 = null;
            if (item == Items.fishingRod && player.internalEntityAdapter != null) {
               resourceLocationAdapter2 = new ResourceLocationAdapter("fishing_rod_cast", "inventory");
            } else if (item == Items.bow && player.getItemStack2() != null) {
               int count = stack.processAction6() - player.getEntityId2();
               if (count >= 18) {
                  resourceLocationAdapter2 = new ResourceLocationAdapter("bow_pulling_2", "inventory");
               } else if (count > 13) {
                  resourceLocationAdapter2 = new ResourceLocationAdapter("bow_pulling_1", "inventory");
               } else if (count > 0) {
                  resourceLocationAdapter2 = new ResourceLocationAdapter("bow_pulling_0", "inventory");
               }
            }

            if (resourceLocationAdapter2 != null) {
               iBakedModel = this.secondaryMapEntry.getIronDust().resolveIBakedModel(resourceLocationAdapter2);
               this.resourceLocationAdapter = resourceLocationAdapter2;
            }
         }

         this.applyItemStack(stack, iBakedModel, itemPhysicsNoneType);
         this.resourceLocationAdapter = null;
      }
   }

   public void setEnabled2(boolean enabled5) {
      this.enabled2 = enabled5;
   }

   private void applyIBakedModel(IBakedModel iBakedModel) {
      if ((!BuildManager.isActive35() || InvalidItemIDManager.isEnabled()) && (!BuildManager.canExecute() || !PreEndHandManager.enabled12)) {
         EventStepHeight event = EventStepHeightCallback.resolveEventStepHeight(-8372020, 1.0);
         GlStateManager.depthMask(false);
         GlStateManager.glDepthFunc(514);
         GlStateManager.applyState3();
         GlStateManager.glStateAction3(768, 1);
         GL11.glDisable(3008);
         this.textureManager.applyResourceLocation3(resourceLocation);
         if (BuildManager.canExecute() && !this.enabled3) {
            RotationBridge.updateState9();
         }

         GlStateManager.matrixMode(5890);
         this.onStepHeight(iBakedModel, event);
         GlStateManager.matrixMode(5888);
         GlStateManager.glStateAction3(770, 771);
         GlStateManager.applyState13();
         GlStateManager.glDepthFunc(515);
         GlStateManager.depthMask(true);
         this.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
         if (BuildManager.canExecute() && !this.enabled3) {
            RotationBridge.updateState();
         }

         GL11.glEnable(3008);
      }
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer, LocalIntArrayEntry localIntArrayEntry, int count) {
      if (this.enabled) {
         if (localIntArrayEntry.getLocalIntArrayEntry() == null) {
            return;
         }

         localIntArrayEntry = localIntArrayEntry.getLocalIntArrayEntry();
      } else if (localIntArrayEntry.getLocalIntArrayEntry() != null) {
         this.enabled4 = true;
      }

      if (worldRenderer.isActive()) {
         worldRenderer.applyIndexArray(localIntArrayEntry.getIndexArrayAsIndexArray());
      } else {
         worldRenderer.applyIndexArray(localIntArrayEntry.getIndexArray());
      }

      worldRenderer.applyServiceStringEntry(localIntArrayEntry.getServiceStringEntry());
      worldRenderer.applyIndex(count);
      this.applyWorldRenderer(worldRenderer, localIntArrayEntry);
   }

   private void applyBlock(Block block, String text) {
      this.applyBlock(block, 0, text);
   }

   private void applyIndex(int count, int count2, boolean flag) {
      GlStateManager.translate((float)count, (float)count2, 100.0F + this.factor);
      GlStateManager.translate(8.0F, 8.0F, 0.0F);
      GlStateManager.translate3f3(1.0F, 1.0F, -1.0F);
      GlStateManager.translate3f3(0.5F, 0.5F, 0.5F);
      if (flag) {
         GlStateManager.translate3f3(40.0F, 40.0F, 40.0F);
         GlStateManager.rotate(210.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.applyState13();
      } else {
         GlStateManager.translate3f3(64.0F, 64.0F, 64.0F);
         GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.applyState3();
      }
   }

   public void applyBlock(Block block, int count, String text) {
      this.applyItem(Item.processAction2(block), count, text);
   }

   public void applyItem(Item item, int count, String text) {
      this.secondaryMapEntry.applyItem(item, count, new ResourceLocationAdapter(text, "inventory"));
   }

   public boolean matchesItemStack(ItemStack stack) {
      IBakedModel iBakedModel = this.secondaryMapEntry.resolveIBakedModel(stack);
      return iBakedModel == null ? false : iBakedModel.canExecute();
   }

   public void renderItemOverlayIntoGUI(FontRenderer fontRenderer, ItemStack stack, int count, int count2, String text) {
      if (stack != null) {
         if (stack.animationsToGo != 1 || text != null) {
            String text2 = text == null ? String.valueOf(stack.animationsToGo) : text;
            if (text == null && stack.animationsToGo < 1) {
               text2 = ClientSettingsBlackType.RED + String.valueOf(stack.animationsToGo);
            }

            GlStateManager.applyState3();
            GlStateManager.enableDepth();
            GlStateManager.enableBlend();
            fontRenderer.getIndex2(text2, (float)(count + 19 - 2 - fontRenderer.getIndex(text2)), (float)(count2 + 6 + 3), 16777215);
            GlStateManager.applyState13();
            GlStateManager.disableDepth();
            GlStateManager.disableBlend();
         }

         if (stack.isActiveAsFlag()) {
            int count3 = (int)Math.round(13.0 - (double)stack.getIndexOffset2() * 13.0 / (double)stack.processAction());
            int count4 = (int)Math.round(255.0 - (double)stack.getIndexOffset2() * 255.0 / (double)stack.processAction());
            GlStateManager.applyState3();
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.applyState10();
            GlStateManager.enableBlend();
            WorldRenderer worldRenderer = Tessellator.getTessellator().getWorldRenderer();
            this.applyWorldRenderer(worldRenderer, count + 2, count2 + 13, 13, 2, 0, 0, 0, 255);
            this.applyWorldRenderer(worldRenderer, count + 2, count2 + 13, 12, 1, (255 - count4) / 4, 64, 0, 255);
            int count5 = 255 - count4;
            int count6 = 0;
            if (BuildManager.isActive33()) {
               int count7 = LoadingManager.computeCountByIndex(count4);
               if (count7 >= 0) {
                  count5 = count7 >> 16 & 0xFF;
                  count4 = count7 >> 8 & 0xFF;
                  count6 = count7 >> 0 & 0xFF;
               }
            }

            this.applyWorldRenderer(worldRenderer, count + 2, count2 + 13, count3, 1, count5, count4, count6, 255);
            GlStateManager.disableBlend();
            GlStateManager.applyState14();
            GlStateManager.disableTexture2D();
            GlStateManager.applyState13();
            GlStateManager.disableDepth();
         }
      }
   }

   public void applyIBakedModel(IBakedModel iBakedModel, int count) {
      this.applyIBakedModel(iBakedModel, count, (ItemStack)null);
   }

   public SecondaryMapEntry getSecondaryMapEntry() {
      return this.secondaryMapEntry;
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer, int count, int count2, int count3, int count4, int count5, int count6, int count7, int count8) {
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
      worldRenderer.getWorldRenderer((double)count, (double)count2, 0.0).resolveWorldRenderer(count5, count6, count7, count8).updateState();
      worldRenderer.getWorldRenderer((double)count, (double)(count2 + count4), 0.0).resolveWorldRenderer(count5, count6, count7, count8).updateState();
      worldRenderer.getWorldRenderer((double)(count + count3), (double)(count2 + count4), 0.0).resolveWorldRenderer(count5, count6, count7, count8).updateState();
      worldRenderer.getWorldRenderer((double)(count + count3), (double)count2, 0.0).resolveWorldRenderer(count5, count6, count7, count8).updateState();
      Tessellator.getTessellator().updateState();
   }

   public void onStepHeight(IBakedModel iBakedModel, EventStepHeight event) {
      GlStateManager.pushMatrix();
      GlStateManager.translate3f3(8.0F, 8.0F, 8.0F);
      long value = (long)((double)Sys.getTime() * 1000.0 * event.getValue() / (double)Sys.getTimerResolution());
      GlStateManager.translate((float)(value % 3000L) / 3000.0F / 8.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
      if (!event.isCancelled()) {
         this.applyIBakedModel(iBakedModel, event.getCount2());
      }

      GlStateManager.popMatrix();
      GlStateManager.pushMatrix();
      GlStateManager.translate3f3(8.0F, 8.0F, 8.0F);
      GlStateManager.translate(-((float)(value % 4873L) / 4873.0F / 8.0F), 0.0F, 0.0F);
      GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
      if (!event.isCancelled()) {
         this.applyIBakedModel(iBakedModel, event.getCount2());
      }

      GlStateManager.popMatrix();
   }

   private void applyIBakedModelByIBakedModel(IBakedModel iBakedModel) {
      this.applyIBakedModel(iBakedModel);
      if (((Module)Client.getClient().getModuleManager().customGlint).isToggled()
         && Client.getClient().getModuleManager().customGlint.settingBrighterEnchantments.isEnabled()) {
         this.applyIBakedModel(iBakedModel);
      }
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer, LocalIntArrayEntry localIntArrayEntry) {
      Vec3i vec3i = localIntArrayEntry.getEnumFacing().getVec3i();
      worldRenderer.applyFloat((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
   }

   private void applyIBakedModel(IBakedModel iBakedModel, ItemStack stack) {
      this.applyIBakedModel(iBakedModel, -1, stack);
   }

   public void applyItemStack(ItemStack stack, IBakedModel iBakedModel, boolean flag, boolean flag2, boolean flag3) {
      if (stack != null) {
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(0.5F, 0.5F, 0.5F);
         if (iBakedModel.isActive()) {
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            DampenerCoordinator.dampenerCoordinator.applyItemStack(stack);
         } else {
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            if (BuildManager.isActive35()) {
               iBakedModel = InvalidItemIDManager.resolveIBakedModel(stack, iBakedModel, this.resourceLocationAdapter, false);
            }

            this.enabled4 = false;
            MovementHandler.processBoolean(
               ((Module)ClientGlobals.moduleManager.blur).isToggled()
                  && (
                     BlurItemsOnGroundType.ITEMS_ON_GROUND.isSelected() && flag
                        || BlurItemsOnGroundType.ITEMS_IN_HOTBAR.isSelected() && flag2
                        || BlurItemsOnGroundType.ITEMS_IN_HAND.isSelected() && flag3
                  ),
               ((Module)ClientGlobals.moduleManager.shadow).isToggled()
                  && (
                     ShadowItemsOnGroundType.ITEMS_ON_GROUND.isSelected() && flag
                        || ShadowItemsOnGroundType.ITEMS_IN_HOTBAR.isSelected() && flag2
                        || ShadowItemsOnGroundType.ITEMS_IN_HAND.isSelected() && flag3
                  )
            );
            MovementHandler.processAction11();
            IBakedModel capturedModel = iBakedModel;
            MovementHandler.applyRunnableTask(true, pass -> this.applyIBakedModel(capturedModel, stack, pass));
            this.applyIBakedModel(iBakedModel, stack);
            if (this.enabled4) {
               float factor2 = OpenGlHelper.factor;
               float factor3 = OpenGlHelper.factor2;
               OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, 240.0F, factor3);
               this.enabled = true;
               this.applyIBakedModel(iBakedModel, stack);
               this.enabled = false;
               OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, factor2, factor3);
            }

            if (!stack.processAction3() || BuildManager.isActive35() && InvalidItemIDManager.matchesRenderItem(this, stack, iBakedModel)) {
               if (((Module)Client.getClient().getModuleManager().customGlint).isToggled()) {
                  Item item = stack.getItem();
                  if (CustomGlintSwordsType.SWORDS.isSelected() && item instanceof ItemSword) {
                     this.applyIBakedModelByIBakedModel(iBakedModel);
                  }

                  if (CustomGlintSwordsType.TOOLS.isSelected() && (item instanceof ItemTool || item instanceof ItemShears)) {
                     this.applyIBakedModelByIBakedModel(iBakedModel);
                  }

                  if (CustomGlintSwordsType.ARMOR.isSelected() && item instanceof ItemGoldenBoots) {
                     this.applyIBakedModelByIBakedModel(iBakedModel);
                  }

                  if (CustomGlintSwordsType.REST.isSelected()
                     && !(item instanceof ItemSword)
                     && !(item instanceof ItemTool)
                     && !(item instanceof ItemShears)
                     && !(item instanceof ItemGoldenBoots)) {
                     this.applyIBakedModelByIBakedModel(iBakedModel);
                  }
               }
            } else {
               this.applyIBakedModelByIBakedModel(iBakedModel);
            }
         }

         GlStateManager.popMatrix();
      }
   }

   public void renderItemAndEffectIntoGUI(ItemStack stack, int count, int count2) {
      this.enabled3 = true;
      IBakedModel iBakedModel = this.secondaryMapEntry.resolveIBakedModel(stack);
      GlStateManager.pushMatrix();
      this.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
      this.textureManager.get(ListenerAdapter27.resourceLocation).applyFlag(false, false);
      GlStateManager.disableLighting();
      GlStateManager.applyState14();
      GlStateManager.alphaFunc(516, 0.1F);
      GlStateManager.disableBlend();
      GlStateManager.glStateAction3(770, 771);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.applyIndex(count, count2, iBakedModel.canExecute());
      iBakedModel.getSpecialItemEntry().applyItemPhysicsNoneType(ItemPhysicsNoneType.GUI);
      this.applyItemStack(stack, iBakedModel, false, true, false);
      GlStateManager.applyState10();
      GlStateManager.applyState8();
      GlStateManager.applyState3();
      GlStateManager.popMatrix();
      this.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
      this.textureManager.get(ListenerAdapter27.resourceLocation).updateState();
      this.enabled3 = false;
   }

   private boolean matchesAuxiliaryItemEntry(AuxiliaryItemEntry auxiliaryItemEntry) {
      return auxiliaryItemEntry.vector3f3.x < 0.0F ^ auxiliaryItemEntry.vector3f3.y < 0.0F ^ auxiliaryItemEntry.vector3f3.z < 0.0F;
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      this.secondaryMapEntry.updateState();
   }

   public static void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, LocalIntArrayEntry localIntArrayEntry, int count) {
      float factor2 = (float)(count & 0xFF);
      float factor3 = (float)(count >>> 8 & 0xFF);
      float factor4 = (float)(count >>> 16 & 0xFF);
      float factor5 = (float)(count >>> 24 & 0xFF);
      int[] indexArray = localIntArrayEntry.getIndexArray();
      int count2 = indexArray.length / 4;

      for (int index = 0; index < 4; index++) {
         int count3 = indexArray[3 + count2 * index];
         float factor6 = (float)(count3 & 0xFF);
         float factor7 = (float)(count3 >>> 8 & 0xFF);
         float factor8 = (float)(count3 >>> 16 & 0xFF);
         float factor9 = (float)(count3 >>> 24 & 0xFF);
         count3 = Math.min(255, (int)(factor2 * factor6 / 255.0F));
         int count4 = Math.min(255, (int)(factor3 * factor7 / 255.0F));
         int count5 = Math.min(255, (int)(factor4 * factor8 / 255.0F));
         int count6 = Math.min(255, (int)(factor5 * factor9 / 255.0F));
         worldRenderer.applyIndex(worldRenderer.computeCount(4 - index), count3, count4, count5, count6);
      }
   }

   private void applyIBakedModel(IBakedModel iBakedModel, ItemStack stack, RenderPass renderPass) {
      this.applyIBakedModel(iBakedModel, stack);
   }

   public void renderItemIntoGUI(ItemStack stack, int count, int count2) {
      if (stack != null && stack.getItem() != null) {
         this.factor += 50.0F;

         try {
            this.renderItemAndEffectIntoGUI(stack, count, count2);
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Rendering item");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Item being rendered");
            crashReportCategory.applyText("Item Type", new Custom(this, stack));
            crashReportCategory.applyText("Item Aux", new AngleEvaluator(this, stack));
            crashReportCategory.applyText("Item NBT", new CurveRanker(this, stack));
            crashReportCategory.applyText("Item Foil", new DistancePool(this, stack));
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }

         this.factor -= 50.0F;
      }
   }

   private void updateState() {
      this.applyBlock(Blocks.block51, "anvil_intact");
      this.applyBlock(Blocks.block51, 1, "anvil_slightly_damaged");
      this.applyBlock(Blocks.block51, 2, "anvil_very_damaged");
      this.applyBlock(Blocks.block141, LocalOptionType.BLACK.getCount(), "black_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.BLUE.getCount(), "blue_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.BROWN.getCount(), "brown_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.CYAN.getCount(), "cyan_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.GRAY.getCount(), "gray_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.GREEN.getCount(), "green_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.LIGHT_BLUE.getCount(), "light_blue_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.LIME.getCount(), "lime_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.MAGENTA.getCount(), "magenta_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.ORANGE.getCount(), "orange_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.PINK.getCount(), "pink_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.PURPLE.getCount(), "purple_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.RED.getCount(), "red_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.SILVER.getCount(), "silver_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.WHITE.getCount(), "white_carpet");
      this.applyBlock(Blocks.block141, LocalOptionType.YELLOW.getCount(), "yellow_carpet");
      this.applyBlock(Blocks.block63, SpecialOptionType.MOSSY.getCount(), "mossy_cobblestone_wall");
      this.applyBlock(Blocks.block63, SpecialOptionType.NORMAL.getCount(), "cobblestone_wall");
      this.applyBlock(Blocks.block115, MovementOffsetProperty.COARSE_DIRT.getCount(), "coarse_dirt");
      this.applyBlock(Blocks.block115, MovementOffsetProperty.DIRT.getCount(), "dirt");
      this.applyBlock(Blocks.block115, MovementOffsetProperty.PODZOL.getCount(), "podzol");
      this.applyBlock(Blocks.blockDoublePlant, ExternalOptionType.FERN.getCount(), "double_fern");
      this.applyBlock(Blocks.blockDoublePlant, ExternalOptionType.GRASS.getCount(), "double_grass");
      this.applyBlock(Blocks.blockDoublePlant, ExternalOptionType.PAEONIA.getCount(), "paeonia");
      this.applyBlock(Blocks.blockDoublePlant, ExternalOptionType.ROSE.getCount(), "double_rose");
      this.applyBlock(Blocks.blockDoublePlant, ExternalOptionType.SUNFLOWER.getCount(), "sunflower");
      this.applyBlock(Blocks.blockDoublePlant, ExternalOptionType.SYRINGA.getCount(), "syringa");
      this.applyBlock(Blocks.decayableManager2, MovementProperty.BIRCH.getCount(), "birch_leaves");
      this.applyBlock(Blocks.decayableManager2, MovementProperty.JUNGLE.getCount(), "jungle_leaves");
      this.applyBlock(Blocks.decayableManager2, MovementProperty.OAK.getCount(), "oak_leaves");
      this.applyBlock(Blocks.decayableManager2, MovementProperty.SPRUCE.getCount(), "spruce_leaves");
      this.applyBlock(Blocks.decayableManager, MovementProperty.ACACIA.getCount() - 4, "acacia_leaves");
      this.applyBlock(Blocks.decayableManager, MovementProperty.DARK_OAK.getCount() - 4, "dark_oak_leaves");
      this.applyBlock(Blocks.block140, MovementProperty.BIRCH.getCount(), "birch_log");
      this.applyBlock(Blocks.block140, MovementProperty.JUNGLE.getCount(), "jungle_log");
      this.applyBlock(Blocks.block140, MovementProperty.OAK.getCount(), "oak_log");
      this.applyBlock(Blocks.block140, MovementProperty.SPRUCE.getCount(), "spruce_log");
      this.applyBlock(Blocks.block104, MovementProperty.ACACIA.getCount() - 4, "acacia_log");
      this.applyBlock(Blocks.block104, MovementProperty.DARK_OAK.getCount() - 4, "dark_oak_log");
      this.applyBlock(Blocks.block22, DefaultOptionType.CHISELED_STONEBRICK.getCount(), "chiseled_brick_monster_egg");
      this.applyBlock(Blocks.block22, DefaultOptionType.COBBLESTONE.getCount(), "cobblestone_monster_egg");
      this.applyBlock(Blocks.block22, DefaultOptionType.CRACKED_STONEBRICK.getCount(), "cracked_brick_monster_egg");
      this.applyBlock(Blocks.block22, DefaultOptionType.MOSSY_STONEBRICK.getCount(), "mossy_brick_monster_egg");
      this.applyBlock(Blocks.block22, DefaultOptionType.STONE.getCount(), "stone_monster_egg");
      this.applyBlock(Blocks.block22, DefaultOptionType.STONEBRICK.getCount(), "stone_brick_monster_egg");
      this.applyBlock(Blocks.block142, MovementProperty.ACACIA.getCount(), "acacia_planks");
      this.applyBlock(Blocks.block142, MovementProperty.BIRCH.getCount(), "birch_planks");
      this.applyBlock(Blocks.block142, MovementProperty.DARK_OAK.getCount(), "dark_oak_planks");
      this.applyBlock(Blocks.block142, MovementProperty.JUNGLE.getCount(), "jungle_planks");
      this.applyBlock(Blocks.block142, MovementProperty.OAK.getCount(), "oak_planks");
      this.applyBlock(Blocks.block142, MovementProperty.SPRUCE.getCount(), "spruce_planks");
      this.applyBlock(Blocks.block58, SystemOptionType.BRICKS.getCount(), "prismarine_bricks");
      this.applyBlock(Blocks.block58, SystemOptionType.DARK.getCount(), "dark_prismarine");
      this.applyBlock(Blocks.block58, SystemOptionType.ROUGH.getCount(), "prismarine");
      this.applyBlock(Blocks.block17, CommonOptionType.CHISELED.getCount(), "chiseled_quartz_block");
      this.applyBlock(Blocks.block17, CommonOptionType.DEFAULT.getCount(), "quartz_block");
      this.applyBlock(Blocks.block17, CommonOptionType.LINES_Y.getCount(), "quartz_column");
      this.applyBlock(Blocks.immutableSetEntry2Entry2, CombatFactorProperty.ALLIUM.getCount(), "allium");
      this.applyBlock(Blocks.immutableSetEntry2Entry2, CombatFactorProperty.BLUE_ORCHID.getCount(), "blue_orchid");
      this.applyBlock(Blocks.immutableSetEntry2Entry2, CombatFactorProperty.HOUSTONIA.getCount(), "houstonia");
      this.applyBlock(Blocks.immutableSetEntry2Entry2, CombatFactorProperty.ORANGE_TULIP.getCount(), "orange_tulip");
      this.applyBlock(Blocks.immutableSetEntry2Entry2, CombatFactorProperty.OXEYE_DAISY.getCount(), "oxeye_daisy");
      this.applyBlock(Blocks.immutableSetEntry2Entry2, CombatFactorProperty.PINK_TULIP.getCount(), "pink_tulip");
      this.applyBlock(Blocks.immutableSetEntry2Entry2, CombatFactorProperty.POPPY.getCount(), "poppy");
      this.applyBlock(Blocks.immutableSetEntry2Entry2, CombatFactorProperty.RED_TULIP.getCount(), "red_tulip");
      this.applyBlock(Blocks.immutableSetEntry2Entry2, CombatFactorProperty.WHITE_TULIP.getCount(), "white_tulip");
      this.applyBlock(Blocks.blockSand, CombatAlphaProperty.RED_SAND.getCount(), "red_sand");
      this.applyBlock(Blocks.blockSand, CombatAlphaProperty.SAND.getCount(), "sand");
      this.applyBlock(Blocks.block136, InternalOptionType.CHISELED.getCount(), "chiseled_sandstone");
      this.applyBlock(Blocks.block136, InternalOptionType.DEFAULT.getCount(), "sandstone");
      this.applyBlock(Blocks.block136, InternalOptionType.SMOOTH.getCount(), "smooth_sandstone");
      this.applyBlock(Blocks.block119, CombatToggleProperty.CHISELED.getCount(), "chiseled_red_sandstone");
      this.applyBlock(Blocks.block119, CombatToggleProperty.DEFAULT.getCount(), "red_sandstone");
      this.applyBlock(Blocks.block119, CombatToggleProperty.SMOOTH.getCount(), "smooth_red_sandstone");
      this.applyBlock(Blocks.block83, MovementProperty.ACACIA.getCount(), "acacia_sapling");
      this.applyBlock(Blocks.block83, MovementProperty.BIRCH.getCount(), "birch_sapling");
      this.applyBlock(Blocks.block83, MovementProperty.DARK_OAK.getCount(), "dark_oak_sapling");
      this.applyBlock(Blocks.block83, MovementProperty.JUNGLE.getCount(), "jungle_sapling");
      this.applyBlock(Blocks.block83, MovementProperty.OAK.getCount(), "oak_sapling");
      this.applyBlock(Blocks.block83, MovementProperty.SPRUCE.getCount(), "spruce_sapling");
      this.applyBlock(Blocks.block11, 0, "sponge");
      this.applyBlock(Blocks.block11, 1, "sponge_wet");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.BLACK.getCount(), "black_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.BLUE.getCount(), "blue_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.BROWN.getCount(), "brown_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.CYAN.getCount(), "cyan_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.GRAY.getCount(), "gray_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.GREEN.getCount(), "green_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.LIGHT_BLUE.getCount(), "light_blue_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.LIME.getCount(), "lime_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.MAGENTA.getCount(), "magenta_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.ORANGE.getCount(), "orange_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.PINK.getCount(), "pink_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.PURPLE.getCount(), "purple_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.RED.getCount(), "red_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.SILVER.getCount(), "silver_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.WHITE.getCount(), "white_stained_glass");
      this.applyBlock(Blocks.blockStainedGlass, LocalOptionType.YELLOW.getCount(), "yellow_stained_glass");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.BLACK.getCount(), "black_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.BLUE.getCount(), "blue_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.BROWN.getCount(), "brown_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.CYAN.getCount(), "cyan_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.GRAY.getCount(), "gray_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.GREEN.getCount(), "green_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.LIGHT_BLUE.getCount(), "light_blue_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.LIME.getCount(), "lime_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.MAGENTA.getCount(), "magenta_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.ORANGE.getCount(), "orange_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.PINK.getCount(), "pink_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.PURPLE.getCount(), "purple_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.RED.getCount(), "red_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.SILVER.getCount(), "silver_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.WHITE.getCount(), "white_stained_glass_pane");
      this.applyBlock(Blocks.blockStainedGlassPane, LocalOptionType.YELLOW.getCount(), "yellow_stained_glass_pane");
      this.applyBlock(Blocks.block107, LocalOptionType.BLACK.getCount(), "black_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.BLUE.getCount(), "blue_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.BROWN.getCount(), "brown_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.CYAN.getCount(), "cyan_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.GRAY.getCount(), "gray_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.GREEN.getCount(), "green_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.LIGHT_BLUE.getCount(), "light_blue_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.LIME.getCount(), "lime_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.MAGENTA.getCount(), "magenta_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.ORANGE.getCount(), "orange_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.PINK.getCount(), "pink_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.PURPLE.getCount(), "purple_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.RED.getCount(), "red_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.SILVER.getCount(), "silver_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.WHITE.getCount(), "white_stained_hardened_clay");
      this.applyBlock(Blocks.block107, LocalOptionType.YELLOW.getCount(), "yellow_stained_hardened_clay");
      this.applyBlock(Blocks.stone, CustomOptionType.ANDESITE.getCount(), "andesite");
      this.applyBlock(Blocks.stone, CustomOptionType.ANDESITE_SMOOTH.getCount(), "andesite_smooth");
      this.applyBlock(Blocks.stone, CustomOptionType.DIORITE.getCount(), "diorite");
      this.applyBlock(Blocks.stone, CustomOptionType.DIORITE_SMOOTH.getCount(), "diorite_smooth");
      this.applyBlock(Blocks.stone, CustomOptionType.GRANITE.getCount(), "granite");
      this.applyBlock(Blocks.stone, CustomOptionType.GRANITE_SMOOTH.getCount(), "granite_smooth");
      this.applyBlock(Blocks.stone, CustomOptionType.STONE.getCount(), "stone");
      this.applyBlock(Blocks.block42, CombatTimeoutProperty.CRACKED.getCount(), "cracked_stonebrick");
      this.applyBlock(Blocks.block42, CombatTimeoutProperty.DEFAULT.getCount(), "stonebrick");
      this.applyBlock(Blocks.block42, CombatTimeoutProperty.CHISELED.getCount(), "chiseled_stonebrick");
      this.applyBlock(Blocks.block42, CombatTimeoutProperty.MOSSY.getCount(), "mossy_stonebrick");
      this.applyBlock(Blocks.extraBlockAdapter6, IndirectOptionType.BRICK.getCount(), "brick_slab");
      this.applyBlock(Blocks.extraBlockAdapter6, IndirectOptionType.COBBLESTONE.getCount(), "cobblestone_slab");
      this.applyBlock(Blocks.extraBlockAdapter6, IndirectOptionType.WOOD.getCount(), "old_wood_slab");
      this.applyBlock(Blocks.extraBlockAdapter6, IndirectOptionType.NETHERBRICK.getCount(), "nether_brick_slab");
      this.applyBlock(Blocks.extraBlockAdapter6, IndirectOptionType.QUARTZ.getCount(), "quartz_slab");
      this.applyBlock(Blocks.extraBlockAdapter6, IndirectOptionType.SAND.getCount(), "sandstone_slab");
      this.applyBlock(Blocks.extraBlockAdapter6, IndirectOptionType.SMOOTHBRICK.getCount(), "stone_brick_slab");
      this.applyBlock(Blocks.extraBlockAdapter6, IndirectOptionType.STONE.getCount(), "stone_slab");
      this.applyBlock(Blocks.extraBlockAdapter4, CoreOptionType.RED_SANDSTONE.getCount(), "red_sandstone_slab");
      this.applyBlock(Blocks.blockTallgrass, MovementColorProperty.DEAD_BUSH.getCount(), "dead_bush");
      this.applyBlock(Blocks.blockTallgrass, MovementColorProperty.FERN.getCount(), "fern");
      this.applyBlock(Blocks.blockTallgrass, MovementColorProperty.GRASS.getCount(), "tall_grass");
      this.applyBlock(Blocks.extraBlockAdapter2, MovementProperty.ACACIA.getCount(), "acacia_slab");
      this.applyBlock(Blocks.extraBlockAdapter2, MovementProperty.BIRCH.getCount(), "birch_slab");
      this.applyBlock(Blocks.extraBlockAdapter2, MovementProperty.DARK_OAK.getCount(), "dark_oak_slab");
      this.applyBlock(Blocks.extraBlockAdapter2, MovementProperty.JUNGLE.getCount(), "jungle_slab");
      this.applyBlock(Blocks.extraBlockAdapter2, MovementProperty.OAK.getCount(), "oak_slab");
      this.applyBlock(Blocks.extraBlockAdapter2, MovementProperty.SPRUCE.getCount(), "spruce_slab");
      this.applyBlock(Blocks.wool, LocalOptionType.BLACK.getCount(), "black_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.BLUE.getCount(), "blue_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.BROWN.getCount(), "brown_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.CYAN.getCount(), "cyan_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.GRAY.getCount(), "gray_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.GREEN.getCount(), "green_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.LIGHT_BLUE.getCount(), "light_blue_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.LIME.getCount(), "lime_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.MAGENTA.getCount(), "magenta_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.ORANGE.getCount(), "orange_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.PINK.getCount(), "pink_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.PURPLE.getCount(), "purple_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.RED.getCount(), "red_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.SILVER.getCount(), "silver_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.WHITE.getCount(), "white_wool");
      this.applyBlock(Blocks.wool, LocalOptionType.YELLOW.getCount(), "yellow_wool");
      this.applyBlock(Blocks.block34, "acacia_stairs");
      this.applyBlock(Blocks.block106, "activator_rail");
      this.applyBlock(Blocks.blockBeacon, "beacon");
      this.applyBlock(Blocks.block95, "bedrock");
      this.applyBlock(Blocks.block118, "birch_stairs");
      this.applyBlock(Blocks.block8, "bookshelf");
      this.applyBlock(Blocks.block100, "brick_block");
      this.applyBlock(Blocks.block100, "brick_block");
      this.applyBlock(Blocks.block109, "brick_stairs");
      this.applyBlock(Blocks.secondaryBlockAdapter2, "brown_mushroom");
      this.applyBlock(Blocks.blockCactus, "cactus");
      this.applyBlock(Blocks.block123, "clay");
      this.applyBlock(Blocks.block9, "coal_block");
      this.applyBlock(Blocks.block89, "coal_ore");
      this.applyBlock(Blocks.block65, "cobblestone");
      this.applyBlock(Blocks.block46, "crafting_table");
      this.applyBlock(Blocks.block5, "dark_oak_stairs");
      this.applyBlock(Blocks.blockDaylightDetectorInverted, "daylight_detector");
      this.applyBlock(Blocks.blockDeadbush, "dead_bush");
      this.applyBlock(Blocks.block40, "detector_rail");
      this.applyBlock(Blocks.block91, "diamond_block");
      this.applyBlock(Blocks.block44, "diamond_ore");
      this.applyBlock(Blocks.block129, "dispenser");
      this.applyBlock(Blocks.block69, "dropper");
      this.applyBlock(Blocks.block93, "emerald_block");
      this.applyBlock(Blocks.block70, "emerald_ore");
      this.applyBlock(Blocks.block102, "enchanting_table");
      this.applyBlock(Blocks.block52, "end_portal_frame");
      this.applyBlock(Blocks.block28, "end_stone");
      this.applyBlock(Blocks.block111, "oak_fence");
      this.applyBlock(Blocks.block38, "spruce_fence");
      this.applyBlock(Blocks.block27, "birch_fence");
      this.applyBlock(Blocks.block87, "jungle_fence");
      this.applyBlock(Blocks.block147, "dark_oak_fence");
      this.applyBlock(Blocks.block97, "acacia_fence");
      this.applyBlock(Blocks.block117, "oak_fence_gate");
      this.applyBlock(Blocks.block43, "spruce_fence_gate");
      this.applyBlock(Blocks.block73, "birch_fence_gate");
      this.applyBlock(Blocks.block31, "jungle_fence_gate");
      this.applyBlock(Blocks.block101, "dark_oak_fence_gate");
      this.applyBlock(Blocks.block116, "acacia_fence_gate");
      this.applyBlock(Blocks.block39, "furnace");
      this.applyBlock(Blocks.block68, "glass");
      this.applyBlock(Blocks.block19, "glass_pane");
      this.applyBlock(Blocks.block77, "glowstone");
      this.applyBlock(Blocks.block50, "golden_rail");
      this.applyBlock(Blocks.block35, "gold_block");
      this.applyBlock(Blocks.block121, "gold_ore");
      this.applyBlock(Blocks.blockGrass, "grass");
      this.applyBlock(Blocks.block45, "gravel");
      this.applyBlock(Blocks.block98, "hardened_clay");
      this.applyBlock(Blocks.block137, "hay_block");
      this.applyBlock(Blocks.block132, "heavy_weighted_pressure_plate");
      this.applyBlock(Blocks.blockHopper, "hopper");
      this.applyBlock(Blocks.block53, "ice");
      this.applyBlock(Blocks.block127, "iron_bars");
      this.applyBlock(Blocks.block16, "iron_block");
      this.applyBlock(Blocks.block103, "iron_ore");
      this.applyBlock(Blocks.block14, "iron_trapdoor");
      this.applyBlock(Blocks.block114, "jukebox");
      this.applyBlock(Blocks.block18, "jungle_stairs");
      this.applyBlock(Blocks.block81, "ladder");
      this.applyBlock(Blocks.block76, "lapis_block");
      this.applyBlock(Blocks.block47, "lapis_ore");
      this.applyBlock(Blocks.block48, "lever");
      this.applyBlock(Blocks.block26, "light_weighted_pressure_plate");
      this.applyBlock(Blocks.block120, "lit_pumpkin");
      this.applyBlock(Blocks.block67, "melon_block");
      this.applyBlock(Blocks.block131, "mossy_cobblestone");
      this.applyBlock(Blocks.blockMycelium, "mycelium");
      this.applyBlock(Blocks.block96, "netherrack");
      this.applyBlock(Blocks.block86, "nether_brick");
      this.applyBlock(Blocks.block134, "nether_brick_fence");
      this.applyBlock(Blocks.block24, "nether_brick_stairs");
      this.applyBlock(Blocks.block143, "noteblock");
      this.applyBlock(Blocks.block3, "oak_stairs");
      this.applyBlock(Blocks.block113, "obsidian");
      this.applyBlock(Blocks.block105, "packed_ice");
      this.applyBlock(Blocks.blockPiston2, "piston");
      this.applyBlock(Blocks.block146, "pumpkin");
      this.applyBlock(Blocks.block4, "quartz_ore");
      this.applyBlock(Blocks.block15, "quartz_stairs");
      this.applyBlock(Blocks.block13, "rail");
      this.applyBlock(Blocks.block57, "redstone_block");
      this.applyBlock(Blocks.block64, "redstone_lamp");
      this.applyBlock(Blocks.block25, "redstone_ore");
      this.applyBlock(Blocks.block12, "redstone_torch");
      this.applyBlock(Blocks.secondaryBlockAdapter, "red_mushroom");
      this.applyBlock(Blocks.block122, "sandstone_stairs");
      this.applyBlock(Blocks.block72, "red_sandstone_stairs");
      this.applyBlock(Blocks.block62, "sea_lantern");
      this.applyBlock(Blocks.block71, "slime");
      this.applyBlock(Blocks.block61, "snow");
      this.applyBlock(Blocks.block74, "snow_layer");
      this.applyBlock(Blocks.block126, "soul_sand");
      this.applyBlock(Blocks.block85, "spruce_stairs");
      this.applyBlock(Blocks.blockPiston, "sticky_piston");
      this.applyBlock(Blocks.block130, "stone_brick_stairs");
      this.applyBlock(Blocks.block75, "stone_button");
      this.applyBlock(Blocks.block23, "stone_pressure_plate");
      this.applyBlock(Blocks.block125, "stone_stairs");
      this.applyBlock(Blocks.block135, "tnt");
      this.applyBlock(Blocks.block21, "torch");
      this.applyBlock(Blocks.block30, "trapdoor");
      this.applyBlock(Blocks.blockTripwireHook, "tripwire_hook");
      this.applyBlock(Blocks.block144, "vine");
      this.applyBlock(Blocks.block33, "waterlily");
      this.applyBlock(Blocks.web, "web");
      this.applyBlock(Blocks.block59, "wooden_button");
      this.applyBlock(Blocks.block94, "wooden_pressure_plate");
      this.applyBlock(Blocks.immutableSetEntry2Entry, CombatFactorProperty.DANDELION.getCount(), "dandelion");
      this.applyBlock(Blocks.blockTrappedChest, "chest");
      this.applyBlock(Blocks.block128, "trapped_chest");
      this.applyBlock(Blocks.block60, "ender_chest");
      this.applyItem(Items.ironShovel, "iron_shovel");
      this.applyItem(Items.ironPickaxe, "iron_pickaxe");
      this.applyItem(Items.ironAxe, "iron_axe");
      this.applyItem(Items.flintAndSteel, "flint_and_steel");
      this.applyItem(Items.apple, "apple");
      this.applyItem(Items.bow, 0, "bow");
      this.applyItem(Items.bow, 1, "bow_pulling_0");
      this.applyItem(Items.bow, 2, "bow_pulling_1");
      this.applyItem(Items.bow, 3, "bow_pulling_2");
      this.applyItem(Items.arrow, "arrow");
      this.applyItem(Items.coal, 0, "coal");
      this.applyItem(Items.coal, 1, "charcoal");
      this.applyItem(Items.diamond, "diamond");
      this.applyItem(Items.ironIngot, "iron_ingot");
      this.applyItem(Items.goldIngot, "gold_ingot");
      this.applyItem(Items.ironSword, "iron_sword");
      this.applyItem(Items.woodenSword, "wooden_sword");
      this.applyItem(Items.woodenShovel, "wooden_shovel");
      this.applyItem(Items.woodenPickaxe, "wooden_pickaxe");
      this.applyItem(Items.woodenAxe, "wooden_axe");
      this.applyItem(Items.stoneSword, "stone_sword");
      this.applyItem(Items.stoneShovel, "stone_shovel");
      this.applyItem(Items.stonePickaxe, "stone_pickaxe");
      this.applyItem(Items.stoneAxe, "stone_axe");
      this.applyItem(Items.diamondSword, "diamond_sword");
      this.applyItem(Items.diamondShovel, "diamond_shovel");
      this.applyItem(Items.diamondPickaxe, "diamond_pickaxe");
      this.applyItem(Items.diamondAxe, "diamond_axe");
      this.applyItem(Items.stick, "stick");
      this.applyItem(Items.bowl, "bowl");
      this.applyItem(Items.mushroomStew, "mushroom_stew");
      this.applyItem(Items.goldenSword, "golden_sword");
      this.applyItem(Items.goldenShovel, "golden_shovel");
      this.applyItem(Items.goldenPickaxe, "golden_pickaxe");
      this.applyItem(Items.goldenAxe, "golden_axe");
      this.applyItem(Items.string, "string");
      this.applyItem(Items.feather, "feather");
      this.applyItem(Items.gunpowder, "gunpowder");
      this.applyItem(Items.woodenHoe, "wooden_hoe");
      this.applyItem(Items.stoneHoe, "stone_hoe");
      this.applyItem(Items.ironHoe, "iron_hoe");
      this.applyItem(Items.diamondHoe, "diamond_hoe");
      this.applyItem(Items.goldenHoe, "golden_hoe");
      this.applyItem(Items.wheatSeeds, "wheat_seeds");
      this.applyItem(Items.wheat, "wheat");
      this.applyItem(Items.bread, "bread");
      this.applyItem(Items.leatherHelmet, "leather_helmet");
      this.applyItem(Items.leatherChestplate, "leather_chestplate");
      this.applyItem(Items.leatherLeggings, "leather_leggings");
      this.applyItem(Items.leatherBoots, "leather_boots");
      this.applyItem(Items.chainmailHelmet, "chainmail_helmet");
      this.applyItem(Items.chainmailChestplate, "chainmail_chestplate");
      this.applyItem(Items.chainmailLeggings, "chainmail_leggings");
      this.applyItem(Items.chainmailBoots, "chainmail_boots");
      this.applyItem(Items.ironHelmet, "iron_helmet");
      this.applyItem(Items.ironChestplate, "iron_chestplate");
      this.applyItem(Items.ironLeggings, "iron_leggings");
      this.applyItem(Items.ironBoots, "iron_boots");
      this.applyItem(Items.diamondHelmet, "diamond_helmet");
      this.applyItem(Items.diamondChestplate, "diamond_chestplate");
      this.applyItem(Items.diamondLeggings, "diamond_leggings");
      this.applyItem(Items.diamondBoots, "diamond_boots");
      this.applyItem(Items.goldenHelmet, "golden_helmet");
      this.applyItem(Items.goldenChestplate, "golden_chestplate");
      this.applyItem(Items.goldenLeggings, "golden_leggings");
      this.applyItem(Items.goldenBoots, "golden_boots");
      this.applyItem(Items.flint, "flint");
      this.applyItem(Items.porkchop, "porkchop");
      this.applyItem(Items.cookedPorkchop, "cooked_porkchop");
      this.applyItem(Items.painting, "painting");
      this.applyItem(Items.goldenApple, "golden_apple");
      this.applyItem(Items.goldenApple, 1, "golden_apple");
      this.applyItem(Items.sign, "sign");
      this.applyItem(Items.woodenDoor, "oak_door");
      this.applyItem(Items.spruceDoor, "spruce_door");
      this.applyItem(Items.birchDoor, "birch_door");
      this.applyItem(Items.jungleDoor, "jungle_door");
      this.applyItem(Items.acaciaDoor, "acacia_door");
      this.applyItem(Items.darkOakDoor, "dark_oak_door");
      this.applyItem(Items.bucket, "bucket");
      this.applyItem(Items.waterBucket, "water_bucket");
      this.applyItem(Items.lavaBucket, "lava_bucket");
      this.applyItem(Items.minecart, "minecart");
      this.applyItem(Items.saddle, "saddle");
      this.applyItem(Items.ironDoor, "iron_door");
      this.applyItem(Items.redstone, "redstone");
      this.applyItem(Items.snowball, "snowball");
      this.applyItem(Items.boat, "boat");
      this.applyItem(Items.leather, "leather");
      this.applyItem(Items.milkBucket, "milk_bucket");
      this.applyItem(Items.brick, "brick");
      this.applyItem(Items.clayBall, "clay_ball");
      this.applyItem(Items.reeds, "reeds");
      this.applyItem(Items.paper, "paper");
      this.applyItem(Items.book, "book");
      this.applyItem(Items.slimeBall, "slime_ball");
      this.applyItem(Items.chestMinecart, "chest_minecart");
      this.applyItem(Items.furnaceMinecart, "furnace_minecart");
      this.applyItem(Items.egg, "egg");
      this.applyItem(Items.compass, "compass");
      this.applyItem(Items.fishingRod, "fishing_rod");
      this.applyItem(Items.fishingRod, 1, "fishing_rod_cast");
      this.applyItem(Items.clock, "clock");
      this.applyItem(Items.glowstoneDust, "glowstone_dust");
      this.applyItem(Items.fish, CombatWeightProperty.COD.getCount2(), "cod");
      this.applyItem(Items.fish, CombatWeightProperty.SALMON.getCount2(), "salmon");
      this.applyItem(Items.fish, CombatWeightProperty.CLOWNFISH.getCount2(), "clownfish");
      this.applyItem(Items.fish, CombatWeightProperty.PUFFERFISH.getCount2(), "pufferfish");
      this.applyItem(Items.cookedFish, CombatWeightProperty.COD.getCount2(), "cooked_cod");
      this.applyItem(Items.cookedFish, CombatWeightProperty.SALMON.getCount2(), "cooked_salmon");
      this.applyItem(Items.dye, LocalOptionType.BLACK.getCount2(), "dye_black");
      this.applyItem(Items.dye, LocalOptionType.RED.getCount2(), "dye_red");
      this.applyItem(Items.dye, LocalOptionType.GREEN.getCount2(), "dye_green");
      this.applyItem(Items.dye, LocalOptionType.BROWN.getCount2(), "dye_brown");
      this.applyItem(Items.dye, LocalOptionType.BLUE.getCount2(), "dye_blue");
      this.applyItem(Items.dye, LocalOptionType.PURPLE.getCount2(), "dye_purple");
      this.applyItem(Items.dye, LocalOptionType.CYAN.getCount2(), "dye_cyan");
      this.applyItem(Items.dye, LocalOptionType.SILVER.getCount2(), "dye_silver");
      this.applyItem(Items.dye, LocalOptionType.GRAY.getCount2(), "dye_gray");
      this.applyItem(Items.dye, LocalOptionType.PINK.getCount2(), "dye_pink");
      this.applyItem(Items.dye, LocalOptionType.LIME.getCount2(), "dye_lime");
      this.applyItem(Items.dye, LocalOptionType.YELLOW.getCount2(), "dye_yellow");
      this.applyItem(Items.dye, LocalOptionType.LIGHT_BLUE.getCount2(), "dye_light_blue");
      this.applyItem(Items.dye, LocalOptionType.MAGENTA.getCount2(), "dye_magenta");
      this.applyItem(Items.dye, LocalOptionType.ORANGE.getCount2(), "dye_orange");
      this.applyItem(Items.dye, LocalOptionType.WHITE.getCount2(), "dye_white");
      this.applyItem(Items.bone, "bone");
      this.applyItem(Items.sugar, "sugar");
      this.applyItem(Items.cake, "cake");
      this.applyItem(Items.bed, "bed");
      this.applyItem(Items.repeater, "repeater");
      this.applyItem(Items.cookie, "cookie");
      this.applyItem(Items.shears, "shears");
      this.applyItem(Items.melon, "melon");
      this.applyItem(Items.pumpkinSeeds, "pumpkin_seeds");
      this.applyItem(Items.melonSeeds, "melon_seeds");
      this.applyItem(Items.beef, "beef");
      this.applyItem(Items.cookedBeef, "cooked_beef");
      this.applyItem(Items.chicken, "chicken");
      this.applyItem(Items.cookedChicken, "cooked_chicken");
      this.applyItem(Items.rabbit, "rabbit");
      this.applyItem(Items.cookedRabbit, "cooked_rabbit");
      this.applyItem(Items.mutton, "mutton");
      this.applyItem(Items.cookedMutton, "cooked_mutton");
      this.applyItem(Items.rabbitFoot, "rabbit_foot");
      this.applyItem(Items.rabbitHide, "rabbit_hide");
      this.applyItem(Items.rabbitStew, "rabbit_stew");
      this.applyItem(Items.rottenFlesh, "rotten_flesh");
      this.applyItem(Items.enderPearl, "ender_pearl");
      this.applyItem(Items.blazeRod, "blaze_rod");
      this.applyItem(Items.ghastTear, "ghast_tear");
      this.applyItem(Items.goldNugget, "gold_nugget");
      this.applyItem(Items.netherWart, "nether_wart");
      this.secondaryMapEntry.put(Items.potion, new RenderItemHolder(this));
      this.applyItem(Items.glassBottle, "glass_bottle");
      this.applyItem(Items.spiderEye, "spider_eye");
      this.applyItem(Items.fermentedSpiderEye, "fermented_spider_eye");
      this.applyItem(Items.blazePowder, "blaze_powder");
      this.applyItem(Items.magmaCream, "magma_cream");
      this.applyItem(Items.brewingStand, "brewing_stand");
      this.applyItem(Items.cauldron, "cauldron");
      this.applyItem(Items.enderEye, "ender_eye");
      this.applyItem(Items.speckledMelon, "speckled_melon");
      this.secondaryMapEntry.put(Items.spawnEgg, new RenderItemWrapper(this));
      this.applyItem(Items.experienceBottle, "experience_bottle");
      this.applyItem(Items.fireCharge, "fire_charge");
      this.applyItem(Items.writableBook, "writable_book");
      this.applyItem(Items.emerald, "emerald");
      this.applyItem(Items.itemFrame, "item_frame");
      this.applyItem(Items.flowerPot, "flower_pot");
      this.applyItem(Items.carrot, "carrot");
      this.applyItem(Items.potato, "potato");
      this.applyItem(Items.bakedPotato, "baked_potato");
      this.applyItem(Items.poisonousPotato, "poisonous_potato");
      this.applyItem(Items.map, "map");
      this.applyItem(Items.goldenCarrot, "golden_carrot");
      this.applyItem(Items.skull, 0, "skull_skeleton");
      this.applyItem(Items.skull, 1, "skull_wither");
      this.applyItem(Items.skull, 2, "skull_zombie");
      this.applyItem(Items.skull, 3, "skull_char");
      this.applyItem(Items.skull, 4, "skull_creeper");
      this.applyItem(Items.carrotOnAStick, "carrot_on_a_stick");
      this.applyItem(Items.netherStar, "nether_star");
      this.applyItem(Items.pumpkinPie, "pumpkin_pie");
      this.applyItem(Items.fireworkCharge, "firework_charge");
      this.applyItem(Items.comparator, "comparator");
      this.applyItem(Items.netherbrick, "netherbrick");
      this.applyItem(Items.quartz, "quartz");
      this.applyItem(Items.tntMinecart, "tnt_minecart");
      this.applyItem(Items.hopperMinecart, "hopper_minecart");
      this.applyItem(Items.armorStand, "armor_stand");
      this.applyItem(Items.ironHorseArmor, "iron_horse_armor");
      this.applyItem(Items.goldenHorseArmor, "golden_horse_armor");
      this.applyItem(Items.diamondHorseArmor, "diamond_horse_armor");
      this.applyItem(Items.lead, "lead");
      this.applyItem(Items.nameTag, "name_tag");
      this.secondaryMapEntry.put(Items.banner, new RenderItemBuffer(this));
      this.applyItem(Items.record13, "record_13");
      this.applyItem(Items.recordCat, "record_cat");
      this.applyItem(Items.recordBlocks, "record_blocks");
      this.applyItem(Items.recordChirp, "record_chirp");
      this.applyItem(Items.recordFar, "record_far");
      this.applyItem(Items.recordMall, "record_mall");
      this.applyItem(Items.recordMellohi, "record_mellohi");
      this.applyItem(Items.recordStal, "record_stal");
      this.applyItem(Items.recordStrad, "record_strad");
      this.applyItem(Items.recordWard, "record_ward");
      this.applyItem(Items.record11, "record_11");
      this.applyItem(Items.recordWait, "record_wait");
      this.applyItem(Items.prismarineShard, "prismarine_shard");
      this.applyItem(Items.prismarineCrystals, "prismarine_crystals");
      this.secondaryMapEntry.put(Items.enchantedBook, new RenderItemHandler(this));
      this.secondaryMapEntry.put(Items.filledMap, new RenderItemContainer(this));
      this.applyBlock(Blocks.block139, "command_block");
      this.applyItem(Items.fireworks, "fireworks");
      this.applyItem(Items.commandBlockMinecart, "command_block_minecart");
      this.applyBlock(Blocks.block145, "barrier");
      this.applyBlock(Blocks.block29, "mob_spawner");
      this.applyItem(Items.writtenBook, "written_book");
      this.applyBlock(Blocks.block54, RenderOptionProperty.ALL_INSIDE.getCount(), "brown_mushroom_block");
      this.applyBlock(Blocks.block41, RenderOptionProperty.ALL_INSIDE.getCount(), "red_mushroom_block");
      this.applyBlock(Blocks.block82, "dragon_egg");
   }

   public void renderItemOverlays(FontRenderer fontRenderer, ItemStack stack, int count, int count2) {
      this.renderItemOverlayIntoGUI(fontRenderer, stack, count, count2, (String)null);
   }

   public void applyItemStack(ItemStack stack, ItemPhysicsNoneType itemPhysicsNoneType) {
      if (stack != null) {
         IBakedModel iBakedModel = this.secondaryMapEntry.resolveIBakedModel(stack);
         this.applyItemStack(stack, iBakedModel, itemPhysicsNoneType);
      }
   }

   public void applyItemStack(ItemStack stack, IBakedModel iBakedModel, ItemPhysicsNoneType itemPhysicsNoneType) {
      this.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
      this.textureManager.get(ListenerAdapter27.resourceLocation).applyFlag(false, false);
      this.applyItemStack(stack);
      GlStateManager.disableLighting();
      GlStateManager.alphaFunc(516, 0.1F);
      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.pushMatrix();
      SpecialItemEntry specialItemEntry = iBakedModel.getSpecialItemEntry();
      specialItemEntry.applyItemPhysicsNoneType(itemPhysicsNoneType);
      if (this.matchesAuxiliaryItemEntry(specialItemEntry.getAuxiliaryItemEntry(itemPhysicsNoneType))) {
         GlStateManager.glCullFace(1028);
      }

      this.applyItemStack(stack, iBakedModel, false, false, true);
      GlStateManager.glCullFace(1029);
      GlStateManager.popMatrix();
      GlStateManager.applyState8();
      GlStateManager.enableBlend();
      this.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
      this.textureManager.get(ListenerAdapter27.resourceLocation).updateState();
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer, List list, int count, ItemStack stack) {
      boolean flag = count == -1 && stack != null;
      int count2 = 0;

      for (int index = list.size(); count2 < index; count2++) {
         LocalIntArrayEntry localIntArrayEntry = (LocalIntArrayEntry)list.get(count2);
         int count3 = count;
         if (flag && localIntArrayEntry.isActive2()) {
            count3 = stack.getItem().processAction(stack, localIntArrayEntry.getCount());
            if (BuildManager.isActive33()) {
               count3 = LoadingManager.computeCount(stack, localIntArrayEntry.getCount(), count3);
            }

            if (EntityRenderer.enabled5) {
               count3 = SpecialLoggerEntry.computeCount(count3);
            }

            count3 |= -16777216;
         }

         this.applyWorldRenderer(worldRenderer, localIntArrayEntry, count3);
      }
   }

   public RenderItem(TextureManager textureManager2, IronDust ironDust2) {
      this.textureManager = textureManager2;
      this.ironDust = ironDust2;
      this.secondaryMapEntry = new SecondaryMapEntry(ironDust2);
      this.updateState();
   }
}
