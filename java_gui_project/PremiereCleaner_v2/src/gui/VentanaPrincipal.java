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
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import static java.nio.file.StandardCopyOption.*;


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
	private ArrayList<File> listaMediosNoUtilizados = new ArrayList<File>();
	private DefaultListModel<File> modeloListaUtilizados = new DefaultListModel<File>();
	private DefaultListModel<File> modeloListaNoUtilizados = new DefaultListModel<File>();
	
	private int contadorUtilizados = 0;
	private int contadorNoUtilizados = 0;
	
	
	private JScrollPane scrollPane;
	private JTextArea txAreaLog;
	private int contadorCFA = 0;
	private int contadorPEK = 0;
	private int contadorAVI = 0;
	private int contadorDirectoriosPRV = 0;
	private int contadorNoEliminados = 0;
	private long tamCFA = 0;	// contador para calcular tamaños totales
	private long tamPEK = 0;
	private long tamAVI = 0;
	
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
	private Thread thread1 = null;
	
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
	private JButton btEscanearMedios;
	private JPanel pnManagerListas;
	private JScrollPane pnMediosUtilizados;
	private JScrollPane pnMediosNoUtilizados;
	private JList listaUtilizados;
	private JList listaNoUtilizados;
	private JPanel pnManagerBotones;
	private JPanel pnBotonesMediosNOUtilizados;
	private JLabel lblMediosUtilizados;
	private JLabel lblMediosNoUtilizados;
	private JButton btnLimpiarMedios;
	private JButton btMover;
	private JButton btnCopiar;
	private JButton btnBorrar;
	private JRadioButton rdbtnMediosUtilizados;
	private JRadioButton rdbtnMediosNoUtilizados;
	private JPanel panel_6;
	private JPanel pnTiempoGestor;
	private JLabel lblTiempoMs;
	private JProgressBar progressbarGestor;
	private JButton btnStop;
	
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
		setBounds(100, 100, 882, 700);
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
					resetData();
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
		long tamTotal = Math.abs(tamAVI) + Math.abs(tamPEK) +Math.abs(tamCFA);
		
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
					JOptionPane.showMessageDialog(vp, "Premiere Pro Cleaner v2.0\nSoftware desarrollado por Miguel Martínez Serrano 2017\nwww.miguelms.es\n\nLogotipo contiene imagen de Adobe e icono de escoba de Nikita Golubev.","Información del programa",JOptionPane.INFORMATION_MESSAGE);
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
		listaMediosNoUtilizados = new ArrayList<File>();
		listaMediosUtilizados = new ArrayList<File>();
		
		contadorNoUtilizados = 0;
		contadorUtilizados = 0;
		
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
		progressbarGestor.setValue(0);
		progressbarGestor.setString("0%");
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
			lbVersion = new JLabel("v2.0");
		}
		return lbVersion;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Gestor Proyectos", null, getPnManager(), "Para separar los medios utilizados en el proyecyo del resto de archivos");
			tabbedPane.addTab("Limpiador previsualizaciones", null, getPnCleaner(), null);
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
			btnSeleccionarProyecto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					File tempFile = new File(txPathProyecto.getText().toString());
					JFileChooser jf = null;
					if(tempFile.exists())
						jf = new JFileChooser(txPathProyecto.getText().toString());
					else
						jf = new JFileChooser(System.getProperty("user.home") + "/Desktop");
					jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
					jf.setFileFilter(new FileNameExtensionFilter("Projectos Adobe Premiere .prproj","prproj"));
					
					if(jf.showOpenDialog(vp) == JFileChooser.APPROVE_OPTION){
						txPathProyecto.setText(jf.getSelectedFile().getAbsolutePath());
						btEscanearMedios.grabFocus();
					}
					
				}
			});
			btnSeleccionarProyecto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return btnSeleccionarProyecto;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.add(getTxPathProyecto());
			panel_5.add(getBtEscanearMedios());
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
	private JButton getBtEscanearMedios() {
		if (btEscanearMedios == null) {
			btEscanearMedios = new JButton("Escanear medios");
			btEscanearMedios.setFont(new Font("Tahoma", Font.BOLD, 13));
			btEscanearMedios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					resetData();
					modeloListaNoUtilizados.clear();
					modeloListaUtilizados.clear();
					
					String txLogAntes = txAreaLog.getText();
					escanearProyecto();
					txAreaLog.setText(txLogAntes);
					
					if(listaMediosUtilizados.size()==0)
						JOptionPane.showMessageDialog(vp, "Se han encontrado 0 archivos en el proyecto.\nAbre el proyecto de premiere y comprueba que los medios están localizados","Atención: localiza los medios del proyecto",JOptionPane.WARNING_MESSAGE);

					actualizarBotones();
					
					resetProgress();
				}
			});
		}
		return btEscanearMedios;
	}
	private void actualizarBotones(){
		if(listaMediosUtilizados.size() == 0 || listaMediosNoUtilizados.size() == 0){
			btMover.setEnabled(false);
			btnCopiar.setEnabled(false);
			btnBorrar.setEnabled(false);
		}else{
			btMover.setEnabled(true);
			btnCopiar.setEnabled(true);
			btnBorrar.setEnabled(true);
		}
		if(listaMediosUtilizados.size()==0)
			rdbtnMediosUtilizados.setEnabled(false);
		else
			rdbtnMediosUtilizados.setEnabled(true);
		
		if(listaMediosNoUtilizados.size()==0)
			rdbtnMediosNoUtilizados.setEnabled(false);
		else
			rdbtnMediosNoUtilizados.setEnabled(true);
		btStop.setEnabled(false);
		btStop.setForeground(Color.LIGHT_GRAY);
	}
	
	private void escanearProyecto(){
		File file = new File(txPathProyecto.getText().toString()); // ruta absoluta al proyecto
		
		// PRIMERO escaneo los medios utilizados
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			NodeList lista = document.getElementsByTagName("ActualMediaFilePath");
			for(int i = 0; i<lista.getLength();i++){
				String ruta = lista.item(i).getTextContent();
				if(!ruta.startsWith("1")){
					if(ruta.startsWith("\\\\?\\")){
						ruta = ruta.substring(4);
					}
					File f = new File(ruta);
					if(f.exists()){
						contadorUtilizados +=1;
//						System.out.println(f.getAbsolutePath() + ", file: " + contadorUtilizados);
						modeloListaUtilizados.addElement(f);
						listaMediosUtilizados.add(f);
					}
				}
			}
			
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
		
		// SEGUNDO escaneo TODOS los archivos  menos los basura para no utilizarlos luego
		escanearArchivos(file.getParent());
		// en "listaArchivos" tengo toda la basura
		
		escanearMediosNoUtilizados(file.getParent());
		
		long sizeUtilizados = 0;
		for (File file2 : listaMediosUtilizados) {
			sizeUtilizados += Math.abs(file2.length());
		}
		long sizeNOUtilizados = 0;
		for (File file2 : listaMediosNoUtilizados) {
			sizeNOUtilizados += file2.length();
		}
		lblMediosUtilizados.setText(" Medios utilizados ("+listaMediosUtilizados.size()+" archivos = "+sizeUtilizados/1000000000.0+" GB):");
		lblMediosNoUtilizados.setText(" Medios NO utilizados ("+listaMediosNoUtilizados.size()+ " archivos = "+sizeNOUtilizados/1000000000.0+" GB):");
	}
	
	
	public void escanearMediosNoUtilizados(String directoryName) {
	    File directorio = new File(directoryName);
	    
	    if(((directoryName.toString().equals(txPathProyecto.getText().toString())) || 
	    		(!directorio.isHidden() && !directorio.getName().startsWith("$"))) &&
	    		!directorio.getName().toString().equals("tdata")){ // ignora telegram data para optimizar
	    	
		    File[] fList = directorio.listFiles();
		    
		    //progressBar.setString(directorio.getAbsolutePath());
		    for (int i=0; fList != null && i<fList.length;i++) {
		    	File file = fList[i];
		    	if(!file.isHidden()){
		    		String printName;
		        	if(chckbxmntmMostrarRutaCompleta.isSelected())
		        		printName = file.getAbsolutePath();
		        	else
		        		printName = file.getName();
			        if (file.isFile()) {
			        	if(!listaArchivos.contains(file) // que no este en la basura
			        			&& !listaMediosUtilizados.contains(file)){ // no está en los utilizados
			        		modeloListaNoUtilizados.addElement(file);
			        		listaMediosNoUtilizados.add(file);
			        	}
			        	
			        }else if (file.isDirectory()) {
			        	// simulación imperfecta de progreso
//			        	if(progressBar.getValue() < progressBar.getMaximum()-1){
//			        		progressBar.setValue(progressBar.getValue()+1);
//			        		progressBar.repaint();
//			        	}
			        	if(!printName.endsWith(".PRV"))
			        		escanearMediosNoUtilizados(file.getAbsolutePath());
			        }
		        }
		    } // fin for
	    }	// fin if recycler
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
			listaNoUtilizados.setModel(modeloListaNoUtilizados);
			listaNoUtilizados.setBorder(new LineBorder(new Color(192, 192, 192)));
		}
		return listaNoUtilizados;
	}
	private JPanel getPnManagerBotones() {
		if (pnManagerBotones == null) {
			pnManagerBotones = new JPanel();
			pnManagerBotones.setLayout(new BorderLayout(0, 0));
			pnManagerBotones.add(getPnBotonesMediosNOUtilizados());
			pnManagerBotones.add(getPanel_6(), BorderLayout.SOUTH);
		}
		return pnManagerBotones;
	}
	private JPanel getPnBotonesMediosNOUtilizados() {
		if (pnBotonesMediosNOUtilizados == null) {
			pnBotonesMediosNOUtilizados = new JPanel();
			pnBotonesMediosNOUtilizados.add(getRdbtnMediosUtilizados());
			pnBotonesMediosNOUtilizados.add(getRdbtnMediosNoUtilizados());
			ButtonGroup bg = new ButtonGroup();
			bg.add(rdbtnMediosNoUtilizados);
			bg.add(rdbtnMediosUtilizados);
			pnBotonesMediosNOUtilizados.add(getBtMover());
			pnBotonesMediosNOUtilizados.add(getBtnCopiar());
			pnBotonesMediosNOUtilizados.add(getBtnBorrar());
			pnBotonesMediosNOUtilizados.add(getBtnStop());
		}
		return pnBotonesMediosNOUtilizados;
	}
	private JLabel getLblMediosUtilizados() {
		if (lblMediosUtilizados == null) {
			lblMediosUtilizados = new JLabel(" Medios utilizados:");
			lblMediosUtilizados.setForeground(new Color(0, 128, 0));
			lblMediosUtilizados.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return lblMediosUtilizados;
	}
	private JLabel getLblMediosNoUtilizados() {
		if (lblMediosNoUtilizados == null) {
			lblMediosNoUtilizados = new JLabel(" Medios NO utilizados:");
			lblMediosNoUtilizados.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblMediosNoUtilizados.setForeground(new Color(220, 20, 60));
		}
		return lblMediosNoUtilizados;
	}
	private JButton getBtnLimpiarMedios() {
		if (btnLimpiarMedios == null) {
			btnLimpiarMedios = new JButton("Limpiar listas");
			btnLimpiarMedios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					modeloListaNoUtilizados.clear();
					modeloListaUtilizados.clear();
					listaMediosNoUtilizados.clear();
					listaMediosUtilizados.clear();
					resetData();
					
					lblMediosUtilizados.setText(" Medios utilizados:");
					lblMediosNoUtilizados.setText(" Medios NO utilizados:");
					actualizarBotones();
				}
			});
			btnLimpiarMedios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnLimpiarMedios;
	}
	private JButton getBtMover() {
		if (btMover == null) {
			btMover = new JButton("Mover");
			btMover.setEnabled(false);
			btMover.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return btMover;
	}
	private JButton getBtnCopiar() {
		if (btnCopiar == null) {
			btnCopiar = new JButton("Copiar");
			btnCopiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					File origen = new File(txPathProyecto.getText());
					JFileChooser jf = new JFileChooser(origen.getParent());
					jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if(jf.showOpenDialog(vp) == jf.APPROVE_OPTION){
						thread1 = new Thread(){
							public void run() {
								long t1 = System.currentTimeMillis();
								progressbarGestor.setMaximum(listaMediosNoUtilizados.size());
		
								// primero clono estructura de carpetas sin ficheros
								clonarCarpetas(origen.getParentFile().getAbsolutePath(), jf.getSelectedFile().getAbsolutePath());
								
								// copio antiguos en nuevos
								ArrayList<File> lista = null;
								if(rdbtnMediosNoUtilizados.isSelected())
									lista = listaMediosNoUtilizados;
								else
									lista = listaMediosUtilizados;
								
								for (File file : lista) {
									if(!file.getAbsolutePath().equals(txPathProyecto.getText())){
										progressbarGestor.setValue(progressbarGestor.getValue()+1);
										progressbarGestor.setString("Copiando... " + file.getAbsolutePath());
										progressbarGestor.repaint();
										String rutaPadreActual = file.getAbsolutePath();
										String nuevaRuta = rutaPadreActual.replace(origen.getParentFile().getAbsolutePath(), jf.getSelectedFile().getAbsolutePath());
										Path pathorigen = Paths.get(rutaPadreActual);
										Path pathdestino = Paths.get(nuevaRuta);
//										System.out.println("origen: " + pathorigen.toString());
//										System.err.println("destino: " + pathdestino.toString());
										
										try {
											Files.copy(pathorigen, pathdestino, REPLACE_EXISTING);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
								progressbarGestor.setString("100%");
								progressbarGestor.setValue(progressbarGestor.getMaximum());
								long t2 =  System.currentTimeMillis();
								lblTiempoMs.setText("Tiempo: "+(t2-t1)+" ms");
							};
							
						};
						btnStop.setEnabled(true);
						btnStop.setForeground(Color.RED);
						thread1.start();
					}
					
				}
			});
			btnCopiar.setEnabled(false);
			btnCopiar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return btnCopiar;
	}
	private JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Borrar");
			btnBorrar.setEnabled(false);
			btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return btnBorrar;
	}
	private JRadioButton getRdbtnMediosUtilizados() {
		if (rdbtnMediosUtilizados == null) {
			rdbtnMediosUtilizados = new JRadioButton("Medios utilizados");
			rdbtnMediosUtilizados.setEnabled(false);
			rdbtnMediosUtilizados.setFont(new Font("Tahoma", Font.PLAIN, 13));
			rdbtnMediosUtilizados.setForeground(new Color(0, 128, 0));
		}
		return rdbtnMediosUtilizados;
	}
	private JRadioButton getRdbtnMediosNoUtilizados() {
		if (rdbtnMediosNoUtilizados == null) {
			rdbtnMediosNoUtilizados = new JRadioButton("Medios NO utilizados");
			rdbtnMediosNoUtilizados.setEnabled(false);
			rdbtnMediosNoUtilizados.setFont(new Font("Tahoma", Font.PLAIN, 13));
			rdbtnMediosNoUtilizados.setSelected(true);
			rdbtnMediosNoUtilizados.setForeground(new Color(220, 20, 60));
		}
		return rdbtnMediosNoUtilizados;
	}
	
	/**
	 * Función para clonar SOLO las carpetas, sin el contenido
	 * @param pathOrigen
	 * @param pathDestino
	 */
	public static void clonarCarpetas(String pathOrigen, String pathDestino) {
        File targetFile = new File(pathDestino);
        if (!targetFile.exists()) {
            targetFile.mkdir();
        }
        for (File f : new File(pathOrigen).listFiles()) {
        	if (f.isDirectory()) {
                String append = "/" + f.getName();
//                System.out.println("Creating '" + pathDestino + append + "': " + new File(pathDestino + append).mkdir());
                clonarCarpetas(pathOrigen + append, pathDestino + append);
            }
        }
    }   
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			GridBagLayout gbl_panel_6 = new GridBagLayout();
			gbl_panel_6.columnWidths = new int[]{114, 146, 0};
			gbl_panel_6.rowHeights = new int[]{24, 0};
			gbl_panel_6.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel_6.setLayout(gbl_panel_6);
			GridBagConstraints gbc_pnTiempoGestor = new GridBagConstraints();
			gbc_pnTiempoGestor.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnTiempoGestor.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnTiempoGestor.insets = new Insets(0, 0, 0, 5);
			gbc_pnTiempoGestor.gridx = 0;
			gbc_pnTiempoGestor.gridy = 0;
			panel_6.add(getPnTiempoGestor(), gbc_pnTiempoGestor);
			GridBagConstraints gbc_progressbarGestor = new GridBagConstraints();
			gbc_progressbarGestor.fill = GridBagConstraints.HORIZONTAL;
			gbc_progressbarGestor.anchor = GridBagConstraints.WEST;
			gbc_progressbarGestor.gridx = 1;
			gbc_progressbarGestor.gridy = 0;
			panel_6.add(getProgressbarGestor(), gbc_progressbarGestor);
		}
		return panel_6;
	}
	private JPanel getPnTiempoGestor() {
		if (pnTiempoGestor == null) {
			pnTiempoGestor = new JPanel();
			pnTiempoGestor.add(getLblTiempoMs());
		}
		return pnTiempoGestor;
	}
	private JLabel getLblTiempoMs() {
		if (lblTiempoMs == null) {
			lblTiempoMs = new JLabel("");
		}
		return lblTiempoMs;
	}
	private JProgressBar getProgressbarGestor() {
		if (progressbarGestor == null) {
			progressbarGestor = new JProgressBar();
			progressbarGestor.setStringPainted(true);
		}
		return progressbarGestor;
	}
	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("Stop");
			btnStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					thread1.stop();
					btnStop.setEnabled(false);
					btnStop.setForeground(Color.LIGHT_GRAY);
					resetProgress();
				}
			});
			btnStop.setEnabled(false);
			btnStop.setForeground(Color.LIGHT_GRAY);
		}
		return btnStop;
	}
}
