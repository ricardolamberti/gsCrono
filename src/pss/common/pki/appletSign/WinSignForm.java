package  pss.common.pki.appletSign;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.KeyStore;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import be.cardon.sign.shared.DialogBox;
import be.cardon.sign.shared.GUIUtils;
import be.cardon.sign.shared.KeyStoreEntry;
import be.cardon.sign.shared.KeyStoreTable;
import be.cardon.sign.shared.KeyStoreUtils;
import be.cardon.sign.shared.TreeEntry;
import be.cardon.utils.Debug;


public class WinSignForm extends JDialog implements TreeSelectionListener{
  private JTree providerTree;
  private Box providerRightPanel;
  private KeyStoreTable keyStoreTable; //current visible table
  private final static String MS_CRYPTO_ID = "mscrypto";
  private final static String JKS_ID = "jks";  //  @jve:decl-index=0:
  private final static String PKCS12_ID = "pkcs12";
  private final static String PKCS11_ID = "pkcs11";
   JFrame wizardFrame;
  private JButton jOK = null;  //  @jve:decl-index=0:visual-constraint="725,175"
  private String label = "";
  KeyStoreEntry signingKeyStoreEntry = null;
  boolean ok = false;
  /**
   * This method initializes 
   * 
   */
  public WinSignForm(JFrame parent) {
    super(parent,  true);
    wizardFrame = parent;
   	initialize();
  }
  public boolean isOk() {
  	return ok;
  }

  /**
   * This method initializes this
   * 
   */
  private void initialize() {
        this.setSize(new Dimension(662, 326));
        this.setTitle("Seleccione el certificado con el que desea firmar");
        this.add(getJOK(), BorderLayout.SOUTH);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        	public void windowClosing(java.awt.event.WindowEvent e) {
        		
        		dispose();
        	}
        });

        JTree providerTree = providerTree();
        this.providerTree = providerTree;
        GUIUtils.setMaxSizeUnfinite(this.providerTree);
        
        //scroll with tree
        JScrollPane providerScroll = new JScrollPane(providerTree);
        //providerScroll.setPreferredSize(new Dimension(300,300));
        
        this.providerRightPanel = Box.createVerticalBox();
        this.providerRightPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, providerScroll, this.providerRightPanel);
        splitPane.setPreferredSize(new Dimension(500,300));
        splitPane.setAlignmentX(0.0f);
        splitPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        GUIUtils.setMaxSizeUnfinite(splitPane);
        
        this.add(splitPane);
 		
  }


  /**
   * This method initializes jOK	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJOK() {
  	if (jOK==null) {
  		jOK=new JButton();
  		jOK.setText("Siguiente >");
  		jOK.setDefaultCapable(true);
  		jOK.addActionListener(new java.awt.event.ActionListener() {
  			public void actionPerformed(java.awt.event.ActionEvent e) {
  				seleccionoFirma();
  			}
  		});
  	}
  	
  	return jOK;
  }

  void seleccionoFirma() {
	  if(this.keyStoreTable!=null){
	    KeyStoreEntry kse = this.keyStoreTable.getSelectedEntry();
	    if(kse!=null){
	        signingKeyStoreEntry =kse;
  				ok = true;
  				dispose();
          return;
	    }
		}
		DialogBox.showWarningBox(wizardFrame, "Por favor seleccione una firma.");
		return;
  }

	public String getLabel() {
		return label;
	}

	
	public void setLabel(String label) {
		this.label=label;
	}
     
  private JTree providerTree(){
    DefaultMutableTreeNode top = new DefaultMutableTreeNode(new TreeEntry("Proveedores", "root"));
    if(be.cardon.utils.OperatingSystem.isWindows()){
        top.add(new DefaultMutableTreeNode(new TreeEntry("Windows repositorio", MS_CRYPTO_ID)));
    }
    top.add(new DefaultMutableTreeNode(new TreeEntry("Java repositorio", JKS_ID)));
    top.add(new DefaultMutableTreeNode(new TreeEntry("PKCS#12 Archivo", PKCS12_ID)));
    top.add(new DefaultMutableTreeNode(new TreeEntry("PKCS#11 DLL Libreria", PKCS11_ID)));
    
    JTree provTree = new JTree(top);
    provTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    provTree.addTreeSelectionListener(this);
    
    return provTree;
    
}

public void valueChanged(TreeSelectionEvent e) {
    Debug.println("KeyPanel : valueChanged (TreeSelectionEvent)");
    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
    this.providerTree.getLastSelectedPathComponent();
    
    if (node == null) return;
    
    Object nodeInfo = node.getUserObject();
    if (node.getUserObject() instanceof TreeEntry) {
        Debug.println("KeyPanel : valueChanged, clic on TreeEntry");
        this.keyStoreTable = null; //reset the pointer to keyStoreTable
        TreeEntry treeEntry = (TreeEntry)node.getUserObject();
        String id = treeEntry.id;
        if(id=="root")      {displayRootPanel(); return; }
        if(id==MS_CRYPTO_ID){displayMSCryptoPanel(); return;}
        if(id==JKS_ID){displayJKSPanel(); return;}
        if(id==PKCS11_ID){displayPKCS11Panel(); return;}
        if(id==PKCS12_ID){displaPKCS12Panel(); return;}
    } else {
        //nothing
    }
}


/**when Root node is selected.*/
private void displayRootPanel(){
    JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    //descriptionPanel.setAlignmentX(0.0f);
    JTextArea descr = GUIUtils.newTextAreaNote(this,"Seleccione un proveedor");
    descr.setAlignmentX(0.0f);
    descriptionPanel.add(descr);
    
    providerRightPanel.removeAll();
    providerRightPanel.add(descriptionPanel);
    providerRightPanel.revalidate();
    providerRightPanel.repaint();
}
/**not implemented.*/
private void displayNotImplementedPanel(){
    JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    //descriptionPanel.setAlignmentX(0.0f);
    JTextArea descr = GUIUtils.newTextAreaNote(this,"Not implemented.");
    descr.setAlignmentX(0.0f);
    descriptionPanel.add(descr);
    
    providerRightPanel.removeAll();
    providerRightPanel.add(descriptionPanel);
    providerRightPanel.revalidate();
    providerRightPanel.repaint();
}

