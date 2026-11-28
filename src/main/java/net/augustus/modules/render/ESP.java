package net.augustus.modules.render;

import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.misc.MurdererFinder;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.modules.render.esp.EspRenderUtil;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.modules.render.esp.EntityEspBox;
import net.augustus.modules.render.esp.EspOverlayImage;
import net.augustus.ClientManagers;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.ActionSetting;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.GlStateSaver;
import net.augustus.render.ProjectedBounds2D;
import net.augustus.render.ProjectionUtil;
import net.augustus.settings.SettingChangeListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.options.EspRenderModeOption;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class ESP extends Module implements RenderListener, TickListener {
   private static final String PICTURES_DIR = ClientPaths.CLIENT_DIR + File.separator + "pictures";
   private final MultiOptionSetting modes;
   public final BooleanValue markMurderer;
   public final BooleanValue customMurdererAlpha;
   public final DoubleValue murdererAlpha;
   private final SettingGroup box;
   private final BooleanValue teamColorBox;
   private final DoubleValue teamColorAlphaBox;
   private final ColorSetting color6;
   private final BooleanValue boxHitColorBool;
   private final ColorSetting hitColor;
   private final DoubleValue boxLineWidth;
   private final DoubleValue boxPriority;
   private final BlurShadowSettings box2;
   private final SettingGroup hitBox;
   private final BooleanValue hitBoxTeamColor;
   private final DoubleValue hitBoxTeamColorAlpha;
   private final ColorSetting color2;
   private final BooleanValue hitBoxHitColorBool;
   private final ColorSetting hitColor2;
   private final DoubleValue hitBoxLineWidth;
   private final DoubleValue hitBoxPriority;
   private final BlurShadowSettings hitBox2;
   private final SettingGroup collisionBox;
   private final BooleanValue collisionBoxTeamColor;
   private final DoubleValue collisionBoxTeamColorAlpha;
   private final ColorSetting color3;
   private final BooleanValue collisionBoxHitColorBool;
   private final ColorSetting hitColor3;
   private final DoubleValue collisionBoxLineWidth;
   private final DoubleValue collisionBoxPriority;
   private final BlurShadowSettings collisionBox2;
   private final SettingGroup n3DCorner;
   private final BooleanValue fakeCornerTeamColor;
   private final ColorSetting color4;
   private final BooleanValue fakeCornerHitColorBool;
   private final ColorSetting hitColor4;
   private final DoubleValue fakeCornerPriority;
   private final BlurShadowSettings fakeCorner;
   private final SettingGroup real2D;
   private final BooleanValue real2DTeamColor;
   private final DoubleValue real2DTeamColorAlpha;
   private final ColorSetting fillColor;
   private final ColorSetting outlineColor;
   private final BooleanValue real2DHitColorBool;
   private final ColorSetting fillColorHit;
   private final ColorSetting outlineColorHit;
   private final DoubleValue real2DPriority;
   private final BlurShadowSettings real2D2;
   private final SettingGroup corner;
   private final BooleanValue cornerTeamColor;
   private final ColorSetting color5;
   private final BooleanValue cornerHitColorBool;
   private final ColorSetting cornerHitColor;
   private final DoubleValue lineWidthCorner;
   private final DoubleValue horizontalPercentage;
   private final DoubleValue verticalPercentage;
   private final DoubleValue cornerPriority;
   private final BlurShadowSettings corner2;
   private final SettingGroup picture;
   private final StringValue picturePictures;
   private final BooleanValue stretchPicture;
   private final DoubleValue scalePicture;
   private final DoubleValue pictureYPositionPercentage;
   private final DoubleValue picturePicturePriority;
   private final BlurShadowSettings picture2;
   private final ActionSetting pictureFolder;
   private final SettingGroup vanilla;
   public final BooleanValue vanillaTeamColor;
   public final ColorSetting vanillaColor;
   public final BooleanValue vanillaHitColorBool;
   public final ColorSetting hitColor5;
   private final TargetSelectionSettings entities;
   public final DoubleValue maximumRenderDistance;
   public final java.util.ArrayList visibleEntities;
   public final HashMap projectedEntityBounds;
   private final Frustum frustum;
   private final Int2ObjectAVLTreeMap render2DPassesByPriority;
   private final Int2ObjectAVLTreeMap render3DPassesByPriority;
   private final Map loadedOverlayImages;
   private final GlStateSaver glStateSaver;
   private EspOverlayImage espOverlayImage;

   public ESP() {
       super("ESP", "SpielerDurchWandSehen", new Color(47, 134, 124, 255), Categorys.RENDER);
      this.modes = new MultiOptionSetting(this, "Modes", EspRenderModeOption.values());
      this.markMurderer = new BooleanValue(this, "MarkMurderer", true);
      BooleanValue booleanValue = new BooleanValue(this, "Custom Murderer Alpha", false);
      BooleanValue markMurderer2 = this.markMurderer;
      BooleanValue booleanValue2 = markMurderer2;
      this.customMurdererAlpha = (BooleanValue)booleanValue.withVisibility(() -> booleanValue2.getValue());
      this.murdererAlpha = new DoubleValue(this, "Murderer Alpha", 65.0, 0.0, 255.0, 0).visibleWhen(() -> this.isMurdererAlphaVisible());
      this.box = new SettingGroup(this, "Box", true).visibleWhen(() -> EspRenderModeOption.isOptionSelected(EspRenderModeOption.BOX));
      this.teamColorBox = (BooleanValue)new BooleanValue(this, "TeamColorBox", false, this.box).withDisplayName("TeamColor (players only)");
      DoubleValue teamColorAlphaBoxSetting = new DoubleValue(this, "TeamColorAlphaBox", 65.0, 0.0, 255.0, 0, this.box).named("TeamColorAlpha");
      markMurderer2 = this.teamColorBox;
      BooleanValue booleanValue4 = markMurderer2;
      this.teamColorAlphaBox = teamColorAlphaBoxSetting.visibleWhen(() -> booleanValue4.getValue());
      Color color7 = new Color(21, 121, 230, 65);
      SettingGroup box3 = this.box;
      Color color8 = color7;
      String str3 = "BoxColor";
      this.color6 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("Color");
      this.boxHitColorBool = new BooleanValue(this, "BoxHitColorBool", false, this.box);
      color7 = new Color(255, 0, 0, 65);
      box3 = this.box;
      color8 = color7;
      str3 = "BoxHitColor";
      ColorSetting colorSetting3 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("HitColor");
      markMurderer2 = this.boxHitColorBool;
      BooleanValue booleanValue5 = markMurderer2;
      this.hitColor = (ColorSetting)colorSetting3.withVisibility(() -> booleanValue5.getValue());
      this.boxLineWidth = new DoubleValue(this, "BoxLineWidth", 6.0, 0.0, 15.0, 0, this.box).named("LineWidth");
      this.boxPriority = (DoubleValue)new DoubleValue(this, "BoxPriority", 0.0, 0.0, 3.0, 0, this.box).named("3DPriority").withChangeListener(() -> this.rebuild3DRenderQueue());
      this.box2 = (new BlurShadowSettings(this, "Box", this.box)).disableBothByDefault().withCustomShadowColor(Color.black);
      this.hitBox = new SettingGroup(this, "HitBox", true).visibleWhen(() -> EspRenderModeOption.isOptionSelected(EspRenderModeOption.HIT_BOX));
      this.hitBoxTeamColor = (BooleanValue)new BooleanValue(this, "HitBoxTeamColor", false, this.hitBox).withDisplayName("TeamColor (players only)");
      DoubleValue hitBoxTeamColorAlphaSetting = new DoubleValue(this, "HitBoxTeamColorAlpha", 65.0, 0.0, 255.0, 0, this.hitBox).named("TeamColorAlpha");
      markMurderer2 = this.hitBoxTeamColor;
      BooleanValue booleanValue7 = markMurderer2;
      this.hitBoxTeamColorAlpha = hitBoxTeamColorAlphaSetting.visibleWhen(() -> booleanValue7.getValue());
      color7 = new Color(21, 121, 230, 65);
      box3 = this.hitBox;
      color8 = color7;
      str3 = "HitBoxColor";
      this.color2 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("Color");
      this.hitBoxHitColorBool = new BooleanValue(this, "HitBoxHitColorBool", false, this.hitBox);
      color7 = new Color(255, 0, 0, 65);
      box3 = this.hitBox;
      color8 = color7;
      str3 = "HitBoxHitColor";
      ColorSetting colorSetting6 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("HitColor");
      markMurderer2 = this.hitBoxHitColorBool;
      BooleanValue booleanValue8 = markMurderer2;
      this.hitColor2 = (ColorSetting)colorSetting6.withVisibility(() -> booleanValue8.getValue());
      this.hitBoxLineWidth = new DoubleValue(this, "HitBoxLineWidth", 6.0, 0.0, 15.0, 0, this.hitBox).named("LineWidth");
      this.hitBoxPriority = (DoubleValue)new DoubleValue(this, "HitBoxPriority", 1.0, 0.0, 3.0, 0, this.hitBox).named("3DPriority").withChangeListener(() -> this.rebuild3DRenderQueue());
      this.hitBox2 = (new BlurShadowSettings(this, "HitBox", this.hitBox)).disableBothByDefault().withCustomShadowColor(Color.black);
      this.collisionBox = new SettingGroup(this, "CollisionBox", true).visibleWhen(() -> EspRenderModeOption.isOptionSelected(EspRenderModeOption.COLLISION_BOX));
      this.collisionBoxTeamColor = (BooleanValue)new BooleanValue(this, "CollisionBoxTeamColor", false, this.collisionBox).withDisplayName("TeamColor (players only)");
      DoubleValue collisionBoxTeamColorAlpha2 = new DoubleValue(this, "CollisionBoxTeamColorAlpha", 65.0, 0.0, 255.0, 0, this.collisionBox).named("TeamColorAlpha");
      markMurderer2 = this.collisionBoxTeamColor;
      BooleanValue booleanValue10 = markMurderer2;
      this.collisionBoxTeamColorAlpha = collisionBoxTeamColorAlpha2.visibleWhen(() -> booleanValue10.getValue());
      color7 = new Color(21, 121, 230, 65);
      box3 = this.collisionBox;
      color8 = color7;
      str3 = "CollisionBoxColor";
      this.color3 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("Color");
      this.collisionBoxHitColorBool = new BooleanValue(this, "CollisionBoxHitColorBool", false, this.collisionBox);
      color7 = new Color(255, 0, 0, 65);
      box3 = this.collisionBox;
      color8 = color7;
      str3 = "CollisionBoxHitColor";
      ColorSetting colorSetting9 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("HitColor");
      markMurderer2 = this.collisionBoxHitColorBool;
      BooleanValue booleanValue11 = markMurderer2;
      this.hitColor3 = (ColorSetting)colorSetting9.withVisibility(() -> booleanValue11.getValue());
      this.collisionBoxLineWidth = new DoubleValue(this, "CollisionBoxLineWidth", 6.0, 0.0, 15.0, 0, this.collisionBox).named("LineWidth");
      this.collisionBoxPriority = (DoubleValue)new DoubleValue(this, "CollisionBoxPriority", 2.0, 0.0, 3.0, 0, this.collisionBox).named("3DPriority").withChangeListener(() -> this.rebuild3DRenderQueue());
      this.collisionBox2 = (new BlurShadowSettings(this, "CollisionBox", this.collisionBox)).disableBothByDefault().withCustomShadowColor(Color.black);
      this.n3DCorner = new SettingGroup(this, "3DCorner", true).visibleWhen(() -> EspRenderModeOption.isOptionSelected(EspRenderModeOption.THREE_E_CORNER));
      this.fakeCornerTeamColor = (BooleanValue)new BooleanValue(this, "FakeCornerTeamColor", false, this.n3DCorner).withDisplayName("TeamColor (players only)");
      color7 = new Color(21, 121, 230, 65);
      box3 = this.n3DCorner;
      color8 = color7;
      str3 = "FakeCornerColor";
      this.color4 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("Color");
      this.fakeCornerHitColorBool = new BooleanValue(this, "FakeCornerHitColorBool", false, this.n3DCorner);
      color7 = new Color(255, 0, 0, 65);
      box3 = this.n3DCorner;
      color8 = color7;
      str3 = "FakeCornerHitColor";
      ColorSetting colorSetting12 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("HitColor");
      markMurderer2 = this.fakeCornerHitColorBool;
      BooleanValue booleanValue13 = markMurderer2;
      this.hitColor4 = (ColorSetting)colorSetting12.withVisibility(() -> booleanValue13.getValue());
      this.fakeCornerPriority = (DoubleValue)new DoubleValue(this, "FakeCornerPriority", 3.0, 0.0, 3.0, 0, this.n3DCorner).named("3DPriority").withChangeListener(() -> this.rebuild3DRenderQueue());
      this.fakeCorner = (new BlurShadowSettings(this, "FakeCorner", this.n3DCorner)).disableBothByDefault().withCustomShadowColor(Color.black);
      this.real2D = new SettingGroup(this, "Real2D", true).visibleWhen(() -> EspRenderModeOption.isOptionSelected(EspRenderModeOption.REAL_2D));
      this.real2DTeamColor = (BooleanValue)new BooleanValue(this, "Real2DTeamColor", false, this.real2D).withDisplayName("TeamColor (players only)");
      DoubleValue real2DTeamColorAlphaSetting = new DoubleValue(this, "Real2DTeamColorAlpha", 60.0, 0.0, 255.0, 0, this.real2D).named("TeamColorAlpha");
      markMurderer2 = this.real2DTeamColor;
      BooleanValue booleanValue15 = markMurderer2;
      this.real2DTeamColorAlpha = real2DTeamColorAlphaSetting.visibleWhen(() -> booleanValue15.getValue());
      color7 = new Color(21, 121, 230, 60);
      box3 = this.real2D;
      color8 = color7;
      str3 = "Real2DColor1";
      this.fillColor = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("FillColor");
      color7 = new Color(0, 0, 0);
      box3 = this.real2D;
      color8 = color7;
      str3 = "Real2DColor2";
      this.outlineColor = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("OutlineColor");
      this.real2DHitColorBool = new BooleanValue(this, "Real2DHitColorBool", false, this.real2D);
      color7 = new Color(255, 0, 0, 60);
      box3 = this.real2D;
      color8 = color7;
      str3 = "Real2DHitColor1";
      ColorSetting colorSetting16 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("FillColorHit");
      markMurderer2 = this.real2DHitColorBool;
      BooleanValue booleanValue16 = markMurderer2;
      this.fillColorHit = (ColorSetting)colorSetting16.withVisibility(() -> booleanValue16.getValue());
      color7 = new Color(0, 0, 0);
      box3 = this.real2D;
      color8 = color7;
      str3 = "Real2DHitColor2";
      ColorSetting colorSetting18 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("OutlineColorHit");
      markMurderer2 = this.real2DHitColorBool;
      BooleanValue booleanValue17 = markMurderer2;
      this.outlineColorHit = (ColorSetting)colorSetting18.withVisibility(() -> booleanValue17.getValue());
      this.real2DPriority = (DoubleValue)new DoubleValue(this, "Real2DPriority", 0.0, 0.0, 2.0, 0, this.real2D).named("2DPriority").withChangeListener(() -> this.rebuild2DRenderQueue());
      this.real2D2 = (new BlurShadowSettings(this, "Real2D", this.real2D)).disableBothByDefault().withCustomShadowColor(Color.black);
      this.corner = new SettingGroup(this, "Corner", true).visibleWhen(() -> EspRenderModeOption.isOptionSelected(EspRenderModeOption.CORNER));
      markMurderer2 = new BooleanValue(this, "CornerTeamColor", false, this.corner);
      this.cornerTeamColor = (BooleanValue)markMurderer2.withDisplayName("TeamColor (players only)");
      Color color9 = new Color(21, 121, 230);
      box3 = this.corner;
      color8 = color9;
      str3 = "CornerColor";
      this.color5 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("Color");
      markMurderer2 = new BooleanValue(this, "CornerHitColorBool", true, this.corner);
      this.cornerHitColorBool = (BooleanValue)markMurderer2.withDisplayName("HitColor");
      color9 = new Color(21, 121, 230);
      box3 = this.corner;
      color8 = color9;
      str3 = "CornerHitColor";
      ColorSetting colorSetting20 = new ColorSetting(this, str3, color8, box3);
      BooleanValue cornerHitColorBool2 = this.cornerHitColorBool;
      this.cornerHitColor = (ColorSetting)colorSetting20.withVisibility(() -> cornerHitColorBool2.getValue());
      this.lineWidthCorner = new DoubleValue(this, "LineWidthCorner", 2.0, 1.0, 5.0, 0, this.corner).named("LineWidth");
      this.horizontalPercentage = new DoubleValue(this, "HorizontalPercentage", 55.0, 1.0, 100.0, 0, this.corner);
      this.verticalPercentage = new DoubleValue(this, "VerticalPercentage", 45.0, 1.0, 100.0, 0, this.corner);
      DoubleValue cornerPrioritySetting = new DoubleValue(this, "CornerPriority", 1.0, 0.0, 2.0, 0, this.corner).named("CornerPriority");
      SettingChangeListener listener11 = () -> this.rebuild2DRenderQueue();
      this.cornerPriority = (DoubleValue)cornerPrioritySetting.withChangeListener(listener11);
      this.corner2 = (new BlurShadowSettings(this, "Corner", this.corner)).disableBothByDefault().withCustomShadowColor(Color.black);
      this.picture = new SettingGroup(this, "Picture", true).visibleWhen(() -> EspRenderModeOption.isOptionSelected(EspRenderModeOption.PICTURE));
      StringValue setting = new StringValue(this, "PicturePictures", "", new String[0], this.picture);
      listener11 = () -> this.updateSelectedOverlayImage();
      this.picturePictures = (StringValue)((StringValue)setting.withChangeListener(listener11)).withDisplayName("Pictures");
      markMurderer2 = new BooleanValue(this, "StretchPicture", false, this.picture);
      this.stretchPicture = (BooleanValue)markMurderer2.withDisplayName("Stretch");
      this.scalePicture = new DoubleValue(this, "ScalePicture", 1.0, 0.1, 2.0, 2, this.picture).named("Scale");
      DoubleValue pictureYPositionPercentage2 = new DoubleValue(this, "PictureYPositionPercentage", 50.0, 0.0, 100.0, 0, this.picture).named("YPositionPercentage");
      SettingVisibilityCondition condition21 = () -> {
         return !this.stretchPicture.getValue();
      };
      this.pictureYPositionPercentage = pictureYPositionPercentage2.visibleWhen(condition21);
      DoubleValue picturePicturePriority2 = new DoubleValue(this, "PicturePicturePriority", 2.0, 0.0, 2.0, 0, this.picture).named("PicturePriority");
      listener11 = () -> this.rebuild2DRenderQueue();
      this.picturePicturePriority = (DoubleValue)picturePicturePriority2.withChangeListener(listener11);
      this.picture2 = (new BlurShadowSettings(this, "Picture", this.picture)).disableBothByDefault().withCustomShadowColor(Color.black);
      this.pictureFolder = new ActionSetting(this, "Picture folder", () -> {
         ClientFileUtil.openFolder(PICTURES_DIR);
      }, this.picture);
      this.vanilla = new SettingGroup(this, "Vanilla", true).visibleWhen(() -> EspRenderModeOption.isOptionSelected(EspRenderModeOption.VANILLA));
      markMurderer2 = new BooleanValue(this, "VanillaTeamColor", false, this.vanilla);
      this.vanillaTeamColor = (BooleanValue)markMurderer2.withDisplayName("TeamColor (players only)");
      color9 = new Color(21, 121, 230, 65);
      box3 = this.vanilla;
      color8 = color9;
      str3 = "VanillaColor";
      this.vanillaColor = new ColorSetting(this, str3, color8, box3);
      this.vanillaHitColorBool = new BooleanValue(this, "VanillaHitColorBool", true, this.vanilla);
      color9 = new Color(255, 0, 0, 65);
      box3 = this.vanilla;
      color8 = color9;
      str3 = "VanillaHitColor";
      ColorSetting colorSetting22 = (ColorSetting)new ColorSetting(this, str3, color8, box3).withDisplayName("HitColor");
      BooleanValue vanillaHitColorBool2 = this.vanillaHitColorBool;
      this.hitColor5 = (ColorSetting)colorSetting22.withVisibility(() -> vanillaHitColorBool2.getValue());
      this.entities = new TargetSelectionSettings(this, TargetFilterPresets.createRenderFilter()).withTargetsDisplayName("Entities");
      this.maximumRenderDistance = new DoubleValue(this, "MaximumRenderDistance", 500.0, 0.0, 500.0, 0);
      this.visibleEntities = new java.util.ArrayList();
      this.projectedEntityBounds = new HashMap();
      this.frustum = new Frustum();
      this.render2DPassesByPriority = new Int2ObjectAVLTreeMap();
      this.render3DPassesByPriority = new Int2ObjectAVLTreeMap();
      this.loadedOverlayImages = new HashMap();
      this.glStateSaver = GlStateSaver.create();
      this.rebuild3DRenderQueue();
      this.rebuild2DRenderQueue();
      this.refreshOverlayImages();
   }

   @Override
   public void onEnable() {
      this.refreshOverlayImages();
   }

   @Override
   public void onDisable() {
      this.projectedEntityBounds.clear();
      this.visibleEntities.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32, -100);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onTick() {
      if (mc.theWorld != null) {
         this.visibleEntities.clear();
         EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
         entityTargetSelector = entityTargetSelector.withTargetFilter(this.entities.getTargetFilter());
         entityTargetSelector = entityTargetSelector.maxDistance(this.maximumRenderDistance.getValue());
         Consumer consumer = obj -> {
            this.addIfInFrustum((EntityLivingBase)obj);
         };
         entityTargetSelector.forEach(consumer);
      }
   }

   @Override
   public void onRender2DPre(float partialTicks, ScaledResolution sr) {
      if (!this.visibleEntities.isEmpty()) {
         ObjectIterator it = this.render2DPassesByPriority.values().iterator();

         while (it.hasNext()) {
            ((Runnable)it.next()).run();
            RenderUtil.resetGlColorState();
         }
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      this.projectedEntityBounds.clear();
      if (!this.visibleEntities.isEmpty()) {
         ObjectIterator it = this.render3DPassesByPriority.values().iterator();

         while (it.hasNext()) {
            Runnable runnable = (Runnable)it.next();
            GlStateManager.pushMatrix();
            RenderUtil.begin3DRender();
            runnable.run();
            RenderUtil.end3DRender();
            GlStateManager.popMatrix();
         }

         if (EspRenderModeOption.isOptionSelected(EspRenderModeOption.REAL_2D) || EspRenderModeOption.isOptionSelected(EspRenderModeOption.CORNER) || EspRenderModeOption.isOptionSelected(EspRenderModeOption.PICTURE)) {
            ScaledResolution sr = new ScaledResolution(mc);

            for (EntityLivingBase entity : (Iterable<EntityLivingBase>)(this.visibleEntities)) {
               Vec3 vec = new Vec3(
                  entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getTimer().renderPartialTicks,
                  entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.getTimer().renderPartialTicks,
                  entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks
               );
               float f2 = entity.width / 2.0F;
               float f3 = ClientManagers.moduleManager.child.isToggled() && entity instanceof EntityPlayer ? entity.height / 1.7F : entity.height;
               this.projectedEntityBounds
                  .put(
                     entity,
                     ProjectionUtil.projectBoundingBox(
                        new AxisAlignedBB(
                              vec.xCoord - (double)f2,
                              vec.yCoord,
                              vec.zCoord - (double)f2,
                              vec.xCoord + (double)f2,
                              vec.yCoord + (double)f3,
                              vec.zCoord + (double)f2
                           )
                           .expand(0.1, 0.1, 0.1),
                        sr
                     )
                  );
            }
         }
      }
   }

   private void rebuild2DRenderQueue() {
      this.render2DPassesByPriority.clear();
      this.render2DPassesByPriority.put((int)this.real2DPriority.getValue(), (Runnable)() -> this.renderReal2DBoxes());
      this.render2DPassesByPriority.put((int)this.cornerPriority.getValue(), (Runnable)() -> this.render2DCorners());
      this.render2DPassesByPriority.put((int)this.picturePicturePriority.getValue(), (Runnable)() -> {
         if (EspRenderModeOption.PICTURE.isSelected() && mc.theWorld != null && this.espOverlayImage != null) {
            BlurShadowManager.enableOneShotPass(this.picture2.isBlurActive(), this.picture2.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderPictureOverlaysPass(shaderRenderPass));
         }
      });
   }

   private void rebuild3DRenderQueue() {
      this.render3DPassesByPriority.clear();
      Int2ObjectAVLTreeMap int2ObjectAVLTreeMap = this.render3DPassesByPriority;
      int2ObjectAVLTreeMap.put((int)this.boxPriority.getValue(), (Runnable)() -> {
         if (EspRenderModeOption.isOptionSelected(EspRenderModeOption.BOX) && mc.theWorld != null) {
            BlurShadowManager.enableOneShotPass(this.box2.isBlurActive(), this.box2.isShadowActive());
            BlurShadowManager.submitRenderTask(true, shaderRenderPass -> this.renderBoxesPass(shaderRenderPass));
         }
      });
      int2ObjectAVLTreeMap = this.render3DPassesByPriority;
      int2ObjectAVLTreeMap.put((int)this.hitBoxPriority.getValue(), (Runnable)() -> {
         if (EspRenderModeOption.isOptionSelected(EspRenderModeOption.HIT_BOX) && mc.theWorld != null) {
            BlurShadowManager.enableOneShotPass(this.hitBox2.isBlurActive(), this.hitBox2.isShadowActive());
            BlurShadowManager.submitRenderTask(true, shaderRenderPass2 -> this.renderHitBoxesPass(shaderRenderPass2));
         }
      });
      this.render3DPassesByPriority.put((int)this.collisionBoxPriority.getValue(), (Runnable)() -> this.renderCollisionBoxes());
      this.render3DPassesByPriority.put((int)this.fakeCornerPriority.getValue(), (Runnable)() -> this.render3DCorners());
   }

   private void addIfInFrustum(EntityLivingBase entity) {
      this.frustum.setPosition(mc.getRenderViewEntity().posX, mc.getRenderViewEntity().posY, mc.getRenderViewEntity().posZ);
      if (ClientManagers.moduleManager.freeCam.isToggled()) {
         this.frustum.setPosition(ClientManagers.moduleManager.freeCam.cameraPos.xCoord, ClientManagers.moduleManager.freeCam.cameraPos.yCoord, ClientManagers.moduleManager.freeCam.cameraPos.zCoord);
      }

      AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
      if (this.frustum.isBoxInFrustum(entityBoundingBox.minX - 0.5, entityBoundingBox.minY - 0.5, entityBoundingBox.minZ - 0.5, entityBoundingBox.maxX + 0.5, entityBoundingBox.maxY + 0.5, entityBoundingBox.maxZ + 0.5)
         && !this.visibleEntities.contains(entity)) {
         this.visibleEntities.add(entity);
      }
   }

   private int resolveEntityColor(EntityLivingBase entity, int n, ColorSetting colorSetting, ColorSetting colorSetting2, boolean flag, boolean flag2, int n2) {
      return this.resolveEntityColor(entity, n, colorSetting, colorSetting2, flag, flag2, n2, true);
   }

   private int resolveEntityColor(EntityLivingBase entity, int n, ColorSetting colorSetting, ColorSetting colorSetting2, boolean flag, boolean flag2, int n2, boolean flag3) {
      int n3;
      if (flag && entity.hurtTime > 0) {
         n3 = colorSetting2.getColorInt(n);
      } else if (flag2 && entity instanceof EntityPlayer) {
         n3 = ColorUtil.withAlpha(ColorUtil.getTeamColor((EntityPlayer)entity), n2);
      } else {
         n3 = colorSetting.getColorInt(n);
      }

      if (flag3 && this.markMurderer.getValue()) {
         if (ClientManagers.moduleManager.murdererFinder.isMurderer(entity.getName())) {
            int n4;
            if (this.customMurdererAlpha.getValue()) {
               n4 = (int)this.murdererAlpha.getValue(true);
            } else {
               n4 = ColorUtil.getAlpha(n3);
            }

            n3 = ColorUtil.packRgba(255, 0, (byte)0, n4);
         }
      }

      return n3;
   }

   private float scaleLineWidthByDistance(double d, EntityLivingBase entity) {
      if (mc.thePlayer.getDistanceToEntity(entity) > 1.0F) {
         double d2 = (double)(1.0F - mc.thePlayer.getDistanceToEntity(entity) / 20.0F);
         if (d2 < 0.3) {
            d2 = 0.3;
         }

         d *= d2;
      }

      return (float)d;
   }

   public boolean isValidTargetEntity(EntityLivingBase entity) {
      return this.entities.getTargetFilter().matches(entity) && (double)mc.thePlayer.getDistanceToEntity(entity) < this.maximumRenderDistance.getValue();
   }

   private void updateSelectedOverlayImage() {
      this.espOverlayImage = (EspOverlayImage)this.loadedOverlayImages.get(this.picturePictures.getSelectedValue());
   }

   private EspOverlayImage loadOverlayImage(String str) {
      File file = new File(PICTURES_DIR + File.separator + str);

      try (InputStream in = Files.newInputStream(file.toPath())) {
         BufferedImage image = ImageIO.read(in);
         if (image != null) {
            ResourceLocation location = new ResourceLocation("augustus", "pictures/" + str);
            Minecraft.getMinecraft().getTextureManager().loadTexture(location, new DynamicTexture(image));
            return new EspOverlayImage(image, location, null);
         }

         System.err.println("Failed to load image from " + file);
      } catch (IOException e) {
         e.printStackTrace();
      }

      return null;
   }

   private void refreshOverlayImages() {
      File file = new File(ClientPaths.CLIENT_DIR + File.separator + "pictures");
      if (!file.exists()) {
         file.mkdirs();
      }

      File[] files = file.listFiles(file2 -> {
         return file2.isFile() && (file2.getName().endsWith(".png") || file2.getName().endsWith(".jpg") || file2.getName().endsWith(".jpeg"));
      });
      HashSet set = new HashSet();
      if (files != null) {
         for (File file3 : files) {
            set.add(file3.getName());
         }
      }

      for (String str : (Iterable<String>)(set)) {
         EspOverlayImage espOverlayImage2 = (EspOverlayImage)this.loadedOverlayImages.get(str);
         if (espOverlayImage2 == null) {
            espOverlayImage2 = this.loadOverlayImage(str);
            if (espOverlayImage2 != null) {
               this.loadedOverlayImages.put(str, espOverlayImage2);
            }
         }
      }

      this.loadedOverlayImages.entrySet().removeIf(obj -> {
         return !set.contains(((Map.Entry)obj).getKey());
      });
      StringValue picturePictures2 = this.picturePictures;
      String[] strings = ((Collection<String>)set).stream().sorted(Comparator.comparing(String::toLowerCase)).toArray(String[]::new);
      picturePictures2.setOptions(strings);
   }

   private void render3DCorners() {
      if (EspRenderModeOption.isOptionSelected(EspRenderModeOption.THREE_E_CORNER) && mc.theWorld != null) {
         BlurShadowManager.enableOneShotPass(this.fakeCorner.isBlurActive(), this.fakeCorner.isShadowActive());
         BlurShadowManager.submitRenderTask(true, shaderRenderPass -> this.render3DCornersPass(shaderRenderPass));
      }
   }

   private void render3DCornersPass(ShaderRenderPass shaderRenderPass) {
      int n = 0;

      for (EntityLivingBase entity : (Iterable<EntityLivingBase>)(this.visibleEntities)) {
         int n2 = this.resolveEntityColor(entity, n, this.color4, this.hitColor4, this.fakeCornerHitColorBool.getValue(), this.fakeCornerTeamColor.getValue(), (byte)1);
         RenderUtil.drawEntityCornerEsp(entity, this.fakeCorner.resolvePassColor(shaderRenderPass, n2, n));
         n++;
      }
   }

   private void renderCollisionBoxes() {
      if (EspRenderModeOption.isOptionSelected(EspRenderModeOption.COLLISION_BOX) && mc.theWorld != null) {
         BlurShadowManager.enableOneShotPass(this.collisionBox2.isBlurActive(), this.collisionBox2.isShadowActive());
         BlurShadowManager.submitRenderTask(true, shaderRenderPass -> this.renderCollisionBoxesPass(shaderRenderPass));
      }
   }

   private void renderCollisionBoxesPass(ShaderRenderPass shaderRenderPass) {
      EspRenderUtil.beginColoredQuads();
      float renderPartialTicks = mc.getTimer().renderPartialTicks;

      for (int i = 0; i < this.visibleEntities.size(); i++) {
         EntityLivingBase entity = (EntityLivingBase)this.visibleEntities.get(i);
         EntityEspBox entityEspBox = entity.entityEspBox;
         int n = this.resolveEntityColor(entity, i, this.color3, this.hitColor3, this.collisionBoxHitColorBool.getValue(), this.collisionBoxTeamColor.getValue(), (int)this.collisionBoxTeamColorAlpha.getValue());
         float alphaFloat = ColorUtil.getAlphaFloat(n);
         entityEspBox.setColor(n, alphaFloat);
         entityEspBox.updateBounds(renderPartialTicks);
         float f = 0.0F;
         float f2 = 0.0F;
         if (ClientManagers.moduleManager.hitBox.isToggled()) {
            f = (float)((double)f + ClientManagers.moduleManager.hitBox.expandXZ.getValue());
            f2 = (float)((double)f2 + ClientManagers.moduleManager.hitBox.expandY.getValue());
         }

         entityEspBox.setExpansion(f, f2, f2);
         if (alphaFloat > 0.0F) {
            EspRenderUtil.addFilledBox(entityEspBox);
         }
      }

      EspRenderUtil.drawTessellator();
      double d = this.collisionBoxLineWidth.getValue() / 2.0;
      if (d > 0.0) {
         EspRenderUtil.beginColoredLines();
         GL11.glLineWidth((float)d);

         for (EntityLivingBase entity2 : (Iterable<EntityLivingBase>)(this.visibleEntities)) {
            EntityEspBox entityEspBox2 = entity2.entityEspBox;
            entityEspBox2.setAlpha(1.0F);
            EspRenderUtil.addOutlinedBox(entityEspBox2);
         }

         EspRenderUtil.drawTessellator();
      }
   }

   private void renderHitBoxesPass(ShaderRenderPass shaderRenderPass) {
      EspRenderUtil.beginColoredQuads();
      float renderPartialTicks = mc.getTimer().renderPartialTicks;

      for (int i = 0; i < this.visibleEntities.size(); i++) {
         EntityLivingBase entity = (EntityLivingBase)this.visibleEntities.get(i);
         EntityEspBox entityEspBox = entity.entityEspBox;
         int n = this.resolveEntityColor(entity, i, this.color2, this.hitColor2, this.hitBoxHitColorBool.getValue(), this.hitBoxTeamColor.getValue(), (int)this.hitBoxTeamColorAlpha.getValue());
         entityEspBox.setColor(n, ColorUtil.getAlphaFloat(n));
         entityEspBox.updateBounds(renderPartialTicks);
         float collisionBorderSize = entity.getCollisionBorderSize();
         float collisionBorderSize2 = entity.getCollisionBorderSize();
         if (ClientManagers.moduleManager.hitBox.isToggled()) {
            collisionBorderSize = (float)((double)collisionBorderSize + ClientManagers.moduleManager.hitBox.expandXZ.getValue());
            collisionBorderSize2 = (float)((double)collisionBorderSize2 + ClientManagers.moduleManager.hitBox.expandY.getValue());
         }

         entityEspBox.setExpansion(collisionBorderSize, collisionBorderSize2, collisionBorderSize2);
         EspRenderUtil.addFilledBox(entityEspBox);
      }

      EspRenderUtil.drawTessellator();
      double d = this.hitBoxLineWidth.getValue() / 2.0;
      if (d > 0.0) {
         EspRenderUtil.beginColoredLines();
         GL11.glLineWidth((float)d);

         for (EntityLivingBase entity2 : (Iterable<EntityLivingBase>)(this.visibleEntities)) {
            EntityEspBox entityEspBox2 = entity2.entityEspBox;
            entityEspBox2.setAlpha(1.0F);
            EspRenderUtil.addOutlinedBox(entityEspBox2);
         }

         EspRenderUtil.drawTessellator();
      }
   }

   private void renderBoxesPass(ShaderRenderPass shaderRenderPass) {
      EspRenderUtil.beginColoredQuads();
      float renderPartialTicks = mc.getTimer().renderPartialTicks;

      for (int i = 0; i < this.visibleEntities.size(); i++) {
         EntityLivingBase entity = (EntityLivingBase)this.visibleEntities.get(i);
         EntityEspBox entityEspBox = entity.entityEspBox;
         int n = this.resolveEntityColor(entity, i, this.color6, this.hitColor, this.boxHitColorBool.getValue(), this.teamColorBox.getValue(), (int)this.teamColorAlphaBox.getValue());
         entityEspBox.setColor(n, ColorUtil.getAlphaFloat(n));
         entityEspBox.updateBounds(renderPartialTicks);
         float f = 0.0F;
         float f2 = 0.12F;
         entityEspBox.setExpansion(0.1F, f2, f);
         EspRenderUtil.addRotatedFilledBox(entityEspBox);
      }

      EspRenderUtil.drawTessellator();
      double d = this.boxLineWidth.getValue() / 2.0;
      if (d > 0.0) {
         GL11.glLineWidth((float)d);
         EspRenderUtil.beginColoredLines();

         for (EntityLivingBase entity2 : (Iterable<EntityLivingBase>)(this.visibleEntities)) {
            EntityEspBox entityEspBox2 = entity2.entityEspBox;
            entityEspBox2.setAlpha(1.0F);
            EspRenderUtil.addRotatedOutlinedBox(entityEspBox2);
         }

         EspRenderUtil.drawTessellator();
      }
   }

   private void renderPictureOverlaysPass(ShaderRenderPass shaderRenderPass) {
      this.glStateSaver.setBlend(true);
      this.glStateSaver.setAlpha(true);
      GlStateManager.resetColor();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      EspRenderUtil.beginTexturedQuads();
      mc.getTextureManager().bindTexture(EspOverlayImage.getResourceLocation(this.espOverlayImage));

      for (EntityLivingBase entity : (Iterable<EntityLivingBase>)(this.visibleEntities)) {
         ProjectedBounds2D projectedBounds2D = (ProjectedBounds2D)this.projectedEntityBounds.get(entity);
         if (projectedBounds2D != null) {
            double d = (double)this.espOverlayImage.getWidth() / (double)this.espOverlayImage.getHeight();
            double value = this.scalePicture.getValue();
            double d2 = (projectedBounds2D.maxX - projectedBounds2D.minX) * value;
            double d3 = (projectedBounds2D.maxY - projectedBounds2D.minY) * value;
            double d4 = projectedBounds2D.minX + (projectedBounds2D.maxX - projectedBounds2D.minX) / 2.0;
            double d5 = projectedBounds2D.minY + (projectedBounds2D.maxY - projectedBounds2D.minY) / 2.0;
            double d6 = d2;
            double d7 = d3;
            if (!this.stretchPicture.getValue()) {
               d6 = d2;
               d7 = d2 / d;
               if (d7 > d3) {
                  d7 = d3;
                  d6 = d3 * d;
               }
            }

            EspRenderUtil.addTexturedQuad(d4 - d2 / 2.0 + (d2 - d6) / 2.0, d5 - d3 / 2.0 + (d3 - d7) * (this.pictureYPositionPercentage.getValue() / 100.0), 0.0F, 0.0F, d6, d7, (float)d6, (float)d7);
         }
      }

      EspRenderUtil.drawTessellator();
      this.glStateSaver.restoreBlend();
      this.glStateSaver.restoreAlpha();
   }

   private void render2DCorners() {
      if (EspRenderModeOption.CORNER.isSelected() && mc.theWorld != null) {
         BlurShadowManager.enableOneShotPass(this.corner2.isBlurActive(), this.corner2.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.render2DCornersPass(shaderRenderPass));
      }
   }

   private void render2DCornersPass(ShaderRenderPass shaderRenderPass) {
      this.glStateSaver.setBlend(true);
      this.glStateSaver.setTexture2D(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glLineWidth((float)this.lineWidthCorner.getValue());
      EspRenderUtil.beginColoredLines();

      for (int i = 0; i < this.visibleEntities.size(); i++) {
         EntityLivingBase entity = (EntityLivingBase)this.visibleEntities.get(i);
         ProjectedBounds2D projectedBounds2D = (ProjectedBounds2D)this.projectedEntityBounds.get(entity);
         if (projectedBounds2D != null) {
            int n = entity.hurtTime != 0 && this.cornerHitColorBool.getValue()
               ? this.cornerHitColor.getColorInt(i)
               : (this.cornerTeamColor.getValue() && entity instanceof EntityPlayer ? ColorUtil.getTeamColor((EntityPlayer)entity) : this.color5.getColorInt(i));
            if (this.markMurderer.getValue()) {
               if (ClientManagers.moduleManager.murdererFinder.isMurderer(entity.getName())) {
                  n = ColorUtil.packRgba(255, 0, 0, ColorUtil.getAlpha(n));
               }
            }

            EspRenderUtil.add2DCorners(
               projectedBounds2D.minX,
               projectedBounds2D.minY,
               projectedBounds2D.maxX,
               projectedBounds2D.maxY,
               MathHelper.clamp_double(this.horizontalPercentage.getValue(), 0.0, 100.0),
               MathHelper.clamp_double(this.verticalPercentage.getValue(), 0.0, 100.0),
               this.corner2.resolvePassColor(shaderRenderPass, n, i)
            );
         }
      }

      EspRenderUtil.drawTessellator();
      this.glStateSaver.restoreTexture2D();
      this.glStateSaver.restoreBlend();
   }

   private void renderReal2DBoxes() {
      if (EspRenderModeOption.REAL_2D.isSelected() && mc.theWorld != null) {
         BlurShadowManager.enableOneShotPass(this.real2D2.isBlurActive(), this.real2D2.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderReal2DBoxesPass(shaderRenderPass));
      }
   }

   private void renderReal2DBoxesPass(ShaderRenderPass shaderRenderPass) {

      for (int i = 0; i < this.visibleEntities.size(); i++) {
         EntityLivingBase entity = (EntityLivingBase)this.visibleEntities.get(i);
         ProjectedBounds2D projectedBounds2D = (ProjectedBounds2D)this.projectedEntityBounds.get(entity);
         if (projectedBounds2D != null) {
            int n = entity.hurtTime != 0 && this.real2DHitColorBool.getValue() ? this.fillColorHit.getColorInt(i) : this.fillColor.getColorInt(i);
            if (this.markMurderer.getValue()) {
               if (ClientManagers.moduleManager.murdererFinder.isMurderer(entity.getName())) {
                  n = ColorUtil.packRgba(255, 0, 0, ColorUtil.getAlpha(n));
               }
            }

            if (shaderRenderPass != ShaderRenderPass.SHADOW) {
               RenderUtil.drawRect(projectedBounds2D.minX, projectedBounds2D.minY, projectedBounds2D.maxX, projectedBounds2D.maxY, n);
            }

            int n3 = this.resolveEntityColor(entity, i, this.outlineColor, this.outlineColorHit, this.real2DHitColorBool.getValue(), this.real2DTeamColor.getValue(), (int)this.real2DTeamColorAlpha.getValue(), false);
            if (shaderRenderPass != ShaderRenderPass.BLUR) {
               RenderUtil.drawRectOutline(projectedBounds2D.minX, projectedBounds2D.minY, projectedBounds2D.maxX, projectedBounds2D.maxY, 1.0F, this.real2D2.resolvePassColor(shaderRenderPass, n3, i));
            }
         }
      }
   }

   private boolean isMurdererAlphaVisible() {
      return this.markMurderer.getValue() && this.customMurdererAlpha.getValue();
   }


}
