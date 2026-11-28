package net.augustus.commands.impl;

import net.augustus.Augustus;
import net.augustus.commands.Command;
import net.augustus.events.EventManager;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.render.RenderUtil;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.pathfinding.AStarPathFinder;
import net.augustus.utils.pathfinding.PathBlockPos;
import net.augustus.utils.pathfinding.PathNode;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class PathFinderCommand extends Command implements RenderListener, UpdateListener, PacketListener {
   private boolean teleportingAlongPath;
   private float colorGreen;
   private float colorBlue;
   public AStarPathFinder pathFinder;
   private ArrayList pathNodes;
   private int currentNodeIndex;
   private boolean renderingEnabled;
   private float colorRed;

   private void unregisterListeners() {
      EventManager.getEventBus().unsubscribe(43, this);
      EventManager.getEventBus().unsubscribe(32, this);
      EventManager.getEventBus().unsubscribe(26, this);
   }

   private void registerListeners() {
      EventManager.getEventBus().subscribe(43, this);
      EventManager.getEventBus().subscribe(32, this);
      EventManager.getEventBus().subscribe(26, this);
   }

   public void onRender3D(float partialTicks) {
      if (this.pathNodes != null && !this.pathNodes.isEmpty()) {
         GL11.glPushMatrix();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GL11.glEnable(2848);
         GlStateManager.disableDepth();
         GlStateManager.disableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.disableTexture2D();
         }

         GlStateManager.disableCull();
         GlStateManager.depthMask(false);
         GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
         GlStateManager.color(this.colorRed, this.colorGreen, this.colorBlue, 1.0F);
         if (this.pathNodes != null) {
            GL11.glBegin(3);

            for (PathNode pathNode : (Iterable<PathNode>)(this.pathNodes)) {
               GL11.glVertex3d((double)pathNode.getPos().x + 0.5, (double)pathNode.getPos().y + 0.01, (double)pathNode.getPos().z + 0.5);
            }

            GL11.glEnd();

            for (PathNode pathNode2 : (Iterable<PathNode>)(this.pathNodes)) {
               RenderUtil.drawFilledBox(AxisAlignedBB.fromCenter(pathNode2.getPos().toVec3().addVector(0.5, 0.01, 0.5), 0.1));
            }
         }

         GL11.glPopMatrix();
         GlStateManager.depthMask(true);
         GlStateManager.enableCull();
         GlStateManager.enableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.enableTexture2D();
         }

         GlStateManager.enableDepth();
         GlStateManager.disableBlend();
         GlStateManager.blendFunc(770, 771);
         GL11.glDisable(2848);
      }
   }

   public PathFinderCommand() {
      super("pathfinder");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (args.length > 1) {
         if (args[1].equalsIgnoreCase("toggle")) {
            this.renderingEnabled = !this.renderingEnabled;
            if (this.renderingEnabled) {
               this.sendChat("Enabled pathrendering");
               this.registerListeners();
            } else {
               this.sendChat("Disabled pathrendering");
               this.unregisterListeners();
            }
         } else {
            if (args[1].equalsIgnoreCase("teleportalong")) {
               if (!this.teleportingAlongPath && this.pathNodes != null && this.pathNodes.size() > 0) {
                  this.teleportingAlongPath = true;
                  this.currentNodeIndex = 0;
                  if (!this.renderingEnabled) {
                     this.sendChat("Enabled pathrendering");
                     this.registerListeners();
                     this.renderingEnabled = true;
                  }

                  this.sendChat("Teleporting along path");
               } else {
                  this.teleportingAlongPath = false;
               }
            }

            if (args[1].equalsIgnoreCase("color")) {
               if (args.length > 4) {
                  int n = Integer.parseInt(args[2]);
                  int n2 = Integer.parseInt(args[3]);
                  int n3 = Integer.parseInt(args[4]);
                  n = MathHelper.clamp_int(n, 0, 255);
                  n2 = MathHelper.clamp_int(n2, 0, 255);
                  n3 = MathHelper.clamp_int(n3, 0, 255);
                  this.colorRed = (float)n / 255.0F;
                  this.colorGreen = (float)n2 / 255.0F;
                  this.colorBlue = (float)n3 / 255.0F;
                  this.sendChat("Set color! (r=" + n + " g=" + n2 + " b=" + n3 + ")");
               }
            } else {
               if (args.length > 3) {
                  int n4 = 0;
                  int n5 = 0;
                  int n6 = 0;

                  try {
                     if (args[1].equals("~")) {
                        n4 = (int)Math.floor(mc.thePlayer.posX);
                     } else {
                        n4 = Integer.parseInt(args[1]);
                     }

                     if (args[2].equals("~")) {
                        n5 = (int)Math.floor(mc.thePlayer.posY);
                     } else {
                        n5 = Integer.parseInt(args[2]);
                     }

                     if (args[3].equals("~")) {
                        n6 = (int)Math.floor(mc.thePlayer.posZ);
                     } else {
                        n6 = Integer.parseInt(args[3]);
                     }
                  } catch (Throwable e) {
                     this.sendChat("Parsing failed");
                     return;
                  }

                  this.sendChat("Calculating a path to (x=" + n4 + " y=" + n5 + " z=" + n6 + ")");
                  Vec3 interpolatedPos = mc.getRenderViewEntity().getInterpolatedPos(1.0F);
                  PathBlockPos pathBlockPos = new PathBlockPos((int)Math.floor(interpolatedPos.xCoord), (int)Math.floor(interpolatedPos.yCoord), (int)Math.floor(interpolatedPos.zCoord));
                  PathBlockPos pathBlockPos2 = new PathBlockPos(n4, n5, n6);
                  long currentTimeMs = System.currentTimeMillis();
                  boolean isToggled = Augustus.getInstance().getModuleManager().jesus.isToggled();
                  boolean flag = isToggled && Augustus.getInstance().getModuleManager().jesus.mode.getSelectedValue().equalsIgnoreCase("dolphin");
                  AStarPathFinder aStarPathFinder = new AStarPathFinder(pathBlockPos, pathBlockPos2, isToggled && !flag, flag, false);
                  aStarPathFinder.smoothPath = true;
                  ArrayList list = aStarPathFinder.computePath(true, 10000L);
                  long l3 = System.currentTimeMillis() - currentTimeMs;
                  if (list == null) {
                     this.sendChat("Exceeded timelimit");
                     return;
                  }

                  if (list.isEmpty()) {
                     this.sendChat("No path was found");
                     return;
                  }

                  Collections.reverse(list);
                  this.pathNodes = list;
                  double d = 0.0;
                  PathBlockPos pathBlockPos3 = null;

                  for (PathNode pathNode : (Iterable<PathNode>)(list)) {
                     if (pathBlockPos3 != null) {
                        double distance = pathBlockPos3.distanceTo(pathNode.getPos());
                        d += distance;
                     }

                     pathBlockPos3 = pathNode.getPos();
                  }

                  String str11 = "Found a "
                     + MathStatisticsUtil.formatRounded(d, 2)
                     + " blocks long path! (cost="
                     + ((PathNode)list.get(list.size() - 1)).getGCost()
                     + ") (took "
                     + MathStatisticsUtil.formatRounded((double)l3 / 1000.0, 2)
                     + "s ("
                     + l3
                     + "ms))";
                  this.sendChat(str11);
                  if (!this.renderingEnabled) {
                     this.sendChat("Enabled pathrendering");
                     this.registerListeners();
                     this.renderingEnabled = true;
                  }
               }
            }
         }
      } else {
         this.sendChat(this.getCommand() + " [§2x§7] [§2y§7] [§2z§7]");
         this.sendChat(this.getCommand() + " [§ccolor§7] [§2r§7] [§2g§7] [§2b§7]");
         this.sendChat(this.getCommand() + " [§ctoggle§7]");
      }
   }

   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S08PacketPlayerPosLook) {
         S08PacketPlayerPosLook packet = (S08PacketPlayerPosLook)event.getPacket();
         if (this.pathNodes != null && this.pathNodes.size() > 0 && this.currentNodeIndex < this.pathNodes.size() && this.teleportingAlongPath) {
            Vec3 vec = new Vec3(packet.getX(), packet.getY(), packet.getZ());
            double d = 1.7976931348623157E308;
            int n = 0;

            for (int i = 0; i < this.pathNodes.size(); i++) {
               double distance = ((PathNode)this.pathNodes.get(i)).getPos().toVec3().distanceTo(vec);
               if (d > distance) {
                  d = distance;
                  n = i;
               }
            }

            if (d > 10.0) {
               this.teleportingAlongPath = false;
               return;
            }

            this.currentNodeIndex = n;
         }
      }
   }

   public void onPostUpdate() {
      if (this.pathNodes != null && !this.pathNodes.isEmpty() && this.currentNodeIndex < this.pathNodes.size() && this.teleportingAlongPath) {
         boolean flag = false;
         boolean flag2 = false;
         boolean flag3 = false;
         Vec3 interpolatedPos = mc.thePlayer.getInterpolatedPos(1.0F);
         Vec3 vec = new Vec3(Math.floor(interpolatedPos.xCoord), Math.floor(interpolatedPos.yCoord), Math.floor(interpolatedPos.zCoord)).addVector(0.5, 0.0, 0.5);
         Vec3 vec2 = null;
         int n = 0;

         for (int i = this.currentNodeIndex; i < this.pathNodes.size(); i++) {
            PathNode pathNode = (PathNode)this.pathNodes.get(i);
            Vec3 vec3 = pathNode.getPos().toVec3().addVector(0.5, 0.0, 0.5);
            if (mc.theWorld.getBlockState(pathNode.getPos().toBlockPos()).getBlock() instanceof BlockStairs) {
               vec3 = vec3.addVector(0.0, 1.0, 0.0);
            }

            if (vec2 == null) {
               vec2 = vec3;
            }

            if (vec != null) {
               if (!flag) {
                  flag = vec.xCoord != vec3.xCoord;
                  if (flag) {
                     n++;
                  }
               }

               if (!flag2) {
                  flag2 = vec.yCoord != vec3.yCoord;
                  if (flag2) {
                     n++;
                  }
               }

               if (!flag3) {
                  flag3 = vec.zCoord != vec3.zCoord;
                  if (flag3) {
                     n++;
                  }
               }

               if (n >= 2 || !(vec2.distanceTo(mc.thePlayer.getInterpolatedPos(1.0F)) <= 9.0)) {
                  break;
               }

               vec2 = vec3;
            }

            vec = vec3;
            this.currentNodeIndex = i + 1;
         }

         mc.thePlayer.setPosition(vec2.xCoord, vec2.yCoord, vec2.zCoord);
      } else {
         this.teleportingAlongPath = false;
      }
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + " §7(calculates a path to a specified location)");
   }

}
