package augustus.modules.player;

import augustus.modes.combat.AuraRenderMode;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventRotation;
import client.event.MoveListener;
import client.event.RenderTickListener;
import client.event.RotationListener;
import client.event.TickListener;
import client.gui.CustomGuiScreen;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.EntitySelectPredicates;
import client.util.LivingEntityFilter;
import client.util.MoveUtil;
import client.util.MovingObjectType;
import client.util.RayTraceUtils;
import client.util.TimeHelper;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderchest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ChestAura extends Module implements RotationListener, ClickListener, TickListener, MoveListener, RenderTickListener {
    public NumberSetting settingPreRange;
    public NumberSetting settingRange;
    public ActionSetting settingYawSpeed;
    public ActionSetting settingPitchSpeed;
    public ActionSetting settingDelay;
    public BooleanSetting settingThroughWalls;
    public BooleanSetting settingThroughEntities;
    public BooleanSetting settingTargetNearbyCheck;
    public BooleanSetting settingNotWhileInventoryIsFull;
    public BooleanSetting settingOnlyOnGround;
    public BooleanSetting settingMoveFix;
    public ArrayList itemList3;
    public TimeHelper timer;
    public TileEntity tileEntity;
    public Vec3 positionVec;
    public WorldClient worldClient;

    public ChestAura() {
        super("ChestAura", "TruhenAura", new Color(134, 227, 145), Category.PLAYER);
        this.settingPreRange = new NumberSetting(this, "PreRange", 6.0, 0.0, 10.0, 1);
        this.settingRange = new NumberSetting(this, "Range", 4.5, 4.5, 6.0, 2);
        this.settingYawSpeed = new ActionSetting(this, "YawSpeed", 30.0, 50.0, 0.0, 180.0, 0);
        this.settingPitchSpeed = new ActionSetting(this, "PitchSpeed", 30.0, 50.0, 0.0, 180.0, 0);
        this.settingDelay = new ActionSetting(this, "Delay", 200.0, 500.0, 0.0, 1000.0, 0);
        this.settingThroughWalls = new BooleanSetting(this, "ThroughWalls", false);
        this.settingThroughEntities = new BooleanSetting(this, "ThroughEntities", false);
        this.settingTargetNearbyCheck = new BooleanSetting(this, "TargetNearbyCheck", true);
        this.settingNotWhileInventoryIsFull = new BooleanSetting(this, "NotWhileInventoryIsFull", false);
        this.settingOnlyOnGround = new BooleanSetting(this, "OnlyOnGround", false);
        this.settingMoveFix = new BooleanSetting(this, "MoveFix", false);
        this.itemList3 = new ArrayList();
        this.timer = new TimeHelper();
        this.tileEntity = (TileEntity)null;
        this.positionVec = (Vec3)null;
        this.worldClient = (WorldClient)null;
    }

    @Override
    public void onEnable() {
        this.registerEvent(28);
        this.registerEvent(7);
        this.registerEvent(40);
        this.registerEvent(21);
        this.registerEvent(29);
    }

    @Override
    public void onDisable() {
        this.worldClient = null;
        this.tileEntity = null;
    }

    @Override
    public void onPreDisable() {
        this.positionVec = null;
        this.tileEntity = null;
    }

    @Override
    public void onRotation(EventRotation event) {
        if (this.settingMoveFix.isEnabled()) {
            event.setScaleFactor(MoveUtil.getFloat2());
        }
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (this.settingMoveFix.isEnabled()) {
            event.setScaleFactor(MoveUtil.getFloat2());
        }
    }

    @Override
    public void onClick(EventClick event) {
        ChestAura chestAura;
        if (ClientGlobals.rotationManager.checkModule(this) && minecraft.currentScreen == null) {
            chestAura = this;
        } else {
            chestAura = this;
        }

        chestAura.timer.reset();
    }

    @Override
    public void onMove(EventMove event) {
        if (this.settingMoveFix.isEnabled()) {
            event.setAuraRenderMode(AuraRenderMode.DEFAULT);
        }
    }

    @Override
    public void onPostRenderTick() {
        if (this.tileEntity != null && ClientGlobals.rotationManager.checkModule(this)) {
            this.processFloat(1.0F);
        }
    }

    @Override
    public void onPreTick() {
        this.worldClient = minecraft.theWorld;
        if (minecraft.currentScreen instanceof CustomGuiScreen) {
            Object[] itemArray = minecraft.theWorld
            .itemList3
            .stream()
            .filter(object -> this.checkTileEntity((TileEntity)object))
            .sorted(Comparator.comparingDouble(object2 -> getCoordinate((TileEntity)object2)))
            .toArray();
            this.tileEntity = null;
            if (itemArray.length > 0 && minecraft.currentScreen == null) {
                this.tileEntity = (TileEntity)itemArray[0];
                new Vec3(
                (double)this.tileEntity.getBlockPos().getX() + 0.5,
                (double)this.tileEntity.getBlockPos().getY() + 0.5,
                (double)this.tileEntity.getBlockPos().getZ() + 0.5
                );
                byte state = ((byte)(this.settingThroughWalls.isEnabled() ? 1 : 0));
                if (state < itemArray.length) {
                    TileEntity tileEntity2 = (TileEntity)itemArray[state];
                    tileEntity2.getBlock().processAction((World)minecraft.theWorld, tileEntity2.getBlockPos());
                } else {
                    this.tileEntity = null;
                }
            }
        }
    }

    private boolean checkVec3(Vec3 vec, TileEntity tileEntity2) {
        MovingObjectPosition movingObjectPosition4 = minecraft.theWorld
        .getMovingObjectPosition4(
        minecraft.thePlayer.posX,
        minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2(),
        minecraft.thePlayer.posZ,
        vec.xCoord,
        vec.yCoord,
        vec.zCoord,
        false,
        false,
        true
        );
        if (movingObjectPosition4 == null) {
            return false;
        } else {
            return movingObjectPosition4.typeOfHit != MovingObjectType.BLOCK ? false : movingObjectPosition4.getBlockPos().checkBlockPos2(tileEntity2.getBlockPos());
        }
    }

    private boolean checkEntity(Entity entity) {
        if (entity != minecraft.thePlayer) {
            double distance = (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
            return distance != this.settingPreRange.getValue() + 3.0;
        } else {
            return false;
        }
    }

    private void processFloat(float value3) {
        Entity renderViewEntity = minecraft.getRenderViewEntity();
        if (renderViewEntity != null && minecraft.theWorld != null) {
            minecraft.pointedEntity = (Entity)null;
            double blockReachDistance = (double)minecraft.playerController.getBlockReachDistance();
            double value = this.settingRange.getValue();
            double offset = Math.max(blockReachDistance, value);
            if (this.settingThroughWalls.isEnabled()) {
                BlockPos blockPos = this.tileEntity.getBlockPos();
                float rotationYaw = minecraft.thePlayer.rotationYaw;
                float rotationPitch = minecraft.thePlayer.rotationPitch;
                double value2 = this.settingRange.getValue();
                MovingObjectPosition movingObjectPosition = RayTraceUtils.getMovingObjectPosition(blockPos, rotationYaw, rotationPitch, value2);
                minecraft.objectMouseOver = movingObjectPosition;
                if (minecraft.objectMouseOver != null) {
                    Vec3 hitVec = minecraft.objectMouseOver.hitVec;
                    EnumFacing sideHit = minecraft.objectMouseOver.sideHit;
                    BlockPos blockPos2 = this.tileEntity.getBlockPos();
                    MovingObjectPosition movingObjectPosition2 = new MovingObjectPosition(hitVec, sideHit, blockPos2);
                    minecraft.objectMouseOver = movingObjectPosition2;
                } else {
                    MovingObjectPosition movingObjectPosition22 = renderViewEntity.getMovingObjectPosition2(offset, value3);
                    minecraft.objectMouseOver = movingObjectPosition22;
                }
            } else {
                MovingObjectPosition movingObjectPosition23 = renderViewEntity.getMovingObjectPosition2(offset, value3);
                minecraft.objectMouseOver = movingObjectPosition23;
            }

            Vec3 vec2 = renderViewEntity.getVec35(value3);
            boolean enabled;
            if ((!Double.isNaN(offset) && !Double.isNaN(3.0) ? (offset > 3.0 ? 1 : (offset == 3.0 ? 0 : -1)) : -1) <= 0) {
                enabled = false;
            } else {
                enabled = true;
            }

            double offset2;
            if (minecraft.objectMouseOver == null) {
                offset2 = offset;
            } else {
                offset2 = minecraft.objectMouseOver.hitVec.getCoordinate4(vec2);
            }

            if (this.settingThroughEntities.isEnabled()) {
                return;
            }

            Vec3 vec3 = renderViewEntity.getVec36(value3);
            double offset3 = vec3.xCoord * offset;
            double offset4 = vec3.yCoord * offset;
            double offset5 = vec3.zCoord * offset;
            Vec3 vec4 = vec2.getVec312(offset3, offset4, offset5);
            minecraft.entityRenderer.entity = (Entity)null;
            float value4 = 1.0F;
            AxisAlignedBB boundingBox2 = renderViewEntity.getBoundingBox2();
            double offset6 = vec3.xCoord * offset;
            double offset7 = vec3.yCoord * offset;
            double offset8 = vec3.zCoord * offset;
            AxisAlignedBB axisAlignedBB7 = boundingBox2.contract(offset6, offset7, offset8);
            double offset9 = (double)value4;
            double offset10 = (double)value4;
            double offset11 = (double)value4;
            AxisAlignedBB axisAlignedBB = axisAlignedBB7.offset(offset9, offset10, offset11);
            Predicate predicate4 = LivingEntityFilter.predicate4;
            EntityRenderer entityRenderer = minecraft.entityRenderer;
            EntitySelectPredicates predicateAdapter = new EntitySelectPredicates(entityRenderer);
            Predicate predicate = Predicates.and(predicate4, predicateAdapter);
            List entitiesInAABBexcluding = minecraft.theWorld.getEntitiesInAABBexcluding(renderViewEntity, axisAlignedBB, predicate);
            Vec3 vec = null;
            double offset12 = offset2;
            int index = 0;

            while (true) {
                int size = entitiesInAABBexcluding.size();
                if (index >= size) {
                    Entity entity = minecraft.entityRenderer.entity;
                    if (entity != null && enabled) {
                        double coordinate4 = vec2.getCoordinate4(vec);
                        if ((!Double.isNaN(coordinate4) && !Double.isNaN(3.0) ? (coordinate4 > 3.0 ? 1 : (coordinate4 == 3.0 ? 0 : -1)) : -1) > 0) {
                            minecraft.entityRenderer.entity = (Entity)null;
                            MovingObjectType movingObjectTypeKind = MovingObjectType.MISS;
                            EnumFacing enumFacing = (EnumFacing)null;
                            BlockPos blockPos3 = new BlockPos(vec);
                            MovingObjectPosition movingObjectPosition3 = new MovingObjectPosition(movingObjectTypeKind, vec, enumFacing, blockPos3);
                            minecraft.objectMouseOver = movingObjectPosition3;
                        }
                    }

                    if (minecraft.entityRenderer.entity != null
                    && (
                    (!Double.isNaN(offset12) && !Double.isNaN(offset2) ? (offset12 > offset2 ? 1 : (offset12 == offset2 ? 0 : -1)) : 1) < 0
                    || minecraft.objectMouseOver == null
                    )) {
                        Entity entity2 = minecraft.entityRenderer.entity;
                        MovingObjectPosition movingObjectPosition4 = new MovingObjectPosition(entity2, vec);
                        minecraft.objectMouseOver = movingObjectPosition4;
                        if (minecraft.entityRenderer.entity instanceof EntityLivingBase || minecraft.entityRenderer.entity instanceof EntityItemframe) {
                            Entity entity3 = minecraft.entityRenderer.entity;
                            minecraft.pointedEntity = entity3;
                        }
                    }
                    break;
                }

                Entity entity4 = (Entity)entitiesInAABBexcluding.get(index);
                float height = entity4.getHeight();
                AxisAlignedBB boundingBox22 = entity4.getBoundingBox2();
                double offset13 = (double)height;
                double offset14 = (double)height;
                double offset15 = (double)height;
                AxisAlignedBB axisAlignedBB2 = boundingBox22.offset(offset13, offset14, offset15);
                MovingObjectPosition movingObjectPosition5 = axisAlignedBB2.calculateIntercept(vec2, vec4);
                if (axisAlignedBB2.isVecInside(vec2)) {
                    if ((!Double.isNaN(offset12) && !Double.isNaN(0.0) ? (offset12 > 0.0 ? 1 : (offset12 == 0.0 ? 0 : -1)) : -1) >= 0) {
                        minecraft.entityRenderer.entity = entity4;
                        if (movingObjectPosition5 == null) {
                            vec = vec2;
                        } else {
                            vec = movingObjectPosition5.hitVec;
                        }

                        offset12 = 0.0;
                    }
                } else if (movingObjectPosition5 != null) {
                    Vec3 hitVec2 = movingObjectPosition5.hitVec;
                    double coordinate42 = vec2.getCoordinate4(hitVec2);
                    if ((!Double.isNaN(coordinate42) && !Double.isNaN(offset12) ? (coordinate42 > offset12 ? 1 : (coordinate42 == offset12 ? 0 : -1)) : 1) < 0
                    || (!Double.isNaN(offset12) && !Double.isNaN(0.0) ? (offset12 > 0.0 ? 1 : (offset12 == 0.0 ? 0 : -1)) : -1) == 0) {
                        Entity ridingEntity = renderViewEntity.ridingEntity;
                        if (entity4 == ridingEntity) {
                            if ((!Double.isNaN(offset12) && !Double.isNaN(0.0) ? (offset12 > 0.0 ? 1 : (offset12 == 0.0 ? 0 : -1)) : -1) == 0) {
                                minecraft.entityRenderer.entity = entity4;
                                vec = movingObjectPosition5.hitVec;
                            }
                        } else {
                            minecraft.entityRenderer.entity = entity4;
                            vec = movingObjectPosition5.hitVec;
                            offset12 = coordinate42;
                        }
                    }
                }

                index++;
            }
        }
    }

    private boolean checkTileEntity(TileEntity tileEntity2) {
        if ((byte)((tileEntity2 instanceof TileEntityChest) ? 1 : 0) == 0 && (byte)((tileEntity2 instanceof TileEntityEnderchest) ? 1 : 0) == 0) {
            return false;
        } else {
            Vec3 vec2 = new Vec3(
            (double)tileEntity2.getBlockPos().getX() + 0.5, (double)tileEntity2.getBlockPos().getY() + 0.5, (double)tileEntity2.getBlockPos().getZ() + 0.5
            );
            double double4 = minecraft.thePlayer.getDouble4(vec2.xCoord, vec2.yCoord, vec2.zCoord);
            if (double4 < this.settingPreRange.getValue()) {
                for (Vec3 vec : (Iterable<Vec3>) this.itemList3) {
                    if (vec != null
                    && vec2.xCoord == vec.xCoord
                    && vec2.yCoord == vec.yCoord
                    && vec2.zCoord == vec.zCoord) {
                        return false;
                    }
                }

                return !minecraft.theWorld.getBlockState(tileEntity2.getBlockPos().up()).getBlock().isState18();
            } else {
                return false;
            }
        }
    }

    private static double getCoordinate(TileEntity tileEntity2) {
        return minecraft.thePlayer
        .getDouble4((double)tileEntity2.getBlockPos().getX() + 0.5, (double)tileEntity2.getBlockPos().getY() + 0.5, (double)tileEntity2.getBlockPos().getZ() + 0.5);
    }
}
