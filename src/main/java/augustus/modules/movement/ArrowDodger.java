package augustus.modules.movement;

import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.MotionListener;
import client.event.MoveListener;
import client.module.Category;
import client.module.Module;
import client.rotation.RotationHelper;
import client.setting.ArrowDodgerPhase;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class ArrowDodger extends Module implements MoveListener, ClickListener, MotionListener {
    public BooleanSetting settingDoge;
    public NumberSetting settingArrowHitboxExpand;
    public BooleanSetting settingReleaseItemAtCollisionIncoming;
    public BooleanSetting settingNotWhileEating;
    public BooleanSetting settingInInv;
    public int keyCode2;
    public int tickDelay;
    public boolean expanded2;

    public ArrowDodger() {
        super("ArrowDodger", "PfeilAusweicher", new Color(49, 234, 247), Category.MOVEMENT);
        this.settingDoge = new BooleanSetting(this, "Doge", true);
        this.settingArrowHitboxExpand = new NumberSetting(this, "Arrow hitbox expand", 0.2, 0.0, 2.0, 2).getNumberSetting(() -> this.settingDoge.isEnabled());
        this.settingReleaseItemAtCollisionIncoming = new BooleanSetting(this, "Release item at collision incoming", true);
        this.settingNotWhileEating = new BooleanSetting(this, "NotWhileEating", false);
        this.settingInInv = new BooleanSetting(this, "InInv", false);
        this.keyCode2 = -2;
        this.tickDelay = -2;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(21);
        this.registerEvent(7);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = false;
    }

    @Override
    public void onClick(EventClick event) {
        if (this.settingReleaseItemAtCollisionIncoming.isEnabled()
        && minecraft.thePlayer.getHeldItem() != null
        && !SlotManager.checkModule(this)) {
            event.setState9(false);
            event.setState11(false);
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.keyCode2 != -2) {
            if (this.settingDoge.isEnabled()) {
                event.setScaleFactor2((float)this.tickDelay);
                event.setScaleFactor7((float)this.keyCode2);
            }
        }
    }

    @Override
    public void onPostMotion() {
        ArrayList arrayList = null;
        boolean completed3 = false;
        boolean completed2 = false;
        int index3 = 0;
        Iterator iterator = null;
        Entity entity = null;
        boolean enabled2 = false;
        boolean completed = false;
        ArrowDodger arrowDodger = null;
        if (this.settingNotWhileEating.isEnabled()) {
            if (minecraft.thePlayer.canExecute6() || !this.settingInInv.isEnabled() && !minecraft.isIntegratedServerRunning()) {
                this.expanded2 = false;
                this.keyCode2 = -2;
                return;
            }
            this.keyCode2 = -2;
            this.tickDelay = -2;
            index3 = Integer.MAX_VALUE;
            arrayList = new ArrayList();
            iterator = minecraft.theWorld.loadedEntityList.iterator();
            if (iterator.hasNext()) {
                entity = (Entity)iterator.next();
                enabled2 = entity instanceof EntityArrow;
                completed = true;
            }
            if (!completed) {
                boolean empty = arrayList.isEmpty();
                if (empty) {
                    completed3 = true;
                }
                if (!completed3) {
                    arrowDodger = this;
                }
            }
        } else {
            if (!this.settingInInv.isEnabled() && !minecraft.isIntegratedServerRunning()) {
                this.expanded2 = false;
                this.keyCode2 = -2;
                return;
            }
            this.keyCode2 = -2;
            this.tickDelay = -2;
            index3 = Integer.MAX_VALUE;
            arrayList = new ArrayList();
            iterator = minecraft.theWorld.loadedEntityList.iterator();
            if (iterator.hasNext()) {
                entity = (Entity)iterator.next();
                enabled2 = entity instanceof EntityArrow;
                completed = true;
            }
            if (!completed) {
                if (arrayList.isEmpty()) {
                    completed3 = true;
                }
                if (!completed3) {
                    arrowDodger = this;
                }
            }
        }
        if (!completed3) {
            if (!completed) {
                if (!arrowDodger.settingDoge.isEnabled()) {
                    completed3 = true;
                }
                if (!completed3) {
                    arrayList.sort(Comparator.comparingDouble(object -> (double)((ArrayList)object).size()));
                    double offset = Double.MIN_VALUE;
                    int index4 = -1;
                    int index5 = -1;
                    while (true) {
                        while (index5 >= 2) {
                            if (++index4 >= 2) {
                                { completed2 = true; break; }
                            }
                            if (!completed2) {
                                index5 = -1;
                            }
                        }
                        if (completed2) break;
                        if (!completed2) {
                            double[] coordArray = CombatUtil.onMove(
                            index3,
                            minecraft.thePlayer,
                            
                            minecraft.thePlayer.posX,
                            minecraft.thePlayer.posY,
                            minecraft.thePlayer.posZ,
                            
                            minecraft.thePlayer.rotationYaw,
                            new EventMove(
                            
                            (float)index5,
                            (float)index4,
                            minecraft.thePlayer.secondaryFloatEntry.state,
                            minecraft.thePlayer.secondaryFloatEntry.state2,
                            ArrowDodgerPhase.PRE
                            )
                            );
                            float value = minecraft.thePlayer.width / 2.0F + 0.1F;
                            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(
                            coordArray[0] - (double)value,
                            coordArray[1] - 0.1F,
                            coordArray[2] - (double)value,
                            coordArray[0] + (double)value,
                            coordArray[1] + (double)minecraft.thePlayer.height + 0.1F,
                            coordArray[2] + (double)value
                            );
                            double offset2 = 0.0;
                            Iterator iterator2 = arrayList.iterator();
                            while (iterator2.hasNext()) {
                                Vec3 vec = ((Vec3)(((ArrayList)iterator2.next()).stream().min(Comparator.comparingDouble(object2 -> getCoordinate(axisAlignedBB, ((Vec3)(object2))))).orElse(null)));
                                if (vec != null) {
                                    offset2 += vec.getCoordinate4(SimulatedPlayer.processAction(vec, axisAlignedBB));
                                }
                            }
                            if (offset2 > offset) {
                                this.keyCode2 = index4;
                                this.tickDelay = index5;
                                offset = offset2;
                            }
                            index5++;
                        }
                    }
                }
            }
            if (!completed3) {
                if (!completed2) {
                    while (true) {
                        if (enabled2 && entity.ticksExisted >= 1) {
                            RotationHelper rotationHelper = new RotationHelper();
                            rotationHelper.updateEntityArrow((EntityArrow)entity);
                            ArrayList arrayList3 = rotationHelper.getArrayList3(200, (float)this.settingArrowHitboxExpand.getValue());
                            if (rotationHelper.getMovingObjectPosition() != null && rotationHelper.getMovingObjectPosition().entityHit instanceof EntityPlayerSP) {
                                arrayList.add(arrayList3);
                                if (rotationHelper.indexOffset < index3) {
                                    index3 = rotationHelper.indexOffset;
                                }
                            }
                        }

                        if (!iterator.hasNext()) {
                            if (!arrayList.isEmpty() && this.settingDoge.isEnabled()) {
                                arrayList.sort(Comparator.comparingDouble(object3 -> (double)((ArrayList)object3).size()));
                                double offset3 = Double.MIN_VALUE;

                                for (int i = -1; i < 2; i++) {
                                    for (int i2 = -1; i2 < 2; i2++) {
                                        double[] coordArray2 = CombatUtil.onMove(
                                        index3,
                                        minecraft.thePlayer,
                                        
                                        minecraft.thePlayer.posX,
                                        minecraft.thePlayer.posY,
                                        minecraft.thePlayer.posZ,
                                        
                                        minecraft.thePlayer.rotationYaw,
                                        new EventMove(
                                        
                                        (float)i2,
                                        (float)i,
                                        minecraft.thePlayer.secondaryFloatEntry.state,
                                        minecraft.thePlayer.secondaryFloatEntry.state2,
                                        ArrowDodgerPhase.PRE
                                        )
                                        );
                                        float value2 = minecraft.thePlayer.width / 2.0F + 0.1F;
                                        AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(
                                        coordArray2[0] - (double)value2,
                                        coordArray2[1] - 0.1F,
                                        coordArray2[2] - (double)value2,
                                        coordArray2[0] + (double)value2,
                                        coordArray2[1] + (double)minecraft.thePlayer.height + 0.1F,
                                        coordArray2[2] + (double)value2
                                        );
                                        double offset4 = 0.0;
                                        Iterator iterator3 = arrayList.iterator();

                                        while (iterator3.hasNext()) {
                                            Vec3 vec2 = ((Vec3)(((ArrayList)iterator3.next())
                                            .stream()
                                            .min(Comparator.comparingDouble(object4 -> getCoordinate(axisAlignedBB2, ((Vec3)(object4)))))
                                            .orElse(null)));
                                            if (vec2 != null) {
                                                offset4 += vec2.getCoordinate4(SimulatedPlayer.processAction(vec2, axisAlignedBB2));
                                            }
                                        }

                                        if (offset4 > offset3) {
                                            this.keyCode2 = i;
                                            this.tickDelay = i2;
                                            offset3 = offset4;
                                        }
                                    }
                                }
                            }
                            break;
                        }

                        entity = (Entity)iterator.next();
                        enabled2 = entity instanceof EntityArrow;
                    }
                }
                this.expanded2 = !arrayList.isEmpty();
                boolean enabled = this.settingReleaseItemAtCollisionIncoming.isEnabled();
                if (!enabled) {
                    return;
                }
                if (this.expanded2 && minecraft.thePlayer.getHeldItem() != null && minecraft.thePlayer.getEntityId2() > 0) {
                    int index = minecraft.thePlayer.inventoryPlayer.currentItem == 8
                    ? minecraft.thePlayer.inventoryPlayer.currentItem - 1
                    : minecraft.thePlayer.inventoryPlayer.currentItem + 1;
                    SlotManager.applyModule3(this, 6, index);
                }
                return;
            }
        }


        this.expanded2 = !arrayList.isEmpty();
        if (this.settingReleaseItemAtCollisionIncoming.isEnabled()) {
            if (this.expanded2 && minecraft.thePlayer.getHeldItem() != null && minecraft.thePlayer.getEntityId2() > 0) {
                int index2 = minecraft.thePlayer.inventoryPlayer.currentItem == 8
                ? minecraft.thePlayer.inventoryPlayer.currentItem - 1
                : minecraft.thePlayer.inventoryPlayer.currentItem + 1;
                SlotManager.applyModule3(this, 6, index2);
            }
        }
    }

    private static double getCoordinate(AxisAlignedBB boundingBox, Vec3 vec) {
        return vec.getCoordinate4(SimulatedPlayer.processAction(vec, boundingBox));
    }
}
