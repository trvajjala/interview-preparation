package output.streams;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

public class DeflaterExample {

    public static void main(String[] args) throws Exception {

        final String message = "here are some simple example to compresshere are some simple example to compresshere are some simple example to compresshere are some simple example to compresshere are some simple example to compresshere are some simple example to compresshere are some simple example to compress";
        final byte[] input = message.getBytes();
        System.out.println("Actual length :  " + input.length);

        final Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length)) {

            final byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                final int bytesCompressed = deflater.deflate(buffer);
                bos.write(buffer, 0, bytesCompressed);
            }

            System.out.println("Length " + bos.toByteArray().length);

        }

    }
}
