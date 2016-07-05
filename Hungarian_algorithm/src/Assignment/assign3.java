/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Nasrallah
 */
public class assign3 extends javax.swing.JFrame {

    JLabel[][] lab2 = new JLabel[1000][1000];
    JLabel[][] lab = new JLabel[1000][1000];
    String Final = "";
    int count = 0;

    double data[][];

    static double Main_data[][];
    int x, y;
    int row_zero[] = new int[x];
    int col_zero[] = new int[y];
    boolean row_line[] = new boolean[x];
    boolean col_line[] = new boolean[y];

    public assign3(int x, int y, double Main_data[][]) {
        initComponents();
        this.Main_data = Main_data;
        data = new double[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                data[i][j] = Main_data[i][j];
            }
        }
        this.x = x;
        this.y = y;
        row_zero = new int[x];
        col_zero = new int[y];
        row_line = new boolean[x];
        col_line = new boolean[y];
        solve();
        jTextArea2.setText(Final);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void solve() {
        //step 1
        append(data);

        minAllRows();
        append(data);

        minAllColumns();
        append(data);
        System.out.println("ma");
        ////////////
        while (true) {
            //step 2
            count_zero();
            System.out.println("trye");
            int w = line();

            if (w == x) {
             
                add_line_number();
              
                count_zero();
              
                get_result();
               

                break;
            }//////////////
            //step3
            min_all();
            append(data);

        }
    }

    public void min_all() {
        double min = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                if (!(row_line[i] == true || col_line[j] == true)) {
                    min = data[i][j];
                    break;
                }
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                if (!(row_line[i] == true || col_line[j] == true)) {
                    if (min > data[i][j]) {
                        min = data[i][j];
                    }
                }
            }
        }
        del_min(min);
    }

    void del_min(double min) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                if (!(row_line[i] == true || col_line[j] == true)) {
                    data[i][j] -= min;

                }
                if (((row_line[i] == true) && (col_line[j] == true))) {
                    data[i][j] += min;
                }
            }
        }
    }

    public void minColumn(int row) {
        double minVal = data[0][row];

        //find min value
        for (int i = 0; i < x; i++) {
            if (minVal > data[i][row]) {
                minVal = data[i][row];
            }
        }

        //subract from row
        for (int i = 0; i < x; i++) {
            data[i][row] -= minVal;
        }

    }

    public void count_zero() {

        int zero = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (data[i][j] == 0) {
                    zero++;
                }

            }
            row_zero[i] = zero;
            zero = 0;
        }
        zero = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (data[j][i] == 0) {
                    zero++;
                }

            }
            col_zero[i] = zero;
            zero = 0;
        }
       

    }

    public void del_zero(int z, char k) {//delete zero from array of zero
        if (k == 'r') {
            for (int i = 0; i < x; i++) {
                if (data[z][i] == 0) {
                    col_zero[i]--;
                }
            }

        } else {
            for (int i = 0; i < x; i++) {
                if (data[i][z] == 0) {
                    row_zero[i]--;
                }
            }

        }
    }

    public int line() {
        int line_num = 0;

        for (int i = 0; i < x; i++) {
            if (row_zero[i] > 1) {
                row_line[i] = true;
                del_zero(i, 'r');

            }
            if (col_zero[i] > 1) {
                col_line[i] = true;
                del_zero(i, 'c');

            }
        }
        for (int i = 0; i < x; i++) {
            if (row_zero[i] == 1) {
                row_line[i] = true;
                del_zero(i, 'r');

            }
            if (col_zero[i] == 1) {
                col_line[i] = true;
                del_zero(i, 'c');

            }
        }


        

        return count_line();
    }

    public int count_line() {
        int line_num = 0;
        for (int i = 0; i < x; i++) {
            if (row_line[i] == true) {
                line_num++;
            }
            if (col_line[i] == true) {
                line_num++;
            }
        }
        return line_num;
    }

    public void minRow(int column) {
        double minVal = data[column][0];
        for (int i = 0; i < x; i++) {
            if (minVal > data[column][i]) {
                minVal = data[column][i];
            }
        }
        for (int i = 0; i < x; i++) {
            data[column][i] -= minVal;
        }

    }

    public void minAllRows() {
        for (int i = 0; i < x; i++) {
            minRow(i);
        }

    }

    public void minAllColumns() {
        for (int i = 0; i < x; i++) {
            minColumn(i);
        }

    }

    public void append(double arr[][]) {
        Final += "\n";
        Final += "Step " + (++count) + "\n";

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Final += data[i][j] + "  X" + (i + 1) + "" + (j + 1) + "\t";
            }
            Final += "\n";
        }

    }

    public void add_line_number() {
        Final += " Number of line = " + count_line() + " line";
    }
    double result = 0;

    public void get_result() {

        String res = "\n";
        int fl = 0;

        boolean flag = true;
        int index;
        index = get_index_row();
        if (index < 0) {
            flag = false;
        }
        while (flag) {

          
            for (int i = 0; i < y; i++) {
                if (data[index][i] == 0) {
                    result += Main_data[index][i];
                  
                    res += "X" + (index + 1) + " " + (i + 1) + " = 1\n";

                    for (int j = 0; j < x; j++) {
                        if (data[j][i] == 0) {
                            data[j][i] = -1;
                            count_zero();
                        }
                    }
                    for (int w = 0; w < y; w++) {
                        if (data[index][w] == 0) {
                            data[index][w] = -1;
                            count_zero();
                        }
                    }

                    break;
                }
            }
            index = get_index_row();
            if (index == -1) {
                flag = false;
            }
        }
       
        res += "\n Result = " + result;
        Final += res;
    }

    public int get_index_row() {
        int min = -1;
        int flag = 0, fl = 0;
        for (int i = 0; i < x; i++) {
            if (row_zero[i] > 0) {
                min = i;
                break;
            } else {
                fl++;
            }
        }
        if (fl == x) {
            return -1;
        }
        for (int i = 0; i < x; i++) {
            if (row_zero[i] > 0) {
                if (row_zero[min] > row_zero[i]) {
                    min = i;
                }
            } else {
                flag++;
            }
        }
        if (flag == x) {
            return -1;
        } else {
            return min;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 0, 0));

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("Solve new problem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enter(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel1.setText("Coded by : Nasrallah Adel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new assign().setVisible(true);
        this.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enter
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton1ActionPerformed(null);
            System.out.println("fff3");
        }// TODO add your handling code here:
    }//GEN-LAST:event_enter

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
            java.util.logging.Logger.getLogger(assign3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(assign3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(assign3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(assign3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

}
