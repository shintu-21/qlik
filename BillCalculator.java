package thoughtworks.thoughtworks;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BillCalculator {
	
	Map<String,Slab> billMap= new HashMap();
	
	// this max can be changed depending on the requirement as of now i set it to 10000
	int max = 10000;

	public static void main(String[] args){
		BillCalculator calc = new BillCalculator();

		calc.setSlab();
		System.out.print("Solution is Rs  ");

		System.out.println(calc.calculatePrice(20));
		
	}
	
	// this method sets the slab as per the need 
	void setSlab(){
		Slab first = new Slab(1,1,100);
		Slab second = new Slab(2,101,200);
		Slab third = new Slab(3,201,300);
		Slab fourth = new Slab(5,301,max);
		
		billMap.put("first", first);
		billMap.put("second", second);
		billMap.put("third", third);
		billMap.put("fourth", fourth);

		

	}
// Method to calculate the price 
int calculatePrice(int unit){
		
		int u = unit;
		int price=0 ;
		BillCalculator calc = new BillCalculator();

		HashMap<String, Slab> sorted = calc.sortByValue(billMap);
				
		for(Map.Entry<String, Slab> entry:sorted.entrySet()){
			
			if (entry.getValue().endUnit<=unit){
				price = price+(((entry.getValue().endUnit-entry.getValue().startUnit)+1)*entry.getValue().price);
				
			}else if(entry.getValue().startUnit<=unit&&unit<=entry.getValue().endUnit){
				price = price+(((unit-entry.getValue().startUnit)+1)*entry.getValue().price);

			}	
			
		}
		return price;
		}
	
   // Sort the given map as per the endUnit 
	public static HashMap<String, Slab> sortByValue(Map<String, Slab> billMap2)
    {
        
        List<Map.Entry<String, Slab> > list =
               new LinkedList<Map.Entry<String, Slab> >(billMap2.entrySet());
 
  
        Collections.sort(list, new Comparator<Map.Entry<String, Slab> >() {
            public int compare(Map.Entry<String, Slab> o1,
                               Map.Entry<String, Slab> o2)
            {
                return (o1.getValue().endUnit).compareTo(o2.getValue().endUnit);
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<String, Slab> temp = new LinkedHashMap<String, Slab>();
        for (Map.Entry<String, Slab> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
	
	

}
