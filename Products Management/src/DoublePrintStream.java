import java.io.*;

public class DoublePrintStream extends PrintStream {
    //The other stream to write to
    final PrintStream[] others;
    public DoublePrintStream(PrintStream o1, PrintStream... others){
        super(o1);
        this.others = others;
    }

    @Override
    public void write(int b) {
        super.write(b);
        //echo every write to the other streams
        for(PrintStream o: others){
            o.write(b);
        }
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        super.write(buf, off, len);
        //echo every write to the other streams
        for(PrintStream o: others){
            o.write(buf, off, len);
        }
    }

}
