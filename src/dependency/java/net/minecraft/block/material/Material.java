package net.minecraft.block.material;

import client.util.FovMonitor;
import client.util.FovPredictor;
import client.util.FovSynchronizer;
import client.util.FovWatcher;

public class Material {
   public static final Material coreItemEntry = new FovMonitor(MapColor.mapColor19);
   public static final Material material30 = new Material(MapColor.mapColor17);
   public static final Material material32 = new Material(MapColor.mapColor4);
   public static final Material material = new Material(MapColor.mapColor18).getMaterial2();
   public static final Material material4 = new Material(MapColor.mapColor10).getMaterial4();
   public static final Material material5 = new Material(MapColor.mapColor34).getMaterial4();
   public static final Material material31 = new Material(MapColor.mapColor34).getMaterial4().getMaterial6();
   public static final Material coreItemEntry1 = new FovPredictor(MapColor.mapColor9).getMaterial3();
   public static final Material material29 = new FovPredictor(MapColor.mapColor8).getMaterial3();
   public static final Material material9 = new Material(MapColor.mapColor26).getMaterial2().getMaterial5().getMaterial3();
   public static final Material material24 = new FovWatcher(MapColor.mapColor26).getMaterial3();
   public static final Material material7 = new FovWatcher(MapColor.mapColor26).getMaterial2().getMaterial3().getMaterial();
   public static final Material material2 = new Material(MapColor.mapColor3);
   public static final Material material19 = new Material(MapColor.mapColor22).getMaterial2();
   public static final Material material6 = new FovMonitor(MapColor.mapColor19).getMaterial3();
   public static final Material material28 = new Material(MapColor.mapColor20);
   public static final Material material22 = new FovWatcher(MapColor.mapColor19).getMaterial3();
   public static final Material material23 = new FovWatcher(MapColor.mapColor22).getMaterial2();
   public static final Material material26 = new Material(MapColor.mapColor19).getMaterial5().getMaterialAsMaterial();
   public static final Material material25 = new Material(MapColor.mapColor19).getMaterialAsMaterial();
   public static final Material material8 = new Material(MapColor.mapColor8).getMaterial2().getMaterial5();
   public static final Material material16 = new Material(MapColor.mapColor26).getMaterial3();
   public static final Material material12 = new Material(MapColor.mapColor32).getMaterial5().getMaterialAsMaterial();
   public static final Material material14 = new Material(MapColor.mapColor32).getMaterialAsMaterial();
   public static final Material material18 = new FovWatcher(MapColor.mapColor6).getMaterial().getMaterial5().getMaterial4().getMaterial3();
   public static final Material material11 = new Material(MapColor.mapColor6).getMaterial4();
   public static final Material material21 = new Material(MapColor.mapColor26).getMaterial5().getMaterial3();
   public static final Material material27 = new Material(MapColor.mapColor28);
   public static final Material material13 = new Material(MapColor.mapColor26).getMaterial3();
   public static final Material material3 = new Material(MapColor.mapColor26).getMaterial3();
   public static final Material material17 = new MaterialLiquid(MapColor.mapColor19).getMaterial6();
   public static final Material material20 = new Material(MapColor.mapColor19).getMaterial3();
   public static final Material material15 = new FovSynchronizer(MapColor.mapColor22).getMaterial4().getMaterial3();
   public static final Material material33 = new Material(MapColor.mapColor10).getMaterial6();
   public static final Material material10 = new Material(MapColor.mapColor19).getMaterial4().getMaterial6();
   public boolean enabled;
   public boolean enabled4;
   public boolean enabled5;
   public final MapColor mapColor;
   public boolean enabled3 = true;
   public int count;
   public boolean enabled2;

   public Material getMaterial4() {
      this.enabled3 = false;
      return this;
   }

   public boolean isActive() {
      return true;
   }

   public Material getMaterial() {
      this.enabled4 = true;
      return this;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public boolean isActive3() {
      return this.enabled5 ? false : this.isActiveAsFlag();
   }

   public Material getMaterialAsMaterial() {
      this.enabled2 = true;
      return this;
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   public boolean isActive2() {
      return false;
   }

   public boolean canExecute() {
      return this.enabled4;
   }

   public Material getMaterial3() {
      this.count = 1;
      return this;
   }

   public Material getMaterial6() {
      this.count = 2;
      return this;
   }

   public boolean isActiveAsFlag() {
      return true;
   }

   public Material getMaterial2() {
      this.enabled = true;
      return this;
   }

   public boolean canExecute2() {
      return true;
   }

   public int getIndex() {
      return this.count;
   }

   private Material getMaterial5() {
      this.enabled5 = true;
      return this;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public Material(MapColor mapColor2) {
      this.mapColor = mapColor2;
   }
}
