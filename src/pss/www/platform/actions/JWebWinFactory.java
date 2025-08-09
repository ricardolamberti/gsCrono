package pss.www.platform.actions;

import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import pss.core.data.interfaces.structure.RFilter;
import pss.core.services.fields.JObjBDs;
import pss.core.services.fields.JObject;
import pss.core.services.records.JBaseRecord;
import pss.core.services.records.JRecord;
import pss.core.services.records.JRecords;
import pss.core.tools.JTools;
import pss.core.tools.collections.JCollectionFactory;
import pss.core.tools.collections.JIterator;
import pss.core.tools.collections.JList;
import pss.core.tools.collections.JMap;
import pss.core.win.JBaseWin;
import pss.core.win.JWin;
import pss.core.win.JWins;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActFieldSwapWins;
import pss.www.platform.actions.requestBundle.JWebActionData;
import pss.www.platform.actions.requestBundle.JWebActionDataField;
import pss.www.platform.applications.JHistory;
import pss.www.platform.applications.JHistoryProvider;
import pss.www.platform.applications.JWebHistoryManager;

public class JWebWinFactory {

	public JWebWinFactory(IControlToBD aControToBD) {
		this.controToBD = aControToBD;
	}

	IControlToBD controToBD;

	TreeMap<String, SoftReference<JBaseWin>> winRefference = new TreeMap<String, SoftReference<JBaseWin>>();
	TreeMap<String, SoftReference<JBaseRecord>> recRefference = new TreeMap<String, SoftReference<JBaseRecord>>();

	public TreeMap<String, SoftReference<JBaseWin>> getWinRefference() {
		return winRefference;
	}

	public void forget() {
		winRefference = new TreeMap<String, SoftReference<JBaseWin>>();
	}

	public TreeMap<String, SoftReference<JBaseRecord>> getRecRefference() {
		return recRefference;
	}

	public void setRecRefference(TreeMap<String, SoftReference<JBaseRecord>> recRefference) {
		this.recRefference = recRefference;
	}

	public void cleanRefference() {
		Iterator<String> it = winRefference.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			SoftReference<JBaseWin> swin = winRefference.get(key);
			if (swin != null) {
				if (swin.get() != null)
					continue;
			}
			it.remove();
		}
	}

	public void fillHistory() throws Exception {
		JWebHistoryManager hm = JWebActionFactory.getCurrentRequest().getHistoryManager();
		if (hm.getActionHistory().size() == 0)
			return;
		for (int i = 0; i < hm.getActionHistory().size(); i++) {
			JHistory hist = hm.getActionHistory().getElementAt(i);
			Iterator<String> it = hist.getProviders().keySet().iterator();
			while (it.hasNext()) {
				String shp = it.next();
				JHistoryProvider hp = hist.getProviders().get(shp);
				rememberAct(hp.getAction());
			}
		}

	}

	public void buildWinsFromField(JBaseWin baseWin, String fields) throws Exception {
		JWins wins = (JWins) baseWin;
		wins.SetEstatico(true);
		String objectList = fields;
		StringTokenizer st = new StringTokenizer(objectList, ";,");
		while (st.hasMoreTokens()) {
			String idObject = st.nextToken();
			JWin win = null;
			Object obj = JWebActionFactory.getCurrentRequest().getRegisterObject(idObject);
			if (obj == null)
				continue; // HGK en seleccion multimple vienen rows que son sub-rows y da error, corregir en el .js para que no vengan
			if (obj instanceof String) {
				String resolveString = (String) obj;
				win = (JWin) getBaseWinFromBundle(resolveString, true, null);
			} else {
				if (!obj.getClass().isAssignableFrom(wins.GetClassWin()))
					continue; // lo mismo que arriba, me aseguro que no se meta un row que no va, truchex!!!
				win = (JWin) obj;
			}

//			wins.AddItem(win, 0);
			wins.addRecord(win);
		}
		return;
	}

//	public JBaseWin getBaseWinFromBundle(JWebActionData zWinBundle) throws Exception {
//		return getBaseWinFromBundle(zWinBundle, true, null);
//	}

	public JBaseWin getBaseWinFromBundle(String zWinBundle) throws Exception {
		return getBaseWinFromBundle(zWinBundle, true, null);
	}

	public JBaseWin getBaseWinFromBundle(String zWinBundle, boolean loadData, String id) throws Exception {
		JBaseWin actionOwner = this.createWin(zWinBundle, id);
//		this.asignFilters(zWinBundle, actionOwner);
//		this.asignProps(zWinBundle, actionOwner);
		if (loadData)
			this.loadData(actionOwner, true, JWebActionFactory.getCurrentRequest().hasTableProvider() ? JWebActionFactory.getCurrentRequest().getTableProvider() : null, null);
		return actionOwner;
	}

	public JBaseRecord getBaseRecFromBundle(String zWinBundle, String id) throws Exception {
		JBaseRecord actionOwner = this.createRec(zWinBundle, id);
		return actionOwner;
	}
