package augustus.modules.legit;

import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.ColorSetting;
import client.setting.MLGHelperNothingType;
import client.setting.NumberSetting;
import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.block.BlockAir;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;

public class MLGHelper extends Module implements RenderListener {
    public ActionSetting settingMinDistance;
    public ColorSetting settingColor;
    public NumberSetting settingPosX;
    public NumberSetting settingPosY;
    public NumberSetting settingScale;
    public ArrayList waterCandidates;
    public ArrayList itemList3;
    public ArrayList itemList4;

    public MLGHelper() {
        super("MLGHelper", "MLGHelfer", new Color(50, 168, 141), Category.LEGIT);
        this.settingMinDistance = new ActionSetting(this, "MinDistance", 10.0, 100.0, 10.0, 100.0, 0);
        Color color = new Color(255, 255, 255, 255);
        this.settingColor = new ColorSetting(this, "Color", color);
        this.settingPosX = new NumberSetting(this, "PosX", 50.0, 0.0, 100.0, 2);
        this.settingPosY = new NumberSetting(this, "PosY", 0.0, 0.0, 100.0, 2);
        this.settingScale = new NumberSetting(this, "Scale", 1.0, 0.5, 4.0, 1);
        Integer[] countArray = new Integer[46];
        countArray[0] = 16;
        countArray[1] = 19;
        countArray[2] = 22;
        countArray[3] = 24;
        countArray[4] = 27;
        countArray[5] = 29;
        countArray[6] = 34;
        countArray[7] = 36;
        countArray[8] = 38;
        countArray[9] = 40;
        countArray[10] = 42;
        countArray[11] = 49;
        countArray[12] = 51;
        countArray[13] = 53;
        countArray[14] = 55;
        countArray[15] = 58;
        countArray[16] = 60;
        countArray[17] = 62;
        countArray[18] = 65;
        countArray[19] = 67;
        countArray[20] = 69;
        countArray[21] = 72;
        countArray[22] = 74;
        countArray[23] = 77;
        countArray[24] = 79;
        countArray[25] = 82;
        countArray[26] = 85;
        countArray[27] = 87;
        countArray[28] = 90;
        countArray[29] = 93;
        countArray[30] = 95;
        countArray[31] = 98;
        countArray[32] = 101;
        countArray[33] = 104;
        countArray[34] = 106;
        countArray[35] = 109;
        countArray[36] = 112;
        countArray[37] = 115;
        countArray[38] = 118;
        countArray[39] = 119;
        countArray[40] = 121;
        countArray[41] = 127;
        countArray[42] = 130;
        countArray[43] = 133;
        countArray[44] = 135;
        countArray[45] = 136;
        this.waterCandidates = Lists.newArrayList(countArray);
        Integer[] countArray2 = new Integer[44];
        countArray2[0] = 17;
        countArray2[1] = 18;
        countArray2[2] = 20;
        countArray2[3] = 23;
        countArray2[4] = 28;
        countArray2[5] = 30;
        countArray2[6] = 31;
        countArray2[7] = 35;
        countArray2[8] = 37;
        countArray2[9] = 39;
        countArray2[10] = 41;
        countArray2[11] = 43;
        countArray2[12] = 45;
        countArray2[13] = 50;
        countArray2[14] = 52;
        countArray2[15] = 54;
        countArray2[16] = 56;
        countArray2[17] = 59;
        countArray2[18] = 61;
        countArray2[19] = 63;
        countArray2[20] = 66;
        countArray2[21] = 68;
        countArray2[22] = 71;
        countArray2[23] = 73;
        countArray2[24] = 76;
        countArray2[25] = 78;
        countArray2[26] = 81;
        countArray2[27] = 83;
        countArray2[28] = 86;
        countArray2[29] = 89;
        countArray2[30] = 91;
        countArray2[31] = 94;
        countArray2[32] = 97;
        countArray2[33] = 100;
        countArray2[34] = 103;
        countArray2[35] = 105;
        countArray2[36] = 107;
        countArray2[37] = 108;
        countArray2[38] = 111;
        countArray2[39] = 114;
        countArray2[40] = 117;
        countArray2[41] = 122;
        countArray2[42] = 125;
        countArray2[43] = 128;
        this.itemList3 = Lists.newArrayList(countArray2);
        Integer[] countArray3 = new Integer[9];
        countArray3[0] = 10;
        countArray3[1] = 11;
        countArray3[2] = 13;
        countArray3[3] = 14;
        countArray3[4] = 15;
        countArray3[5] = 22;
        countArray3[6] = 26;
        countArray3[7] = 33;
        countArray3[8] = 47;
        this.itemList4 = Lists.newArrayList(countArray3);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        this.getMLGNothingType();
    }

    private MLGHelperNothingType getMLGNothingType(int index) {
        return (double)index != this.settingMinDistance.getDouble3()
        ? MLGHelperNothingType.BOTH
        : MLGHelperNothingType.IMPOSSIBLE;
    }

    private boolean checkMovingObjectPosition(MovingObjectPosition hitResult) {
        BlockPos blockPos9 = hitResult.getBlockPos().up();
        return minecraft.theWorld.getBlockState(blockPos9).getBlock() instanceof BlockAir;
    }

    private boolean canExecute5() {
        return minecraft.thePlayer.getHeldItem().getItem().equals(Item.processAction(30));
    }

    private int getIndex(MovingObjectPosition hitResult) {
        int y = hitResult.getBlockPos().getY();
        return minecraft.thePlayer.getBlockPos().getY() - y;
    }

    private MLGHelperNothingType getMLGNothingType2() {
        MovingObjectPosition movingObjectPosition = this.getMovingObjectPosition();
        if (movingObjectPosition != null) {
            byte state = ((byte)(this.checkMovingObjectPosition2(movingObjectPosition) ? 1 : 0));
            return this.getMLGNothingType(state);
        } else {
            return MLGHelperNothingType.NOTHING;
        }
    }

    private boolean checkCondition() {
        return minecraft.thePlayer.prevPosY != minecraft.thePlayer.posY;
    }

    private MovingObjectPosition getMovingObjectPosition() {
        return minecraft.thePlayer.getMovingObjectPosition2(256.0, 1.0F);
    }

    private boolean checkMovingObjectPosition2(MovingObjectPosition hitResult) {
        BlockPos blockPos = minecraft.thePlayer.getBlockPos();
        BlockPos blockPos2 = hitResult.getBlockPos();
        int index = Math.abs(blockPos2.getX() - blockPos.getX());
        int index2 = Math.abs(blockPos2.getZ() - blockPos.getZ());
        return (double)index <= this.settingMinDistance.getDouble6()
        && (double)index2 <= this.settingMinDistance.getDouble6();
    }

    public MLGHelperNothingType getMLGNothingType() {
        if (minecraft.thePlayer == null) {
            return MLGHelperNothingType.NOTHING;
        } else if (minecraft.theWorld != null) {
            if (this.checkCondition()) {
                return MLGHelperNothingType.NOTHING;
            } else {
                return !this.canExecute5() ? MLGHelperNothingType.NOTHING : this.getMLGNothingType2();
            }
        } else {
            return MLGHelperNothingType.NOTHING;
        }
    }
}