private void displayMSCryptoPanel(){
    Debug.println("KeyPanel: displayMSCryptoPanel");
    try{
        JLabel description = new JLabel("Firmas disponibles");
        description.setAlignmentX(0.0f);
        
        KeyStore keystore = KeyStoreUtils.getMSKeyStore();
        KeyStoreTable keyStoreTable = new KeyStoreTable(keystore);
        this.keyStoreTable = keyStoreTable;
        JScrollPane scroll= new JScrollPane(keyStoreTable);
        scroll.setAlignmentX(0.0f);
        providerRightPanel.removeAll();
        providerRightPanel.add(description);
        providerRightPanel.add(scroll);
        revalidateAndRepaint(providerRightPanel);
    }catch(Exception e){
        DialogBox.showExceptionBox(wizardFrame, e);
        providerRightPanel.removeAll();
        providerRightPanel.add(new JLabel("Imposible acceder al keyStore."));
    }
}
private void displayJKSPanel(){
    JPanel jksPanel= new JKSPanel();
    providerRightPanel.removeAll();
    providerRightPanel.add(jksPanel);
    revalidateAndRepaint(providerRightPanel);
    
}
private void displaPKCS12Panel(){
    JPanel jksPanel= new PKCS12Panel();
    providerRightPanel.removeAll();
    providerRightPanel.add(jksPanel);
    revalidateAndRepaint(providerRightPanel);        
    
}
private void displayPKCS11Panel(){
    JPanel jksPanel= new PKCS11Panel();
    providerRightPanel.removeAll();
    providerRightPanel.add(jksPanel);
    revalidateAndRepaint(providerRightPanel);       
    
}

// ********************** JKS PANEL *******************************

class JKSPanel extends JPanel implements ActionListener{
    
    private JButton defaultFileButton;
    private JButton selectFileButton;
    private JLabel filePath;
    private KeyStoreTable ksTable;
    private Box ksBox; //will contained the scrollPane with the list
    
