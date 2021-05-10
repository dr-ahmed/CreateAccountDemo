package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import presenter.NewClinicPresenter;

public class NewClinicFrame extends JFrame implements ActionListener {

    private JPanel bigPanel, namePanel, datePanel, cnamPanel, consultationPricePanel, btnPanel;
    private JLabel mainLabel;
    private JTextField nameTxtField, consultationPriceTxtField;
    private DateTimePicker dateTimePicker;
    private JRadioButton yesRdBtn, noRdBtn;
    private JButton createAccountBtn, cancelBtn;

    public NewClinicFrame() {
        initComponents();
        addComponents();
    }

    private void addComponents() {
        getContentPane().add(mainLabel, "North");

        bigPanel = new JPanel();
        bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));
        bigPanel.add(namePanel);
        bigPanel.add(datePanel);
        bigPanel.add(cnamPanel);
        bigPanel.add(consultationPricePanel);
        getContentPane().add(bigPanel);

        getContentPane().add(btnPanel, "South");
    }

    private void initComponents() {
        mainLabel = new JLabel("Création de clinique");
        mainLabel.setHorizontalAlignment(JLabel.CENTER);

        namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel nameLabel = new JLabel("Nom");
        nameTxtField = new JTextField();
        nameTxtField.setPreferredSize(new Dimension(210, 25));

        namePanel.add(nameLabel);
        namePanel.add(nameTxtField);

        datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        Date date = new Date();

        dateTimePicker = new DateTimePicker();
        dateTimePicker.setFormats(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM));
        dateTimePicker.setTimeFormat(DateFormat.getTimeInstance(DateFormat.MEDIUM));

        dateTimePicker.setDate(date);

        JLabel creationDateLabel = new JLabel("Date de création");

        datePanel.add(creationDateLabel);
        datePanel.add(dateTimePicker);

        cnamPanel = new JPanel();
        cnamPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel cnamLabel = new JLabel("CNAM");

        yesRdBtn = new JRadioButton("Oui");
        noRdBtn = new JRadioButton("Non");
        ButtonGroup group = new ButtonGroup();
        group.add(yesRdBtn);
        group.add(noRdBtn);

        cnamPanel.add(cnamLabel);
        cnamPanel.add(yesRdBtn);
        cnamPanel.add(noRdBtn);

        consultationPricePanel = new JPanel();
        consultationPricePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel consultationPriceLabel = new JLabel("Prix de consultation");
        consultationPriceTxtField = new JTextField();
        consultationPriceTxtField.setPreferredSize(new Dimension(70, 25));

        consultationPricePanel.add(consultationPriceLabel);
        consultationPricePanel.add(consultationPriceTxtField);

        btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        createAccountBtn = new JButton("Créer");
        createAccountBtn.addActionListener(this);
        btnPanel.add(createAccountBtn);

        cancelBtn = new JButton("Annuler");
        cancelBtn.addActionListener(this);
        btnPanel.add(cancelBtn);
    }

    String getClinicName() {
        return nameTxtField.getText();
    }

    java.sql.Date getClinicStartDate() {
        return new java.sql.Date(dateTimePicker.getDate().getTime());
    }

    boolean getCNAMState() {
        return yesRdBtn.isSelected() ? true : false;
    }

    float getConsultationPriceValue() {
        return Float.valueOf(consultationPriceTxtField.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn)
            dispose();
        else if (e.getSource() == createAccountBtn) {
            NewClinicPresenter newClinicPresenter = new NewClinicPresenter(getClinicName(), getClinicStartDate(),
                    getCNAMState(), getConsultationPriceValue());
            newClinicPresenter.setClinicFrame(this);
            newClinicPresenter.addNewClinic();
        }
    }

    public void connectionFailed() {
        JOptionPane.showMessageDialog(null, "La connection au serveur est échouée!", "Opération échouée",
                JOptionPane.ERROR_MESSAGE, null);
    }

    public void displayMessage(String info) {
        if (info != null)
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite!\n" + info, "Insertion échouée",
                    JOptionPane.ERROR_MESSAGE, null);
        else
            JOptionPane.showMessageDialog(null, "Les informations de la clinique ont été ajoutées avec succès",
                    "Opération réussie", JOptionPane.INFORMATION_MESSAGE, null);
    }
}