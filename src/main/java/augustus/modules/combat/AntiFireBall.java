package augustus.modules.combat;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.TickListener;
import client.module.Category;
import client.module.SubModule;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.util.BooleanSupplier;
import client.util.MovingObjectType;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import java.awt.Color;
import java.util.Comparator;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class AntiFireBall extends SubModule implements ClickListener, TickListener {
    public BooleanSetting settingNoRotate = new BooleanSetting(this, "NoRotate", true);
    public NumberSetting settingPreAimRange;
    public NumberSetting settingDelay;
    public NumberSetting settingRange;
    public BooleanSetting settingSlowDown;
    public NumberSetting settingTicksExisted;
    public BooleanSetting settingNotWhileDigging;
    public TimeHelper timer;
    public Object[] objectArray;
    public Entity entity;

    public AntiFireBall() {
        super("AntiFireBall", "GegenFeuerbälle", new Color(232, 147, 0), Category.COMBAT);
        this.settingPreAimRange = new NumberSetting(this, "PreAimRange", 4.0, 0.0, 15.0, 1).getNumberSetting(() -> canExecute7());
        this.settingDelay = new NumberSetting(this, "Delay", 60.0, 0.0, 400.0, 0);
        this.settingRange = new NumberSetting(this, "Range", 4.0, 0.0, 6.0, 2).getNumberSetting(() -> canExecute6());
        this.settingSlowDown = new BooleanSetting(this, "SlowDown", true);
        this.settingTicksExisted = new NumberSetting(this, "TicksExisted", 10.0, 0.0, 20.0, 0);
        this.settingNotWhileDigging = new BooleanSetting(this, "NotWhileDigging", false);
        this.timer = new TimeHelper();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.registerEvent(7);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.entity = null;
        this.objectArray = null;
    }

    @Override
    public void onPreDisable() {
        super.onPreDisable();
    }

    @Override
    public void onClick(EventClick event) {
        boolean condition;
        if (this.canExecute5()
        && !this.settingNoRotate.isEnabled()
        && ClientGlobals.rotationManager.checkModule(this)) {
            condition = true;
        } else {
            condition = false;
        }

        this.expanded2 = condition;
        if (this.canExecute5()) {
            if (!this.settingNoRotate.isEnabled()) {
                if (minecraft.objectMouseOver == null) {
                    return;
                }

                if (minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                    TimeHelper timer2 = this.timer;
                    long time;
                    if (this.settingDelay.getValue() == 0.0) {
                        time = 0L;
                    } else {
                        double value = this.settingDelay.getValue();
                        double coord = (double)SettingContainer.settingProvider.getInt3(0, 60);
                        time = (long)(value + coord);
                    }

                    if (!timer2.hasReached(time)) {
                        return;
                    }

                    if (this.settingSlowDown.isEnabled()) {
                        event.updateInt(1);
                    } else {
                        event.updateInt(1);
                        event.setRunnable3(() -> updateState());
                    }

                    this.timer.reset();
                }
            }
        }
    }

    @Override
    public void onPreTick() {
        this.objectArray = null;
        this.entity = null;
        this.objectArray = minecraft.theWorld
        .loadedEntityList
        .stream()
        .filter(item -> this.checkEntity((Entity)item))
        .sorted(Comparator.comparingDouble(item2 -> getCoordinate((Entity)item2)))
        .toArray();
        if (this.objectArray.length > 0) {
            this.entity = (Entity)this.objectArray[0];
        }

        if (!this.settingNoRotate.isEnabled()) {
            if (this.canExecute5()) {
                if (!BlockCache.canExecute() && ClientGlobals.moduleManager.killAura.target == null) {
                    Entity entity2 = this.entity;
                    Rotation rotation = this.getRotation(entity2);
                    ClientGlobals.rotationManager.applyRotation2(rotation, -1, 1898960900, this);
                }
            }
        } else {
            if (this.entity != null) {
                if (minecraft.playerController.state && this.settingNotWhileDigging.isEnabled()) {
                    return;
                }

                if (!minecraft.isIntegratedServerRunning()) {
                    return;
                }

                double coord;
                if (minecraft.thePlayer.checkEntity4(this.entity)) {
                    coord = this.settingRange.getValue();
                } else {
                    coord = Math.min(3.0, this.settingRange.getValue());
                }

                if ((double)minecraft.thePlayer.getDistanceSqToEntity(this.entity) < coord) {
                    TimeHelper timer2 = this.timer;
                    long time;
                    if (this.settingDelay.getValue() == 0.0) {
                        time = 0L;
                    } else {
                        double value = this.settingDelay.getValue();
                        double coord2 = (double)SettingContainer.settingProvider.getInt3(0, 60);
                        time = (long)(value + coord2);
                    }

                    if (timer2.hasReached(time)) {
                        minecraft.thePlayer.respawnPlayer();
                        minecraft.playerController.attackEntity(minecraft.thePlayer, this.entity);
                        this.timer.reset();
                    }
                }
            }
        }
    }

    private static double getCoordinate(Entity entity2) {
        return (double)minecraft.thePlayer.getDistanceSqToEntity(entity2);
    }

    private static void updateState() {
        minecraft.thePlayer.respawnPlayer();
        if (minecraft.objectMouseOver.entityHit != null) {
            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C02PacketUseEntity(minecraft.objectMouseOver.entityHit, UseEntityAction.ATTACK));
        }
    }

    private boolean checkEntity(Entity entity2) {
        if (!(entity2 instanceof EntityFireball)) {
            return false;
        } else {
            double coord = (double)entity2.ticksExisted;
            if (coord <= this.settingTicksExisted.getValue()) {
                return false;
            } else {
                double coord3 = (double)minecraft.thePlayer.getDistanceSqToEntity(entity2);
                if (coord3 >= this.settingPreAimRange.getValue()) {
                    return false;
                } else {
                    double coord5 = (double)minecraft.thePlayer.getDistanceSqToEntity(entity2);
                    return coord5 <= minecraft.thePlayer.getDouble4(entity2.prevPosX, entity2.prevPosY, entity2.prevPosZ);
                }
            }
        }
    }

    private boolean canExecute5() {
        if (this.entity != null && ClientGlobals.moduleManager.killAura.target == null) {
            if (BlockCache.canExecute()) {
                return false;
            }

            if (!minecraft.isIntegratedServerRunning()) {
                return false;
            }

            if (!minecraft.playerController.state) {
                return true;
            }

            if (!this.settingNotWhileDigging.isEnabled()) {
                return true;
            }
        }

        return false;
    }

    private static boolean canExecute6() {
        return ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
    }

    private static boolean canExecute7() {
        return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
    }
}
