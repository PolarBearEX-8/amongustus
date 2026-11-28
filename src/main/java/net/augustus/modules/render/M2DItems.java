package net.augustus.modules.render;

import java.awt.Color;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.options.M2DItemsFilterOption;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.render.ProjectionUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

public class M2DItems extends Module implements RenderListener, TickListener, LoadWorldListener {
   private final MultiOptionSetting items;
   private final DoubleValue scale;
   public final BooleanValue remove3DItems;
   private final BooleanValue showStackSize;
   private final BooleanValue background;
   private final ColorSetting backgroundColor;
   private final BlurShadowSettings background2;
   private final BooleanValue onlyAtOptifineZoom;
   private final java.util.ArrayList filteredItemEntities;
   private final HashMap projectedItemCoords;

   public M2DItems() {
       super("2DItems", "2DGegenstände", new Color(66, 135, 245), Categorys.RENDER);
      this.items = new MultiOptionSetting(this, "Items", M2DItemsFilterOption.values());
      this.scale = new DoubleValue(this, "Scale", 1.0, 0.25, 4.0, 2);
      this.remove3DItems = new BooleanValue(this, "Remove3DItems", true);
      this.showStackSize = new BooleanValue(this, "ShowStackSize", false);
      this.background = new BooleanValue(this, "Background", false);
      ColorSetting colorSetting = new ColorSetting(this, "BackgroundColor", new Color(0, 0, 0, 128));
      BooleanValue background3 = this.background;
      BooleanValue booleanValue = background3;
      this.backgroundColor = (ColorSetting)colorSetting.withVisibility(() -> booleanValue.getValue());
      BlurShadowSettings blurShadowSettings = new BlurShadowSettings(this, "Background");
      background3 = this.background;
      BooleanValue booleanValue2 = background3;
      this.background2 = (BlurShadowSettings)blurShadowSettings.withVisibility(() -> booleanValue2.getValue());
      this.onlyAtOptifineZoom = new BooleanValue(this, "OnlyAtOptifineZoom", false);
      this.filteredItemEntities = new java.util.ArrayList();
      this.projectedItemCoords = new HashMap();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.projectedItemCoords.clear();
      this.filteredItemEntities.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.filteredItemEntities.clear();
      this.projectedItemCoords.clear();
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {

      for (EntityItem entityItem : (Iterable<EntityItem>)(this.filteredItemEntities)) {
         Vec3 vec = (Vec3)this.projectedItemCoords.get(entityItem);
         if (vec.zCoord >= 0.0 && vec.zCoord <= 1.0) {
            float value = (float)this.scale.getValue();
            int xCoord = (int)vec.xCoord;
            int yCoord = (int)vec.yCoord;
            if (this.background.getValue()) {
               BlurShadowManager.enableOneShotPass(this.background2.isBlurActive(), this.background2.isShadowActive());
               BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderItemWithBackgroundPass(xCoord, yCoord, value, entityItem, shaderRenderPass));
            } else {
               this.beginItemTransform(xCoord, yCoord, value);
               RenderHelper.enableGUIStandardItemLighting();
               mc.getRenderItem().renderItemIntoGUI(entityItem.getEntityItem(), 0, 0);
               if (this.showStackSize.getValue()) {
                  mc.getRenderItem().renderItemOverlays(mc.fontRendererObj, entityItem.getEntityItem(), 0, 0);
               }

               RenderHelper.disableStandardItemLighting();
               this.endItemTransform();
            }
         }
      }

      RenderUtil.resetGlColorState();
   }

