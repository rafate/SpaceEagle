import java.awt.Image;
import java.awt.Toolkit;

public class SpriteSheet {
	private int frameX;
	private int frameY;
	private int controlaVelocidade = 0;
	private int velocidade = 5;
	private int cena = 0;
	private int numeroDeCenas; //= 5;
	private int alturaCena; //= 96;
	private int larguraCena; //= 96;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image imgExplosao;
	
	public SpriteSheet (int alturaCena, int larguraCena, int numeroDeCenas, String pCaminhoDaImagem) {
		this.alturaCena = alturaCena;
		this.larguraCena = larguraCena;
		this.numeroDeCenas = numeroDeCenas;
		imgExplosao = tk.getImage(pCaminhoDaImagem);
    }
	public int getFrameX() {
		return frameX;
	}
	public void setFrameX(int frameX) {
		this.frameX = frameX;
	}
	public int getFrameY() {
		return frameY;
	}
	public void setFrameY(int frameY) {
		this.frameY = frameY;
	}
	public int getControlaVelocidade() {
		return controlaVelocidade;
	}
	public void setControlaVelocidade(int controlaVelocidade) {
		this.controlaVelocidade = controlaVelocidade;
	}
	public int getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public int getNumeroDeCenas() {
		return numeroDeCenas;
	}
	public void setNumeroDeCenas(int numeroDeCenas) {
		this.numeroDeCenas = numeroDeCenas;
	}
	public int getAlturaCena() {
		return alturaCena;
	}
	public void setAlturaCena(int alturaCena) {
		this.alturaCena = alturaCena;
	}
	public int getLarguraCena() {
		return larguraCena;
	}
	public void setLarguraCena(int larguraCena) {
		this.larguraCena = larguraCena;
	}
	public void animar() {
		//http://stackoverflow.com/questions/621835/how-to-extract-part-of-this-image-in-java
		controlaVelocidade+=1;
	      if(controlaVelocidade>velocidade){
	       cena += 1;
	       controlaVelocidade = 0;
	       if(cena == numeroDeCenas){ cena = 1; }
	      }
	      frameX = (cena % numeroDeCenas) * larguraCena;
	      frameY = (cena / numeroDeCenas) * alturaCena;
	}
	public Image getImgExplosao() {
		return imgExplosao;
	}
	public void setImgExplosao(Image imgExplosao) {
		this.imgExplosao = imgExplosao;
	}
    
}