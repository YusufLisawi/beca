package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Produit;
import service.ProduitDAOImpl;

@FacesConverter(value = "produitConverter")
public class ProduitConverter implements Converter{
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String value) {
        if(value == null) {
            return null;
        }
        return new ProduitDAOImpl().getProduitById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return (value instanceof Produit) ? String.valueOf(((Produit) value).getId()) : null;    }
}
