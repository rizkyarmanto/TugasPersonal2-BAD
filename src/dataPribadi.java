import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class dataPribadi extends JFrame implements ActionListener {
    private JTextField txtNama, txtAlamat, txtNik, txtTanggalLahir;
    private JButton btnSimpan, btnTampilkan, btnTambah, btnUpdate, btnHapus, btnSebelumnya, btnSesudahnya;
    private String[][] data = new String[100][4];
    private int count = 0;

    // Set untuk menyimpan NIK
    private Set<String> nikSet = new HashSet<>();

    public dataPribadi() {
        setTitle("Data Pribadi");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblNama = new JLabel("Nama:");
        txtNama = new JTextField(20);

        JLabel lblAlamat = new JLabel("Alamat:");
        txtAlamat = new JTextField(20);

        JLabel lblNik = new JLabel("NIK:");
        txtNik = new JTextField(20);

        JLabel lblTanggalLahir = new JLabel("Tanggal Lahir:");
        txtTanggalLahir = new JTextField(20);

        btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(this);

        btnTampilkan = new JButton("Tampilkan Data");
        btnTampilkan.addActionListener(this);

        JPanel panelInput = new JPanel(new GridLayout(4,2));
        panelInput.add(lblNama);
        panelInput.add(txtNama);
        panelInput.add(lblAlamat);
        panelInput.add(txtAlamat);
        panelInput.add(lblNik);
        panelInput.add(txtNik);
        panelInput.add(lblTanggalLahir);
        panelInput.add(txtTanggalLahir);

        JPanel panelButton1 = new JPanel(new FlowLayout());
        panelButton1.add(btnTambah);
        panelButton1.add(btnUpdate);
        panelButton1.add(btnHapus);

        JPanel panelButton2 = new JPanel(new FlowLayout());
        panelButton2.add(btnSebelumnya);
        panelButton2.add(btnSesudahnya);

        JPanel panelButton3 = new JPanel(new FlowLayout());
        panelButton3.add(btnSimpan);
        panelButton3.add(btnTampilkan);

        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.add(panelInput, BorderLayout.CENTER);
        panelMain.add(panelButton3, BorderLayout.SOUTH);

        add(panelMain);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Mengecek apakah NIK sudah ada sebelumnya
        String nik = txtNik.getText();
        if (nikSet.contains(nik)) {
            JOptionPane.showMessageDialog(null, "NIK sudah terdaftar!");
            return;
        }

        // Menambahkan NIK ke Set
        nikSet.add(nik);

        // Mendapatkan tanggal lahir dari input
        String tglLahirString = txtTanggalLahir.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MMM-DD");
        Date tglLahir = null;
        try {
            tglLahir = dateFormat.parse(tglLahirString);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Format tanggal salah!");
            return;
        }
        if(e.getSource() == btnSimpan) {
            data[count][0] = txtNama.getText();
            data[count][1] = txtAlamat.getText();
            data[count][2] = txtNik.getText();
            data[count][3] = txtTanggalLahir.getText();
            count++;
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        }

        if(e.getSource() == btnTampilkan) {
            if (count == 0) {
                JOptionPane.showMessageDialog(this, "Belum ada data yang disimpan!");
            } else {
                String[][] rowData = new String[count][4];
                for (int i = 0; i < count; i++) {
                    rowData[i][0] = data[i][0];
                    rowData[i][1] = data[i][1];
                    rowData[i][2] = data[i][2];
                    rowData[i][3] = data[i][3];
                }

                String[] columnNames = {"Nama", "Alamat", "NIK", "Tanggal Lahir"};
                JTable table = new JTable(rowData, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                JPanel panelTable = new JPanel(new BorderLayout());
                panelTable.add(scrollPane, BorderLayout.CENTER);
                JOptionPane.showMessageDialog(this, panelTable, "Data Pribadi", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new dataPribadi();
    }
}