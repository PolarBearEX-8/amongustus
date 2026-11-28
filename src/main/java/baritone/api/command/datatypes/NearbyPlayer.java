package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.entity.player.EntityPlayer;

public enum NearbyPlayer implements IDatatypeFor {
   INSTANCE;
public EntityPlayer get2(IDatatypeContext datatypeContext) throws CommandException {
      String string = datatypeContext.getConsumer().getString();
      return (EntityPlayer)(getPlayers(datatypeContext).stream().filter(obj -> ((EntityPlayer)obj).getName().equalsIgnoreCase(string)).findFirst().orElse(null));
   }

   private static List getPlayers(IDatatypeContext datatypeContext) {
      return datatypeContext.getBaritone().getPlayerContext().world().playerEntities;
   }

   @Override
   public Object get(IDatatypeContext datatypeContext) throws CommandException {
      return this.get2(datatypeContext);
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      return new TabCompleteHelper().append(getPlayers(datatypeContext).stream().map(obj -> ((EntityPlayer)obj).getName())).filterPrefix(datatypeContext.getConsumer().getString()).sortAlphabetically().getStream();
   }

}
