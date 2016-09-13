import java.util.*;

public class LargeInteger{
	private int size;
	private String number;

	LargeInteger() {
		number = "0";
		size = 1;
	}

	LargeInteger(String numString) {
		number = numString;
		size = numString.length();
	}

	LargeInteger(LargeInteger numString) {
		number = numString.getNumber();

	}

	LargeInteger(int number) {
		this.number = Integer.toString(number);
		size = this.number.length();
	}

	public LargeInteger add(LargeInteger a){
		LargeInteger ans = new LargeInteger();
		int i = 0, j = 0, carry = 0, k=0, digits;		
		boolean flag = false;

		char[] str1 = number.toCharArray();
		char[] str2 = a.number.toCharArray();
		char[] str3 = ans.number.toCharArray();

		for(; str1[i]=='\0';i++);
		for(; str2[j]=='\0';j++);

		digits = (size>a.size) ? size : a.size;

	    k = (105 <= str1[999] + str2[999]) ? 998-digits : 1000-digits;

	    ans.size = 1000-k;

		for(;i < 1000 && j < 1000; i++, j++, k++){
	        str3[k] += carry;
			
			if(str2[j] + str1[i] + carry >= 106){
				str3[k] -= 58;
				carry = 1;
			}

			else{
	            str3[k] -= 48;
	            carry = 0;
			}

			str3[k] += str2[j] + str1[i];
			if(str3[k]>57)
	            str3[k]-=10;
		}

		if(k<1000){
			if(i<1000)
				for(;i<1000;i++,k++){
				    str3[k] += (str1[i]+carry>57) ? str1[i] + carry - 10 : str1[i] + carry;
	                if(str1[i]+carry>57)
	                    carry = 1;
	                else
	                    carry = 0;
				}
			else
				for(;j<1000;j++,k++){
	                str3[k] += (str1[i]+carry>57) ? str2[j] + carry - 10 : str2[j] + carry;
	                if(str2[j]+carry>57)
	                    carry = 1;
	                else
	                    carry = 0;
				}
		}
		if(k==1000-1)
	        str3[k] += carry+48;
	    for(i = 1000-1; 1000 - i < ans.size ; i--){
	        if(str3[i]=='\0'&& str3[i-1] != '\0'){
	            str3[i] = str3[i-1];
	            flag=true;
	        }
	        if(flag == true){
	            str3[i-1] = '\0';
	            flag = false;
	        }
	    }
	    if(str3[1000-1]=='0'){
	        for(i = 1000-1; str3[i]!='\0';i--)
	            str3[i] = str3[i-1];
	        ans.size--;
	    }
	    ans.number = str3.toString();
		return ans;		
	}

	public LargeInteger subtract(LargeInteger a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger multiply(LargeInteger a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

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
	
	public String getNumber(){ return this.number; }
	
	public int getSize(){ return this.size; }
}

