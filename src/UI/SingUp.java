/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import edd_safeway.UserController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSED
 */
public class SingUp extends javax.swing.JFrame {
    
    private boolean flagEmail;
    private boolean flagName;
    private boolean flagUserName;
    private boolean flagPassword;
    
    private UserController controller;

    /**
     * Creates new form SingUp
     */
    public SingUp() {
        initComponents();
        
        controller = UserController.getInstance();
        
        kind_user_field.add(kind_user);
        kind_user_field.add(kind_driver);
        
        this.label_correo.setText("");
        this.label_user.setText("");
        this.label_name.setText("");
        this.label_password.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kind_user_field = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        name_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        username_field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        email_field = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        label_correo = new javax.swing.JLabel();
        password_field = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        phone_field = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        kind_user = new javax.swing.JRadioButton();
        kind_driver = new javax.swing.JRadioButton();
        label_user = new javax.swing.JLabel();
        label_name = new javax.swing.JLabel();
        label_password = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sing Up");
        setMinimumSize(new java.awt.Dimension(345, 512));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SafeWay - EDD project");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sing Up"));

        jLabel2.setText("Nombre completo");

        name_field.setText("Jonh Doe");
        name_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                name_fieldFocusLost(evt);
            }
        });

        jLabel3.setText("Nombre de usuario");

        username_field.setText("jonh_doe");
        username_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                username_fieldFocusLost(evt);
            }
        });
        username_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                username_fieldKeyTyped(evt);
            }
        });

        jLabel4.setText("Correo electrónico");

        email_field.setText("jonh_doe@example.com");
        email_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                email_fieldFocusLost(evt);
            }
        });

        jLabel5.setText("Contraseña");

        label_correo.setForeground(new java.awt.Color(153, 0, 0));
        label_correo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_correo.setText("Hola");

        password_field.setText("Password");
        password_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                password_fieldFocusLost(evt);
            }
        });
        password_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                password_fieldKeyTyped(evt);
            }
        });

        jLabel8.setText("Telefono");

        phone_field.setText("12345678");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo"));

        kind_user.setSelected(true);
        kind_user.setText("Usuario normal");

        kind_driver.setText("Conductor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kind_user)
                .addGap(18, 18, 18)
                .addComponent(kind_driver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kind_user)
                    .addComponent(kind_driver))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label_user.setForeground(new java.awt.Color(153, 0, 0));
        label_user.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_user.setText("Hola");

        label_name.setForeground(new java.awt.Color(153, 0, 0));
        label_name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_name.setText("Hola");

        label_password.setForeground(new java.awt.Color(153, 0, 0));
        label_password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_password.setText("Hola");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name_field)
                    .addComponent(username_field)
                    .addComponent(email_field)
                    .addComponent(password_field)
                    .addComponent(phone_field)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_correo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(label_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(label_user))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(label_correo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(label_password))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phone_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 0));
        jButton2.setText("Registrarse");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Borrar todo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void username_fieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_username_fieldKeyTyped
        
        //Quitar espacios en blanco
        username_field.setText(username_field.getText().trim());
        
    }//GEN-LAST:event_username_fieldKeyTyped

    private void password_fieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password_fieldKeyTyped
        
        //Quitar espacios en blanco
        password_field.setText(password_field.getText().trim());
    }//GEN-LAST:event_password_fieldKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        //Btn cancelar
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        //Borrar todo
        cleanFields();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void email_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_email_fieldFocusLost
        
        
        //Validar correo
        if(this.email_field.getText().isEmpty()){
            this.label_correo.setText("Campo obligatorio");
            this.flagEmail = false;
        }else if(!isEmail(this.email_field.getText())){
            this.label_correo.setText("Correo electrónico inválido");
            this.email_field.requestFocus();
            this.flagEmail = false;
        } else {
            this.label_correo.setText("");
            this.flagEmail = true;
        }
    }//GEN-LAST:event_email_fieldFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        //Registrar
        if (flagEmail && flagName && flagUserName && flagPassword) {
            System.out.println(" | > Registrando usuario: " + this.username_field.getText());
            if(kind_user_field.getSelection().equals(kind_driver.getModel())){
                //Registrar conductor
                boolean flag = controller.createDriver(name_field.getText(), username_field.getText(), email_field.getText(), password_field.getText(), phone_field.getText());
                
                if(flag){
                    System.out.println(" | > Conductor registrado");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo crear el usuario, verificar datos nueva mente", "Nuevo conductor", JOptionPane.WARNING_MESSAGE);
                }
                
            } else {
                //Registrar usuario
                 boolean flag = controller.createUser(name_field.getText(), username_field.getText(), email_field.getText(), password_field.getText(), phone_field.getText());
                
                if(flag){
                    System.out.println(" | > Usuario registrado");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo crear el usuario, verificar datos nueva mente", "Nuevo usuario", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void name_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_name_fieldFocusLost
        
        //Validar campo
        if(this.name_field.getText().isEmpty()){
            this.label_name.setText("Campo obligatorio");
            this.flagName = false;
        } else {
            this.label_name.setText("");
            this.flagName = true;
        }
       
    }//GEN-LAST:event_name_fieldFocusLost

    private void username_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_username_fieldFocusLost
        
        //Validar campo
        if(this.username_field.getText().isEmpty()){
            this.label_user.setText("Campo obligatorio");
            this.flagUserName = false;
        } else {
            this.label_user.setText("");
            this.flagUserName = true;
        }
        
    }//GEN-LAST:event_username_fieldFocusLost

    private void password_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_fieldFocusLost
       
        //Validar campo
        if(this.password_field.getText().isEmpty()){
            this.label_password.setText("Campo obligatorio");
            this.flagPassword = false;
        } else {
            this.label_name.setText("");
            this.flagPassword = true;
        }
        
    }//GEN-LAST:event_password_fieldFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SingUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SingUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SingUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SingUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SingUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email_field;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton kind_driver;
    private javax.swing.JRadioButton kind_user;
    private javax.swing.ButtonGroup kind_user_field;
    private javax.swing.JLabel label_correo;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_password;
    private javax.swing.JLabel label_user;
    private javax.swing.JTextField name_field;
    private javax.swing.JTextField password_field;
    private javax.swing.JTextField phone_field;
    private javax.swing.JTextField username_field;
    // End of variables declaration//GEN-END:variables

    private boolean isEmail(String email) {
        
        //Validar correo electrónico
        Pattern pattern = null;
        Matcher matcher = null;
        
        pattern = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        matcher = pattern.matcher(email);
        
        return matcher.find();
    }
    
    private void cleanFields(){
        
        //Limpiar campos
        this.name_field.setText("");
        this.username_field.setText("");
        this.email_field.setText("");
        this.password_field.setText("");
        this.phone_field.setText("");
        this.label_correo.setText("");
        this.label_user.setText("");
        this.label_name.setText("");
        this.label_password.setText("");
        
        this.flagEmail = false;
        this.flagName = false;
        this.flagPassword = false;
        this.flagUserName = false;
    }
}
