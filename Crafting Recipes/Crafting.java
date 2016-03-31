import java.util.*;
import java.io.*;

public class Crafting {
	// private ArrayList<ArrayList<ArrayList<String>>> recipes = new
	// ArrayList<ArrayList<ArrayList<String>>>();
	private ArrayList<String>[] recipes;
	private String[] itemNames;
	private ArrayList<String[]> data = new ArrayList<String[]>();
	private Map tree = new TreeMap();
	private int itemNL;
	private String itemsWor = "";

	public Crafting(Scanner beta) {
		int nOfR = Integer.parseInt(beta.nextLine());
		itemNames = new String[nOfR];
		recipes = new ArrayList[nOfR];
		String[] recipites = new String[nOfR];
		for (int i = 0; i < nOfR; i++) {
			// recipes[i].add((String)beta.next());
			recipites[i] = beta.nextLine();
		}
		for (int x = 0; x < nOfR; x++) {
			recipes[x] = new ArrayList<String>();
			String[] temp = recipites[x].split(" ");
			data.add(temp);
			for (int z = 0; z < temp.length; z++) {
				recipes[x].add(temp[z]);
			}
		}
		for (int i = 0; i < nOfR; i++) {
			itemNames[i] = recipes[i].get(0);
		}
		for (int i = 0; i < itemNames.length; i++) {
			itemsWor += itemNames[i] + " ";
		}
	}
	
	public void addToMap(String item){
		setItemNL(item);
		//System.out.println(itemNL);
		int tvalue=(int)tree.get(item)-1;
		tree.remove(item);
		tree.put(item, tvalue);
		if((int)tree.get(item)==0){
			tree.remove(item);
		}
		for (int z = 3; z < recipes[itemNL].size(); z = z + 3) {
			String key = data.get(itemNL)[z];
			z = z - 1;
			int value = Integer.parseInt(data.get(itemNL)[z].trim());
			try{
			tree.put(key, value+(int)tree.get(key));
			}catch(NullPointerException e){
				tree.put(key, value);
			}
		}
		System.out.println(tree);
	}
	
	public void addToMapUnUsedExceptOneTimeSoIgnoreMostly(String item){
		setItemNL(item);
		//System.out.println(itemNL);
		for (int z = 3; z < recipes[itemNL].size(); z = z + 3) {
			String key = data.get(itemNL)[z];
			z = z - 1;
			int value = Integer.parseInt(data.get(itemNL)[z].trim());
			try{
			tree.put(key, value+(int)tree.get(key));
			}catch(NullPointerException e){
				tree.put(key, value);
			}
		}
		System.out.println(tree);
	}
	
	public String[] getRecipe(String item) {
		addToMapUnUsedExceptOneTimeSoIgnoreMostly(item);
		iterate();
		
		while(rawMaterials().size()!=0){
			iterate();
		}
		String[] beta = new String[1];
		beta[0] = " ";
		return beta;
	}

	public void setItemNL(String itemn) {
		//System.out.println(itemn);
		for (int i = 0; i < itemNames.length; i++) {
			//System.out.println(itemNames[i]);
			if (itemNames[i].equals(itemn.trim())) {
				itemNL = i;}
		}
	}
	public int getItemNameLocation(String item){
		for(int i=0; i<itemNames.length;i++){
			if(itemNames[i].equals(item.trim())){
				return i;
			}
		}
		return 0;
	}
	
	public void iterate() {
		Iterator update = (tree.keySet().iterator());
		String current_Key = "";
		ArrayList<String> convertS1 = new ArrayList<String>();
		while (update.hasNext()) {  //buffer filled with each key
			//System.out.println(update.next());
			convertS1.add(update.next()+"");
		}
		ListIterator convertS2 = convertS1.listIterator();
		while(convertS2.hasNext()){
			 current_Key=""+convertS2.next();
			//System.out.println(current_Key);
			if(itemsWor.indexOf(current_Key)!=-1){
				addToMap(current_Key);
			}
		}
	}
	
	public ArrayList<Integer> rawMaterials(){
		ArrayList<Integer> notRaw = new ArrayList<Integer>();
		Iterator lists = tree.keySet().iterator();
		while(lists.hasNext()){
			String t = lists.next()+"";
			if(itemsWor.indexOf(t)!=-1){
				notRaw.add(getItemNameLocation(t));
			}
		}
		return notRaw;
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File alpha = new File("recipes.txt");
		Scanner beta = new Scanner(alpha);
		Crafting delta = new Crafting(beta);
		delta.getRecipe("UberHammerOfPoundation");
		System.out.println();
		System.out.println();
	
	}

}
