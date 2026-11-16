package augustus.modules.misc;

import augustus.anticheat.checks.AimDistanceCheck;
import augustus.anticheat.checks.AimP1Check;
import augustus.anticheat.checks.AimP2Check;
import augustus.anticheat.checks.AimP3Check;
import augustus.anticheat.checks.AimP4Check;
import augustus.anticheat.checks.AntiCheatCheck;
import augustus.anticheat.checks.AntiCheatManager;
import augustus.anticheat.checks.AutoClickerECheck;
import augustus.anticheat.checks.AutoClickerP1Check;
import augustus.anticheat.checks.AutoClickerP2Check;
import augustus.anticheat.checks.AutoClickerP3Check;
import augustus.anticheat.checks.AutoClickerP4Check;
import augustus.anticheat.checks.AutoClickerP5Check;
import augustus.anticheat.checks.AutoClickerP6Check;
import augustus.anticheat.checks.AutoClickerP7Check;
import augustus.anticheat.checks.CombinedCheck;
import augustus.anticheat.checks.EagleACheck;
import augustus.anticheat.checks.EagleBCheck;
import augustus.anticheat.checks.EagleCCheck;
import augustus.anticheat.checks.FirstHitCPSCheck;
import augustus.anticheat.checks.FirstHitCheck;
import augustus.anticheat.checks.HitDelayFixACheck;
import augustus.anticheat.checks.HitDelayFixBCheck;
import augustus.anticheat.checks.HitDelayFixCCheck;
import augustus.anticheat.checks.HurtTimeHitCheck;
import augustus.anticheat.checks.Intave13P19Check;
import augustus.anticheat.checks.Intave13P56Check;
import augustus.anticheat.checks.Intave14ACDoubleClickCheck;
import augustus.anticheat.checks.Intave14ACInvariantCheck;
import augustus.anticheat.checks.Intave14ACLowDeviationCheck;
import augustus.anticheat.checks.Intave14ACRelativeCheck;
import augustus.anticheat.checks.Intave14ACSpikeCheck;
import augustus.anticheat.checks.Intave14ACVarianceCheck;
import augustus.anticheat.checks.Intave14InvalidBlockPlaceCheck;
import augustus.anticheat.checks.Intave14InvalidPitchCheck;
import augustus.anticheat.checks.Intave14InventoryAnalysisCheck;
import augustus.anticheat.checks.Intave14InventorySlotCheck;
import augustus.anticheat.checks.Intave14InventorySlotDupeCheck;
import augustus.anticheat.checks.Intave14InventoryWalkingCheck;
import augustus.anticheat.checks.Intave14P131Check;
import augustus.anticheat.checks.Intave14P141144Check;
import augustus.anticheat.checks.Intave14P200Check;
import augustus.anticheat.checks.Intave14P231Check;
import augustus.anticheat.checks.Intave14P41Check;
import augustus.anticheat.checks.Intave14P51Check;
import augustus.anticheat.checks.Intave14P71Check;
import augustus.anticheat.checks.Intave14P81Check;
import augustus.anticheat.checks.Intave14P82Check;
import augustus.anticheat.checks.Intave14P83Check;
import augustus.anticheat.checks.Intave14P84Check;
import augustus.anticheat.checks.Intave14P85Check;
import augustus.anticheat.checks.Intave14P86Check;
import augustus.anticheat.checks.Intave14P91Check;
import augustus.anticheat.checks.Intave14P92Check;
import augustus.anticheat.checks.Intave14PostBlockPlaceCheck;
import augustus.anticheat.checks.Intave14RotationBlockCheck;
import augustus.anticheat.checks.Intave14TimeBlockPlaceCheck;
import augustus.anticheat.checks.Intave14TimerCheck;
import augustus.anticheat.checks.NoJumpDelayACheck;
import augustus.anticheat.checks.PerfectHitAlphaCheck;
import augustus.anticheat.checks.SelectionCheck;
import augustus.anticheat.checks.TrackAimCheck;
import augustus.anticheat.checks.TriggerCheck;
import client.Client;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.EventRotation;
import client.event.EventWorldLoad;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.RotationListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.MovingObjectType;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ClientAntiCheat
    extends Module
    implements WorldLoadListener,
    AttackEntityListener,
    RotationListener,
    MoveListener,
    UpdateStateListener,
    RenderListener,
    PacketListener,
    TickListener {
    public AntiCheatManager customCheck = new AntiCheatManager();
    public HashMap entryMap = new HashMap();
    public ArrayList itemList3 = new ArrayList();
    public EntityLivingBase targetEntity = null;

    public ClientAntiCheat() {
        super("ClientAntiCheat", "KundenAntiSchummel", new Color(78, 60, 102), Category.MISC);
        this.itemList3.add(new Intave13P19Check("Intave13P_19", this.customCheck));
        this.itemList3.add(new Intave13P56Check("Intave13P_56", this.customCheck));
        this.itemList3.add(new Intave14P41Check("Intave14P_41", this.customCheck));
        this.itemList3.add(new Intave14P51Check("Intave14P_51", this.customCheck));
        this.itemList3.add(new Intave14P71Check("Intave14P_71", this.customCheck));
        this.itemList3.add(new Intave14P81Check("Intave14P_81", this.customCheck));
        this.itemList3.add(new Intave14P82Check("Intave14P_82", this.customCheck));
        this.itemList3.add(new Intave14P83Check("Intave14P_83", this.customCheck));
        this.itemList3.add(new Intave14P84Check("Intave14P_84", this.customCheck));
        this.itemList3.add(new Intave14P85Check("Intave14P_85", this.customCheck));
        this.itemList3.add(new Intave14P86Check("Intave14P_86", this.customCheck));
        this.itemList3.add(new Intave14P91Check("Intave14P_91", this.customCheck));
        this.itemList3.add(new Intave14P92Check("Intave14P_92", this.customCheck));
        this.itemList3
            .add(new Intave14PostBlockPlaceCheck("Intave14PostBlockPlaceCheck", this.customCheck));
        this.itemList3.add(new Intave14TimeBlockPlaceCheck("Intave14TimeBlockPlaceCheck", this.customCheck, true));
        this.itemList3.add(new Intave14InvalidBlockPlaceCheck("Intave14InvalidBlockPlace", this.customCheck));
        this.itemList3
            .add(new Intave14RotationBlockCheck("Intave14RotationBlockCheck", this.customCheck));
        this.itemList3.add(new Intave14ACInvariantCheck("Intave14ACInvariant", this.customCheck));
        this.itemList3.add(new Intave14ACRelativeCheck("Intave14ACRelative", this.customCheck));
        this.itemList3.add(new Intave14ACSpikeCheck("Intave14ACSpike", this.customCheck));
        this.itemList3.add(new Intave14ACVarianceCheck("Intave14ACVariance", this.customCheck));
        this.itemList3.add(new Intave14ACLowDeviationCheck("Intave14ACLowDeviation", this.customCheck));
        this.itemList3.add(new Intave14ACDoubleClickCheck("Intave14ACDoubleClick", this.customCheck));
        this.itemList3.add(new Intave14P131Check("Intave14P_131", this.customCheck));
        this.itemList3.add(new Intave14InventoryAnalysisCheck("Intave14InventoryAnalysis", this.customCheck));
        this.itemList3.add(new Intave14InventoryWalkingCheck("Intave14InventoryWalking", this.customCheck));
        this.itemList3.add(new Intave14InventorySlotCheck("Intave14InventorySlot", this.customCheck));
        this.itemList3.add(new Intave14P200Check("Intave14P_200", this.customCheck));
        this.itemList3.add(new Intave14P141144Check("Intave14P141_144", this.customCheck));
        this.itemList3.add(new Intave14P231Check("Intave14P_231", this.customCheck));
        this.itemList3.add(new Intave14InvalidPitchCheck("Intave14InvalidPitch", this.customCheck));
        this.itemList3.add(new Intave14InventorySlotDupeCheck("Intave14InventorySlotDupe", this.customCheck));
        this.itemList3.add(new Intave14TimerCheck("Intave14Timer", this.customCheck));
        this.itemList3.add(new AutoClickerP1Check("AutoClickerP1", this.customCheck));
        this.itemList3.add(new AutoClickerP2Check("AutoClickerP2", this.customCheck));
        this.itemList3.add(new AutoClickerP3Check("AutoClickerP3", this.customCheck));
        this.itemList3.add(new AutoClickerP4Check("AutoClickerP4", this.customCheck));
        this.itemList3.add(new AutoClickerP5Check("AutoClickerP5", this.customCheck));
        this.itemList3.add(new AutoClickerP6Check("AutoClickerP6", this.customCheck));
        this.itemList3.add(new AutoClickerP7Check("AutoClickerP7", this.customCheck));
        this.itemList3.add(new HurtTimeHitCheck("HurtTimeHit", this.customCheck));
        this.itemList3.add(new AimP1Check("AimP1", this.customCheck));
        this.itemList3.add(new AimP2Check("AimP2", this.customCheck));
        this.itemList3.add(new AimP3Check("AimP3", this.customCheck));
        this.itemList3.add(new AimP4Check("AimP4", this.customCheck));
        Client.getClient().getClass();
        this.itemList3.add(new SelectionCheck("SelectionCheck", this.customCheck));
        this.itemList3.add(new TriggerCheck("Trigger", this.customCheck));
        this.itemList3.add(new PerfectHitAlphaCheck("PerfectHit1", this.customCheck));
        this.itemList3.add(new FirstHitCPSCheck("FirstHitCPS", this.customCheck));
        this.itemList3.add(new FirstHitCheck("FirstHit", this.customCheck));
        this.itemList3.add(new CombinedCheck("Combined", this.customCheck));
        this.itemList3.add(new AutoClickerECheck("AutoClickerE", this.customCheck));
        this.itemList3.add(new EagleACheck("EagleA", this.customCheck));
        this.itemList3.add(new EagleBCheck("EagleB", this.customCheck));
        this.itemList3.add(new EagleCCheck("EagleC", this.customCheck));
        this.itemList3.add(new TrackAimCheck("TrackAim", this.customCheck));
        this.itemList3.add(new HitDelayFixACheck("HitDelayFixA", this.customCheck));
        this.itemList3.add(new HitDelayFixBCheck("HitDelayFixB", this.customCheck));
        this.itemList3.add(new HitDelayFixCCheck("HitDelayFixC", this.customCheck));
        this.itemList3.add(new AimDistanceCheck("AimDistance", this.customCheck));
        this.itemList3.add(new NoJumpDelayACheck("NoJumpDelayA", this.customCheck));
        SettingGroup settingGroup = new SettingGroup(this, "ChecksForMyself");

        for (Object object : this.itemList3) {
            AntiCheatCheck auxiliaryCustomCheck = (AntiCheatCheck)object;
            BooleanSetting booleanSetting = new BooleanSetting(this, auxiliaryCustomCheck.getText(), true, settingGroup);
            BooleanSetting setting = (BooleanSetting)booleanSetting.getSetting(() -> checkAuxiliaryCustomCheck(auxiliaryCustomCheck));
            this.entryMap.put(auxiliaryCustomCheck, setting);
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(1);
        this.registerEvent(28, 20);
        this.registerEvent(21, 20);
        this.registerEvent(43);
        this.registerEvent(32, 10);
        this.registerEvent(26);
        this.registerEvent(40);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        Client.getClient().getClass();
    }

    @Override
    public void onPreDisable() {
        Client.getClient().getClass();
    }

    @Override
    public void onRotation(EventRotation event) {
    }

    @Override
    public void onPacketReceive(EventPacket packet) {
        Client.getClient().getClass();
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Client.getClient().getClass();
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        if (this.customCheck != null && event.getEntity() instanceof EntityLivingBase) {
            if (minecraft.objectMouseOver == null) {
                return;
            }

            if (minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                this.customCheck.processAction(event.getEntity().getEntityId());
                this.customCheck.processAction(minecraft.thePlayer.getVec35(1.0F).getCoordinate4(minecraft.objectMouseOver.hitVec));
            }
        }
    }

    @Override
    public void onPostRotation(EventRotation event) {
        Client.getClient().getClass();
    }

    @Override
    public void onPacketPostReceive(EventPacket packet) {
        Client.getClient().getClass();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.customCheck.updateState();
    }

    @Override
    public void onMove(EventMove event) {
        Client.getClient().getClass();
    }

    @Override
    public void onRender3D(float partialTicks) {
        Client.getClient().getClass();
    }

    @Override
    public void onPreUpdateState() {
        boolean enabled = false;
        Iterator iterator = minecraft.theWorld.loadedEntityList.iterator();

        while (iterator.hasNext()) {
            if (((Entity)iterator.next()).getEntityId() == this.customCheck.getIndex()) {
                enabled = true;
                break;
            }
        }

        if (!enabled) {
            this.customCheck.updateState();
        }

        this.customCheck.processShort();
    }

    @Override
    public void onRunTick() {
        Client.getClient().getClass();
    }

    private static boolean checkAuxiliaryCustomCheck(AntiCheatCheck auxiliaryCustomCheck) {
        return auxiliaryCustomCheck.state;
    }
}
