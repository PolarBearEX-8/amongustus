package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.IDatatypeFor;
import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.command.argument.IArgConsumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

public class FollowCommand extends Command {

   public FollowCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"follow"});
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      if (argConsumer.hasExactlyOne()) {
         return new TabCompleteHelper().append3(FollowGroup.class).append3(FollowList.class).filterPrefix(argConsumer.getString()).getStream();
      } else {
         IDatatypeFor datatypeFor;
         try {
            datatypeFor = ((FollowList)argConsumer.getEnum(FollowList.class)).datatype;
         } catch (NullPointerException e) {
            return Stream.empty();
         }

         while (argConsumer.has(2)) {
            if (argConsumer.peekDatatypeOrNull(datatypeFor) == null) {
               return Stream.empty();
            }

            argConsumer.get();
         }

         return argConsumer.tabCompleteDatatype(datatypeFor);
      }
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The follow command tells Baritone to follow certain kinds of entities.",
         "",
         "Usage:",
         "> follow entities - Follows all entities.",
         "> follow entity <entity1> <entity2> <...> - Follow certain entities (for example 'skeleton', 'horse' etc.)",
         "> follow players - Follow players",
         "> follow player <username1> <username2> <...> - Follow certain players"
      );
   }

   @Override
   public String getShortDesc() {
      return "Follow entity things";
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMin(1);
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();
      FollowGroup followGroup;
      if (argConsumer.hasExactlyOne()) {
         this.baritone.getFollowProcess().follow((followGroup = (FollowGroup)argConsumer.getEnum(FollowGroup.class)).filter);
      } else {
         argConsumer.requireMin(2);
         followGroup = null;
         FollowList followList = (FollowList)argConsumer.getEnum(FollowList.class);

         while (argConsumer.hasAny()) {
            Object datatypeFor = argConsumer.getDatatypeFor(followList.datatype);
            if (datatypeFor instanceof Class) {
               list2.add((Class)datatypeFor);
            } else if (datatypeFor != null) {
               list.add((Entity)datatypeFor);
            }
         }

         this.baritone.getFollowProcess().follow(list2.isEmpty() ? list::contains : obj -> list2.stream().anyMatch(obj2 -> ((Class)obj2).isInstance(obj)));
      }

      if (followGroup != null) {
         this.logDirect2(String.format("Following all %s", followGroup.name().toLowerCase(Locale.US)));
      } else if (list2.isEmpty()) {
         if (list.isEmpty()) {
            throw new NoEntitiesException();
         }

         this.logDirect2("Following these entities:");
         list.stream().map(obj3 -> ((Entity)obj3).toString()).forEach(obj4 -> this.logDirect2((String)obj4));
      } else {
         this.logDirect2("Following these types of entities:");
         list2.stream().map(obj5 -> EntityList.getEntityStringFromClass((Class)obj5)).map(Objects::requireNonNull).forEach(obj6 -> this.logDirect2((String)obj6));
      }
   }

}
