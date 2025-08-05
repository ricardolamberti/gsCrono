package pss.game.models.variable;

import pss.core.services.JExec;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.services.records.JRecords;
import pss.core.tools.collections.JCollectionFactory;
import pss.core.tools.collections.JIterator;
import pss.core.tools.collections.JMap;
import pss.core.win.JWins;
import pss.game.models.variable.logica.IVariableLogica;
import pss.game.models.variable.logica.VariableLogicaListValue;
import pss.game.models.variable.logica.VariableLogicaRange;
import pss.game.models.variable.logica.VariableLogicaRangeFloat;
import pss.game.models.variableOption.BizVariableOption;
import pss.game.models.variableOption.BizVariableOptionBase;

public class BizVariableBase extends JRecord {

	protected JLong pId = new JLong();
	protected JString pName = new JString();
	protected JString pType = new JString ();
	protected JLong pRangeMin = new JLong();
	protected JLong pRangeMax = new JLong();
	protected JLong pTotalOptions = new JLong();
	protected JString pCompany = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setId(long zValue) throws Exception {    pId.setValue(zValue);  }
	public long getId()	throws Exception {     return pId.getValue();  }
	public boolean isNullId() throws Exception { return  pId.isNull(); } 
	public void setNullToId() throws Exception {  pId.setNull(); } 
	public void setName(String zValue) throws Exception {    pName.setValue(zValue);  }
	public String getName()	throws Exception {     return pName.getValue();  }
	public boolean isNullName() throws Exception { return  pName.isNull(); } 
	public void setNullToName() throws Exception {  pName.setNull(); } 
	public void setType(String zValue) throws Exception {    pType.setValue(zValue);  }
	public String getType()	throws Exception {     return pType.getValue();  }
	public boolean isNullType() throws Exception { return  pType.isNull(); } 
	public void setNullToType() throws Exception {  pType.setNull(); } 
	public void setRangeMin(long zValue) throws Exception {    pRangeMin.setValue(zValue);  }
	public long getRangeMin()	throws Exception {     return pRangeMin.getValue();  }
	public boolean isNullRangeMin() throws Exception { return  pRangeMin.isNull(); } 
	public void setNullToRangeMin() throws Exception {  pRangeMin.setNull(); } 
	public void setRangeMax(long zValue) throws Exception {    pRangeMax.setValue(zValue);  }
	public long getRangeMax()	throws Exception {     return pRangeMax.getValue();  }
	public boolean isNullRangeMax() throws Exception { return  pRangeMax.isNull(); } 
	public void setNullToRangeMax() throws Exception {  pRangeMax.setNull(); } 
	public void setTotalOptions(long zValue) throws Exception {    pTotalOptions.setValue(zValue);  }
	public long getTotalOptions()	throws Exception {     return pTotalOptions.getValue();  }
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 

	
  public static final String RANGE = "RANGE";
  public static final String LIST_VALUE = "LIST_VALUE";
  public static final String RANGE_FLOAT = "RANGE_FLOAT";

	public static Class variableLogicasClasesConocidas(String id) {
		if (id.equals(BizVariableBase.RANGE)) return VariableLogicaRange.class;
		if (id.equals(BizVariableBase.RANGE_FLOAT)) return VariableLogicaRangeFloat.class;
		if (id.equals(BizVariableBase.LIST_VALUE)) return VariableLogicaListValue.class;
		return null;
	}

	public static JMap<String, String> variableLogicasClasesConocidasMap(JMap<String, String> cc) {
		cc.addElement(BizVariableBase.RANGE, "Rango Enteros");
		cc.addElement(BizVariableBase.RANGE_FLOAT, "Rango Flotantes");
		cc.addElement(BizVariableBase.LIST_VALUE, "Lista de valores");
		return cc;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	static JMap<String, String> logicas;

	public static JMap<String, String> getLogicas() throws Exception {
		if (logicas != null)
			return logicas;
		JMap<String, String> maps = JCollectionFactory.createMap();
		return logicas = variableLogicasClasesConocidasMap(maps);
	}

	public static Class getLogicasClass(String id) throws Exception {
		return variableLogicasClasesConocidas(id);
	}
	
	public IVariableLogica getObjLogica() throws Exception {
		if (isNullType()) return null;
		return (IVariableLogica)getLogicasClass(getType()).newInstance();
	}
  /**
   * Constructor de la Clase
   */
  public BizVariableBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "name", pName );
		addItem( "type", pType );
		addItem( "range_min", pRangeMin );
		addItem( "range_max", pRangeMax );
		addItem( "total_options", pTotalOptions );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "name", "name", true, true, 255 );
		addFixedItem( FIELD, "type", "type", true, true, 0 );
		addFixedItem( FIELD, "range_min", "range_min", true,false, 32 );
		addFixedItem( FIELD, "range_max", "range_max", true,false, 32 );
		addFixedItem( FIELD, "total_options", "total_option", true,false, 32 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
  }
	
  @Override
  public void createControlProperties() throws Exception {
   	this.addControlsProperty("type", createControlCombo(JWins.createVirtualWinsFromMap(getLogicas()),null, null) );
   	super.createControlProperties();
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_variable"; }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  /**
   * Default read() method
   */
	public boolean read( long zId ) throws Exception { 
		addFilter( "id",  zId ); 
		return read(); 
  } 
	public boolean readByName( String zName ) throws Exception { 
		addFilter( "name",  zName ); 
		return read(); 
  } 
	
	public boolean isRangeVisible() throws Exception {
		if (getObjLogica()==null) return false;
		return getObjLogica().useRange();
	}
	public boolean hasListValueVisible() throws Exception {
		if (getObjLogica()==null) return false;
		return getObjLogica().useListValue();
	}	
	
	public BizVariableOptionBase getOneValue() throws Exception {
		return  getObjLogica().getOneValue(this);

	}
	public BizVariableOptionBase getOneValue(String operator, String valueComp) throws Exception {
		return  getObjLogica().getOneValue(this,operator,valueComp);

	}
	public void execProcessReordenar() throws Exception {
		JExec exec = new JExec() {
			public void Do() throws Exception {
				processReordenar();
			}
		};
		exec.execute();
	}
	
	public void processReordenar() throws Exception {
		JRecords<BizVariableOption> options = new JRecords<BizVariableOption>(BizVariableOption.class);
		options.addFilter("variable_id", getId());
		JIterator<BizVariableOption> it = options.getStaticIterator();
		int id=0;
		while (it.hasMoreElements()) {
			BizVariableOption option = it.nextElement();
			option.setOrden(id++);
			option.update();
		}
		setTotalOptions(id);
		update();
	}
}
