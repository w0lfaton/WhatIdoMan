package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utility {
    public BufferedImage imageIOtoBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        if (image != null) {
            BufferedImage result = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D resultGraphics = result.createGraphics();
            resultGraphics.drawImage(image, 0, 0, null);
            resultGraphics.dispose();
            return result;
        } else {
            System.out.println("Error: image is null - cannot convert.");
            return null;
        }
    }

    public int getDotProduct(Vec2D firstVector, Vec2D secondVector) {
        int xMul = firstVector.getX() * secondVector.getX();
        int yMul = firstVector.getY() * secondVector.getY();
        return xMul+yMul;
    }
}
