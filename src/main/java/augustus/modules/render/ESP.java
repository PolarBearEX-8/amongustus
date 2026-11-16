package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import augustus.modes.render.EspRenderMode;
import augustus.modules.misc.MurdererFinder;
import client.ClientGlobals;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.EntityEspRenderer;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ESPTargetEntity;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.ConsumerTask;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.RunnableTask;
import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class ESP extends Module implements RenderListener, TickListener {
    public MultiSelectSetting settingModes;
    public BooleanSetting settingMarkMurderer;
    public BooleanSetting settingCustomMurdererAlpha;
    public NumberSetting settingMurdererAlpha;
    public SettingGroup settingBox;
    public BooleanSetting settingTeamColorBox;
    public ColorSetting settingBoxColor;
    public BooleanSetting settingBoxHitColorBool;
    public ColorSetting settingBoxHitColor;
    public NumberSetting settingBoxLineWidth;
    public NumberSetting settingBoxPriority;
    public ShaderOptions settingBox2;
    public SettingGroup settingHitBox;
    public BooleanSetting settingHitBoxTeamColor;
    public ColorSetting settingHitBoxColor;
    public BooleanSetting settingHitBoxHitColorBool;
    public ColorSetting settingHitBoxHitColor;
    public NumberSetting settingHitBoxLineWidth;
    public NumberSetting settingHitBoxPriority;
    public ShaderOptions settingHitBox2;
    public SettingGroup settingCollisionBox;
    public BooleanSetting settingCollisionBoxTeamColor;
    public ColorSetting settingCollisionBoxColor;
    public BooleanSetting settingCollisionBoxHitColorBool;
    public ColorSetting settingCollisionBoxHitColor;
    public NumberSetting settingCollisionBoxLineWidth;
    public NumberSetting settingCollisionBoxPriority;
    public ShaderOptions settingCollisionBox2;
    public SettingGroup setting3DCorner;
    public BooleanSetting settingFakeCornerTeamColor;
    public ColorSetting settingFakeCornerColor;
    public BooleanSetting settingFakeCornerHitColorBool;
    public ColorSetting settingFakeCornerHitColor;
    public NumberSetting settingFakeCornerPriority;
    public ShaderOptions settingFakeCorner;
    public SettingGroup settingReal2D;
    public BooleanSetting settingReal2DTeamColor;
    public ColorSetting settingReal2DColor1;
    public ColorSetting settingReal2DColor2;
    public BooleanSetting settingReal2DHitColorBool;
    public ColorSetting settingReal2DHitColor1;
    public ColorSetting settingReal2DHitColor2;
    public NumberSetting settingReal2DPriority;
    public ShaderOptions settingReal2D2;
    public SettingGroup settingCorner;
    public BooleanSetting settingCornerTeamColor;
    public ColorSetting settingCornerColor;
    public BooleanSetting settingCornerHitColorBool;
    public ColorSetting settingCornerHitColor;
    public NumberSetting settingLineWidthCorner;
    public NumberSetting settingHorizontalPercentage;
    public NumberSetting settingVerticalPercentage;
    public NumberSetting settingCornerPriority;
    public ShaderOptions settingCorner2;
    public SettingGroup settingVanilla;
    public BooleanSetting settingVanillaTeamColor;
    public ColorSetting settingVanillaColor;
    public BooleanSetting settingVanillaHitColorBool;
    public ColorSetting settingVanillaHitColor;
    public MultiSelectSetting settingEntities;
    public NumberSetting settingMaximumRenderDistance;
    public ArrayList itemList3;
    public HashMap entryMap;
    public EntityEspRenderer entityEspRenderer;
    public Int2ObjectAVLTreeMap int2ObjectAVLTreeMap;
    public Int2ObjectAVLTreeMap int2ObjectAVLTreeMap2;
    public static String[] stringArray;

    public ESP() {
        super("ESP", "SpielerDurchWandSehen", new Color(47, 134, 124, 255), Category.RENDER);
        EspRenderMode[] espRenderModeArray = EspRenderMode.values();
        this.settingModes = new MultiSelectSetting(this, "Modes", espRenderModeArray);
        this.settingMarkMurderer = new BooleanSetting(this, "MarkMurderer", true);
        this.settingCustomMurdererAlpha = (BooleanSetting)new BooleanSetting(this, "Custom Murderer Alpha", false).getSetting(() -> this.settingMarkMurderer.isEnabled());
        this.settingMurdererAlpha = new NumberSetting(this, "Murderer Alpha", 65.0, 0.0, 255.0, 0).getNumberSetting(() -> this.isMurdererAlphaVisible());
        this.settingBox = new SettingGroup(this, "Box", true).getSettingGroup(() -> canExecute6());
        this.settingTeamColorBox = (BooleanSetting)new BooleanSetting(this, "TeamColorBox", false, this.settingBox).getSetting("TeamColor");
        Color color = new Color(21, 121, 230, 65);
        ColorSetting colorSetting = new ColorSetting(this, "BoxColor", color, this.settingBox);
        Setting setting3 = colorSetting.getSetting("Color");
        this.settingBoxColor = (ColorSetting)(((ColorSetting)setting3).getSetting(() -> this.isBoxColorVisible()));
        this.settingBoxHitColorBool = new BooleanSetting(this, "BoxHitColorBool", false, this.settingBox);
        Color color2 = new Color(255, 0, 0, 65);
        ColorSetting colorSetting2 = new ColorSetting(this, "BoxHitColor", color2, this.settingBox);
        this.settingBoxHitColor = (ColorSetting)(((ColorSetting)colorSetting2.getSetting("HitColor")).getSetting(() -> this.settingBoxHitColorBool.isEnabled()));
        this.settingBoxLineWidth = new NumberSetting(this, "BoxLineWidth", 6.0, 0.0, 15.0, 0, this.settingBox).getNumberSetting("LineWidth");
        this.settingBoxPriority = (NumberSetting)new NumberSetting(this, "BoxPriority", 0.0, 0.0, 3.0, 0, this.settingBox).getNumberSetting("3DPriority").getSetting(() -> this.onBoxPriorityAction());
        ShaderOptions shaderOptions2 = new ShaderOptions(this, "Box", this.settingBox).getShaderOptions2();
        Color black = Color.black;
        this.settingBox2 = shaderOptions2.getShaderOptions(black);
        this.settingHitBox = new SettingGroup(this, "HitBox", true).getSettingGroup(() -> canExecute5());
        this.settingHitBoxTeamColor = (BooleanSetting)new BooleanSetting(this, "HitBoxTeamColor", false, this.settingHitBox).getSetting("TeamColor");
        Color color3 = new Color(21, 121, 230, 65);
        Setting setting8 = new ColorSetting(this, "HitBoxColor", color3, this.settingHitBox).getSetting("Color");
        this.settingHitBoxColor = (ColorSetting)(((ColorSetting)setting8).getSetting(() -> this.isHitBoxColorVisible()));
        this.settingHitBoxHitColorBool = new BooleanSetting(this, "HitBoxHitColorBool", false, this.settingHitBox);
        Color color4 = new Color(255, 0, 0, 65);
        ColorSetting colorSetting5 = new ColorSetting(this, "HitBoxHitColor", color4, this.settingHitBox);
        this.settingHitBoxHitColor = (ColorSetting)(((ColorSetting)colorSetting5.getSetting("HitColor")).getSetting(() -> this.settingHitBoxHitColorBool.isEnabled()));
        this.settingHitBoxLineWidth = new NumberSetting(this, "HitBoxLineWidth", 6.0, 0.0, 15.0, 0, this.settingHitBox).getNumberSetting("LineWidth");
        this.settingHitBoxPriority = (NumberSetting)new NumberSetting(this, "HitBoxPriority", 1.0, 0.0, 3.0, 0, this.settingHitBox).getNumberSetting("3DPriority").getSetting(() -> this.onBoxPriorityAction());
        ShaderOptions shaderOptions22 = new ShaderOptions(this, "HitBox", this.settingHitBox).getShaderOptions2();
        Color black2 = Color.black;
        this.settingHitBox2 = shaderOptions22.getShaderOptions(black2);
        this.settingCollisionBox = new SettingGroup(this, "CollisionBox", true).getSettingGroup(() -> canExecute7());
        this.settingCollisionBoxTeamColor = (BooleanSetting)new BooleanSetting(this, "CollisionBoxTeamColor", false, this.settingCollisionBox).getSetting("TeamColor");
        Color color5 = new Color(21, 121, 230, 65);
        Setting setting13 = new ColorSetting(this, "CollisionBoxColor", color5, this.settingCollisionBox).getSetting("Color");
        this.settingCollisionBoxColor = (ColorSetting)(((ColorSetting)setting13).getSetting(() -> this.isCollisionBoxColorVisible()));
        this.settingCollisionBoxHitColorBool = new BooleanSetting(this, "CollisionBoxHitColorBool", false, this.settingCollisionBox);
        Color color6 = new Color(255, 0, 0, 65);
        ColorSetting colorSetting8 = new ColorSetting(this, "CollisionBoxHitColor", color6, this.settingCollisionBox);
        this.settingCollisionBoxHitColor = (ColorSetting)(((ColorSetting)colorSetting8.getSetting("HitColor")).getSetting(() -> this.settingCollisionBoxHitColorBool.isEnabled()));
        this.settingCollisionBoxLineWidth = new NumberSetting(this, "CollisionBoxLineWidth", 6.0, 0.0, 15.0, 0, this.settingCollisionBox).getNumberSetting("LineWidth");
        this.settingCollisionBoxPriority = (NumberSetting)new NumberSetting(this, "CollisionBoxPriority", 2.0, 0.0, 3.0, 0, this.settingCollisionBox).getNumberSetting("3DPriority").getSetting(() -> this.onBoxPriorityAction());
        ShaderOptions shaderOptions23 = new ShaderOptions(this, "CollisionBox", this.settingCollisionBox).getShaderOptions2();
        Color black3 = Color.black;
        this.settingCollisionBox2 = shaderOptions23.getShaderOptions(black3);
        this.setting3DCorner = new SettingGroup(this, "3DCorner", true).getSettingGroup(() -> canExecute8());
        this.settingFakeCornerTeamColor = (BooleanSetting)new BooleanSetting(this, "FakeCornerTeamColor", false, this.setting3DCorner).getSetting("TeamColor");
        Color color7 = new Color(21, 121, 230, 65);
        Setting setting18 = new ColorSetting(this, "FakeCornerColor", color7, this.setting3DCorner).getSetting("Color");
        this.settingFakeCornerColor = (ColorSetting)(((ColorSetting)setting18).getSetting(() -> this.isFakeCornerColorVisible()));
        this.settingFakeCornerHitColorBool = new BooleanSetting(this, "FakeCornerHitColorBool", false, this.setting3DCorner);
        Color color8 = new Color(255, 0, 0, 65);
        ColorSetting colorSetting11 = new ColorSetting(this, "FakeCornerHitColor", color8, this.setting3DCorner);
        this.settingFakeCornerHitColor = (ColorSetting)(((ColorSetting)colorSetting11.getSetting("HitColor")).getSetting(() -> this.settingFakeCornerHitColorBool.isEnabled()));
        this.settingFakeCornerPriority = (NumberSetting)new NumberSetting(this, "FakeCornerPriority", 3.0, 0.0, 3.0, 0, this.setting3DCorner).getNumberSetting("3DPriority").getSetting(() -> this.onBoxPriorityAction());
        ShaderOptions shaderOptions24 = new ShaderOptions(this, "FakeCorner", this.setting3DCorner).getShaderOptions2();
        Color black4 = Color.black;
        this.settingFakeCorner = shaderOptions24.getShaderOptions(black4);
        this.settingReal2D = new SettingGroup(this, "Real2D", true).getSettingGroup(() -> canExecute11());
        this.settingReal2DTeamColor = (BooleanSetting)new BooleanSetting(this, "Real2DTeamColor", false, this.settingReal2D).getSetting("TeamColor");
        Color color9 = new Color(21, 121, 230, 60);
        this.settingReal2DColor1 = (ColorSetting)new ColorSetting(this, "Real2DColor1", color9, this.settingReal2D).getSetting("FillColor");
        Color color10 = new Color(0, 0, 0);
        Setting setting24 = new ColorSetting(this, "Real2DColor2", color10, this.settingReal2D).getSetting("OutlineColor");
        this.settingReal2DColor2 = (ColorSetting)(((ColorSetting)setting24).getSetting(() -> this.isReal2DColor2Visible()));
        this.settingReal2DHitColorBool = new BooleanSetting(this, "Real2DHitColorBool", false, this.settingReal2D);
        Color color11 = new Color(255, 0, 0, 60);
        ColorSetting colorSetting15 = new ColorSetting(this, "Real2DHitColor1", color11, this.settingReal2D);
        this.settingReal2DHitColor1 = (ColorSetting)(((ColorSetting)colorSetting15.getSetting("FillColorHit")).getSetting(() -> this.settingReal2DHitColorBool.isEnabled()));
        Color color12 = new Color(0, 0, 0);
        ColorSetting colorSetting17 = new ColorSetting(this, "Real2DHitColor2", color12, this.settingReal2D);
        this.settingReal2DHitColor2 = (ColorSetting)(((ColorSetting)colorSetting17.getSetting("OutlineColorHit")).getSetting(() -> this.settingReal2DHitColorBool.isEnabled()));
        this.settingReal2DPriority = (NumberSetting)new NumberSetting(this, "Real2DPriority", 0.0, 0.0, 1.0, 0, this.settingReal2D).getNumberSetting("2DPriority").getSetting(() -> this.onReal2DPriorityAction());
        ShaderOptions shaderOptions25 = new ShaderOptions(this, "Real2D", this.settingReal2D).getShaderOptions2();
        Color black5 = Color.black;
        this.settingReal2D2 = shaderOptions25.getShaderOptions(black5);
        this.settingCorner = new SettingGroup(this, "Corner", true).getSettingGroup(() -> canExecute9());
        this.settingCornerTeamColor = (BooleanSetting)new BooleanSetting(this, "CornerTeamColor", false, this.settingCorner).getSetting("TeamColor");
        Color color13 = new Color(21, 121, 230);
        Setting setting30 = new ColorSetting(this, "CornerColor", color13, this.settingCorner).getSetting("Color");
        this.settingCornerColor = (ColorSetting)(((ColorSetting)setting30).getSetting(() -> this.isCornerColorVisible()));
        this.settingCornerHitColorBool = (BooleanSetting)new BooleanSetting(this, "CornerHitColorBool", true, this.settingCorner).getSetting("HitColor");
        Color color14 = new Color(21, 121, 230);
        this.settingCornerHitColor = (ColorSetting)new ColorSetting(this, "CornerHitColor", color14, this.settingCorner).getSetting(() -> this.settingCornerHitColorBool.isEnabled());
        this.settingLineWidthCorner = new NumberSetting(this, "LineWidthCorner", 2.0, 1.0, 5.0, 0, this.settingCorner).getNumberSetting("LineWidth");
        this.settingHorizontalPercentage = new NumberSetting(this, "HorizontalPercentage", 55.0, 1.0, 100.0, 0, this.settingCorner);
        this.settingVerticalPercentage = new NumberSetting(this, "VerticalPercentage", 45.0, 1.0, 100.0, 0, this.settingCorner);
        this.settingCornerPriority = (NumberSetting)new NumberSetting(this, "CornerPriority", 1.0, 0.0, 1.0, 0, this.settingCorner).getNumberSetting("2DPriority").getSetting(() -> this.onReal2DPriorityAction());
        ShaderOptions shaderOptions26 = new ShaderOptions(this, "Corner", this.settingCorner).getShaderOptions2();
        Color black6 = Color.black;
        this.settingCorner2 = shaderOptions26.getShaderOptions(black6);
        this.settingVanilla = new SettingGroup(this, "Vanilla", true).getSettingGroup(() -> canExecute10());
        this.settingVanillaTeamColor = (BooleanSetting)new BooleanSetting(this, "VanillaTeamColor", false, this.settingVanilla).getSetting("TeamColor");
        Color color15 = new Color(21, 121, 230, 65);
        this.settingVanillaColor = (ColorSetting)new ColorSetting(this, "VanillaColor", color15, this.settingVanilla).getSetting(() -> this.isVanillaColorVisible());
        this.settingVanillaHitColorBool = new BooleanSetting(this, "VanillaHitColorBool", true, this.settingVanilla);
        Color color16 = new Color(255, 0, 0, 65);
        ColorSetting colorSetting22 = new ColorSetting(this, "VanillaHitColor", color16, this.settingVanilla);
        this.settingVanillaHitColor = (ColorSetting)(((ColorSetting)colorSetting22.getSetting("HitColor")).getSetting(() -> this.settingVanillaHitColorBool.isEnabled()));
        ESPTargetEntity[] eSPTargetEntityArray = ESPTargetEntity.values();
        this.settingEntities = new MultiSelectSetting(this, "Entities", eSPTargetEntityArray);
        this.settingMaximumRenderDistance = new NumberSetting(this, "MaximumRenderDistance", 500.0, 0.0, 500.0, 0);
        this.itemList3 = new ArrayList();
        this.entryMap = new HashMap();
        EntityEspRenderer entityEspRenderer2 = new EntityEspRenderer();
        this.entityEspRenderer = entityEspRenderer2;
        this.int2ObjectAVLTreeMap = new Int2ObjectAVLTreeMap();
        this.int2ObjectAVLTreeMap2 = new Int2ObjectAVLTreeMap();
        this.onBoxPriorityAction();
        this.onReal2DPriorityAction();
    }

    @Override
    public void onEnable() {
        this.registerEvent(32, -100);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.entryMap.clear();
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (!this.itemList3.isEmpty()) {
            ObjectIterator objectIterator = this.int2ObjectAVLTreeMap.values().iterator();

            while (objectIterator.hasNext()) {
                ((Runnable)objectIterator.next()).run();
                RenderUtils.processAction();
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        this.entryMap.clear();
        if (!this.itemList3.isEmpty()) {
            for (Object object : this.int2ObjectAVLTreeMap2.values()) {
                Runnable runnable = (Runnable)object;
                GL11.glPushMatrix();
                RenderUtils.updateState();
                runnable.run();
                RenderUtils.prepareScissor();
                GL11.glPopMatrix();
            }

            if (EspRenderMode.checkEspRenderMode(EspRenderMode.REAL_2D) || EspRenderMode.checkEspRenderMode(EspRenderMode.CORNER)) {
                ScaledResolution scaledResolution = new ScaledResolution(minecraft);

                for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>) this.itemList3) {
                    Vec3 vec = new Vec3(
                    livingEntity.lastTickPosX + (livingEntity.posX - livingEntity.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6,
                    livingEntity.lastTickPosY + (livingEntity.posY - livingEntity.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6,
                    livingEntity.lastTickPosZ + (livingEntity.posZ - livingEntity.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6
                    );
                    float value = livingEntity.width / 2.0F;
                    float value2;
                    if (ClientGlobals.moduleManager.child.isToggled() && livingEntity instanceof EntityPlayer) {
                        value2 = livingEntity.height / 1.7F;
                    } else {
                        value2 = livingEntity.height;
                    }

                    this.entryMap
                    .put(
                    livingEntity,
                    RuntimeBridge.projectBoundingBox(
                    new AxisAlignedBB(
                    vec.xCoord - (double)value,
                    vec.yCoord,
                    vec.zCoord - (double)value,
                    vec.xCoord + (double)value,
                    vec.yCoord + (double)value2,
                    vec.zCoord + (double)value
                    )
                    .offset(0.1, 0.1, 0.1),
                    scaledResolution
                    )
                    );
                }
            }
        }
    }

    @Override
    public void onRunTick() {
        if (minecraft.theWorld != null) {
            this.itemList3.clear();

            for (Object object : minecraft.theWorld.loadedEntityList) {
                if (object instanceof EntityLivingBase) {
                    EntityLivingBase livingEntity = (EntityLivingBase)object;
                    if (this.checkEntityLivingBase(livingEntity)) {
                        this.applyEntityLivingBase(livingEntity);
                    }
                }
            }
        }
    }

    private int getIndex(EntityLivingBase livingEntity, int index, ColorSetting colorSetting, ColorSetting colorSetting2, boolean enabled, boolean enabled2) {
        return this.getIndex(livingEntity, index, colorSetting, colorSetting2, enabled, enabled2, true);
    }

    private void applyRenderPass(RenderPass renderPass) {
        int index2 = 0;

        for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>) this.itemList3) {
            boolean enabled = this.settingFakeCornerHitColorBool.isEnabled();
            boolean enabled2 = this.settingFakeCornerTeamColor.isEnabled();
            int index = this.getIndex(livingEntity, index2, this.settingFakeCornerColor, this.settingFakeCornerHitColor, enabled, enabled2);
            RenderUtils.applyEntityLivingBase(livingEntity, this.settingFakeCorner.getIndex(renderPass, index, index2));
            index2++;
        }
    }

    private void updateState() {
        if (EspRenderMode.REAL_2D.isSelected() && minecraft.theWorld != null) {
            boolean enabled = this.settingReal2D2.canExecute();
            boolean enabled2 = this.settingReal2D2.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass3(renderPass);
            MovementHandler.applyRunnableTask(false, runnableTask);
        }
    }

    public static void setStringArray(String[] textArray) {
        stringArray = textArray;
    }

    private int getIndex(EntityLivingBase livingEntity, int index2, ColorSetting colorSetting, ColorSetting colorSetting2, boolean enabled, boolean enabled2, boolean enabled3) {
        int index3;
        if (enabled && livingEntity.hurtTime > 0) {
            index3 = colorSetting2.getInt12(index2);
        } else {
            colorSetting.getInt12(0);
            if (enabled2 && livingEntity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)livingEntity;
                int index = RandomUtil.getIndex(player);
                int int9 = colorSetting.getInt9();
                index3 = RandomUtil.getInt13(index, int9);
            } else {
                index3 = colorSetting.getInt12(index2);
            }
        }

        if (enabled3 && this.settingMarkMurderer.isEnabled()) {
            MurdererFinder murdererFinder = ClientGlobals.moduleManager.murdererFinder;
            String text = livingEntity.getText();
            if (murdererFinder.checkString(text)) {
                int index4;
                if (this.settingCustomMurdererAlpha.isEnabled()) {
                    index4 = (int)this.settingMurdererAlpha.getDouble2(true);
                } else {
                    index4 = RandomUtil.processAction3(index3);
                }

                index3 = RandomUtil.getInt12(255, 0, 0, index4);
            }
        }

        return index3;
    }

    private void updateState2() {
        if (EspRenderMode.checkEspRenderMode(EspRenderMode.BOX) && minecraft.theWorld != null) {
            boolean enabled = this.settingBox2.canExecute();
            boolean enabled2 = this.settingBox2.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass4(renderPass);
            MovementHandler.applyRunnableTask(true, runnableTask);
        }
    }

    private void updateState3() {
        if (EspRenderMode.checkEspRenderMode(EspRenderMode.COLLISION_BOX) && minecraft.theWorld != null) {
            boolean enabled = this.settingCollisionBox2.canExecute();
            boolean enabled2 = this.settingCollisionBox2.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass2(renderPass);
            MovementHandler.applyRunnableTask(true, runnableTask);
        }
    }

    private void applyRenderPass2(RenderPass renderPass) {
        int index2 = 0;

        for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>) this.itemList3) {
            boolean enabled = this.settingCollisionBoxHitColorBool.isEnabled();
            boolean enabled2 = this.settingCollisionBoxTeamColor.isEnabled();
            int index = this.getIndex(livingEntity, index2, this.settingCollisionBoxColor, this.settingCollisionBoxHitColor, enabled, enabled2);
            double offset = (double)((float)this.settingCollisionBoxLineWidth.getValue() / 2.0F);
            float scaleFactor = this.getScaleFactor(offset, livingEntity);
            RenderUtils.applyEntity5(livingEntity, this.settingCollisionBox2.getIndex(renderPass, index, index2), 1.0F, scaleFactor, true);
            index2++;
        }
    }

    private void onBoxPriorityAction() {
        this.int2ObjectAVLTreeMap2.clear();
        this.int2ObjectAVLTreeMap2.put((int)this.settingBoxPriority.getValue(), ((Runnable)(() -> this.updateState2())));
        this.int2ObjectAVLTreeMap2.put((int)this.settingHitBoxPriority.getValue(), ((Runnable)(() -> this.updateState5())));
        this.int2ObjectAVLTreeMap2.put((int)this.settingCollisionBoxPriority.getValue(), ((Runnable)(() -> this.updateState3())));
        this.int2ObjectAVLTreeMap2.put((int)this.settingFakeCornerPriority.getValue(), ((Runnable)(() -> this.updateState6())));
    }

    private static boolean canExecute8() {
        return EspRenderMode.checkEspRenderMode(EspRenderMode.THREE_E_CORNER);
    }

    private void applyRenderPass3(RenderPass renderPass) {
        int index2 = 0;

        for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>) this.itemList3) {
            double[] coordArray = (double[])this.entryMap.get(livingEntity);
            if (coordArray != null) {
                int index3;
                if (livingEntity.hurtTime != 0 && this.settingReal2DHitColorBool.isEnabled()) {
                    index3 = this.settingReal2DHitColor1.getInt12(index2);
                } else {
                    index3 = this.settingReal2DColor1.getInt12(index2);
                }

                if (this.settingMarkMurderer.isEnabled()) {
                    MurdererFinder murdererFinder = ClientGlobals.moduleManager.murdererFinder;
                    String text = livingEntity.getText();
                    if (murdererFinder.checkString(text)) {
                        int index4 = RandomUtil.processAction3(index3);
                        index3 = RandomUtil.getInt12(255, 0, 0, index4);
                    }
                }

                if (renderPass != RenderPass.SHADOW) {
                    RenderUtils.processDouble4(coordArray[0], coordArray[1], coordArray[2], coordArray[3], index3);
                }

                boolean enabled = this.settingReal2DHitColorBool.isEnabled();
                boolean enabled2 = this.settingReal2DTeamColor.isEnabled();
                int index = this.getIndex(livingEntity, index2, this.settingReal2DColor2, this.settingReal2DHitColor2, enabled, enabled2, false);
                if (renderPass != RenderPass.BLUR) {
                    double offset = coordArray[0];
                    double offset2 = coordArray[1];
                    double offset3 = coordArray[2];
                    double offset4 = coordArray[3];
                    RenderUtils.processDouble10(offset, offset2, offset3, offset4, 1.0F, this.settingReal2D2.getIndex(renderPass, index, index2));
                }

                index2++;
            }
        }
    }

    public static String[] getStringArray() {
        return stringArray;
    }

    private void updateState4() {
        if (EspRenderMode.CORNER.isSelected() && minecraft.theWorld != null) {
            boolean enabled = this.settingCorner2.canExecute();
            boolean enabled2 = this.settingCorner2.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass6(renderPass);
            MovementHandler.applyRunnableTask(false, runnableTask);
        }
    }

    private static boolean canExecute9() {
        return EspRenderMode.checkEspRenderMode(EspRenderMode.CORNER);
    }

    private void updateState5() {
        if (EspRenderMode.checkEspRenderMode(EspRenderMode.HIT_BOX) && minecraft.theWorld != null) {
            boolean enabled = this.settingHitBox2.canExecute();
            boolean enabled2 = this.settingHitBox2.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass5(renderPass);
            MovementHandler.applyRunnableTask(true, runnableTask);
        }
    }

    private void applyRenderPass4(RenderPass renderPass) {
        int index2 = 0;

        for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>) this.itemList3) {
            boolean enabled = this.settingBoxHitColorBool.isEnabled();
            boolean enabled2 = this.settingTeamColorBox.isEnabled();
            int index = this.getIndex(livingEntity, index2, this.settingBoxColor, this.settingBoxHitColor, enabled, enabled2);
            double offset = (double)((float)this.settingBoxLineWidth.getValue() / 2.0F);
            float scaleFactor = this.getScaleFactor(offset, livingEntity);
            RenderUtils.applyEntity4(livingEntity, this.settingBox2.getIndex(renderPass, index, index2), 1.0F, scaleFactor);
            index2++;
        }
    }

    private boolean isBoxColorVisible() {
        return !this.settingTeamColorBox.isEnabled();
    }

    private void onReal2DPriorityAction() {
        this.int2ObjectAVLTreeMap.clear();
        this.int2ObjectAVLTreeMap.put((int)this.settingReal2DPriority.getValue(), ((Runnable)(() -> this.updateState())));
        this.int2ObjectAVLTreeMap.put((int)this.settingCornerPriority.getValue(), ((Runnable)(() -> this.updateState4())));
    }

    private void applyRenderPass5(RenderPass renderPass) {
        int index2 = 0;

        for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>) this.itemList3) {
            boolean enabled = this.settingHitBoxHitColorBool.isEnabled();
            boolean enabled2 = this.settingHitBoxTeamColor.isEnabled();
            int index = this.getIndex(livingEntity, index2, this.settingHitBoxColor, this.settingHitBoxHitColor, enabled, enabled2);
            double offset = (double)((float)this.settingHitBoxLineWidth.getValue() / 2.0F);
            float scaleFactor = this.getScaleFactor(offset, livingEntity);
            RenderUtils.applyEntity5(livingEntity, this.settingHitBox2.getIndex(renderPass, index, index2), 1.0F, scaleFactor, false);
            index2++;
        }
    }

    public boolean checkEntityLivingBase(EntityLivingBase livingEntity) {
        if (livingEntity instanceof EntityPlayerSP) {
            return false;
        } else {
            boolean enabled = ESPTargetEntity.checkESPTargetEntity(ESPTargetEntity.PLAYERS);
            boolean enabled2 = ESPTargetEntity.checkESPTargetEntity(ESPTargetEntity.MOBS);
            boolean enabled3 = ESPTargetEntity.checkESPTargetEntity(ESPTargetEntity.ANIMALS);
            boolean enabled4 = ESPTargetEntity.checkESPTargetEntity(ESPTargetEntity.VILLAGER);
            boolean enabled5 = ESPTargetEntity.checkESPTargetEntity(ESPTargetEntity.INVISIBLE);
            boolean enabled6 = !ESPTargetEntity.checkESPTargetEntity(ESPTargetEntity.TEAMMATES);
            return !ChatUtil.checkEntity5(livingEntity, enabled, enabled2, enabled3, enabled4, false, enabled5, enabled6, false, true)
            ? false
            : (double)minecraft.thePlayer.getDistanceSqToEntity(livingEntity) < this.settingMaximumRenderDistance.getValue();
        }
    }

    private void applyRenderPass6(RenderPass renderPass) {
        int index = 0;
        Iterator iterator = this.itemList3.iterator();

        while (iterator.hasNext()) {
            EntityLivingBase livingEntity = (EntityLivingBase)iterator.next();
            double[] coordArray = (double[])this.entryMap.get(livingEntity);
            if (coordArray != null) {
                int indexOffset4 = livingEntity.hurtTime;

                outerLoop:
                while (true) {
                    int index2;
                    if (indexOffset4 != 0 && this.settingCornerHitColorBool.isEnabled()) {
                        index2 = this.settingCornerHitColor.getInt12(index);
                    } else if (this.settingCornerTeamColor.isEnabled() && livingEntity instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer)livingEntity;
                        index2 = RandomUtil.getIndex(player);
                    } else {
                        index2 = this.settingCornerColor.getInt12(index);
                    }

                    if (this.settingMarkMurderer.isEnabled()) {
                        MurdererFinder murdererFinder = ClientGlobals.moduleManager.murdererFinder;
                        String text = livingEntity.getText();
                        if (murdererFinder.checkString(text)) {
                            int index3 = RandomUtil.processAction3(index2);
                            index2 = RandomUtil.getInt12(255, 0, 0, index3);
                        }
                    }

                    double offset = coordArray[0];
                    double offset2 = coordArray[1];
                    double offset3 = coordArray[2];
                    double offset4 = coordArray[3];
                    float lineWidthCorner = (float)this.settingLineWidthCorner.getValue();
                    double double7 = MathHelper.getDouble7(this.settingHorizontalPercentage.getValue(), 0.0, 100.0);
                    double double72 = MathHelper.getDouble7(this.settingVerticalPercentage.getValue(), 0.0, 100.0);
                    RenderUtils.processAction(offset, offset2, offset3, offset4, lineWidthCorner, double7, double72, this.settingCorner2.getIndex(renderPass, index2, index));
                    index++;

                    while (iterator.hasNext()) {
                        livingEntity = (EntityLivingBase)iterator.next();
                        coordArray = (double[])this.entryMap.get(livingEntity);
                        if (coordArray != null) {
                            indexOffset4 = livingEntity.hurtTime;
                            continue outerLoop;
                        }
                    }

                    return;
                }
            }
        }
    }

    private void applyEntityLivingBase(EntityLivingBase livingEntity) {
        EntityEspRenderer entityEspRenderer2 = this.entityEspRenderer;
        double posX = minecraft.getRenderViewEntity().posX;
        double posY = minecraft.getRenderViewEntity().posY;
        double posZ = minecraft.getRenderViewEntity().posZ;
        entityEspRenderer2.processDouble(posX, posY, posZ);
        if (ClientGlobals.moduleManager.freeCam.isToggled()) {
            EntityEspRenderer entityEspRenderer3 = this.entityEspRenderer;
            double xCoord = ClientGlobals.moduleManager.freeCam.positionVec.xCoord;
            double yCoord = ClientGlobals.moduleManager.freeCam.positionVec.yCoord;
            double zCoord = ClientGlobals.moduleManager.freeCam.positionVec.zCoord;
            entityEspRenderer3.processDouble(xCoord, yCoord, zCoord);
        }

        AxisAlignedBB boundingBox2 = livingEntity.getBoundingBox2();
        if (this.entityEspRenderer.checkCondition(boundingBox2.minX - 0.5, boundingBox2.minY - 0.5, boundingBox2.minZ - 0.5, boundingBox2.maxX + 0.5, boundingBox2.maxY + 0.5, boundingBox2.maxZ + 0.5)) {
            if (!this.itemList3.contains(livingEntity)) {
                this.itemList3.add(livingEntity);
            }
        }
    }

    private void updateState6() {
        if (EspRenderMode.checkEspRenderMode(EspRenderMode.THREE_E_CORNER) && minecraft.theWorld != null) {
            boolean enabled = this.settingFakeCorner.canExecute();
            boolean enabled2 = this.settingFakeCorner.canExecute2();
            MovementHandler.processBoolean(enabled, enabled2);
            RunnableTask runnableTask = renderPass -> this.applyRenderPass(renderPass);
            MovementHandler.applyRunnableTask(true, runnableTask);
        }
    }

    private float getScaleFactor(double offset, EntityLivingBase livingEntity) {
        double offset2 = offset;
        if (minecraft.thePlayer.getDistanceSqToEntity(livingEntity) > 1.0F) {
            float value = minecraft.thePlayer.getDistanceSqToEntity(livingEntity) / 20.0F;
            double offset3 = (double)(1.0F - value);
            if (offset3 < 0.3) {
                offset3 = 0.3;
            }

            offset2 = offset * offset3;
        }

        return (float)offset2;
    }

    private boolean isVanillaColorVisible() {
        return !this.settingVanillaTeamColor.isEnabled();
    }

    private boolean isCollisionBoxColorVisible() {
        return !this.settingCollisionBoxTeamColor.isEnabled();
    }

    private static boolean canExecute10() {
        return EspRenderMode.checkEspRenderMode(EspRenderMode.VANILLA);
    }

    private boolean isCornerColorVisible() {
        return !this.settingCornerTeamColor.isEnabled();
    }

    private static boolean canExecute11() {
        return EspRenderMode.checkEspRenderMode(EspRenderMode.REAL_2D);
    }

    private static boolean canExecute5() {
        return EspRenderMode.checkEspRenderMode(EspRenderMode.HIT_BOX);
    }

    private boolean isReal2DColor2Visible() {
        return !this.settingReal2DTeamColor.isEnabled();
    }

    private static boolean canExecute6() {
        return EspRenderMode.checkEspRenderMode(EspRenderMode.BOX);
    }

    private boolean isFakeCornerColorVisible() {
        return !this.settingFakeCornerTeamColor.isEnabled();
    }

    private static boolean canExecute7() {
        return EspRenderMode.checkEspRenderMode(EspRenderMode.COLLISION_BOX);
    }

    private boolean isHitBoxColorVisible() {
        return !this.settingHitBoxTeamColor.isEnabled();
    }

    private boolean isMurdererAlphaVisible() {
        return !this.settingMarkMurderer.isEnabled() ? false : this.settingCustomMurdererAlpha.isEnabled();
    }
}
