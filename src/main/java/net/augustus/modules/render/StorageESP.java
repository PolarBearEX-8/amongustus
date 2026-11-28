package net.augustus.modules.render;

import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.awt.Color;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.render.RenderPassTask;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.render.ProjectedBounds2D;
import net.augustus.render.ProjectionUtil;
import net.augustus.settings.SettingChangeListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.options.StorageEspRenderModeOption;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class StorageESP extends Module implements RenderListener, TickListener, LoadWorldListener {
   private final MultiOptionSetting modes;
   private final SettingGroup box;
   private final ColorSetting chestColor;
   private final ColorSetting enderChestColor;
   private final DoubleValue boxLineWidth;
   private final DoubleValue boxExpand;
   private final DoubleValue boxPriority;
   private final BlurShadowSettings boxShader;
   private final SettingGroup blockBox;
   private final ColorSetting chestColor2;
   private final ColorSetting enderChestColor2;
   private final DoubleValue blockBoxLineWidth;
   private final DoubleValue blockBoxPriority;
   private final BlurShadowSettings blockBoxShader;
   private final SettingGroup n3DCorner;
   private final ColorSetting chestColor3;
   private final ColorSetting enderChestColor3;
   private final DoubleValue corner3DPriority;
   private final BlurShadowSettings corner3DShader;
   private final SettingGroup real2D;
   public final ColorSetting chestColor1;
   public final ColorSetting chestColor22;
   public final ColorSetting enderChestColor1;
   public final ColorSetting enderChestColor22;
   private final DoubleValue real2DPriority;
   private final BlurShadowSettings real2DShader;
   private final SettingGroup corner;
   public final ColorSetting chestColor4;
   public final ColorSetting enderChestColor4;
   public final DoubleValue cornerLineWidth;
   public final DoubleValue cornerHorizontalPercentage;
   public final DoubleValue cornerVerticalPercentage;
   private final DoubleValue cornerPriority;
   private final BlurShadowSettings cornerShader;
   public final DoubleValue maxDistance;
   public final HashMap projectedTileBounds;
   private final java.util.ArrayList visibleTileEntities;
   private final Frustum frustum;
   private final Int2ObjectAVLTreeMap render2DPassesByPriority;
   private final Int2ObjectAVLTreeMap render3DPassesByPriority;

   public StorageESP() {
       super("StorageESP", "SpeicherRöntgenBlick", new Color(100, 166, 148, 255), Categorys.RENDER);
      this.modes = new MultiOptionSetting(this, "Modes", StorageEspRenderModeOption.values());
      this.box = new SettingGroup(this, "Box", true).visibleWhen(StorageEspRenderModeOption.BOX::isSelected);
      Color color2 = new Color(21, 121, 230, 65);
      SettingGroup box2 = this.box;
      Color color3 = color2;
      String str = "BoxChestColor";
      ColorSetting colorSetting = new ColorSetting(this, str, color3, box2);
      this.chestColor = (ColorSetting)colorSetting.withDisplayName("ChestColor");
      color2 = new Color(21, 121, 230, 65);
      box2 = this.box;
      color3 = color2;
      str = "BoxEnderChestColor";
      colorSetting = new ColorSetting(this, str, color3, box2);
      this.enderChestColor = (ColorSetting)colorSetting.withDisplayName("EnderChestColor");
      this.boxLineWidth = new DoubleValue(this, "BoxLineWidth", 2.0, 0.0, 12.0, 0, this.box).named("LineWidth");
      this.boxExpand = new DoubleValue(this, "BoxExpand", 0.0, -1.0, 1.0, 4, this.box).named("Expand");
      DoubleValue boxPrioritySetting = new DoubleValue(this, "BoxPriority", 0.0, 0.0, 2.0, 0, this.box).named("3DPriority");
      SettingChangeListener listener = () -> this.rebuild3DRenderQueue();
      this.boxPriority = (DoubleValue)boxPrioritySetting.withChangeListener(listener);
      this.boxShader = new BlurShadowSettings(this, "BoxShader", this.box).withCustomShadowColor(Color.black).disableBlurByDefault();
      this.blockBox = new SettingGroup(this, "BlockBox", true).visibleWhen(StorageEspRenderModeOption.BLOCK_BOX::isSelected);
      color2 = new Color(21, 121, 230, 65);
      box2 = this.blockBox;
      color3 = color2;
      str = "BlockBoxChestColor";
      colorSetting = new ColorSetting(this, str, color3, box2);
      this.chestColor2 = (ColorSetting)colorSetting.withDisplayName("ChestColor");
      color2 = new Color(21, 121, 230, 65);
      box2 = this.blockBox;
      color3 = color2;
      str = "BlockBoxEnderChestColor";
      colorSetting = new ColorSetting(this, str, color3, box2);
      this.enderChestColor2 = (ColorSetting)colorSetting.withDisplayName("EnderChestColor");
      this.blockBoxLineWidth = new DoubleValue(this, "BlockBoxLineWidth", 2.0, 0.0, 12.0, 0, this.blockBox).named("LineWidth");
      DoubleValue blockBoxPrioritySetting = new DoubleValue(this, "BlockBoxPriority", 1.0, 0.0, 2.0, 0, this.blockBox).named("3DPriority");
      listener = () -> this.rebuild3DRenderQueue();
      this.blockBoxPriority = (DoubleValue)blockBoxPrioritySetting.withChangeListener(listener);
      this.blockBoxShader = new BlurShadowSettings(this, "BlockBoxShader", this.blockBox).withCustomShadowColor(Color.black).disableBlurByDefault();
      this.n3DCorner = new SettingGroup(this, "3DCorner", true).visibleWhen(StorageEspRenderModeOption.CORNER_3D::isSelected);
      color2 = new Color(21, 121, 230, 65);
      box2 = this.n3DCorner;
      color3 = color2;
      str = "Corner3ChestColor";
      colorSetting = new ColorSetting(this, str, color3, box2);
      this.chestColor3 = (ColorSetting)colorSetting.withDisplayName("ChestColor");
      Color color4 = new Color(21, 121, 230, 65);
      box2 = this.n3DCorner;
      color3 = color4;
      str = "Corner3EnderChestColor";
      this.enderChestColor3 = (ColorSetting)new ColorSetting(this, str, color3, box2).withDisplayName("EnderChestColor");
      DoubleValue corner3DPrioritySetting = new DoubleValue(this, "Corner3DPriority", 2.0, 0.0, 2.0, 0, this.n3DCorner).named("3DPriority");
      SettingChangeListener listener4 = () -> this.rebuild3DRenderQueue();
      this.corner3DPriority = (DoubleValue)corner3DPrioritySetting.withChangeListener(listener4);
      this.corner3DShader = new BlurShadowSettings(this, "Corner3DShader", this.n3DCorner).withCustomShadowColor(Color.black).disableBlurByDefault();
      this.real2D = new SettingGroup(this, "Real2D", true).visibleWhen(StorageEspRenderModeOption.REAL_2D::isSelected);
      color4 = new Color(21, 121, 230, 65);
      box2 = this.real2D;
      color3 = color4;
      str = "Real2DColorChest1";
      this.chestColor1 = (ColorSetting)new ColorSetting(this, str, color3, box2).withDisplayName("ChestColor1");
      color4 = new Color(21, 121, 230);
      box2 = this.real2D;
      color3 = color4;
      str = "Real2DColorChest2";
      this.chestColor22 = (ColorSetting)new ColorSetting(this, str, color3, box2).withDisplayName("ChestColor2");
      color4 = new Color(66, 245, 164, 65);
      box2 = this.real2D;
      color3 = color4;
      str = "Real2DColorEnderChest1";
      this.enderChestColor1 = (ColorSetting)new ColorSetting(this, str, color3, box2).withDisplayName("EnderChestColor1");
      color4 = new Color(66, 245, 164);
      box2 = this.real2D;
      color3 = color4;
      str = "Real2DColorEnderChest2";
      this.enderChestColor22 = (ColorSetting)new ColorSetting(this, str, color3, box2).withDisplayName("EnderChestColor2");
      DoubleValue real2DPrioritySetting = new DoubleValue(this, "Real2DPriority", 0.0, 0.0, 1.0, 0, this.real2D).named("2DPriority");
      listener4 = () -> this.rebuild2DRenderQueue();
      this.real2DPriority = (DoubleValue)real2DPrioritySetting.withChangeListener(listener4);
      this.real2DShader = new BlurShadowSettings(this, "Real2DShader", this.real2D).withCustomShadowColor(Color.black).disableBlurByDefault();
      this.corner = new SettingGroup(this, "Corner", true).visibleWhen(StorageEspRenderModeOption.CORNER::isSelected);
      color4 = new Color(21, 121, 230);
      box2 = this.corner;
      color3 = color4;
      str = "CornerChestColor";
      this.chestColor4 = (ColorSetting)new ColorSetting(this, str, color3, box2).withDisplayName("ChestColor");
      color4 = new Color(66, 245, 164);
      box2 = this.corner;
      color3 = color4;
      str = "CornerEnderChestColor";
      this.enderChestColor4 = (ColorSetting)new ColorSetting(this, str, color3, box2).withDisplayName("EnderChestColor");
      this.cornerLineWidth = new DoubleValue(this, "CornerLineWidth", 2.0, 1.0, 5.0, 0, this.corner).named("LineWidth");
      this.cornerHorizontalPercentage = new DoubleValue(this, "CornerHorizontalPercentage", 50.0, 1.0, 100.0, 0, this.corner);
      this.cornerVerticalPercentage = new DoubleValue(this, "CornerVerticalPercentage", 50.0, 1.0, 100.0, 0, this.corner).named("VerticalPercentage");
      DoubleValue cornerPrioritySetting = new DoubleValue(this, "CornerPriority", 1.0, 0.0, 1.0, 0, this.corner).named("2DPriority");
      listener4 = () -> this.rebuild2DRenderQueue();
      this.cornerPriority = (DoubleValue)cornerPrioritySetting.withChangeListener(listener4);
      this.cornerShader = new BlurShadowSettings(this, "CornerShader", this.corner).withCustomShadowColor(Color.black).disableBlurByDefault();
      this.maxDistance = new DoubleValue(this, "MaxDistance", 6.0, 0.0, 32.0, 0);
      this.projectedTileBounds = new HashMap();
      this.visibleTileEntities = new java.util.ArrayList();
      this.frustum = new Frustum();
      this.render2DPassesByPriority = new Int2ObjectAVLTreeMap();
      this.render3DPassesByPriority = new Int2ObjectAVLTreeMap();
      this.rebuild3DRenderQueue();
      this.rebuild2DRenderQueue();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.visibleTileEntities.clear();
      this.projectedTileBounds.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32, -9);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onRender2DPre(float partialTicks, ScaledResolution sr) {
      if (!this.visibleTileEntities.isEmpty()) {
         ObjectIterator it = this.render2DPassesByPriority.values().iterator();

         while (it.hasNext()) {
            ((Runnable)it.next()).run();
            RenderUtil.resetGlColorState();
         }
      }
   }

   @Override
   public void onTick() {
      if (mc.theWorld != null) {
         this.visibleTileEntities.clear();
         this.frustum.setPosition(mc.getRenderViewEntity().posX, mc.getRenderViewEntity().posY, mc.getRenderViewEntity().posZ);

         for (TileEntity tileEntity : (Iterable<TileEntity>)(mc.theWorld.loadedTileEntityList)) {
            if ((tileEntity instanceof TileEntityChest || tileEntity instanceof TileEntityEnderChest)
               && !(
                  mc.thePlayer.getDistance((double)tileEntity.getPos().getX(), (double)tileEntity.getPos().getY(), (double)tileEntity.getPos().getZ())
                     > this.maxDistance.getValue() * 16.0
               )
               && (!(tileEntity instanceof TileEntityChest) || ((TileEntityChest)tileEntity).adjacentChestXNeg == null && ((TileEntityChest)tileEntity).adjacentChestZNeg == null)
               )
             {
               this.addIfInFrustum(tileEntity);
            }
         }
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (!this.visibleTileEntities.isEmpty()) {
         ObjectIterator it = this.render3DPassesByPriority.values().iterator();

         while (it.hasNext()) {
            Runnable runnable = (Runnable)it.next();
            GlStateManager.pushMatrix();
            RenderUtil.begin3DRender();
            runnable.run();
            RenderUtil.end3DRender();
            GlStateManager.popMatrix();
         }

         if (!this.render3DPassesByPriority.isEmpty()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         }

         if (StorageEspRenderModeOption.REAL_2D.isSelected() || StorageEspRenderModeOption.CORNER.isSelected()) {
            ScaledResolution sr = new ScaledResolution(mc);

            for (TileEntity tileEntity : (Iterable<TileEntity>)(this.visibleTileEntities)) {
               AxisAlignedBB selectedBoundingBox = tileEntity.getBlockType().getSelectedBoundingBox(mc.theWorld, tileEntity.getPos());
               if (tileEntity instanceof TileEntityChest) {
                  TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
                  if (tileEntityChest.adjacentChestXNeg != null) {
                     selectedBoundingBox = selectedBoundingBox.union(tileEntityChest.adjacentChestXNeg.getBlockType().getSelectedBoundingBox(mc.theWorld, tileEntityChest.adjacentChestXNeg.getPos()));
                  } else if (tileEntityChest.adjacentChestZNeg != null) {
                     selectedBoundingBox = selectedBoundingBox.union(tileEntityChest.adjacentChestZNeg.getBlockType().getSelectedBoundingBox(mc.theWorld, tileEntityChest.adjacentChestZNeg.getPos()));
                  } else if (tileEntityChest.adjacentChestXPos != null) {
                     selectedBoundingBox = selectedBoundingBox.union(tileEntityChest.adjacentChestXPos.getBlockType().getSelectedBoundingBox(mc.theWorld, tileEntityChest.adjacentChestXPos.getPos()));
                  } else if (tileEntityChest.adjacentChestZPos != null) {
                     selectedBoundingBox = selectedBoundingBox.union(tileEntityChest.adjacentChestZPos.getBlockType().getSelectedBoundingBox(mc.theWorld, tileEntityChest.adjacentChestZPos.getPos()));
                  }
               }

               this.projectedTileBounds.put(tileEntity, ProjectionUtil.projectBoundingBox(selectedBoundingBox, sr));
            }
         }
      }
   }

   private void rebuild2DRenderQueue() {
      this.render2DPassesByPriority.clear();
      this.render2DPassesByPriority.put((int)this.real2DPriority.getValue(), (Runnable)() -> this.renderReal2DBoxes());
      this.render2DPassesByPriority.put((int)this.cornerPriority.getValue(), (Runnable)() -> this.render2DCorners());
   }

   private void rebuild3DRenderQueue() {
      this.render3DPassesByPriority.clear();
      this.render3DPassesByPriority.put((int)this.boxPriority.getValue(), (Runnable)() -> this.renderBoxes());
      this.render3DPassesByPriority.put((int)this.blockBoxPriority.getValue(), (Runnable)() -> this.renderBlockBoxes());
      this.render3DPassesByPriority.put((int)this.corner3DPriority.getValue(), (Runnable)() -> this.render3DCorners());
   }

   private void addIfInFrustum(TileEntity tileEntity) {
      BlockPos pos = tileEntity.getPos();
      if (this.frustum
         .isBoxInFrustum(
            (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 1), (double)(pos.getZ() + 1)
         )) {
         this.visibleTileEntities.add(tileEntity);
      }
   }

   private int resolveTileEntityColor(TileEntity tileEntity, ColorSetting colorSetting, ColorSetting colorSetting2, int n) {
      return tileEntity instanceof TileEntityEnderChest ? colorSetting2.getColorInt(n) : colorSetting.getColorInt(n);
   }

   private float scaleLineWidthByDistance(TileEntity tileEntity, DoubleValue stringValue) {
      float f = (float)(stringValue.getValue() / 2.0);
      if (mc.thePlayer.getDistance((double)tileEntity.getPos().getX(), (double)tileEntity.getPos().getY(), (double)tileEntity.getPos().getZ()) > 1.0) {
         float f2 = (float)(1.0 - mc.thePlayer.getDistance((double)tileEntity.getPos().getX(), (double)tileEntity.getPos().getY(), (double)tileEntity.getPos().getZ()) / 20.0);
         if ((double)f2 < 0.3) {
            f2 = 0.3F;
         }

         f *= f2;
      }

      return f;
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.visibleTileEntities.clear();
      this.projectedTileBounds.clear();
   }

   private void render3DCorners() {
      if (StorageEspRenderModeOption.CORNER_3D.isSelected()) {
         BlurShadowManager.enableOneShotPass(this.corner3DShader.isBlurActive(), this.corner3DShader.isShadowActive());
         BlurShadowManager.submitRenderTask(true, shaderRenderPass -> this.render3DCornersPass(shaderRenderPass));
      }
   }

   private void render3DCornersPass(ShaderRenderPass shaderRenderPass) {

      for (int i = 0; i < this.visibleTileEntities.size(); i++) {
         TileEntity tileEntity = (TileEntity)this.visibleTileEntities.get(i);
         ColorSetting enderChestColor32 = this.enderChestColor3;
         int n = this.resolveTileEntityColor(tileEntity, this.chestColor3, enderChestColor32, i);
         RenderUtil.drawTileEntityCornerEsp(tileEntity, this.corner3DShader.resolvePassColor(shaderRenderPass, n, i));
      }
   }

   private void renderBlockBoxes() {
      if (StorageEspRenderModeOption.BLOCK_BOX.isSelected()) {
         BlurShadowManager.enableOneShotPass(this.blockBoxShader.isBlurActive(), this.blockBoxShader.isShadowActive());
         BlurShadowManager.submitRenderTask(true, shaderRenderPass -> this.renderBlockBoxesPass(shaderRenderPass));
      }
   }

   private void renderBlockBoxesPass(ShaderRenderPass shaderRenderPass) {

      for (int i = 0; i < this.visibleTileEntities.size(); i++) {
         TileEntity tileEntity = (TileEntity)this.visibleTileEntities.get(i);
         ColorSetting enderChestColor23 = this.enderChestColor2;
         int n = this.resolveTileEntityColor(tileEntity, this.chestColor2, enderChestColor23, i);
         float f = this.scaleLineWidthByDistance(tileEntity, this.blockBoxLineWidth);
         int n2 = this.blockBoxShader.resolvePassColor(shaderRenderPass, n, i);
         double d = 0.0;
         RenderUtil.drawTileEntityEspBox(tileEntity, n2, 1.0F, f, d, true);
      }
   }

   private void renderBoxes() {
      if (StorageEspRenderModeOption.BOX.isSelected()) {
         BlurShadowManager.enableOneShotPass(this.boxShader.isBlurActive(), this.boxShader.isShadowActive());
         BlurShadowManager.submitRenderTask(true, shaderRenderPass -> this.renderBoxesPass(shaderRenderPass));
      }
   }

   private void renderBoxesPass(ShaderRenderPass shaderRenderPass) {

      for (int i = 0; i < this.visibleTileEntities.size(); i++) {
         TileEntity tileEntity = (TileEntity)this.visibleTileEntities.get(i);
         ColorSetting enderChestColor5 = this.enderChestColor;
         int n = this.resolveTileEntityColor(tileEntity, this.chestColor, enderChestColor5, i);
         float f = this.scaleLineWidthByDistance(tileEntity, this.boxLineWidth);
         int n2 = this.boxShader.resolvePassColor(shaderRenderPass, n, i);
         double value = this.boxExpand.getValue();
         RenderUtil.drawTileEntityEspBox(tileEntity, n2, 1.0F, f, value, false);
      }
   }

   private void render2DCorners() {
      if (StorageEspRenderModeOption.CORNER.isSelected()) {
         BlurShadowManager.enableOneShotPass(this.cornerShader.isBlurActive(), this.cornerShader.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.render2DCornersPass(shaderRenderPass));
      }
   }

   private void render2DCornersPass(ShaderRenderPass shaderRenderPass) {

      for (int i = 0; i < this.visibleTileEntities.size(); i++) {
         TileEntity tileEntity = (TileEntity)this.visibleTileEntities.get(i);
         ProjectedBounds2D projectedBounds2D = (ProjectedBounds2D)this.projectedTileBounds.get(tileEntity);
         int n = tileEntity instanceof TileEntityEnderChest ? this.enderChestColor4.getColorInt(i) : this.chestColor4.getColorInt(i);
         double minX = projectedBounds2D.minX;
         RenderUtil.drawCornerBrackets(minX, projectedBounds2D.minY, projectedBounds2D.maxX, projectedBounds2D.maxY, (float)this.cornerLineWidth.getValue(), this.cornerHorizontalPercentage.getValue(), this.cornerVerticalPercentage.getValue(), this.cornerShader.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n), i));
      }
   }

   private void renderReal2DBoxes() {
      if (StorageEspRenderModeOption.REAL_2D.isSelected()) {
         BlurShadowManager.enableOneShotPass(this.real2DShader.isBlurActive(), this.real2DShader.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderReal2DBoxesPass(shaderRenderPass));
      }
   }

   private void renderReal2DBoxesPass(ShaderRenderPass shaderRenderPass) {

      for (int i = 0; i < this.visibleTileEntities.size(); i++) {
         TileEntity tileEntity = (TileEntity)this.visibleTileEntities.get(i);
         ProjectedBounds2D projectedBounds2D = (ProjectedBounds2D)this.projectedTileBounds.get(tileEntity);
         if (shaderRenderPass != ShaderRenderPass.SHADOW) {
            RenderUtil.drawRect(projectedBounds2D.minX, projectedBounds2D.minY, projectedBounds2D.maxX, projectedBounds2D.maxY, tileEntity instanceof TileEntityEnderChest ? this.enderChestColor1.getColorInt(i) : this.chestColor1.getColorInt(i));
         }

         if (shaderRenderPass != ShaderRenderPass.BLUR) {
            int n2 = tileEntity instanceof TileEntityEnderChest ? this.enderChestColor22.getColorInt(i) : this.chestColor22.getColorInt(i);
            double minX2 = projectedBounds2D.minX;
            double minY2 = projectedBounds2D.minY;
            double maxX2 = projectedBounds2D.maxX;
            double maxY2 = projectedBounds2D.maxY;
            BlurShadowSettings real2DShader2 = this.real2DShader;
            int n3 = ColorUtil.resolvePassColor(shaderRenderPass, n2);
            RenderUtil.drawRectOutline(minX2, minY2, maxX2, maxY2, 1.0F, real2DShader2.resolvePassColor(shaderRenderPass, n3, i));
         }
      }
   }

}
