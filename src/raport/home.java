/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author angel
 */
public class home extends javax.swing.JFrame {
    private DefaultTableModel model = null;
    private PreparedStatement stat;
    private ResultSet rs;
    koneksi  k = new koneksi();

    /**
     * Creates new form siswa
     */
    public home() {
    initComponents();
    k.connect();
    refreshTable();

    // Event listener untuk tombol SEARCH berdasarkan Nama Siswa
  new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        btn_search6ActionPerformed(e);
    }
};

    btn_clear3.addActionListener(evt -> clearForm());
}
    
    

    private void clearForm() {
    text_id_siswa5.setText("");
    text_semester2.setText("");
    text_blok.setText("");
    text_course1.setText("");
    text_course2.setText("");
    text_course3.setText("");
    text_course4.setText("");
    text_course5.setText("");
    text_score1.setText("");
    text_score2.setText("");
    text_score3.setText("");
    text_score4.setText("");
    text_score5.setText("");

    }

   private int getIdSiswaFromField() {
    String idSiswaText = text_id_siswa5.getText(); // Ambil ID siswa dari text field
    int idSiswa = -1; // Inisialisasi nilai id_siswa

    // Pastikan ID siswa yang dimasukkan valid
    try {
        idSiswa = Integer.parseInt(idSiswaText); // Konversi teks menjadi integer
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID siswa harus berupa angka!", "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    return idSiswa; // Kembalikan id_siswa yang ditemukan
}
   
    
    class datasiswa extends home{
    int id_siswa;
     String nama_siswa, nis, tanggal_lahir, jenis_kelamin, email, no_handphone, no_hp_ayah, no_hp_ibu, alamat;
     
          
     public datasiswa (){
         nama_siswa = text_nama_siswa.getText();
         nis = text_nis.getText();
         try {
                java.util.Date date = tanggal.getDate();
                DateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
                this.tanggal_lahir = dateformat.format(date);
         }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
         jenis_kelamin = combo_jk.getSelectedItem().toString(); 
         email = text_email.getText();
         no_handphone = text_no_hp.getText();
         no_hp_ayah = text_no_ayah.getText();
         no_hp_ibu = text_no_ibu.getText();
         alamat = text_alamat.getText();
     }
    }
    
    public void refreshTable(){
        {
        model = new DefaultTableModel();
        model.addColumn("ID SISWA");
        model.addColumn("NAMA SISWA");
        model.addColumn("NIS");
        model.addColumn("TANGGAL LAHIR");
        model.addColumn("JENIS KELAMIN");
        model.addColumn("EMAIL");
        model.addColumn("NO HP");
        model.addColumn("NO HP AYAH");
        model.addColumn("NO HP IBU");
        model.addColumn("ALAMAT");
        tbl_siswa.setModel(model);
        try {
           this.stat = k.getCon().prepareStatement("select * from students");
           this.rs = this.stat.executeQuery();
            while (rs.next()) {
                Object[] data ={
                    rs.getInt("id_siswa"),
                    rs.getString("nama_siswa"),
                    rs.getString("nis"),
                    rs.getString("tanggal_lahir"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("email"),
                    rs.getString("no_handphone"),
                    rs.getString("no_hp_ayah"),
                    rs.getString("no_hp_ibu"),
                    rs.getString("alamat"),
                };
                model.addRow(data);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        text_id_siswa.setText("");
        text_nama_siswa.setText("");
        text_nis.setText("");
        text_email.setText("");
        text_no_hp.setText("");   
        text_no_ayah.setText("");
        text_no_ibu.setText("");
        text_alamat.setText("");
        }
        
        {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("ID SISWA");
        model.addColumn("SEMESTER");
        model.addColumn("BLOK");
        model.addColumn("COURSE 1");
        model.addColumn("COURSE 2");
        model.addColumn("COURSE 3");
        model.addColumn("COURSE 4");
        model.addColumn("COURSE 5");
        tbl_course.setModel(model);
        try {
           this.stat = k.getCon().prepareStatement("select * from courses");
           this.rs = this.stat.executeQuery();
            while (rs.next()) {
                Object[] data ={
                    rs.getInt("id_course"),
                    rs.getString("id_siswa"),
                    rs.getString("semester"),
                    rs.getString("blok"),
                    rs.getString("course1"),
                    rs.getString("course2"),
                    rs.getString("course3"),
                    rs.getString("course4"),
                    rs.getString("course5"),
                };
                model.addRow(data);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        text_id.setText("");
        text_id_siswa2.setText(""); 
        }
        
        {  
    model = new DefaultTableModel();
    model.addColumn("ID MARK");
    model.addColumn("ID SISWA");
    model.addColumn("SEMESTER");
    model.addColumn("BLOCK");
    model.addColumn("COURSE 1");
    model.addColumn("SCORE 1");
    model.addColumn("COURSE 2");
    model.addColumn("SCORE 2");
    model.addColumn("COURSE 3");
    model.addColumn("SCORE 3");
    model.addColumn("COURSE 4");
    model.addColumn("SCORE 4");
    model.addColumn("COURSE 5");
    model.addColumn("SCORE 5");

    tbl_score.setModel(model); // Set model ke tabel score

    try {
        this.stat = k.getCon().prepareStatement("SELECT * FROM marks");
        this.rs = this.stat.executeQuery();

        while (rs.next()) {
            Object[] data = {
                rs.getInt("id_mark"),
                rs.getString("id_siswa"),
                rs.getString("semester"),
                rs.getString("block"),
                rs.getString("id_course1"),
                rs.getString("score1"),
                rs.getString("id_course2"),
                rs.getString("score2"),
                rs.getString("id_course3"),
                rs.getString("score3"),
                rs.getString("id_course4"),
                rs.getString("score4"),
                rs.getString("id_course5"),
                rs.getString("score5"),
            };
            model.addRow(data);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

    // Kosongkan input field
    text_id.setText("");
    text_id_siswa5.setText("");
    text_semester2.setText("");
    text_blok.setText("");
    text_course1.setText("");
    text_score1.setText("");
    text_course2.setText("");
    text_score2.setText("");
    text_course3.setText("");
    text_score3.setText("");
    text_course4.setText("");
    text_score4.setText("");
    text_course5.setText("");
    text_score5.setText("");
}
}

    
    class course extends home{
    int id_course;
     String id_siswa,semester, blok, course1, course2, course3, course4, course5;
     
     public course (){
         id_siswa = text_id_siswa2.getText();
         semester = combo_semester.getSelectedItem().toString(); 
         blok = combo_blok.getSelectedItem().toString();
         course1 = combo1.getSelectedItem().toString();
         course2 = combo2.getSelectedItem().toString();
         course3 = combo3.getSelectedItem().toString();
         course4 = combo4.getSelectedItem().toString();
         course5 = combo5.getSelectedItem().toString();
     }
    }
    public void searchCourse() {
        try {
            String idSiswa = text_id_siswa2.getText();
            String semester = combo_semester.getSelectedItem().toString();

            String sql = "SELECT blok, course1, course2, course3, course4, course5 FROM courses WHERE id_siswa = ? AND semester = ?";
            PreparedStatement pst = k.getCon().prepareStatement(sql);
            pst.setString(1, idSiswa);
            pst.setString(2, semester);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Isi data ke field yang relevan
                combo_blok.setSelectedItem(rs.getString("blok"));
                combo1.setSelectedItem(rs.getString("course1"));
                combo2.setSelectedItem(rs.getString("course2"));
                combo3.setSelectedItem(rs.getString("course3"));
                combo4.setSelectedItem(rs.getString("course4"));
                combo5.setSelectedItem(rs.getString("course5"));
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    class score extends home{
    int id_mark;
     String id_siswa, semester, block, id_course1, score1, id_course2, score2, id_course3, score3, id_course4, score4, id_course5, score5;
     
          
     public score (){
         id_siswa = text_id_siswa5.getText();
         semester = text_semester2.getText();
         block = text_blok.getText();
         id_course1 = text_course1.getText();
         score1 = text_score1.getText();
         id_course2 = text_course2.getText();
         score2 = text_score2.getText();
         id_course3 = text_course3.getText();
         score3 = text_score3.getText();
         id_course4 = text_course4.getText();
         score4 = text_score4.getText();
         id_course5 = text_course5.getText();
         score5 = text_score5.getText();
     }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        text_nama_siswa = new javax.swing.JTextField();
        text_nis = new javax.swing.JTextField();
        tanggal = new com.toedter.calendar.JDateChooser();
        combo_jk = new javax.swing.JComboBox<>();
        text_email = new javax.swing.JTextField();
        text_no_hp = new javax.swing.JTextField();
        text_no_ayah = new javax.swing.JTextField();
        text_no_ibu = new javax.swing.JTextField();
        text_alamat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        text_id_siswa = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_siswa = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        text_id = new javax.swing.JTextField();
        text_id_siswa2 = new javax.swing.JTextField();
        combo_semester = new javax.swing.JComboBox<>();
        combo_blok = new javax.swing.JComboBox<>();
        combo1 = new javax.swing.JComboBox<>();
        combo2 = new javax.swing.JComboBox<>();
        combo3 = new javax.swing.JComboBox<>();
        combo4 = new javax.swing.JComboBox<>();
        combo5 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_course = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        btn_logout2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        text_id_siswa3 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        text_semester = new javax.swing.JTextField();
        btn_search4 = new javax.swing.JButton();
        text_id2 = new javax.swing.JTextField();
        text_id_siswa5 = new javax.swing.JTextField();
        text_semester2 = new javax.swing.JTextField();
        text_blok = new javax.swing.JTextField();
        text_score1 = new javax.swing.JTextField();
        text_score2 = new javax.swing.JTextField();
        text_score3 = new javax.swing.JTextField();
        text_score4 = new javax.swing.JTextField();
        text_score5 = new javax.swing.JTextField();
        text_course1 = new javax.swing.JTextField();
        text_course2 = new javax.swing.JTextField();
        text_course3 = new javax.swing.JTextField();
        text_course4 = new javax.swing.JTextField();
        text_course5 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_score = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        btn_save2 = new javax.swing.JButton();
        btn_update2 = new javax.swing.JButton();
        btn_clear3 = new javax.swing.JButton();
        btn_delete3 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        text_id_siswa6 = new javax.swing.JTextField();
        btn_search6 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        lbl_rata_rata = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_marks = new javax.swing.JTable();
        jPanel34 = new javax.swing.JPanel();
        btn_print4 = new javax.swing.JButton();
        btn_clear4 = new javax.swing.JButton();
        btn_logout4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel1.setText("STUDENT MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(29, 41, 81));

        jPanel4.setBackground(new java.awt.Color(29, 41, 81));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        combo_jk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- PILIH JENIS KELAMIN--", "LAKI-LAKI", "PEREMPUAN" }));
        combo_jk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_jkActionPerformed(evt);
            }
        });

        text_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_alamatActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NAMA SISWA");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NIS");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TANGGAL LAHIR");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("JENIS KELAMIN");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("NO HANDPHONE");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("NO HANDPHONE AYAH");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("NO HANDPHONE IBU");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("EMAIL");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ALAMAT");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID SISWA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_nama_siswa)
                    .addComponent(text_nis)
                    .addComponent(tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(text_email)
                    .addComponent(text_no_hp)
                    .addComponent(text_no_ayah)
                    .addComponent(text_no_ibu)
                    .addComponent(text_alamat)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(combo_jk, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 124, Short.MAX_VALUE))
                    .addComponent(text_id_siswa, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_id_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(text_nama_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(text_nis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(combo_jk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_no_hp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_no_ayah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_no_ibu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(256, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(29, 41, 81));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jPanel8.setBackground(new java.awt.Color(29, 41, 81));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("DATA SISWA");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(29, 41, 81));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        tbl_siswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID SISWA", "NAMA SISWA", "NIS", "TANGGAL LAHIR", "JENIS KELAMIN", "EMAIL", "NO HP", "NO HP AYAH", "NO HP IBU", "ALAMAT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_siswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_siswa);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(29, 41, 81));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        btn_add.setBackground(new java.awt.Color(0, 102, 153));
        btn_add.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_add.setForeground(new java.awt.Color(29, 41, 81));
        btn_add.setText("ADD NEW");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(0, 102, 153));
        btn_update.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_update.setForeground(new java.awt.Color(29, 41, 81));
        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(0, 102, 153));
        btn_delete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(29, 41, 81));
        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_logout.setBackground(new java.awt.Color(0, 102, 153));
        btn_logout.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_logout.setForeground(new java.awt.Color(29, 41, 81));
        btn_logout.setText("LOGOUT");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(btn_logout, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                    .addComponent(btn_update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Student", jPanel3);

        jPanel11.setBackground(new java.awt.Color(29, 41, 81));

        jPanel12.setBackground(new java.awt.Color(29, 41, 81));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jPanel13.setBackground(new java.awt.Color(29, 41, 81));
        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("MATA PELAJARAN ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        text_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_idActionPerformed(evt);
            }
        });

        combo_semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- PILIH SEMESTER --", "1", "2", " " }));
        combo_semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_semesterActionPerformed(evt);
            }
        });

        combo_blok.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- PILIH BLOK --", "1", "2", "3", "4", "5", "6", "7", "8" }));

        combo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- PELAJARAN --", "AGAMA ", "MATEMATIKA", "MANDARIN", "ENGLISH", "BAHASA INDONESIA", "PPKN", "SEJARAH", "PEMOGRAMAN WEB", "PEMOGRAMAN BERGERAK", "PBTGM", "BASIS DATA", "CONVERSATION", "PKK", "SENI BUDAYA", "PENJAS", "IPA", "IPS" }));

        combo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- PELAJARAN --", "AGAMA ", "MATEMATIKA", "MANDARIN", "ENGLISH", "BAHASA INDONESIA", "PPKN", "SEJARAH", "PEMOGRAMAN WEB", "PEMOGRAMAN BERGERAK", "PEMOGRAMAN TEKS, GRAFIS, MULTIMEDIA", "BASIS DATA", "CONVERSATION", "PKK", "SENI BUDAYA", "PENJAS", "IPA", "IPS", " " }));

        combo3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- PELAJARAN --", "AGAMA ", "MATEMATIKA", "MANDARIN", "ENGLISH", "BAHASA INDONESIA", "PPKN", "SEJARAH", "PEMOGRAMAN WEB", "PEMOGRAMAN BERGERAK", "PBTGM", "BASIS DATA", "CONVERSATION", "PKK", "SENI BUDAYA", "PENJAS", "IPA", "IPS" }));

        combo4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- PELAJARAN --", "AGAMA ", "MATEMATIKA", "MANDARIN", "ENGLISH", "BAHASA INDONESIA", "PPKN", "SEJARAH", "PEMOGRAMAN WEB", "PEMOGRAMAN BERGERAK", "PBTGM", "BASIS DATA", "CONVERSATION", "PKK", "SENI BUDAYA", "PENJAS", "IPA", "IPS", " " }));

        combo5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- PELAJARAN --", "AGAMA ", "MATEMATIKA", "MANDARIN", "ENGLISH", "BAHASA INDONESIA", "PPKN", "SEJARAH", "PEMOGRAMAN WEB", "PEMOGRAMAN BERGERAK", "PBTGM", "BASIS DATA", "CONVERSATION", "PKK", "SENI BUDAYA", "PENJAS", "IPA", "IPS" }));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ID SISWA");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("SEMESTER");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("BLOK");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("COURSE 1");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("COURSE 2");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("COURSE 3");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("COURSE 4");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("COURSE 5");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(text_id)
                            .addComponent(text_id_siswa2)
                            .addComponent(combo_semester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo_blok, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo1, 0, 326, Short.MAX_VALUE)
                            .addComponent(combo2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_id_siswa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_semester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_blok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(29, 41, 81));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jPanel16.setBackground(new java.awt.Color(29, 41, 81));
        jPanel16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("DATA MATA PELAJARAN");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(29, 41, 81));
        jPanel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        tbl_course.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "ID SISWA", "SEMESTER", "BLOK", "COURSE 1", "COURSE 2", "COURSE 3", "COURSE 4", "COURSE 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_course.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_courseMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_course);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(29, 41, 81));
        jPanel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        btn_save.setBackground(new java.awt.Color(0, 102, 153));
        btn_save.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_save.setForeground(new java.awt.Color(29, 41, 81));
        btn_save.setText("SAVE");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_logout2.setBackground(new java.awt.Color(0, 102, 153));
        btn_logout2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_logout2.setForeground(new java.awt.Color(29, 41, 81));
        btn_logout2.setText("LOGOUT");
        btn_logout2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logout2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(29, 41, 81));
        jButton1.setText("DELETE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_logout2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(365, 365, 365))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_save, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btn_logout2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Course", jPanel11);

        jPanel19.setBackground(new java.awt.Color(29, 41, 81));

        jPanel20.setBackground(new java.awt.Color(29, 41, 81));
        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jPanel21.setBackground(new java.awt.Color(29, 41, 81));
        jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("ID SISWA");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("SEMESTER");

        text_semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_semesterActionPerformed(evt);
            }
        });

        btn_search4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_search4.setText("SEARCH");
        btn_search4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_id_siswa3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_semester, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(btn_search4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(text_id_siswa3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_semester, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btn_search4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        text_id2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_id2ActionPerformed(evt);
            }
        });

        text_id_siswa5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_id_siswa5ActionPerformed(evt);
            }
        });

        text_score1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        text_score2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        text_score3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        text_score4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        text_score5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        text_course1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_course1ActionPerformed(evt);
            }
        });

        text_course5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_course5ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("ID");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("ID SISWA");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("SEMESTER");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("BLOK");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("COURSE 1");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("COURSE 2");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("COURSE 3");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("COURSE 4");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("COURSE 5");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(text_id2, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(text_id_siswa5)
                            .addComponent(text_semester2)
                            .addComponent(text_blok)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(text_course2)
                                    .addComponent(text_course1)
                                    .addComponent(text_course3)
                                    .addComponent(text_course4)
                                    .addComponent(text_course5, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(text_score1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(text_score5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                        .addComponent(text_score4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(text_score3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(text_score2, javax.swing.GroupLayout.Alignment.TRAILING)))))))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_id2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_id_siswa5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_semester2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_blok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_score1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_course1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_score2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_course2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_score3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_course3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_score4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_course4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_score5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_course5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(29, 41, 81));
        jPanel23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jPanel25.setBackground(new java.awt.Color(29, 41, 81));
        jPanel25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        tbl_score.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID SISWA", "SEMESTER", "BLOK", "COURSE 1", "SCORE 1", "COURSE 2", "SCORE 2", "COURSE 3", "SCORE 3", "COURSE 4", "SCORE 4", "COURSE 5", "SCORE 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, true, false, true, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_score.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_scoreMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_score);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(29, 41, 81));
        jPanel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        btn_save2.setBackground(new java.awt.Color(0, 102, 153));
        btn_save2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_save2.setForeground(new java.awt.Color(29, 41, 81));
        btn_save2.setText("SAVE");
        btn_save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save2ActionPerformed(evt);
            }
        });

        btn_update2.setBackground(new java.awt.Color(0, 102, 153));
        btn_update2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_update2.setForeground(new java.awt.Color(29, 41, 81));
        btn_update2.setText("UPDATE");

        btn_clear3.setBackground(new java.awt.Color(0, 102, 153));
        btn_clear3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_clear3.setForeground(new java.awt.Color(29, 41, 81));
        btn_clear3.setText("CLEAR");
        btn_clear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear3ActionPerformed(evt);
            }
        });

        btn_delete3.setBackground(new java.awt.Color(0, 102, 153));
        btn_delete3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_delete3.setForeground(new java.awt.Color(29, 41, 81));
        btn_delete3.setText("DELETE");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(btn_save2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_update2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btn_clear3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(btn_delete3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_clear3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_update2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_delete3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Score", jPanel19);

        jPanel27.setBackground(new java.awt.Color(29, 41, 81));

        jPanel28.setBackground(new java.awt.Color(29, 41, 81));
        jPanel28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jPanel14.setBackground(new java.awt.Color(29, 41, 81));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("ID SISWA");

        btn_search6.setBackground(new java.awt.Color(255, 255, 255));
        btn_search6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_search6.setText("SEARCH");
        btn_search6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(text_id_siswa6, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btn_search6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_search6, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(text_id_siswa6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(29, 41, 81));
        jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        lbl_rata_rata.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lbl_rata_rata.setForeground(new java.awt.Color(255, 255, 255));
        lbl_rata_rata.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_rata_rata.setText("RATA-RATA : 0");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_rata_rata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_rata_rata, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel31.setBackground(new java.awt.Color(29, 41, 81));
        jPanel31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        jPanel33.setBackground(new java.awt.Color(29, 41, 81));
        jPanel33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        tbl_marks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID SISWA", "SEMESTER", "BLOK", "COURSE 1", "SCORE 1", "COURSE 2", "SCORE 2", "COURSE 3", "SCORE 3", "COURSE 4", "SCORE 4", "COURSE 5", "SCORE 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, true, false, true, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_marks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_marksMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_marks);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
        );

        jPanel34.setBackground(new java.awt.Color(29, 41, 81));
        jPanel34.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 4, true));

        btn_print4.setBackground(new java.awt.Color(0, 102, 153));
        btn_print4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_print4.setForeground(new java.awt.Color(29, 41, 81));
        btn_print4.setText("PRINT");
        btn_print4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print4ActionPerformed(evt);
            }
        });

        btn_clear4.setBackground(new java.awt.Color(0, 102, 153));
        btn_clear4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_clear4.setForeground(new java.awt.Color(29, 41, 81));
        btn_clear4.setText("CLEAR");
        btn_clear4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear4ActionPerformed(evt);
            }
        });

        btn_logout4.setBackground(new java.awt.Color(0, 102, 153));
        btn_logout4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_logout4.setForeground(new java.awt.Color(29, 41, 81));
        btn_logout4.setText("LOGOUT");
        btn_logout4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logout4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btn_print4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_clear4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_logout4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_print4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btn_clear4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_logout4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Marks Sheet", jPanel27);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clear4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear4ActionPerformed
        // TODO add your handling code here:
        text_id_siswa6.setText(""); // Kosongkan field pencarian
    lbl_rata_rata.setText("RATA-RATA: 0"); // Reset label rata-rata

    DefaultTableModel model = (DefaultTableModel) tbl_marks.getModel();
    model.setRowCount(0); // Hapus semua data dari tabel
    }//GEN-LAST:event_btn_clear4ActionPerformed

    private void text_course5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_course5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_course5ActionPerformed

    private void text_id_siswa5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_id_siswa5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_id_siswa5ActionPerformed

    private void text_semesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_semesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_semesterActionPerformed

    private void btn_logout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logout2ActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Do you want to Logout now?", "Select", JOptionPane. YES_NO_OPTION);
        if (a == 0){
            this.dispose();
        }
    }//GEN-LAST:event_btn_logout2ActionPerformed

    private void text_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_idActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Do you want to Logout now?", "Select", JOptionPane. YES_NO_OPTION);
        if (a == 0){
            this.dispose();
        }
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        try {
            this.stat = k.getCon().prepareStatement("delete from students where id_siswa=?");
            stat.setInt(1, Integer.parseInt(text_id_siswa.getText()));
            stat.executeUpdate();
            refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        try {
            datasiswa sis =new datasiswa();
            this.stat = k.getCon().prepareStatement("update students set nama_siswa=?,"
                + "nis=?, tanggal_lahir=?, jenis_kelamin=?, email=?, no_handphone=?, no_hp_ayah=?, no_hp_ibu=?, alamat=? where id_siswa=?");
            stat.setString(1, sis.nama_siswa);
            stat.setString(2, sis.nis);
            stat.setString(3, sis.tanggal_lahir);
            stat.setString(4, sis.jenis_kelamin);
            stat.setString(5, sis.email);
            stat.setString(6, sis.no_handphone);
            stat.setString(7, sis.no_hp_ayah);
            stat.setString(8, sis.no_hp_ibu);
            stat.setString(9, sis.alamat);
            stat.setInt(10, Integer.parseInt(text_id_siswa.getText()));
            stat.executeUpdate();
            refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        try {
            datasiswa sis =new datasiswa();
            this.stat = k.getCon().prepareStatement("INSERT INTO students (nama_siswa, nis, tanggal_lahir, jenis_kelamin, email, no_handphone, no_hp_ayah, no_hp_ibu, alamat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stat.setString(1, sis.nama_siswa);
            stat.setString(2, sis.nis);
            stat.setString(3, sis.tanggal_lahir);
            stat.setString(4, sis.jenis_kelamin);
            stat.setString(5, sis.email);
            stat.setString(6, sis.no_handphone);
            stat.setString(7, sis.no_hp_ayah);
            stat.setString(8, sis.no_hp_ibu);
            stat.setString(9, sis.alamat);
            int pilihan = JOptionPane.showConfirmDialog(null,
                "Tanggal: "+sis.tanggal_lahir+
                "\nNama Siswa "+sis.nama_siswa+
                "\nNis"+sis.nis+
                "\nJenis Kelamin" +sis.jenis_kelamin+
                "\nEmail "+sis.email+
                "\nNo Handphone"+sis.no_handphone+
                "\nNo Hp Ayah" +sis.no_hp_ayah+
                "\nNo Hp Ibu" +sis.no_hp_ibu+
                "\nAlamat"+sis.alamat+"\n",
                "Tambahkan Data Siswa?",
                JOptionPane.YES_NO_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {
                this.stat.executeUpdate();
                refreshTable();
            } else if(pilihan == JOptionPane.NO_OPTION) {
                refreshTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void tbl_siswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_siswaMouseClicked
        // TODO add your handling code here:
        int row = tbl_siswa.getSelectedRow() ;
    DefaultTableModel model = (DefaultTableModel) tbl_siswa.getModel();
    
    text_id_siswa.setText(model.getValueAt(row, 0).toString());
    text_nama_siswa.setText(model.getValueAt(row, 1).toString());
    text_nis.setText(model.getValueAt(row, 2).toString());

    // Konversi Tanggal dari String ke java.util.Date
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(model.getValueAt(row, 3).toString());
        tanggal.setDate(date);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Format tanggal salah: " + e.getMessage());
    }

    combo_jk.setSelectedItem(model.getValueAt(row, 4).toString());
    text_email.setText(model.getValueAt(row, 5).toString());
    text_no_hp.setText(model.getValueAt(row, 6).toString());
    text_no_ayah.setText(model.getValueAt(row, 7).toString());
    text_no_ibu.setText(model.getValueAt(row, 8).toString());
    text_alamat.setText(model.getValueAt(row, 9).toString());

    }//GEN-LAST:event_tbl_siswaMouseClicked

    private void text_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_alamatActionPerformed

    private void combo_jkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_jkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_jkActionPerformed

    private void btn_logout4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logout4ActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Do you want to Logout now?", "Select", JOptionPane. YES_NO_OPTION);
        if (a == 0){
            this.dispose();
        }
        
    }//GEN-LAST:event_btn_logout4ActionPerformed

    private void combo_semesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_semesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_semesterActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
    try {
        // Ambil data dari form
        String idSiswa = text_id_siswa2.getText();
        String semester = combo_semester.getSelectedItem().toString();
        String blok = combo_blok.getSelectedItem().toString();
        String course1 = combo1.getSelectedItem().toString();
        String course2 = combo2.getSelectedItem().toString();
        String course3 = combo3.getSelectedItem().toString();
        String course4 = combo4.getSelectedItem().toString();
        String course5 = combo5.getSelectedItem().toString();

        // Validasi jika ada field yang kosong
        if (idSiswa.isEmpty() || semester.isEmpty() || blok.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pastikan semua data terisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Konfirmasi sebelum menyimpan data
        int pilihan = JOptionPane.showConfirmDialog(null,
            "Id Siswa: " + idSiswa +
            "\nSemester: " + semester +
            "\nBlok: " + blok +
            "\nCourse 1: " + course1 +
            "\nCourse 2: " + course2 +
            "\nCourse 3: " + course3 +
            "\nCourse 4: " + course4 +
            "\nCourse 5: " + course5 + "\n",
            "Tambahkan Data Pelajaran?",
            JOptionPane.YES_NO_OPTION);
        
        if (pilihan == JOptionPane.YES_OPTION) {
            // Query SQL
            String sql = "INSERT INTO courses (id_siswa, semester, blok, course1, course2, course3, course4, course5) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            // Persiapan query
            this.stat = k.getCon().prepareStatement(sql);
            stat.setString(1, idSiswa);
            stat.setString(2, semester);
            stat.setString(3, blok);
            stat.setString(4, course1);
            stat.setString(5, course2);
            stat.setString(6, course3);
            stat.setString(7, course4);
            stat.setString(8, course5);

            // Eksekusi query
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_btn_saveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                      
    try {
        // Pastikan ID tidak kosong
        if (text_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Konfirmasi sebelum menghapus
        int konfirmasi = JOptionPane.showConfirmDialog(this, 
                "Apakah kamu yakin ingin menghapus data ini?", 
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            // Buat query DELETE
            this.stat = k.getCon().prepareStatement("DELETE FROM courses WHERE id_course = ?");
            stat.setInt(1, Integer.parseInt(text_id.getText()));

            // Eksekusi query
            int hasil = stat.executeUpdate();
            
            if (hasil > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();  // Refresh tampilan tabel
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_courseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_courseMouseClicked
        // TODO add your handling code here:                                                                    
    int selectedRow = tbl_course.getSelectedRow(); 
    if (selectedRow != -1) { // Pastikan ada baris yang dipilih
        try {
            // Ambil model dari tabel
            DefaultTableModel model = (DefaultTableModel) tbl_course.getModel();

            // Ambil data dari tabel dan set ke field
            text_id.setText(model.getValueAt(selectedRow, 0).toString());
            text_id_siswa2.setText(model.getValueAt(selectedRow, 1).toString());
            
            combo_semester.setSelectedItem(model.getValueAt(selectedRow, 2) != null ? model.getValueAt(selectedRow, 2).toString() : "");
            combo_blok.setSelectedItem(model.getValueAt(selectedRow, 3) != null ? model.getValueAt(selectedRow, 3).toString() : "");
            combo1.setSelectedItem(model.getValueAt(selectedRow, 4) != null ? model.getValueAt(selectedRow, 4).toString() : "");
            combo2.setSelectedItem(model.getValueAt(selectedRow, 5) != null ? model.getValueAt(selectedRow, 5).toString() : "");
            combo3.setSelectedItem(model.getValueAt(selectedRow, 6) != null ? model.getValueAt(selectedRow, 6).toString() : "");
            combo4.setSelectedItem(model.getValueAt(selectedRow, 7) != null ? model.getValueAt(selectedRow, 7).toString() : "");
            combo5.setSelectedItem(model.getValueAt(selectedRow, 8) != null ? model.getValueAt(selectedRow, 8).toString() : "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    }//GEN-LAST:event_tbl_courseMouseClicked

    private void text_course1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_course1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_course1ActionPerformed

    private void btn_search4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search4ActionPerformed

        String id_siswa = text_id_siswa3.getText();  // Ambil ID Siswa
        String semester = text_semester.getText();  // Ambil Semester

// Query untuk mencari mata pelajaran berdasarkan ID Siswa dan Semester
        String query = "SELECT s.id_siswa, s.nama_siswa, c.course1, c.course2, c.course3, c.course4, c.course5, c.semester, c.blok "
                        + "FROM courses c "
                        + "JOIN students s ON c.id_siswa = s.id_siswa "
                        + "WHERE c.id_siswa = ? AND c.semester = ?";

        try (PreparedStatement pst = k.getCon().prepareStatement(query)) {
                pst.setString(1, id_siswa);   // Set ID Siswa
                pst.setString(2, semester);   // Set Semester

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            // Set data ke field yang sesuai
            text_id_siswa5.setText(rs.getString("id_siswa")); // Menampilkan ID Siswa
            text_id_siswa5.setText(rs.getString("nama_siswa")); // Menampilkan Nama Siswa
            text_semester2.setText(rs.getString("semester")); // Menampilkan Semester
            text_blok.setText(rs.getString("blok"));  // Menampilkan Blok

            // Isi data Course ke dalam form sesuai dengan data dari query
            text_course1.setText(rs.getString("course1")); // Course 1
            text_course2.setText(rs.getString("course2")); // Course 2
            text_course3.setText(rs.getString("course3")); // Course 3
            text_course4.setText(rs.getString("course4")); // Course 4
            text_course5.setText(rs.getString("course5")); // Course 5
        } else {
            JOptionPane.showMessageDialog(this, "Data siswa tidak ditemukan untuk semester ini.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }//GEN-LAST:event_btn_search4ActionPerformed

    private void btn_save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save2ActionPerformed
        try {
        // Ambil data input
        String id_siswa = text_id_siswa5.getText().trim();  
        String semester = text_semester2.getText().trim();
        String block = text_blok.getText().trim();
        String id_course1 = text_course1.getText().trim();
        String score1 = text_score1.getText().trim();
        String id_course2 = text_course2.getText().trim();
        String score2 = text_score2.getText().trim();
        String id_course3 = text_course3.getText().trim();
        String score3 = text_score3.getText().trim();
        String id_course4 = text_course4.getText().trim();
        String score4 = text_score4.getText().trim();
        String id_course5 = text_course5.getText().trim();
        String score5 = text_score5.getText().trim();

        // Query SQL untuk insert data ke tabel marks
        String query = "INSERT INTO marks (id_siswa, semester, block, id_course1, score1, id_course2, score2, id_course3, score3, id_course4, score4, id_course5, score5) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = k.getCon().prepareStatement(query)) {
            pst.setString(1, id_siswa);
            pst.setString(2, semester);
            pst.setString(3, block);
            pst.setString(4, id_course1); 
            pst.setString(5, score1);
            pst.setString(6, id_course2);
            pst.setString(7, score2);
            pst.setString(8, id_course3);
            pst.setString(9, score3);
            pst.setString(10, id_course4);
            pst.setString(11, score4);
            pst.setString(12, id_course5);
            pst.setString(13, score5);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke tabel marks.");
                refreshTable(); // 🔥 Panggil refreshTable agar tabel diperbarui
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan SQL: " + e.getMessage());
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan umum: " + e.getMessage());
    }

    }//GEN-LAST:event_btn_save2ActionPerformed

    private void btn_clear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear3ActionPerformed
       clearForm();
    }//GEN-LAST:event_btn_clear3ActionPerformed

    private void tbl_scoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_scoreMouseClicked
        // TODO add your handling code here:
        text_id2.setText(model.getValueAt(tbl_score.getSelectedRow(), 0).toString());
        text_id_siswa5.setText(model.getValueAt(tbl_score.getSelectedRow(), 1).toString());
        text_semester2.setText(model.getValueAt(tbl_score.getSelectedRow(), 2).toString());
        text_blok.setText(model.getValueAt(tbl_score.getSelectedRow(), 3).toString());
        text_course1.setText(model.getValueAt(tbl_score.getSelectedRow(), 4).toString());
        text_course2.setText(model.getValueAt(tbl_score.getSelectedRow(), 6).toString());
        text_course3.setText(model.getValueAt(tbl_score.getSelectedRow(), 8).toString());
        text_course4.setText(model.getValueAt(tbl_score.getSelectedRow(), 10).toString());
        text_course5.setText(model.getValueAt(tbl_score.getSelectedRow(), 12).toString());
        text_score1.setText(model.getValueAt(tbl_score.getSelectedRow(), 5).toString());
        text_score2.setText(model.getValueAt(tbl_score.getSelectedRow(), 7).toString());
        text_score3.setText(model.getValueAt(tbl_score.getSelectedRow(), 9).toString());
        text_score4.setText(model.getValueAt(tbl_score.getSelectedRow(), 11).toString());
        text_score5.setText(model.getValueAt(tbl_score.getSelectedRow(), 13).toString());
    }//GEN-LAST:event_tbl_scoreMouseClicked

    private void text_id2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_id2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_id2ActionPerformed

    private void btn_search6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search6ActionPerformed
                                        
    String namaSiswa = text_id_siswa6.getText().trim(); // Ambil inputan nama siswa
    DefaultTableModel model = (DefaultTableModel) tbl_marks.getModel();
    model.setRowCount(0); // Kosongkan tabel sebelum menampilkan data baru

    if (namaSiswa.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Masukkan nama siswa terlebih dahulu!");
        return;
    }

    try {
        Connection con = koneksi.getCon(); // Pastikan koneksi tetap aktif
        String sql = "SELECT * FROM marks WHERE id_siswa LIKE ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, "%" + namaSiswa + "%");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Object[] rowData = {
                rs.getInt("id_mark"),
                rs.getString("id_siswa"),
                rs.getInt("semester"),
                rs.getInt("block"),
                rs.getString("id_course1"),
                rs.getInt("score1"),
                rs.getString("id_course2"),
                rs.getInt("score2"),
                rs.getString("id_course3"),
                rs.getInt("score3"),
                rs.getString("id_course4"),
                rs.getInt("score4"),
                rs.getString("id_course5"),
                rs.getInt("score5")
            };
            model.addRow(rowData);
        }

        rs.close();
        pst.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data: " + e.getMessage());
    }
    }//GEN-LAST:event_btn_search6ActionPerformed

    private void tbl_marksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_marksMouseClicked

    raportcardstudent reportCardForm = new raportcardstudent(); // Pastikan ini adalah instance yang sesuai
    DefaultTableModel modelReportCard = (DefaultTableModel) reportCardForm.getTblRaportCard().getModel();


    DefaultTableModel modelMarks = (DefaultTableModel) tbl_marks.getModel();
    int rowCount = modelMarks.getRowCount();
    double total = 0;
    int jumlahScore = 0;

    for (int i = 0; i < rowCount; i++) {
       String subject1 = modelMarks.getValueAt(i, 4).toString(); // Kolom ke-4 (Subject)
       String subject2 = modelMarks.getValueAt(i, 6).toString(); // Kolom ke-6 (Subject)
       String subject3 = modelMarks.getValueAt(i, 8).toString(); // Kolom ke-8 (Subject)
       String subject4 = modelMarks.getValueAt(i, 10).toString(); // Kolom ke-10 (Subject)
       String subject5 = modelMarks.getValueAt(i, 12).toString(); // Kolom ke-12 (Subject)


        // Ambil rata-rata dari 5 score (kolom 5, 7, 9, 11, 13)
        int totalScore = 0;
        int count = 0;
        for (int j = 5; j <= 13; j += 2) { // Score ada di kolom 5,7,9,11,13
            Object nilaiObj = modelMarks.getValueAt(i, j);
            if (nilaiObj != null) {
                try {
                    int nilai = Integer.parseInt(nilaiObj.toString());
                    totalScore += nilai;
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing nilai: " + nilaiObj);
                }
            }
        }

        double averageScore = (count > 0) ? (double) totalScore / count : 0;
        total += averageScore;
        jumlahScore++;

        // Tambahkan ke tbl_raportcard
        modelReportCard.addRow(new Object[]{(i + 1), subject1, subject2, subject3, subject4, subject5,  String.format("%.2f", averageScore)});
    }

    // Hitung rata-rata keseluruhan
    double overallAverage = jumlahScore > 0 ? total / jumlahScore : 0;
    lbl_rata_rata.setText(String.format("%.2f", overallAverage));

    }//GEN-LAST:event_tbl_marksMouseClicked

    private void btn_print4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print4ActionPerformed
    int selectedRow = tbl_marks.getSelectedRow(); // Ambil baris yang dipilih

    if (selectedRow != -1) {
        // Ambil data dari tabel tbl_marks
        String namaSiswa = tbl_marks.getValueAt(selectedRow, 1).toString(); // Kolom ID_Siswa
        String semester = tbl_marks.getValueAt(selectedRow, 2).toString(); // Kolom Semester
        
        String[] subjects = {
            tbl_marks.getValueAt(selectedRow, 4).toString(), // id_course1
            tbl_marks.getValueAt(selectedRow, 6).toString(), // id_course2
            tbl_marks.getValueAt(selectedRow, 8).toString(), // id_course3
            tbl_marks.getValueAt(selectedRow, 10).toString(), // id_course4
            tbl_marks.getValueAt(selectedRow, 12).toString()  // id_course5
        };

        String[] scores = {
            tbl_marks.getValueAt(selectedRow, 5).toString(), // score1
            tbl_marks.getValueAt(selectedRow, 7).toString(), // score2
            tbl_marks.getValueAt(selectedRow, 9).toString(), // score3
            tbl_marks.getValueAt(selectedRow, 11).toString(), // score4
            tbl_marks.getValueAt(selectedRow, 13).toString()  // score5
        };

        // Hitung rata-rata nilai
        double total = 0;
        int count = 0;
        for (String score : scores) {
            total += Double.parseDouble(score);
            count++;
        }
        double avgScore = total / count;

        // Pindah ke RaportCardStudent
        raportcardstudent report = new raportcardstudent();
        report.setVisible(true);

        // Isi field di RaportCardStudent
        report.setStudentName(namaSiswa);
        report.setSemester(semester);
        report.setAverageScore(avgScore);

        // Isi tabel tbl_raportcard
        DefaultTableModel model = (DefaultTableModel) report.getTblRaportCard().getModel();
        model.setRowCount(0); // Kosongkan tabel sebelum isi

        for (int i = 0; i < subjects.length; i++) {
            model.addRow(new Object[]{subjects[i], scores[i]});
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih data siswa terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }

    }//GEN-LAST:event_btn_print4ActionPerformed

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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_clear3;
    private javax.swing.JButton btn_clear4;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_delete3;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_logout2;
    private javax.swing.JButton btn_logout4;
    private javax.swing.JButton btn_print4;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_save2;
    private javax.swing.JButton btn_search4;
    private javax.swing.JButton btn_search6;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_update2;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JComboBox<String> combo2;
    private javax.swing.JComboBox<String> combo3;
    private javax.swing.JComboBox<String> combo4;
    private javax.swing.JComboBox<String> combo5;
    private javax.swing.JComboBox<String> combo_blok;
    private javax.swing.JComboBox<String> combo_jk;
    private javax.swing.JComboBox<String> combo_semester;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_rata_rata;
    private com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JTable tbl_course;
    private javax.swing.JTable tbl_marks;
    private javax.swing.JTable tbl_score;
    private javax.swing.JTable tbl_siswa;
    private javax.swing.JTextField text_alamat;
    private javax.swing.JTextField text_blok;
    private javax.swing.JTextField text_course1;
    private javax.swing.JTextField text_course2;
    private javax.swing.JTextField text_course3;
    private javax.swing.JTextField text_course4;
    private javax.swing.JTextField text_course5;
    private javax.swing.JTextField text_email;
    private javax.swing.JTextField text_id;
    private javax.swing.JTextField text_id2;
    private javax.swing.JTextField text_id_siswa;
    private javax.swing.JTextField text_id_siswa2;
    private javax.swing.JTextField text_id_siswa3;
    private javax.swing.JTextField text_id_siswa5;
    private javax.swing.JTextField text_id_siswa6;
    private javax.swing.JTextField text_nama_siswa;
    private javax.swing.JTextField text_nis;
    private javax.swing.JTextField text_no_ayah;
    private javax.swing.JTextField text_no_hp;
    private javax.swing.JTextField text_no_ibu;
    private javax.swing.JTextField text_score1;
    private javax.swing.JTextField text_score2;
    private javax.swing.JTextField text_score3;
    private javax.swing.JTextField text_score4;
    private javax.swing.JTextField text_score5;
    private javax.swing.JTextField text_semester;
    private javax.swing.JTextField text_semester2;
    // End of variables declaration//GEN-END:variables
}