//	public JBaseWin getBaseWinFromBundle(JWebActionData zWinBundle, boolean loadData, String id) throws Exception {
//		JBaseWin actionOwner = this.createWin(zWinBundle, id);
//		this.asignFilters(zWinBundle, actionOwner);
//		this.asignProps(zWinBundle, actionOwner);
//		if (loadData)
//			this.loadData(actionOwner, true, JWebActionFactory.getCurrentRequest().hasTableProvider() ? JWebActionFactory.getCurrentRequest().getTableProvider() : null, null);
//		return actionOwner;
//	}

	public String loadWinBundle() throws Exception {
		return JWebActionFactory.getCurrentRequest().getArgument(JWebActionFactory.ACTION_DATA_PREFIX + "act_owner");
	}

	public JWebActionData loadWinBundle(String data) throws Exception {
		JWebActionData winBundle = JWebActionFactory.getCurrentRequest().getData(data);
		if (winBundle == null)
			return null;
		if (winBundle.isNull()) {
			// winBundle=null;
			return null;
		}
		if (winBundle.isEmpty()) {
			// winBundle=null;
			return null;
		}
		return winBundle;
	}

	public JBaseWin processObjectRegisteredDest(boolean loadData) throws Exception {
		return processObjectRegisteredById(JWebActionFactory.getCurrentRequest().getPssObjectOwnerDest(), loadData);
	}

	public JBaseWin processObjectRegistered(boolean loadData) throws Exception {
		return processObjectRegisteredById(JWebActionFactory.getCurrentRequest().getPssObjectOwner(), loadData);
	}

	public JBaseWin processObjectRegisteredSelect(boolean loadData) throws Exception {
		return processObjectRegisteredById(JWebActionFactory.getCurrentRequest().getPssObjectSelect(), loadData);
	}

	public JBaseWin processObjectRegisteredById(String id, boolean loadData) throws Exception {
		Object obj = JWebActionFactory.getCurrentRequest().getRegisterObject(id);
//		PssLogger.logDebug("|------------------------------------------------> TRY FIND "+id);
		if (obj instanceof JBaseWin) {
//			PssLogger.logDebug("|------------------------------------------------> FIND WIN "+id);
			JBaseWin actionOwner = (JBaseWin) obj;
			JBaseWin contextAtionOwner = actionOwner;
			if (JWebActionFactory.getCurrentRequest().getPssObjectOwnerContext() != null)
				contextAtionOwner = (JBaseWin) JWebActionFactory.getCurrentRequest().getRegisterObject(JWebActionFactory.getCurrentRequest().getPssObjectOwnerContext());
			if (loadData)
				this.loadData(actionOwner, true, JWebActionFactory.getCurrentRequest().hasTableProvider() ? JWebActionFactory.getCurrentRequest().getTableProvider() : null, contextAtionOwner);
			return actionOwner;
		}
		if (obj instanceof String) {
//			PssLogger.logDebug("|------------------------------------------------> FIND STRING "+id);
//			JWebActionFactory.getCurrentRequest().addDataBundle("act_owner", (String) obj);
//			JWebActionData bundle=this.loadWinBundle();
//			if (bundle==null) return null;
			return this.getBaseWinFromBundle((String) obj, true, id);
		}
//		PssLogger.logDebug("|------------------------------------------------> NO FIND STRING "+id);

		return null;
	}

	public JBaseWin processObjectRegisteredByIdForCard(String id) throws Exception {
		Object obj = JWebActionFactory.getCurrentRequest().getRegisterObject(id);
		if (obj instanceof JBaseWin) {
			JBaseWin actionOwner = (JBaseWin) obj;
			return actionOwner;
		}
		if (obj instanceof String) {
//			JWebActionFactory.getCurrentRequest().addDataBundle("act_card", (String) obj);
//			JWebActionData bundle=this.loadWinBundle("act_card");
//			if (bundle==null) return null;
			return this.getBaseWinFromBundle((String) obj, false, id);
		}

		return null;
	}

	public void rememberAct(BizAction action) throws Exception {
		JBaseWin owner = action.getObjOwner();
		if (owner != null)
			rememberWin(owner.getUniqueId(), owner);

		if (action.hasSubmit()) {
			JAct act = action.getObjSubmit();
			if (act.GetBWin() != null)
				rememberWin(act.GetBWin().getUniqueId(), act.GetBWin());
			if (act.hasResult())
				rememberWin(act.getResult().getUniqueId(), act.getResult());
		}
	}

	public void rememberWin(String uniqueId, JBaseWin win) throws Exception {
		if (!winRefference.containsKey(uniqueId)) {
			winRefference.put(uniqueId, new SoftReference<JBaseWin>(win));
		}
	}

	public void rememberRec(String uniqueId, JBaseRecord rec) throws Exception {
		if (!winRefference.containsKey(uniqueId)) {
			recRefference.put(uniqueId, new SoftReference<JBaseRecord>(rec));
		}
	}

	public JBaseWin getRememberWin(String uniqueId) throws Exception {
		if (winRefference.containsKey(uniqueId)) {
			SoftReference<JBaseWin> swin = winRefference.get(uniqueId);
			if (swin == null)
				return null;
			JBaseWin win = swin.get();
			if (win == null)
				return null;
			return win;
		}
		return null;
	}

	public JBaseRecord getRememberRec(String uniqueId) throws Exception {
		if (recRefference.containsKey(uniqueId)) {
			SoftReference<JBaseRecord> srec = recRefference.get(uniqueId);
			if (srec == null)
				return null;
			JBaseRecord rec = srec.get();
			if (rec == null)
				return null;
			return rec;
		}
		return null;
	}

//	public JBaseWin createWin(JWebActionData zWinBundle,String id) throws Exception {
//		// instantiate the object
//		String sUniqueId=id!=null?id:zWinBundle.get("uniqueId");
//		if (sUniqueId!=null) {
//			JBaseWin win=getRememberWin(sUniqueId);
//			if (win!=null)
//				return win;
//		}
//		JBaseWin actionOwner=null;
//		String sClass=null;
//		Class<?> oClass=null;
//		try {
//			sClass=zWinBundle.get("cls");
//			oClass=Class.forName(sClass);
//			actionOwner=(JBaseWin) oClass.newInstance();
//			actionOwner.setUniqueID(sUniqueId);
//			actionOwner.SetVision(zWinBundle.get("vision"));
//			if (actionOwner.isWin())
//				 ((JWin) actionOwner).getRecord().setDatosLeidos(zWinBundle.get("readed").equals("S"));
//
//			if (sUniqueId!=null) {
//				rememberWin(sUniqueId,actionOwner);
//			}
//			if (zWinBundle.get("drop")!=null) {
//				JWebActionFactory.getCurrentRequest().addDataBundle("act_drop", (String) JTools.byteVectorToString(Base64.getDecoder().decode(zWinBundle.get("drop"))));
//				JWebActionData bundle=loadWinBundle("act_drop");
//				if (bundle==null) return null;
//				actionOwner.setDropListener( this.getBaseWinFromBundle(bundle,true,id));
//
//			}
//			if (zWinBundle.get("dropControl")!=null) {
//				actionOwner.setDropControlIdListener((JAct)JWebActionFactory.getCurrentRequest().deserializeObject( JTools.byteVectorToString(Base64.getDecoder().decode(zWinBundle.get("dropControl")))));
//			}
//		} catch (Exception e) {
//			PssLogger.logError(e);
//		}
//		if (sClass==null) {
//			throw new RuntimeException("Pss action request owner data has no class name parameter");
//		}
//		if (oClass==null) {
//			throw new RuntimeException("Pss action class not found: "+sClass);
//		}
//		if (actionOwner==null) {
//			throw new RuntimeException("Not a JBaseWin class: "+sClass);
//		}
//		return actionOwner;
//	}

