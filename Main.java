import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){

            System.out.println("Enter data separated by spaces: Surname Name  Patronymic DateOfBirth(format dd.mm.yyyy) telefonNumber (Integer) gender (f or m)");
            String input = scanner.nextLine();

            String[] data = input.split(" ");
            if (data.length <6) {
                throw new IllegalArgumentException("Less data entered than required");
            }
            if (data.length >6) {
                throw new IllegalArgumentException("More data entered than required");
            }

            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String birthDate = data[3];
            if (!birthDate.matches("\\d{2}.\\d{2}.\\d{4}")) {
                throw new IllegalArgumentException("Invalid date of birth format");
            }
            
            long  phoneNumber=0;                      
            try{
                phoneNumber = Long.parseLong(data[4]);
            }catch(NumberFormatException e){
                System.out.println("Error when entering a phone number: " + e.getMessage());
                System.exit(0);;
            }
            

            char gender = data[5].charAt(0);
            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Invalid gender value. must be f or m");
            }
        
            String fileName = surname + ".txt";

            String dataOutput=concatenateArguments(surname, name, patronymic, birthDate, phoneNumber, gender);
            writeToFile(fileName, dataOutput);
            System.out.println("Data was successfully written to the file " + fileName);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
           
        } catch (IOException e) {
             e.printStackTrace();
        }

    }

    public static String concatenateArguments(String arg1, String arg2, String arg3, String arg4, long arg5, char arg6) {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(arg1).append(">");
        sb.append("<").append(arg2).append(">");
        sb.append("<").append(arg3).append(">");
        sb.append("<").append(arg4).append(">");
        sb.append("<").append(arg5).append(">");
        sb.append("<").append(arg6).append(">");
        return sb.toString();
    }

    private static void writeToFile(String fileName, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.write(data);
        writer.newLine();
        writer.close();
    }

}