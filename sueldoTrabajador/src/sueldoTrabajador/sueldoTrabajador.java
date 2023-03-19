package sueldoTrabajador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.awt.event.ActionEvent;

public class sueldoTrabajador {

	private JFrame frame;
	private JTextField inputMinimunWage;
	private JTextField inputTotalSalesAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sueldoTrabajador window = new sueldoTrabajador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sueldoTrabajador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
		frame.setBounds(100, 100, 480, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Sueldo Trabajador");
		lblTitle.setForeground(new Color(32, 74, 135));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTitle.setBounds(84, 28, 288, 15);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblMinimumWage = new JLabel("Sueldo Básico");
		lblMinimumWage.setBounds(40, 50, 200, 30);
		frame.getContentPane().add(lblMinimumWage);
		
		JLabel lblTotalSalesAmount = new JLabel("Importe de venta del  mes:");
		lblTotalSalesAmount.setBounds(40, 90, 200, 30);
		frame.getContentPane().add(lblTotalSalesAmount);
		
		JLabel lblResults = new JLabel("Resultados:");
		lblResults.setFont(new Font("Dialog", Font.BOLD, 14));
		lblResults.setBounds(40, 200, 160, 30);
		frame.getContentPane().add(lblResults);
		
		JLabel lblResultMinimunWage = new JLabel("Sueldo básico: ");
		lblResultMinimunWage.setBounds(50, 240, 160, 30);
		frame.getContentPane().add(lblResultMinimunWage);
		
		JLabel lblResultTotalSale = new JLabel("Venta total del mes:");
		lblResultTotalSale.setBounds(50, 280, 160, 30);
		frame.getContentPane().add(lblResultTotalSale);
		
		JLabel lblResultCommission = new JLabel("Comisión:");
		lblResultCommission.setBounds(50, 320, 160, 30);
		frame.getContentPane().add(lblResultCommission);
		
		JLabel lblResultGrossSalary = new JLabel("Sueldo bruto:");
		lblResultGrossSalary.setBounds(50, 360, 160, 30);
		frame.getContentPane().add(lblResultGrossSalary);
		
		JLabel lblResultDiscount = new JLabel("Descuento");
		lblResultDiscount.setBounds(50, 400, 160, 30);
		frame.getContentPane().add(lblResultDiscount);
		
		JLabel lblNetIncome = new JLabel("Sueldo Neto ");
		lblNetIncome.setBounds(50, 440, 160, 30);
		frame.getContentPane().add(lblNetIncome);
		
		inputMinimunWage = new JTextField();
		inputMinimunWage.setBounds(260, 50, 150, 20);
		frame.getContentPane().add(inputMinimunWage);
		inputMinimunWage.setColumns(10);
		
		inputTotalSalesAmount = new JTextField();
		inputTotalSalesAmount.setBounds(260, 90, 150, 20);
		frame.getContentPane().add(inputTotalSalesAmount);
		inputTotalSalesAmount.setColumns(10);
		
		JLabel lblResulMW = new JLabel("");
		lblResulMW.setBounds(280, 240, 160, 30);
		frame.getContentPane().add(lblResulMW);
		
		JLabel lblResultTSA = new JLabel("");
		lblResultTSA.setBounds(280, 280, 160, 30);
		frame.getContentPane().add(lblResultTSA);
		
		JLabel lblResultCom = new JLabel("");
		lblResultCom.setBounds(280, 320, 160, 30);
		frame.getContentPane().add(lblResultCom);
		
		JLabel lblResultGross = new JLabel("");
		lblResultGross.setBounds(280, 360, 160, 30);
		frame.getContentPane().add(lblResultGross);
		
		JLabel lblResultDisc = new JLabel("");
		lblResultDisc.setBounds(280, 400, 160, 30);
		frame.getContentPane().add(lblResultDisc);
		
		JLabel lblResultNI = new JLabel("");
		lblResultNI.setBounds(280, 440, 160, 30);
		frame.getContentPane().add(lblResultNI);
		
		JButton btnCalculate = new JButton("Calcular");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String labelMinimunWage			= "";
				String labelTotalSalesAmount	= "";
				String labelComission 			= "";
				String labelGrossSalary 		= "";
				String labelDiscount 			= "";
				String labelNetIncome 			= "";
				double minimunWage 				= Double.parseDouble( inputMinimunWage.getText() );
				double totalSalesAmount 		= Double.parseDouble( inputTotalSalesAmount.getText() );
				
				double commission				= 0.1 * totalSalesAmount;
				double grossSalary				= minimunWage + commission;
				double discount					= 0.13 * grossSalary;
				double netIncome				= grossSalary - discount;
				
				labelMinimunWage 		= labelResult( minimunWage );
				labelTotalSalesAmount 	= labelResult( totalSalesAmount );
				labelComission 			= labelResult( commission );
				labelGrossSalary 		= labelResult( grossSalary );
				labelDiscount 			= labelResult( discount );
				labelNetIncome 			= labelResult( netIncome );
				
				lblResulMW.setText( labelMinimunWage );
				lblResultTSA.setText( labelTotalSalesAmount );
				lblResultCom.setText( labelComission );
				lblResultGross.setText( labelGrossSalary );
				lblResultDisc.setText( labelDiscount );
				lblResultNI.setText( labelNetIncome );
			}
		});
		btnCalculate.setBounds(40, 140, 120, 25);
		frame.getContentPane().add(btnCalculate);
		
		JButton btnClean = new JButton("Limpiar");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Limpiar entradas
				 */
				inputMinimunWage.setText("");
				inputTotalSalesAmount.setText("");
				
				/**
				 * Limpiar Resultados
				 */
				lblResulMW.setText("");
				lblResultTSA.setText("");
				lblResultCom.setText("");
				lblResultGross.setText("");
				lblResultDisc.setText("");
				lblResultNI.setText("");
			}
		});
		btnClean.setBounds(180, 140, 120, 25);
		frame.getContentPane().add(btnClean);
		
		JButton btnExit = new JButton("Salir");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(320, 140, 120, 25);
		frame.getContentPane().add(btnExit);
	}
	
	private static String labelResult( double calculate ) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator( '.' );
		
		DecimalFormat formatter = new DecimalFormat( "#.##", symbols );
		
		String calculateTemp = formatter.format(calculate);
		return "S/" + calculateTemp;
	}

}
