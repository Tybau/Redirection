package fr.thibault.redirection.utils;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
	
	private int width, height;
    private int id;
    private BufferedImage image;

    public Texture(String path, int filter){
        int[] pixels = null;

        try {
            image = ImageIO.read(Texture.class.getResourceAsStream("/textures/" + path));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        }catch (IOException e){
            e.printStackTrace();
        }

        int[] data = new int[width * height];
        for(int i = 0; i < data.length; i++){
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }

        int id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);

        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);

        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);

        buffer.put(data);
        buffer.flip();

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA,width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    public static void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
    
    public void setTexture(Texture texture, String path){
    	texture = new Texture(path, GL_NEAREST);
    }
    
    public BufferedImage getImage(Texture texture){
    	return texture.image;
    }
}
