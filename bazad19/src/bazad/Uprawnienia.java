package bazad;

public class Uprawnienia {
   String userName;
   String password;
   boolean edit;
   boolean add;
   boolean delete;
   boolean exportHtml;
   boolean exportExcell;
   boolean admin;

	public Uprawnienia(String userName, String password, boolean edit,
			boolean add, boolean delete, boolean exportHtml,
			boolean exportExcell, boolean admin) {
	super();
	this.userName = userName;
	this.password = password;
	this.edit = edit;
	this.add = add;
	this.delete = delete;
	this.exportHtml = exportHtml;
	this.exportExcell = exportExcell;
	this.admin = admin;
}

	@Override
	public String toString() {
		return "Uprawnienia [userName=" + userName + ", password=" + password + ", edit=" + edit + ", add=" + add
				+ ", delete=" + delete + ", exportHtml=" + exportHtml + ", exportExcell=" + exportExcell + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isExportHtml() {
		return exportHtml;
	}

	public void setExportHtml(boolean exportHtml) {
		this.exportHtml = exportHtml;
	}

	public boolean isExportExcell() {
		return exportExcell;
	}

	public void setExportExcell(boolean exportExcell) {
		this.exportExcell = exportExcell;
	}
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
   
}
