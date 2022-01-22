package database;

import com.github.javafaker.Faker;
import models.New;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DbNewsService {
    public Scanner scanner = new Scanner(System.in);
    public ArrayList<New> SelectProductsFromDb(String str)
    {
        try (Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            String query = "SELECT * FROM `news`";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                ArrayList<New> news = new ArrayList<>();
                ResultSet data = statement.executeQuery();
                while(data.next())
                {
                    news.add(new New(data.getInt("Id"), data.getString("Title"),
                            data.getString("Text"),
                            data.getDate("DateCreated"), data.getInt("CategoryId")));
                }

                return news;
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
            String query = "INSERT INTO `news` (`Title`, `Text`, `DateCreated`, `CategoryId`) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                String name, description;
                int CategoryId;
                System.out.print("Ведіть назву новини: ");
                name = scanner.nextLine();

                System.out.print("Ведіть текст новини: ");
                description = scanner.nextLine();
                System.out.print("Ведіть ідентифікатор категорії, до якої належить новина: ");
                if(scanner.hasNextInt())
                {
                    CategoryId = Integer.parseInt(scanner.nextLine());
                }
                else
                {
                    System.out.println("Дані введено не коректно!");
                    return;
                }

                statement.setString(1, name);
                statement.setString(2, description);
                statement.setDate(3, new Date(System.currentTimeMillis()));
                statement.setInt(4, CategoryId);

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
            String query = "UPDATE `news` SET `Title` = ?, `Text` =?, `CategoryId`=? WHERE `Id` = ?";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                int rows = 0;
                do {
                    System.out.println("Оновлення новини:");
                    System.out.print("Ведіть ідентифікатор новини: ");

                    String id;
                    String name, description;
                    int categoryId;
                    if(scanner.hasNextInt())
                    {
                        id = scanner.nextLine();
                    }else
                    {
                        System.out.println("Не коректно введено дані!");
                        return;
                    }


                    System.out.print("Ведіть назву новини: ");
                    name = scanner.nextLine();


                    System.out.print("Ведіть опис новини: ");
                    description = scanner.nextLine();

                    System.out.print("Ведіть номер категорії до якої належить новина: ");
                    if(scanner.hasNextInt())
                    {
                        categoryId = Integer.parseInt(scanner.nextLine());
                    }
                    else
                    {
                        System.out.println("Не коректно введені дані!");
                        return;
                    }

                    statement.setString(1, name);
                    statement.setString(2, description);
                    statement.setInt(3, categoryId);
                    statement.setString(4, id);
                    rows = statement.executeUpdate();
                    System.out.println("Обновлено кількість рядків: " + rows);

                    if(rows == 0)
                    {
                        System.out.println("Новину не знайдено!");
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
            String query = "DELETE FROM `news` WHERE `Id` = ?";
            try (PreparedStatement statement = conn.prepareStatement(query))
            {
                int rows = 0;
                System.out.println("Видалення категорії:");
                do
                {
                    System.out.print("Ведіть ідентифікатор новини: ");


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
                        System.out.println("Новину не знайдено!");
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
        ArrayList<New> news = SelectProductsFromDb(str);
        for (New item : news)
        {
            System.out.println(item);
        }
    }
}
