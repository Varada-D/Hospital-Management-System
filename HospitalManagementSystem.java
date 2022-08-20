
import java.util.*;
import java.io.*;

class Hospital implements Serializable{
	static String MonthsOfYear[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	static String DaysOfWeek[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	static String am_pm[] = { "AM", "PM" };
	static char[] adminPwd = "admin@xyzhospital".toCharArray();
	static void Display() {
		System.out.println("XYZ Hospital");
		System.out.println("Phone Number: 1234567890");
		System.out.println("Email: admin@xyzhospital.com");
		System.out.println("Website: https://www.xyzhospital.org");
	}
	

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
	
	
    public static void WriteObjectToFile(Object serObj, File fileName) {
        try {
 
            FileOutputStream fileOut = new FileOutputStream(fileName, true);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	public static void printRecords(File RecordFile) {
        try {
			if (RecordFile.length()!=0) {
				FileInputStream F = new FileInputStream(RecordFile);
				ObjectInputStream input = new ObjectInputStream(F);
				Object obj = input.readObject();
				while(obj!=null) {
					try {
						if (obj != null) {
							if (obj instanceof Doctor) {
								System.out.println("\n Name" +  "\t\t\t Phone Number" + "\t\t\t Qualification" + "\t\t\t Specialization");
								Doctor d = (Doctor) obj;
								System.out.println('\n' + d.Name +  "\t\t" + d.PhoneNumber + "\t\t\t" + d.Qualification + "\t\t" + d.Specialization);
							}
							else if (obj instanceof Patient) {
								System.out.println("\n Name" + "\t\t\t Age" + "\t\t\t Address" + "\t\t\t Phone Number" + "\t\t\t Blood Group" + "\t\t\t Gender" + "\t\t\t Doctor Name" + "\t\t\t Appointment Date");
								Patient p = (Patient) obj;
								System.out.println('\n' + p.Name + "\t\t" + p.Age + "\t\t" + p.Address + "\t\t" + p.PhoneNumber + "\t\t" + p.BloodGroup + "\t\t" + p.Gender + "\t\t" + p.DocName + "\t\t" + p.AppDate);
							}
							obj = input.readObject();
						}
					} catch (Exception e) {
						break;
					}
				}
				input.close();
				F.close();
			}
			else
				System.out.println("File Empty.");
        } catch (Exception ex) {
			System.out.println("An error occurred.");
        }
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			clear();
			Calendar calendar = Calendar.getInstance();
			System.out.print("Date: " + MonthsOfYear[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR));
			System.out.print("\t\t\t\t\t\t\t\tDay: " + DaysOfWeek[calendar.get(Calendar.DAY_OF_WEEK)-1]);
			System.out.print("\t\t\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " " + am_pm[calendar.get(Calendar.AM_PM)]);
			System.out.print("\n\nWelcome to ");
			Display();
			System.out.println("\nWhich Portal would you like to access?" + "\n1. Doctor" + "\n2. Patient" + "\n3. Admin" + "\n4. Exit");
			System.out.print("Enter option number: ");
			choice = sc.nextInt();
			switch(choice) {
				case 1: Doctor d = new Doctor();
						break;
				case 2: Patient p = new Patient();
						break;
				case 3: System.out.println("Enter Password: ");
						Console con = System.console(); 
						sc.nextLine();
						char[] pwd = con.readPassword();
						if (!Arrays.equals(pwd,adminPwd)) {
							System.out.println("\nIncorrect Password. Entry to admin portal denied!");
							sc.nextLine();
							continue;
						}
						Admin a = new Admin();
						break;
				case 4: clear();
						System.out.print("\nThank you for showing your trust in ");
						Display();
						break;
				default: System.out.println("Invalid Choice. Please Re-enter.");
			} 
		}while(choice!=4);
	}
}

class Doctor extends Hospital {
	String Name, PhoneNumber, Qualification, Specialization, AddDate, AddTime;
	Doctor() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			clear();
			Calendar calendar = Calendar.getInstance();
			System.out.print("Date: " + MonthsOfYear[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR));
			System.out.print("\t\t\t\t\t\t\t\tDay: " + DaysOfWeek[calendar.get(Calendar.DAY_OF_WEEK)]);
			System.out.print("\t\t\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " " + am_pm[calendar.get(Calendar.AM_PM)]);
			System.out.print("\n\nWelcome to the Doctor Portal of ");
			Display();
			System.out.println("\nWhat would you like to do?" + "\n1. View Patient Appointments" + "\n2. Back to Main Menu");
			System.out.print("Enter option number: ");
			choice = sc.nextInt();
			switch(choice) {
				case 1: clear();
						ViewAppointments();
						sc.nextLine();
						sc.nextLine();
						break;
				case 2: break;
				default: System.out.println("Invalid Choice. Please Re-enter.");
			} 
		}while(choice!=2);
	}
	Doctor (String Name, String PhoneNumber, String Qualification, String Specialization, String AddDate, String AddTime) {
		this.Name = Name;
		this.PhoneNumber = PhoneNumber;
		this.Qualification = Qualification;
		this.Specialization = Specialization;
		this.AddDate = AddDate;
		this.AddTime = AddTime;
		File F = new File("DoctorRecords.txt");
		WriteObjectToFile(this, F);
	}
	static void ViewAppointments() {
		Display();
		Scanner sc = new Scanner(System.in);
		String nm;
		System.out.print("\n\nEnter your name to view the appointments booked with you: ");
		nm = sc.nextLine();
		boolean deleted = false;
        try {
			if ("PatientRecords.txt".length()!=0) {
				FileInputStream F = new FileInputStream("PatientRecords.txt");
				ObjectInputStream input = new ObjectInputStream(F);
				Object obj = input.readObject();
				File temp = new File("temp.txt");
				while(obj!=null) {
					try {
						if (obj != null) {
							if (obj instanceof Patient) {
								Patient p= (Patient) obj;
								if (nm.equalsIgnoreCase(p.DocName)) {
									p.getDetails();
								}
							}
							obj = input.readObject();
						}
					} catch (Exception e) {
						break;
					}
				}
				input.close();
				F.close();
			}
			else
				System.out.println("File Empty.");
        } catch (Exception ex) {
			System.out.println("An error occurred.");
        }
	}
}

class Patient extends Hospital {
	String Name, Address, BloodGroup, Gender, PhoneNumber, DocName, AppDate, BookDate, BookTime;
	int Age;
	Patient() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			clear();
			Calendar calendar = Calendar.getInstance();
			System.out.print("Date: " + MonthsOfYear[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR));
			System.out.print("\t\t\t\t\t\t\t\tDay: " + DaysOfWeek[calendar.get(Calendar.DAY_OF_WEEK)]);
			System.out.print("\t\t\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " " + am_pm[calendar.get(Calendar.AM_PM)]);
			System.out.print("\nWelcome to the Patient Portal of ");
			Display();
			System.out.println("\nWhat would you like to do?" + "\n1. View Hospital Facilities" + "\n2. View Doctor List" + "\n3. Book an Appointment" + "\n4. Back to Main Menu");
			System.out.print("Enter option number: ");
			choice = sc.nextInt();
			switch(choice) {
				case 1: clear();
						viewFacilities();
						sc.nextLine();
						sc.nextLine();
						break;
				case 2: clear();
						File f = new File("DoctorRecords.txt");
						printRecords(f);
						sc.nextLine();
						sc.nextLine();
						break;
				case 3: getDetails();
						File F = new File("PatientRecords.txt");
						WriteObjectToFile(this, F);
						System.out.println("\n\nBooking Successful. Here are the details we got from you:-");
						printDetails();
						sc.nextLine();
						sc.nextLine();
						break;
				case 4: break;
				default: System.out.println("Invalid Choice. Please Re-enter.");
			} 
		}while(choice!=4);
	}
	
	void getDetails() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Patient Name: ");
		Name = sc.nextLine();
		System.out.print("Patient Age: ");
		Age = sc.nextInt();
		System.out.print("Patient Gender: ");
		sc.nextLine();
		Gender = sc.nextLine();
		System.out.print("Patient Address: ");
		Address = sc.nextLine();
		System.out.print("Patient Contact Number: ");
		PhoneNumber = sc.nextLine();
		System.out.println("\n\n");
		if ("PatientRecords.txt".length()==0) {
			do {
				File f = new File("DoctorRecords.txt");
				printRecords(f);
				System.out.print("\n\nDoctor Name: ");
				DocName = sc.nextLine();
				Calendar calendar = Calendar.getInstance();
				BookDate = MonthsOfYear[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.YEAR);
				System.out.print("Appointment date eg: " + BookDate + ": ");
				AppDate = sc.nextLine();
				BookTime = calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
			} while (!checkFreeApp(DocName, AppDate));
		}
		else {
				File f = new File("DoctorRecords.txt");
				printRecords(f);
				System.out.print("\n\nDoctor Name: ");
				DocName = sc.nextLine();
				Calendar calendar = Calendar.getInstance();
				BookDate = MonthsOfYear[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.YEAR);
				System.out.print("Appointment date eg: " + BookDate + ": ");
				AppDate = sc.nextLine();
				BookTime = calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
		}
	}
	void printDetails() {
		System.out.println("Patient Name: " + Name);
		System.out.println("Patient Age: " + Age);
		System.out.println("Patient Gender: " + Gender);
		System.out.println("Patient Address: " + Address);
		System.out.println("Patient Contact Number: " + PhoneNumber);
		System.out.println("Book Date: " + BookDate);
		System.out.println("Book Time: " + BookTime);
	}
	
	public static boolean checkFreeApp(String DocName, String AppDate) {
		int count=0;
		try {
			System.out.println("Doctor Name\t\t Contact Number \t Qualification\t\tSpecialization");
			Scanner pw = new Scanner(new BufferedReader(new FileReader("DoctorRecords.txt")));
			int i=0;
			while(pw.hasNext()) {
				String line = pw.nextLine();
				String[] lineparts = line.split("\t", -1);
				if (lineparts[7].equalsIgnoreCase(DocName) && lineparts[8].equalsIgnoreCase(AppDate))
					count = count + 1;
			}
		} 	catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				} 
		if (count>10) {
			System.out.println("Appointments for " + DocName + " for " + AppDate + " is full. Please try again for another doctor/date.");
			return false;
		}
		else {
			System.out.println("Appointment Successful.");
			return true;
		}
	}
	
