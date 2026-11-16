package client.command;

import client.Client;
import client.event.EventBusManager;
import client.event.EventCommand;
import client.event.EventCommandCallback;
import client.util.AdvancedStringEntryAdapter;
import client.util.AltStringEntryAdapter;
import client.util.AuxiliaryStringEntry;
import client.util.AuxiliaryStringEntryBridge;
import client.util.BaseStringEntryAdapter;
import client.util.CompositeStringEntryAdapter;
import client.util.CoreStringEntryAdapter;
import client.util.CustomStringEntryBridge;
import client.util.DefaultStringEntryAdapter;
import client.util.DynamicStringEntryAdapter;
import client.util.EnhancedStringEntryAdapter;
import client.util.ExtendedStringEntryBridge;
import client.util.ExtraStringEntryAdapter;
import client.util.GenericStringEntryAdapter;
import client.util.InternalStringEntryBridge;
import client.util.ManagedStringEntryAdapter;
import client.util.NativeStringEntryAdapter;
import client.util.PrimaryStringEntryAdapter;
import client.util.SecondaryStringEntryBridge;
import client.util.SharedStringEntryAdapter;
import client.util.SmoothBridge;
import client.util.SpecialStringEntryAdapter;
import client.util.StandardStringEntryAdapter;
import client.util.StaticStringEntryAdapter;
import client.util.StringEntryHandler;
import client.util.SupplementalStringEntryAdapter;
import client.util.TertiaryClientManager;
import client.util.TertiaryStringEntryBridge;
import java.util.ArrayList;
import java.util.List;

public class CommandCallbackHandler implements EventCommandCallback {
   public List list = new ArrayList();
   public final StringEntryHandler stringEntryHandler;
   public final TertiaryClientManager tertiaryClientManager;

   @Override
   public void onCommand(EventCommand event) {
      String text = event.getText();

      for (AuxiliaryStringEntry auxiliaryStringEntry : (Iterable<AuxiliaryStringEntry>)(Iterable<?>)Client.getClient().getEventCommandCallbackAdapter().getList()) {
         String[] textArray = text.split(" ");
         if (textArray.length > 0 && textArray[0].equalsIgnoreCase(auxiliaryStringEntry.getString())) {
            auxiliaryStringEntry.printStackTrace(textArray, text);
            event.cancel();
            Client.getClient().getAltItemEntry().updateState();
         }
      }

      if (text.startsWith(Client.getClient().getModuleManager().clientSettings.settingCommandPrefixes.getSelected()) && text.length() > 1) {
         event.cancel();
      }
   }

   public void setList(List list2) {
      this.list = list2;
   }

   public List getList() {
      return this.list;
   }

   public CommandCallbackHandler() {
      EventBusManager.getDietrichEvents2().subscribe(5, this);
      this.list.add(new AuxiliaryStringEntryBridge());
      this.list.add(new InternalStringEntryBridge());
      this.list.add(new SupplementalStringEntryAdapter());
      this.list.add(new ExtraStringEntryAdapter());
      this.list.add(new EnhancedStringEntryAdapter());
      this.list.add(new AltStringEntryAdapter());
      this.list.add(new ManagedStringEntryAdapter());
      this.list.add(new CustomStringEntryBridge());
      this.list.add(new StandardStringEntryAdapter());
      this.list.add(new SecondaryStringEntryBridge());
      this.list.add(new CompositeStringEntryAdapter());
      this.list.add(new NativeStringEntryAdapter());
      this.list.add(new SpecialStringEntryAdapter());
      this.list.add(new DefaultStringEntryAdapter());
      this.list.add(new GenericStringEntryAdapter());
      this.list.add(new AdvancedStringEntryAdapter());
      this.list.add(new CoreStringEntryAdapter());
      this.list.add(new StaticStringEntryAdapter());
      this.list.add(new ExtendedStringEntryBridge());
      this.list.add(new DynamicStringEntryAdapter());
      this.list.add(this.tertiaryClientManager = new TertiaryClientManager());
      Object entry = null;
      entry = null;
      this.list.add(new BaseStringEntryAdapter());
      this.list.add(new SmoothBridge());
      this.list.add(new TertiaryStringEntryBridge());
      this.list.add(this.stringEntryHandler = new StringEntryHandler());
      this.list.add(new PrimaryStringEntryAdapter());
      this.list.add(new SharedStringEntryAdapter());
      Client.getClient().getClass();
   }
}
