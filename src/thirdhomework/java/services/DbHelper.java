package services;

import com.github.javafaker.Faker;

import java.sql.*;
import java.util.Locale;

public class DbHelper {
    public static void SeedAll(String str)
    {
        if(isEmpty(str))
        {
        try (Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            SeedCategories(conn);

            SeedProducts(conn);

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
    }

    private static void SeedCategories(Connection conn)
    {
        String query = "INSERT INTO `categories` (`Title`, `Description`) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query))
        {
            int rows = 0;
            statement.setString(1, "Книги");
            statement.setString(2, "Категорія про нові книги");
            rows = statement.executeUpdate();
            statement.setString(1, "Комп'ютери");
            statement.setString(2, "Категорія про нові комп'ютери");
            rows = statement.executeUpdate();
            statement.setString(1, "Картини");
            statement.setString(2, "Категорія про нові картини");
            rows = statement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private static void SeedProducts(Connection conn)
    {
        String query = "INSERT INTO `news` (`Title`, `Text`, `DateCreated`, `CategoryId`) VALUES (?, ?, ? ,?)";
        try (PreparedStatement statement = conn.prepareStatement(query))
        {
            int testId = GetCategoryId(conn);

            statement.setString(1, "Тестова новина");
            statement.setString(2, "Опис тестової новини");
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setInt(4, testId);

            int rows = statement.executeUpdate();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private static int GetCategoryId(Connection conn)
    {
        String query = "SELECT * FROM `categories` ";
        try (PreparedStatement statement = conn.prepareStatement(query))
        {
            ResultSet res = statement.executeQuery();
            res.next();
            int id = res.getInt("Id");
            return id;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public static Boolean isEmpty(String str)
    {
        String query = "SELECT * FROM `categories`";
        try(Connection conn = DriverManager.getConnection(str, "root", ""))
        {
            try(PreparedStatement statement = conn.prepareStatement(query))
            {
                ResultSet res = statement.executeQuery();
                boolean result = res.next();
                return !result;
            }catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
                return false;
            }
            catch(Throwable th)
            {
                System.out.println(th.getMessage());
                return false;

            }
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
