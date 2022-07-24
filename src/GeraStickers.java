import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GeraStickers {

    // ler imagem

    public void criar() throws Exception{

        BufferedImage imagemOriginal = ImageIO.read(new File("shared/theGodfather_maior.jpg"));
        
        // criar nova imagem em memoria com transparencia  e com  tamanho novo
        // 1 - pegando o tamanho e a autura da imagem original
        
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        
        // 2 parsando o novo tamanho da imagens
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        // copiar a imagem original pra novo imagem
        
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0, 0, null);
        
        // escrever uma frase na imagem  
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
        graphics.setColor(Color.YELLOW);

        // Captura das dimenções do texto, para realizar o posicionamento.
        int stringLargura = graphics.getFontMetrics().stringWidth("É o pai!");
        int stringAltura = graphics.getFontMetrics().getHeight();
        
        // Posicionamento do texto
        int posiX = largura/2-stringLargura/2;
        int posiY = novaAltura - stringAltura/2;
        graphics.drawString("É o pai!", posiX, posiY );
        
        // escrever a nova imagem em um arquivo 
        
        ImageIO.write(novaImagem, "png", new File("shared/saida/novaFoto.png"));// fazer gerar pasta automaticamente
    }

    public static void main(String[] args) throws Exception {
        var gerador = new GeraStickers();

        gerador.criar();
    }
        
}
