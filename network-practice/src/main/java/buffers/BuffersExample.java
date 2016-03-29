package buffers;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class BuffersExample {
    public static void main(String[] args) {
        final int[] data = { 3, 4, 939, 19 };
        final IntBuffer intBuffer = IntBuffer.wrap(data);
        System.out.println(intBuffer.isDirect());
        System.out.println(ByteBuffer.allocateDirect(1024).isDirect());

        final IntBuffer buffer = IntBuffer.allocate(8);
        buffer.put(1);
        System.out.println(buffer.position());
        buffer.put(221);
        System.out.println(buffer.position());
        System.out.println(buffer.limit(1));

    }
}
