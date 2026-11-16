package augustus.modules.combat;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.MotionListener;
import client.event.MoveListener;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.RangeSetting;
import client.util.ChatUtil;
import client.util.Packet;
import client.util.SlotManager;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.Iterator;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AutoGapple extends Module implements ClickListener, PacketListener, MotionListener, MoveListener {
    public RangeSetting settingHealth;
    public BooleanSetting settingConsiderTargetHealth;
    public BooleanSetting settingNotWhileKillAura;
    public BooleanSetting settingWhileRegeneration;
    public BooleanSetting settingJumpAndPressS;
    public TimeHelper timer;
    public int keyCode2;

    public AutoGapple() {
        super("AutoGapple", "AutoApfel", new Color(100, 217, 203), Category.COMBAT);
        this.settingHealth = new RangeSetting(this, "Health", 13.0, 15.0, 0.0, 20.0, 0).getRangeSetting2();
        this.settingConsiderTargetHealth = new BooleanSetting(this, "ConsiderTargetHealth", false);
        this.settingNotWhileKillAura = new BooleanSetting(this, "NotWhileKillAura", false);
        this.settingWhileRegeneration = new BooleanSetting(this, "WhileRegeneration", false);
        this.settingJumpAndPressS = new BooleanSetting(this, "JumpAndPressS", false);
        this.timer = new TimeHelper();
        this.keyCode2 = -1;
        this.settingHealth.recalculate();
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(26);
        this.registerEvent(34);
        this.registerEvent(21, 100);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onClick(EventClick event) {
        if (minecraft.thePlayer.inventoryPlayer.currentItem == this.keyCode2 || this.keyCode2 <= -1) {
            if (this.keyCode2 >= 0) {
                if (minecraft.isIntegratedServerRunning()) {
                    event.setState11(true);
                }
            }
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (minecraft.thePlayer.inventoryPlayer.currentItem == this.keyCode2 || this.keyCode2 <= -1) {
            if (this.keyCode2 >= 0) {
                if (minecraft.isIntegratedServerRunning()) {
                    if (this.settingJumpAndPressS.isEnabled()) {
                        float value = -1.0F;
                        event.setScaleFactor7(value);
                        event.setState(true);
                    }
                }
            }
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S07PacketRespawn || packet2 instanceof S08PacketPlayerPosLook) {
            this.timer.reset();
        }
    }

    @Override
    public void onPostMotion() {
        this.keyCode2 = -1;
        if (this.canExecute5() && minecraft.thePlayer.ticksExisted > 10 && minecraft.isIntegratedServerRunning()) {
            int index = 36;

            while (true) {
                if (index >= minecraft.thePlayer.container.itemList2.size()) {
                    break;
                }

                ItemStack itemStack = minecraft.thePlayer.container.getSlot(index).getStack();
                if (itemStack != null && itemStack.getItem() == Item.getItem("golden_apple")) {
                    this.keyCode2 = index - 36;
                }

                index++;
            }
        } else {
            this.settingHealth.recalculate();
        }

        if (!this.timer.hasReached(250L)) {
            this.keyCode2 = -1;
        }

        if (this.keyCode2 >= 0) {
            int keyCode22 = this.keyCode2;
            SlotManager.applyModule3(this, 3, keyCode22);
        }
    }

    private boolean canExecute5() {
        boolean enabled = false;
        boolean enabled2 = false;
        Iterator iterator = minecraft.thePlayer.getCollection().iterator();
        boolean enabled3;
        boolean enabled4;
        if (iterator.hasNext()) {
            PotionEffect potionEffect = (PotionEffect)iterator.next();
            int indexOffset3 = potionEffect.getIndexOffset3();

            while (true) {
                int indexOffset32 = Potion.potion11.indexOffset3;
                if (indexOffset3 == indexOffset32) {
                    enabled = true;
                } else {
                    int indexOffset33 = potionEffect.getIndexOffset3();
                    int indexOffset34 = Potion.potion23.indexOffset3;
                    if (indexOffset33 == indexOffset34) {
                        enabled2 = true;
                    }
                }

                if (!iterator.hasNext()) {
                    enabled3 = true;
                    enabled4 = this.settingConsiderTargetHealth.isEnabled();
                    break;
                }

                potionEffect = (PotionEffect)iterator.next();
                indexOffset3 = potionEffect.getIndexOffset3();
            }
        } else {
            enabled3 = true;
            enabled4 = this.settingConsiderTargetHealth.isEnabled();
        }

        if (enabled4) {
            EntityLivingBase entityLivingBase = ChatUtil.getEntityLivingBase();
            if (entityLivingBase != null) {
                float value = entityLivingBase.getFloat2() - minecraft.thePlayer.getFloat2();
                if (value < 0.0F) {
                    enabled3 = false;
                }
            }
        }

        if (!enabled3) {
            return false;
        } else {
            float value2 = minecraft.thePlayer.getFloat2() - (float)this.settingHealth.getIndexOffset2();
            if (value2 >= 0.0F) {
                return false;
            } else if (this.settingNotWhileKillAura.isEnabled()
            && ClientGlobals.moduleManager.killAura.isToggled()
            && ClientGlobals.moduleManager.killAura.target != null) {
                return false;
            } else if (BlockCache.canExecute()) {
                return false;
            } else if (!enabled && !enabled2) {
                return true;
            } else if (enabled2 && !enabled) {
                return true;
            } else {
                return minecraft.thePlayer.getScaleFactor13() != 0.0F
                ? false
                : this.settingWhileRegeneration.isEnabled();
            }
        }
    }

    public int getKeyCode2() {
        return this.keyCode2;
    }
}
