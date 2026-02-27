/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import controlador.ObraControlador;
import dao.ObraDAO;
import dao.UsuarioDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.Obra;
import modelo.Response;
import modelo.Usuario;


public class ObrasPanel extends javax.swing.JPanel {

    int idc;
    
    
    public ObrasPanel() {
        initComponents();
        cargarObras();
        cargarEmpleadosDisponibles();
        cargarObrasYEmpleados();
    }

    
   
    void cargarObras (){
        
     ObraDAO dao = new ObraDAO();
    Response<Obra> resp = dao.readAll();

    if (resp.isSuccess()) {
        List<Obra> obras = resp.getData();

        String[] columnas = {"ID", "Nombre", "Ubicación", "Fecha Inicio", "Fecha Fin", "Presupuesto", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Obra o : obras) {
            Object[] fila = {
                o.getId(),
                o.getNombre(),
                o.getUbicacion(),
                o.getFechaInicio().toString(),
                o.getFechaFin().toString(),
                o.getPresupuesto(),
                o.getEstado()
            };
            model.addRow(fila);
        }

        tablaObras.setModel(model);

    } else {
        JOptionPane.showMessageDialog(this, "Error al cargar obras: " + resp.getMessage());
    }
    }
    
       //Cargar empleados disponibles
    void cargarEmpleadosDisponibles (){
        
     UsuarioDAO dao = new UsuarioDAO();
    Response<Usuario> resp = dao.readAll();

    if (resp.isSuccess()) {
        List<Usuario> empleados = resp.getData();

        String[] columnas = {"ID", "Nombre", "Contraseña", "DNI", "Tipo"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Usuario u : empleados) {
            Object[] fila = {
               u.getId(),
                u.getNombre(),
                u.getContrasenia(),
                u.getDni(),
                u.getTipo()
            };
            model.addRow(fila);
        }

        tablaEmpleados.setModel(model);

    } else {
        JOptionPane.showMessageDialog(this, "Error al cargar empleados: " + resp.getMessage());
    }
    }
    
    // Atributos de la ventana
private final ObraControlador obraCtrl = new ObraControlador();

