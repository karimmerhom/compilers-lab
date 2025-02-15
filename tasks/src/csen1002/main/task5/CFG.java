package csen1002.main.task5;

import java.io.Console;

/**
 * Write your info here
 * 
 * @name Karim Ebahim Merhom
 * @id 43-0414
 * @labNumber 010
 */
public class CFG {
	/**
	 * CFG constructor
	 * 
	 * @param description is the string describing a CFG
	 */
	String[] language ;
	public CFG(String description) {
		// TODO Write Your Code Here
		
	    language = description.split(";");
	
	}

	/**
	 * Returns a string of elimnated left recursion.
	 * 
	 * @param input is the string to simulate by the CFG.
	 * @return string of elimnated left recursion.
	 */
	public String lre() {
		String result = "";
		String resultSub = "";
		String newFirstLanguage = "";
		for(int i = 0 ; i < language.length ; i++)
		{
			
			for(int j = 0 ; j <= i-1 ; j++)
			{
				String[] firstLanguage = language[i].split(",");			
				String firstLetter = firstLanguage[0];
				String[] newLanguage = resultSub.split(";");
				newFirstLanguage=firstLetter ;
				boolean flag = false;
				for(int x = 1 ; x < firstLanguage.length; x++ )
				{
				
					String[] newLanguageSplit = newLanguage[j].split(",");
					if(firstLanguage[x].substring(0,1).equals(newLanguageSplit[0]))
					{
						flag = true;
						String wantedPart = firstLanguage[x].substring(1,firstLanguage[x].length());
					 for(int z = 1 ; z <newLanguageSplit.length ; z ++ )
					 {
						 newFirstLanguage+= ","+newLanguageSplit[z]+wantedPart;
					 }
					}
					else
					{
						newFirstLanguage+= "," +firstLanguage[x];
					}
					
				}
		
				if (flag)
				{
					language[i] = newFirstLanguage;
					
				}
				
			}
			String[] firstLanguage = language[i].split(",");
			String firstLetter = firstLanguage[0];
			String alphas = "";
			String betas = "";
			for(int j = 1 ; j < firstLanguage.length ; j++ )
			{
			
				if(firstLanguage[j].substring(0,1).equals(firstLetter))
				{
					alphas+=firstLanguage[j].substring(1,firstLanguage[j].length())+";";
				}
				else
				{
					betas+=firstLanguage[j]+";";
				}
				
			}
			String[] betasSplit = betas.split(";");
			if(alphas.length() == 0)
			{
				result+= language[i]+";";
				resultSub+= language[i]+";";
			}
			else
			{
				result+=firstLetter;
				resultSub+=firstLetter;
			for(int j = 0 ; j < betasSplit.length ; j ++ )
			{
				result+=","+betasSplit[j]+firstLetter+"'";
				resultSub+=","+betasSplit[j]+firstLetter+"'";
			}
			resultSub+=";";
			result+=";"+firstLetter+"'";
			String[] alphasSplit = alphas.split(";");
			for(int j = 0 ; j < alphasSplit.length ; j ++ )
			{
				result+=","+alphasSplit[j]+firstLetter+"'";
			}
			result+=",";
			result+="e";
			result+=";";
			}
			
			}
		result = result.substring(0,result.length()-1);
		return result;
	}
	
	public static void main (String[] args)
	{
		CFG cfg = new CFG("S,ScT,Sm,T,n;T,mSn,imLn,i;L,SdL,S");
		cfg.lre();
		
	}
}
