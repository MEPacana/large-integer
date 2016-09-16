import java.util.Arrays;
/**
    DIVISION NA LNGGG!!!!!!
    please check if naa pa bay errors
    ok na ni siya btw

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

	LargeInteger(LargeInteger numString) {
		number = numString.getNumber();
        size = numString.size;
	}


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
	private void errleadzer(char[] a){
        while(a[0] == '0'){
            for(int i = 1; i < a.length; i++){
                a[i-1] = a[i];
            }
        }
	}

    public LargeInteger add(LargeInteger a) {
        if((number.charAt(0) == '-' && a.number.charAt(0) == '-') || (number.charAt(0) != '-' && a.number.charAt(0) != '-')){
            char[] temp1 = new char[size];
            char[] temp2 = new char[a.size];
            temp1 = number.toCharArray();
            temp2 = a.number.toCharArray();

            if(number.charAt(0) == '-' && a.number.charAt(0) == '-'){
                temp1[0] = '0';
                temp2[0] = '0';
            }

            String c = new String(temp1);
            String d = new String(temp2);
            int[] result = new int[sizearr(size,a.size) + 1];
            plus(result,c);
            plus(result,d);

            char tmp[] = new char[result.length + 1];
            for(int i = 0; i < tmp.length - 1; i++){
                tmp[i] = (char)(result[i] + 48);
            }
            tmp[tmp.length-1] = '\0';
            errleadzer(tmp);

            String tmp2 = new String(tmp);
            LargeInteger ans = new LargeInteger();

            if(number.charAt(0) == '-' && a.number.charAt(0) == '-'){
                ans.number = "-" + tmp2;
                ans.size = tmp2.length();
            }
            else{
                ans.number = tmp2;
                ans.size = tmp2.length();
            }
            return ans;
        }
        else{
            LargeInteger ans = new LargeInteger();

            if(number.charAt(0) != '-' && a.number.charAt(0) == '-'){
                char[] n2 = new char[a.size];
                n2 = a.number.toCharArray();
                n2[0] = '0';
                String c = new String(n2);
                LargeInteger neww = new LargeInteger();
                neww.number = c;
                neww.size = c.length();
                ans = subtract(neww);
            }
            else{
                ans = subtract(a);
            }
            return ans;
        }
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
        }
    }

    public LargeInteger subtract(LargeInteger a){
        if((number.charAt(0) != '-' && a.number.charAt(0) != '-') || (number.charAt(0) == '-' && a.number.charAt(0) != '-')){
            char[] n1 = new char[size];
            char[] n2 = new char[a.size];
            int i,j,z,sum,k;
            int[] ans = new int[sizearr(size,a.size)];
            char temp;
            boolean change = false;
            boolean flag = false;
            n1 = number.toCharArray();
            n2 = a.number.toCharArray();

            if(number.charAt(0) == '-'){
                n1[0] = '0';
                for(i = 1; i < n1.length; i++){
                    if(n1[i] < n2[i-1]){
                        flag = true;
                        break;
                    }
                }
            }

            int lim1=n1.length, lim2=n2.length;
            int carry;
            if(n1.length == n2.length){//if both were the same size this determines which is bigger by comparing the values from first to last until deemed the same
                if(n1[0] == n2[0]){
                    for(i = 0; i < n1.length; i++){
                        if(n1[i] < n2[i]){
                            change = true;
                            break;
                        }
                    }
                }
            }
            if(n2.length > n1.length || change == true){//if the second one was bigger which means the outcome was a negative, i had to interchange them and then set a flag that would display a negative answer
                flag = true;
                for(i=0;i<ans.length;i++){
                    temp =n1[i];
                    n1[i]=n2[i];
                    n2[i]= temp;
                }
                carry=lim1; //size sa array gi swap
                lim1=lim2;
                lim2=carry;
            }
            for(i = n1.length - 1,j = n2.length -1; lim1 > 0;lim1--,j--,i--){//subtracts the second array from the first aray
                if (j < 0){//If the second one is shorter, just copy the first arrays remaining numbers
                    sum=(int)(n1[i]-48);
                }
                else{
                    if(n1[i] < n2[j]){//if the first array was less than the second
                        n1[i]=(char)(n1[i] + 10);
                       // if(n1.length != 1){
                            if(n1.length == 1){
                                z=0;
                            }
                            else{
                                z=1;
                            }
                            while(n1[i-z] == 48){//If the one next to it where you 'borrow' is zero, automatically turn it to 9
                                n1[i-z] = 57;
                                z++;
                            }
                            n1[i-z]=(char)(n1[i-z]-1);// If it isn't a zero you subtract 1 from it
                        //}
                    }
                    sum=(int)((n1[i]-48)-(n2[j]-48));//continue normally
                }
                ans[i]= sum ;//add 48 since im using a string of characters
            }
            char result[] = new char[ans.length + 1];      //converting to char[]
            for(i = 0; i < result.length - 1; i++){
                result[i] = (char)(ans[i] + 48);
            }
            result[result.length-1] = '\0';
            errleadzer(result);

            String tmp2 = new String(result);
            LargeInteger res = new LargeInteger();

            if(flag == true){//the display depends if the answer is a positive or a negative
                res.number = "-" + tmp2;
                res.size = tmp2.length() + 1;
            }
            else{
                res.number = tmp2;
                res.size = tmp2.length();
            }
            return res;
        }
        else{
            LargeInteger ans = new LargeInteger();

            if(number.charAt(0) != '-' && a.number.charAt(0) == '-'){
                char[] n2 = new char[a.size];
                n2 = a.number.toCharArray();
                n2[0] = '0';
                String c = new String(n2);
                LargeInteger neww = new LargeInteger();
                neww.number = c;
                neww.size = c.length();
                ans = add(neww);
            }
            else{
                ans = add(a);
            }
            return ans;

        }
	}

	public LargeInteger multiply(LargeInteger a){
        char[] temp1 = new char[size];
        char[] temp2 = new char[a.size];
        temp1 = number.toCharArray();
        temp2 = a.number.toCharArray();
        if(number.charAt(0) == '-'){
            temp1[0] = '0';
        }
        if(a.number.charAt(0) == '-'){
            temp2[0] = '0';
        }
        String c = new String(temp1);
        String d = new String(temp2);
        int[] result = new int[size + a.size];
        int[] anew = new int[sizearr(size,a.size) + 1];
        int carry = 0;
        for(int i = 0; i < a.size; i++){
            int j = 0;
            carry = 0;
            for(; j < size; j++){
                int num1 = Character.getNumericValue(d.charAt(a.size-i-1));
                int num2 = Character.getNumericValue(c.charAt(size-j-1));
                int prod = num1 * num2;
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
        errleadzer(tmp);
        String tmp2 = new String(tmp);
        LargeInteger ans = new LargeInteger();

        if(number.charAt(0) == '-' || a.number.charAt(0) == '-'){
            if(number.charAt(0) == '-' && number.charAt(0) == '-'){
                ans.number = tmp2;
            }
            else{
                ans.number = "-" + tmp2;
            }
            ans.size = tmp2.length() + 1;
        }
        else{
            ans.number = tmp2;
            ans.size = tmp2.length();
        }
		return ans;
	}



	public LargeInteger divide(LargeInteger a){
        LargeInteger ans = new LargeInteger(a);
        LargeInteger tmp = new LargeInteger(b)

        if(a.number == "0"){
            ans.number = "ERR";
            ans.size = ans.number.length();
            return ans;
        }

       	while(a.number != "0"){

       	}



		return ans;
	}

	public LargeInteger add(String a){
        //LargeInteger neww = LargeInteger();
        //neww.number = a.
		LargeInteger ans = new LargeInteger(a);
        ans = add(ans);
		return ans;
	}


	public LargeInteger subtract(String a){
		LargeInteger ans = new LargeInteger(a);
        ans = subtract(ans);
		return ans;
	}

	public LargeInteger multiply(String a){
		LargeInteger ans = new LargeInteger(a);
        ans = multiply(ans);
		return ans;
	}

	public LargeInteger divide(String a){
		LargeInteger ans = new LargeInteger();
		return ans;
	}

	public LargeInteger add(int a){
		LargeInteger ans = new LargeInteger(a);
		ans = add(ans);
		return ans;
	}

	public LargeInteger subtract(int a){
		LargeInteger ans = new LargeInteger(a);
		ans = subtract(ans);
		return ans;
	}

	public LargeInteger multiply(int a){
		LargeInteger ans = new LargeInteger(a);
		ans = multiply(ans);
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
	    /*int i = 999;
	    char[] str = number.toCharArray();

	    for(; str[i]=='0' ; i--);
	    for(;str[i]!='\0';i--)
	        System.out.print(str[i]);
	    System.out.printf("\n%i\n",size);*/
	    System.out.println(number);
        System.out.println(size + " this is size");
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
