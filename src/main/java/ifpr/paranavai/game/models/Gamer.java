package ifpr.paranavai.game.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_jogador")
public class Gamer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_gamer", unique = true, nullable = false)
    private Integer gamerId;
    @Column(name = "nome", unique = true, nullable = false, length = 100)
    private String nome;

    public Gamer() {}
    public Gamer(String nome) {
        this.nome = nome;
    }
    public Integer getGamerId() {
        return gamerId;
    }

    public void setGamerId(Integer gamerId) {
        this.gamerId = gamerId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}