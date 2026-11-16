package augustus.modules.player;

import client.ClientGlobals;
import client.event.AttackEntityListener;
import client.event.ClickListener;
import client.event.EventAttackEntity;
import client.event.EventClick;
import client.event.EventWorldLoad;
import client.event.MotionListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationHelper;
import client.setting.NumberSetting;
import client.util.CombatTargetFilter;
import client.util.InventoryUtil;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityThrownenderpearl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class CombatPearl extends Module implements TickListener, AttackEntityListener, WorldLoadListener, MotionListener, ClickListener {
    public NumberSetting settingMinDistance;
    public EntityLivingBase targetEntity;
    public Rotation rotation;
    public int keyCode2;
    public int tickDelay;
    public EntityThrownenderpearl entityThrownenderpearl;

    public CombatPearl() {
        super("CombatPearl", "CombatPearl", new Color(238, 255, 194), Category.PLAYER);
        this.settingMinDistance = new NumberSetting(this, "MinDistance", 10.0, 0.0, 20.0, 0);
        this.rotation = (Rotation)null;
        this.keyCode2 = 0;
        this.entityThrownenderpearl = (EntityThrownenderpearl)null;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(17);
        this.registerEvent(1);
        this.registerEvent(34);
        this.registerEvent(7);
    }

    @Override
    public void onDisable() {
        this.targetEntity = null;
    }

    @Override
    public void onPreDisable() {
        this.updateState();
    }

    @Override
    public void onClick(EventClick event) {
        if (minecraft.isIntegratedServerRunning()) {
            this.updateState();
        }
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityLivingBase) {
            this.targetEntity = (EntityLivingBase)entity;
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.targetEntity = null;
    }

    @Override
    public void onPostMotion() {
        if (minecraft.isIntegratedServerRunning()) {
            if (this.rotation == null) {
                return;
            }

            int tickDelay2 = this.tickDelay;
            SlotManager.applyModule3(this, 0, tickDelay2);
        }
    }

    @Override
    public void onPreTick() {
        this.tickDelay = this.getInt2();
        if (this.tickDelay != 0) {
            if (this.getEntityThrownenderpearl().ticksExisted == 3) {
                RotationHelper rotationHelper = new RotationHelper();
                rotationHelper.updateAuxiliaryEntityAdapter(this.entityThrownenderpearl);
                rotationHelper.getArrayList(300);
                rotationHelper.getMovingObjectPosition();
            } else {
                this.keyCode2++;
            }
        }
    }

    private static boolean checkEntity(Entity entity) {
        return !(entity instanceof EntityThrownenderpearl);
    }

    private EntityThrownenderpearl getEntityThrownenderpearl() {
        return (EntityThrownenderpearl)((Object)(this.targetEntity));
    }

    private Rotation getRotation(Vec3 vec) {
        double[] coordOffsetArray = CombatTargetFilter.getCoordOffsetArray(minecraft.thePlayer, 0);
        double offset = vec.xCoord - coordOffsetArray[0];
        double offset2 = vec.yCoord - (coordOffsetArray[1] + (double)minecraft.thePlayer.getHeight2());
        double offset3 = vec.zCoord - coordOffsetArray[2];
        float value = (float)Math.toDegrees(Math.atan2(offset3, offset)) - 90.0F;
        float value2 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
        float float9 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, value, 180.0F);
        float value3 = 0.0F;
        if (value3 >= 60.0F) {
            return new Rotation(0.0F, 0.0F);
        } else {
            float float92 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().pitch, value2 + value3 * (float)-1, 180.0F);
            RotationHelper rotationHelper = new RotationHelper();
            rotationHelper.applyEntityLivingBase3(
                minecraft.thePlayer, new ItemStack(Items.enderPearl), float9, float92, coordOffsetArray[0], coordOffsetArray[1], coordOffsetArray[2]
            );
            return rotationHelper.getIndex(
                        200,
                        this.targetEntity,
                        new AxisAlignedBB(vec.xCoord - 0.5, vec.yCoord - 1.0, vec.zCoord - 0.5, vec.xCoord + 0.5, vec.yCoord + 1.0, vec.zCoord + 0.5)
                    )
                    == 0
                ? new Rotation(float9, float92)
                : new Rotation(float9, float92);
        }
    }

    private double getCoordinate(Entity entity) {
        return (double)this.targetEntity.getDistanceToEntity(entity);
    }

    private int getInt2() {
        return (InventoryUtil.getArrayList(minecraft.thePlayer.container, 36, new Item[]{Items.enderPearl}).isEmpty() ? 1 : 0);
    }

    private void updateState() {
        this.rotation = null;
        this.keyCode2 = 0;
        this.entityThrownenderpearl = null;
    }
}
