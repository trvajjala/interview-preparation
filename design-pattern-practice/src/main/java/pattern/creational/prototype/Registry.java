package pattern.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class Registry {


    static Map<String,Item> items=new HashMap<>();

    static{
	items.put("movie", new Movie("Blood And Bone","Michal James White",20.00));
	items.put("game", new Game("Kumffo","Jackie Chain",22.00));
    }

    public Item getItem(String name) throws CloneNotSupportedException{

	return (Item) items.get(name).clone();
    }




}