    public JKSPanel(){
        Debug.println("KeyPanel: displayJKSPanel");
        this.defaultFileButton = new JButton("JKS por defecto");
        defaultFileButton.addActionListener(this);
        this.selectFileButton = new JButton("Seleccione Archivo JKS ...");
        selectFileButton.addActionListener(this);
        
        Box buttonsBox = Box.createHorizontalBox();
        buttonsBox.add(defaultFileButton);
        buttonsBox.add(selectFileButton);
        buttonsBox.add(Box.createHorizontalGlue());
        
        filePath = new JLabel("No selecciono archivo.");
        
        Box fileBox = Box.createHorizontalBox();
        fileBox.add(filePath);
        fileBox.add(Box.createHorizontalGlue());
        
        this.ksBox = Box.createHorizontalBox();
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(buttonsBox);
        this.add(fileBox);
        this.add(this.ksBox);
        
        
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==defaultFileButton){
            try{
            File JKSFile = KeyStoreUtils.getDefaultJKSKeyStore();
            if(JKSFile == null){
                DialogBox.showWarningBox(wizardFrame, "Imposible determina Java KeyStore por defecto");
                return;
            }
            this.openJKSFileStore(JKSFile);
            }catch(Exception ex){
                DialogBox.showExceptionBox(wizardFrame, ex);
                return;
            }
        }
        if(e.getSource()==selectFileButton){
            File JKSFile = this.JKSfileChooser();
            if(JKSFile==null){return;} //user cancel
            openJKSFileStore(JKSFile);
        }
    }
    public File JKSfileChooser(){
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione un archivo JKS");
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        jfc.setMultiSelectionEnabled(false);
        //TO DO : extension filter
        int status = jfc.showDialog(wizardFrame, "Abriendo JKS archivo");
        if(status == JFileChooser.APPROVE_OPTION){
            return jfc.getSelectedFile();
        }else{
            return null;
        }
    }
    
    public void openJKSFileStore(File JKSFile){
        try{
            if(JKSFile==null){
                Debug.println("KeyPane: openJKSFileStore: JKSfile is null");
                return;
            }
            KeyStore ks=null;
            //attemp to open the store without password.
            ks = KeyStoreUtils.getJKSStore(JKSFile, null);
            if(ks==null){
                Debug.println("KeyPanel: wrong pass 1");
                //open dialog
                boolean passUnvalid = true;
                boolean firstAsk = true;
                while(ks==null){
                    char[] pass = DialogBox.askPassword(
                            wizardFrame, "Entre clave del keystore.",firstAsk);
                    if(pass==null){return;} //user cancelled
                    firstAsk = false;
                    ks = KeyStoreUtils.getJKSStore(JKSFile, pass);
                }
            }
            if(ks==null){
                Debug.println("keystore : null pointer");
                return;
            }
            //here valid password
            KeyStoreTable ksTable = new KeyStoreTable(ks);
            keyStoreTable = ksTable;
            ksBox.removeAll();
            ksBox.add(new JScrollPane(ksTable));
            filePath.setText("File: "+JKSFile.getAbsolutePath());
            ksBox.revalidate();
            ksBox.repaint();
            
        }catch(Exception xmlEx){
            DialogBox.showExceptionBox(wizardFrame, xmlEx);
            ksBox.removeAll();
            ksBox.add(new JLabel("Imposible acceder al JKS keyStore."));
        }
    }
};

   // ********************** PKCS12 PANEL *******************************

class PKCS12Panel extends JPanel implements ActionListener{
    
    private JButton selectFileButton;
    private JLabel filePath;
    private KeyStoreTable ksTable;
    private Box ksBox; //will contained the scrollPane with the list
    
    public PKCS12Panel(){
        Debug.println("KeyPanel: PKCS12Panel");
        this.selectFileButton = new JButton("Seleccione PKCS#12 Archivo...");
        selectFileButton.addActionListener(this);
        
        Box buttonsBox = Box.createHorizontalBox();
        buttonsBox.add(selectFileButton);
        buttonsBox.add(Box.createHorizontalGlue());
        
        filePath = new JLabel("Archivo no seleccionado.");
        
        Box fileBox = Box.createHorizontalBox();
        fileBox.add(filePath);
        fileBox.add(Box.createHorizontalGlue());
        
        this.ksBox = Box.createHorizontalBox();
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(buttonsBox);
        this.add(fileBox);
        this.add(this.ksBox);
        
        
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==selectFileButton){
            File PKCS12File = this.PKCS12fileChooser();
            if(PKCS12File==null){return;} //user cancel
            openPKCS12FileStore(PKCS12File);
        }
    }
    public File PKCS12fileChooser(){
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Select a PKCS#12 file");
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        jfc.setMultiSelectionEnabled(false);
        //TO DO : extension filter
        int status = jfc.showDialog(wizardFrame, "Abrir PKCS#12 archivo");
        if(status == JFileChooser.APPROVE_OPTION){
            return jfc.getSelectedFile();
        }else{
            return null;
        }
    }
    
    public void openPKCS12FileStore(File PKCS12File){
        try{
            if(PKCS12File==null){
                Debug.println("KeyPane: openPKCS12FileStore: PKCS12File is null");
                return;
            }
            KeyStore ks=null;
            //attemp to open the store without password.
            // ks = KeyStoreUtils.getPKCS12Store(PKCS12File, null); => BUG 
               // with PKCS12 SUN Provider, getCertificate(alias) returns null
               // if null storepass given
            if(ks==null){
                Debug.println("KeyPanel: wrong pass 1");
                //open dialog
                boolean passUnvalid = true;
                boolean firstAsk = true;
                while(ks==null){
                    char[] pass = DialogBox.askPassword(
                            wizardFrame, "Ingrese keystore clave.",firstAsk);
                    if(pass==null){return;} //user cancelled
                    firstAsk = false;
                    ks = KeyStoreUtils.getPKCS12Store(PKCS12File, pass);
                }
            }
            if(ks==null){
                Debug.println("keystore : null pointer");
                return;
            }
            //here valid password
            KeyStoreTable ksTable = new KeyStoreTable(ks);
            keyStoreTable = ksTable;
            ksBox.removeAll();
            ksBox.add(new JScrollPane(ksTable));
            filePath.setText("File: "+PKCS12File.getAbsolutePath());
            ksBox.revalidate();
            ksBox.repaint();
            
        }catch(Exception xmlEx){
            DialogBox.showExceptionBox(wizardFrame, xmlEx);
            ksBox.removeAll();
            ksBox.add(new JLabel("Imposible acceder al PKCS#12 keyStore."));
        }
    }
};

       // ********************** PKCS11 PANEL *******************************
