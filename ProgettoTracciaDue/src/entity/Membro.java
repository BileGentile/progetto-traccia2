package entity;

import java.util.ArrayList;

public class Membro {
	
	//ATTRIBUTI
	private String Nome;
	private String Cognome;
	private String CF;
    private String Ruolo;
    private int SalarioMedio;
    private ArrayList<Skills> skills;
    private ArrayList<Progetto> progetti;
    private ArrayList<Meeting> meeting;

    
    //COSTRUTTORI
	public Membro(String nome, String cognome, String cF, String ruolo, int salarioMedio) {
		super();
		Nome = nome;
		Cognome = cognome;
		CF = cF;
		Ruolo = ruolo;
		SalarioMedio = salarioMedio;
	}
	
	public Membro(String CF) {
		this.CF = CF;
	}

	//GETTERS & SETTERS
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public String getCognome() {
		return Cognome;
	}
	
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	
	public String getCF() {
		return CF;
	}
	
	public void setCF(String cF) {
		CF = cF;
	}
	
	public String getRuolo() {
		return Ruolo;
	}

	public void setRuolo(String ruolo) {
		Ruolo = ruolo;
	}
	
	public int getSalarioMedio() {
		return SalarioMedio;
	}
	
	public void setSalarioMedio(int salario) {
		SalarioMedio = salario;
	}
	
	public ArrayList<Skills> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skills> skills) {
		this.skills = skills;
	}
	
	public ArrayList<Progetto> getProgetti() {
		return progetti;
	}

	public void setProgetti(ArrayList<Progetto> progetti) {
		this.progetti = progetti;
	}

	public ArrayList<Meeting> getMeeting() {
		return meeting;
	}

	public void setMeeting(ArrayList<Meeting> meeting) {
		this.meeting = meeting;
	}

	@Override
	public String toString() {
		return "Membro [Nome=" + Nome + ", Cognome=" + Cognome + ", CF=" + CF + ", Ruolo=" + Ruolo + ", SalarioMedio="
				+ SalarioMedio + ", skills=" + skills + ", progetti=" + progetti + "]";
	}

}
