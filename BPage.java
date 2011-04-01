import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author claudiu
 *
 */
public class BPage extends ModelPrimitiva{
	private String path;//Calea catre fisier
	private Page p = new Page();//Pagina clona pentru "pagina" curenta
	/**
	 * Constructorul clasei BPage, initializeaza campurile path, nume si body si construieste corpul obiectului(body)
	 * @param path - calea catre fisier
	 * @param name - numele noului obiect
	 */
	public BPage(String path, String name){
		super();
		this.path = path;
		this.nume = name;
		body = new char[30][60];
		MakeBody();//Construieste corpul obiectului
	}
	/**
	 * Redimensioneaza obiectul pagina
	 * @param i
	 * @param j - noile dimensiuni ale obiectului pagina
	 */
	public void resize (int i, int j){
		super.resize(i, j);
		p.resizePage(i, j);//Redimensionez si pagina clona
	}
		
	/**
	 * Construieste corpul obiectului pagina
	 */
	
	public void MakeBody(){
		Plugins plg = new Plugins();
		Manager m = new Manager();//Lista proprie de obiecte care vor fi adaugate in pagina obiect
		Scanner sc;
		try{
		sc = new Scanner(new FileInputStream(new File(path)));//Citesc din fisier
		}
		catch (Exception e){
			e.printStackTrace();
			return;
		}
		String s = sc.nextLine();//Citesc prima linie
		ArrayList<String> a = new ArrayList<String>();//Lista de cuvinte din fiecare comanda
		//Procedeul de citire este asemenea celui din clasa MainClass
		while (s.equals("quit") == false){ //Cat timp continui sa citesc comenzi
			//Analizez linia de comanda:	
			int i = 0,l;
			char ap = "'".charAt(0);
			boolean pass = false;
			while (i<s.length()){
				String c = "";
				char pss = s.charAt(i);
				l=0;
				pass = false;
				//Daca e spatiu sari:
				if (pss == ' ') {
					i++;
					pass = true;
					continue;
				}
				//Daca e apostrof:
				if (ap == pss){
					i++;
					pass = true;
					while(s.charAt(i) != ap){
						c += s.charAt(i);
						i++;
						l++;
					}
					i++;
					a.add(c);
				}
				//Daca e litera sau altceva
				if (pass == false){
					while (s.charAt(i)!=' '){
						c += s.charAt(i);
						i++;
						l++;
						if (i == s.length()) break;
					}
					a.add(c);
				}
				if (i == s.length()) break;
			}
			if (a.get(0).equals("new") == true){
				//Creez un model nou:
				ModelPrimitiva tl;
				String ce, name, text,split;
				char x;
				ce = a.get(1);
				name = a.get(2);
				if (ce.equals("TextLine") == true){
					text = a.get(3);
					tl = new TextLine(name,text); //Creez un TextLine
					m.add(tl);//Adaug in manager
				}
				if (ce.equals("TextArea")== true){
					split = a.get(3);
					text = a.get(4);
					if (split.equals("true")==true){
						tl = new TextArea(name,true,text);
					}
					else tl = new TextArea(name,false,text);
					m.add(tl);//Adaug in manager
				}
				if (ce.equals("TextBox")==true){
					split = a.get(3);
					x = a.get(4).charAt(0);
					text = a.get(5);
					if (split.equals("true")==true){
						tl = new TextBox(name,true,x,text);
					}				
					else tl = new TextBox(name,false,x,text);					
					m.add(tl);//Adaug in manager
				}
				if (ce.equals("List")==true){
					ArrayList<String> aux = new ArrayList<String>();
					for (int k =4; k<a.size();k++){
						aux.add(a.get(k));
					}
					x = a.get(3).charAt(0);
					tl = new Lista(name,x,aux);
					m.add(tl);//Adaug in manager
				}
				if (ce.equals("Title")== true){
					x = a.get(3).charAt(0);
					text = a.get(4);
					tl = new Title(name, x,text);
					m.add(tl);//Adaug in manager
				}
				if (ce.equals("Rectangle")==true){
					x = a.get(3).charAt(0);
					tl = new Rectangle(name,x);
					m.add(tl);//Adaug in manager
				}
				//Creare BonusPage:
				if (ce.equals("Page")==true){
					String path = a.get(3);
					tl = new BPage(path, name);
					m.add(tl);
				}
			//Termin creare obiect nou
			}
			
			//Operatia MOVE:
			if (a.get(0).equals("move")==true){
				String name = a.get(1);
				int i2 = Integer.parseInt(a.get(2));
				int j = Integer.parseInt(a.get(3));
				for (int k=0; k<m.getNo(); k++){
					if (m.lista[k].nume.equals(name)==true){
						m.lista[k].move(i2,j);
					}
				}
			}
			
			//Operatia RESIZE:
			if (a.get(0).equals("resize")==true){
				String name = a.get(1);
				int i2 = Integer.parseInt(a.get(2));
				int j = Integer.parseInt(a.get(3));
				for (int k=0; k<m.getNo(); k++){
					if (m.lista[k].nume.equals(name)==true){
						m.lista[k].resize(i2, j);
					}
				}
			}
			
			//Operatia resizePage
			if (a.get(0).equals("resizePage")==true){
				int i2 = Integer.parseInt(a.get(1));
				int j = Integer.parseInt(a.get(2));
				p.resizePage(i2, j);
			}
			
			//Operatia bringFront:
			if (a.get(0).equals("bringFront")==true){
				String name = a.get(1);
				for (int k=0; k<m.getNo(); k++){
					if (m.lista[k].nume.equals(name)==true){
						m.lista[k].bringFront(m);
					}
				}
			}
			
			//Operatia sendBack:
			if (a.get(0).equals("sendBack")==true){
				String name = a.get(1);
				for (int k=0; k<m.getNo(); k++){
					if (m.lista[k].nume.equals(name)==true){
						m.lista[k].sendBack(m);
					}
				}
			}
			
			//Operatia DELETE:
			if (a.get(0).equals("delete")==true){
				String name = a.get(1);
				for (int k=0; k<m.getNo(); k++){
					if (m.lista[k].nume.equals(name)==true){
						m.lista[k].delete(m);
					}
				}
			}
			
			
			//Plugin-uri:
			if (a.get(0).equals("invoke")== true){
				if (a.get(1).equals("doubleWidth")==true){
					String name = a.get(2);
					for (int k=0; k<m.getNo(); k++){
						if (m.lista[k].nume.equals(name)==true){
							plg.doubleWidth(m.lista[k]);
						}
					}
				}
				if (a.get(1).equals("doubleHeight")==true){
					String name = a.get(2);
					for (int k=0; k<m.getNo(); k++){
						if (m.lista[k].nume.equals(name)==true){
							plg.doubleHeight(m.lista[k]);
						}
					}
				}
				if (a.get(1).equals("maximize")==true){
					String name = a.get(2);
					for (int k=0; k<m.getNo(); k++){
						if (m.lista[k].nume.equals(name)==true){
							plg.maximize(m.lista[k],p);
						}
					}
				}
				if (a.get(1).equals("center")==true){
					String name = a.get(2);
					for (int k=0; k<m.getNo(); k++){
						if (m.lista[k].nume.equals(name)==true){
							plg.center(m.lista[k],p);
						}
					}
				}
				if (a.get(1).equals("makeSquare")==true){
					String name = a.get(2);
					for (int k=0; k<m.getNo(); k++){
						if (m.lista[k].nume.equals(name)==true){
							plg.makeSquare(m.lista[k]);
						}
					}
				}
				if (a.get(1).equals("toLowerCase")==true){
					String name = a.get(2);
					for (int k=0; k<m.getNo(); k++){
						if (m.lista[k].nume.equals(name)==true){
							plg.toLowerCase(m.lista[k]);
						}
					}
				}
				if (a.get(1).equals("toUpperCase")==true){
					String name = a.get(2);
					for (int k=0; k<m.getNo(); k++){
						if (m.lista[k].nume.equals(name)==true){
							plg.toUpperCase(m.lista[k]);
						}
					}
				}
			}//S-a terminat Plugin-uri
			a.removeAll(a);
			s = sc.nextLine();
		}//S-a terminat bucla, am lista manager cu toate obiectele
	//Creare corp:
		for (int i=0; i<p.getHeight() ; i++){
			for (int j=0; j<p.getWidth(); j++){
				p.finalpage[i][j] = ' ';//Initializez corpul paginii clona cu spatii
			}
		}
		int i = m.getNo();
		for (int j=0;j<i;j++){//Parcurg lista si desenez fiecare obiect din ea pe pagina clona
			m.lista[j].draw(p);
		}
		
		body = p.finalpage.clone();//Copiez corpul paginii clona in corpul paginii obiect
		
	}//S-a terminat MakeBody
	
}
