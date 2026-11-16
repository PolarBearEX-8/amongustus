package augustus.modules.fun;

import augustus.modes.fun.MlgDelayedTask;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventJump;
import client.event.EventJumpCallback;
import client.event.EventTimeUpdate;
import client.event.EventTimeUpdateCallback;
import client.event.EventWorldLoad;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.SettingContainer;
import client.util.AudioClipPlayer;
import client.util.FileManager;
import client.util.MlgBlockCollector;
import client.util.PicturesConfigPngManager;
import client.util.RandomUtil;
import client.util.SoundPlayer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class MLG extends Module implements WorldLoadListener, EventJumpCallback, EventTimeUpdateCallback, RenderListener, AttackEntityListener, TickListener {
    public long timestampMillis;
    public int mlgTrackIndex = 1;
    public AudioClipPlayer audioClipEntry;
    public int tickDelay;
    public EntityLivingBase targetEntity;
    public long timestampMillis1;
    public List itemList;
    public ArrayList activeOverlays;
    public ArrayList itemList3;
    public CopyOnWriteArrayList copyOnWriteArrayList;
    public static String[] stringArray;

    public MLG() {
        super("MLG", "MLG", new Color(50, 168, 82), Category.FUN);
        this.audioClipEntry = new AudioClipPlayer();
        this.tickDelay = 10;
        this.itemList = new ArrayList();
        this.activeOverlays = new ArrayList();
        this.itemList3 = new ArrayList();
        CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
        this.copyOnWriteArrayList = copyOnWriteArrayList2;
        Iterator iterator = FileManager.getArrayList("sounds/mlg/randomsounds").iterator();

        while (iterator.hasNext()) {
            String text = (String)iterator.next();
            if (text.length() >= 4) {
                StringBuilder sb = new StringBuilder().append("sounds/mlg/randomsounds/").append(text);
                ResourceLocation resourceLocation = new ResourceLocation("augustus", sb.toString());
                SoundHandler mcSoundHandler = Minecraft.getMinecraft().getMcSoundHandler();
                StringBuilder sb2 = new StringBuilder().append("mlg/randomsounds/");
                String text2 = text.substring(0, text.length() - 4);
                StringBuilder sb3 = sb2.append(text2);
                mcSoundHandler.applyResourceLocation2(resourceLocation, sb3.toString());
                this.itemList.add(resourceLocation);
            }
        }

        Iterator iterator2 = FileManager.getArrayList("gifs/mlg/").iterator();

        while (iterator2.hasNext()) {
            String text3 = (String)iterator2.next();
            ArrayList settings22 = this.activeOverlays;
            StringBuilder sb4 = new StringBuilder().append("gifs/mlg/").append(text3);
            MlgBlockCollector arrayListEntry = new MlgBlockCollector(sb4.toString(), 60);
            settings22.add(arrayListEntry);
        }

        this.setState4(false);
    }

    @Override
    public void onEnable() {
        this.registerEvent(44, 100);
        this.registerEvent(45, 100);
        this.registerEvent(32, 100);
        this.registerEvent(1);
        this.registerEvent(40);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.audioClipEntry.processChar();
        this.targetEntity = null;
        this.copyOnWriteArrayList.clear();
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
        this.processShort();
    }

    @Override
    public void onJump(EventJump event) {
        int int6 = RandomUtil.getInt6(0, 1000, 1.0F, 1.0F);
        event.processAction2((float)RandomUtil.processAction9(int6));
        event.processAction((float)RandomUtil.processAction6(int6));
        event.processAction3((float)RandomUtil.processAction(int6));
    }

    @Override
    public void onTimeUpdate(EventTimeUpdate event) {
        event.updateState(this.timestampMillis = this.timestampMillis + (long)SettingContainer.settingProvider.getInt2(50, 100));
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        this.tickDelay++;
        this.timestampMillis1 = System.currentTimeMillis();
        if (event.getEntity() instanceof EntityLivingBase) {
            this.targetEntity = (EntityLivingBase)event.getEntity();
        }

        if (this.tickDelay > 4) {
            this.updateState2();
            this.updateState();
            this.tickDelay = 0;
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        Iterator iterator = this.copyOnWriteArrayList.iterator();

        while (iterator.hasNext()) {
            ((MlgDelayedTask)iterator.next()).processAction();
        }

        RenderUtils.processAction();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.targetEntity = null;
    }

    @Override
    public void onJump2(EventJump event) {
        if (!this.audioClipEntry.checkCondition()) {
            this.processShort();
        }

        int int6 = RandomUtil.getInt6(0, 1000, 1.0F, 1.0F);
        event.processAction2((float)RandomUtil.processAction9(int6));
        event.processAction((float)RandomUtil.processAction6(int6));
        event.processAction3((float)RandomUtil.processAction(int6));
    }

    @Override
    public void onRunTick() {
        if (this.targetEntity == null || System.currentTimeMillis() >= this.timestampMillis1 - 5000L) {
            this.targetEntity = null;
        } else if (this.targetEntity.hurtTime == 10) {
            SoundPlayer.processAction(SoundPlayer.resourceLocation5);
        }
    }

    public static String[] getStringArray() {
        return stringArray;
    }

    private static void updateArrayListEntry(double offset, MlgBlockCollector arrayListEntry, int x, int x2, int y, int z) {
        GL11.glPushMatrix();
        GL11.glScaled(offset, offset, 1.0);
        ResourceLocation resourceLocation = arrayListEntry.getResourceLocation();
        PicturesConfigPngManager.applyResourceLocation(resourceLocation, x, x2, y, z, 1.0);
        GL11.glPopMatrix();
    }

    public static void setStringArray(String[] textArray) {
        stringArray = textArray;
    }

    private void updateState() {
        if (this.itemList3.isEmpty()) {
            this.itemList3.addAll(this.activeOverlays);
        }

        int index = 0;
        double offset = (double)(1.0F / (float)new ScaledResolution(minecraft).getScaleFactor());
        int int2 = SettingContainer.settingProvider.getInt2(0, this.itemList3.size() - 1);
        MlgBlockCollector arrayListEntry = (MlgBlockCollector)this.itemList3.get(int2);
        this.itemList3.remove(int2);
        double double4 = SettingContainer.settingProvider.getDouble4(0.7, 1.2);
        int index2 = !arrayListEntry.getText().contains("sonic") && !arrayListEntry.getText().contains("yolo")
        ? SettingContainer.settingProvider.getInt2(5000, 7000)
        : 2;

        while (index < index2) {
            index += arrayListEntry.getInt();
        }

        int index3 = (int)((double)arrayListEntry.getIndex() * double4);
        int index4 = (int)((double)arrayListEntry.getIndex2() * double4);
        int int22 = SettingContainer.settingProvider.getInt2(0, minecraft.getFramebuffer().indexOffset2 - index4);
        int index5 = (int)SettingContainer.settingProvider.getLong(0L, 120000L) % 2 == 0
        ? (int)((double)minecraft.getFramebuffer().indexOffset1 - (double)arrayListEntry.getIndex() * double4)
        : 0;
        if (!arrayListEntry.getText().contains("sonic")) {
            this.copyOnWriteArrayList
            .add(new MlgDelayedTask(this, System.currentTimeMillis(), index, () -> updateArrayListEntry2(offset, arrayListEntry, int22, index5, index4, index3)));
        } else {
            CopyOnWriteArrayList copyOnWriteArrayList2 = this.copyOnWriteArrayList;
            MlgDelayedTask mlgDelayedTask = new MlgDelayedTask(
            this, System.currentTimeMillis(), index, () -> updateArrayListEntry(offset, arrayListEntry, int22, index5, index4, index3)
            );
            copyOnWriteArrayList2.add(mlgDelayedTask);
        }
    }

    private static void updateArrayListEntry2(double offset, MlgBlockCollector arrayListEntry, int x, int x2, int y, int z) {
        GL11.glPushMatrix();
        GL11.glScaled(offset, offset, 1.0);
        ResourceLocation resourceLocation = arrayListEntry.getResourceLocation();
        PicturesConfigPngManager.applyResourceLocation(resourceLocation, x, x2, y, z, 1.0);
        GL11.glPopMatrix();
    }

    private void updateState2() {
        int int2 = SettingContainer.settingProvider.getInt2(0, this.itemList.size() - 1);
        SoundPlayer.processAction((ResourceLocation)this.itemList.get(int2));
    }

    public static CopyOnWriteArrayList getCopyOnWriteArrayList(MLG mLG) {
        return mLG.copyOnWriteArrayList;
    }

    private void processShort() {
        if (this.mlgTrackIndex > 3) {
            this.mlgTrackIndex = 1;
        }

        MLG mLG;
        mLG = this;

        try {
            mLG.audioClipEntry.applyString2("sounds/mlg/music/MLG" + this.mlgTrackIndex + ".mp3", true);
        } catch (Exception e2) {
        }

        this.mlgTrackIndex++;
    }
}
