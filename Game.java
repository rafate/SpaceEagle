import java.awt.*; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.image.BufferedImage; 
import javax.swing.ImageIcon; 
import javax.swing.JFrame; 
//A CLASSE GAME HERDA AS FUNCIONALIDADES DE JFRAME 
public class Game extends JFrame implements KeyListener { 
  
 /** 
*  
*/ 
private static final long serialVersionUID = 1L; 
BufferedImage backBuffer; //ESSE É O NOSSO BUFFER 
 final int FPS = 30;    //ESSA É A TAXA DE ATUALIZAÇÃO DA TELA 
 final int janelaW = 500;   //LARGURA DA TELA 
 final int janelaH = 500;   //ALTURA DA TELA 
 int fy = -580;
 int score = 0;
 int vidas = 3;
 
 Player player = new Player(200, 400, 20,"src/eagle.png"); 
 Player inimigo1 = new Player(120, 20, 50,"src/nave.png");  
 ImageIcon fundo = new ImageIcon("src/fundo.png");
 SpriteSheet explosao = new SpriteSheet(96,96,5,"src/Explosion2.png"); //http://www.codeproject.com/KB/game/677417/Explosion2.png 
 
 private boolean colisao(int obj1X, int obj1Y, int obj1W, int obj1H, 
   int obj2X, int obj2Y, int obj2W, int obj2H) { 
  if ((obj1X >= obj2X && obj1X <= obj2X + obj2W) 
    && (obj1Y >= obj2Y && obj1Y <= obj2Y + obj2H)) { 
   return true; 
  } else if ((obj1X + obj1W >= obj2X && obj1X + obj1W <= obj2X + obj2W) 
    && (obj1Y >= obj2Y && obj1Y <= obj2Y + obj2H)) { 
   return true; 
  } else if ((obj1X >= obj2X && obj1X <= obj2X + obj2W) 
    && (obj1Y + obj1H >= obj2Y && obj1Y + obj1H <= obj2Y + obj2H)) { 
   return true; 
  } else if ((obj1X + obj1W >= obj2X && obj1X + obj1W <= obj2X + obj2W) 
    && (obj1Y + obj1H >= obj2Y && obj1Y + obj1H <= obj2Y + obj2H)) { 
   return true; 
  } else { 
   return false; 
  } 
 } 
 
 //NO NOSSO METODO ATUALIZAR VAMOS CHAMAR OS METODOS 
 //QUE SERÃO EXECUTADOS O TEMPO INTEIRO... 
 public void atualizar() { 
 
     if (fy > 27) { 
         fy = -580;      
          } 
     fy += 1; 
      
     inimigo1.moverAbaixo (0, 500, 20, 420); 
 
     if (player.atirou()) { 
         player.moverTiroAcima(10); 
          } 
     if (player.getX() < (inimigo1.getX()+50) && player.getX() > (inimigo1.getX()-50)) { 
         inimigo1.atirar(); 
          } 
     if (inimigo1.atirou()) { 
          inimigo1.moverTiroAbaixo(500); 
           }       
 } 
  
