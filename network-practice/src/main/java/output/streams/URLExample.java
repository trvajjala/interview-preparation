package output.streams;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class URLExample {

    public static void main(String[] args) throws Exception {

        final URL url = new URL("https://facebook.com");
        final URLConnection connection = url.openConnection();
        final HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        System.out.println(httpURLConnection.getRequestMethod());

        httpURLConnection.setDoOutput(true);

        final OutputStream os = httpURLConnection.getOutputStream();
        os.write("Some Bytes".getBytes());
        os.close();
        httpURLConnection.setDoInput(true);
        final InputStream is = httpURLConnection.getInputStream();

        final byte[] b = new byte[1024];
        is.read(b);
        System.out.println(new String(b));

    }
}
