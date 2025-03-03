package AgriTrac;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTra {
    static List<Tractor> tractors = new ArrayList<>();

    public static void addTractor(Tractor tractor) {
        tractors.add(tractor);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        
    

        while (true) {
            System.out.println("\n🚜 AgriTrac - Tractor Rental System 🚜");
            System.out.println("1. Check Service Availability (Distance)");
            System.out.println("2. Add more Tractor details");
            System.out.println("3. Remove a Tractor");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the distance within 10km range: ");
                    double dist = sc.nextDouble();
                    if (dist <= 10) {
                        System.out.println("✅ Service is Available in your area.");
                    } else {
                        System.out.println("❌ Sorry, we are not available in your area yet.");
                    }

                    System.out.println("The available tractors within the distance:");
                    for (Tractor t : tractors) {
                        if (t.km <= dist) {
                            System.out.println(t);
                        }
                    }
                    
                    System.out.print("Enter your budget (maximum price per hour): ₹");
                    double maxPrice = sc.nextDouble();

                    List<Tractor> affordableTractors = new ArrayList<>();
                    for (Tractor t : tractors) {
                        if (t.km <= dist && t.price <= maxPrice) {
                            affordableTractors.add(t);
                        }
                    }

                    if (affordableTractors.isEmpty()) {
                        System.out.println("❌ No tractors available within your budget.");
                    } else {
                        System.out.println("\n✅ Tractors available within your budget:");
                        for (Tractor t : affordableTractors) {
                            System.out.println(t);
                        }

                        System.out.print("\nEnter the ID of the tractor you want to book: ");
                        int selectedId = sc.nextInt();
                        boolean booked = false;

                        for (Tractor t : affordableTractors) {
                            if (t.id == selectedId) {
                                System.out.println("✅ " + t.name + " booked successfully at ₹" + t.price + " per hour!");
                                booked = true;
                                break;
                            }
                        }

                        if (!booked) {
                            System.out.println("❌ Invalid tractor ID. Please try again.");
                        }
                    }
                    break;
                
                case 2:
                    System.out.println("Enter Tractor ID Name Price Km:");
                    Tractor tractor = new Tractor(sc.nextInt(), sc.next(), sc.nextDouble(), sc.nextDouble());
                    addTractor(tractor);

                    try (FileWriter writer = new FileWriter("mytrac.txt", true)) {
                        writer.write(tractor.toString() + "\n");
                        System.out.println("✅ Tractor details saved successfully!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                
                case 3:
                    System.out.print("Enter the ID of the tractor to remove: ");
                    int removeId = sc.nextInt();
                    
                    boolean removed = tractors.removeIf(t -> t.id == removeId);
                    if (removed) {
                        System.out.println("✅ Tractor with ID " + removeId + " removed successfully.");
                    } else {
                        System.out.println("❌ Tractor ID not found.");
                    }
                    break;
                
                case 4:
                    System.out.println("🚜 Thank you for using AgriTrac!");
                    sc.close();
                    System.exit(0);
                    break;
                
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
}