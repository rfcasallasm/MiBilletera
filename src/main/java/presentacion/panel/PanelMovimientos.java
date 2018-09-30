/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.panel;

import logica.entidades.Cuenta;
import logica.entidades.Movimiento;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import presentacion.Modelo;
import presentacion.Vista;

/**
 *
 * @author rfcas
 */
public class PanelMovimientos extends javax.swing.JPanel {

    private Vista vista;

    private TableModel movimientosModel;

    /**
     * Creates new form PanelMovimientos
     */
    public PanelMovimientos(Vista vista) {
        super();
        this.vista = vista;
        initModels();
        initComponents();
    }

    private void initModels() {
        this.movimientosModel = new MovimientoTableModel(this.vista.getModelo());
    }

    public void refrescar(Cuenta cuenta) {
        ((MovimientoTableModel) this.movimientosModel).setCuenta(cuenta);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(800, 400));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jTable1.setModel(this.movimientosModel);
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

class MovimientoTableModel extends AbstractTableModel {

    private static final String[] COLUMNAS = new String[]{"#", "Tipo", "Categoria", "Fecha", "Descripción", "Valor COP"};

    private Modelo modelo;

    private Cuenta cuenta;

    private List<Movimiento> movimientos;

    public MovimientoTableModel(Modelo modelo) {
        super();
        this.modelo = modelo;
        this.cuenta = null;
        this.movimientos = null;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        //
        this.fireTableDataChanged();
    }

    private List<Movimiento> getMovimientos() {
        if (this.movimientos == null) {
            this.movimientos = modelo.getListadoMovimientos(cuenta);
        }
        return this.movimientos;
    }

    @Override
    public int getRowCount() {
        return this.getMovimientos().size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int col) {
        return MovimientoTableModel.COLUMNAS[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movimiento movimiento = this.getMovimientos().get(rowIndex);
        switch (columnIndex) {
            case 0: {
                NumberFormat numberFormat = new DecimalFormat("0000000");
                return numberFormat.format(movimiento.getId());
            }
            case 1:
                return movimiento.getIdCategoriaMovimiento().getIdTipoCategoria().getNombre();
            case 2:
                return movimiento.getIdCategoriaMovimiento().getNombre();
            case 3: {
                return movimiento.getFecha();
            }
            case 4:
                return movimiento.getDescripcion();
            case 5: {
                NumberFormat numberFormat = new DecimalFormat("0.##");
                return numberFormat.format(movimiento.getValor());
            }
            default:
                return null;
        }
    }

    @Override
    public void fireTableDataChanged() {
        this.movimientos = null;
        super.fireTableDataChanged(); //To change body of generated methods, choose Tools | Templates.
    }

}
