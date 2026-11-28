package baritone.api.command.argparser;


import baritone.api.command.argument.ICommandArgument;
public interface IStatedArgParser extends IArgParser {
   Class getStateType();

   Object parseArg(ICommandArgument commandArgument, Object obj) throws Exception;
}
