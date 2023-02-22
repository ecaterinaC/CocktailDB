package assignment.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data // automatically generates Getter and Setter methods for each of the fields
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientPOJO {

    private String idIngredient;
    private String strIngredient;
    private String strDescription;
    private String strType;
    private String strAlcohol;
    private String strABV;

    /**
     * Getters and Setters for each field executed manually would look like that, however @Data annotation from lombak gives this result within seconds
     */

    /*
    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrAlcohol() {
        return strAlcohol;
    }

    public void setStrAlcohol(String strAlcohol) {
        this.strAlcohol = strAlcohol;
    }

    public String getStrABV() {
        return strABV;
    }

    public void setStrABV(String strABV) {
        this.strABV = strABV;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }
}

     */

}
