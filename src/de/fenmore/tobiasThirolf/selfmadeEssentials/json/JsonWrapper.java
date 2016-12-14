package de.fenmore.tobiasThirolf.selfmadeEssentials.json;

public class JsonWrapper {
	
	private String json = "";

	public JsonWrapper wrapJsonTellraw(String name) {
		json = "tellraw " + name + " [\"\"";
		return this;
	}

	@Override
	public String toString() {
		json += "]";
		return json;
	}

	public JsonWrapper addText(String string) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"none\",\"underlined\":\"false\"}";
		return this;
	}

	public JsonWrapper addText(String string, JsonColor color) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"" + color.toString().toLowerCase() + "\",\"underlined\":\"false\"}";
		return this;
	}

	public JsonWrapper addText(String string, JsonFormat format) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"none\",\"" + format.toString().toLowerCase() + "\":\"true\"}";
		return this;
	}

	public JsonWrapper addText(String string, JsonFormat format1, JsonFormat format2) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"none\",\"" + format1.toString().toLowerCase() + "\":\"true\",\"" + format2.toString().toLowerCase() + "\":\"true\"}";
		return this;
	}

	public JsonWrapper addText(String string, JsonFormat format1, JsonFormat format2, JsonFormat format3) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"none\",\"" + format1.toString().toLowerCase() + "\":\"true\",\"" + format2.toString().toLowerCase() + "\":\"true\",\"}" +
				format3.toString().toLowerCase() + "\":true\"";
		return this;
	}

	public JsonWrapper addText(String string, JsonFormat format1, JsonFormat format2, JsonFormat format3, JsonFormat format4) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"none\",\"" + format1.toString().toLowerCase() + "\":\"true\",\"" + format2.toString().toLowerCase() + "\":\"true\",\"}" +
				format3.toString().toLowerCase() + "\":true\",\"" + format4.toString().toLowerCase() + "\":\"true\"";
		return this;
	}

	public JsonWrapper addText(String string, JsonFormat format1, JsonFormat format2, JsonFormat format3, JsonFormat format4, JsonFormat format5) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"none\",\"" + format1.toString().toLowerCase() + "\":\"true\",\"" + format2.toString().toLowerCase() + "\":\"true\",\"" +
				format3.toString().toLowerCase() + "\":true\",\"" + format4.toString().toLowerCase() + "\":\"true\",\"" + format5.toString().toLowerCase() + "\":\"true\"}";
		return this;
	}

	public JsonWrapper addText(String string, JsonColor color, JsonFormat format) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"" + color.toString().toLowerCase() + "\",\"" + format.toString().toLowerCase() + "\":\"true\"}";
		return this;
	}

	public JsonWrapper addText(String string, JsonColor color, JsonFormat format1, JsonFormat format2) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"" + color.toString().toLowerCase() + "\",\"" + format1.toString().toLowerCase() + "\":\"true\",\"" + format2.toString().toLowerCase() + "\":\"true\"}";
		return this;
	}

	public JsonWrapper addText(String string, JsonColor color, JsonFormat format1, JsonFormat format2, JsonFormat format3) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"" + color.toString().toLowerCase() + "\",\"" + format1.toString().toLowerCase() + "\":\"true\",\"" + format2.toString().toLowerCase() + "\":\"true\",\"" +
				format3.toString().toLowerCase() + "\":\"true\"}";
		return this;
	}

	public JsonWrapper addText(String string, JsonColor color, JsonFormat format1, JsonFormat format2, JsonFormat format3, JsonFormat format4) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"" + color.toString().toLowerCase() + "\",\"" + format1.toString().toLowerCase() + "\":\"true\",\"" + format2.toString().toLowerCase() + "\":\"true\",\"" +
				format3.toString().toLowerCase() + "\":\"true\",\"" + format4.toString().toLowerCase() + "\":\"true\"}";
		return this;
	}

	public JsonWrapper addText(String string, JsonColor color, JsonFormat format1, JsonFormat format2, JsonFormat format3, JsonFormat format4, JsonFormat format5) {
		json += ",{\"text\":\"" + string + "\",\"color\":\"" + color.toString().toLowerCase() + "\",\"" + format1.toString().toLowerCase() + "\":\"true\",\"" + format2.toString().toLowerCase() + "\":\"true\",\"" +
				format3.toString().toLowerCase() + "\":\"true\",\"" + format4.toString().toLowerCase() + "\":\"true\",\"" + format5.toString().toLowerCase() + "\":\"true\"}";
		return this;
	}

	public JsonWrapper addClickEvent(ClickAction action, String text) {
		remLastChar(json);
		json += ",\"clickEvent\":{\"action\":\"" + action.toString().toLowerCase() + "\",\"value\":\"" + text + "\"}}";
		return this;
	}
	
	public JsonWrapper addHoverEvent(HoverAction action, String text) {
		remLastChar(json);
		json += ",\"hoverEvent\":{\"action\":\"" + action.toString().toLowerCase() + "\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + text + "\"}]}}}";
		return this;
	}
	
	public JsonWrapper addHoverEvent(HoverAction action, String text, JsonColor color) {
		remLastChar(json);
		json += ",\"hoverEvent\":{\"action\":\"" + action.toString().toLowerCase() + "\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + text + "\",\"color\":\"" + color.toString().toLowerCase() + "\"}]}}}";
		return this;
	}

	private void remLastChar(String text) {
		json = text.substring(0, text.length() - 1);
	}

}
