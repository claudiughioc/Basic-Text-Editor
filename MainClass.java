import java.util.ArrayList;
import java.util.Scanner;
/**
 * Citesc linie cu linie fiecare comanda si o interpretez
 * @author Claudiu Ghioc
 *
 */


public class MainClass{
	public static void main(String [] args){
		Page p = new Page(); // Creez pagina 30 x 60 plina cu spatii
		Manager m = new Manager(); // Creez o lista de modele sau un manager
		Scanner sc = new Scanner(System.in);
		Plugins plg = new Plugins();
		String s = sc.nextLine(); //Citesc prima linie
		ArrayList<String> a = new ArrayList<String>();//Lista de cuvinte din fiecare comanda
		while (s.equals("quit") == false){ //Cat timp continui nu intalnesc comanda quit
			
			//Analizez linia de comanda:	
			int i = 0,l;
			char ap = "'".charAt(0);
			boolean pass = false;
			while (i<s.length()){
				String c = "";//Initializez fiecare cuvant 
				char pss = s.charAt(i);//Fiecare caracter citit din comanda
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
					while(s.charAt(i) != ap){//Parcurg pana gasesc alt apostrof
						c += s.charAt(i);//Adaug in cuvant
						i++;
						l++;
					}
					i++;
					a.add(c);//Adaug cuvantul in lista
				}
				//Daca e litera sau altceva
				if (pass == false){
					while (s.charAt(i)!=' '){//Parcurg pana la urmatorul spatiu exclusiv
						c += s.charAt(i);//Adaug in cuvant
						i++;
						l++;
						if (i == s.length()) break;
					}
					a.add(c);//Adaug cuvantul in lista
				}
				if (i == s.length()) break;//Daca ajung intre timp la capatul liniei ma opresc
			}
			
			//Analizez fiecare cuvant in parte
			if (a.get(0).equals("new") == true){
				//Creez un model nou:
				ModelPrimitiva tl;
				String ce, name, text,split;
				char x;
				ce = a.get(1);//Reprezinta tipul obiectului creat
				name = a.get(2);//Numele lui...
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
					if (split.equals("true")==true){//Creez in functie de split
						tl = new TextBox(name,true,x,text);
					}				
					else tl = new TextBox(name,false,x,text);					
					m.add(tl);//Adaug in manager
				}
				if (ce.equals("List")==true){
					ArrayList<String> aux = new ArrayList<String>();//Lista parametrilor listei
					for (int k =4; k<a.size();k++){
						aux.add(a.get(k));//Adaug fiecare element al listei
					}
					x = a.get(3).charAt(0);
					tl = new Lista(name,x,aux);//Creez lista
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
					String path = a.get(3);//Calea fisierului
					tl = new BPage(path, name);//Creez pagina
					m.add(tl);//Adaug in manager
				}
			//Termin creare obiect nou
			}
			
			//Operatia MOVE:
			if (a.get(0).equals("move")==true){
				String name = a.get(1);//Nume obiect
				int i2 = Integer.parseInt(a.get(2));
				int j = Integer.parseInt(a.get(3));
				for (int k=0; k<m.getNo(); k++){//Parcurg managerul si caut dupa nume
					if (m.lista[k].nume.equals(name)==true){
						m.lista[k].move(i2,j);//Apelez metoda move din clasa parinte
					}
				}
			}
			
			//Operatia RESIZE:
			if (a.get(0).equals("resize")==true){
				String name = a.get(1);//Nume obiect
				int i2 = Integer.parseInt(a.get(2));
				int j = Integer.parseInt(a.get(3));
				for (int k=0; k<m.getNo(); k++){//Parcurg managerul si caut dupa nume
					if (m.lista[k].nume.equals(name)==true){
						m.lista[k].resize(i2, j);//Apelez metoda resize din clasa parinte
					}
				}
			}
			
			//Operatia resizePage
			if (a.get(0).equals("resizePage")==true){
				int i2 = Integer.parseInt(a.get(1));
				int j = Integer.parseInt(a.get(2));
				p.resizePage(i2, j);//Apelez metoda resizePage pentru pagina curenta
			}
			
			//Operatia bringFront:
			if (a.get(0).equals("bringFront")==true){
				String name = a.get(1);
				for (int k=0; k<m.getNo(); k++){
					if (m.lista[k].nume.equals(name)==true){//Parcurg managerul si caut dupa nume
						m.lista[k].bringFront(m);//Apelez metoda bringFront din clasa parinte
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
				for (int k=0; k<m.getNo(); k++){//Parcurg managerul si caut dupa nume obiectul
					if (m.lista[k].nume.equals(name)==true){
						m.lista[k].delete(m);//Apelez metoda delete din clasa parinte
					}
				}
			}
			
			//PRINTAREA:
			if (a.get(0).equals("print")==true){
				p.print(m);//Apelez metoda print pentru pagina curent, cu parametru: managerul de obiecte
			}
			
			
			//Plugin-uri:
			if (a.get(0).equals("invoke")== true){
				//Pentru fiecare plugin voi cauta in manager dupa nume si voi apela
				//metoda respectiva in clasa Plugins
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
			a.removeAll(a);//Sterg toate cuvintele pentru urmatoarea linie de comanda
			s = sc.nextLine();//Citesc urmatoarea linie de comanda
		}//S-a terminat bucla
		
	}//End of main
}//End of class
