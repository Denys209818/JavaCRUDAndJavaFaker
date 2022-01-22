package database;

import com.github.javafaker.Faker;
import models.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class DbQuery {
    public Scanner scanner = new Scanner(System.in);
    /*Різниця класу Exception та Throwable полягає у тому, що клас Throwable,
     * містить у собі підтипи Exception та Error, тобто даний клас більш обширний
     * оскільки відловлює не тільки виключення а й помилки, що звязані з роботою
     * самої віртуальної машини java*/
    public ArrayList<Product> SelectProductsFromDb(String str)
    {
        try (Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            String query = "SELECT * FROM `products`";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                ArrayList<Product> products = new ArrayList<>();
                ResultSet data = statement.executeQuery();
                while(data.next())
                {
                    products.add(new Product(data.getInt("Id"), data.getString("Name"),
                            data.getBigDecimal("Price"), data.getString("Description")));
                }

                return products;
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
                return null;
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Помилка підключення до БД!");
            return null;
        }
        catch (Throwable ex)
        {
            System.out.println("Exception driver: " + ex.getMessage());
            return null;
        }
    }

    public void InsertIntoDb(String str)
    {
        try (Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            String query = "INSERT INTO `products` (`Name`, `Price`, `Description`) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                String name, description;
                BigDecimal price;
                System.out.print("Ведіть назву продукту: ");
                name = scanner.nextLine();
                System.out.print("Ведіть ціну продукту: ");
                if(scanner.hasNextBigDecimal())
                {
                    price = new BigDecimal(scanner.nextLine());
                }else
                {
                    System.out.println("Не коректно введено дані!");
                    return;
                }
                System.out.print("Ведіть опис продукту: ");
                description = scanner.nextLine();

                statement.setString(1, name);
                statement.setBigDecimal(2, price);
                statement.setString(3, description);

                int rows = statement.executeUpdate();
                System.out.println("Обновлено кількість рядків: " + rows);
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Помилка підключення до БД!");
        }
        catch (Throwable ex)
        {
            System.out.println("Exception driver: " + ex.getMessage());
        }
    }

    public void InsertIntoDbFaker(String str, Product product)
    {
        try (Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            String query = "INSERT INTO `products` (`Name`, `Price`, `Description`) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                statement.setString(1, product.getName());
                statement.setBigDecimal(2, product.getPrice());
                statement.setString(3, product.getDescription());

                int rows = statement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Помилка підключення до БД!");
        }
        catch (Throwable ex)
        {
            System.out.println("Exception driver: " + ex.getMessage());
        }
    }

    public void UpdateDb(String str)
    {
        try (Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            String query = "UPDATE `products` SET `Name` = ?, `Price` = ?, `Description` =? WHERE `Id` = ?";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                int rows = 0;
                do {
                System.out.println("Оновлення продукту:");
                System.out.print("Ведіть ідентифікатор продукту: ");

                String id;
                String name, description;
                BigDecimal price;
                if(scanner.hasNextInt())
                {
                    id = scanner.nextLine();
                }else
                {
                    System.out.println("Не коректно введено дані!");
                    return;
                }


                System.out.print("Ведіть назву продукту: ");
                name = scanner.nextLine();
                System.out.print("Ведіть ціну продукту: ");
                if(scanner.hasNextBigDecimal())
                {
                    price = new BigDecimal(scanner.nextLine());
                }else
                {
                    System.out.println("Не коректно введено дані!");
                    return;
                }
                System.out.print("Ведіть опис продукту: ");
                description = scanner.nextLine();

                statement.setString(1, name);
                statement.setBigDecimal(2, price);
                statement.setString(3, description);
                statement.setString(4, id);
                rows = statement.executeUpdate();
                System.out.println("Обновлено кількість рядків: " + rows);

                if(rows == 0)
                {
                    System.out.println("Продукт не знайдено!");
                }

            }while(rows == 0);
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Помилка підключення до БД!");
        }
        catch (Throwable ex)
        {
            System.out.println("Exception driver: " + ex.getMessage());
        }
    }

    public void DeleteDb(String str)
    {
        try (Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            String query = "DELETE FROM `products` WHERE `Id` = ?";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                int rows = 0;
                System.out.println("Видалення продукту:");
                do
                {
                    System.out.print("Ведіть ідентифікатор продукту: ");


                String id;
                if(scanner.hasNextInt())
                {
                    id = scanner.nextLine();
                }else
                {
                    System.out.println("Не коректно введено дані!");
                    return;
                }

                statement.setString(1, id);

                rows = statement.executeUpdate();
                    System.out.println("Обновлено кількість рядків: " + rows);

                    if(rows == 0)
                {
                    System.out.println("Продукт не знайдено!");
                }

                }while(rows == 0);

            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Помилка підключення до БД!");
        }
        catch (Throwable ex)
        {
            System.out.println("Exception driver: " + ex.getMessage());
        }
    }

    public Boolean isEmpty(String str)
    {
        ArrayList<Product> products = SelectProductsFromDb(str);
        return products.isEmpty();
    }

    public void SeedData(String str)
    {
        if(isEmpty(str))
        {
        Faker faker = new Faker(new Locale("uk"));

        String productName, description;
        int price;

        for(int i = 0; i< 1000; i++)
        {
            Product product = new Product(i,
                    faker.commerce().productName(), new BigDecimal(faker.random().nextInt(5, 1000)),
                    faker.commerce().material());

            InsertIntoDbFaker(str, product);
        }
        }
    }
}
