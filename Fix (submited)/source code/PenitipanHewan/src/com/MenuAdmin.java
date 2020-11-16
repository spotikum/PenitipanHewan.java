/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getString;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Indra
 */
public class MenuAdmin extends javax.swing.JFrame {
    // input variable
    // int jenisHewan, jenisMakanan, paketPenitipan, harga, dbayar, kembalian, uangMuka, lamatitip, tmp, kode, tanggal;
    public static String vnamaHewan, vjenisHewan, vjenisMakanan, vjenisPaket, ambil, dt, namaHewanLainnya, berlaku;
    public static String namaPemilik, check_lainnya, telepon, email, alamat, keterangan, lamaPenitipan, namaPaket;
    public static int jenisHewan, jenisMakanan, paketPenitipan, harga, dbayar, kembalian, uangMuka, lamatitip, tmp, kode, tanggal, jenis_hewan, jenis_makanan, sisabayar, tanggalInt, lainnya, dibayarkan, hargapaket, barcode;
    public static SimpleDateFormat Tanggal;
    
    public Login Login;
    public Connection con;
    public Statement stt;
    public MenuAdmin() {
        initComponents();
        
            Calendar timer = Calendar.getInstance();
            timer.getTime();
            SimpleDateFormat tTime = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat Tdate = new SimpleDateFormat("yyyy-MM-dd");
            Tanggal = Tdate;
            int tanggal = (int) (new Date().getTime()/1000);          

            dt = String.valueOf(Tdate);  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
        
        // Tabel View
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_log, tb_user, tb_namahewan, tb_jenishewan, tb_pakethewan WHERE tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 order by id_log");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)table_view.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_log"), rs.getString("namapemilik"), rs.getString("namahewan"), rs.getString("namahewanlainnya"), rs.getString("tanggalterima"), rs.getString("tanggalambil"), rs.getString("namapaket"), rs.getString("telepon")};
                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
        // tabel Output
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_log, tb_user, tb_namahewan, tb_jenishewan, tb_pakethewan WHERE tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 order by id_log");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)outout_tabel.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_log"), rs.getString("namapemilik"), rs.getString("namahewan"), rs.getString("namahewanlainnya")};
                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_user, tb_usertype where tb_user.type = tb_usertype.id_usertype");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)table_setting.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("namausertype")};

                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "select * from tb_user";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                String edit_type = rs.getString("id_user");
                edit_userid.addItem(edit_type);
                delete_userid.addItem(edit_type);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "SELECT * FROM tb_namahewan";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                String namahewan = rs.getString("namahewan");
                cbhewan.addItem(namahewan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
        // buat kode 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "select * from tb_log";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                kode = rs.getInt("kode");
                barcode = rs.getInt("id_log");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
       
        panel_transaksi.setVisible(false);
        panelBayar.setVisible(false);
        
        // Detail transaksi
        /**
        paket_penitipan.setEnabled(false);
        harga_paket_penitipan.setEnabled(false);
        djenis_makanan.setEnabled(false);
        harga_jenismakanan.setEnabled(false);
        dharga_makanan.setEnabled(false);
        harga_harga_makanan.setEnabled(false);
        dlama_penitipan.setEnabled(false);
        harga_lama_penitipan.setEnabled(false);
        uang_muka.setEnabled(false);
        * */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jFileChooser1 = new javax.swing.JFileChooser();
        tabble_setting = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_view = new javax.swing.JTable();
        button_refresh = new javax.swing.JButton();
        view_search = new javax.swing.JTextField();
        view_searchbutton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        paket_gold = new javax.swing.JRadioButton();
        paket_silver = new javax.swing.JRadioButton();
        paket_bronze = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jenismakanan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbjeniskucing = new javax.swing.JComboBox<>();
        namapemilik = new javax.swing.JTextField();
        cbhewan = new javax.swing.JComboBox<>();
        logout1 = new javax.swing.JButton();
        panelBayar = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        terima = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        paket_penitipan = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        djenis_makanan = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        dharga_makanan = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        dlama_penitipan = new javax.swing.JTextField();
        harga_jenismakanan = new javax.swing.JTextField();
        harga_harga_makanan = new javax.swing.JTextField();
        harga_lama_penitipan = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        uang_muka = new javax.swing.JTextField();
        dpembayaran = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        total_bayar = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        input_telepon = new javax.swing.JTextField();
        input_email = new javax.swing.JTextField();
        input_alamat = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        input_lainnya = new javax.swing.JCheckBox();
        input_cheklainnya = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        input_keterangan = new javax.swing.JTextArea();
        lama_titip = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        view_cek_button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        logout2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        outout_tabel = new javax.swing.JTable();
        panel_transaksi = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        out_pemilik = new javax.swing.JTextField();
        out_hewan = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        out_jenis = new javax.swing.JTextField();
        out_makanan = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        out_paket = new javax.swing.JTextField();
        out_telepon = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        out_uangmuka = new javax.swing.JTextField();
        out_kurang = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        out_bayarbutton = new javax.swing.JButton();
        outinputbayar = new javax.swing.JTextField();
        barcodeinput = new javax.swing.JTextField();
        refreshout = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        add_username = new javax.swing.JTextField();
        add_password = new javax.swing.JTextField();
        add_user_button = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        add_type = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        edit_password = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        edit_user_button = new javax.swing.JButton();
        edit_username = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        edit_type = new javax.swing.JComboBox<>();
        edit_userid = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        delete_user_button = new javax.swing.JButton();
        delete_userid = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        logout3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_setting = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabble_setting.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tabble_setting.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabble_setting.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        tabble_setting.setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 720));

        logout.setBackground(new java.awt.Color(255, 0, 51));
        logout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Krabby Patty", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Penitipan Hewan");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        table_view.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table_view.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Pemilik", "Jenis", "Ras", "Dititip", "Diambil", "Paket", "Telepon"
            }
        ));
        table_view.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_view);

        button_refresh.setText("Refresh");
        button_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_refreshActionPerformed(evt);
            }
        });

        view_search.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        view_search.setText("Search");
        view_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view_searchMouseClicked(evt);
            }
        });
        view_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_searchActionPerformed(evt);
            }
        });
        view_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                view_searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                view_searchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                view_searchKeyTyped(evt);
            }
        });

        view_searchbutton.setText("Go");
        view_searchbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_searchbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logout))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(button_refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(view_search, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(view_searchbutton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(view_searchbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(view_search))
                .addContainerGap())
        );

        tabble_setting.addTab("View", jPanel1);

        jLabel2.setFont(new java.awt.Font("Krabby Patty", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Penitipan Hewan");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        buttonGroup1.add(paket_gold);
        paket_gold.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paket_gold.setForeground(new java.awt.Color(0, 102, 0));
        paket_gold.setText("PAKET GOLD");
        paket_gold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paket_goldActionPerformed(evt);
            }
        });

        buttonGroup1.add(paket_silver);
        paket_silver.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paket_silver.setForeground(new java.awt.Color(0, 102, 0));
        paket_silver.setText("PAKET SILVER");
        paket_silver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paket_silverActionPerformed(evt);
            }
        });

        buttonGroup1.add(paket_bronze);
        paket_bronze.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        paket_bronze.setForeground(new java.awt.Color(0, 102, 0));
        paket_bronze.setText("PAKET BRONZE ");
        paket_bronze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paket_bronzeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 0));
        jLabel3.setText("PAKET PENITIPAN");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("HEWAN");

        jenismakanan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jenismakanan.setForeground(new java.awt.Color(0, 102, 0));
        jenismakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenismakananActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 0));
        jLabel6.setText("JENIS HEWAN");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 0));
        jLabel7.setText("NAMA PEMILIK");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 0));
        jLabel8.setText("JENIS MAKANAN");

        cbjeniskucing.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbjeniskucing.setForeground(new java.awt.Color(0, 102, 0));
        cbjeniskucing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjeniskucingActionPerformed(evt);
            }
        });

        namapemilik.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        namapemilik.setForeground(new java.awt.Color(0, 102, 0));
        namapemilik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namapemilikActionPerformed(evt);
            }
        });

        cbhewan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbhewan.setForeground(new java.awt.Color(0, 102, 0));
        cbhewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbhewanActionPerformed(evt);
            }
        });

        logout1.setBackground(new java.awt.Color(255, 0, 51));
        logout1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        logout1.setText("Logout");
        logout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout1ActionPerformed(evt);
            }
        });

        panelBayar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 0));
        jLabel9.setText("Bayar");

        terima.setText("Terima");
        terima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terimaActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("Paket Penitipan ");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Jenis Makanan");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("Harga Makanan");

        jLabel33.setFont(new java.awt.Font("Krabby Patty", 0, 24)); // NOI18N
        jLabel33.setText("Detail Transaksi");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("Lama Penitipan");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("Uang Muka");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("Total");

        javax.swing.GroupLayout panelBayarLayout = new javax.swing.GroupLayout(panelBayar);
        panelBayar.setLayout(panelBayarLayout);
        panelBayarLayout.setHorizontalGroup(
            panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBayarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBayarLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(dpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(terima, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBayarLayout.createSequentialGroup()
                        .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelBayarLayout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(56, 56, 56)
                                .addComponent(uang_muka))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBayarLayout.createSequentialGroup()
                                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelBayarLayout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                        .addComponent(dharga_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelBayarLayout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(djenis_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(harga_jenismakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(harga_harga_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBayarLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(paket_penitipan, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBayarLayout.createSequentialGroup()
                                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelBayarLayout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addGap(25, 25, 25))
                                    .addGroup(panelBayarLayout.createSequentialGroup()
                                        .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(56, 56, 56)))
                                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(total_bayar)
                                    .addGroup(panelBayarLayout.createSequentialGroup()
                                        .addComponent(dlama_penitipan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(harga_lama_penitipan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBayarLayout.setVerticalGroup(
            panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBayarLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paket_penitipan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(djenis_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(harga_jenismakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dharga_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(harga_harga_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dlama_penitipan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(harga_lama_penitipan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uang_muka, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(terima, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dpembayaran))
                .addContainerGap())
        );

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 0));
        jLabel24.setText("Lama Penitipan");

        input_telepon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        input_telepon.setForeground(new java.awt.Color(0, 102, 0));
        input_telepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_teleponActionPerformed(evt);
            }
        });

        input_email.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        input_email.setForeground(new java.awt.Color(0, 102, 0));
        input_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_emailActionPerformed(evt);
            }
        });

        input_alamat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        input_alamat.setForeground(new java.awt.Color(0, 102, 0));
        input_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_alamatActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 0));
        jLabel27.setText("Alamat");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 0));
        jLabel26.setText("Email");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 0));
        jLabel25.setText("Telepon");

        input_lainnya.setText("Lainnya");
        input_lainnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_lainnyaActionPerformed(evt);
            }
        });

        input_cheklainnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_cheklainnyaActionPerformed(evt);
            }
        });
        input_cheklainnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                input_cheklainnyaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                input_cheklainnyaKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 0));
        jLabel21.setText("Keterangan");

        input_keterangan.setColumns(20);
        input_keterangan.setRows(5);
        jScrollPane5.setViewportView(input_keterangan);

        lama_titip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lama_titipActionPerformed(evt);
            }
        });
        lama_titip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lama_titipKeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 0));
        jLabel28.setText("Hari");

        view_cek_button.setText("cek");
        view_cek_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_cek_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(cbhewan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(namapemilik, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(input_lainnya)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(input_cheklainnya))
                                        .addComponent(cbjeniskucing, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jenismakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(paket_gold)
                                    .addComponent(lama_titip, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(paket_silver, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(paket_bronze))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(input_telepon, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(input_email, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(input_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(view_cek_button, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logout1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(logout1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(namapemilik, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cbhewan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cbjeniskucing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(input_lainnya)
                            .addComponent(input_cheklainnya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jenismakanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(lama_titip, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(paket_bronze)
                            .addComponent(paket_silver)
                            .addComponent(paket_gold))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(input_telepon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(input_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(input_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(view_cek_button, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );

        tabble_setting.addTab("Input", jPanel2);

        jLabel4.setFont(new java.awt.Font("Krabby Patty", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Penitipan Hewan");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        logout2.setBackground(new java.awt.Color(255, 0, 51));
        logout2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        logout2.setText("Logout");
        logout2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Barkode");

        jToggleButton1.setText("Go");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        outout_tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Pemilik", "Hewan", "Jenis"
            }
        ));
        outout_tabel.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(outout_tabel);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );

        panel_transaksi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel43.setFont(new java.awt.Font("Krabby Patty", 0, 24)); // NOI18N
        jLabel43.setText("Detail Transaksi");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("Pemilik");

        out_pemilik.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        out_hewan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel45.setText("Hewan");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("Jenis");

        out_jenis.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        out_makanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("Makanan");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("Paket");

        out_paket.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        out_telepon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel49.setText("Telepon");

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel50.setText("Uang Muka");

        out_uangmuka.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        out_kurang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setText("Kurang");

        out_bayarbutton.setText("Bayar");
        out_bayarbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                out_bayarbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_transaksiLayout = new javax.swing.GroupLayout(panel_transaksi);
        panel_transaksi.setLayout(panel_transaksiLayout);
        panel_transaksiLayout.setHorizontalGroup(
            panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_transaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(out_pemilik, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(out_hewan, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(out_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(out_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(out_paket, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(out_telepon, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(out_uangmuka, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(out_kurang, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_transaksiLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_transaksiLayout.createSequentialGroup()
                        .addComponent(outinputbayar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(out_bayarbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel_transaksiLayout.setVerticalGroup(
            panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_transaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel44)
                    .addComponent(out_pemilik, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(out_hewan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(out_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(out_makanan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(out_paket, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(out_telepon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(out_uangmuka, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addGap(18, 18, 18)
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(out_kurang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(panel_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outinputbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(out_bayarbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        refreshout.setText("Refresh");
        refreshout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(refreshout, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(barcodeinput, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 20, Short.MAX_VALUE)
                        .addComponent(panel_transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logout2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(logout2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(barcodeinput)
                            .addComponent(refreshout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(panel_transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        tabble_setting.addTab("Output", jPanel3);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        add_user_button.setText("Add");
        add_user_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_user_buttonActionPerformed(evt);
            }
        });

        jLabel12.setText("Password");

        jLabel13.setText("Type");

        jLabel14.setText("Username");

        add_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Karyawan" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12)
                            .addComponent(add_username, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addComponent(add_password, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(add_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(add_user_button, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_username, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_password, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(add_type, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(add_user_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );

        jTabbedPane1.addTab("Add", jPanel6);

        jLabel15.setText("User ID");

        jLabel16.setText("New Password");

        jLabel17.setText("Type");

        edit_user_button.setText("Edit");
        edit_user_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_user_buttonActionPerformed(evt);
            }
        });

        jLabel18.setText("New Username");

        edit_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Karyawan" }));
        edit_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_typeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(edit_user_button, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16)
                            .addComponent(edit_password, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addComponent(edit_username, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18)
                            .addComponent(edit_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edit_userid, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(161, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edit_userid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_username, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_password, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edit_type, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(edit_user_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        jTabbedPane1.addTab("Edit", jPanel7);

        jLabel19.setText("User ID");

        delete_user_button.setText("Delete");
        delete_user_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_user_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(delete_userid, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(133, 133, 133)
                            .addComponent(delete_user_button, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jLabel19)))
                    .addContainerGap(130, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(delete_userid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(346, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(199, 199, 199)
                    .addComponent(jLabel19)
                    .addGap(81, 81, 81)
                    .addComponent(delete_user_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(267, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Delete", jPanel8);

        jLabel11.setFont(new java.awt.Font("Krabby Patty", 1, 36)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Penitipan Hewan");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        logout3.setBackground(new java.awt.Color(255, 0, 51));
        logout3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        logout3.setText("Logout");
        logout3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout3ActionPerformed(evt);
            }
        });

        table_setting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Username", "Password", "Type"
            }
        ));
        table_setting.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_setting);

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logout3)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(logout3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabble_setting.addTab("Setting", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabble_setting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabble_setting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_user, tb_usertype where tb_user.type = tb_usertype.id_usertype");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)table_setting.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("namausertype")};

                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void logout3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout3ActionPerformed
        Login call = new Login();
        call.setVisible(true);
        call.setTitle("Login");
        this.dispose();
    }//GEN-LAST:event_logout3ActionPerformed

    private void delete_user_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_user_buttonActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // String url="jdbc:mysql://localhost:3306;databaseName=penitipanhewan;user=root";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            String query = "DELETE FROM tb_user WHERE id_user = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, delete_userid.getSelectedItem().toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Yeah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_user, tb_usertype where tb_user.type = tb_usertype.id_usertype");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)table_setting.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("namausertype")};

                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_delete_user_buttonActionPerformed

    private void edit_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_typeActionPerformed

    private void edit_user_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_user_buttonActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            String query = "UPDATE tb_user SET username = ?, password = ?, type = ? WHERE id_user = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, edit_username.getText());
            pst.setString(2, edit_password.getText());
            String typebox = null;
            try {
                String user_edit = "select * from tb_user";
                PreparedStatement edit = con.prepareStatement(user_edit);
                if (add_type.getSelectedItem().toString()=="Admin") {
                    typebox = "1";
                }
                else if(add_type.getSelectedItem().toString()=="Karyawan"){
                    typebox = "2";
                }
            } catch (Exception e) {
            }
            pst.setString(3, typebox);
            pst.setString(4, edit_userid.getSelectedItem().toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Yeah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_user, tb_usertype where tb_user.type = tb_usertype.id_usertype");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)table_setting.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("namausertype")};

                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_edit_user_buttonActionPerformed

    private void add_user_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_user_buttonActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // String url="jdbc:mysql://localhost:3306;databaseName=penitipanhewan;user=root";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            String query = "insert into tb_user(username, password, type) values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, add_username.getText());
            pst.setString(2, add_password.getText());
            String typebox = null;
            if (add_type.getSelectedItem().toString()=="Admin") {
                typebox = "1";
            }
            else if(add_type.getSelectedItem().toString()=="Karyawan"){
                typebox = "2";
            }
            pst.setString(3, typebox);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Yeah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_user, tb_usertype where tb_user.type = tb_usertype.id_usertype");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)table_setting.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("namausertype")};

                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_add_user_buttonActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        panel_transaksi.setVisible(true);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "SELECT * FROM tb_log, tb_user, tb_namahewan, tb_jenishewan, tb_pakethewan, tb_jenismakanan WHERE tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND tb_log.`id_jenismakanan` = tb_jenismakanan.`id_jenismakanan` AND stats = 1 AND id_log = '"+barcodeinput.getText()+"'";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                namaPemilik = rs.getString("namapemilik");
                namaHewanLainnya = rs.getString("namahewan");
                vjenisHewan = rs.getString("namahewanlainnya");
                vjenisMakanan = rs.getString("namajenismakanan");
                vjenisPaket = rs.getString("namapaket");
                email = rs.getString("email");
                telepon = rs.getString("telepon");
                uangMuka = rs.getInt("harga");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        out_pemilik.setText(namaPemilik);
        out_hewan.setText(namaHewanLainnya);
        out_jenis.setText(vjenisHewan);
        out_makanan.setText(vjenisMakanan);
        out_paket.setText(vjenisPaket);
        out_telepon.setText(telepon);
        out_uangmuka.setText("Rp. "+String.valueOf(uangMuka));
        out_kurang.setText("Rp. "+String.valueOf(uangMuka));
        
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void logout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout2ActionPerformed
        Login call = new Login();
        call.setVisible(true);
        call.setTitle("Login");
        this.dispose();
    }//GEN-LAST:event_logout2ActionPerformed

    private void view_cek_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_cek_buttonActionPerformed
        // TODO add your handling code here:
        panelBayar.setVisible(true);
        namaPemilik = namapemilik.getText();
        lamaPenitipan = lama_titip.getText();
        telepon = input_telepon.getText();
        email = input_email.getText();
        alamat = input_alamat.getText();
        keterangan = input_keterangan.getText();
        namaHewanLainnya = cbjeniskucing.getSelectedItem().toString();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "SELECT * FROM tb_jenismakanan WHERE id_jenismakanan = '"+jenis_makanan+"'";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                harga = rs.getInt("harga");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "SELECT * FROM tb_jenishewan, tb_namahewan WHERE tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_namahewan.`id_namahewan` = '"+jenis_hewan+"'";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                jenisHewan = rs.getInt("id_jenishewan");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        // show to detail
        String Pakettitip = null;
        if (paketPenitipan == 1) {
            Pakettitip = "Gold";
        }
        else if(paketPenitipan == 2){
            Pakettitip = "Silver";
        }
        else if (paketPenitipan == 3){
            Pakettitip = "Bronze";
        }
        else{
            Pakettitip = "";
        }
        lamatitip = Integer.parseInt(lama_titip.getText());
        // paket_penitipan.setText(email);
        paket_penitipan.setText("Paket " + String.valueOf(Pakettitip));
        djenis_makanan.setText(jenismakanan.getSelectedItem().toString());
        harga_jenismakanan.setText(cbhewan.getSelectedItem().toString());
        dharga_makanan.setText("Rp. "+String.valueOf(harga)+"/Kg");
        harga_harga_makanan.setText("Rp. "+String.valueOf(harga/5)+"/Hari");
        dlama_penitipan.setText(lama_titip.getText()+" Hari");
        harga_lama_penitipan.setText("Rp. "+String.valueOf((harga/5)+(hargapaket))+"/Hari");
        uang_muka.setText("Rp. "+String.valueOf(((harga/5)+(20000*lamatitip))/2));
        total_bayar.setText("Rp. "+String.valueOf((harga/5)+(hargapaket*lamatitip)));

        vnamaHewan = cbhewan.getSelectedItem().toString();
        vjenisHewan = cbjeniskucing.getSelectedItem().toString();
        vjenisMakanan = jenismakanan.getSelectedItem().toString();
        vjenisPaket = Pakettitip;
        sisabayar = (((harga/5)+(20000*lamatitip))/2);

    }//GEN-LAST:event_view_cek_buttonActionPerformed

    private void lama_titipKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lama_titipKeyTyped
        try {
            int i=Integer.parseInt(lama_titip.getText());
            //lama_titip.setEditable(true);
        } catch (Exception e) {
            //lama_titip.setEditable(false);
            lama_titip.setText("");
        }
    }//GEN-LAST:event_lama_titipKeyTyped

    private void lama_titipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lama_titipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lama_titipActionPerformed

    private void input_cheklainnyaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_input_cheklainnyaKeyTyped
        if (input_lainnya.isSelected()) {
            cbjeniskucing.removeAllItems();
            check_lainnya = input_cheklainnya.getText();
            cbjeniskucing.addItem(check_lainnya);
        }
        else{
            cbjeniskucing.removeAllItems();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
                ResultSet rs;
                String query = "SELECT * FROM tb_jenishewan, tb_namahewan WHERE tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_jenishewan.`id_namahewan` = '"+jenis_hewan+"'";
                PreparedStatement pst = con.prepareStatement(query);
                rs=pst.executeQuery();
                while (rs.next()) {
                    String namahewan = rs.getString("namajenishewan");
                    cbjeniskucing.addItem(namahewan);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }//GEN-LAST:event_input_cheklainnyaKeyTyped

    private void input_cheklainnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_input_cheklainnyaKeyPressed
        /**
        if (input_lainnya.isSelected()) {
            cbjeniskucing.removeAllItems();
            check_lainnya = input_cheklainnya.getText();
            cbjeniskucing.addItem(check_lainnya);
        }
        */

    }//GEN-LAST:event_input_cheklainnyaKeyPressed

    private void input_cheklainnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_cheklainnyaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_cheklainnyaActionPerformed

    private void input_lainnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_lainnyaActionPerformed
        // TODO add your handling code here:
        if (input_lainnya.isSelected()) {
            cbjeniskucing.removeAllItems();
            check_lainnya = input_cheklainnya.getText();
            cbjeniskucing.addItem(check_lainnya);
            lainnya = 1;
        }
        else{
            cbjeniskucing.removeAllItems();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
                ResultSet rs;
                String query = "SELECT * FROM tb_jenishewan, tb_namahewan WHERE tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_jenishewan.`id_namahewan` = '"+jenis_hewan+"'";
                PreparedStatement pst = con.prepareStatement(query);
                rs=pst.executeQuery();
                while (rs.next()) {
                    String namahewan = rs.getString("namajenishewan");
                    cbjeniskucing.addItem(namahewan);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
            lainnya = 0;
        }
    }//GEN-LAST:event_input_lainnyaActionPerformed

    private void input_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_alamatActionPerformed

    private void input_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_emailActionPerformed

    private void input_teleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_teleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_teleponActionPerformed

    private void terimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terimaActionPerformed
        uangMuka = ((harga/5)+(20000*lamatitip))/2;
        kembalian = Integer.valueOf(dpembayaran.getText()) - uangMuka;
        if (kembalian>0) {
            Calendar timer = Calendar.getInstance();
            timer.getTime();
            SimpleDateFormat tTime = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat Tdate = new SimpleDateFormat("yyyy-MM-dd");
            tanggalInt = (int) (new Date().getTime()/1000);

            String dt = String.valueOf(Tdate);  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException ex) {
                Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            c.add(Calendar.DATE, lamatitip);  // number of days to add
            dt = sdf.format(c.getTime());  // dt is now the new date
            ambil = dt;
            c.add(Calendar.DATE, lamatitip+60);  // number of days to add
            dt = sdf.format(c.getTime());  // dt is now the new date
            berlaku = dt;
            barcode +=1;
            
            // JOptionPane.showMessageDialog(null, Integer.parseInt(ambil) - Integer.parseInt(String.valueOf(Tdate)));
            // int sekarang = tanggalInt+(tanggalInt-1591768440);
            // SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            // String formatedDate = newFormat.format(sekarang);
            // JOptionPane.showMessageDialog(null, formatedDate);
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
                String query = "INSERT INTO tb_log(id_user, id_jenishewan, id_jenismakanan, id_pakethewan, kode, namapemilik, hewanlainnya, namahewanlainnya, tanggalterima, tanggalambil, telepon, email, alamat, keterangan, stats, tanggalint, harga) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, 1);
                pst.setInt(2, jenisHewan);
                pst.setInt(3, jenisMakanan);
                pst.setInt(4, paketPenitipan);
                pst.setInt(5, kode+1);
                pst.setString(6, namaPemilik);
                pst.setInt(7, lainnya);
                pst.setString(8, namaHewanLainnya);
                pst.setString(9, Tdate.format(timer.getTime()));
                pst.setString(10, dt);
                pst.setString(11, telepon);
                pst.setString(12, email);
                pst.setString(13, alamat);
                pst.setString(14, keterangan);
                pst.setInt(15, 1);
                pst.setInt(16, tanggalInt);
                pst.setInt(17, uangMuka);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Kembalian : Rp. "+kembalian);
                
                Printfile frame = null;
                try {
                    frame = new Printfile();
                } catch (PrinterException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.setVisible(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }

            
        }
        else{
            JOptionPane.showMessageDialog(null, "Uang Kurang");
        }

    }//GEN-LAST:event_terimaActionPerformed

    private void logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout1ActionPerformed
        Login call = new Login();
        call.setVisible(true);
        call.setTitle("Login");
        this.dispose();
    }//GEN-LAST:event_logout1ActionPerformed

    private void cbhewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbhewanActionPerformed
        cbjeniskucing.removeAllItems();
        /**
        if (cbhewan.getSelectedItem().toString().equalsIgnoreCase("anjing")) {
            jenishewan = 2;
        }
        else if (cbhewan.getSelectedItem().toString().equalsIgnoreCase("kucing")) {
            jenishewan = 1;
        }
        */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "SELECT * FROM tb_namahewan WHERE namahewan = '"+cbhewan.getSelectedItem().toString()+"'";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                jenis_hewan = rs.getInt("id_namahewan");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "SELECT * FROM tb_jenishewan, tb_namahewan WHERE tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_jenishewan.`id_namahewan` = '"+jenis_hewan+"'";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                String namahewan = rs.getString("namajenishewan");
                cbjeniskucing.addItem(namahewan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_cbhewanActionPerformed

    private void namapemilikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namapemilikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namapemilikActionPerformed

    private void cbjeniskucingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjeniskucingActionPerformed
        jenismakanan.removeAllItems();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "SELECT * FROM tb_namahewan WHERE namahewan = '"+cbhewan.getSelectedItem().toString()+"'";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                jenis_makanan = rs.getInt("id_namahewan");
                jenisMakanan = rs.getInt("id_namahewan");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            ResultSet rs;
            String query = "SELECT * FROM tb_jenismakanan, tb_namahewan WHERE tb_jenismakanan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_jenismakanan.`id_namahewan` = '"+jenis_makanan+"'";
            PreparedStatement pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()) {
                String namahewan = rs.getString("namajenismakanan");
                jenismakanan.addItem(namahewan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_cbjeniskucingActionPerformed

    private void jenismakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenismakananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenismakananActionPerformed

    private void paket_bronzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paket_bronzeActionPerformed
        // TODO add your handling code here:
        if (paket_bronze.isSelected()) {
            paketPenitipan = 3;
            hargapaket = 20000;
        }
    }//GEN-LAST:event_paket_bronzeActionPerformed

    private void paket_silverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paket_silverActionPerformed
        // TODO add your handling code here:
        if (paket_silver.isSelected()) {
            paketPenitipan = 2;
            hargapaket = 40000;
        }
    }//GEN-LAST:event_paket_silverActionPerformed

    private void paket_goldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paket_goldActionPerformed
        // TODO add your handling code here:
        if (paket_gold.isSelected()) {
            paketPenitipan = 1;
            hargapaket = 60000;
        }

    }//GEN-LAST:event_paket_goldActionPerformed

    private void view_searchbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_searchbuttonActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_log, tb_user, tb_namahewan, tb_jenishewan, tb_pakethewan WHERE tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 AND id_log = '"+view_search.getText()+"' or tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 AND namapemilik = '"+view_search.getText()+"' or tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 AND namahewan = '"+view_search.getText()+"' order by id_log");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)table_view.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_log"), rs.getString("namapemilik"), rs.getString("namahewan"), rs.getString("namahewanlainnya"), rs.getString("tanggalterima"), rs.getString("tanggalambil"), rs.getString("namapaket"), rs.getString("telepon")};
                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_view_searchbuttonActionPerformed

    private void view_searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_view_searchKeyTyped
        // view_search.setText("");
    }//GEN-LAST:event_view_searchKeyTyped

    private void view_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_view_searchKeyReleased
        // view_search.setText("");
    }//GEN-LAST:event_view_searchKeyReleased

    private void view_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_view_searchKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
                stt = (Statement) con.createStatement();
                PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_log, tb_user, tb_namahewan, tb_jenishewan, tb_pakethewan WHERE tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 AND id_log = '"+view_search.getText()+"' or tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 AND namapemilik = '"+view_search.getText()+"' or tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 AND namahewan = '"+view_search.getText()+"' order by id_log");
                ResultSet rs=ps.executeQuery();
                DefaultTableModel tm=(DefaultTableModel)table_view.getModel();
                tm.setRowCount(0);
                while(rs.next()){
                    Object o[]={rs.getInt("id_log"), rs.getString("namapemilik"), rs.getString("namahewan"), rs.getString("namahewanlainnya"), rs.getString("tanggalterima"), rs.getString("tanggalambil"), rs.getString("namapaket"), rs.getString("telepon")};
                    tm.addRow(o);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }//GEN-LAST:event_view_searchKeyPressed

    private void view_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_searchActionPerformed
        // TODO add your handling code here:
        // view_search.setVisible(false);
    }//GEN-LAST:event_view_searchActionPerformed

    private void view_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view_searchMouseClicked
        view_search.setText("");
    }//GEN-LAST:event_view_searchMouseClicked

    private void button_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_refreshActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_log, tb_user, tb_namahewan, tb_jenishewan, tb_pakethewan WHERE tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 order by id_log");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)table_view.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_log"), rs.getString("namapemilik"), rs.getString("namahewan"), rs.getString("namahewanlainnya"), rs.getString("tanggalterima"), rs.getString("tanggalambil"), rs.getString("namapaket"), rs.getString("telepon")};
                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_button_refreshActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        Login call = new Login();
        call.setVisible(true);
        call.setTitle("Login");
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void out_bayarbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_out_bayarbuttonActionPerformed
        // TODO add your handling code here:
        kembalian = Integer.valueOf(outinputbayar.getText()) - uangMuka;
        if (kembalian>0) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
                String query = "UPDATE tb_log SET stats = ? WHERE id_log = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, 0);
                pst.setString(2, barcodeinput.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Kembalian : Rp. "+kembalian);
                dibayarkan = Integer.parseInt(outinputbayar.getText());
                
                Printfile2 frame = null;
                try {
                    frame = new Printfile2();
                } catch (PrinterException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Uang Kurang");
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_log, tb_user, tb_namahewan, tb_jenishewan, tb_pakethewan WHERE tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 order by id_log");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)outout_tabel.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_log"), rs.getString("namapemilik"), rs.getString("namahewan"), rs.getString("namahewanlainnya")};
                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_out_bayarbuttonActionPerformed

    private void refreshoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshoutActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/penitipanhewan", "root", "");
            stt = (Statement) con.createStatement();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM tb_log, tb_user, tb_namahewan, tb_jenishewan, tb_pakethewan WHERE tb_log.`id_user` = tb_user.`id_user` AND tb_jenishewan.`id_namahewan` = tb_namahewan.`id_namahewan` AND tb_log.`id_jenishewan` = tb_jenishewan.`id_jenishewan`  AND tb_log.`id_pakethewan` = tb_pakethewan.`id_pakethewan` AND stats = 1 order by id_log");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel tm=(DefaultTableModel)outout_tabel.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id_log"), rs.getString("namapemilik"), rs.getString("namahewan"), rs.getString("namahewanlainnya")};
                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_refreshoutActionPerformed
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
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField add_password;
    private javax.swing.JComboBox<String> add_type;
    private javax.swing.JButton add_user_button;
    private javax.swing.JTextField add_username;
    private javax.swing.JTextField barcodeinput;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton button_refresh;
    private javax.swing.JComboBox<String> cbhewan;
    private javax.swing.JComboBox<String> cbjeniskucing;
    private javax.swing.JButton delete_user_button;
    private javax.swing.JComboBox<String> delete_userid;
    private javax.swing.JTextField dharga_makanan;
    private javax.swing.JTextField djenis_makanan;
    private javax.swing.JTextField dlama_penitipan;
    private javax.swing.JTextField dpembayaran;
    private javax.swing.JTextField edit_password;
    private javax.swing.JComboBox<String> edit_type;
    private javax.swing.JButton edit_user_button;
    private javax.swing.JComboBox<String> edit_userid;
    private javax.swing.JTextField edit_username;
    private javax.swing.JTextField harga_harga_makanan;
    private javax.swing.JTextField harga_jenismakanan;
    private javax.swing.JTextField harga_lama_penitipan;
    private javax.swing.JTextField input_alamat;
    private javax.swing.JTextField input_cheklainnya;
    private javax.swing.JTextField input_email;
    private javax.swing.JTextArea input_keterangan;
    private javax.swing.JCheckBox input_lainnya;
    private javax.swing.JTextField input_telepon;
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JComboBox<String> jenismakanan;
    private javax.swing.JTextField lama_titip;
    private javax.swing.JButton logout;
    private javax.swing.JButton logout1;
    private javax.swing.JButton logout2;
    private javax.swing.JButton logout3;
    private javax.swing.JTextField namapemilik;
    private javax.swing.JButton out_bayarbutton;
    private javax.swing.JTextField out_hewan;
    private javax.swing.JTextField out_jenis;
    private javax.swing.JTextField out_kurang;
    private javax.swing.JTextField out_makanan;
    private javax.swing.JTextField out_paket;
    private javax.swing.JTextField out_pemilik;
    private javax.swing.JTextField out_telepon;
    private javax.swing.JTextField out_uangmuka;
    private javax.swing.JTextField outinputbayar;
    private javax.swing.JTable outout_tabel;
    private javax.swing.JRadioButton paket_bronze;
    private javax.swing.JRadioButton paket_gold;
    private javax.swing.JTextField paket_penitipan;
    private javax.swing.JRadioButton paket_silver;
    private javax.swing.JPanel panelBayar;
    private javax.swing.JPanel panel_transaksi;
    private javax.swing.JButton refreshout;
    private javax.swing.JTabbedPane tabble_setting;
    private javax.swing.JTable table_setting;
    private javax.swing.JTable table_view;
    private javax.swing.JButton terima;
    private javax.swing.JTextField total_bayar;
    private javax.swing.JTextField uang_muka;
    private javax.swing.JButton view_cek_button;
    private javax.swing.JTextField view_search;
    private javax.swing.JButton view_searchbutton;
    // End of variables declaration//GEN-END:variables

}
