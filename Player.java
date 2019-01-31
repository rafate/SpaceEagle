import java.util.Random;

import javax.swing.ImageIcon;


public class Player {
    private int x;    
	private int y;
    private int tiroy;
    private int tirox;
    private int iniciotiroy;    
    private int velocidadeX;
    private int velocidadeY;
    private int velocidadeTiro;
    private boolean tiroativo = false;    
    private ImageIcon imagem;
    
    public Player (int px, int py, int ptiroy, String pCaminhoDaImagem){
    	x = px;
    	y = py;
    	iniciotiroy = ptiroy;
    	tiroy = py+ptiroy;      	
    	imagem = new ImageIcon(pCaminhoDaImagem);
    	
    }
    public void moverDireita (int limiteDireitoDaTela){
    	if (x < limiteDireitoDaTela){
			   x += velocidadeX;  
		   }
    	
    }
    public void moverEsquerda (int limiteEsquerdoDaTela){
    	if (x > limiteEsquerdoDaTela){
			   x -= velocidadeX;  
		   }
    	
    }
    public void fimTiro(){
    	tiroativo = false;
    	tiroy = iniciotiroy;
    }
    public void moverAbaixo (int limiteSuperiorDaTela, int limiteInferiorDaTela, int inicioIntervalo, int fimIntervalo){
    	y += velocidadeY; 
    	if (y > limiteInferiorDaTela) {
    		y = limiteSuperiorDaTela;
    		setxAleatorio(inicioIntervalo, fimIntervalo);    		
    	}    	
    }
    public void atirar (){    	
    	if (!tiroativo) {
    		tiroativo = true;
        	tiroy = y+iniciotiroy;
        	tirox = x;
    	}
    	
    }
    public boolean atirou (){
    	return tiroativo;
    }
    public void moverTiroAbaixo (int limiteDaTela){
    	if (tiroy < limiteDaTela) 
        	tiroy += velocidadeTiro; 
    	if (tiroy >= limiteDaTela)
        	fimTiro();        	
        }
    public void moverTiroAcima (int limiteDaTela){
       	if (tiroy > limiteDaTela) 
           	tiroy -= velocidadeTiro; 
       	if (tiroy <= limiteDaTela)
       		fimTiro();        	          
         }
    public int getX() {
		return x;
	}	
	public int getY() {
		return y;
	}	
	public int getTiroy() {
		return tiroy;
	}		
	public int getTirox() {
		return tirox;
	}
	public ImageIcon getImagem() {
		return imagem;
	}	
    public int getVelocidadeX() {
		return velocidadeX;
	}
	public void setVelocidadeX(int velocidadeX) {
		this.velocidadeX = velocidadeX;
	}
	public int getVelocidadeY() {
		return velocidadeY;
	}
	public void setVelocidadeY(int velocidadeY) {
		this.velocidadeY = velocidadeY;
	}
	public int getVelocidadeTiro() {
		return velocidadeTiro;
	}
	public void setVelocidadeTiro(int velocidadeTiro) {
		this.velocidadeTiro = velocidadeTiro;
	}
	public void setxAleatorio(int inicioIntervalo, int fimIntervalo) {
    	Random gerador = new Random();
    	x = inicioIntervalo + gerador.nextInt(fimIntervalo);
    }
	public void setY(int y) {
		this.y = y;
	}
	
}