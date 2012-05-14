package Cosm;

import org.json.JSONException;
import org.json.JSONObject;

public class Unit {
	private String type;
	private String symbol;
	private String label;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		jo.putOpt("type",type);
		jo.putOpt("symbol", symbol);
		jo.putOpt("label", label);		
		return jo;
	}
	
}
