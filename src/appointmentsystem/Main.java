package appointmentsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AppointmentDAO dao = new AppointmentDAO();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("==============================================");
        System.out.println("             PEEJAY SALON STATION");
        System.out.println("==============================================");

        while (true) {
            System.out.println("\n=== Appointment System Menu ===");
            System.out.println("1. ➤ Add Appointment");
            System.out.println("2. ➤ View Appointments");
            System.out.println("3. ➤ Update Appointment");
            System.out.println("4. ➤ Delete Appointment");
            System.out.println("5. ➤ Exit");
            System.out.print("Select an option (1-5): ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Restart the loop
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- Add Appointment ---");
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String dateStr = scanner.nextLine();
                    java.util.Date date;
                    try {
                        date = dateFormat.parse(dateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        break;
                    }

                    System.out.print("Enter time (HH:mm:ss): ");
                    String time = scanner.nextLine();

                    System.out.print("Enter notes: "); // Fixed the prompt
                    String notes = scanner.nextLine();

                    Appointment appointment = new Appointment();
                    appointment.setName(name);
                    appointment.setDate(date);
                    appointment.setTime(time);
                    appointment.setNotes(notes);
                    dao.addAppointment(appointment);
                    System.out.println("✅ Appointment added successfully.");
                    break;

                case 2:
                    System.out.println("\n--- View Appointments ---");
                    List<Appointment> appointments = dao.getAllAppointments();
                    if (appointments.isEmpty()) {
                        System.out.println("No appointments found.");
                    } else {
                        System.out.println("=======================================");
                        System.out.println("         APPOINTMENT RECEIPT           ");
                        System.out.println("=======================================");
                        
                        for (Appointment a : appointments) {
                            System.out.println("***************************************");
                            System.out.printf("ID: %-30s%n", a.getId());
                            System.out.printf("Name: %-28s%n", a.getName());
                            System.out.printf("Date: %-28s%n", dateFormat.format(a.getDate()));
                            System.out.printf("Time: %-28s%n", a.getTime());
                            System.out.printf("Notes: %-27s%n", a.getNotes());
                            System.out.println("***************************************");
                        }
                        
                        System.out.println("=======================================");
                        System.out.println("         END OF APPOINTMENTS           ");
                        System.out.println("=======================================");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Update Appointment ---");
                    System.out.print("Enter the ID of the appointment to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new name: ");
                    String updateName = scanner.nextLine();

                    System.out.print("Enter new date (yyyy-MM-dd): ");
                    String updateDateStr = scanner.nextLine();
                    java.util.Date updateDate;
                    try {
                        updateDate = dateFormat.parse(updateDateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        break;
                    }

                    System.out.print("Enter new time (HH:mm:ss): ");
                    String updateTime = scanner.nextLine();

                    System.out.print("Enter new notes: ");
                    String updateNotes = scanner.nextLine();

                    Appointment updateAppointment = new Appointment();
                    updateAppointment.setId(updateId);
                    updateAppointment.setName(updateName);
                    updateAppointment.setDate(updateDate);
                    updateAppointment.setTime(updateTime);
                    updateAppointment.setNotes(updateNotes);
                    
                    // Check if the appointment exists before updating
                    if (dao.updateAppointment(updateAppointment)) {
                        System.out.println("✅ Appointment updated successfully.");
                    } else {
                        System.out.println("❌ Appointment not found.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Delete Appointment ---");
                    System.out.print("Enter the ID of the appointment to delete: ");
                    int deleteId = scanner.nextInt();
                    
                    // Check if the appointment exists before deleting
                    if (dao.deleteAppointment(deleteId)) {
                        System.out.println("✅ Appointment deleted successfully.");
                    } else {
                        System.out.println("❌ Appointment not found.");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using the Appointment System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice. Please select a valid option.");
            }
        }
    }
}

