package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Casella;
import model.Nivell;

//import edu.upc.fib.wordguess.domain.model.Category;
//import edu.upc.fib.wordguess.util.Log;


public class JugarPartidaView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final int MINA = -1;
	private static final int MARCADA = -2;
	private static final int BLANC = -3;
	
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
	private JButton but[][];
	JPanel gridPanel;
	JLabel lb_nivell;
	JLabel tir = new JLabel("0");
	Timer timer;
	JLabel temps = new JLabel("0");
	private int nF;
	private int nC;
	
	
	ActionListener updateTimerLabel = new ActionListener() {
		public void actionPerformed (ActionEvent e){
			int t = Integer.parseInt(temps.getText());
			pmc.setTemps(t);
			temps.setText(String.valueOf(t+1));
		}
	};
	
	
	public void updateBoard(Casella[][] caselles) {
		for(int i = 0; i < nF; ++i)
			for(int j = 0; j < nC; ++j) {
				int val = pmc.checkCasella(i, j);
				if(val == MARCADA) but[i][j].setText("M");
				else if(val == BLANC) but[i][j].setText("");
				else if(val == MINA) {
					but[i][j].setOpaque(true);
					but[i][j].setBackground(Color.RED);
					but[i][j].setText("X");
				}
				else {
					but[i][j].setText(String.valueOf(val));
					but[i][j].setEnabled(false);
				}
			}
		
		
		
		gridPanel.revalidate();
		gridPanel.repaint();
		
	}
	
	
	public void buildBoard(Casella[][] caselles, Nivell n) {
		gridPanel = new JPanel();
		timer = new Timer(1000, updateTimerLabel);
		timer.start();
		lb_nivell.setText(n.getNom());
		nF = n.getNombreCasellesxFila();
		nC = n.getNombreCasellesxColumna();
		int sizeX, sizeY;
		if(nC <= 10) sizeX = sizeY = 250;
		else if (nC <= 16) sizeX = sizeY = 300;
		else {
			sizeX = 500;
			sizeY = 300;
		}
		
		but = new JButton[nF][nC];
		
		Dimension size = new Dimension(sizeX,sizeY);
		
		gridPanel.setLayout(new GridLayout(nF, nC));
		gridPanel.setPreferredSize(size);
		gridPanel.setMaximumSize(size);
		gridPanel.setMinimumSize(size);
		gridPanel.setSize(size);
		gridPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=1;
		c.gridy=2;
		c.gridheight=GridBagConstraints.RELATIVE;
		for(int i = 0; i < nF; ++i)
			for(int j = 0; j < nC; ++j) {
				but[i][j] =  new JButton("");
				but[i][j].addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
						JButton b = (JButton) e.getSource();
						for(int i = 0; i < nF; ++i) {
							for(int j = 0; j < nC; ++j){
								if(but[i][j].equals(b)) {
									try {
										if(SwingUtilities.isRightMouseButton(e)) {
											pmc.PrCheck(i, j, 2);
										}
										else if (!b.getText().equals("M")){
											pmc.PrCheck(i, j, 1);
										}
										
									} catch (IOException eX){
										
									}
								}
							}
						}
					}
					
					@Override
					public void mousePressed(MouseEvent e){}
					@Override
					public void mouseExited(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseClicked(MouseEvent e) {}
				});
				
				gridPanel.add(but[i][j]);
			}
		matchPanel.add(gridPanel,c);
		
		
		
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
						lb_messagesCategoriesPanel.setText("");
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
			
			
			JButton btn_back = new JButton("Back");
			GridBagConstraints c = new GridBagConstraints();
			c.gridy=0;
			c.gridx=0;
			
			btn_back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					timer.stop();
					temps.setText("0");
					pmc.PrStopMatch();
					setContentPane(categoriesSelectionPanel);
					categoriesSelectionPanel.updateUI();
					matchPanel.remove(gridPanel);
				}
			});
			
			add(btn_back, c);
			
			lb_nivell = new JLabel("NIVELL");
			lb_nivell.setFont(new java.awt.Font("Arial",1,18));
			c.gridy=0;
			c.gridx=1;
			add(lb_nivell, c);
			
			JLabel lb_tirades = new JLabel("Tirades:");
			c.gridy=0;
			c.gridx=3;
			add(lb_tirades, c);
			
			
			c.gridx=4;
			add(tir, c);
			
			JLabel lb_temps = new JLabel("Temps:");
			c.gridx=3;
			c.gridy=1;
			add(lb_temps, c);
			
			c.gridx=4;
			add(temps, c);
			
			
			//boto aturar partida
			btn_stopMatch = new JButton("Aturar");
			btn_stopMatch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pmc.PrStopMatch();
					setContentPane(categoriesSelectionPanel);
					categoriesSelectionPanel.updateUI();
					for(JButton[] buttons : but)
						for(JButton b: buttons) matchPanel.remove(b);
				}
			});
			
			btn_stopMatch.setBackground( new Color( 255, 172, 92) );
			btn_stopMatch.setBounds( 150 , 290, 100, 35);
			btn_stopMatch.setFont(new java.awt.Font("Tahoma", Font.BOLD, 15));
			btn_stopMatch.setBorder( BorderFactory.createLineBorder( new Color(255,103,1), 2 ));
			
			btn_stopMatch.setVisible(true);
			//c2.fill = GridBagConstraints.HORIZONTAL;
			c.gridy=3;
			c.gridx=0;
			
			add(btn_stopMatch, c);
			
			
			lb_messagesMatchPanel = new JLabel("");
			c.gridy=4;
			c.gridx=1;
			add(lb_messagesMatchPanel,c);
			
			
			
			
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
			c.gridy=5;
			c.gridx=1;
			add(btn_finishMatch,c);
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
					try {
						pmc.PrStartMatch(cat);
						setContentPane(matchPanel);
						
					} catch (Exception e) {
						showMessage(e.toString(), 1);
					}
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
	
	public void actualitzaTirades(int t) {
		this.tir.setText(String.valueOf(t));
	}
	
	
	public void stopMatch() {
		/**Funci� corresponent al event de click sobre el bot� "Aturar Partida"*/
		setContentPane(login);
		login.updateUI();
	}
	
	public void close() {
		/**Funci� corresponent al event de click sobre el bot� "Tancar Partida"*/
		System.exit(-1);
	}
	
	public void finishMatch(boolean guanyada, int puntuacio) {
		/**Quan una partida �s finalitzada, pot ser que o b� hagui guanyat o b� hagui superat
		  la quantitat maxima d'errors permesos, en funci� d'aquestes possibilitats actualitzem
		  els labels de missatges i l'aparici� o no, del botons corresponents a l'interface*/
		this.matchWon = guanyada;
		timer.stop();
		for(JButton[] buttons : but)
			for(JButton b : buttons) {
				for(MouseListener l : b.getMouseListeners()) b.removeMouseListener(l);
				b.setEnabled(false);
			}
		if (guanyada) {
			lb_messagesMatchPanel.setForeground( new Color( 0, 113, 0 ) );
			lb_messagesMatchPanel.setText("Enhorabona has guanyat la partida! Puntuacio: "+puntuacio);
		}
		else lb_messagesMatchPanel.setText("Has trobat una mina i has perdut! Puntuacio: "+puntuacio);
		lb_messagesMatchPanel.repaint();
		btn_stopMatch.setVisible(false);
		btn_finishMatch.setVisible(true);		
	}
	/*
	public void markLetterBox(boolean encert) {
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
	}*/
}
