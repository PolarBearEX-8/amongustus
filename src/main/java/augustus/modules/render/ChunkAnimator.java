package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.render.ChunkAnimationProgress;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.KeybindAnimation;
import java.awt.Color;
import java.util.WeakHashMap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public final class ChunkAnimator extends Module {
    public String[] variationModes;
    public ModeSetting settingMode;
    public ModeSetting settingAnimation;
    public NumberSetting settingAnimationTime;
    public BooleanSetting settingDisableAroundPlayer;
    public WeakHashMap weakHashMap;

    public ChunkAnimator() {
        super("ChunkAnimator", "LadungsAnimationFürBereiche", new Color(54, 21, 230), Category.RENDER);
        String[] textArray = new String[5];
        textArray[0] = "Below";
        textArray[1] = "Above";
        textArray[2] = "Horizon";
        textArray[3] = "Cardinal";
        textArray[4] = "Variation";
        this.variationModes = textArray;
        Object object = this.variationModes[0];
        this.settingMode = new ModeSetting(this, "Mode", (String)object, this.variationModes);
        String[] stringArray = KeybindAnimation.getStringArray(true);
        this.settingAnimation = new ModeSetting(this, "Animation", "easeOutBack", stringArray);
        this.settingAnimationTime = new NumberSetting(this, "AnimationTime", 500.0, 0.0, 2000.0, 0).getNumberSetting(() -> this.isAnimationTimeVisible());
        this.settingDisableAroundPlayer = new BooleanSetting(this, "DisableAroundPlayer", false);
        this.weakHashMap = new WeakHashMap();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    private boolean isAnimationTimeVisible() {
        return !this.settingAnimation.getSelected().equals("None");
    }

    private static EnumFacing getEnumFacing(Vec3i vec) {
        int index = Math.abs(vec.getX());
        int index2 = Math.abs(vec.getZ());
        if (index > index2) {
            return vec.getX() > 0 ? EnumFacing.EAST : EnumFacing.WEST;
        } else {
            return vec.getZ() > 0 ? EnumFacing.SOUTH : EnumFacing.NORTH;
        }
    }

    public void updateItemArrayEntry(RenderChunk itemArrayEntry) {
        if (this.isToggled()) {
            ChunkAnimationProgress internalLongEntry = (ChunkAnimationProgress)this.weakHashMap.get(itemArrayEntry);
            if (internalLongEntry != null) {
                long epochMillis = internalLongEntry.epochMillis;
                int int2 = this.settingMode.getInt2();
                if (epochMillis == -1L) {
                    epochMillis = System.currentTimeMillis();
                    internalLongEntry.epochMillis = epochMillis;
                    if (int2 == 4) {
                        BlockPos blockPos = minecraft.thePlayer.getBlockPos();
                        BlockPos blockPos3 = blockPos.add(0, -blockPos.getY(), 0);
                        BlockPos blockPos32 = itemArrayEntry.getBlockPos().add(8, -itemArrayEntry.getBlockPos().getY(), 8);
                        internalLongEntry.enumFacing = getEnumFacing(blockPos3.getBlockPos(blockPos32));
                    }
                }

                long time2 = System.currentTimeMillis() - epochMillis;
                int animationTime = (int)this.settingAnimationTime.getValue();
                if (time2 >= (long)animationTime) {
                    this.weakHashMap.remove(itemArrayEntry);
                    return;
                }

                int y = itemArrayEntry.getBlockPos().getY();
                if (int2 == 2) {
                    if ((double)y < minecraft.theWorld.getDouble()) {
                        int2 = 0;
                    } else {
                        int2 = 1;
                    }
                }

                if (int2 == 4) {
                    int2 = 3;
                }

                switch (int2) {
                    case 0:
                        GlStateManager.translate3d2(0.0, (double)(-y) + this.getDouble((float)time2, (float)y, (float)animationTime), 0.0);
                        break;
                    case 1:
                        GlStateManager.translate3d2(
                            0.0, (double)(256 - y) - this.getDouble((float)time2, (float)(256 - y), (float)animationTime), 0.0
                        );
                    case 2:
                    default:
                        break;
                    case 3:
                        if (internalLongEntry.enumFacing != null) {
                            Vec3i vec3i = internalLongEntry.enumFacing.getVec3i();
                            double offset = -(200.0 - this.getDouble((float)time2, 200.0F, (float)animationTime));
                            GlStateManager.translate3d2((double)vec3i.getX() * offset, 0.0, (double)vec3i.getZ() * offset);
                        }
                }
            }
        }
    }

    private double getDouble(float value, float value2, float value3) {
        return KeybindAnimation.getCoordinate(KeybindAnimation.getEasingFunction(this.settingAnimation.getSelected()), (double)(value / value3))
            * (double)value2;
    }

    public void updateItemArrayEntry(RenderChunk itemArrayEntry, BlockPos blockPos) {
        if (this.isToggled() && minecraft.thePlayer != null) {
            boolean enabled = true;
            BlockPos blockPos2 = minecraft.thePlayer.getBlockPos();
            BlockPos blockPos3 = blockPos2.add(0, -blockPos2.getY(), 0);
            BlockPos blockPos32 = blockPos.add(8, -blockPos.getY(), 8);
            if (this.settingDisableAroundPlayer.isEnabled()) {
                enabled = blockPos3.getCoordinate(blockPos32) > 4096.0;
            }

            if (enabled) {
                EnumFacing enumFacing = null;
                if (this.settingMode.getInt2() == 3) {
                    enumFacing = getEnumFacing(blockPos3.getBlockPos(blockPos32));
                }

                ChunkAnimationProgress internalLongEntry = new ChunkAnimationProgress(-1L, enumFacing);
                this.weakHashMap.put(itemArrayEntry, internalLongEntry);
                return;
            }

            this.weakHashMap.remove(itemArrayEntry);
        }
    }
}
