import ghidra.app.script.GhidraScript;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionIterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ListFunctions extends GhidraScript {
    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length != 1) throw new IllegalArgumentException("Usage: ListFunctions.java <output.tsv>");
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(args[0])))) {
            FunctionIterator functions = currentProgram.getFunctionManager().getFunctions(true);
            while (functions.hasNext()) {
                Function f = functions.next();
                out.println(f.getEntryPoint() + "\t" + f.getName() + "\t" + f.isExternal() + "\t" + f.getBody().getNumAddresses());
            }
        }
        println("Listed " + currentProgram.getFunctionManager().getFunctionCount() + " functions");
    }
}
