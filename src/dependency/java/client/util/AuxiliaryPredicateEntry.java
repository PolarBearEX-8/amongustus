package client.util;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

public class AuxiliaryPredicateEntry extends RotationPool {
   public final Predicate predicate = new CompositePredicateAdapter(this);
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public double value;
   public double value2;
   public Entity entity;
   public float factor;
   public InterpolationConverter interpolationConverter;
   public PathfindManager pathfindManager;
   public Class memberRef;
   public Predicate predicate2;

   @Override
   public void updateState() {
      this.entity = null;
   }

   @Override
   public void updateState3() {
      this.pathfindManager.matchesInterpolationConverter(this.interpolationConverter, this.value);
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.pathfindManager.isActive2();
   }

   @Override
   public void updateState2() {
      if (this.auxiliaryUUIDEntry.getCoordinate(this.entity) < 49.0) {
         this.auxiliaryUUIDEntry.getPathfindManager().setValue(this.value2);
      } else {
         this.auxiliaryUUIDEntry.getPathfindManager().setValue(this.value);
      }
   }

   @Override
   public boolean isActive() {
      List list = this.auxiliaryUUIDEntry
         .worldObj
         .resolveList(
            this.memberRef,
            this.auxiliaryUUIDEntry.getBoundingBox2().offset((double)this.factor, 3.0, (double)this.factor),
            Predicates.and(new Predicate[]{LivingEntityFilter.predicate4, this.predicate, this.predicate2})
         );
      if (list.isEmpty()) {
         return false;
      } else {
         this.entity = (Entity)list.get(0);
         Vec3 vec = Vec3Entry2.resolveVec3(this.auxiliaryUUIDEntry, 16, 7, new Vec3(this.entity.posX, this.entity.posY, this.entity.posZ));
         if (vec == null) {
            return false;
         } else if (this.entity.getDouble3(vec.xCoord, vec.yCoord, vec.zCoord) < this.entity.getCoordinate(this.auxiliaryUUIDEntry)) {
            return false;
         } else {
            this.interpolationConverter = this.pathfindManager.resolveInterpolationConverter(vec.xCoord, vec.yCoord, vec.zCoord);
            return this.interpolationConverter == null ? false : this.interpolationConverter.matchesVec3(vec);
         }
      }
   }

   public AuxiliaryPredicateEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, Class clazz, Predicate predicate3, float factor2, double offset, double offset2) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.memberRef = clazz;
      this.predicate2 = predicate3;
      this.factor = factor2;
      this.value = offset;
      this.value2 = offset2;
      this.pathfindManager = auxiliaryUUIDEntry2.getPathfindManager();
      this.setCount(1);
   }

   public AuxiliaryPredicateEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, Class clazz, float factor2, double offset, double offset2) {
      this(auxiliaryUUIDEntry2, clazz, Predicates.alwaysTrue(), factor2, offset, offset2);
   }
}
