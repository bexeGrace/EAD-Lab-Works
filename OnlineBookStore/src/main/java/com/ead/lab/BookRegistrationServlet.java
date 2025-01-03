package com.ead.lab;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;

// Betsegaw Mesele
@RequestMapping("/addBook")
public class BookRegistrationServlet {
    private static final String query = "insert into books(title, author, price) values(?, ?, ?)";
    @Setter
    private DBConnectionManager db;

    @GetMapping
    @ResponseBody
    public String showForm() {
        return "<html>"
                + "<head>"
                + "<title>Task Registration</title>"
                + "<style>"
                + "body {"
                + "    font-family: Arial, sans-serif;"
                + "    background-color: #f9f9f9;"
                + "    margin: 0;"
                + "    padding: 20px;"
                + "}"
                + ".form-container {"
                + "    max-width: 600px;"
                + "    margin: 50px auto;"
                + "    padding: 20px;"
                + "    background: #ffffff;"
                + "    border-radius: 8px;"
                + "    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);"
                + "}"
                + "h2 {"
                + "    text-align: center;"
                + "    color: #333;"
                + "}"
                + "form {"
                + "    display: flex;"
                + "    flex-direction: column;"
                + "    gap: 15px;"
                + "}"
                + "label {"
                + "    font-weight: bold;"
                + "    color: #555;"
                + "}"
                + "input[type='text'], input[type='date'] {"
                + "    padding: 10px;"
                + "    font-size: 1em;"
                + "    border: 1px solid #ccc;"
                + "    border-radius: 4px;"
                + "    width: 100%;"
                + "    box-sizing: border-box;"
                + "}"
                + "input[type='submit'] {"
                + "    padding: 10px;"
                + "    font-size: 1em;"
                + "    color: #fff;"
                + "    background-color: #007bff;"
                + "    border: none;"
                + "    border-radius: 4px;"
                + "    cursor: pointer;"
                + "    transition: background-color 0.3s;"
                + "}"
                + "input[type='submit']:hover {"
                + "    background-color: #0056b3;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='form-container'>"
                + "<h2>Task Registration Form</h2>"
                + "<form method='post' action='/OnlineBookstore/books/addBook'>"
                + "<label for='title'>Title:</label>"
                + "<input type='text' id='title' name='title' placeholder='Enter book title'>"
                + "<label for='author'>Author:</label>"
                + "<input type='text' id='author' name='author' placeholder='Enter the name or the Author'>"
                + "<label for='price'>Price:</label>"
                + "<input type='number' id='price' name='price'>"
                + "<input type='submit' value='Add Book'>"
                + "</form>"
                + "</div>"
                + "</body>"
                + "</html>";
    }


    @PostMapping
    @ResponseBody
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam int price) {
        db.openConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setLong(3, price);
            int count = ps.executeUpdate();
            db.closeConnection();
            if (count == 1) {
                return "Book Registration Successful";
            } else {
                return "Book Registration Failed";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Book Registration Failed";
        } finally {
            db.closeConnection();
        }
    }
}
