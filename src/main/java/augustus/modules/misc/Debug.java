package augustus.modules.misc;

import augustus.custom.RuntimeBridge;
import client.Client;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.EventSafeWalk;
import client.event.EventSendPacket;
import client.event.EventTargetEntity;
import client.event.EventTargetEntityCallback;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.SafeWalkListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.CustomDataPacket;
import client.network.LatencyPacket;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.AnimationTimer;
import client.util.ArrayListData;
import client.util.BooleanSupplier;
import client.util.ClickHelper;
import client.util.CombatTargetFilter;
import client.util.EnchantmentManager;
import client.util.LvlManager;
import client.util.OnlineConfigsManager;
import client.util.Packet;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import client.util.RayTraceUtils;
import client.util.TimeHelper;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class Debug
extends Module
implements AttackEntityListener,
PacketListener,
TickListener,
RenderListener,
SafeWalkListener,
MoveListener,
NetworkPacketListener,
EventTargetEntityCallback {
    public BooleanSetting settingRangeDebug;
    public BooleanSetting settingIgnoreBackTrack;
    public NumberSetting settingMinRange;
    public NumberSetting settingDecimalPlaces;
    public BooleanSetting settingTimerRangeDebug;
    public BooleanSetting settingLagRangeDebug;
    public BooleanSetting settingTimeManipulation;
    public BooleanSetting settingHitBug;
    public BooleanSetting settingServersideChat;
    public BooleanSetting settingSoundName;
    public BooleanSetting settingBlink;
    public BooleanSetting settingTransaction;
    public BooleanSetting settingOnlyDebugChat;
    public BooleanSetting settingKit1V1Damage;
    public BooleanSetting settingAutoClickerDebug;
    public BooleanSetting settingPlaceDebug;
    public BooleanSetting settingSneakDebug;
    public BooleanSetting settingLostHitDebug;
    public BooleanSetting settingMotionDebug;
    public BooleanSetting settingDecimalPlaces2;
    public BooleanSetting booleanSetting;
    public BooleanSetting booleanSetting2;
    public BooleanSetting booleanSetting3;
    public BooleanSetting booleanSetting4;
    public BooleanSetting booleanSetting5;
    public BooleanSetting booleanSetting6;
    public BooleanSetting booleanSetting7;
    public BooleanSetting booleanSetting8;
    public BooleanSetting settingDoubleClickFirstHit;
    public BooleanSetting booleanSetting9;
    public BooleanSetting booleanSetting10;
    public BooleanSetting booleanSetting11;
    public BooleanSetting booleanSetting12;
    public BooleanSetting booleanSetting13;
    public BooleanSetting booleanSetting14;
    public ModeSetting modeSetting;
    public BooleanSetting booleanSetting15;
    public BooleanSetting booleanSetting16;
    public BooleanSetting booleanSetting17;
    public double reachDistance5;
    public TimeHelper timer;
    public CopyOnWriteArrayList copyOnWriteArrayList;
    public ArrayList itemList3;
    public ArrayList itemList7;
    public int tickDelay8;
    public int tickDelay18;
    public int tickDelay14;
    public int tickDelay26;
    public int tickDelay2;
    public int tickDelay10;
    public TimeHelper timer2;
    public ArrayList itemList4;
    public float motionMultiplier17;
    public float motionMultiplier21;
    public EntityPlayer targetPlayer;
    public EntityPlayer targetPlayer2;
    public TimeHelper timer3;
    public ArrayList debugEntries;
    public ArrayList itemList5;
    public TimeHelper timer4;
    public AnimationTimer animationTimer;
    public int tickDelay9;
    public int tickDelay16;
    public EntityLivingBase targetEntity;
    public TimeHelper timer5;
    public int tickDelay12;
    public int tickDelay6;
    public LinkedList linkedList;
    public LinkedList linkedList2;
    public long timestampMillis7;
    public ArrayList itemList6;
    public EntityLivingBase targetEntity2;
    public TimeHelper timer6;
    public EntityPlayer targetPlayer3;
    public int tickDelay20;
    public int tickDelay19;
    public ClickHelper clickHelper;
    public int tickDelay27;
    public int tickDelay22;
    public double reachDistance;
    public float motionMultiplier;
    public float motionMultiplier24;
    public long timestampMillis;
    public long timestampMillis15;
    public int tickDelay1;
    public ArrayListData tertiaryArrayListEntry23;
    public ArrayListData tertiaryArrayListEntry;
    public ArrayListData tertiaryArrayListEntry11;
    public boolean activeState;
    public int tickDelay3;
    public ClickHelper clickHelper2;
    public ClickHelper clickHelper3;
    public ClickHelper clickHelper4;
    public ClickHelper clickHelper5;
    public ClickHelper clickHelper6;
    public ClickHelper clickHelper7;
    public ClickHelper clickHelper8;
    public int tickDelay25;
    public boolean expanded2;
    public int tickDelay13;
    public double reachDistance4;
    public static int tickDelay;

    public Debug() {
        super("Debug", "Fehlersuche", Color.BLACK, Category.MISC);
        this.settingRangeDebug = new BooleanSetting(this, "RangeDebug", false);
        Setting setting = new BooleanSetting(this, "IgnoreBackTrack", false).getSetting(() -> this.settingRangeDebug.isEnabled());
        this.settingIgnoreBackTrack = (BooleanSetting)setting;
        this.settingMinRange = new NumberSetting(this, "MinRange", 3.0, 0.0, 6.0, 2).getNumberSetting(() -> this.settingRangeDebug.isEnabled());
        this.settingDecimalPlaces = new NumberSetting(this, "DecimalPlaces", 4.0, 1.0, 10.0, 0).getNumberSetting(() -> this.settingRangeDebug.isEnabled());
        this.settingTimerRangeDebug = new BooleanSetting(this, "TimerRangeDebug", false);
        this.settingLagRangeDebug = new BooleanSetting(this, "LagRangeDebug", false);
        this.settingTimeManipulation = new BooleanSetting(this, "TimeManipulation", false);
        this.settingHitBug = new BooleanSetting(this, "HitBug", false);
        this.settingServersideChat = new BooleanSetting(this, "ServersideChat", false);
        this.settingSoundName = new BooleanSetting(this, "SoundName", false);
        this.settingBlink = new BooleanSetting(this, "Blink", false);
        this.settingTransaction = new BooleanSetting(this, "Transaction", false);
        this.settingOnlyDebugChat = new BooleanSetting(this, "OnlyDebugChat", false);
        this.settingKit1V1Damage = new BooleanSetting(this, "Kit1V1Damage", false);
        this.settingAutoClickerDebug = new BooleanSetting(this, "AutoClickerDebug", false);
        this.settingPlaceDebug = new BooleanSetting(this, "PlaceDebug", false);
        this.settingSneakDebug = new BooleanSetting(this, "SneakDebug", false);
        this.settingLostHitDebug = new BooleanSetting(this, "LostHitDebug", false);
        this.settingMotionDebug = new BooleanSetting(this, "Motion Debug", false);
        this.settingDecimalPlaces2 = (BooleanSetting)null;
        this.booleanSetting = (BooleanSetting)null;
        this.booleanSetting2 = (BooleanSetting)null;
        this.booleanSetting3 = (BooleanSetting)null;
        this.booleanSetting4 = (BooleanSetting)null;
        this.booleanSetting5 = (BooleanSetting)null;
        this.booleanSetting6 = (BooleanSetting)null;
        this.booleanSetting7 = (BooleanSetting)null;
        this.booleanSetting8 = (BooleanSetting)null;
        this.settingDoubleClickFirstHit = (BooleanSetting)null;
        this.booleanSetting9 = (BooleanSetting)null;
        this.booleanSetting10 = (BooleanSetting)null;
        this.booleanSetting11 = (BooleanSetting)null;
        this.booleanSetting12 = (BooleanSetting)null;
        this.booleanSetting13 = (BooleanSetting)null;
        this.booleanSetting14 = (BooleanSetting)null;
        this.modeSetting = (ModeSetting)null;
        this.booleanSetting15 = (BooleanSetting)null;
        this.booleanSetting16 = (BooleanSetting)null;
        this.booleanSetting17 = (BooleanSetting)null;
        this.reachDistance5 = Double.NaN;
        this.timer = new TimeHelper();
        this.copyOnWriteArrayList = new CopyOnWriteArrayList();
        ArrayList arrayList = new ArrayList();
        this.itemList3 = arrayList;
        this.itemList7 = new ArrayList();
        this.tickDelay8 = 0;
        this.tickDelay18 = 0;
        this.tickDelay14 = 0;
        this.tickDelay26 = 0;
        this.tickDelay2 = 0;
        this.tickDelay10 = 0;
        this.timer2 = new TimeHelper();
        this.itemList4 = new ArrayList();
        this.timer3 = new TimeHelper();
        this.debugEntries = new ArrayList();
        this.itemList5 = new ArrayList();
        this.timer4 = new TimeHelper();
        this.animationTimer = new AnimationTimer();
        this.tickDelay9 = 0;
        this.tickDelay16 = 0;
        this.targetEntity = (EntityLivingBase)null;
        this.timer5 = new TimeHelper();
        this.tickDelay12 = -1;
        this.tickDelay6 = 0;
        this.linkedList = new LinkedList();
        this.linkedList2 = new LinkedList();
        this.timestampMillis7 = 0L;
        this.itemList6 = new ArrayList();
        this.targetEntity2 = (EntityLivingBase)null;
        this.timer6 = new TimeHelper();
        this.targetPlayer3 = (EntityPlayer)null;
        this.tickDelay20 = 0;
        this.tickDelay19 = 0;
        ClickHelper clickHelper9 = new ClickHelper(20);
        this.clickHelper = clickHelper9;
        this.tickDelay27 = 0;
        this.tickDelay22 = 0;
        this.reachDistance = 0.0;
        this.motionMultiplier24 = 0.0F;
        this.timestampMillis = 0L;
        this.timestampMillis15 = 0L;
        this.tickDelay1 = 0;
        this.tertiaryArrayListEntry23 = new ArrayListData(100);
        this.tertiaryArrayListEntry = new ArrayListData(100);
        this.tertiaryArrayListEntry11 = new ArrayListData(100);
        this.clickHelper2 = new ClickHelper(20);
        this.clickHelper3 = new ClickHelper(20);
        this.clickHelper4 = new ClickHelper(20);
        this.clickHelper5 = new ClickHelper(20);
        this.clickHelper6 = new ClickHelper(20);
        this.clickHelper7 = new ClickHelper(20);
        this.clickHelper8 = new ClickHelper(20);
        this.tickDelay25 = 0;
        this.expanded2 = false;
        this.tickDelay13 = 0;
        this.reachDistance4 = 0.0;
        Client.getClient();
    }

    @Override
    public void onEnable() {
        this.registerEvent(1);
        this.registerEvent(26);
        this.registerEvent(40);
        this.registerEvent(32);
        this.registerEvent(35);
        this.registerEvent(21, 2000);
        this.registerEvent(25);
        this.registerEvent(50);
    }

    @Override
    public void onDisable() {
        this.targetEntity = null;
        this.targetEntity2 = null;
        this.targetPlayer = null;
        this.targetPlayer2 = null;
    }

    @Override
    public void onPreDisable() {
        this.tickDelay26 = 0;
        this.tickDelay18 = 0;
        this.tickDelay14 = 0;
        this.tickDelay2 = 0;
        this.motionMultiplier17 = 0.0F;
        this.motionMultiplier21 = 0.0F;
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        if (!this.timer4.hasReached(500L)) {
            packet.setCancelled(true);
        }
    }

    @Override
    public void onPacketReceive(EventPacket packet) {
    }

    @Override
    public void onTargetEntity(EventTargetEntity event) {
        Client.getClient().getClass();
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (this.settingAutoClickerDebug.isEnabled()) {
            if (this.timer2.hasReached(500L)) {
                this.itemList4.clear();
            }

            if (packet2 instanceof C03PacketPlayer && !this.timer2.hasReached(250L)) {
                this.itemList4.add(this.tickDelay10);

                while (this.itemList4.size() > 20) {
                    this.itemList4.remove(0);
                }

                if (this.itemList4.size() > 19) {
                    StringBuilder sb = new StringBuilder().append("Clicks: ");
                    int tickDelay102 = this.tickDelay10;
                    StringBuilder sb2 = sb.append(this.getString3(tickDelay102)).append(this.tickDelay10).append("§r CPS: ");
                    ArrayList itemList42 = this.itemList4;
                    String text = sb2.append(RuntimeBridge.formatNumberString(PlayerUtil.getCoordinate(itemList42) * 20.0, 0)).toString();
                    RayTraceUtil.applyString(text);
                } else {
                    StringBuilder sb3 = new StringBuilder().append("Clicks: ");
                    int tickDelay103 = this.tickDelay10;
                    String text2 = sb3.append(this.getString3(tickDelay103)).append(this.tickDelay10).toString();
                    RayTraceUtil.applyString(text2);
                }

                this.tickDelay10 = 0;
            } else if (packet2 instanceof C0APacketAnimation) {
                if (!minecraft.playerController.state) {
                    this.timer2.reset();
                    this.tickDelay10++;
                }
            }
        }

        if (packet2 instanceof C0BPacketEntityAction && this.booleanSetting14 != null && this.booleanSetting14.isEnabled() && this.targetEntity != null) {
            EntityPlayerAction entityPlayerAction = ((C0BPacketEntityAction)packet2).getEntityPlayerAction();
            if (entityPlayerAction == EntityPlayerAction.START_SPRINTING && this.tickDelay12 >= 0) {
                this.linkedList.add(this.tickDelay6);
                this.linkedList2.add(this.tickDelay12);
                this.tickDelay12 = -1;
            }

            EntityPlayerAction entityPlayerAction2 = EntityPlayerAction.STOP_SPRINTING;
            if (entityPlayerAction == entityPlayerAction2) {
                if (!minecraft.thePlayer.isCollidedHorizontally) {
                    this.tickDelay12 = 0;
                }
            }
        }

        if (packet2 instanceof C08PacketPlayerBlockPlacement) {
            if (this.settingPlaceDebug.isEnabled()) {
                if (this.clickHelper.canExecute()) {
                    StringBuilder sb4 = new StringBuilder().append("Delay: ").append(this.tickDelay20).append("  AVG: ");
                    ClickHelper clickHelper9 = this.clickHelper;
                    String text3 = sb4.append(clickHelper9.getDouble11() * 20.0).toString();
                    RayTraceUtil.applyString(text3);
                } else {
                    String text4 = "Delay: " + this.tickDelay20;
                    RayTraceUtil.applyString(text4);
                }

                this.tickDelay20 = 0;
                this.tickDelay19++;
            }
        }
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        if (event.getEntity() instanceof EntityLivingBase) {
            if (this.targetEntity != event.getEntity()) {
                this.tickDelay25 = -1;
            }

            this.targetEntity = (EntityLivingBase)event.getEntity();
            this.timer5.reset();
        }

        if (event.getEntity() instanceof EntityLivingBase) {
            this.targetEntity2 = (EntityLivingBase)event.getEntity();
            this.timer6.reset();
        }

        Client.getClient().getClass();
        if (this.settingRangeDebug.isEnabled()) {
            double offset;
            if (this.settingIgnoreBackTrack.isEnabled()) {
                Vec3 vec = minecraft.thePlayer.getVec35(1.0F);
                offset = vec.getCoordinate4(minecraft.objectMouseOver.hitVec);
            } else {
                MovingObjectPosition movingObjectPosition4 = RayTraceUtils.getMovingObjectPosition4();
                offset = minecraft.thePlayer.getVec35(1.0F).getCoordinate4(movingObjectPosition4.hitVec);
            }

            if (offset >= this.settingMinRange.getValue()) {
                StringBuilder sb = new StringBuilder().append("Attack distance: ");
                int decimalPlaces = (int)this.settingDecimalPlaces.getValue();
                String text = sb.append(PlayerUtil.getString(offset, decimalPlaces)).toString();
                this.applyString6(text);
            }
        }

        Client.getClient().getClass();
        if (minecraft.objectMouseOver != null) {
            if (minecraft.objectMouseOver.entityHit instanceof EntityPlayer) {
                this.targetPlayer2 = (EntityPlayer)minecraft.objectMouseOver.entityHit;
                this.timer3.reset();
            }
        }
    }

    @Override
    public void onPacketPostReceive(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        BooleanSetting setting;
        if (this.booleanSetting3 != null) {
            setting = this.booleanSetting3;
        } else {
            setting = this.booleanSetting5;
        }

        BooleanSetting setting2;
        if (setting != null) {
            setting2 = this.booleanSetting5;
        } else {
            setting2 = this.booleanSetting4;
        }

        int index8 = 0;
        boolean completed = false;
        if (setting2 != null && this.booleanSetting4.isEnabled()) {
            int index9 = 0;
            if (packet2 instanceof S12PacketEntityVelocity && minecraft.thePlayer != null) {
                index9 = ((S12PacketEntityVelocity)packet2).getEntityId();
            } else {
                index9 = (packet2 instanceof S27PacketExplosion ? 1 : 0);
            }
            if (index9 != 0 && minecraft.thePlayer != null) {
                float value = Math.abs(((S27PacketExplosion)packet2).getScaleFactor2());
                index8 = !Float.isNaN(value) && !Float.isNaN(0.0F) ? (value > 0.0F ? 1 : (value == 0.0F ? 0 : -1)) : -1;
                completed = true;
            }
        }
        if (!completed) {
            index8 = (packet2 instanceof S13PacketDestroyEntities ? 1 : 0);
        }


        boolean enabled = false;
        boolean completed2 = false;
        if (index8 != 0 && minecraft.theWorld != null) {
            S0BPacketAnimation s0BPacketAnimation = (S0BPacketAnimation)packet2;
            if (s0BPacketAnimation.getIndex2() == 1) {
                int index = s0BPacketAnimation.getIndex();
                enabled = minecraft.theWorld.getEntityByID(index) instanceof EntityLivingBase;
                completed2 = true;
            }
        }
        if (!completed2) {
            enabled = packet2 instanceof S19PacketEntityStatus;
        }


        boolean enabled2 = false;
        boolean completed3 = false;
        if (enabled && minecraft.theWorld != null) {
            S19PacketEntityStatus s19PacketEntityStatus = (S19PacketEntityStatus)packet2;
            if (s19PacketEntityStatus.processValue() == 2) {
                int index2 = s19PacketEntityStatus.getIndex();
                enabled2 = minecraft.theWorld.getEntityByID(index2) instanceof EntityLivingBase;
                completed3 = true;
            }
        }
        if (!completed3) {
            enabled2 = packet2 instanceof S32PacketConfirmTransaction;
        }


        boolean enabled3 = false;
        boolean completed4 = false;
        if (enabled2 && minecraft.theWorld != null) {
            S19PacketEntityStatus s19PacketEntityStatus2 = (S19PacketEntityStatus)packet2;
            if (s19PacketEntityStatus2.processValue() == 2) {
                int index3 = s19PacketEntityStatus2.getIndex();
                Entity entityByID = minecraft.theWorld.getEntityByID(index3);
                if (entityByID != null && this.targetEntity2 != null) {
                    int entityId = entityByID.getEntityId();
                    int entityId2 = this.targetEntity2.getEntityId();
                    if (entityId == entityId2 && this.targetEntity2.hurtTime == 0) {
                        long time2 = System.currentTimeMillis();
                        long epochMillis = this.targetEntity2.epochMillis;
                        long time3 = time2 - epochMillis;
                        if ((time3 > 400L ? 1 : (time3 == 400L ? 0 : -1)) < 0) {
                            enabled3 = this.itemList6.isEmpty();
                            completed4 = true;
                        }
                    }
                }
            }
        }
        if (!completed4) {
            enabled3 = packet2 instanceof S0BPacketAnimation;
        }


        boolean enabled4 = false;
        boolean completed5 = false;
        if (enabled3 && minecraft.theWorld != null) {
            S0BPacketAnimation s0BPacketAnimation2 = (S0BPacketAnimation)packet2;
            if (s0BPacketAnimation2.getIndex2() == 1) {
                int index4 = s0BPacketAnimation2.getIndex();
                if (minecraft.theWorld.getEntityByID(index4) != null) {
                    int entityId3 = this.targetEntity2.getEntityId();
                    int entityId4 = this.targetEntity2.getEntityId();
                    if (entityId3 == entityId4 && this.targetEntity2.hurtTime == 0) {
                        long time4 = System.currentTimeMillis();
                        long epochMillis2 = this.targetEntity2.epochMillis;
                        long time5 = time4 - epochMillis2;
                        if ((time5 > 400L ? 1 : (time5 == 400L ? 0 : -1)) < 0) {
                            enabled4 = this.itemList6.isEmpty();
                            completed5 = true;
                        }
                    }
                }
            }
        }
        if (!completed5) {
            enabled4 = this.settingTransaction.isEnabled();
        }


        boolean enabled5;
        if (enabled4) {
            enabled5 = packet2 instanceof S32PacketConfirmTransaction;
        } else {
            Client.getClient().getClass();
            enabled5 = packet2 instanceof S07PacketRespawn;
        }

        boolean enabled6 = false;
        boolean completed6 = false;
        if (enabled5) {
            this.itemList7.clear();
            this.itemList3.clear();
            this.tickDelay8 = 0;
            if (this.settingDoubleClickFirstHit != null) {
                enabled6 = this.settingDoubleClickFirstHit.isEnabled();
                completed6 = true;
            }
        }
        if (!completed6) {
            enabled6 = packet2 instanceof S0BPacketAnimation;
        }


        if (enabled6) {
            S0BPacketAnimation s0BPacketAnimation3 = (S0BPacketAnimation)packet2;
            if (s0BPacketAnimation3.getIndex2() == 4 && minecraft.thePlayer != null) {
                int index5 = s0BPacketAnimation3.getIndex();
                int entityId5 = minecraft.thePlayer.getEntityId();
                if (index5 == entityId5) {
                    this.tickDelay2 = this.tickDelay2 + 1;
                }
            }

            if (s0BPacketAnimation3.getIndex2() == 4 && this.targetPlayer3 != null) {
                int entityId6 = this.targetPlayer3.getEntityId();
                int index6 = s0BPacketAnimation3.getIndex();
                if (entityId6 == index6) {
                    this.tickDelay14 = this.tickDelay14 + 1;
                }
            }
        }

        if (packet2 instanceof S06PacketUpdateHealth && this.booleanSetting6 != null && this.booleanSetting6.isEnabled()) {
            S06PacketUpdateHealth s06PacketUpdateHealth = (S06PacketUpdateHealth)packet2;
            double reachDistance42 = this.reachDistance4;
            double scaleFactor11 = (double)s06PacketUpdateHealth.getScaleFactor();
            double offset = reachDistance42 - scaleFactor11;
            float value2 = 1.0F;
            float value3 = 0.0F;
            EntityPlayer targetPlayer32 = this.targetPlayer3;
            if (targetPlayer32 != null && (!Double.isNaN(offset) && !Double.isNaN(0.0) ? (offset > 0.0 ? 1 : (offset == 0.0 ? 0 : -1)) : -1) > 0) {
                ItemStack itemStack = targetPlayer32.getItemStack();
                if (itemStack != null) {
                    Item item = itemStack.getItem();
                    if (item instanceof ItemSword) {
                        ItemSword itemSword = (ItemSword)item;
                        double offset2 = (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, itemStack);
                        float scaleFactor = itemSword.getScaleFactor();
                        value2 += scaleFactor;
                        value3 = (float)(offset2 * 1.25);
                        if (item.processAction2(itemStack)
                        && (!Double.isNaN(offset2) && !Double.isNaN(0.0) ? (offset2 > 0.0 ? 1 : (offset2 == 0.0 ? 0 : -1)) : -1) == 0
                        && minecraft.getServerData() != null
                        && minecraft.getServerData().text.equals("gommehd.net")) {
                            ItemStack itemStack2 = itemStack;
                            value3 = this.getScaleFactor(itemStack2);
                        }
                    } else if (item instanceof ItemTool) {
                        ItemTool internalItemAdapter = (ItemTool)item;
                        LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, itemStack);
                        float scaleFactor2 = internalItemAdapter.getScaleFactor();
                        value2 += scaleFactor2;
                        if (!item.processAction2(itemStack) && minecraft.getServerData() != null && minecraft.getServerData().text.equals("gommehd.net")) {
                            ItemStack itemStack3 = itemStack;
                            value3 = this.getScaleFactor(itemStack3);
                        }
                    }
                }

                DamageSource damageSource14 = DamageSource.damageSource14;
                float value4 = value2 + value3;
                DamageSource damageSource = damageSource14;
                EntityPlayer player = targetPlayer32;
                float scaleFactor3 = this.getScaleFactor(damageSource, value4, player, true, false);
                DamageSource damageSource142 = DamageSource.damageSource14;
                float value5 = value2 * 1.5F + value3;
                DamageSource damageSource2 = damageSource142;
                EntityPlayer player2 = targetPlayer32;
                float scaleFactor4 = this.getScaleFactor(damageSource2, value5, player2, true, false);
                DamageSource damageSource143 = DamageSource.damageSource14;
                float value6 = value2 * 0.5F;
                DamageSource damageSource3 = damageSource143;
                EntityPlayer player3 = targetPlayer32;
                float scaleFactor5 = this.getScaleFactor(damageSource3, value6, player3, true, false);
                DamageSource damageSource144 = DamageSource.damageSource14;
                float value7 = value2 + value3;
                DamageSource damageSource4 = damageSource144;
                EntityPlayer player4 = targetPlayer32;
                float scaleFactor6 = this.getScaleFactor(damageSource4, value7, player4, false, false);
                DamageSource damageSource145 = DamageSource.damageSource14;
                float value8 = value2 * 1.5F + value3;
                DamageSource damageSource5 = damageSource145;
                EntityPlayer player5 = targetPlayer32;
                float scaleFactor7 = this.getScaleFactor(damageSource5, value8, player5, false, false);
                DamageSource damageSource146 = DamageSource.damageSource14;
                float value9 = value2 * 0.5F;
                DamageSource damageSource6 = damageSource146;
                EntityPlayer player6 = targetPlayer32;
                float scaleFactor8 = this.getScaleFactor(damageSource6, value9, player6, false, false);
                if (this.booleanSetting8.isEnabled()) {
                    if (this.booleanSetting7.isEnabled()) {
                        float value10 = (float)offset;
                        float[] factorArray = new float[]{scaleFactor6, scaleFactor7};
                        double scaleFactor12 = (double)this.getScaleFactor(value10, factorArray);
                        double offset3 = (double)Math.abs(scaleFactor6 - scaleFactor3);
                        if (!this.checkCondition(offset, scaleFactor12, offset3)) {
                            if (this.tickDelay8 > 8) {
                                this.applyString6("Your enemy makes reduced Damage (unsafe) (single)!");
                            }

                            this.tickDelay8 = this.tickDelay8 + 2;
                        }
                    }
                } else {
                    double offset4 = (double)scaleFactor3;
                    boolean enabled7 = this.checkCondition(offset, offset4, 0.01F);
                    double offset5 = (double)scaleFactor3;
                    if (!this.checkCondition(offset, offset5, 0.01F)) {
                        double offset6 = (double)scaleFactor4;
                        if (!this.checkCondition(offset, offset6, 0.01F)) {
                            double offset7 = (double)scaleFactor5;
                            if (!this.checkCondition(offset, offset7, 0.01F)) {
                                String text = new StringBuilder()
                                .append("Your enemy makes reduced Damage (single)! ")
                                .append(offset)
                                .append(" ")
                                .append(scaleFactor6)
                                .append(" ")
                                .append(scaleFactor4)
                                .toString();
                                this.applyString6(text);
                            }
                        }
                    }
                }

                this.tickDelay8 = this.tickDelay8 - 1;
                ArrayList itemList72 = this.itemList7;
                float value11 = (float)offset;
                float[] factorArray2 = new float[]{scaleFactor6, scaleFactor7, scaleFactor8};
                Float scaleFactor9 = this.getScaleFactor(value11, factorArray2);
                itemList72.add(scaleFactor9);
                ArrayList itemList32 = this.itemList3;
                Float floatVal = (float)offset;
                itemList32.add(floatVal);
                int index14;
                if (this.itemList7.size() <= 10) {
                    index14 = this.itemList3.size();
                } else {
                    index14 = this.itemList7.size();
                }

                if (index14 >= 10 && this.booleanSetting7.isEnabled()) {
                    this.itemList7.stream().mapToDouble(object -> ((Float)object).doubleValue()).average().orElse(0.0);
                    this.itemList3.stream().mapToDouble(object2 -> ((Float)object2).doubleValue()).average().orElse(0.0);
                    String text2 = new StringBuilder().append(offset).append(" ").append(scaleFactor6).toString();
                    this.applyString6(text2);
                }
            }

            this.reachDistance4 = (double)s06PacketUpdateHealth.getScaleFactor();
        }

        boolean enabled8;
        if (this.modeSetting != null) {
            enabled8 = this.modeSetting.getSelected().equals("Incoming");
        } else {
            enabled8 = packet2 instanceof S32PacketConfirmTransaction;
        }

        boolean enabled9;
        if (!enabled8
        && !(packet2 instanceof S14PacketEntity)
        && !(packet2 instanceof S02PacketChat)
        && !(packet2 instanceof S19PacketEntityHeadLook)
        && !(packet2 instanceof S18PacketEntityTeleport)
        && !(packet2 instanceof S38PacketPlayerListItem)
        && !(packet2 instanceof S3APacketTabComplete)
        && !(packet2 instanceof S06PacketUpdateHealth)
        && !(packet2 instanceof S3EPacketTeams)
        && !(packet2 instanceof S03PacketTimeUpdate)
        && !(packet2 instanceof S2FPacketSetSlot)
        && !(packet2 instanceof S04PacketEntityEquipment)
        && !(packet2 instanceof S0BPacketAnimation)
        && !(packet2 instanceof S20PacketEntityProperties)
        && !(packet2 instanceof S1BPacketEntityAttach)
        && !(packet2 instanceof S1CPacketEntityMetadata)
        && !(packet2 instanceof S23PacketBlockChange)
        && !(packet2 instanceof S08PacketPlayerPosLook)
        && !(packet2 instanceof S07PacketRespawn)
        && !(packet2 instanceof S21PacketChunkData)
        && !(packet2 instanceof S33PacketUpdateSign)
        && !(packet2 instanceof S00PacketKeepAlive)
        && !(packet2 instanceof S13PacketDestroyEntities)
        && !(packet2 instanceof S2BPacketChangeGameState)
        && !(packet2 instanceof S35PacketUpdateTileEntity)
        && !(packet2 instanceof S30PacketWindowItems)) {
            enabled9 = packet2 instanceof S45PacketTitle;
        } else {
            enabled9 = packet2 instanceof S32PacketConfirmTransaction;
        }

        boolean enabled10;
        if (!enabled9 && !(packet2 instanceof S48PacketResourcePackSend)) {
            enabled10 = packet2 instanceof S38PacketPlayerListItem;
        } else {
            enabled10 = this.settingKit1V1Damage.isEnabled();
        }

        int index15 = 0;
        boolean completed7 = false;
        if (enabled10 && packet2 instanceof S06PacketUpdateHealth && minecraft.thePlayer != null) {
            float scaleFactor10 = ((S06PacketUpdateHealth)packet2).getScaleFactor();
            float float2 = minecraft.thePlayer.getFloat2();
            float value12 = scaleFactor10 - float2;
            double offset8 = (double)value12;
            if ((!Double.isNaN(offset8) && !Double.isNaN(-2.58) ? (offset8 > -2.58 ? 1 : (offset8 == -2.58 ? 0 : -1)) : -1) > 0) {
                index15 = !Float.isNaN(value12) && !Float.isNaN(0.0F) ? (value12 > 0.0F ? 1 : (value12 == 0.0F ? 0 : -1)) : 1;
                completed7 = true;
            }
        }
        if (!completed7) {
            index15 = (packet2 instanceof S29PacketSoundEffect ? 1 : 0);
        }


        boolean enabled11;
        if (index15 != 0) {
            enabled11 = this.settingSoundName.isEnabled();
        } else {
            enabled11 = packet2 instanceof S02PacketChat;
        }

        boolean enabled12;
        if (enabled11 && this.settingOnlyDebugChat.isEnabled()) {
            enabled12 = ((S02PacketChat)packet2).getChatComponent().getString().contains("Augustus");
        } else {
            enabled12 = packet2 instanceof S19PacketEntityStatus;
        }

        if (enabled12 && this.settingHitBug.isEnabled() && minecraft.thePlayer != null) {
            S19PacketEntityStatus s19PacketEntityStatus3 = (S19PacketEntityStatus)packet2;
            int index7 = s19PacketEntityStatus3.getIndex();
            int entityId7 = minecraft.thePlayer.getEntityId();
            if (index7 == entityId7 && s19PacketEntityStatus3.processValue() == 2) {
                if (!this.timer.hasReached(450L)) {
                    StringBuilder sb = new StringBuilder().append("Hitbug! ");
                    long time6 = this.timer.getTime();
                    String text3 = sb.append(time6).toString();
                    this.applyString6(text3);
                }

                this.timer.reset();
            }
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        packet.getPacket();
    }

    @Override
    public void onSafeWalk(EventSafeWalk event) {
        if (this.settingDoubleClickFirstHit != null) {
            if (this.settingDoubleClickFirstHit.isEnabled()) {
                if (event.getText().equals("game.player.hurt")) {
                    EntityPlayer entityPlayer = this.getEntityPlayer();
                    double coordinate3 = event.getCoordinate3();
                    double posX = minecraft.thePlayer.posX;
                    if (this.checkCondition(coordinate3, posX, 0.01)) {
                        double coordinate = event.getCoordinate();
                        double posY = minecraft.thePlayer.posY;
                        if (this.checkCondition(coordinate, posY, 0.01)) {
                            double coordinate2 = event.getCoordinate2();
                            double posZ = minecraft.thePlayer.posZ;
                            if (this.checkCondition(coordinate2, posZ, 0.01)) {
                                this.tickDelay26++;
                                return;
                            }
                        }
                    }

                    if (entityPlayer != null) {
                        this.tickDelay18++;
                    }
                }
            }
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.settingDoubleClickFirstHit != null && this.settingDoubleClickFirstHit.isEnabled()) {
            int index = this.tickDelay18 - this.tickDelay26;
            int index2 = this.tickDelay26 - this.tickDelay18;
            int index3 = this.tickDelay14 - this.tickDelay2;
            int index4 = this.tickDelay2 - this.tickDelay14;
            FontRenderer fontRendererObj = minecraft.fontRendererObj;
            StringBuilder sb = new StringBuilder().append("MeHits: §7").append(this.tickDelay18).append("§f ");
            String text;
            if (index == 0) {
                text = "";
            } else {
                text = index < 0 ? "§c" + index : " §a+" + index;
            }

            StringBuilder sb2 = sb.append(text).append("§f  Crits: §7").append(this.tickDelay14).append(" §f");
            String text2;
            if (index3 == 0) {
                text2 = "";
            } else {
                text2 = index3 < 0 ? "§c" + index3 : " §a+" + index3;
            }

            fontRendererObj.getIndex(sb2.append(text2).append("§f").toString(), 0, 100, -1);
            FontRenderer fontRendererObj2 = minecraft.fontRendererObj;
            StringBuilder sb3 = new StringBuilder().append("OpponentHits: §7").append(this.tickDelay26).append("§f ");
            String text3;
            if (index2 == 0) {
                text3 = "";
            } else {
                text3 = index2 < 0 ? "§c" + index2 : " §a+" + index2;
            }

            StringBuilder sb4 = sb3.append(text3).append("§f  Crits: §7").append(this.tickDelay2).append(" §f");
            String text4;
            if (index4 == 0) {
                text4 = "";
            } else {
                text4 = index4 < 0 ? "§c" + index4 : " §a+" + index4;
            }

            fontRendererObj2.getIndex(sb4.append(text4).append("§f").toString(), 0, 120, -1);
        }

        RenderUtils.processAction();
    }

    @Override
    public void onPacket(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (this.modeSetting != null
        && (this.modeSetting.getSelected().equals("Outgoing") || this.modeSetting.getSelected().equals("All"))) {
            this.handlePacket(packet2, "Outgoing: ");
        }

        Client.getClient().getClass();
    }

    @Override
    public void onMove(EventMove event) {
        BooleanSetting settingSneakDebug2 = this.settingSneakDebug;
        if (settingSneakDebug2.isEnabled()) {
            event.isState4();
            this.tickDelay27++;
        }

        if (settingSneakDebug2 != null) {
            if (this.booleanSetting9.isEnabled()) {
                MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
                if (objectMouseOver != null && objectMouseOver.entityHit instanceof EntityPlayer) {
                    this.targetPlayer = (EntityPlayer)minecraft.objectMouseOver.entityHit;
                }

                if (this.targetPlayer == null) {
                    return;
                }

                File file = new File(OnlineConfigsManager.text + File.separator + "debug" + File.separator + "InputLog.txt");
                boolean enabled = Files.exists(file.getParentFile().toPath());
                if (!enabled) {
                    try {
                        Files.createDirectories(file.getParentFile().toPath());
                    } catch (IOException iOException) {
                    }
                }

                file.exists();
                if (!enabled) {
                    try {
                        file.createNewFile();
                    } catch (IOException iOException2) {
                        throw new RuntimeException(iOException2);
                    }
                }

                try {
                    Files.write(
                    file.toPath(),
                    ("Forward: "
                    + event.getScaleFactor7()
                    + "; Strafe: "
                    + event.getScaleFactor2()
                    + "; TargetHurttime: "
                    + this.targetPlayer.hurtTime
                    + "; TargetDistance: "
                    + this.targetPlayer.getDistanceToEntity(minecraft.thePlayer)
                    + "\r")
                    .getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND
                    );
                } catch (IOException iOException3) {
                    throw new RuntimeException(iOException3);
                }
            }
        }
    }

    @Override
    public void onRunTick() {
        Client.getClient().getClass();
        if (this.booleanSetting15 != null && this.booleanSetting15.isEnabled()) {
            EntityPlayer player = null;
            double offset = 10000.0;

            for (EntityPlayer player2 : (Iterable<EntityPlayer>) minecraft.theWorld.itemList7) {
                if (player2 != minecraft.thePlayer) {
                    double scaleFactor3 = (double)player2.getScaleFactor3(minecraft.thePlayer);
                    if (scaleFactor3 < offset) {
                        player = player2;
                        offset = scaleFactor3;
                    }
                }
            }

            if (player != null && !CombatTargetFilter.checkEntityLivingBase(1222936913, 29201, player)) {
                String text = player.getText() + " " + Arrays.toString(CombatTargetFilter.getIndexOffsetArray(player));
                RayTraceUtil.applyString(text);
            }
        }

        if (this.timer3.hasReached(3000L)) {
            this.targetPlayer2 = null;
        }

        if (this.timer6.hasReached(4000L)) {
            this.targetEntity2 = null;
        }
    }

    @Override
    public void onPreTick() {
        if (this.settingPlaceDebug.isEnabled()) {
            this.tickDelay20++;
            double tickDelay192 = (double)this.tickDelay19;
            this.clickHelper.processDouble(tickDelay192);
            this.tickDelay19 = 0;
        }

        if (this.settingMotionDebug.isEnabled()
        && (
        minecraft.thePlayer.motionX != 0.0
        || minecraft.thePlayer.motionZ != 0.0
        || minecraft.thePlayer.motionY != this.reachDistance5
        || this.reachDistance5 != -0.0784000015258789
        || minecraft.thePlayer.ticksExisted % 20 == 0
        )) {
            String text = "motion=(x=" + minecraft.thePlayer.motionX + ", y=" + minecraft.thePlayer.motionY + ", z=" + minecraft.thePlayer.motionZ + ")";
            RayTraceUtil.applyString(text);
            this.reachDistance5 = minecraft.thePlayer.motionY;
        }

        this.targetPlayer3 = this.getEntityPlayer();
        if (this.timer5.hasReached(2000L)) {
            this.targetEntity = null;
            this.tickDelay12 = -1;
        }

        if (this.booleanSetting14 != null && this.booleanSetting14.isEnabled() && this.targetEntity != null) {
            this.tickDelay6++;
            if (this.tickDelay12 >= 0) {
                this.tickDelay12++;
            }

            if (this.targetEntity.hurtTime == 9) {
                if (this.linkedList.isEmpty()) {
                    RayTraceUtil.applyString("No Sprint Reset");
                } else {
                    String text2 = "MoreKBTimes: "
                    + Arrays.toString(this.linkedList.toArray())
                    + ";  MoreKBDurations: "
                    + Arrays.toString(this.linkedList2.toArray())
                    + ";  "
                    + this.tickDelay6;
                    RayTraceUtil.applyString(text2);
                }

                this.linkedList.clear();
                this.linkedList2.clear();
                this.tickDelay6 = 0;
            }
        }

        this.animationTimer.processAction();
        if (this.animationTimer.checkCondition(20)) {
            this.animationTimer.processAction3();
            this.tickDelay16 = this.tickDelay9;
            this.tickDelay9 = 0;
        }
    }

    public List getList() {
        return this.getList(SourceDataLine.class);
    }

    private float getScaleFactor(DamageSource damageSource, float value, EntityPlayer player) {
        float value2 = value;
        if (!damageSource.canExecute()) {
            float value3 = 25.0F - this.getFloat();
            value2 = value * value3 / 25.0F;
        }

        return value2;
    }

    private float getScaleFactor(float value, float[] factorArray) {
        float value2 = Float.MAX_VALUE;
        int index2 = 0;

        for (int i = 0; i < factorArray.length; i++) {
            float value3 = Math.abs(factorArray[i] - value);
            if (value3 < value2) {
                value2 = value3;
                index2 = i;
            }
        }

        return factorArray[index2];
    }

    public SourceDataLine getSourceDataLine(String text) {
        return (SourceDataLine)this.getEntry(SourceDataLine.class, text);
    }

    public static void processAction(int index) {
        tickDelay = index;
    }

    public float getFloat() {
        float value = 0.0F;

        for (int i = 0; i < minecraft.thePlayer.inventoryPlayer.armorInventory.length; i++) {
            ItemStack itemStack = minecraft.thePlayer.inventoryPlayer.armorInventory[i];
            if (itemStack != null && itemStack.getItem() instanceof ItemGoldenBoots) {
                float indexOffset4 = (float)((ItemGoldenBoots)itemStack.getItem()).indexOffset4;
                value += indexOffset4;
            }
        }

        return value;
    }

    private void handlePacket(Packet packet, String text) {
        if (this.copyOnWriteArrayList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();

            for (String text2 : (Iterable<String>) this.copyOnWriteArrayList) {
                if (text2 != null) {
                    stringBuilder.append(text2);
                }
            }

            this.copyOnWriteArrayList.clear();

            try {
                File file = new File(OnlineConfigsManager.text + File.separator + "debug" + File.separator + "PacketLog.txt");
                if (!Files.exists(file.getParentFile().toPath())) {
                    try {
                        Files.createDirectories(file.getParentFile().toPath());
                    } catch (IOException iOException) {
                    }
                }

                if (!file.exists()) {
                    file.createNewFile();
                }

                Files.write(file.toPath(), stringBuilder.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            } catch (IOException iOException2) {
            }
        }

        if (!(packet instanceof LatencyPacket)) {
            if (!(packet instanceof CustomDataPacket)) {
                if (!(packet instanceof S2APacketParticles)) {
                    StringBuilder stringBuilder2 = new StringBuilder(" " + packet.getClass().getSimpleName() + " ");
                    Field[] declaredFields = packet.getClass().getDeclaredFields();

                    for (Field field : declaredFields) {
                        stringBuilder2.append(" ");
                        stringBuilder2.append(field.getName());
                        stringBuilder2.append(": ");
                        field.setAccessible(true);
                        Object object = null;

                        try {
                            object = field.get(packet);
                        } catch (IllegalAccessException illegalAccessException) {
                        }

                        if (object == null) {
                            stringBuilder2.append("ERROR");
                        } else {
                            stringBuilder2.append(object.toString());
                        }

                        stringBuilder2.append(" ");
                    }

                    boolean completed = false;
                    Field[] fieldArray = null;
                    int index = 0;
                    try {
                        Field[] declaredFields2 = packet.getClass().getSuperclass().getDeclaredFields();
                        fieldArray = declaredFields2;
                        index = declaredFields2.length;
                    } catch (Exception e) {
                        completed = true;
                    }
                    if (!completed) {
                        int index2 = 0;
                        index2 = 0;
                        try {
                            while (index2 < index) {
                                Field field2 = fieldArray[index2];
                                stringBuilder2.append(" ");
                                stringBuilder2.append(field2.getName());
                                stringBuilder2.append(": ");
                                field2.setAccessible(true);
                                Object object2 = null;

                                try {
                                    object2 = field2.get(packet);
                                } catch (IllegalAccessException illegalAccessException2) {
                                }

                                if (object2 == null) {
                                    stringBuilder2.append("ERROR");
                                } else {
                                    stringBuilder2.append(object2.toString());
                                }

                                stringBuilder2.append(" ");
                                index2++;
                            }
                        } catch (Exception e3) {
                        }
                    }


                    stringBuilder2.append("\r");
                    this.copyOnWriteArrayList.add(text + new SimpleDateFormat().format(new Date()) + stringBuilder2.toString());
                }
            }
        }
    }

    private boolean canExecute5() {
        for (ItemStack itemStack : minecraft.thePlayer.inventoryPlayer.armorInventory) {
            if (itemStack != null && itemStack.getItem() instanceof ItemGoldenBoots && LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, itemStack) > 0) {
                return true;
            }
        }

        return false;
    }

    private EntityPlayer getEntityPlayer() {
        double offset = 1000000.0;
        EntityPlayer player = null;
        if (minecraft.theWorld == null) {
            return null;
        } else {
            for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
                if (entity instanceof EntityPlayer && entity.getEntityId() != minecraft.thePlayer.getEntityId()) {
                    double distance = (double)minecraft.thePlayer.getDistanceToEntity(entity);
                    if (!(distance >= offset) && distance < 6.0) {
                        offset = distance;
                        player = (EntityPlayer)entity;
                    }
                }
            }

            return player;
        }
    }

    public static int getIndex() {
        return tickDelay;
    }

    public List getList2() {
        return this.getList(TargetDataLine.class);
    }

    public float getScaleFactor(DamageSource damageSource, float value, EntityPlayer player, boolean enabled, boolean enabled2) {
        float value2 = value;
        if (!damageSource.canExecute() && minecraft.thePlayer.isBlocking() && value > 0.0F) {
            value2 = (1.0F + value) * 0.5F;
        }

        value2 = this.getScaleFactor(damageSource, value2, player);
        value2 = this.getScaleFactor(damageSource, value2, enabled, enabled2);
        return Math.max(value2 - minecraft.thePlayer.getScaleFactor13(), 0.0F);
    }

    private String getString3(int index) {
        if (index == 0) {
            return "§3";
        } else if (index == 1) {
            return "§b";
        } else {
            return index >= 3 ? "§c" : "§9";
        }
    }

    public void applyString6(String text) {
        if (this.settingServersideChat.isEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            Client client = Client.getClient();
            minecraft.thePlayer.sendChatMessage(stringBuilder.append(client.getString4()).append(": ").append(text).toString());
        } else {
            RayTraceUtil.applyString(text);
        }
    }

    private float getScaleFactor(ItemStack itemStack) {
        NBTTagList extendedLoggerEntry = itemStack.getExtendedLoggerEntry();
        if (extendedLoggerEntry != null) {
            for (int i = 0; i < extendedLoggerEntry.getInt(); i++) {
                short id = extendedLoggerEntry.getNBTTagCompound(i).processValue("id");
                short id2 = extendedLoggerEntry.getNBTTagCompound(i).processValue("lvl");
                if (id == 62) {
                    return (float)id2 * 1.25F;
                }
            }
        }

        return 0.0F;
    }

    public static int getInt2() {
        return getIndex() == 0 ? 34 : 0;
    }

    private boolean canExecute6() {
        return this.booleanSetting6.isEnabled();
    }

    public Object getEntry(Class clazz, String text) {
        for (Info info : AudioSystem.getMixerInfo()) {
            Mixer mixer = AudioSystem.getMixer(info);
            Line.Info info2 = new Line.Info(clazz);
            if (mixer.isLineSupported(info2) && info.getName().equals(text)) {
                try {
                    return clazz.cast(mixer.getLine(info2));
                } catch (Exception e) {
                }
            }
        }

        return null;
    }

    public List getList(Class clazz) {
        ArrayList arrayList = new ArrayList();

        for (Info info : AudioSystem.getMixerInfo()) {
            Mixer mixer = AudioSystem.getMixer(info);
            Line.Info info2 = new Line.Info(clazz);
            if ((char)(mixer.isLineSupported(info2) ? 1 : 0) != 0) {
                arrayList.add(info.getName());
            }
        }

        return arrayList;
    }

    public TargetDataLine getTargetDataLine(String text) {
        return (TargetDataLine)this.getEntry(TargetDataLine.class, text);
    }

    public float getScaleFactor(DamageSource damageSource, float value, boolean enabled, boolean enabled2) {
        float value2 = value;
        if (damageSource.canExecute2()) {
            return value;
        } else {
            if (minecraft.thePlayer.checkPotion(Potion.potion12) && damageSource != DamageSource.damageSource10) {
                int index3 = (minecraft.thePlayer.getPotionEffect(Potion.potion12).getIndexOffset2() + 1) * 5;
                int index4 = 25 - index3;
                value2 = value * (float)index4 / 25.0F;
            }

            if (value2 <= 0.0F) {
                return 0.0F;
            } else {
                int index5 = 0;
                LvlManager.storageEspRenderer.damageSource = damageSource;
                if (!enabled2) {
                    for (int i = 0; i < minecraft.thePlayer.inventoryPlayer.armorInventory.length; i++) {
                        ItemStack itemStack = minecraft.thePlayer.inventoryPlayer.armorInventory[i];
                        if (itemStack != null && itemStack.getItem() instanceof ItemGoldenBoots) {
                            float value3 = 0.0F;
                            if (LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, itemStack) > 0) {
                                int index = LvlManager.storageEspRenderer
                                .getIndex(
                                EnchantmentManager.enchantmentManager6, LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, itemStack)
                                );
                                value3 = (float)(index + 1 >> 1) + (float)((index >> 1) + 1) / (enabled ? 1.0F : 2.0F);
                            }

                            index5 = (int)((float)index5 + value3);
                        }
                    }
                }

                if (index5 > 20) {
                    index5 = 20;
                }

                if (index5 <= 0) {
                    return value2;
                } else {
                    int index2 = 25 - index5;
                    return value2 * (float)index2 / 25.0F;
                }
            }
        }
    }

    private boolean checkCondition(double x, double y, double z) {
        return Math.abs(x - y) <= z;
    }

    private boolean canExecute7() {
        return this.booleanSetting6.isEnabled();
    }
}
