import java.io.*;
import java.util.Scanner;
import java.io.IOException;

interface Aeronava {
	abstract String getType();

	abstract String[] getLocation();

	abstract void setLocation(String type, String number);
}

class Elicopter implements Aeronava {
	private String type;
	private String[] location;

	public Elicopter(String t) {
		this.type = t;
		this.location = new String[2];
	}

	public String getName() {
		return "Elicopter";
	}

	public String getType() {
		return this.type;
	}

	public String[] getLocation() {
		return this.location;
	}

	public void setLocation(String type, String number) {
		location[0] = type;
		location[1] = number;
	}
}

class Avion implements Aeronava {
	private String type;
	private String[] location;

	public Avion(String t) {
		this.type = t;
		this.location = new String[2];
	}

	public String getName() {
		return "Avion";
	}

	public String getType() {
		return this.type;
	}

	public String[] getLocation() {
		return this.location;
	}

	public void setLocation(String type, String number) {
		location[0] = type;
		location[1] = number;
	}
}

interface Stocare {
	abstract String getType();

	abstract String getNumber();

	abstract boolean addAeronava(Aeronava a);
}

class Hangar implements Stocare {
	Aeronava[] aeronave;
	private String number;
	private int nrAeronave;

	public Hangar(String n) {
		this.number = n;
		this.aeronave = new Aeronava[5];
		this.nrAeronave = 0;
	}

	public String getType() {
		return "Hangar";
	}

	public String getNumber() {
		return this.number;
	}

	public boolean addAeronava(Aeronava a) {
		if (this.nrAeronave > 0) {
			for (int i = 0; i < this.nrAeronave; i++)
				if (a.equals(this.aeronave[i])) {
					System.out.println(getType() + " " + getNumber() + " full, nu se mai pot adauga aeronave");
					return false;
				}
		}
		if (this.nrAeronave == 5) {
			System.out.println(getType() + " " + getNumber() + " full, nu se mai pot adauga aeronave");
			return false;
		}
		this.aeronave[this.nrAeronave] = a;
		this.aeronave[this.nrAeronave].setLocation(getType(), getNumber());
		this.nrAeronave++;
		return true;
	}
}

class Pista implements Stocare {
	Aeronava[] aeronave;
	private String number;
	private int nrAeronave;

	public Pista(String n) {
		this.number = n;
		this.aeronave = new Aeronava[10];
		this.nrAeronave = 0;
	}

	public String getNumber() {
		return this.number;
	}

	public String getType() {
		return "Pista";
	}

	public boolean addAeronava(Aeronava a) {
		if (this.nrAeronave > 0) {
			for (int i = 0; i < this.nrAeronave; i++)
				if (a.equals(aeronave[i])) {
					System.out.println(getType() + " " + getNumber() + " full, nu se mai pot adauga aeronave");
					return false;
				}
		}
		if (this.nrAeronave == 5) {
			System.out.println(getType() + " " + getNumber() + " full, nu se mai pot adauga aeronave");
			return false;
		}
		this.aeronave[nrAeronave] = a;
		this.aeronave[nrAeronave].setLocation(getType(), getNumber());
		this.nrAeronave++;
		return true;
	}
}

public class Aeroport {
	static Stocare[] stoc = new Stocare[10];
	static String[] deStocat = new String[4];
	static int numarStoc = 0;

	public static void main(String[] args) {
		System.out.print("File name: ");
		Scanner read = new Scanner(System.in);
		String fileName = new String();
		fileName = read.next();
		read.close();
		try {
			File file = new File(fileName);
			if (file.createNewFile()) {
				System.out.println("New file created.");
			} else {
				System.out.println("File already exists. Loading information.");
				Scanner fileReader = new Scanner(file);
				if (!fileReader.hasNextLine())
					System.out.println("File is empty.");
				else {
					while (fileReader.hasNextLine()) {
						String line = new String();
						line = fileReader.nextLine();
						deStocat = line.split(" ");
						if (deStocat[0].equalsIgnoreCase("hangar")) {
							stoc[numarStoc] = new Hangar(deStocat[1]);
							if (deStocat[2].equalsIgnoreCase("avion")) {
								Aeronava a = new Avion(deStocat[3]);
								stoc[numarStoc].addAeronava(a);
							}
							if (deStocat[2].equalsIgnoreCase("elicopter")) {
								Aeronava a = new Elicopter(deStocat[3]);
								stoc[numarStoc].addAeronava(a);
							}
							numarStoc++;
						}
						if (deStocat[0].equalsIgnoreCase("pista")) {
							stoc[numarStoc] = new Pista(deStocat[1]);
							if (deStocat[2].equalsIgnoreCase("avion")) {
								Aeronava a = new Avion(deStocat[3]);
								stoc[numarStoc].addAeronava(a);
							}
							if (deStocat[2].equalsIgnoreCase("elicopter")) {
								Aeronava a = new Elicopter(deStocat[3]);
								stoc[numarStoc].addAeronava(a);
							}
							numarStoc++;
						}
					}//WHILE
				}
				fileReader.close();	
			}
		} catch (IOException exception) {
			System.err.println(exception);
		}
		String rasp=new String();
		rasp="da";
		Scanner readRasp=new Scanner(System.in);
		System.out.println("Introduceti nava in formatul: <hangar/pista> <numar> <avion/elicopter> <militar/agrement/...>");
		while(rasp.equalsIgnoreCase("da")){
			deStocat[0]=readRasp.next();
			deStocat[1]=readRasp.next();
			deStocat[2]=readRasp.next();
			deStocat[3]=readRasp.next();
			if (deStocat[0].equalsIgnoreCase("hangar")) {
				stoc[numarStoc] = new Hangar(deStocat[1]);
				if (deStocat[2].equalsIgnoreCase("avion")) {
					Aeronava a = new Avion(deStocat[3]);
					stoc[numarStoc].addAeronava(a);
				}
				if (deStocat[2].equalsIgnoreCase("elicopter")) {
					Aeronava a = new Elicopter(deStocat[3]);
					stoc[numarStoc].addAeronava(a);
				}
				numarStoc++;
			}
			if (deStocat[0].equalsIgnoreCase("pista")) {
				stoc[numarStoc] = new Pista(deStocat[1]);
				if (deStocat[2].equalsIgnoreCase("avion")) {
					Aeronava a = new Avion(deStocat[3]);
					stoc[numarStoc].addAeronava(a);
				}
				if (deStocat[2].equalsIgnoreCase("elicopter")) {
					Aeronava a = new Elicopter(deStocat[3]);
					stoc[numarStoc].addAeronava(a);
				}
				numarStoc++;
			}
			//DE SCRIS IN FISIER
			System.out.println("Continuati? (da/nu)");
			rasp=readRasp.nextLine();
		}
	}
}
