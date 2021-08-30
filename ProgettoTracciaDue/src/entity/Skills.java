package entity;

import java.util.ArrayList;

public class Skills {

    //ATTRIBUTI
	private String nomeSkill;
	private String codSkill;
	private ArrayList<Membro> membri;
	
    //COSTRUTTORI

	public Skills(String nomeSkill, String codSkill) {
		super();
		this.nomeSkill = nomeSkill;
		this.codSkill = codSkill;
	}
	
	public Skills(String nomeSkill) {
		super();
		this.nomeSkill = nomeSkill;
	}

	public String getNomeSkill() {
		return nomeSkill;
	}


	public void setNomeSkill(String nomeSkill) {
		this.nomeSkill = nomeSkill;
	}
	
	public ArrayList<Membro> getMembri() {
		return membri;
	}

	public void setMembri(ArrayList<Membro> membri) {
		this.membri = membri;
	}

	public String getCodSkill() {
		return codSkill;
	}

	public void setCodSkill(String codSkill) {
		this.codSkill = codSkill;
	}

	@Override
	public String toString() {
		return "Skills [nomeSkill=" + nomeSkill + ", codSkill=" + codSkill + "]";
	}

}