 //NESSE MÉTODO VAMOS DESENHAR 
 //FORMAS GEOMETRICAS, IMAGENS E TEXTOS NA TELA E ETC... 
 public void desenharGraficos() { 
  Graphics g = getGraphics();//COM g IREMOS DESENHAR O QUE ESTÁ NO BUFFER NA TELA 
  Graphics bbg = backBuffer.getGraphics();//COM bbg IREMOS DESENHAR NO NOSSO BUFFER 
  bbg.drawImage(fundo.getImage(),0,fy,this); 
  bbg.drawImage(player.getImagem().getImage(), player.getX(), player.getY(),100,100,this); //aqui não fica com as dimensões originais! 
  bbg.drawImage(inimigo1.getImagem().getImage(),inimigo1.getX(), inimigo1.getY(),70,70,this); //desenha a imagem*/ 
   
  if (player.atirou()) { 
      bbg.setColor(new Color(255,0,0)); 
      bbg.fillRect(player.getTirox()+48, player.getTiroy(), 2, 8);  
  }     
  bbg.drawString("Score: " + score,10,10);    
  bbg.drawString("Vidas: " + vidas,80,10);
  //bbg.drawString("inimigox: " + inimigo1.getX() + " inimigoy: " + inimigo1.getY() + " tiroy: " + inimigo1.getTiroy() + " tiroativo: " + inimigo1.atirou(), 100, 100); 
  //bbg.drawString("playerx: " + player.getX() + " playery: " + player.getY() + " tiroy: " + player.getTiroy() + " tiroativo: " + player.atirou(), 100, 200); 
  
  if (colisao(player.getTirox(),player.getTiroy(),20,20,inimigo1.getX(),inimigo1.getY(),20,20)){ 
      explosao.animar();
	  bbg.drawImage(explosao.getImgExplosao(), player.getTirox(), player.getTiroy(), player.getTirox()+explosao.getLarguraCena(), player.getTiroy()+explosao.getAlturaCena(), explosao.getFrameX(), explosao.getFrameY(), explosao.getFrameX()+explosao.getAlturaCena(), explosao.getFrameY()+explosao.getAlturaCena(), this);
	  player.fimTiro();
	  inimigo1.setY(20);
	  inimigo1.setxAleatorio(20, 420);
	  score += 1;
  } 
   
  if (inimigo1.atirou()) { 
      bbg.setColor(new Color(255,0,0)); 
      bbg.fillRect(inimigo1.getX()+33, inimigo1.getTiroy(), 2, 8);       
       } 
   
  //AQUI ESTAMOS DESENHANDO O BUFFER NA TELA, 
  //NAS COORDENADAS X:0 e Y:0   
  g.drawImage(backBuffer, 0, 0, this); 
 } 
 //ESSE É O NOSSO MÉTODO INICIALIZAR 
 //AQUI VAMOS INICIALIZAR ALGUMAS CONFIGURAÇÃO DO frame E OUTRAS CONFIGURAÇÕES 
 public void inicializar() { 
  setTitle("Space Eagle");//SETANDO O TITULO DA JANELA 
  setSize(janelaW, janelaH);//DEFINIDO AS DIMENSÕES DA JANELA 
  setResizable(false);//TIRANDO A PERMISSÃO DO USUÁRIO REDIMENSIONAR A JANELA 
  setDefaultCloseOperation(EXIT_ON_CLOSE);//QUANDO FECHARMOS O frame A APLICAÇÃO PARA DE EXECUTAR 
  setLayout(null);//COM ISSO PODEREMOS DEFINIAR COORDENADA E DIMESÕES DE ELMENTOS DE FORMULARIO NO NOSSO FRAME 
  setVisible(true);//MUDANDO A VISIBILIDADE DO frame PARA TRUE, ASSIM ELE APARECERÁ 
  backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);//CRIANDO O NOSSO BUFFER DE IMAGEM 
   
  player.setVelocidadeTiro(10); 
  player.setVelocidadeX(8);   
  inimigo1.setxAleatorio(20, 420); 
  inimigo1.setVelocidadeY(2); 
  inimigo1.setVelocidadeTiro(10); 
   
  //AQUI ESTAMOS ADICIONANDO UM ESCUTADOR DE TECLAS 
  addKeyListener(this); 
//=========================================================================== 
  //========== É AQUI QUE EU TO CHAMANDO O MÉTODO tocarMusica()================ 
  //tocarMusica(musica1, 999); //CHAMAMOS O TOCADOR DENTRO DO MÉTODO inicializar() 
  //ENTÃO AO INICAR O GAME, ESSA SERÁ A NOSSA MÚSICA DE FUNDO 
  //=========================================================================== 
     
 } 
 //AQUI É O NOSSO MÉTODO RUN() 
 //NELE TEMOS O NOSSO GAME LOOP (UM LOOP INFINITO) 
 public void run() { 
  inicializar();//AQUI CHAMAMOS O METODO INICIALIZAR SOMENTE UMA VEZ, POIS ELE ESTÁ FORA DO NOSSO LOOP 
  while (true) {//AQUI É O NOSSO LOOP INFINITO 
   atualizar();//CHAMAMOS O METODO ATUALIZAR O TEMPO INTEIRO 
   desenharGraficos();//ATUALIZAREMOS O GRÁFICO QUE APARECE NA TELA O TEMPO INTEIRO 
    try { 
     Thread.sleep(1000/FPS); //TAXA DE ATUALIZAÇÃO NA TELA, FUNCIONA COMO UM DELAY 
    } catch (Exception e) { 
     System.out.println("Thread interrompida!"); 
    } 
  } 
 } 
 
@Override 
public void keyPressed(KeyEvent e) { 
if(e.getKeyCode() == KeyEvent.VK_LEFT ){        
   player.moverEsquerda(-20);    
 } 
if(e.getKeyCode() == KeyEvent.VK_RIGHT ){        
   player.moverDireita(424);    
 } 
if(e.getKeyCode() == KeyEvent.VK_SPACE ){        
  player.atirar();             
 } 
} 
 
@Override 
public void keyReleased(KeyEvent e) { 
// TODO Auto-generated method stub 
} 
 
@Override 
public void keyTyped(KeyEvent e) { 
// TODO Auto-generated method stub 
} 
 
 
 } 