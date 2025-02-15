package csen1002.main.task7;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Write your info here
 * 
 * @name Karim Merhom
 * @id 43-0414
 * @labNumber 10
 */
public class LL1CFG {
	String first [] ;
	String follow [] ;
	String CFG [] ;
	String vArr [] ;
	String tArr[] ;
	String parsingTable[][];
	public LL1CFG(String description) {
		String v = "" ;
		String t = "" ;
		String intitialSplit [] = description.split("#");
		CFG = intitialSplit[0].split(";");
		first = intitialSplit[1].split(";");
		follow = intitialSplit[2].split(";");
		System.out.println("CFG " + intitialSplit[0]);
		System.out.println("---------------------------");
		System.out.println("First " + intitialSplit[1]);
		System.out.println("---------------------------");
		System.out.println("Follow " + intitialSplit[2]);
		System.out.println("---------------------------");
		
		for(int i = 0 ; i < CFG.length ; i++ )
		{
			v+= CFG[i].charAt(0);
		}
		
		for(int i = 0 ; i < CFG.length ; i++)
		{
			String firstRule [] = CFG[i].split(",");
			for(int j = 0 ; j < firstRule.length ; j ++)
			{
				String firstInFirstRule  = firstRule[j];
				for(int z = 0 ; z < firstInFirstRule.length() ; z ++)
				{
					if(!(Character.isUpperCase(firstInFirstRule.charAt(z))) &&  firstInFirstRule.charAt(z) != 'e' )
					{
						if(!(t.contains(String.valueOf(firstInFirstRule.charAt(z)))))
						{
						t+= firstInFirstRule.charAt(z) ;
						}
					}
				}
			}
		}
		
		System.out.println("variables " + v);
		System.out.println("---------------------------");
		vArr = v.split("");
		t+="$";
		System.out.println("terminals " + t);
		System.out.println("---------------------------");
		tArr = t.split("");
		parsingTable = new String[v.length()][t.length()];
		
		
		for (int i = 0 ; i < parsingTable.length ; i ++)
		{
			//variables
			for(int j = 0 ; j < parsingTable[i].length ; j ++ )
			{
				boolean found = false ;
				String firstAtI[] = first[i].split(",");
				for( int z = 0 ; z < firstAtI.length ; z++)
				{
					if(firstAtI[z].length() == 1) {
					if(tArr[j].equals(firstAtI[z]))
					{
						String cfgRule [] = CFG[i].split(",");
						parsingTable[i][j] = cfgRule[z] ;
						found = true;
					}
			     }
					else
					{
						for (int c = 0 ; c < firstAtI[z].length() ; c ++)
						if(tArr[j].equals(String.valueOf(firstAtI[z].charAt(c))))
						{
							String cfgRule [] = CFG[i].split(",");
							parsingTable[i][j] = cfgRule[z] ;
							found = true;
						}
					}
			  }
				if(!found)
				{
					for( int z = 0 ; z < firstAtI.length ; z++)
					{
						if(firstAtI[z].equals("e"))
						{
							String followAtI[] = follow[i].split("");
							for(int n = 0 ; n < followAtI.length ; n++ )
							{
								if(followAtI[n].equals(tArr[j]))
								{
									parsingTable[i][j] = "e" ;
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < parsingTable.length; i++) { //this equals to the row in our matrix.
	         for (int j = 0; j < parsingTable[i].length; j++) { //this equals to the column in each row.
	            System.out.print(parsingTable[i][j] + " ");
	         }
	         System.out.println(); //change line on console as row comes to end in the matrix.
	      }
		System.out.println("---------------------------");
	}

	public String parse(String input) {
	
		ArrayList<String> trace = new ArrayList<String>();
		String answer = "S";
		trace.add("$");
		trace.add("S");
		input+="$";
	    String inputArr [] = input.split("");
	    Boolean notDone = true ;
	    for(int z = 0 ; z < inputArr.length && notDone ; z ++)
	    {
	    	System.out.println(trace.toString());
	    	String currInputLetter = inputArr[z];
	    	String topOfStack = trace.get(trace.size()-1);
	    	if (currInputLetter.equals(topOfStack) && currInputLetter.equals("$"))
	    	{
	    		System.out.println("true");
	    		break;
	    	}
	    	if (currInputLetter.equals(topOfStack))
	    	{
	    		//answer+="," + trace.get(trace.size()-1) ;
	    		trace.remove(trace.size()-1);
	    		
	    	}
	    	else
	    	{
	    		if(Character.isUpperCase(topOfStack.charAt(0)))
	    		{
	    		int i ;
	    		int x ;
	    		for( i = 0 ; i < vArr.length ; i ++ )
	    		{
	    			if(topOfStack.equals(vArr[i]))
	    			{
	    				break;
	    			}
	    		}
	    		for( x = 0 ; x < tArr.length ; x ++ )
	    		{
	    			if(currInputLetter.equals(tArr[x]))
	    			{
	    				break;
	    			}
	    		}
	    		
	    		String addToTrace = parsingTable[i][x];
	    		if(addToTrace != null)
	    		{
	    		trace.remove(trace.size()-1);
	    		String [] answerArr = answer.split(",");
	    		String prev = answerArr[answerArr.length -1];
	    		String toAdd = "";
	    		for(int g = 0 ; g < prev.length() ; g++)
	    		{
	    			if(Character.isUpperCase(prev.charAt(g)))
	    			{

	    				if(!(addToTrace.equals("e")) )
	    				{
	    				toAdd+=addToTrace ;
	    				}
	    				toAdd+= prev.substring(g+1,prev.length());
	    				answer+=","+toAdd;
	    				break;
	    			}
	    			else
	    			{
	    				toAdd+=prev.charAt(g);
	    			}
	    		}
	    		
	    		z--;
	    		for(int f = addToTrace.length()-1 ; f >= 0 ; f-- )
	    		{
	    			if(addToTrace.charAt(f) != 'e')
	    			{
	    			trace.add(String.valueOf(addToTrace.charAt(f)));	
	    			}
	    		}
	    		
	    	  }
	    		else
	    		{
	    			answer+=",ERROR" ;
	    			notDone = false ;
	    		}
	    	}
	    		else
	    		{
	    			answer+=",ERROR" ;
	    			notDone = false ;
	    		}
	    	}
	    }
	    
	    System.out.println(answer);
		
		return answer;
	}

	public static void main (String[] args)
	{
		LL1CFG ll1cfg2 = new LL1CFG("S,ipD,oSmDc,e;D,VmS,LxS;V,n,e;L,oSc,e#S,i,o,e;D,mn,ox;V,n,e;L,o,e#S,cm$;D,cm$;V,m;L,x");
		ll1cfg2.parse("omocxipmc");
		//S,oSmDc,omDc,omLxSc,omoScxSc,omocxSc,omocxipDc,omocxipVmSc,omocxipmSc,omocxipmc
	}
}
