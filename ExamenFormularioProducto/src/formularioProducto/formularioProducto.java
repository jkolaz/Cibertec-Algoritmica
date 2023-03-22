package formularioProducto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class formularioProducto {

	private JFrame frame;
	private JTextField inputPrecio;
	private JTextField inputCantidad;
	private JLabel lblResultImporteCompra;
	private JLabel lblResultDescuento;
	private JLabel lblResultSubTotal;
	private JLabel lblResultIgv;
	private JLabel lblResultImportePagar;
	private JLabel lblResultObsequio;
	private JLabel lblObsequios;
	private JLabel lblErrors;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formularioProducto window = new formularioProducto();
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
	public formularioProducto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * Variables de configuración
		 */
		Color background = new Color(18, 35, 46);
		Color colorBorder = new Color(238, 251, 251);
		Color labelColor = new Color(0, 124, 199);
		Color resultColor = new Color(77, 168, 218);
		Color dangerColor = new Color( 220, 53, 69 );
		Color warningColor = new Color( 255, 193, 7 );
		int fontSize =  14;
		
		frame = new JFrame();
		frame.getContentPane().setBackground( background );
		frame.setBackground(background);
		frame.setBounds(100, 100, 440, 607);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("FORMULARIO PRODUCTO");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(238, 251, 251));
		label.setBackground( background );
		label.setFont(new Font("Dialog", Font.BOLD, 25));
		label.setBounds(0, 27, 440, 30);
		frame.getContentPane().add(label);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setForeground( labelColor );
		lblPrecio.setFont(new Font("Dialog", Font.BOLD, fontSize));
		lblPrecio.setBackground(background);
		lblPrecio.setBounds(50, 60, 100, 30);
		frame.getContentPane().add(lblPrecio);
		
		JLabel lblIncIgv = new JLabel("* Incluye IGV");
		lblIncIgv.setForeground( dangerColor );
		lblIncIgv.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIncIgv.setBounds(320, 60, 100, 30);
		frame.getContentPane().add(lblIncIgv);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setForeground( labelColor );
		lblCantidad.setFont(new Font("Dialog", Font.BOLD, fontSize));
		lblCantidad.setBackground(background);
		lblCantidad.setBounds(50, 100, 100, 30);
		frame.getContentPane().add(lblCantidad);
		
		/**
		 * Entradas
		 */
		inputPrecio = new JTextField();
		inputPrecio.setSelectedTextColor( labelColor );
		inputPrecio.setForeground( labelColor );
		inputPrecio.setBorder(new MatteBorder(0, 0, 2, 0, (Color) colorBorder));
		inputPrecio.setBackground(background);
		inputPrecio.setBounds(170, 60, 150, 30);
		frame.getContentPane().add(inputPrecio);
		inputPrecio.setColumns(10);
		
		inputCantidad = new JTextField();
		inputCantidad.setSelectedTextColor( labelColor );
		inputCantidad.setForeground( labelColor );
		inputCantidad.setBorder(new MatteBorder(0, 0, 2, 0, (Color) colorBorder));
		inputCantidad.setBackground(background);
		inputCantidad.setBounds(170, 100, 150, 30);
		frame.getContentPane().add(inputCantidad);
		inputCantidad.setColumns(10);
		
		/**
		 * Botón Calcular
		 */
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font("Dialog", Font.BOLD, fontSize));
		btnCalcular.setForeground( colorBorder );
		btnCalcular.setBackground( labelColor );
		btnCalcular.setBorder(new MatteBorder(2, 2, 2, 2, (Color) colorBorder));
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Declaración de variables
				ArrayList<String> errors 	= new ArrayList<String>();
				String precioEntrada 		= inputPrecio.getText();
				String cantidadEntrada 		= inputCantidad.getText();
				int cantidad;
				double precio;
				
				
				if( isNumeric( precioEntrada ) && isNumeric( cantidadEntrada ) ) {
					precio 			= Double.parseDouble( precioEntrada );
					cantidad 		= Integer.parseInt( cantidadEntrada );
					
					if( precio > 0 && cantidad > 0 ) {
						lblErrors.setText( "" );
						calcular(precio, cantidad);
						
					} else {
						if( precio <= 0 ) {
							inputPrecio.setText("");
							inputPrecio.requestFocus();
							errors.add( "El Precio debe ser mayor a cero." );
						}
						
						if( cantidad <= 0 ) {
							inputCantidad.setText("");
							inputCantidad.requestFocus();
							errors.add( "La Cantidad debe ser mayor a cero." );
						}
					}
					
					
				} else {
					if( !isNumeric( precioEntrada ) ) {
						inputPrecio.setText("");
						inputPrecio.requestFocus();
						errors.add( "El Precio ingresado no es valido." );
					}
					
					if( !isNumeric( cantidadEntrada ) ) {
						inputCantidad.setText("");
						inputCantidad.requestFocus();
						errors.add( "La Cantidad ingresada no es valido." );
					}
				}
				
				imprimirError(errors);
			}
		});
		btnCalcular.setBounds(30, 150, 120, 25);
		frame.getContentPane().add(btnCalcular);
		
		/**
		 * Boton Limpiar Formulario
		 */
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputPrecio.requestFocus();
				inputPrecio.setText("");
				inputCantidad.setText("");
				lblResultImporteCompra.setText("");
				lblResultDescuento.setText("");
				lblResultSubTotal.setText("");
				lblResultIgv.setText("");
				lblResultImportePagar.setText("");
				lblResultObsequio.setText("");
			}
		});
		btnLimpiar.setFont(new Font("Dialog", Font.BOLD, fontSize));
		btnLimpiar.setForeground( colorBorder );
		btnLimpiar.setBackground( labelColor );
		btnLimpiar.setBounds(160, 150, 120, 25);
		btnLimpiar.setBorder(new MatteBorder(2, 2, 2, 2, (Color) colorBorder));
		frame.getContentPane().add(btnLimpiar);
		
		/**
		 * Boton Cerrar
		 */
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCerrar.setForeground( colorBorder );
		btnCerrar.setBackground( dangerColor );
		btnCerrar.setBounds(290, 150, 120, 25);
		btnCerrar.setBorder(new MatteBorder(2, 2, 2, 2, (Color) colorBorder));
		frame.getContentPane().add(btnCerrar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground( colorBorder );
		separator.setBackground( background );
		separator.setBounds(25, 190, 390, 5);
		frame.getContentPane().add(separator);
		
		/**
		 * Etiquetas de resultados
		 */
		
		JLabel lblResultadoTitulo = new JLabel("Resultado:");
		lblResultadoTitulo.setForeground( labelColor );
		lblResultadoTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblResultadoTitulo.setBackground(background);
		lblResultadoTitulo.setBounds(50, 210, 200, 30);
		frame.getContentPane().add(lblResultadoTitulo);
		
		JLabel lblImporteDeCompra = new JLabel("Importe de compra:");
		lblImporteDeCompra.setForeground( colorBorder );
		lblImporteDeCompra.setFont(new Font("Dialog", Font.BOLD, fontSize));
		lblImporteDeCompra.setBackground(background);
		lblImporteDeCompra.setBounds(50, 250, 170, 30);
		frame.getContentPane().add(lblImporteDeCompra);
		
		JLabel lblDescuento = new JLabel("Descuento (20% IC):");
		lblDescuento.setForeground( colorBorder );
		lblDescuento.setFont(new Font("Dialog", Font.BOLD, fontSize));
		lblDescuento.setBackground(background);
		lblDescuento.setBounds(50, 290, 170, 30);
		frame.getContentPane().add(lblDescuento);
		
		JLabel lblSubtotalAPagar = new JLabel("Subtotal a pagar:");
		lblSubtotalAPagar.setForeground( colorBorder );
		lblSubtotalAPagar.setFont(new Font("Dialog", Font.BOLD, fontSize));
		lblSubtotalAPagar.setBackground(background);
		lblSubtotalAPagar.setBounds(50, 330, 170, 30);
		frame.getContentPane().add(lblSubtotalAPagar);
		
		JLabel lblIgv = new JLabel("IGV(18%):");
		lblIgv.setForeground( colorBorder );
		lblIgv.setFont(new Font("Dialog", Font.BOLD, fontSize));
		lblIgv.setBackground(background);
		lblIgv.setBounds(50, 370, 170, 30);
		frame.getContentPane().add(lblIgv);
		
		JLabel lblImportePagar = new JLabel("Importe a pagar");
		lblImportePagar.setForeground( colorBorder );
		lblImportePagar.setFont(new Font("Dialog", Font.BOLD, fontSize));
		lblImportePagar.setBackground(background);
		lblImportePagar.setBounds(50, 410, 170, 30);
		frame.getContentPane().add(lblImportePagar);
		
		lblObsequios = new JLabel("Obsequio:");
		lblObsequios.setForeground( colorBorder );
		lblObsequios.setFont(new Font("Dialog", Font.BOLD, fontSize));
		lblObsequios.setBackground(background);
		lblObsequios.setBounds(50, 450, 170, 30);
		frame.getContentPane().add(lblObsequios);
		
		/**
		 * Imprimir resultados
		 */
		lblResultImporteCompra = new JLabel("");
		lblResultImporteCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResultImporteCompra.setBounds(250, 250, 120, 30);
		lblResultImporteCompra.setForeground( resultColor );
		lblResultImporteCompra.setBackground(background);
		lblResultImporteCompra.setFont(new Font("Dialog", Font.BOLD, fontSize));
		frame.getContentPane().add(lblResultImporteCompra);
		
		lblResultDescuento = new JLabel("");
		lblResultDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResultDescuento.setBounds(250, 290, 120, 30);
		lblResultDescuento.setForeground( resultColor );
		lblResultDescuento.setBackground(background);
		lblResultDescuento.setFont(new Font("Dialog", Font.BOLD, fontSize));
		frame.getContentPane().add(lblResultDescuento);
		
		lblResultSubTotal = new JLabel("");
		lblResultSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResultSubTotal.setBounds(250, 330, 120, 30);
		lblResultSubTotal.setForeground( resultColor );
		lblResultSubTotal.setBackground(background);
		lblResultSubTotal.setFont(new Font("Dialog", Font.BOLD, fontSize));
		frame.getContentPane().add(lblResultSubTotal);
		
		lblResultIgv = new JLabel("");
		lblResultIgv.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResultIgv.setBounds(250, 370, 120, 30);
		lblResultIgv.setForeground( resultColor );
		lblResultIgv.setBackground(background);
		lblResultIgv.setFont(new Font("Dialog", Font.BOLD, fontSize));
		frame.getContentPane().add(lblResultIgv);
		
		lblResultImportePagar = new JLabel("");
		lblResultImportePagar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResultImportePagar.setBounds(250, 410, 120, 30);
		lblResultImportePagar.setForeground( resultColor );
		lblResultImportePagar.setBackground(background);
		lblResultImportePagar.setFont(new Font("Dialog", Font.BOLD, fontSize));
		frame.getContentPane().add(lblResultImportePagar);
		
		lblResultObsequio = new JLabel("");
		lblResultObsequio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResultObsequio.setBounds(150, 450, 220, 30);
		lblResultObsequio.setForeground( warningColor );
		lblResultObsequio.setBackground( background );
		lblResultObsequio.setFont(new Font("Dialog", Font.BOLD, 12));
		frame.getContentPane().add(lblResultObsequio);
		
		lblErrors = new JLabel("");
		lblErrors.setBounds(50, 492, 335, 46);
		lblErrors.setForeground( dangerColor );
		lblErrors.setBackground( background );
		lblErrors.setFont(new Font("Dialog", Font.BOLD, 12));
		frame.getContentPane().add(lblErrors);
	
		
	}
	
	public void calcular( double precio, int cantidad ) {
		double importeCompra, descuento, importePagar, subTotal, igv;
		String labelObsequio;
		
		importeCompra 	= precio * cantidad;
		descuento 		= 0.2 * importeCompra;
		importePagar 	= importeCompra - descuento;
		subTotal 		= ( importePagar * 100 ) / 118;
		igv 			= 0.18 * subTotal;
		
		
		labelObsequio = String.valueOf( cantidad );
		if( cantidad > 1 ) {
			labelObsequio += " caramelos por su compra.";
		} else {
			labelObsequio += " caramelo por su compra.";
		}
		
		lblResultImporteCompra.setText( labelResult( importeCompra ) );
		lblResultDescuento.setText( labelResult( descuento ) );
		lblResultImportePagar.setText( labelResult( importePagar ) );
		lblResultSubTotal.setText( labelResult( subTotal ) );
		lblResultIgv.setText( labelResult( igv ) );
		lblResultObsequio.setText( labelObsequio );
	}
	
	private static String labelResult( double calculate ) {
		return "S/" + String.format("%.2f", calculate);
	}
	
	private static boolean isNumeric( String entry ) {
		return entry.matches("[+-]?\\d*(\\.\\d+)?");
	}
	
	private void imprimirError( ArrayList errors ) {
		String message = "";
		
		if( errors.size() > 0 ) {
			message += "<html><body>";
			for (int i = 0; i < errors.size(); i++) {
				message += errors.get(i) + "<br>";
			}
			message += "</body></html>";
		}

		if( message != "" ) {
			lblErrors.setText( message );
		}
	}
}
