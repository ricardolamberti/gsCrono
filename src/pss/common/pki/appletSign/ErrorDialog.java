package  pss.common.pki.appletSign;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.TextArea;

import javax.swing.JButton;


public class ErrorDialog extends Dialog {
	  private JButton jOK = null;
	  private TextArea jLabel = null;
	  private String label = "";
	 
	  /**
	   * This method initializes 
	   * 
	   */
	  public ErrorDialog(Frame parent, String zLabel) {
	    super(parent,  true);
	    label = zLabel;
	   	initialize();
	  }

	  /**
	   * This method initializes this
	   * 
	   */
	  private void initialize() {
	        jLabel = new TextArea();
	        jLabel.setText(getLabel());
	        this.setSize(new Dimension(364, 118));
	        this.setTitle("Error");
	        this.add(getJOK(), BorderLayout.SOUTH);
	        this.add(jLabel, BorderLayout.CENTER);
	        this.addWindowListener(new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent e) {
	        		
	        		dispose();
	        	}
	        });
	  	
	  }


	  /**
	   * This method initializes jOK	
	   * 	
	   * @return javax.swing.JButton	
	   */
	  private JButton getJOK() {
	  	if (jOK==null) {
	  		jOK=new JButton();
	  		jOK.setText("Cerrar");
	  		jOK.setDefaultCapable(true);
	  	  
	  		jOK.addActionListener(new java.awt.event.ActionListener() {
	  			public void actionPerformed(java.awt.event.ActionEvent e) {
	  				dispose();
	  			}
	  		});
	  	}
	  	
	  	return jOK;
	  }

		
		public String getLabel() {
			return label;
		}

		
		public void setLabel(String label) {
			this.label=label;
		}
	     
			

}  //  @jve:decl-index=0:visual-constraint="10,10"
