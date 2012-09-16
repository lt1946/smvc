package com.iatb.bean;

public class ColumnBean {
	
	private String ColumnLabel;
	private String ColumnName;
	private String SchemaName;
	private int ColumnType;
	private String ColumnTypeName;
	private boolean isNull;
	private String defaultString;
	private int maxlength;
	private String comments;
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}
	public String getDefaultString() {
		return defaultString;
	}
	public void setDefaultString(String defaultString) {
		this.defaultString = defaultString;
	}
	public boolean isNull() {
		return isNull;
	}
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	public String getColumnLabel() {
		return ColumnLabel;
	}
	public void setColumnLabel(String columnLabel) {
		ColumnLabel = columnLabel;
	}
	public String getColumnName() {
		return ColumnName;
	}
	public void setColumnName(String columnName) {
		ColumnName = columnName;
	}
	public String getSchemaName() {
		return SchemaName;
	}
	public void setSchemaName(String schemaName) {
		SchemaName = schemaName;
	}
	public int getColumnType() {
		return ColumnType;
	}
	public void setColumnType(int columnType) {
		ColumnType = columnType;
	}
	public String getColumnTypeName() {
		return ColumnTypeName;
	}
	public void setColumnTypeName(String columnTypeName) {
		ColumnTypeName = columnTypeName;
	}
	public ColumnBean() {
		super();
	}
	@Override
	public String toString() {
		return "ColumnBean [ColumnLabel=" + ColumnLabel + ", ColumnName="
				+ ColumnName + ", ColumnType=" + ColumnType
				+ ", ColumnTypeName=" + ColumnTypeName + ", SchemaName="
				+ SchemaName + ", comments=" + comments + ", defaultString="
				+ defaultString + ", isNull=" + isNull + ", maxlength="
				+ maxlength + "]";
	}
	
	
}