//	private void asignFilters(JWebActionData zWinBundle, JBaseWin zActionOwner) throws Exception {
//		JIterator<JWebActionDataField> oFieldIt=zWinBundle.getFieldIterator();
//		String sFilterPrefix="fltr_";
//		while (oFieldIt.hasMoreElements()) {
//			JWebActionDataField oField=oFieldIt.nextElement();
//			if (!oField.getName().startsWith(sFilterPrefix)) continue;
//			String field=oField.getName().substring(sFilterPrefix.length());
//			// int idx = fullField.indexOf(".");
//			// String field = (idx<0)? fullField : fullField.substring(idx+1);
//			if (!this.okFilter(zActionOwner, field)) continue;
//			zActionOwner.GetBaseDato().addFilter(field, oField.getValue(), oField.getOperator());
//			zActionOwner.GetBaseDato().forceFilterToData();
//			// RFilter filtro=zActionOwner.GetBaseDato().addFilter(field,
//			// oField.getValue(), oField.getOperator());
//			// if (idx>=0) filtro.setTable(fullField.substring(0, idx));
//		}
//	}
//
//	private void asignProps(JWebActionData zWinBundle, JBaseWin zActionOwner) throws Exception {
//		if (!zActionOwner.isWin()) return;
//		JIterator<JWebActionDataField> oFieldIt=zWinBundle.getFieldIterator();
//		String sFilterPrefix="rec_";
//		String sRecFilterPrefix="onerec_";
//		String sRecsFilterPrefix="recs_";
//		while (oFieldIt.hasMoreElements()) {
//			JWebActionDataField oField=oFieldIt.nextElement();
//			if (oField.getName().startsWith(sFilterPrefix)) {
//				String field=oField.getName().substring(sFilterPrefix.length());
//				JObject obj = ((JWin)zActionOwner).getRecord().getProp(field);
//				obj.setValue(((JWin)processObjectRegisteredByIdForCard(oField.getValue())).getRecord());
//			}
//			if (oField.getName().startsWith(sRecFilterPrefix)) {
//				String field=oField.getName().substring(sFilterPrefix.length());
//				JObject obj = ((JWin)zActionOwner).getRecord().getProp(field);
//				obj.setValue(getRegisterObjectTemp(oField.getValue().substring(7)));
//			}
//			if (oField.getName().startsWith(sRecsFilterPrefix)) {
//				String field=oField.getName().substring(sFilterPrefix.length());
//				JObject obj = ((JWin)zActionOwner).getRecord().getProp(field);
//				obj.setValue(getRegisterObjectTemp(oField.getValue().substring(5)));
//			}
//		}
//	}
	private boolean okFilter(JBaseRecord baseRec, String filter) throws Exception {
		if (!(baseRec instanceof JRecord))
			return true;
		return ((JRecord) baseRec).getFixedProp(filter).isKey();
	}

	private JWebActionData getExtraFormData() throws Exception {
		return JWebActionFactory.getCurrentRequest().getData("extra_form_data");
	}

	public boolean isEmbedded() throws Exception {
		if (getExtraFormData().get("embedded") == null)
			return false;
		return getExtraFormData().get("embedded").equalsIgnoreCase("true");
	}

	private boolean isParseable(JBaseWin zActionOwner) throws Exception {
		if (zActionOwner instanceof JWins) { // deberia tender a desaparecer
//		addWinListFilters(zActionOwner, getFilters());
			return false;
		}
		return true;
	}

	private JWin loadWin(JBaseWin zActionOwner, String tableProvider, boolean force) throws Exception {
		JWin win = (JWin) zActionOwner;
		win.setEmbedded(this.isEmbedded());
		JWebActionData data = JWebActionFactory.getCurrentRequest().getFormData(tableProvider);

		if (data.isNull() && force) {
			if (win.isReadeable())
				win.getRecord().read();
		} else
			this.controlsToBD(data, win.getRecord());
		return win;
	}

	private JRecords getTable(JWin win, String providerRow, JRecords table) throws Exception {
		if (table == null) {
			JObjBDs objs = ((JObjBDs) win.getRecord().getProp(providerRow, false));
			if (objs == null)
				return null;
			if (win.canConvertToURL())
				table = objs.getRawValue();

			if (table == null) {
				table = objs.getValue();
				// table.clearStaticItems();
			}
		}
		if (table != null && !table.isStatic())
			table.setStatic(true);
		return table;
	}

	private JWin getCard(JWin win, String providerRow, JWin table) throws Exception {
		Object obj = processObjectRegisteredByIdForCard(providerRow);
		// return ((JWin) obj);
//		JObjBD objz = ((JObjBD)win.getRecord().getProp(providerRow,false));
//		objz.setValue(((JWin) obj).getRecord());
		return ((JWin) obj);
	}

	private JRecord addRowToTable(JWin win, String providerRow, JRecords table, boolean estatica) throws Exception {
		JRecord rec = (JRecord) win.getRecord().getFixedProp(providerRow).getClase().newInstance();
		rec.setStatic(estatica);
		table.getStaticItems().addElement(rec);
		return rec;
	}

	private boolean addRowNoData(long idRow, int idx, JWin win, String providerRow, JRecords table) throws Exception {
		if (idRow > idx) { // cuando viene un form lov solo viene la fila que interesa
			addRowToTable(win, providerRow, table, false);
			return true;
		}
		return false;
	}

	private void addRowWithData(int idx, JWin win, String providerRow, JRecords table, JWebActionData dataRow) throws Exception {
		JRecord rec;
//		if (!table.hasInfoRow()) {
//			table.getStaticItems().removeAllElements(); // la info no trae info de row, se descarta y se toma como buena la que  viene del cliente
//		}
		if ((rec = table.findRowId(idx)) == null) {
			rec = addRowToTable(win, providerRow, table, true);
			rec.setRowId("" + idx);
		}
		this.controlsToBD(dataRow, rec);

	}

	public void loadData(JBaseWin zActionOwner, boolean force, String zTableProvider, JBaseWin zContextOwner) throws Exception {
		String tableProvider = zTableProvider;
		boolean total = !JWebActionFactory.getCurrentRequest().isParcial();
//		if (tableProvider!=null) tableProvider = zTableProvider;
//		if (JWebActionFactory.getCurrentRequest().hasTableProvider()) tableProvider=JWebActionFactory.getCurrentRequest().getTableProvider();
		zActionOwner.GetBaseDato().clearDynamicFilters();

		if (!isParseable(zActionOwner))
			return;

		JWin win = this.loadWin(zActionOwner, tableProvider, force);

		if (zContextOwner != null)
			win = (JWin) zContextOwner;

		// buscador de multiples provider, para las filas de las grillas
		JList<String> finder = JCollectionFactory.createList();
		JIterator<String> dataArg = JWebActionFactory.getCurrentRequest().getAllArgumentNames();

		while (dataArg.hasMoreElements()) {
			String ds = dataArg.nextElement();
			if (processCardElement(ds, total, finder, win))
				continue;
			if (processTableElement(ds, total, finder, win))
				continue;
			if (processTableExtendElement(ds, total, finder, win))
				continue;

		}

	}

	public void processSwapElement(JAct submit, String zTableProvider) throws Exception {
		if (!submit.hasSelected())
			return;
		JActFieldSwapWins act = (JActFieldSwapWins) submit;
		String data = JWebActionFactory.getCurrentRequest().getArgument("dgf_" + zTableProvider + "_fd-swap");
		if (data == null)
			return;
		act.setSelecteds(act.getOptions().createClone());
		act.getSelecteds().SetEstatico(true);
		act.getSelecteds().setDropListener(submit.getResult().getDropListener());
		buildWinsFromField(act.getSelecteds(), data);
	}

	public boolean processCardElement(String ds, boolean total, JList<String> finder, JWin win) throws Exception {
		int p = ds.indexOf("-");
		if (p == -1)
			return false;
		if (finder.containsElement(ds.substring(0, p)))
			return false;
		int p2 = ds.indexOf("_card_");
		if (p2 == -1)
			return false;
		finder.addElement(ds.substring(0, p));

		JWin card = null;

		int p3 = ds.indexOf("_row_");
		if (p3 != -1) { // tabla
			if (finder.containsElement(ds.substring(0, p3)))
				return false;
			String providerCard = ds.substring(4, p - 3);
			String providerCardObj = ds.substring(p2 + 1, ds.lastIndexOf("_", p3 - 3));

			JWebActionData dataCard = JWebActionFactory.getCurrentRequest().getFormData(providerCard);
			card = getCard(win, providerCardObj, card);
			if (card == null)
				return false;

			processTableElement(ds, total, null, card);
			finder.addElement(ds.substring(0, p3));
			return true;
		}
		String providerCard = ds.substring(4, p - 3);
		String providerCardObj = ds.substring(p2 + 1, p - 3);

		JWebActionData dataCard = JWebActionFactory.getCurrentRequest().getFormData(providerCard);
		card = getCard(win, providerCardObj, card);
		if (card == null || dataCard.isEmpty())
			return false;
		this.controlsToBD(dataCard, card.getRecord());

		return true;
	}

	public boolean processTableElement(String ds, boolean total, JList<String> finder, JWin zwin) throws Exception {
		if (!ds.startsWith("dgt_"))
			return false;
		String data = JWebActionFactory.getCurrentRequest().getArgument(ds);
		String variableTable = null;
		String fieldname = null;
		JWin win = zwin;
		TreeMap<Long, String> fieldnames = new TreeMap<Long, String>();
		String toksField = JTools.getFirstTokens(data, '[', ']');
		StringTokenizer toksFieldNamess = new StringTokenizer(toksField, ",");
		while (toksFieldNamess.hasMoreTokens()) {
			String posandvalue = toksFieldNamess.nextToken();
			if (variableTable == null) {
				variableTable = posandvalue.substring(0, posandvalue.indexOf('|'));
				fieldname = posandvalue.substring(posandvalue.indexOf('|') + 1);

				int p = fieldname.indexOf("-");
				if (p != -1) {
					int p2 = fieldname.indexOf("_card_");
					if (p2 != -1) {
						String providerCardObj = fieldname.substring(p2 + 1, p - 3);
						JWin card = null;
						card = getCard(win, providerCardObj, card);
						if (card != null)
							win = card;
					}
					;
				}

				continue;
			}
			StringTokenizer toksInternalFields = new StringTokenizer(posandvalue, ":");
			long idxField = JTools.getLongFirstNumberEmbedded(toksInternalFields.nextToken());
			String value = toksInternalFields.nextToken();
			fieldnames.put(idxField, value);

		}

		// getCard(win, providerRow, table)
		JRecords table = null;
		table = getTable(win, variableTable, table);
		if (table == null)
			return false;
		int idx = 0;
		List<String> toks = JTools.getTokens(data, '{', '}');
		Iterator<String> it = toks.iterator();
		while (it.hasNext()) {
			String stringRow = it.next();
			StringTokenizer toksFields = new StringTokenizer(stringRow, ",");
			JWebActionData dataRow = new JWebActionData("row_" + idx);
			while (toksFields.hasMoreTokens()) {
				String posandvalue = toksFields.nextToken();
				long idxField = JTools.getLongFirstNumberEmbedded(posandvalue.substring(0, posandvalue.indexOf(":")));
				String value = posandvalue.substring(posandvalue.indexOf(":") + 1);
				dataRow.add(fieldnames.get(idxField), value);
			}
			addRowWithData(idx, win, variableTable, table, dataRow);
			idx++;

		}

		if (table != null) {
			JObjBDs dtable = (JObjBDs) win.getRecord().getProp(variableTable);
			if (dtable != null)
				dtable.setValue(table);
		}

		return true;
	}

	public boolean processTableExtendElement(String ds, boolean total, JList<String> finder, JWin win) throws Exception {
		int idx = 0;
		JRecords table = null;
		String providerRow;
		String variableTable;
		String spos;
		long idRow;
		int p = ds.indexOf("-");
		if (p == -1)
			return false;
//		if (finder!=null) {
//			if (finder.containsElement(ds.substring(0,p))) return false;
//			finder.addElement(ds.substring(0,p));
//		}
		int p2 = ds.indexOf("_row_");
		if (p2 == -1)
			return false;
//		if (finder!=null) {
//			if (finder.containsElement(ds.substring(0,p2))) return false;
//			finder.addElement(ds.substring(0,p2));
//		}
		if (ds.indexOf("__l") != -1) {
			int pInicial = ds.lastIndexOf("_", ds.lastIndexOf("__") - 1) + 1;
			int pos = ds.lastIndexOf("_", p2 - 1) - 1;
			if (pos == -1)
				return false;
			int pos2 = ds.substring(pInicial).indexOf("__");
			if (pos2 == -1)
				return false;

			providerRow = ds.substring(4, pInicial + pos);
			variableTable = ds.substring(pInicial, pInicial + pos2);
			spos = JTools.getNumberEmbedded(ds.substring(p2 + 5, p));
			idRow = spos.equals("") ? 0 : Long.parseLong(spos);

		} else {
			int pInicial = ds.lastIndexOf("_", p2 - 1) + 1;
			int pos = ds.substring(pInicial).indexOf("_");
			if (pos == -1)
				return false;

			providerRow = ds.substring(4, pInicial + pos);
			variableTable = ds.substring(pInicial, pInicial + pos);
			spos = JTools.getNumberEmbedded(ds.substring(p2 + 5, p));
			idRow = spos.equals("") ? 0 : Long.parseLong(spos);

		}

		while (true) {
			JWebActionData dataRow = JWebActionFactory.getCurrentRequest().getFormData(providerRow + "_row_" + idx);
			table = getTable(win, variableTable, table);
			if (table == null)
				break;
			if (dataRow.isNull()) {
				if (!total) {
					if (table.sizeStaticElements() < idx)
						break;
					idx++;
					continue;
				}
				if (addRowNoData(idRow, idx, win, variableTable, table)) {
					idx++;
					continue;
				}
				break;
			}
			if (finder != null)
				finder.addElement(ds.substring(0, p2));
			addRowWithData(idx, win, variableTable, table, dataRow);
			idx++;
		}
		if (table != null) {
			JObjBDs dtable = (JObjBDs) win.getRecord().getProp(variableTable);
			if (dtable != null)
				dtable.setValue(table);
		}
		return true;
	}

	public void controlsToBD(JWebActionData data, JRecord record) throws Exception {
		if (controToBD != null) {
			controToBD.controlsToBD(data, record);
			return;
		}
		JIterator<JWebActionDataField> iter = data.getFieldIterator();
		while (iter.hasMoreElements()) {
			JWebActionDataField field = iter.nextElement();
			if (field == null)
				continue;
			if (record == null)
				continue;
			JObject<?> prop = record.getPropDeep(field.getName(), false);
			if (prop == null) {
				record.addExtraData(field.getName(), field.getValue());
				continue;
			}
//			if (field.getName().equals("previousPriceQuantity"))
//			PssLogger.logDebug(field.getName()+"="+field.getValue());

			String value = field.getValue();
			Object obj = null;
			if (value.startsWith("obj_")) {
				if (value.startsWith("obj_t_")) {
					obj = getRegisterObjectTemp(value.substring(6));
				} else {
					obj = JWebActionFactory.getCurrentRequest().getRegisterObject(value);
				}
			}
			if (prop.isRecord()) {
				if (obj != null && obj instanceof JWin)
					prop.setValue(record.getPropValue(field.getName(), prop, ((JWin) obj).getRecord()));
				else
					prop.setNull();
			} else if (obj != null && obj instanceof JWin)
				prop.setValue(record.getPropValue(field.getName(), prop, ((JWin) obj).GetValorItemClave()));
			else
				prop.setValueFormUI(record.getPropValue(field.getName(), prop, JTools.decodeURLIfNecesary(value)));
		}
	}

	public JBaseWin getRegisterObjectTemp(String zKey) throws Exception {
//		JWebActionFactory.getCurrentRequest().addDataBundle("act_temp", (String) new String(Base64.getDecoder().decode(zKey), StandardCharsets.UTF_8));
//		JWebActionData bundle=loadWinBundle("act_temp");
//		if (bundle==null) return null;
		return this.getBaseWinFromBundle(new String(Base64.getDecoder().decode(zKey), StandardCharsets.UTF_8), true, null);

	}

	public JBaseRecord getRegisterObjectRecTemp(String zKey) throws Exception {
		return this.getBaseRecFromBundle(new String(Base64.getDecoder().decode(zKey), StandardCharsets.UTF_8), null);

	}

	private boolean isExport() throws Exception {
		JWebActionData p = JWebActionFactory.getCurrentRequest().getData("export");
		if (p == null)
			return false;
		return p.get("range") != null;
	}

	public Serializable resolveData() throws Exception {
		String data = JWebActionFactory.getCurrentRequest().getArgument("data_asoc");
		if (data == null || data.equals(""))
			return null;
		return JWebActionFactory.getCurrentRequest().getRegisterObject(data);
	}

	public boolean detectVirtualActionIsMultiple() throws Exception {
		JWebActionData data = JWebActionFactory.getCurrentRequest().getData("export");
		if (data == null)
			return false;
		String dato = data.get("multiple");
		if (dato == null)
			return false;
		return dato.equals("true");

	}

	public String convertActionToURL(BizAction zAction) throws Exception {
		Map<String, String> dict = new HashMap<String, String>();
		dict.put("actionid", zAction.getIdAction());
//		if (zAction.getIdAction().indexOf("anonimus_")!=-1) {
		dict.put("action", Base64.getEncoder().encodeToString(JWebActionFactory.getCurrentRequest().serializeObject(zAction).getBytes(StandardCharsets.UTF_8)));
//		} else {
//			dict.put("owner", Base64.getEncoder().encodeToString(JTools.stringToByteVector( baseWinToURL(zAction.getObjOwner()))));
//			if (zAction.hasSubmit() ) {
//				JAct submit = zAction.getSubmit();
//				if (submit.hasResult())
//					dict.put("result", Base64.getEncoder().encodeToString(JTools.stringToByteVector( baseWinToURL(submit.getResult()))));
//			}
//		}
		return JWebActionFactory.getCurrentRequest().serializeRegisterMapJSON(dict);

	}

	public BizAction convertURLToAction(String sAction) throws Exception {
		Map<String, String> dict = JWebActionFactory.getCurrentRequest().deserializeRegisterMapJSON(sAction);
		BizAction action;
		if (dict.containsKey("action")) {
			action = (BizAction) JWebActionFactory.getCurrentRequest().deserializeObject(new String(Base64.getDecoder().decode(dict.get("action")), StandardCharsets.UTF_8));
		} else {
			JBaseWin win = getRegisterObjectTemp(dict.get("owner"));
			action = win.findActionByUniqueId(dict.get("actionid"));
			if (dict.containsKey("result")) {
				JBaseWin result = getRegisterObjectTemp(dict.get("result"));
				action.getObjSubmit().setResult(result);

			}
		}
		return action;
	}

