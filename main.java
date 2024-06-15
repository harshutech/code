import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) || Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

class ContactManager {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        if (contacts.contains(contact)) {
            System.out.println("Contact with the same name or phone number already exists.");
        } else {
            contacts.add(contact);
            System.out.println("Contact added successfully.");
        }
    }

    public void deleteContact(String name) {
        Iterator<Contact> iterator = contacts.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                found = true;
                System.out.println("Contact deleted successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    public void viewAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();

        while (true) {
            System.out.println("\n1. Add Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. View All Contacts");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    Contact contact = new Contact(name, phoneNumber, email);
                    contactManager.addContact(contact);
                    break;
                case 2:
                    System.out.print("Enter name of the contact to delete: ");
                    String nameToDelete = scanner.nextLine();
                    contactManager.deleteContact(nameToDelete);
                    break;
                case 3:
                    contactManager.viewAllContacts();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
