import java.util.Arrays;
/**
    na disturb koh tungod kay naay repetitive loops nga ganahan unta nako i
    himuon into function hahaha
    btw feel nako ma lapas ni 500 lines

    by Loewe the Gwapo
**/
public class LargeInteger{
	private int size;
	private String number;

	LargeInteger() {
		number = "0";
		size = 1;
	}

	LargeInteger(String numString) {
        number = numString;
        size = number.length();
	}

	/*LargeInteger(LargeInteger numString) {
		number = numString.getNumber();

	}*/


	LargeInteger(int number) {
		this.number = Integer.toString(number);
		size = this.number.length();
	}

	/**
        kuhaon niya ang size
	**/

	private int sizearr(int a,int b){
        if(a >= b){
            return a;
        }
        else{
            return b;
        }
	}

    public LargeInteger add(LargeInteger a) {
        int[] result = new int[sizearr(size,a.size) + 1];
        for(int i = 0; i < result.length; i++){
            result[i] = 0;
        }

        plus(result,number);
        plus(result,a.number);

        char tmp[] = new char[result.length + 1];
        for(int i = 0; i < tmp.length - 1; i++){
        	tmp[i] = (char)(result[i] + 48);
        }
        tmp[tmp.length-1] = '\0';
        String tmp2 = new String(tmp);
		LargeInteger ans = new LargeInteger();
		ans.number = tmp2;
		return ans;
    }
    //no negatives yet
    private void plus(int[] result, String a){
        int len = a.length();
        int resultlen = result.length;
        int carry = 0,num1 = 0;

        while(len > 0){
            num1 = Character.getNumericValue(a.charAt(len-1));

            num1 += carry;
            carry = 0;
            if(result[resultlen-1] + num1 > 9){
                carry = 1;
                num1 = (num1 + result[resultlen-1]) % 10;
                result[resultlen-1] = num1;
            }
            else{
                result[resultlen-1] += num1;
            }
            len--;
            resultlen--;
        }
        if(carry == 1){
            num1 = (result[resultlen-1] + carry) % 10;
            result[resultlen-1] = num1;
            result[resultlen-2] = 1;
        }
    }

	public LargeInteger subtract(LargeInteger a){
























		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger multiply(LargeInteger a){
        int[] result = new int[size + a.size];
        int[] anew = new int[sizearr(size,a.size) + 1];
        int carry = 0;

        for(int i = 0; i < result.length; i++){
        	result[i] = 0;
        }
        for(int i = 0; i < anew.length; i++){
            anew[i] = 0;
        }


        for(int i = 0; i < a.size; i++){
            int j = 0;
            carry = 0;
            for(; j < size; j++){
                int num1 = Character.getNumericValue(a.number.charAt(a.size-i-1));
                int num2 = Character.getNumericValue(number.charAt(size-j-1));
                int prod = num1 * num2;
                //System.out.println(prod + " mao ni si prod");
                prod += carry;
                carry = 0;

                if(prod > 9){
                    carry = prod / 10;
                    prod = prod % 10;
                }
                anew[anew.length-j-1] += prod;
            }
            anew[anew.length-j-1] = carry;

            int carry1 = 0;
            int k,l;
            for(k = result.length - i - 1, l = anew.length-1; l >= 0; k--,l--){
            	anew[l] += carry1;
            	carry1 = 0;
            	if(result[k] + anew[l] > 9){
                    carry1 = 1;
            		anew[l] = (anew[l] + result[k]) % 10;
                    result[k] = anew[l];
            	}
            	else{
                    result[k] += anew[l];
            	}
            }
            if(carry1 == 1){
                result[k] = (result[k] + carry) % 10;
                result[k-1] = 1;
            }
            for(int z = 0; z<anew.length;z++){
            	anew[z] = 0;
            }
        }

        char tmp[] = new char[result.length + 1];
        for(int i = 0; i < tmp.length - 1; i++){
        	tmp[i] = (char)(result[i] + 48);
        }
        tmp[tmp.length-1] = '\0';
        String tmp2 = new String(tmp);
		LargeInteger ans = new LargeInteger();
		ans.number = tmp2;
		return ans;
	}


    /*
	public LargeInteger divide(LargeInteger a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger add(String a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger subtract(String a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger multiply(String a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger divide(String a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger add(int a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger subtract(int a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger multiply(int a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger divide(int a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public String toString(){
		return number;
	}

	public void display(){
	    int i = 999;
	    char[] str = number.toCharArray();

	    for(; str[i]=='0' ; i--);
	    for(;str[i]!='\0';i--)
	        System.out.print(str[i]);
	    System.out.printf("\n%i\n",size);
	}

	public boolean equals(LargeInteger b){
		if(this.number.equals(b.number)){
			return true;
		} else {
			return false;
		}
	}
    */
	public String getNumber(){ return this.number; }

	public int getSize(){ return this.size; }



}
