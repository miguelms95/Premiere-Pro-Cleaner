package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCentro;
	private JPanel panelTop;
	private JLabel lblAdobePremierePro;
	private JPanel panel;
	private JPanel panelCentroTop;
	private JButton btSeleccionar;
	private JLabel lblSeleccionarDirectorioPara;
	private JTextArea txAreaLog;
	private JPanel panel_1;
	private JPanel panel_2;
	private JTextField txPathSeleccionado;
	private JButton btLimpiar;

	private ArrayList<File> listaArchivos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					VentanaPrincipal frame = new VentanaPrincipal();
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
		setTitle("Adobe Premiere Pro Cleaner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
		contentPane.add(getPanelTop(), BorderLayout.NORTH);
	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new BorderLayout(0, 0));
			panelCentro.add(getPanel(), BorderLayout.CENTER);
			panelCentro.add(getPanelCentroTop(), BorderLayout.NORTH);
		}
		return panelCentro;
	}
	private JPanel getPanelTop() {
		if (panelTop == null) {
			panelTop = new JPanel();
			panelTop.add(getLblAdobePremierePro());
		}
		return panelTop;
	}
	private JLabel getLblAdobePremierePro() {
		if (lblAdobePremierePro == null) {
			lblAdobePremierePro = new JLabel("Adobe Premiere Pro Cleaner");
			lblAdobePremierePro.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return lblAdobePremierePro;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.add(getTxAreaLog());
		}
		return panel;
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
			btSeleccionar = new JButton("Seleccionar directorio ");
		}
		return btSeleccionar;
	}
	private JLabel getLblSeleccionarDirectorioPara() {
		if (lblSeleccionarDirectorioPara == null) {
			lblSeleccionarDirectorioPara = new JLabel("Seleccionar directorio para escanear y limpiar:");
		}
		return lblSeleccionarDirectorioPara;
	}
	private JTextArea getTxAreaLog() {
		if (txAreaLog == null) {
			txAreaLog = new JTextArea();
		}
		return txAreaLog;
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
			panel_2.add(getBtLimpiar());
		}
		return panel_2;
	}
	private JTextField getTxPathSeleccionado() {
		if (txPathSeleccionado == null) {
			txPathSeleccionado = new JTextField();
			txPathSeleccionado.setText("D:\\Test\\");
			txPathSeleccionado.setEditable(false);
			txPathSeleccionado.setColumns(40);
		}
		return txPathSeleccionado;
	}
	private JButton getBtLimpiar() {
		if (btLimpiar == null) {
			btLimpiar = new JButton("Ejecutar");
			btLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					escanearArchivos(txPathSeleccionado.getText(), listaArchivos);
					
				}
			});
			btLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
			btLimpiar.setForeground(Color.BLACK);
		}
		return btLimpiar;
	}
	
	public void escanearArchivos(String directoryName, ArrayList<File> files) {
	    File directorio = new File(directoryName);

	    File[] fList = directorio.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            escanearArchivos(file.getAbsolutePath(), files);
	        }
	    }
	}
	
	private void rellenaListaArchivos(){
		String cadena = "";
		for (File file : listaArchivos) {
			cadena += file.getAbsolutePath();
		}
		txAreaLog.setText(cadena);
	}
}
