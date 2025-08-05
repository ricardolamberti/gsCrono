package pss.game.gameBusiness;

import pss.common.regions.company.BizCompany;
import pss.common.security.BizRol;
import pss.common.security.BizUsuario;
import pss.common.todolist.BizToDoList;
import pss.core.services.fields.JObjBD;
import pss.core.services.records.JRecords;
import pss.core.tools.collections.JIterator;
import pss.game.gameBusiness.company.BizCompanyInfo;

public class BizGameCompany extends BizCompany  {
  private JObjBD pCompanyinfo = new JObjBD() {
  	public void preset() throws Exception {
  		if (pCompanyinfo.isRawNull())
  			pCompanyinfo.setValue(fillObjCompanyInfo());
  	};
  };
  
  @Override
  public void clean() throws Exception {
  	super.clean();
  }
  
	public BizGameCompany() throws Exception {
		super();
	}
	
  public boolean read(String company ) throws Exception { 
    addFilter( "company",  company ); 
    return read(); 
  } 

  
  public void createProperties() throws Exception {
    this.addItem( "company_info", pCompanyinfo );
    super.createProperties();
  }
  /**
   * Adds the fixed object properties
   */
  public void createFixedProperties() throws Exception {
    this.addFixedItem( RECORD, "company_info", "company_info", true, true, 64).setClase(BizCompanyInfo.class);
    super.createFixedProperties();
  }

  public BizCompanyInfo getObjCompanyInfo() throws Exception {
  	return (BizCompanyInfo) pCompanyinfo.getValue();
  }
  public BizCompanyInfo fillObjCompanyInfo() throws Exception {
  	BizCompanyInfo p = new BizCompanyInfo();
  	p.dontThrowException(true);
  	if (!p.read(getCompany())) {
  		p.setCompany(getCompany());
  		p.getObjPersona().setCompany(getCompany());
  		p.getObjPersona().SetApellido(getDescription());
  	}
  	return p;
  }  

  @Override
  public void processInsert() throws Exception {
  	getObjCompanyInfo().processUpdateOrInsertWithCheck();
  	setDescription(getObjCompanyInfo().getObjPersona().GetApellido());
  	super.processInsert();
  }
  @Override
  public void processUpdate() throws Exception {
  	getObjCompanyInfo().processUpdateOrInsertWithCheck();
  	setDescription(getObjCompanyInfo().getObjPersona().GetApellido());
  	super.processUpdate();
  }
  @Override
  public void processDelete() throws Exception {
  	getObjCompanyInfo().processDelete();
  	super.processDelete();
  }
  
	public synchronized static boolean isTurnosUser() {
		return BizUsuario.getUsr() instanceof BizGameUser;
	}

	 public long getDefaultRol() throws Exception {
		 BizRol findRol = new BizRol();
		 findRol.dontThrowException(true);
		 findRol.addFilter("company", getCompany());
		 findRol.addFilter("descripcion", "usuario");
		 if (!findRol.read())
	  	return -1;
		 return findRol.getRol();
	  }

	public static BizGameCompany getObjTurnosCompany(String company) throws Exception {
		BizGameCompany val = new BizGameCompany();
		val.Read(company);
		return val;
	}
  public void  cloneTableCompany(String newCompanyName,String newUserName) throws Exception {
  	super.cloneTableCompany(newCompanyName, newUserName);
   	copyToDoList(newCompanyName,newUserName);
  }
  public void copyToDoList(String newCompanyName,String newUserName) throws Exception {
  	JRecords<BizToDoList> todo = new JRecords<BizToDoList>(BizToDoList.class);
  	todo.addFilter("company", getCompany());
  	todo.addFilter("id_usuario", "null");
  	JIterator<BizToDoList> it = todo.getStaticIterator();
  	while (it.hasMoreElements()) {
  		BizToDoList rec = it.nextElement();
  		BizToDoList newrec = (BizToDoList) rec.createCopyClone();
  		newrec.setCompany(newCompanyName);
  		newrec.setStatus(BizToDoList.PENDIENTE);
   		newrec.processInsert();
  	}
  	
    todo = new JRecords<BizToDoList>(BizToDoList.class);
  	todo.addFilter("company", getCompany());
  	todo.addFilter("id_usuario", BizUsuario.C_ADMIN_USER);
  	it = todo.getStaticIterator();
  	while (it.hasMoreElements()) {
  		BizToDoList rec = it.nextElement();
  		BizToDoList newrec = (BizToDoList) rec.createCopyClone();
  		newrec.setCompany(newCompanyName);
   		newrec.setUsuario(newUserName);
  		newrec.setStatus(BizToDoList.PENDIENTE);
   	 	newrec.processInsert();
  	}
  }
}
