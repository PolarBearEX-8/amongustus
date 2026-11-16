package client.util;

import client.setting.CombatScaleProperty;

public class HcAM7JyhxEManager {
   public SignumComparable signumComparable2;
   public static final HcAM7JyhxEManager hcAM7JyhxEManager = new HcAM7JyhxEManager(0);
   public static final HcAM7JyhxEManager hcAM7JyhxEManager2 = new HcAM7JyhxEManager(1);
   public static FG7jSMNbfywSerializable fG7jSMNbfywSerializable = new FG7jSMNbfywSerializable(10, CombatScaleProperty.HALF_UP);
   public static final HcAM7JyhxEManager hcAM7JyhxEManager3 = new HcAM7JyhxEManager(2);
   public SignumComparable signumComparable;

   public boolean isActive() {
      SignumComparable signumComparable3 = SignumComparable.signumComparable5;
      boolean flag = this.signumComparable.computeCountBySignumComparable(signumComparable3) == -1;
      SignumComparable signumComparable4 = SignumComparable.signumComparable5;
      return flag ^ this.signumComparable2.computeCountBySignumComparable(signumComparable4) == -1;
   }

   public String resolveText(FG7jSMNbfywSerializable fG7jSMNbfywSerializable2) {
      SplineParser splineParser = new SplineParser(this.signumComparable);
      SplineParser splineParser2 = new SplineParser(this.signumComparable2);
      return splineParser.resolveSplineParser2(splineParser2, fG7jSMNbfywSerializable2).getText();
   }

   public HcAM7JyhxEManager resolveHcAM7JyhxEManagerByIndex(int count) {
      SignumComparable signumComparable3 = this.signumComparable;
      SignumComparable signumComparable4 = this.signumComparable2;
      SignumComparable signumComparable5 = SignumComparable.resolveSignumComparableByTime((long)count);
      SignumComparable signumComparable6 = signumComparable4.resolveSignumComparable4(signumComparable5);
      return (new HcAM7JyhxEManager(signumComparable3.resolveSignumComparable5(signumComparable6), this.signumComparable2)).getHcAM7JyhxEManagerAsHcAM7JyhxEManage();
   }

   public HcAM7JyhxEManager resolveHcAM7JyhxEManager(HcAM7JyhxEManager hcAM7JyhxEManager4) {
      SignumComparable signumComparable3 = hcAM7JyhxEManager4.getSignumComparable();
      SignumComparable signumComparable4 = this.signumComparable;
      signumComparable3 = signumComparable3.resolveSignumComparable4(signumComparable4);
      SignumComparable signumComparable5 = hcAM7JyhxEManager4.getSignumComparable2();
      SignumComparable signumComparable6 = this.signumComparable2;
      return (new HcAM7JyhxEManager(signumComparable3, signumComparable5.resolveSignumComparable4(signumComparable6))).getHcAM7JyhxEManagerAsHcAM7JyhxEManage();
   }

   public SignumComparable getSignumComparable2() {
      return this.signumComparable2;
   }

   public HcAM7JyhxEManager getHcAM7JyhxEManager() {
      return new HcAM7JyhxEManager(this.signumComparable.getSignumComparable5(), this.signumComparable2.getSignumComparable5());
   }

   public boolean matchesHcAM7JyhxEManager(HcAM7JyhxEManager hcAM7JyhxEManager4) {
      SignumComparable signumComparable3 = this.signumComparable;
      SignumComparable signumComparable4 = hcAM7JyhxEManager4.getSignumComparable();
      if (signumComparable3.equals(signumComparable4)) {
         signumComparable3 = this.signumComparable2;
         SignumComparable signumComparable5 = hcAM7JyhxEManager4.getSignumComparable2();
         if (signumComparable3.equals(signumComparable5)) {
            return true;
         }
      }

      return false;
   }

