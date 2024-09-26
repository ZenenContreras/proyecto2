import java.util.List;

public class Clausula {
    private List<Literal> literales;
    private int indice;  // Subíndice de la cláusula

    public Clausula(List<Literal> literales, int indice) {
        this.literales = literales;
        this.indice = indice;
    }

    public List<Literal> getLiterales() {
        return literales;
    }

    public int getIndice() {
        return indice;
    }

    @Override
    public String toString() {
        return "Cláusula " + indice + ": " + literales.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clausula clausula = (Clausula) o;
        return this.literales.equals(clausula.getLiterales());
    }

    @Override
    public int hashCode() {
        return literales.hashCode();
    }

    public boolean containsComplement(Literal literal) {
        return literales.contains(literal.getComplemento());
    }
}
