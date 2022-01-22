package main;

import services.DbHelper;

public class Program {
    public static void main(String[] args) {
        String connString = "jdbc:mariadb://localhost:3306/vpu911db";
        DbHelper.SeedAll(connString);

        // CRUD для категорій
        //DbCategoryService categoryService = new DbCategoryService();

        //categoryService.InsertIntoDb(connString);
        //categoryService.ShowAllItems(connString);
        //categoryService.UpdateDb(connString);
        //categoryService.ShowAllItems(connString);
        //categoryService.DeleteDb(connString);
        //categoryService.ShowAllItems(connString);

        // CRUD для новин
        //DbNewsService newsService = new DbNewsService();

        /*newsService.ShowAllItems(connString);
        newsService.InsertIntoDb(connString);
        newsService.ShowAllItems(connString);
        newsService.UpdateDb(connString);
        newsService.ShowAllItems(connString);
        newsService.DeleteDb(connString);
        newsService.ShowAllItems(connString);*/

    }
}
