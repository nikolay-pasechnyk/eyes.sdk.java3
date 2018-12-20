package com.applitools.eyes.visualGridClient.model;

import com.applitools.eyes.Logger;
import com.applitools.utils.GeneralUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileDebugResourceWriter implements IDebugResourceWriter {

    private static final String DEFAULT_PREFIX = "resource_";
    private static final String DEFAULT_PATH = "";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private Logger logger;
    private String path;
    private String prefix;
    private String filter;

    public FileDebugResourceWriter(Logger logger, String path, String prefix, String filter) {
        this.logger = logger;
        this.setPath(path);
        this.setPrefix(prefix);
        this.filter = filter;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? DEFAULT_PREFIX : prefix;
    }

    public void setPath(String path) {
        if (path != null) {
            path = path.endsWith("/") ? path : path + '/';
        } else {
            path = DEFAULT_PATH;
        }

        this.path = path;
    }

    @Override
    public void write(RGridResource resource) {
        String url = resource.getUrl();
        if (filter == null || filter.isEmpty() || url.toUpperCase().contains(filter.toUpperCase())) {
            try {
                String urlHash = GeneralUtils.getSha256hash(url.getBytes());
                String ext = resource.getContentType();
                int slash = ext.indexOf("/");
                ext = ext.substring(slash + 1);
                int semicolon = ext.indexOf(";");
                if (semicolon > -1) {
                    ext = ext.substring(0, semicolon);
                }
                String pathname = path + prefix + urlHash + "_" + resource.getSha256() + "." + ext;
                pathname = pathname.replaceAll("\\?", "_");
                File file = new File(pathname);
                ensureFilePath(file);
                byte[] data = ArrayUtils.toPrimitive(resource.getContent());
                FileUtils.writeByteArrayToFile(file, data);
            } catch (Exception e) {
                GeneralUtils.logExceptionStackTrace(logger, e);
            }
        }
    }

    private void ensureFilePath(File file) {
        File path = file.getParentFile();
        if (path != null && !path.exists()) {
            System.out.println("No Folder");
            boolean success = path.mkdirs();
            System.out.println("Folder created");
        }
    }
}
