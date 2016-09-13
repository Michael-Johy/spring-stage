package com.johnny.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Description: This ImageUtils is used to process image-related
 * <p>
 * Author: johnny01.yang
 * Date  : 2016-08-24
 */
public class ImageUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

    private ImageUtils() {
    }

    /**
     * fetch Image format name by {@link ImageIO getImageReaders } and {@link ImageReader getFormatName}
     *
     * @param filePath file path
     * @return image format name  eg: jpeg | JPEG | gif | GIF
     */
    public static String fetchImageFormatName(String filePath) {
        String formatName = null;
        File file = new File(filePath);
        ImageInputStream iis = null;
        try {
            iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (readers.hasNext()) {
                formatName = readers.next().getFormatName();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != iis) {
                try {
                    iis.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return formatName;
    }
}
