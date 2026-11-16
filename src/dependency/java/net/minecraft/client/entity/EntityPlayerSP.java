package net.minecraft.client.entity;

import cc.baritone.utils.AStarAuditor;
import cc.baritone.utils.AStarCalculator;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.HeuristicInspector;
import cc.baritone.utils.SecondaryOptionType;
import cc.baritone.utils.SettingsTxtManager;
import cc.baritone.utils.TickListenerAdapter;
import client.Client;
import client.ClientGlobals;
import client.event.EventCameraTransform;
import client.event.EventCameraTransformCallback;
import client.event.EventCommandCallback;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.event.EventMoveInput;
import client.event.EventRotation;
import client.event.EventSneak;
import client.event.LocalListener;
import client.event.MotionUpdateListener;
import client.event.MoveEntityListener;
import client.event.MoveInputListener;
import client.event.MoveListener;
import client.event.RotationListener;
import client.event.SneakListener;
import client.event.StepListener;
import client.event.UpdateStateListener;
import client.gui.CustomGuiScreen;
import client.gui.SpecialGuiScreenAdapter;
import client.module.Module;
import client.render.BoundingBoxDimension;
import client.setting.ArrowDodgerPhase;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.BugUpMoveFlyingType;
import client.setting.CombatModeProperty;
import client.setting.TertiaryOptionType;
import client.util.BacktrackRecord;
import client.util.ClickHelper;
import client.util.CommonCustom;
import client.util.CurveEvaluator;
import client.util.DuplicateStatIdManager;
import client.util.EnumHand;
import client.util.GeometryCoordinator;
import client.util.IBlockState;
import client.util.InvulnerableManager;
import client.util.Listener63Extension;
import client.util.PlayerDiggingActionType;
import client.util.PrimaryLoggerEntry;
import client.util.SimulationSnapshot;
import client.util.SplineBuilder;
import client.util.SplineEngine;
import client.util.SplinePipeline;
import client.util.SplinePool;
import client.util.SplineProfiler;
import client.util.SplineRegistry;
import client.util.SplineService;
import client.util.SplineSynchronizer;
import client.util.StatCraftItemManager;
import client.util.TimeHelper;
import client.util.VectorDispatcher;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import de.florianmichael.dietrichevents2.StateTypes;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChestContainer;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C05PacketPlayerLook;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.CAnimateHandPacket;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityPlayerSP extends AbstractClientPlayer implements Serializable {
   public final NetHandlerPlayClient netHandlerPlayClient;
   public final StatFileWriter statFileWriter;
   public GuiScreen guiScreen;
   public double coordOffset9;
   public int indexOffset41;
   public int indexOffset67;
   public int indexOffset12;
   public Vec3 positionVec10;
   public int indexOffset28;
   public double coordOffset55;
   public double coordOffset23;
   public double coordOffset59;
   public float scaleFactor11;
   public float scaleFactor30;
   public float scaleFactor27;
   public float scaleFactor20;
   public boolean state40;
   public boolean isInWeb2;
   public int entityId3;
   public boolean state71;
   public String text2;
   public MovementInput secondaryFloatEntry;
   public Minecraft mcClient;
   public int indexOffset52;
   public int deathTime;
   public float scaleFactor9;
   public float scaleFactor61;
   public float scaleFactor14;
   public float scaleFactor32;
   public int indexOffset44;
   public float scaleFactor18;
   public float scaleFactor7;
   public float scaleFactor16;
   public boolean state3;
   public int indexOffset33 = 0;
   public float scaleFactor5;
   public float scaleFactor17;
   public final ClickHelper clickHelper = new ClickHelper(40);
   public final ClickHelper clickHelper2 = new ClickHelper(60);
   public final ClickHelper clickHelper3 = new ClickHelper(100);
   public float scaleFactor66 = 0.0F;
   public int indexOffset49 = 0;
   public Vec3 positionVec7;
   public Vec3 positionVec9;
   public int indexOffset10 = 3;
   public boolean state6 = false;
   public boolean state47 = false;
   public boolean inWater3 = false;
   public boolean velocityChanged = false;
   public boolean state7;
   public TimeHelper timer = new TimeHelper();
   public float scaleFactor25 = 0.0F;
   public float scaleFactor19 = 0.0F;
   public Vec3 positionVec8 = new Vec3(0.0, 0.0, 0.0);
   public Vec3 positionVec12 = new Vec3(0.0, 0.0, 0.0);
   public Vec3 positionVec6 = new Vec3(0.0, 0.0, 0.0);
   public Vec3 positionVec11 = new Vec3(0.0, 0.0, 0.0);

   public void updateState46() {
      this.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(this, EntityPlayerAction.OPEN_INVENTORY));
   }

   public double getCoordOffset23() {
      return this.coordOffset23;
   }

   public GuiScreen getGuiScreen() {
      return this.guiScreen;
   }

   public void processFloat7(float factor, float factor2) {
      if (this.indexOffset28 <= 0 && this.onGround && !this.isSprinting() && !this.isSneaking()) {
         SimulationSnapshot simulationSnapshot = new SimulationSnapshot(
            (double)this.mcClient.thePlayer.secondaryFloatEntry.moveStrafe, (double)this.mcClient.thePlayer.secondaryFloatEntry.moveForward
         );
         if (simulationSnapshot.value != 0.0 || simulationSnapshot.value2 != 0.0) {
            Vec3 vec = new Vec3(this.posX, this.getBoundingBox2().minY, this.posZ);
            double offset = this.posX + (double)factor;
            double offset2 = this.posZ + (double)factor2;
            Vec3 vec2 = new Vec3(offset, this.getBoundingBox2().minY, offset2);
            Vec3 vec3 = new Vec3((double)factor, 0.0, (double)factor2);
            float scaleFactor = this.getScaleFactor6();
            float value = (float)vec3.getValue();
            if (value <= 0.001F) {
               double offset3 = (double)scaleFactor * simulationSnapshot.value;
               double offset4 = (double)scaleFactor * simulationSnapshot.value2;
               float factor3 = MathHelper.abs(this.rotationYaw * (float) (Math.PI / 180.0));
               float factor4 = MathHelper.cos(this.rotationYaw * (float) (Math.PI / 180.0));
               vec3 = new Vec3(offset3 * (double)factor4 - offset4 * (double)factor3, vec3.yCoord, offset4 * (double)factor4 + offset3 * (double)factor3);
               value = (float)vec3.getValue();
               if (value <= 0.001F) {
                  return;
               }
            }

            float factor5 = (float)MathHelper.processAction5((double)value);
            Vec3 vec32 = vec3.getVec3((double)factor5);
            Vec3 vec33 = this.getVec34(this.rotationPitch, this.rotationYaw);
            if ((float)(vec33.xCoord * vec32.xCoord + vec33.zCoord * vec32.zCoord) >= -0.15F) {
               BlockPos blockPos = new BlockPos(this.posX, this.getBoundingBox2().maxY, this.posZ);
               IBlockState blockState = this.worldObj.getBlockState(blockPos);
               if (blockState.getBlock().processAction(this.worldObj, blockState, blockPos.getX(), blockPos.getY(), blockPos.getZ()) == null) {
                  BlockPos blockPos2 = blockPos.up();
                  IBlockState blockState2 = this.worldObj.getBlockState(blockPos2);
                  if (blockState2.getBlock().processAction(this.worldObj, blockState2, blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()) == null) {
                     float factor6 = 1.2F;
                     if (this.checkPotion(Potion.potion9)) {
                        factor6 += (float)(this.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.75F;
                     }

                     float factor7 = Math.max(scaleFactor * 7.0F, 1.0F / factor5);
                     Vec3 vec34 = vec2.getVec36(vec32.getVec3((double)factor7));
                     float width = this.width;
                     float height = this.height;
                     AxisAlignedBB boundingBox = new AxisAlignedBB(vec, vec34.getVec312(0.0, (double)height, 0.0)).offset((double)width, 0.0, (double)width);
                     Vec3 vec35 = vec.getVec312(0.0, 0.51F, 0.0);
                     Vec3 vec36 = vec34.getVec312(0.0, 0.51F, 0.0);
                     Vec3 vec37 = vec32.subtract(new Vec3(0.0, 1.0, 0.0)).getVec3((double)(width * 0.5F));
                     Vec3 vec38 = vec35.getVec311(vec37);
                     Vec3 vec39 = vec36.getVec311(vec37);
                     Vec3 vec310 = vec35.getVec36(vec37);
                     Vec3 vec311 = vec36.getVec36(vec37);
                     List list = this.worldObj.getList2(this, boundingBox);
                     float factor8 = Float.MIN_VALUE;

                     for (AxisAlignedBB boundingBox2 : (Iterable<AxisAlignedBB>)(Iterable<?>)list) {
                        if (boundingBox2.matchesVec3(vec38, vec39) || boundingBox2.matchesVec3(vec310, vec311)) {
                           factor8 = (float)boundingBox2.maxY;
                           Vec3 vec312 = boundingBox2.getVec3();
                           BlockPos blockPos3 = new BlockPos(vec312);

                           for (int index = 1; !((float)index >= factor6); index++) {
                              BlockPos blockPos4 = blockPos3.resolveBlockPos2(index);
                              IBlockState blockState3 = this.worldObj.getBlockState(blockPos4);
                              AxisAlignedBB boundingBox3;
                              if ((boundingBox3 = blockState3.getBlock().processAction(this.worldObj, blockState3, blockPos4.getX(), blockPos4.getY(), blockPos4.getZ())) != null) {
                                 factor8 = (float)boundingBox3.maxY + (float)blockPos4.getY();
                                 if ((double)factor8 - this.getBoundingBox2().minY > (double)factor6) {
                                    return;
                                 }
                              }

                              if (index > 1) {
                                 blockPos2 = blockPos2.up();
                                 IBlockState blockState4 = this.worldObj.getBlockState(blockPos2);
                                 if (blockState4.getBlock().processAction(this.worldObj, blockState4, blockPos4.getX(), blockPos4.getY(), blockPos4.getZ()) != null) {
                                    return;
                                 }
                              }
                           }
                           break;
                        }
                     }

                     if (factor8 != Float.MIN_VALUE) {
                        float factor9 = (float)((double)factor8 - this.getBoundingBox2().minY);
                        if (factor9 > 0.5F && factor9 <= factor6) {
                           this.indexOffset28 = 1;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public MovingObjectPosition getMovingObjectPosition(double offset, float posX, float posY, float posZ) {
      Vec3 vec3 = this.getVec35(posX);
      Vec3 vec = this.resolveVec3(posX, posY, posZ);
      Vec3 vec32 = vec3.getVec312(vec.xCoord * offset, vec.yCoord * offset, vec.zCoord * offset);
      return this.worldObj.getMovingObjectPosition4(vec3.xCoord, vec3.yCoord, vec3.zCoord, vec32.xCoord, vec32.yCoord, vec32.zCoord, false, false, true);
   }

   public float getScaleFactor4(Entity entity) {
      float factor = (float)(this.posX - entity.posX);
      float factor2 = (float)(this.posZ - entity.posZ);
      return MathHelper.sin(factor * factor + factor2 * factor2);
   }

   @Override
   public void applyIInventory(IInventory iInventory) {
      String text = iInventory instanceof Listener63Extension ? ((Listener63Extension)iInventory).getLabelText2() : "minecraft:container";
      if ("minecraft:chest".equals(text)) {
         Minecraft mcClient2 = this.mcClient;
         CustomGuiScreen customGuiScreen = new CustomGuiScreen(this.inventoryPlayer, iInventory);
         mcClient2.displayGuiScreen(customGuiScreen);
      } else if ("minecraft:hopper".equals(text)) {
         Minecraft mcClient3 = this.mcClient;
         SplineEngine splineEngine = new SplineEngine(this.inventoryPlayer, iInventory);
         mcClient3.displayGuiScreen(splineEngine);
      } else if ("minecraft:furnace".equals(text)) {
         Minecraft mcClient4 = this.mcClient;
         SplineBuilder splineBuilder = new SplineBuilder(this.inventoryPlayer, iInventory);
         mcClient4.displayGuiScreen(splineBuilder);
      } else if ("minecraft:brewing_stand".equals(text)) {
         Minecraft mcClient5 = this.mcClient;
         SplinePool splinePool = new SplinePool(this.inventoryPlayer, iInventory);
         mcClient5.displayGuiScreen(splinePool);
      } else if ("minecraft:beacon".equals(text)) {
         Minecraft mcClient6 = this.mcClient;
         CurveEvaluator curveEvaluator = new CurveEvaluator(this.inventoryPlayer, iInventory);
         mcClient6.displayGuiScreen(curveEvaluator);
      } else if (!"minecraft:dispenser".equals(text) && !"minecraft:dropper".equals(text)) {
         Minecraft mcClient7 = this.mcClient;
         CustomGuiScreen customGuiScreen2 = new CustomGuiScreen(this.inventoryPlayer, iInventory);
         mcClient7.displayGuiScreen(customGuiScreen2);
      } else {
         Minecraft mcClient8 = this.mcClient;
         SplineProfiler splineProfiler = new SplineProfiler(this.inventoryPlayer, iInventory);
         mcClient8.displayGuiScreen(splineProfiler);
      }
   }

   public MovingObjectPosition resolveMovingObjectPosition(double offset, float posX, float posY, float posZ, double[] offsetArray, double[] offsetArray2) {
      Vec3 vec = this.resolveVec3(posX, offsetArray, offsetArray2);
      Vec3 vec2 = this.resolveVec3(posX, posY, posZ);
      Vec3 vec3 = vec.getVec312(vec2.xCoord * offset, vec2.yCoord * offset, vec2.zCoord * offset);
      return this.worldObj.getMovingObjectPosition4(vec.xCoord, vec.yCoord, vec.zCoord, vec3.xCoord, vec3.yCoord, vec3.zCoord, false, false, true);
   }

   @Override
   public void applyIChatComponentByIChatComponent(IChatComponent iChatComponent) {
      this.mcClient.ingameGUI.getInternalLoggerEntry().applyIChatComponent(iChatComponent);
   }

   @Override
   public boolean matchesText(int count, String text) {
      return count <= 0;
   }

   public void setPositionVec8(Vec3 positionVec) {
      this.positionVec8 = positionVec;
   }

   public Vec3 getPositionVec10() {
      return this.positionVec10;
   }

   @Override
   public float getHeight2() {
      float factor = 1.62F;
      if (this.isState5()) {
         factor = 0.2F;
      }

      if (this.isSprinting()) {
         factor -= 0.08F;
      }

      return factor;
   }

   @Override
   public void applyText(String text, float factor, float factor2) {
      this.worldObj.applyText(this.posX, this.posY, this.posZ, text, factor, factor2, false);
   }

   public double getCoordOffset59() {
      return this.coordOffset59;
   }

   @Override
   public boolean checkDouble(double posX, double posY, double posZ) {
      if (this.noClip) {
         return false;
      } else {
         BlockPos blockPos = new BlockPos(posX, posY, posZ);
         double offset = posX - (double)blockPos.getX();
         double offset2 = posZ - (double)blockPos.getZ();
         if (!this.matchesBlockPos(blockPos)) {
            byte byteValue = -1;
            double offset3 = 9999.0;
            if (this.matchesBlockPos(blockPos.getBlockPos()) && offset < offset3) {
               offset3 = offset;
               byteValue = 0;
            }

            if (this.matchesBlockPos(blockPos.getBlockPos3()) && 1.0 - offset < offset3) {
               offset3 = 1.0 - offset;
               byteValue = 1;
            }

            if (this.matchesBlockPos(blockPos.getBlockPosAsBlockPos()) && offset2 < offset3) {
               offset3 = offset2;
               byteValue = 4;
            }

            if (this.matchesBlockPos(blockPos.getBlockPos2()) && 1.0 - offset2 < offset3) {
               byteValue = 5;
            }

            float factor = 0.1F;
            if (byteValue == 0) {
               this.motionX = (double)(-factor);
            }

            if (byteValue == 1) {
               this.motionX = (double)factor;
            }

            if (byteValue == 4) {
               this.motionZ = (double)(-factor);
            }

            if (byteValue == 5) {
               this.motionZ = (double)factor;
            }
         }

         return false;
      }
   }

   public Vec3 getPositionVec11() {
      return this.positionVec11;
   }

   @Override
   public void applyListener63Extension(Listener63Extension listener63Extension) {
      String labelText = listener63Extension.getLabelText2();
      if ("minecraft:crafting_table".equals(labelText)) {
         Minecraft mcClient2 = this.mcClient;
         GuiChestContainer guiChestContainer = new GuiChestContainer(this.inventoryPlayer, this.worldObj);
         mcClient2.displayGuiScreen(guiChestContainer);
      } else if ("minecraft:enchanting_table".equals(labelText)) {
         Minecraft mcClient3 = this.mcClient;
         SplineService splineService = new SplineService(this.inventoryPlayer, this.worldObj, listener63Extension);
         mcClient3.displayGuiScreen(splineService);
      } else if ("minecraft:anvil".equals(labelText)) {
         Minecraft mcClient4 = this.mcClient;
         SplinePipeline splinePipeline = new SplinePipeline(this.inventoryPlayer, this.worldObj);
         mcClient4.displayGuiScreen(splinePipeline);
      }
   }

   @Override
   public void applyEntityItem(EntityItem entityItem) {
   }

   public Vec3 resolveVec3(float factor, double[] offsetArray, double[] offsetArray2) {
      if (factor == 1.0F) {
         return new Vec3(offsetArray[0], offsetArray[1] + (double)this.getHeight2(), offsetArray[2]);
      } else {
         double offset = offsetArray2[0] + (offsetArray[0] - offsetArray2[0]) * (double)factor;
         double offset2 = offsetArray2[1] + (offsetArray[1] - offsetArray2[1]) * (double)factor + (double)this.getHeight2();
         double offset3 = offsetArray2[2] + (offsetArray[2] - offsetArray2[2]) * (double)factor;
         return new Vec3(offset, offset2, offset3);
      }
   }

   @Override
   public void setEntity8(Entity entity) {
      super.setEntity8(entity);
      if (entity instanceof EntityMinecart) {
         this.mcClient.getMcSoundHandler().applyISound2(new CommonCustom(this, (EntityMinecart)entity));
      }
   }

   @Override
   public void updateDuplicateStatIdManager(DuplicateStatIdManager duplicateStatIdManager, int count) {
      if (duplicateStatIdManager != null && duplicateStatIdManager.enabled) {
         super.updateDuplicateStatIdManager(duplicateStatIdManager, count);
      }
   }

   @Override
   public void updateState21() {
      ActiveListener activeListener = SettingsTxtManager.getExtraListener().resolveActiveListenerByEntityPlayerSP(this);
      if (activeListener != null) {
         ((TickListenerAdapter)activeListener.getGlobalListener()).updateState();
      }

      super.updateState21();
   }

   public void setGuiScreen(GuiScreen guiScreen2) {
      this.guiScreen = guiScreen2;
   }

   public void setText2(String text) {
      this.text2 = text;
   }

   @Override
   public void applyTileEntitySign(TileEntitySign tileEntitySign) {
      Minecraft mcClient2 = this.mcClient;
      SplineRegistry splineRegistry = new SplineRegistry(tileEntitySign);
      mcClient2.displayGuiScreen(splineRegistry);
   }

   @Override
   public EntityItem getEntityItem(boolean flag) {
      PlayerDiggingActionType playerDiggingActionType = flag ? PlayerDiggingActionType.DROP_ALL_ITEMS : PlayerDiggingActionType.DROP_ITEM;
      this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(playerDiggingActionType, BlockPos.origin, EnumFacing.DOWN));
      return null;
   }

   public MovingObjectPosition resolveMovingObjectPosition(double offset, float posX, float posX2, float posX3, float posY, float posZ) {
      Vec3 vec3 = this.getVec35(posX);
      Vec3 vec = this.resolveVec3(posX, posX2, posX3, posY, posZ);
      Vec3 vec32 = vec3.getVec312(vec.xCoord * offset, vec.yCoord * offset, vec.zCoord * offset);
      return this.worldObj.getMovingObjectPosition4(vec3.xCoord, vec3.yCoord, vec3.zCoord, vec32.xCoord, vec32.yCoord, vec32.zCoord, false, false, true);
   }

   public StatFileWriter getStatFileWriter() {
      return this.statFileWriter;
   }

   public void setScaleFactor11(float scaleFactor) {
      this.scaleFactor11 = scaleFactor;
   }

   @Override
   public boolean isActive18() {
      return true;
   }

   @Override
   public void applyLocalListener(LocalListener localListener) {
      Minecraft mcClient2 = this.mcClient;
      PrimaryLoggerEntry primaryLoggerEntry = new PrimaryLoggerEntry(this.inventoryPlayer, localListener, this.worldObj);
      mcClient2.displayGuiScreen(primaryLoggerEntry);
   }

   @Override
   public void applyItemStack(ItemStack stack) {
      if (stack.getItem() == Items.writableBook) {
         Minecraft mcClient2 = this.mcClient;
         SpecialGuiScreenAdapter specialGuiScreenAdapter = new SpecialGuiScreenAdapter(this, stack, true);
         mcClient2.displayGuiScreen(specialGuiScreenAdapter);
      }
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      this.mcClient.ingameGUI.getInternalLoggerEntry().applyIChatComponent(iChatComponent);
   }

   public void setCoordOffset23(double coordOffset) {
      this.coordOffset23 = coordOffset;
   }

   public void setPositionVec6(Vec3 positionVec) {
      this.positionVec6 = positionVec;
   }

   public Vec3 getPositionVec7() {
      return this.positionVec7;
   }

   @Override
   public void setState3(boolean state) {
      super.setState3(state);
      this.deathTime = state ? 600 : 0;
   }

   public double getValue2() {
      Collection collection = this.getCollection();
      double offset = 0.1F * (1.0 + invalidOperationManager.getValue());
      if (collection != null) {
         int count = -1;
         int count2 = -1;

         for (PotionEffect potionEffect : (Iterable<PotionEffect>)(Iterable<?>)collection) {
            if (potionEffect.getIndexOffset3() == Potion.potion2.indexOffset3) {
               count = potionEffect.getIndexOffset2();
            } else if (potionEffect.getIndexOffset3() == Potion.potion3.indexOffset3) {
               count2 = potionEffect.getIndexOffset2();
            }
         }

         if (count > -1) {
            offset *= 1.0 + 0.2F * (double)(count + 1);
         }

         if (count2 > -1) {
            offset *= 1.0 + -0.15F * (double)(count2 + 1);
         }
      }

      return offset;
   }

   private boolean matchesBlockPos(BlockPos blockPos) {
      return !this.worldObj.getBlockState(blockPos).getBlock().isActive2() && !this.worldObj.getBlockState(blockPos.up()).getBlock().isActive2();
   }

   public Vec3 getPositionVec6() {
      return this.positionVec6;
   }

   @Override
   public void applyVectorDispatcher(VectorDispatcher vectorDispatcher) {
      Minecraft mcClient2 = this.mcClient;
      GeometryCoordinator geometryCoordinator = new GeometryCoordinator(vectorDispatcher);
      mcClient2.displayGuiScreen(geometryCoordinator);
   }

   public void sendChatMessage(String text) {
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         HeuristicInspector heuristicInspector = new HeuristicInspector(text);
         ActiveListener activeListener = SettingsTxtManager.getExtraListener().resolveActiveListenerByEntityPlayerSP(this);
         if (activeListener == null) {
            return;
         }

         activeListener.getCompositeListener().forEach(heuristicInspector);
         if (heuristicInspector.isEnabled()) {
            return;
         }
      }

      if (!EventCommandCallback.resolveEventCommand(text).isCancelled()) {
         this.netHandlerPlayClient.handlePacket(new C01PacketChatMessage(text));
      }
   }

   @Override
   public float getScaleFactor6() {
      Float doubleItem = (float)this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getDouble();
      if (MoveInputListener.resolveEventMoveInput(CombatModeProperty.MOTION_SPRINT).isEnabled()) {
         doubleItem = (float)this.getValue2();
      }

      return Float.valueOf(StepListener.resolveEventStep(doubleItem).getScaleFactor());
   }

   public Vec3 getPositionVec12() {
      return this.positionVec12;
   }

   @Override
   public void applyFloat(float posX, float posY, float posZ) {
      EventRotation event = RotationListener.resolveEventRotation(this.rotationYaw, posY, posX, posZ, BugUpMoveFlyingType.MOVE_FLYING);
      if (!event.isCancelled()) {
         float scaleFactor = event.getScaleFactor1();
         float scaleFactor2 = event.getScaleFactor3();
         float scaleFactor3 = event.getScaleFactor2();
         float factor = scaleFactor * scaleFactor + scaleFactor2 * scaleFactor2;
         if (factor >= 1.0E-4F) {
            float factor2 = MathHelper.sin(factor);
            if (factor2 < 1.0F) {
               factor2 = 1.0F;
            }

            float factor3 = scaleFactor3 / factor2;
            scaleFactor *= factor3;
            scaleFactor2 *= factor3;
            float factor4 = MathHelper.abs(event.getScaleFactor() * (float) Math.PI / 180.0F);
            float factor5 = MathHelper.cos(event.getScaleFactor() * (float) Math.PI / 180.0F);
            this.motionX += (double)(scaleFactor * factor5 - scaleFactor2 * factor4);
            this.motionZ += (double)(scaleFactor2 * factor5 + scaleFactor * factor4);
         }
      }
   }

   public void setCoordOffset55(double coordOffset) {
      this.coordOffset55 = coordOffset;
   }

   private Vec3 resolveVec3(float posX, float posX2, float posX3, float posY, float posZ) {
      if (posX == 1.0F) {
         return this.getVec34(posX3, posX2);
      } else {
         float factor = posZ + (posX3 - posZ) * posX;
         float factor2 = posY + (posX2 - posY) * posX;
         return this.getVec34(factor, factor2);
      }
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ) {
      EventMoveEntity event = MoveEntityListener.resolveEventMoveEntity(posX, posY, posZ, TertiaryOptionType.PRE);
      double coordOffset = event.getCoordOffset2();
      double coordOffset2 = event.getCoordOffset1();
      double coordOffset3 = event.getCoordOffset();
      if (!event.isCancelled()) {
         super.applyDouble(coordOffset, coordOffset2, coordOffset3);
      }

      MoveEntityListener.resolveEventMoveEntity(coordOffset, coordOffset2, coordOffset3, TertiaryOptionType.POST);
   }

   public void setPositionVec12(Vec3 positionVec) {
      this.positionVec12 = positionVec;
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      return false;
   }

   public Vec3 getPositionVec8() {
      return this.positionVec8;
   }

   private boolean matchesKeyBinding(KeyBinding keyBinding) {
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         ActiveListener activeListener = SettingsTxtManager.getExtraListener().resolveActiveListenerByEntityPlayerSP(this);
         if (activeListener == null) {
            return keyBinding.isKeyDown();
         }

         AStarAuditor aStarAuditor = new AStarAuditor();
         activeListener.getCompositeListener().forEach(aStarAuditor);
         if (aStarAuditor.getMemberRef() != null) {
            return aStarAuditor.getMemberRef();
         }

         if (activeListener != SettingsTxtManager.getExtraListener().get()) {
            return false;
         }
      }

      return keyBinding.isKeyDown();
   }

   @Override
   public void applyDamageSource(DamageSource damageSource, float factor) {
      if (!this.matchesDamageSource(damageSource)) {
         this.applyFloat(this.getFloat2() - factor);
      }
   }

   @Override
   public void applyEntityHorse(EntityHorse entityHorse, IInventory iInventory) {
      Minecraft mcClient2 = this.mcClient;
      SplineSynchronizer splineSynchronizer = new SplineSynchronizer(this.inventoryPlayer, iInventory, entityHorse);
      mcClient2.displayGuiScreen(splineSynchronizer);
   }

   public void applyFloat(float factor, int count, int count2) {
      this.factor39 = factor;
      this.count32 = count;
      this.count25 = count2;
   }

   @Override
   public void applyEntity17(Entity entity) {
      if (!((Module)Client.getClient().getModuleManager().attackEffects).isToggled() && entity.getEntityId() != this.mcClient.thePlayer.getEntityId()) {
         this.mcClient.effectRenderer.applyEntity3(entity, AttackEffectsExplosionNormalType.CRIT);
      }
   }

   private Vec3 resolveVec3(float posX, float posY, float posZ) {
      if (posX != 1.0F && posX != 2.0F) {
         float factor = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * posX;
         float factor2 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * posX;
         return this.getVec34(factor, factor2);
      } else {
         return this.getVec34(posZ, posY);
      }
   }

   public void setPositionVec11(Vec3 positionVec) {
      this.positionVec11 = positionVec;
   }

   @Override
   public void applyFloatByFloat(float factor) {
   }

   private Vec3 resolveVec3(float posX, float posY, float posZ, boolean flag) {
      if (posX == 1.0F) {
         return this.getVec34(posZ, posY);
      } else {
         float factor = flag ? this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * posX : posZ;
         float factor2 = flag ? this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * posX : posY;
         return this.getVec34(factor, factor2);
      }
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5);
   }

   @Override
   public Vec3 getVec36(float factor) {
      float factor2 = ClientGlobals.moduleManager.fixes.canExecute10() ? this.rotationYaw : this.scaleFactor12;
      float factor3 = ClientGlobals.moduleManager.fixes.canExecute10() ? this.prevRotationYaw : this.scaleFactor22;
      float pitch = this.rotationPitch;
      if (factor == 1.0F) {
         if (ClientGlobals.moduleManager.fixes.canExecute7()) {
            factor2 %= 360.0F;
         }

         return this.getVec34(pitch, factor2);
      } else {
         float factor4 = this.prevRotationPitch + (pitch - this.prevRotationPitch) * factor;
         float factor5 = factor3 + (factor2 - factor3) * factor;
         return this.getVec34(factor4, factor5);
      }
   }

   public double getCoordOffset55() {
      return this.coordOffset55;
   }

   private boolean matchesInvulnerableManager(InvulnerableManager invulnerableManager) {
      ActiveListener activeListener = SettingsTxtManager.getExtraListener().resolveActiveListenerByEntityPlayerSP(this);
      return activeListener == null ? invulnerableManager.state2 : !activeListener.getCoreListener().isActiveAsFlag() && invulnerableManager.state2;
   }

   public void setCoordOffset59(double coordOffset) {
      this.coordOffset59 = coordOffset;
   }

   public MovingObjectPosition getMovingObjectPosition(Vec3 vec, double offset, float posX, float posY, float posZ) {
      Vec3 vec2 = this.resolveVec3(posX, posY, posZ);
      Vec3 vec3 = vec.getVec312(vec2.xCoord * offset, vec2.yCoord * offset, vec2.zCoord * offset);
      return this.worldObj.getMovingObjectPosition4(vec.xCoord, vec.yCoord, vec.zCoord, vec3.xCoord, vec3.yCoord, vec3.zCoord, false, false, true);
   }

   public void updateState48() {
      this.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(this, EntityPlayerAction.RIDING_JUMP, (int)(this.getScaleFactor18() * 100.0F)));
   }

   public void updateState47() {
      this.inventoryPlayer.setItemStack((ItemStack)null);
      super.updateState5();
      GuiScreen screen = null;
      this.mcClient.displayGuiScreen(screen);
   }

   @Override
   public void updateState38() {
      super.updateState38();
      if (this.canExecute13()) {
         this.scaleFactor24 = this.secondaryFloatEntry.moveStrafe;
         this.scaleFactor3 = this.secondaryFloatEntry.moveForward;
         this.state31 = this.secondaryFloatEntry.state;
         this.scaleFactor14 = this.scaleFactor9;
         this.scaleFactor32 = this.scaleFactor61;
         this.scaleFactor61 = (float)((double)this.scaleFactor61 + (double)(Client.getClient().getViaProtocolHelper().scaleFactor2 - this.scaleFactor61) * 0.5);
         this.scaleFactor9 = (float)((double)this.scaleFactor9 + (double)(Client.getClient().getViaProtocolHelper().scaleFactor1 - this.scaleFactor9) * 0.5);
      }
   }

   public float getScaleFactor18() {
      return this.scaleFactor18;
   }

   public boolean canExecute13() {
      return this.mcClient.getRenderViewEntity() == this;
   }

   public boolean isState40() {
      return this.state40;
   }

   @Override
   public void updateState4() {
      this.netHandlerPlayClient.handlePacket(new C16PacketClientStatus(AutoSoupPerformRespawnType.PERFORM_RESPAWN));
   }

   public float getScaleFactor11() {
      return this.scaleFactor11;
   }

   public boolean isActive30() {
      return this.ridingEntity != null && this.ridingEntity instanceof EntityHorse && ((EntityHorse)this.ridingEntity).isActive34();
   }

   public boolean isInWeb2() {
      return this.isInWeb2;
   }

   @Override
   public void updateState5() {
      this.netHandlerPlayClient.handlePacket(new C0DPacketCloseWindow(this.container2.indexOffset2));
      this.updateState47();
   }

   @Override
   public void updateState43() {
      this.netHandlerPlayClient.handlePacket(new C13PacketPlayerAbilities(this.invulnerableManager));
   }

   public float getScaleFactor27() {
      return this.scaleFactor27;
   }

   public void updateState6() {
      this.indexOffset12 = 0;
   }

   public String getText4() {
      return this.text2;
   }

   @Override
   public void applyEntity16(Entity entity) {
      if (!((Module)Client.getClient().getModuleManager().attackEffects).isToggled() && entity.getEntityId() != this.mcClient.thePlayer.getEntityId()) {
         this.mcClient.effectRenderer.applyEntity3(entity, AttackEffectsExplosionNormalType.CRIT_MAGIC);
      }
   }

   public void setIsInWeb2(boolean isInWeb) {
      this.isInWeb2 = isInWeb;
   }

   public void setState40(boolean state) {
      this.state40 = state;
   }

   public void setState6(boolean state) {
      this.state6 = state;
   }

   @Override
   public boolean isActive24() {
      return true;
   }

   @Override
   public boolean isSprinting() {
      return (this.secondaryFloatEntry != null ? this.secondaryFloatEntry.state2 : false) && !this.state5;
   }

   public void setSprinting(boolean sprinting) {
      EventMotionUpdate event = sprinting
         ? MotionUpdateListener.getEventMotionUpdate(this.onGround, this.rotationYaw, this.rotationPitch, this.posX, this.getBoundingBox2().minY, this.posZ)
         : new EventMotionUpdate(this.onGround, this.rotationYaw, this.rotationPitch, this.posX, this.getBoundingBox2().minY, this.posZ, StateTypes.PRE);
      boolean flag = this.isRiding() && !this.state6;
      if (flag != this.isInWeb2) {
         if (flag) {
            this.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(this, EntityPlayerAction.START_SPRINTING));
         } else {
            this.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(this, EntityPlayerAction.STOP_SPRINTING));
         }

         this.state47 = false;
         this.inWater3 = false;
         this.velocityChanged = false;
         this.isInWeb2 = flag;
      }

      this.state6 = false;
      boolean sprinting2 = this.isSprinting();
      if (sprinting2 != this.state40) {
         if (sprinting2) {
            this.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(this, EntityPlayerAction.START_SNEAKING));
         } else {
            this.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(this, EntityPlayerAction.STOP_SNEAKING));
         }

         this.state40 = sprinting2;
      }

      if (this.canExecute13()) {
         double offset = event.getCoordOffset3() - this.coordOffset55;
         double offset2 = event.getCoordOffset1() - this.coordOffset23;
         double offset3 = event.getCoordOffset() - this.coordOffset59;
         double offset4 = (double)(event.getScaleFactor() - this.scaleFactor11);
         double offset5 = (double)(event.getScaleFactor2() - this.scaleFactor27);
         if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
            this.entityId3++;
         }

         boolean flag2 = offset * offset + offset2 * offset2 + offset3 * offset3 > (ViaLoadingBase.protocolVersion.newerThan(ProtocolVersion.v1_18) ? 4.0E-8 : 9.0E-4)
            || this.entityId3 >= 20;
         boolean flag3 = offset4 != 0.0 || offset5 != 0.0;
         if (this.ridingEntity == null) {
            if (flag2 && flag3) {
               this.netHandlerPlayClient
                  .handlePacket(
                     new C06PacketPlayerPosLook(
                        event.getCoordOffset3(), event.getCoordOffset1(), event.getCoordOffset(), event.getScaleFactor(), event.getScaleFactor2(), event.isState()
                     )
                  );
            } else if (flag2) {
               this.netHandlerPlayClient
                  .handlePacket(new C04PacketPlayerPosition(event.getCoordOffset3(), event.getCoordOffset1(), event.getCoordOffset(), event.isState()));
            } else if (flag3) {
               this.netHandlerPlayClient.handlePacket(new C05PacketPlayerLook(event.getScaleFactor(), event.getScaleFactor2(), event.isState()));
            } else if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8) || this.state3 != this.mcClient.thePlayer.onGround) {
               this.netHandlerPlayClient.handlePacket(new C03PacketPlayer(event.isState()));
            }
         } else {
            this.netHandlerPlayClient
               .handlePacket(new C06PacketPlayerPosLook(this.motionX, -999.0, this.motionZ, event.getScaleFactor(), event.getScaleFactor2(), event.isState()));
            flag2 = false;
         }

         if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            this.entityId3++;
         }

         if (flag2) {
            this.coordOffset55 = event.getCoordOffset3();
            this.coordOffset23 = event.getCoordOffset1();
            this.coordOffset59 = event.getCoordOffset();
            this.entityId3 = 0;
         }

         this.scaleFactor30 = this.scaleFactor11;
         this.scaleFactor20 = this.scaleFactor27;
         if (flag3) {
            this.scaleFactor11 = event.getScaleFactor();
            this.scaleFactor27 = event.getScaleFactor2();
         }
      }

      this.state3 = this.onGround;
      if (this.positionVec7 == null) {
         this.positionVec7 = new Vec3(event.getCoordOffset3(), event.getCoordOffset1(), event.getCoordOffset());
      }

      if (this.positionVec9 == null) {
         this.positionVec9 = new Vec3(event.getCoordOffset3(), event.getCoordOffset1(), event.getCoordOffset());
      }

      this.indexOffset10 = this.positionVec9.xCoord == event.getCoordOffset3()
            && this.positionVec9.yCoord == event.getCoordOffset1()
            && this.positionVec9.zCoord == event.getCoordOffset()
         ? this.indexOffset10
         : 3;
      if (this.indexOffset10 > 0) {
         this.positionVec7 = new Vec3(
            this.positionVec7.xCoord + (event.getCoordOffset3() - this.positionVec7.xCoord) / (double)this.indexOffset10,
            this.positionVec7.yCoord + (event.getCoordOffset1() - this.positionVec7.yCoord) / (double)this.indexOffset10,
            this.positionVec7.zCoord + (event.getCoordOffset() - this.positionVec7.zCoord) / (double)this.indexOffset10
         );
         this.indexOffset10--;
      }

      this.positionVec9 = new Vec3(event.getCoordOffset3(), event.getCoordOffset1(), event.getCoordOffset());
      if (sprinting) {
         MotionUpdateListener.onPostMotionUpdate();
      }

      this.setPositionVec6(this.getPositionVec8());
      this.setPositionVec11(this.getPositionVec12());
   }

   @Override
   public void updateState() {
      if (this.worldObj.checkBlockPos12(new BlockPos(this.posX, 0.0, this.posZ))) {
         if (this.ticksExisted < 2 && this.onGround && !this.isSneaking()) {
            this.positionVec10 = new Vec3(this.posX, this.posY, this.posZ);
            this.positionVec7 = new Vec3(this.posX, this.posY, this.posZ);
         }

         if (UpdateStateListener.resolveEventStateUpdate(StateTypes.PRE).isCancelled()) {
            return;
         }

         super.updateState();
         if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
            ActiveListener activeListener = SettingsTxtManager.getExtraListener().resolveActiveListenerByEntityPlayerSP(this);
            if (activeListener != null) {
               activeListener.getCompositeListener().forEach(new AStarCalculator(SecondaryOptionType.PRE));
            }
         }

         if (this.onGround) {
            this.coordOffset9 = this.posY;
            this.indexOffset67++;
            this.indexOffset41 = 0;
         } else {
            this.indexOffset41++;
            this.indexOffset67 = 0;
         }

         UpdateStateListener.resolveEventStateUpdate(StateTypes.POST);
         if (this.isSneaking()) {
            this.netHandlerPlayClient.handlePacket(new C05PacketPlayerLook(this.rotationYaw, this.rotationPitch, this.onGround));
            this.netHandlerPlayClient
               .handlePacket(new C0CPacketInput(this.scaleFactor24, this.scaleFactor3, this.secondaryFloatEntry.state, this.secondaryFloatEntry.state2));
         } else {
            this.setSprinting(true);
         }
      }
   }

   public void setScaleFactor27(float scaleFactor) {
      this.scaleFactor27 = scaleFactor;
   }

   public void applyFloat5(float factor) {
      if (this.state71) {
         float factor2 = this.getFloat2() - factor;
         if (factor2 <= 0.0F) {
            this.applyFloat(factor);
            if (factor2 < 0.0F) {
               this.hurtResistantTime = this.count14 / 2;
            }
         } else {
            this.factor13 = factor2;
            this.applyFloat(this.getFloat2());
            this.hurtResistantTime = this.count14;
            this.applyDamageSource(DamageSource.damageSource12, factor2);
            this.hurtTime = this.count12 = 10;
            this.maxHurtTime = this.count12 = 10;
            this.count15 = this.count12 = 10;
         }
      } else {
         this.applyFloat(factor);
         this.state71 = true;
      }
   }

   @Override
   public void closeScreen() {
      EventRotation eventRotation = RotationListener.getEventRotation(this.rotationYaw, BugUpMoveFlyingType.JUMP);
      if (!eventRotation.isCancelled()) {
         this.motionY = (double)this.getFloat6();
         if (this.checkPotion(Potion.potion9)) {
            this.motionY = this.motionY + (double)((float)(this.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
         }

         EventMoveInput event = MoveInputListener.resolveEventMoveInput(CombatModeProperty.JUMP_SPRINT);
         if ((this.isRiding() || event.isEnabled5()) && event.isEnabled7()) {
            if (event.isEnabled5()) {
               if (this.mcClient.thePlayer.scaleFactor3 < 0.0F) {
                  float factor = eventRotation.getScaleFactor() - 180.0F;
                  eventRotation.setScaleFactor(factor);
               }

               if (this.mcClient.thePlayer.scaleFactor24 != 0.0F && this.mcClient.thePlayer.scaleFactor3 == 0.0F) {
                  if (this.mcClient.thePlayer.scaleFactor24 > 0.0F) {
                     float factor2 = eventRotation.getScaleFactor() - 45.0F;
                     eventRotation.setScaleFactor(factor2);
                  } else if (this.mcClient.thePlayer.scaleFactor24 < 0.0F) {
                     float factor3 = eventRotation.getScaleFactor() + 45.0F;
                     eventRotation.setScaleFactor(factor3);
                  }
               }
            }

            float factor4 = eventRotation.getScaleFactor() * (float) (Math.PI / 180.0);
            this.motionX = this.motionX - (double)(MathHelper.abs(factor4) * 0.2F);
            this.motionZ = this.motionZ + (double)(MathHelper.cos(factor4) * 0.2F);
         }

         this.enabled4 = true;
         this.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager30);
         if (this.isRiding()) {
            this.applyFloat4(0.8F);
         } else {
            this.applyFloat4(0.2F);
         }
      }
   }

   @Override
   public void updateState33() {
      if (this.deathTime > 0) {
         this.deathTime--;
         if (this.deathTime == 0) {
            this.setState3(false);
         }
      }

      if (this.indexOffset52 > 0) {
         this.indexOffset52--;
      }

      this.scaleFactor16 = this.scaleFactor7;
      if (this.enabled7) {
         if (this.mcClient.currentScreen != null && !this.mcClient.currentScreen.isActive()) {
            GuiScreen screen = null;
            this.mcClient.displayGuiScreen(screen);
         }

         if (this.scaleFactor7 == 0.0F) {
            this.mcClient
               .getMcSoundHandler()
               .applyISound2(PositionedSoundRecord.createButtonPress(new ResourceLocation("portal.trigger"), this.random.nextFloat() * 0.4F + 0.8F));
         }

         this.scaleFactor7 += 0.0125F;
         if (this.scaleFactor7 >= 1.0F) {
            this.scaleFactor7 = 1.0F;
         }

         this.enabled7 = false;
      } else if (this.checkPotion(Potion.potion15) && this.getPotionEffect(Potion.potion15).getIndexOffset() > 60) {
         this.scaleFactor7 += 0.006666667F;
         if (this.scaleFactor7 > 1.0F) {
            this.scaleFactor7 = 1.0F;
         }
      } else {
         if (this.scaleFactor7 > 0.0F) {
            this.scaleFactor7 -= 0.05F;
         }

         if (this.scaleFactor7 < 0.0F) {
            this.scaleFactor7 = 0.0F;
         }
      }

      if (this.count10 > 0) {
         this.count10--;
      }

      boolean state = this.secondaryFloatEntry.state;
      boolean state2 = this.secondaryFloatEntry.state2;
      float factor = 0.8F;
      boolean flag = this.secondaryFloatEntry.moveForward >= factor;
      this.secondaryFloatEntry.applyFloat(this.secondaryFloatEntry.moveForward, this.secondaryFloatEntry.moveStrafe);
      EventCameraTransform eventCameraTransform = EventCameraTransformCallback.getEventCameraTransform(0.2F, 0.2F);
      if (this.isUsingItem() && !this.isSneaking()) {
         this.secondaryFloatEntry.moveStrafe = this.secondaryFloatEntry.moveStrafe * eventCameraTransform.getScaleFactor2();
         this.secondaryFloatEntry.moveForward = this.secondaryFloatEntry.moveForward * eventCameraTransform.getScaleFactor();
         this.indexOffset52 = 0;
      }

      boolean flag2 = false;
      if (this.indexOffset28 > 0 && this.mcClient.isIntegratedServerRunning()) {
         this.indexOffset28--;
         flag2 = true;
         this.secondaryFloatEntry.state = true;
      }

      this.checkDouble(this.posX - (double)this.width * 0.35, this.getBoundingBox2().minY + 0.5, this.posZ + (double)this.width * 0.35);
      this.checkDouble(this.posX - (double)this.width * 0.35, this.getBoundingBox2().minY + 0.5, this.posZ - (double)this.width * 0.35);
      this.checkDouble(this.posX + (double)this.width * 0.35, this.getBoundingBox2().minY + 0.5, this.posZ - (double)this.width * 0.35);
      this.checkDouble(this.posX + (double)this.width * 0.35, this.getBoundingBox2().minY + 0.5, this.posZ + (double)this.width * 0.35);
      EventMoveInput event = MoveInputListener.resolveEventMoveInput(CombatModeProperty.SPRINT);
      MoveListener.resolveEventMove(
         this.secondaryFloatEntry.moveForward,
         this.secondaryFloatEntry.moveStrafe,
         this.secondaryFloatEntry.state,
         this.secondaryFloatEntry.state2,
         ArrowDodgerPhase.POST
      );
      boolean flag3 = (float)this.getFoodLevelManager().getIndex() > 6.0F || event.isEnabled4() || this.matchesInvulnerableManager(this.invulnerableManager);
      float moveForward = this.secondaryFloatEntry.moveForward;
      if (this.state47 || this.inWater3) {
         this.secondaryFloatEntry.moveForward = 0.0F;
      }

      if (this.velocityChanged) {
         this.secondaryFloatEntry.moveForward = -1.0F;
      }

      boolean flag4 = event.isEnabled8() ? event.isEnabled2() : this.matchesKeyBinding(this.mcClient.gameSettings.keyBindSprint);
      if (this.isRiding() && event.isEnabled8() && !event.isEnabled2() && this.secondaryFloatEntry.moveForward >= factor) {
         this.secondaryFloatEntry.moveForward = 0.0F;
      }

      if (this.onGround
         && !state2
         && !flag
         && this.secondaryFloatEntry.moveForward >= factor
         && !this.isRiding()
         && flag3
         && (!this.isUsingItem() || eventCameraTransform.isEnabled())
         && !this.checkPotion(Potion.potion16)) {
         if (this.indexOffset52 <= 0 && !flag4) {
            this.indexOffset52 = 7;
         } else {
            this.setState3(true);
         }
      }

      if (!this.isRiding()
         && this.secondaryFloatEntry.moveForward >= factor
         && flag3
         && (!this.isUsingItem() || eventCameraTransform.isEnabled())
         && !this.checkPotion(Potion.potion16)
         && flag4) {
         this.setState3(true);
      }

      if (this.isRiding() && (this.secondaryFloatEntry.moveForward < factor || this.isCollidedHorizontally && !event.isEnabled6() || !flag3)) {
         this.setState3(false);
      }

      if (!eventCameraTransform.isEnabled() && this.isUsingItem() && !this.isSneaking()) {
         this.setState3(false);
      }

      if (event.isEnabled3() && !this.state47 && !this.inWater3 && !this.velocityChanged) {
         this.setState3(true);
      }

      if (this.state47) {
         this.secondaryFloatEntry.moveForward = moveForward;
         this.state47 = false;
      }

      if (this.inWater3) {
         this.inWater3 = false;
      }

      if (this.velocityChanged) {
         this.velocityChanged = false;
      }

      if (this.matchesInvulnerableManager(this.invulnerableManager)) {
         if (this.mcClient.playerController.isActive2()) {
            if (!this.invulnerableManager.state) {
               this.invulnerableManager.state = true;
               this.updateState43();
            }
         } else if (!state && this.secondaryFloatEntry.state && !flag2) {
            if (this.count26 == 0) {
               this.count26 = 7;
            } else {
               this.invulnerableManager.state = !this.invulnerableManager.state;
               this.updateState43();
               this.count26 = 0;
            }
         }
      }

      if (this.secondaryFloatEntry.state2
         && this.isInWater()
         && !this.invulnerableManager.state
         && ((Module)ClientGlobals.moduleManager.jesus).isToggled()
         && ClientGlobals.moduleManager.jesus.settingMode.getSelected().equals("1.13")) {
         this.motionY += -0.04F;
      }

      if (this.invulnerableManager.state && this.canExecute13()) {
         if (this.secondaryFloatEntry.state2) {
            this.motionY = this.motionY - (double)(this.invulnerableManager.getFactor() * 3.0F);
         }

         if (this.secondaryFloatEntry.state) {
            this.motionY = this.motionY + (double)(this.invulnerableManager.getFactor() * 3.0F);
         }
      }

      if (this.isActive30()) {
         if (this.indexOffset44 < 0) {
            this.indexOffset44++;
            if (this.indexOffset44 == 0) {
               this.scaleFactor18 = 0.0F;
            }
         }

         if (state && !this.secondaryFloatEntry.state) {
            this.indexOffset44 = -10;
            this.updateState48();
         } else if (!state && this.secondaryFloatEntry.state) {
            this.indexOffset44 = 0;
            this.scaleFactor18 = 0.0F;
         } else if (state) {
            this.indexOffset44++;
            if (this.indexOffset44 < 10) {
               this.scaleFactor18 = (float)this.indexOffset44 * 0.1F;
            } else {
               this.scaleFactor18 = 0.8F + 2.0F / (float)(this.indexOffset44 - 9) * 0.1F;
            }
         }
      } else {
         this.scaleFactor18 = 0.0F;
      }

      super.updateState33();
      if (this.onGround && this.invulnerableManager.state && !this.mcClient.playerController.isActive2()) {
         this.invulnerableManager.state = false;
         this.updateState43();
      }

      BacktrackRecord backtrackRecord = this.backtrackRecord5;
      BoundingBoxDimension boundingBoxDimension = new BoundingBoxDimension(this);
      backtrackRecord.applyObject(boundingBoxDimension);
   }

   @Override
   public void respawnPlayer() {
      this.mcClient.thePlayer.updateState6();
      EventSneak eventSneak = SneakListener.getEventSneak();
      if (!this.state2 || this.indexOffset20 >= this.getInt3() / 2 || this.indexOffset20 < 0) {
         if (!eventSneak.canExecute2()) {
            this.indexOffset20 = -1;
            this.state2 = true;
         }

         if (this.worldObj instanceof WorldServer) {
            ((WorldServer)this.worldObj).getSecondaryLoggerEntry().applyEntity3(this, new S0BPacketAnimation(this, 0));
         }
      }

      if (!eventSneak.canExecute()) {
         if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            this.netHandlerPlayClient.handlePacket(new C0APacketAnimation());
         } else {
            this.netHandlerPlayClient.handlePacket(new CAnimateHandPacket(EnumHand.MAIN_HAND));
         }
      }
   }

   public EntityPlayerSP(Minecraft mc, World world, NetHandlerPlayClient netHandlerPlayClient2, StatFileWriter statFileWriter2) {
      super(world, netHandlerPlayClient2.getGameProfile());
      this.netHandlerPlayClient = netHandlerPlayClient2;
      this.statFileWriter = statFileWriter2;
      this.mcClient = mc;
      this.count4 = 0;
   }
}