   @Override
   public void onRender3D(float partialTicks) {
      this.projectedItemCoords.clear();
      ScaledResolution sr = new ScaledResolution(mc);

      for (EntityItem entityItem : (Iterable<EntityItem>)(this.filteredItemEntities)) {
         Vec3 vec = new Vec3(
            entityItem.lastTickPosX + (entityItem.posX - entityItem.lastTickPosX) * (double)partialTicks - mc.getRenderManager().getRenderPosX(),
            entityItem.lastTickPosY + (entityItem.posY - entityItem.lastTickPosY) * (double)partialTicks + 0.4 - mc.getRenderManager().getRenderPosY(),
            entityItem.lastTickPosZ + (entityItem.posZ - entityItem.lastTickPosZ) * (double)partialTicks - mc.getRenderManager().getRenderPosZ()
         );
         this.projectedItemCoords.put(entityItem, ProjectionUtil.project3DTo2D(vec.xCoord, vec.yCoord, vec.zCoord, sr));
      }
   }

   @Override
   public void onTick() {
      this.filteredItemEntities.clear();

      for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (entity instanceof EntityItem && ((EntityItem)entity).getEntityItem() != null) {
            if (this.shouldRenderItem2D(((EntityItem)entity).getEntityItem())) {
               this.filteredItemEntities.add((EntityItem)entity);
            }
         }
      }
   }

   public boolean shouldRenderItem2D(ItemStack stack) {
      return (!this.onlyAtOptifineZoom.getValue() || mc.gameSettings.ofKeyBindZoom.isKeyDown())
         && (
            M2DItemsFilterOption.BRONZE.isSelected() && stack.getItem().equals(Items.brick)
               || M2DItemsFilterOption.DIAMOND.isSelected() && stack.getItem().equals(Items.diamond)
               || M2DItemsFilterOption.EMERALD.isSelected() && stack.getItem().equals(Items.emerald)
               || M2DItemsFilterOption.GOLD.isSelected() && stack.getItem().equals(Items.gold_ingot)
               || M2DItemsFilterOption.IRON.isSelected() && stack.getItem().equals(Items.iron_ingot)
               || M2DItemsFilterOption.ALL.isSelected()
                  && !stack.getItem().equals(Items.brick)
                  && !stack.getItem().equals(Items.diamond)
                  && !stack.getItem().equals(Items.emerald)
                  && !stack.getItem().equals(Items.gold_ingot)
                  && !stack.getItem().equals(Items.iron_ingot)
         );
   }

   private void beginItemTransform(int n, int n2, float f) {
      GlStateManager.pushMatrix();
      GlStateManager.disableBlend();
      GlStateManager.enableRescaleNormal();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.translate((float)(n + 8), (float)(n2 + 8), 0.0F);
      GlStateManager.scale(f, f, 1.0F);
      GlStateManager.translate(-8.0F, -8.0F, 0.0F);
   }

   private void endItemTransform() {
      GlStateManager.disableRescaleNormal();
      GlStateManager.enableBlend();
      GlStateManager.disableLighting();
      GlStateManager.popMatrix();
   }

   private void renderItemWithBackgroundPass(int n, int n2, float f, EntityItem entityItem, ShaderRenderPass shaderRenderPass) {
      this.beginItemTransform(n, n2, f);
      if (shaderRenderPass == ShaderRenderPass.SHADOW) {
         RenderUtil.drawRect(0, 0, 16, 16, this.background2.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, this.backgroundColor.getColorInt((byte)0))));
      } else if (shaderRenderPass == ShaderRenderPass.BLUR) {
         RenderUtil.drawRect(0, 0, 16, 16, this.backgroundColor.getColorInt((byte)0));
      }

      if (shaderRenderPass == ShaderRenderPass.NONE) {
         RenderUtil.drawRect(0, 0, 16, 16, this.backgroundColor.getColorInt((byte)0));
         RenderHelper.enableGUIStandardItemLighting();
         mc.getRenderItem().renderItemIntoGUI(entityItem.getEntityItem(), 0, 0);
         if (this.showStackSize.getValue()) {
            mc.getRenderItem().renderItemOverlays(mc.fontRendererObj, entityItem.getEntityItem(), 0, 0);
         }

         RenderHelper.disableStandardItemLighting();
      }

      this.endItemTransform();
   }

}
