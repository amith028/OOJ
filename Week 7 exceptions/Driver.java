import java.util.Scanner;

class WrongAgeExcpetion extends Exception{
	int ag;
	public WrongAgeExcpetion(String message, int ag){
	super(message);
	this.ag = ag;
	}

	@Override
	public String toString(){
		return "Invalid age: "+ ag +"\n"+getMessage();
	}

}

class Father{
	int fage;
	protected String fname = new String();
	boolean getfdetails(){
		Scanner sc = new Scanner(System.in);
		try{
			System.out.println("Enter the name of the father");
			this.fname = sc.nextLine();
			System.out.println("Enter the age of the father");
			this.fage = sc.nextInt();
			if(this.fage<21){
				throw new WrongAgeExcpetion("Enter a valid age!! Minimum age of father must be 21 \n", this.fage);
			}
			else{
				System.out.println("The details of the father are");
				System.out.println("Name of the father: "+this.fname);
				System.out.println("Age of the father: "+this.fage);
				return true;
			}
 		}
		catch (WrongAgeExcpetion e){
			System.out.println(e);
			return false;
		}
	}
}

class Son extends Father{
	int sage;
	protected String sname = new String();
	void getsdetails(){
		Scanner sc = new Scanner(System.in);
		try{
			System.out.println("Enter the name of the son");
			this.sname = sc.nextLine();
			System.out.println("Enter the age of the son");
			this.sage = sc.nextInt();
			if(super.fage<this.sage){
				throw new WrongAgeExcpetion("Enter a valid age!! Son's age cannot be greater than father's age\n", this.sage);
			}
			else if(super.fage-this.sage<=21){
				throw new WrongAgeExcpetion("Enter a valid age!! Age difference of son and father must be atleast 21 years!! \n", this.sage);

			}
			else{
				System.out.println("The details of the son are");
				System.out.println("Name of the son: "+this.sname);
				System.out.println("Age of the son: "+this.sage);
			}

  		}
		catch (WrongAgeExcpetion e){
			System.out.println(e);
		}
	}

}


class Driver{
	public static void main(String args[]){
	System.out.println("Name: Amith R\nUSN: 1BM23CS028\n");
	Son s = new Son();
	if(s.getfdetails()){
		s.getsdetails();
	}
	else{
		System.out.println("Father's details are invalid; so you cannot enter son's details \n");
	}
	}
}