//	public String baseWinToURL(JBaseWin zOwner) throws Exception {
//		if (zOwner.isWin())
//			((JWin) zOwner).getRecord().keysToFilters(); // para asegurar que tenga filtros antes de serializar HGK
//
//		JWebActionData oData = new JWebActionData("");
//		oData.add("cls", zOwner.getClass().getName());
//		oData.add("uniqueId", zOwner.getUniqueId());
//		oData.add("vision", zOwner.GetVision());
//		if (zOwner.isWin())
//			oData.add("readed", ((JWin) zOwner).getRecord().wasDbRead() ? "S" : "N");
//		if (zOwner.hasDropListener()) {
//			oData.add("drop", Base64.getEncoder().encodeToString(JTools.stringToByteArray(baseWinToJSON(zOwner.getDropListener()))));
//		}
//		if (zOwner.hasDropControlIdListener()) {
//			oData.add("dropControl", Base64.getEncoder().encodeToString(JTools.stringToByteArray(JWebActionFactory.getCurrentRequest().serializeObject(zOwner.getDropControlIdListener()))));
//
//		}
//		if (zOwner.isWin() && zOwner.GetBaseDato().getFilters().isEmpty())
//			((JWin) zOwner).getRecord().keysToFilters();
//		JList<RFilter> filters = zOwner.GetBaseDato().getFilters();
//		if (filters != null && !filters.isEmpty()) {
//			JIterator<RFilter> iter = filters.getIterator();
//			RFilter filter;
//			while (iter.hasMoreElements()) {
//				filter = iter.nextElement();
//				if (filter.isDynamic())
//					continue;
//				oData.add("fltr_" + filter.getField(), filter.getOperator(), filter.getValue());
//			}
//		}
//		if (zOwner.isWin()) {
//			JMap<String, JObject<?>> props = ((JWin) zOwner).getRecord().getProperties();
//			JIterator<String> it = props.getKeyIterator();
//			while (it.hasMoreElements()) {
//				String key = it.nextElement();
//				JObject prop = props.getElement(key);
////					if (!(prop.isRecord()||prop.isRecords())) continue;
//				if (prop.isRecord()) {
//					oData.add("onerec_" + key, JWebActionFactory.getCurrentRequest().registerWinObjectObj((JWin) prop.getObjectValue()));
//				}
//				if (prop.isRecords()) {
//					oData.add("recs_" + key, JWebActionFactory.getCurrentRequest().registerWinObjectObj((JWins) prop.getObjectValue()));
//				}
//				if (!prop.hasValue())
//					continue;
//				if (prop.getUniqueId() == null)
//					continue;
//				oData.add("rec_" + key, prop.getUniqueId());
//			}
//		}
//		return JWebActionFactory.fieldsAsURLString(oData, false);
//	}
	private final ObjectMapper objectMapper = new ObjectMapper();

	// Serializar JBaseWin a JSON y codificar en Base64
	public String baseWinToJSON(JBaseWin win) throws Exception {
		JSerializableBaseWin serializableWin = prepareSerializableWin(win);
		String json = objectMapper.writeValueAsString(serializableWin);

		// Codificar en Base64
		return Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
	}

	public String baseRecToJSON(JBaseRecord rec) throws Exception {
		JSerializableBaseWin serializableWin = prepareSerializableRec(rec, false);
		String json = objectMapper.writeValueAsString(serializableWin);

		// Codificar en Base64
		return Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
	}

	// Método para deserializar desde JSON (como JAct u otros objetos)
	public Object deserializeObject(String json, Class<?> clazz) throws IOException {
		return objectMapper.readValue(json, clazz);
	}

	public JBaseWin createWin(String encodedJson, String id) throws Exception {
		// Decodificar JSON Base64
		String jsonString = new String(org.apache.commons.codec.binary.Base64.decodeBase64(encodedJson), "UTF-8");

		// Deserializar a JSerializableBaseWin usando Gson
		JSerializableBaseWin serializableWin = (JSerializableBaseWin) deserializeObject(jsonString, JSerializableBaseWin.class);

		// Obtener o crear la instancia original de JBaseWin
		String sUniqueId = id != null ? id : serializableWin.uniqueId;
		JBaseWin win = getRememberWin(sUniqueId);
		if (win != null)
			return win;
		JBaseWin actionOwner = getOrCreateWin(serializableWin.cls, sUniqueId);
		actionOwner.setUniqueID(sUniqueId);
			actionOwner.setDropListener(this.getBaseWinFromBundle(new String(Base64.getDecoder().decode(serializableWin.drop), StandardCharsets.UTF_8)));
			actionOwner.setDropControlIdListener((JAct) JWebActionFactory.getCurrentRequest().deserializeObject(new String(Base64.getDecoder().decode(serializableWin.dropControl), StandardCharsets.UTF_8)));
		if (actionOwner.isWin()) {
			((JWin) actionOwner).getRecord().setDatosLeidos(serializableWin.readed);
		}

		// Asignar filtros y propiedades usando los métodos adaptados
		assignFilters(serializableWin, actionOwner.GetBaseDato());
		assignProps(serializableWin, actionOwner.GetBaseDato());

		// Asignar listeners si existen

		if (serializableWin.drop != null) {
//			JWebActionFactory.getCurrentRequest().addDataBundle("act_drop", (String) JTools.byteVectorToString(Base64.getDecoder().decode(zWinBundle.get("drop"))));
//			JWebActionData bundle = loadWinBundle("act_drop");
//			if (bundle == null)
//				return null;
			actionOwner.setDropListener(this.getBaseWinFromBundle((String) JTools.byteVectorToString(Base64.getDecoder().decode(serializableWin.drop))));

		}
		if (serializableWin.dropControl != null) {
			actionOwner.setDropControlIdListener((JAct) JWebActionFactory.getCurrentRequest().deserializeObject(JTools.byteVectorToString(Base64.getDecoder().decode(serializableWin.dropControl))));
		}

		return actionOwner;
	}

	public JBaseRecord createRec(String encodedJson, String id) throws Exception {
		// Decodificar JSON Base64
		String jsonString = new String(org.apache.commons.codec.binary.Base64.decodeBase64(encodedJson), "UTF-8");

		// Deserializar a JSerializableBaseWin usando Gson
		JSerializableBaseWin serializableWin = (JSerializableBaseWin) deserializeObject(jsonString, JSerializableBaseWin.class);

		// Obtener o crear la instancia original de JBaseWin
		String sUniqueId = id != null ? id : serializableWin.uniqueId;
		JBaseRecord actionOwner = getOrCreateRec(serializableWin.cls, sUniqueId);

		// Asignar propiedades básicas
		actionOwner.SetVision(serializableWin.vision);
		if (actionOwner instanceof JRecord) {
			((JRecord) actionOwner).setDatosLeidos(serializableWin.readed);
		}
		if (actionOwner instanceof JRecords) {
			((JRecords) actionOwner).setRecordRef(serializableWin.recordClass);
		}

		// Asignar filtros y propiedades usando los métodos adaptados
		assignFilters(serializableWin, actionOwner);
		assignProps(serializableWin, actionOwner);
		asaignElements(serializableWin, actionOwner);

		return actionOwner;
	}

	private void asaignElements(JSerializableBaseWin serializableWin, JBaseRecord actionOwner) throws Exception {
		if (!(actionOwner instanceof JRecords) || serializableWin.elements == null)
			return;
		JRecords recs = (JRecords<JRecord>) actionOwner;
		recs.setStatic(true);
		for (String element : serializableWin.elements) {
			recs.getStaticItems().addElement(getRegisterObjectRecTemp(element.substring(8)));
		}

	}

	private void assignProps(JSerializableBaseWin serializableWin, JBaseRecord actionOwner) throws Exception {
		if (!(actionOwner instanceof JRecord) || serializableWin.properties == null)
			return;

		// Obtener los campos declarados de la clase concreta
		Class<?> currentClass = actionOwner.getClass();
		Field[] fields = currentClass.getDeclaredFields();

		for (Map.Entry<String, String> entry : serializableWin.properties.entrySet()) {
			String fieldKey = entry.getKey();
			String propValue = entry.getValue();

			if (fieldKey.startsWith("REC_")) {
				JObject<?> obj = ((JRecord) actionOwner).getProp(fieldKey.substring(4));
				obj.setValue(getRegisterObjectRecTemp(propValue.substring(8)));
			} else if (fieldKey.startsWith("RECS_")) {
				JObject<?> obj = ((JRecord) actionOwner).getProp(fieldKey.substring(5));
				obj.setValue(getRegisterObjectRecTemp(propValue.substring(8)));
			} else if (fieldKey.startsWith("UID_")) {
				JObject<?> obj = ((JRecord) actionOwner).getProp(fieldKey.substring(4));
				obj.setUniqueId(propValue);
			} else if (fieldKey.startsWith("PROP_")) {
				JObject<?> obj = ((JRecord) actionOwner).getProp(fieldKey.substring(5));
				obj.setValueFormUI(propValue);
			} else if (fieldKey.startsWith("OTH_")) {
				String fieldName = fieldKey.substring(4);
				try {
					Field field = currentClass.getDeclaredField(fieldName);
					field.setAccessible(true);
					Object value = convertToFieldType(field.getType(), propValue);
					field.set(actionOwner, value);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					System.err.println("Error al asignar campo OTH: " + fieldName + " -> " + e.getMessage());
				}
			} else if (fieldKey.startsWith("SREC_")) {
				Field field = currentClass.getDeclaredField(fieldKey.substring(5));
				field.setAccessible(true);

				JBaseRecord obj = getRegisterObjectRecTemp(propValue.substring(8));
				field.set(actionOwner, obj); 
			} else if (fieldKey.startsWith("SRECS_")) {
				Field field = currentClass.getDeclaredField(fieldKey.substring(6));
				field.setAccessible(true);
				JBaseRecord obj = getRegisterObjectRecTemp(propValue.substring(8));
				field.set(actionOwner, obj); 
			} else if (fieldKey.startsWith("SER_")) {
				String fieldName = fieldKey.substring(4);
				try {
					Field field = currentClass.getDeclaredField(fieldName);
					field.setAccessible(true);

					String serializedData = propValue;
					byte[] decodedBytes = Base64.getDecoder().decode(serializedData);
					String jsonString = new String(decodedBytes, StandardCharsets.UTF_8);
					Serializable obj = (Serializable) JWebActionFactory.getCurrentRequest().deserializeObject(jsonString);

					field.set(actionOwner, obj); 
				} catch (NoSuchFieldException | IllegalAccessException | ClassCastException e) {
					System.err.println("Error al asignar campo SER: " + fieldName + " -> " + e.getMessage());
				}
			}
		}
	}

	private Object convertToFieldType(Class<?> fieldType, String value) {
		if (fieldType == int.class || fieldType == Integer.class) {
			return Integer.parseInt(value);
		} else if (fieldType == long.class || fieldType == Long.class) {
			return Long.parseLong(value);
		} else if (fieldType == double.class || fieldType == Double.class) {
			return Double.parseDouble(value);
		} else if (fieldType == boolean.class || fieldType == Boolean.class) {
			return Boolean.parseBoolean(value);
		} else if (fieldType == String.class) {
			return value;
		}
		// Si el tipo no se reconoce, se retorna el valor tal cual (toString)
		return value;
	}

	private void assignFilters(JSerializableBaseWin serializableWin, JBaseRecord actionOwner) throws Exception {
		if (serializableWin.filters == null)
			return;

		for (SerializableFilter filter : serializableWin.filters) {
			if (!okFilter(actionOwner, filter.field))
				continue;

			// Agregar el filtro al objeto actionOwner
			actionOwner.addFilter(filter.field, filter.value, filter.operator);
			actionOwner.forceFilterToData();
		}
	}

	private JBaseRecord getOrCreateRec(String className, String uniqueId) throws Exception {
		JBaseRecord rec = getRememberRec(uniqueId);
		if (rec != null)
			return rec;

		Class<?> clazz = Class.forName(className);
		JBaseRecord newRec = (JBaseRecord) clazz.newInstance();
		newRec.setUniqueId(uniqueId);
		rememberRec(uniqueId, newRec);

		return newRec;
	}

	private JBaseWin getOrCreateWin(String className, String uniqueId) throws Exception {

		Class<?> clazz = Class.forName(className);
		JBaseWin newWin = (JBaseWin) clazz.newInstance();
		newWin.setUniqueID(uniqueId);
		rememberWin(uniqueId, newWin);

		return newWin;
	}

	// Preparar el objeto auxiliar JSerializableBaseWin
	private JSerializableBaseWin prepareSerializableWin(JBaseWin win) throws Exception {
		JSerializableBaseWin serializableWin = prepareSerializableRec(win.GetBaseDato(), win.canConvertToURL());
		serializableWin.cls = win.getClass().getName();

		if (win.hasDropListener()) {
			serializableWin.drop = Base64.getEncoder().encodeToString(baseWinToJSON(win.getDropListener()).getBytes(StandardCharsets.UTF_8));
		}
		if (win.hasDropControlIdListener()) {
			serializableWin.dropControl = Base64.getEncoder().encodeToString(JWebActionFactory.getCurrentRequest().serializeObject(win.getDropControlIdListener()).getBytes(StandardCharsets.UTF_8));
		}
		return serializableWin;
	}

	private JSerializableBaseWin prepareSerializableRec(JBaseRecord rec, boolean onlyProperties) throws Exception {
		JSerializableBaseWin serializableWin = new JSerializableBaseWin();
		serializableWin.cls = rec.getClass().getName();
		serializableWin.uniqueId = rec.getUniqueId();
		serializableWin.vision = rec.GetVision();
		serializableWin.readed = rec instanceof JRecord && ((JRecord) rec).wasDbRead() && rec.isStatic();

		// Agregar filtros
		serializableWin.filters = new ArrayList<SerializableFilter>();
		JList<RFilter> filters = rec.getFilters();
		if (filters != null && !filters.isEmpty()) {
			JIterator<RFilter> iter = filters.getIterator();
			RFilter filter;
			while (iter.hasMoreElements()) {
				filter = iter.nextElement();
				if (filter.isDynamic())
					continue;
				serializableWin.filters.add(new SerializableFilter(filter.getField(), filter.getOperator(), filter.getValue()));
			}
		}

		// Agregar propiedades
		serializableWin.properties = new HashMap<String, String>();
		if (rec instanceof JRecord) {
			JMap<String, JObject<?>> props = ((JRecord) rec).getProperties();
			JIterator<String> it = props.getKeyIterator();
			while (it.hasMoreElements()) {
				String key = it.nextElement();
				JObject prop = props.getElement(key);
				if (prop.getUniqueId() != null)
					serializableWin.properties.put("UID_" + key, prop.getUniqueId());

				if (prop.isRecord()&&  prop.getInternalVal()!=null) {
					serializableWin.properties.put("REC_" + key, JWebActionFactory.getCurrentRequest().registerRecObjectObj((JRecord) prop.getInternalVal()));
				}
				if (prop.isRecords() &&  prop.getInternalVal()!=null) {
					serializableWin.properties.put("RECS_" + key, JWebActionFactory.getCurrentRequest().registerRecObjectObj((JRecords) prop.getInternalVal()));

				}
				if (!prop.hasValue())
					continue;
				if (serializableWin.readed)
					continue;
				serializableWin.properties.put("PROP_" + key, prop.toRawString());
			}
		}
		if (!onlyProperties) {
			Class<?> currentClass = rec.getClass(); // Clase específica de rec
			Field[] fields = currentClass.getDeclaredFields(); // Obtener los campos declarados

			for (Field field : fields) {
				field.setAccessible(true); // Asegurarse de tener acceso al campo
				 if (Modifier.isTransient(field.getModifiers())) continue;

				String fieldName = field.getName();
				Object fieldValue = field.get(rec); // Obtener el valor del campo

				// Ignorar si el valor es null
				if (fieldValue == null)
					continue;

				// Si es un JRecord, serializar recursivamente
				 if (fieldValue instanceof JObject) {
						continue;
				 } else if (fieldValue instanceof JRecord) {
					String serialized = JWebActionFactory.getCurrentRequest().registerRecObjectObj((JRecord) fieldValue);
					serializableWin.properties.put("SREC_" + fieldName, serialized);
				}
				// Si es un JRecords, serializar recursivamente
				else if (fieldValue instanceof JRecords) {
					String serialized = JWebActionFactory.getCurrentRequest().registerRecObjectObj((JRecords) fieldValue);
					serializableWin.properties.put("SRECS_" + fieldName, serialized);
				} else if (fieldValue instanceof String || fieldValue.getClass().isPrimitive()) {
					serializableWin.properties.put("OTH_" + fieldName, fieldValue.toString());
				} else if (fieldValue instanceof Serializable) {
					Serializable serObj = (Serializable) fieldValue;
					serializableWin.properties.put("SER_" + fieldName, Base64.getEncoder().encodeToString(JWebActionFactory.getCurrentRequest().serializeObject(serObj).getBytes(StandardCharsets.UTF_8)));
				} else if (fieldValue instanceof Serializable) {
					serializableWin.properties.put("OTH_" + fieldName, fieldValue.toString());
				}
			}
		}

		serializableWin.elements = null;
		if (rec instanceof JRecords && rec.isStatic()) {
			JRecords recs = (JRecords) rec;
			serializableWin.recordClass = recs.getBasedClass();
			serializableWin.elements = new ArrayList<String>();
			JIterator<JRecord> it = recs.getStaticIterator();
			while (it.hasMoreElements()) {
				JRecord localrec = it.nextElement();
				serializableWin.elements.add(JWebActionFactory.getCurrentRequest().registerRecObjectObj(localrec));

			}
		}

		return serializableWin;
	}

	// Reconstruir JBaseWin desde JSerializableBaseWin
	private JBaseWin convertToJBaseWin(JSerializableBaseWin serializableWin) throws Exception {
		JBaseWin win = (JBaseWin) Class.forName(serializableWin.cls).newInstance();
		win.setUniqueID(serializableWin.uniqueId);
		win.SetVision(serializableWin.vision);

		if (win.isWin()) {
			((JWin) win).getRecord().setDatosLeidos(serializableWin.readed);
		}

		if (serializableWin.filters != null) {
			for (SerializableFilter filter : serializableWin.filters) {
				win.GetBaseDato().addFilter(filter.field, filter.value, filter.operator);
			}
		}

		if (serializableWin.properties != null) {
			for (Map.Entry<String, String> entry : serializableWin.properties.entrySet()) {
				JObject<?> obj = ((JWin) win).getRecord().getProp(entry.getKey());
				obj.setValue(entry.getValue());
			}
		}

		return win;
	}

	public static class JSerializableBaseWin {
		public boolean readed;
		public String cls;
		public String uniqueId;
		public String vision;
		public String drop;
		public String dropControl;
		public Class recordClass;

		public List<SerializableFilter> filters;
		public Map<String, String> properties;
		public List<String> elements;

		public JSerializableBaseWin() {
		}
	}

	public static class SerializableFilter {
		public String field;
		public String operator;
		public String value;

		public SerializableFilter() {
		}

		public SerializableFilter(String field, String operator, String value) {
			this.field = field;
			this.operator = operator;
			this.value = value;
		}
	}
}
