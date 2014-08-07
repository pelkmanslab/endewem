/*
Copyright (C) <2011>  Ernesto Iacucci

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package statforweights;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.math.BigInteger;
import java.math.BigDecimal;

public class Statforweights {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       examplePhi2Phi1();
       examplePhi2Phi2(); 
    }
    
    public static void examplePhi2Phi2() {   
        System.out.println("Values for PHI2/PH2");
        
        HashMap chm=new HashMap();
        HashMap bhm=new HashMap();
        //instances for a particular classification
        chm.put("g1", 1+"");  // v_HashMap
        chm.put("g2", 2+"");
        chm.put("g3", 1+"");
        chm.put("g7", 3+"");
   
        //instances for the whole tree
        bhm.put("g1", 2+"");
        bhm.put("g2", 2+"");
        bhm.put("g3", 3+"");
        bhm.put("g4", 4+"");
        bhm.put("g5", 1+"");
        bhm.put("g6", 2+"");
        bhm.put("g7", 3+"");
        bhm.put("g8", 4+"");

        HashMap sa=new HashMap();
        HashMap sb=new HashMap();
        
        //instances for the complement
        sa.put("g4", 4+"");
        sa.put("g5", 1+"");
        sa.put("g6", 2+"");
        sa.put("g8", 4+"");
        
        sb.put("g1", 1+""); // compl_v_HashMap
        sb.put("g2", 0+"");
        sb.put("g3", 2+"");
        sb.put("g7", 0+"");
    
        int n=1;
        int a=10;
        
        long[][] a_compli_table=setuptable_UncompressedL(a, chm, sb);
        BigDecimal[] b_array=setuptableEHM(a, sa);
        BigDecimal[] basevalues=setuptableEHM(a,bhm);
        
        System.out.println("Prbability of observing " + 1 + " "+ probL(a_compli_table, a, n, getSumOfValues(sb), b_array, basevalues[a-1]));
        n=2;
        System.out.println("Prbability of observing " + 2 + " "+ probL(a_compli_table, a, n, getSumOfValues(sb), b_array, basevalues[a-1]));
        n=3;
        System.out.println("Prbability of observing " + 3 + " "+ probL(a_compli_table, a, n, getSumOfValues(sb), b_array, basevalues[a-1]));
        n=4;
        System.out.println("Prbability of observing " + 4 + " "+ probL(a_compli_table, a, n, getSumOfValues(sb), b_array, basevalues[a-1]));
        n=5;
        System.out.println("Prbability of observing " + 5 + " "+ probL(a_compli_table, a, n, getSumOfValues(sb), b_array, basevalues[a-1]));
        n=6;
        System.out.println("Prbability of observing " + 6 + " "+ probL(a_compli_table, a, n, getSumOfValues(sb), b_array, basevalues[a-1]));
        n=7;
        System.out.println("Prbability of observing " + 7 + " "+ probL(a_compli_table, a, n, getSumOfValues(sb), b_array, basevalues[a-1]));
        n=0;
        System.out.println("Prbability of observing " + 0 + " "+ probL(a_compli_table, a, n, getSumOfValues(sb), b_array, basevalues[a-1]));
   
        n=7;
        BigDecimal pvalue=BigDecimal.valueOf(0);
        for(int ntemp=0; ntemp<=n; ntemp++)
        {
            pvalue=pvalue.add(probL(a_compli_table, a, ntemp, getSumOfValues(sb), b_array, basevalues[a-1]));
        }
        System.out.println("p-value " + pvalue);
        
    }
    
    public static void examplePhi2Phi1()
    {
        System.out.println("Values for PHI2/PH1");
        HashMap chm=new HashMap();
    
        //instances for a particular classification
        chm.put("g1", 1+"");  // v_HashMap
        chm.put("g2", 2+"");
        chm.put("g3", 1+"");
        chm.put("g7", 3+"");
        
        BigDecimal[][] ss = setuptableSS(4, 7, chm);
        System.out.println("Prbability of observing " + 0 + " "+ probSS(ss, 4, 8, 4, 0));
        System.out.println("Prbability of observing " + 1 + " "+ probSS(ss, 4, 8, 4, 1));
        System.out.println("Prbability of observing " + 2 + " "+ probSS(ss, 4, 8, 4, 2));
        System.out.println("Prbability of observing " + 3 + " "+ probSS(ss, 4, 8, 4, 3));
        System.out.println("Prbability of observing " + 4 + " "+ probSS(ss, 4, 8, 4, 4));
        System.out.println("Prbability of observing " + 5 + " "+ probSS(ss, 4, 8, 4, 5));
        System.out.println("Prbability of observing " + 6 + " "+ probSS(ss, 4, 8, 4, 6));
        System.out.println("Prbability of observing " + 7 + " "+ probSS(ss, 4, 8, 4, 7));
        
          BigDecimal pvalue=BigDecimal.valueOf(0);
          int n=7;
        for(int ntemp=0; ntemp<=n; ntemp++)
        {
            pvalue=pvalue.add(probSS(ss, 4, 8, 4, ntemp));
        }
       System.out.println("p-value " + pvalue);
    }
    
      public static BigDecimal[][] setuptableSS(int setsize, int rows,  HashMap cHM) 
      {
        //***************************************
        HashMap inputHM=cHM;
        int totalscore=rows;
        BigDecimal[] result=new BigDecimal[rows];
       
        //**************************************

        int max=0;
        //find max
        for(int c=0; c<inputHM.size(); c++)
        {
            if(Integer.parseInt(inputHM.values().toArray()[c]+"")>=max)
                {
                    max=Integer.parseInt(inputHM.values().toArray()[c]+"");
                }  
        }
  
        // make cardinalities hashmap
        HashMap cardinalities=new HashMap();
        int temp=0;
            for(int c=0; c<inputHM.size(); c++)
            {
                temp=Integer.parseInt(inputHM.values().toArray()[c]+"");
                
                if(cardinalities.get(temp+"")==null)
                {
                    cardinalities.put(temp+"", 1+"");
                }
                else
                {
                    cardinalities.put(temp+"", (Integer.parseInt(cardinalities.get(temp+"")+"")+1)+"");
                }
            }
        
        BigDecimal[][] counts=new BigDecimal[totalscore][totalscore+1];
        BigDecimal[][] resultcounts=new BigDecimal[totalscore][totalscore+1];
            for(int i=0;i<totalscore; i++)
            {
                for(int j=0; j<(totalscore+1);j++)
                {
                    resultcounts[i][j]=BigDecimal.valueOf(0);
                     counts[i][j]=BigDecimal.valueOf(0);
                }
            }
        //go through the column
      for(int m=1; (m<=max && m<=totalscore);m++)
        {

            //go though earlier columns
            if(cardinalities.get(m+"")!=null)
            {
                //go through rows
                for(int r=0; r<totalscore; r++)
                {       
                    if(((r+1)<=((m)*Integer.parseInt(cardinalities.get(m+"")+"")))&&((r+1)%(m)==0)&&((r+1)%(m)<=totalscore))
                    {
                        double setcount = Math.round(nchoosek(Integer.parseInt(cardinalities.get(m+"")+""),((r+1)/(m))) );
                        int ssoffset=(r+1)/m;
                        
                        if((ssoffset)<=totalscore)
                          {
                        if((counts[r][ssoffset]==null)&&((ssoffset)<setsize))
                        {
                            counts[r][ssoffset]=BigDecimal.valueOf(0);
                        }
                          
                            counts[r][ssoffset]=counts[r][ssoffset].add(BigDecimal.valueOf((long) setcount));
                          }

                        //go through the past coloumns
                        for(int cp=0; cp<=totalscore;cp++)
                        {
                            //goes though the rows
                            for(int rp=0; rp<totalscore; rp++)
                                {
                                    // make sure the result does not go above total score
                                    if((rp+(m)*((r+1)/(m)))<totalscore)
                                    {                           
                                        if((cp+ssoffset)<=totalscore)
                                        {
                                          if(counts[rp+(m)][cp+ssoffset]==null)
                                                {
                                                 counts[rp+(m)*((r+1)/(m))][cp+ssoffset]=BigDecimal.valueOf(0);
                                                }
                                        }
                                          
                                          if((cp+ssoffset)<=totalscore)
                                          {
                                             if(counts[rp][cp]==null)
                                                {
                                                 counts[rp][cp]=BigDecimal.valueOf(0);
                                                }
                               
                                             counts[rp+(m)*((r+1)/(m))][cp+ssoffset]=counts[rp+(m)*((r+1)/(m))][cp+ssoffset].add(resultcounts[rp][cp].multiply(BigDecimal.valueOf((long)setcount)));                                       
                                          }
                                    }
                                }
                        }          
                    }
                }
            }
            
            for(int i=0;i<totalscore; i++)
            {
                for(int j=0; j<(totalscore+1);j++)
                {
                    resultcounts[i][j]=resultcounts[i][j].add(counts[i][j]);
                    counts[i][j]=BigDecimal.valueOf(0);
                }
            }
   
        }
        return resultcounts;
    } //ends the setuptableSS
    
     public static double nchoosek(int n, int k)
     {
         double value=0;
         
         value=logfactorialvalue(n)-(logfactorialvalue(k)+logfactorialvalue(n-k));
         value=Math.pow(10, value);
         return value;
     }
         
    public static double logfactorialvalue(int i)
    {

        double value=(Math.log(1)/Math.log(10));
        
        for(int index=i;index>1;index--)
        {
            value=value + (Math.log(index)/Math.log(10));
        }

        return value;
    }
    
    public static int getSumOfValues(HashMap hm)
     {
        int ans=0;
        Object[] terms = hm.values().toArray();
        
        for(int i=0; i<terms.length;i++)
        {
            int value=Integer.parseInt(terms[i]+"");         
            ans=ans+value;
        }      
        return ans;
     } //ends method

    public static BigDecimal probSS(BigDecimal[][] ss, int setsize, int popsize, int numGenesinV, int n)
    {
        BigDecimal result;
        BigDecimal denominator=new BigDecimal(nchoosekBI(popsize, setsize));
        BigDecimal numerator=BigDecimal.valueOf(0);
        
        if(n==0)
        {
            numerator=new BigDecimal(nchoosekBI((popsize-numGenesinV), setsize));
            numerator.setScale(1022); denominator.setScale(1022);
            result=numerator.divide(denominator, 1022, BigDecimal.ROUND_DOWN);
        }
        else{
        //gu: genes used
        for(int gu=0; gu<=n; gu++)
        {
            numerator=numerator.add(ss[n-1][gu].multiply(new BigDecimal(nchoosekBI((popsize-numGenesinV), (setsize-gu)))));
        }
        }
         numerator.setScale(1022); denominator.setScale(1022);
         result=numerator.divide(denominator, 1022, BigDecimal.ROUND_DOWN);
         
         return result;
        
    }//ends the probSS

    
     public static BigInteger nchoosekBI(int n, int k)
     {
         BigInteger value=BigInteger.valueOf(0);
         
         value=factorialvalueBI(n).divide(factorialvalueBI(k).multiply(factorialvalueBI(n-k)));
     
         return value;
     }
    
    public static BigInteger factorialvalueBI(int i)
    {
        BigInteger value=BigInteger.valueOf((long) 1);
        
        for(int index=i;index>1;index--)
        {
            value=value.multiply(BigInteger.valueOf((long) index));
        }
        return value;
    }
    
   public static long[][] setuptable_UncompressedL(int rows, HashMap gHM, HashMap cHM) 
    {
        int totalscore=getSumOfValues(gHM);
        long[][] counts=new long[totalscore][gHM.size()];
        for(int i=0; totalscore>i;i++)
        {
            for(int j=0; gHM.size()>j;j++)
            {
                counts[i][j]=0;
            }
        }
        
        int totalscoreCompliment=getSumOfValues(cHM);
        long[][] countsCompliment=new long[totalscore][totalscoreCompliment+1];
        long[][] tempCC=new long[totalscore][totalscoreCompliment+1];
        
        for(int i=0; totalscore>i;i++)
        {
            for(int j=0; totalscoreCompliment>=j;j++)
            {
                countsCompliment[i][j]=0;
                tempCC[i][j]=0;
            }   
        }
        
        Object[] terms = gHM.keySet().toArray();
        
        for(int i=0; i<terms.length;i++) //goes through columns
        {
            int value=Integer.parseInt(gHM.get(terms[i])+"");
            int valueCompliment=Integer.parseInt(cHM.get(terms[i])+"");
            
         if(value<=rows) 
         {               
            counts[value-1][i]=counts[value-1][i]+1;
            
            for(int j=0; j<i;j++)      //goes through previous columns
            {
                for(int k=0; k<totalscore; k++) //goes through rows
                {
                    if(counts[k][j]>=1)
                    {
                    counts[k+value][i]=counts[k+value][i]+counts[k][j];
                    }
                }
                
            }
            
           for(int k=0; k<totalscore; k++)
                {
                    if(counts[k][i]>0)
                    {
                        for(int j=0; j<=totalscoreCompliment;j++)
                        {
                            if(((k-value)==-1)&&j==0)
                            {
                                tempCC[k][valueCompliment]=tempCC[k][valueCompliment]+1;
                            }else if((k-value)>=0)
                            {              
                                if(countsCompliment[k-value][j]>0)
                                {
                                    tempCC[k][j+valueCompliment]= tempCC[k][j+valueCompliment]+countsCompliment[k-value][j]; 
                                 }
                            }
                        }
                    }
                    
                }
            
            
       for(int ii=0; totalscore>ii;ii++)
        {
            for(int j=0; totalscoreCompliment>=j;j++)
            {
               countsCompliment[ii][j]=countsCompliment[ii][j]+tempCC[ii][j];
               tempCC[ii][j]=0;
            }   
        }            
                 
        }  
        }

        return countsCompliment;
    } //ends setuptable_UncompressedL

     //*****************************************
    // takes the   bHM: base hashmap
    //             cHM: comparitive hasshmap (sample HM)
    //             z: considered genes
    //*****************************************
    
    public static BigDecimal[] setuptableEHM(int rows, HashMap cHM) {
        
        //*****************temp variables**
        HashMap inputHM=cHM;
        int totalscore=rows;
        BigDecimal[] result=new BigDecimal[rows];
        //*********************************

        int max=0;
        //find max
        for(int c=0; c<inputHM.size(); c++)
        {
            if(Integer.parseInt(inputHM.values().toArray()[c]+"")>=max)
                {
                    max=Integer.parseInt(inputHM.values().toArray()[c]+"");
                }  
        }
  
        // make cardinalities hashmap
        HashMap cardinalities=new HashMap();
        int temp=0;
            for(int c=0; c<inputHM.size(); c++)
            {
                temp=Integer.parseInt(inputHM.values().toArray()[c]+"");
                
                if(cardinalities.get(temp+"")==null)
                {
                    cardinalities.put(temp+"", 1+"");
                }
                else
                {
                    cardinalities.put(temp+"", (Integer.parseInt(cardinalities.get(temp+"")+"")+1)+"");
                }
            }
        
        
        BigDecimal[][] counts=new BigDecimal[totalscore][cardinalities.size()];
        
        int c=-1;
        //go through the column
      for(int m=1; m<=max;m++)
        {
            //go though earlier columns
            if(cardinalities.get(m+"")!=null)
            {
                c++;
                //go through rows
                for(int r=0; r<totalscore; r++)
                {       
                    if(((r+1)<=((m)*Integer.parseInt(cardinalities.get(m+"")+"")))&&((r+1)%(m)==0))
                    {
                        
                        double setcount=nchoosek(Integer.parseInt(cardinalities.get(m+"")+""),((r+1)/(m)));

                        if(new Double(setcount).isInfinite())
                        {
                             if(counts[r][c]==null)
                            {
                                counts[r][c]=BigDecimal.valueOf(0);
                            }
                        counts[r][c]=counts[r][c].add(new BigDecimal(nchoosekBI(Integer.parseInt(cardinalities.get(m+"")+""),((r+1)/(m)))));
                        }else{
                               setcount =  Math.round(setcount);
                               if((new Double(setcount).isInfinite()) ||  (new Double(setcount).isNaN()))
                               {System.out.println("value is undefined");}
                        if(counts[r][c]==null)
                        {
                            counts[r][c]=BigDecimal.valueOf(0);
                        }
                        counts[r][c]=counts[r][c].add(BigDecimal.valueOf((long) setcount));
                        }
                
                        //go through the past coloumns
                        for(int cp=0; cp<c;cp++)
                        {
                            //goes though the rows
                            for(int rp=0; rp<totalscore; rp++)
                                {
                                    // make sure the result does not go above total score
                                    if((rp+(m)*((r+1)/(m)))<totalscore)
                                    {
                                 
                                          if(counts[rp+(m)][c]==null)
                                                {
                                                 counts[rp+(m)*((r+1)/(m))][c]=BigDecimal.valueOf(0);
                                                }
                                             if(counts[rp][cp]==null)
                                                {
                                                 counts[rp][cp]=BigDecimal.valueOf(0);
                                                }
                                          
                                         counts[rp+(m)*((r+1)/(m))][c]=counts[rp+(m)*((r+1)/(m))][c].add(counts[rp][cp].multiply(BigDecimal.valueOf((long)setcount)));
                                    }
                                }
                        }          
                    }
                }
            }
        }
                         
        for(int i=0;i<totalscore; i++)
        {
            for(int j=0; j<cardinalities.size();j++)
            {
                if(result[i]==null)
                {
                    result[i]=BigDecimal.valueOf(0);
                }
                if(counts[i][j]==null)
                {
                    counts[i][j]=BigDecimal.valueOf(0);
                }
                result[i]=result[i].add(counts[i][j]);
            }
        }   
        return result;
    }

      public static BigDecimal probL(long[][] compliA, int a, int n, int colA,  BigDecimal[] bterm, BigDecimal c)
   {
       BigDecimal result=BigDecimal.valueOf(0);
       BigDecimal numerator=BigDecimal.valueOf(0);
       BigDecimal denominator=c;

       if(n==0)
       {
            numerator=numerator.add(bterm[a-1]);
            numerator.setScale(1022); denominator.setScale(1022);
            result=numerator.divide(denominator, 1022, BigDecimal.ROUND_DOWN);
       }
       else{
            for(int i=0;i<=colA;i++)  //goes through the columns of a compliment table
            {     
                if((compliA[n-1][i]>0)&&!((n+i)>(a)))
                {
                    if((n+i)==(a))
                    {}
                    else{
                    numerator=numerator.add(BigDecimal.valueOf(compliA[n-1][i]).multiply(bterm[a-(n+i+1)]));
                                     }
                    
                    if((a)==(n+i))
                    {
                        numerator=numerator.add(BigDecimal.valueOf(compliA[n-1][i]));
                    }
                }
            }
       
            numerator.setScale(1022); denominator.setScale(1022);
            result=numerator.divide(denominator, 1022, BigDecimal.ROUND_DOWN);
       }
       
       return result;
   }
    
}
