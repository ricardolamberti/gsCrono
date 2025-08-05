package  pss.common.pki.appletSign;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.util.zip.GZIPInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;

import be.cardon.sign.shared.DialogBox;
import be.cardon.sign.shared.KeyStoreEntry;

public class SignForm extends Applet {

	public void setFilename(String zFilename) {
		localFilename=zFilename;
	}

	public SignForm() {
		jButton=null;
		localFilename=null;
		scriptFromApplet=null;
	}

	public void init() {
		setSize(new Dimension(148, 37));
		add(getJButton(), null);
	}

	private JButton getJButton()
    {
        if(jButton == null)
        {
            jButton = new JButton();
            jButton.setText(getParameter("label_button"));
            jButton.setDefaultCapable(true);
            jButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    firmar();
                }
              }
            );
        }
        return jButton;
    }

	public void firmar() {
		outputFilename=getParameter("outputFilename");
		outputProgreso=getParameter("outputProgreso");
		String filename=getParameter("filename");
		if (filename==null||filename.equals("")) filename=localFilename;
		String script=getParameter("script");
		if (script==null||script.equals("")) script=scriptFromApplet;
		sign(filename, script);
	}

	private PrivateKey getPrivateKey(KeyStoreEntry zpfx, char pass[]) throws Exception {
		if (zpfx==null) {
			throw new NullPointerException("signingKeyStoreEntry");
		} else {
			String alias=zpfx.getAlias();
			return (PrivateKey) zpfx.getLoadedKeyStore().getKey(alias, pass);
		}
	}

	public PrivateKey getPrivateKey(KeyStoreEntry zpfx) throws Exception {
		PrivateKey key=null;
		try {
			key=getPrivateKey(zpfx, null);
		} catch (Exception e) {
			boolean firstAsk=true;
			while (key==null) {
				char pass[]=DialogBox.askPassword(null, "Ingrese clave privada de su firma.", firstAsk);
				firstAsk=false;
				if (pass==null) throw new Exception("Operacion cancelada");
				firstAsk=false;
				try {
					key=getPrivateKey(zpfx, pass);
				} catch (Exception exception) {
				}
			}
		}
		if (key==null) throw new NullPointerException("clave es nula!");
		else return key;
	}

	public void signPdf(KeyStoreEntry zpfx, String zpdf, OutputStream zpdfSigner, String reason, String location) throws Exception {
		String pdf=(new File(zpdf)).getAbsolutePath();
		Provider prov=zpfx.getProvider();
		Security.removeProvider(prov.getName());
		Security.insertProviderAt(prov, 1);
		java.security.cert.Certificate chain[]=zpfx.getCertificateChain();
		PrivateKey key=getPrivateKey(zpfx);
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Firmando 10 %')").toString());
		PdfReader reader=new PdfReader(pdf);
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Firmando 20 %')").toString());
		BufferedOutputStream fout=new BufferedOutputStream(zpdfSigner);
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Firmando 30 %')").toString());
		PdfStamper stp=PdfStamper.createSignature(reader, fout, '\0');
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Firmando 40 %')").toString());
		PdfSignatureAppearance sap=stp.getSignatureAppearance();
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Firmando 50 %')").toString());
		sap.setCrypto(key, chain, null, PdfSignatureAppearance.WINCER_SIGNED);
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Firmando 60 %')").toString());
		sap.setReason(reason);
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Firmando 70 %')").toString());
		sap.setLocation(location);
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Firmando 80 %')").toString());
		sap.setVisibleSignature(new Rectangle(100F, 100F, 200F, 200F), 1, null);
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Firmando 90 %')").toString());
		stp.close();
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Documento Firmado')").toString());
	}

	private void sign(String filename, String script) {
		try {
			FileDialog fd;
			if (filename!=null&&!filename.equals("")) return;
			fd=new FileDialog(new Frame(), "Seleccione documento pdf:", 0);
			fd.show();
			filename=fd.getFile();
			if (filename==null) return;
			filename=(new StringBuilder(String.valueOf(fd.getDirectory()))).append(fd.getDirectory().endsWith(File.separator) ? "" : File.separator).append(fd.getFile()).toString();
			WinSignForm signKey=new WinSignForm(new JFrame());
			signKey.show();
			KeyStoreEntry sign=signKey.getSigningKeyStoreEntry();
			File newFile=new File(filename);
			ByteArrayOutputStream os=new ByteArrayOutputStream(0xf4240);
			signPdf(sign, filename, os, "Firma en linea", "Web");
			uploadSignature(newFile.getName(), os.toByteArray(), script);
		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}

	}

	public int uploadSignature(String filename, byte pdf[], String script) throws Exception {
		ClientHttpRequest http=new ClientHttpRequest(script) {
			@Override
			public void step(long i) {
				execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Transfiriendo "+i+" bytes')").toString());
			}
		};
		http.setParameter("uploaded_file", filename, pdf);
		if (outputFilename!=null) http.setParameter("field", outputFilename);
		if (outputProgreso!=null) http.setParameter("progreso", outputProgreso);
		java.io.InputStream response=http.post();
	
		GZIPInputStream  zin = new GZIPInputStream (response);
		String output = "";
		while (zin.available()!=0) output+=(char) (zin.read()&0xFF);
		zin.close();
		
		execJS((new StringBuilder("progreso('")).append(outputProgreso).append("','Transferencia completa')").toString());
	
		int start = output.indexOf("<script");
		start=output.indexOf(">", start);
		int end = output.indexOf("</script>");
		String js = output.substring(start+1,end);
		execJS(js);
		return 1;
	}

	public String execJS(String jscmd) {
		String jsresult=null;
		boolean success=false;
		try {
			Method getw=null;
			Method eval=null;
			Object jswin=null;
			Class c=Class.forName("netscape.javascript.JSObject");
			Method ms[]=c.getMethods();
			for(int i=0; i<ms.length; i++)
				if (ms[i].getName().compareTo("getWindow")==0) getw=ms[i];
				else if (ms[i].getName().compareTo("eval")==0) eval=ms[i];

			Object a[]=new Object[1];
			a[0]=this;
			jswin=getw.invoke(c, a);
			a[0]=jscmd;
			Object result=eval.invoke(jswin, a);
			if (result instanceof String) jsresult=(String) result;
			else jsresult=result.toString();
			success=true;
		} catch (InvocationTargetException ite) {
			jsresult=(new StringBuilder()).append(ite.getTargetException()).toString();
		} catch (Exception e) {
			jsresult=(new StringBuilder()).append(e).toString();
		}
		return success ? jsresult : "ERROR";
	}

	private void showError(String error) {
//		ErrorDialog d=new ErrorDialog(new Frame(), (new StringBuilder("Verificar archivo de firma\r\n(")).append(error).append(")").toString());
//		d.show();
	}

	public String getScriptFromApplet() {
		return scriptFromApplet;
	}

	public void setScriptFromApplet(String scriptFromApplet) {
		this.scriptFromApplet=scriptFromApplet;
	}

	public String getOutputFilename() {
		return outputFilename;
	}

	public void setOutputFilename(String outputFilename) {
		this.outputFilename=outputFilename;
	}

	public String getOutputProgreso() {
		return outputProgreso;
	}

	public void setOutputProgreso(String outputProgreso) {
		this.outputProgreso=outputProgreso;
	}

	private JButton jButton;
	private String localFilename;
	private String scriptFromApplet;
	private String outputProgreso;
	private String outputFilename;
}
