# About the Project:
* The purpose of this project entitled “Hospital Management System” is to develop and provide a user-friendly software which is simple, fast, reliable, and cost-effective. The main function of the system is to store the details of doctors and patients, and retrieve them as and when required, and also to manipulate these details meaningfully.
* Tha databases are managed using regular textual file handling (for Alloted Rooms), and object file handling using the serializable class (for doctor and patient records).
* This project on Hospital Management System using Java provides distinct functionalities to different types of users (doctors, patients and admin).


## Functionalities
### Doctor
A doctor has the options to:-
1. View the scheduled appointments for her/him and the details of her/his patient.

### Patient
A patient has the options to:-
1. View the facilities provided by the hospital.
2. View the details of available doctors – their qualification, specialization and
contact number.
3. Booking an appointment.
4. Cancelling an appointment.


### Admin
The admin holds the rights to manage (add/remove/modify) the details of doctors and hospital
facilities; and also to allot a room to a patient requiring hospitalization. This portal is, however,
***password-protected***.

# Instructions on Running the File
1. Download the [Source Code](https://github.com/Varada-D/Hospital-Management-System/blob/main/HospitalManagementSystem.java)
2. Ensure that you have [JDK 11](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html) installed in your machine
3. If you want some pre-populated data in your database, please download the contents of the [Databases](https://github.com/Varada-D/Hospital-Management-System/tree/main/Databases) folder of this repository, and store the files at the same location as your source code. If you have changed the filenames in your code, kindly ensure that the names of the database too, are changed accordingly.  
***Warning:*** Do not change the database files manually
4. Open your Command Prompt, and navigate to the folder containing the source code  
        > Syntax in case the source code is in the same drive as the current: **cd \<path\>**  
        > If you need to change drive to navigate to the required folder: **cd \d \<path\>**
5. After navigating to the required path, compile the java file by typing:  
        > If you haven't changed the file name of the source code: **javac HospitalManagementSystem.java**  
        > Else: **javac \<filename\>.java**  
6. On successful compilation, run the class containing the main function  
        > If you haven't changed the class names: **java Hospital**  
        > Else: **java \<name_of_class_containing_main_function\>**

# Future Enhancements:
* The future updates for this project can contain data validation for user inputs. This would ensure that we are getting a valid input from the user.
* The current system, being command-line based, seems to be monotonous and boring to users. Developing the system further, encorporating a GUI (Graphics User Interface) would make the system more attractive, accessible and easy to use.
* As an additional functionality, we can also extend the project by syncing/attaching it to web portals as well. This way, a patient would not have to search elsewhere or call up or go to the hospital to book an appointment. She/he can do it easily within the comfort of her/his home over the internet as well.


# Demo, Source Code and Project Report:
* A demo of the project is available at: [Demo Video](https://github.com/Varada-D/Hospital-Management-System/blob/main/Hospital%20Management%20System%20Demo.mp4)
* For the source code for the project, kindly refer: [Source Code](https://github.com/Varada-D/Hospital-Management-System/blob/main/HospitalManagementSystem.java)
* The project report is available at: [Project Report](https://github.com/Varada-D/Hospital-Management-System/blob/main/Hospital%20Management%20System%20-%20Project%20Report.pdf)

# Some Helpful Resources:
* https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
* https://stackoverflow.com/questions/27409718/java-reading-multiple-objects-from-a-file-as-they-were-in-an-array
* https://stackoverflow.com/questions/17293991/how-to-write-and-read-java-serialized-objects-into-a-file
* https://stackoverflow.com/questions/2979383/how-to-clear-the-console
* https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
