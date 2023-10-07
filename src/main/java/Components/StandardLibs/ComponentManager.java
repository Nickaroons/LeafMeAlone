package Components.StandardLibs;

import Components.SpriteRenderer;

import java.awt.*;
import java.util.HashMap;

public class ComponentManager {

    private HashMap<String, Component> components = new HashMap<>();

    public ComponentManager() {

    }

    public Component getComponent(String name) {
        return components.get(name);
    }

    public void newComponent(String name, Component component) {
        components.put(name, component);
    }

    public final void update() {
        for (String key : components.keySet()) {
            components.get(key).update();
        }
    }

    public final void renderFirst(Graphics g) {
        for (String key : components.keySet()) {
            if (components.get(key).renderBeforeObject() && components.get(key) instanceof SpriteRenderer == false){
                components.get(key).render(g);
            }
        }
    }
    public final void renderLast(Graphics g) {
        for (String key : components.keySet()) {
            if (!components.get(key).renderBeforeObject() && components.get(key) instanceof SpriteRenderer == false){
                components.get(key).render(g);
            }
        }
    }

    public final void renderSprite(Graphics g) {
        for (String key : components.keySet()) {
            if ((components.get(key) instanceof SpriteRenderer)){
                components.get(key).render(g);
            }
        }
    }

    public HashMap<String, Component> getComponents() {
        return components;
    }


}
