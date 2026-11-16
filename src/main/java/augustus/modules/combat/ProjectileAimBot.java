package augustus.modules.combat;

import augustus.custom.RuntimeBridge;
import augustus.modes.combat.AuraRenderMode;
import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.MotionListener;
import client.event.MoveListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationHelper;
import client.setting.BooleanSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.ProjectileAimBotTargetEntity;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.CombatTargetFilter;
import client.util.CurveManager;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import client.util.TrajectoryPredictor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class ProjectileAimBot extends Module implements TickListener, ClickListener, MotionListener, MoveListener {
    public SettingGroup settingRotations;
    public NumberSetting settingMaxTargetLookFovDiff;
    public BooleanSetting settingCombat;
    public BooleanSetting settingPredict;
    public BooleanSetting settingOnlyAtKillAura;
    public BooleanSetting settingOnlyKillAuraTarget;
    public NumberSetting settingAimRange;
    public BooleanSetting settingAssistAtRightClick;
    public MultiSelectSetting settingTargets;
    public EntityLivingBase targetEntity;
    public int tickDelay1;
    public int keyCode2;
    public CurveManager curveManager;
    public AxisAlignedBB boundingBox;
    public int tickDelay;

    public ProjectileAimBot() {
        super("ProjectileAimBot", "ProjektilZielhilfe", new Color(250, 233, 85), Category.COMBAT);
        this.settingRotations = new SettingGroup(this, "Rotations", true);
        this.settingMaxTargetLookFovDiff = new NumberSetting(this, "MaxTargetLookFovDiff", 180.0, 20.0, 180.0, 0);
        this.settingCombat = new BooleanSetting(this, "Combat", true);
        this.settingPredict = new BooleanSetting(this, "Predict", true);
        this.settingOnlyAtKillAura = (BooleanSetting)new BooleanSetting(this, "OnlyAtKillAura", true).getSetting(() -> this.settingCombat.isEnabled());
        this.settingOnlyKillAuraTarget = (BooleanSetting)new BooleanSetting(this, "OnlyKillAuraTarget", true).getSetting(() -> this.isMaxTargetLookFovDiffVisible());
        this.settingAimRange = new NumberSetting(this, "AimRange", 10.0, 3.0, 15.0, 1).getNumberSetting(() -> this.isAimRangeVisible());
        this.settingAssistAtRightClick = new BooleanSetting(this, "AssistAtRightClick", false);
        ProjectileAimBotTargetEntity[] projectileAimBotTargetEntityArray = ProjectileAimBotTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", projectileAimBotTargetEntityArray);
        this.targetEntity = (EntityLivingBase)null;
        this.tickDelay1 = 0;
        this.keyCode2 = 0;
        this.curveManager = new CurveManager(this, this.settingRotations);
        this.boundingBox = (AxisAlignedBB)null;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(7);
        this.registerEvent(34);
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
        this.targetEntity = null;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onClick(EventClick event) {
        if (minecraft.isIntegratedServerRunning()) {
            this.keyCode2++;
            if (this.canExecute5() && event.isState11()) {
                event.setState9(false);
            }

            if (this.targetEntity != null) {
                if (!ClientGlobals.rotationManager.checkModule(this)) {
                    return;
                }

                if (minecraft.thePlayer.ticksExisted - 1 == this.tickDelay || this.settingPredict.isEnabled()) {
                    EntityLivingBase targetEntity2 = this.targetEntity;
                    if (!this.checkEntityLivingBase(targetEntity2)) {
                        return;
                    }
                }

                if (this.keyCode2 <= 10) {
                    return;
                }

                this.keyCode2 = 0;
                event.updateInt2(1);
                event.setState9(true);
            }
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (ClientGlobals.rotationManager.checkModule(this)) {
            event.setAuraRenderMode(AuraRenderMode.DEFAULT);
        }
    }

    @Override
    public void onPostMotion() {
        if (minecraft.isIntegratedServerRunning()) {
            if (this.targetEntity == null) {
                return;
            }

            if (!this.settingCombat.isEnabled()) {
                return;
            }

            if (!ClientGlobals.rotationManager.checkModule(this)) {
                return;
            }

            int int2 = this.getInt2();
            if (int2 < 0) {
                return;
            }

            SlotManager.applyModule3(this, -1, int2);
        }
    }

    @Override
    public void onPreTick() {
        if (minecraft.isIntegratedServerRunning()) {
            this.targetEntity = null;
            if (this.getInt2() >= 0) {
                this.targetEntity = this.getEntityLivingBase();
                if (this.targetEntity != null) {
                    double offset = 180.0
                    - TrajectoryPredictor.getDouble4(
                    (double)MathHelper.sqrt_float(minecraft.thePlayer.rotationYaw), (double)MathHelper.sqrt_float(this.targetEntity.scaleFactor12)
                    );
                    if (offset > this.settingMaxTargetLookFovDiff.getValue()) {
                        this.targetEntity = null;
                    }
                }

                if (this.targetEntity != null) {
                    EntityLivingBase targetEntity2 = this.targetEntity;
                    if (!CombatTargetFilter.checkEntityLivingBase(1222936913, 29201, targetEntity2)) {
                        if (!minecraft.gameSettings.keyBindUseItem.isKeyDown() && !this.settingCombat.isEnabled()) {
                            this.targetEntity = null;
                        } else if (minecraft.gameSettings.keyBindUseItem.isKeyDown() && !this.canExecute5()) {
                            this.targetEntity = null;
                        } else {
                            EntityLivingBase targetEntity3 = this.targetEntity;
                            Rotation rotation = this.getRotation(targetEntity3, 3);
                            if (rotation.yaw == 0.0F && rotation.pitch == 0.0F) {
                                this.targetEntity = null;
                            } else if (this.settingPredict.isEnabled()
                            || this.canExecute5()
                            || !ClientGlobals.moduleManager.killAura.isToggled()
                            || ClientGlobals.moduleManager.killAura.target == null
                            || !this.settingCombat.isEnabled()) {
                                Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
                                Rotation rotation2 = this.curveManager.getRotation(rotation, rotation3);
                                ClientGlobals.rotationManager.applyRotation2(rotation2, 0, 1898960900, this);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean canExecute5() {
        if (this.settingAssistAtRightClick.isEnabled() && minecraft.thePlayer.getHeldItem() != null) {
            Item item = minecraft.thePlayer.getHeldItem().getItem();
            return item instanceof ItemSnowball || item instanceof ItemEgg;
        } else {
            return false;
        }
    }

    private Rotation getRotation(EntityLivingBase livingEntity, int index4) {
        ItemStack itemStack = this.canExecute5()
        ? minecraft.thePlayer.getHeldItem()
        : (this.getInt2() < 0 ? null : minecraft.thePlayer.container.getSlot(this.getInt2() + 36).getStack());
        if (itemStack == null) {
            return new Rotation(0.0F, 0.0F);
        } else {
            this.tickDelay1 = 1;
            double offset = (double)(livingEntity.width / 2.0F);
            float value = 0.0F;
            float value2 = 0.0F;
            ArrayList arrayList = new ArrayList();
            Vec3 trajectoryPoint = RuntimeBridge.getTrajectoryPoint(
            RuntimeBridge.findTargetTrajectories(
            minecraft.thePlayer, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + 1
            ),
            0
            );
            int index5 = 0;
            if (0 >= index4) {
                return new Rotation(value, value2);
            } else {
                double offset2;
                double offset3;
                double offset4;
                float value3;
                boolean enabled;
                float value4;
                if (this.tickDelay1 <= arrayList.size() && this.tickDelay1 > 0) {
                    Vec3 trajectoryPoint2 = RuntimeBridge.getTrajectoryPoint(arrayList, this.tickDelay1);
                    offset2 = trajectoryPoint2.xCoord;
                    offset3 = trajectoryPoint2.yCoord;
                    offset4 = trajectoryPoint2.zCoord;
                    double offset5 = offset2 - trajectoryPoint.xCoord;
                    double offset6 = offset3 + (double)this.targetEntity.getHeight2() - (trajectoryPoint.yCoord + (double)minecraft.thePlayer.getHeight2());
                    double offset7 = offset4 - trajectoryPoint.zCoord;
                    float value5 = (float)Math.toDegrees(Math.atan2(offset7, offset5)) - 90.0F;
                    value3 = (float)(-(MathHelper.getDouble6(offset6, (double)MathHelper.sqrt_double(offset5 * offset5 + offset7 * offset7)) * 180.0 / Math.PI));
                    value4 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, value5, 180.0F);
                    enabled = this.settingPredict.isEnabled();
                } else {
                    Vec3 trajectoryPoint3 = RuntimeBridge.getTrajectoryPoint(
                    arrayList = RuntimeBridge.calculateTrajectory(
                    minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + this.tickDelay1, 136
                    ),
                    this.tickDelay1
                    );
                    offset2 = trajectoryPoint3.xCoord;
                    offset3 = trajectoryPoint3.yCoord;
                    offset4 = trajectoryPoint3.zCoord;
                    double offset8 = offset2 - trajectoryPoint.xCoord;
                    double offset9 = offset3 + (double)this.targetEntity.getHeight2() - (trajectoryPoint.yCoord + (double)minecraft.thePlayer.getHeight2());
                    double offset10 = offset4 - trajectoryPoint.zCoord;
                    float value6 = (float)Math.toDegrees(Math.atan2(offset10, offset8)) - 90.0F;
                    value3 = (float)(-(MathHelper.getDouble6(offset9, (double)MathHelper.sqrt_double(offset8 * offset8 + offset10 * offset10)) * 180.0 / Math.PI));
                    value4 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, value6, 180.0F);
                    enabled = this.settingPredict.isEnabled();
                }

                while (enabled || this.canExecute5() || !this.settingCombat.isEnabled()) {
                    for (float i = 0.0F; i < 60.0F; i += 0.2F) {
                        boolean enabled2 = false;

                        for (int i2 = -1; i2 < 2; i2 = (byte)(i2 + 2)) {
                            float value7 = value3 + i * (float)i2;
                            RotationHelper rotationHelper = new RotationHelper();
                            rotationHelper.applyEntityLivingBase(minecraft.thePlayer, itemStack, trajectoryPoint.xCoord, trajectoryPoint.yCoord, trajectoryPoint.zCoord, value4, value7);
                            int index = rotationHelper.getIndex(
                            100,
                            livingEntity,
                            new AxisAlignedBB(offset2 - offset, offset3 + 0.2, offset4 - offset, offset2 + offset, offset3 + (double)livingEntity.height, offset4 + offset)
                            );
                            if (index != Integer.MAX_VALUE) {
                                this.tickDelay1 = index;
                                enabled2 = true;
                                value = value4;
                                value2 = value7;
                                this.boundingBox = new AxisAlignedBB(offset2 - offset, offset3, offset4 - offset, offset2 + offset, offset3 + (double)livingEntity.height, offset4 + offset);
                                this.tickDelay = minecraft.thePlayer.ticksExisted;
                                break;
                            }
                        }

                        if (enabled2) {
                            break;
                        }
                    }

                    if (++index5 >= index4) {
                        return new Rotation(value, value2);
                    }

                    ArrayList arrayList2;
                    if (this.tickDelay1 <= arrayList.size() && this.tickDelay1 > 0) {
                        arrayList2 = arrayList;
                    } else {
                        arrayList2 = arrayList = RuntimeBridge.calculateTrajectory(
                        minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + this.tickDelay1, 136
                        );
                    }

                    Vec3 trajectoryPoint4 = RuntimeBridge.getTrajectoryPoint(arrayList2, this.tickDelay1);
                    offset2 = trajectoryPoint4.xCoord;
                    offset3 = trajectoryPoint4.yCoord;
                    offset4 = trajectoryPoint4.zCoord;
                    double offset11 = offset2 - trajectoryPoint.xCoord;
                    double offset12 = offset3 + (double)this.targetEntity.getHeight2() - (trajectoryPoint.yCoord + (double)minecraft.thePlayer.getHeight2());
                    double offset13 = offset4 - trajectoryPoint.zCoord;
                    float value8 = (float)Math.toDegrees(Math.atan2(offset13, offset11)) - 90.0F;
                    value3 = (float)(-(MathHelper.getDouble6(offset12, (double)MathHelper.sqrt_double(offset11 * offset11 + offset13 * offset13)) * 180.0 / Math.PI));
                    value4 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, value8, 180.0F);
                    enabled = this.settingPredict.isEnabled();
                }

                return new Rotation(
                value4 + SettingContainer.settingProvider.getFloat2(-2.0F, 2.0F),
                SimulatedPlayer.getFloat9(
                ClientGlobals.rotationManager.getRotation3().pitch,
                value3 + SettingContainer.settingProvider.getFloat2(-2.0F, 2.0F),
                180.0F
                )
                );
            }
        }
    }

    private EntityLivingBase getEntityLivingBase() {
        if (minecraft.gameSettings.keyBindUseItem.isKeyDown()) {
            Object[] itemArray = minecraft.theWorld
            .loadedEntityList
            .stream()
            .filter(object -> this.checkEntity3((Entity)object))
            .sorted(Comparator.comparingDouble(object2 -> this.getDistanceToTarget((Entity)object2)))
            .toArray();
            if (itemArray.length != 0) {
                return (EntityLivingBase)itemArray[0];
            }
        }

        if (this.settingCombat.isEnabled()
        && ClientGlobals.moduleManager.killAura.isToggled()
        && ClientGlobals.moduleManager.killAura.target != null) {
            return (double)minecraft.thePlayer.getDistanceToEntity(ClientGlobals.moduleManager.killAura.target) < 3.3
            ? null
            : ClientGlobals.moduleManager.killAura.target;
        } else if (!this.settingCombat.isEnabled()) {
            return null;
        } else if ((!ClientGlobals.moduleManager.killAura.isToggled() || this.settingOnlyKillAuraTarget.isEnabled())
        && this.settingOnlyAtKillAura.isEnabled()) {
            return null;
        } else {
            Object[] itemArray2 = minecraft.theWorld
            .loadedEntityList
            .stream()
            .filter(object3 -> this.checkEntity((Entity)object3))
            .sorted(Comparator.comparingDouble(object4 -> this.getDistanceToTarget((Entity)object4)))
            .toArray();
            if (itemArray2.length == 0) {
                return null;
            } else {
                Object object5 = itemArray2[0];
                return (double)minecraft.thePlayer.getDistanceToEntity((Entity)object5) < 3.3
                ? null
                : (EntityLivingBase)itemArray2[0];
            }
        }
    }

    private boolean isAimRangeVisible() {
        return !this.settingCombat.isEnabled() ? false : !this.settingOnlyKillAuraTarget.isEnabled();
    }

    private boolean checkEntity(Entity entity) {
        return this.checkEntity2(entity, true);
    }

    private boolean checkEntity2(Entity entity, boolean enabled) {
        if (entity == minecraft.thePlayer) {
            return false;
        } else if (!ChatUtil.checkEntity3(
        entity,
        ProjectileAimBotTargetEntity.checkProjectileAimBotTargetEntity(ProjectileAimBotTargetEntity.PLAYER),
        ProjectileAimBotTargetEntity.checkProjectileAimBotTargetEntity(ProjectileAimBotTargetEntity.MOB),
        ProjectileAimBotTargetEntity.checkProjectileAimBotTargetEntity(ProjectileAimBotTargetEntity.ANIMAL),
        ProjectileAimBotTargetEntity.checkProjectileAimBotTargetEntity(ProjectileAimBotTargetEntity.VILLAGER),
        ProjectileAimBotTargetEntity.checkProjectileAimBotTargetEntity(ProjectileAimBotTargetEntity.ARMOR_STAND),
        ProjectileAimBotTargetEntity.checkProjectileAimBotTargetEntity(ProjectileAimBotTargetEntity.INVISIBLE),
        true,
        true,
        
        true,
        ProjectileAimBotTargetEntity.checkProjectileAimBotTargetEntity(ProjectileAimBotTargetEntity.DEAD),
        true
        )) {
            return false;
        } else {
            return (double)entity.getDistanceToEntity(minecraft.thePlayer) < this.settingAimRange.getValue()
            ? true
            : !enabled;
        }
    }

    private boolean checkEntity3(Entity entity) {
        return this.checkEntity2(entity, false);
    }

    private int getInt2() {
        for (int i = 36; i < minecraft.thePlayer.container.itemList2.size(); i++) {
            ItemStack itemStack = minecraft.thePlayer.container.getSlot(i).getStack();
            if (itemStack != null) {
                if (itemStack.getItem() instanceof ItemSnowball) {
                    return i - 36;
                }

                if (itemStack.getItem() instanceof ItemEgg) {
                    return i - 36;
                }
            }
        }

        return -1;
    }

    private boolean checkEntityLivingBase(EntityLivingBase livingEntity) {
        RotationHelper rotationHelper = new RotationHelper();
        ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
        float scaleFactor11 = minecraft.thePlayer.scaleFactor11;
        float scaleFactor27 = minecraft.thePlayer.scaleFactor27;
        rotationHelper.applyEntityLivingBase4(minecraft.thePlayer, itemStack5, scaleFactor11, scaleFactor27);
        AxisAlignedBB boundingBox2 = this.boundingBox;
        return rotationHelper.getIndex(200, livingEntity, boundingBox2) != Integer.MAX_VALUE;
    }

    private double getDistanceToTarget(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return 1000.0;
        } else {
            double posX = entity.posX;
            double posY = entity.posY;
            double posZ = entity.posZ;
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            Client client = Client.getClient();
            return (double)Math.abs(SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2)[0]);
        }
    }

    private boolean isMaxTargetLookFovDiffVisible() {
        return !this.settingCombat.isEnabled() ? false : this.settingOnlyAtKillAura.isEnabled();
    }
}
