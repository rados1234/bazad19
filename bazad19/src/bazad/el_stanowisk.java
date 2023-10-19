package bazad;

public class el_stanowisk {
  int id;
  String nazwa;
  public el_stanowisk(int id, String nazwa) {
	super();
	this.id = id;
	this.nazwa = nazwa;
  }
  
  public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNazwa() {
	return nazwa;
}

public void setNazwa(String nazwa) {
	this.nazwa = nazwa;
}

public String toString() {
	  return nazwa;
  }
}
