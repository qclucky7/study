package com.qingchen.study.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: ywx
 * @Date: 18-10-11 14:11
 * @Description: 图像工具（各种网上学习，随便改）
 */
public class ImageUtil {
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public enum ImageQuality {
        // 图像质量
        max(1.0f), high(0.75f), medium(0.5f), low(0.25f);

        private Float quality;

        ImageQuality(float quality) {
            this.quality = quality;
        }

        public Float getQuality() {
            return this.quality;
        }
    }

    /**
     * 将目标图像转为固定长宽
     * @param width 目标宽度
     * @param height 目标长度
     * @param originImage 原始图像
     * @return
     */
    public static BufferedImage resize(int width, int height, @NonNull Image originImage) {
        width = Math.max(width, 1);
        height = Math.max(height, 1);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(originImage, 0, 0, width, height, null);
        return bufferedImage;
    }

    /**
     * 将图像高度等比例缩放到指定值
     * @param height 目标高度
     * @param originImage 原始图像
     * @return
     */
    public static BufferedImage scaleByHeight(int height, @NonNull Image originImage) {
        int[] resultSize = getScaleSizeByHeight(height, originImage);
        return resize(resultSize[0], resultSize[1], originImage);
    }

    /**
     * 将图像宽度等比例缩放到指定值
     * @param width 目标宽度
     * @param originImage 原始图像
     * @return
     */
    public static BufferedImage scaleByWidth(int width, @NonNull Image originImage) {
        int[] resultSize = getScaleSizeByWidth(width, originImage);
        return resize(resultSize[0], resultSize[1], originImage);
    }

    /**
     * 压缩图像质量
     * @param quality 质量
     * @param oldImage 原图
     * @param path 压缩图路径
     * @return void
     * @throws IOException
     */
    public static void compressQuality(@NonNull ImageQuality quality, @NonNull BufferedImage oldImage, @NonNull String path) throws IOException {
        FileOutputStream out = null;
        ImageWriter imgWriter;
        ImageWriteParam imgWriteParams;

        // 指定写图片的方式为 jpg
        imgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = imgWriter.getDefaultWriteParam();
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality(quality.getQuality());
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel
                .createCompatibleSampleModel(16, 16)));
        imgWriter.reset();
        // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
        out = new FileOutputStream(path);
        imgWriter.setOutput(ImageIO.createImageOutputStream(out));
        // 调用write方法，就可以向输入流写图片
        imgWriter.write(null, new IIOImage(oldImage, null, null), imgWriteParams);
        out.flush();
        out.close();
    }

    /**
     * 判断输入流内容是否为图片
     * @param inputStream 输入流
     * @return 流中内容为图片则为true,反之则为false
     */
    public static boolean isImage(@NonNull InputStream inputStream) {
        if (inputStream == null) {
            logger.warn("[ImageUtil] [check inputStream has image content: empty inputStream]");
            return false;
        }
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            logger.debug("[ImageUtil] [check inputStream: inputStream has no image content]");
            return false;
        }
        return bufferedImage!= null && bufferedImage.getWidth() > 0 && bufferedImage.getHeight() > 0;
    }

    /**
     * 若要将图像高度等比例缩放到指定值，求出图像最后的宽高
     * @param height 目标高度
     * @param originImage 原始图像
     * @return
     */
    private static int[] getScaleSizeByHeight(int height, @NonNull Image originImage) {
        int width = Math.round(originImage.getWidth(null) * height / (originImage.getHeight( null) * 1.0f));
        return new int[] { width, height };
    }

    /**
     * 若要将图像宽度等比例缩放到指定值，求出图像最后的宽高
     * @param width 目标宽度
     * @param originImage 原始图像
     * @return
     */
    private static int[] getScaleSizeByWidth(int width, @NonNull Image originImage) {
        int height = Math.round(originImage.getHeight(null) * width / (originImage.getWidth(null) * 1.0f));
        return new int[] { width, height };
    }

}
