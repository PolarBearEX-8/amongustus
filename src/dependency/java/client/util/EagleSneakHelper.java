package client.util;

import client.event.EventClick;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.util.BooleanCondition;
import client.util.CombatRayTrace;
import client.util.CombatUtil;
import client.util.MinecraftAccessor;
import client.util.MovingObjectType;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class EagleSneakHelper
implements MinecraftAccessor {
    public final BooleanSetting booleanSetting3;
    public final ModeSetting modeSetting;
    public final int count5;
    public int count = 0;
    public int count2 = 0;
    public final BooleanSetting booleanSetting;
    public int count3;
    public int count4;
    public boolean enabled;
    public final BooleanSetting booleanSetting2;
    public final SmoothDamper smoothDamper2 = new SmoothDamper(20, 0.0);
    public final RangeSetting rangeSetting;
    public boolean enabled2 = false;
    public final SmoothDamper smoothDamper = new SmoothDamper(20, 0.0);
    public final RangeSetting rangeSetting2;

    public void applyMovingObjectPosition(MovingObjectPosition movingObjectPosition, EventClick eventClick, BooleanCondition booleanCondition, boolean bl) {
        double[] dArray;
        MovingObjectPosition movingObjectPosition2;
        int n = 9604;
        int n2 = 14880;
        int n3 = 37651;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ 0x4CEC5C0A33A4L ^ 0xCEE03D51336L;
        if (movingObjectPosition == null || movingObjectPosition.typeOfHit != MovingObjectType.BLOCK) {
            return;
        }
        if (MinecraftAccessor.minecraft.thePlayer.isUsingItem()) {
            return;
        }
        boolean bl2 = false;
        if (this.booleanSetting.isEnabled() && (movingObjectPosition2 = CombatRayTrace.resolveMovingObjectPosition(1.0f, new Vec3((dArray = CombatUtil.getCoordOffsetArray(0, MinecraftAccessor.minecraft.thePlayer))[0], dArray[1], dArray[2]), new Rotation(MinecraftAccessor.minecraft.thePlayer.rotationYaw, MinecraftAccessor.minecraft.thePlayer.rotationPitch))) != null && movingObjectPosition2.getBlockPos() != null && !MinecraftAccessor.minecraft.thePlayer.isSprinting() && movingObjectPosition2.getBlockPos().checkBlockPos2(movingObjectPosition.getBlockPos()) && movingObjectPosition2.sideHit == movingObjectPosition.sideHit) {
            Vec3 vec3 = movingObjectPosition2.hitVec;
            EnumFacing enumFacing = movingObjectPosition2.sideHit;
            BlockPos blockPos = movingObjectPosition2.getBlockPos();
            bl2 = CombatUtil.checkItemStack(MinecraftAccessor.minecraft.thePlayer.getHeldItem(), blockPos, enumFacing, vec3);
        }
        Vec3 vec3 = movingObjectPosition.hitVec;
        EnumFacing enumFacing = movingObjectPosition.sideHit;
        BlockPos blockPos = movingObjectPosition.getBlockPos();
        boolean bl3 = CombatUtil.checkItemStack(MinecraftAccessor.minecraft.thePlayer.getHeldItem(), blockPos, enumFacing, vec3);
        boolean bl4 = false;
        if (bl2 && bl3 && !this.enabled2 && this.booleanSetting.isEnabled()) {
            bl4 = true;
            this.enabled2 = true;
        } else {
            this.enabled2 = false;
        }
        if (bl4) {
            return;
        }
        boolean bl5 = this.modeSetting.getSelected().equals("Internal");
        if (!bl5) {
            this.count2 = 0;
        }
        int n4 = this.rangeSetting2.getIndexOffset2();
        this.count2 += this.smoothDamper2.getInt(n4);
        if (this.booleanSetting3.isEnabled()) {
            this.count2 = 0;
        }
        int n5 = bl5 ? SettingContainer.settingProvider.getInt2(-1, 0) : 0;
        boolean bl6 = false;
        boolean bl7 = false;
        for (int i = 0; i < 20 && this.count2 + n5 > 0; ++i) {
            booleanCondition.getAsBoolean();
            bl7 = true;
            bl6 = true;
            bl3 = false;
            --this.count2;
        }
        if ((this.booleanSetting3.isEnabled() || this.booleanSetting2.isEnabled()) && bl3) {
            boolean bl8 = booleanCondition.getAsBoolean();
            eventClick.setState7(bl8);
            bl6 = true;
        }
        if (bl6 && !bl5) {
            int n6 = this.rangeSetting.getIndexOffset2();
            int n7 = this.smoothDamper.getInt(n6);
            for (int i = 0; i < 20 && n7 > 0; --n7, ++i) {
                booleanCondition.getAsBoolean();
            }
        }
        if (!bl6 && bl) {
            Vec3 vec32 = movingObjectPosition.hitVec;
            EnumFacing enumFacing2 = movingObjectPosition.sideHit;
            BlockPos blockPos2 = movingObjectPosition.getBlockPos();
            if (CombatUtil.checkItemStack(MinecraftAccessor.minecraft.thePlayer.getHeldItem(), blockPos2, enumFacing2, vec32)) {
                booleanCondition.getAsBoolean();
            }
        }
        if (bl7) {
            this.rangeSetting2.recalculate();
            this.rangeSetting.recalculate();
        }
    }

    public boolean canExecute() {
        boolean bl = MinecraftAccessor.minecraft.theWorld.getIBlockState2(MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY - 0.5, MinecraftAccessor.minecraft.thePlayer.posZ).getBlock().isState18();
        if (bl && !this.enabled) {
            this.count4 = SettingContainer.settingProvider.getInt2(0, 1);
            this.count3 = SettingContainer.settingProvider.getInt2(0, 1);
        }
        if (!bl && this.enabled) {
            this.count = this.count3;
        }
        boolean bl2 = (this.count4 >= 0 || !this.enabled) && !bl;
        this.enabled = bl;
        double[] dArray = CombatUtil.getCoordOffsetArray(this.count4 - 1, MinecraftAccessor.minecraft.thePlayer);
        if (bl && !MinecraftAccessor.minecraft.theWorld.getIBlockState2(dArray[0], MinecraftAccessor.minecraft.thePlayer.posY - 1.0, dArray[2]).getBlock().isState18()) {
            bl2 = true;
        }
        if (bl && this.count > 0) {
            bl2 = true;
            --this.count;
        }
        return bl2;
    }

    public void applyMovingObjectPosition2(MovingObjectPosition movingObjectPosition, EventClick eventClick, boolean bl) {
        long l = 0x7b8d04276861L;
        long l2 = 0x4CEC5C0A33A4L ^ l ^ 0x12E5620DC8D6L;
        int n = (int)(l2 << 32 >>> 48);
        BooleanCondition booleanCondition = () -> EagleSneakHelper.onClick(eventClick);
        EventClick eventClick2 = eventClick;
        MovingObjectPosition movingObjectPosition2 = movingObjectPosition;
        this.applyMovingObjectPosition(movingObjectPosition2, eventClick2, booleanCondition, bl);
    }

    public void applyMovingObjectPosition3(MovingObjectPosition movingObjectPosition, EventClick eventClick, BooleanCondition booleanCondition) {
        EventClick eventClick2 = eventClick;
        MovingObjectPosition movingObjectPosition2 = movingObjectPosition;
        this.applyMovingObjectPosition(movingObjectPosition2, eventClick2, booleanCondition, false);
    }

    private static boolean onClick(EventClick eventClick) {
        eventClick.updateInt2(1);
        return true;
    }

    public EagleSneakHelper(RangeSetting rangeSetting, ModeSetting modeSetting, RangeSetting rangeSetting2, BooleanSetting booleanSetting, BooleanSetting booleanSetting2, BooleanSetting booleanSetting3) {
        this.count5 = 20;
        this.count4 = SettingContainer.settingProvider.getInt2(0, 2);
        this.count3 = SettingContainer.settingProvider.getInt2(0, 2);
        this.rangeSetting2 = rangeSetting;
        this.modeSetting = modeSetting;
        this.rangeSetting = rangeSetting2;
        this.booleanSetting2 = booleanSetting;
        this.booleanSetting = booleanSetting3;
        this.booleanSetting3 = booleanSetting2;
        this.rangeSetting2.recalculate();
        this.rangeSetting.recalculate();
    }
}

