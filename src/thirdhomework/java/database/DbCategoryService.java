package database;

import com.github.javafaker.Faker;
import models.Category;
import models.New;
import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DbCategoryService {
    public Scanner scanner = new Scanner(System.in);
    public ArrayList<Category> SelectProductsFromDb(String str)
    {
        try (Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            String query = "SELECT * FROM `categories`";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                ArrayList<Category> categories = new ArrayList<>();
                ResultSet data = statement.executeQuery();
                while(data.next())
                {
                    categories.add(new Category(data.getInt("Id"), data.getString("Title"),
                            data.getString("Description")));
                }

                return categories;
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
            String query = "INSERT INTO `categories` (`Title`, `Description`) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                String name, description;
                System.out.print("Ведіть назву категорії: ");
                name = scanner.nextLine();

                System.out.print("Ведіть опис категорії: ");
                description = scanner.nextLine();

                statement.setString(1, name);
                statement.setString(2, description);

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

    public void UpdateDb(String str)
    {
        try (Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            String query = "UPDATE `categories` SET `Title` = ?, `Description` =? WHERE `Id` = ?";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                int rows = 0;
                do {
                    System.out.println("Оновлення категорії:");
                    System.out.print("Ведіть ідентифікатор категорії: ");

                    String id;
                    String name, description;
                    if(scanner.hasNextInt())
                    {
                        id = scanner.nextLine();
                    }else
                    {
                        System.out.println("Не коректно введено дані!");
                        return;
                    }


                    System.out.print("Ведіть назву категорії: ");
                    name = scanner.nextLine();


                    System.out.print("Ведіть опис категорії: ");
                    description = scanner.nextLine();

                    statement.setString(1, name);
                    statement.setString(2, description);
                    statement.setString(3, id);
                    rows = statement.executeUpdate();
                    System.out.println("Обновлено кількість рядків: " + rows);

                    if(rows == 0)
                    {
                        System.out.println("Категорію не знайдено!");
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
            String query = "DELETE FROM `categories` WHERE `Id` = ?";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                int rows = 0;
                System.out.println("Видалення категорії:");
                do
                {
                    System.out.print("Ведіть ідентифікатор категорії: ");


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
                        System.out.println("Категорію не знайдено!");
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

    public void ShowAllItems(String str)
    {
        ArrayList<Category> categories = SelectProductsFromDb(str);
        for (Category category : categories)
        {
            System.out.println(category);
        }
    }
}
