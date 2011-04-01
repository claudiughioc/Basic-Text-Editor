/**
 * 
 * @author claudiu
 * Interfata pentru toate obiectele grafice
 */
public interface GraphicPrimitive {
	void move(int i, int j);
	void resize (int i, int j);
	void bringFront(Manager m);
	void sendBack(Manager m);
	void delete(Manager m);
	void draw(Page p);
}
