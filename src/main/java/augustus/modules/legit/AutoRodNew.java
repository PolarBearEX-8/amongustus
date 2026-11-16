package augustus.modules.legit;

import augustus.custom.RuntimeBridge;
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
import client.setting.AutoRodNewTargetEntity;
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
import client.util.CurveManager;
import client.util.HitBoxUtils;
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
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemMilkBucket;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class AutoRodNew extends Module implements WorldLoadListener, RotationListener, ClickListener, TickListener, MoveListener, RenderListener, MotionListener {
    public SettingGroup settingRotations;
    public NumberSetting settingMaxTargetLookFovDiff;
    public NumberSetting settingFov;
    public NumberSetting settingMaxRodRange;
    public BooleanSetting settingMoveFix;
    public MultiSelectSetting settingTargets;
    public BooleanSetting settingIgnoreEatingTargets;
    public BooleanSetting settingCombat;
    public BooleanSetting settingOnlyRodIfNotInReach;
    public BooleanSetting settingLuckyThrow;
    public BooleanSetting settingClick;
    public BooleanSetting settingDisableWhileScaffold;
    public BooleanSetting settingOnlyWhileKillAuraHasTarget;
    public BooleanSetting settingESP;
    public ColorSetting settingESPColor;
    public ShaderOptions settingESPColor2;
    public TimeHelper timer;
    public int tickDelay;
    public int keyCode2;
    public EntityLivingBase targetEntity;
    public Vec3 positionVec;
    public int tickDelay1;
    public boolean expanded2;
    public boolean enabled2;
    public int tickDelay2;
    public CurveManager curveManager;
    public AxisAlignedBB boundingBox;

    public AutoRodNew() {
        super("AutoRodNew", "AutomatischeAngel", new Color(203, 232, 162), Category.LEGIT);
        this.settingRotations = new SettingGroup(this, "Rotations", true);
        this.settingMaxTargetLookFovDiff = new NumberSetting(this, "MaxTargetLookFovDiff", 180.0, 20.0, 180.0, 0);
        this.settingFov = new NumberSetting(this, "Fov", 90.0, 0.0, 180.0, 0);
        this.settingMaxRodRange = new NumberSetting(this, "MaxRodRange", 10.0, 0.0, 20.0, 2);
        this.settingMoveFix = new BooleanSetting(this, "MoveFix", true);
        AutoRodNewTargetEntity[] autoRodNewTargetEntityArray = AutoRodNewTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", autoRodNewTargetEntityArray);
        this.settingIgnoreEatingTargets = new BooleanSetting(this, "IgnoreEatingTargets", true);
        this.settingCombat = new BooleanSetting(this, "Combat", true);
        this.settingOnlyRodIfNotInReach = new BooleanSetting(this, "OnlyRodIfNotInReach", false);
        this.settingLuckyThrow = new BooleanSetting(this, "LuckyThrow", false);
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
        this.keyCode2 = -1;
        this.targetEntity = (EntityLivingBase)null;
        this.positionVec = (Vec3)null;
        this.tickDelay1 = 0;
        this.expanded2 = false;
        this.enabled2 = false;
        this.tickDelay2 = -1;
        this.curveManager = new CurveManager(this, this.settingRotations);
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
        this.tickDelay1 = 0;
        this.expanded2 = false;
        this.enabled2 = true;
    }

    @Override
    public void onRotation(EventRotation event) {
        if (this.settingMoveFix.isEnabled()) {
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            event.setScaleFactor(scaleFactor1);
        }
    }

    @Override
    public void onClick(EventClick event) {
        this.keyCode2 = this.getInt3();
        if (this.getInternalEntityAdapter() == null) {
            if ((this.expanded2 ? 1 : 0) >= this.tickDelay1) {
                return;
            }

            AutoRodNew autoRodNew;
            if (!this.settingCombat.isEnabled()) {
                autoRodNew = this;
            } else {
                event.updateInt2(1);
                this.tickDelay = 0;
                this.expanded2 = true;
                autoRodNew = this;
            }

            autoRodNew.enabled2 = true;
        }
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (this.settingMoveFix.isEnabled()) {
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
        AutoRodNew autoRodNew = null;
        boolean completed = false;
        this.keyCode2 = this.getInt3();
        if ((this.canExecute8() ? 1 : 0) == this.getInt3()) {
            if (minecraft.playerController.indexOffset == this.getInt3()) {
                autoRodNew = this;
                completed = true;
            }
            if (!completed) {
                if (this.settingCombat.isEnabled()) {
                    this.keyCode2 = this.getInt2();
                    this.expanded2 = false;
                }
            }
        } else {
            this.expanded2 = false;
            if (minecraft.thePlayer.inventoryPlayer.currentItem != 0) {
                autoRodNew = this;
                completed = true;
            }
        }
        if (!completed) {
            this.tickDelay++;
            autoRodNew = this;
        }


        if (autoRodNew.targetEntity != null && !minecraft.thePlayer.isUsingItem()) {
            int keyCode22 = this.keyCode2;
            SlotManager.applyModule3(this, 2, keyCode22);
        }
    }

    @Override
    public void onPreTick() {
        if (!minecraft.gameSettings.keyBindAttack.isKeyDown()) {
            this.timer.reset();
        }

        this.targetEntity = null;
        this.boundingBox = null;
        if (this.canExecute6()) {
            if (!ClientGlobals.moduleManager.scaffold.isToggled() || !this.settingDisableWhileScaffold.isEnabled()) {
                Object[] itemArray = minecraft.theWorld
                .loadedEntityList
                .stream()
                .filter(object -> this.checkEntity((Entity)object))
                .sorted(Comparator.comparingDouble(object2 -> this.getCoordinate((Entity)object2)))
                .toArray();
                if (itemArray.length > 0) {
                    this.targetEntity = (EntityLivingBase)itemArray[0];
                    if (this.settingCombat.isEnabled()) {
                        double offset = (double)minecraft.thePlayer.getDistanceToEntity(this.targetEntity) - 3.2;
                        if (offset < 0.0) {
                            this.targetEntity = null;
                        }
                    }
                }

                if (this.targetEntity != null) {
                    double offset2 = 180.0
                    - TrajectoryPredictor.getDouble4(
                    (double)MathHelper.sqrt_float(minecraft.thePlayer.rotationYaw), (double)MathHelper.sqrt_float(this.targetEntity.scaleFactor12)
                    );
                    double offset3 = offset2 - this.settingMaxTargetLookFovDiff.getValue();
                    if (offset3 > 0.0) {
                        this.targetEntity = null;
                    } else {
                        EntityLivingBase targetEntity2 = this.targetEntity;
                        if ((this.tickDelay1 = this.getIndex(targetEntity2, 8)) >= 0) {
                            if (this.tickDelay1 < 10) {
                                Vec3 trajectoryPoint = RuntimeBridge.getTrajectoryPoint(
                                RuntimeBridge.calculateTrajectory(
                                minecraft.thePlayer, this.targetEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + this.tickDelay1, 136
                                ),
                                99999
                                );
                                if (!this.settingCombat.isEnabled() || !this.settingOnlyRodIfNotInReach.isEnabled() || this.canExecute5()) {
                                    double offset4 = (double)(this.targetEntity.width / 2.0F + 0.1F);
                                    AxisAlignedBB axisAlignedBB = new AxisAlignedBB(
                                    trajectoryPoint.xCoord - offset4,
                                    trajectoryPoint.yCoord,
                                    trajectoryPoint.zCoord - offset4,
                                    trajectoryPoint.xCoord + offset4,
                                    trajectoryPoint.yCoord + (double)(this.targetEntity.height / 2.0F),
                                    trajectoryPoint.zCoord + offset4
                                    );
                                    this.positionVec = trajectoryPoint;
                                    Vec3 vec = (Vec3)RuntimeBridge.findTargetTrajectories(
                                    minecraft.thePlayer, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + 1
                                    )
                                    .get(0);
                                    double offset5 = trajectoryPoint.xCoord - vec.xCoord;
                                    double offset6 = trajectoryPoint.yCoord
                                    + (double)this.targetEntity.height / 1.7
                                    - (vec.yCoord + (double)minecraft.thePlayer.getHeight2());
                                    double offset7 = trajectoryPoint.zCoord - vec.zCoord;
                                    float value = (float)Math.toDegrees(Math.atan2(offset7, offset5)) - 90.0F;
                                    double offset8 = (double)MathHelper.sqrt_double(offset5 * offset5 + offset7 * offset7);
                                    double distance = (double)minecraft.thePlayer.getDistanceSqToEntity(this.targetEntity);
                                    float value2 = (float)((double)((float)(-(Math.atan2(offset6, offset8) * 180.0 / Math.PI))) - 0.55F * Math.max(0.0, distance - 3.0));
                                    Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
                                    Rotation rotation6 = this.curveManager.getRotation6(value, value2, rotation3);
                                    ClientGlobals.rotationManager.applyRotation2(rotation6, 0, 1898960900, this);
                                    this.boundingBox = axisAlignedBB;
                                }
                            }
                        }
                    }
                }
            }
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

    public EntityLivingBase getTargetEntity() {
        return this.targetEntity;
    }

    private boolean canExecute5() {
        ArrayList arrayList = RuntimeBridge.findTargetTrajectories(
        minecraft.thePlayer, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + this.tickDelay1
        );
        ArrayList arrayList2 = RuntimeBridge.predictTargetPoints(
        minecraft.thePlayer, this.targetEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + this.tickDelay1
        );
        int index = Math.max(arrayList.size(), arrayList2.size());
        double height2 = (double)minecraft.thePlayer.getHeight2();

        while (0 < index) {
            RuntimeBridge.getTrajectoryPoint(arrayList, 0).getVec312(0.0, height2, 0.0);
            HitBoxUtils.getAxisAlignedBB(RuntimeBridge.getTrajectoryPoint(arrayList2, 0), true);
        }

        return true;
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

    private double getCoordinate(Entity entity) {
        double posX = entity.posX;
        double posY = entity.posY;
        double posZ = entity.posZ;
        float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
        Client client = Client.getClient();
        return (double)Math.abs(SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2)[0]);
    }

    private int getInt2() {
        if (!this.enabled2) {
            return this.tickDelay2;
        } else {
            this.enabled2 = false;
            this.tickDelay2 = -1;
            int int3 = this.getInt3();
            if (SlotManager.indexOffset != int3) {
                this.tickDelay2 = SlotManager.indexOffset;
                return this.tickDelay2;
            } else {
                ArrayList arrayList = InventoryUtil.getArrayList(minecraft.thePlayer.container, 22826, 8654579, (byte)0, 36);
                if (!arrayList.isEmpty()) {
                    this.tickDelay2 = (Integer)arrayList.get(0);
                    return this.tickDelay2;
                } else {
                    do {
                        this.tickDelay2 = SettingContainer.settingProvider.getInt2(0, 8);
                    } while (this.tickDelay2 == int3);

                    return this.tickDelay2;
                }
            }
        }
    }

    public boolean canExecute6() {
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

                if (this.keyCode2 != -1) {
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

    public int getInt3() {
        if (36 < minecraft.thePlayer.container.itemList2.size()) {
            minecraft.thePlayer.container.getSlot(36).getStack();
        }

        return -1;
    }

    private int getIndex(EntityLivingBase livingEntity, int index) {
        new ArrayList();
        Container container = minecraft.thePlayer.container;
        int index2 = this.getInt3() + 36;
        container.getSlot(index2).getStack();
        int ticksExisted = minecraft.thePlayer.ticksExisted;
        int ticksExisted2 = minecraft.thePlayer.ticksExisted;
        int index3 = minecraft.thePlayer.ticksExisted + 1;
        RuntimeBridge.getTrajectoryPoint(RuntimeBridge.findTargetTrajectories(minecraft.thePlayer, ticksExisted, ticksExisted2, index3), 999);
        return 0 < index ? 1 : 0;
    }

    private boolean canExecute7() {
        RotationHelper rotationHelper = new RotationHelper();
        rotationHelper.applyEntityLivingBase4(
        
        minecraft.thePlayer,
        minecraft.thePlayer.container.getSlot(this.getInt3() + 36).getStack(),
        minecraft.thePlayer.scaleFactor11,
        minecraft.thePlayer.scaleFactor27
        );
        return rotationHelper.checkEntityLivingBase(100, this.targetEntity, this.boundingBox);
    }

    private boolean canExecute8() {
        return this.getInternalEntityAdapter() != null;
    }
}
