package entity;

public class Skills {

    //ATTRIBUTI

	private String CodFiscale;
	private String Skill;
    
    //COSTRUTTORI
    public Skills(String codFiscale, String skill) {
		super();
		CodFiscale = codFiscale;
		Skill = skill;
    }
	
	
	public String getSkill() {
		return Skill;
	}

	public void setSkill(String skill) {
		Skill = skill;
	}
	
	public String getCodFiscale() {
		return CodFiscale;
	}
	public void setCodFiscalea(String codFiscale) {
		CodFiscale = codFiscale;
	}
	
	@Override
	public String toString() {
		return "Skills [CodFiscale=" + CodFiscale + ", Skill=" + Skill + ", ]";
	}
	

}

