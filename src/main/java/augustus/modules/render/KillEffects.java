package augustus.modules.render;

import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventWorldLoad;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.HUIHRVManager;
import client.util.Pair;
import client.util.TimeHelper;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EffectRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class KillEffects extends Module implements WorldLoadListener, AttackEntityListener, TickListener {
    public ModeSetting settingExecutionMode;
    public BooleanSetting settingFlash;
    public BooleanSetting settingDragonDeathSound;
    public BooleanSetting settingFireSound;
    public EntityLivingBase targetEntity;
    public EntityLivingBase targetEntity2;
    public TimeHelper timer;
    public Map entryMap;

    public KillEffects() {
        super("KillEffects", "TötungsEffekte", new Color(168, 255, 176), Category.RENDER);
        String[] textArray = new String[2];
        textArray[0] = "RemovedEntity";
        textArray[1] = "ZeroHealth";
        this.settingExecutionMode = new ModeSetting(this, "ExecutionMode", "RemovedEntity", textArray);
        this.settingFlash = new BooleanSetting(this, "Flash", true);
        this.settingDragonDeathSound = new BooleanSetting(this, "Dragon Death Sound", true);
        this.settingFireSound = new BooleanSetting(this, "Fire Sound", true);
        this.targetEntity = (EntityLivingBase)null;
        this.targetEntity2 = (EntityLivingBase)null;
        this.timer = new TimeHelper();
        this.entryMap = new HashMap();
        AttackEffectsExplosionNormalType[] attackEffectsExplosionNormalTypeArray = AttackEffectsExplosionNormalType.values();
        int length = Array.getLength(attackEffectsExplosionNormalTypeArray);

        for (int i = 0; i < length; i++) {
            Object object = attackEffectsExplosionNormalTypeArray[i];
            StringBuilder sb = new StringBuilder().append("Use ");
            String text = ((AttackEffectsExplosionNormalType)object).name();
            String text5 = this.getString3(text);
            String text2 = sb.append(text5).toString();
            BooleanSetting booleanSetting4 = new BooleanSetting(this, text2, false);
            StringBuilder stringBuilder = new StringBuilder();
            String text3 = ((AttackEffectsExplosionNormalType)object).name();
            String text6 = this.getString3(text3);
            String text4 = stringBuilder.append(text6).append(" Count ").toString();
            NumberSetting numberSetting2 = new NumberSetting(this, text4, 1.0, 1.0, 20.0, 0).getNumberSetting(booleanSetting4::isEnabled);
            Pair pair = new Pair(booleanSetting4, numberSetting2);
            this.entryMap.put(object, pair);
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(1);
        this.registerEvent(40);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.targetEntity = null;
        this.targetEntity2 = null;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        if (event.getEntity() instanceof EntityLivingBase) {
            this.targetEntity = (EntityLivingBase)event.getEntity();
            this.timer.reset();
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.targetEntity2 = null;
        this.targetEntity = null;
    }

    @Override
    public void onRunTick() {
        if (minecraft.thePlayer.getFloat2() <= 0.0F) {
            this.targetEntity = (EntityLivingBase)null;
        }

        if (!this.timer.hasReached(2000L)) {
            if (this.targetEntity != null) {
                if (this.targetEntity != this.targetEntity2) {
                    boolean enabled = minecraft.theWorld.loadedEntityList.contains(this.targetEntity);
                    boolean enabled2 = false;
                    if (!enabled) {
                        enabled2 = this.settingExecutionMode.getSelected().equals("RemovedEntity");
                    }

                    if (!enabled2) {
                        if (this.targetEntity.canExecute()) {
                            return;
                        }

                        if (!this.settingExecutionMode.getSelected().equals("ZeroHealth")) {
                            return;
                        }
                    }

                    if (this.settingFlash.isEnabled()) {
                        HUIHRVManager hUIHRVManager = new HUIHRVManager(minecraft.theWorld, this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ);
                        minecraft.theWorld.checkNoEntityCollision(hUIHRVManager);
                        SoundHandler mcSoundHandler = minecraft.getMcSoundHandler();
                        PositionedSoundRecord positionedSoundRecord = new PositionedSoundRecord(
                            new ResourceLocation("ambient.weather.thunder"),
                            10000.0F,
                            0.8F + new Random().nextFloat() * 0.2F,
                            (float)this.targetEntity.posX,
                            (float)this.targetEntity.posY,
                            (float)this.targetEntity.posZ
                        );
                        mcSoundHandler.applyISound2(positionedSoundRecord);
                        SoundHandler mcSoundHandler2 = minecraft.getMcSoundHandler();
                        PositionedSoundRecord positionedSoundRecord2 = new PositionedSoundRecord(
                            new ResourceLocation("random.explode"),
                            10000.0F,
                            0.5F + new Random().nextFloat() * 0.2F,
                            (float)this.targetEntity.posX,
                            (float)this.targetEntity.posY,
                            (float)this.targetEntity.posZ
                        );
                        mcSoundHandler2.applyISound2(positionedSoundRecord2);
                    }

                    if (this.settingDragonDeathSound.isEnabled()) {
                        BlockPos blockPos = new BlockPos(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ);
                        minecraft.theWorld.applyBlockPos19(1018, blockPos, 0);
                    }

                    if (this.settingFireSound.isEnabled()) {
                        SoundHandler mcSoundHandler3 = minecraft.getMcSoundHandler();
                        PositionedSoundRecord positionedSoundRecord3 = new PositionedSoundRecord(
                            new ResourceLocation("item.fireCharge.use"),
                            10000.0F,
                            0.5F + new Random().nextFloat() * 0.2F,
                            (float)this.targetEntity.posX,
                            (float)this.targetEntity.posY,
                            (float)this.targetEntity.posZ
                        );
                        mcSoundHandler3.applyISound2(positionedSoundRecord3);
                    }

                    Iterator iterator = this.entryMap.entrySet().iterator();

                    while (iterator.hasNext()) {
                        Entry entry = (Entry)iterator.next();
                        if (((BooleanSetting)((Pair)entry.getValue()).getSecond()).isEnabled()) {
                            try {
                                int index = 0;

                                while (true) {
                                    NumberSetting setting = (NumberSetting)((Pair)entry.getValue()).getFirst();
                                    if (!((double)index < setting.getValue())) {
                                        break;
                                    }

                                    EffectRenderer effectRenderer = minecraft.effectRenderer;
                                    EntityLivingBase targetEntity3 = this.targetEntity;
                                    AttackEffectsExplosionNormalType attackEffectsExplosionNormalTypeKind = (AttackEffectsExplosionNormalType)entry.getKey();
                                    effectRenderer.applyEntity3(targetEntity3, attackEffectsExplosionNormalTypeKind);
                                    index++;
                                }
                            } catch (Exception e) {
                            }
                        }
                    }

                    this.targetEntity2 = this.targetEntity;
                    this.targetEntity = (EntityLivingBase)null;
                }
            }
        }
    }

    private String getString3(String text) {
        String text2 = text.replace("_", " ");
        StringBuilder stringBuilder = new StringBuilder();
        char[] cArray = text2.toCharArray();

        for (int i = 0; i < cArray.length; i++) {
            char character = cArray[i];
            if (i != 0 && cArray[i - 1] != ' ') {
                stringBuilder.append(String.valueOf(character).toLowerCase());
            } else {
                stringBuilder.append(character);
            }
        }

        return stringBuilder.toString();
    }
}
