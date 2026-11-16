package client.util;

import client.Client;
import client.event.EventBusManager;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.UpdateStateListener;
import client.module.Module;
import client.render.PathRenderer;
import client.render.RenderUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.block.BlockPlaceData;
import net.minecraft.block.BlockRedSandstoneStairs;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class GenericStringEntryAdapter extends AuxiliaryStringEntry implements UpdateStateListener, PacketListener, RenderListener {
   public boolean enabled;
   public ArrayList arrayList;
   public boolean enabled2;
   public int count;
   public float factor;
   public float factor2;
   public PathRenderer pathRenderer;
   public float factor3;

   private void updateState2() {
      EventBusManager.getDietrichEvents2().subscribe(43, this);
      EventBusManager.getDietrichEvents2().subscribe(32, this);
      EventBusManager.getDietrichEvents2().subscribe(26, this);
   }

   @Override
   public void updateState() {
      String text = "§c" + this.getString() + " §7(calculates a path to a specified location)";
      this.applyText(text);
   }

   private void updateState3() {
      EventBusManager.getDietrichEvents2().unsubscribe(43, this);
      EventBusManager.getDietrichEvents2().unsubscribe(32, this);
      EventBusManager.getDietrichEvents2().unsubscribe(26, this);
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      if (packet.getPacket() instanceof S08PacketPlayerPosLook) {
         S08PacketPlayerPosLook packet2 = (S08PacketPlayerPosLook)packet.getPacket();
         if (this.arrayList != null && this.arrayList.size() > 0 && this.count < this.arrayList.size() && this.enabled) {
            Vec3 vec = new Vec3(packet2.getCoordinate(), packet2.getCoordinate1(), packet2.getCoordinate4());
            double offset = Double.MAX_VALUE;
            int count2 = 0;

            for (int index = 0; index < this.arrayList.size(); index++) {
               double coordinate = ((PathNode)this.arrayList.get(index)).getBlockPlaceData().getVec3().getCoordinate4(vec);
               if (offset > coordinate) {
                  offset = coordinate;
                  count2 = index;
               }
            }

            if (offset > 10.0) {
               this.enabled = false;
               return;
            }

            this.count = count2;
         }
      }
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length > 1) {
         if (textArray[1].equalsIgnoreCase("toggle")) {
            this.enabled2 = !this.enabled2;
            if (this.enabled2) {
               String text2 = "Enabled pathrendering";
               this.applyText(text2);
               this.updateState2();
            } else {
               String text3 = "Disabled pathrendering";
               this.applyText(text3);
               this.updateState3();
            }
         } else {
            if (textArray[1].equalsIgnoreCase("teleportalong")) {
               if (!this.enabled && this.arrayList != null && this.arrayList.size() > 0) {
                  this.enabled = true;
                  this.count = 0;
                  if (!this.enabled2) {
                     String text4 = "Enabled pathrendering";
                     this.applyText(text4);
                     this.updateState2();
                     this.enabled2 = true;
                  }

                  String text5 = "Teleporting along path";
                  this.applyText(text5);
               } else {
                  this.enabled = false;
               }
            }

            if (textArray[1].equalsIgnoreCase("color")) {
               if (textArray.length > 4) {
                  int count2 = Integer.parseInt(textArray[2]);
                  int count3 = Integer.parseInt(textArray[3]);
                  int count4 = Integer.parseInt(textArray[4]);
                  int intItem = MathHelper.getInt12(count2, 0, 255);
                  int intItem2 = MathHelper.getInt12(count3, 0, 255);
                  int intItem3 = MathHelper.getInt12(count4, 0, 255);
                  this.factor2 = (float)intItem / 255.0F;
                  this.factor3 = (float)intItem2 / 255.0F;
                  this.factor = (float)intItem3 / 255.0F;
                  String text6 = "Set color! (r=" + intItem + " g=" + intItem2 + " b=" + intItem3 + ")";
                  this.applyText(text6);
               }
            } else {
               if (textArray.length > 3) {
                  int count5;
                  int count6;
                  int count7;
                  try {
                     if (textArray[1].equals("~")) {
                        count5 = (int)Math.floor(MinecraftAccessor.minecraft.thePlayer.posX);
                     } else {
                        count5 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[2].equals("~")) {
                        count6 = (int)Math.floor(MinecraftAccessor.minecraft.thePlayer.posY);
                     } else {
                        count6 = Integer.parseInt(textArray[2]);
                     }

                     if (textArray[3].equals("~")) {
                        count7 = (int)Math.floor(MinecraftAccessor.minecraft.thePlayer.posZ);
                     } else {
                        count7 = Integer.parseInt(textArray[3]);
                     }
                  } catch (Throwable throwable) {
                     String text7 = "Parsing failed";
                     this.applyText(text7);
                     return;
                  }

                  String text8 = "Calculating a path to (x=" + count5 + " y=" + count6 + " z=" + count7 + ")";
                  this.applyText(text8);
                  Vec3 vec3 = MinecraftAccessor.minecraft.getRenderViewEntity().getVec32(1.0F);
                  BlockPlaceData blockPlaceData = new BlockPlaceData((int)Math.floor(vec3.xCoord), (int)Math.floor(vec3.yCoord), (int)Math.floor(vec3.zCoord));
                  BlockPlaceData blockPlaceData2 = new BlockPlaceData(count5, count6, count7);
                  long timestamp = System.currentTimeMillis();
                  boolean toggled = ((Module)Client.getClient().getModuleManager().jesus).isToggled();
                  boolean flag = toggled && Client.getClient().getModuleManager().jesus.settingMode.getSelected().equalsIgnoreCase("dolphin");
                  PathRenderer pathRenderer2 = new PathRenderer(blockPlaceData, blockPlaceData2, toggled && !flag, flag, false);
                  pathRenderer2.state1 = true;
                  ArrayList arrayList2 = pathRenderer2.getArrayList2(true, 10000L);
                  long value = System.currentTimeMillis() - timestamp;
                  if (arrayList2 == null) {
                     String text9 = "Exceeded timelimit";
                     this.applyText(text9);
                     return;
                  }

                  if (arrayList2.isEmpty()) {
                     String text10 = "No path was found";
                     this.applyText(text10);
                     return;
                  }

                  Collections.reverse(arrayList2);
                  this.arrayList = arrayList2;
                  double offset = 0.0;
                  BlockPlaceData blockPlaceData3 = null;

                  for (PathNode pathNode : (Iterable<PathNode>)(Iterable<?>)arrayList2) {
                     if (blockPlaceData3 != null) {
                        BlockPlaceData blockPlaceData4 = pathNode.getBlockPlaceData();
                        double coordinate = blockPlaceData3.getCoordinate(blockPlaceData4);
                        offset += coordinate;
                     }

                     blockPlaceData3 = pathNode.getBlockPlaceData();
                  }

                  String text11 = "Found a "
                     + PlayerUtil.getString(offset, 2)
                     + " blocks long path! (cost="
                     + ((PathNode)arrayList2.get(arrayList2.size() - 1)).getDouble()
                     + ") (took "
                     + PlayerUtil.getString((double)value / 1000.0, 2)
                     + "s ("
                     + value
                     + "ms))";
                  this.applyText(text11);
                  if (!this.enabled2) {
                     String text12 = "Enabled pathrendering";
                     this.applyText(text12);
                     this.updateState2();
                     this.enabled2 = true;
                  }
               }
            }
         }
      } else {
         String text13 = this.getString() + " [§2x§7] [§2y§7] [§2z§7]";
         this.applyText(text13);
         String text14 = this.getString() + " [§ccolor§7] [§2r§7] [§2g§7] [§2b§7]";
         this.applyText(text14);
         String text15 = this.getString() + " [§ctoggle§7]";
         this.applyText(text15);
      }
   }

   @Override
   public void onRender3D(float factor4) {
      if (this.arrayList != null && !this.arrayList.isEmpty()) {
         GL11.glPushMatrix();
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glEnable(2848);
         GL11.glDisable(2929);
         GL11.glDisable(3553);
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState();
         }

         GlStateManager.disableAlpha();
         GL11.glDepthMask(false);
         GL11.glTranslated(
            -MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(),
            -MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(),
            -MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ()
         );
         GlStateManager.color4f2(this.factor2, this.factor3, this.factor, 1.0F);
         if (this.arrayList != null) {
            GL11.glBegin(3);

            for (PathNode pathNode : (Iterable<PathNode>)(Iterable<?>)this.arrayList) {
               GL11.glVertex3d(
                  (double)pathNode.getBlockPlaceData().indexOffset1 + 0.5,
                  (double)pathNode.getBlockPlaceData().indexOffset2 + 0.01,
                  (double)pathNode.getBlockPlaceData().indexOffset + 0.5
               );
            }

            GL11.glEnd();
            Iterator iterator = this.arrayList.iterator();

            while (iterator.hasNext()) {
               RenderUtils.drawBoundingBox(AxisAlignedBB.getAxisAlignedBB(((PathNode)iterator.next()).getBlockPlaceData().getVec3().getVec312(0.5, 0.01, 0.5), 0.1));
            }
         }

         GL11.glPopMatrix();
         GL11.glDepthMask(true);
         GlStateManager.enableLighting();
         GL11.glEnable(3553);
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState2();
         }

         GL11.glEnable(2929);
         GL11.glDisable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glDisable(2848);
      }
   }

   @Override
   public void onPreUpdateState() {
      if (this.arrayList != null && !this.arrayList.isEmpty() && this.count < this.arrayList.size() && this.enabled) {
         boolean flag = false;
         boolean flag2 = false;
         boolean flag3 = false;
         Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec32(1.0F);
         Vec3 vec32 = new Vec3(Math.floor(vec3.xCoord), Math.floor(vec3.yCoord), Math.floor(vec3.zCoord)).getVec312(0.5, 0.0, 0.5);
         Vec3 vec = null;
         int count2 = 0;

         for (int index = this.count; index < this.arrayList.size(); index++) {
            PathNode pathNode = (PathNode)this.arrayList.get(index);
            Vec3 vec33 = pathNode.getBlockPlaceData().getVec3().getVec312(0.5, 0.0, 0.5);
            if (MinecraftAccessor.minecraft.theWorld.getBlockState(pathNode.getBlockPlaceData().getBlockPos()).getBlock() instanceof BlockRedSandstoneStairs) {
               vec33 = vec33.getVec312(0.0, 1.0, 0.0);
            }

            if (vec == null) {
               vec = vec33;
            }

            if (vec32 != null) {
               if (!flag) {
                  flag = vec32.xCoord != vec33.xCoord;
                  if (flag) {
                     count2++;
                  }
               }

               if (!flag2) {
                  flag2 = vec32.yCoord != vec33.yCoord;
                  if (flag2) {
                     count2++;
                  }
               }

               if (!flag3) {
                  flag3 = vec32.zCoord != vec33.zCoord;
                  if (flag3) {
                     count2++;
                  }
               }

               if (count2 >= 2 || !(vec.getCoordinate4(MinecraftAccessor.minecraft.thePlayer.getVec32(1.0F)) <= 9.0)) {
                  break;
               }

               vec = vec33;
            }

            vec32 = vec33;
            this.count = index + 1;
         }

         MinecraftAccessor.minecraft.thePlayer.processDouble2(vec.xCoord, vec.yCoord, vec.zCoord);
      } else {
         this.enabled = false;
      }
   }

   public GenericStringEntryAdapter() {
      super("pathfinder");
   }
}
