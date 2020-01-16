/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulb.lisa.infoh400.labs2020.view;

import be.fedict.commons.eid.client.CancelledException;
import be.fedict.commons.eid.client.FileType;
import be.fedict.commons.eid.consumer.Identity;
import be.fedict.commons.eid.consumer.tlv.TlvParser;
import be.fedict.commons.eid.jca.BeIDProvider;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.smartcardio.CardException;
import org.apache.commons.io.FileUtils;
import ulb.lisa.infoh400.labs2020.GlobalConfig;
import ulb.lisa.infoh400.labs2020.controller.DICOMServices;
import ulb.lisa.infoh400.labs2020.controller.PatientJpaController;
import ulb.lisa.infoh400.labs2020.controller.PersonJpaController;
import ulb.lisa.infoh400.labs2020.controller.exceptions.NonexistentEntityException;
import ulb.lisa.infoh400.labs2020.model.Patient;

/**
 * Popup window with a form for adding & editing a Patient.
 *
 * @author Adrien Foucart
 */
public class AddPatientWindow extends javax.swing.JFrame {

    // EntityManager & Controllers are needed to persist changes in the database.
    private final EntityManagerFactory emfac = Persistence.createEntityManagerFactory("infoh400_PU");
    private final PatientJpaController patientCtrl = new PatientJpaController(emfac);
    private final PersonJpaController personCtrl = new PersonJpaController(emfac);

    private Patient patient = null;

    /**
     * Creates new form AddPatientWindow
     */
    public AddPatientWindow() {
        initComponents();
    }

    /**
     * Set the current person & patient, & fill the form fields.
     *
     * @param patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;

        addPersonPanel.setPerson(patient.getIdperson());
        phonenumberTextField.setText(patient.getPhonenumber());
        statusComboBox.setSelectedItem(patient.getStatus());
    }

    /**
     * Read the from fields, update patient & get the Patient object.
     *
     * @return
     */
    public Patient getPatient() {
        updatePatient();

        return patient;
    }

    /**
     * Update the Patient object according to the content of the form fields.
     */
    public void updatePatient() {
        if (patient == null) {
            patient = new Patient();
        }

        patient.setIdperson(addPersonPanel.getPerson());
        patient.setPhonenumber(phonenumberTextField.getText());
        patient.setStatus((String) statusComboBox.getSelectedItem());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addPersonPanel = new ulb.lisa.infoh400.labs2020.view.AddPersonPanel();
        jLabel2 = new javax.swing.JLabel();
        phonenumberTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        cFindButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cFindResultTextePane = new javax.swing.JTextPane();
        FHIRSearchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add Patient");

        jLabel2.setText("Phone Number:");

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Status:");

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive", "Banned" }));

        cFindButton.setText("Find in PACS");
        cFindButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cFindButtonActionPerformed(evt);
            }
        });

        cFindResultTextePane.setEnabled(false);
        jScrollPane1.setViewportView(cFindResultTextePane);

        FHIRSearchButton.setText("FHIR Search");
        FHIRSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FHIRSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(phonenumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(40, 40, 40)
                                .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addPersonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FHIRSearchButton)
                    .addComponent(cFindButton)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FHIRSearchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cFindButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addPersonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(phonenumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Close the window when the user clicks on the cancel button.
     *
     * @param evt
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Save new or edited patient to the database.
     *
     * @param evt
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // Update Patient object from the form fields
        updatePatient();
        
        if( GlobalConfig.cards.hasBeIDCards() ){
            try {
                Security.addProvider(new BeIDProvider());
                KeyStore keyStore = KeyStore.getInstance("BeID");
                keyStore.load(null);
                PrivateKey sigPK = (PrivateKey) keyStore.getKey("Authentication", null);
                Signature signature = Signature.getInstance("SHA1withRSA");
                signature.initSign(sigPK);
                String toBeSigned = GlobalConfig.CONSENT_FORM.replace("[PATIENT]", patient.getIdperson().toString());
                signature.update(toBeSigned.getBytes());
                byte[] signatureValue = signature.sign();
                
                // Check consent
                Certificate cert = keyStore.getCertificate("Authentication");
                Signature verifSig = Signature.getInstance("SHA1withRSA");
                verifSig.initVerify(cert);
                verifSig.update(toBeSigned.getBytes());
                if(!verifSig.verify(signatureValue)){
                    System.out.println("Invalid PIN code. Consent form not signed.");
                    return;
                }
                else{
                    String rnn = TlvParser.parse(GlobalConfig.cards.getOneBeIDCard().readFile(FileType.Identity), Identity.class).getNationalNumber();
                    FileUtils.writeByteArrayToFile(new File(GlobalConfig.CONSENT_FORM_DIRECTORY, "Patient_"+rnn+".txt"), toBeSigned.getBytes());
                    FileUtils.writeByteArrayToFile(new File(GlobalConfig.CONSENT_FORM_DIRECTORY, "Patient_"+rnn+".sig"), signatureValue);
                }
            } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException | InvalidKeyException | SignatureException ex) {
                Logger.getLogger(AddPatientWindow.class.getName()).log(Level.SEVERE, null, ex);
                return;
            } catch (CancelledException | CardException | InterruptedException ex) {
                Logger.getLogger(AddPatientWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Create person if necessary:
        if (patient.getIdperson().getIdperson() == null) {
            personCtrl.create(patient.getIdperson());
        }
        // Create patient if necessary:
        if (patient.getIdpatient() == null) {
            patientCtrl.create(patient);
        }

        // Save changes to person & patient.
        try {
            personCtrl.edit(patient.getIdperson());
            patientCtrl.edit(patient);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AddPatientWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddPatientWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cFindButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cFindButtonActionPerformed
        updatePatient();

        DICOMServices.doCFind(patient, new CFindResultDisplayer());
        if (cFindResultTextePane.getText().isEmpty()) {
            cFindResultTextePane.setText("No studies in PACS");
        }
    }//GEN-LAST:event_cFindButtonActionPerformed

    private void FHIRSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FHIRSearchButtonActionPerformed
        FHIRSearchWindow popup = new FHIRSearchWindow();
        popup.setVisible(true);
    }//GEN-LAST:event_FHIRSearchButtonActionPerformed

    public class CFindResultDisplayer {

        private List<String> studyDates = new ArrayList();

        public void addResult(String result) {
            studyDates.add(result);
            String text = studyDates.size() + " studies :\n";
            for (String sd : studyDates) {
                text += sd + "\n";
            }
            cFindResultTextePane.setText(text);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FHIRSearchButton;
    private ulb.lisa.infoh400.labs2020.view.AddPersonPanel addPersonPanel;
    private javax.swing.JButton cFindButton;
    private javax.swing.JTextPane cFindResultTextePane;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField phonenumberTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> statusComboBox;
    // End of variables declaration//GEN-END:variables
}