   public HcAM7JyhxEManager resolveHcAM7JyhxEManager(int count) {
      SignumComparable signumComparable3 = this.signumComparable2;
      SignumComparable signumComparable4 = SignumComparable.resolveSignumComparableByTime((long)count);
      SignumComparable signumComparable5 = signumComparable3.resolveSignumComparable4(signumComparable4);
      return (new HcAM7JyhxEManager(this.signumComparable.resolveSignumComparable11(signumComparable5), this.signumComparable2)).getHcAM7JyhxEManagerAsHcAM7JyhxEManage();
   }

   public String getText() {
      return this.signumComparable + "/" + this.signumComparable2;
   }

   public SplineParser resolveSplineParser(FG7jSMNbfywSerializable fG7jSMNbfywSerializable2) {
      SplineParser splineParser = new SplineParser(this.signumComparable);
      SplineParser splineParser2 = new SplineParser(this.signumComparable2);
      return splineParser.resolveSplineParser2(splineParser2, fG7jSMNbfywSerializable2);
   }

   public HcAM7JyhxEManager resolveHcAM7JyhxEManagerByHcAM7JyhxEManage(HcAM7JyhxEManager hcAM7JyhxEManager4) {
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      SignumComparable signumComparable3 = this.signumComparable;
      SignumComparable signumComparable4 = hcAM7JyhxEManager4.getSignumComparable2();
      entry = signumComparable3.resolveSignumComparable4(signumComparable4);
      signumComparable3 = this.signumComparable2;
      SignumComparable signumComparable5 = hcAM7JyhxEManager4.getSignumComparable2();
      entry2 = signumComparable3.resolveSignumComparable4(signumComparable5);
      signumComparable3 = hcAM7JyhxEManager4.getSignumComparable();
      SignumComparable signumComparable6 = this.signumComparable2;
      entry3 = signumComparable3.resolveSignumComparable4(signumComparable6);
      return (new HcAM7JyhxEManager(((SignumComparable)entry).resolveSignumComparable11((SignumComparable)entry3), (SignumComparable)entry2))
         .getHcAM7JyhxEManagerAsHcAM7JyhxEManage();
   }

   public HcAM7JyhxEManager resolveHcAM7JyhxEManager2(HcAM7JyhxEManager hcAM7JyhxEManager4) {
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      SignumComparable signumComparable3 = this.signumComparable;
      SignumComparable signumComparable4 = hcAM7JyhxEManager4.getSignumComparable2();
      entry = signumComparable3.resolveSignumComparable4(signumComparable4);
      signumComparable3 = this.signumComparable2;
      SignumComparable signumComparable5 = hcAM7JyhxEManager4.getSignumComparable2();
      entry2 = signumComparable3.resolveSignumComparable4(signumComparable5);
      signumComparable3 = hcAM7JyhxEManager4.getSignumComparable();
      SignumComparable signumComparable6 = this.signumComparable2;
      entry3 = signumComparable3.resolveSignumComparable4(signumComparable6);
      return (new HcAM7JyhxEManager(((SignumComparable)entry).resolveSignumComparable5((SignumComparable)entry3), (SignumComparable)entry2))
         .getHcAM7JyhxEManagerAsHcAM7JyhxEManage();
   }

   public static SignumComparable resolveSignumComparable(SignumComparable signumComparable3, SignumComparable signumComparable4) {
      SignumComparable signumComparable5 = signumComparable3.resolveSignumComparable9(signumComparable4);
      return signumComparable3.resolveSignumComparable4(signumComparable4).resolveSignumComparable17(signumComparable5);
   }

   public HcAM7JyhxEManager getHcAM7JyhxEManagerAsHcAM7JyhxEManage() {
      Object entry = null;
      SignumComparable signumComparable3 = this.signumComparable2;
      entry = this.signumComparable.resolveSignumComparable9(signumComparable3);
      SignumComparable signumComparable4 = SignumComparable.signumComparable5;
      return !((SignumComparable)entry).equals(signumComparable4)
         ? new HcAM7JyhxEManager(
            this.signumComparable.resolveSignumComparable17((SignumComparable)entry), this.signumComparable2.resolveSignumComparable17((SignumComparable)entry)
         )
         : this;
   }

