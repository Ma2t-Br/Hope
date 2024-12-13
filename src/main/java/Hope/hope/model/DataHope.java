package Hope.hope.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hope")
public class DataHope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "Titre", nullable = false)
    private String titre;

    @Column(name = "Domaine", nullable = false)
    private String domaine;

    @Lob
    @Column(name = "Lien", nullable = false)
    private String lien;

    @Lob
    @Column(name = "Description_simple")
    private String descriptionSimple;

    @Lob
    @Column(name = "Description_detaillee")
    private String descriptionDetaillee;

    @Lob
    @Column(name = "Acces")
    private String acces;

    @Lob
    @Column(name = "Feedback_utilisateurs")
    private String feedbackUtilisateurs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getDescriptionSimple() {
        return descriptionSimple;
    }

    public void setDescriptionSimple(String descriptionSimple) {
        this.descriptionSimple = descriptionSimple;
    }

    public String getDescriptionDetaillee() {
        return descriptionDetaillee;
    }

    public void setDescriptionDetaillee(String descriptionDetaillee) {
        this.descriptionDetaillee = descriptionDetaillee;
    }

    public String getAcces() {
        return acces;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }

    public String getFeedbackUtilisateurs() {
        return feedbackUtilisateurs;
    }

    public void setFeedbackUtilisateurs(String feedbackUtilisateurs) {
        this.feedbackUtilisateurs = feedbackUtilisateurs;
    }

}