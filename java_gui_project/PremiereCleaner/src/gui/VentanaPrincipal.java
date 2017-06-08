package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JViewport;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JProgressBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Dimension;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCentro;
	private JPanel panelTop;
	private JLabel lblAdobePremierePro;
	private JPanel panelCentroTop;
	private JButton btSeleccionar;
	private JLabel lblSeleccionarDirectorioPara;
	private JPanel panel_1;
	private JPanel panel_2;
	private JTextField txPathSeleccionado;
	private JButton btEjecutar;
	//private final String DEFAULT_PATH = "J:\\";
	private final String DEFAULT_PATH = "C:\\Users\\";
	
	VentanaPrincipal vp;
	private ArrayList<File> listaArchivos = new ArrayList<File>();
	private ArrayList<File> listaNOEliminados = new ArrayList<File>();
	private ArrayList<File> listaDirectoriosPRV = new ArrayList<File>();
	
	private JScrollPane scrollPane;
	private JTextArea txAreaLog;
	private int contadorCFA = 0;
	private int contadorPEK = 0;
	private int contadorAVI = 0;
	private int contadorDirectoriosPRV = 0;
	private int contadorNoEliminados = 0;
	private int tamCFA = 0;	// contador para calcular tamaños totales
	private int tamPEK = 0;
	private int tamAVI = 0;
	
	private JMenuBar menuBar;
	private JMenu mnAplicacin;
	private JMenu mnAyuda;
	private JMenuItem mntmSeleccionarDirectorio;
	private JMenuItem mntmEjecutar;
	private JSeparator separator;
	private JMenuItem mntmSalir;
	private JMenuItem mntmAcercaDe;
	private JMenu mnOpciones;
	private JCheckBoxMenuItem chckbxmntmMostrarRutaCompleta;
	private JButton btnSoloEscanear;
	private JProgressBar progressBar;
	private JPanel panel;
	private JLabel lbTiempoEjecucion;
	private JPanel panel_3;
	private JMenuItem mntmReiniciar;
	private JLabel lbIcono;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setMinimumSize(new Dimension(683, 400));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		vp = this;
		setTitle("Premiere Pro Cleaner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 559);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelTop(), BorderLayout.NORTH);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		
	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new BorderLayout(0, 0));
			panelCentro.add(getPanelCentroTop(), BorderLayout.NORTH);
			panelCentro.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelCentro;
	}
	private JPanel getPanelTop() {
		if (panelTop == null) {
			panelTop = new JPanel();
			panelTop.add(getLbIcono());
			panelTop.add(getLblAdobePremierePro());
		}
		return panelTop;
	}
	private JLabel getLblAdobePremierePro() {
		if (lblAdobePremierePro == null) {
			lblAdobePremierePro = new JLabel("Premiere Pro Cleaner");
			lblAdobePremierePro.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		return lblAdobePremierePro;
	}
	private JPanel getPanelCentroTop() {
		if (panelCentroTop == null) {
			panelCentroTop = new JPanel();
			panelCentroTop.setLayout(new BorderLayout(0, 0));
			panelCentroTop.add(getPanel_1());
			panelCentroTop.add(getPanel_2(), BorderLayout.SOUTH);
		}
		return panelCentroTop;
	}
	private JButton getBtSeleccionar() {
		if (btSeleccionar == null) {
			btSeleccionar = new JButton("Seleccionar directorio");
			btSeleccionar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					seleccionarDirectorio();
				}
			});
		}
		return btSeleccionar;
	}
	private JLabel getLblSeleccionarDirectorioPara() {
		if (lblSeleccionarDirectorioPara == null) {
			lblSeleccionarDirectorioPara = new JLabel("Seleccionar directorio para escanear y limpiar:");
			lblSeleccionarDirectorioPara.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lblSeleccionarDirectorioPara;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getLblSeleccionarDirectorioPara());
			panel_1.add(getBtSeleccionar());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
			panel_2.add(getTxPathSeleccionado());
			panel_2.add(getBtEjecutar());
			panel_2.add(getBtnSoloEscanear());
		}
		return panel_2;
	}
	private JTextField getTxPathSeleccionado() {
		if (txPathSeleccionado == null) {
			txPathSeleccionado = new JTextField();
			txPathSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txPathSeleccionado.setText(DEFAULT_PATH);
			txPathSeleccionado.setColumns(30);
		}
		return txPathSeleccionado;
	}
	private JButton getBtEjecutar() {
		if (btEjecutar == null) {
			btEjecutar = new JButton("Escanear y limpiar");
			btEjecutar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					MyThread myThread = new MyThread();
					myThread.start();
					//escanear();
					eliminarArchivos();
				}
			});
			btEjecutar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return btEjecutar;
	}

	/**
	 * Escanea y lista los archivos recursivamente a partir del directorio pasado por parametro
	 * @param directoryName
	 */
	public void escanearArchivos(String directoryName) {
	    File directorio = new File(directoryName);
	    
//    	System.out.println(directorio.getName());
	    if((directoryName.toString().equals(txPathSeleccionado.getText().toString())) || 
	    		(!directorio.isHidden() && !directorio.getName().startsWith("$"))){
	    	
//	    	System.out.println("-> "+txPathSeleccionado.getText().toString() + " -- " + directoryName);
//	    	System.out.println(directoryName.toString().equals(txPathSeleccionado.getText().toString()));
//	    	System.out.println(directorio.isHidden());
//	    	System.out.println(directorio.getName().startsWith("$"));
	    	
	    	if(directorio.getName() == "RECYCLER")
	    		System.err.println("ENTRO EN CAMPO PELIGROSO. DEBUG.");
		    File[] fList = directorio.listFiles();
		    
		    progressBar.setString(directorio.getAbsolutePath());
		    for (int i=0; fList != null && i<fList.length;i++) {
		    	File file = fList[i];
		    	if(!file.isHidden()){
		    		String printName;
		        	if(chckbxmntmMostrarRutaCompleta.isSelected())
		        		printName = file.getAbsolutePath();
		        	else
		        		printName = file.getName();
			        if (file.isFile()) {
			        	if(printName.endsWith(".cfa")){
			        		contadorCFA += 1;
			        		tamCFA += file.length();
			        	}else if(printName.endsWith(".pek")){
			        		contadorPEK += 1;
			        		tamPEK += file.length();
			        	}else if(printName.startsWith("Rendered - ") && printName.endsWith(".AVI")){
			        		contadorAVI += 1;
			        		tamAVI += file.length();
			        	}
			        	if(printName.endsWith(".cfa") || printName.endsWith(".pek") || (printName.startsWith("Rendered - ") && printName.endsWith(".AVI"))){
			        		listaArchivos.add(file);
			        		txAreaLog.append("   "+printName+" - "+file.length()/1000000.0+" MB\n");
			        	}
			        	
			        }else if (file.isDirectory()) {
			        	// simulación imperfecta de progreso
			        	if(progressBar.getValue() < progressBar.getMaximum()-1){
			        		progressBar.setValue(progressBar.getValue()+1);
			        		progressBar.repaint();
			        	}
			        	if(printName.endsWith(".PRV")){
			        		if(txAreaLog.getText().equals(""))
			        			txAreaLog.append(""+printName+"\n");
			        		else
			        			txAreaLog.append("\n"+printName+"\n");
			        		listaDirectoriosPRV.add(file);
			        		contadorDirectoriosPRV += 1;
			        	}
			        	if(file.getName() == "RECYCLER")
				    		System.err.println("######## ENTRO EN CAMPO PELIGROSO. DEBUG. ########");
			        	escanearArchivos(file.getAbsolutePath());
			        }
		        }
		    } // fin for
	    }	// fin if recycler
	}
	
	/**
	 * Método que elimina TODOS los archivos encontrados
	 */
	private void eliminarArchivos(){
		int contadorEliminados = 0;
		for (File file : listaArchivos) {
			if(!file.delete()){
				listaNOEliminados.add(file);
				contadorNoEliminados += 1;
			}else
				contadorEliminados += 1;
		}
		for (File file : listaDirectoriosPRV) {
			file.delete();
		}
		if(contadorEliminados > 0){
			JOptionPane.showMessageDialog(vp, "Se han eliminado " + contadorEliminados + " archivos", "Limpieza finalizada", JOptionPane.INFORMATION_MESSAGE);
			//txAreaLog.append("## LIMPIEZA FINALIZADA ##\nSe han eliminado " + contadorEliminados + " archivos.");
		}
		if(contadorNoEliminados > 0){
			JOptionPane.showMessageDialog(vp, "No se han eliminado " + contadorNoEliminados + " archivos", "¡Atención!", JOptionPane.WARNING_MESSAGE);
			txAreaLog.append("\n *** ¡Atención! No se han eliminado " + contadorNoEliminados + " archivos ***");
			for (File file : listaNOEliminados) {
				if(chckbxmntmMostrarRutaCompleta.isSelected())
					txAreaLog.append(file.getAbsolutePath() + "\n");
				else
					txAreaLog.append(file.getName() + "\n");
			}
		}
		resetData();
	}
	
	private void seleccionarDirectorio(){
		JFileChooser jf = new JFileChooser();
		jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		if(jf.showOpenDialog(vp) == jf.APPROVE_OPTION){
			txPathSeleccionado.setText(jf.getSelectedFile().getAbsolutePath());
			btEjecutar.setFont(new Font("Tahoma", Font.BOLD, 11));
			btEjecutar.setEnabled(true);
			btEjecutar.setForeground(Color.BLACK);
			btEjecutar.grabFocus();
		}
	}
	
	/**
	 * Método escanea e imprime resultados.
	 */
	public void escanear(){
		File f = new File(txPathSeleccionado.getText().toString());
		if(f.exists()){
			txAreaLog.setText("");
			lbTiempoEjecucion.setText("");
			progressBar.setValue(0);
			progressBar.repaint();
			vp.repaint();
			//System.out.println(new File(txPathSeleccionado.getText()).list().length);
			long t1 = System.currentTimeMillis();
			escanearArchivos(txPathSeleccionado.getText());
			long t2 = System.currentTimeMillis();
			progressBar.setValue(progressBar.getMaximum());
			progressBar.setString("100%");
			lbTiempoEjecucion.setText("Tiempo: "+ (t2-t1) + " ms");
			printStats("ENCONTRADOS");
			JOptionPane.showMessageDialog(vp, "¡Ejecución finalizada!", "Escaneo completo", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(vp, "¡El directorio/archivo seleccionado: < "+txPathSeleccionado.getText()+" >  no existe!", "Error, no te inventes la ruta ;)", JOptionPane.ERROR_MESSAGE);
			btSeleccionar.grabFocus();
		}
	}
	
	/**
	 * Imprime estadisticas de la ejecución
	 * @param accion
	 */
	private void printStats(String accion){
		int tamTotal = tamAVI + tamPEK +tamCFA;
		
//		System.out.println("#### RESUMEN ARCHIVOS "+accion+" ###");
//		System.out.println(contadorDirectoriosPRV + " directorios temporales encontrados");
//		System.out.println(contadorAVI + " archivos AVI - " + tamAVI + " Bytes = " + tamAVI/1000000000.0 + " GB");
//		System.out.println(contadorPEK + " archivos PEK - " + tamPEK + " Bytes = " + tamPEK/1000000000.0 + " GB");
//		System.out.println(contadorCFA + " archivos CFA - " + tamCFA + " Bytes = " + tamCFA/1000000000.0 + " GB");
//		System.out.println("Espacio total: " + tamTotal + " Bytes = " + tamTotal/1000000000.0 + " GB");
		
		txAreaLog.append("\n###### RESUMEN ARCHIVOS "+accion+" ######\n");
		txAreaLog.append(contadorDirectoriosPRV + " directorios temporales encontrados\n");
		txAreaLog.append(contadorAVI + " archivos AVI - " + tamAVI + " Bytes = " + tamAVI/1000000000.0 + " GB\n");
		txAreaLog.append(contadorPEK + " archivos PEK - " + tamPEK + " Bytes = " + tamPEK/1000000000.0 + " GB\n");
		txAreaLog.append(contadorCFA + " archivos CFA - " + Math.abs(tamCFA) + " Bytes = " + Math.abs(tamCFA/1000000000.0) + " GB\n\n");
		txAreaLog.append("Espacio total: " + tamTotal + " Bytes = " + tamTotal/1000000000.0 + " GB\n");
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTxAreaLog());
			scrollPane.add(txAreaLog);
			scrollPane.setViewportView(txAreaLog);
			
			
		}
		return scrollPane;
	}
	private JTextArea getTxAreaLog() {
		if (txAreaLog == null) {
			txAreaLog = new JTextArea();
			txAreaLog.setEditable(false);
		}
		return txAreaLog;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnAplicacin());
			menuBar.add(getMnOpciones());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnAplicacin() {
		if (mnAplicacin == null) {
			mnAplicacin = new JMenu("Aplicaci\u00F3n");
			mnAplicacin.add(getMntmSeleccionarDirectorio());
			mnAplicacin.add(getMntmEjecutar());
			mnAplicacin.add(getMntmReiniciar());
			mnAplicacin.add(getSeparator());
			mnAplicacin.add(getMntmSalir());
		}
		return mnAplicacin;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmSeleccionarDirectorio() {
		if (mntmSeleccionarDirectorio == null) {
			mntmSeleccionarDirectorio = new JMenuItem("Seleccionar directorio...");
			mntmSeleccionarDirectorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					seleccionarDirectorio();
				}
			});
		}
		return mntmSeleccionarDirectorio;
	}
	private JMenuItem getMntmEjecutar() {
		if (mntmEjecutar == null) {
			mntmEjecutar = new JMenuItem("Ejecutar");
			mntmEjecutar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					MyThread hiloEjecucion = new MyThread();
					hiloEjecucion.start();
					//escanear();
					resetData();
				}
			});
		}
		return mntmEjecutar;
	}
	
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmSalir;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(vp, "Premiere Pro Cleaner v1.0\nSoftware desarrollado por Miguel Martínez Serrano 2017\nwww.miguelms.es","Información del programa",JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAcercaDe;
	}
	private JMenu getMnOpciones() {
		if (mnOpciones == null) {
			mnOpciones = new JMenu("Opciones");
			mnOpciones.add(getChckbxmntmMostrarRutaCompleta());
		}
		return mnOpciones;
	}
	private JCheckBoxMenuItem getChckbxmntmMostrarRutaCompleta() {
		if (chckbxmntmMostrarRutaCompleta == null) {
			chckbxmntmMostrarRutaCompleta = new JCheckBoxMenuItem("Mostrar ruta completa");
			chckbxmntmMostrarRutaCompleta.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if(txAreaLog.getText().toString().length()>0 && !listaArchivos.isEmpty()){
						txAreaLog.setText("");
						MyThread hiloEjecucion = new MyThread();
						hiloEjecucion.start();
	//					escanear();
						resetData();
					}
						
				}
			});
			chckbxmntmMostrarRutaCompleta.setToolTipText("Mostrar la ruta completa de archivos");
		}
		return chckbxmntmMostrarRutaCompleta;
	}
	private JButton getBtnSoloEscanear() {
		if (btnSoloEscanear == null) {
			btnSoloEscanear = new JButton("Solo escanear");
			btnSoloEscanear.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnSoloEscanear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					MyThread myThread = new MyThread();
					myThread.start();
					//escanear();
					resetData();
				}
			});
		}
		return btnSoloEscanear;
	}
	private JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setMaximum(1000);
			progressBar.setForeground(new Color(30, 144, 255));
			progressBar.setStringPainted(true);
		}
		return progressBar;
	}

	private void resetData(){
		listaArchivos = new ArrayList<File>();
		listaNOEliminados = new ArrayList<File>();
		listaDirectoriosPRV = new ArrayList<File>();
		
		contadorCFA = 0;
		contadorPEK = 0;
		contadorAVI = 0;
		contadorDirectoriosPRV = 0;
		contadorNoEliminados = 0;
		tamCFA = 0;	
		tamPEK = 0;
		tamAVI = 0;		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{115, 378, 0};
			gbl_panel.rowHeights = new int[]{26, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel_3.insets = new Insets(0, 0, 0, 5);
			gbc_panel_3.gridx = 0;
			gbc_panel_3.gridy = 0;
			panel.add(getPanel_3(), gbc_panel_3);
			GridBagConstraints gbc_progressBar = new GridBagConstraints();
			gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
			gbc_progressBar.gridx = 1;
			gbc_progressBar.gridy = 0;
			panel.add(getProgressBar(), gbc_progressBar);
		}
		return panel;
	}
	private JLabel getLbTiempoEjecucion() {
		if (lbTiempoEjecucion == null) {
			lbTiempoEjecucion = new JLabel("");
		}
		return lbTiempoEjecucion;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getLbTiempoEjecucion());
		}
		return panel_3;
	}
	private JMenuItem getMntmReiniciar() {
		if (mntmReiniciar == null) {
			mntmReiniciar = new JMenuItem("Reiniciar");
			mntmReiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					resetData();
					progressBar.setValue(0);
					lbTiempoEjecucion.setText("");
					txPathSeleccionado.setText(DEFAULT_PATH);
				}
			});
		}
		return mntmReiniciar;
	}
	
	public class MyThread extends Thread {

	    public void run(){
	       //System.out.println("Hilo ejecutandose");
	       escanear();
	    }
	  }
	private JLabel getLbIcono() {
		if (lbIcono == null) {
			lbIcono = new JLabel("");
			lbIcono.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo_small.png")));
		}
		return lbIcono;
	}
}
