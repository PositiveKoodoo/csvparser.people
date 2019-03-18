import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class ParsingHelper {


    public static void main(String[] args) {
        CSVReader reader = null;
        LinkedList<Person> people = new LinkedList<>();

        //TODO: nicht alles in einem riesigen try/catch-Block machen.
        try {
            reader = new CSVReader(new FileReader(".//src/main/resources/one.csv"));
            String [] nextLine;

            while ((nextLine = reader.readNext()) != null){
                String lastName = nextLine[0];
                /*Here I wanted to do some regex-matching to check if the input consists of only letters, no numbers
                 & no special characters but after reading
                 https://www.kalzumeus.com/2010/06/17/falsehoods-programmers-believe-about-names/
                 I decided against it.
                 Would have probably been something like this: "[A-Z][a-z]*" */
                String firstName = nextLine[1];
                //no regex-matching. Reasons see above.

                String street = nextLine[2];
                //no regex-matching. Really don't wanna touch that.
                // https://www.mjt.me.uk/posts/falsehoods-programmers-believe-about-addresses/

                String zip = nextLine[3];
                int zipNumber = 0;
                try {
                    zipNumber = Integer.parseInt(zip);
                } catch (NumberFormatException n){
                    System.out.println("zip-code must be a number");
                }

                String city = nextLine[4];

                String birthday = nextLine[5];
                String day, month, year;
                if(Pattern.matches("\\d{2}\\.\\d{2}\\.\\d{4}",birthday)){
                    day = birthday.substring(0,2);
                    month = birthday.substring(3,5);
                    year = birthday.substring(6);
                } else {
                    throw new IllegalArgumentException("Date of birth must be in a DD.MM.YYYY format");
                }
                int dayNumbers = 0;
                int monthNumbers = 0;
                int yearNumbers = 0;
                try{
                    dayNumbers = Integer.parseInt(day);
                    monthNumbers = Integer.parseInt(month);
                    yearNumbers = Integer.parseInt(year);
                } catch (NumberFormatException e){
                    System.out.println("Date of may only consist of digits and .");
                }
                LocalDate dateOfBirth = LocalDate.of(yearNumbers,monthNumbers,dayNumbers);

                Person p = new Person(firstName,lastName,street,zipNumber,city,dateOfBirth);
                people.add(p);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        for(Person person : people){
            System.out.println(person.toString());
        }

        //finally {
            try {
                if(reader !=null) {
                    /*
                    Don't think the if is really necessary, since the program should have stopped with an exception, if
                    creating the reader was not possible, but intelliJ would not stop warning so here we go.
                     */
                    reader.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        //}

    }
}
