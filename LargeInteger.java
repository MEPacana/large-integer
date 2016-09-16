//I declare upon my honor that I made this mp by myself
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define BUFFSIZE 1000
typedef struct{//Struct for computation
    int ub,ubflag,lbflag,lb,cons,add;
    char n[100];
}Teeofn;
void counting(char[],Teeofn*);//Difft. functions
void init(Teeofn*);
void countlb(char[],Teeofn*);
void countub(char[],Teeofn*);
void countit(char[],Teeofn*);
void countn(char[],Teeofn*);
void countTofN(Teeofn*);
int main(){//main
    FILE *ifp;
    ifp=fopen("sample-test-cases.txt","r");//file manipulation
    char x[BUFFSIZE-1],temp[BUFFSIZE-1];
    char* parse;
    Teeofn forloop;
    init(&forloop);
    for(;fgets(x,BUFFSIZE-1,ifp)!=NULL;){//parsing
        strcpy(temp,x);
        parse=strtok(temp,"({;");
        counting(parse,&forloop);
    }
}
void init(Teeofn *flp){//initialization
    flp->ub=flp->ubflag=flp->lbflag=flp->lb=flp->cons=flp->add=0;
    flp->n[10]=NULL;
}
void countlb(char parse[], Teeofn *flp){//function for lowerbound
    int i,flag;
    for(i=0,flag=0;parse[i]!=NULL;i++){
        if(parse[i]>='0' && parse[i]<='9'){
            flp->lb=parse[i]-'0';
        }
        else if (parse[i]>='a' && parse[i]<='z' && flag!=0){
            flp->ub=1;
            flp->lbflag=-1;//meaning var
            flp->ubflag=-1;
        }
        else if (parse[i]=='<' || parse[i]=='>' || parse[i]=='='){
            flag=i;
            flp->add++;
        }
    }
}
void countub(char parse[], Teeofn *flp){//function for upper bound
    int i,flag,count=0;
    for(i=0,flag=0,count=0;parse[i]!=NULL;i++){
            if(parse[i]>='0' && parse[i]<='9' && flp->lbflag>=0){//bigger than one number
                if(flp->ub!=0){
                    flp->ub*=10;
                    flp->ub+=parse[i]-'0';
                }
                else
                    flp->ub=parse[i]-'0';
                if(parse[flag]=='>'){
                    //printf("nisud sa >\n");
                    flp->ubflag=4;//meaning var
                }
                else if(parse[flag]=='<'){
                    //printf("nisud sa <\n");
                    flp->ubflag=5;//meaning var
                }
                else if(parse[flag]=='='){
                    //printf("nisud sa =\n");
                    flp->ubflag=6;//meaning var
                }
                //printf(" The ub is %i\n", flp->ub);
            }
            else if(parse[i]>='a' && parse[i]<='z' && flag!=0){
                flp->ub=1;
               // printf("nisud sa n ub\n");
                if(parse[flag]=='>'){
                    //printf("nisud sa >\n");
                    flp->ubflag=1;//meaning var
                }
                else if(parse[flag]=='<'){
                   //printf("nisud sa <\n");
                    flp->ubflag=2;//meaning var
                }
                else if(parse[flag]=='='){
                    //printf("nisud sa =\n");
                    flp->ubflag=3;//meaning var
                }
            }
            else if(flp->lbflag==-1 &&parse[i]>='0' && parse[i]<='9' && flag!=0 ){
                flp->ub=parse[i]-'0';
                //printf(" The lb is %i\n", flp->ub);
                if(flp->lbflag==-1 &&parse[flag]=='>'){
                    flp->lbflag=-2;
                }
                else if(flp->lbflag==-1 && parse[flag]=='<'){
                    flp->lbflag=-3;
                }
                else if(flp->lbflag==-1 &&parse[flag]=='='){
                    flp->lbflag=-4;
                }
            }
            else if(flag==0 && parse[i]=='*'){
                count++;
                flp->add++;
                flp->cons++;
            }
        if(parse[i]=='<'||parse[i]=='>'||parse[i]=='=' && flag==0){
            flag=i;
            flp->add++;
        }
    }
    if(count>0){
        flp->ubflag=6+count;
    }
    flp->cons++;
}
void countit(char parse[], Teeofn *flp){//function for O(n)
    int i;
    for(i=0;parse[i]!=NULL;i++){
        if(flp->ubflag<7){
            if(parse[i]=='+' && parse[i+1]=='+'|| parse[i]=='-' && parse[i+1]=='-'){
                strcpy(flp->n,"n");
               // printf(" The n is %s\n", flp->n);
                if(parse[i]=='+' && parse[i+1]=='+'){
                    flp->n[9]='+';
                }
                else if(parse[i]=='-' && parse[i+1]=='-'){
                    flp->n[9]='-';
                }
            }
            else if((parse[i]=='*' && parse[i+1]=='=') || (parse[i]=='/' && parse[i+1]=='=')){
                int x=parse[i+3];
                strcpy(flp->n,"log(x) n");
                flp->n[4]=x;
              //  printf(" The n is %s\n", flp->n);
                if(parse[i]=='*' && parse[i+1]=='='){
                    flp->n[9]='+';
                }
                else if(parse[i]=='/' && parse[i+1]=='='){
                    flp->n[9]='-';
                }
            }
            else if((parse[i]=='+' && parse[i+1]=='=') || (parse[i]=='-' && parse[i+1]=='=')){
                int x=parse[i+3];
                strcpy(flp->n,"n/x");
                    flp->n[2]=x;
               // printf(" The n is %s\n", flp->n);
                if(parse[i]=='*' && parse[i+1]=='='){
                    flp->n[9]='+';
                }
                else if(parse[i]=='/' && parse[i+1]=='='){
                    flp->n[9]='-';
                }
            }
        }
        else if(flp->ubflag>6){
            if(flp->ubflag-6==1){
                strcpy(flp->n,"sqrt(n)");
            }
            else if(flp->ubflag-6==2){
                strcpy(flp->n,"cubert(n)");
            }
        }
    }
    flp->cons++;
}
void countn(char parse[], Teeofn *flp){//function for counts the summation
    int i;
    for(i=0;parse[i]!=NULL;i++){
        if((parse[i]=='+' || parse[i]=='-' || parse[i]=='/' ||parse[i]=='*' || parse[i]=='=')&& parse[i+1]!='+' && parse[i+1]!='-'&& parse[i+1]!='/'&& parse[i+1]!='*'&& parse[i+1]!='='){
            flp->cons++;
        }
    }
}
void countTofN(Teeofn *flp){//calculation of the summation
    int ans;
    if(flp->ubflag>3 &&flp->ubflag<7 && flp->lbflag>=0 ){
        if(flp->ubflag==4 && flp->n[9]=='+' && flp->lb <flp->ub){
            printf("T(n) = 2\n");
        }
        else{
                flp->ub=(flp->ub -flp->lb+1);
                ans=(flp->ub * flp->cons) +2;
                printf("T(n) = %i\n", ans);
        }
    }
    else if(flp->ubflag==1 ||flp->ubflag==2||flp->ubflag==3 ||flp->ubflag>6 && flp->lbflag>=0){
        if(flp->lb!=1){
                int ans;
                printf("T(n) = %i%s",flp->cons,flp->n);
                ans=((flp->lb*-1)+1)*flp->cons+2;
                if(ans>=0){
                    printf("+ %i\n",ans);
                }
                else{
                    printf("- %i\n",ans*-1);
                }
        }
        else if(flp->ubflag==1){
            printf("T(n) = 2\n");
        }
        else if(flp->ubflag==2 && flp->n[9]=='-'){
            printf("T(n) = Infinite\n", ans);
        }
        else{
            printf("T(n) = %i%s + %i\n", flp->cons,flp->n,flp->add);
        }
    }
    else if(flp->lbflag<0){
        if(flp->lbflag==-3){
            printf("T(n) = 2");
            printf(" %i\n",flp->ubflag);
        }
        else {
            printf("potato\n");
        }
    }
}
void counting(char parse[],Teeofn *flp){
    int i,flag=0,answer=0;
    for(;parse!=NULL;){//further parsing
        //printf("%i",i);
        if(parse[i]=='f' && parse[i+1]=='o' && parse[i+2]=='r'){
            flag=1;
            parse=strtok(NULL,"t{;})");
            //printf("\n",parse);
        }
        else if(i==1 && flag==1){
            //printf("This is the initializer\n");
            countlb(parse,flp);//searches lower bound
        }
        else if(i==2 && flag==1){
            //printf("This is the condition\n");
            countub(parse,flp);//searches upper bound
        }
        else if(i==3 && flag==1){
            //printf("This is the incrementation\n");
            countit(parse,flp);//searches for O(n)
        }
        if(parse[0]!='\n' && parse[0]!='i' && parse[1]!='n'){
            if(parse[0]!='}'){
                //printf( "%s\n", parse);
            }//for calculation
            else{
                countTofN(flp);
                init(flp);
            }
        }
        if(flag!=1){
            countn(parse,flp);
        }
        if(parse[0]!='\n' && parse[0]!='i' && parse[1]!='n' && i==3){
            //printf("These are the operation lines:\n");
        }
        parse = strtok(NULL,"{;");
        i++;
    }
}
