package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Casella;
import model.Nivell;

//import edu.upc.fib.wordguess.domain.model.Category;
//import edu.upc.fib.wordguess.util.Log;

public class JugarPartidaView extends JFrame {
	
	CtrlJugarPartidaPresentacio pmc;
	JLogin login;
	JSelniv categoriesSelectionPanel;
	JPartidaEnJoc matchPanel;
	JTextField [] letters;
	JComboBox<String> cBox_Nivells;
	JLabel lb_messagesLoginPanel;
	JLabel lb_messagesMatchPanel;
	JLabel lb_messagesCategoriesPanel;
	JLabel lb_pointsPerCorrectLetter;
	JLabel lb_PointsPerError;
	JLabel lb_currentPoints;
	JLabel lb_ErrorCounter;
	JButton btn_startMatch;
	JButton btn_CheckLetter;
	JButton btn_stopMatch;
	JButton btn_finishMatch;
	private int index;
	private boolean matchWon;
	private int numLetters;
	private int maxErrors;
	
	public void disableLetterBoxes (int pos) {
		for (int i=0; i<numLetters; ++i) {
			if (i!=pos) {
				letters[i].setEnabled(false);
			}
		}
	}
	
	public void buildBoard(Casella[][] caselles, Nivell n) {
		int nF = n.getNombreCasellesxFila();
		int nC = n.getNombreCasellesxColumna();
		int sizeX, sizeY;
		if(nC <= 10) sizeX = sizeY = 250;
		else if (nC <= 16) sizeX = sizeY = 300;
		else {
			sizeX = 500;
			sizeY = 300;
		}
		
		
		JButton but[][] = new JButton[nF][nC];
		JPanel gridPanel = new JPanel();
		Dimension size = new Dimension(sizeX,sizeY);
		
		gridPanel.setLayout(new GridLayout(nF, nC));
		gridPanel.setBackground(Color.blue);
		gridPanel.setPreferredSize(size);
		gridPanel.setMaximumSize(size);
		gridPanel.setMinimumSize(size);
		gridPanel.setSize(size);
		gridPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		for(int i = 0; i < nF; ++i)
			for(int j = 0; j < nC; ++j) {
				but[i][j] =  new JButton(String.valueOf(caselles[i][j].getNumMines()));
				but[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton) e.getSource();
				    	 b.setText("X");
				    	 System.out.println("press");
					}
				});
				gridPanel.add(but[i][j]);
			}
		matchPanel.add(gridPanel);
		
		
		
		gridPanel.revalidate();
		gridPanel.repaint();
		
	}
	
	public class JLogin extends JPanel {
		
		private static final long serialVersionUID = 1L;
		private JTextField userField;
		private JPasswordField passField;
		public JLogin() {
			setLayout(null);
			
			//label usuari
			JLabel lb_user = new JLabel("Usuari:");
			lb_user.setFont(new java.awt.Font("Arial",0,20));
			lb_user.setBounds(160, 100, 150, 30);
			lb_user.setHorizontalAlignment(SwingConstants.CENTER);
			add(lb_user);
			
			//label contrasenya
			JLabel lb_pass = new JLabel("Password:");
			lb_pass.setFont(new java.awt.Font("Arial",0,20));
			lb_pass.setBounds(160, 150, 130, 30);
			lb_pass.setHorizontalAlignment(SwingConstants.CENTER);
			add(lb_pass);
			
			
			//textField username
			userField = new JTextField();
			userField.setBounds(320, 100, 130, 30);
			//userField.setHorizontalAlignment(SwingConstants.CENTER);
			userField.setBackground( Color.LIGHT_GRAY );
			//userField.setBorder( BorderFactory.createLineBorder( new Color(160, 160, 160), 2 ));
			userField.setFont(new java.awt.Font("Arial",0,17));
			add(userField);
			userField.setColumns(10);
			
			//textField contrasenya
			passField = new JPasswordField();
			passField.setBounds(320, 150, 130, 30);
			//passField.setHorizontalAlignment(SwingConstants.CENTER);
			passField.setBackground( Color.lightGray );
			//passField.setBorder( BorderFactory.createLineBorder( new Color(160, 160, 160), 2 ));
			passField.setFont(new java.awt.Font("Arial",0,17));
			add(passField);
			
			//panell missatges
			lb_messagesLoginPanel = new JLabel("",JLabel.CENTER);
			lb_messagesLoginPanel.setBounds(150, 200, 300, 60);
			lb_messagesLoginPanel.setHorizontalAlignment(SwingConstants.CENTER);
			lb_messagesLoginPanel.setFont(new java.awt.Font("Arial",0,17));
			add(lb_messagesLoginPanel);
			
			//boto de login
			JButton btn_login = new JButton("Entrar");
			btn_login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String username = userField.getText();
					String pass = passField.getText();
					if(pmc.PrLogin(username,pass)) {
						setContentPane(categoriesSelectionPanel);
						categoriesSelectionPanel.updateUI();
					}
					userField.setText("");
					passField.setText("");
				}
			});
			//btn_login.setBackground( new Color( 117, 255, 71) );
			btn_login.setBounds(250, 270, 100, 35);
			btn_login.setFont(new java.awt.Font("Arial", Font.BOLD, 15));
			//btn_login.setBorder( BorderFactory.createLineBorder( new Color(0,133,0), 2 ));
			add(btn_login);
		}
	}
	
	
	public class JPartidaEnJoc extends JPanel {
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	/**Pantalla corresponent a la partida que s'est� jugant
		* Mostra puntuaci� actual, num errors i les caselles per tal d'endevinar la paraula, 
		* a m�s apareixar�n els botons: comprovar, aturar partida i tancar partida*/
		public JPartidaEnJoc() {
			setLayout(new GridBagLayout());
			
			
			/*
			// marcador puntucio actual
			lb_currentPoints = new JLabel("Punts:",SwingConstants.CENTER);
			lb_currentPoints.setBounds(102, 62, 172, 35);
			lb_currentPoints.setFont(new java.awt.Font("Tahoma",1,18));
			lb_currentPoints.setBackground( new Color( 51, 102, 255 ) );
			lb_currentPoints.setForeground(Color.white);
			lb_currentPoints.setOpaque(true);
			add(lb_currentPoints);		
			
			//marcador errors
			lb_ErrorCounter = new JLabel("Errors 0 de X:",SwingConstants.CENTER);
			lb_ErrorCounter.setBounds(323, 62, 172, 35);
			lb_ErrorCounter.setFont(new java.awt.Font("Tahoma",1,18));
			//lbErrors.setFont(boldfont);
			lb_ErrorCounter.setBackground(Color.gray);
			lb_ErrorCounter.setForeground(Color.white);
			lb_ErrorCounter.setOpaque(true);
			add(lb_ErrorCounter);
			
			//area missatges
			lb_messagesMatchPanel = new JLabel("",SwingConstants.CENTER);
			lb_messagesMatchPanel.setBounds(100, 240, 400, 30);
			lb_messagesMatchPanel.setHorizontalAlignment(SwingConstants.CENTER);
			lb_messagesMatchPanel.setVerticalAlignment(SwingConstants.CENTER);
			lb_messagesMatchPanel.setFont(new java.awt.Font("Tahoma",1,15));
			add(lb_messagesMatchPanel);
			
			//lbPuntsEncert
			lb_pointsPerCorrectLetter = new JLabel("+10",SwingConstants.CENTER);
			lb_pointsPerCorrectLetter.setBounds(175, 115, 250, 25);
			lb_pointsPerCorrectLetter.setFont(new java.awt.Font("Tahoma",1,14));
			lb_pointsPerCorrectLetter.setForeground( new Color(0,133,0) );
			lb_pointsPerCorrectLetter.setOpaque(true);
			add(lb_pointsPerCorrectLetter);
			
			lb_PointsPerError = new JLabel("-5",SwingConstants.CENTER);
			lb_PointsPerError.setBounds(175, 140, 250, 25);
			lb_PointsPerError.setFont(new java.awt.Font("Tahoma",1,14));
			//lb_PuntsError.setFont(boldfont);
			//lb_PuntsError.setBackground(Color.red);
			lb_PointsPerError.setForeground(Color.red);
			lb_PointsPerError.setOpaque(true);
			add(lb_PointsPerError);
				
			//boto aturar partida
			btn_stopMatch = new JButton("Aturar");
			btn_stopMatch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pmc.PrStopMatch();
					setContentPane(categoriesSelectionPanel);
					categoriesSelectionPanel.updateUI();
					for (int i=0; i<numLetters; ++i) matchPanel.remove(letters[i]);
				}
			});
			
			btn_stopMatch.setBackground( new Color( 255, 172, 92) );
			btn_stopMatch.setBounds( 150 , 290, 100, 35);
			btn_stopMatch.setFont(new java.awt.Font("Tahoma", Font.BOLD, 15));
			btn_stopMatch.setBorder( BorderFactory.createLineBorder( new Color(255,103,1), 2 ));
			
			btn_stopMatch.setVisible(true);
			add(btn_stopMatch);
			
			
			//boto comprovar
			btn_CheckLetter = new JButton("Comprovar");
			btn_CheckLetter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					  try {
						  lb_messagesMatchPanel.setText("");
						  //pmc.PrCheck(index,letters[index].getText());
					  } catch(Exception e) {
						  e.printStackTrace();
						  if (!matchWon) {
							  System.out.println("Saltem al catch!");
							  lb_messagesMatchPanel.setText("La casella no pot estar buida");
						  }
					  }
				}
			});
			
			btn_CheckLetter.setBackground( new Color( 117, 255, 71) );
			btn_CheckLetter.setBounds(350, 290, 100, 35);
			btn_CheckLetter.setFont(new java.awt.Font("Tahoma", Font.BOLD, 15));
			btn_CheckLetter.setBorder( BorderFactory.createLineBorder( new Color(0,133,0), 2 ));
			
			btn_CheckLetter.setVisible(true);
			add(btn_CheckLetter);
			
			btn_finishMatch = new JButton("Tancar");
			btn_finishMatch.setBackground( new Color( 255, 112, 112) );
			btn_finishMatch.setBounds( 250 , 290, 100, 35);
			btn_finishMatch.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
			btn_finishMatch.setBorder( BorderFactory.createLineBorder( new Color(133,0,0), 2 ));
			
			btn_finishMatch.setVisible(false);
			btn_finishMatch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pmc.PrFinishGame();
				}
			});
			add(btn_finishMatch);*/
		}
	}
	
	public class JSelniv extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**Pantalla corresponent a seleccionar una categoria de paraules per tal de que la partida
		 *sigui creada amb una paraula random de les corresponents a la categoria escollida*/
		public JSelniv() {
			setLayout(null);
			
			
			//label seleccionar categoria
			JLabel lb_Selcat = new JLabel("Tria una nivell:");
			lb_Selcat.setFont(new java.awt.Font("Tahoma",0,20));
			lb_Selcat.setHorizontalAlignment(SwingConstants.CENTER);
			lb_Selcat.setBounds(200, 60, 200, 30);
			add(lb_Selcat);
			
			//combobox
			cBox_Nivells = new JComboBox<String>();
			cBox_Nivells.setBorder( BorderFactory.createLineBorder( new Color(160, 160, 160), 2 ));
			cBox_Nivells.setFont(new java.awt.Font("Tahoma",1, 14));
			cBox_Nivells.setBounds(220, 120, 160, 30);
			add(cBox_Nivells);
			
			//label missatge
			lb_messagesCategoriesPanel = new JLabel("",JLabel.CENTER);
			lb_messagesCategoriesPanel.setBounds(100, 170, 400, 40 );
			lb_messagesCategoriesPanel.setHorizontalAlignment(SwingConstants.CENTER);
			lb_messagesCategoriesPanel.setFont(new java.awt.Font("Tahoma",0,17));
			add(lb_messagesCategoriesPanel);
			
			//boto logout
			JButton btn_logout = new JButton("Logout");
			btn_logout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lb_messagesLoginPanel.setText("");
					setContentPane(login);
					login.updateUI();
					btn_startMatch.setEnabled(true);
				}
			});
			btn_logout.setBackground( new Color( 255, 92, 92) );
			btn_logout.setBounds( 150 , 280, 100, 35);
			btn_logout.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
			btn_logout.setBorder( BorderFactory.createLineBorder( new Color(133,0,0), 2 ));
			add(btn_logout);
			
			//boto ok
			btn_startMatch = new JButton("Jugar");
			btn_startMatch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String cat = String.valueOf(cBox_Nivells.getSelectedItem());
					//try {
						pmc.PrStartMatch(cat);
						setContentPane(matchPanel);
						
					//} catch (UserIsNotPlayerException e) {
					//	Log.debug("PlayMatchView", "user is not player");
						showMessage("L'usuari no es un jugador.", 1);
					//}
					matchPanel.updateUI();
				}
			});

			btn_startMatch.setBackground( new Color( 92, 92, 255) );
			btn_startMatch.setBounds( 350 , 280, 100, 35);
			btn_startMatch.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
			btn_startMatch.setBorder( BorderFactory.createLineBorder( new Color(0,0,133), 2 ));
			add(btn_startMatch);
			
		}
	}


	public JugarPartidaView(CtrlJugarPartidaPresentacio jpc) {
		pmc = jpc;
		login = new JLogin();
		categoriesSelectionPanel = new JSelniv();
		matchPanel = new JPartidaEnJoc();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600 , 400);
		this.setTitle("Buscamines");
		setContentPane(login);
	}
	
        public void loadNivells(ArrayList<String> nivells) {
                cBox_Nivells.removeAllItems();
                for (String st : nivells) {
                	cBox_Nivells.addItem(st);
                }
        }
	
	public void showMessage(String text,int panelNumber) {
		/**Mostra un missatge al label corresponent en funci� de la pantalla indicada*/
		if(panelNumber==0) {
			//Pantalla de Login
			lb_messagesLoginPanel.setText(text);
			lb_messagesLoginPanel.setForeground(Color.red);
		}
		else if(panelNumber==1) {
			//Pantalla de Categories
			lb_messagesCategoriesPanel.setText(text);
			lb_messagesCategoriesPanel.setForeground(Color.red);
			btn_startMatch.setEnabled(false);
		}
		else {
			//Pantalla de Partida
			lb_messagesMatchPanel.setText(text);
			lb_messagesMatchPanel.setForeground(Color.red);
		}		
	}
	
	public void loadPointsPer(int en, int err) {
		/**Mostra la quantitat de punts per error i punt per encert definit pr�viament a 
		  l'estrat�gia que se l'hi ha aplicat a la partida*/
		String encert = Integer.toString(en);
		String error = Integer.toString(err);
		lb_pointsPerCorrectLetter.setText("Punts per encert: +"+encert);
		lb_PointsPerError.setText("Punts per fallada: "+error);
	}
	
	public void updateCurrentScoring(int points) {
		/**Actualitza la puntuaci� actual*/
		String Spoints = Integer.toString(points);
		lb_currentPoints.setText("Punts: \n"+ Spoints);
	}
		
	public void updateErrorCount(int ea,int nme) {
		/**Actualitza la quantitat d'errors actuals respecte el nombre m�xim d'errors permesos*/
		this.maxErrors = nme;
		String errActuals = Integer.toString(ea);
		String numMaxErr = Integer.toString(nme);
		this.lb_ErrorCounter.setText("Errors : "+ea+" de "+maxErrors);
	}
	public void updateErrors(int ea) {
		/**Actualitza la quantitat d'errors actuals respecte el nombre m�xim d'errors permesos*/
		String errActuals = Integer.toString(ea);
		lb_ErrorCounter.setText("Errors : "+ea+" de "+maxErrors );
	}
	
	public void emptyLetterBoxes() {
		/**Buidar el text corresponent a les caselles de la partida*/
		for(int i=0; i < letters.length; ++i ) {
			letters[i].setText("");
		}
	}
	
	public void stopMatch() {
		/**Funci� corresponent al event de click sobre el bot� "Aturar Partida"*/
		emptyLetterBoxes();
		setContentPane(login);
		login.updateUI();
	}
	
	public void close() {
		/**Funci� corresponent al event de click sobre el bot� "Tancar Partida"*/
		System.exit(-1);
	}
	
	public void finishMatch(boolean guanyada) {
		/**Quan una partida �s finalitzada, pot ser que o b� hagui guanyat o b� hagui superat
		  la quantitat maxima d'errors permesos, en funci� d'aquestes possibilitats actualitzem
		  els labels de missatges i l'aparici� o no, del botons corresponents a l'interface*/
		this.matchWon = guanyada;
		if (guanyada) {
			lb_messagesMatchPanel.setForeground( new Color( 0, 113, 0 ) );
			lb_messagesMatchPanel.setText("Enhorabona has guanyat la partida!");
		}
		else lb_messagesMatchPanel.setText("Has superat el nombre maxim d'errors");
		btn_CheckLetter.setVisible(false);
		btn_stopMatch.setVisible(false);
		btn_finishMatch.setVisible(true);		
	}
	
	public void markLetterBox(boolean encert) {
		/**Pinta la casella corresponent de color verd o vermell en funci� de si en aquesta casella
		  s'ha produ�t un encert o una fallada*/
		if(encert) {
			letters[index].setBackground(Color.green);
		}
		else {
			letters[index].setBackground(Color.red);
			showMessage("La lletra es incorrecta",2);
		}
		for (int i=0; i<numLetters; ++i) {
			if (letters[i].getBackground() == Color.green) {
				letters[i].setEnabled(false);
				letters[i].setDisabledTextColor(Color.BLACK);
			}
			else {
				letters[i].setEnabled(true);
			}
			letters[i].setBorder(UIManager.getBorder("TextField.border"));
		}
		if(!encert) letters[index].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
	}
}