	void viewFacilities() {
		System.out.println("\n Radiology (X-Ray) \n Sonography \n CT Scan \n Physiotherapy \n ECG \n Ambulance Services \n Laboratory Services");
	}
}

class Admin extends Hospital {
	Admin() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			clear();
			Calendar calendar = Calendar.getInstance();
			System.out.print("Date: " + MonthsOfYear[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR));
			System.out.print("\t\t\t\t\t\t\t\tDay: " + DaysOfWeek[calendar.get(Calendar.DAY_OF_WEEK)]);
			System.out.print("\t\t\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " " + am_pm[calendar.get(Calendar.AM_PM)]);
			System.out.print("\n\nWelcome to the Admin Portal of ");
			Display();
			System.out.println("\nWhat would you like to do?" + "\n1. Add to Doctor List" + "\n2. Delete from Doctor List" + "\n3. Allot Room" + "\n4. View Room Allotment Details" + "\n5. Back to Main Menu");
			System.out.print("Enter option number: ");
			choice = sc.nextInt();
			switch(choice) {
				case 1: AddDoc();
						sc.nextLine();
						sc.nextLine();
						break;
				case 2: if (DeleteDoc())
							System.out.println("\nRecord Deleted Successfully");
						else
							System.out.println("\nRecord not Found");
						sc.nextLine();
						sc.nextLine();
						break;
				case 3: AllotRoom();
						sc.nextLine();
						sc.nextLine();
						break;
				case 4: ViewRoomAllotment();
						sc.nextLine();
						sc.nextLine();
						break;
				case 5: break;
				default: System.out.println("Invalid Choice. Please Re-enter.");
			} 
		} while(choice!=5);
	}
	static void AddDoc() {
		Scanner sc = new Scanner(System.in);
		String Name, PhoneNumber, Qualification, Specialization, AddDate, AddTime;
		System.out.print("Doctor Name: ");
		Name = sc.nextLine();
		System.out.print("Doctor Contact Number: ");
		PhoneNumber = sc.nextLine();
		System.out.print("Doctor Qualification: ");
		Qualification = sc.nextLine();
		System.out.print("Doctor Specialization: ");
		Specialization = sc.nextLine();
		Calendar calendar = Calendar.getInstance();
		AddDate = MonthsOfYear[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.YEAR);
		AddTime = calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
		Doctor d = new Doctor(Name, PhoneNumber, Qualification, Specialization, AddDate, AddTime);
	}
	static boolean DeleteDoc() {
		Scanner sc = new Scanner(System.in);
		String nm;
		System.out.print("Enter doctor name to delete her/his details from the list: ");
		nm = sc.nextLine();
		boolean deleted = false;
        try {
			if ("DoctorRecords.txt".length()!=0) {
				FileInputStream F = new FileInputStream("DoctorRecords.txt");
				ObjectInputStream input = new ObjectInputStream(F);
				Object obj = input.readObject();
				File temp = new File("temp.txt");
				while(obj!=null) {
					try {
						if (obj != null) {
							if (obj instanceof Doctor) {
								Doctor d = (Doctor) obj;
								if (nm.equalsIgnoreCase(d.Name)) {
									deleted = true;
								}
								else {
									WriteObjectToFile(d, temp);
								}
							}
							obj = input.readObject();
						}
					} catch (Exception e) {
						break;
					}
				}
				input.close();
				F.close();
				File oldFile = new File("DoctorRecords.txt");
				File newFile = new File("temp.txt");
				oldFile.delete();
				newFile.renameTo(oldFile);
			}
			else
				System.out.println("File Empty.");
        } catch (Exception ex) {
			System.out.println("An error occurred.");
        }
		return deleted;
	}
	static void AllotRoom() {
		Scanner sc = new Scanner(System.in);
        String  PatientName, PatientId, RoomNo, TypeOfRoom="";
		int ServiceCharges = 0;
        System.out.print("\nPatient Name: ");
        PatientName = sc.nextLine();
        System.out.print("Patient id: ");
        PatientId = sc.nextLine();
        System.out.print("Room no: ");
        RoomNo = sc.nextLine();
        int RoomType = 0;
		do {
			System.out.print("\nSelect your Room Type:- \n1. General Ward \n2. Semi-ward \n3. Deluxe Room \nEnter option number: ");
			RoomType = sc.nextInt();
			switch(RoomType){
				case 1:
					System.out.println("You have alloted General Ward.");
					ServiceCharges = 500;
					TypeOfRoom = "General Ward";
					break;
				case 2:
					System.out.println("You have alloted Semi-ward.");
					ServiceCharges = 1000;
					TypeOfRoom = "Semi-ward";
					break;
				case 3:
					System.out.println("You have alloted Deluxe room.");
					ServiceCharges = 1500;
					TypeOfRoom = "Deluxe Room";
					break;
				default:
					System.out.println("Invalid option. Please re-enter!");
			}
        } while(RoomType<1 || RoomType>3);
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try {  
			fw = new FileWriter("AllotedRooms.txt", true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.write("\n" + PatientName + "\t" + PatientId + "\t" + RoomNo + "\t" + TypeOfRoom + "\t" + ServiceCharges);
			pw.close();
		} 	catch (IOException e) {
					System.out.println("An error occurred.");
			}
    }
	
	static void ViewRoomAllotment() {
		if("AllotedRooms.txt".length()>0) {
			System.out.println("\n\nPatient Name \t\t Patient Id \t Room Number \t\t TypeOfRoom \t\t Service Charges");
			try {
				Scanner pw = new Scanner(new BufferedReader(new FileReader("AllotedRooms.txt")));
				int i;
				while(pw.hasNext()) {
					String line = pw.nextLine();
					String[] lineparts = line.split("\t", -1);
					i=0;
					for(String data : lineparts)
						if (i<5) {
							System.out.print(data + "\t\t");
							i++;
						}
					System.out.println();
				}
			} 	catch (IOException e) {
						System.out.println("File Empty.");
					} 
		}
	}
}
