import java.util.HashMap;
import java.util.Map;

public class Component extends SparePart{
//    private static Map <Integer, SparePart> misComponent;
    private Map<Integer, SparePart> misComponent ;

    public Component(int code, String text, double price) {
        super(code, text, price);
        this.misComponent = new HashMap<>();
    }

    public Map<Integer, SparePart> getMisComponent() {
        if (misComponent == null) {
            misComponent = new HashMap<>();
        }
        return misComponent;
    }

    public void setMisComponent(Map<Integer, SparePart> misComponent) {
        this.misComponent = misComponent;
    }

    public boolean addComponent(SparePart miComponent){
        if(!misComponent.containsKey(miComponent.getCode())){
            misComponent.put(miComponent.getCode(), miComponent);
            return true;
        }
        return false;
    }

    public boolean estaComponent(int codigo){

        if(misComponent.containsKey(codigo)){
            return true;

        }
        return false;
    }

    public String getComponents(int codigo) {
        SparePart sp = misComponent.get(codigo);
        if (sp == null){

            return null;
        }
        return sp.toString();
    }



}
