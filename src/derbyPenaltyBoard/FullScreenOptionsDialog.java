/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard;

import java.awt.GraphicsDevice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nbp184
 */
public class FullScreenOptionsDialog extends javax.swing.JDialog {
    public static final int TOP = 0;
    public static final int LEFT = 1;
    public static final int BOTTOM = 2;
    public static final int RIGHT = 3;
    
    private static MyGraphicsDevice graphicsDevice = new MyGraphicsDevice();
    private static float headerFontSize = 20;
    private static boolean showTeamIdentifier = false;
    private static int[] headerPadding = {5, 5, 5, 5};
    private static boolean headerPaddingSame = true;
    private static int[] rowPadding = {5, 5, 5, 5};
    private static boolean rowPaddingSame = true;
    private static float cellFontSize = 20;
    private static int ejectedLineThickness = 1;
    private static boolean showOnSingleDevice = true;
    
    public static void showDialog(java.awt.Frame parent) {
        FullScreenOptionsDialog dialog = new FullScreenOptionsDialog(parent, true);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    
    public static void showDialog(java.awt.Frame parent, FullScreenForm fsf) {
        FullScreenOptionsDialog dialog = new FullScreenOptionsDialog(parent, true);
        dialog.setFullScreenForm(fsf);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    
    public static GraphicsDevice getScreenDevice() {
        return graphicsDevice.device;
    }
    
    public static float getHeaderFontSize() {
        return headerFontSize;
    }
    
    public static boolean isShowingTeamIdentifier() {
        return showTeamIdentifier;
    }
    
    public static float getCellFontSize() {
        return cellFontSize;
    }
    
    public static int getHeaderPadding(int which) {
        return headerPadding[which];
    }
    
    public static int getRowPadding(int which) {
        return rowPadding[which];
    }
    
    public static int getEjectedLineThickness() {
        return ejectedLineThickness;
    }
    
    public static boolean isOnSingleDevice() {
        return showOnSingleDevice;
    }

    public static void save(String rv) {
        try {
            PrintWriter outFile = new PrintWriter(new File("options.ini"));
            outFile.println(headerFontSize);
            outFile.println(showTeamIdentifier);
            for(int i = 0; i < headerPadding.length; i++) {
                outFile.println(headerPadding[i]);
            }
            outFile.println(headerPaddingSame);
            outFile.println(cellFontSize);
            for(int i = 0; i < rowPadding.length; i++) {
                outFile.println(rowPadding[i]);
            }
            outFile.println(rowPaddingSame);
            outFile.println(ejectedLineThickness);
            outFile.println(showOnSingleDevice);
            outFile.println(rv);
            outFile.close();
        } catch (IOException ex) {
            Logger.getLogger(FullScreenOptionsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String load() {
        String rv = null;
        try {
            BufferedReader inFile = new BufferedReader(new FileReader("options.ini"));
            headerFontSize = Float.parseFloat(inFile.readLine());
            showTeamIdentifier = Boolean.parseBoolean(inFile.readLine());
            for(int i = 0; i < headerPadding.length; i++) {
                headerPadding[i] = Integer.parseInt(inFile.readLine());
            }
            headerPaddingSame = Boolean.parseBoolean(inFile.readLine());
            cellFontSize = Float.parseFloat(inFile.readLine());
            for(int i = 0; i < rowPadding.length; i++) {
                rowPadding[i] = Integer.parseInt(inFile.readLine());
            }
            rowPaddingSame = Boolean.parseBoolean(inFile.readLine());
            ejectedLineThickness = Integer.parseInt(inFile.readLine());
            showOnSingleDevice = Boolean.parseBoolean(inFile.readLine());
            rv = inFile.readLine();
            inFile.close();
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(FullScreenOptionsDialog.class.getName()).log(Level.SEVERE, null, ex);
            rv = null;
        }
        return rv;
    }
    
    private FullScreenForm fsf;

    /**
     * Creates new form FullScreenOptionsDialog
     */
    private FullScreenOptionsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fsf = null;
        lstDisplayOptions.setListData(getScreenDevices());
        lstDisplayOptions.setSelectedValue(graphicsDevice, true);
        spnHeaderFont.setValue((int)headerFontSize);
        cbDisplayIdentifier.setSelected(showTeamIdentifier);
        spnCellFont.setValue((int)cellFontSize);
        spnHeaderPadTop.setValue(headerPadding[0]);
        spnHeaderPadLeft.setValue(headerPadding[1]);
        spnHeaderPadBottom.setValue(headerPadding[2]);
        spnHeaderPadRight.setValue(headerPadding[3]);
        cbHeaderPaddingSame.setSelected(headerPaddingSame);
        spnHeaderPadLeft.setEnabled(!cbHeaderPaddingSame.isSelected());
        spnHeaderPadBottom.setEnabled(!cbHeaderPaddingSame.isSelected());
        spnHeaderPadRight.setEnabled(!cbHeaderPaddingSame.isSelected());
        spnRowPadTop.setValue(rowPadding[0]);
        spnRowPadLeft.setValue(rowPadding[1]);
        spnRowPadBottom.setValue(rowPadding[2]);
        spnRowPadRight.setValue(rowPadding[3]);
        cbRowPaddingSame.setSelected(rowPaddingSame);
        spnRowPadLeft.setEnabled(!cbRowPaddingSame.isSelected());
        spnRowPadBottom.setEnabled(!cbRowPaddingSame.isSelected());
        spnRowPadRight.setEnabled(!cbRowPaddingSame.isSelected());
        spnEjectedThickness.setValue(ejectedLineThickness);
        cbSingleMonitor.setSelected(showOnSingleDevice);
        lblDisplayOn.setEnabled(!cbSingleMonitor.isSelected());
        lstDisplayOptions.setEnabled(!cbSingleMonitor.isSelected());
        spDisplayOptions.setEnabled(!cbSingleMonitor.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDisplayOn = new javax.swing.JLabel();
        spDisplayOptions = new javax.swing.JScrollPane();
        lstDisplayOptions = new javax.swing.JList();
        btnCancel = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        spnHeaderFont = new javax.swing.JSpinner();
        cbDisplayIdentifier = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        spnCellFont = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        spnHeaderPadTop = new javax.swing.JSpinner();
        spnHeaderPadBottom = new javax.swing.JSpinner();
        spnHeaderPadLeft = new javax.swing.JSpinner();
        spnHeaderPadRight = new javax.swing.JSpinner();
        cbHeaderPaddingSame = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        spnRowPadTop = new javax.swing.JSpinner();
        spnRowPadBottom = new javax.swing.JSpinner();
        spnRowPadLeft = new javax.swing.JSpinner();
        spnRowPadRight = new javax.swing.JSpinner();
        cbRowPaddingSame = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        spnEjectedThickness = new javax.swing.JSpinner();
        cbSingleMonitor = new javax.swing.JCheckBox();
        btnAutoFontSize = new javax.swing.JButton();

        setTitle("Full Screen Options");

        lblDisplayOn.setText("Display on:");

        lstDisplayOptions.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        spDisplayOptions.setViewportView(lstDisplayOptions);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jLabel2.setText("Header Font Height");

        cbDisplayIdentifier.setText("Display team identifier in header");

        jLabel3.setText("Row Font Height");

        jLabel4.setText("Header Padding");

        cbHeaderPaddingSame.setText("all same");
        cbHeaderPaddingSame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHeaderPaddingSameActionPerformed(evt);
            }
        });

        jLabel5.setText("Row Padding");

        cbRowPaddingSame.setText("all same");
        cbRowPaddingSame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRowPaddingSameActionPerformed(evt);
            }
        });