   public int computeCount(HcAM7JyhxEManager hcAM7JyhxEManager4) {
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      Object entry5 = null;
      SignumComparable signumComparable3 = this.getSignumComparable2();
      SignumComparable signumComparable4 = hcAM7JyhxEManager4.getSignumComparable2();
      entry = resolveSignumComparable(signumComparable3, signumComparable4);
      SignumComparable signumComparable5 = this.getSignumComparable2();
      entry2 = ((SignumComparable)entry).resolveSignumComparable17(signumComparable5);
      SignumComparable signumComparable6 = hcAM7JyhxEManager4.getSignumComparable2();
      entry3 = ((SignumComparable)entry).resolveSignumComparable17(signumComparable6);
      entry4 = this.getSignumComparable().resolveSignumComparable4((SignumComparable)entry2);
      entry5 = hcAM7JyhxEManager4.getSignumComparable().resolveSignumComparable4((SignumComparable)entry3);
      return ((SignumComparable)entry4).computeCountBySignumComparable((SignumComparable)entry5);
   }

   public SignumComparable getSignumComparable() {
      return this.signumComparable;
   }

   public SplineParser getSplineParser() {
      FG7jSMNbfywSerializable fG7jSMNbfywSerializable2 = fG7jSMNbfywSerializable;
      return this.resolveSplineParser(fG7jSMNbfywSerializable2);
   }

   public HcAM7JyhxEManager resolveHcAM7JyhxEManager2(int count) {
      HcAM7JyhxEManager hcAM7JyhxEManager4 = null;
      Object entry = null;
      if (count == 0) {
         return new HcAM7JyhxEManager(1);
      } else {
         hcAM7JyhxEManager4 = new HcAM7JyhxEManager(this.signumComparable, this.signumComparable2);
         entry = hcAM7JyhxEManager4;
         int count2 = Math.abs(count);

         for (int index = 1; index < count2; index++) {
            hcAM7JyhxEManager4 = hcAM7JyhxEManager4.resolveHcAM7JyhxEManager((HcAM7JyhxEManager)entry);
         }

         return hcAM7JyhxEManager4;
      }
   }

   public HcAM7JyhxEManager(int count) {
      this(String.valueOf(count), "1");
   }

   public HcAM7JyhxEManager(String text, String text2) {
      this.signumComparable = new SignumComparable(text);
      this.signumComparable2 = new SignumComparable(text2);
      SignumComparable signumComparable3 = SignumComparable.signumComparable5;
      if (this.signumComparable2.equals(signumComparable3)) {
         throw new ArithmeticException("Created impossible fraction");
      }
   }

   public HcAM7JyhxEManager(SignumComparable signumComparable3, SignumComparable signumComparable4) {
      this.signumComparable = signumComparable3;
      this.signumComparable2 = signumComparable4;
      SignumComparable signumComparable5 = SignumComparable.signumComparable5;
      if (signumComparable4.equals(signumComparable5)) {
         throw new ArithmeticException("Created impossible fraction");
      }
   }

   public HcAM7JyhxEManager(long value, long value2) {
      this(String.valueOf(value), String.valueOf(value2));
   }

   @Override
   public String toString() {
      SplineParser splineParser = new SplineParser(this.signumComparable);
      SplineParser splineParser2 = new SplineParser(this.signumComparable2);
      FG7jSMNbfywSerializable fG7jSMNbfywSerializable2 = fG7jSMNbfywSerializable;
      SplineParser splineParser3 = splineParser2;
      return splineParser.resolveSplineParser2(splineParser3, fG7jSMNbfywSerializable2).getText();
   }
}
