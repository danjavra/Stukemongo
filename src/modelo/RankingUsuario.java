package modelo;

public class RankingUsuario {
    private int ranking;
    private String user;
    private Integer numtotalpokemon;


    public RankingUsuario() {
    }

    public RankingUsuario(int ranking, String user, Integer numtotalpokemon) {
        this.ranking = ranking;
        this.user = user;
        this.numtotalpokemon = numtotalpokemon;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getNumtotalpokemon() {
        return numtotalpokemon;
    }

    public void setNumtotalpokemon(Integer numtotalpokemon) {
        this.numtotalpokemon = numtotalpokemon;
    }

    @Override
    public String toString() {
        return "RankingUsuario{" + "ranking=" + ranking + ", user=" + user + ", numtotalpokemon=" + numtotalpokemon + '}';
    }

}