        jLabel6.setText("Ejected Line Thickness");

        cbSingleMonitor.setText("Show on same monitor");
        cbSingleMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSingleMonitorActionPerformed(evt);
            }
        });

        btnAutoFontSize.setText("Set Font Size Based On Display Resolution");
        btnAutoFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutoFontSizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnOK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnApply)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDisplayOn)
                                .addComponent(spDisplayOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnCellFont, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnHeaderFont, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnHeaderPadTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(spnHeaderPadLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(spnHeaderPadBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnHeaderPadRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbHeaderPaddingSame))
                                    .addComponent(cbDisplayIdentifier)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnRowPadTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnRowPadLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnRowPadBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnRowPadRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbRowPaddingSame))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnEjectedThickness, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbSingleMonitor)
                            .addComponent(btnAutoFontSize))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDisplayOn)
                    .addComponent(cbSingleMonitor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spDisplayOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spnHeaderFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDisplayIdentifier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(spnHeaderPadTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnHeaderPadBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnHeaderPadLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnHeaderPadRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbHeaderPaddingSame))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(spnCellFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(spnRowPadTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnRowPadBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnRowPadLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnRowPadRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbRowPaddingSame))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(spnEjectedThickness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAutoFontSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnApply)
                    .addComponent(btnOK))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        doSave();
        this.setVisible(false);
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        doSave();
    }//GEN-LAST:event_btnApplyActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cbHeaderPaddingSameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHeaderPaddingSameActionPerformed
        spnHeaderPadLeft.setEnabled(!cbHeaderPaddingSame.isSelected());
        spnHeaderPadBottom.setEnabled(!cbHeaderPaddingSame.isSelected());
        spnHeaderPadRight.setEnabled(!cbHeaderPaddingSame.isSelected());
    }//GEN-LAST:event_cbHeaderPaddingSameActionPerformed

    private void cbRowPaddingSameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRowPaddingSameActionPerformed
        spnRowPadLeft.setEnabled(!cbRowPaddingSame.isSelected());
        spnRowPadBottom.setEnabled(!cbRowPaddingSame.isSelected());
        spnRowPadRight.setEnabled(!cbRowPaddingSame.isSelected());
    }//GEN-LAST:event_cbRowPaddingSameActionPerformed

    private void cbSingleMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSingleMonitorActionPerformed
        lblDisplayOn.setEnabled(!cbSingleMonitor.isSelected());
        lstDisplayOptions.setEnabled(!cbSingleMonitor.isSelected());
        spDisplayOptions.setEnabled(!cbSingleMonitor.isSelected());
    }//GEN-LAST:event_cbSingleMonitorActionPerformed

    private void btnAutoFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutoFontSizeActionPerformed
        int height;
        if(cbSingleMonitor.isSelected()) {
            GraphicsDevice screenDevice = getGraphicsConfiguration().getDevice();
            height = screenDevice.getDisplayMode().getHeight();
        } else {
            graphicsDevice = (MyGraphicsDevice)lstDisplayOptions.getSelectedValue();
            height = graphicsDevice.device.getDisplayMode().getHeight();
        }
        spnHeaderFont.setValue((int)Math.floor(height/24.0 - 10));
        spnCellFont.setValue((int)Math.floor(4*height/75.0 - 9));
    }//GEN-LAST:event_btnAutoFontSizeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnAutoFontSize;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JCheckBox cbDisplayIdentifier;
    private javax.swing.JCheckBox cbHeaderPaddingSame;
    private javax.swing.JCheckBox cbRowPaddingSame;
    private javax.swing.JCheckBox cbSingleMonitor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblDisplayOn;
    private javax.swing.JList lstDisplayOptions;
    private javax.swing.JScrollPane spDisplayOptions;
    private javax.swing.JSpinner spnCellFont;
    private javax.swing.JSpinner spnEjectedThickness;
    private javax.swing.JSpinner spnHeaderFont;
    private javax.swing.JSpinner spnHeaderPadBottom;
    private javax.swing.JSpinner spnHeaderPadLeft;
    private javax.swing.JSpinner spnHeaderPadRight;
    private javax.swing.JSpinner spnHeaderPadTop;
    private javax.swing.JSpinner spnRowPadBottom;
    private javax.swing.JSpinner spnRowPadLeft;
    private javax.swing.JSpinner spnRowPadRight;
    private javax.swing.JSpinner spnRowPadTop;
    // End of variables declaration//GEN-END:variables

    private void doSave() {
        graphicsDevice = (MyGraphicsDevice)lstDisplayOptions.getSelectedValue();
        headerFontSize = (int)spnHeaderFont.getValue();
        showTeamIdentifier = cbDisplayIdentifier.isSelected();
        cellFontSize = (int)spnCellFont.getValue();
        headerPadding[0] = (int)spnHeaderPadTop.getValue();
        if(!cbHeaderPaddingSame.isSelected()) {
            headerPadding[1] = (int)spnHeaderPadLeft.getValue();
            headerPadding[2] = (int)spnHeaderPadBottom.getValue();
            headerPadding[3] = (int)spnHeaderPadRight.getValue();
        } else {
            headerPadding[1] = (int)spnHeaderPadTop.getValue();
            headerPadding[2] = (int)spnHeaderPadTop.getValue();
            headerPadding[3] = (int)spnHeaderPadTop.getValue();
        }
        headerPaddingSame = cbHeaderPaddingSame.isSelected();
        rowPadding[0] = (int)spnRowPadTop.getValue();
        if(!cbRowPaddingSame.isSelected()) {
            rowPadding[1] = (int)spnRowPadLeft.getValue();
            rowPadding[2] = (int)spnRowPadBottom.getValue();
            rowPadding[3] = (int)spnRowPadRight.getValue();
        } else {
            rowPadding[1] = (int)spnRowPadTop.getValue();
            rowPadding[2] = (int)spnRowPadTop.getValue();
            rowPadding[3] = (int)spnRowPadTop.getValue();
        }
        rowPaddingSame = cbRowPaddingSame.isSelected();
        ejectedLineThickness = (int)spnEjectedThickness.getValue();
        showOnSingleDevice = cbSingleMonitor.isSelected();
        if(fsf != null) {
            fsf.updateTables();
        }
    }

    private MyGraphicsDevice[] getScreenDevices() {
        GraphicsDevice[] devices = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        MyGraphicsDevice[] rv = new MyGraphicsDevice[devices.length];
        for(int i = 0; i < devices.length; i++) {
            rv[i] = new MyGraphicsDevice(devices[i], i);
        }
        return rv;
    }

    private void setFullScreenForm(FullScreenForm fsf) {
        this.fsf = fsf;
    }

    private static class MyGraphicsDevice {
        GraphicsDevice device;
        int index;
        
        public MyGraphicsDevice() {
            device = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            index = -1;
        }
        
        public MyGraphicsDevice(GraphicsDevice device, int index) {
            this.device = device;
            this.index = index;
        }
        
        @Override
        public String toString() {
            return "Display " +(index+1);
        }
        
        @Override
        public boolean equals(Object o) {
            if(o instanceof MyGraphicsDevice) {
                return ((MyGraphicsDevice)o).device.equals(device);
            } else {
                return false;
            }
        }
        
    }
}
