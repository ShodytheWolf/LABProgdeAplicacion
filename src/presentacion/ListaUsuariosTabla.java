package presentacion;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import excepciones.UsuarioNoExisteException;
import logica.DataUsuario;
import logica.IControladorUsuario;

/**
 * JInternalFrame que permite listar todos los usuarios del sistema.
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class ListaUsuariosTabla extends JInternalFrame{

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorUsuario controlUsr;
    private JTable table;
    private JScrollPane scrollPane;
    
    /**
     * Create the frame.
     */
    public ListaUsuariosTabla(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 300, 100);
        
        // En este caso se utiliza un BorderLayout en donde los componentes se ubican según una orientación.
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        scrollPane = new JScrollPane();
        table = new JTable();
        scrollPane.setViewportView(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        
    }
    
    // Método que permite cargar un nuevo modelo para la tabla con la información
    // actualizada de usuarios, provista por la operación del sistema getUsuarios(). 
    // Se invoca el método antes de hacer visible el JInternalFrame
    public void cargarUsuarios() {
    	DefaultTableModel model;        
        try {
        	// Nombres de las columnas
            String[] columnNames = {"Nombre", "Apellido", "CI"};
        	// Creo data model para la tabla a partir de data types
            Object[][] data = {};
        	model = new DefaultTableModel(data, columnNames);    	
			for (DataUsuario usr : controlUsr.getUsuarios()) {
	        	model.addRow(new Object[]{usr.getNombre(), usr.getApellido(), usr.getCedulaIdentidad()});
			}
			table.setModel(model);
		} catch (UsuarioNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún elemento
		}
    }
}
