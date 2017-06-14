/**
 * (c) 2017 Miguel Martínez Serrano. www.miguelms.es
 * @Version: 2.0
 * 
 * */
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;
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
import javax.swing.JScrollBar;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

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
	//private final String DEFAULT_PATH = "C:\\Users\\";
	
	VentanaPrincipal vp;
	private ArrayList<File> listaArchivos = new ArrayList<File>();
	private ArrayList<File> listaNOEliminados = new ArrayList<File>();
	private ArrayList<File> listaDirectoriosPRV = new ArrayList<File>();
	
	private ArrayList<File> listaMediosUtilizados = new ArrayList<File>();
	private DefaultListModel<File> modeloListaUtilizados = new DefaultListModel<File>();
	
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
	
	private MyThread hiloEjecucion = null;
	private Thread hiloEliminacion = null;
	
	private JButton btStop;
	private JMenuItem mntmEcanearYLimpiar;
	private JLabel lbVersion;
	private JTabbedPane tabbedPane;
	private JPanel pnCleaner;
	private JPanel pnManager;
	private JPanel pnManagerTop;
	private JPanel panel_4;
	private JLabel lblSeleccionarProyectoDe;
	private JButton btnSeleccionarProyecto;
	private JPanel panel_5;
	private JTextField txPathProyecto;
	private JButton btnBuscarMediosUtilizados;
	private JPanel pnManagerListas;
	private JScrollPane pnMediosUtilizados;
	private JScrollPane pnMediosNoUtilizados;
	private JList listaUtilizados;
	private JList listaNoUtilizados;
	private JPanel pnManagerBotones;
	private JPanel pnBotonesMediosUtilizados;
	private JPanel pnBotonesMediosNOUtilizados;
	private JButton btnExportarMedios;
	private JLabel lblMediosUtilizados;
	private JLabel lblMediosNoUtilizados;
	private JButton btnLimpiarMedios;
	private JButton btnExportarMedios_1;
	
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
		setBounds(100, 100, 713, 648);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelTop(), BorderLayout.NORTH);
		contentPane.add(getTabbedPane(), BorderLayout.CENTER);
		
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
			panelTop.add(getLbVersion());
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
			btSeleccionar.setToolTipText("Seleccionar el directorio a escanear");
			btSeleccionar.setMnemonic('s');
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
			panel_2.add(getBtStop());
		}
		return panel_2;
	}
	private JTextField getTxPathSeleccionado() {
		if (txPathSeleccionado == null) {
			txPathSeleccionado = new JTextField();
			txPathSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txPathSeleccionado.setText(System.getProperty("user.home") + "\\Desktop");
			txPathSeleccionado.setColumns(25);
		}
		return txPathSeleccionado;
	}
	private JButton getBtEjecutar() {
		if (btEjecutar == null) {
			btEjecutar = new JButton("Escanear y limpiar");
			btEjecutar.setToolTipText("Escanear archivos temporales y eliminarlos");
			btEjecutar.setMnemonic('l');
			btEjecutar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//escanear();
					hiloEliminacion = new Thread(){
						public void run() {
							escanear();
							eliminarArchivos();
						};
					};
					hiloEliminacion.start();
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
	    if(((directoryName.toString().equals(txPathSeleccionado.getText().toString())) || 
	    		(!directorio.isHidden() && !directorio.getName().startsWith("$"))) &&
	    		!directorio.getName().toString().equals("tdata")){ // ignora telegram data para optimizar
	    	
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
			        	if(printName.endsWith(".cfa") || printName.endsWith(".CFA")){
			        		contadorCFA += 1;
			        		tamCFA += file.length();
			        	}else if(printName.endsWith(".pek") || printName.endsWith(".PEK")){
			        		contadorPEK += 1;
			        		tamPEK += file.length();
			        	}else if(printName.startsWith("Rendered - ") && (printName.endsWith(".AVI") || printName.endsWith(".avi"))){
			        		contadorAVI += 1;
			        		tamAVI += file.length();
			        	}
			        	if(printName.endsWith(".cfa")|| printName.endsWith(".CFA") 
			        			|| printName.endsWith(".pek") || printName.endsWith(".PEK")
			        			|| (printName.startsWith("Rendered - ") && (printName.endsWith(".AVI") || printName.endsWith(".avi")))){
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
		int contadorDirectoriosEliminados = 0;
		for (File file : listaArchivos) {
			if(!file.delete()){
				listaNOEliminados.add(file);
				contadorNoEliminados += 1;
			}else
				contadorEliminados += 1;
		}
		for (File file : listaDirectoriosPRV) {
			if(file.delete())
				contadorDirectoriosEliminados += 1;
		}
		if(contadorEliminados > 0){
			JOptionPane.showMessageDialog(vp, "Se han eliminado " + contadorEliminados + " archivos y " + contadorDirectoriosEliminados + " directorios", "Limpieza finalizada", JOptionPane.INFORMATION_MESSAGE);
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
		File tempFile = new File(txPathSeleccionado.getText().toString());
		JFileChooser jf = null;
		if(tempFile.exists())
			jf = new JFileChooser(txPathSeleccionado.getText().toString());
		else
			jf = new JFileChooser(System.getProperty("user.home") + "/Desktop");
		jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		if(jf.showOpenDialog(vp) == JFileChooser.APPROVE_OPTION){
			txPathSeleccionado.setText(jf.getSelectedFile().getAbsolutePath());
			btEjecutar.setFont(new Font("Tahoma", Font.BOLD, 12));
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
			btStop.setEnabled(true);
			btStop.setForeground(Color.RED);
			long t1 = System.currentTimeMillis();
			escanearArchivos(txPathSeleccionado.getText());
			long t2 = System.currentTimeMillis();
			progressBar.setValue(progressBar.getMaximum());
			progressBar.setString("100%");
			lbTiempoEjecucion.setText("Tiempo: "+ (t2-t1) + " ms");
			printStats("ENCONTRADOS");
			btStop.setEnabled(false);
			btStop.setForeground(Color.GRAY);

			JScrollBar vertical = scrollPane.getVerticalScrollBar();
			vertical.setValue(vertical.getMaximum()+1000); // scroll down cuando ejecuta para ver resultados
			
			
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
			scrollPane.setBorder(null);
			scrollPane.setViewportView(getTxAreaLog());
			scrollPane.add(txAreaLog);
			scrollPane.setViewportView(txAreaLog);
			
			
		}
		return scrollPane;
	}
	private JTextArea getTxAreaLog() {
		if (txAreaLog == null) {
			txAreaLog = new JTextArea();
			txAreaLog.setBorder(null);
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
			mnAplicacin.setMnemonic('a');
			mnAplicacin.add(getMntmSeleccionarDirectorio());
			mnAplicacin.add(getMntmEjecutar());
			mnAplicacin.add(getMntmEcanearYLimpiar());
			mnAplicacin.add(getMntmReiniciar());
			mnAplicacin.add(getSeparator());
			mnAplicacin.add(getMntmSalir());
		}
		return mnAplicacin;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('y');
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmSeleccionarDirectorio() {
		if (mntmSeleccionarDirectorio == null) {
			mntmSeleccionarDirectorio = new JMenuItem("Seleccionar directorio...");
			mntmSeleccionarDirectorio.setMnemonic('s');
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
			mntmEjecutar = new JMenuItem("Solo escanear");
			mntmEjecutar.setMnemonic('e');
			mntmEjecutar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
			mntmEjecutar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					hiloEjecucion = new MyThread();
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
					JOptionPane.showMessageDialog(vp, "Premiere Pro Cleaner v1.0\nSoftware desarrollado por Miguel Martínez Serrano 2017\nwww.miguelms.es\n\nLogotipo contiene imagen de Adobe e icono de escoba de Nikita Golubev.","Información del programa",JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAcercaDe;
	}
	private JMenu getMnOpciones() {
		if (mnOpciones == null) {
			mnOpciones = new JMenu("Opciones");
			mnOpciones.setMnemonic('o');
			mnOpciones.add(getChckbxmntmMostrarRutaCompleta());
		}
		return mnOpciones;
	}
	private JCheckBoxMenuItem getChckbxmntmMostrarRutaCompleta() {
		if (chckbxmntmMostrarRutaCompleta == null) {
			chckbxmntmMostrarRutaCompleta = new JCheckBoxMenuItem("Mostrar ruta completa");
			chckbxmntmMostrarRutaCompleta.setMnemonic('m');
			chckbxmntmMostrarRutaCompleta.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if(txAreaLog.getText().toString().length()>0 && !listaArchivos.isEmpty()){
						txAreaLog.setText("");
						hiloEjecucion = new MyThread();
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
			btnSoloEscanear.setToolTipText("Escanear archivos");
			btnSoloEscanear.setMnemonic('e');
			btnSoloEscanear.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnSoloEscanear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					hiloEjecucion = new MyThread();
					hiloEjecucion.start();
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
	
	private void resetProgress(){
		progressBar.setValue(0);
		progressBar.setString("0%");
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
			mntmReiniciar.setMnemonic('r');
			mntmReiniciar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
			mntmReiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(btStop.isEnabled())
						btStop.doClick();
					resetData();
					progressBar.setValue(0);
					lbTiempoEjecucion.setText("");
					//txPathSeleccionado.setText(DEFAULT_PATH);
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
	private JButton getBtStop() {
		if (btStop == null) {
			btStop = new JButton("Stop");
			btStop.setMnemonic('t');
			btStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(hiloEjecucion != null)
						hiloEjecucion.stop();
					if(hiloEliminacion != null)
						hiloEliminacion.stop();
					resetData();
					resetProgress();
					btStop.setEnabled(false);
					btStop.setForeground(Color.GRAY);
					btSeleccionar.grabFocus();
				}
			});
			btStop.setEnabled(false);
			btStop.setForeground(Color.GRAY);
			btStop.setToolTipText("Parar esc\u00E1ner");
			btStop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return btStop;
	}
	private JMenuItem getMntmEcanearYLimpiar() {
		if (mntmEcanearYLimpiar == null) {
			mntmEcanearYLimpiar = new JMenuItem("Ecanear y limpiar");
			mntmEcanearYLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btEjecutar.doClick();
				}
			});
			mntmEcanearYLimpiar.setMnemonic('l');
			mntmEcanearYLimpiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		}
		return mntmEcanearYLimpiar;
	}
	private JLabel getLbVersion() {
		if (lbVersion == null) {
			lbVersion = new JLabel("v1.0");
		}
		return lbVersion;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Gestor Proyectos", null, getPnManager(), "Para separar los medios utilizados en el proyecyo del resto de archivos");
			tabbedPane.addTab("Limpiador Proyectos", null, getPnCleaner(), null);
			tabbedPane.setBackgroundAt(1, Color.LIGHT_GRAY);
		}
		return tabbedPane;
	}
	private JPanel getPnCleaner() {
		if (pnCleaner == null) {
			pnCleaner = new JPanel();
			pnCleaner.setLayout(new BorderLayout(0, 0));
			pnCleaner.add(getPanelCentro(), BorderLayout.CENTER);
			pnCleaner.add(getPanel(), BorderLayout.SOUTH);
		}
		return pnCleaner;
	}
	private JPanel getPnManager() {
		if (pnManager == null) {
			pnManager = new JPanel();
			pnManager.setLayout(new BorderLayout(0, 0));
			pnManager.add(getPnManagerTop(), BorderLayout.NORTH);
			pnManager.add(getPnManagerListas(), BorderLayout.CENTER);
			pnManager.add(getPnManagerBotones(), BorderLayout.SOUTH);
		}
		return pnManager;
	}
	private JPanel getPnManagerTop() {
		if (pnManagerTop == null) {
			pnManagerTop = new JPanel();
			pnManagerTop.setLayout(new BorderLayout(0, 0));
			pnManagerTop.add(getPanel_4());
			pnManagerTop.add(getPanel_5(), BorderLayout.SOUTH);
		}
		return pnManagerTop;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.add(getLblSeleccionarProyectoDe());
			panel_4.add(getBtnSeleccionarProyecto());
		}
		return panel_4;
	}
	private JLabel getLblSeleccionarProyectoDe() {
		if (lblSeleccionarProyectoDe == null) {
			lblSeleccionarProyectoDe = new JLabel("Seleccionar proyecto de premiere (.prproj):");
			lblSeleccionarProyectoDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lblSeleccionarProyectoDe;
	}
	private JButton getBtnSeleccionarProyecto() {
		if (btnSeleccionarProyecto == null) {
			btnSeleccionarProyecto = new JButton("Seleccionar proyecto");
			btnSeleccionarProyecto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return btnSeleccionarProyecto;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.add(getTxPathProyecto());
			panel_5.add(getBtnBuscarMediosUtilizados());
			panel_5.add(getBtnLimpiarMedios());
		}
		return panel_5;
	}
	private JTextField getTxPathProyecto() {
		if (txPathProyecto == null) {
			txPathProyecto = new JTextField();
			txPathProyecto.setText("D:\\PROYECTO 6 - LOS TALENTOS\\Proyecto 6 - Capitulo Los Talentos.prproj");
			txPathProyecto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			//txPathProyecto.setText("D:\\Documentos\\Investigaci\u00F3n\\PremiereCleaner\\Proyecto pruebas\\proyecto multimedia de pruebas.prproj");
			txPathProyecto.setColumns(25);
		}
		return txPathProyecto;
	}
	private JButton getBtnBuscarMediosUtilizados() {
		if (btnBuscarMediosUtilizados == null) {
			btnBuscarMediosUtilizados = new JButton("Escanear medios");
			btnBuscarMediosUtilizados.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnBuscarMediosUtilizados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					escanearProyecto();
				}
			});
		}
		return btnBuscarMediosUtilizados;
	}
	
	private void escanearProyecto(){
		File file = new File(txPathProyecto.getText().toString()); // ruta absoluta al proyecto
		
		// primero escaneo archivos basura para no utilizarlos luego
		
		//escanearArchivos(file.getParent());
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			NodeList lista = document.getElementsByTagName("ActualMediaFilePath");
			int counterFiles = 0;
			for(int i = 0; i<lista.getLength();i++){
				String ruta = lista.item(i).getTextContent();
				if(!ruta.startsWith("1")){
					if(ruta.startsWith("\\\\?\\")){
						ruta = ruta.substring(4);
					}
					File f = new File(ruta);
					if(f.exists()){
						counterFiles +=1;
						System.out.println(f.getAbsolutePath() + ", file: " + counterFiles);
						modeloListaUtilizados.addElement(f);
						listaMediosUtilizados.add(f);
					}
				}
			}
			lblMediosUtilizados.setText(" Medios utilizados ("+counterFiles+" archivos):");
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.err.println(file.getParent());
		
	}
	
	private JPanel getPnManagerListas() {
		if (pnManagerListas == null) {
			pnManagerListas = new JPanel();
			pnManagerListas.setLayout(new GridLayout(1, 0, 0, 0));
			pnManagerListas.add(getPnMediosUtilizados());
			pnManagerListas.add(getPnMediosNoUtilizados());
		}
		return pnManagerListas;
	}
	private JScrollPane getPnMediosUtilizados() {
		if (pnMediosUtilizados == null) {
			pnMediosUtilizados = new JScrollPane();
			pnMediosUtilizados.setBorder(null);
			pnMediosUtilizados.setViewportView(listaUtilizados);
			pnMediosUtilizados.setViewportView(getListaUtilizados());
			pnMediosUtilizados.setColumnHeaderView(getLblMediosUtilizados());
		}
		return pnMediosUtilizados;
	}
	private JScrollPane getPnMediosNoUtilizados() {
		if (pnMediosNoUtilizados == null) {
			pnMediosNoUtilizados = new JScrollPane();
			pnMediosNoUtilizados.setBorder(null);
			pnMediosNoUtilizados.setViewportView(getListaNoUtilizados());
			pnMediosNoUtilizados.setColumnHeaderView(getLblMediosNoUtilizados());
		}
		return pnMediosNoUtilizados;
	}
	private JList getListaUtilizados() {
		if (listaUtilizados == null) {
			listaUtilizados = new JList();
			
			listaUtilizados.setModel(modeloListaUtilizados);
			listaUtilizados.setBorder(new LineBorder(new Color(192, 192, 192)));
		}
		return listaUtilizados;
	}
	private JList getListaNoUtilizados() {
		if (listaNoUtilizados == null) {
			listaNoUtilizados = new JList();
			listaNoUtilizados.setBorder(new LineBorder(new Color(192, 192, 192)));
		}
		return listaNoUtilizados;
	}
	private JPanel getPnManagerBotones() {
		if (pnManagerBotones == null) {
			pnManagerBotones = new JPanel();
			pnManagerBotones.setLayout(new GridLayout(1, 0, 0, 0));
			pnManagerBotones.add(getPnBotonesMediosUtilizados());
			pnManagerBotones.add(getPnBotonesMediosNOUtilizados());
		}
		return pnManagerBotones;
	}
	private JPanel getPnBotonesMediosUtilizados() {
		if (pnBotonesMediosUtilizados == null) {
			pnBotonesMediosUtilizados = new JPanel();
			pnBotonesMediosUtilizados.add(getBtnExportarMedios());
		}
		return pnBotonesMediosUtilizados;
	}
	private JPanel getPnBotonesMediosNOUtilizados() {
		if (pnBotonesMediosNOUtilizados == null) {
			pnBotonesMediosNOUtilizados = new JPanel();
			pnBotonesMediosNOUtilizados.add(getBtnExportarMedios_1());
		}
		return pnBotonesMediosNOUtilizados;
	}
	private JButton getBtnExportarMedios() {
		if (btnExportarMedios == null) {
			btnExportarMedios = new JButton("Exportar medios");
		}
		return btnExportarMedios;
	}
	private JLabel getLblMediosUtilizados() {
		if (lblMediosUtilizados == null) {
			lblMediosUtilizados = new JLabel(" Medios utilizados:");
			lblMediosUtilizados.setForeground(new Color(0, 128, 0));
			lblMediosUtilizados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lblMediosUtilizados;
	}
	private JLabel getLblMediosNoUtilizados() {
		if (lblMediosNoUtilizados == null) {
			lblMediosNoUtilizados = new JLabel(" Medios NO utilizados:");
			lblMediosNoUtilizados.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblMediosNoUtilizados.setForeground(new Color(220, 20, 60));
		}
		return lblMediosNoUtilizados;
	}
	private JButton getBtnLimpiarMedios() {
		if (btnLimpiarMedios == null) {
			btnLimpiarMedios = new JButton("Limpiar listas");
			btnLimpiarMedios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnLimpiarMedios;
	}
	private JButton getBtnExportarMedios_1() {
		if (btnExportarMedios_1 == null) {
			btnExportarMedios_1 = new JButton("Exportar medios no utilizados");
		}
		return btnExportarMedios_1;
	}
}
