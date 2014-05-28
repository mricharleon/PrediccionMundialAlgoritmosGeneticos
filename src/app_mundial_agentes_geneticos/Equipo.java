package app_mundial_agentes_geneticos;

public class Equipo {
	private String pais;
    private String grupo;
    private String ranking;
    private String copas;
    private String partido1;
    private String partido2;
    private String partido3;
 
    public Equipo(String pais, String ranking, String grupo, String copas, String partido1, String partido2, String partido3) {
        setPais(pais);
        setRanking(ranking);
        setGrupo(grupo);
        setCopas(copas);
        setPartido1(partido1);
        setPartido2(partido2);
        setPartido3(partido3);
    }

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getCopas() {
		return copas;
	}

	public void setCopas(String copas) {
		this.copas = copas;
	}

	public String getPartido1() {
		return partido1;
	}

	public void setPartido1(String partido1) {
		this.partido1 = partido1;
	}

	public String getPartido2() {
		return partido2;
	}

	public void setPartido2(String partido2) {
		this.partido2 = partido2;
	}

	public String getPartido3() {
		return partido3;
	}

	public void setPartido3(String partido3) {
		this.partido3 = partido3;
	}
   
	public String toString() {
		return " "+pais+"\t\t"+
				ranking+"\t"+
				copas+"\t"+
				grupo+"\t"+
				partido1+"\t"+
				partido2+"\t"+
				partido3;
        
    }

}










