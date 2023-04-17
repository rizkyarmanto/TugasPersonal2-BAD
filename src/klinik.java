import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class klinik extends JFrame implements ActionListener {

    private JLabel labelNama, labelAlamat, labelNIK, labelTglLahir;
    private JTextField fieldNama, fieldAlamat, fieldNIK, fieldTglLahir;
    private JButton buttonSimpan, buttonTampilkan;
    private String[][] data = new String[100][4];
    private int count = 0;

    // Set untuk menyimpan NIK
    private Set<String> nikSet = new HashSet<>();

    public klinik() {
        // Membuat frame
        setTitle("Input Data");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat label
        labelNama = new JLabel("Nama:");
        labelAlamat = new JLabel("Alamat:");
        labelNIK = new JLabel("NIK:");
        labelTglLahir = new JLabel("Tanggal Lahir (YYYY-MMM-DD):");

        // Membuat field input
        fieldNama = new JTextField(20);
        fieldAlamat = new JTextField(20);
        fieldNIK = new JTextField(20);
        fieldTglLahir = new JTextField(20);

        // Membuat tombol simpan
        buttonSimpan = new JButton("Simpan");
        buttonSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mengecek apakah NIK sudah ada sebelumnya
                String nik = fieldNIK.getText();
                if (nikSet.contains(nik)) {
                    JOptionPane.showMessageDialog(null, "NIK sudah terdaftar!");
                    return;
                }

                // Menambahkan NIK ke Set
                nikSet.add(nik);

                // Mendapatkan tanggal lahir dari input
                String tglLahirString = fieldTglLahir.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
                Date tglLahir = null;
                try {
                    tglLahir = dateFormat.parse(tglLahirString);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Format tanggal salah!");
                    return;
                }
            }
        });

        buttonTampilkan = new JButton("Tampilkan Data");
        buttonTampilkan.addActionListener(this);

        // Membuat panel
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(labelNama);
        panel.add(fieldNama);
        panel.add(labelAlamat);
        panel.add(fieldAlamat);
        panel.add(labelNIK);
        panel.add(fieldNIK);
        panel.add(labelTglLahir);
        panel.add(fieldTglLahir);
        panel.add(new JLabel()); // Cell kosong

        JPanel panelButton = new JPanel(new FlowLayout());
        panelButton.add(buttonSimpan);
        panelButton.add(buttonTampilkan);

        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.add(panel, BorderLayout.CENTER);
        panelMain.add(panelButton, BorderLayout.SOUTH);

        add(panelMain);
        setVisible(true);
    }

    public static void main(String[] args) {
        new klinik();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSimpan) {
            data[count][0] = labelNama.getText();
            data[count][1] = labelAlamat.getText();
            data[count][2] = labelNIK.getText();
            data[count][3] = labelTglLahir.getText();
            count++;
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        }

        if (e.getSource() == buttonTampilkan) {
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
};
