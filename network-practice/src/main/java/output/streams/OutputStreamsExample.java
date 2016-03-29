package output.streams;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.StringReader;

public class OutputStreamsExample {

    public static void main(String[] args) throws Exception {

        final DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("some.txt")));
        dos.writeInt(12);
        dos.writeInt(12);
        dos.writeInt(12);
        dos.writeInt(12);
        dos.writeInt(12);
        dos.close();

        final PrintStream ps = new PrintStream(new FileOutputStream("some.txt"));
        ps.println("some");
        ps.close();

        final ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        bos.write("Writing some text here ".getBytes());

        System.out.println(new String(bos.toByteArray()));

        for (int i = 30; i < 129; i++) {
            System.out.println((char) i + " =  " + i);
        }

        final FileWriter fw = new FileWriter("write.txt");
        fw.write("Some".toCharArray());
        fw.close();

        final StringReader sr = new StringReader("SOME");
        System.out.println((char) sr.read());

        final CharArrayReader car = new CharArrayReader("some dsfsdfds".toCharArray());

        int i = 0;
        while ((i = car.read()) != -1) {
            System.out.print((char) i);
        }

    }
}
