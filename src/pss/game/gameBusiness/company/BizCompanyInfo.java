package pss.game.gameBusiness.company;

import pss.common.personalData.BizPersona;
import pss.common.personalData.types.BizPersonaJuridica;
import pss.common.regions.company.BizCompany;
import pss.common.security.BizUsuario;
import pss.common.security.license.license.BizLicense;
import pss.common.security.license.typeLicense.BizTypeLicense;
import pss.core.services.JExec;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JObjBD;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.game.gameBusiness.BizGameCompany;

public class BizCompanyInfo extends JRecord {

	private JString pCompany = new JString();
	private JLong pIdpersona = new JLong();
	private JObjBD pPersona = new JObjBD() {
		public void preset() throws Exception {
			if (pPersona.isRawNull())
				pPersona.setValue(fillObjPersona());
		};
	};

	public void setIdpersona(long zValue) throws Exception {
		pIdpersona.setValue(zValue);
	}

	public long getIdpersona() throws Exception {
		return pIdpersona.getValue();
	}

	public boolean isNullIdpersona() throws Exception {
		return pIdpersona.isNull();
	}

	public void setNullToIdpersona() throws Exception {
		pIdpersona.setNull();
	}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void setCompany(String zValue) throws Exception {
		pCompany.setValue(zValue);
	}

	public String getCompany() throws Exception {
		return pCompany.getValue();
	}

	public boolean isNullCompany() throws Exception {
		return pCompany.isNull();
	}

	public void setNullToCompany() throws Exception {
		pCompany.setNull();
	}

	/**
	 * Constructor de la Clase
	 */
	public BizCompanyInfo() throws Exception {
	}

	public void createProperties() throws Exception {
		this.addItem("company", pCompany);
		this.addItem("id_persona", pIdpersona);
		this.addItem("persona", pPersona);

	}

	/**
	 * Adds the fixed object properties
	 */
	public void createFixedProperties() throws Exception {
		this.addFixedItem(KEY, "company", "Company", true, true, 15);
		this.addFixedItem(FIELD, "id_persona", "Persona", true, true, 64);
		this.addFixedItem(RECORD, "persona", "Persona", true, false, 15).setClase(BizPersona.class);
	}

	@Override
	public void createControlProperties() throws Exception {
		super.createControlProperties();
	}

	/**
	 * Returns the table name
	 */
	public String GetTable() {
		return "gms_companyinfo";
	}

	/**
	 * Default read() method
	 */
	public boolean read(String company) throws Exception {
		addFilter("company", company);
		return read();
	}

	public BizPersona getObjPersona() throws Exception {
		return (BizPersona) pPersona.getValue();
	}

	public BizPersona fillObjPersona() throws Exception {
		BizPersonaJuridica p = new BizPersonaJuridica();
		p.dontThrowException(true);
		if (getIdpersona() == 0)
			return p;
		if (!p.Read(getIdpersona())) {
			BizCompany comp = BizCompany.getCompany(getCompany());
			p.SetApellido(comp.getDescription());
			p.SetNombre(comp.getDescription());
		}
		return p;
	}


	@Override
	public void processInsert() throws Exception {

		getObjPersona().processInsert();
		setIdpersona(getObjPersona().GetPersona());
		
		super.processInsert();
	}

	@Override
	public void processDelete() throws Exception {
		getObjPersona().processDelete();
		super.processDelete();
		BizCompany.cleanCompany(getCompany());

	}

	@Override
	public void processUpdate() throws Exception {
		getObjPersona().processUpdate();

		super.processUpdate();
		BizCompany.cleanCompany(getCompany());

	}

	public static BizCompanyInfo getInfo() throws Exception {
		BizGameCompany cpny = new BizGameCompany();
		cpny.read(BizUsuario.getUsr().getCompany());
		return cpny.getObjCompanyInfo();
	}



}