          // Cargar tabla "Obras y Empleados"
private void cargarObrasYEmpleados() {
    Response<Obra> resp = obraCtrl.listarObras();

    if (resp.isSuccess() && resp.getData() != null) {
        List<Obra> obras = resp.getData();

        String[] columnas = {"Obra", "Empleado 1", "Empleado 2", "Empleado 3"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Obra o : obras) {
            List<Empleado> empleados = o.getEmpleados(); // nunca será null gracias al constructor
            String e1 = empleados.size() > 0 ? empleados.get(0).getNombre() : "";
            String e2 = empleados.size() > 1 ? empleados.get(1).getNombre() : "";
            String e3 = empleados.size() > 2 ? empleados.get(2).getNombre() : "";

            Object[] fila = {o.getNombre(), e1, e2, e3};
            model.addRow(fila);
        }

        tablaObrasYEmpleados.setModel(model);
    } else {
        JOptionPane.showMessageDialog(this,
            "Error al cargar obras y empleados: " + (resp.getMessage() != null ? resp.getMessage() : "Sin datos"),
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Asignar empleado a obra
private void asignarEmpleado() {
    DefaultTableModel modeloObras = (DefaultTableModel) tablaObras.getModel();
    DefaultTableModel modeloEmpleados = (DefaultTableModel) tablaEmpleados.getModel();

    int filaObra = tablaObras.getSelectedRow();
    int filaEmpleado = tablaEmpleados.getSelectedRow();

    if (filaObra == -1 || filaEmpleado == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione obra y empleado.");
        return;
    }

    int obraId = (int) modeloObras.getValueAt(filaObra, 0);
    int empleadoId = (int) modeloEmpleados.getValueAt(filaEmpleado, 0);

    Response<String> resp = obraCtrl.asignarEmpleado(obraId, empleadoId);
    JOptionPane.showMessageDialog(this, resp.getMessage());

    // Actualizar tabla
    cargarObrasYEmpleados();
}

    // Eliminar empleado de obra
    private void eliminarEmpleado() {
    DefaultTableModel modeloObras = (DefaultTableModel) tablaObras.getModel();
    DefaultTableModel modeloEmpleados = (DefaultTableModel) tablaEmpleados.getModel();

        int filaObra = tablaObras.getSelectedRow();
        int filaEmpleado = tablaEmpleados.getSelectedRow();

        if (filaObra == -1 || filaEmpleado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione obra y empleado.");
            return;
        }

        int obraId = (int) modeloObras.getValueAt(filaObra, 0);
        int empleadoId = (int) modeloEmpleados.getValueAt(filaEmpleado, 0);

        Response<String> resp = obraCtrl.eliminarEmpleado(obraId, empleadoId);
        JOptionPane.showMessageDialog(this, resp.getMessage());
        cargarObrasYEmpleados();
    }


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaObras = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaObrasYEmpleados = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Operaciones"));
        jPanel1.setName(""); // NOI18N

        jButton1.setText("AGREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addGap(59, 59, 59)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Obra"));

        jLabel1.setText("ID");

        jLabel2.setText("Nombre");

        jLabel3.setText("Ubicación");

        jLabel4.setText("Fecha Inicio");

        jLabel5.setText("Fecha Fin");

        jTextField1.setEditable(false);
        jTextField1.setEnabled(false);

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Presupuesto");

        jLabel7.setText("Estado");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jTextField4)
                    .addComponent(jTextField5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Obras"));

        tablaObras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Ubicación", "Fecha Inicio", "Fecha Fin", "Presupuesto", "Estado"
            }
        ));
        tablaObras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObrasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaObras);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados Disponibles"));

        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Contraseña", "DNI", "Tipo"
            }
        ));
        jScrollPane5.setViewportView(tablaEmpleados);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Operaciones"));
        jPanel5.setName(""); // NOI18N

        jButton4.setText("ASIGNAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("ELIMINAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(61, 61, 61)
                .addComponent(jButton6)
                .addGap(113, 113, 113))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Obras y Empleados"));

        tablaObrasYEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Obra", "Empleado 1", "Empleado 2", "Empleado 3"
            }
        ));
        jScrollPane1.setViewportView(tablaObrasYEmpleados);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(498, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(259, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       
        //Boton para registar obra
        
        String nombre = jTextField2.getText();
        String ubicacion = jTextField3.getText();
        String fechaInicioStr = jTextField4.getText();
        String fechaFinStr = jTextField5.getText();
        String presupuestoStr = jTextField6.getText();
        String estado = jTextField7.getText();
        
        if (nombre.isEmpty() || ubicacion.isEmpty() || fechaInicioStr.isEmpty() || fechaFinStr.isEmpty() || presupuestoStr.isEmpty() || estado.isEmpty()) {
        JOptionPane.showMessageDialog(this, " Faltan ingresar datos");
        return;
        }
           
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicioUtil = sdf.parse(fechaInicioStr);
        Date fechaFinUtil = sdf.parse(fechaFinStr);
        double presupuesto = Double.parseDouble(presupuestoStr);
        
        java.sql.Date fechaInicio = new java.sql.Date(fechaInicioUtil.getTime());
        java.sql.Date fechaFin = new java.sql.Date(fechaFinUtil.getTime());

        Obra o = new Obra();
        o.setNombre(nombre);
        o.setUbicacion(ubicacion);
        o.setFechaInicio(fechaInicio);
        o.setFechaFin(fechaFin);
        o.setPresupuesto(presupuesto);
        o.setEstado(estado);

        ObraControlador controlador = new ObraControlador();
        Response<Obra> response = controlador.crearObra(o);

        //Verificar si la obra se creo correctamente
        if (response.isSuccess()) {
            JOptionPane.showMessageDialog(this, "Obra registrada correctamente");
            
            //limpiar los campos de texto
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            
            //Recargar la tabla de obras
            cargarObras();
            
        } else {
            JOptionPane.showMessageDialog(this, "Error: " + response.getMessage());
        }

    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto, usar yyyy-MM-dd");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Presupuesto debe ser un número");
    } catch (Exception e) { //Manejo de excepciones
        JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
    }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Boton para editar obra
        
    String idStr = jTextField1.getText();
    String nombre = jTextField2.getText();
    String ubicacion = jTextField3.getText();
    String fechaInicioStr = jTextField4.getText();
    String fechaFinStr = jTextField5.getText();
    String presupuestoStr = jTextField6.getText();
    String estado = jTextField7.getText();

    if (idStr.isEmpty() || nombre.isEmpty() || ubicacion.isEmpty() ||
        fechaInicioStr.isEmpty() || fechaFinStr.isEmpty() || presupuestoStr.isEmpty() || estado.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Faltan ingresar datos");
        return;
    }

    try {
        int id = Integer.parseInt(idStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaInicioUtil = sdf.parse(fechaInicioStr);
        java.util.Date fechaFinUtil = sdf.parse(fechaFinStr);
        java.sql.Date fechaInicio = new java.sql.Date(fechaInicioUtil.getTime());
        java.sql.Date fechaFin = new java.sql.Date(fechaFinUtil.getTime());
        double presupuesto = Double.parseDouble(presupuestoStr);

        Obra o = new Obra();
       
        o.setId(id);
        o.setNombre(nombre);
        o.setUbicacion(ubicacion);
        o.setFechaInicio(fechaInicio);
        o.setFechaFin(fechaFin);
        o.setPresupuesto(presupuesto);
        o.setEstado(estado);

        ObraControlador controlador = new ObraControlador();
        Response<Obra> response = controlador.actualizarObra(o); 

        //Verificar si la obra se actualizo correctamente
        if (response.isSuccess()) {
            JOptionPane.showMessageDialog(this, "Obra actualizada correctamente");

           //Limpiar campos de texto
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            
            //recargar la tabla de obras
            cargarObras();
        } else {
            JOptionPane.showMessageDialog(this, "Error: " + response.getMessage());
        }

    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Usar yyyy-MM-dd");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID y presupuesto deben ser números");
    } catch (Exception e) { //Manejo de excepciones
        JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
    }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        //Botón para eliminar obra
        
    int fila = tablaObras.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una obra");
        return;
    }
    int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar esta obra?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (opcion == JOptionPane.YES_OPTION) {
        try {
            int id = Integer.parseInt(tablaObras.getValueAt(fila, 0).toString()); 

            ObraControlador controlador = new ObraControlador();
            Response<Obra> response = controlador.eliminarObra(id);

            //Verificar si la obra se elimino correctamente
            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(this, "Obra eliminada correctamente.");
                
                //Recargar la tabla de obras
                cargarObras(); 
                
                //Limpiar los campos de texto
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField7.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + response.getMessage());
            }

        } catch (Exception e) { //Manejo de excepciones
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tablaObrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObrasMouseClicked
         // Seleccionar obra desde la tabla para editarla:
        
        int fila = tablaObras.getSelectedRow();
        if (fila == -1){
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fila");
        }else {
            idc = Integer.parseInt((String) tablaObras.getValueAt(fila, 0) .toString());
            String nombre = (String) tablaObras.getValueAt(fila, 1);
            String ubicacion = (String) tablaObras.getValueAt(fila, 2);
            String fechaInicioStr = tablaObras.getValueAt(fila, 3).toString();
            String fechaFinStr = tablaObras.getValueAt(fila, 4).toString();
            double presupuesto = Double.parseDouble(tablaObras.getValueAt(fila, 5).toString());
            String estado = (String) tablaObras.getValueAt(fila, 6);
            
            jTextField1.setText(""+idc);
            jTextField2.setText(nombre);
            jTextField3.setText(ubicacion);
            jTextField4.setText(fechaInicioStr);         
            jTextField5.setText(fechaFinStr);                  
            jTextField6.setText(String.valueOf(presupuesto));               
            jTextField7.setText(estado); 

        
       
        }    
        
    }//GEN-LAST:event_tablaObrasMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Boton asignar empleado
        asignarEmpleado();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Boton eliminar empleado
        eliminarEmpleado();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tablaObras;
    private javax.swing.JTable tablaObrasYEmpleados;
    // End of variables declaration//GEN-END:variables

  
}