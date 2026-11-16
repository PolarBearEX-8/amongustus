package augustus.modules.render;

import client.ClientGlobals;
import client.event.EventStrafe;
import client.event.RenderListener;
import client.event.StrafeListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.setting.TracersTargetEntity;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.MovementHandler;
import client.util.RandomUtil;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class Tracers extends Module implements StrafeListener, RenderListener, TickListener {
    public ModeSetting settingMode = new ModeSetting(this, "Mode", "Feet", new String[]{"Feet", "CrossHair"});
    public BooleanSetting settingStaticColor;
    public ColorSetting settingColor;
    public NumberSetting settingLineWidth;
    public ShaderOptions settingLineWidth2;
    public MultiSelectSetting settingEntities;
    public NumberSetting settingMaximumRenderDistance;
    public boolean expanded2;
    public ArrayList itemList3;
    public float motionMultiplier;

    public Tracers() {
        super("Tracers", "Isotopenindikator", new Color(12, 58, 133), Category.RENDER);
        BooleanSetting booleanSetting = new BooleanSetting(this, "StaticColor", false);
        this.settingStaticColor = (BooleanSetting)booleanSetting.getSetting(() -> canExecute5());
        ColorSetting colorSetting = new ColorSetting(this, "Color", new Color(21, 121, 230, 65));
        this.settingColor = (ColorSetting)colorSetting.getSetting(() -> canExecute6());
        this.settingLineWidth = new NumberSetting(this, "LineWidth", 6.0, 0.1, 15.0, 1);
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingLineWidth2 = shaderOptions.getShaderOptions(black).getShaderOptions2();
        this.settingEntities = new MultiSelectSetting(this, "Entities", TracersTargetEntity.values());
        this.settingMaximumRenderDistance = new NumberSetting(this, "MaximumRenderDistance", 500.0, 0.0, 500.0, 0);
        this.itemList3 = new ArrayList();
        this.motionMultiplier = 0.0F;
    }

    @Override
    public void onEnable() {
        this.registerEvent(24, 11);
        this.registerEvent(32);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        minecraft.gameSettings.state28 = this.expanded2;
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = minecraft.gameSettings.state28;
        minecraft.gameSettings.state28 = false;
    }

    @Override
    public void onStrafe(EventStrafe event) {
        this.motionMultiplier = event.getScaleFactor();
    }

    @Override
    public void onRender3D(float partialTicks) {
        RenderUtils.updateState();
        GL11.glLineWidth((float)(this.settingLineWidth.getValue() / 2.0));
        MovementHandler.processBoolean(this.settingLineWidth2.canExecute(), this.settingLineWidth2.canExecute2());
        MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(renderPass));
        RenderUtils.prepareScissor();
    }

    @Override
    public void onRunTick() {
        this.itemList3.clear();

        for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
            if (!(entity instanceof EntityPlayerSP)) {
                boolean selected = TracersTargetEntity.PLAYER.isSelected();
                boolean selected2 = TracersTargetEntity.MOB.isSelected();
                boolean selected3 = TracersTargetEntity.ANIMAL.isSelected();
                boolean selected4 = TracersTargetEntity.VILLAGER.isSelected();
                boolean selected5 = TracersTargetEntity.ARMOR_STAND.isSelected();
                boolean selected6 = TracersTargetEntity.INVISIBLE.isSelected();
                boolean enabled = !TracersTargetEntity.TEAMMATES.isSelected();
                boolean selected7 = TracersTargetEntity.ALERTED.isSelected();
                if (ChatUtil.checkEntity(entity, selected, selected2, selected3, selected4, selected5, selected6, enabled, true, true, selected7)
                && (double)minecraft.thePlayer.getDistanceSqToEntity(entity) < this.settingMaximumRenderDistance.getValue()) {
                    this.itemList3.add((EntityLivingBase)entity);
                }
            }
        }
    }

    private void applyEntityLivingBase(EntityLivingBase livingEntity, int index, RenderPass renderPass) {
        int int12 = this.settingColor.getInt12(index);
        if (!this.settingStaticColor.isEnabled()) {
            double offset = (double)(Math.min(Math.max(minecraft.thePlayer.getDistanceSqToEntity(livingEntity), 6.0F), 36.0F) - 6.0F);
            float[] floatArray = RandomUtil.getFloatArray(int12);
            int12 = Color.HSBtoRGB((float)(0.011415525F * offset), floatArray[1], floatArray[2]);
        }

        RenderUtils.applyEntityLivingBase2(
        livingEntity,
        this.settingLineWidth2.getIndex(renderPass, RandomUtil.getIndex(renderPass, int12)),
        this.settingMode.getSelected()
        );
    }

    private static boolean canExecute5() {
        return !ClientGlobals.moduleManager.tracers.settingColor.isToggled();
    }

    private void applyRenderPass(RenderPass renderPass) {
        for (int i = 0; i < this.itemList3.size(); i++) {
            EntityLivingBase livingEntity = (EntityLivingBase)this.itemList3.get(i);
            this.applyEntityLivingBase(livingEntity, i, renderPass);
        }
    }

    private static boolean canExecute6() {
        return ClientGlobals.moduleManager.tracers.settingStaticColor.isEnabled();
    }
}
