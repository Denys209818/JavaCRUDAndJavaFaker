package services;

import models.Product;

import java.util.ArrayList;

public class DbServices {
    public static void ShowAllItems(ArrayList<Product> products)
    {
        for(Product item : products)
        {
            System.out.println(item);
        }
    }
}
