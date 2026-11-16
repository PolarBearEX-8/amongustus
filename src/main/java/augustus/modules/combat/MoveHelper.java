package augustus.modules.combat;

import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventMove;
import client.event.EventSafeWalk;
import client.event.EventWorldLoad;
import client.event.MoveListener;
import client.event.SafeWalkListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.AutoClickerComboBreakerType;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.util.AnimationTimer;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.CombatUtil;
import client.util.MovingObjectType;
import client.util.PlayerUtil;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class MoveHelper extends Module implements WorldLoadListener, MoveListener, AttackEntityListener, TickListener, SafeWalkListener {
    public boolean movementActive = false;
    public MultiSelectSetting settingModes;
    public NumberSetting settingCombos;
    public int keyCode2;
    public int tickDelay;
    public AnimationTimer animationTimer;
    public EntityLivingBase targetEntity;
    public EntityLivingBase targetEntity2;
    public TimeHelper timer;
    public TimeHelper timer2;
    public boolean enabled2;

    public MoveHelper() {
        super("MoveHelper", "BewegungsHelfer", new Color(50, 168, 82), Category.COMBAT);
        AutoClickerComboBreakerType[] autoClickerComboBreakerTypeArray = AutoClickerComboBreakerType.values();
        this.settingModes = new MultiSelectSetting(this, "Modes", autoClickerComboBreakerTypeArray);
        this.settingCombos = new NumberSetting(this, "Combos", 1.0, 0.0, 4.0, 0).getNumberSetting(() -> canExecute5());
        this.keyCode2 = 0;
        this.tickDelay = 0;
        this.animationTimer = new AnimationTimer();
        this.targetEntity = (EntityLivingBase)null;
        this.targetEntity2 = (EntityLivingBase)null;
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.enabled2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(21, 1000);
        this.registerEvent(40);
        this.registerEvent(35);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.targetEntity2 = null;
        this.targetEntity = null;
        this.tickDelay = 0;
        this.keyCode2 = 0;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        if (event.getEntity() instanceof EntityLivingBase) {
            this.targetEntity2 = (EntityLivingBase)event.getEntity();
        }
    }

    @Override
    public void onSafeWalk(EventSafeWalk event) {
        if (PlayerUtil.checkCondition(event.getCoordinate3(), minecraft.thePlayer.posX, 0.01)
            && PlayerUtil.checkCondition(event.getCoordinate(), minecraft.thePlayer.posY, 0.01)
            && PlayerUtil.checkCondition(event.getCoordinate2(), minecraft.thePlayer.posZ, 0.01)) {
            if (event.getText().equals("game.player.hurt.fall.small") || event.getText().equals("game.player.hurt.fall.big")) {
                this.timer.reset();
            }

            if (event.getText().equals("game.player.hurt")) {
                if (this.timer.hasReached(100L)) {
                    this.keyCode2++;
                }
            }
        } else {
            if (this.targetEntity != null) {
                if (!PlayerUtil.checkCondition(event.getCoordinate3(), this.targetEntity.coordOffset5, 0.7)) {
                    return;
                }

                if (!PlayerUtil.checkCondition(event.getCoordinate(), this.targetEntity.coordOffset20, 2.0)) {
                    return;
                }

                if (!PlayerUtil.checkCondition(event.getCoordinate2(), this.targetEntity.coordOffset2, 0.7)) {
                    return;
                }

                if (event.getText().equals("game.player.hurt.fall.small") || event.getText().equals("game.player.hurt.fall.big")) {
                    this.timer2.reset();
                }

                if (!event.getText().equals("game.player.hurt")) {
                    return;
                }

                if (!this.timer2.hasReached(100L)) {
                    return;
                }

                this.keyCode2 = 0;
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.targetEntity2 = null;
        this.targetEntity = null;
    }

    @Override
    public void onMove(EventMove event) {
        if (AutoClickerComboBreakerType.COMBO_BREAKER.isSelected()) {
            this.onMove2(event);
        }
    }

    @Override
    public void onTick() {
        this.animationTimer.processAction();
    }

    @Override
    public void onPreTick() {
        EntityLivingBase entityLivingBase = this.getEntityLivingBase();
        if (entityLivingBase != null) {
            this.targetEntity = entityLivingBase;
        }

        if (this.targetEntity2 != this.targetEntity) {
            this.keyCode2 = 0;
        }

        this.targetEntity2 = this.targetEntity;
        if (AutoClickerComboBreakerType.HIT_SELECTION.isSelected()) {
            this.updateState();
        }
    }

    private void onMove2(EventMove event) {
        if (!((double)this.keyCode2 <= this.settingCombos.getValue())) {
            if (minecraft.thePlayer.hurtTime == 9) {
                if (minecraft.thePlayer.onGround) {
                    event.setState(true);
                    this.keyCode2 = 0;
                }
            }
        }
    }

    private EntityLivingBase getEntityLivingBase() {
        Object object = null;
        if (minecraft.objectMouseOver == null) {
            return ChatUtil.getEntityLivingBase2((EntityLivingBase)object);
        } else if (minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
            return ChatUtil.getEntityLivingBase2((EntityLivingBase)object);
        } else {
            MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
            return !(objectMouseOver.entityHit instanceof EntityLivingBase)
                ? ChatUtil.getEntityLivingBase2((EntityLivingBase)object)
                : ChatUtil.getEntityLivingBase2((EntityLivingBase)minecraft.objectMouseOver.entityHit);
        }
    }

    private void updateState() {
        boolean enabled = this.targetEntity != null;
        if (enabled) {
            enabled = minecraft.thePlayer != null;
        }

        if (enabled) {
            enabled = minecraft.thePlayer.getPositionVec7() != null;
        }

        if (enabled) {
            enabled = this.targetEntity.positionVec4 != null;
        }

        if (enabled) {
            enabled = minecraft.thePlayer.ticksExisted >= 10;
        }

        if (!enabled) {
            this.movementActive = false;
        } else {
            Vec3 vec = SimulatedPlayer.getVec3(this.targetEntity, (short)20072, -521758288);
            double coordinate4 = minecraft.thePlayer.getVec35(1.0F).getCoordinate4(vec);
            double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer);
            double[] coordOffsetArray2 = CombatUtil.getCoordOffsetArray(this.targetEntity);
            Vec3 vec2 = new Vec3(coordOffsetArray[0], coordOffsetArray[1] + (double)this.targetEntity.getHeight2(), coordOffsetArray[2]);
            EntityLivingBase targetEntity3 = this.targetEntity;
            Vec3 vec3 = SimulatedPlayer.getVec3(28209, vec2, targetEntity3, coordOffsetArray2[0], coordOffsetArray2[1], coordOffsetArray2[2], 52307);
            double coordinate42 = vec2.getCoordinate4(vec3);
            Vec3 vec4 = minecraft.thePlayer.getVec35(1.0F);
            EntityLivingBase targetEntity4 = this.targetEntity;
            Vec3 vec5 = SimulatedPlayer.getVec3(28209, vec4, targetEntity4, coordOffsetArray2[0], coordOffsetArray2[1], coordOffsetArray2[2], 52307);
            Vec3 vec6 = minecraft.thePlayer.getVec35(1.0F);
            double coordinate2 = vec5.squareDistanceTo(vec6);
            double coordinate22 = minecraft.thePlayer.getVec35(1.0F).squareDistanceTo(vec);
            int indexOffset4 = this.targetEntity.hurtTime;
            int indexOffset15 = this.targetEntity.indexOffset15;
            boolean enabled3 = false;
            if (indexOffset4 == indexOffset15) {
                int indexOffset42 = minecraft.thePlayer.hurtTime;
                int indexOffset152 = minecraft.thePlayer.indexOffset15;
                enabled3 = indexOffset42 == indexOffset152;
                if (!enabled3 && coordinate42 < 2.9 && coordinate4 < 3.0) {
                    enabled3 = minecraft.thePlayer.hurtTime > 8;
                }
            }

            this.tickDelay = enabled3 ? this.tickDelay + 1 : 0;
            this.enabled2 = this.movementActive;
            boolean enabled4 = false;
            if (coordinate22 > coordinate2 && coordinate42 < 2.9 && coordinate4 > 2.0) {
                enabled4 = this.tickDelay > 3;
            }

            this.movementActive = enabled4;
        }
    }

    private static boolean canExecute5() {
        return AutoClickerComboBreakerType.checkAutoClickerComboBreakerType(AutoClickerComboBreakerType.COMBO_BREAKER);
    }

    public boolean isExpanded2() {
        return this.movementActive;
    }

    public boolean isEnabled2() {
        return this.enabled2;
    }
}
