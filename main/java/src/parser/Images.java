package parser;


import app.model.Proxy;

import java.io.*;
import java.net.URL;

public class Images {
    public static String download (String photoUrl, String siteUrl, int photoId, String directory) throws IOException {
        photoUrl = photoUrl.startsWith("http") ? photoUrl : siteUrl + photoUrl;

        URL url = null;
        InputStream in = null;
        byte[] response = null;
        try {
            url = new URL(photoUrl);
            in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            response = out.toByteArray();
        } catch (Exception e) {
            return null;
        }

        URL resource =
                new File(directory).toURI().toURL();
        File folder = new File(resource.getPath() + photoId + ".jpg");
        FileOutputStream fos = new FileOutputStream(folder);
        fos.write(response);
        fos.close();

        return photoId + ".jpg";
    }

    public static String download (String photoUrl, String siteUrl, int photoId, String directory, Proxy proxy) throws IOException {
        System.setProperty("http.proxyHost", proxy.getIp());
        System.setProperty("http.proxyPort", proxy.getPort());
        String src = download(photoUrl, siteUrl, photoId, directory);
        System.setProperty("http.proxyHost", "");
        return src;
    }
}
