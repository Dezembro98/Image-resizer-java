import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizer {
	public static void main (String[] args)
	{		
		BufferedImage img = null;
		try 
		{
			img = ImageIO.read(new File("caminho para input"));
			ImageResizer main = new ImageResizer();
			img = main.scale(img, 500, 500,Color.black);
			ImageIO.write(img,"JPG",new File("caminho para output"));
		}
		catch (IOException e) 
		{
			
		}
	}
	
	public BufferedImage scale(BufferedImage bsrc, int width, int height,Color color) throws IOException 
	{
			//Pega a altura e largura da imagem
		   int h = bsrc.getHeight();
		   int w = bsrc.getWidth();
		   //Recebe inicialmente o maior lado como a altura
		   int maior = h;
		   //Inicia a margem como 0 (caso a resolução final tenha outra proporção, margens serão adicionadas para a imagem não se distorcer) 
		   int padding = 0;
		   int padding2 = 0;
		   //Verificando qual é o maior lado da imagem
		   if(w > maior)
		   {
			   maior = w;
			   //altura é o menor
			   padding = maior - h;
		   }
		   else
		   {
			   //largura é o menor
			   padding2 = maior - w;
		   }
		   //System out do padding final de cada lado
		   System.out.println(padding);
		   System.out.println(padding2);
		   //Cria uma imagem do tamanho anterior + margem
		   BufferedImage newImage = new BufferedImage(bsrc.getWidth()+padding2, bsrc.getHeight()+padding, bsrc.getType());
		   Graphics g1 = newImage.getGraphics();
		   //Pinta o fundo com a cor previamente escolhida
		   g1.setColor(color);
		   g1.fillRect(0,0,bsrc.getWidth()+padding2,bsrc.getHeight()+padding);		   
		   if(padding==0)
		   {
			   g1.drawImage(bsrc, newImage.getWidth()/2-bsrc.getWidth()/2, 0, null);
		   }
		   else
		   {
			   g1.drawImage(bsrc, 0, newImage.getHeight()/2-bsrc.getHeight()/2, null);
		   }
		   g1.dispose();
		   //Cria uma imagem do tamanho final desejado
		   BufferedImage bdest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		   Graphics2D g = bdest.createGraphics();
		   //Adiciona a imagem redimenesioanda no centro da imagem final 
		   AffineTransform at = AffineTransform.getScaleInstance((double)width/newImage.getWidth(),(double)height/newImage.getHeight());
		   g.drawRenderedImage(newImage,at);
		   return bdest;
	}	
}
