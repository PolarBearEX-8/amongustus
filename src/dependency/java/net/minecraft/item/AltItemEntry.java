package net.minecraft.item;

import client.event.JsonListener;
import client.util.AuxiliaryListener60EntryAdapter;
import client.util.BaseHashMapEntry;
import client.util.BezierBuffer;
import client.util.BezierBuilder;
import client.util.BezierEngine;
import client.util.BezierPool;
import client.util.Listener60EntryAdapter;
import java.util.ArrayList;

public class AltItemEntry {
   public final BaseHashMapEntry baseHashMapEntry;
   public final Listener60EntryAdapter listener60EntryAdapter;
   public final AuxiliaryListener60EntryAdapter auxiliaryListener60EntryAdapter;
   public final BezierEngine bezierEngine;
   public final BezierBuilder bezierBuilder = new BezierBuilder();
   public static final ArrayList arrayList = new ArrayList();
   public final BezierPool bezierPool = new BezierPool();
   public final BezierBuffer bezierBuffer;

   public void updateState() {
      arrayList.forEach((item -> AltItemEntry.applyJsonListener((JsonListener)item)));
   }

   public void forEach() {
      arrayList.forEach((item -> AltItemEntry.applyJsonListenerByJsonListener((JsonListener)item)));
   }

   private static void applyJsonListener(JsonListener jsonListener) {
      if (jsonListener.isActive2()) {
         jsonListener.updateState2();
      }
   }

   private static void applyJsonListenerByJsonListener(JsonListener jsonListener) {
      if (jsonListener.isActive2()) {
         jsonListener.updateState3();
      }
   }

   public void forEach2() {
      arrayList.forEach((item -> AltItemEntry.applyJsonListener2((JsonListener)item)));
   }

   private static void applyJsonListener2(JsonListener jsonListener) {
      if (jsonListener.isActive2()) {
         jsonListener.updateState();
      }
   }

   public AltItemEntry() {
      this.auxiliaryListener60EntryAdapter = new AuxiliaryListener60EntryAdapter();
      this.baseHashMapEntry = new BaseHashMapEntry();
      this.bezierEngine = new BezierEngine();
      this.bezierBuffer = new BezierBuffer();
      this.listener60EntryAdapter = new Listener60EntryAdapter();
   }
}
