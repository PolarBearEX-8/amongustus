package augustus.modules.legit;

import client.Client;
import client.ClientGlobals;
import client.event.EventSilentMove;
import client.event.EventWorldLoad;
import client.event.SilentMoveListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.SubModule;
import client.rotation.Rotation;
import client.setting.AimBotTargetEntity;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.setting.TargetFilterSetting;
import client.util.MovingObjectType;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class AimBot extends SubModule implements WorldLoadListener, TickListener, SilentMoveListener {
    public NumberSetting settingPreAimRange;
    public NumberSetting settingOwnYawMul;
    public NumberSetting settingOwnPitchMul;
    public NumberSetting settingAssistYawMul;
    public NumberSetting settingAssistPitchMul;
    public NumberSetting settingYawFov;
    public NumberSetting settingPitchFov;
    public BooleanSetting settingClick;
    public BooleanSetting settingDiggingCheck;
    public MultiSelectSetting settingTargets;
    public ModeSetting settingTargetSort;
    public TargetFilterSetting auxiliarySettingAdapter;
    public EntityLivingBase target;
    public EntityLivingBase targetEntity2;
    public long timestampMillis6;
    public long timestampMillis;
    public int tickDelay;
    public double[] coordOffsetArray2;
    public double[] coordOffsetArray;
    public double[] coordOffsetArray5;
    public int[] intValueArray2;
    public int[] intValueArray3;
    public Rotation rotation;
    public static boolean activeState4;

    public AimBot() {
        super("AimBot", "Ziehlhilfe", new Color(66, 135, 245), Category.LEGIT);
        this.settingPreAimRange = new NumberSetting(this, "PreAimRange", 5.0, 0.0, 10.0, 1);
        this.settingOwnYawMul = new NumberSetting(this, "OwnYawMul", 1.0, 0.0, 1.0, 2);
        this.settingOwnPitchMul = new NumberSetting(this, "OwnPitchMul", 1.0, 0.0, 1.0, 2);
        this.settingAssistYawMul = new NumberSetting(this, "AssistYawMul", 0.5, 0.0, 1.0, 2);
        this.settingAssistPitchMul = new NumberSetting(this, "AssistPitchMul", 0.5, 0.0, 1.0, 2);
        this.settingYawFov = new NumberSetting(this, "YawFov", 45.0, 0.0, 180.0, 0);
        this.settingPitchFov = new NumberSetting(this, "PitchFov", 26.0, 0.0, 180.0, 0);
        this.settingClick = new BooleanSetting(this, "Click", true);
        this.settingDiggingCheck = new BooleanSetting(this, "DiggingCheck", true);
        AimBotTargetEntity[] aimBotTargetEntityArray = AimBotTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", aimBotTargetEntityArray);
        String[] textArray = new String[4];
        textArray[0] = "Single";
        textArray[1] = "FOV";
        textArray[2] = "Health";
        textArray[3] = "Distance";
        this.settingTargetSort = new ModeSetting(this, "TargetSort", "Single", textArray);
        this.auxiliarySettingAdapter = new TargetFilterSetting(this);
        this.target = (EntityLivingBase)null;
        this.targetEntity2 = (EntityLivingBase)null;
        this.timestampMillis6 = 0L;
        this.timestampMillis = 0L;
        this.tickDelay = 0;
        this.coordOffsetArray2 = new double[2];
        this.coordOffsetArray = new double[2];
        this.coordOffsetArray5 = new double[2];
        this.intValueArray2 = new int[2];
        this.intValueArray3 = new int[2];
        this.rotation = (Rotation)null;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.registerEvent(40);
        this.registerEvent(19);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.target = null;
        this.targetEntity2 = null;
    }

    @Override
    public void onPreDisable() {
        this.settingDrops2.setState3(true);
        this.settingDrops2.getSetting(() -> canExecute5());
        this.modeSetting4.setSelected("None");
        this.expanded2 = true;
        super.onPreDisable();
        this.target = null;
        this.timestampMillis6 = System.currentTimeMillis();
        this.timestampMillis = System.currentTimeMillis();
        this.targetEntity2 = null;
        this.intValueArray3[0] = 0;
        this.intValueArray3[1] = 0;
        this.intValueArray2[0] = this.intValueArray2[1] = 0;
        this.coordOffsetArray5[0] = this.coordOffsetArray5[1] = 0.0;
    }

    @Override
    public void onSilentMove(EventSilentMove event) {
        if (ClientGlobals.moduleManager.freelook.isToggled()) {
            event.processAction(0);
            event.processAction2(0);
        }

        if (minecraft.thePlayer != null) {
            if (!minecraft.thePlayer.isDead
                && minecraft.thePlayer.ticksExisted > 20
                && this.target != null
                && minecraft.isIntegratedServerRunning()
                && (!this.settingClick.isEnabled() || minecraft.gameSettings.keyBindAttack.isKeyDown())
                && !ClientGlobals.rotationManager.checkModule(ClientGlobals.moduleManager.autoRod)
                && (
                    !this.settingDiggingCheck.isEnabled()
                        || !minecraft.gameSettings.keyBindAttack.isKeyDown()
                        || minecraft.objectMouseOver == null
                        || minecraft.objectMouseOver.typeOfHit != MovingObjectType.BLOCK
                )) {
                int index = (int)(System.currentTimeMillis() - this.timestampMillis6);
                Rotation rotation2 = this.getRotation(
                    this.target,
                    new Rotation(this.getFloat3(), this.getFloat2()),
                    event.getScaleFactor(),
                    180.0F,
                    
                    180.0F
                );
                if (rotation2 == null) {
                    return;
                }

                int[] intArray = SimulatedPlayer.getIntArray(
                    rotation2.yaw, rotation2.pitch, this.getFloat3(), this.getFloat2()
                );
                double offset = (double)this.intValueArray3[0] / (double)this.tickDelay * (double)index;
                double offset2 = (double)this.intValueArray3[1] / (double)this.tickDelay * (double)index;
                this.coordOffsetArray5[0] = this.coordOffsetArray5[0] + offset;
                this.coordOffsetArray5[1] = this.coordOffsetArray5[1] + offset2;
                int index2 = (int)Math.round(this.coordOffsetArray5[0] - (double)this.intValueArray2[0]);
                int index3 = (int)Math.round(this.coordOffsetArray5[1] - (double)this.intValueArray2[1]);
                this.intValueArray2[0] = this.intValueArray2[0] + index2;
                this.intValueArray2[1] = this.intValueArray2[1] + index3;
                if (event.getIndex2() != 0) {
                    int index4 = intArray[0];
                    if (index4 < 0) {
                        if (index4 - event.getIndex2() <= index4) {
                            double offset3 = (double)event.getIndex2() * this.settingAssistYawMul.getValue() + this.coordOffsetArray2[0];
                            int index5 = (int)Math.round(offset3);
                            event.processAction(index5);
                            this.coordOffsetArray2[0] = offset3 - (double)index5;
                        }
                    } else if (index4 - event.getIndex2() >= index4) {
                        double offset4 = (double)event.getIndex2() * this.settingAssistYawMul.getValue() + this.coordOffsetArray2[0];
                        int index6 = (int)Math.round(offset4);
                        event.processAction(index6);
                        this.coordOffsetArray2[0] = offset4 - (double)index6;
                    }
                }

                if (event.getIndex() != 0) {
                    int index7 = intArray[1];
                    if (index7 < 0) {
                        if (index7 - event.getIndex() <= index7) {
                            double offset5 = (double)event.getIndex() * this.settingAssistPitchMul.getValue() + this.coordOffsetArray2[1];
                            int index8 = (int)Math.round(offset5);
                            event.processAction2(index8);
                            this.coordOffsetArray2[1] = offset5 - (double)index8;
                        }
                    } else if (index7 - event.getIndex() >= index7) {
                        double offset6 = (double)event.getIndex() * this.settingAssistPitchMul.getValue() + this.coordOffsetArray2[1];
                        int index9 = (int)Math.round(offset6);
                        event.processAction2(index9);
                        this.coordOffsetArray2[1] = offset6 - (double)index9;
                    }
                }

                double offset7 = (double)event.getIndex2() * this.settingOwnYawMul.getValue() + this.coordOffsetArray[0];
                int index10 = (int)Math.round(offset7);
                event.processAction(index10);
                this.coordOffsetArray[0] = offset7 - (double)index10;
                double offset8 = (double)event.getIndex() * this.settingOwnPitchMul.getValue() + this.coordOffsetArray[1];
                int index11 = (int)Math.round(offset8);
                event.processAction2(index11);
                this.coordOffsetArray[1] = offset8 - (double)index11;
                event.processAction(index2 + event.getIndex2());
                event.processAction2(index3 + event.getIndex());
            }
        }

        this.timestampMillis6 = System.currentTimeMillis();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.target = null;
        this.targetEntity2 = null;
    }

    @Override
    public void onTick() {
        this.targetEntity2 = this.target;
        this.target = null;
        if (minecraft.isIntegratedServerRunning()
            && (!this.settingClick.isEnabled() || minecraft.gameSettings.keyBindAttack.isKeyDown())
            && this.auxiliarySettingAdapter.canExecute()) {
            Object[] itemArray = null;
            String selected = this.settingTargetSort.getSelected();
            switch (selected) {
                case "FOV":
                    itemArray = minecraft.theWorld
                        .loadedEntityList
                        .stream()
                        .filter(object -> this.checkEntity((Entity)object))
                        .sorted(Comparator.comparingDouble(object2 -> this.getDistanceToTarget4((Entity)object2)))
                        .toArray();
                    break;
                case "Health":
                    itemArray = minecraft.theWorld
                        .loadedEntityList
                        .stream()
                        .filter(object3 -> this.checkEntity((Entity)object3))
                        .sorted(Comparator.comparingDouble(object4 -> getDistanceToTarget3((Entity)object4)))
                        .toArray();
                    break;
                case "Distance":
                    itemArray = minecraft.theWorld
                        .loadedEntityList
                        .stream()
                        .filter(object5 -> this.checkEntity((Entity)object5))
                        .sorted(Comparator.comparingDouble(object6 -> getDistanceToTarget((Entity)object6)))
                        .toArray();
                    break;
                case "Single":
                    itemArray = minecraft.theWorld
                        .loadedEntityList
                        .stream()
                        .filter(object7 -> this.checkEntity((Entity)object7))
                        .sorted(Comparator.comparingDouble(object8 -> this.getDistanceToTarget2((Entity)object8)))
                        .toArray();
            }

            if (itemArray != null && itemArray.length > 0) {
                this.target = (EntityLivingBase)itemArray[0];
            }

            if (this.target != null) {
                float value = SettingContainer.settingProvider
                        .getFloat(this.actionSetting.getDouble3(), this.actionSetting.getDouble6())
                    / 2.0F;
                float value2 = SettingContainer.settingProvider
                        .getFloat(this.actionSetting2.getDouble3(), this.actionSetting2.getDouble6())
                    / 2.0F;
                Rotation rotation2 = this.getRotation(
                    this.target, new Rotation(this.getFloat3(), this.getFloat2()), 1.0F, value, value2
                );
                this.intValueArray3 = SimulatedPlayer.getIntArray(
                    rotation2.yaw, rotation2.pitch, this.getFloat3(), this.getFloat2()
                );
                System.out.println(Arrays.toString(this.intValueArray3));
                return;
            }

            this.rotation = null;
        }

        this.intValueArray3[0] = 0;
        this.intValueArray3[1] = 1;
    }

    @Override
    public void onPostTick() {
        this.tickDelay = (int)Math.abs(this.timestampMillis - System.currentTimeMillis());
        this.timestampMillis = System.currentTimeMillis();
        this.intValueArray2[0] = this.intValueArray2[1] = 0;
        this.coordOffsetArray5[0] = this.coordOffsetArray5[1] = 0.0;
        this.enabled2 = true;
    }

    private static double getDistanceToTarget(Entity entity) {
        return (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
    }

    private double getDistanceToTarget2(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return 1000.0;
        } else {
            double posX = this.targetEntity2.posX;
            double posY = entity.posY;
            double posZ = entity.posZ;
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            Client client = Client.getClient();
            return (double)Math.abs(SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2)[0]);
        }
    }

    private boolean checkEntity(Entity entity) {
        return entity instanceof EntityPlayerSP;
    }

    public static void setActive4(boolean enabled) {
        activeState4 = enabled;
    }

    private float getFloat2() {
        return Client.getClient().getViaProtocolHelper().scaleFactor2;
    }

    private float getFloat3() {
        float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;

        while (minecraft.thePlayer.rotationYaw - scaleFactor1 >= 180.0F) {
            scaleFactor1 += 360.0F;
        }

        while (!(minecraft.thePlayer.rotationYaw >= scaleFactor1 - -180.0F)) {
            scaleFactor1 -= 360.0F;
        }

        return scaleFactor1;
    }

    private static double getDistanceToTarget3(Entity entity) {
        return (double)((EntityLivingBase)entity).getFloat2();
    }

    private double getDistanceToTarget4(Entity entity) {
        double posX = entity.posX;
        double posY = entity.posY;
        double posZ = entity.posZ;
        float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
        Client client = Client.getClient();
        return (double)Math.abs(SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2)[0]);
    }

    private static boolean canExecute5() {
        return false;
    }

    public static boolean canExecute6() {
        return activeState4;
    }

    public static boolean canExecute7() {
        return !canExecute6();
    }
}
