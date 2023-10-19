package bazad;

import javax.swing.JTextField;

public class TextWiek extends JTextField{
  public TextWiek() {
	  
  }
  public String getSqlString() {
	  String str = this.getText();
	  if(str.equals("")) return "";
	  int pos = this.getText().indexOf("-");
	  if(pos==-1) {
		  return "\n and wiek = " + str;
	  }	else {
		  String before = str.substring(0, pos);
		  String after = str.substring(pos+1);
		  //System.out.println("'"+before+ "'"+after);
		  return "\n and wiek >= "+before+" and wiek <= "+ after;
	  } 
	  //else return "and wiek = "+this.getText();
  }
}
