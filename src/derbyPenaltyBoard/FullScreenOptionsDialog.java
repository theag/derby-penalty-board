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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    private static boolean setPlayerColumnWidth = false;
    private static int playerColumnWidth = 50;
    private static float satDotHeight = 0.25f;
    private static char satDotHeightUnit = 'F';
    private static String officialReview = "O";
    private static String timeout = "T";
    
    public static void showDialog(java.awt.Frame parent, GraphicsDevice device) {
        FullScreenOptionsDialog dialog = new FullScreenOptionsDialog(parent, true, device);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    
    public static void showDialog(java.awt.Frame parent, GraphicsDevice device, FullScreenForm fsf) {
        FullScreenOptionsDialog dialog = new FullScreenOptionsDialog(parent, true, device);
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
    
    public static boolean isPlayerColumnWidthSet() {
        return setPlayerColumnWidth;
    }
    
    public static int getPlayerColumnWidth() {
        return playerColumnWidth;
    }
    
    public static char getSatDotHeightUnit() {
        return satDotHeightUnit;
    }
    
    public static int getSatDotHeight_Pixel() {
        return (int)satDotHeight;
    }
    
    public static float getSatDotHeight_Fraction() {
        return satDotHeight;
    }
    
    public static String getOfficialReviewString() {
        return officialReview;
    }
    
    public static String getTimeoutString() {
        return timeout;
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
            outFile.println(setPlayerColumnWidth);
            outFile.println(playerColumnWidth);
            outFile.println(satDotHeight);
            outFile.println(satDotHeightUnit);
            outFile.println(officialReview);
            outFile.println(timeout);
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
            setPlayerColumnWidth = Boolean.parseBoolean(inFile.readLine());
            playerColumnWidth = Integer.parseInt(inFile.readLine());
            satDotHeight = Float.parseFloat(inFile.readLine());
            satDotHeightUnit = inFile.readLine().charAt(0);
            officialReview = inFile.readLine();
            timeout = inFile.readLine();
            rv = inFile.readLine();
            inFile.close();
        } catch (IOException ex) {
            Logger.getLogger(FullScreenOptionsDialog.class.getName()).log(Level.SEVERE, null, ex);
            rv = null;
        } catch (NumberFormatException ex) {
            Logger.getLogger(FullScreenOptionsDialog.class.getName()).log(Level.SEVERE, null, ex);
            rv = null;
        }
        return rv;
    }
    
    private FullScreenForm fsf;
    private int currentDeviceIndex;

    /**
     * Creates new form FullScreenOptionsDialog
     */
    private FullScreenOptionsDialog(java.awt.Frame parent, boolean modal, GraphicsDevice device) {
        super(parent, modal);
        initComponents();
        fsf = null;
        MyGraphicsDevice[] devices = getScreenDevices();
        lstDisplayOptions.setListData(devices);
        lstDisplayOptions.setSelectedValue(graphicsDevice, true);
        currentDeviceIndex = -1;
        for(int i = 0; i < devices.length; i++) {
            if(devices[i].isDevice(device)) {
                currentDeviceIndex = i;
                lblCurrentDevice.setText("(current device is " +devices[i] +")");
                break;
            }
        }
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
        chkSetPlayerColumnWidth.setSelected(setPlayerColumnWidth);
        txtPlayerColumnWidth.setText(""+playerColumnWidth);
        txtSatDotHeight.setText(""+satDotHeight);
        switch(satDotHeightUnit) {
            case 'F':
                rbSatDotHeight_Fraction.setSelected(true);
                break;
            case 'P':
                rbSatDotHeight_Pixels.setSelected(true);
                break;
        }
        txtOfficialReviewString.setText(officialReview);
        txtTimeoutString.setText(timeout);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgSatDotHeight_Unit = new javax.swing.ButtonGroup();
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
        btnAutoFontSize = new javax.swing.JButton();
        chkSetPlayerColumnWidth = new javax.swing.JCheckBox();
        txtPlayerColumnWidth = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblCurrentDevice = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSatDotHeight = new javax.swing.JTextField();
        rbSatDotHeight_Pixels = new javax.swing.JRadioButton();
        rbSatDotHeight_Fraction = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtOfficialReviewString = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTimeoutString = new javax.swing.JTextField();

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

        btnAutoFontSize.setText("Set Font Size Based On Display Resolution");
        btnAutoFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutoFontSizeActionPerformed(evt);
            }
        });

        chkSetPlayerColumnWidth.setText("Set Player Column Width");

        txtPlayerColumnWidth.setColumns(4);
        txtPlayerColumnWidth.setText("50");

        jLabel1.setText("px");

        lblCurrentDevice.setText("jLabel7");

        jLabel7.setText("Sat Dot Height");

        txtSatDotHeight.setColumns(4);

        rbgSatDotHeight_Unit.add(rbSatDotHeight_Pixels);
        rbSatDotHeight_Pixels.setText("px");

        rbgSatDotHeight_Unit.add(rbSatDotHeight_Fraction);
        rbSatDotHeight_Fraction.setText("fraction");

        jLabel8.setText("Official Review String");

        txtOfficialReviewString.setColumns(1);

        jLabel9.setText("Timeout String");

        txtTimeoutString.setColumns(1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkSetPlayerColumnWidth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlayerColumnWidth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(130, 130, 130))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnCellFont, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnHeaderFont, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnHeaderPadTop, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnHeaderPadLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cbDisplayIdentifier))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnHeaderPadBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnHeaderPadRight, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbHeaderPaddingSame))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnRowPadTop, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnRowPadLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnRowPadBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnRowPadRight, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbRowPaddingSame))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnEjectedThickness, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAutoFontSize)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDisplayOn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCurrentDevice))
                            .addComponent(spDisplayOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSatDotHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbSatDotHeight_Pixels)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbSatDotHeight_Fraction))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOfficialReviewString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimeoutString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApply)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDisplayOn)
                    .addComponent(lblCurrentDevice))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnCellFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSetPlayerColumnWidth)
                    .addComponent(txtPlayerColumnWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSatDotHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbSatDotHeight_Pixels)
                    .addComponent(rbSatDotHeight_Fraction))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOfficialReviewString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTimeoutString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnApply)
                    .addComponent(btnOK))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if(doSave()) {
            this.setVisible(false);
        }
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

    private void btnAutoFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutoFontSizeActionPerformed
        int height;
        graphicsDevice = (MyGraphicsDevice)lstDisplayOptions.getSelectedValue();
        height = graphicsDevice.device.getDisplayMode().getHeight();
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
    private javax.swing.JCheckBox chkSetPlayerColumnWidth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblCurrentDevice;
    private javax.swing.JLabel lblDisplayOn;
    private javax.swing.JList lstDisplayOptions;
    private javax.swing.JRadioButton rbSatDotHeight_Fraction;
    private javax.swing.JRadioButton rbSatDotHeight_Pixels;
    private javax.swing.ButtonGroup rbgSatDotHeight_Unit;
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
    private javax.swing.JTextField txtOfficialReviewString;
    private javax.swing.JTextField txtPlayerColumnWidth;
    private javax.swing.JTextField txtSatDotHeight;
    private javax.swing.JTextField txtTimeoutString;
    // End of variables declaration//GEN-END:variables

    private boolean doSave() {
        if(!validateData()) {
            return false;
        }
        graphicsDevice = (MyGraphicsDevice)lstDisplayOptions.getSelectedValue();
        headerFontSize = (Integer)spnHeaderFont.getValue();
        showTeamIdentifier = cbDisplayIdentifier.isSelected();
        cellFontSize = (Integer)spnCellFont.getValue();
        headerPadding[0] = (Integer)spnHeaderPadTop.getValue();
        if(!cbHeaderPaddingSame.isSelected()) {
            headerPadding[1] = (Integer)spnHeaderPadLeft.getValue();
            headerPadding[2] = (Integer)spnHeaderPadBottom.getValue();
            headerPadding[3] = (Integer)spnHeaderPadRight.getValue();
        } else {
            headerPadding[1] = (Integer)spnHeaderPadTop.getValue();
            headerPadding[2] = (Integer)spnHeaderPadTop.getValue();
            headerPadding[3] = (Integer)spnHeaderPadTop.getValue();
        }
        headerPaddingSame = cbHeaderPaddingSame.isSelected();
        rowPadding[0] = (Integer)spnRowPadTop.getValue();
        if(!cbRowPaddingSame.isSelected()) {
            rowPadding[1] = (Integer)spnRowPadLeft.getValue();
            rowPadding[2] = (Integer)spnRowPadBottom.getValue();
            rowPadding[3] = (Integer)spnRowPadRight.getValue();
        } else {
            rowPadding[1] = (Integer)spnRowPadTop.getValue();
            rowPadding[2] = (Integer)spnRowPadTop.getValue();
            rowPadding[3] = (Integer)spnRowPadTop.getValue();
        }
        rowPaddingSame = cbRowPaddingSame.isSelected();
        ejectedLineThickness = (Integer)spnEjectedThickness.getValue();
        setPlayerColumnWidth = chkSetPlayerColumnWidth.isSelected();
        playerColumnWidth = Integer.parseInt(txtPlayerColumnWidth.getText());
        satDotHeight = Float.parseFloat(txtSatDotHeight.getText());
        if(rbSatDotHeight_Fraction.isSelected()) {
            satDotHeightUnit = 'F';
        } else if(rbSatDotHeight_Pixels.isSelected()) {
            satDotHeightUnit = 'P';
        }
        officialReview = txtOfficialReviewString.getText();
        timeout = txtTimeoutString.getText();
        if(fsf != null) {
            fsf.updateTables();
        }
        return true;
    }
    
    private boolean validateData() {
        ArrayList<String> errors = new ArrayList<String>();
        if(lstDisplayOptions.getSelectedIndex() == currentDeviceIndex) {
            errors.add("You cannot full screen to your current device.");
        }
        try {
            Integer.parseInt(txtPlayerColumnWidth.getText());
        } catch(NumberFormatException ex) {
            errors.add("Player Column Width must be an integer.");
        }
        try {
            Float.parseFloat(txtSatDotHeight.getText());
        } catch(NumberFormatException ex) {
            errors.add("Sat Dot Height must be a number.");
        }
        if(errors.isEmpty()) {
            return true;
        } else {
            String errorString = "";
            for(int i = 0; i < errors.size(); i++) {
                if(i > 0) {
                    errorString += "\n";
                }
                errorString += (i+1) +".  " +errors.get(i);
            }
            JOptionPane.showMessageDialog(this, errorString, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
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
            GraphicsDevice def = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            GraphicsDevice[] options = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            for(int i = 0; i < options.length; i++) {
                if(!options[i].equals(def)) {
                    device = options[i];
                    break;
                }
            }
            if(device == null) {
                device = def;
            }
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
        
        public boolean isDevice(GraphicsDevice device) {
            return this.device.equals(device);
        }
        
    }
}
