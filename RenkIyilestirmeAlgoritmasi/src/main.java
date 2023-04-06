import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class main {
    
    public static BufferedImage renkIyilestir(BufferedImage resim) {
        
        int genislik = resim.getWidth();
        int yukseklik = resim.getHeight();
        
        // Histogram hesaplamak için renk sayılarını saklayacak bir dizi oluşturuyoruz.
        int[] kirmiziHistogram = new int[256];
        int[] yesilHistogram = new int[256];
        int[] maviHistogram = new int[256];
        
        // Her piksel için histogramı hesaplıyoruz.
        for (int x = 0; x < genislik; x++) {
            for (int y = 0; y < yukseklik; y++) {
                Color renk = new Color(resim.getRGB(x, y));
                kirmiziHistogram[renk.getRed()]++;
                yesilHistogram[renk.getGreen()]++;
                maviHistogram[renk.getBlue()]++;
            }
        }
        
        // Histogramları normalleştiriyoruz.
        int pikselSayisi = genislik * yukseklik;
        double[] kirmiziNormalize = new double[256];
        double[] yesilNormalize = new double[256];
        double[] maviNormalize = new double[256];
        for (int i = 0; i < 256; i++) {
            kirmiziNormalize[i] = kirmiziHistogram[i] / (double)pikselSayisi;
            yesilNormalize[i] = yesilHistogram[i] / (double)pikselSayisi;
            maviNormalize[i] = maviHistogram[i] / (double)pikselSayisi;
        }
        
        // Kumulatif dağılım fonksiyonunu hesaplıyoruz.
        int[] kirmiziCDF = new int[256];
        int[] yesilCDF = new int[256];
        int[] maviCDF = new int[256];
        kirmiziCDF[0] = kirmiziHistogram[0];
        yesilCDF[0] = yesilHistogram[0];
        maviCDF[0] = maviHistogram[0];
        for (int i = 1; i < 256; i++) {
            kirmiziCDF[i] = kirmiziCDF[i-1] + kirmiziHistogram[i];
            yesilCDF[i] = yesilCDF[i-1] + yesilHistogram[i];
            maviCDF[i] = maviCDF[i-1] + maviHistogram[i];
        }
        
        // Yeni piksel değerlerini hesaplıyoruz.
        BufferedImage yeniResim = new BufferedImage(genislik, yukseklik, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < genislik; x++) {
            for (int y = 0; y < yukseklik; y++) {
                Color renk = new Color(resim.getRGB(x, y));
                int yeniKirmizi = (int)Math.round(255 * kirmiziCDF[renk.getRed()] / (double)pikselSayisi);
                int yeniYesil = (
            int)Math.round(255 * yesilCDF[renk.getGreen()] / (double)pikselSayisi);
            int yeniMavi = (int)Math.round(255 * maviCDF[renk.getBlue()] / (double)pikselSayisi);
            Color yeniRenk = new Color(yeniKirmizi, yeniYesil, yeniMavi);
            yeniResim.setRGB(x, y, yeniRenk.getRGB());
        }
    }
    
    return yeniResim;
}

public static void main(String[] args) throws IOException {
    
    //resmimizin uzantisini giriyoruz.
    BufferedImage orijinalResim = ImageIO.read(new File("kirklareli.JPG"));
    BufferedImage iyilestirilmisResim = renkIyilestir(orijinalResim);
        try {
            ImageIO.write(iyilestirilmisResim, "jpg", new File("iyilestirilmis_resim.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}