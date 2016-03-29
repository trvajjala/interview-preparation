package output.streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channels {

    public static void main(String[] args) throws Exception {

        final FileOutputStream fos = new FileOutputStream(new File("/Volumes/DATA/Main2.java"));

        final FileChannel outChannel = fos.getChannel();

        final FileInputStream fis = new FileInputStream(new File("/Volumes/DATA/ExeService.java"));

        final FileChannel inChannel = fis.getChannel();

        final ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            final int readBytes = inChannel.read(buffer);

            if (readBytes == -1) {
                break;
            }
            buffer.flip();

            while (buffer.hasRemaining()) {
                outChannel.write(buffer);
            }
            buffer.clear();

        }

        fis.close();
        fos.close();

    }
}