class PKCS11Panel extends JPanel implements ActionListener{
    
    private JButton selectFileButton;
    private JLabel filePath;
    private KeyStoreTable ksTable;
    private Box ksBox; //will contained the scrollPane with the list
    
    public PKCS11Panel(){
        Debug.println("KeyPanel: PKCS11Panel");
        this.selectFileButton = new JButton("Seleccione PKCS#11 Libraria...");
        selectFileButton.addActionListener(this);
        
        Box buttonsBox = Box.createHorizontalBox();
        buttonsBox.add(selectFileButton);
        buttonsBox.add(Box.createHorizontalGlue());
        
        filePath = new JLabel("No hay libreria seleccionada.");
        
        Box fileBox = Box.createHorizontalBox();
        fileBox.add(filePath);
        fileBox.add(Box.createHorizontalGlue());
        
        this.ksBox = Box.createHorizontalBox();
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(buttonsBox);
        this.add(fileBox);
        this.add(this.ksBox);
        
        
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==selectFileButton){
            File PKCS11LibFile = this.PKCS11libraryChooser();
            if(PKCS11LibFile==null){return;} //user cancel
            openPKCS11KeyStore(PKCS11LibFile);
        }
    }
    public File PKCS11libraryChooser(){
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Select a PKCS#11 library");
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        jfc.setMultiSelectionEnabled(false);
        //TO DO : extension filter
        int status = jfc.showDialog(wizardFrame, "Cargando PKCS#11 libraria");
        if(status == JFileChooser.APPROVE_OPTION){
            return jfc.getSelectedFile();
        }else{
            return null;
        }
    }
    
    public void openPKCS11KeyStore(File PKCS11LibFile){
        try{
            if(PKCS11LibFile==null){
                Debug.println("KeyPane: openPKCS12FileStore: PKCS12File is null");
                return;
            }
            KeyStore ks=null;
            //attemp to open the store without password.
            String tokenName = "webSign";
            ks = KeyStoreUtils.getPKCS11Store(PKCS11LibFile, null, tokenName);
            if(ks==null){
                Debug.println("KeyPanel: wrong pass 1");
                //open dialog
                boolean passUnvalid = true;
                boolean firstAsk = true;
                while(ks==null){
                    char[] pass = DialogBox.askPassword(
                            wizardFrame, "Ingrese la clave del keystore.",firstAsk);
                    if(pass==null){return;} //user cancelled
                    firstAsk = false;
                    ks = KeyStoreUtils.getPKCS11Store(PKCS11LibFile, pass, tokenName);
                }
            }
            if(ks==null){
                Debug.println("keystore : null pointer");
                return;
            }
            //here valid password
            KeyStoreTable ksTable = new KeyStoreTable(ks);
            keyStoreTable = ksTable;
            ksBox.removeAll();
            ksBox.add(new JScrollPane(ksTable));
            filePath.setText("Library: "+PKCS11LibFile.getAbsolutePath());
            ksBox.revalidate();
            ksBox.repaint();
            
        }catch(Exception xmlEx){
            DialogBox.showExceptionBox(wizardFrame, xmlEx);
            ksBox.removeAll();
            ksBox.add(new JLabel("Imposible acceder al PKCS#11 keyStore."));
        }
    }
};
// **********************                *******************************


/** revalidate + repaint component.*/
private void revalidateAndRepaint(JComponent comp){
    comp.revalidate();
    comp.repaint();
}

public KeyStoreEntry getSigningKeyStoreEntry() {
	return signingKeyStoreEntry;
}
		
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
