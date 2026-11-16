package augustus.modules.legit;

import augustus.modes.combat.AuraRenderMode;
import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventRotation;
import client.event.EventWorldLoad;
import client.event.MotionListener;
import client.event.MoveListener;
import client.event.RenderListener;
import client.event.RotationListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.rotation.Rotation;
import client.rotation.RotationHelper;
import client.setting.AutoRodTargetEntity;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.CombatTargetFilter;
import client.util.CurveManager;
import client.util.InventoryUtil;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import client.util.TimeHelper;
import client.util.TrajectoryPredictor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemMilkBucket;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class AutoRod extends Module implements WorldLoadListener, RotationListener, ClickListener, TickListener, MoveListener, RenderListener, MotionListener {
    public SettingGroup settingRotations;
    public NumberSetting settingMaxTargetLookFovDiff;
    public NumberSetting settingFov;
    public NumberSetting settingMaxRodRange;
    public BooleanSetting settingMoveFix;
    public MultiSelectSetting settingTargets;
    public BooleanSetting settingIgnoreEatingTargets;
    public BooleanSetting settingCombat;
    public BooleanSetting settingClick;
    public BooleanSetting settingDisableWhileScaffold;
    public BooleanSetting settingOnlyWhileKillAuraHasTarget;
    public BooleanSetting settingESP;
    public ColorSetting settingESPColor;
    public ShaderOptions settingESPColor2;
    public TimeHelper timer;
    public int tickDelay;
    public int tickDelay1;
    public EntityLivingBase targetEntity;
    public Vec3 positionVec;
    public int tickDelay2;
    public boolean expanded2;
    public boolean enabled2;
    public int tickDelay3;
    public CurveManager curveManager;

    public AutoRod() {
        super("AutoRod", "AutomatischeAngel", new Color(203, 232, 162), Category.LEGIT);
        this.settingRotations = new SettingGroup(this, "Rotations", true);
        this.settingMaxTargetLookFovDiff = new NumberSetting(this, "MaxTargetLookFovDiff", 180.0, 20.0, 180.0, 0);
        this.settingFov = new NumberSetting(this, "Fov", 90.0, 0.0, 180.0, 0);
        this.settingMaxRodRange = new NumberSetting(this, "MaxRodRange", 10.0, 0.0, 20.0, 2);
        this.settingMoveFix = new BooleanSetting(this, "MoveFix", true);
        AutoRodTargetEntity[] autoRodTargetEntityArray = AutoRodTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", autoRodTargetEntityArray);
        this.settingIgnoreEatingTargets = new BooleanSetting(this, "IgnoreEatingTargets", true);
        this.settingCombat = new BooleanSetting(this, "Combat", true);
        this.settingClick = new BooleanSetting(this, "Click", true);
        this.settingDisableWhileScaffold = new BooleanSetting(this, "DisableWhileScaffold", true);
        this.settingOnlyWhileKillAuraHasTarget = new BooleanSetting(this, "OnlyWhileKillAuraHasTarget", false);
        this.settingESP = new BooleanSetting(this, "ESP", true);
        this.settingESPColor = (ColorSetting)new ColorSetting(this, "ESPColor", Color.GREEN).getSetting(() -> this.settingESP.isEnabled());
        ShaderOptions shaderOptions5 = new ShaderOptions(this).getShaderOptions5();
        Color black = Color.black;
        this.settingESPColor2 = shaderOptions5.getShaderOptions(black);
        this.timer = new TimeHelper();
        this.tickDelay = 0;
        this.tickDelay1 = -1;
        this.targetEntity = (EntityLivingBase)null;
        this.positionVec = (Vec3)null;
        this.tickDelay2 = 0;
        this.expanded2 = false;
        this.enabled2 = false;
        this.tickDelay3 = -1;
        CurveManager curveManager2 = new CurveManager(this, this.settingRotations);
        this.curveManager = curveManager2;
    }

    @Override
    public void onEnable() {
        this.registerEvent(28);
        this.registerEvent(7);
        this.registerEvent(40);
        this.registerEvent(21);
        this.registerEvent(32);
        this.registerEvent(34);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.targetEntity = null;
    }

    @Override
    public void onPreDisable() {
        this.tickDelay2 = 0;
        this.expanded2 = false;
        this.enabled2 = true;
    }

    @Override
    public void onRotation(EventRotation event) {
        if (!this.settingMoveFix.isEnabled()) {
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            event.setScaleFactor(scaleFactor1);
        }
    }

    @Override
    public void onClick(EventClick event) {
        if (this.getInternalEntityAdapter() == null) {
            if ((this.expanded2 ? 1 : 0) >= this.tickDelay2) {
                return;
            }

            event.updateInt2(1);
            this.tickDelay = 0;
            this.expanded2 = true;
            this.enabled2 = true;
        }
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (!this.settingMoveFix.isEnabled()) {
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            event.setScaleFactor(scaleFactor1);
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.targetEntity = null;
    }

    @Override
    public void onMove(EventMove event) {
        if (this.settingMoveFix.isEnabled()) {
            event.setAuraRenderMode(AuraRenderMode.DEFAULT);
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        RenderUtils.updateState();
    }

    @Override
    public void onPostMotion() {
        AutoRod autoRod = null;
        boolean completed = false;
        this.tickDelay1 = this.getInt3();
        if ((this.canExecute6() ? 1 : 0) == this.getInt3()) {
            if (minecraft.playerController.indexOffset == this.getInt3()) {
                autoRod = this;
                completed = true;
            }
            if (!completed) {
                if (this.settingCombat.isEnabled()) {
                    this.tickDelay1 = this.getInt2();
                    this.expanded2 = false;
                }
            }
        } else {
            this.expanded2 = false;
            if (minecraft.thePlayer.inventoryPlayer.currentItem != 0) {
                autoRod = this;
                completed = true;
            }
        }
        if (!completed) {
            this.tickDelay++;
            autoRod = this;
        }


        if (autoRod.targetEntity != null && !minecraft.thePlayer.isUsingItem()) {
            int tickDelay12 = this.tickDelay1;
            SlotManager.applyModule3(this, 2, tickDelay12);
        }
    }

    @Override
    public void onPreTick() {
        if (!minecraft.gameSettings.keyBindAttack.isKeyDown()) {
            Object object;
            if (minecraft.theWorld
            .loadedEntityList
            .stream()
            .filter(object2 -> this.checkEntity((Entity)object2))
            .sorted(Comparator.comparingDouble(object3 -> this.getCoordinate((Entity)object3)))
            .toArray().length
            != 0) {
                object = minecraft.thePlayer;
            } else {
                object = this.targetEntity;
            }

            if (object != null) {
                double offset = 180.0
                - TrajectoryPredictor.getDouble4(
                (double)MathHelper.sqrt_float(minecraft.thePlayer.rotationYaw), (double)MathHelper.sqrt_float(this.targetEntity.scaleFactor12)
                );
                double[] coordArray = new double[Double.compare(offset, this.settingMaxTargetLookFovDiff.getValue())];
                coordArray[0] = this.targetEntity.posX;
                coordArray[1] = this.targetEntity.posY;
                coordArray[2] = this.targetEntity.posZ;
                EntityLivingBase targetEntity2 = this.targetEntity;
                this.tickDelay2 = this.getIndex(targetEntity2, 4) + 2;
                this.positionVec = new Vec3(coordArray[0], coordArray[1], coordArray[2]);
                double offset2 = coordArray[0];
                double offset3 = offset2 - minecraft.thePlayer.posX;
                double offset4 = coordArray[1] + (double)this.targetEntity.height / 1.7;
                double offset5 = offset4 - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                double offset6 = coordArray[2];
                double offset7 = offset6 - minecraft.thePlayer.posZ;
                float value = (float)Math.toDegrees(Math.atan2(offset7, offset3)) - 90.0F;
                double offset8 = (double)MathHelper.sqrt_double(offset3 * offset3 + offset7 * offset7);
                double distance = (double)minecraft.thePlayer.getDistanceSqToEntity(this.targetEntity);
                float value2 = (float)((double)((float)(-(Math.atan2(offset5, offset8) * 180.0 / Math.PI))) - 0.55F * Math.max(0.0, distance - 3.0));
                CurveManager curveManager2 = this.curveManager;
                Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
                Rotation rotation6 = curveManager2.getRotation6(value, value2, rotation3);
                int index = Double.compare(distance, 4.0);
                if (index < 20) {
                    RotationHelper rotationHelper = new RotationHelper();
                    rotationHelper.applyEntityLivingBase4(
                    
                    minecraft.thePlayer,
                    minecraft.thePlayer.container.getSlot(this.getInt3() + 36).getStack(),
                    rotation6.yaw,
                    rotation6.pitch + (float)index
                    );
                } else {
                    ClientGlobals.rotationManager.applyRotation2(rotation6, 0, 1898960900, this);
                }
            }
        }
    }

    private double getCoordinate(Entity entity) {
        double posX = entity.posX;
        double posY = entity.posY;
        double posZ = entity.posZ;
        float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
        Client client = Client.getClient();
        return (double)Math.abs(SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2)[0]);
    }

    private int getIndex(EntityLivingBase livingEntity, int index2) {
        int index3 = -1;

        for (int i = 0; i < index2; i++) {
            double offset = livingEntity.posX - minecraft.thePlayer.posX;
            double offset2 = livingEntity.posY + (double)this.targetEntity.height / 1.7 - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
            double offset3 = livingEntity.posZ - minecraft.thePlayer.posZ;
            double[] coordOffsetArray = CombatTargetFilter.getCoordOffsetArray(this.targetEntity, index3);
            if (index3 != -1) {
                offset = coordOffsetArray[0] - minecraft.thePlayer.posX;
                offset2 = coordOffsetArray[1] + (double)this.targetEntity.height / 1.7 - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                offset3 = coordOffsetArray[2] - minecraft.thePlayer.posZ;
            }

            float value = (float)Math.toDegrees(Math.atan2(offset3, offset)) - 90.0F;
            double offset4 = (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3);
            double distance = (double)minecraft.thePlayer.getDistanceSqToEntity(this.targetEntity);
            float value2 = (float)((double)((float)(-(Math.atan2(offset2, offset4) * 180.0 / Math.PI))) - 0.55F * Math.max(0.0, distance - 3.0));
            float float9 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, value, 180.0F);
            float float92 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().pitch, value2, 180.0F);
            RotationHelper rotationHelper = new RotationHelper();
            rotationHelper.applyEntityLivingBase4(
            
            minecraft.thePlayer,
            minecraft.thePlayer.container.getSlot(this.getInt3() + 36).getStack(),
            float9,
            float92
            );
            double offset5 = (double)(livingEntity.width / 2.0F + 0.1F);
            index3 = rotationHelper.predictInterceptTick(
            100,
            livingEntity,
            new AxisAlignedBB(
            coordOffsetArray[0] - offset5, coordOffsetArray[1] - 0.1F, coordOffsetArray[2] - offset5, coordOffsetArray[0] + offset5, coordOffsetArray[1] + (double)livingEntity.height + 0.1F, coordOffsetArray[2] + offset5
            )
            );
            if (index3 == Integer.MAX_VALUE) {
                return 0;
            }
        }

        return index3;
    }

    private EntityFishHook getInternalEntityAdapter() {
        if (minecraft.thePlayer != null && minecraft.thePlayer.internalEntityAdapter != null) {
            return minecraft.thePlayer.internalEntityAdapter;
        } else if (minecraft.theWorld == null) {
            return null;
        } else {
            Iterator iterator = minecraft.theWorld.loadedEntityList.iterator();

            while (iterator.hasNext()) {
                Entity entity;
                EntityFishHook internalEntityAdapter;
                if ((entity = (Entity)iterator.next()) instanceof EntityFishHook
                && (internalEntityAdapter = (EntityFishHook)entity).targetPlayer.getText().equals(minecraft.session.getText1())) {
                    return internalEntityAdapter;
                }
            }

            return null;
        }
    }

    private boolean checkEntity(Entity entity) {
        double offset = this.getCoordinate(entity) - this.settingFov.getValue();
        return ((int)(offset == 0.0 ? 0.0 : (double)(offset > 0.0 ? 1 : -1)) & 1) != 0;
    }

    private void applyRenderPass(float value, float value2, float value3, float value4, RenderPass renderPass) {
        int int12 = this.settingESPColor.getInt12(0);
        RenderUtils.setColor(this.settingESPColor2.getIndex(renderPass, int12));
        RenderUtils.processAction2(value - value2, value3, value4 - value2, value + value2, value3 + this.targetEntity.height, value4 + value2);
    }

    public boolean canExecute5() {
        if (minecraft.thePlayer.getHeldItem() != null
        && (
        minecraft.thePlayer.getHeldItem().getItem() instanceof ItemFood
        || minecraft.thePlayer.getHeldItem().getItem() instanceof ItemSword
        || minecraft.thePlayer.getHeldItem().getItem() instanceof ItemPotion
        || minecraft.thePlayer.getHeldItem().getItem() instanceof ItemMilkBucket
        )
        && minecraft.gameSettings.keyBindUseItem.isKeyDown()) {
            return false;
        } else {
            if (minecraft.isIntegratedServerRunning()) {
                if ((minecraft.thePlayer.getHeldItem() == null || !(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod))
                && !this.settingCombat.isEnabled()) {
                    return false;
                }

                if (this.tickDelay1 != -1) {
                    if (this.getInt3() == -1) {
                        return false;
                    }

                    if (minecraft.gameSettings.keyBindUseItem.pressed) {
                        return false;
                    }

                    if (!this.timer.hasReached(100L) && this.settingClick.isEnabled()) {
                        return false;
                    }

                    if (!this.settingOnlyWhileKillAuraHasTarget.isEnabled()
                    || ClientGlobals.moduleManager.killAura.isToggled() && ClientGlobals.moduleManager.killAura.target != null) {
                        return true;
                    }

                    return false;
                }
            }

            return false;
        }
    }

    private int getInt2() {
        if (!this.enabled2) {
            return this.tickDelay3;
        } else {
            this.enabled2 = false;
            this.tickDelay3 = -1;
            int int3 = this.getInt3();
            if (SlotManager.indexOffset != int3) {
                this.tickDelay3 = SlotManager.indexOffset;
                return this.tickDelay3;
            } else {
                ArrayList arrayList = InventoryUtil.getArrayList(minecraft.thePlayer.container, 22826, 8654579, (byte)0, 36);
                if (!arrayList.isEmpty()) {
                    this.tickDelay3 = (Integer)arrayList.get(0);
                    return this.tickDelay3;
                } else {
                    do {
                        this.tickDelay3 = SettingContainer.settingProvider.getInt2(0, 8);
                    } while (this.tickDelay3 == int3);

                    return this.tickDelay3;
                }
            }
        }
    }

    public EntityLivingBase getTargetEntity() {
        return this.targetEntity;
    }

    private boolean canExecute6() {
        return this.getInternalEntityAdapter() != null;
    }

    public int getInt3() {
        if (36 < minecraft.thePlayer.container.itemList2.size()) {
            minecraft.thePlayer.container.getSlot(36).getStack();
        }

        return -1;
    }
}
